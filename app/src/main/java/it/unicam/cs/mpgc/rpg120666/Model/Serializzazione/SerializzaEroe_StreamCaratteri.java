/**Classe SerializzaEroe:
 * Classe che serve per serializzare l'eroe, tramite Stream di caratteri.
 * Fatta solo per a di ripasso della serializzazione (e magari di completezza per mostrare altri argomenti
 * svolti a lezione).
 * In questa classe utilizzo la serializzazione solo tramite Streaming di CARATTERI (non byte).
 * L'eroe viene passato al costruttore.
 * @since Versione 2.0.
 * @author Runci Riccardo.*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SerializzaEroe_StreamCaratteri
{
    /*La serializzazione tramite stream di caratteri è di solito più onerosa (in termini di ...) rispetto
     * alla serializzazione di stream buffer di caratteri!
     * Per far vedere questo rallentamento inserisco anche il campo per  tempo_start:*/
    private double tempoStartMilliSec;

    /*Dato che è una classe di esempio, non la uso nel programma ma faccio dei test per vedere se
    * effettivamente il tempoImpiegatoSenzaBuffer > tempoImpiegatoConBuffer
    * Questo vale solo se la quantità di informazioni da serializzare super una certa quantità!
    * Se ho tantissimi elementi da serializzare allora i buffer sono più veloci (degli stream senza buffer)!*/
    private double tempoEndMilliSec; //per i test! Default = 0 (double tipo base)!

    /*Mi "copio" il riferimento a Eroe:*/
    private final Eroe e;

    /**Metodo costruttore:
     * Costruisce l'oggetto!
     * @param e = l'eroe passato da Serializzare!
     * @throws IllegalArgumentException Se l'eroe passato è null! Oppure se il suo nome è una stringa vuota
     * @since Versione 2.0.
     * @author Runci Riccardo.*/
    public SerializzaEroe_StreamCaratteri(Eroe e)
    {
        if (e == null || e.getNomePersonaggio().isEmpty())
            throw new IllegalArgumentException("Eroe passato al costruttore di SerializzaEroeStreamCaratteri null \n" +
                    "Oppure il suo nome è una stringa vuota!\n");
        else
        {
            this.e = e;
        }
    }

    /**Metodo serializzaEroe_NoBuffer:
     * Serve per serializzare l'eroe tramite stream di caratteri (senza usare i buffer):
     * @note Catturo l'eccezione dentro questo metodo tramite il try/catch (senza rimandarla
     *       al metodo chiamante tramite "throws IOException" nella firma del metodo)
     *       In modo da avere tutto il metodo qui.
     * @since Versione 2.0.
     * @author Runci Riccardo.*/
    public void serializzaEroe_NoBuffer()
    {
        this.tempoStartMilliSec = System.nanoTime(); //inizializzo il tempo!
        double tempoFinedMilliSec, tempoTotalMilliSec;
        try
        {
            FileWriter fw; //Il FileWriter (serializzazione caratteri NO BUFFER):
            fw = new FileWriter("./eroeSerializzatoStreamCaratteriNoBuffer.txt");

            StringBuilder informazioniDaSerializzare = new StringBuilder();
            informazioniDaSerializzare.append("NOME EROE: " + this.e.getNomePersonaggio()+"\n");
            informazioniDaSerializzare.append("VITA EROE: " + this.e.getPuntiVitaPersonaggio()+"\n");
            informazioniDaSerializzare.append("DANNO CHE CAUSA EROE: " + this.e.getDannoCausato()+"\n");
            informazioniDaSerializzare.append("OGGETTI DELLO ZAINO EROE: " + this.e.getZaino().toString()+"\n");
            informazioniDaSerializzare.append("-----------------------------------------------------\n");

            //serializzo nel file lo StringBuilder ("trasformato" in String):
            fw.write(informazioniDaSerializzare.toString());
            //Serializzo una linea spaziatrice:
            fw.write("\n");

            tempoFinedMilliSec = System.nanoTime();
            tempoTotalMilliSec = (tempoFinedMilliSec - this.tempoStartMilliSec) /  1_000_000.0;
            /*System.out.println("Per serializzare L'eroe tramite l'utilizzo di stream di caratteri (SENZA BUFFER)," +
                "ho impiegato: "+tempoTotalMilliSec+" nanosecondi\n");*/

            this.setTempoFinale(tempoTotalMilliSec); //aggiorno il campo di questa classe TempoFine!
            //serializzo l'informazione sulla quantità di tempo speso anche nel file:
            fw.write("TEMPO SPESO PER SERIALIZZARE L'EROE\nCON STREAM DI CARATTERI, SENZA BUFFER:\n" + tempoTotalMilliSec + " (in MilliSecondi).");
            fw.close();
        }
        catch (IOException ex) { ex.printStackTrace(); }
    }
    /**Metodo serializzaEroe_Buffer:
     * Serve per serializzare l'eroe tramite stream di caratteri (usando i buffer):
     * @note cattura l'eccezione dentro il blocco try/catch, non riportandola al metodo chiamante
     *       (come fatto sopra).
     * @since Versione 2.0.
     * @author Runci Riccardo.*/
    public void serializzaEroe_Buffer()
    {
        this.tempoStartMilliSec = System.nanoTime(); //inizializzo il tempo!
        double tempoFineMilliSec, tempoTotalMilliSec;
        try
        {
            BufferedWriter bw; //Il BufferedWriter (serializzazione caratteri CON BUFFER):
            /*Il costruttore di BufferedWriter non vuole un parametro Path, ma vuole un oggetto FileWriter!*/
            bw=new BufferedWriter(new FileWriter("./eroeSerializzatoStreamCaratteriBuffer.txt"));

            StringBuilder informazioniDaSerializzare = new StringBuilder();
            informazioniDaSerializzare.append("NOME EROE: " + this.e.getNomePersonaggio()+"\n");
            informazioniDaSerializzare.append("VITA EROE: " + this.e.getPuntiVitaPersonaggio()+"\n");
            informazioniDaSerializzare.append("DANNO CHE CAUSA EROE: " + this.e.getDannoCausato()+"\n");
            informazioniDaSerializzare.append("OGGETTI DELLO ZAINO EROE: " + this.e.getZaino().toString()+"\n");
            informazioniDaSerializzare.append("-----------------------------------------------------\n");
            bw.write(informazioniDaSerializzare.toString());
            bw.write("\n");

            tempoFineMilliSec = System.nanoTime();
            tempoTotalMilliSec = (tempoFineMilliSec - this.tempoStartMilliSec) /  1_000_000.0;
            /*System.out.println("Per serializzare L'eroe tramite l'utilizzo di stream di caratteri (SENZA BUFFER)," +
                   "ho impiegato: " + tempoTotalMilliSec + " MILLISECONDI\n");*/
            this.setTempoFinale(tempoTotalMilliSec); //aggiorno il campo di questa classe TempoFine!
            bw.write("TEMPO SPESO PER SERIALIZZARE L'EROE\nCON STREAM DI CARATTERI, CON BUFFER:\n" + tempoTotalMilliSec + " (in Millisecondi).");
            bw.close();
        }
        catch (IOException ex) {ex.printStackTrace();}
    }

    /**Metodo getTempoFinale:
     * NON Ritorna IL TEMPO TRASCORSO dall'inizio alla fine!
     * Ma ritorna il tempo totale impiegato.
     * La mia idea è quella di fare un test: assertTrue(TempoImpiegatoBuffer<TempoImpiegatoNoBuffer).
     * @return tempoEndNanoSec
     * @since Versione 2.0.
     * @author Runci Riccardo. */
    public double getTempoFinale()
    {
        return this.tempoEndMilliSec;
    }
    public void setTempoFinale(Double tempoFinale)
    {
        this.tempoEndMilliSec = tempoFinale;
    }
}
