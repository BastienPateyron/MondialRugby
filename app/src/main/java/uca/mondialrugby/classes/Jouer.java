package uca.mondialrugby.classes;

import android.util.Log;

import static android.content.ContentValues.TAG;
import static java.sql.Types.NULL;

/**
 * Created by watson on 28/02/2018.
 */

public class Jouer {
    private Equipe idEquipe;
    private Match match;
    private int score;

    public Jouer(Equipe idPays){
        this.idEquipe = idPays;
    }
    
    public Jouer(Equipe idPays, Match match, int score) {
        this.idEquipe = idPays;
        this.match = match;
        this.score = score;
    }
   
    @Override
    public String toString() {
//        Log.d(TAG, "toString: Pays: " + idEquipe.getPays());
//        Log.d(TAG, "toString: Match: " + match.getIdMatch());
//        Log.d(TAG, "toString: Score: " + score);
        return idEquipe.getPays() + " Match (" + match.getIdMatch() + ")  score [ " + score + " ]  " ;
    }
    
    public String toStringDomicile(){
    	// TODO Afficher uniquement 3 lettres
	    String tag = idEquipe.getPays().substring(0,3); // Garde les 3 premières lettres du pays ex: FRA, ITA, AUS, IRL ...
	    if(score == NULL) return "\t\t\t\t\t\t\t[" + tag + "]\t\t-";
	    else return "\t\t\t\t\t\t\t[" + tag + "]  \t" + score;
    }
    
    public String toStringExterieur(){
	    String tag = idEquipe.getPays().substring(0,3); // Garde les 3 premières lettres du pays ex: FRA, ITA, AUS, IRL ...
	    if(score == NULL) return "\t\t-\t\t[" + tag + "]";
	    else return "\t" + score + "\t\t[" + tag + "]";
    }

    public Equipe getIdPays() {
        return idEquipe;
    }

    public void setIdPays(Equipe idPays) {
        this.idEquipe = idPays;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
