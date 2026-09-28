package it.unicam.cs.mpgc.rpg120666.Main;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerSpostamentoEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
public class MainCli
{
    public String saluta() {return "Benvenuto nel gioco RPG!"; }
    public static void main(String[] args)
    {
        //0 Creo l'EROE!
        Eroe IlNostroEroe = new Eroe("RICCARDO",50);
        // 1) MODEL
        MatriceGioco model = new MatriceGioco(EnumDifficolta.MEDIA,IlNostroEroe);
        /*I POPOLAMENTI VENGONO ADESSO FATTI NEL COSTRUTTORE DI MatriceGioco!
         *PopolaMatriceOggetti popolaOggettiInMatrice= new PopolaMatriceOggetti(model);
         *PopolaMatriceNemici popolaNemiciInMatrice = new PopolaMatriceNemici(model);
         *PopolaMatriceEroe popEroeInMatrice = new PopolaMatriceEroe(model,IlNostroEroe);*/
        /*Matrice già popolata perchè nel costruttore di MatriceGioco creo gli oggetti PopolaMatrice...*/
        System.out.println("LA MATRICE POPOLATA SARA':\n");
        model.stampaMatriceCLI();

        System.out.println("");

        /*Test della classe CalcolaNumOggettiNemiciRimanenti:
         * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.EROE);
         * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA);
         * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT);
         * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO);
         * model.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO);*/

        //2) Manager spostamento eroe:
        ManagerSpostamentoEroe managerSpostamentoEroe = new ManagerSpostamentoEroe(model,IlNostroEroe);
        managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(0,0));
        System.out.println("");
        managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(0,1));
        System.out.println("");
        managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(0,2));
        System.out.println("");
        managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(0,3));
        System.out.println("");
        managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(0,4));
        System.out.println("");


        /*ManagerSpostamentoEroe crea al suo interno una istanza di ManagareIncontroNemiciOggetti.
        * ManagerIncontroNemiciOggetti rimanda, grazie al metodo ..calpestataDaEroe(Eroe),
        *   Dell'interfaccia ElementiPerMatrici, il lavoro concreto alle singole classi:
        *   Di raccolta, se nella cella incontrata c'è un Oggetto;
        *   Di combattimento (tramite il managerCombattimento), se nella cella c'è un Nemico.*/
    }
}
