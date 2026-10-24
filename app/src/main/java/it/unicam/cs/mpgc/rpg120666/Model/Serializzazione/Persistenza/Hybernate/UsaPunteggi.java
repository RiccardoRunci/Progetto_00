package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

public final class UsaPunteggi
{
    private UsaPunteggi()
    {
        throw new UnsupportedOperationException("Questa classe non può essere istanziata.");
    }
    // L'EntityManagerFactory va creato UNA SOLA VOLTA per tutta l'applicazione (può essere static).
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Progetto");

    /**Inserisce una nuova statistica nel database in modo sicuro.*/
    public static void inserisciPunteggio(String nomeGiocatore, int nemiciRimasti, int oggettiRimasti)
    {
        // Validazione dei parametri
        if (nemiciRimasti < 0 || oggettiRimasti < 0 || nomeGiocatore == null || nomeGiocatore.isEmpty())
        {
            throw new IllegalArgumentException("Parametri non validi per l'inserimento del Punteggio!");
        }

        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();     // 1. Inizia la transazione
            // 2. Crea l'oggetto (usando LocalDate come visto in precedenza)
            Punteggi punteggi = new Punteggi(nomeGiocatore, nemiciRimasti, oggettiRimasti, new Date());
            em.persist(punteggi); // 3. Salva nel database
            em.getTransaction().commit(); // 4. Conferma la transazione

            System.out.println("Punteggio salvata con successo! ID generato: " + punteggi.getId());

        }
        catch (Exception e)
        {
            // In caso di errore, annulla tutto
            if (em.getTransaction().isActive()) { em.getTransaction().rollback(); }
            e.printStackTrace();
        }
        finally
        {
            // Chiude sempre l'EntityManager per evitare leak
            if (em != null && em.isOpen()) { em.close(); }
        }
    }

    public static List<Punteggi> ritornaTutteRighe()
    {
        EntityManager em = emf.createEntityManager();
        List<Punteggi> listaPunteggi = null;

        try
        {
            // Qui usi ESATTAMENTE il nome della tabella SQL ("Statistiche")
            String sql = "SELECT * FROM Punteggi";

            // Usi createNativeQuery passando la stringa SQL e la classe di entità di destinazione
            Query query = em.createNativeQuery(sql, Punteggi.class);

            // Esegui la query
            listaPunteggi = query.getResultList();

        } catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return listaPunteggi;
    }
    public static void cancellaTutteRighe()
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            // Qui usi ESATTAMENTE il nome della tabella SQL ("Statistiche")
            String sql = "DELETE FROM Punteggi";

            // Usi createNativeQuery passando la stringa SQL e la classe di entità di destinazione
            Query query = em.createNativeQuery(sql/*,Punteggi.class*/);
            /*Punteggi.class non serve per una delete. Serve solo quando devo inserire o recuperare un record*/

            // Esegui la query
            query.executeUpdate();
            //Questo che fa?
            em.getTransaction().commit();

        } catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }
    }

    public static void chiudiEMF()
    {
        emf.close();
    }
}