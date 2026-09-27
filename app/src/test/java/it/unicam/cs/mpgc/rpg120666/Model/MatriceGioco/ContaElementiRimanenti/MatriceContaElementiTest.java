package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatriceContaElementiTest
{
    /* VECCHIO MAIN:
     * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.EROE);
     * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA);
     * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT);
     * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO);
     * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO);*/

    @Test
    public void testDiffFacile()
    {
        this.testContaElementiDiffPassata(EnumDifficolta.FACILE);
    }

    @Test
    public void testDiffMedia()
    {
        this.testContaElementiDiffPassata(EnumDifficolta.MEDIA);
    }

    @Test
    public void testDiffDifficile()
    {
        this.testContaElementiDiffPassata(EnumDifficolta.DIFFICILE);
    }

    private void testContaElementiDiffPassata(EnumDifficolta enumDifficolta)
    {
        Eroe IlNostroEroe = new Eroe("RICCARDO",50);
        MatriceGioco model = new MatriceGioco(enumDifficolta,IlNostroEroe);

        /*Test se l'eroe è uno solo:*/
        assertTrue(model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.EROE)==1);

        /*testo se il numero di nemici totali è == a quello che dice inizialmente la difficoltà:*/
        assertTrue(model.getDifficoltaGioco().getNumNemici() ==
          model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO) +
          model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO));

        /*testo se il numero di oggetti totali è == a quello che dice inizialmente la difficoltà:*/
        assertTrue(model.getDifficoltaGioco().getNumOggetti() ==
        model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA) +
        model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT));
    }

}
