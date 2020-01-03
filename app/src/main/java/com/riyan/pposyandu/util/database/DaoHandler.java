package com.riyan.pposyandu.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DaoHandler {
    public static DaoSession getInstance(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "dataanak_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }
}
