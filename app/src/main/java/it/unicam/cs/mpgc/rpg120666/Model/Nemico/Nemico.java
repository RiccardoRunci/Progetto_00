/**Classe astratta Nemico:
 * Questa classe rappresenta un generico Nemico (come Oggetto, è astratta perché non mi interessava creare un generico
 * Nemico, viene estesa dalla sue sottoclassi che creano dei nemici specifici).
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Mago
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Guerriero
 * Se si vorrà aggiungere un nuovo nemico si dovrà creare la sua classe specifica apposito (come queste 2 sopra),
 * Poi si dovrà aggiungere il suo case nel FactoryNemici:
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
 *
 * La nuova classe nemico_Specifico dovrà implementare anche ElementiPerMatrice & InterfacciaPerPersonaggi.
 * 1)ElementiPerMatrice serve per creare la matrice di diversi tipi di Oggetti (Arma, Medikit, Mago, Eroe, ecc...).
 * 2)Serve anche per sapere cosa succederà a loro se verranno calpestatiDallEroe (ogni oggetto o nemico può reagire diversamente).
 *   Tramite il metodo "calpestataDaEroe(Eroe e)".
 *   @Note qui presumibilmente faccio una violazione del principio solid ISP
 * 3)Serve per sapere che tipo sono gli elementi che implementano ElementiPerMatrice (Eroe, Mago, ecc...).
 *   Tramite il metodo "public String restituisciTipoOggetto()"
 * 4)con il metodo veroVolto = riesco ad avere un raggruppamento per tipologia:
 *                             Oggetto = (Medikit, Arma);
 *                             Nemici = (Mago, Guerriero)
 * @see it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice
 *
 * InterfacciaPersonaggi (interfaccia funzionale che ha "eseguiAttacco(Eroe e)), il quale dovrà essere sovrascritto
 *                        in tutte le sotto classi di Nemici (perché ogni nemico attacca diversamente).
 *                        Mi evita di scrivere anche istance-of!
 * @see it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi
 * @author Runci Riccardo.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public abstract class Nemico extends Personaggio implements InterfacciaPerPersonaggi, ElementiPerMatrice
{
    private final int dannoCausato;
    /** Metodo costruttore:
     * @param nomePersonaggio = il nome del personaggio che (dovrà superare i controlli della classe VerificaParametri)
     *                          poi sarà passato al costruttore di Personaggio tramite super().
     * @param puntiVitaPersonaggio = i puntiVita del personaggio (anche loro sottoposti a controlli della classe VerificaParametri)
     *                               poi trasmessi al costruttore di Personaggio tramite super().
     * @param dannoCausato = Unico attributo di questa classe, indica la potenza di danno generato da un attacco di Eroe.
     *                       @note Questo parametro può essere aumentato tramite l'uso di un Oggetto Arma.
     * @throws IllegalArgumentException se i controlli non superano quelli di VerificaParametri.
     * @author Runci Riccardo.
     * @since versione 1.0. */
    public Nemico(String nomePersonaggio, int puntiVitaPersonaggio, int dannoCausato)
    {
        if((!VerificaParametri.verificaStringhe(nomePersonaggio))
                || (!VerificaParametri.verificaInteri(puntiVitaPersonaggio,CREAZIONE))
                || (!(VerificaParametri.verificaInteri(dannoCausato,CREAZIONE))))
            throw new IllegalArgumentException("");
        super(nomePersonaggio,puntiVitaPersonaggio);
        this.dannoCausato = dannoCausato;
    }
    /**Metodo getDannoCausato
     * @return dannoCausato = l'intero che indica i punti vita (che l'eroe toglie al nemico) dopo averlo colpito.*/
    public int getDannoCausato() {return this.dannoCausato;}
    /**Metodo toString:
     * @override del metodo toString della classe Object.
     * @return String = una stringa che descrive un istanza di Oggetto Nemico.
     *                  Questa descrizione è formata da "Classe Personaggio"+ il nome e i punti vita del Personaggio (prese da super)
     *                  e il danno causato (preso da questa classe qui).*
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public String toString(){
        return "CLASSE NEMICO!\n"+
                "NOME: "+super.getNomePersonaggio()+"\n"+
                "VITA:"+super.getPuntiVitaPersonaggio()+"\n"+
                "DANNO CAUSATO: "+this.dannoCausato+"\n";
    }
    /**Metodo hashCode:
     *@override del metodo hashCode della classe Object.
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.dannoCausato);
    }
    /**Metodo equals:
      *@override del metodo equals della classe Personaggio.
      *@return (boolean) True, se i due oggetti confrontati sono uguali.
      *                  False, altrimenti.
      *@since versione 1.0.
      *@autor Runci Riccardo.*/
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Nemico)) return false;
        if(!super.equals(obj)) return false; //riga da aggiungere in un equals di una sottoclasse!
            Nemico other = (Nemico)obj;
        return this.getDannoCausato()==(other.getDannoCausato());
    }
    /**Metodo che deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi.
     * @see InterfacciaPerPersonaggi
     * Lo metto abstract così da farlo implementare nelle sottoclassi di Nemico (come ho fatto per Personaggio).
     * Nemico è una classe astratta, non si può istanziare.
     * Questo metodo tornerà utili per eliminare gli istance-of nella classe ManagerCombattimentoEroeNemico
     * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerCombattimentoEroeNemico
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public abstract boolean eseguiAttacco(Personaggio e);
    /**Metodo veroVolto (preso dall'interfaccia ElementiPerMatrice):
     * @Override ElementiPerMatrice.
     * Messo final, significa che è scritto in questa super classe e le sue sotto classi lo prendono
     * così come è senza che possono modificarlo.
     * Mi fà molto comodo in MatriceFineGioco, dove io scansiono la  matrice in cerca di Nemici.
     * Dato che le sottoclassi di Nemico  sono anche loro nemici, evito di dover scrivere (nella classe ManagerFineGioco)
     * @code if (elemento==MAGO || elemento==GUERRIERO || elemento==NUOVO_NEMICO_CHE_METTERO'_IN_FUTURO)
     *       then numNemici++;
     * In questo senso mi rende il codice estendibile. Quando andrò a inserire un nuovo nemico nel gioco (farò estendere
     * questa classe Nemico e avrò in automatico questo metodo che mi ritorna "Nemico"!
     * @return String "Nemico", sempre!
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public final String veroVolto()
    {
        return "Nemico";
    }
}
