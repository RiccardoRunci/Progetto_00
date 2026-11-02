# 📌 Nome del Progetto

Progetto molto didattico di un gioco rpg.
Devi muoverti in una matrice di gioco infestata di Nemici e Oggetti.
Il tuo obbiettivo è quello di eliminare tutti i Nemici.
Per farlo dovrai per forza raccogliere almeno qualche Oggetto!

---

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle

### Istruzioni

```bash
git clone https://github.com/RiccardoRunci/Progetto_00.git
cd Progetto_00
```

### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run

```cmd di windows
gradlew.bat run

```powershell di windows
./gradlew.bat run

```

```NOTE:
1)Se si esegue su un terminale cmd di windows, prima di eseguire il programma, digitare "chcp 65001".
  Serve per dire al cmd di usare la codifica UTF-8. Perchè il terminale di intellij salta questa cosa.
  Anche se l'ide intellij è impostato su utf8, non funziona. Continua a fare segni strani.
  Se il comando viene digitato nel terminale di intellij (indifferentemente se è selezionato "Windows powershell" o "local")
  comunque non funziona.
  
2)Se si esegue in un terminale powershell (a parte, non quello compreso in intellij) Puoi usare anche "chcp 65001",
  Non funziona uguale.
  
3)ALLA FINE NON FUNZIONAVA UGUALMENTE, QUINDI HO TOLTO TUTTI I CARATTERI ACCENTENTATI DAI System.out.println.
```

---

## 🤖 Uso di strumenti di AI
Nella wiki ho scritto un breve recap del funzionamento dei punti dal 1 al 7.
Il codice contiene molti commenti che ne possono spiegare il funzionamento.
Nel progetto ho usato Copilot per aiutarmi nei seguenti casi:

**1**)Nella parte grafica mi ha aiutato a capire come funziona il componente "TableView".
Non tanto per la creazione dell'Oggetto ma per collegargli il suo click listener.
Questo si può vedere nella classe: /Controller/ZainoController, nel metodo: collegaClickAllaTabella().

**2**)Nella parte grafica mi sono fatto aiutare aggiungendo una variabile di tipo
interfaccia CellaClickListener, per far funzionare i click del mouse della matrice di Label.
Questo si può vedere: nella classe /View/MatriceView, nell'interfaccia /View/CellaClickListener,
e nel metodo costruisciMatrice (della classe MatriceView).

**3**)Nella classe /Model/Utility/VerificaParametri mi ha aiutato lavorando con le Enumerazioni.
Mi ha creato l'enumerazione CreazioneAggiornamento che mi serviva per accorpare dei metodi
che avevano quasi lo stesso codice. Una volta capito il procedimento l'ho fatto mio e replicato molte altre
volte (alcune rimosse nel corso dei commit) durante il progetto.
Questo si può vedere: Nella classe /Model/Utility/VerificaParametri, e nell'interfaccia: /Model/Utility/CreazioneAggiornamento.

**4**)Mi sono fatto aiutare nella classe che usa Hibernate (Model/Serializzazione/Persistenza/Hibernate/UsaPunteggi).
Nel metodo: ritornaTutteRighe(). Perchè volevo vedere come si usavano le "normali" query SQL.
Non mi trovavo con gli appunti, avevo solo "query" in stile:
EntityManager.persist(Oggetto); + EntityManager.getTransaction().commit().
Alla fine ho creato un altro metodo (cancellaTutteRighe()) sulla falsa riga di "ritornaTutteRighe()",
dove ho ricalcato il concetto di spiegatomi da Copilot.

**5**)Mi sono fatto fare le classi Factory (una delle 2 che ho messo nel progetto,
l'altra l'ho ricopiata nella falsa	riga della precedente).
Queste classi mi servivano per dare dei: Nomi, puntiVita, ecc... Per i Personaggi e Oggetti disposti nella MatriceGioco.
Questo si può vedere: nella classe /Model/Oggetto/ClassiEstese/FactoryOggetti e
/Model/Nemico/ClassiEstese/FactoryNemici.
Queste 2 classi vengono usate nelle classi /Model/MatriceGioco/Popolamento/PopolaMatriceNemici & PopolaMatriceOggetti.

**6**)Mi sono fatto aiutare con gli Oggetti Alert, JRadioButton di Java Fx.

**7**)Mi sono fatto aiutare per la stesura della classe che usa il parser DOM per i file xml,
nella classe: /Model/Serializzazione/Persistenza/LeggiXMLOggetti_DOM.

**8**)Frequentemente gli ho domandato aiuto per trovare metodi delle classi che mi servivano
durante il progetto che fatico a ricordare. Esempi: metodi per prendere chiavi e valori delle hash,
metodi già implementati nelle classi Collection in generale.   

**9**)Gli ho anche chiesto che cosa erano i file .md e quali fossero i loro tag per formattare un
minimo decentemente il testo per scrivere questo file e la wiki.
---




