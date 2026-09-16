/**Classe astratta che verrà estesa dai vari personaggi che comporranno il gioco.
 * @author Runci Riccardo.
 * @since versione 0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public abstract class Nemico implements ElementiPerMatrice
{
    /**Campo della classe che rappresenta il nome del nemico:*/
    private String nomeNemico;
    /**Campo della classe che rappresenta i punti vita del nemico:*/
    private int puntiVitaNemico;
    /**Costruttore vuoto che mi servirà per javaFx e/o Hibernate.*/
    public Nemico(){}
    /**Fa Creare una nuova istanza di Nemico da parte di una classe che la estenderà.
     * @param nomeNemico (String) il nome del personaggio, non deve essere null e vuoto.
     * @param puntiVitaNemico (int) l'energia vitale del personaggio, non deve essere <=0.
     * @throws IllegalArgumentException il nome è null o vuoto, e se la vita è<=0.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public Nemico(String nomeNemico, int puntiVitaNemico)
    {
        if (ClasseUtility.controllaStringa(nomeNemico,"Nemico") &&
                (ClasseUtility.verificaNumeriInteri(puntiVitaNemico,
                        EnumControlloCreazioneAggiornamento.CREAZIONE,"Nemico")))
        {
            this.nomeNemico = nomeNemico;
            this.puntiVitaNemico = puntiVitaNemico;
        }
    }
    /**Metodo getNomeNemico.
     * @return (String) il nome del nemico.*/
    public String getNomeNemico(){ return this.nomeNemico; }
    /**Metodo getPuntiVitaNemico.
     * @return (int) i punti vita del nemico.*/
    public int getPuntiVitaNemico(){ return this.puntiVitaNemico; }
    /**Metodo setNomeNemico. Imposta il nome del nemico.
     * @param nomeNemico (String) parametro che non deve essere null, oppure vuoto.
     * @throws IllegalArgumentException se il nome è null o blank.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public void setNomeNemico(String nomeNemico) {
        if(ClasseUtility.controllaStringa(nomeNemico,"Nemico")) {
            /*Se passo un parametro non valido la classe utility già ha lanciato l'eccezione.
             * Così evito di fare un metodo set con un parametro sbagliato.*/
            this.nomeNemico = nomeNemico;
        }
    }
    /**Metodo setPuntiVitaNemico. Imposta i punti vita del nemico.
     * @param puntiVitaNemico (int) valore dei punti vita, deve essere >= 0.
     * @throws IllegalArgumentException se puntiVita è negativo.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public void setPuntiVitaNemico(int puntiVitaNemico) {
        if (ClasseUtility.verificaNumeriInteri(puntiVitaNemico, EnumControlloCreazioneAggiornamento.AGGIORNAMENTO, "Nemico"))
        {    /*Come sopra, se passo un intero <0, la classe utility già ha lanciato l'eccezione.*/
            this.puntiVitaNemico = puntiVitaNemico;
        }
    }
    /**Metodo toString:
     *@return una descrizione del nemico*/
    @Override
    public String toString(){
        return "CLASSE NEMICO!\n"+
                "NOME: "+this.getNomeNemico()+"\n"+
                "VITA:"+this.getPuntiVitaNemico();
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getNomeNemico(), this.getPuntiVitaNemico());
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Nemico)) return false;
            Nemico other = (Nemico)obj;
        return this.getNomeNemico().equals(other.getNomeNemico()) &&
                (this.getPuntiVitaNemico() == other.getPuntiVitaNemico());
    }
    /**Metodo astratto che dovrà essere riscritto in tutte le classi che estenderanno Nemico.
     * Questo perché magari è utile scoprire se un nemico può morire in qualche modo e un altro in un altro.
     * @return (boolean) true, se è ancora vivo (se ha ancora della vita >0).
     * False, altrimenti (se non ha più vita).*/
    public abstract boolean controllaSeNemicoInVita();

    /**Metodo astratto che dovrà essere riscritto in tutte le classi che estenderanno Nemico.
     * Questo perché magari perché un nemico può attaccare in qualche modo e un altro in un altro.
     * @return (boolean) true, se l'attacco è andato a buon fine.
     * False, altrimenti (se l'attacco non è andato a buon fine).
    public abstract boolean attaccaFisicamente(Eroe e);*/

    /*TODO: a parte i test, vedi di fare qualcosa per serializzare i personaggi!*/
}