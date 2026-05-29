package fr.univorleans.iut45.mathsgraphe;

import java.util.List;

public class TestAlgorithmes {

    public static void main(String[] args) {
        int nombrePages = 25;
        int nombreObjets = 5;

        System.out.println("=== Test des algorithmes ===\n");

        runTest("Linéaire", new LivrejeuGenerateurLineaire().generer(nombrePages, nombreObjets));
        runTest("Aléatoire", new LivrejeuGenerateurAleatoire().generer(nombrePages, nombreObjets));
    }

    private static void runTest(String description, Livrejeu livreJeu) {
        System.out.println("--- " + description + " ---");
        System.out.println("Pages : " + livreJeu.getNombrePageTotal());
        
        livreJeu.afficherStructure();

        afficherResultat("BFS", new AlgorithmeBFS(livreJeu));
        afficherResultat("Dijkstra", new AlgorithmeDijkstra(livreJeu));

        System.out.println();
    }

    private static void afficherResultat(String nom, Algorithme algorithme) {
        List<Page> chemin = algorithme.resoudre();
        System.out.println(nom + " :");
        if (chemin.isEmpty()) {
            System.out.println("  Aucun chemin trouvé.");
        } else {
            System.out.print("  Chemin : ");
            for (int i = 0; i < chemin.size(); i++) {
                System.out.print(chemin.get(i).getNumero());
                if (i < chemin.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            System.out.println("  Longueur en pages : " + chemin.size());
            System.out.println("  Coût total des arêtes : " + calculerCout(chemin));
            System.out.println("  Temps total des énigmes : " + calculerTempsEnigmes(chemin) + "s");
        }
    }

    private static int calculerCout(List<Page> chemin) {
        int coutTotal = 0;
        for (int i = 0; i < chemin.size() - 1; i++) {
            coutTotal += chemin.get(i).getCoutVers(chemin.get(i + 1));
        }
        return coutTotal;
    }

    private static int calculerTempsEnigmes(List<Page> chemin) {
        int tempsTotal = 0;
        for (Page page : chemin) {
            tempsTotal += page.getEnigme().getTemps();
        }
        return tempsTotal;
    }
}
