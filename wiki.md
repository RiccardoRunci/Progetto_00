# Il Progetto - Non ha un titolo, è solo un progetto didattico.
## (Wiki del Progetto)

---

# INDICE:
1. Descrizione del Progetto
2. Software e versioni di Software utilizzate
3. Classi e Responsabilità
4. Persistenza dei Dati
5. Estensibilità
6. Principi SOLID & Clean Code
7. Test
8. Dichiarazione Uso AI

---

# 1)DESCRIZIONE DEL PROGETTO:

Il Progetto è un gioco di ruolo senza titolo e trama. 
Lo scopo del gioco è riuscire a eliminare tutti i nemici presenti nella Matrice Gioco.

Ci si sposta tramite bottoni e text box dell'interfaccia grafica.
Per completare il gioco non è necessario raccogliere tutti gli oggetti. Tuttavia, 
è consigliabile recuperare almeno alcuni medikit, altrimenti si rischia di morire quasi subito.

L'eroe comincia il gioco sempre nell'ultima casella in basso a destra della matrice.

Nella mia interfaccia grafica:
L'eroe è rappresentato da una casella viola,
I nemici sono colorati di rosso (se sono Guerrieri) e di blu (se sono dei Maghi),
Gli oggetti sono verdi (medikit) e gialli (armi = potenziamento attacco dell'eroe).
Una volta che l'eroe va nella stessa casella di un nemico, comincia la lotta.
Il nemico attacca sempre per primo.
Se l'eroe va sopra una casella Oggetto (Arma o Medikit), l'eroe raccoglie l'ogetto e lo mette dentro il suo zaino.
Lo Zaino è rappresentato dell'oggeto TableView situato alla destra della matrice.
Per usare un oggetto posseduto dall'eroe, basta cliccare sopra a quell'oggetto (presente nella tabella).

---

# 2)SOFTWARE E VERSIONI DI SOFTWARE IMPORTATE IN GRADLE:

Ho usato Gradle come build automation software (versione 9.4.1);
Ho implementato il progetto in Java (java SE version "25.0.2" 2026-01-20 LTS );
Ho Usato la libreria Junit6 per i test (versione6.1.1)
Ho usato la libreria JavaFX per la grafica del gioco (versione 25);
Ho usato la libreria Gson per la serializzazione in formato JSON (versione 2.14);

Ho utilizzato Jpa, Hibernate e H2 per la Serializzazione dei punteggi, alle seguenti versioni:
Hibernate:6.6.59.Final,
Jpa:3.1.0
H2:2.2.22



---

# 3)CLASSI E RESPONSABILITÀ:

## package Test
Ho implementato alcuni test per le classi del progetto (non di tutte), quelle che ho fatto
si possono trovare qui dentro.

## package View
Ho usato il pattern Model View Controller. 
Dentro il package View ho inserito le classi che contengono gli oggetti JavaFx utilizzati 
per l'interfaccia grafica del progetto.
## package Controller
Contiene tutte le classi "controller" che fanno comunicare le classi view con le classi model.
All'inizio avevo solo una classe controller (MatriceController.java). 
Poi sono riuscito a "snellirla" e inserire una nuova classe per la "gestione dello zaino" (ZainoController).
## package Main 
Contiene tutte le classi che hanno il metodo main per far partire il progetto.
Ce ne sono diverse perché sono quelle che sono state usate durante lo sviluppo (le ho tenute). 
La più importate è **MainFx2.java**.

## package Model 
contiente tutte le classi Logiche del progetto, nello specifico:

| Package                               | Classe                         | Descrizione                                                                                                                                                                                     |
|---------------------------------------|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Difficolta                            | Difficolta                     | Rappresenta la difficoltà del gioco.                                                                                                                                                            |
| Difficolta                            | EnumDifficolta                 | Enumerazione che serve per la classe Difficoltà.                                                                                                                                                |
| ElementiPerMatrice                    | ElementiPerMatrice             | Interfaccia che viene implementata da tutte le classi che possono entrare dentro la MatriceGioco. E' una sorta di sosia della classe Validatore vista a lezione.                                |
| Eroe                                  | Eroe                           | Classe che rappresenta L'Eroe del gioco.                                                                                                                                                        |
| Eroe                                  | Zaino                          | Questa classe è una sorta di sosia della classe Libretto (dello studente) vista a lezione. Rappresenta lo zaino dell'eroe.                                                                      |
| MatriceGioco                          | CellaMatrice                   | Classe che rappresenta una cella della MatriceGioco.                                                                                                                                            |
| MatriceGioco                          | MatriceGioco                   | Classe che crea la matrice(la griglia) di gioco. Chiama le classi per posizionare Oggetti, Nemici E Eroe.                                                                                       |
| MatriceGioco\ContaElementiRimanenti   | EnumOggNem                     | Enumerazione che rappresenta una soluzione "casalinga" che serve per evitare i wildcards (< ?) suggeriti dall'IA ma non utilizzati **implica la violazione di open/Close**.                     |
| MatriceGioco\ContaElementiRimanenti   | MatriceContaElementi           | Classe che conta gli elementi rimanenti in "ElementiPerMatrice[][] matrice. **implica la violazione di open/Close**.                                                                            |
| MatriceGioco\Manager                  | ManagerCombattimentoEroeNemico | Classe che rappresenta il combattimento tra l'Eroe e il Nemico incontrato.                                                                                                                      |
| MatriceGioco\Manager                  | ManagerFineGioco               | Classe che Serve per controllare se il gioco è terminato o meno. **pratica scorrett: utilizzo Alert di javaFx**.                                                                                |
| MatriceGioco\Manager                  | ManagerIncontroNemiciOggetti   | Classe che Controllare cosa c'è nella cella di destinazione. Delega all'oggetto incontrato il comportamento corretto tramite calpestataDaEroe() Contenuto nell'interfaccia ElementiPerMatrice.  |
| MatriceGioco\Manager                  | ManagerSpostamentoEroe         | In questa classe parte una serie di classi a catena. Questa classe è lo "start". Questa classe richiama la classe ManagerIncontroNemiciOggetti.                                                 |
| MatriceGioco\Popolamento              | PopolaMatriceEroe              | Questa classe serve per inserire l'oggetto Eroe dentro la matrice di gioco.                                                                                                                     |
| MatriceGioco\Popolamento              | PopolaMatriceNemici            | Questa classe serve per caricare nella matrice gli antagonisti dell'eroe. **usa la clase FacoryNemici**                                                                                         |
| MatriceGioco\Popolamento              | PopolaMatriceOggetti           | Questa classe serve per riempire la matriceGioco di Oggetti. **usa la classe FactoryOggetti**                                                                                                   |
| Nemico                                | Nemico                         | Questa clase rappresenta un Nemico dell'antagonista, ma è una classe abstract.                                                                                                                  |
| Nemico\ClassiEstese                   | FactoryNemici                  | Classe factory che ritorna oggetti letti da json & xml di tipo sotto-classe di Nemico.                                                                                                          |
| Nemico\ClassiEstese                   | Guerriero                      | Questa classe è un'implementazione della classe abastract Nemico, serve per creare un Guerriero.                                                                                                |
| Nemico\ClassiEstese                   | Mago                           | Questa classe è un'implementazione della classe abastract Nemico, serve per creare un Mago.                                                                                                     |
| Oggetto                               | Oggetto                        | Questa è una classe abstract, serve per rappresentare un Oggetto.                                                                                                                               |
| Oggetto                               | UsaOggetto                     | Interfaccia Funzionale che viene implementata dalla classe Oggetto.                                                                                                                             |
| Oggetto\ClassiEstese                  | Arma                           | Questa è una classe concreta che estente Oggetto e serve per costruire una Arma.                                                                                                                |
| Oggetto\ClassiEstese                  | FactoryOggetti                 | Classe che serve per creare Oggetti letti da xml da inserire nella matrice gioco.                                                                                                               |
| Oggetto\ClassiEstese                  | Medikit                        | Questa è una classe concreta che estente Oggetto e serve per costruire un Medikit.                                                                                                              |
| Oggetto\Interfacce\                   | InterfacciaArma                | Questa interfaccia viene utilizzata dalla classe Arma che estende la classe Oggetto.                                                                                                            |
| Oggetto\Interfacce\                   | InterfacciMedikit              | Questa interfaccia viene utilizzata dalla classe Medikit che estende la classe Oggetto.                                                                                                         |
| Personaggio                           | InterfacciaPerPersonaggio      | Interfaccia Funzionale implementata da Eroe, Guerriero, Mago e Personaggio.                                                                                                                     |
| Personaggio                           | Personaggio                    | Classe astratta estesa dalle classi Nemico e Eroe.                                                                                                                                              |
| Utility                               | CreazioneAggiornamento         | Enumerazione, serve per distinguere i controlli che faccio sui parametri interi che passo ai costruttori e ai metodi set.                                                                       |
| Utility                               | MieAnnotazioni                 | Classe di annotazioni personalizzata. Sulla falsa riga di autore vista in classe.                                                                                                               |
| Utility                               | VerificaParametri              | Classe che permette una verifica dei parametri (stringhe e numeri int) così da non riscrivere sempre lo stesso codice.                                                                          |
| Serializzazione\Persistenza\Hibernate | Punteggi                       | Classe POJO che rappresenta una linea di una tabella Statistiche (memorizzata nel dbms H2 visto a lezione).                                                                                     |
| Serializzazione\Persistenza\Hibernate | UsaPunteggi                    | Classe che contiene l'EntityManager di Hibernate.                                                                                                                                               |
| Serializzazione\Persistenza           | ContatorePersistenzaMatrice    | *Non inclusa nel Main2FX*  Classe utilizzavo per creare un numero progressivo quando salvavo l'intera matrice (grazie alla classe PersistenzaMatrice).                                          |
| Serializzazione\Persistenza           | PersistenzaMatrice             | *Non inclusa nel Main2FX*  Questa classe si occupa di serializzare e deserializzare una matrice gioco tramite l'utilizzo di oggetti ObjectInputStream & ObjectOutputStream.                     |
| Serializzazione\Persistenza           | LeggiJsonGuerrieri             | Questa classe mi serve per leggere una serie di Guerrieri (Nemici) salvati in un file .json contenuto nella cartella speciale "resource" di gradle.                                             |
| Serializzazione\Persistenza           | LeggiXmlMaghi_SAX              | Questa classe usa il file "Maghi_ver2.xml" situato dentro la cartella dei file statici di gradle. Fa il suo parsing utilizando SAX e mi ritorna l'elenco dei Maghi.                             |
| Serializzazione\Persistenza           | LeggiXmlMaghi_DOM              | Classe che mi permette di fare il parsing del documento xml "Oggetti.xml". Oggetti.xml è un file xml che contiene Armi e Medikit. Il parsing di questo documento viene fatto con il metodo DOM. |


--- 

# 4)PERSISTENZA DEI DATI:
## Per il progetto ho utilizzato diverse tecniche di persistenza viste a lezione:
1. Ho utilizzato la Deserializzazione di un file .json (tramite la libreria GSON) per leggere un file json che contiene una lista di Guerrieri (situato nella cartella statica resource di gradle),
2. Ho utilizzato la Deserializzazione di un file .xml (tramite la tecnica Sax) per leggere un file xml contenente una lista di soli Maghi (file situato nella cartella di file statici resource di gradle),
3. Ho utilizzato la Deserializzazione di un file .xml (tramite la tecnica Dom) per leggere un file xml contente una lista di Oggetti (quindi Armi e Medikit), il file è situato nella cartella resource,
4. Ho utilizzato la Deserializzazione e la Serializzazione (tramite stream di caratteri FileReder e FileWriter con i relativi Buffer) per leggere e scrivere un "indice" che veniva usato per la serializzazione della matrice (non incluso nell Main2FX),
5. Ho utilizzato la Serializzazione e Deserializzazine (tramite stream di byte) attraverso l'utilizzo di oggetti ObjectInputStream e ObjectOutputStream per serializzare la matrice di gioco. Questo pezzo non è stato inglobato nella parte grafica (non compare nel "Main2Fx", il suo utilizzo si può vedere commentato in un'altra classe Main.
6. Ho utilizzato la Serializzazione tramite Hibernate & H2 (come visto a lezione) per realizzare una specie di raccolta dei punteggi che vengono salvati ad ogni partita.

---

# 5)ESTENSIBILITÀ:
## Aggiungere nuovi personaggi al gioco:
### Per aggiungere nuovi personaggi (Guerrieri o Maghi) nel gioco basta inserirli nel file json Guerrieri e nel file xml maghi. 

## Aggiungere nuovi oggetti:
### Per aggiungere nuovi Oggetti (Armi o Medikit) nel gioco basta inserirle nel file xml Oggetti (quello validato tramite XSD).
### Ci sono 2 file xml Oggetti nella cartella resource (li ho fatti per ripassare le tecniche di validazione xml), quello che viene utilizzato dal progetto è quello validato con XSD.

## Aggiungere una nuova categoria di Oggetti:
### Ho scritto un file a parte, si può trovare in /Model/Oggetto/HOW_TO_AggiungiOggetto.txt

## Aggiungere una nuova categoria di Nemici:
### Bisogna creare una nuova classe dentro /Model/Nemico/ClassiEstese.
### Questa classe deve extends Nemico implements InterfacciaPerPersonaggi, ElementiPerMatrice.
### Però bisogna modificare anche la classe FactoryNemici (/Model/Nemici/ClassiEstese/FactoryNemici).
### Aggiungendo un case nello statement return switch e aumentare di 1 la condizione (dello stesso statement).

---

# 6)PRINCIPI SOLID E "CLEAN CODE":
Mi sono sforzato di usare i principi SOLID e Clean Code visti durante le lezioni.
## ⚠ ️ Errori che ho commesso (o perlomeno, quelli che sono stato in grado di riconoscere):
### ISP violato nella classe Eroe:
Credo però di aver violato il principio solid di segregazione delle interfacce nella classe:
/Model/Eroe/Eroe che implementa l'interfaccia ElementiPerMatrice.
L'interfaccia mi obbliga a sovrascrivere il metodo:
``` @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        e.toString();
        return true;
    }
```
Ma è un metodo che non serve all'oggetto perché non è previsto che ci siano più di un Oggetto Eroe dentro la Matrice Gioco.
Sempre nella stessa classe, quell'interfaccia mi obbliga a sovrascrivere anche il metodo:
``` @Override
    public String veroVolto() 
    {
        return this.restituisciTipoOggetto();
    }
```
Non dovrebbe essere molto corretto. Ne parlo meglio all'interno dei commenti javadoc di quella classe.
Ho 2 metodi che fanno la stessa cosa (veroVolto e restituisciTipoOggetto).
### Open/Close violato nella classe MatriceContaElementi:
Credo inoltre di aver violato il principio solid Open/Close nella classe:
/Model/MatriceContaElementi/MatriceContaElementi.
Infatti, questo principio dice che il codice deve essere aperto a nuove funzionalità ma (per far funzionare quelle
nuove feature) non devo modificare il vecchio codice già presente nelle altre classi del progetto.
Questo purtroppo accede. Infatti nella classe MatriceContaElementi, qual'ora volessi aggiungere un nuovo elemento all'interno 
della matrice gioco, dovrei (per forza) mettere un nuovo blocco:
``` Nel metodo: public int calcolaNumOggettiNemiciRimanenti(EnumOggNem oggNem).
switch(oggNem)
{
    ...
    case NUOVO_OGGETTO_PERSONAGGIO ->{        
        somma=this.calcola("NUOVO_OGGETTO_PERSONAGGIO"); }
    ...
    default -> throw new IllegalArgumentException("Il valore non valido");
}
```
### Messo Oggetto Alert (javaFx) in una classe back end:
Inoltre (non credo che sia una violazione del clean code, ma di sicuro è sbagliata), ho inserito 
degli Oggetti javafx Alert in una classe backend (/Model/MatriceGioco/Manager/ManagerFineGioco).
L'ho fatto perché a fine progetto mi ero reso contro che non avevo scritto niente per verificare se 
la partita era terminata o meno.

---

# 7)TEST:
Ho utilizzato la libreria JUnit6 per implementare i test di alcune classi del progetto.
Non ho implementato tutti i test di tutte le classi ma solo alcuni.

---

# 8)DICHIARAZIONE USO AI:
## Ho usato molto l'AI come "collega" di studi (durante il periodo in cui studiavo).
## Mi ero successivamente ripromesso di non ri-utilizzarla per il progetto, ma, 
## invece sono incappato nei seguenti scenari (i punti sotto sono collegati a quelli del file README):

**1) TaleView di JavaFx! Come lo ho usato:**
Nella classe /View/ZainoView creo un campo di tipo TableView.
` private final TableView<Map.Entry<Oggetto,Integer>> tabellaZaino; `
Il quale deve essere tipato così (come una riga della Map (con Map.Entry<>)).
Successivamente creo 2 campi (che sono le sue intestazioni), anche loro  di tipo riga della Mappa (con Map.Entry).

