package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

public class CellaMatrice
{
    private final int x;
    private final int y;

    public CellaMatrice(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CellaMatrice)) return false;
        CellaMatrice cella = (CellaMatrice) o;
        return this.getX() == cella.getX() && this.getY() == cella.getY();
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.getX(), this.getY());
    }

    @Override
    public String toString()
    {
        return "["+x+","+y+"]";
    }
}
