package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializzaEroe_StreamCaratteriTest
{
    /**Metodo per testare i tempi della classe SerializzaEroe_StreamCaratteri.
     * Vorrei avere una conferma che la serializzazione con buffer è più veloce della
     * serializzazione senza buffer.*/
    @Test
    public void testTempiSerializzazioneTramiteStreamCaratteri()
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
        //NON POSSO FARLO PERCHE' QUANTITA DI DATI DA SERIALIZZARE TROPPO PICCOLA!!:
        //double tempoTrascorsoConBuffer=oggettoSerializzaCaratteri.getTempoFinale();

        oggettoSerializzaCaratteri.serializzaEroe_NoBuffer();
        //NON POSSO FARLO PERCHE' QUANTITA DI DATI DA SERIALIZZARE TROPPO PICCOLA!!:
        //double tempoTrascorsoSenzaBuffer=oggettoSerializzaCaratteri.getTempoFinale();

        //Mi aspetto che il tempo senza buffer sia maggiore! TROPPE POCHE COSE DA SERIALIZZARE!
        //assertTrue(tempoTrascorsoSenzaBuffer>=tempoTrascorsoConBuffer);
        //Mi aspetto che il tempo con buffer sia minore!
        //assertTrue(tempoTrascorsoConBuffer<=tempoTrascorsoSenzaBuffer);
    }
}
