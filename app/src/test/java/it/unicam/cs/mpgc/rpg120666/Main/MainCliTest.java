package it.unicam.cs.mpgc.rpg120666.Main;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainCliTest
{
    @Test
    public void appHasAGreeting()
    {
        MainCli classUnderTest = new MainCli();
        assertNotNull("app should have a greeting", classUnderTest.saluta());
    }

    @Test
    public void testGeneralePrendiUsaOggetto()
    {
        Eroe eroe1 = new Eroe("Riccardo",100);

        Oggetto medikit1 = new Medikit("PFZIER",100);
//        eroe1.prendiOggetto(medikit1);
//        eroe1.usaOggetto(medikit1);
//        assertTrue(eroe1.getPuntiVitaPersonaggio()==200);

//
//        Oggetto arma1 = new Arma("Spada",800);
//        eroe1.prendiOggetto(arma1);
//        eroe1.usaOggetto(arma1);
//        assertTrue(eroe1.getDannoCausato()==810);
    }

}
