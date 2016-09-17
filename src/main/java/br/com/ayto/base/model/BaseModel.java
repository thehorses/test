package br.com.ayto.base.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -5274413044083501380L;

	public abstract Serializable getId();

	public boolean isIdNull() {
		return getId() == null;
	}

}
