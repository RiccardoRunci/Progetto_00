Il Progetto - Ancora senza titolo
(Wiki del Progetto)
--------------------------------------------------------------------------------------------
INDICE:
1. Descrizione del Progetto
2. Software e versioni di Software utilizzate
3. Classi e Responsabilità
4. Interfacce
5. Persistenza dei Dati
6. Estensibilità
7. Principi SOLID & Clean Code
8. Test
9. Dichiarazione Uso AI
--------------------------------------------------------------------------------------------
1)■ DESCRIZIONE DEL PROGETTO ■

Il Progetto è un gioco di ruolo.
Ancora senza titolo e trama.
--------------------------------------------------------------------------------------------
2)■ SOFTWARE E VERSIONI DI SOFTWARE UTILIZZATE ■

Ho Usato i Javadoc per le annotazioni del codice;
Ho usato Gradle come build automation software (versione 9.4.1);
Ho implementato il progetto in Java (java SE version "25.0.2" 2026-01-20 LTS );
Ho Usato la libreria Junit6 per i test (versione6.1.1)
Ho usato la libreria JavaFX per la grafica del gioco (versione 25);
Ho usato la libreria Gson per la serializzazione in formato JSON (versione 2.14);
--------------------------------------------------------------------------------------------
3)■ CLASSI E RESPONSABILITÀ ■

Nome Package "model", contiene le seguenti classi:
NomeClasse (abstract/public/ecc...)
Che cosa fà la classe?
--------------------------------------------------------------------------------------------
4)■ INTERFACCE ■
Il progetto deve seguire il principio ISP con interfacce piccole e mirate.

Nome Package "___", contiene le seguenti Interfacce:
NomeInterfaccia "", che cosa fa!
--------------------------------------------------------------------------------------------
5)■ PERSISTENZA DEI DATI ■
Dati di configurazione (statici)
I dati del gioco sono definiti in file JSON nella cartella src/main/resources/.

Stato di gioco (dinamico)
--------------------------------------------------------------------------------------------
6)■ ESTENSIBILITÀ ■
Aggiungere nuovi personaggi al gioco

Aggiungere nuovi oggetti (posseduti dai personaggi del gioco)

Aggiungere nuovi luoghi

Aggiungere nuove missioni
--------------------------------------------------------------------------------------------
7)■ PRINCIPI SOLID E "CLEAN CODE" ■
Mi sono sforzato di usare i principi SOLID e Clean Code visti durante le lezioni.
Spero di averlo fatto bene, visto che è parte del programma del corso.
--------------------------------------------------------------------------------------------
8)■ TEST ■
Ho deciso di usare la libreria JUnit6 per implementare i test delle classi del gioco.
--------------------------------------------------------------------------------------------
9)■ DICHIARAZIONE USO AI ■
Ho usato molto l'AI  come "collega" di studi (durante il periodo in cui studiavo).
Mi ero successivamente ripromesso di non ri-utilizzarla per il progetto, ma, invece sono incappato nel seguente scenario:
Nella classe che ho chiamato "UtilityPersonaggio" avevo 2 metodi quasi uguali,
l'unica cosa che cambiava era la logica del < o <=.

    public static boolean controllaCreazionePuntiVitaPersonaggio (int puntiVitaPersonaggio)
    {
        if (puntiVitaPersonaggio<=0)
            throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                    "I suoi punti vita: "+puntiVitaPersonaggio+" sono <=0.\"n"+
                    "Non posso creare un personaggio con questi punti vita!");
        else
            return true;
    }

	public static boolean controllaPuntiVitaPersonaggio (int puntiVitaPersonaggio)
    {
        if (puntiVitaPersonaggio<0)
            throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                    "I suoi punti vita: "+puntiVitaPersonaggio+" sono <0.\"n"+
                    "Il personaggio dovrebbe essere già morto!");
        else
            return true;
    }


L'ia mi ha suggerito di creare un enumerazione che specificava se - il controllo della vita del personaggio -
era da fare in fase di creazione o in fase di aggiornamento.
Perchè non posso creare un personaggio con la vita <=0, ma quando muore un personaggio per forza deve avere i suoi punti vita = 0.
E niente: IA 1, Riccardo 0.