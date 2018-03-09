package uca.mondialrugby.classes;

/**
 * Created by watson on 28/02/2018.
 */

public class Equipe {

    private String pays;
    private String surmon;

    // Constructeur
    public Equipe(String pays, String surmon) {
        this.pays = pays;
        this.surmon = surmon;
    }

    public  Equipe (){}
    // toString

 // Todo : rédéfinir le toString
    @Override
    public String toString() {
        return "Equipe{" + "pays='" + pays + '\'' + ", surmon='" + surmon + '\'' + '}';
    }

    public String getPays() {
        return pays;
    }

    public String getSurmon() {
        return surmon;
    }

    public void setSurmon(String surmon) {
        this.surmon = surmon;
    }



}
