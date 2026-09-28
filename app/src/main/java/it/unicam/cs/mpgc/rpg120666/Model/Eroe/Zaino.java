package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Zaino
{
    private final Eroe eroeProprietarioZaino;
    private Map<Oggetto,Integer> oggettiPosseduti;

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
    public Eroe getEroeProprietarioDelloZaino()
    {
        if(this.eroeProprietarioZaino==null)
            throw new NullPointerException("il campo Eroe della classe Zaino è  ancora == null");
        else
            return this.eroeProprietarioZaino;
    }
    // public void setEroeProprietarioDelloZaino(Eroe eroeProprietarioDelloZaino),
    // non si può fare perché  eroeProprietarioDelloZaino è final.
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

    public boolean controllaPresenzaOggetto(Oggetto o)
    {
        return (this.oggettiPosseduti.containsKey(o));
    }
    /**Si ma hashmap non ammette duplicati!*/
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
    @Override
    public String toString()
    {
        return "ELEMENTI DELLO ZAINO: \n"+this.elencaOggettiDelloZainoEroe()+"\n";
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
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(!(obj instanceof Zaino)) return false;
        Zaino other = (Zaino)obj;
        return this.getEroeProprietarioDelloZaino().equals(other.getEroeProprietarioDelloZaino()) &&
                (this.getOggettiPosseduti().equals(other.getOggettiPosseduti()));
    }
    /**Metodo hashCode:
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.*/
    @Override
    public int hashCode(){
        return java.util.Objects.hash(this.getOggettiPosseduti(),this.getEroeProprietarioDelloZaino());
    }
}
