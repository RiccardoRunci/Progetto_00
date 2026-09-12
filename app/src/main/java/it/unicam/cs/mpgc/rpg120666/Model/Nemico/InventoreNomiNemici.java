/**Classe abstract che legge dei file txt (salvati dentro la cartella resources di gradle).
 * Questi file hanno alcuni nomi random dei vari personaggi (Guerrieri, Maghi, ecc...).
 *
 * Se si vuole creare un file con all'interno dei nomi per dei nuovi personaggi che compariranno nel gioco,
 * si deve creare un file .txt chiamato "NOMI_CategoriaPersonaggi" e salvarlo dentro la cartella
 * "Cartella_Radice/app/src/main/resources". Successivamente bisogna fare "gradle build" e gradle farà una copia
 * del file dentro la cartella "Cartella_Radice/app/build/resourcers". Questa è la cartella fatta apposta per i file statici del gioco.
 * Poi bisogna creare una nuova opzione della categoria CategoriaPersonaggi creata nell'enumerazione "EnumListaPersonaggiGioco".
 *
 * ESEMPIO: Voglio creare il file txt per la categoria ROBOT:
 * Come prima cosa aggiungo "ROBOT" nell'enumerazione "EnumListaPersonaggiGioco".
 * Poi creo il file "NOMI_ROBOT.txt" (nella cartella "Cartella_Radice/app/src/main/resources")
 * e lo riempo con i nomi separati da un \n, tipo:
 * Skynet
 * MrRoboto
 * C3PO
 * Gundam
 * Bimby
 *
 * Infine faccio "gradle build", gradle farà una copia del mio file dentro "Cartella_Radice/app/build/resourcers".
 *
 * @author Runci Riccardo.
 * @since versione 0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class InventoreNomiNemici
{
 /**Metodo statico, utile a estrarre dai file .txt (localizzati nella cartella statica di gradle, resources)
 * un singolo nome. Che sia presente nell' enumerazione 'tipoNemico'.
 * @param tipoNemico = GUERRIERO, MAGO, Ecc... Sono le opzioni dell'enumerazione;
 * @return listaNomi.get(n_caso).toUpperCase() = il nome letto scritto in MAIUSCOLO, oppure "ERRORE" se è andato qualcosa storto;
 * @throws RuntimeException, IOException. * */
    public static String ritornaNomeACaso(EnumListaNemiciGioco tipoNemico) /*throws Exception*/
    {
        ArrayList<String> listaNomi = new ArrayList<>();
        Random rand = new Random();
        InputStream is = InventoreNomiNemici.class.getClassLoader() .getResourceAsStream("NOMI_"+tipoNemico+".txt");
        if (is == null)
        {
            throw new RuntimeException("Il file: Nomi"+tipoNemico+".txt \n" +
                    "Non trovato nella mitica cartella Resources di gradle!\n" +
                    "Quella dei file STATICI (utili alle configurazioni del gioco)! \n" +
                    "Quella dove tu devi copiare il file dentro: 'Cartella_Radice/app/src/main/resources'!\n, " +
                    "Poi fai 'gradle build' e Trovi una copia del tuo file dentro: \n" +
                    "'Cartella_Radice/app/build/resourcers'!\n");
        }                                               //DIVERSAMENTE DAGLI APPUNTI!
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is)))
        {                      //FILE READER vuole una path stabile e sempre quella del file system!
            String nome;       //InputStream prende la cartella di gradle (che cambia da pc a pc)!
            while ((nome = reader.readLine()) != null) //InputStream usa il classpath di java!
            {                  //ossia il posto dove java mette tutto quello che serve per far eseguire il programma!
                if(nome!=null && !nome.isEmpty())
                {
                    //System.out.println("LINEA LETTA DAL FILE: " + nome);
                    listaNomi.add(nome);
                }
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        // Se la lista non è vuota, allora genera un numero a caso tra [0, ... , array.size()]
        // Non va fuori dallo stack [0, ... , Array.size()-1 GIA LO FA, senza -1]
        if((!listaNomi.isEmpty()))
        {
            int n_caso = rand.nextInt(listaNomi.size());
            return listaNomi.get(n_caso).toUpperCase();
        }
        else
            return "ERRORE!";
    }
}
