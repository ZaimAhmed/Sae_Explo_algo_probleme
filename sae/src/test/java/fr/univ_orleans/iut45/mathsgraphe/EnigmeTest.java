package fr.univ_orleans.iut45.mathsgraphe;

import fr.univorleans.iut45.mathsgraphe.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnigmeTest {

    @Test
    public void testConstructeurGetteurs() {
        Enigme enigme = new Enigme("Quel est le résultat?");

        assertEquals("Quel est le résultat?", enigme.getTexte());
        assertEquals(60, enigme.getTemps());
    }
}