Nel costruttore (sempre di ZainoView):
Lo istanzio -->  `this.tabellaZaino = new TableView<>();`
Gli dò altre proprietà tipo: il suo font, lo metto editable, ecc...
Gli aggiungo le sue intestazioni.

    /*Devo inserire questi 2 metodi che servono PER VEDERE i DATI dentro la tableview: */
    this.intestazione1Colonna.setCellValueFactory(cellData ->
        new SimpleStringProperty(
            cellData.getValue().getKey().getNomeOggetto()
        )
    );

        this.intestazione2Colonna.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        cellData.getValue().getValue()
                ).asObject()
        );

Faccio un metodo get che ritorna tutto il TableView alla sua classe controller (/Controller/ZainoController).

Nella classe Controller creo un attributo di tipo TableView (tipato come nella classe View con una Entry della Map).
Passo il TableView (della view) al costruttore del Controller e lo associo al suo campo.

Nella classe Controller c'è un metodo `collegaClickAllaTabella()` che contiene le istruzioni per registrare un listener  all'Oggetto TableView passato dal View.

Nella classe controller c'è un metodo (`aggiornaComponentiZainoeEroe()`) il quale serve ad aggiornare la lista di Oggetti che quel Table view contiene.
Per far questo devo "trasformare" la Map ritornatami dalla classe Zaino in un oggetto ObservableList<RigaDellaMappa>
e riempirlo tramite un for each da quella Mappa ritornatami da Zaino.

