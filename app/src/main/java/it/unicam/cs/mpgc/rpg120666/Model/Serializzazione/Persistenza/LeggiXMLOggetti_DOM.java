/**Classe che mi permette di fare il parsing del documento xml "Oggetti.xml".
 * Oggetti.xml è un file xml che contiene Armi e Medikit.
 * Il parsing di questo documento lo vorrei fare con il metodo DOM.
 * @author Runci Riccardo.
 * @since versione 2.0*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LeggiXMLOggetti_DOM
{
    private InputStream is; //serve per la cartella Resource.
    /*DocumentBuilderFactory -> crea il parser DOM!*/
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    /*DocumentBuilder -> legge il file xml e costruisce l'albero DOM in memoria*/
    private DocumentBuilder builder= null;
    /*Rappresenta l'albero xml in memoria!*/
    private Document doc=null;

    /*arrayOggetti è una variabile da ritornare alla classe FactoryOggetti, questa conterrà tutti gli Oggetti estratti da questo
    * parser dom (sia Medikit che Armi)! Poi dovrò nella classe FactoryOggetti separarli.
    *
    * Ho usato un AraryList (quindi con il rischio che mi potevo portare dietro anche dei doppioni).
    * Se invece dovevo levare i doppioni dovevo trasformarlo in un set. Prima creavo il set, poi usavo un metodo
    * setCreato.addAll(arrayOggetti) Che è un metodo esposto dalla classe Collection! Estesa da HashSet che a sua volta implementa Set!*/
    private ArrayList<Oggetto> arrayOggetti;

    /**Metodo get che servirà alla classe Factory Oggetti per recuperare gli oggetti letti dal file xml!*/
    public ArrayList<Oggetto> getArrayOggetti()
    {
        if(this.arrayOggetti==null)
            throw new IllegalArgumentException("Array Oggetti letti dal parser dom non ancora inizializzato!");
        return this.arrayOggetti;
    }
    /**Metodo costruttore:
     * Carico il file xml (Oggetti.xml) contenuto dentro la cartella statica resource di gradle.
     * Poi richiamo i metodi caricaXmlInMemoria & scorriXMLePrendiTutto!*/
    public LeggiXMLOggetti_DOM()
    {
        try
        {
            /*Carico il file xml dalla classpath, perché l'ho inserito dentro la cartella resource di gradle!
             * Lo salvo dentro il campo is. */
            this.is = getClass().getClassLoader().getResourceAsStream("Oggetti.xml");
            if (is == null)
            {
                throw new FileNotFoundException("Oggetti.xml non trovato nel classpath");
            }
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        this.arrayOggetti=new ArrayList<>(); //inizializzo l' array se non è avvenuta un eccezione!
        this.caricaXmlInMemoria(); //chiamo i metodi che mi servono a far funzionare il parser!
        this.scorriXMLePrendiTutto();
        //this.stampaArrayOggetti();
    }

    /**Metodo caricaXmlInMemoria privato che con:
     * DocumentBuilderFactory -> Crea il parser DOM (stà in alto nei campi della classe, ma in una diversa
     *                           implementazione dovrebbe comparire vicino agli oggetti sotto);
     * DocumentBuilder -> legge il file xml e costruisce l'albero DOM in memoria;
     * Document -> rappresenta l'albero xml in memoria!
     * @implNote Differentemente dagli appunti, ho usato il | per inglobare
     * in un unico catch tutte le eccezioni che l' IDE mi obbligava di lanciare.
     * */
    private void caricaXmlInMemoria()
    {
        /*Queste 3 righe di codice sono quelle che ci consentono di caricare un documento xml in memoria:
        * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        * DocumentBuilder builder = factory.newDocumentBuilder();
        * Document doc=builder.parse("File.xml");
        * [Pag 72 Lorenzo Rossi]*/
        try
        {
            this.builder = factory.newDocumentBuilder();
            this.doc=builder.parse(this.is);
        }
        catch (ParserConfigurationException | SAXException | IOException e) { throw new RuntimeException(e); }
    }
    /**Metodo scorriXMLePrendiTutto privato:
     * Metodo che scorre tutto il file xml e prende tutto il contenuto che mi interessa!*/
    private void scorriXMLePrendiTutto()
    {
        /*Estrazione del nodo radice e stampa del nome del nodo(quindi la radice del xml):*/
        Element root =this.doc.getDocumentElement();
        //ystem.out.println(root.getNodeName()); //Stampa la radice del documento xml!

        /*Il metodo getChildenNodes() restituisce una lista di nodi FIGLI DI ROOT.
         * getChildNodes() restituisce TUTTI i nodi figli, non solo gli elementi XML.
         * Quindi anche gli spazi bianchi e gli "a capo".
         * Children è una lista di nodi!*/
        NodeList childern = root.getChildNodes();

        for(int i=0;i<childern.getLength();i++)
        {
            Node nodo=childern.item(i); //Prende l'i-esimo nodo dalla lista children (lista di nodi)!
            /*nodo rappresenta tutto l'elemento xml, esempio, se il nodo è un Arma, allora il nodo sarà:
            * <Arma><nomeOggetto>BFG90000</nomeOggetto> <puntiDannoPiu>9000</puntiDannoPiu></Arma>*/

            /*Devo ignorare i text e gli "a capo":
            * Se il nodo (letto dalla lista di nodi children) NON è un elemento xml, lo salto:*/
            if(nodo.getNodeType() != Node.ELEMENT_NODE) continue;

           //System.out.println(nodo.getNodeName()); //Stampa il nome del nodo (Arma/Medikit)!

            /*Ora dovrei filtrare gli elementi per Armi e per Medikit, tramite il metodo .getNodeName()!*/
            if(nodo.getNodeName().equals("Arma"))
            {
                /*La variabile "arma" NON é una variabile della mia classe Arma!
                * é una variabile di tipo org.w3c.dom.Element!
                * La quale punta al tag: <Arma> <nomeOggetto>BFG90000</nomeOggetto> <puntiDannoPiu>9000</puntiDannoPiu> </Arma>!
                * Element arma = è un elemento XML che rappresenta un'Arma dentro il mio file xml!*/
                Element arma = (Element) nodo;
                /*La difficoltà di getElementByTagName è che lui ti restituisce una LISTA, ma (per come è fatto il file xml) a
                * me serve solo e sempre il 1° elemento (cioè l'elemento 0). Poi posso prendere il testo (contenuto tra i tag
                * xml del file) grazie al metodo getTextContent()!*/
                //System.out.println("NOME ARMA: "+ arma.getElementsByTagName("nomeOggetto").item(0).getTextContent());
                //System.out.println("Punti DANNO ARMA: "+ arma.getElementsByTagName("puntiDannoPiu").item(0).getTextContent());
                String NomeOgg = arma.getElementsByTagName("nomeOggetto").item(0).getTextContent();
                int puntiOgg = Integer.parseInt(arma.getElementsByTagName("puntiDannoPiu").item(0).getTextContent());
                this.arrayOggetti.add(new Arma(NomeOgg,puntiOgg));
            }
            else if(nodo.getNodeName().equals("Medikit"))
            {
                Element medikit = (Element) nodo;
                String NomeOgg = medikit.getElementsByTagName("nomeOggetto").item(0).getTextContent();
                int puntiOgg = Integer.parseInt(medikit.getElementsByTagName("puntiVitaRistabiliti").item(0).getTextContent());
                this.arrayOggetti.add(new Medikit(NomeOgg,puntiOgg));
            }
        }
    }
    /**Metodo deprecato.
     * Usato solo per vedere se ha funzionato il parser!*/
    @Deprecated
    private void stampaArrayOggetti()
    {
        System.out.println("Array Oggetti:");
        for (int i=0;i<this.arrayOggetti.size();i++)
        {
            System.out.println("Oggetto:"+i+" = "+this.arrayOggetti.get(i).toString());
        }
    }
}
