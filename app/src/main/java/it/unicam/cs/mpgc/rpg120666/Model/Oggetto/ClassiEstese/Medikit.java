package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaMedikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Medikit extends Oggetto implements InterfacciaMedikit
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
