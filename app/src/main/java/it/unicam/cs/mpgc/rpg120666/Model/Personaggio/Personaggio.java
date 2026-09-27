package it.unicam.cs.mpgc.rpg120666.Model.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.AGGIORNAMENTO;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public abstract class Personaggio implements InterfacciaPerPersonaggi
{
    private final String nomePersonaggio;
    private int puntiVitaPersonaggio;
    /**Metodo costruttore:*/
    public Personaggio(String nomePersonaggio, int puntiVitaPersonaggio)
    {
        /*Se la verifica torna true in entrambi i parametri passati allora va bene!*/
        if(VerificaParametri.verificaStringhe(nomePersonaggio) && VerificaParametri.verificaInteri(puntiVitaPersonaggio,CREAZIONE))
        {
            this.nomePersonaggio = nomePersonaggio;
            this.puntiVitaPersonaggio = puntiVitaPersonaggio;
        }
        else
            throw new IllegalArgumentException("Parametri passati al costruttore di Personaggio sono invalidi");
    }
    /*Metodi set e get:*/
    public void setPuntiVitaPersonaggio(int puntiVitaPersonaggio)
    {
        if(VerificaParametri.verificaInteri(puntiVitaPersonaggio,AGGIORNAMENTO))
            this.puntiVitaPersonaggio = puntiVitaPersonaggio;
        else
            throw new IllegalArgumentException("Parametro int passato a Personaggio.setPuntiVitaPersonaggio invalido");
    }
    public int getPuntiVitaPersonaggio(){ return this.puntiVitaPersonaggio; }
   //Il metodo 'void setNomePersonaggio(String ...)' non può essere fatto perché this.nomePersonaggio è final!
    public String getNomePersonaggio() { return this.nomePersonaggio; }
    @Override
    public String toString(){
        return "CLASSE: Personaggio!\n"+
                "NOME: "+this.nomePersonaggio+"\n"+
                "VITA: "+this.puntiVitaPersonaggio+"\n";
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.nomePersonaggio, this.puntiVitaPersonaggio);
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Personaggio)) return false;
        Personaggio other = (Personaggio)obj;
        return (this.nomePersonaggio.equals(other.nomePersonaggio)) &&
                (this.puntiVitaPersonaggio == other.puntiVitaPersonaggio);
    }
    /**Metodo che:
     * @return true se il personaggio è ancora vivo!
     * ritorna false se il personaggio è morto*/
    public boolean controllaSeVivo()
    {
        if(this.getPuntiVitaPersonaggio()>0) return true;
        else return false;
    }
    /**Metodo che deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi. Lo metto abstract,
     * così da farlo implementare nelle sottoclassi di Personaggio.
     * Personaggio è una classe astratta, non si istanzia.
     * Questo metodo tornerà utili per eliminare gli istance of nella classe ManagerCombattimentoEroeNemico*/
    @Override
    public abstract boolean eseguiAttacco(Personaggio e);
}
