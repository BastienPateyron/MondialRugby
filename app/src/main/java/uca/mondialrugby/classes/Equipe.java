package uca.mondialrugby.classes;

/**
 * Created by watson on 28/02/2018.
 */

public class Equipe {

    private String pays;
    private String surnom;

    private String copyPays;

    // Constructeur
    public Equipe(String pays, String surmon) {
        this.pays = pays;
        this.surnom = surmon;
    }

    public  Equipe (){}
    // toString

 // Todo : rédéfinir le toString
    @Override
    public String toString() {
        return pays + "\t\t( " + surnom + " )";
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPays() {
        return pays;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surmon) {
        this.surnom = surmon;
    }
    public String getCopyPays() {
        return copyPays;
    }

    public void setCopyPays(String copyPays) {
        this.copyPays = copyPays;
    }


}
