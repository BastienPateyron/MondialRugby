package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uca.mondialrugby.classes.Match;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Stade;

/**
 * Created by watson on 04/03/2018.
 */

public class MatchDAO extends SQLiteDBHelper {
    private static final String TABLE_MATCH = "MATCH";
    private static final String COL_ID = "ID_MATCH";
    private static final String COL_STADE = "ID_STADE";
    private static final String COL_PERSONNE = "ID_PERSONNE";
    private static final String COL_DATE = "DATE_MATCH ";

    private Personne personne;
    private Stade stade;
    /******** Match
     * @param context***********/
    public MatchDAO(Context context) {
        super(context);
    }

    /*insertMatch*/
    public boolean insertMatch( Match match){
        ContentValues values = new ContentValues();

        values.put(COL_ID,match.getIdMatch());
        values.put(COL_STADE,match.getStade().getId());
        values.put(COL_PERSONNE,match.getPersonne().getId());
        values.put(COL_DATE,match.getDateMatch());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean insertSuccessful = db.insert(TABLE_MATCH,null,values) > 0;

        return insertSuccessful;
    }

    public Match retrieveMatch(int idPays, int idMatch, Context context){
        SQLiteDatabase db = this.getReadableDatabase();
       PersonneDAO personneDAO = new PersonneDAO(context);
       StadeDAO stadeDAO = new StadeDAO(context);



        /* Requete */
        Cursor cursor = db.query(TABLE_MATCH, // Nom de table
                new String[] {COL_ID, COL_STADE, COL_PERSONNE, COL_DATE},
                COL_ID + "=?",
                new String[] {String.valueOf(idPays), String.valueOf(idMatch)},
                null, null, null, null); // Options
        if(cursor != null)
            cursor.moveToFirst();

        personne = personneDAO.retrievePersonne(cursor.getInt(0),context);
        stade = stadeDAO.retrieveStade(cursor.getInt(1));
        Match match = new Match (
                cursor.getInt(0),
                stade,
                personne,
                cursor.getString(3) );
        ;
        db.close();
        return match;
    }

    /* getAllJouer*/
    public ArrayList<Match> getAllMatch(Context context){
        SQLiteDatabase db = this.getReadableDatabase();
       PersonneDAO personneDAO = new PersonneDAO (context);
       StadeDAO stadeDAO = new StadeDAO(context);

        ArrayList<Match> listMatch = new ArrayList<>();
        String query = "SELECT * FROM MATCH;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {

                stade = stadeDAO.retrieveStade(cursor.getInt(0));
                personne = personneDAO.retrievePersonne(cursor.getInt(1),context);
                Match jouer = new Match (
                        cursor.getInt(0),
                        stade,
                        personne,
                        cursor.getString(3)
                );

                listMatch.add(jouer);
            } while(cursor.moveToNext());
        }
        db.close();
        return listMatch;
    }

    public void updateMatch(Match match){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,match.getIdMatch());
        values.put(COL_STADE,match.getStade().getId());
        values.put(COL_PERSONNE,match.getPersonne().getId());
        values.put(COL_DATE,match.getDateMatch());
        db.update(TABLE_MATCH, values, COL_ID+ "="+ match.getIdMatch(), null);
        db.close();
    }


    // deleteEquipe
    public void deleteClient(Context context, int idEquipe , int idMatch)
    {

        //TODO : supprimer avec double clé

        SQLiteDatabase db = this.getWritableDatabase();


        db.delete(TABLE_MATCH, COL_ID + "=" + idEquipe, null);

        db.close();
    }




}
