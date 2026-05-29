package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlgorithmeBFS extends Algorithme {

    public AlgorithmeBFS(Livrejeu livreJeu) {
        super(livreJeu);
    }

    @Override
    public List<Page> resoudre() {
        Page depart = livreJeu.getPageDebut();
        Page arrivee = livreJeu.getPageFin();

        if (depart == null || arrivee == null) {
            chemin = new ArrayList<>();
            return chemin;
        }

        // État = (page, objets_collectés)
        Queue<String> file = new ArrayDeque<>();
        Set<String> visites = new HashSet<>();
        Map<String, String> precedent = new HashMap<>();
        Map<String, Page> pageMap = new HashMap<>();
        Map<String, Set<ObjetPage>> objetsMap = new HashMap<>();

        Set<ObjetPage> objetsInitiaux = new HashSet<>();
        collecterObjets(depart, objetsInitiaux);
        String etatDeDepart = construireEtat(depart, objetsInitiaux);
        
        file.add(etatDeDepart);
        visites.add(etatDeDepart);
        pageMap.put(etatDeDepart, depart);
        objetsMap.put(etatDeDepart, objetsInitiaux);

        String meilleurEtat = null;

        while (!file.isEmpty()) {
            String etatActuelle = file.poll();
            Page courant = pageMap.get(etatActuelle);
            Set<ObjetPage> objetsCollectes = objetsMap.get(etatActuelle);

            // Vérifier si on a atteint la destination avec tous les objets
            if (courant.equals(arrivee) && objetsCollectes != null && 
                objetsCollectes.containsAll(livreJeu.getObjetsACollecter())) {
                meilleurEtat = etatActuelle;
                break;
            }

            for (Page suivant : courant.getPagesSuivantes()) {
                Set<ObjetPage> nouvelObjets = new HashSet<>(objetsCollectes);
                collecterObjets(suivant, nouvelObjets);

                String prochainEtat = construireEtat(suivant, nouvelObjets);

                if (!visites.contains(prochainEtat)) {
                    visites.add(prochainEtat);
                    precedent.put(prochainEtat, etatActuelle);
                    pageMap.put(prochainEtat, suivant);
                    objetsMap.put(prochainEtat, nouvelObjets);
                    file.add(prochainEtat);
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
}
