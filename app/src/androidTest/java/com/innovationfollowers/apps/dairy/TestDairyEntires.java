package com.innovationfollowers.apps.dairy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.innovationfollowers.apps.dairy.db.DairyEntriesContract;
import com.innovationfollowers.apps.dairy.db.DairyEntriesDbHelper;

/**
 * Created by Naresh on 21-11-2015.
 */
public class TestDairyEntires extends AndroidTestCase {



    public void testDairyEntriesDbCreated() {
        DairyEntriesDbHelper helper = new DairyEntriesDbHelper(mContext);
        SQLiteDatabase database = helper.getWritableDatabase();
        assertTrue(database.isOpen());
        database.close();
    }

    public void testInsertAndReadDairyEntry()
    {
        DairyEntriesDbHelper helper = new DairyEntriesDbHelper(mContext);
        SQLiteDatabase database = helper.getWritableDatabase();
        long id = helper.insertDairyEntry("Diwali", "Enjoyed Diwali", "12-11-15", "12,1,3", database);
        assertTrue(id != -1);

        Cursor cursor = helper.getAllDairyEntries(database);
        assertTrue(cursor.moveToFirst());

        int idColumnIndex = cursor.getColumnIndex(DairyEntriesContract.DairyEntries._ID);
        int dbId = cursor.getInt(idColumnIndex);

        int titleColumnIndex = cursor.getColumnIndex(DairyEntriesContract.DairyEntries.COLUMN_NAME_TITLE);
        String title = cursor.getString(titleColumnIndex);

        int descColumnIndex = cursor.getColumnIndex(DairyEntriesContract.DairyEntries.COLUMN_NAME_DESCRIPTION);
        String desc = cursor.getString(descColumnIndex);

        int dateColumnIndex = cursor.getColumnIndex(DairyEntriesContract.DairyEntries.COLUMN_NAME_DATE);
        String date = cursor.getString(dateColumnIndex);

        int imagesColumnIndex = cursor.getColumnIndex(DairyEntriesContract.DairyEntries.COLUMN_NAME_IMAGES);
        String images = cursor.getString(imagesColumnIndex);

        assertEquals(id, dbId);
        assertEquals("Diwali", title);
        assertEquals("Enjoyed Diwali", desc);
        assertEquals("12-11-15", date);
        assertEquals("12,1,3", images);

        database.close();
    }


    public void testDropDairyEntriesDb()
    {
        assertTrue(mContext.deleteDatabase(DairyEntriesDbHelper.DATABASE_NAME));

    }

    public void testDeleteDairyEntriesDb()
    {
        DairyEntriesDbHelper helper = new DairyEntriesDbHelper(mContext);
        SQLiteDatabase database = helper.getWritableDatabase();
        long id = helper.insertDairyEntry("Diwali", "Enjoyed Diwali", "12-11-15", "12,1,3", database);
        assertTrue(id != -1);

        int deletedId = helper.deleteDairyEntry(id, database);
        assertTrue(deletedId == 1);
    }


}
