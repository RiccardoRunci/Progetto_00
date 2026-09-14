package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;

public class PopolaMatriceEroe
{
    private Eroe ilNostroEroe;
    private MatriceGioco matriceGioco;
    private  CellaMatrice cellaStartEroe;
    //Costruttore Vuoto:
    public PopolaMatriceEroe() {}
    //Costruttore Pieno:
    public PopolaMatriceEroe(MatriceGioco matriceGioco,Eroe ilNostroEroe)
    {
        this.ilNostroEroe = ilNostroEroe;
        this.matriceGioco = matriceGioco;
        this.cellaStartEroe =new CellaMatrice(this.matriceGioco.getDimensione()-1,this.matriceGioco.getDimensione()-1);
        this.collocaEroeInMatrice();
    }
    private void collocaEroeInMatrice()
    {
        this.matriceGioco.setOggettoNellaMatrice(this.ilNostroEroe,cellaStartEroe);
        this.ilNostroEroe.setCellaPosizioneEroe(cellaStartEroe);
    }
}
