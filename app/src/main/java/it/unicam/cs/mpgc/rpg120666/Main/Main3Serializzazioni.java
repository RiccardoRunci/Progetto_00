package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.Punteggi;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.UsaPunteggi;

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

        UsaPunteggi.inserisciPunteggio("Riccardo",100,20);
        UsaPunteggi.inserisciPunteggio("Riccardo",30,20);
        UsaPunteggi.inserisciPunteggio("Riccardo",79,20);
        UsaPunteggi.inserisciPunteggio("Riccardo",100,20);
        ArrayList<Punteggi> bravo=new ArrayList<Punteggi>(UsaPunteggi.ritornaTutteRighe());


        for(int i=0;i<bravo.size();i++)
        {
            System.out.println("ID: "+bravo.get(i).getId());
            System.out.println("Nome giocatore: "+bravo.get(i).getNomeGiocatore());
            System.out.println("data: "+bravo.get(i).getDataGioco());
            System.out.println("Nemici rimasti: "+bravo.get(i).getNemiciRimasti());
            System.out.println("Oggetti rimasti: "+bravo.get(i).getOggettiRimasti());
        }
        UsaPunteggi.chiudiEMF();


    }
}
