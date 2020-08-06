package com.putnam.demos.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;


@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonIgnore
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
