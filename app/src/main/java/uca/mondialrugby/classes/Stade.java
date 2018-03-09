package uca.mondialrugby.classes;

/**
 * Created by watson on 28/02/2018.
 */

public class Stade extends ClasseManager {

    private int id;
    private String nom   ;
    private String num_rue  ;
    private String   nom_rue;
    private String   ville;
    private String   cp;
    private int nombre_place;

 // Constructeur
    public Stade(Integer id, String nom, String num_rue, String nom_rue, String ville, String cp, int nombre_place) {
        this.id = id;
        this.nom = nom;
        this.num_rue = num_rue;
        this.nom_rue = nom_rue;
        this.ville = ville;
        this.cp = cp;
        this.nombre_place = nombre_place;
    }
    public Stade () {}

    // toString
    @Override
    public String toString () {
        return ("Le stade " + this.nom);
    }

    // Getter & Setter


    public String getId() {
        return String.valueOf(id);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum_rue() {
        return num_rue;
    }

    public void setNum_rue(String num_rue) {
        this.num_rue = num_rue;
    }

    public String getNom_rue() {
        return nom_rue;
    }

    public void setNom_rue(String nom_rue) {
        this.nom_rue = nom_rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getNombre_place() {
        return nombre_place;
    }

    public void setNombre_place(int nombre_place) {
        this.nombre_place = nombre_place;
    }
} // fin de la classe Stade
