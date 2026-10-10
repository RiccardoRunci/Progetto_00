package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_CARATTERI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DeserializzaEroe_StreamCaratteri
{
    public DeserializzaEroe_StreamCaratteri()
    { }
    public void  deserializzaEroeStreamByte_NoBuffer()
    {
        try
        {
            FileReader fr;
            fr = new FileReader("./eroeSerializzatoStreamCaratteriNoBuffer.txt");

            int dato;
            while((dato = fr.read()) != -1) //-1 significa che il file è finito!
            {
                System.out.print("NUM CARATTERE: "+dato+" CARATTERE UNICODE: "+(char) dato+"\n");
            }

            /*PER LEGGERE SOLO IL PRIMO CARATTERE DEL FILE:
             *read legge il codice Unicode del carattere salvato sul file.txt, devi fare il cast!
            char c = (char) fr.read();
            System.out.println("carattere letto: "+c); */

            fr.close();
        }
        catch (IOException ex) {ex.printStackTrace();}
    }
    public void  deserializzaEroeStreamByte_ConBuffer()
    {
        try
        {
            FileReader fr;
            fr = new FileReader("./eroeSerializzatoStreamCaratteriBuffer.txt");
            BufferedReader br = new BufferedReader(fr);

            int dato;
            while((dato = fr.read()) != -1) //-1 significa che il file è finito!
            {
                System.out.print("NUM CARATTERE: "+dato+" CARATTERE UNICODE: "+(char) dato+"\n");
            }

            /*PER LEGGERE SOLO IL PRIMO CARATTERE DEL FILE:
             *read legge il codice Unicode del carattere salvato sul file.txt, devi fare il cast!
            char c = (char) fr.read();
            System.out.println("carattere letto: "+c); */
            fr.close();
        }
        catch (IOException ex) {ex.printStackTrace();}
    }
}