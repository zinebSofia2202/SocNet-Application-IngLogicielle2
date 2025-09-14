package socnet.com.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import socnet.com.conf.HibernateUtil;
import socnet.com.controller.invitation;
import socnet.com.entities.Invitation;
import socnet.com.entities.User;

public class InvitationDao implements IInvitation{

	public Invitation save(Invitation u) {
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		ses.persist(u);
		ses.getTransaction().commit();
		ses.close();
		return u;
		
	}

	public Invitation findById(Long id) {
		// TODO Auto-generated method stub
		
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		
		Invitation v = ses.find(Invitation.class, id);
		ses.close();
		return v;
	}

	public List<Invitation> findAll() {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		
		List<Invitation> vs = ses.createQuery("From Invitation",Invitation.class).list();
		ses.close();
		return vs;
	}

	public int inviter(long idus, long idur) {
		Invitation v=new Invitation();
		v.setDate(LocalDate.now());
		v.setState(false);
		
		
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		
		User us = ses.find(User.class, idus);
		User ur = ses.find(User.class, idur);
		v.setUsend(us);
		v.setUrecieve(ur);
		
		
		ses.persist(v);
		ses.getTransaction().commit();
		ses.close();
		
		
		return 0;
	}

	public int accepter(long idus, long idur) {

		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
		
		List<Invitation> invs = ses.createQuery("From Invitation v"
				+ " where v.usend.id="+idus+""
						+ " and v.urecieve.id="+idur,Invitation.class)
				.list();
		Invitation inv;
		if (invs.size()>0) {
			inv =  invs.get(0);
			inv.setState(true);
			ses.update(inv);
			ses.getTransaction().commit();
			return 1;
		}
			
		ses.close();
		return -1;
	}

	public List<Invitation> allinvitationrecieve(long idu, boolean state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitation isInvite(long idu1, long idu2) {
		SessionFactory sf = HibernateUtil.getsf();
		Session ses = sf.openSession();
		ses.beginTransaction();
				

		List<Invitation> vs=ses.createQuery("From Invitation v  "
				+"where v.usend.id=" +idu1+""
				+" and v.urecieve.id="+idu2+""
				+" or  v.usend.id="+idu2+""
				+" and v.urecieve.id="+idu1+""
		, Invitation.class).list();
		if (vs.size()>0) {
			return vs.get(0);
		}
		ses.close();
		return null;
	}

}
