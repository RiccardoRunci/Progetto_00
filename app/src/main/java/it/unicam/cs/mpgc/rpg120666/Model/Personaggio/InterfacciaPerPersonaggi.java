/**Questa interfaccia è implementata da: Eroe, Guerriero e Mago.
 * Eroe, Guerriero e Mago sono delle sottoclassi di Personaggi.
 * Guerriero e Mago sono delle sottoclassi di Nemici (che a sua volta è sotto classe di Personaggio).
 * Grazie a questa interfaccia posso evitare di scrivere (come avevo scritto nel vecchio codice) gli
 * istanceof nella classe ManagerComnattimentoEroeNemico.
 * Perchè in quella classe richiamo eseguiAttacco che è un metodo posseduto da tutte le classi Personaggi.
 * Come ho fatto in ElementiPerMatrice, però stavolta non dovrei avere delle violazioni di ISP.*/
package it.unicam.cs.mpgc.rpg120666.Model.Personaggio;
public interface InterfacciaPerPersonaggi
{
    /**Questo metodo ritorna true se il singolo attacco,
     * da parte dell'eroe ai danni del nemico (o viceversa), è andato a buon fine.
     * Ritorna false se invece c'è stato qualche problema (e il singolo attacco non è andato a segno).*/
    public boolean eseguiAttacco(Personaggio e);
}
