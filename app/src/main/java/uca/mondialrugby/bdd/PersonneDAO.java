package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uca.mondialrugby.classes.Equipe;
import uca.mondialrugby.classes.Personne;
import uca.mondialrugby.classes.Poste;

/**
 * Created by watson on 28/02/2018.
 */

public class PersonneDAO extends SQLiteDBHelper {
    private static final String TABLE_PERSONNE = "PERSONNE";
    private static final String COL_ID = "ID_PERSONNE";
    private static final String COL_PERS_PAYS = "PAYS_PERSONNE";
    private static final String COL_POSTE = "NUMERO";
    private static final String COL_NOM = "NOM_PERSONNE";
    private static final String COL_PRENOM = "PRENOM_PERSONNE";
    private static final String COL_DATE_N = "DATE_NAISSANCE";

    public PersonneDAO(Context context) {
        super(context);
    }



    /*insertPersonne*/
    public boolean insertPersonne( Personne personne){
        ContentValues values = new ContentValues();

        values.put(COL_ID,personne.getId());
        values.put(COL_PERS_PAYS,personne.getPoste().getLibelle());
        values.put(COL_POSTE,personne.getEquipe().getPays());
        values.put(COL_NOM,personne.getNom());
        values.put(COL_PRENOM,personne.getPrenom());
        values.put(COL_DATE_N,personne.getDate());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean insertSuccessful = db.insert(TABLE_PERSONNE,null,values) > 0;

        return insertSuccessful;
    }

    public Personne retrievePersonne(int id, Context context){
        SQLiteDatabase db = this.getReadableDatabase();


        /* Requete */
        Cursor cursor = db.query(TABLE_PERSONNE, // Nom de table
                new String[] {COL_ID, COL_PERS_PAYS, COL_POSTE, COL_NOM, COL_PRENOM, COL_DATE_N},
                COL_ID + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null); // Options
        if(cursor != null)
            cursor.moveToFirst();

        EquipeDAO equipeDAO = new EquipeDAO(context);
        Equipe equipe = equipeDAO.retrieveEquipe(cursor.getString(1));
        if (equipe == null){
            equipe = new Equipe ();
            equipe.setSurmon("Null");
        }

        PosteDAO posteDAO = new PosteDAO (context);
        Poste poste = posteDAO.retrievePoste (cursor.getString(2));

        Personne personne = new Personne(
                cursor.getInt(0),
                poste,
                equipe,
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );


        db.close();
        return personne;
    }

    /* getAllPersonne*/
    public ArrayList<Personne> getAllPersonne(Context context){
        SQLiteDatabase db = this.getReadableDatabase();
        PosteDAO posteDAO = new PosteDAO (context);
        EquipeDAO equipeDAO = new EquipeDAO(context);

        ArrayList<Personne> listPersonne = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PERSONNE + ";";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
    
                Equipe equipe = equipeDAO.retrieveEquipe(cursor.getString(1));
                Poste poste = posteDAO.retrievePoste (cursor.getString(2));
                
                Personne personne = new Personne (
                        cursor.getInt(0),
                        poste,
                        equipe,
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)

                );

                listPersonne.add(personne);
            } while(cursor.moveToNext());
        }
        db.close();
        return listPersonne;
    }

    public void updateStade(Personne personne){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,personne.getId());
        values.put(COL_PERS_PAYS,personne.getPoste().getLibelle());
        values.put(COL_POSTE,personne.getEquipe().getPays());
        values.put(COL_NOM,personne.getNom());
        values.put(COL_PRENOM,personne.getPrenom());
        values.put(COL_DATE_N,personne.getDate());
        db.update(TABLE_PERSONNE, values, COL_ID + "="+ personne.getId(), null);
        db.close();
    }

    // deleteStade
    public void deleteClient(Context context, int id_client)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PERSONNE, COL_ID + "=" + id_client, null);

        db.close();
    }




}// fin de la classe
