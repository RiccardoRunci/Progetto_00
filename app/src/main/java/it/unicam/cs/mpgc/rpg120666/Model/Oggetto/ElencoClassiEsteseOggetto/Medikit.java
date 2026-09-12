package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.InterfaccePerOggetti.InterfacciaMedikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Medikit extends Oggetto implements InterfacciaMedikit
{
    private int puntiVitaRistabiliti;
    public Medikit() {}
    public Medikit(String nomeOggetto, int puntiVitaRistabiliti)
    {
      if(!ClasseUtility.controllaStringa(nomeOggetto,"Medikit"))
          throw new  IllegalArgumentException("Nome Oggetto medikit"+nomeOggetto);
      if(!ClasseUtility.verificaNumeriInteri(puntiVitaRistabiliti,EnumControlloCreazioneAggiornamento.CREAZIONE,"Medikit"))
              throw new  IllegalArgumentException("punti vita ristabiliti da medikit: "+puntiVitaRistabiliti+"\n"+
                      "Non validi!");

            super(nomeOggetto);
            this.puntiVitaRistabiliti = puntiVitaRistabiliti;
    }
    public int getPuntiVita() { return this.puntiVitaRistabiliti; }
    public void setPuntiVitaRistabiliti(int puntiVitaRistabiliti)
    {
        if(ClasseUtility.verificaNumeriInteri(puntiVitaRistabiliti,EnumControlloCreazioneAggiornamento.AGGIORNAMENTO,"MEDIKIT"))
            this.puntiVitaRistabiliti=puntiVitaRistabiliti;
    }
    @Override
    public boolean usaMedikit(Eroe eroe)
    {
        if(eroe==null || eroe.controllaSeMorto())
        {
            throw new  IllegalArgumentException("Eroe non valido o morto!");
        }
         else
        {
            eroe.setPuntiVitaEroe(eroe.getPuntiVitaEroe() + this.puntiVitaRistabiliti);
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