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

}
