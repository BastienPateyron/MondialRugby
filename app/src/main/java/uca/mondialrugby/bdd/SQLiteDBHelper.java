package uca.mondialrugby.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by basti on 2/3/2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "MONDIALRUGBY";
    private static final int DATABASE_VERSION = 40;      /* A incrémenter quand on modifie cette classe */


	private Context context;
    /* Tables */

    // TODO Ajouter les ON DELETE CASCADE !!
	
    private static final String CREATE_TABLE_PERSONNE = "CREATE TABLE PERSONNE" +
            "(" +
            "ID_PERSONNE  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "PAYS_PERSONNE TEXT NOT NULL        REFERENCES EQUIPE(PAYS)     ON DELETE CASCADE   ON UPDATE CASCADE,"  +
            "NUMERO INTEGER NOT NULL            REFERENCES POSTE(NUMERO)    ON DELETE CASCADE, "  +
            "NOM_PERSONNE TEXT NOT NULL," +
            "PRENOM_PERSONNE TEXT NOT NULL," +
            "DATE_NAISSANCE TEXT NOT NULL" +
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


    // On met les valeurs par défaut à {null} dans le cas ou on supprime un stade
    private static final String CREATE_TABLE_MATCHS = "CREATE TABLE MATCHS" +
            "(" +
            "ID_MATCH INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "ID_STADE INTEGER NOT NULL      REFERENCES STADE(ID_STADE)          ON DELETE CASCADE," +
            "ID_PERSONNE INTEGER NOT NULL   REFERENCES PERSONNE(ID_PERSONNE)    ON DELETE CASCADE," +
            "DATE_MATCH TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_STADE = "CREATE TABLE STADE" +
            "(" +
            "ID_STADE  INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL ," +
            "NOM_STADE TEXT NOT NULL  ,"  +
            "NUM_RUE TEXT NOT NULL    ,"  +
            "NOM_RUE TEXT NOT NULL    ,"  +
            "VILLE TEXT NOT NULL      ," +
            "CP TEXT NOT NULL         ," +
            "NB_PLACE INTEGER NOT NULL" +
            ");";

    private static final String CREATE_TABLE_JOUER = "CREATE TABLE JOUER" +
            "(" +
            "PAYS  TEXT  NOT NULL           REFERENCES EQUIPE(PAYS)     ON DELETE CASCADE    ON UPDATE CASCADE," +
            "ID_MATCH INTEGER NOT NULL      REFERENCES STADE(ID_STADE)  ON DELETE CASCADE,"  +
            "SCORE INTEGER DEFAULT NULL"  +
            ");";



    /* Inserts */
    /******** Stade ********/

    private static final String INSERT_STADES =
		    "INSERT INTO STADE (NOM_STADE, NUM_RUE, NOM_RUE, VILLE, CP, NB_PLACE) VALUES " +
            "('Marcel Michelin', 12, 'avenue michelin', 'Clermont-Ferrand', '63000', 20000)," +
            "('Marcel-Deflandre', 15, 'rue musclor', 'La Rochelle', '17000', 16000)," +
		    "('Mayol', 1, 'Quai Joseph Lafontan', 'Toulon', '83000', 18200);";
	
    /******** Personne ********/
    private static final String INSERT_PERSONNES =
		    "INSERT INTO PERSONNE (PAYS_PERSONNE, NUMERO, NOM_PERSONNE, PRENOM_PERSONNE, DATE_NAISSANCE) VALUES " +
		    // Arbitres
		    "('FRANCE',     0,  'LEPRAT',   'Fabrice',  '03/11/1987'),"     +
		    "('AUSTRALIE',  0,  'REEVES',   'Mathiew',  '04/09/1978'),"     +
		    "('IRLANDE',    0,  'TORTELI',  'Armando',  '03/04/1983'),"     +
		    
		    // Joueurs
		    "('FRANCE', 1, 'LEDUC',     'Bruce',    '13/03/1988'),"         +
		    "('FRANCE', 2, 'DULONG',    'Mikael',   '13/03/1988'),"         +
		    "('FRANCE', 3, 'PELUS',     'George',   '13/03/1988'),"         +
		    "('FRANCE', 4, 'BENTON',    'Pierre',   '13/03/1988'),"         +
		    "('FRANCE', 5, 'DUPONT',    'Dylan',    '13/03/1988'),"         +
		    "('FRANCE', 6, 'MAILLE',    'Paul',     '13/03/1988'),"         +
		    "('FRANCE', 7, 'PARAT',     'Morgan',   '13/03/1988'),"         +
		    "('FRANCE', 8, 'LENEUF',    'Henri',    '13/03/1988'),"         +
		    "('FRANCE', 9, 'MELET',     'Pele',     '13/03/1988'),"         +
		    "('FRANCE', 10, 'BARBOSSE', 'Jean',     '13/03/1988'),"         +
		    "('FRANCE', 11, 'COURBET',  'Theo',     '13/03/1988'),"         +
		    "('FRANCE', 12, 'CELUS',    'Kevin',    '13/03/1988'),"         +
		    "('FRANCE', 13, 'PIQUET',   'Moris',    '13/03/1988'),"         +
		    "('FRANCE', 14, 'TULIS',    'Patrick',  '13/03/1988'),"         +
		    "('FRANCE', 15, 'RANDET',   'Eric',     '13/03/1988'),"         +
		    
		    "('ANGLETERRE', 1, 'BOND',  'James',    '13/03/1988'),"         +
		    "('ANGLETERRE', 2, 'CRAIG', 'Daniel',   '13/03/1988'),"         +
		    "('ANGLETERRE', 3, 'MOORE', 'Roger',    '13/03/1988'),"         +
		    "('ANGLETERRE', 4, 'CONERY','Shean',    '13/03/1988'),"         +
		    
		    "('AUSTRALIE', 1, 'WILLIS', 'Bruce',    '13/03/1988'),"         +
		    "('AUSTRALIE', 2, 'LETNER', 'Bill',     '13/03/1988'),"         +
		    "('AUSTRALIE', 3, 'BOTER',  'Spencer',  '13/03/1988'),"         +
		    "('AUSTRALIE', 4, 'WILKINS','Stan',     '13/03/1988'),"         +
		    "('AUSTRALIE', 5, 'HARPER', 'Ben',      '13/03/1988'),"         +
		    "('AUSTRALIE', 6, 'KOLE',   'Chris',    '13/03/1988'),"         +
		    "('AUSTRALIE', 7, 'DENVER', 'Joe',      '13/03/1988'),"         +
		    "('AUSTRALIE', 8, 'MILES',  'Price',    '13/03/1988'),"         +
		    "('AUSTRALIE', 9, 'SINK',   'Fabian',   '13/03/1988'),"         +
		    "('AUSTRALIE', 10, 'TRANT', 'Merryn',   '13/03/1988'),"         +
		    "('AUSTRALIE', 11, 'STARK', 'Tony',     '13/03/1988'),"         +
		    "('AUSTRALIE', 12, 'JIGHS', 'Killian',  '13/03/1988'),"         +
		    "('AUSTRALIE', 13, 'FALOUT','Shelter',  '13/03/1988'),"         +
		    "('AUSTRALIE', 14, 'MOORE', 'Morris',   '13/03/1988'),"         +
		    "('AUSTRALIE', 15, 'PRICE', 'Tag',      '13/03/1988');";

    /******** Poste ***********/

    private static final String INSERT_POSTES = "INSERT INTO POSTE (NUMERO, LIBELLE_POSTE) VALUES " +
		    "(0, 'Arbitre'),"               +
            "(1, 'Première Ligne'),"        +
            "(2, 'Première Ligne'),"        +
            "(3, 'Première Ligne'),"        +
            "(4, 'Deuxième Ligne'),"        +
            "(5, 'Deuxième Ligne'),"        +
            "(6, 'Troisième Ligne'),"       +
            "(7, 'Troisième Ligne'),"       +
            "(8, 'Troisième Ligne'),"       +
            "(9, 'Demi de mêlée'),"         +
            "(10, 'Demi d\'\'ouverture'),"  + // Double cote pour insérer une cote simple dans SQLite
            "(12, 'Trois-quart centre'),"   +
            "(13, 'Trois-quart centre'),"   +
            "(11, 'Aillier'),"              +
            "(14, 'Aillier'),"              +
            "(15, 'Arrière');";

    /******** Jouer ***********/
	private static String INSERT_JOUER = "INSERT INTO JOUER (PAYS, ID_MATCH, SCORE) VALUES " +
		    // Match 1
		    "('FRANCE',     1, 27),"    +
		    "('AUSTRALIE',  1, 13),"    +
		    
		    // Match 2
		    "('FRANCE',     2, 17),"    +
		    "('ANGLETERRE', 2, 17),"    +
		    
		    // Match 3
		    "('FRANCE',     3, 31),"    +
		    "('AUSTRALIE',  3, 9),"     +
		    
		    // Match 4
		    "('ANGLETERRE', 4, 17),"    +
		    "('AUSTRALIE', 4, 7)," +
		    
		    // 5
		    "('ANGLETERRE', 5, 7)," +
		    "('FRANCE', 5, 21)," +
		    
		    "('AUSTRALIE', 6, '')," +
		    "('FRANCE', 6, '')," +
		    
		    "('ANGLETERRE', 7, '')," +
		    "('FRANCE', 7, '')," +
		    
		    "('AUSTRALIE', 8, '')," +
		    "('ANGLETERRE', 8, '')" +
		    ";";
		    
	
	
	
	
	/******** Equipe **********/
	private static String INSERT_EQUIPES = "INSERT INTO EQUIPE (PAYS, SURNOM_EQUIPE) VALUES " +
		    "('FRANCE',     'Les Bleus'),"          +
		    "('ANGLETERRE', 'Les Rosbifs'),"        +
		    "('AUSTRALIE',  'Les Wallabies'),"      +
		    "('IRLANDE',    'Les Verts');";
    


    /******** Match ***********/
    private static String INSERT_MATCHS = "INSERT INTO MATCHS (ID_STADE, ID_PERSONNE, DATE_MATCH) VALUES " +
		    "(1, 1, '13/03/2018')," +
		    "(1, 2, '14/03/2018')," +
		    "(1, 1, '15/03/2018')," +
		    "(2, 3, '16/03/2018')," +
		   
		    // Matchs non joués
		    "(2, 2, '17/03/2018')," +
		    "(2, 2, '18/03/2018')," +
		    "(3, 3, '19/03/2018')," +
		    "(3, 3, '20/03/2018')" +
		    ";";


    
    
    
    /* VUES */
	/* Matchs joués*/
	private static String MATCHS_FINIS_VIEW =
		    "CREATE VIEW IF NOT EXISTS MATCHSFINIS AS " +
		    "SELECT A.PAYS A_PAYS, A.SCORE A_SCORE, B.SCORE B_SCORE, B.PAYS  B_PAYS " +
		    "FROM JOUER A JOIN JOUER B USING (ID_MATCH) " +
		    "WHERE A.PAYS NOT LIKE B.PAYS" +
		    "AND A.SCORE;";
	
	/* Victoires
	TODO: Comment on gère les egalités ? ? ?
	ca marche pas*/
