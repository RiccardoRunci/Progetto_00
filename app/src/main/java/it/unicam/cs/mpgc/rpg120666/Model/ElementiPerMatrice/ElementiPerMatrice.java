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
        /**Metodo che serve per evitare i wild cards (nella classe MaticeContaElementi),
         * tutte le classi che estendono questa interfaccia devono avere questo metodo che ritorna
         * una stringa che indica il tipo dell'oggetto.
         * Bisognerà ricordarsi (qual'ora si vuole mettere una nuova categoria di ElementiPerMatrice) di inserirla anche
         * nell'enumerazione EnumOggNem.
         * Parlo più dettagliatamente di questa "operazione" nella classe MatriceContaElementi.*/
        public String restituisciTipoOggetto(); //Soluzione "casareccia".

        /*Questa se funziona è un pò una porchetta.
        * Dato che la matrice è una matrice di questa interfaccia e dato che:
        * La classe Oggetto ha come suoi elementi le sue classi-estese, Che poi posso aumentare con il tempo;
        * La classe Nemico ha come suoi elementi le sue classi-estese, che poi posso aumentare con il tempo.
        * Entrambi sia Oggetto che Nemico estendono questa interfaccia.
        * Quindi devono implementare questi metodi in Oggetto, Nemico e (se le loro classi estese implementano questa interfaccia, anche da loro).
        * Io invece lo implemento solo su Oggetto e Nemico, ma lo implemento final.
        * public final String veroVolto()
        * Anche se cambia la firma, in teoria è lo stesso metodo.
        * così i vari nemici nuovi avranno sempre return "Nemico" (anche se sono Dragi, Orchi,ecc...)
        * e i vari Oggetti nuovi ritorneranno sempre "Oggetto" (anche se saranno cose varie).
        * Questo metodo mi serve per la classe ManagerFineGioco, per rendere il codice estendibile,
        * senza dover creare un nuovo if elemento==Drago || elemento==Orco || elemento==ecc... */
        public String veroVolto(); //sempre nella classe MatriceContaElementi!

        /**Metodo che viene richiamato nella classe Manager.ManagerIncontroNemiciOggetti.
         * @Note Serve per evitare gli istanceof in quella classe.
         * Ne parlo meglio nei commenti di ManagerIncontroNemiciOggetti.
         * @Note questo metodo purtroppo mi dà origine anche a una violazione solid ISP nella Classe Eroe.
         * Perchè anche Eroe è una classe che implementa questa interfaccia.*/
        public boolean calpestataDaEroe(Eroe e);
}


