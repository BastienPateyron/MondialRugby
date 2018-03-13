package uca.mondialrugby.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by basti on 2/3/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "MONDIALRUGBY";
    private static final int DATABASE_VERSION = 11; /* A incrémenter quand on modifie cette classe */


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

    private static final String INSERT_STADES = "INSERT INTO STADE (ID_STADE, NOM_STADE, NUM_RUE, NOM_RUE, NOM_RUE, VILLE, CP, NB_PLACE) VALUES " +
            "(1, 'Marcel Michelin', 12, 'avenue michelin', 'Clermont-Ferrand', '63000', 20000)," +
            "(2, 'Stade Marcel-Deflandre', 15, 'rue musclor', 'La Rochelle', '17000', 16000)," +
		    "(3, 'Stade Mayol', 1, 'Quai Joseph Lafontan', 'Toulon', '83000', 18200);";

    // insert


    /******** Personne ********/
    private static final String INSERT_PERSONNES = "INSERT INTO PERSONNE (PAYS_PERSONNE, NUMERO, NOM_PERSONNE, PRENOM_PERSONNE, DATE_NAISSANCE) VALUES " +
		    "('France', 1, 'LEDUC',     'Bruce', '03/13/1988'),"        +
		    "('France', 2, 'DULONG',    'Mikael', '03/13/1988'),"       +
		    "('France', 3, 'PELUS',     'George', '03/13/1988'),"       +
		    "('France', 4, 'BENTON',    'Pierre', '03/13/1988'),"       +
		    "('France', 5, 'DUPONT',    'Dylan', '03/13/1988'),"        +
		    "('France', 6, 'MAILLE',    'Paul', '03/13/1988'),"         +
		    "('France', 7, 'PARAT',     'Morgan', '03/13/1988'),"       +
		    "('France', 8, 'LENEUF',    'Henri', '03/13/1988'),"        +
		    "('France', 9, 'MELET',     'Pele', '03/13/1988'),"         +
		    "('France', 10, 'BARBOSSE', 'Jean', '03/13/1988'),"         +
		    "('France', 11, 'COURBET',  'Theo', '03/13/1988'),"         +
		    "('France', 12, 'CELUS',    'Kevin', '03/13/1988'),"        +
		    "('France', 13, 'PIQUET',   'Moris', '03/13/1988'),"        +
		    "('France', 14, 'TULIS',    'Patrick', '03/13/1988'),"      +
		    "('France', 15, 'RANDET',   'Eric', '03/13/1988'),"         +
		    
		    "('Australie', 1, 'WILLIS', 'Bruce', '03/13/1988'),"        +
		    "('Australie', 2, 'LETNER', 'Bill', '03/13/1988'),"         +
		    "('Australie', 3, 'BOTER',  'Spencer', '03/13/1988'),"      +
		    "('Australie', 4, 'WILKINS','Stan', '03/13/1988'),"         +
		    "('Australie', 5, 'HARPER', 'Ben', '03/13/1988'),"          +
		    "('Australie', 6, 'KOLE',   'Chris', '03/13/1988'),"        +
		    "('Australie', 7, 'DENVER', 'Joe', '03/13/1988'),"          +
		    "('Australie', 8, 'MILES',  'Price', '03/13/1988'),"        +
		    "('Australie', 9, 'SINK',   'Fabian', '03/13/1988'),"       +
		    "('Australie', 10, 'TRANT', 'Merryn', '03/13/1988'),"       +
		    "('Australie', 11, 'STARK', 'Tony', '03/13/1988'),"         +
		    "('Australie', 12, 'JIGHS', 'Killian', '03/13/1988'),"      +
		    "('Australie', 13, 'FALOUT','Shelter', '03/13/1988'),"      +
		    "('Australie', 14, 'MOORE', 'Morris', '03/13/1988'),"       +
		    "('Australie', 15, 'PRICE', 'Tag', '03/13/1988');";

    /******** Poste ***********/

    private static final String INSERT_POSTES = "INSERT INTO POSTE (NUMERO, LIBELLE_POSTE)VALUES " +
            "(1, 'Première Ligne'),"        +
            "(2, 'Première Ligne'),"        +
            "(3, 'Première Ligne'),"        +
            "(4, 'Deuxième Ligne'),"        +
            "(5, 'Deuxième Ligne'),"        +
            "(6, 'Troisième Ligne'),"       +
            "(7, 'Troisième Ligne'),"       +
            "(8, 'Troisième Ligne'),"       +
            "(9, 'Demi de mêlée'),"         +
            "(10, 'Demi d'ouverture'),"     +
            "(12, 'Trois-quart centre'),"   +
            "(13, 'Trois-quart centre'),"   +
            "(11, 'Aillier'),"              +
            "(14, 'Aillier'),"              +
            "(15, 'Arrière');";

    /******** Jouer ***********/
    
    



    /******** Equipe **********/
    // PAYS
	// SURNOM_EQUIPE
	private static String INSERT_EQUIPES = "INSERT INTO EQUIPE (PAYS, SURNOM_EQUIPE) VALUES " +
		    "('FRANCE',     'Les Bleus'),"          +
		    "('ANGLETERRE', 'Les Rosbifs'),"    +
		    "('IRLANDE',    'Les Verts');";
    


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


        // Valeurs par défaut
        db.execSQL(INSERT_STADES);
        db.execSQL(INSERT_POSTES);
        db.execSQL(INSERT_EQUIPES);


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
