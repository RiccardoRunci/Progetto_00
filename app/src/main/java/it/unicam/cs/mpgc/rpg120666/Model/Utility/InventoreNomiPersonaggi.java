package it.unicam.cs.mpgc.rpg120666.Model.Utility;
import java.io.*;
import java.util.Set;
import java.util.HashSet;
public class InventoreNomiPersonaggi
{
    private Set<String> listaNomi = new HashSet<String>();
    //Costruttore vuoto:
    public InventoreNomiPersonaggi() { }
    /*Costruttore che cerca se nelle directory del progetto è
     *presente un file.txt da dove prelevare qualche nome già salvato!*/
    public InventoreNomiPersonaggi(EnumListaPersonaggiGioco tipoPersonaggio)
    {
        if(tipoPersonaggio == null) { throw new IllegalArgumentException("Tipo personaggio non valido."); }
        else
        {
            switch (tipoPersonaggio)
            {
                case GUERRIERO, MAGO ->
                {
                    try
                    {
                        System.out.println("HAI SCELTO DI CREARE I PRESONAGGI DI:"+tipoPersonaggio);
                        this.caricaListaNomi(tipoPersonaggio);
                    }
                    catch (Exception e){ throw new RuntimeException(e); }
                }
                default -> { /*Non dovrebbe mai accadere*/ }
            }
        }
    }
    /**Metodo Set,
     * @return l'interfaccia Set<Stringhe> listaNomi.
     * ATTENZIONE, ritorna l'interfaccia, NON HashSet!*/
    public Set<String> getListaNomi() {return this.listaNomi; } //sinceramente non so perché l'ho fatto!
    //todo MOMENTANEAMENTE NON FACCIO IL METODO get!
    private void caricaListaNomi(EnumListaPersonaggiGioco tipoPersonaggio) throws Exception
    {
        InputStream is = getClass() .getClassLoader() .getResourceAsStream("NOMI_"+tipoPersonaggio+".txt");
            //System.out.println("CHE COSA E' IS:"+is.toString());
        if (is == null)
        {
            throw new RuntimeException("Il file: Nomi"+tipoPersonaggio+".txt \n" +
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
                    System.out.println("LINEA LETTA DAL FILE: "+nome);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
