package it.unicam.cs.mpgc.rpg120666.Model.Attori;
import it.unicam.cs.mpgc.rpg120666.Model.InterfaccePersonaggi.InterfacciaGuerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Guerriero extends Personaggio implements InterfacciaGuerriero
{
    public static final int DANNO_DA_FORZA= 10;
    private int puntiScudo;

    public Guerriero(){}
    public Guerriero(String nomeGuerriero,int puntiVita, int puntiScudo)
    {
        super(nomeGuerriero,puntiVita);
        if(ClasseUtility.verificaNumeriInteri(puntiScudo, EnumControlloCreazioneAggiornamento.CREAZIONE,this.getClass().getName()))
            this.puntiScudo = puntiScudo;
    }
    public void setPuntiScudo(int puntiScudo)
    {
        if (ClasseUtility.verificaNumeriInteri(puntiScudo,EnumControlloCreazioneAggiornamento.AGGIORNAMENTO,this.getClass().getName()))
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
        // todo Intanto li scrivo così posso cominciare a fare i test. Poi magari li riscrivo con le interfacce.
        if(personaggio.controllaSePersonaggioInVita())
        {
            personaggio.setPuntiVitaPersonaggio(personaggio.getPuntiVitaPersonaggio()-DANNO_DA_FORZA);
            return true;
        }
        else return false; //il personaggio è già morto
    }
    @Override
    public String toString()
    {
        return "Sono un Personaggio, della classe"+this.getClass()+"\n"+
                "Il mio nome è: "+super.getNomePersonaggio()+
                "I miei punti vita sono: "+super.getPuntiVitaPersonaggio()+
                "Il mio numero di punti scudo è: "+this.getPuntiScudo();
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
        if(!(obj instanceof Mago)) return false;
        if(!super.equals(obj)) return false; //Riga da aggiungere in un equals di una classe che ne estende un altra.
        Guerriero other = (Guerriero)obj;
        return this.getPuntiScudo()==(other.getPuntiScudo());
    }
}
