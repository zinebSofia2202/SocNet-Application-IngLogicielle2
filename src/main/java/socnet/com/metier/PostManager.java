package socnet.com.metier;

import java.util.List;

import socnet.com.dao.IInvitation;
import socnet.com.dao.IPost;
import socnet.com.dao.IUser;
import socnet.com.dao.InvitationDao;
import socnet.com.dao.PostDao;
import socnet.com.dao.UserDao;
import socnet.com.entities.Invitation;
import socnet.com.entities.Post;
import socnet.com.entities.User;

public class PostManager implements IPostMetier{
	
	public IUser udao;
	public IPost pdao;
	
	public PostManager() {
		udao = new UserDao();
		pdao = new PostDao();
	}
	
	
	@Override
	public List<Post> allposts() {
		List<Post> pts = pdao.allpost();
		return pts;
	}
	@Override
	public Post findPost(long id) {
		return pdao.findPost(id);
	}

	@Override
	public byte[] PostImage(long pid) {
		byte[] imagebytes = pdao.getUserImage(pid);
		return imagebytes;
	}

}



