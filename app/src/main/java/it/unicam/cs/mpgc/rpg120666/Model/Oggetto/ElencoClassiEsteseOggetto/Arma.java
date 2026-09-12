package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.InterfaccePerOggetti.InterfacciaArma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Arma extends Oggetto implements InterfacciaArma
{
    private int puntiDannoPiu;
    public Arma() { }
    public Arma(String nomeOggetto,int puntiDannoPiu)
    {
        if(!ClasseUtility.controllaStringa(nomeOggetto,"Medikit"))
            throw new IllegalArgumentException("");
        if(!ClasseUtility.verificaNumeriInteri(puntiDannoPiu, EnumControlloCreazioneAggiornamento.AGGIORNAMENTO,"ARMA"))
            throw new IllegalArgumentException("");
        super(nomeOggetto);
        this.puntiDannoPiu = puntiDannoPiu;
    }

    @Override
    public void intensificaDanno(Eroe e)
    {
        if(e==null || e.controllaSeMorto())
            throw new IllegalArgumentException("Eroe Morto o ==null");
        else
            e.setDannoCausato(e.getDannoCausato()+this.puntiDannoPiu);
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
