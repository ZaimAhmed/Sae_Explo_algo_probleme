package fr.univ_orleans.iut45.mathsgraphe;

import fr.univorleans.iut45.mathsgraphe.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PageTest {

    @Test
    public void testConstructeurGetteurs() {
        Enigme enigme = new Enigme("Une énigme");
        Page page = new Page(1, enigme);

        assertEquals(1, page.getNumero());
        assertEquals(enigme, page.getEnigme());
        assertTrue(page.getObjet() != null);
        assertTrue(page.getObjet().isEmpty());
    }

    @Test
    public void testAjouterObjet(){
        Page page1 = new Page(1, new Enigme("A"));
        Page page2 = new Page(2, new Enigme("B"));
        ObjetPage objet = new ObjetPage("clef");

        page1.ajouterObjet(objet);
        page1.ajouterPage(page2);

        assertEquals(1, page1.getObjet().size());
        assertEquals(objet, page1.getObjet().get(0));
    }

    @Test
    public void testEqualsHashCode() {
        Page page1 = new Page(1, new Enigme("A"));
        Page page2 = new Page(1, new Enigme("B"));
        Page page3 = new Page(2, new Enigme("A"));

        assertTrue(page1.equals(page2));
        assertEquals(page1.hashCode(), page2.hashCode());
        assertFalse(page1.equals(page3));
        assertFalse(page1.equals(null));
        assertFalse(page1.equals("pas une page"));
    }
}
