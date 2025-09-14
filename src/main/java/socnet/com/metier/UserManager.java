package socnet.com.metier;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import socnet.com.conf.HibernateUtil;
import socnet.com.dao.IInvitation;
import socnet.com.dao.IUser;
import socnet.com.dao.InvitationDao;
import socnet.com.dao.UserDao;
import socnet.com.entities.Invitation;
import socnet.com.entities.User;

public class UserManager implements IMetier{
	
	public IUser udao;
	public IInvitation idao;
	
	
	
	public UserManager() {
		udao = new UserDao();
		idao = new InvitationDao();
	}
	
	
	@Override
	public User inscription(User u) {
		u = udao.save(u);
		return u;
	}
	@Override
	public User signing(String log, String pass) {
		// Integrer Regle Metier par exemple check Date Time
		User u = udao.auth(log, pass);
		return u;
	}
	@Override
	public List<User> allusers() {
		return udao.findAll();
	}
	@Override
	public User findUser(long id) {
		// TODO Auto-generated method stub
		User u = udao.findById(id);
		return u;
	}
	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> allusers(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int inviter(long idus, long idur) {
		// Verification
		Invitation v = idao.isInvite(idus, idur);
		if (v!=null) {
			return -1;
		}
		else {
			idao.inviter(idus, idur);
		}
		return 1;
	}
	
	@Override
	public int accepter(long idus, long idur) {
		int n = idao.accepter(idus, idur);
		return n;
	}
	@Override
	public Invitation findinv(long id) {
		Invitation inv = idao.findById(id);
		return inv;
	}
	@Override
	public List<Invitation> allinv(long id, boolean state) {
		// TODO Auto-generated method stub
		List<Invitation> inv = idao.allinvitationrecieve(id, state);
		return inv;
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


	@Override
	public byte[] UserImage(long uid) {
		byte[] imagebytes = udao.getUserImage(uid);
		return imagebytes;
	}

}
