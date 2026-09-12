package it.unicam.cs.mpgc.rpg120666.Controller;

import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;

public class ManagerMovimento
{
    /*La variabile final messa qui, mi impedisce che venga poi re-inizializzata la matrice.
    * Nel senso che (dopo, nel codice) non potrò più rifare: 'this.matrice= altro'.*/
    private final MatriceGioco matrice;

    public ManagerMovimento(MatriceGioco matrice)
    {
        this.matrice = matrice;
    }

    public boolean muovi(Nemico nemico, CellaMatrice destinazione)
    {
        //Se il personaggio muore, non si muove! return false!
        if (!nemico.controllaSeNemicoInVita()) { return false; }
        //Se la cella passata è fuori dai confini della martrice, il personaggio non si muove! return false!
        if (!matrice.controllaConfiniMatrice(destinazione)) { return false; }
        /*Se sono arrivato qui vuol dire che la cella di destinazione sta dentro la matrice e
         *che il personaggio è in vita (quindi si può muovere)!
         *Creo una variabile d'appoggio chiamata 'contenuto' di tipo "ElementiPerMatrice"*/
        ElementiPerMatrice contenuto = matrice.getOggettoDellaMatrice(destinazione);
        //Se nella matrice trovo che ho la cella vuota (ossia con null, perchè dentro non c'è un "ElementiPerMatrice"..
        if (contenuto == null)
        {
            /*Uso il metodo set della classe "MatriceGioco" per inserire il personaggio in
             * quella cella vuota! e ritorno true! Ossia il personaggio si è mosso!*/
            System.out.println("CELLA VUOTA NON HO TROVATO NIENTE!");
            matrice.setOggettoNellaMatrice(nemico, destinazione);
            return true;
        }
        //Se nella cella di destinazione trovo un'altro personaggio...
        if (contenuto instanceof Nemico altroNemico)
        {   /*deve cominciare lo scontro tra i personaggi,
         *il vincitore andrà a occupare la cella di destinazione!*/
            return gestisciScontro(nemico, altroNemico, destinazione);
        }
        //Se nella cella di destinazione trovo un oggetto...
        if (contenuto instanceof ElementiPerMatrice oggetto)
        {  /*deve iniziare il modo per raccogliere l'oggetto
         *e spostarsi su quella cella (dove c'era prima l'oggetto)!*/
            return raccogliOggetto(nemico, oggetto, destinazione);
        }
        return false; //Altrimenti ritorno false. True è ritornato solo nei casi sopra!
    }

    private boolean gestisciScontro(Nemico p1, Nemico p2, CellaMatrice cella) {
        // TODO: logica combattimento
        System.out.println("\n\n\n");
        System.out.println("Nelle cella della matrice, quella dove tu vuoi andare: +"+cella.toString()+"\n"+
                "Ho trovato che è occupata da un'altro personaggio: "+p2.toString()+"\n");
        System.out.println("\n\n\n");
        System.out.println("Quindi comincia lo scontro!\nTu sei: "+p1.toString()+"\n");
        return false;
    }

    private boolean raccogliOggetto(Nemico p1, ElementiPerMatrice oggetto, CellaMatrice cella) {
        // TODO: logica raccolta oggetti
        System.out.println("\n\n\n");
        System.out.println("Nelle cella della matrice, quella dove tu vuoi andare: +"+cella.toString()+"\n"+
                "Ho trovato che è occupata da un'OGGETTO: "+oggetto.toString()+"\n");
        System.out.println("\n\n\n");
        System.out.println("Quindi puoi raccoglierlo!\nTu sei: "+p1.toString()+"\n");
        return false;
    }
}