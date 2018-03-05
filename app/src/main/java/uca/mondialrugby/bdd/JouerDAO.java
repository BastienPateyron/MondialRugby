package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uca.mondialrugby.Classe.Equipe;
import uca.mondialrugby.Classe.Jouer;
import uca.mondialrugby.Classe.Match;
import uca.mondialrugby.Classe.Stade;

/**
 * Created by watson on 04/03/2018.
 */

public class JouerDAO extends SQLiteDBHelper {
    private static final String TABLE_JOUER = "JOUER";
    private static final String COL_PAYS = "PAYS_ID";
    private static final String COL_MATCH = "MATCH_ID";
    private static final String COL_SCORE = "SCORE";

    private Match match;
    private Equipe equipe;
    private MatchDAO matchDAO;
    private EquipeDAO equipeDAO;

    public JouerDAO(Context context) {
        super(context);
    }

    /*insertJouer*/
    public boolean insertJouer( Jouer jouer){
        ContentValues values = new ContentValues();

        values.put(COL_PAYS,jouer.getIdPays().getPays());
        values.put(COL_MATCH,jouer.getMatch().getIdMatch());
        values.put(COL_SCORE,jouer.getScore());

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

        equipe = equipeDAO.retrieveEquipe(cursor.getInt(0));
        match = matchDAO.retrieveMatch(cursor.getInt(1),cursor.getInt(0), context); // TODO : risque de beug
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

                equipe = equipeDAO.retrieveEquipe(cursor.getInt(0));
                match = matchDAO.retrieveMatch(cursor.getInt(1),cursor.getInt(0),context); // TODO : rique de beug 2eme cursor
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

    public void updateEquipe(Jouer jouer){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PAYS,jouer.getIdPays().getPays());
        values.put(COL_MATCH,jouer.getMatch().getIdMatch());
        values.put(COL_SCORE,jouer.getScore());

        db.update(TABLE_JOUER, values, COL_PAYS + "="+ equipe.getPays(), null);
        db.close();
    }


    // deleteEquipe
    public void deleteClient(Context context, int idEquipe , int idMatch)
    {


        SQLiteDatabase db = this.getWritableDatabase();


        db.delete(TABLE_JOUER, COL_PAYS + "=" + idEquipe + COL_MATCH  + "=" + idMatch , null);

        db.close();
    }





}// fin JouerDAO
