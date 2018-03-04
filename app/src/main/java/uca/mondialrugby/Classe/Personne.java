package uca.mondialrugby.Classe;

/**
 * Created by watson on 28/02/2018.
 */

public class Personne {
    private final int id;
    private Poste poste;
    private Equipe equipe;
    private String nom;
    private String prenom;
    private String date;


    // CONSTRUCTEUR
    public Personne(int id, Poste poste, Equipe equipe, String Nom, String Prenom, String date) {
        this.id = id;
        this.poste = poste;
        this.equipe = equipe;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }


    // GETTER & SETTER
    public int getId() {
        return id;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
