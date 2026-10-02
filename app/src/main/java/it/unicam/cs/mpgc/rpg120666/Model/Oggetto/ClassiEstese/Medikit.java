package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaMedikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Medikit extends Oggetto implements InterfacciaMedikit, ElementiPerMatrice
{
    private final int puntiVitaRistabiliti;

    public Medikit(String nomeOggetto, int puntiVitaRistabiliti)
    {
        if((!VerificaParametri.verificaStringhe(nomeOggetto)) || (!VerificaParametri.verificaInteri(puntiVitaRistabiliti,CREAZIONE)))
            throw new IllegalArgumentException("");
        super(nomeOggetto);
        this.puntiVitaRistabiliti = puntiVitaRistabiliti;
    }
    //Metodi get (set no perché puntiVitaRistabiliti è final):
    public int getPuntiVitaRistabiliti() { return this.puntiVitaRistabiliti; }
    /**Metodo esteso dalla super classe Oggetto, che a sua volta viene ereditato
     * (da Oggetto) dall' interfaccia UsaOggetto. Così da permettere a ogni sotto classe che estende Oggetto
     * di utilizzare l'oggetto con scopi diversi! Questo dovrebbe impedire la scrittura di 'instance of'
     * nella futura matrice gioco! Speriamo!*/
    @Override
    public boolean usaOggetto(Eroe e)
    {
        if(e==null || !e.controllaSeVivo())
            throw new IllegalArgumentException("Eroe passato al metodo usaOggetto di Medikit == null.\n" +
                    "Oppure Eroe morto!\n");
        else
        {
            //todo una volta corretto, elimina i printf!
            System.out.println("Prima la vita dell'eroe era: "+e.getPuntiVitaPersonaggio());
            return this.curaEroe(e);
        }
    }

    /**Questo DICHIARATO nell'interfacciaMedikit! E USATO in questa classe Medikit!
     * Viene richiamato dal metodo sopra!*/
    @Override
    public boolean curaEroe(Eroe e)
    {
        if(e==null || !e.controllaSeVivo())
        {
            throw new  IllegalArgumentException("Eroe non valido o morto!");
        }
        else
        {
            e.setPuntiVitaPersonaggio(e.getPuntiVitaPersonaggio() + this.puntiVitaRistabiliti);
            //todo una volta corretto, elimina i printf!
            System.out.println("Dopo aver usato il medikit, la vita dell'eroe= "+e.getPuntiVitaPersonaggio());
            /*Mi devo ricordare di eliminare l'oggetto dallo zaino!*/
            e.getZaino().eliminaOggettoDalloZaino(this);
            return true;
        }
    }
    @Override
    public String toString()
    {
        return "OGGETTO MEDIKIT!\n"+
                "NOME= "+super.getNomeOggetto()+"\n"+
                "+VITA= "+this.puntiVitaRistabiliti;
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.puntiVitaRistabiliti);
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Medikit)) return false;
        Medikit other = (Medikit)obj;
        return this.getPuntiVitaRistabiliti()==other.getPuntiVitaRistabiliti();
    }

    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi. L'ho fatto solo per non darla vinta all' IA.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Medikit";
    }

    /**@Note avrei dovuto implementare l'interfaccia ElementiPerMatrice in Oggetto e non nelle
     * sue sottoclassi. Questo metodo mi serviva (non in questa classe ma in Nemico) per contare il
     * numero di Nemici generali ancora in vita dentro la matrice.
     * Può essere utile anche per contare gli oggetti, ma oramai l'ho messo qui e quando aggiungo
     * (in futuro) un nuovo Oggetto, dovrò sovrascrivere anche questo metodo scrivendoci "Oggetto".
     * Non era qui che era meglio metterlo, ma anche qui (per la conta degli oggetti quando scansiono la matrice)
     * dovrebbe andare bene.
     * Ne parlo meglio dentro l'interfaccia ElementiPerMatrice e dentro la classe ManagerFineGioco.*/
    @Override
    public String veroVolto() {
        return "Oggetto";
    }

    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i vari istanceof dentro la classe ManagerIncontroNemiciOggetti.
     * Perchè la matriceGioco passata al metodo ManagerIncontroNemiciOggetti è di tipo interfaccia ElementiPerMatrice.
     * Essendo di quel tipo interfaccia ElementiPerMatrice, deve avere anche questo metodo implementato.
     * Così da riuscire a fare una cosa tipo ElementoDellaMatrice (che sia oggetto, nemico, arma, ecc...).calpestatoDaEroe(eroe che calpesta l'oggetto).
     * Così da usare le stesse righe di codice, ma ogni oggetto le implementa in modo diverso!*/
    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        //System.out.println("L'EROE HA TROVATO UN MEDIKIT!");
        if(e.prendiOggetto(this))
        {
            //System.out.println("L'eroe: "+e.toString()+"ha raccolto l'oggetto: "+this.toString());
            return true;
        }
        else
        {
            System.out.println("accaduto qualche problema nel raccogliere l'oggetto:\n"+this.toString());
        }
        return false;
        //todo quando funzionerà tutto fai direttamente: return e.prendiOggetto(this);
    }
}
