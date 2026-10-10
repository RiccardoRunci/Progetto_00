/**Classe SerializzaEroe_StreamByte:
 * Questa classe non è utile ai fini del progetto, l'ho fatta solo per ripassare la serializzazione.
 * In questa classe utilizzo la serializzazione di stream di byte.
 * Sia con buffer, sia senza buffer.
 * @since versione 2.0.
 * @author Runci Riccardo.*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_BYTE;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializzaEroe_StreamByte
{
    private Eroe e;
    /**Metodo costruttore:
     * @param e = è l'eroe da serializzare.
     * @throws IllegalArgumentException se l'eroe da serializzare è null oppure ha il nome = stringa vuota!
     * Se va tutto bene crea l'oggetto!*/
    public  SerializzaEroe_StreamByte(Eroe e)
    {
        if(e==null || e.getNomePersonaggio().isEmpty())
            throw new IllegalArgumentException("Eroe passato al costruttore della classe:\n" +
                    "SerializzaEroe_StreamByte è null oppure il suo nome è stringa vuota!\n");
        this.e = e;
    }
    /**Metodo serializzaEroeStreamByteNOBuffer:
     * Serializza (solo alcuni campi dell')l'eroe con il metodo Stream Byte SENZA BUFFER!
     * Tutte le eccezioni vengono gestite qui all'interno!*/
    public void serializzaEroeStreamByteNOBuffer()
    {
        try
        {
            FileOutputStream fi = null;
            fi=new FileOutputStream("./eroeSerializzatoStreamByteNoBuffer.txt");

            StringBuilder stringaDaScrivere = new StringBuilder();
            stringaDaScrivere.append("NOME PERSONAGGIO: "+e.getNomePersonaggio()+"\n");
            stringaDaScrivere.append("VITA PERSONAGGIO: "+e.getPuntiVitaPersonaggio()+"\n");

            /*.write(...) delle classi STREAM BYTE vuole che trasformi il tuo contenuto da salvare in bytes,
            * tramite .getBytes()!*/
            fi.write(stringaDaScrivere.toString().getBytes());

            fi.close();
        }
        catch (IOException e) { e.printStackTrace(); }


    }
    /**Metodo serializzaEroeStreamByteNOBuffer:
     * Serializza (solo alcuni campi dell')l'eroe con il metodo Stream Byte CON BUFFER!
     * Tutte le eccezioni vengono gestite qui all'interno!*/
    public void serializzaEroeStreamByteCONBuffer()
    {
        try
        {
            BufferedOutputStream fi = null;
                                                //BufferedOutputStream richiede un FileOutputStream:
            fi=new BufferedOutputStream(new FileOutputStream("./eroeSerializzatoStreamByteConBuffer.txt"));
            StringBuilder stringaDaScrivere = new StringBuilder();
            stringaDaScrivere.append("NOME PERSONAGGIO: "+e.getNomePersonaggio()+"\n");
            stringaDaScrivere.append("VITA PERSONAGGIO: "+e.getPuntiVitaPersonaggio()+"\n");

            /*.write(...) delle classi STREAM BYTE vuole che trasformi il tuo contenuto da salvare in bytes,
             * tramite .getBytes()!*/
            fi.write(stringaDaScrivere.toString().getBytes());

            fi.close();
        }
        catch (IOException e) { e.printStackTrace(); }

    }
}
