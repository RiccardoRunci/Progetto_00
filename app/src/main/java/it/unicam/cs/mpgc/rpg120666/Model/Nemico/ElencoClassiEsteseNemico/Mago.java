package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.InterfaccePerNemico.IntefacciaMago;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Mago extends Nemico implements IntefacciaMago
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
    public boolean eseguiAttaccoMagico(Nemico nemico)
    {
        if (this.numeroMagieDisponibili==0 || (!this.controllaSeNemicoInVita()))
        return false;
        else if(nemico.controllaSeNemicoInVita())
        {
            if(nemico.getPuntiVitaNemico()<=DANNO_DA_MAGIA)
            {
                this.numeroMagieDisponibili--;
                nemico.setPuntiVitaNemico(0);
                return true;
            }
            else
            {
                this.numeroMagieDisponibili--;
                nemico.setPuntiVitaNemico(nemico.getPuntiVitaNemico() - DANNO_DA_MAGIA);
                return true;
            }
        }
        else return false;
    }

    @Override
    public boolean eseguiCuraMagica(Nemico nemico)
    {
        if (this.numeroMagieDisponibili==0 || (!this.controllaSeNemicoInVita()))
            return false;
        else  if(nemico.controllaSeNemicoInVita())
        {
            this.numeroMagieDisponibili--;
            nemico.setPuntiVitaNemico(nemico.getPuntiVitaNemico()+CURA_MAGICA);
            return true;
        }
        return false;
    }
    /**Metodo che serve per vedere se il personaggio ha (o non ha) punti vita.
     * @return (boolean) true, se è ancora vivo (se ha ancora della vita >0).
     * False, altrimenti (se non ha più vita).*/
    @Override
    public boolean controllaSeNemicoInVita()
    {
        if (super.getPuntiVitaNemico()==0) /*è morto*/ {return false;}
        else /*è in vita*/ return true;
    }
    @Override
    public String toString()
    {
        return "CLASSE MAGO\n"+
                "NOME: "+super.getNomeNemico()+"\n"+
                "VITA: "+super.getPuntiVitaNemico()+"\n"+
                "MAGIE: "+this.getNumeroMagieDisponibili()+"\n";
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
