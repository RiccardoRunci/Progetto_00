package it.unicam.cs.mpgc.rpg120666.Main;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.EnumListaOggettiGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.InventoreNomiOggetti;

public class MainProvaClasseInvetoreNomiOggetti
{
    public static void main(String[] args)
    {
        //InventoreNomiPersonaggi inventore = new InventoreNomiPersonaggi(GUERRIERO);
        //InventoreNomiPersonaggi inventore2 = new InventoreNomiPersonaggi(MAGO);
        try
        {
           System.out.println("NOME A CASO DI Medikit: "+ InventoreNomiOggetti.ritornaNomeACaso(EnumListaOggettiGioco.MEDIKIT));
        } catch (Exception e) { throw new RuntimeException(e); }

        try
        {
            System.out.println("NOME A CASO DI Arma: "+ InventoreNomiOggetti.ritornaNomeACaso(EnumListaOggettiGioco.ARMA));
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
