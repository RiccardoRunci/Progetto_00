/**Questo classe mi serve per creare un contatore da anteporre al nome del file .dat.
 * Che viene usato nella classe PersistenzaMatrice.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.PersistenzaMatrice
 *
 * Ad esempio: vorrei avere la possibilità di salvare più volte la matrice, non sovrascrivendo sempre
 * lo stesso file ma creando un file ./MatriceSalvata1.dat, ./MatriceSalvata2.dat, ecc...
 * Quindi avevo pensato di passare (alla serializzazione della matrice nella classe PersistenzaMatrice)
 * l'intero che mi restituiva un metodo di questa classe.
 *
 * Questo intero è salvato dentro il file indicato dal campo final "URL_CONTATORE".
 *
 * Questa classe è final perché non penso di aver bisogno di istanziare un oggetto di questa classe, mi
 * serve solo per leggere e scrivere un numero su un file.txt.
 *
 * @implNote Questa classe utilizza la persistenza "base" ossia quella che usa gli stream di caratteri.
 *           Ho messo anche i buffer, ma per piccole quantità di dati serializzati/deserializzati abbiamo visto
 *           in classe che non c'è un guadagno di velocità ma al contrario (per piccole quantità di dati,
 *           FileReader & FileWriter, senza buffer, fanno prima).
 * @author Runci Riccardo.
 * @since versione 2.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza;
import java.io.*;
public final class ContatorePersistenzaMatrice
{
    /*URL DEL FILE DEL CONTATORE: */
    private static final String URL_CONTATORE ="./Contatore.txt";
    /**Metodo scriviNumero:
     * Questo metodo serializza un numero dentro il file .txt sopra.
     * Mi serve perché poi verrà preso dal metodo sotto, aumentato e restituito!
     * @author Runci Riccardo.
     * @since versione 2.0.*/
    public static void scriviNumero(int contatore)
    {
        /*Scrive il contatore!*/
        try
        {
            FileWriter fw = new FileWriter(URL_CONTATORE);
            BufferedWriter bw = new BufferedWriter(fw);
            /*Bisogna mettere valueOf perché .write scrive unicode.
            * Con String.valueOf(numero) si converte in stringa il numero ma si lascia sempre lo stesso "numero".*/
            bw.write(String.valueOf(contatore));
            bw.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }
    /**Metodo leggiNumeroEAumenta:
     * Metodo che mi legge, incrementa e restituisce il numero presente nel file .txt.
     * @author Runci Riccardo.
     * @since versione 2.0.*/
    public static int leggiNumeroEAumenta()
    {
        int contatore=0;
        /*Legge il contatore:*/
        try
        {
            FileReader fr;
            fr=new FileReader(URL_CONTATORE);
            BufferedReader br=new BufferedReader(fr);
            /*br.readLine restituisce una stringa, devo fare il cast a un intero!
            * Ma non va bene farlo solo con (int), bisogna usare Integer.parseInt(...)
            * ParseInt converte quella stringa passata in un vero intero!
            * Java non può fare il cast (int) di una Stinga!*/
            contatore= Integer.parseInt(br.readLine());

            br.close();
        }
        catch (IOException e) {e.printStackTrace();}

        contatore++;

        scriviNumero(contatore);
        return contatore;
    }

}
