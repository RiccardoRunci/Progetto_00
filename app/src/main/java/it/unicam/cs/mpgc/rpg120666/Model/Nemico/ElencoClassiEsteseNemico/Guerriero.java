package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.InterfaccePerNemico.InterfacciaGuerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Guerriero extends Nemico implements InterfacciaGuerriero
{
    private static final int DANNO_DA_FORZA= 10;
    private int puntiScudo;

    public Guerriero(){}
    public Guerriero(String nomeGuerriero,int puntiVita, int puntiScudo)
    {
        super(nomeGuerriero,puntiVita);
        if(ClasseUtility.verificaNumeriInteri(puntiScudo, EnumControlloCreazioneAggiornamento.CREAZIONE,"Guerriero"))
            this.puntiScudo = puntiScudo;
    }
    public void setPuntiScudo(int puntiScudo)
    {
        if (ClasseUtility.verificaNumeriInteri(puntiScudo,EnumControlloCreazioneAggiornamento.AGGIORNAMENTO,"Guerriero"))
            this.puntiScudo = puntiScudo;
    }
    public int getPuntiScudo()
    {
        return this.puntiScudo;
    }
    @Override
    public boolean controllaSeNemicoInVita()
    {
        if (super.getPuntiVitaNemico()==0) /*è morto*/ {return false;}
        else /*è in vita*/ return true;
    }

    @Override
    public boolean eseguiAttaccoFisico(Eroe eroe)
    {
        //todo ANCHE questo metodo fà più o meno la stessa cosa dei 2 metodi di mago, che vogliamo fare?
        if(!eroe.controllaSeMorto()) //se l'eroe è ancora in vita!
        {
            if(eroe.getPuntiVitaEroe()<=DANNO_DA_FORZA)
            {  //Colpisco l'eroe e muore!
                eroe.setPuntiVitaEroe(0);
                return true;
            }
            else
            {
                //Colpisco l'eroe e sopravvive!
                eroe.setPuntiVitaEroe(eroe.getPuntiVitaEroe() - DANNO_DA_FORZA);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString()
    {
        return "CLASSE GUERRIERO!\n"+
                "NOME: "+super.getNomeNemico()+"\n"+
                "VITA: "+super.getPuntiVitaNemico()+"\n"+
                "SCUDO: "+this.getPuntiScudo();
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(puntiScudo);
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Guerriero)) return false;
        if(!super.equals(obj)) return false; //Riga da aggiungere in un equals di una classe che ne estende un altra.
        Guerriero other = (Guerriero)obj;
        return this.getPuntiScudo()==(other.getPuntiScudo());
    }
}
