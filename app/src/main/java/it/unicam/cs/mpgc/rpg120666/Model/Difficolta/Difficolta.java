package it.unicam.cs.mpgc.rpg120666.Model.Difficolta;

public class Difficolta
{
    private final String livelloDifficolta;
    private final int numNemici;
    private final int numOggetti;

    //Costruttore "PIENO":
    public Difficolta(EnumDifficolta enumDifficolta)
    {
        switch (enumDifficolta)
        {
            case FACILE -> {
                this.livelloDifficolta="FACILE";
                this.numNemici=10;
                this.numOggetti=20;
            }
            case MEDIA -> {
                this.livelloDifficolta="MEDIA";
                this.numNemici=20;
                this.numOggetti=15;
            }
            case DIFFICILE-> {
                this.livelloDifficolta="DIFFICLE";
                this.numNemici=40;
                this.numOggetti=10;
            }
            default -> throw new IllegalArgumentException("Difficoltà non valida");
            /*Il default -> {  } vuoto non deve esserci quando usi dei campi final!
             * Perché il costruttore pensa "se arriva il caso che si deve usare il default, quei campi
             * final non vengono inizializzati!".
             * Il default ci deve essere (se no ti dà sempre errore nei campi final), ma deve avere un lancio
             * di un eccezione! Fai il default con il lancio dell'eccezione, come scritto sopra!*/
        }
    }
    /*Con dei campi final non ci devono essere i metodi set dei campi final!*/
    public String getDifficolta() { return this.livelloDifficolta; }
    public int getNumNemici() { return this.numNemici; }
    public int getNumOggetti() { return this.numOggetti; }
    @Override
    public String toString()
    {
        return "\nLIVELLO DIFFICOLTA'= "+this.getDifficolta()+"\n"+
                "NUMERO NEMICI= "+this.getNumNemici()+"\n"+
                "NUMERO OGGETTI= "+this.getNumOggetti()+"\n";
    }
    @Override
    public int  hashCode()
    {
        return java.util.Objects.hash(this.getDifficolta(),this.getNumNemici(),this.getNumOggetti());
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Difficolta)) return false;
        Difficolta other = (Difficolta)obj;
        return (this.getDifficolta().equals(other.getDifficolta())) &&
                (this.getNumNemici() == other.getNumNemici()) &&
                (this.getNumOggetti() == other.getNumOggetti());
    }
}
