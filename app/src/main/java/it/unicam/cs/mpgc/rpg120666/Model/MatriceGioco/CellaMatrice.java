/**Classe che rappresenta una cella della MatriceGioco.
 * @author Runci Riccardo.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

public class CellaMatrice
{
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

    public int getRiga() { return this.riga; }
    public int getColonna() { return this.colonna; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CellaMatrice)) return false;
        CellaMatrice cella = (CellaMatrice) o;
        return this.getRiga() == cella.getRiga() && this.getColonna() == cella.getColonna();
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.getRiga(), this.getColonna());
    }

    @Override
    public String toString()
    {
        return "["+this.riga+","+this.colonna+"]";
    }
}
