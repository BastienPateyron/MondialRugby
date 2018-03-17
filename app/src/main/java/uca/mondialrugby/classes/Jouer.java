package uca.mondialrugby.classes;

import static java.sql.Types.NULL;

/**
 * Created by watson on 28/02/2018.
 */

public class Jouer {
    private Equipe idEquipe;
    private Match match;
    private int score;

    public Jouer(Equipe idPays, Match match, int score) {
        this.idEquipe = idPays;
        this.match = match;
        this.score = score;
    }
   
    @Override
    public String toString() {
        return "Jouer{" + "idPays='" + idEquipe + '\'' + ", match=" + match + ", score=" + score + '}';
    }
    
    public String toStringDomicile(){
    	// TODO Afficher uniquement 3 lettres
	    String tag = idEquipe.getPays().substring(0,3); // Garde les 3 premières lettres du pays ex: FRA, ITA, AUS, IRL ...
	    if(score == NULL) return tag + " ~";
	    else return tag + " " + score;
    }
    
    public String toStringExterieur(){
	    String tag = idEquipe.getPays().substring(0,3); // Garde les 3 premières lettres du pays ex: FRA, ITA, AUS, IRL ...
	    if(score == NULL) return " ~ " + tag;
	    else return score + " " + tag;
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
