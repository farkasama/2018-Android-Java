package com.example.etien.examexo2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class MyContentProvider extends ContentProvider {
    /* version de la base de donnees */
    public static final int VERSION = 9;
    /* authority à ajuster selon besoin */
    public static final String AUTHORITY = "fr.etiennejouanne.examexo2";
    private static final int EVT = 1;
    private static final int DAT = 2;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private BD sqlitehelper;

    static {
        matcher.addURI(AUTHORITY, "all_events", EVT);
        matcher.addURI(AUTHORITY, "single_event/#", DAT);
    }

    public MyContentProvider() {
        super();
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = sqlitehelper.getWritableDatabase();
        //long l = ContentUris.parseId(uri);
        return db.delete(BD.TABLE, "_id = " + uri.getLastPathSegment() , null);

        //new String[]{Long.toString(l)});
        // Implement this to handle requests to delete one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = sqlitehelper.getWritableDatabase();
        long id = db.insert(BD.TABLE, null, values);
        Uri.Builder builder = new Uri.Builder();
        return builder.authority(AUTHORITY)
                .appendPath("key").appendPath(Long.toString(id)).build();

    }

    @Override
    public boolean onCreate() {
        sqlitehelper = new BD(getContext(), VERSION);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = sqlitehelper.getReadableDatabase();
        int code = matcher.match(uri);
        Cursor cursor;
        String key;

        switch (code) {
            case EVT:
                return db.query(BD.TABLE,
                        new String[]{"_id", BD.EVENT},
                        null, null, null,
                        null, null);


            case DAT:
                key = uri.getLastPathSegment();

                return db.query(BD.TABLE,
                        new String[]{BD.YEAR, BD.MONTH, BD.DAY},
                        "_id =" + key, null,
                        null, null, null);


        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
