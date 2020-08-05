package com.putnam.demos.java.domain;


import java.io.PrintStream;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;


@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Transient
	private boolean isNew = true;
	
	@Override
	public Long getId(){
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}

	@PostPersist
	@PostLoad
	@PostUpdate
	void markNotNew() {
		this.isNew =false;
	}

}
