package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaMedikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.UsaOggetto;
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
    public int getPuntiVita() { return this.puntiVitaRistabiliti; }
    /**Metodo esteso dalla super classe Oggetto, che a sua volta viene ereditato
     * (da Oggetto) dall' interfaccia UsaOggetto. Così da permettere a ogni sotto classe che estende Oggetto
     * di utilizzare l'oggetto con scopi diversi! Questo dovrebbe impedire la scrittura di 'instance of'
     * nella futura matrice gioco! Speriamo!*/
    @Override
    public boolean usaOggetto(Eroe e)
    {
        if(e==null || e.controllaSeMorto())
            throw new IllegalArgumentException("Eroe passato al metodo usaOggetto di Medikit == null.\n" +
                    "Oppure Eroe morto!\n");
        else
        {
            /*Aggiungo oggetto nello zaino!*/
            e.getZaino().aggiungiOggettoNelloZaino(this);
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
        if(e==null || e.controllaSeMorto())
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
        return java.util.Objects.hash(this.getPuntiVita());
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Medikit)) return false;
        Medikit other = (Medikit)obj;
        return this.getPuntiVita()==other.getPuntiVita();
    }


}
