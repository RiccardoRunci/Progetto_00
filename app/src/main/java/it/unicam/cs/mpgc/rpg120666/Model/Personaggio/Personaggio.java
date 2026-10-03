/**Classe astratta Personaggio.
 * Questa classe viene estesa dalle classi:
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico
 * @see it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe
 * Le classi contenute in Model.Nemico.ClassiEstese(Mago & Guerriero) saranno "Nipoti" di questa classe.
 * @note: La classe Eroe è direttamente figlia. Qui anche potevo farla diventare Nipote e non direttamente figlia, ma è stato
 *        un progetto molto arduo e alla fine ho lasciato così. Se lo facevo diventava più lineare.
 * Questa classe implementa l'interfaccia Personaggio.InterfacciaPerPersonaggi, la quale ha un solo metodo:
 *  "boolean eseguiAttacco(Personaggio e);
 *  Serve nella classe "MatriceGioco.Manager.ManagerComnattimentoEroeNemico", dove grazie a quel metodo evito di fare
 *  if(elementoMatrice istanceOf "qualchePersonaggio"). Prendo il personaggio e faccio il metodo eseguiAttacco(Personaggio e).
 * @autor Runci Riccardo.
 * @since version 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.MieAnnotazioni;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import java.util.Objects; //Per l'hashCode

 /*NOTE Degli import statici per l'enumerazione CreazioneAggiornamento:
  * Me li ha messo in automatico (credo) Intelli-j, se no avrei dovuto scrivere a posto di:
  * VerificaParametri.verificaInteri(puntiVitaPersonaggio, CREAZIONE) -->
  * VerificaParametri.verificaInteri(interoVario, CreazioneAggiornamento.CREAZIONE).*/
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.AGGIORNAMENTO;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;
@MieAnnotazioni( domanda = "non so se è un errore fare gli import statici.",
                osservazione = "Non si usano le annotazioni negli import... ")

public abstract class Personaggio implements InterfacciaPerPersonaggi
{
    private final String nomePersonaggio;
    private int puntiVitaPersonaggio;
    /**Metodo costruttore:
     * @param nomePersonaggio (Stringa) = indica il nome del personaggio.
     * @param puntiVitaPersonaggio (int) = indica i punti vita del personaggio.
     * @throws  IllegalArgumentException se i parametri non vanno bene alla classe Utility.VerificaParametri.
     * @see VerificaParametri Questa classe ha un metodo che prende come parametro un enumerazione. Grazie a questa enumerazione
     *      posso distinguere quando un personaggio viene creato nel costruttore (passando puntiVitaPersonaggio>0),
     *      e quando un personaggio (viene colpito da un attacco) setta la sua vita a (passando puntiVitaPersonaggio=0) 0.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    public Personaggio(String nomePersonaggio, int puntiVitaPersonaggio)
    {
        /*Se la verifica torna true in entrambi i parametri passati allora va bene!*/
        if(VerificaParametri.verificaStringhe(nomePersonaggio) && VerificaParametri.verificaInteri(puntiVitaPersonaggio,CREAZIONE))
        {
            this.nomePersonaggio = nomePersonaggio;
            this.puntiVitaPersonaggio = puntiVitaPersonaggio;
        }
        else
            throw new IllegalArgumentException("Parametri passati al costruttore di Personaggio sono invalidi");
    }
    /**Metodo setPuntiVitaPersonaggi:
     * @param puntiVitaPersonaggio (int) = indica i punti vita del personaggio.
     * @return void.
     * @throws  IllegalArgumentException se il parametro non va bene alla classe Utility.VerificaParametri.
     * @see VerificaParametri Questa classe utilizza un metodo di classe VerificaParaemtri associato a una enumearazione.
     *      Questo permette: Grazie all'enumerazione 'CREAZIONE', La creazione di un personaggio nel costruttore con puntiVitaPersonaggio>0 (non >=0);
     *      Permette anche (grazie all' enumerazione'AGGIORNAMENTO') che il setVitaPersonaggio possa prendere puntiVitaPersonaggio=0
     *      {@link CreazioneAggiornamento}
     *@since versione 1.0.
     *@autor Runci Riccardo*/
    public void setPuntiVitaPersonaggio(int puntiVitaPersonaggio)
    {
        if(VerificaParametri.verificaInteri(puntiVitaPersonaggio,AGGIORNAMENTO))
            this.puntiVitaPersonaggio = puntiVitaPersonaggio;
        else
            throw new IllegalArgumentException("Parametro int passato a Personaggio.setPuntiVitaPersonaggio invalido");
    }
    /**Metodo getPuntiVitaPersonaggio:
     * @return int = ritorna i puntiVitaPersonaggio.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    public int getPuntiVitaPersonaggio(){ return this.puntiVitaPersonaggio; }
   //Il metodo 'void setNomePersonaggio(String ...)' non può essere fatto perché this.nomePersonaggio è final!
    /**Metodo getNomePersonaggio:
     * @return String = ritorna il nomePersonaggio.
     * @since versione 1.0.*/
    public String getNomePersonaggio() { return this.nomePersonaggio; }
    /**Metodo toString:
     * @override del metodo toString della classe Object.
     * @return String = una stringa che descrive un istanza di Oggetto Personaggio.
     *                  Questa descrizione è formata da "Classe Personaggio"+ il nome e i punti vita del Personaggio.*
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public String toString(){
        return "CLASSE: Personaggio!\n"+
                "NOME: "+this.nomePersonaggio+"\n"+
                "VITA: "+this.puntiVitaPersonaggio+"\n";
    }
    /**Metodo hashCode:
     *@override del metodo hashCode della classe Object.
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public int hashCode(){
        return Objects.hash(this.nomePersonaggio, this.puntiVitaPersonaggio);
    }
    /**Metodo equals:
     *@override del metodo equals della classe Object.
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     *                  False, altrimenti.
     *@param (obj) di tipo Object = classico parametro dei metodi equals. Serve per confrontare con questa istanza
     *                              l'oggetto passato a questo metodo, dopo il suo cast.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Personaggio)) return false;
        Personaggio other = (Personaggio)obj;
        return (this.nomePersonaggio.equals(other.nomePersonaggio)) &&
                (this.puntiVitaPersonaggio == other.puntiVitaPersonaggio);
    }
    /**Metodo controllaSeVivo:
     * @return true se il personaggio è ancora vivo!
     *         false se il personaggio è morto.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    public boolean controllaSeVivo()
    {
        if(this.getPuntiVitaPersonaggio()>0) return true;
        else return false;
    }
    /**Metodo eseguiAttacco:
     * deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi. Lo metto abstract,
     * così da farlo implementare nelle sottoclassi di Personaggio.
     * Personaggio è una classe astratta, non si istanzia.
     * Questo metodo tornerà utile per eliminare gli istance of nella classe ManagerCombattimentoEroeNemico.
     * @return bolean = se il singolo attacco da parte di una sottoclasse di Personaggio è andato a buon fine.
     *                  False altrimenti.
     * @param e = di tipo Personaggio, è l'oggetto che riceve l'attacco.
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public abstract boolean eseguiAttacco(Personaggio e);
}
