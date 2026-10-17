/**Classe LeggiXmlMaghi:
 * Questa classe (è una sorta di clone della classe LeggiJsonGuerrieri).
 * Questa classe usa il file "NomiMaghi_ver2.xml" situato dentro la cartella dei file statici di gradle.
 *
 * Cartella Resource di Gradle (non mi ricordo mai le path):
 * Inizialmente devo copiare il file dentro: 'Cartella_Radice/app/src/main/resources'!,
 * Poi fare 'gradle build' e Trovo una copia del tuo file dentro: 'Cartella_Radice/app/build/resourcers'!
 *
 * Per fare il parsing del file xml vorrei usare il parsing a eventi sax
 * (anche se leggo tutto i documento, lo faccio solo per ripasso).
 * Poi dovrò gestire io le informazioni estrapolate dal file xml.
 * @author Runci Riccardo
 * @since versione 2.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Mago;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LeggiXmlMaghi extends DefaultHandler
{
    private InputStream is; //serve per la cartella Resource.
    private StringBuilder infoLette=null;
    private ArrayList<Mago> listaMaghi= new ArrayList<>();
    private String nomeMago = "";
    private int vitaMago=0,dannoMago=0,magieMago=0;

    /**Metodo costruttore:
     * come prima cosa tiro fuori il file xml dalla cartella resource.
     * @throws FileNotFoundException se il file non viene trovato!*/
    public LeggiXmlMaghi()
    {
        try
        {
            /*Carico il file xml dalla classpath, perché l'ho inserito dentro la cartella resource di gradle!
            * Lo salvo dentro il campo is. Questo poi sarà utile al metodo sxp.parse(this.is, vaiConIlParser);*/
            this.is = getClass().getClassLoader().getResourceAsStream("NomiMaghi_ver2.xml");
            if (is == null)
            {
                throw new FileNotFoundException("NomiMaghi_ver2.xml non trovato nel classpath");
            }
        }
        catch (FileNotFoundException e) {e.printStackTrace();}

        this.infoLette=new StringBuilder();
        buttaLoFoco(); //come se dice a citanò
    }
    private void buttaLoFoco()
    {
        /*Per far partire il parser sax del file xml maghi:
         * Pag 70 appunti Lorenzo Rossi:*/
        SAXParserFactory factory =SAXParserFactory.newInstance();
        factory.setValidating(true);
        try
        {
            SAXParser sxp=factory.newSAXParser();
            //LeggiXmlMaghi vaiConIlParser = new LeggiXmlMaghi();
            sxp.parse(this.is, this);

            System.out.println("Parsing completato con successo!");
            this.stampaListaMaghi();
        }
        catch (ParserConfigurationException | SAXException | IOException e){e.printStackTrace();}
    }
    @Override
    public void startElement(String uri,String localName, String qName, Attributes attributes) throws SAXException
    {
        if(qName.equals("Mago")) //é il tag di apertura dei miei elementi Mago del file xml!
        {
            /*Leggo l'attributo numMago dell'elemento Mago (del file xml):
            * e faccio il cast con Integer.parseInt perché non posso trasformare facilmente una stringa in un
            * intero con "(int)stringa":
            *
            * Non credo che lo userò, però devo ripassare:*/
            int numMago = Integer.parseInt(attributes.getValue("numMago"));
                //System.out.println("Il numero del mago letto è: "+numMago);
        }
        // Pulisci il buffer ogni volta che entri in un nuovo tag
        this.infoLette.setLength(0);
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException
    {
        /*String testoLetto = new String(ch, start, length);
         *System.out.println("Testo letto: " + testoLetto);*/
        this.infoLette.append(new String(ch, start, length));
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        String testoDelTag = infoLette.toString().trim();

        if (qName.equals("nomePersonaggio"))
        {
            //System.out.println("NOME MAGO: "+testoDelTag);
            this.nomeMago=testoDelTag;
        }
        if (qName.equals("puntiVitaPersonaggio"))
        {
            //System.out.println("VITA MAGO: "+testoDelTag);
            this.vitaMago=Integer.parseInt(testoDelTag);
        }
        if (qName.equals("dannoCausato"))
        {
            //System.out.println("DANNO MAGO: "+testoDelTag);
            this.dannoMago=Integer.parseInt(testoDelTag);
        }
        if (qName.equals("numeroMagie"))
        {
            //System.out.println("MAGIE MAGO: "+testoDelTag);
            this.magieMago=Integer.parseInt(testoDelTag);
        }
        if (qName.equals("Mago"))
        {
            /*Alla fine quando legge </Mago> posso creare l'oggetto mago con tutte e info raccolte!*/
            //System.out.println("MAGO LETTO!");
            this.listaMaghi.add(new Mago(this.nomeMago,this.vitaMago,this.dannoMago,this.magieMago));
        }
    }
    @Deprecated
    private void stampaListaMaghi()
    {
        System.out.println("LISTA MAGHI LETTA: ");
        System.out.println("HO LETTO "+ this.listaMaghi.size()+" MAGHI DAL FILE XML!");
        for (int i=0;i<this.listaMaghi.size();i++)
        {
            //this.listaMaghi.get(i).toString();
            System.out.println(this.listaMaghi.get(i).toString());
        }
    }
}
