package uca.mondialrugby.bdd;

import android.content.Context;

import uca.mondialrugby.Classe.Stade;

/**
 * Created by watson on 03/03/2018.
 */

public abstract class DAO extends SQLiteDBHelper {
    public DAO(Context context) {
        super(context);
    }



    public boolean insert(Object object) {
        return false;
    }


    public boolean insert(Stade object) {
        return false;
    }




    public boolean delete(Object object) {
        return false;
    }


    public void update(Object object) {

    }


    public void find(Object object) {

    }

     public String getId(){
        return null;
     };
}
