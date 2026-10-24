package it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

public final class UsaStatistiche
{
    private UsaStatistiche()
    {
        throw new UnsupportedOperationException("Questa classe non può essere istanziata.");
    }
    // L'EntityManagerFactory va creato UNA SOLA VOLTA per tutta l'applicazione (può essere static).
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Progetto");

    /**Inserisce una nuova statistica nel database in modo sicuro.*/
    public static void inserisciStatistica(String nomeGiocatore, int nemiciRimasti, int oggettiRimasti)
    {
        // Validazione dei parametri
        if (nemiciRimasti < 0 || oggettiRimasti < 0 || nomeGiocatore == null || nomeGiocatore.isEmpty())
        {
            throw new IllegalArgumentException("Parametri non validi per l'inserimento della statistica!");
        }

        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();     // 1. Inizia la transazione
            // 2. Crea l'oggetto (usando LocalDate come visto in precedenza)
            Statistica statistica = new Statistica(nomeGiocatore, nemiciRimasti, oggettiRimasti, new Date());
            em.persist(statistica); // 3. Salva nel database
            em.getTransaction().commit(); // 4. Conferma la transazione

            System.out.println("Statistica salvata con successo! ID generato: " + statistica.getId());

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

    public static List<Statistica> ritornaTutteRighe()
    {
        EntityManager em = emf.createEntityManager();
        List<Statistica> listaStatistiche = null;

        try
        {
            // Qui usi ESATTAMENTE il nome della tabella SQL ("Statistiche")
            String sql = "SELECT * FROM Statistica";

            // Usi createNativeQuery passando la stringa SQL e la classe di entità di destinazione
            Query query = em.createNativeQuery(sql, Statistica.class);

            // Esegui la query
            listaStatistiche = query.getResultList();

        } catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return listaStatistiche;
    }

    public static void chiudiEMF()
    {
        emf.close();
    }
}