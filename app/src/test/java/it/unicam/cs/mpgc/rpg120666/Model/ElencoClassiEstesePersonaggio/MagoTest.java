package it.unicam.cs.mpgc.rpg120666.Model.ElencoClassiEstesePersonaggio;

import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MagoTest
{
    Mago mag1;
    Mago mag2;
    /*Questo test annotato con BeforeEach significa che viene eseguito per primo di tutti i test*/
    @BeforeEach
    void setUp()
    {
        /*Questo metodo setUp è un metodo che non può essere modificato, nel senso che si deve chiamare così e
         * non si può cambiare ne mettendo paremetri ne cambiando valore di ritorno. Non deve nemmeno essere static.*/
        mag1 = new Mago("Merlino",100 ,50 );
        mag2 = new Mago("Circe", 100, 50);
    }
    /*Questo viene fatto DOPO tutti i test di questa classe e deve essere static*/
    @AfterAll
    static void afterAllTests()
    {

        System.out.println(">> Tutti i test di 'Mago' sono stati eseguiti.");
    }
    @Test
    public void testGetSetNome()
    {
        assertEquals("Merlino", mag1.getNomeNemico());
        mag1.setNomeNemico("Circe");
        assertEquals("Circe", mag1.getNomeNemico());
        mag2.setNomeNemico("Merlino");
        assertEquals("Merlino", mag2.getNomeNemico());
        assertThrows(IllegalArgumentException.class, () -> {
            mag1.setNomeNemico("");
            mag2.setNomeNemico("");
        });
    }
    @Test
    public void testEqualsMago()
    {
        assertEquals(mag1, mag1);
        assertEquals(mag2, mag2);
        mag2.setNomeNemico("Merlino");
        assertEquals(mag1, mag2);
        mag2.setNomeNemico("Circe");
        assertNotEquals(mag1,mag2);

        mag1.setNomeNemico("Circe");
        mag2.setNomeNemico("Circe");

        mag1.setNumeroMagieDisponibili(1);
        assertNotEquals(mag1,mag2);

        mag2.setNumeroMagieDisponibili(1);
        assertEquals(mag1,mag2);

        mag1.setPuntiVitaNemico(10);
        assertNotEquals(mag1,mag2);
        mag2.setPuntiVitaNemico(10);
        assertEquals(mag1,mag2);
    }

    @Test
    public void testVitaMaghiEMagie()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            mag1.setPuntiVitaNemico(-1);
            mag2.setPuntiVitaNemico(-1);
            mag1.setNumeroMagieDisponibili(-1);
        });

        mag1.setNumeroMagieDisponibili(1);
        assertTrue(mag1.getNumeroMagieDisponibili()==1);
        mag2.setNumeroMagieDisponibili(1);
        assertTrue(mag2.getNumeroMagieDisponibili()==1);

        assertTrue(mag1.eseguiAttaccoMagico(mag2));
        assertTrue(mag2.eseguiAttaccoMagico(mag1));

        assertFalse(mag1.eseguiAttaccoMagico(mag2));
        assertFalse(mag2.eseguiAttaccoMagico(mag1));
        assertTrue(mag1.getNumeroMagieDisponibili()==0);
        assertTrue(mag2.getNumeroMagieDisponibili()==0);

        mag1.setPuntiVitaNemico(30);
        mag2.setPuntiVitaNemico(30);
        mag1.setNumeroMagieDisponibili(10);
        mag2.setNumeroMagieDisponibili(10);
        for(int i=0;i<10;i++) assertTrue(mag1.eseguiAttaccoMagico(mag2));
        assertFalse(mag2.controllaSeNemicoInVita());
        assertTrue(mag1.getNumeroMagieDisponibili()==0);

        assertFalse(mag1.eseguiCuraMagica(mag2)); //Non posso resuscitare i morti!
        mag2.setNumeroMagieDisponibili(1);
        assertFalse(mag2.eseguiCuraMagica(mag1)); //i morti non possono fare magie (anche se hanno le magie)!
    }

    @Test
    public void testEseguiCuraMagica()
    {
        Guerriero gue1=new Guerriero("Silla",4,50);
        mag1.eseguiCuraMagica(gue1);
        assertTrue(gue1.getPuntiVitaNemico()==10);
        gue1.setPuntiVitaNemico(2);
        mag1.eseguiAttaccoMagico(gue1);
        assertFalse(gue1.controllaSeNemicoInVita());
    }

    @Test
    void testToString()
    {
        String s = mag1.toString();
        assertTrue(s.contains("Merlino"));
        assertTrue(s.contains("100"));
        assertTrue(s.contains("50"));
        assertFalse(s.contains("Silla"));

        mag1.setPuntiVitaNemico(79);
        String s1 = mag1.toString();
        assertTrue(s1.contains("79"));
    }
}
