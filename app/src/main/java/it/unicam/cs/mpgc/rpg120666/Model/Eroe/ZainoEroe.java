
package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
public class ZainoEroe
{
    private Eroe eroeProprietarioDelloZaino;
    //Oggetto è la chiave, Integer = quantità dell'oggetto!
    private Map<Oggetto,Integer> oggettiPosseduti;
    public ZainoEroe(){}
    public ZainoEroe(Eroe eroeProprietarioDelloZaino)
    {
        if(eroeProprietarioDelloZaino==null)
            throw new NullPointerException("eroeProprietarioDelloZaino == null");
        this.eroeProprietarioDelloZaino=eroeProprietarioDelloZaino;
        this.oggettiPosseduti = new HashMap<>();
    }
    //Metodi Set e get
    public Map<Oggetto,Integer> getOggettiPosseduti()
    {
        if(this.oggettiPosseduti==null)
            throw new NullPointerException("il campo Map<Oggetti,Integer> della classe Zaino è  ancora == null");
        else return this.oggettiPosseduti;
    }
    public void setOggettiPosseduti(Map<Oggetto, Integer> oggetti)
    {
        if (oggetti != null)
        {
            this.oggettiPosseduti = new HashMap<>(oggetti); // copia difensiva
        }
        else
            throw new NullPointerException("La Map passata al metodo set come parametro è == null!");
    }
    public void setEroeProprietarioDelloZaino(Eroe eroeProprietarioDelloZaino)
    {
        if (eroeProprietarioDelloZaino==null)
        {
            throw new NullPointerException("Eroe passato come parametro al metodo set è null");
        }
        else
            this.eroeProprietarioDelloZaino=eroeProprietarioDelloZaino;
    }
    public Eroe getEroeProprietarioDelloZaino()
    {
        if(this.eroeProprietarioDelloZaino==null)
            throw new NullPointerException("il campo Eroe della classe Zaino è  ancora == null");
        else
            return this.eroeProprietarioDelloZaino;
    }
    public boolean aggiungiOggettoNelloZaino(Oggetto oggetto)
    {
        if(oggetto!=null) //Se l'oggetto è != null
        {
            if(this.oggettiPosseduti.containsKey(oggetto)) //se ho già l'oggetto
            {   //.get(oggetto) restituisce il valore associato a quella chiave "oggetto".
                int quantita = this.oggettiPosseduti.get(oggetto);   // valore = quantità attuale
                this.oggettiPosseduti.put(oggetto, quantita + 1);    // nuovo valore = quantità + 1
                return true;
            }
            else //se non ho l'oggetto nella hashmap:
            {
                this.oggettiPosseduti.put(oggetto, 1); //lo aggiungo con la quantità=1.
                return true;
            }
        }
        else return false;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof ZainoEroe)) return false;
        ZainoEroe other = (ZainoEroe)obj;
        return this.getEroeProprietarioDelloZaino().equals(other.getEroeProprietarioDelloZaino()) &&
                (this.getOggettiPosseduti().equals(other.getOggettiPosseduti()));
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getOggettiPosseduti(),this.getEroeProprietarioDelloZaino());
    }
    @Override
    public String toString()
    {
        return "OGGETTO ZAINO\n"+
                "OGGETTI CONTENUTI:\n"+this.elencaOggettiDelloZainoEroe();
    }
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

}
