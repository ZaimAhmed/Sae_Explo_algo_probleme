package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Livrejeu {

    private List<Page> LesPages;
    private Page pageDebut;
    private Page pageFin;
    private Set<ObjetPage> objetACollecter;

    public Livrejeu(List<Page> lesPages, Page pageDebut, Page pageFin, Set<ObjetPage> objetACollecter) {
        this.LesPages = new ArrayList<>(lesPages);
        this.pageDebut = pageDebut;
        this.pageFin = pageFin;
        this.objetACollecter = objetACollecter;
    }

    public List<Page> getPages(){
        return this.LesPages;
    }

    public Page getPageDebut() {
        return pageDebut;
    }

    public Page getPageFin() {
        return pageFin;
    }

    public int getNombrePageTotal() {
        return this.LesPages.size();
    }

    public boolean estNecessaire(ObjetPage o){
        return objetACollecter.contains(o);
    }

    public Set<ObjetPage> getObjetsACollecter() {
        return this.objetACollecter;
    }

    public void afficherStructure() {
        System.out.println("\n=== STRUCTURE DU GRAPHE ===");
        System.out.println("Début : Page " + pageDebut.getNumero());
        System.out.println("Fin : Page " + pageFin.getNumero());
        System.out.println("Objets à collecter : " + objetACollecter.size());
        System.out.println("\nConnexions :");
        
        for (Page page : LesPages) {
            System.out.print("  Page " + page.getNumero() + " -> ");
            List<Page> suivantes = page.getPagesSuivantes();
            
            if (suivantes.isEmpty()) {
                System.out.println("(aucune connexion)");
            } else {
                for (int i = 0; i < suivantes.size(); i++) {
                    Page suivante = suivantes.get(i);
                    int cout = page.getCoutVers(suivante);
                    System.out.print("Page " + suivante.getNumero() + " (coût=" + cout + ")");
                    if (i < suivantes.size() - 1) System.out.print(", ");
                }
                System.out.println();
            }
            
            List<ObjetPage> objets = page.getObjet();
            if (!objets.isEmpty()) {
                System.out.println("    Objets : " + objets.size());
            }
        }
        System.out.println("===========================\n");
    }
}
