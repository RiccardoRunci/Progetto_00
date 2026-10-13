package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiJsonGuerrieri;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.PersistenzaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_BYTE.DeserializzaEroe_StreamByte;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_BYTE.SerializzaEroe_StreamByte;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_CARATTERI.DeserializzaEroe_StreamCaratteri;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Ser_Des_STREAM_CARATTERI.SerializzaEroe_StreamCaratteri;

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

    }
}
