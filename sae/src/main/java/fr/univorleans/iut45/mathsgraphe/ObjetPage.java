package fr.univorleans.iut45.mathsgraphe;

public class ObjetPage {
    
    private String nomObjet;

    public ObjetPage(String nom){
        this.nomObjet = nom;
    }

    public String getNomObjet(){
        return this.nomObjet;
    }

    @Override
    public String toString() {
        return "ObjetPage : " + nomObjet;
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (this == o){
            return true;
        }
        if (!(o instanceof ObjetPage)){
            return false;
        }

        ObjetPage obj = (ObjetPage) o;

        return (this.nomObjet.equals(obj.nomObjet));
    }

    @Override
    public int hashCode(){
        return this.nomObjet.hashCode();
    }
}
