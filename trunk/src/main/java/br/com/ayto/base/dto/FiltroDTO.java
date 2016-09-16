package br.com.ayto.base.dto;

public class FiltroDTO extends BaseDTO {
	private static final long serialVersionUID = 2107529864066708875L;

	private int primeiroReg;
	private int qtdePagina;
	private boolean todosReg;

	public int getPrimeiroReg() {
		return primeiroReg;
	}

	public void setPrimeiroReg(int primeiroReg) {
		this.primeiroReg = primeiroReg;
	}

	public int getQtdePagina() {
		return qtdePagina;
	}

	public void setQtdePagina(int qtdePagina) {
		this.qtdePagina = qtdePagina;
	}

	public boolean isTodosReg() {
		return todosReg;
	}

	public void setTodosReg(boolean todosReg) {
		this.todosReg = todosReg;
	}

}
