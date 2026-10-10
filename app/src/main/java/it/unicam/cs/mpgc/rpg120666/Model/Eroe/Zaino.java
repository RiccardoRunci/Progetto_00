/**Classe Zaino:
 * Questa classe è una sorta di sosia della classe Libretto (dello studente) vista a lezione.
 *
 * Rappresenta lo zaino che si porta appresso l'eroe.
 * Ho usato una struttura dati hashMap<chiave=Oggetto, valore= quantità>.
 *
 * L'oggetto appena viene calpestato dall'Eroe va a finire nel suo zaino.
 * Poi (solo in seguito) viene usato.
 *
 * @since versione 1.0.
 * @author Runci Riccardo.*/
package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Zaino implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final Eroe eroeProprietarioZaino;
    private Map<Oggetto,Integer> oggettiPosseduti;
    /**Metodo costruttore:
     * @param eroeProprietarioZaino l'eroe che possiede lo zaino!
     * @throws IllegalArgumentException se l'eroe passato è = null.
     * Questo metodo crea anche la struttura dati dello zaino!
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public Zaino(Eroe eroeProprietarioZaino)
    {
        if (eroeProprietarioZaino!=null)
        {
            this.eroeProprietarioZaino = eroeProprietarioZaino;
            this.oggettiPosseduti = new HashMap<>();
        }
        else
            throw new IllegalArgumentException("Eroe passato al costruttore di Zaino non valido!");
    }
    /**Metodo getOggettiPosseduti:
     * @return oggettiPosseduti il riferimento alla hashmap dello zaino.
     * @throws IllegalArgumentException lo la Map non è stata creta con HashMap.
     * Metodo che ritorna tutto lo zaino (zaino inteso come zaino, non oggetto).
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public Map<Oggetto,Integer> getOggettiPosseduti()
    {
        if(this.oggettiPosseduti==null)
            throw new IllegalArgumentException("il campo Map<Oggetti,Integer> della classe Zaino è  ancora == null");
        else return this.oggettiPosseduti;
    }
    /**Metodo setOggettiPosseduti:
     * @param oggetti gli oggetti che compongono lo zaino!
     * @throws IllegalArgumentException se lo zaino passato è = null.
     * Metodo che permette il passaggio come parametro di una Map zaino e la implementa con una hashMap
     * Per farla diventare lo zaino dell'eroe (utile in caso di de-serializzazione)!
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public void setOggettiPosseduti(Map<Oggetto, Integer> oggetti)
    {
        if (oggetti != null)
        {
            this.oggettiPosseduti = new HashMap<>(oggetti); // copia difensiva
        }
        else
            throw new IllegalArgumentException("La Map passata al metodo set come parametro è == null!");
    }
    /**Metodo getEroeProprietarioDelloZaino:
     * @throws IllegalArgumentException se l'eroe proprietario dello zaino è null).
     * @return eroeProprietarioZaino!
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public Eroe getEroeProprietarioDelloZaino()
    {
        if(this.eroeProprietarioZaino==null)
            throw new IllegalArgumentException("il campo Eroe della classe Zaino è  ancora == null");
        else
            return this.eroeProprietarioZaino;
    }
    // public void setEroeProprietarioDelloZaino(Eroe eroeProprietarioDelloZaino),
    // non si può fare perché  eroeProprietarioDelloZaino è final.
    /**Metodo aggiungiOggettoNelloZaino:
     * @return true se l'oggetto è stato inserito correttamente nello zaino, false altrimenti!
     * @param oggetto è l'oggetto da inserire nell' hashmap dello zaino.
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public boolean aggiungiOggettoNelloZaino(Oggetto oggetto)
    {
        if (oggetto != null || (!eroeProprietarioZaino.controllaSeVivo()))//Se l'oggetto è != null
        {
            if (this.oggettiPosseduti.containsKey(oggetto)) //se ho già l'oggetto
            {   //.get(oggetto) restituisce il valore associato a quella chiave "oggetto".
                int quantita = this.oggettiPosseduti.get(oggetto);   // valore = quantità attuale
                this.oggettiPosseduti.put(oggetto, quantita + 1);    // nuovo valore = quantità + 1
                return true;
            } else //se non ho l'oggetto nella hashmap:
            {
                this.oggettiPosseduti.put(oggetto, 1); //lo aggiungo con la quantità=1.
                return true;
            }
        }
        else return false;
    }
    /**Metodo controllaPresenzaOggetto:
     * @return true se l'oggetto è presente nello zaino!
     * @param o è l'oggetto da cercare all'interno dello zaino.
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public boolean controllaPresenzaOggetto(Oggetto o)
    {
        return (this.oggettiPosseduti.containsKey(o));
    }
    /**Si ma hashmap non ammette duplicati!*/
    /**Metodo eliminaOggettoDalloZaino:
     * @return true se l'oggetto è stati eliminato dallo zaino, false altrimeti!
     * @param o è l'oggetto da eliminare dall'interno dello zaino.
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    public boolean eliminaOggettoDalloZaino(Oggetto o)
    {
       //Il metodo containsKey(chiave) ritorna true se l'oggetto ∃ dentro HashMap:
        if (this.oggettiPosseduti.containsKey(o))
        {
            int quantita;
            //Il metodo HashMap.get(chiave), ritorna il valore (della hashmap <Chiave,Valore>).
            quantita = this.oggettiPosseduti.get(o);
            if(quantita==1) //se quantità è = 1, elimino l'oggetto con:
                this.oggettiPosseduti.remove(o);
            else //altrimenti con questo metodo scalo la sua quantità!
                this.oggettiPosseduti.put(o, quantita-1);
            return true;
        }
        else
        {
            //todo elimina i printf quando tutto funziona!
            System.out.println("PER QUALCHE STRANO MOTIVO, L'oggetto non era presente nello zaino!");
            return false; //per qualche strano motivo ha ritornato false!
        }
    }
    /**Metodo toString:
     * @return Una stringa contenente la descrizione di tutti gli elementi contenuti nell'hashmap zaino
     *          Perchè richiama anche il metodo sotto!
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    @Override
    public String toString()
    {
        return "ELEMENTI DELLO ZAINO: \n"+this.elencaOggettiDelloZainoEroe()+"\n";
    }
    /**Metodo elencaOggettiDelloZainoEroe:
     * @return Una stringa contenente la descrizione di tutti gli elementi contenuti nell'hashmap zaino.
     *         e la passa al metodo sopra toString.
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    private String elencaOggettiDelloZainoEroe()
    {
        StringBuilder s=new StringBuilder();
        Iterator<Map.Entry<Oggetto, Integer>> iteratoreMappa = oggettiPosseduti.entrySet().iterator();
        if(this.oggettiPosseduti.isEmpty())
            return "ZAINO VUOTO!";
        while (iteratoreMappa.hasNext())
        {
            Map.Entry<Oggetto, Integer> entry = iteratoreMappa.next();

            Oggetto chiave = entry.getKey();      // la chiave
            Integer valore = entry.getValue();    // il valore
            s.append("OGGETTO= "+chiave + " -> " +" QUANTITA'= "+ valore+"\n");
        }
        return s.toString();
    }
    /**Metodo equals:
     * @return true se 2 istanze di Zaino sono uguali, false altrimenti
     * @param obj è lo zaino passato per fare il controllo con l'istanza di questa classe.
     * @since versione 1.0.
     * @author Runci Riccardo.*/
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Zaino)) return false;
        Zaino other = (Zaino)obj;
        return this.getEroeProprietarioDelloZaino().equals(other.getEroeProprietarioDelloZaino()) &&
                (this.getOggettiPosseduti().equals(other.getOggettiPosseduti()));
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.
     *@since versione 1.0.
     *@author Runci Riccardo.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getOggettiPosseduti(),this.getEroeProprietarioDelloZaino());
    }
}
