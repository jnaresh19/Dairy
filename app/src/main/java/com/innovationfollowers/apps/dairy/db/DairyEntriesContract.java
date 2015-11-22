package com.innovationfollowers.apps.dairy.db;

import android.provider.BaseColumns;

/**
 * Created by Naresh on 21-11-2015.
 */
public final class DairyEntriesContract {

     public void DairyEntriesContract()
     {

     }

    public static abstract class DairyEntries implements BaseColumns {
        public static final String TABLE_NAME = "DairyEntires";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "entrydate";
        public static final String COLUMN_NAME_IMAGES = "images";

    }
}
