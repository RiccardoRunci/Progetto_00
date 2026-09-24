package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaArma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
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
    public boolean potenziaAttacco(Eroe e)
    {
        if(e==null || e.controllaSeMorto())
        {
            throw new  IllegalArgumentException("Eroe non valido o morto!");
        }
        else
        {
            e.setDannoCausato(e.getDannoCausato() + this.puntiDannoPiu);
            return true;
        }
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
}
