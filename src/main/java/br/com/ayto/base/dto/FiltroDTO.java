package br.com.ayto.base.dto;

public class FiltroDTO extends BaseDTO {
	private static final long serialVersionUID = 2107529864066708875L;

	private int primeiroReg;
	private int qtdePorPagina;

	public int getPrimeiroReg() {
		return primeiroReg;
	}

	public void setPrimeiroReg(int primeiroReg) {
		this.primeiroReg = primeiroReg;
	}

	public int getQtdePorPagina() {
		return qtdePorPagina;
	}

	public void setQtdePorPagina(int qtdePorPagina) {
		this.qtdePorPagina = qtdePorPagina;
	}

}
