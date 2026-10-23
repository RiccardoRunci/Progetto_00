package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXMLOggetti_DOM;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXmlMaghi_SAX;

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





    }
}
