package fr.univorleans.iut45.mathsgraphe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LivrejeuGenerateurLineaire implements LivrejeuGenerateur {

    @Override
    public Livrejeu generer(int nombrePages, int nombreObjetsRequis) {
        List<Page> pages = new ArrayList<>();
        for (int i = 1; i <= nombrePages; i++) {
            int tempsEnigme = 30 + (i - 1) * 10;
            pages.add(new Page(i, new Enigme("Énigme de la page " + i, tempsEnigme)));
        }

        for (int i = 0; i < nombrePages - 1; i++) {
            pages.get(i).ajouterPage(pages.get(i + 1));
        }

        Set<ObjetPage> objetsRequis = new HashSet<>();
        int objetsAjoutes = Math.min(nombreObjetsRequis, nombrePages - 2);
        for (int i = 1; i <= objetsAjoutes; i++) {
            ObjetPage objet = new ObjetPage("Objet" + i);
            pages.get(i).ajouterObjet(objet);
            objetsRequis.add(objet);
        }

        return new Livrejeu(pages, pages.get(0), pages.get(nombrePages - 1), objetsRequis);
    }
}
