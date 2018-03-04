package uca.mondialrugby.Classe;

import java.util.Date;

/**
 * Created by watson on 28/02/2018.
 */

public class Match {

    private int idMatch;
    private Stade idStade;
    private Personne idPersonne;
    private Date dateMatch;

    public Match(int idMatch, Stade idStade, Personne idPersonne, Date dateMatch) {
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

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }
}
