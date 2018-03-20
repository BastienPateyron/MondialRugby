package uca.mondialrugby.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uca.mondialrugby.classes.Stade;

/**
 * Created by watson on 28/02/2018.
 */

public class StadeDAO extends SQLiteDBHelper {



    private static final String TABLE_STADE = "STADE";
    private static final String COL_ID = "ID_STADE";
    private static final String COL_NOM = "NOM_STADE";
    private static final String COL_NUM_RUE = "NUM_RUE";
    private static final String COL_NOM_RUE = "NOM_RUE";
    private static final String COL_VILLE = "VILLE";
    private static final String COL_CP = "CP";
    private static final String COL_PLACE = "NB_PLACE";


    public StadeDAO(Context context) {
        super(context);
    }





    /*insertSTADE*/
    public boolean insertStade( Stade stade){
        ContentValues values = new ContentValues();

        values.put(COL_NOM,stade.getNom());
        values.put(COL_NUM_RUE,stade.getNum_rue());
        values.put(COL_NOM_RUE,stade.getNom_rue());
        values.put(COL_CP,stade.getCp());
        values.put(COL_VILLE,stade.getVille());
        values.put(COL_PLACE,stade.getNombre_place());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean insertSuccessful = db.insert(TABLE_STADE,null,values) > 0;

        return insertSuccessful;
    }

    public Stade retrieveStade(int id){
        SQLiteDatabase db = this.getReadableDatabase();


        /* Requete */
        Cursor cursor = db.query(TABLE_STADE, // Nom de table
                new String[] {COL_ID, COL_NOM, COL_NUM_RUE, COL_NOM_RUE, COL_VILLE, COL_CP, COL_PLACE},
                COL_ID + "=?",
                new String[] {String.valueOf(id)},
                null, null, null, null); // Options
        if(cursor != null)
            cursor.moveToFirst();
        Stade stade = new Stade (
        cursor.getInt(0),
        cursor.getString(1),
        cursor.getString(2),
        cursor.getString(3),
        cursor.getString(4),
        cursor.getString(5),
        cursor.getInt(6) );
	    
        db.close();
        return stade;
    }

    /* getAllStade*/
    public ArrayList<Stade> getAllStade(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Stade> listeStade = new ArrayList<>();
        String query = "SELECT * FROM STADE;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Stade stade = new Stade (
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                        );

                listeStade.add(stade);
            } while(cursor.moveToNext());
        }
        db.close();
        return listeStade;
    }

    public void updateStade(Stade stade){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOM,stade.getNom());
        values.put(COL_NUM_RUE,stade.getNum_rue());
        values.put(COL_NOM_RUE,stade.getNom_rue());
        values.put(COL_CP,stade.getCp());
        values.put(COL_VILLE,stade.getVille());
        values.put(COL_PLACE,stade.getNombre_place());
        db.update(TABLE_STADE, values, COL_ID + "="+ stade.getId(), null);
        System.out.println("update faite");
        System.out.println(stade.getNom());
        db.close();
    }

    // deleteStade
    public void deleteStade(int id_stade)
    {

        //TODO : supprimer les matchs delete on cascade

        SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("PRAGMA foreign_keys;");
   
        db.delete(TABLE_STADE, COL_ID + "=" + id_stade, null);

        db.close();
    }

} // Fin StadeDAO
