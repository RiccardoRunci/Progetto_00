/**Classe astratta che verrà estesa dai vari personaggi che comporranno il gioco.
 * @author Runci Riccardo.
 * @since versione 0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.OggettiPerMatrice.OggettiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public abstract class Personaggio implements OggettiPerMatrice
{
    /**Campo della classe che rappresenta il nome del personaggio:*/
    private String nomePersonaggio;
    /**Campo della classe che rappresenta i punti vita del personaggio:*/
    private int puntiVitaPersonaggio;
    /**Costruttore vuoto che mi servirà per javaFx e/o Hibernate.*/
    public Personaggio(){}
    /**Fa Creare una nuova istanza di Personaggio da parte di una classe che la estenderà.
     * @param nomePersonaggio (String) il nome del personaggio, non deve essere null e vuoto.
     * @param puntiVitaPersonaggio (int) l'energia vitale del personaggio, non deve essere <=0.
     * @throws IllegalArgumentException il nome è null o vuoto, e se la vita è<=0.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public Personaggio( String  nomePersonaggio, int puntiVitaPersonaggio)
    {
        if (ClasseUtility.controllaStringa(nomePersonaggio,"Personaggio") &&
                (ClasseUtility.verificaNumeriInteri(puntiVitaPersonaggio,
                        EnumControlloCreazioneAggiornamento.CREAZIONE,"Personaggio")))
        {
            this.nomePersonaggio = nomePersonaggio;
            this.puntiVitaPersonaggio = puntiVitaPersonaggio;
        }
    }
    /**Metodo getNomePersonaggio.
     * @return (String) il nome del personaggio.*/
    public String getNomePersonaggio(){ return this.nomePersonaggio; }
    /**Metodo getPuntiVitaPersonaggio.
     * @return (int) i punti vita del personaggio.*/
    public int getPuntiVitaPersonaggio(){ return this.puntiVitaPersonaggio; }
    /**Metodo setNomePersonaggio. Imposta il nome del personaggio.
     * @param nomePersonaggio (String) parametro che non deve essere null, oppure vuoto.
     * @throws IllegalArgumentException se il nome è null o blank.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public void setNomePersonaggio(String nomePersonaggio) {
        if(ClasseUtility.controllaStringa(nomePersonaggio,"Personaggio")) {
            /*Se passo un parametro non valido la classe utility già ha lanciato l'eccezione.
             * Così evito di fare un metodo set con un parametro sbagliato.*/
            this.nomePersonaggio = nomePersonaggio;
        }
    }
    /**Metodo setPuntiVitaPersonaggio. Imposta i punti vita del personaggio.
     * @param puntiVitaPersonaggio (int) valore dei punti vita, deve essere >= 0.
     * @throws IllegalArgumentException se puntiVita è negativo.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public void setPuntiVitaPersonaggio(int puntiVitaPersonaggio) {
        if (ClasseUtility.verificaNumeriInteri(puntiVitaPersonaggio, EnumControlloCreazioneAggiornamento.AGGIORNAMENTO, "Personaggio"))
        {    /*Come sopra, se passo un intero <0, la classe utility già ha lanciato l'eccezione.*/
            this.puntiVitaPersonaggio = puntiVitaPersonaggio;
        }
    }
    /**Metodo toString:
     *@return una descrizione del personaggio*/
    @Override
    public String toString(){
        return "Sono un Personaggio, della classe Personaggio!\n"+
                "Il mio nome è: "+this.getNomePersonaggio()+
                "I miei punti vita sono:"+this.getPuntiVitaPersonaggio();
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getNomePersonaggio(), this.getPuntiVitaPersonaggio());
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Personaggio)) return false;
        Personaggio other = (Personaggio)obj;
        return this.getNomePersonaggio().equals(other.getNomePersonaggio()) &&
                (this.getPuntiVitaPersonaggio() == other.getPuntiVitaPersonaggio());
    }
    /**Metodo astratto che dovrà essere riscritto in tutte le classi che estenderanno Personaggio.
     * Questo perchè magari è utile scoprire se un personaggio può morire in qualche modo e un altro in un altro.
     * @return (boolean) true, se è ancora vivo (se ha ancora della vita >0).
     * False, altrimenti (se non ha più vita).*/
    public abstract boolean controllaSePersonaggioInVita();

    /*TODO: a parte i test, vedi di fare qualcosa per serializzare i personaggi!*/

}