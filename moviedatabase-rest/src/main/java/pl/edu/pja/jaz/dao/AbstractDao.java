package pl.edu.pja.jaz.dao;

import pl.edu.pja.jaz.domain.HasId;

public abstract class AbstractDao<T extends HasId> implements Dao<T> {
	protected static final Db db = Db.INSTANCE;

	protected abstract void copy(T source, T destination);
	protected abstract T newInstance(int id);
	
	@Override
	public T create(T entity) {
		int id = list().size();
		T t = newInstance(id);
		copy(entity, t);
		list().add(t);
		return t;
	}

	@Override
	public T get(int id) {
		for (T t : list()) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	@Override
	public T update(int id, T entity) {
		T t = get(id);
		copy(entity, t);
		return t;
	}

	@Override
	public void delete(int id) {
		T t = get(id);
		if (t != null) {
			list().remove(t);
		}
	}
}
