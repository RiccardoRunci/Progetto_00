package it.unicam.cs.mpgc.rpg120666.Model.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public abstract class Nemico extends Personaggio implements InterfacciaPerPersonaggi, ElementiPerMatrice
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
    /**Metodo che deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi. Lo metto abstract,
     * così da farlo implementare nelle sottoclassi di Nemico (come ho fatto per Personaggio).
     * Nemico è una classe astratta, non si istanzia.
     * Questo metodo tornerà utili per eliminare gli istance of nella classe ManagerCombattimentoEroeNemico*/
    @Override
    public abstract boolean eseguiAttacco(Personaggio e);
    /**Metodo final, significa che è scritto nella super classe
     * e le sotto classi lo prendono così come è senza che possono
     * modificarlo.
     * Mi fà molto comodo in MatriceFineGioco, dove io scansiono la
     * matrice in cerca di Nemici. Dato che le sottoclassi di Nemico
     * sono anche loro nemici, evito di dover scrivere (nella classe ManagerFineGioco)
     * @code if (elemento==MAGO || elemento==GUERRIERO || elemento==NUOVO_NEMICO_CHE_METTERO'_IN_FUTURO)*
     *          then numNemici++;
     * In questo senso mi rende il codice estendibile. Quando andrò a inserire un nuovo nemico nel gioco (farò ereditare
     * la classe Nemico e avrò in automatico questo metodo che mi ritorna "Nemico"!*/
    public final String veroVolto()
    {
        return "Nemico";
    }
}
