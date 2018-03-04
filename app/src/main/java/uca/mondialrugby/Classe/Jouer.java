package uca.mondialrugby.Classe;

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
   // Todo : redéfinir le toString
    @Override
    public String toString() {
        return "Jouer{" + "idPays='" + idEquipe + '\'' + ", match=" + match + ", score=" + score + '}';
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
