package it.unicam.cs.mpgc.rpg120666.Model.Attori;
import it.unicam.cs.mpgc.rpg120666.Model.InterfaccePersonaggi.IntefacciaMago;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Mago extends Personaggio implements IntefacciaMago
{
    private int numeroMagieDisponibili;
    public static final int DANNO_DA_MAGIA= 3;
    public static final int CURA_MAGICA= 6;

    public Mago(){}
    public Mago(String nomeMago, int puntiVitaMago, int numeroMagieDisponibili)
    {
        super(nomeMago, puntiVitaMago);
        if(ClasseUtility.verificaNumeriInteri(numeroMagieDisponibili, EnumControlloCreazioneAggiornamento.CREAZIONE, this.getClass().getName()))
        {
            this.numeroMagieDisponibili = numeroMagieDisponibili;
        }
    }

    public void setNumeroMagieDisponibili(int numeroMagieDisponibili)
    {
        if(ClasseUtility.verificaNumeriInteri(numeroMagieDisponibili, EnumControlloCreazioneAggiornamento.AGGIORNAMENTO, this.getClass().getName()))
        this.numeroMagieDisponibili = numeroMagieDisponibili;
    }
    public int getNumeroMagieDisponibili()
    {
        return this.numeroMagieDisponibili;
    }

    @Override
    public boolean eseguiAttaccoMagico(Personaggio personaggio)
    { //todo Questi 2 bei metodi fanno più o meno la stessa cosa, quindi devo fare quella magia dell'interfacce.
        if (this.numeroMagieDisponibili==0)
        return false;
        else if(personaggio.controllaSePersonaggioInVita())
        {
            this.numeroMagieDisponibili--;
            personaggio.setPuntiVitaPersonaggio(personaggio.getPuntiVitaPersonaggio()-DANNO_DA_MAGIA);
            return true;
        }
        else return false; //il personaggio è già morto
    }

    @Override
    public boolean eseguiCuraMagica(Personaggio personaggio)
    { //todo Questi 2 bei metodi fanno più o meno la stessa cosa, quindi devo fare quella magia dell'interfacce.
        if (this.numeroMagieDisponibili==0)
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
        return "Sono un Personaggio, della classe"+this.getClass()+"\n"+
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
