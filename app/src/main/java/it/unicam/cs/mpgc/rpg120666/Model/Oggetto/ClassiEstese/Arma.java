package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaArma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Arma extends Oggetto implements InterfacciaArma, ElementiPerMatrice
{
    private int puntiDannoPiu;

    public Arma(String nomeOggetto, int puntiDannoPiu)
    {
        if(!(VerificaParametri.verificaStringhe(nomeOggetto)) ||  !(VerificaParametri.verificaInteri(puntiDannoPiu,CREAZIONE)))
            throw new IllegalArgumentException("Parametri passati al costruttore di Arma invalidi|");
        super(nomeOggetto);
        this.puntiDannoPiu = puntiDannoPiu;
    }


    @Override
    public String toString()
    { return "CLASSE ARMA!\n"+
            "NOME= "+super.getNomeOggetto()+"\n"+
            "+DANNO= "+this.puntiDannoPiu;
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.puntiDannoPiu);
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Arma)) return false;
        Arma other = (Arma)obj;
        return this.puntiDannoPiu==other.puntiDannoPiu;
    }
    /**Metodo abstract della super classe Oggetto, che a sua volta viene ereditato (da Oggetto) dall' interfaccia UsaOggetto.
     * Così da permettere a ogni sotto classe che estende Oggetto di utilizzare l'oggetto con scopi diversi!
     * Questo dovrebbe impedire la scrittura di 'instance of' nella futura matrice gioco! Speriamo!*/
    @Override
    public boolean usaOggetto(Eroe e)
    {
        /*Se l'eroe è null, allora lancia eccezione!*/
        if (e==null || !e.controllaSeVivo())
            throw new IllegalArgumentException("Eroe passato al metodo usaOggetto di Arma = null!\n" +
                    "Oppure l'eroe è morto!\n");
        else
        {
            //todo una volta corretto, elimina i printf!
            System.out.println("Prima i punti attacco di eroe erano: "+e.getDannoCausato()+"\n");
            return this.potenziaAttacco(e);
        }
    }
    /**Questo è il metodo che viene dall' interfacciaArma!*/
    @Override
    public boolean potenziaAttacco(Eroe e)
    {
        if(e==null || !e.controllaSeVivo())
        {
            throw new  IllegalArgumentException("Eroe non valido o morto!");
        }
        else
        {
            e.setDannoCausato(e.getDannoCausato() + this.puntiDannoPiu);
            //todo una volta corretto, elimina i printf!
            System.out.println("Ora il danno causato da eroe= "+e.getDannoCausato()+"\n");
            /*Mi devo ricordare di eliminare l'oggetto dallo zaino!*/
            e.getZaino().eliminaOggettoDalloZaino(this);
            return true;
        }
    }


    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi. L'ho fatto solo per non darla vinta all' IA.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Arma";
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
        System.out.println("L'EROE HA TROVATO UN ARMA!");
        if ((e.controllaSeVivo()) && e!=null)
        {
            //System.out.println("L'eroe:"+e.toString()+" ha raccolto l'oggetto: "+this.toString());
            e.prendiOggetto(this);
            //System.out.println("CONTENUTO ZAINO EROE:\n"+e.getZaino().toString());
            return true;
        }
        else
        {
            System.out.println("Accaduto qualche problema, l'eroe non è riuscito a prendere l'oggetto:\n"+this.toString());
            return false;
        }
        /*todo quando si è sicuri che funziona tutto sostituire
           tutto il codice sopra con: "return e.prendiOggetto(this);"*/
    }
}
