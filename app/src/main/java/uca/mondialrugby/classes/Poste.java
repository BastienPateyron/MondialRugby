package uca.mondialrugby.classes;

/**
 * Created by watson on 28/02/2018.
 */

public class Poste {

    private final String numero;
    private final String libelle;

    public Poste(String numero, String libelle) {

        this.numero = numero;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Poste{" + "numero='" + numero + '\'' + ", libelle='" + libelle + '\'' + '}';
    }

    public String getNumero() {
        return numero;
    }

    public String getLibelle() {
        return libelle;
    }
}
