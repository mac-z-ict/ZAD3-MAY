package pl.edu.pja.jaz.dao;

import java.util.Set;

import pl.edu.pja.jaz.domain.HasId;

public interface Dao<T extends HasId> {
	public Set<T> list();
	public T create(T entity);
	public T get(int id);
	public T update(int id, T entity);
	public void delete(int id);
}
