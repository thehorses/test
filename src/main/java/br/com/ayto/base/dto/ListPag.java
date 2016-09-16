package br.com.ayto.base.dto;

import java.util.List;

import br.com.ayto.base.model.BaseModel;

//Tem um BUG no Eclipse que se extender um List com Generics, ele fica fechando sozinha sempre que usa a classe.
// :(

//public class ListPag<E extends BaseModel> extends ArrayList<E> implements java.util.List<E> {

public class ListPag<E extends BaseModel> extends BaseDTO {
	private static final long serialVersionUID = -4022291044372347268L;

	private int qtdeRegistros;
	private List<E> lista;

	public ListPag() {
	}

	public ListPag(List<E> lista, int qtdeRegistros) {
		this.lista = lista;
		this.qtdeRegistros = qtdeRegistros;
		
	}

	public int getQtdeRegistros() {
		return qtdeRegistros;
	}

	public void setQtdeRegistros(int qtdeRegistros) {
		this.qtdeRegistros = qtdeRegistros;
	}

	public List<E> getLista() {
		return lista;
	}

	public void setLista(List<E> lista) {
		this.lista = lista;
	}

}
