/**Classe ManagerIncontroNemiciOggetti:
 * Questa è il 2° anello della catena che partiva con la classe ManagerSpostamentoEroe.
 * Questa classe nel costruttore prende i riferimenti di MatriceGioco & di Eroe.
 * Ha un metodo (controllaIncontroNemiciOggetti) che:
 *                  Se il contenuto della cellaDestinazione passata è null, allora ritorna al metodo
 *                  ManagerSpostamentoEroe.spostaIlNostroEroe true.
 *                  Se il contenuto della cellaDestinazione è != null, vuol dire che c'è qualcosa.
 *                  Estrapola quel qualcosa e lo rende un tipo ElementoPerMatrice.
 *                  Grazie all'interfaccia ElementiPerMatrice, tutti gli oggetti (che implementa quell'interfaccia)
 *                  Hanno un metodo chiamato calpestatoDaEroe.
 *                  Poi sarà cura di ogni singolo oggetto scrivere il codice di quel metodo.
 * @author Runci Riccardo
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;

public class ManagerIncontroNemiciOggetti
{
    private Eroe ilNostroEroe;
    private MatriceGioco matriceGioco;
    /**Metodo costruttore:
     * @param matriceGioco = passata per riferimento dalla classe ManagerSpostamentoEroe.
     * @param ilNostroEroe = passato per riferimento dalla classe ManagerSpostamentoEroe.
     * @throws IllegalArgumentException se uno dei 2 (o entrambi) parametri sopra è null!
     * @author Runci Riccardo
     * @since versione 1.0.*/
    public ManagerIncontroNemiciOggetti(MatriceGioco matriceGioco, Eroe ilNostroEroe)
    {
        if(matriceGioco==null || ilNostroEroe==null)
            throw new IllegalArgumentException("Parametri passati al metodo costruttore \n " +
                    "della classe: 'ManagerIncontroNemiciOggetti' \n "+
                    "matriceGioco oppure ilNostroEroe sono NULL!\n");
        this.matriceGioco = matriceGioco;
        this.ilNostroEroe = ilNostroEroe;
    }
    /**Metodo controllaIncontroNemiciOggetti:
     * Metodo chiamato dalla classe ManagerSpostamentoEroe.spostaIlNostroEroe(CellaMatrice cellaDestinazione).
     * @param cellaDestinazione è la cella da controllare (dove ci dovrà andare l'eroe, se riesce).
     * @throws IllegalArgumentException se la cella sopra passata come parametro è null!
     * Qui mi prendo l'oggetto che stà dentro la matrice, lo estrapolo e lo faccio diventare ElementiPerMatrice.
     * Grazie a quell'interfaccia tutti gli oggetti hanno implementato un metodo chiamato calpestatoDeEroe.*/
    public boolean controllaIncontroNemiciOggetti(CellaMatrice cellaDestinazione)
    {
        /*Ho rifatto il controllo*/
        if(cellaDestinazione==null) //controllo se la cellaDestinazione passata è =null!
            throw new IllegalArgumentException("Parametro: 'cellaDestinazione' " +"\n"+
                    "passato al metodo: 'controllaIncontroNemicoOggetti', \n" +
                    "della classe: 'ManagerIncontroNemiciOggetti' è NULL!\n");
        //Prendo l'oggetto contenuto nella matrice e lo salvo in una variabile di tipo ElementiPerMatrice.
        ElementiPerMatrice elementoIncontrato = this.matriceGioco.getOggettoDellaMatrice(cellaDestinazione);

        // Caso 1: cella vuota → movimento consentito
        if (elementoIncontrato == null)
            return true;

        /*Grazie al metodo: "boolean calpestataDaEroe(Eroe e)", contenuto nell'interfaccia ElementiPerMatrice,
         *riesco a togliere i vari "instanceof Oggetto","instanceof Nemico",ecc... Presenti qui sotto.
         *Sostituendoli con: "elementoIncontrato.calpestataDaEroe(this.ilNostroEroe)"
         * if (elementoIncontrato instanceof Oggetto ogg)  Se in caso la cella conteneva un Oggetto!
         *      return incontratoOggetto(ogg); Posso togliere anche questo metodo locale che stava sotto in questa classe!
         * if (elementoIncontrato instanceof Nemico nem) //in caso la cella conteneva un Nemico!
         *      return incontratoNemico(nem); Posso togliere anche questo metodo locale che stava sotto in questa classe!
         * Questo codice sopra faceva parte della mia vecchia versione del progetto.*/
         return elementoIncontrato.calpestataDaEroe(this.ilNostroEroe);
         /*L'unico guaio ce l'ho sulla classe Eroe, dove anche essa (essendo una classe che implementa l'interfaccia
         * ElementiPerMatrice) deve ereditare il metodo ".calpestataDaEroe(this.ilNostroEroe)".
         * In quella classe stò violando il principio solid ISP (segregazione delle interfacce).*/
    }
}
