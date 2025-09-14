package socnet.com.dao;

// Remove all jakarta.persistence imports related to EntityManager/Factory/Query
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.EntityManagerFactory;
// import jakarta.persistence.Persistence;
// import jakarta.persistence.NoResultException; // Keep if needed for specific native Hibernate query exceptions
// import jakarta.persistence.Query;

import org.hibernate.Session;       // Import Hibernate Session
import org.hibernate.SessionFactory; // Import Hibernate SessionFactory
import org.hibernate.Transaction;    // Import Hibernate Transaction
import org.hibernate.query.Query;    // Import Hibernate's native Query (org.hibernate.query.Query)

import socnet.com.conf.HibernateUtil; // Your custom HibernateUtil
import socnet.com.entities.Post;
import socnet.com.entities.Reaction;
import socnet.com.entities.ReactionType; // Still needed for the enum itself
import socnet.com.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactionDao {

    private SessionFactory sessionFactory; // Changed from EntityManagerFactory

    public ReactionDao() {
        sessionFactory = HibernateUtil.getsf(); // Use your HibernateUtil
    }

    public void addReaction(Reaction reaction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(reaction); // Use session.persist
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error adding reaction: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public void updateReaction(Reaction reaction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(reaction); // Use session.merge
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error updating reaction: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public void deleteReaction(Reaction reaction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // To delete, the entity must be in a managed state or re-fetched.
            // session.remove() works directly if the entity is managed.
            // If the reaction object passed in is detached, you need to re-fetch it first.
            Reaction managedReaction = session.get(Reaction.class, reaction.getId()); // Use session.get
            if (managedReaction != null) {
                session.remove(managedReaction);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error deleting reaction: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public Reaction getReactionByUserAndPost(User user, Post post) {
        Session session = sessionFactory.openSession();
        Reaction reaction = null;
        try {
            reaction = session.createQuery(
                            "SELECT r FROM Reaction r WHERE r.user = :user AND r.post = :post", Reaction.class)
                            .setParameter("user", user)
                            .setParameter("post", post)
                            .uniqueResult(); // uniqueResult returns null if no result
        } catch (org.hibernate.NonUniqueResultException e) { // <--- ONLY CATCH THIS ONE!
            // This exception means there was more than one reaction for the same user and post,
            // which usually indicates a data integrity issue or a logical error in the query/requirements.
            System.err.println("Non-unique reaction found for user " + user.getId() + " and post " + post.getId() + ": " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging
            return null; // Return null as it's an unexpected state or handle as an application error
        } catch (Exception e) {
            System.err.println("Error getting reaction by user and post: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reaction;
    }

    public Map<String, Long> getReactionCountsForPost(long postId) {
        Session session = sessionFactory.openSession();
        Map<String, Long> counts = new HashMap<>();
        try {
            Query<Object[]> query = session.createQuery(
                "SELECT r.reactionType, COUNT(r.id) FROM Reaction r WHERE r.post.id = :postId GROUP BY r.reactionType",
                Object[].class
            );
            query.setParameter("postId", postId);

            List<Object[]> results = query.list();
            for (Object[] result : results) {
                // The problem is here: Hibernate is returning ReactionType enum object, not String
                // Cast to ReactionType, then get its name
                ReactionType reactionTypeEnum = (ReactionType) result[0]; // <--- CAST TO ReactionType
                String reactionTypeName = reactionTypeEnum.name();      // <--- GET THE NAME AS STRING
                Long count = (Long) result[1];
                counts.put(reactionTypeName, count);
            }
        } catch (Exception e) {
            System.err.println("Error getting reaction counts for post: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return counts;
    }
}