package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.AGGIORNAMENTO;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Eroe extends Personaggio implements ElementiPerMatrice
{
    private final Zaino zainoEroe;
    private int dannoCausato = 10;
    private CellaMatrice cellaPosizioneEroe;

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
    public void setCellaPosizioneEroe(CellaMatrice cellaPosizioneEroe)
    {
        if(cellaPosizioneEroe!=null) //todo ClasseMatriceAstratta.controllaPosizioniOutOfBound(cellaPosizioneEroe)
            this.cellaPosizioneEroe = cellaPosizioneEroe;
        else
            throw new NullPointerException("Cella passata alla classe Eroe nel metodo set == null");
    }
    public boolean prendiOggetto(Oggetto oggetto)
    {
        if (oggetto==null || (!this.controllaSeVivo()))
            throw new IllegalArgumentException("Parametro oggetto passato al metodo prendiOggetto della classe Eroe invalido" +
                    "Oppure eroe morto, non può più fare nulla!");
        else
            return (this.zainoEroe.aggiungiOggettoNelloZaino(oggetto));
    }
    public boolean usaOggetto(Oggetto oggetto)
    {
        /*Controllo se l'oggetto passato è = null:*/
        if(oggetto==null || (!(this.controllaSeVivo())))
            throw new IllegalArgumentException("Oggetto passato al metodo usaOggetto di Eroe = null!\nOppure Eroe Morto!");
        /*Controllo se l'oggetto passato è realmente presente nella collezione di zaino:*/
        if(this.zainoEroe.controllaPresenzaOggetto(oggetto))
        {
            return oggetto.usaOggetto(this);
        }
        else
        {
            //todo elimina i printf quando stai a cavallo!
            System.out.println("l'oggetto non presente nello zaino!\n Oppure eroe morto, non potere fare + nulla!");
            return false;
        }
    }
    @Override
    public String toString()
    { return "CLASSE EROE!\n"+
            "NOME= "+super.getNomePersonaggio()+"\n"+
            "VITA= "+super.getPuntiVitaPersonaggio()+"\n"+
            "+ZAINO: "+this.zainoEroe.toString()+"\n";
            //"CELLA: "+this.cellaPosizioneEroe.toString();
    }
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.zainoEroe,this.cellaPosizioneEroe);
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Eroe)) return false;
        if(!super.equals(o)) return false; //riga in più se hai una classe che si estende da una super.
        Eroe eroe = (Eroe) o;
        return this.zainoEroe.equals(eroe.zainoEroe) &&
                (this.cellaPosizioneEroe.equals(eroe.cellaPosizioneEroe));
    }

    /**Metodo dell'interfaccia ElementiPerMatrice.
     * Qui ho dovuto ereditare il metodo su una classe che non lo doveva ereditare.
     * Allora ho optato per un toString(). Una toppa.*/
    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        e.toString();
        return true;
    }
}
