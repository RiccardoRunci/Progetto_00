package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaArma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.UsaOggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Arma extends Oggetto implements InterfacciaArma
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
        if (e==null || e.controllaSeMorto())
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
        if(e==null || e.controllaSeMorto())
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
}
