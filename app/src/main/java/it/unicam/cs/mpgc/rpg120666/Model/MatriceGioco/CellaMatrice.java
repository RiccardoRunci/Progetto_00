package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

public class CellaMatrice
{
    private final int riga;
    private final int colonna;

    public CellaMatrice(int riga, int colonna)
    {
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
