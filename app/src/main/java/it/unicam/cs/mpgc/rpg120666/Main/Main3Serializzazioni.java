package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.SerializzaEroe_StreamCaratteri;

public class Main3Serializzazioni
{
    public static void main(String[] args)
    {
        //Creo l'eroe da serializzare e gli oggetti che dovrò mettere nel suo zaino:
        Eroe Riccardo = new Eroe("Riccardo",100);
        Medikit aspirina = new Medikit("Aspirina",50);
        Medikit brufen = new Medikit("Brufen",50);
        Arma pistolaAcqua = new Arma("Pistola Acqua",9000);

        //Metto gli oggetti nel suo zaino:
        Riccardo.getZaino().aggiungiOggettoNelloZaino(aspirina);
        Riccardo.getZaino().aggiungiOggettoNelloZaino(brufen);
        Riccardo.getZaino().aggiungiOggettoNelloZaino(pistolaAcqua);
        SerializzaEroe_StreamCaratteri oggettoSerializzaCaratteri= new SerializzaEroe_StreamCaratteri(Riccardo);

        oggettoSerializzaCaratteri.serializzaEroe_Buffer();
        oggettoSerializzaCaratteri.serializzaEroe_NoBuffer();



    }
}
