/**Classe PopolaMatriceEroe:
 * Questa classe serve per inserire l'oggetto Eroe dentro la matrice di gioco.
 * Qui per semplicità ho impostato che la cella di start (da dove parte il nostro eroe) sarà sempre la cella
 *      in basso a destra (this.matriceGioco.getDimensione()-1,this.matriceGioco.getDimensione()-1).
 *@author Runci Riccardo.
 *@since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;

public class PopolaMatriceEroe
{
    private Eroe ilNostroEroe;
    private MatriceGioco matriceGioco;
    private CellaMatrice cellaStartEroe;

    /**Metodo costruttore:
     * @param matriceGioco è la matrice del gioco (la scacchiera di gioco).
     * @param ilNostroEroe è L'eroe da posizionare.
     * @note l'eroe verrà piazzato sempre nell'ultima cella in basso a dx.*/
    public PopolaMatriceEroe(MatriceGioco matriceGioco,Eroe ilNostroEroe)
    {
        this.ilNostroEroe = ilNostroEroe;
        this.matriceGioco = matriceGioco;
        this.cellaStartEroe =new CellaMatrice(this.matriceGioco.getDimensione()-1,this.matriceGioco.getDimensione()-1);
        this.collocaEroeInMatrice();
    }
    /**Metodo collocaEroeInMatrice:
     * questo metodo viene richiamato dal costruttore sopra.
     * Quindi si esegue appena dopo la creazione dell'oggetto.
     * Il suo scopo è collocare l'eroe nell'ultima cella in fondo a dx della matrice.*/
    private void collocaEroeInMatrice()
    {
        this.matriceGioco.setOggettoNellaMatrice(this.ilNostroEroe,cellaStartEroe);
        this.ilNostroEroe.setCellaPosizioneEroe(cellaStartEroe);
    }
}