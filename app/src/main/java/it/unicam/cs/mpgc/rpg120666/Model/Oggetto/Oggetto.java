package it.unicam.cs.mpgc.rpg120666.Model.Oggetto;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

public abstract class Oggetto implements UsaOggetto
{
    private final String nomeOggetto;

    public Oggetto(String nomeOggetto)
    {
        if (VerificaParametri.verificaStringhe(nomeOggetto))
        {
            this.nomeOggetto = nomeOggetto;
        }
        else
            throw new IllegalArgumentException("Parametro nomeOggetto passato al costruttore di Oggetto invalido!");
    }
    /**Metodo getNomeOggetto.
     * @return (String) il nome dell'Oggetto.*/
    public String getNomeOggetto() { return this.nomeOggetto; }
    /**Metodo toString:
     *@return una descrizione dell'Oggetto.*/
    @Override
    public String toString()
    {
        return "CLASSE OGGETTO!\n"+
                "NOME: "+this.getNomeOggetto()+"\n";
    }
    /**Metodo equals:
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     * False, altrimenti.*/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Oggetto)) return false;
        Oggetto other = (Oggetto)obj;
        return this.getNomeOggetto().equals(other.getNomeOggetto());
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getNomeOggetto());
    }
    /**Anche la super classe Oggetto implementa l'interfaccia UsaOggetto (che ha un solo metodo, UsaOggetto(eroe e).
     * Però l'ho messo abstract, così che ogni sottoclasse che estende questo Oggetto può dare la sua implementazione di questo metodo!
     * Così da permettere (ad esempio) alla sottoclasse Medikit di usare l'oggetto per ricaricare la vita!
     * Oppure alla sottoclasse Arma di aumentare la potenza di attacco dell'Eroe!*/
    public abstract boolean usaOggetto(Eroe e);
}