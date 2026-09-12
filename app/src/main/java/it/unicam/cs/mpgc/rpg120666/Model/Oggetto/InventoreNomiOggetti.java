package it.unicam.cs.mpgc.rpg120666.Model.Oggetto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public  abstract class InventoreNomiOggetti
{        /**Metodo statico, utile a estrarre dai file .txt (localizzati nella cartella statica di gradle, resources)
         * un singolo nome. Che sia presente nell' enumerazione 'ListaOggetti'.
         * @param tipoOggetto = GUERRIERO, MAGO, Ecc... Sono le opzioni dell'enumerazione;
         * @return listaNomi.get(n_caso).toUpperCase() = il nome letto scritto in MAIUSCOLO, oppure "ERRORE" se è andato qualcosa storto;
         * @throws RuntimeException, IOException. * */
        public static String ritornaNomeACaso(EnumListaOggettiGioco tipoOggetto) /*throws Exception*/
        {
            ArrayList<String> listaNomi = new ArrayList<>();
            Random rand = new Random();
            InputStream is = it.unicam.cs.mpgc.rpg120666.Model.Nemico.InventoreNomiNemici.class.getClassLoader() .getResourceAsStream("NOMI_"+tipoOggetto+".txt");
            if (is == null)
            {
                throw new RuntimeException("Il file: Nomi"+tipoOggetto+".txt \n" +
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
