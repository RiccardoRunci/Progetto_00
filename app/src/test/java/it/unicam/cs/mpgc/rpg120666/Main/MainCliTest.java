package it.unicam.cs.mpgc.rpg120666.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainCliTest
{
    @Test
    public void appHasAGreeting()
    {
        MainCli classUnderTest = new MainCli();
        assertNotNull("app should have a greeting", classUnderTest.saluta());
    }
}
