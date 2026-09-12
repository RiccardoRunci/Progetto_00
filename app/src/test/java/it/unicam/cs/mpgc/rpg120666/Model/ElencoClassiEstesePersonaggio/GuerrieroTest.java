package it.unicam.cs.mpgc.rpg120666.Model.ElencoClassiEstesePersonaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuerrieroTest
{
    Guerriero Gue1;
    Guerriero Gue2;

    /*Questo test annotato con BeforeEach significa che viene eseguito per primo di tutti i test*/
    @BeforeEach
    void setUp()
    {
        /*Questo metodo setUp è un metodo che non può essere modificato, nel senso che si deve chiamare così e
         * non si può cambiare ne mettendo paremetri ne cambiando valore di ritorno. Non deve nemmeno essere static.*/
        Gue1 = new Guerriero("Spartaco",100 ,50 );
        Gue2 = new Guerriero("Gannicus", 100, 50);
    }
    /*Questo viene fatto DOPO tutti i test di questa classe e deve essere static*/
    @AfterAll
    static void afterAllTests()
    {
        System.out.println(">> Tutti i test di 'Guerriero' sono stati eseguiti.");
    }

    /*tutti i metodi di test devono avere l'annotazione Test per funzionare!*/
    @Test
    public void testGetNome()
    {
        assertEquals("Spartaco", Gue1.getNomeNemico());
    }

    @Test public void testSetNome()
    {
        Gue1.setNomeNemico("Agamennone");
        assertEquals("Agamennone", Gue1.getNomeNemico());
    }

    @Test
    void testToString()
    {
        String s = Gue1.toString();
        assertTrue(s.contains("Spartaco"));
        assertTrue(s.contains("100"));
        assertTrue(s.contains("50"));
    }

    @Test
    void testEquals1()
    {
        assertTrue(Gue1.equals(Gue1)); //L'ide mi dice che potevo scrivere: assertEquals
        assertFalse(Gue2.equals(Gue1));// o assertNotEquals! OK! è grave come errore?
        assertFalse(Gue2.equals(Gue1));
        assertTrue(Gue2.equals(Gue2));
        Gue2.setNomeNemico("Spartaco");
        assertTrue(Gue2.equals(Gue1));

        Gue1.setPuntiVitaNemico(79);
        Gue2.setPuntiVitaNemico(79);
        assertTrue(Gue2.equals(Gue1));


        Gue1.setPuntiScudo(10);
        Gue2.setPuntiScudo(10);
        assertTrue(Gue2.equals(Gue1));
        assertTrue(Gue1.equals(Gue2));
    }
    @Test
    void testIllegalArgumentException()
    {
        Gue1.setPuntiVitaNemico(0);
        Gue1.setPuntiScudo(0);
        assertThrows(IllegalArgumentException.class, () -> {
           Gue1.setNomeNemico("");
           Gue1.setPuntiVitaNemico(-1);
           Gue1.setPuntiScudo(-1);
        });
    }
   @Test
    void testNUM2GeneraleSaluteGuerriero()
    {
        Gue2.setPuntiVitaNemico(10);
        Gue1.eseguiAttaccoFisico(Gue2);
        assertFalse(Gue2.controllaSeNemicoInVita());
    }
    @Test
    void testNUM3GeneraleSaluteGuerriero()
    {
        Gue2.setPuntiVitaNemico(20);
        Gue1.eseguiAttaccoFisico(Gue2);
        assertTrue(Gue2.controllaSeNemicoInVita());
        assertTrue(Gue2.getPuntiVitaNemico() == 10);
        Gue1.eseguiAttaccoFisico(Gue2);
        assertFalse(Gue2.controllaSeNemicoInVita());
    }
}
