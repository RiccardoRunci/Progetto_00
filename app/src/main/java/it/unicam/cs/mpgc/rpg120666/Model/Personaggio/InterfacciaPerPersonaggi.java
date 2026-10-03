/**Questa interfaccia FUNZIONALE (aka con un solo metodo) è implementata da: Eroe, Guerriero, Mago e Personaggio.
 * @note Personaggio ha questo metodo sovrascitto ma abstract (così non se ne occupa e lo fà implementare alle sue sottoclassi).
 * Eroe, Guerriero e Mago sono delle sottoclassi di Personaggi.
 * Guerriero e Mago sono delle sottoclassi di Nemici (che a sua volta è sotto classe di Personaggio).
 * Grazie a questa interfaccia posso evitare di scrivere (come avevo scritto nel vecchio codice) gli
 * istanceof nella classe ManagerComnattimentoEroeNemico.
 * Perchè in quella classe richiamo eseguiAttacco che è un metodo posseduto da tutte le classi Personaggi.
 * Come ho fatto in ElementiPerMatrice, però stavolta non dovrei avere delle violazioni di ISP.
 * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerCombattimentoEroeNemico
 * @since versione 1.0.
 * @autor Runci Riccardo.*/
package it.unicam.cs.mpgc.rpg120666.Model.Personaggio;
@FunctionalInterface
public interface InterfacciaPerPersonaggi
{
    /**Metodo eseguiAttacco(Personaggio e):
     * @return true se il singolo attacco, da parte dell'eroe ai danni del nemico (o viceversa), è andato a buon fine.
     *         False se invece c'è stato qualche problema (e il singolo attacco non è andato a segno).
     * @param e = la variabile di tipo Personaggio che si prende l'attacco.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    public boolean eseguiAttacco(Personaggio e);
}
