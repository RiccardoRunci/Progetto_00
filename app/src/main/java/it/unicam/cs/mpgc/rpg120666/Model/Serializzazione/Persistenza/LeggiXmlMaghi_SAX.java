/**Classe LeggiXmlMaghi:
 * Questa classe (è una sorta di clone della classe LeggiJsonGuerrieri).
 * Questa classe usa il file "Maghi_ver2.xml" situato dentro la cartella dei file statici di gradle.
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
/*La classe che implementa un parser SAX deve estendere la superclasse DefaultHandler.
* In questo modo, la nostra classe parser sax, dovrà fare la sovrascrittura di 3 metodi:
* 1)void startElement(String uri,String localName, String qName, Attributes attributes) throws SAXException;
* 2)void characters(char ch[], int start, int length) throws SAXException;
* 3)void endElement(String uri, String localName, String qName) throws SAXException. */
public class LeggiXmlMaghi_SAX extends DefaultHandler
{
    private InputStream is; //serve per la cartella Resource.
    private StringBuilder infoLette=null;
    private ArrayList<Mago> listaMaghi= new ArrayList<>();
    private String nomeMago = "";
    private int vitaMago=0,dannoMago=0,magieMago=0;

    /**Metodo costruttore:
     * Come prima cosa tiro fuori il file xml dalla cartella resource.
     * Lo tiro fuori usando gli InputStream perché è l'unico modo che si può usare.
     * @throws FileNotFoundException se il file non viene trovato!*/
    public LeggiXmlMaghi_SAX()
    {
        try
        {
            /*Carico il file xml dalla classpath, perché l'ho inserito dentro la cartella resource di gradle!
            * Lo salvo dentro il campo is. Questo poi sarà utile al metodo sxp.parse(this.is, vaiConIlParser);*/
            this.is = getClass().getClassLoader().getResourceAsStream("Maghi_ver2.xml");
            if (is == null)
            {
                throw new FileNotFoundException("Maghi_ver2.xml non trovato nel classpath");
            }
        }
        catch (FileNotFoundException e) {e.printStackTrace();}

        this.infoLette=new StringBuilder();
    }
    /**Metodo che fa partire il parser SAX:
     * Contiene tutte le direttive che solitamente stavano nelle classi Main
     * (che servivano a far partire tutto il lavoro di parsing del sax).
     *
     * @return listaMago è l' array list di maghi letti dal file xml.
     * Restituisce l' ArrayList riempito di maghi.
     *
     * Dovrei restituire l' ArrayList alla classe FactoryNemici,
     * così posso fare in modo di avere dei nomi di maghi in matrice "personalizzati" (che non siano "MAGO1","MAGO2", ecc...).
     * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
     * @since versione 2.0.*/
    public ArrayList<Mago> vaiConIlSAX()
    {
        /*Per far partire il parser sax del file xml maghi:
         * Pag 70 appunti Lorenzo Rossi:*/
        SAXParserFactory factory =SAXParserFactory.newInstance();
        factory.setValidating(true);
        try
        {
            SAXParser sxp=factory.newSAXParser();
            //LeggiXmlMaghi vaiConIlParser = new LeggiXmlMaghi(); EX CORPO DEL MAIN Che lanciava questo parser. Trasformato in:
            sxp.parse(this.is, this);

            //System.out.println("Parsing completato con successo!");
            //this.stampaListaMaghi();
        }
        catch (ParserConfigurationException | SAXException | IOException e){e.printStackTrace();}
        return this.listaMaghi;//se arriva fino qui restituisco l' ArrayList.
    }
    /**È il metodo sovrascritto della super classe DefaultHandler, estesa da questa classe.
     *Si attiva quando vede nel documento xml (da 'parsare') i tag di apertura.
     * @param uri NON UTILIZZATO, è un stringa che serve ai namespace.
     * @param localName NON UTILIZZATO, è una stringa che serve per leggere il nome del tag (in caso ci sono insieme dei namespace).
     * @param qName serve per capire quale TAG sto scorrendo nel file xml!
     * @param attributes sono tutti gli ATTRIBUTI xml (quelli che stanno dentro i tag) incontrati durante la 'scansione'
     *                   del file xml. Con attributes.getValue("nomeAttributo") riesco a filtrare quelli che mi interessano!*/
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
        this.infoLette.setLength(0); //perché se no va a finire che nella stringa ci sono un sacco di cose.
    }
    /**È un altro metodo ereditato e sovrascritto dalla super classe DefaultHandler,
     * se ho ben capito, dovrebbe essere il testo che è riuscito a catturare dentro i 2 tag xml di apertura e chiusura.
     * @param ch è il vettore di char contenente tutto il testo (SENZA i caratteri dei tag di apertura e chiusura).
     *           Quando arriviamo al metodo characters, i tag di apertura e chiusura non ci sono più.
     * @param start è il "puntatore" che punta all'indice di ch dove INCOMINCIA il testo da estrapolare.
     *              Abbiamo detto che qui dentro non abbiamo più i caratteri dei tag!
     * @param length è la lunghezza del testo compreso da estrapolare.*/
    @Override
    public void characters(char ch[], int start, int length) throws SAXException
    {
        /*String testoLetto = new String(ch, start, length);
         *System.out.println("Testo letto: " + testoLetto);*/
        this.infoLette.append(new String(ch, start, length));
    }
    /**L'ultimo metodo che viene ereditato dalla classe DefaultHandler e sovrascritto qui!
     * Questo metodo si attiva quando riceve i tag di chiusura del file xml 'parsato'.
     * @param uri  saranno sempre delle stringhe che fanno riferimento ai namespace dei tag.
     * @param localName saranno sempre delle stringhe che fanno riferimento ai tag che hanno i namespace,
     *                  ma tralasciando i namespace.
     * @param qName è l'unico parametro che ci interessa.
     *              In questo metodo riesco a capire/carpire/filtrare le informazioni del file xml che mi interessano!*/
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        String testoDelTag = infoLette.toString().trim();

        if (qName.equals("nomePersonaggio")) //questo è il di chiusura: </nomePersonaggio>
        {
            //System.out.println("NOME MAGO: "+testoDelTag);
            this.nomeMago=testoDelTag;
        }
        if (qName.equals("puntiVitaPersonaggio")) //questo è il di chiusura: </puntiVitaPersonaggio>
        {
            //System.out.println("VITA MAGO: "+testoDelTag);
            this.vitaMago=Integer.parseInt(testoDelTag);
        }
        if (qName.equals("dannoCausato")) //questo è il di chiusura: </dannoCausato>
        {
            //System.out.println("DANNO MAGO: "+testoDelTag);
            this.dannoMago=Integer.parseInt(testoDelTag);
        }
        if (qName.equals("numeroMagie")) //questo è il di chiusura: </numeroMagie>
        {
            //System.out.println("MAGIE MAGO: "+testoDelTag);
            this.magieMago=Integer.parseInt(testoDelTag);
        }
        /*Quando poi ho letto alla fine il tag di chiusura del mio xml (PER INDICARE UN MAGO),
        * posso prendere tutti gli elementi che ho carpito:*/
        if (qName.equals("Mago")) //questo è il di chiusura: </Mago>
        {
            /*Alla fine quando legge </Mago> posso creare l'oggetto mago con tutte e info raccolte!*/
            //System.out.println("MAGO LETTO!");
            this.listaMaghi.add(new Mago(this.nomeMago,this.vitaMago,this.dannoMago,this.magieMago));
        }
    }
//    /*Metodo usato solo per vedere se funzionava tutto*/
//    @Deprecated
//    private void stampaListaMaghi()
//    {
//        System.out.println("LISTA MAGHI LETTA: ");
//        System.out.println("HO LETTO "+ this.listaMaghi.size()+" MAGHI DAL FILE XML!");
//        for (int i=0;i<this.listaMaghi.size();i++)
//        {
//            //this.listaMaghi.get(i).toString();
//            System.out.println(this.listaMaghi.get(i).toString());
//        }
//    }
}
