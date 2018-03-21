package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import uca.mondialrugby.classes.Equipe;

import static android.content.ContentValues.TAG;

/**
 * Created by watson on 04/03/2018.
 */

public class EquipeDAO extends SQLiteDBHelper {
    private static final String TABLE_EQUIPE = "EQUIPE";
    private static final String COL_PAYS = "PAYS";
    private static final String COL_SURNOM = "SURNOM_EQUIPE";



    public EquipeDAO(Context context) {
        super(context);
    }
    
    
    /* Get Classement
    * Retourne une liste des équipes classées en fonction de leur nombre de victoires
    * puis de leur score total */
    // TODO
    public ArrayList<Equipe> getClassement(){
        SQLiteDatabase db = this.getReadableDatabase();
        
        ArrayList<Equipe> classement = new ArrayList<>();
        String query = "SELECT * FROM EQUIPE";
        Cursor cursor = db.rawQuery(query, null);
        
        if (cursor.moveToFirst()){
            do {
                Equipe equipe = new Equipe (
                        cursor.getString(0),
                        cursor.getString(1)
                );
    
                classement.add(equipe);
            } while(cursor.moveToNext());
        }
        db.close();
        return classement;
    }
    
    
    /*insertEquipe*/
    public boolean insertEquipe( Equipe equipe){
        ContentValues values = new ContentValues();

        values.put(COL_PAYS,equipe.getPays());
        values.put(COL_SURNOM,equipe.getSurnom());


        SQLiteDatabase db = this.getWritableDatabase();

        boolean insertSuccessful = db.insert(TABLE_EQUIPE,null,values) > 0;

        return insertSuccessful;
    }

    public Equipe retrieveEquipe(String id){ // TODO : modifier avec un string
        SQLiteDatabase db = this.getReadableDatabase();
		Equipe equipe = new Equipe();

        /* Requete */
        Cursor cursor = db.query(TABLE_EQUIPE, // Nom de table
                new String[] {COL_PAYS, COL_SURNOM},
                COL_PAYS + "=?",
                new String[] {String.valueOf(id)},

                null, null, null, null); // Options

        if(cursor != null) {
	        cursor.moveToFirst();
	        equipe.setPays(cursor.getString(0));
	        equipe.setSurnom(cursor.getString(1));
        }
		else Log.d(TAG, "retrieveEquipe: equipe introuvable");
	
	    db.close();

        return equipe;
    }

    /* getAllEquipe*/
    public ArrayList<Equipe> getAllEquipe(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Equipe> listEquipe = new ArrayList<>();
        String query = "SELECT * FROM EQUIPE";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Equipe equipe = new Equipe (
                        cursor.getString(0),
                        cursor.getString(1)

                );

                listEquipe.add(equipe);
            } while(cursor.moveToNext());
        }
        db.close();
        return listEquipe;
    }

    public void updateEquipe(Equipe equipe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PAYS,equipe.getPays());
        values.put(COL_SURNOM,equipe.getSurnom());
        System.out.println("copie de l'id : " +equipe.getCopyPays());
        db.update(TABLE_EQUIPE, values, COL_PAYS + "= '"+ equipe.getCopyPays()+ "'", null);
        equipe.setCopyPays(equipe.getPays());
        db.close();
    }


    // deleteEquipe
    public void deleteEquipe(String id_equipe)
    {

        //TODO : supprimer delete on cascade les matchs

        SQLiteDatabase db = this.getWritableDatabase();


        db.delete(TABLE_EQUIPE, COL_PAYS + "='" + id_equipe + "'", null);

        db.close();
    }







}
