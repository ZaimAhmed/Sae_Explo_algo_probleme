package fr.univorleans.iut45.mathsgraphe;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public abstract class Algorithme {

    protected final Livrejeu livreJeu;
    protected List<Page> chemin;

    public Algorithme(Livrejeu livreJeu) {
        this.livreJeu = livreJeu;
    }

    public abstract List<Page> resoudre();

    protected boolean cheminValide(List<Page> chemin) {
        if (chemin.isEmpty()) {
            return false;
        }
        Set<ObjetPage> objetsCollectes = new HashSet<>();
        for (Page page : chemin) {
            for (ObjetPage objet : page.getObjet()) {
                if (livreJeu.estNecessaire(objet)) {
                    objetsCollectes.add(objet);
                }
            }
        }
        return objetsCollectes.containsAll(livreJeu.getObjetsACollecter());
    }

    public int getLongueur() {
        return chemin.size();
    }
}
