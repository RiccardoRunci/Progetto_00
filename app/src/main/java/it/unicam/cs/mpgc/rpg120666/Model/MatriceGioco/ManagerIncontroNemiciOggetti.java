package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;

public class ManagerIncontroNemiciOggetti
{
    private Eroe ilNostroEroe;
    private MatriceGioco matriceGioco;
    public ManagerIncontroNemiciOggetti(MatriceGioco matriceGioco, Eroe ilNostroEroe)
    {
        if(matriceGioco==null || ilNostroEroe==null)
            throw new IllegalArgumentException("Parametri passati al metodo costruttore \n " +
                    "della classe: 'ManagerIncontroNemiciOggetti' \n "+
                    "matriceGioco oppure ilNostroEroe sono NULL!\n");
        this.matriceGioco = matriceGioco;
        this.ilNostroEroe = ilNostroEroe;
    }
    public boolean controllaIncontroNemiciOggetti(CellaMatrice cellaDestinazione)
    {
        if(cellaDestinazione==null) //controllo se la cellaDestinazione passata è =null!
            throw new IllegalArgumentException("Parametro: 'cellaDestinazione' " +"\n"+
                    "passato al metodo: 'controllaIncontroNemicoOggetti', \n" +
                    "della classe: 'ManagerIncontroNemiciOggetti' è NULL!\n");

        /*Se nella matrice, alla cellaDestinazione passata, è presente qualcosa al suo interno,
        * la salvo in una variabile ElementiPerMatrice (variabile che comprende tutti gli oggetti che può
        * contenere la matrice, perché tutti gli oggetti contenuti della matrice hanno:
        * ClasseOggettoMatrice  implements ElementiPerMatrice)*/
        if(this.matriceGioco.getOggettoDellaMatrice(cellaDestinazione)!=null) //Se dentro la cellaDestinazione c'è qualcosa:
        {   //Per dichiarare questa variabile ha voluto che inserissi le {...} parentesi!
            ElementiPerMatrice elementoIncontrato = this.matriceGioco.getOggettoDellaMatrice(cellaDestinazione);
            if(elementoIncontrato instanceof Oggetto) //Il nostro eroe ha incontrato un oggetto!
            {
                this.incontratoOggetto();
            }
            if(elementoIncontrato instanceof Nemico) //Il nostro eroe ha incontrato un Nemico!
            {
                this.incontratoNemico();
            }
            return true; //Ha incontrato qualcosa!
        }
        else //cellaDestinazione VUOTA:
        {
            return false; //Non c'è nulla!
        }
    }
    private boolean incontratoOggetto()
    {

        return false;
    }
    private boolean incontratoNemico()
    {

    return false;
    }
}
