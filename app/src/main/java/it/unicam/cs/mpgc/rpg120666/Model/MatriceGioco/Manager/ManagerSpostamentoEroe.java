/**Classe ManagerSpostamenteEroe:
 * Qui parte una serie di classi a catena. Questa classe è lo "start".
 *
 * 1)ManagerSpostamentoEroe
 * Nel suo costruttore inizializza un oggetto ManagerIncontroNemiciOggetti
 * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerIncontroNemiciOggetti
 *
 * 2)ManagerSpostamentoEroe
 * Ha un metodo che boolean spostaIlNostroEroe(cellaDestinazione):
 *      Se la cella di destinazione è vuota (null), ci sposta subito l'eroe dentro e torna true.
 *      Se contiene qualcosa, bisogna vedere che cosa, allora si usa
 *      ManagerIncontroNemiciOggetti.controllaIncontroNemiciOggetti(cellaDestinazione)
 * 	    e si aspetta cosa ritorna ManagerIncontroNemiciOggetti.controllaIncontroNemiciOggetti.
 *@author Runci Riccardo.
 *@since versione 1.0*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;

public class ManagerSpostamentoEroe
{
    private Eroe ilNostroEroe;
    private MatriceGioco matriceGioco;
    private ManagerIncontroNemiciOggetti managerIncontroNemiciOggetti;

    /**Metodo Costruttore:
     * @param matriceGioco = la matrice del nostro gioco.
     * @param ilNostroEroe = il nostro eroe.
     * @throws  IllegalArgumentException Se uno dei parametri sopra (o entrambi) sono null! */
    public  ManagerSpostamentoEroe(MatriceGioco matriceGioco, Eroe ilNostroEroe)
    {
        if(matriceGioco==null || ilNostroEroe==null)
            throw new IllegalArgumentException("PARAMETRI PASSATI ALLA CLASSE 'ManagerSpostamentoEroe'"+"\n"+
                    "MatriceGioco oppure ilNostroEroe sono NULL!\n");
        this.matriceGioco = matriceGioco;
        this.ilNostroEroe = ilNostroEroe;
        this.managerIncontroNemiciOggetti=new ManagerIncontroNemiciOggetti(this.matriceGioco,ilNostroEroe);
    }
    /**Metodo spostaIlNostroEroe:
     * @param cellaDestinazione = la cella dove il nostro eroe deve spostarsi.
     * @return false = Se L'eroe NON SI PUO' muovere!
     *         Per tornare true = chiama la classe ManagerIncontroNemiciOggetti.
     *                            e vede se nella nuova cella c'è un Nemico o un oggetto o una cella vuota.
     * @see ManagerIncontroNemiciOggetti
     * Aggiorna: il campo Eroe.CelleDoveSiTrova, aggiorna anche la matriceGioco (toglie l'eroe
     *           dalla vecchia cella e lo mette nella nuova cella).
     * @author Runci Riccardo.
     * @since versione 1.0.*/
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

        /* La classe ManagerIncontroNemiciOggetti ha un metodo che ritorna true se l'eroe è riuscito
         * nell'impresa di muoversi nella cella di destinazione!*/
        if (this.managerIncontroNemiciOggetti.controllaIncontroNemiciOggetti(cellaDestinazione))
        {
            //Se tutto va bene, devo togliere l'Eroe dalla cella di partenza della matrice!
            this.matriceGioco.setOggettoNellaMatrice(null, this.ilNostroEroe.getCellaPosizioneEroe());
            //E POSIZIONARE L'Eroe nella MATRICE (dentro la sua cella di DESTINAZIONE):
            this.matriceGioco.setOggettoNellaMatrice(this.ilNostroEroe, cellaDestinazione);
            //E devo spostare L'Eroe dentro la cella di destinazione!
            this.ilNostroEroe.setCellaPosizioneEroe(cellaDestinazione);

            //this.matriceGioco.stampaMatriceCLI();
            return true;
        }
        else
        {
            System.out.println("Per qualche motivo il nostro eroe non si è riuscito a muovere dalla cella di partenza.\n");
            return false;
        }
    }
}
