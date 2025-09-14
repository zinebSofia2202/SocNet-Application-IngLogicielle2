package socnet.com.dao;

import java.util.List;

import socnet.com.entities.Invitation;

public interface IInvitation extends IDao<Invitation, Long> {

	public int inviter(long idus, long idur);
	public int accepter(long idus, long idur);
	public List<Invitation> allinvitationrecieve(long idu, boolean state);
	
	public Invitation isInvite(long id2, long idu2);
	
}
