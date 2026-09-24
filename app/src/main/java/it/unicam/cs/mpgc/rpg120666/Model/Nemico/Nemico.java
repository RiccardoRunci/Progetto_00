package it.unicam.cs.mpgc.rpg120666.Model.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public abstract class Nemico extends Personaggio
{
    private final int dannoCausato;
    /** Metodo costruttore:
     * @param nomePersonaggio
     * @param puntiVitaPersonaggio*/
    public Nemico(String nomePersonaggio, int puntiVitaPersonaggio, int dannoCausato)
    {
        if((!VerificaParametri.verificaStringhe(nomePersonaggio))
                || (!VerificaParametri.verificaInteri(puntiVitaPersonaggio,CREAZIONE))
                || (!(VerificaParametri.verificaInteri(dannoCausato,CREAZIONE))))
            throw new IllegalArgumentException("");
        super(nomePersonaggio,puntiVitaPersonaggio);
        this.dannoCausato = dannoCausato;
    }
    public int getDannoCausato() {return this.dannoCausato;}
    @Override
    public String toString(){
        return "CLASSE NEMICO!\n"+
                "NOME: "+super.getNomePersonaggio()+"\n"+
                "VITA:"+super.getPuntiVitaPersonaggio()+"\n"+
                "DANNO CAUSATO: "+this.dannoCausato+"\n";
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.dannoCausato);
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Nemico)) return false;
            Nemico other = (Nemico)obj;
        return this.getDannoCausato()==(other.getDannoCausato());
    }
}
