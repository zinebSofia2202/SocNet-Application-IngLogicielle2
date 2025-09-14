package socnet.com.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import socnet.com.conf.HibernateUtil;
import socnet.com.entities.User;

public class UserDao implements IUser{

	public User save(User u) {
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		ses.persist(u);
		ses.getTransaction().commit();
		ses.close();
		return u;
	}

	public User findById(Long id) {
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		User u = ses.find(User.class, id);
		ses.close();
		return u;
	}

	public List<User> findAll() {
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		List<User> us = ses.createQuery("from User", User.class).list();
		ses.close();
		return us;
	}

	public User auth(String log, String pass) {
		
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		User u = ses.createQuery("From User u where u.username='"+log+"' and u.password='"+pass+"'", User.class).list().get(0);
		
		ses.close();
		return u;
	}
	

	public byte[] getUserImage(long uid) {
		
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		byte[] image;
		image = ses.createQuery("SELECT u.image FROM User u WHERE u.id = :uid", byte[].class)
                .setParameter("uid", uid)
                .getSingleResult();
		if (image == null) {	
			image = getDefaultImage();  // Call method to load the default image
		}
		
		ses.close();
		return image;
	}
	
	  // Load default image from resources
	private byte[] getDefaultImage() {
	    try (InputStream is = getClass().getClassLoader().getResourceAsStream("static/images/default_profile.png")) {
	        if (is != null) {
	            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	            byte[] data = new byte[1024];
	            int nRead;
	            while ((nRead = is.read(data, 0, data.length)) != -1) {
	                buffer.write(data, 0, nRead);
	            }
	            return buffer.toByteArray();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return new byte[0];  // Return empty byte array if the default image is missing
	}



}
