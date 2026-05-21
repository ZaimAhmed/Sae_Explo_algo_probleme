package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.List;

public class Page{

    private int numero;
    private Enigme enigme;
    private List<Objet> objet;

    public Page(int numero, Enigme enigme) {
        this.numero = numero;
        this.enigme = enigme;
        this.objet = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public Enigme getEnigme() {
        return enigme;
    }

    public List<Objet> getObjet() {
        return objet;
    }

    public void ajouterObjet(Objet o){
        return objet.add(o);

    }
}
