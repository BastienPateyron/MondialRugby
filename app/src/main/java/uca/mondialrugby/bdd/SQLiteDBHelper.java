package uca.mondialrugby.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by basti on 2/3/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "MONDIALRUGBY";
    private static final int DATABASE_VERSION = 1; /* A incrémenter quand on modifie cette classe */


    /* Tables */
    // TODO Creer tables

    /* Inserts */
    // TODO Creer jeux d'essais



    /* Constructeur */
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* Methodes */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Implémenter onCreate
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Implémenter onUpgrade
    }
}
