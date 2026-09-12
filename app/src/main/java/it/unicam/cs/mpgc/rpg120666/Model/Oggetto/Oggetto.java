/**Classe astratta che verrà estesa dai vari Oggetti che comporranno il gioco.
 * @author Runci Riccardo.
 * @since versione 0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public abstract class Oggetto implements ElementiPerMatrice
{
    private String nomeOggetto;
    /**Costruttore vuoto che mi servirà per javaFx e/o Hibernate.*/
    public Oggetto(){}
    /**Fa Creare una nuova istanza di Oggetto da parte di una classe che la estenderà.
     * @param nomeOggetto (String) il nome dell'Oggetto, non deve essere null e vuoto.
     * @throws IllegalArgumentException il nome è null o vuoto, e se la vita è<=0.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public Oggetto(String nomeOggetto)
    {
        if (ClasseUtility.controllaStringa(nomeOggetto,"Oggetto")) {
            this.nomeOggetto = nomeOggetto;
        }

    }
    /**Metodo getNomeOggetto.
     * @return (String) il nome dell'Oggetto.*/
    public String getNomeOggetto() { return this.nomeOggetto; }

    /**Metodo setNomeOggetto. Imposta il nome dell'Oggetto.
     * @param nomeOggetto (String) parametro che non deve essere null, oppure vuoto.
     * @throws IllegalArgumentException se il nome è null o blank.
     * @see it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility = Classe per i controlli sul nome e sui punti vita generali.*/
    public void setNomePersonaggio(String nomeOggetto) {
        if(ClasseUtility.controllaStringa(nomeOggetto,"Oggetto")) {
            /*Se passo un parametro non valido la classe utility già ha lanciato l'eccezione.
             * Così evito di fare un metodo set con un parametro sbagliato.*/
            this.nomeOggetto = nomeOggetto;
        }
    }
    /**Metodo toString:
     *@return una descrizione dell'Oggetto.*/
    @Override
    public String toString()
    {
        return "CLASSE OGGETTO!\n"+
                "NOME: "+this.getNomeOggetto()+"\n";
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Oggetto)) return false;
        Oggetto other = (Oggetto)obj;
        return this.getNomeOggetto().equals(other.getNomeOggetto());
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getNomeOggetto());
    }
}
