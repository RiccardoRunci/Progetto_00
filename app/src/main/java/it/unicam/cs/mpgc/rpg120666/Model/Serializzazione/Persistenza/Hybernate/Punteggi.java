/**Classe POJO
 * (avente solo: campi, costruttore, metodi set e get) che serve per mappare
 * la tabella Statistiche del dbms.
 * QUESTE CLASSI DEVONO ESSERE SERIALIZZABILI (implements Serializable)!*/
package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.MieAnnotazioni;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/*Questa classe è (una classe Entità, ossia, oltre che a essere POJO, deve essere) annotata con
 * l'annotazione @Entity perché é una classe che rispecchia UNA RIGA della tabella Punteggi del mio DBMS.*/
@Entity
public class Punteggi implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    /*I campi delle classi Entità rappresentano le COLONNE della tabella Punteggi del mio dbms.
     * Se non voglio che un campo venga collegato al DBMS, allora lo devo annotare con @Transient.*/

    /*Le 2 annotazioni qui sotto servono a dire che id è un campo chiave primaria (@id);
    * E con @GeneratedValue(strategy = GenerationType.IDENTITY) che sulla tabella del DB è segnato come AUTO-INCREMENT!*/
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //Invece che int devo metterlo INTEGER. È meglio per i campi Auto-increment!
    //poi gli altri campi che sono presenti anche nella tabella:
    private String nomeGiocatore;
    private int nemiciRimasti;
    private int oggettiRimasti;
    private Date dataGioco;

    public Punteggi() { } // Costruttore vuoto obbligatorio per Hibernate

           //Quando un campo chiave primaria è autoincrement NON VA nel costruttore!
    public Punteggi(/*int id,*/ String nomeGiocatore, int nemiciRimasti, int oggettiRimasti, Date dataGioco)
    {
        if (nomeGiocatore==null || nomeGiocatore.isEmpty() || nemiciRimasti<0 || oggettiRimasti<0 || dataGioco==null)
            throw new IllegalArgumentException("Uno o più parametri passati al costruttore (della classe POJO Punteggi) non sono validi!");

        this.nomeGiocatore=nomeGiocatore;
        this.nemiciRimasti=nemiciRimasti;
        this.oggettiRimasti=oggettiRimasti;
        this.dataGioco=dataGioco;
    }

    public int getId() { return this.id; }
    public String getNomeGiocatore() { return this.nomeGiocatore; }
    public int getNemiciRimasti(){ return this.nemiciRimasti; }
    public int getOggettiRimasti() {return this.oggettiRimasti; }
    public Date getDataGioco() { return this.dataGioco; }
    //IL SET DELLA CHIAVE PRIMARIA é SBAGLIATO!
    //public void setId(int id)
    public void setNomeGiocatore(String nomeGiocatore)
    {
        if  (nomeGiocatore==null || nomeGiocatore.isEmpty())
            throw new IllegalArgumentException("Parametro nomeGiocatore, passato al setNomeGiocatore della classe Pojo Punteggi, non valido!");
        this.nomeGiocatore=nomeGiocatore;
    }
    public void setNemiciRimasti(int nemiciRimasti)
    {
        if(nemiciRimasti<0)
            throw new IllegalArgumentException("Parametro nemiciRimasti, passato al setId della classe POJO Punteggi, non valido!");
        this.nemiciRimasti=nemiciRimasti;
    }
    public void setOggettiRimasti(int oggettiRimasti)
    {
        if(oggettiRimasti<0)
            throw new IllegalArgumentException("Parametro oggettiRimasti, passato al setId della classe POJO Punteggi, non valido!");
        this.oggettiRimasti=oggettiRimasti;
    }

    public void setDataGioco(Date dataGioco)
    {
        if (dataGioco==null)
            throw new IllegalArgumentException("Parametro dataGioco, passato al setDataGioco della classe Pojo Punteggi, non valido!");
        this.dataGioco = dataGioco;
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.nomeGiocatore,this.nemiciRimasti,this.oggettiRimasti,this.dataGioco);
    }

    @MieAnnotazioni(
            domanda="Nell' equals, per confrontare la chiave primaria ho richiamato i metodi getId()." +
                    "Però la chiave primaria è un Oggetto Integer (per via dei null).",
            osservazione="Se gli faccio il return a int (come nei get) un integer tornerà sempre un int!?!?")
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Punteggi)) return false;
        Punteggi other = (Punteggi)obj;
        return (this.getId()==(other.getId()) && //Sarà uguale se faccio: campo=Integer e getId=int ? Uno torna un Oggetto, uno un int!
                (this.getNomeGiocatore().equals(other.getNomeGiocatore())) &&
                (this.getNemiciRimasti()==other.getNemiciRimasti()) &&
                (this.getOggettiRimasti()==other.getOggettiRimasti()) &&
                (this.getDataGioco().equals(other.getDataGioco())));
    }

}
