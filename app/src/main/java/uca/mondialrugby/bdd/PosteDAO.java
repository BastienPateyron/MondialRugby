package uca.mondialrugby.bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uca.mondialrugby.classes.Poste;

/**
 * Created by watson on 04/03/2018.
 */

/* PosteDAO : 2 méhodes CRUD : retrieve & getAll
  Pas d'ajout ni de suppression
 */
public class PosteDAO extends SQLiteDBHelper {

    private static final String TABLE_POSTE = "POSTE";
    private static final String COL_ID = "NUMERO";
    private static final String COL_LIBELLE = "LIBELLE_POSTE";


    public PosteDAO(Context context) {
        super(context);
    }



    public Poste retrievePoste(String id){
        SQLiteDatabase db = this.getReadableDatabase();
//        System.out.println("debut retrieve poste");
//        System.out.println("id : " + id);

        /* Requete */
        Cursor cursor = db.query(TABLE_POSTE, // Nom de table
                new String[] {COL_ID, COL_LIBELLE},
                COL_ID + "=?",
                new String[] {id},
                null, null, null, null); // Options
        if(cursor != null)
            cursor.moveToFirst();
        Poste poste = new Poste (
                cursor.getString(0),
                cursor.getString(1))
                ;

        db.close();
//        System.out.println("fin retrieve poste");
        return poste;
    }

    /* getAllPoste*/
    public ArrayList<Poste> getAllPoste(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Poste> listPoste = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_POSTE + ";";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Poste poste = new Poste (
                        cursor.getString(0),
                        cursor.getString(1)

                );

                listPoste.add(poste);
            } while(cursor.moveToNext());
        }
        db.close();
        return listPoste;
    }

}
