package socnet.com.metier;

import java.util.List;

import socnet.com.entities.Post;

public interface IPostMetier{

	public List<Post> allposts();
	public Post findPost(long id);
	public byte[] PostImage(long pid);
	
}
