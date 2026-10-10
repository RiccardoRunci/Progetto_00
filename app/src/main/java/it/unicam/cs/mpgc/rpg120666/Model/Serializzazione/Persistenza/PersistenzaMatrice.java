/**Classe PersistenzaMatrice:
 * Questa classe si occupa di serializzare e deserializzare la matrice gioco tramite l'utilizzo di oggetti ObjectInputStream
 * & ObjectOutputStream.
 * Questa è una classe final, perché non voglio istanziare un oggetto, non credo che mi serva. Mi servono solo
 * i metodi carica e salva. In stile Math.sqrt!
 * Questi oggetti permettono la serializzazione/deserializzazione tramite stream di byte. Utilizzano i loro "antenati"
 * FileInputStream e FileOutputStream (con i relativi loro buffer, BufferedInputStream & BufferedOutputStream per "velocizzare"
 * il lavoro).
 * Il file utilizzato per serializzare la Matrice sarà: "./MatriceSalvata.dat".
 * Possiamo vedere la sua URL nel campo final URL della classe.
 * @author Runci Riccardo.
 * @since Versione 2.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;

import java.io.*;
public final class PersistenzaMatrice
{
    private static final String URL ="./MatriceSalvata";
    /**Metodo serializzaMatrice:
     * Questo è il metodo che salva la matrice nel file .dat.
     * @param matrice da salvare!
     * @return true se è riuscito a salvare la matrice, false altrimenti.
     * Utilizza gli oggetti della classe ObjectOutputStream, i quali richiedono:
     * gli oggetti "base" della serializzazione tramite stream di byte = FileOutputStream
     * e i relativi buffer = BufferedOutputStream!
     * @author Runci Riccardo.
     * @since Versione 2.0.*/
    public static boolean serializzaMatrice(ElementiPerMatrice[][] matrice)
    {
        if(matrice==null || matrice.length==0)
        {
            return false;
        }
        try
        {
            int cont = ContatorePersistenzaMatrice.leggiNumeroEAumenta();
            FileOutputStream fos = new FileOutputStream(URL+"_"+cont+".dat"); //FileOutputStream "base" stream di byte!
            BufferedOutputStream bos = new BufferedOutputStream(fos); //buffer applicati all'oggetto sopra!
            ObjectOutputStream oos = new ObjectOutputStream(bos); //il nostro oggetto che salva la matrice!

            oos.writeObject(matrice); //.writeObject(...) accetta anche l'intera matrice!

            oos.close();
            return true;
        }
        catch(IOException e) { e.printStackTrace(); return false;}
    }
    /**Metodo deserializzaMatrice:
     * @param URL l'url per caricare la matrice!
     * @return matrice la matrice caricata dal file .dat.
     * @throws IOException se c'è qualche problema con il file .dat.
     * @throws ClassNotFoundException se c'è qualche altro problema.
     * @throws IllegalArgumentException se passiamo un'URL non valida (quando carichiamo una matrice salvata)!
     * Utilizza gli oggetti ObjectInputStream, che a sua volta richiede l'oggetto base FileInputStream,
     * con il suo relativo BufferInputStream per fare prima!*/
    public static ElementiPerMatrice[][] deserializzaMatrice(String URL)
    {
        if(URL==null)
            throw new IllegalArgumentException ("L'url, il file passato al metodo:\n" +
                    "deserializzaMatrice, della classe PersistenzaMatrice non è valido!\n");

        ElementiPerMatrice[][] matrice = null;
        try
        {
            FileInputStream fis = new FileInputStream(URL);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            /*readObject restituisce un Object, devo fare il cast e fortunatamente posso
            * farlo di tutta la matrice!*/
            matrice=(ElementiPerMatrice[][]) ois.readObject();

            ois.close();
        }
        catch(IOException | ClassNotFoundException e) {e.printStackTrace();}
        /*Questo | sopra me l'ha suggerito l'ide intellij*/

        if(matrice!=null)
            stampaMatriceCaricata(matrice);
        else
            return null;

        return matrice;
    }
    /** Metodo che mi permette di visualizzare la matrice che ho caricato dal file.
     * L'ho deprecato perché è giusto NON FARLO, ma mi serviva per vedere se avevo combinato qualche casino.*/
    @Deprecated
    private static void stampaMatriceCaricata(ElementiPerMatrice[][] matrice)
    {
        ElementiPerMatrice element;
        System.out.println("COSA HO TIRATO FUORI DAL FILE .DAT:\n");
        for(int riga=0;riga<matrice.length;riga++)
        {
            for (int colonna = 0; colonna < matrice.length; colonna++)
            {
                element= matrice[riga][colonna];
                if (element==null)
                {
                    //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Vuota\t");
                    System.out.print("-\t");
                }
                else
                {
                    if(element instanceof Mago)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Nemico\t");
                        System.out.print("M\t");
                    if(element instanceof Guerriero)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Nemico\t");
                        System.out.print("G\t");
                    else if(element instanceof Arma)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Oggetto\t");
                        System.out.print("A\t");
                    else if(element instanceof Medikit)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Oggetto\t");
                        System.out.print("+\t");
                    else if (element instanceof Eroe)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Eroe\t");
                        System.out.print("E\t");
                }
            }
            System.out.println(""); //accapo!
        }
        System.out.println("La matrice ha dimensioni: "+matrice.length);
    }
}