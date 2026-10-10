package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_BYTE;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DeserializzaEroe_StreamByte
{
    public DeserializzaEroe_StreamByte()
    {
    }
    public void deserializzaEroeStreamByteNoBuffer()
    {
        try
        {
            FileInputStream fis = null;
            fis = new FileInputStream("eroeSerializzatoStreamCaratteriNoBuffer.txt");

            int dato;
            while((dato=fis.read())!=-1)
            {
                System.out.println("Codice unicode: "+dato);
                System.out.println("Carattere: "+(char)dato);
            }

            fis.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }
    public void deserializzaEroeStreamByteConBuffer()
    {
        try
        {
            FileInputStream fis = null;
            fis = new FileInputStream("eroeSerializzatoStreamCaratteriBuffer.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);

            int dato;
            while((dato=fis.read())!=-1)
            {
                System.out.println("Codice unicode: "+dato);
                System.out.println("Carattere: "+(char)dato);
            }

            fis.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
