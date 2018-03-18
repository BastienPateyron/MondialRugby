package uca.mondialrugby.classes;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import uca.mondialrugby.MainActivity;
import uca.mondialrugby.bdd.JouerDAO;

import static android.content.ContentValues.TAG;

/**
 * Created by watson on 28/02/2018.
 */

public class Match {

    private int idMatch;
    private Stade idStade;
    private Personne idPersonne;
    private String dateMatch;
    private Context context;
    

    
    public Match (int idMatch, Stade idStade, Personne idPersonne, String dateMatch) {
    	this.idMatch = idMatch;
	    this.idStade = idStade;
	    this.idPersonne = idPersonne;
	    this.dateMatch = dateMatch;
    }
	
    
    // TODO : redéfinir le toString
    @Override
    public String toString() {
        // TODO Recupere les 2 matchs (getAllMatchOf(Table, id))
        // Garde les 3 lettre du début
        // [FRA 17 - 19 ITA]
        
		JouerDAO jouerDAO = new JouerDAO(MainActivity.getsContext());
		
		// Récupère les 2 équipes du match
		ArrayList<Jouer> jouerArrayList = jouerDAO.getAllJouerOf("MATCHS", idMatch);
	    String matchStr = jouerArrayList.get(0).toStringDomicile() + "\t\t\t|\t" + jouerArrayList.get(1).toStringExterieur();
	    return jouerArrayList.get(0).toStringDomicile() + "\t\t\t|\t" + jouerArrayList.get(1).toStringExterieur();
    }

    public int getIdMatch() {
        return idMatch;
    }

    public Stade getStade() {
        return idStade;
    }

    public void setIdStade(Stade idStade) {
        this.idStade = idStade;
    }

    public Personne getPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }
}
