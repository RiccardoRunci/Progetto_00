package it.unicam.cs.mpgc.rpg120666.Model.Attori;
import it.unicam.cs.mpgc.rpg120666.Model.InterfaccePersonaggi.IntefacciaMago;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Mago extends Personaggio implements IntefacciaMago
{
    private int numeroMagieDisponibili;
    private static final int DANNO_DA_MAGIA= 3;
    private static final int CURA_MAGICA= 6;

    public Mago(){}
    public Mago(String nomeMago, int puntiVitaMago, int numeroMagieDisponibili)
    {
        super(nomeMago, puntiVitaMago);
        if(ClasseUtility.verificaNumeriInteri(numeroMagieDisponibili, EnumControlloCreazioneAggiornamento.CREAZIONE, "Mago"))
        {
            this.numeroMagieDisponibili = numeroMagieDisponibili;
        }
    }

    public void setNumeroMagieDisponibili(int numeroMagieDisponibili)
    {
        if(ClasseUtility.verificaNumeriInteri(numeroMagieDisponibili, EnumControlloCreazioneAggiornamento.AGGIORNAMENTO, "Mago"))
            this.numeroMagieDisponibili = numeroMagieDisponibili;
    }
    public int getNumeroMagieDisponibili()
    {
        return this.numeroMagieDisponibili;
    }

    @Override
    public boolean eseguiAttaccoMagico(Personaggio personaggio)
    {
        if (this.numeroMagieDisponibili==0 || (!this.controllaSePersonaggioInVita()))
        return false;
        else if(personaggio.controllaSePersonaggioInVita())
        {
            if(personaggio.getPuntiVitaPersonaggio()<=DANNO_DA_MAGIA)
            {
                this.numeroMagieDisponibili--;
                personaggio.setPuntiVitaPersonaggio(0);
                return true;
            }
            else
            {
                this.numeroMagieDisponibili--;
                personaggio.setPuntiVitaPersonaggio(personaggio.getPuntiVitaPersonaggio() - DANNO_DA_MAGIA);
                return true;
            }
        }
        else return false;
    }

    @Override
    public boolean eseguiCuraMagica(Personaggio personaggio)
    {
        if (this.numeroMagieDisponibili==0 || (!this.controllaSePersonaggioInVita()))
            return false;
        else  if(personaggio.controllaSePersonaggioInVita())
        {
            this.numeroMagieDisponibili--;
            personaggio.setPuntiVitaPersonaggio(personaggio.getPuntiVitaPersonaggio()+CURA_MAGICA);
            return true;
        }
        return false;
    }
    /**Metodo che serve per vedere se il personaggio ha (o non ha) punti vita.
     * @return (boolean) true, se è ancora vivo (se ha ancora della vita >0).
     * False, altrimenti (se non ha più vita).*/
    @Override
    public boolean controllaSePersonaggioInVita()
    {
        if (super.getPuntiVitaPersonaggio()==0) /*è morto*/ {return false;}
        else /*è in vita*/ return true;
    }
    @Override
    public String toString()
    {
        return "Sono un Personaggio, della classe Mago\n"+
                "Il mio nome è: "+super.getNomePersonaggio()+
                "I miei punti vita sono: "+super.getPuntiVitaPersonaggio()+
                "Il mio numero di magie disponibili è: "+this.getNumeroMagieDisponibili();
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(numeroMagieDisponibili);
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Mago)) return false;
        if(!super.equals(obj)) return false; //Riga da aggiungere in un equals di una classe che ne estende un altra.
        Mago other = (Mago)obj;
        return this.getNumeroMagieDisponibili()==(other.getNumeroMagieDisponibili());
    }
}
