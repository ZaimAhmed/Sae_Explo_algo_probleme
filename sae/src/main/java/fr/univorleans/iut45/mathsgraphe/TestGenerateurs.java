package fr.univorleans.iut45.mathsgraphe;

import java.util.List;

public class TestGenerateurs {

    public static void main(String[] args) {
        int nombrePages = 10;
        int nombreObjets = 3;

        System.out.println("=== Test des générateurs de livrejeu ===\n");

        Livrejeu lineaire = new LivrejeuGenerateurLineaire().generer(nombrePages, nombreObjets);
        afficherLivrejeu("Générateur linéaire", lineaire);

        Livrejeu aleatoire = new LivrejeuGenerateurAleatoire().generer(nombrePages, nombreObjets);
        afficherLivrejeu("Générateur aléatoire", aleatoire);
    }

    private static void afficherLivrejeu(String titre, Livrejeu livreJeu) {
        System.out.println(titre);
        System.out.println("Pages totales : " + livreJeu.getNombrePageTotal());
        System.out.println("Page de départ : " + livreJeu.getPageDebut().getNumero());
        System.out.println("Page d'arrivée : " + livreJeu.getPageFin().getNumero());
        System.out.println("Liste des pages et transitions :");

        for (Page page : livreJeu.getPages()) {
            String ligne = "Page " + page.getNumero() + " -> ";
            List<Page> suivants = page.getPagesSuivantes();
            if (suivants.isEmpty()) {
                ligne += "aucune page suivante";
            } else {
                for (int i = 0; i < suivants.size(); i++) {
                    Page suivant = suivants.get(i);
                    ligne += "(" + suivant.getNumero() + ", coût=" + page.getCoutVers(suivant) + ")";
                    if (i < suivants.size() - 1) {
                        ligne += ", ";
                    }
                }
            }
            System.out.println(ligne + " (temps énigme : " + page.getEnigme().getTemps() + "s)");
            if (!page.getObjet().isEmpty()) {
                System.out.print("  Objets : ");
                for (int i = 0; i < page.getObjet().size(); i++) {
                    System.out.print(page.getObjet().get(i).getNomObjet());
                    if (i < page.getObjet().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
