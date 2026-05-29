package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page{

    private int numero;
    private Enigme enigme;
    private List<ObjetPage> objet;
    private List<Page> pagesSuivantes;
    private Map<Page, Integer> coutsPagesSuivantes;

    public Page(int numero, Enigme enigme) {
        this.numero = numero;
        this.enigme = enigme;
        this.objet = new ArrayList<>();
        this.pagesSuivantes = new ArrayList<>();
        this.coutsPagesSuivantes = new HashMap<>();
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
        ajouterPage(p, 1);
    }

    public void ajouterPage(Page p, int cout){
        this.pagesSuivantes.add(p);
        this.coutsPagesSuivantes.put(p, Math.max(1, cout));
    }

    public List<Page> getPagesSuivantes() {
        return this.pagesSuivantes;
    }

    public int getCoutVers(Page page) {
        return coutsPagesSuivantes.getOrDefault(page, 1);
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
