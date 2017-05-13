package pl.edu.pja.jaz.domain;

import java.io.Serializable;

public abstract class AbstractEntity implements HasId, Serializable {
	
	protected int id;
	
	public AbstractEntity(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
}
