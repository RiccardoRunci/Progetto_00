package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Eroe implements ElementiPerMatrice
{
    private String nomeEroe;
    private int puntiVitaEroe;
    private CellaMatrice cellaPosizioneEroe;
    private ZainoEroe oggettiEroe;
    private int dannoCausato=10;

    public Eroe(){}
    public Eroe(String nomeEroe, int puntiVitaEroe,CellaMatrice cellaPosizioneEroe)
    {
        if((ClasseUtility.controllaStringa(nomeEroe,"EROE")) &&
                (ClasseUtility.verificaNumeriInteri(puntiVitaEroe, EnumControlloCreazioneAggiornamento.CREAZIONE,"EROE")))
                {
                    this.puntiVitaEroe=puntiVitaEroe;
                    this.nomeEroe = nomeEroe;
                    this.cellaPosizioneEroe=cellaPosizioneEroe;
                    this.oggettiEroe=new ZainoEroe(); //CREO LO ZAINO!
                }
    }
    public String getNomeEroe(){ return this.nomeEroe; }
    public int getPuntiVitaEroe(){ return this.puntiVitaEroe; }
    public ZainoEroe getOggettiEroe() { return this.oggettiEroe; }
    public CellaMatrice getCellaPosizioneEroe() { return this.cellaPosizioneEroe; }
    public int getDannoCausato() { return this.dannoCausato; }
    public void setDannoCausato(int dannoCausato)
    {
        if(ClasseUtility.verificaNumeriInteri(dannoCausato,EnumControlloCreazioneAggiornamento.AGGIORNAMENTO,"EROE"))
            this.dannoCausato=dannoCausato;
        else
            throw new IllegalArgumentException("parametro: "+dannoCausato+" invalido (classe Eroe,metodo set).");
    }
    public void setNomeEroe(String nomeEroe)
    {
        if((ClasseUtility.controllaStringa(nomeEroe,"EROE")))
            this.nomeEroe=nomeEroe;
        else
            throw new IllegalArgumentException("Nome dell'eroe passato nel metodo set (della classe Eroe) sbagliato: "+nomeEroe);
    }
    public void setPuntiVitaEroe(int punti)
    {
        if((ClasseUtility.verificaNumeriInteri(punti,EnumControlloCreazioneAggiornamento.AGGIORNAMENTO,"EROE"))) //se questo torna true, è un numero!
            this.puntiVitaEroe=punti;
        else
            throw new IllegalArgumentException("punti dell'eroe passato nel metodo set (della classe Eroe) sbagliato: "+punti);
    }

    public void setCellaPosizioneEroe(CellaMatrice cellaPosizioneEroe)
    {
        if(cellaPosizioneEroe!=null) //todo ClasseMatriceAstratta.controllaPosizioniOutOfBound(cellaPosizioneEroe)
            this.cellaPosizioneEroe = cellaPosizioneEroe;
        else
            throw new NullPointerException("Cella passata alla classe Eroe nel metodo set == null");
    }

    public void setOggettiEroe(ZainoEroe oggettiEroe)
    {
        if (oggettiEroe == null) { throw new IllegalArgumentException("Lo zaino dell'eroe passato al metodo set della classe Eroe == null"); }
        this.oggettiEroe = oggettiEroe;
    }

    @Override
    public String toString(){
        return "CLASSE EROE!\n"+
                "NOME: "+this.getNomeEroe()+"\n"+
                "VITA: "+this.getPuntiVitaEroe()+"\n"+
                "ZAINO: "+this.oggettiEroe.toString()+"\n"+
                "POSIZIONE: "+this.cellaPosizioneEroe.toString()+"\n";
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getNomeEroe(), this.getPuntiVitaEroe(),this.getOggettiEroe(),this.getCellaPosizioneEroe());
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Eroe)) return false;
        Eroe other = (Eroe)obj;
        return (this.getNomeEroe().equals(other.getNomeEroe())) &&
                (this.getPuntiVitaEroe() == other.getPuntiVitaEroe()) &&
                (this.getOggettiEroe().equals(other.getOggettiEroe())) &&
                (this.getCellaPosizioneEroe().equals(other.getCellaPosizioneEroe()));
    }
    public boolean attaccaFisicamente(Nemico nemico)
    {
        if(nemico!=null)
        {
            int vitaNemico=nemico.getPuntiVitaNemico()-this.getDannoCausato();
            nemico.setPuntiVitaNemico(vitaNemico);
            return true;
        }
        else
            throw new NullPointerException("Nemico passato come parametro al metodo attacca di eroe nullo");
    }
    public boolean prendiOggetto(Oggetto oggetto)
    {
        //"TUTTI" i controlli stanno dentro il metodo ZAINO.aggiungiOggettoNelloZaino.
        return (this.oggettiEroe.aggiungiOggettoNelloZaino(oggetto));
        //true se l'eroe ha preso l'oggetto, false se non l'ha preso!
    }
    /**Questo metodo fa la stessa cosa del metodo getCellaPosizioneEroe!
     * Lo lascio perché almeno posso usare l'annotazione @Deprecated!*/
    @Deprecated
    public CellaMatrice posizioneCellaEroe()
    {
        return this.getCellaPosizioneEroe();
    }
    public boolean controllaSeMorto()
    { //se è true vuol dire che è morto!
        return (this.getPuntiVitaEroe()<=0);
    }
}
