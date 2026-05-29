package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LivrejeuGenerateurAleatoire implements LivrejeuGenerateur {

    private final Random random;

    public LivrejeuGenerateurAleatoire() {
        this.random = new Random();
    }

    @Override
    public Livrejeu generer(int nombrePages, int nombreObjetsRequis) {
        List<Page> pages = new ArrayList<>();
        for (int i = 1; i <= nombrePages; i++) {
            int tempsEnigme = random.nextInt(100);
            pages.add(new Page(i, new Enigme("Énigme aléatoire " + i, tempsEnigme)));
        }
        for (int i = 0; i < nombrePages; i++) {
            Page page = pages.get(i);
            
            for (int j = i + 1; j < nombrePages; j++) {
                int distance = j - i;
                
                // Probabilité décroissante selon la distance
                // Connexions proches : très probable, moyennes : probable, lointaines : rare
                double probabilite = 1.0;
                if (distance == 1) {
                    probabilite = 0.99; // Toujours connecté 
                } else if (distance == 2) {
                    probabilite = 0.70; // Souvent connecté au suivant+1 pas tout le temps
                } else if (distance <= 4) {
                    probabilite = 0.50 + random.nextDouble() * 0.20;
                } else if (distance <= 7) {
                    probabilite = 0.30 + random.nextDouble() * 0.15;
                } else if (distance <= 12) {
                    probabilite = 0.15 + random.nextDouble() * 0.10;
                } else {
                    probabilite = 0.08 + random.nextDouble() * 0.08;
                }
                
                if (random.nextDouble() < probabilite) {
                    int cout = 1 + random.nextInt(6) + (distance - 1) / 3;
                    page.ajouterPage(pages.get(j), cout);
                }
            }
            
            if (i > 0 && random.nextDouble() < 0.20) {
                int targetIndex = random.nextInt(i);
                int cout = 2 + random.nextInt(5);
                page.ajouterPage(pages.get(targetIndex), cout);
            }
        }

        Set<ObjetPage> objetsRequis = new HashSet<>();
        List<Integer> positions = new ArrayList<>();
        for (int i = 1; i < nombrePages - 1; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions, random);

        int objetsAjoutes = Math.min(nombreObjetsRequis, positions.size());
        for (int i = 0; i < objetsAjoutes; i++) {
            int position = positions.get(i);
            ObjetPage objet = new ObjetPage("Objet" + (i + 1));
            pages.get(position).ajouterObjet(objet);
            objetsRequis.add(objet);
        }

        return new Livrejeu(pages, pages.get(0), pages.get(nombrePages - 1), objetsRequis);
    }
}
