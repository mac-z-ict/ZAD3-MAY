package pl.edu.pja.jaz.service;

import java.util.Set;

import pl.edu.pja.jaz.dao.Dao;
import pl.edu.pja.jaz.domain.HasId;

public abstract class AbstractService<E extends HasId> implements Service<E> {
	
	protected Dao<E> dao;
	
	public AbstractService(Dao<E> dao) {
		this.dao = dao;
	}

	@Override
	public Set<E> list() {
		return dao.list();
	}

	@Override
	public E create(E entity) {
		return dao.create(entity);
	}

	@Override
	public E get(int id) {
		return dao.get(id);
	}

	@Override
	public E update(int id, E entity) {
		return dao.update(id, entity);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}
	
}
