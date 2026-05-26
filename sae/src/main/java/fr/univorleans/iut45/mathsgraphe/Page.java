package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.List;

public class Page{

    private int numero;
    private Enigme enigme;
    private List<ObjetPage> objet;
    private List<Page> pagesSuivantes;

    public Page(int numero, Enigme enigme) {
        this.numero = numero;
        this.enigme = enigme;
        this.objet = new ArrayList<>();
        this.pagesSuivantes = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public Enigme getEnigme() {
        return enigme;
    }

    public List<ObjetPage> getObjet() {
        return objet;
    }

    public void ajouterObjet(ObjetPage o){
        this.objet.add(o);
    }

    public void ajouterPage(Page p){
        this.pagesSuivantes.add(p);
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (this == o){
            return true;
        }
        if (!(o instanceof Page)){
            return false;
        }

        Page p = (Page) o;

        return (this.numero == p.numero);
    }

    @Override
    public int hashCode(){
        return this.numero;
    }
}
