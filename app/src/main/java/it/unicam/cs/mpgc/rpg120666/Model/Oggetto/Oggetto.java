/**Classe astratta Oggetto:
 * Questa classe rappresenta un Oggetto generico, è astratta perché (come in classe avevamo fatto l'esercizio di
 * Persona in un sistema di studenti) ho preferito non aggiungere un Oggetto generico nella MatriceGioco, ma bensì degli
 * oggetti specifici(contenuti dentro Model.Oggetto.ClassiEstese(Arma & Medikit).
 * Questa classe verrà estesa dalle classi figli di Oggetto (esempio: Arma e Medikit)
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit
 * @autor Runci Riccardo.
 * @since version 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import java.io.Serializable;

public abstract class Oggetto implements Serializable, UsaOggetto
{
    private static final long serialVersionUID = 1L;
    private final String nomeOggetto;
    /**Metodo costruttore:
     * @param nomeOggetto (Stringa) = indica il nome dell'Oggetto.
     * @throws  IllegalArgumentException se il parametro non va bene alla classe Utility.VerificaParametri.
     * @see VerificaParametri Questa classe ha un metodo che prende una stringa e controlla:
     *                        se non è vuota, null e se non è formata da soli numeri.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    public Oggetto(String nomeOggetto)
    {
        if (VerificaParametri.verificaStringhe(nomeOggetto))
        {
            this.nomeOggetto = nomeOggetto;
        }
        else
            throw new IllegalArgumentException("Parametro nomeOggetto passato al costruttore di Oggetto invalido!");
    }
    /**Metodo getNomeOggetto:
     * @return (String) il nome dell'Oggetto.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    public String getNomeOggetto() { return this.nomeOggetto; }
    /**Metodo toString:
     *@return una descrizione dell'Oggetto.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public String toString()
    {
        return "CLASSE OGGETTO!\n"+
                "NOME: "+this.getNomeOggetto()+"\n";
    }
    /**Metodo equals:
     *@return (boolean) true, se i due oggetti confrontati sono uguali.
     *                  False, altrimenti.
     * @override il metodo equals di Object.
     * @since versione 1.0.
     * @autor Runci Riccardo.**/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Oggetto)) return false;
        Oggetto other = (Oggetto)obj;
        return this.getNomeOggetto().equals(other.getNomeOggetto());
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.
     *@override il metodo hashCode di Object.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getNomeOggetto());
    }
    /**Anche la super classe Oggetto implementa l'interfaccia UsaOggetto (che ha un solo metodo, UsaOggetto(eroe e).
     * Però l'ho messo abstract, così che ogni sottoclasse che estende questo Oggetto può dare la sua implementazione di questo metodo!
     * Così da permettere (ad esempio) alla sottoclasse Medikit di usare l'oggetto per ricaricare la vita!
     * Oppure alla sottoclasse Arma di aumentare la potenza di attacco dell'Eroe!
     * @since versione 1.0
     * @author Runci Riccardo.*/
    public abstract boolean usaOggetto(Eroe e);

}