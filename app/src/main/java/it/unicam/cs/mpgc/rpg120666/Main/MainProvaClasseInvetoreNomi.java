package it.unicam.cs.mpgc.rpg120666.Main;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.InventoreNomiPersonaggi;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumListaPersonaggiGioco.*;

public class MainProvaClasseInvetoreNomi
{
    public static void main(String[] args)
    {
        //InventoreNomiPersonaggi inventore = new InventoreNomiPersonaggi(GUERRIERO);
        //InventoreNomiPersonaggi inventore2 = new InventoreNomiPersonaggi(MAGO);
        try
        {
           System.out.println("NOME A CASO DI GUERRIERO: "+ InventoreNomiPersonaggi.ritornaNomeACaso(GUERRIERO));
        } catch (Exception e) { throw new RuntimeException(e); }

        try
        {
            System.out.println("NOME A CASO DI MAGO: "+ InventoreNomiPersonaggi.ritornaNomeACaso(MAGO));
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
