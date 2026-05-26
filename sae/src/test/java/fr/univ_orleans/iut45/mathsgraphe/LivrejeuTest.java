package fr.univ_orleans.iut45.mathsgraphe;

import fr.univorleans.iut45.mathsgraphe.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;

public class LivrejeuTest {

    @Test
    public void testConstructeurGetteurs() {
        Page debut = new Page(1, new Enigme("Debut"));
        Page fin = new Page(2, new Enigme("Fin"));
        List<Page> pages = Arrays.asList(debut, fin);
        ObjetPage objetNecessaire = new ObjetPage("clef");
        Set<ObjetPage> objetsACollecter = new HashSet<>();
        objetsACollecter.add(objetNecessaire);

        Livrejeu livre = new Livrejeu(pages, debut, fin, 2, objetsACollecter);

        assertTrue(debut == livre.getPageDebut());
        assertTrue(fin == livre.getPageFin());
        assertTrue(debut == livre.getPageActuelle());
        assertEquals(2, livre.getNombrePageTotal());
        assertTrue(livre.estNecessaire(objetNecessaire));
        assertFalse(livre.estCollecté(objetNecessaire));
    }

    @Test
    public void testGagne() {
        Page debut = new Page(1, new Enigme("Debut"));
        Page fin = new Page(2, new Enigme("Fin"));
        List<Page> pages = Arrays.asList(debut, fin);
        Set<ObjetPage> objetsACollecter = new HashSet<>();

        Livrejeu livre = new Livrejeu(pages, debut, fin, 2, objetsACollecter);
        livre.setPageActuelle(fin);

        assertTrue(livre.gagne());
    }

    @Test
    public void testPerdu() {
        Page debut = new Page(1, new Enigme("Debut"));
        Page fin = new Page(2, new Enigme("Fin"));
        List<Page> pages = Arrays.asList(debut, fin);
        Set<ObjetPage> objetsACollecter = new HashSet<>();

        Livrejeu livre = new Livrejeu(pages, debut, fin, 2, objetsACollecter);

        assertFalse(livre.gagne());
    }
}
