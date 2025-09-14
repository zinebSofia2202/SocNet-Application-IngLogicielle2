package socnet.com.dao;

import java.util.List;

public interface IDao<T, K> {

	public T save(T u);
	public  T findById(K id);
	public List<T> findAll();
	
}
