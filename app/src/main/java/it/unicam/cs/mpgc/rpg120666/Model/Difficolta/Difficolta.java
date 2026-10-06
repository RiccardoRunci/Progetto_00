/**Classe Difficoltà:
 * rappresenta la difficoltà di Gioco.
 * @since versione 1.0.
 * @author Runci Riccardo*/
package it.unicam.cs.mpgc.rpg120666.Model.Difficolta;

public class Difficolta
{
    private final String livelloDifficolta;
    private final int numNemici;
    private final int numOggetti;

    /**Metodo costruttore:
     * @param enumDifficolta = una opzione dell'enumerezione EnumDifficolta (FACILE,MEDIA,DIFFICILE).
     * @see EnumDifficolta
     * @since versione 1.0.
     * @author Runci Riccardo*/
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
   /**Metodo getDifficolta:
    * @return this.livelloDifficolta è una stringa che ritorna FACILE o MEDIA o DIFFICILE.
    * @since versione 1.0.
    * @author Runci Riccardo*/
    public String getDifficolta() { return this.livelloDifficolta; }
    /**Metodo getNumNemici:
     * @return int il numero di nemici creati in base alla difficoltà.
     * @since versione 1.0.
     * @author Runci Riccardo*/
    public int getNumNemici() { return this.numNemici; }
    /**Metodo getNumOggetti:
     * @return int il numero di oggetti creati in base alla difficoltà.
     * @since versione 1.0.
     * @author Runci Riccardo*/
    public int getNumOggetti() { return this.numOggetti; }
    /**Metodo toString:
     * @return String una breve panoramica sulla difficoltà scelta.
     * @since versione 1.0.
     * @author Runci Riccardo*/
    @Override
    public String toString()
    {
        return "\nLIVELLO DIFFICOLTA'= "+this.getDifficolta()+"\n"+
                "NUMERO NEMICI= "+this.getNumNemici()+"\n"+
                "NUMERO OGGETTI= "+this.getNumOggetti()+"\n";
    }
    /**Metodo hashCode:
     * @Override del metodo hashCode di Object.
     * @return int l'identificativo del basket dove andrà a salvarsi l'oggetto!
     * @since versione 1.0.
     * @author Runci Riccardo*/
    @Override
    public int  hashCode()
    {
        return java.util.Objects.hash(this.getDifficolta(),this.getNumNemici(),this.getNumOggetti());
    }
    /**Metodo equals:
     * @return true se i 2 oggetti Difficoltà sono uguali, false altrimenti.
     * @param obj = l'altro oggetto con il quale fare il confronto.
     * @since versione 1.0.
     * @author Runci Riccardo*/
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
