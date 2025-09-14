package socnet.com.metier;

import java.util.List;

import socnet.com.entities.Invitation;
import socnet.com.entities.User;

public interface IMetier {
	public User inscription(User u);
	public User signing(String log, String pass);
	public List<User> allusers();
	public User findUser(long id );
	public User findUser(String username );
	public List<User> allusers(String nom);
	
	public int inviter(long idus, long idur);
	public int accepter(long idus, long idur);
	public Invitation findinv(long id);
	public List<Invitation> allinv(long id, boolean state);
	
	public Invitation isInvite(long id2, long idu2);
	
	public byte[] UserImage(long uid);
	

}
