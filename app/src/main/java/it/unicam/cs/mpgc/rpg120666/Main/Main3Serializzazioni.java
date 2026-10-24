package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.Statistica;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.UsaStatistiche;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXMLOggetti_DOM;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXmlMaghi_SAX;

import java.util.ArrayList;

public class Main3Serializzazioni
{
    public static void main(String[] args)
    {
        //0 Creo l'EROE!
        //Eroe IlNostroEroe = new Eroe("RICCARDO",50);
        // 1) MODEL
        //MatriceGioco model = new MatriceGioco(EnumDifficolta.MEDIA,IlNostroEroe);
        /*I POPOLAMENTI VENGONO ADESSO FATTI NEL COSTRUTTORE DI MatriceGioco!
         *PopolaMatriceOggetti popolaOggettiInMatrice= new PopolaMatriceOggetti(model);
         *PopolaMatriceNemici popolaNemiciInMatrice = new PopolaMatriceNemici(model);
         *PopolaMatriceEroe popEroeInMatrice = new PopolaMatriceEroe(model,IlNostroEroe);*/
        /*Matrice già popolata perchè nel costruttore di MatriceGioco creo gli oggetti PopolaMatrice...*/

        //PersistenzaMatrice.serializzaMatrice(model.getMatriceElementi());

        //PersistenzaMatrice.deserializzaMatrice();

        //LeggiJsonGuerrieri leggi=new LeggiJsonGuerrieri();

        //LeggiXmlMaghi_SAX vai = new LeggiXmlMaghi_SAX();
        //LeggiXMLOggetti_DOM Ogg=new LeggiXMLOggetti_DOM();

        UsaStatistiche.inserisciStatistica("Riccardo",100,20);
        UsaStatistiche.inserisciStatistica("Riccardo",30,20);
        UsaStatistiche.inserisciStatistica("Riccardo",79,20);
        UsaStatistiche.inserisciStatistica("Riccardo",100,20);
        ArrayList<Statistica> bravo=new ArrayList<Statistica>(UsaStatistiche.ritornaTutteRighe());


        for(int i=0;i<bravo.size();i++)
        {
            System.out.println("ID: "+bravo.get(i).getId());
            System.out.println("Nome giocatore: "+bravo.get(i).getNomeGiocatore());
            System.out.println("data: "+bravo.get(i).getDataGioco());
            System.out.println("Nemici rimasti: "+bravo.get(i).getNemiciRimasti());
            System.out.println("Oggetti rimasti: "+bravo.get(i).getOggettiRimasti());
        }
        UsaStatistiche.chiudiEMF();


    }
}
