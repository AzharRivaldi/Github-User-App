package com.azhar.githubusers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.azhar.githubusers.database.DatabaseContract.FavoriteColoumn.TABLE_NAME;

/**
 * Created by Azhar Rivaldi on 23-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favoritedb";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_FAVORITE = String.format(
            "CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavoriteColoumn.ID,
            DatabaseContract.FavoriteColoumn.TITLE,
            DatabaseContract.FavoriteColoumn.IMAGE,
            DatabaseContract.FavoriteColoumn.URL
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
