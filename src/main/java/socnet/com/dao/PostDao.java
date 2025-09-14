package socnet.com.dao;

import org.hibernate.Session;       // Import Hibernate Session
import org.hibernate.SessionFactory; // Import Hibernate SessionFactory
import org.hibernate.Transaction;    // Import Hibernate Transaction
import org.hibernate.query.IllegalSelectQueryException;


import socnet.com.conf.HibernateUtil; // Your custom HibernateUtil
import socnet.com.entities.Post;
import socnet.com.entities.Reaction; // Still needed for entities
import socnet.com.entities.User;     // Still needed for entities

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PostDao implements IPost {

    private SessionFactory sessionFactory; // Changed from EntityManagerFactory

    public PostDao() {
        sessionFactory = HibernateUtil.getsf(); // Use your HibernateUtil
    }

    // --- Methods implementing IDao<Post, Long> ---
    @Override
    public Post save(Post p) {
        Session session = sessionFactory.openSession(); // Open a Session
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(p); // Use session.persist for new entities
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Log the exception for debugging
            System.err.println("Error saving post: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw the exception after rollback
        } finally {
            session.close(); // Close the session
        }
        return p;
    }

    @Override
    public Post findById(Long id) {
        Session session = sessionFactory.openSession();
        Post p = null;
        try {
            // session.get() is the equivalent of em.find() for primary key lookup
            p = session.get(Post.class, id);
        } catch (Exception e) {
            System.err.println("Error finding post by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return p;
    }

    @Override
    public List<Post> findAll() {
        Session session = sessionFactory.openSession();
        List<Post> posts = null;
        try {
            // Use session.createQuery for HQL queries
            posts = session.createQuery("FROM Post ORDER BY id DESC", Post.class).list(); // .list() for Hibernate native
        } catch (Exception e) {
            System.err.println("Error finding all posts: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return posts;
    }

    // --- Methods implementing IPost (specific to Post) ---

    @Override
    public Post add(Post p) {
        // This is fine, just calls the save method
        return save(p);
    }

    @Override
    public Post findPost(long id) {
        return findById(id);
    }

    @Override
    public List<Post> allpost() {
        return findAll();
    }

    @Override
    public List<Post> allpost(LocalDate date) {
        Session session = sessionFactory.openSession();
        List<Post> posts = null;
        try {
            posts = session.createQuery("FROM Post p WHERE p.date = :date", Post.class)
                           .setParameter("date", date)
                           .list(); // .list() for Hibernate native
        } catch (Exception e) {
            System.err.println("Error finding posts by date: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return posts;
    }

    @Override
    public List<Post> allpost(long idu) {
        Session session = sessionFactory.openSession();
        List<Post> posts = null;
        try {
            posts = session.createQuery("FROM Post p WHERE p.user.id = :userId", Post.class)
                           .setParameter("userId", idu)
                           .list(); // .list() for Hibernate native
        } catch (Exception e) {
            System.err.println("Error finding posts by user ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return posts;
    }

    @Override
    public byte[] getUserImage(long pid) {
        Session session = sessionFactory.openSession();
        byte[] image = null;
        try {
            // For single results, use .uniqueResult() in native Hibernate
        	image = (byte[]) session.createQuery("SELECT p.image FROM Post p WHERE p.id = :pid", byte[].class) // <--- ADDED byte[].class
                    .setParameter("pid", pid)
                    .uniqueResult();
            if (image == null) {
                // If a post exists but has no image, try the default
                image = getDefaultImage();
            }
        } catch (IllegalSelectQueryException e) { // Catch Hibernate-specific query exceptions
            System.err.println("QueryException for user image: " + e.getMessage());
            e.printStackTrace();
            // This might catch cases where no result is found, but null is also handled.
        } catch (Exception e) {
             System.err.println("General error getting user image: " + e.getMessage());
             e.printStackTrace();
        } finally {
            session.close();
        }
        return image;
    }

    // Load default image from resources
    private byte[] getDefaultImage() {
        try (java.io.InputStream is = getClass().getClassLoader().getResourceAsStream("static/images/default_profile.png")) {
            if (is != null) {
                java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int nRead;
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                return buffer.toByteArray();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}