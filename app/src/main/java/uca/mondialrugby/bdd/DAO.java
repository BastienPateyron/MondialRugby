package uca.mondialrugby.bdd;

import android.content.Context;

import uca.mondialrugby.classes.Stade;

/**
 * Created by watson on 03/03/2018.
 */

public abstract class DAO extends SQLiteDBHelper {
    public DAO(Context context) {
        super(context);
    }
	
    // TODO
    public boolean insert(Object object) {
        return false;
    }


        // TODO Inutile ?
//    public boolean insert(Stade object) {
//        return false;
//    }




    public boolean delete(Object object) {
        return false;
    }


    public void update(Object object) {

    }

    // Retrieve correspond à find mais est plus lisible car il appartient aux méthodes CRUD
    public void retrieve(Object object) {

    }

    // Je crois que getId() n'appartiens pas aux classes DAO vu qu'il dépend uniquement de l'objet et pas de la bdd
     public String getId(){
        return null;
     };
}
