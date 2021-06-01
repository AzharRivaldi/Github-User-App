package com.azhar.githubusers.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Azhar Rivaldi on 23-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class DatabaseContract {

    public static final String PACKAGE = "com.azhar.githubusers";
    public static final String SCHEME = "content";

    public static final class FavoriteColoumn implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String ID = "id";
        public static final String TITLE = "login";
        public static final String IMAGE = "avatar_url";
        public static final String URL = "html_url";
        public static final Uri FAVORITE_URI = new Uri.Builder().scheme(SCHEME).authority(PACKAGE).appendPath(TABLE_NAME).build();
    }

    public static String getFavorite(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    public static int getIntFavorite(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndex(column));
    }

}
