/**Questa interfaccia viene implementata da tutte le classi che possono entrare dentro la MatriceGioco.
 * Così posso creare una matrice di classi diverse (ma che queste classi) implementano questa interfaccia.
 * Così la mia MatriceGioco può contenere: "Oggetti","Nemici" e "Eroe"; Che sono 3 tipi diversi.
 * Le classi che implementano questa interfaccia per adesso sono le sottoclassi di Oggetto(Medikit, Arma),
 * la classe Eroe e le sottoclassi di Nemici(Guerriero e Mago).
 * Se devo aggiungere una nuova categoria di ElementiPerMatrice nel gioco, dovrò far implementare a quella nuova classe questa interfaccia.*/
package it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;

public interface ElementiPerMatrice
{
        /**Metodo che serve per evitare i wild cards,
         * tutte le classi che estendono questa interfaccia devono avere questo metodo che ritorna
         * una stringa che indica il tipo dell'oggetto.
         * Bisognerà ricordarsi (qual'ora si vuole mettere una nuova categoria di ElementiPerMatrice) di inserirla anche
         * nell'enumerazione EnumOggNem.
         * Parlo più dettagliatamente di questa "operazione" nella classe MatriceContaElementi.*/
        public String restituisciTipoOggetto(); //Soluzione "casareccia".

        /**Metodo che viene richiamato nella classe Manager.ManagerIncontroNemiciOggetti.
         * @Note Serve per evitare gli istanceof in quella classe.
         * Ne parlo meglio nei commenti di ManagerIncontroNemiciOggetti.
         * @Note questo metodo purtroppo mi dà origine anche a una violazione solid ISP nella Classe Eroe.
         * Perchè anche Eroe è una classe che implementa questa interfaccia.*/
        public boolean calpestataDaEroe(Eroe e);
}