*NOTA: tutto il codice descritto in questi passaggi compare scritto meglio nelle classi sopracitate.*

---
**2)Associare una variabile interfaccia per far cliccare le celle di una matrice di Label!**
Si deve creare una nuova interfaccia funzionale in questo modo (View/CellaClickListener):

    public interface CellaClickListener
    {
        void onCellaClick(int riga, int colonna);
    }

Nella classe View (/View/MatriceView) si deve creare un campo:
`private CellaClickListener cellaClickListener;`
e associare (dopo aver creato la matrice di Label) a tutte le label (che compongono quella matrice)
un listener:

    cella.setOnMouseClicked(e -> {
        if (cellaClickListener != null) {
            cellaClickListener.onCellaClick(fRiga, fColonna); }

*NOTA: tutto il codice descritto in questi passaggi compare scritto meglio nelle classi sopracitate.*

---

**3)La prima volta che mi sono imbattuto nelle Enumerazioni (da quella volta dell'esempio del cifrario di Cesare):** 
*Questo esempio è di un implementazione vecchia, ma il succo del discorso è che ...*
Nella classe che ho chiamato "UtilityPersonaggio" avevo 2 metodi quasi uguali,
l'unica cosa che cambiava era la logica del < o <=.

    public static boolean controllaCreazionePuntiVitaPersonaggio (int puntiVitaPersonaggio)
    {
        if (puntiVitaPersonaggio<=0)
            throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                    "I suoi punti vita: "+puntiVitaPersonaggio+" sono <=0.\"n"+
                    "Non posso creare un nemico con questi punti vita!");
        else
            return true;
    }

	public static boolean controllaPuntiVitaPersonaggio (int puntiVitaPersonaggio)
    {
        if (puntiVitaPersonaggio<0)
            throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                    "I suoi punti vita: "+puntiVitaPersonaggio+" sono <0.\"n"+
                    "Il nemico dovrebbe essere già morto!");
        else
            return true;
    }

