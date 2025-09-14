package socnet.com.conf;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import socnet.com.entities.Invitation;
import socnet.com.entities.Post;
import socnet.com.entities.Reaction;
import socnet.com.entities.Comment;

import socnet.com.entities.User;

public class HibernateUtil {

	public static SessionFactory getsf()
	{ 
		Configuration configuration = new Configuration();
	ServiceRegistry serviceRegistry=
     new StandardServiceRegistryBuilder()
	 .applySettings(configuration.getProperties())
	 .build();
	 configuration.addAnnotatedClass(User.class);
	 configuration.addAnnotatedClass(Invitation.class);
	 configuration.addAnnotatedClass(Post.class);
	 configuration.addAnnotatedClass(Reaction.class);
	 configuration.addAnnotatedClass(Comment.class);

   
	 SessionFactory  sf= configuration
	       .buildSessionFactory(serviceRegistry);
	    return sf;
	}
}
