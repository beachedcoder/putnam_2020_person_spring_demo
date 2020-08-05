package com.putnam.demos.java.domain;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public abstract class AbstractEntity<ID> implements Persistable<ID> {

	@Transient
	private boolean isNew;

	{
		this.isNew = true;
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}
	
	@PrePersist
	@PostLoad
	void markNotNew() {
		this.isNew =false;
	}

}