L'ia mi ha suggerito di creare un enumerazione che specificava se - il controllo della vita del nemico -
era da fare in fase di creazione o in fase di aggiornamento.
Perchè non posso creare un nemico con la vita <=0, ma quando muore un nemico per forza deve avere i suoi punti vita = 0.

---

**4)Per usare la serializzazione tramite JPA e Hibernate**
Prima di tutto bisogna importare le librerie di JPA e di Hibernate nel file build.gradle.kts.
Ho creato la mia classe POJO (/Model/Serializzazione/Persistenza/Hiberate/Punteggi), la quale rappresenta
un record di una tabella di un database (Ho usato H2, come visto a lezione).
Ho utilizzato le @annotation di JPA (nella classe pojo) per riconoscere gli elementi della tabella del database.
La classe Pojo deve `implements Serializable`

Per H2 bisogna:
Importare le sue librerie nelle dipendenze (dentro il file build.gradle.kts);
Scrivere il file di configurazione xml (che si chiama persistence.xml, e non si può cambiare nome) 
presente nella cartella /main/resources/META-INF/persistence.xml.
In quel file bisogna mettere: 
la classe che rappresenta la tabella DBMS (in questo caso Punteggi),
I driver jdbc per collegare H2 a Java,
Ho messo anche una cartella per mantenere la persistenza dei dati salvati su quel database, per mettere questa persistenza ho dovuto anche 
cambiare un parametro in update.

