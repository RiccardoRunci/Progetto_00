/**Classe LeggiJsonGuerrieri:
 * Questa classe mi serve per leggere una serie di Guerrieri (Nemici) salvati in un file .json
 * contenuto nella cartella speciale "resource" di gradle.
 *
 * La cartella dei file statici di gradle è:
 * Quella dove tu devi copiare il file dentro: 'Cartella_Radice/app/src/main/resources'!,
 * Poi fai 'gradle build' e Trovi una copia del tuo file dentro: 'Cartella_Radice/app/build/resourcers'!
 *
 * In poche parole vorrei De-serializzare un json (che contiene una lista di Guerrieri) già salvato,
 * per avere una lista di Nomi, punti vita e punti danno; Da passare alla classe FactoryNemico.
 *
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
 * @author Runci Riccardo
 * @since versione 2.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza;
import com.google.gson.Gson; //import per usare Gson.
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Guerriero;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class LeggiJsonGuerrieri
{

    private final Set<Guerriero> insiemeGuerrieri = new HashSet<>();

    /**Metodo costruttore:
     * Costruisce l'oggetto della classe, ma costruendolo:
     * 1)Mette il contenuto del file Guerrieri.json dentro un vettore di Guerrieri (Guerrieri[]);
     * 2)Dato che nel vettore sopra possono esserci dei doppioni di eroi, lo trasformo in un Set->HashSet!
     * 3)Una volta finito il costruttore, posso prendere il campo setGuerrieri tramite un metodo get classico.
     * @implNote Da notare che una volta preso il campo, avrò un Set! quindi non avrò una collection indicizzata!
     *           Nella classe dove andrò a pescare questo set (molto probabilmente in FactoryNemici)..
     *           @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
     *           Dovrò fare un nuovo ArrayList e inserirci tutto questo set tramite addAll(questo set).
     *           Così potrò avere un Array indicizzato!
     *@author Runci Riccardo.
     *@since Versione 2.0.*/
    public LeggiJsonGuerrieri()
    {
        /*Metto nel campo "Guerriero[] elencoGuerrieri" tutto il contenuto del JSON.
        * Tramite l'utilizzo del metodi di questa classe "leggiTuttoJsonGuerrieri":*/
        Guerriero[] arrayGue = this.leggiTuttoJsonGuerrieri();

        /*Stampo quello che sono riuscito a tirare fuori e mettere nel campo "Guerriero[] elencoGuerrieri". */
        //this.qualiJsonHaiLetto(arrayGue);

        /*Metto il contenuto dell'Array Guerriero[] dentro un set, così
        * cancello gli eventuali duplicati. Utilizzando il metodo "riempiSetGuerrieri(Guerriero[])"
        * di questa classe:*/
        this.riempiSetGuerrieri(arrayGue);

        /*Stampo il contenuto del set, per verificare se è tutto ok!*/
        //this.stampaSetGuerrieri();
    }
    /**Metodo leggiTuttoJsonGuerrieri:
     * @return elencoGuerrieri Che è un vettore di Guerrieri[].
     *         Può contenere doppioni di guerrieri (se sono presenti nel JSON).
     *
     * Questo metodo utilizza l'oggetto gs che è della Classe GSON (libreria google per leggere/scrivere Json).
     * PRIMA Lo utilizzavo tramite stream di caratteri (+ relativo buffer),
     *       utilizzando un altro "modo" di blocco try/catch diverso da quello che
     *       ho negli appunti. Ho utilizzato il blocco try/catch "normale/solito".
     *       Il blocco try/catch catturava l'eccezione IOException!
     * DOPO che ho messo il file json dentro la cartella resource di gradle,
     *      ho dovuto cambiare lo stream di caratteri con lo stream di byte!
     *      Perchè quella cartella vuole stream di byte.
     *
     *      Devo cambiare anche l'eccezione catturata dal blocco try/catch.
     *      In realtà non lancia nessuna eccezione, ma se voglio fargli catturare un eccezione devo
     *      fargli dire che se is==null (non ha funzionato la presa del file dentro la cartella resources)
     *      allora lancio una FileNotFoundException!
     *      Se no potevo direttamente levare il try/catch!
     *
     * Questo metodo mi permetti di caricare e restituire (agli altri metodi di questa classe) il vettore elencoGuerrieri.
     * @throws FileNotFoundException se il file json non viene trovato o ha altri problemi correlati.
     * @author Runci Riccardo
     * @since Versione 2.0.*/
    private Guerriero[] leggiTuttoJsonGuerrieri()
    {
        Guerriero[] elencoGuerrieri = new Guerriero[0];
        try
        {
            /*Dopo che ho inserito il file json nella cartella Resource di gradle ho dovuto cambiare lo stream di caratteri
             * e il suo relativo buffer:
             *  FileReader fr = new FileReader("./Guerrieri.json");
             *  BufferedReader br = new BufferedReader(fr);
             * Con gli InputStreamReader (ossia stream di byte) Perchè la cartella resource usa i stream di byte!*/

            InputStream is;
            is=getClass().getClassLoader().getResourceAsStream("Guerrieri.json");
            if(is==null)
            {
                throw new FileNotFoundException("Guerrieri.json non trovato nel classpath");
            }

            InputStreamReader isr;
            isr=new InputStreamReader(is);

            Gson gs = new Gson();

            /* Il metodo fromJson ritorna il tipo di oggetti
             * che hai passato nelle parentesi, in questo caso
             * ritorna Guerriero.class!
             *  gs.fromJson(br, Guerriero.class);
            * Però, tu hai un json che contiene array di guerrieri:
            *   [ {gue1}, {gue2}, {gue3} ]
            * Quindi devi prendere un array di Guerrieri!
            * Quindi devi fare:
            * ... =gs.fromJson(br, Guerriero[].class); */

            //elencoGuerrieri=gs.fromJson(br, Guerriero[].class); Vecchia versione con gli stream di caratteri!
            elencoGuerrieri=gs.fromJson(isr, Guerriero[].class); //Nuova versione con gli stream di byte (per via della cartella resource)!
        }
        catch (FileNotFoundException e){ e.printStackTrace();}
        return elencoGuerrieri;
    }
    /**Metodo qualiJsonHaiLetto:
     * Questo metodo mi serviva solo per vedere se il vettore Guerriero[] elencoGuerrieri veniva
     * carica correttamente. Ecco perché l'ho deprecato!
     * @author Runci Riccardo
     * @since Versione 2.0.*/
    @Deprecated
    private void qualiJsonHaiLetto(Guerriero[] elencoGuerrieri)
    {
        System.out.println("ELENCO LETTO:\n");
        for (Guerriero g : elencoGuerrieri)
        {
            System.out.println(g.toString());
            System.out.println("Prossimo:\n");
        }
    }
    /**Metodo che mi serve per riempire il Set<Guerrieri>!
     * @param elencoGuerrieri che è il Guerriero[] che riempo dal JSON.
     * Questo metodo mi riempe il set con gli elementi del vettore, in modo tale che filtro i doppioni!
     * @author Runci Riccardo
     * @since Versione 2.0*/
    private void riempiSetGuerrieri(Guerriero[] elencoGuerrieri)
    {
        for(Guerriero g : elencoGuerrieri)
        {
            this.insiemeGuerrieri.add(g);
        }
    }
    /**Metodo che mi serve per controllare se il set è stato
     * riempito per bene. Con questo metodo stampo il suo contenuto!
     * E' un metodo deprecato perchè volevo vedere solo se funzionava!
     * @author Runci Riccardo
     * @since Versione 2.0*/
    @Deprecated
    private void stampaSetGuerrieri()
    {
        for (Guerriero g:this.insiemeGuerrieri)
        {
            System.out.println(g.toString());
        }
    }

    /**Questo è il metodo che mi permette di recuperare il Set di Guerrieri che dovrebbe essere caricato
     * alla creazione dell'oggetto, perché ho richiamato tutti i metodi che mi servivano nel costruttore di questa
     * classe!
     * @implNote ritorna un Set, quindi ritorna una collection non indicizzata, dovrò trasformarlo in un ArrayList
     * (utilizzando addAll) su FactoryNemici!
     * @return this.insiemeGuerrieri!
     * @author Runci Riccardo.
     * @since versione 2.0.*/
    public Set<Guerriero> getInsiemeGuerrieri()
    {
        return this.insiemeGuerrieri;
    }
}
