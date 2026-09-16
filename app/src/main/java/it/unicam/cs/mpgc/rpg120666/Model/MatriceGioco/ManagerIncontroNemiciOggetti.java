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
        //Prendo l'oggetto contenuto nella matrice e lo salvo in una variabile di tipo ElementiPerMatrice.
        ElementiPerMatrice elementoIncontrato = this.matriceGioco.getOggettoDellaMatrice(cellaDestinazione);

        // Caso 1: cella vuota → movimento consentito
        if (elementoIncontrato == null)
            return true;

        // Caso 2: oggetto → raccogli → movimento consentito
        if (elementoIncontrato instanceof Oggetto ogg) //todo instanceof Classe nomeOggetto suggerito dall'IA
            return incontratoOggetto(ogg);

        // Caso 3: nemico → combatti → movimento dipende dal risultato
        if (elementoIncontrato instanceof Nemico nem) //todo instanceof Classe nomeOggetto suggerito dall'IA
            return incontratoNemico(nem);

        return false; //se è successo qualcosa di storto.
     }
    private boolean incontratoOggetto(Oggetto ogg)
    {
        return this.ilNostroEroe.prendiOggetto(ogg);
    }
    private boolean incontratoNemico(Nemico nem)
    {
        ManagerCombattimentoEroeNemico manCombat= new ManagerCombattimentoEroeNemico(this.ilNostroEroe,nem);
        return manCombat.combattimento();
    }
}