//	select *
//	from matchsFinis
//	where  a_score > b_score;

	
	/* On ordonne les pays en fonction du nombre de victoires puis du score total */
	
	/* On compte le score total de chaque pays */
	
	/* On compte le nombre de victoires par pays */
	
	
	/* Constructeur */
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    
    public Context getContext(){ return this.context; }

	@Override
    public SQLiteDatabase getReadableDatabase(){
		SQLiteDatabase db = super.getReadableDatabase();
		db.setForeignKeyConstraintsEnabled(true);
    	return db;
	}
	
	@Override
	public SQLiteDatabase getWritableDatabase(){
		SQLiteDatabase db = super.getReadableDatabase();
		db.setForeignKeyConstraintsEnabled(true);
		return db;
	}
	
	
    /* Methodes */
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
        db.execSQL(CREATE_TABLE_PERSONNE);
        db.execSQL(CREATE_TABLE_POSTE);
        db.execSQL(CREATE_TABLE_MATCHS);
        db.execSQL(CREATE_TABLE_STADE);
        db.execSQL(CREATE_TABLE_EQUIPE);
        db.execSQL(CREATE_TABLE_JOUER);


        // Valeurs par défaut
	    db.execSQL(INSERT_PERSONNES);
        db.execSQL(INSERT_POSTES);
        db.execSQL(INSERT_MATCHS);
	    db.execSQL(INSERT_STADES);
	    db.execSQL(INSERT_EQUIPES);
	    db.execSQL(INSERT_JOUER);
     
     
	    // Création des Vues
	    // TODO


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   
	    
        db.execSQL("DROP TABLE IF EXISTS " + "PERSONNE");
        db.execSQL("DROP TABLE IF EXISTS " + "POSTE");
        db.execSQL("DROP TABLE IF EXISTS " + "EQUIPE");
        db.execSQL("DROP TABLE IF EXISTS " + "MATCHS");
        db.execSQL("DROP TABLE IF EXISTS " + "JOUER");
        db.execSQL("DROP TABLE IF EXISTS " + "STADE");
        
        onCreate(db);
    }

}
