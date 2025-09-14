package socnet.com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import socnet.com.conf.HibernateUtil;
import socnet.com.entities.Comment;

import java.util.List;

public class CommentDao implements IComment {

    private SessionFactory sessionFactory = HibernateUtil.getsf();

    @Override
    public Comment save(Comment c) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(c);
            tx.commit();
        }
        return c;
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Comment WHERE post.id = :postId", Comment.class)
                          .setParameter("postId", postId)
                          .list();
        }
    }

    @Override
    public Comment findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Comment.class, id);
        }
    }

    @Override
    public List<Comment> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Comment", Comment.class).list();
        }
    }
}
