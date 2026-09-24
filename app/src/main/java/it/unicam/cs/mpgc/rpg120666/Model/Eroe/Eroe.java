package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.AGGIORNAMENTO;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Eroe extends Personaggio
{
    private final Zaino zainoEroe;
    private int dannoCausato = 10;

    public Eroe(String nomePersonaggio, int puntiVitaPersonaggio)
    {
        if(!VerificaParametri.verificaStringhe(nomePersonaggio) || !VerificaParametri.verificaInteri(puntiVitaPersonaggio,CREAZIONE))
            throw new IllegalArgumentException("Nessuna parametri valido");
        //altrimenti se la verifica dei parametri è andata bene:
        super(nomePersonaggio, puntiVitaPersonaggio);
        this.zainoEroe=new Zaino(this); //creo lo zaino passando il riferimento all'oggetto Eroe padrone dello zaino.
    }
    public Zaino getZaino(){ return this.zainoEroe; }
    public int getDannoCausato() { return this.dannoCausato; }
    public void setDannoCausato(int dannoCausato)
    {
        if(VerificaParametri.verificaInteri(dannoCausato,AGGIORNAMENTO))
            this.dannoCausato=dannoCausato;
        else
            throw new IllegalArgumentException("Parametro dannoCausato al metodo setDannoCausato di Eroe invalido!");
    }
    public boolean prendiOggetto(Oggetto oggetto)
    {
        if (oggetto==null)
            throw new IllegalArgumentException("Parametro oggetto passato al metodo prendiOggetto della classe Eroe invalido");
        return (this.zainoEroe.aggiungiOggettoNelloZaino(oggetto));
    }
    @Override
    public String toString()
    { return "CLASSE EROE!\n"+
            "NOME= "+super.getNomePersonaggio()+"\n"+
            "VITA= "+super.getPuntiVitaPersonaggio()+"\n"+
            "+ZAINO: "+this.zainoEroe.toString()+"\n";
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.zainoEroe);
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Eroe)) return false;
        if(!super.equals(o)) return false; //riga in più se hai una classe che si estende da una super.
        Eroe eroe = (Eroe) o;
        return this.zainoEroe.equals(eroe.zainoEroe);
    }


}
