package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;

import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;

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