Per poter utilizzare Hibernate (tramite la classe Pojo sopra, la riga della tabella), bisogna creare un'altra classe: 
(/Model/Serializzazione/Persistenza/Hibernate/UsaPunteggi) che usa EntityManager per fare le operazioni nel database.
In questa classe l'attributo EntityManagerFactory deve essere creato una volta sola e statico:
`private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Progetto");`
"Progetto" viene riportato nel file di configurazione xml di H2.

Per ogni query si può fare un metodo diverso:

    public static List<Punteggi> ritornaTutteRighe()
    {
        EntityManager em = emf.createEntityManager();
        List<Punteggi> listaPunteggi = null;
        try
        {
            String sql = "SELECT * FROM Punteggi";

            // tramite em posso fare le query!
            Query query = em.createNativeQuery(sql, Punteggi.class);

            // Esegui la query
            listaPunteggi = query.getResultList();

        } catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return listaPunteggi;
    }

Alla fine dell'esecuzione del programma bisogna ricordarsi di chiudere la sessione dell' EntityManagerFactory.
Io l'ho messa nel metodo stop() sovrascritto dalla super-classe Application di javafx.

---

**5)Classi Factory**
Le classi factory sono classi speciali che servono per aggiungere al mio progetto dei Oggetti di tipi
diversi. Grazie al `return switch` e a un generatore di numeri interi random, posso ritornare un
`new Oggetto(vari parametri che servono per costruirlo)`.
Queste classi sono fatte apositamente per rispettare il principio solid Open/Close.
Bisogna si modificare il loro contenuto ma bisogna modificare solo quello e non altre parti del codice.
Io le ho un po sporcate con l'aggiunta delle varie deserializzazioni, ma il loro funzionamento è molto semplice.

