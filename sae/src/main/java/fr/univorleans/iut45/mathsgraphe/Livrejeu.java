package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.List;

public class Livrejeu {



    private List<Page> LesPages;
    private Page pageDebut;
    private Page pageFin;
    private int nombrePageTotal;

    public Livrejeu(Page pageDebut, Page pageFin, int nombrePageTotal) {
        LesPages = new ArrayList<>();
        this.pageDebut = pageDebut;
        this.pageFin = pageFin;
        this.nombrePageTotal = nombrePageTotal;
    }

    public Page getPageDebut() {
        return pageDebut;
    }
    public Page getPageFin() {
        return pageFin;
    }
    public int getNombrePageTotal() {
        return nombrePageTotal;
    }

}
