package socnet.com;

import java.time.LocalDate;

import socnet.com.dao.UserDao;
import socnet.com.entities.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDao dao=new UserDao();
		User u=new User("Administrator", "cc", "aaa", LocalDate.now());
		dao.save(u);
		//User ut = dao.auth("ff", "fff");
		//System.out.println(ut.getNom());
	}

}
