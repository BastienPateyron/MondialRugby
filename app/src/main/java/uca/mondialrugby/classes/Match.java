package uca.mondialrugby.classes;

/**
 * Created by watson on 28/02/2018.
 */

public class Match {

    private int idMatch;
    private Stade idStade;
    private Personne idPersonne;
    private String dateMatch;

    public Match(int idMatch, Stade idStade, Personne idPersonne, String dateMatch) {
        this.idMatch = idMatch;
        this.idStade = idStade;
        this.idPersonne = idPersonne;
        this.dateMatch = dateMatch;
    }
    public Match () {}
    // TODO : redéfinir le toString
    @Override
    public String toString() {
        return "Match{}";
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
