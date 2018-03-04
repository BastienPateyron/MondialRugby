package uca.mondialrugby.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by basti on 2/3/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "MONDIALRUGBY";
    private static final int DATABASE_VERSION = 10; /* A incrémenter quand on modifie cette classe */


    /* Tables */
    // TODO Creer tables Personne | poste | match | jouer | stade | equipe

    private static final String CREATE_TABLE_PERSONNE = "CREATE TABLE PERSONNE" +
            "(" +
            "ID_PERSONNE  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "PAYS_PERSONNE TEXT NOT NULL,"  +
            "NUMERO INTEGER NOT NULL, "  +
            "NOM_PERSONNE TEXT NOT NULL," +
            "PRENOM_PERSONNE TEXT NOT NULL," +
            "DATE_NAISSANCE TEXT NOT NULL," +
            "FOREIGN KEY(PAYS_PERSONNE) REFERENCES EQUIPE(PAYS)" +
            "FOREIGN KEY(NUMERO) REFERENCES POSTE(NUMERO)" +
            ");";

    private static final String CREATE_TABLE_POSTE = "CREATE TABLE POSTE" +
            "(" +
            "NUMERO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "LIBELLE_POSTE TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_EQUIPE = "CREATE TABLE EQUIPE" +
            "(" +
            "PAYS TEXT NOT NULL PRIMARY KEY  NOT NULL," +
            "SURNOM_EQUIPE TEXT NOT NULL" +
            ");";


    private static final String CREATE_TABLE_MATCH1 = "CREATE TABLE MATCH" +
            "(" +
            "ID_MATCH INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "ID_STADE INTEGER NOT NULL," +
            "ID_PERSONNE INTEGER NOT NULL," +
            "DATE_MATCH TEXT NOT NULL," +
            "FOREIGN KEY(ID_PERSONNE) REFERENCES PERSONNE(ID_PERSONNE)," +
            "FOREIGN KEY(ID_STADE) REFERENCES STADE(ID_STADE)" +
            ");";

    private static final String CREATE_TABLE_STADE = "CREATE TABLE STADE" +
            "(" +
            "ID_STADE  INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL," +
            "NOM_STADE TEXT NOT NULL,"  +
            "NUM_RUE TEXT NOT NULL,"  +
            "NOM_RUE TEXT NOT NULL,"  +
            "VILLE TEXT NOT NULL," +
            "CP TEXT NOT NULL," +
            "NB_PLACE INTEGER NOT NULL" +

            ");";

    private static final String CREATE_TABLE_JOUER = "CREATE TABLE JOUER" +
            "(" +
            "PAYS  INTEGER  NOT NULL," +
            "ID_MATCH INTEGER NOT NULL,"  +
            "SCORE TEXT NOT NULL,"  +
            "FOREIGN KEY(PAYS) REFERENCES EQUIPE(PAYS)," +
            "FOREIGN KEY(ID_MATCH) REFERENCES STADE(ID_MATCH)" +
            ");";



    /* Inserts */
    /******** Stade ********/

    private static final String INSERT_CLERMONT = "INSERT INTO STADE VALUES " +
            "(1, ' Marcel Michelin', 12, 'avenue', 'Clermont-Ferrand', '63000', 20000);";

    // insert


    /******** Personne ********/


    /******** Poste ***********/

    private static final String INSERT_TALONNEUR = "INSERT INTO STADE VALUES " +
            "(2, 'Talloneur ');";

    /******** Jouer ***********/



    /******** Equipe **********/



    /******** Match ***********/


     /* Constructeur */
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /* Methodes */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSONNE);
        db.execSQL(CREATE_TABLE_POSTE);
        db.execSQL(CREATE_TABLE_MATCH1);
        db.execSQL(CREATE_TABLE_STADE);
        db.execSQL(CREATE_TABLE_EQUIPE);
        db.execSQL(CREATE_TABLE_JOUER);


        db.execSQL(INSERT_CLERMONT);
        db.execSQL(INSERT_TALONNEUR);


    }

    //TODO : y a un ordre ????????? // ça veut pas de match
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "PERSONNE");
        db.execSQL("DROP TABLE IF EXISTS " + "POSTE");
        db.execSQL("DROP TABLE IF EXISTS " + "EQUIPE");
        db.execSQL("DROP TABLE IF EXISTS " + "MATCH1");
        db.execSQL("DROP TABLE IF EXISTS " + "JOUER");
        db.execSQL("DROP TABLE IF EXISTS " + "STADE");
    }
}
