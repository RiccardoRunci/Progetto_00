package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;

public class ManagerSpostamentoEroe
{
    private Eroe ilNostroEroe;
    private MatriceGioco matriceGioco;
    private ManagerIncontroNemiciOggetti managerIncontroNemiciOggetti;

    //Costruttore Pieno:
    public  ManagerSpostamentoEroe(MatriceGioco matriceGioco, Eroe ilNostroEroe)
    {
        if(matriceGioco==null || ilNostroEroe==null)
            throw new IllegalArgumentException("PARAMETRI PASSATI ALLA CLASSE 'ManagerSpostamentoEroe'"+"\n"+
                    "MatriceGioco oppure ilNostroEroe sono NULL!\n");
        this.matriceGioco = matriceGioco;
        this.ilNostroEroe = ilNostroEroe;
        this.managerIncontroNemiciOggetti=new ManagerIncontroNemiciOggetti(this.matriceGioco,ilNostroEroe);
    }
    /**Questo metodo:
     * @return false = Se L'eroe NON SI PUO' muovere!
     * @return true = Se SI PUO' muovere (e nel metodo, se l'eroe si può muovere, lo muove).
     * Aggiorna: il campo Eroe.CelleDoveSiTrova, aggiorna anche la matriceGioco (toglie l'eroe
     *           dalla vecchia cella e lo mette nella nuova cella).*/
    public boolean spostaIlNostroEroe(CellaMatrice cellaDestinazione)
    {
        //Controllo se la cella è != null!
        if(cellaDestinazione==null)
            throw new IllegalArgumentException("Cella di destinazione non valida!" +"\n"+
                    "Perché é =NULL \n"+
                    "Eccezione in classe ManagerSpostamentoEroe, Metodo: spostaIlNostroEroe.\n");
        //Controllo se la cella NON è una cella all'interno della matrice!
        if(!(this.matriceGioco.controllaConfiniMatrice(cellaDestinazione))) return false;
        //Controlla se la cella di destinazione è la stessa di dove si trova già il nostro eroe!
        if(this.ilNostroEroe.getCellaPosizioneEroe().equals(cellaDestinazione))
            return false; //CellaDestinazione = CellaPartenza!

        /**La classe ManagerIncontroNemiciOggetti ha un metodo "omonimo" che ritorna true se l'eroe è riuscito
         * nell'impresa di muoversi nella cella di destinazione!*/
        if (this.managerIncontroNemiciOggetti.controllaIncontroNemiciOggetti(cellaDestinazione))
        {
            //Se tutto va bene, devo togliere l'Eroe dalla cella di partenza della matrice!
            this.matriceGioco.setOggettoNellaMatrice(null, this.ilNostroEroe.getCellaPosizioneEroe());
            //E POSIZIONARE L'Eroe nella MATRICE (dentro la sua cella di DESTINAZIONE):
            this.matriceGioco.setOggettoNellaMatrice(this.ilNostroEroe, cellaDestinazione);
            //E devo spostare L'Eroe dentro la cella di destinazione!
            this.ilNostroEroe.setCellaPosizioneEroe(cellaDestinazione);

            this.matriceGioco.stampaMatriceCLI();
            return true;
        }
        else
        {
            //todo TOGLI QUESTO PRINTF quando 6 convinto che funziona!
            System.out.println("Per qualche motivo il nostro eroe non si è riuscito a muovere dalla cella di partenza.\n");
            return false;
        }
    }
}
