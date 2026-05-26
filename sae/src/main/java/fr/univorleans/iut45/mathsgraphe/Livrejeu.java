package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Livrejeu {

    private List<Page> LesPages;
    private Page pageActuelle;
    private Page pageDebut;
    private Page pageFin;
    private int nombrePageTotal;
    private Set<ObjetPage> objetACollecter;
    private Set<ObjetPage> objetsRecuperés;

    public Livrejeu(List<Page> lesPages, Page pageDebut, Page pageFin, int nombrePageTotal, Set<ObjetPage> objetACollecter) {
        this.LesPages = lesPages;
        this.pageDebut = pageDebut;
        this.pageActuelle = pageDebut;
        this.pageFin = pageFin;
        this.nombrePageTotal = nombrePageTotal;
        this.objetACollecter = objetACollecter;
        this.objetsRecuperés = new HashSet<>();
    }

    public Page getPageDebut() {
        return pageDebut;
    }
    public Page getPageFin() {
        return pageFin;
    }
    public Page getPageActuelle() {
        return pageActuelle;
    }
    public void setPageActuelle(Page p){
        this.pageActuelle = p;
    }
    public int getNombrePageTotal() {
        return nombrePageTotal;
    }
    public boolean estCollecté(ObjetPage o){
        return objetsRecuperés.contains(o);
    }
    public boolean estNecessaire(ObjetPage o){
        return objetACollecter.contains(o);
    }
    public boolean gagne(){
        if (!(this.pageActuelle.equals(this.pageFin))){
            return false;
        }
        for(ObjetPage o : this.objetsRecuperés){
            if (!(this.objetACollecter.contains(o))){
                return false;
            }
        } 
        return true;
    }

}
