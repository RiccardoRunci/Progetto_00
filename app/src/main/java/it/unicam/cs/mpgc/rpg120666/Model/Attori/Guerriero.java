package it.unicam.cs.mpgc.rpg120666.Model.Attori;
import it.unicam.cs.mpgc.rpg120666.Model.InterfaccePerPersonaggi.InterfacciaGuerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Guerriero extends Personaggio implements InterfacciaGuerriero
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
    public boolean controllaSePersonaggioInVita()
    {
        if (super.getPuntiVitaPersonaggio()==0) /*è morto*/ {return false;}
        else /*è in vita*/ return true;
    }

    @Override
    public boolean eseguiAttaccoFisico(Personaggio personaggio)
    {
        //todo ANCHE questo metodo fà più o meno la stessa cosa dei 2 metodi di mago, che vogliamo fare?
        if(personaggio.controllaSePersonaggioInVita())
        {
            if(personaggio.getPuntiVitaPersonaggio()<=DANNO_DA_FORZA)
            {  //Colpisco il nemico e muore!
                personaggio.setPuntiVitaPersonaggio(0);
                return true;
            }
            else
            {
                //Colpisco il nemico e sopravvive!
                personaggio.setPuntiVitaPersonaggio(personaggio.getPuntiVitaPersonaggio() - DANNO_DA_FORZA);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString()
    {
        return "CLASSE GUERRIERO!\n"+
                "NOME: "+super.getNomePersonaggio()+"\n"+
                "VITA: "+super.getPuntiVitaPersonaggio()+"\n"+
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
