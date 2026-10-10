/**Classe che rappresenta una cella della MatriceGioco.
 * @author Runci Riccardo.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

import java.io.Serializable;

public class CellaMatrice implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final int riga;
    private final int colonna;

    /**Metodo Costruttore:
     * @param riga
     * @param colonna
     * @throws IllegalArgumentException se i 2 parametri sopra sono < 0.
     * @author Runci Riccardo.
     * @since versione 1.0*/
    public CellaMatrice(int riga, int colonna)
    {
        if(riga<0 || colonna<0)
            throw new IllegalArgumentException("Parametri riga o colonna passati al costruttore di CellaMatrice < 0!");
        this.riga = riga;
        this.colonna = colonna;
    }
    /**Metodo getRiga:
     * @return this.riga (int) ritorna la riga di questa cella.*/
    public int getRiga() { return this.riga; }
    /**Metodo getColonna:
     * @return this.colonna (int) ritorna la colonna di questa cella.*/
    public int getColonna() { return this.colonna; }
    /**Metodo equals:
     * @Override del metodo equals della classe Object.
     * @return true se le 2 celle confrontate sono uguali.*/
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CellaMatrice)) return false;
        CellaMatrice cella = (CellaMatrice) o;
        return this.getRiga() == cella.getRiga() && this.getColonna() == cella.getColonna();
    }
    /**Metodo hashCode:
     * @Override del metodo hashCode di Object.
     * @return (int) indica il bucket dove verrà salvato l'oggetto cella!*/
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.getRiga(), this.getColonna());
    }
    /**Metodo toString:
     * @Override del metodo toString di Object.
     * @return (String) una descrizione di questa cella [riga, colonna].*/
    @Override
    public String toString()
    {
        return "["+this.riga+","+this.colonna+"]";
    }
}
