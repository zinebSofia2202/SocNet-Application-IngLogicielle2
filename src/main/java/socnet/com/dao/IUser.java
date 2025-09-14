package socnet.com.dao;

import socnet.com.entities.User;

public interface IUser extends IDao<User, Long>{

	public User auth(String log, String pass);

	public byte[] getUserImage(long uid);
	
}