---

**6)Oggetti Alert di javaFx & RadioButton**
Messi dentro una classe logica (ERORE), perché non avevo pensato minimamente al pezzo del progetto per vedere se la partita era conclusa. 
/Model/MatriceGioco/Manager/ManagerFineGioco.

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("TESTO DELLA FINESTRA");
    alert.setHeaderText("Altro testo sotto che può essere nascosto");
    alert.setContentText("Testo effettivo della finestra");
    alert.showAndWait();

Gli Alert sono degli oggetti JavaFx che sostituiscono i vecchi JOptionPane di Swing.
Il loro funzionamento è molto simile.

Mentre i RadioButton li ho usati nella classe main2Fx per scegliere la difficoltà del gioco.
Bisogna metterli dentro un ButtonGroup e successivamente bisogna mettere il ButtonGroup dentro un panel.
Il loro "spuntamento" viene preso dal metodo jradioButton.isSelected

---

**7)Aiuto con i parser Dom:**
I parser Dom sono più complicati rispetto a quelli fatti tramite la modalità a eventi SAX.
Perché hanno (oltre al parser di sola lettura sax), molte altre funzioni, ad esempio:
navigazione del albero xml, salvataggio del xml ecc...

DocumentBuilderFactory -> crea il parser DOM!
`private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();`
DocumentBuilder -> legge il file xml e costruisce l'albero DOM in memoria
`private DocumentBuilder builder= null;`
Rappresenta l'albero xml in memoria!
`private Document doc=null;`

DocumentBuilderFactory -> Crea il parser DOM (stà in alto nei campi della classe, ma in una diversa
                           implementazione dovrebbe comparire vicino agli oggetti sotto);
DocumentBuilder -> legge il file xml e costruisce l'albero DOM in memoria;
Document -> rappresenta l'albero xml in memoria!
 
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

Metodo che scorre tutto il file xml e prende tutto il contenuto che mi interessa:

    private void scorriXMLePrendiTutto()
    {
        /*Estrazione del nodo radice e stampa del nome del nodo(quindi la radice del xml):*/
        Element root =this.doc.getDocumentElement();
        //System.out.println(root.getNodeName()); //Stampa la radice del documento xml!

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

---

## Comunque credo di aver compreso il codice che mi ha aiutato a scrivere.