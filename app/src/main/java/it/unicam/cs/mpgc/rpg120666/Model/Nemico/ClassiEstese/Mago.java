/**Classe Mago Estence la classe Nemico
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico
 * Questa classe rappresenta un Mago (un oggetto specifico di Nemico).
 * Implementa IntefacciaPersonaggi tramite il metodo "eseguiAttacco(Eroe e)".
 * @see it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi
 * Implementa ElementiPerMatrice tramite la sovra-scrizione dei metodi:
 *            restituisciTipoOggetto, calpestataDaEroe, Ma non "veroVolto" (perché è final sulla classe Nemico).
 * @see it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice
 * @author Runci Riccardo.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerCombattimentoEroeNemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.AGGIORNAMENTO;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Mago extends Nemico implements InterfacciaPerPersonaggi, ElementiPerMatrice
{
    private int numeroMagie;

    /**Metodo costruttore:
     *@param nomePersonaggio Che andrà (tramite super) al costruttore di Nemico, successivamente al costruttore di Personaggio.
     *@param puntiVitaPersonaggio Che andrà (tramite super) al costruttore di Nemico, successivamente al costruttore di Personaggio.
     *@param dannoCausato che andrà (tramite super) al costruttore di Nemico.
     *@param numeroMagie l'unico campo di questa classe = rappresenta il numero di magie che può compiere il mago.
     *                    Con numeroMagie il mago può attaccare e può rigenerare i suoi punti vita.
     *                    Se non ha più magie a sua disposizione, muore.
     *@throws IllegalArgumentException se i controlli di VerificaParametri non vengono soddisfatti.
     *@author Runci Riccardo.
     *@since versione 1.0.*/
    public Mago(String nomePersonaggio, int puntiVitaPersonaggio,int dannoCausato,int numeroMagie)
    {
        if(!(VerificaParametri.verificaInteri(numeroMagie,CREAZIONE)))
            throw new IllegalArgumentException("Parametro numero magie passato al costruttore di Mago non valido");
        this.numeroMagie=numeroMagie;
        super(nomePersonaggio, puntiVitaPersonaggio,dannoCausato);
    }
    /**Metodo getNumeroMagie:
     *@return int il numero di magie a disposizione!
     *@author Runci Riccardo.
     *@since versione 1.0.*/
    public int getNumeroMagie() {return this.numeroMagie;}
    /**Metodo setNumeroMagie:
     *@param numeroMagie il numero di magie passate!
     *@throws IllegalArgumentException se i controlli di VerificaParametri non vengono soddisfatti!
     *@author Runci Riccardo.
     *@since versione 1.0.*/
     public void setNumeroMagie(int numeroMagie)
     {
         if(VerificaParametri.verificaInteri(numeroMagie,AGGIORNAMENTO))
            this.numeroMagie = numeroMagie;
         else
             throw new IllegalArgumentException("Parametro numMagie al metodo setNumMagie di Mago non valido!");
     }

     /**Metodo restituisciTipoOggetto che viene preso dall'interfaccia ElementiPerMatrice:
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi.
     *@note L'ho fatto solo per non darla vinta all' IA.
     *@return String "Mago".
     *@author Runci Riccardo.
     *@since versione 1.0.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Mago";
    }
    /**Metodo calpestataDaEroe che viene preso dall'interfaccia ElementiPerMatrice:
     * Serve per evitare i vari istanceof dentro la classe ManagerIncontroNemiciOggetti.
     * Perchè la matriceGioco passata al metodo ManagerIncontroNemiciOggetti è di tipo interfaccia ElementiPerMatrice.
     * Essendo di quel tipo interfaccia ElementiPerMatrice, deve avere anche questo metodo implementato.
     * Così da riuscire a fare una cosa tipo ElementoDellaMatrice (che sia oggetto, nemico, arma, ecc...).calpestatoDaEroe(eroe che calpesta l'oggetto).
     * Così da usare le stesse righe di codice, ma ogni oggetto le implementa in modo diverso!
     * @param e = l'eroe che è entrato nella stessa casella di questo Mago.
     * @return il verdetto del metodo combattimento della classe ManagerCombattimentoEroeNemico.
     *         True se l'eroe ha vinto lo scontro totale con questo nemico e riuscira a spostarsi nella cella dove stava questo Mago.
     *@see ManagerCombattimentoEroeNemico
     *@throws IllegalArgumentException se l'eroe passato è = null!
     *@author Runci Riccardo.
     *@since versione 1.0.*/
    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        if(e==null) throw new IllegalArgumentException("Eroe che ha calpestato il mago = null!");
        //System.out.println("L'EROE HA INCONTRATO UN MAGO!");
        ManagerCombattimentoEroeNemico manCombat= new ManagerCombattimentoEroeNemico(e,this);
        /* manCombat.Combattimento deve ritornare:
        * True se il combattimento è finito e ha vinto l'eroe, così (l'eroe) si può muovere.
        * False se il combattimento è stato vinto dal Nemico!*/
        return manCombat.combattimento();
    }

    /**Metodo eseguiAttacco che deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi:
     * @see InterfacciaPerPersonaggi
     * @param e = Eroe a cui sferrare l'attacco.
     * @return true = se ha effettuato con successo l'attacco a eroe!
     *         False = se non è riuscito ad attaccare l'eroe!
     * @throws IllegalArgumentException (se e ==null).
     * @author Runci Riccardo.
     * @since versione 1.0.*/
    @Override
    public boolean eseguiAttacco(Personaggio e)
    {
        if(e!=null) //Controllo se l'eroe passato è un parametro != null.
            /*Se il mago è ancora vivo e ha le magie, allora il mago può attaccare l'eroe!*/
            if(this.numeroMagie>0 && this.controllaSeVivo())
            {
            //System.out.println("PRIMA L'eroe HA VITA: "+e.getPuntiVitaPersonaggio());
               //Attacca l'eroe:
            e.setPuntiVitaPersonaggio(e.getPuntiVitaPersonaggio() - this.getDannoCausato());
            //System.out.println("DOPO L'eroe HA VITA: "+e.getPuntiVitaPersonaggio());

            this.numeroMagie--; //-1 magia perché ha attaccato!
            this.eseguiCuraMagica(); //è un mago e si auto-cura!
            this.numeroMagie--; //-1 magia perché si è autocurato!
            return true; //L'attacco del mago (ai danni dell'eroe) Ha avuto successo!
            }
            else if(this.numeroMagie<=0) //se il numero magie del mago scende a 0, il mago auto-muore.
            {
                this.setPuntiVitaPersonaggio(0);
                return false;
            }
            else
                return false; //L'attacco del mago ai danni dell'eroe NON è andato a buon fine.
        else
            throw new IllegalArgumentException("Eroe passato al metodo eseguiAttacco di Mago: invalido!");
    }
    /**Metodo eseguiCuraMagica privato che ho implementato per differenziare il mago dall'altro Nemico (guerriero).
     * Consiste nel far recuperare un pò di salute al mago.
     * Viene richiamato sopra (nel metodo implementato dall'interfaccia InterfacciaPerNemici),
     * così da permettere al mago di auto rigenerarsi a ogni attacco che sferra.
     * @author Runci Riccardo.
     * @since versione 1.0.**/
    private void eseguiCuraMagica()
    {
        /*Per attaccare e per autocurarsi c'è bisogno di magie disponibili.
         *Il numero delle magie lo controllo sopra, insieme a se il mago è ancora in vita!
         * Essendo il metodo privato non dovrebbe essere visibile da un istanza mago*/
        this.setPuntiVitaPersonaggio(this.getPuntiVitaPersonaggio()+5);
        this.numeroMagie--;
    }
    /**Metodo toString:
     * @override del metodo toString della classe Nemico.
     * @return String = una stringa che descrive un istanza di Oggetto Mago.
     *                  Questa descrizione è formata da "Classe Personaggio"+ il nome e i punti vita del Personaggio,
     *                  il danno causato(prese da super) e dal numero di magie (preso da questa classe qui).*
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public String toString(){
        return "CLASSE Mago!\n"+
                "NOME: "+super.getNomePersonaggio()+"\n"+
                "VITA:"+super.getPuntiVitaPersonaggio()+"\n"+
                "DANNO CAUSATO: "+super.getDannoCausato()+"\n"+
                "NUMERO MAGIE: "+this.numeroMagie+"\n";
    }
    /**Metodo hashCode:
     *@override del metodo hashCode della classe Nemico.
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.numeroMagie);
    }
    /**Metodo equals:
     *@override del metodo equals della classe Nemico.
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
        Mago other = (Mago)obj;
        return this.numeroMagie==(other.numeroMagie);
    }
}
