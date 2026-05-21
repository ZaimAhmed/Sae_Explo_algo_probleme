package fr.univorleans.iut45.mathsgraphe;

public class Enigme {
    
    private String texte;
    private int temps;

    public Enigme(String texte){
        this.texte = texte;
        this.temps = 60;
    }

    public String getTexte(){
        return this.texte;
    }

    public int getTemps(){
        return this.temps;
    }
}
