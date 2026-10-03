/**Classe Enumerazione CreazioneAggiornamento:
 * Serve per distinguere i controlli che faccio sui parametri interi che passo ai costruttori e ai metodi set.
 * Mi ha evitato di scrivere sempre lo stesso pezzo di codice e di distinguere (grazie a essa) 2 metodi/controlli e
 * accorparli in uno solo.
 * @autor Copilot & Runci Riccardo.
 * @note una volta capito il ragionamento l'ho riutilizzato molte volte nel progetto. Alcune volte ho tenuto il codice,
 *       altre volte l'ho buttato.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Utility;
public enum CreazioneAggiornamento
{
    CREAZIONE,
    AGGIORNAMENTO
}
/*CREAZIONE è un controllo per i costruttori (i quali non possono avere puntiVita=0).
* AGGIORNAMENTO è un controllo per i metodi set (i quali possono anche arrivare ad avere puntiVita=0, in caso di morte
*               del personaggio).*/
