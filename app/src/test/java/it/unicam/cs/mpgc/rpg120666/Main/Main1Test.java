package it.unicam.cs.mpgc.rpg120666.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Main1Test
{
    @Test
    public void appHasAGreeting()
    {
        Main1 classUnderTest = new Main1();
        assertNotNull("app should have a greeting", classUnderTest.saluta());
    }
}
