/**Classe LeggiXmlMaghi:
 * Questa classe (è una sorta di clone della classe LeggiJsonGuerrieri)
 * */
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LeggiXmlMaghi
{
    public LeggiXmlMaghi()
    {}

    public void leggiFileXml()
    {
        try
        {
            /*Carico il file xml dalla classpath, perché l'ho inserito dentro la cartella resource di gradle!*/
            InputStream is;
            is = getClass().getClassLoader().getResourceAsStream("NomiMaghi.xml");
            if (is == null) {
                throw new FileNotFoundException("NomiMaghi.xml non trovato nel classpath");
            }
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
    }
}
