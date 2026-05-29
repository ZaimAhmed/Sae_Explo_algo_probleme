package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AlgorithmeDijkstra extends Algorithme {

    public AlgorithmeDijkstra(Livrejeu livreJeu) {
        super(livreJeu);
    }

    @Override
    public List<Page> resoudre() {
        Page pageDepart = livreJeu.getPageDebut();
        Page pageArrivee = livreJeu.getPageFin();

        if (pageDepart == null || pageArrivee == null) {
            chemin = new ArrayList<>();
            return chemin;
        }

        // État = (page, objets_collectés)
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> precedent = new HashMap<>();
        Map<String, Page> pageMap = new HashMap<>();
        Map<String, Set<ObjetPage>> objetsMap = new HashMap<>();

        Set<ObjetPage> objetsInitiaux = new HashSet<>();
        collecterObjets(pageDepart, objetsInitiaux);
        String etatDeDepart = construireEtat(pageDepart, objetsInitiaux);
        distances.put(etatDeDepart, 0);
        pageMap.put(etatDeDepart, pageDepart);
        objetsMap.put(etatDeDepart, objetsInitiaux);

        PriorityQueue<PageDistance> file = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
        file.add(new PageDistance(etatDeDepart, 0));

        String meilleurEtat = null;

        while (!file.isEmpty()) {
            PageDistance distanceEtat = file.poll();
            String etatCourant = distanceEtat.etat;
            Page courant = pageMap.get(etatCourant);
            Set<ObjetPage> objetsCollectes = objetsMap.get(etatCourant);

            if (distances.containsKey(etatCourant) && distances.get(etatCourant) < distanceEtat.distance) {
                continue;
            }

            // Vérifier si on a atteint la destination avec tous les objets
            if (courant.equals(pageArrivee) && objetsCollectes != null && 
                objetsCollectes.containsAll(livreJeu.getObjetsACollecter())) {
                meilleurEtat = etatCourant;
                break;
            }

            for (Page suivant : courant.getPagesSuivantes()) {
                int cout = courant.getCoutVers(suivant);
                int tempsEnigme = suivant.getEnigme().getTemps();
                int distanceAlternative = distances.get(etatCourant) + cout + tempsEnigme;

                Set<ObjetPage> nouvelObjets = new HashSet<>(objetsCollectes);
                collecterObjets(suivant, nouvelObjets);

                String etatSuivant = construireEtat(suivant, nouvelObjets);

                if (!distances.containsKey(etatSuivant) || distanceAlternative < distances.get(etatSuivant)) {
                    distances.put(etatSuivant, distanceAlternative);
                    precedent.put(etatSuivant, etatCourant);
                    pageMap.put(etatSuivant, suivant);
                    objetsMap.put(etatSuivant, nouvelObjets);
                    file.add(new PageDistance(etatSuivant, distanceAlternative));
                }
            }
        }

        if (meilleurEtat != null) {
            chemin = reconstruireChemin(precedent, pageMap, etatDeDepart, meilleurEtat);
        } else {
            chemin = new ArrayList<>();
        }
        
        return chemin;
    }

    private void collecterObjets(Page page, Set<ObjetPage> objets) {
        for (ObjetPage objet : page.getObjet()) {
            if (livreJeu.estNecessaire(objet)) {
                objets.add(objet);
            }
        }
    }

    private String construireEtat(Page page, Set<ObjetPage> objets) {
        List<Integer> indiceObjets = new ArrayList<>();
        for (ObjetPage objet : objets) {
            indiceObjets.add(objet.hashCode());
        }
        java.util.Collections.sort(indiceObjets);
        return page.getNumero() + "|" + indiceObjets.toString();
    }

    private List<Page> reconstruireChemin(Map<String, String> precedent, Map<String, Page> pageMap, 
                                         String etatDeDepart, String etatFin) {
        List<Page> chemin = new ArrayList<>();
        String courant = etatFin;

        while (courant != null) {
            chemin.add(0, pageMap.get(courant));
            if (courant.equals(etatDeDepart)) {
                break;
            }
            courant = precedent.get(courant);
        }

        return chemin;
    }

    private static class PageDistance {
        private final String etat;
        private final int distance;

        private PageDistance(String etat, int distance) {
            this.etat = etat;
            this.distance = distance;
        }
    }
}
