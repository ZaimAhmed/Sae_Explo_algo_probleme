package fr.univ_orleans.iut45.mathsgraphe;

import fr.univorleans.iut45.mathsgraphe.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObjetPageTest {

    @Test
    public void testConstructeurGetteurs() {
        ObjetPage objet = new ObjetPage("clef");

        assertEquals("clef", objet.getNomObjet());
    }
}
