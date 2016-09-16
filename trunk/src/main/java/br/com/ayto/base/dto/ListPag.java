package br.com.ayto.base.dto;

import java.util.Collection;

import br.com.ayto.base.model.BaseModel;

public class ListPag<T extends BaseModel> /*extends ArrayList<T> implements java.util.List<T>*/ {

	private static final long serialVersionUID = 215258710427357558L;

	private int qtdeRegistros;

	public ListPag() {
	}

	public ListPag(Collection<T> list) {
		//this.addAll(list);
	}

	public int getQtdeRegistros() {
		return qtdeRegistros;
	}

	public void setQtdeRegistros(int qtdeRegistros) {
		this.qtdeRegistros = qtdeRegistros;
	}

}
