package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ZainoTest
{
    @Test
    public void testZainoConMedikit()
    {
        Eroe eroe1 = new Eroe("Riccardo",100);
        assertTrue(eroe1.controllaSeVivo());
        Oggetto medikit1 = new Medikit("PFZIER",100);

        assertFalse(medikit1 == null);
        eroe1.prendiOggetto(medikit1);
        //Ora si dovrebbe vedere che in zaino si trova un medikit.
        assertTrue(eroe1.getZaino().controllaPresenzaOggetto(medikit1));
        eroe1.usaOggetto(medikit1);  //medikit1.usaOggetto(eroe1); questo anche andava bene!
        //Ora si dovrebbe vedere che in zaino NON C'E' + Il medikit
        assertFalse(eroe1.getZaino().controllaPresenzaOggetto(medikit1));
    }
    @Test
    public void testQuantitaZainoConMedikit()
    {
        Eroe eroe1 = new Eroe("Riccardo",100);
        Oggetto medikit1 = new Medikit("PFZIER",100);
        eroe1.prendiOggetto(medikit1);
        assertTrue(eroe1.getZaino().controllaPresenzaOggetto(medikit1));
        assertTrue(eroe1.getZaino().getOggettiPosseduti().size()==1);
        eroe1.usaOggetto(medikit1); //medikit1.usaOggetto(eroe1); questo anche andava bene!
        assertTrue(eroe1.getZaino().getOggettiPosseduti().size()==0);
        eroe1.prendiOggetto(medikit1);
        eroe1.prendiOggetto(medikit1);
        assertTrue(eroe1.getZaino().getOggettiPosseduti().size()==1);
        //int quantitaMedikit = eroe1.getZaino().getOggettiPosseduti().get(medikit1);
        assertTrue(eroe1.getZaino().getOggettiPosseduti().get(medikit1)==2);
    }
    @Test
    public void testGeneralePrendiUsaOggettoMedikit()
    {
        Eroe eroe1 = new Eroe("Riccardo", 100);
        Oggetto medikit1 = new Medikit("PFZIER", 100);
        eroe1.prendiOggetto(medikit1);
        eroe1.usaOggetto(medikit1); //medikit1.usaOggetto(eroe1); questo anche andava bene!
        assertTrue(eroe1.getPuntiVitaPersonaggio() == 200);
    }

    @Test
    public void testGeneralePrendiUsaOggettoArma()
    {
        Eroe eroe1 = new Eroe("Riccardo", 100);
        Oggetto arma1 = new Arma("Spada",800);
        eroe1.prendiOggetto(arma1);
        eroe1.usaOggetto(arma1);
        assertTrue(eroe1.getDannoCausato()==810);
    }
    @Test
    public void testQuantitaZainoConArma()
    {
        Eroe eroe1 = new Eroe("Riccardo",100);
        Oggetto arma1 = new Arma("Spada",100);
        eroe1.prendiOggetto(arma1);
        assertTrue(eroe1.getZaino().controllaPresenzaOggetto(arma1));
        assertTrue(eroe1.getZaino().getOggettiPosseduti().size()==1);
        eroe1.usaOggetto(arma1); //medikit1.usaOggetto(eroe1); questo anche andava bene!
        assertTrue(eroe1.getZaino().getOggettiPosseduti().size()==0);
        eroe1.prendiOggetto(arma1);
        eroe1.prendiOggetto(arma1);
        assertTrue(eroe1.getZaino().getOggettiPosseduti().size()==1);
        //int quantitaMedikit = eroe1.getZaino().getOggettiPosseduti().get(arma1);
        assertTrue(eroe1.getZaino().getOggettiPosseduti().get(arma1)==2);
    }
}
