package pl.edu.pja.jaz.service;

import pl.edu.pja.jaz.dao.Dao;
import pl.edu.pja.jaz.domain.HasId;

public interface Service<E extends HasId> extends Dao<E> {
}
