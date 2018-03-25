package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Jouer;
import uca.mondialrugby.classes.Match;

import static android.content.ContentValues.TAG;

/**
 * Created by watson on 04/03/2018.
 */

public class JouerDAO extends SQLiteDBHelper {
    private static final String TABLE_JOUER = "JOUER";
    private static final String COL_PAYS = "PAYS";
    private static final String COL_MATCH = "ID_MATCH";
    private static final String COL_SCORE = "SCORE";

    private Match match;
    private Equipe equipe;
    private MatchDAO matchDAO;
    private EquipeDAO equipeDAO;

    public JouerDAO(Context context) {
        super(context);
    }

    
    //TODO
    /* getJouerOf
    * Cherche tout les occurences de la table jouer en fonction de de l'id du match ou de l'équipe (qui sont les seules tables admissibles */
    public ArrayList<Jouer> getAllJouerOf(String tableName, int id){
    	
    	tableName       = tableName.toUpperCase();                // Conversion en majuscules
	    String idName   = "";                                      // Contiendra le libellé de l'id de la table concernée
	    
	    if(tableName == "EQUIPE") idName        = "PAYS";
	    else if(tableName == "MATCHS") idName    = "ID_MATCH";
	    else Log.d(TAG, "getAllJouerOf: tableName invalide ! [" + tableName + "]");
	    
	    SQLiteDatabase db = this.getReadableDatabase();
	    equipeDAO = new EquipeDAO(super.getContext());
	    matchDAO = new MatchDAO(super.getContext());
	
	    ArrayList<Jouer> jouerArrayList = new ArrayList<>();
	    String query = "SELECT * FROM JOUER WHERE " + idName + " = " + id + ";";
	    Cursor cursor = db.rawQuery(query, null);
	
	    if (cursor.moveToFirst()){
		    do {
			    equipe = equipeDAO.retrieveEquipe(cursor.getString(0));
			    match = matchDAO.retrieveMatch(super.getContext(), cursor.getInt(1)); // Risque de bug 2e élément :: risque supprimé
			    Jouer jouer = new Jouer (
					    equipe,
					    match,
					    cursor.getInt(2)
			    );
			
			    jouerArrayList.add(jouer);
		    } while(cursor.moveToNext());
	    }
	    db.close();
	    return jouerArrayList;
    }
    
    /* Méthodes CRUD */
    /*createJouer*/
    public boolean createJouer(Jouer jouer){
        ContentValues values = new ContentValues();

        values.put(COL_PAYS,jouer.getIdPays().getPays());
        values.put(COL_MATCH,jouer.getMatch().getIdMatch());
//        values.put(COL_SCORE,jouer.getScore());       // Quand on cree un jouer le score est null

        SQLiteDatabase db = this.getWritableDatabase();
        boolean insertSuccessful = db.insert(TABLE_JOUER,null,values) > 0;

        return insertSuccessful;
    }

    public Jouer retrieveJouer(int idPays, int idMatch, Context context){
        SQLiteDatabase db = this.getReadableDatabase();
        equipeDAO = new EquipeDAO(context);
        matchDAO = new MatchDAO(context);


        //TODO : rajouter idMAtch : pas sur de moi ;
        /* Requete */
        Cursor cursor = db.query(TABLE_JOUER, // Nom de table
                new String[] {COL_PAYS, COL_MATCH, COL_SCORE},
                COL_PAYS + "=?" + COL_MATCH + "=?" ,
                new String[] {String.valueOf(idPays), String.valueOf(idMatch)},
                null, null, null, null); // Options
        if(cursor != null)
            cursor.moveToFirst();

        equipe = equipeDAO.retrieveEquipe(cursor.getString(0));
        match = matchDAO.retrieveMatch(context, cursor.getInt(1)); // TODO : risque de beug
        Jouer jouer = new Jouer (
                equipe,
                match,
                cursor.getInt(3) );
                ;
        db.close();
        return jouer;
    }

    /* getAllJouer*/
    public ArrayList<Jouer> getAllJouer(Context context){
        SQLiteDatabase db = this.getReadableDatabase();
        equipeDAO = new EquipeDAO(context);
        matchDAO = new MatchDAO(context);

        ArrayList<Jouer> listJouer = new ArrayList<>();
        String query = "SELECT * FROM JOUER;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {

                equipe = equipeDAO.retrieveEquipe(cursor.getString(0));
                match = matchDAO.retrieveMatch(context, cursor.getInt(1)); // TODO : rique de beug 2eme cursor
                Jouer jouer = new Jouer (
                        equipe,
                        match,
                        cursor.getInt(2)
                );

                listJouer.add(jouer);
            } while(cursor.moveToNext());
        }
        db.close();
        return listJouer;
    }

    public void updateJouer(Jouer jouer){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PAYS,jouer.getIdPays().getPays());
        values.put(COL_MATCH,jouer.getMatch().getIdMatch());
        values.put(COL_SCORE,jouer.getScore());

        db.update(
        		TABLE_JOUER, values,
		        COL_PAYS + "='"+ jouer.getIdPays().getPays() + "' AND " + COL_MATCH + "='" + jouer.getMatch().getIdMatch() + "'",
		        null);
        db.close();
    }


    // deleteJouer
    public void deleteJouer(Context context, int idEquipe , int idMatch)
    {


        SQLiteDatabase db = this.getWritableDatabase();


        db.delete(TABLE_JOUER, COL_PAYS + "=" + idEquipe + COL_MATCH  + "=" + idMatch , null);

        db.close();
    }





}// fin JouerDAO
