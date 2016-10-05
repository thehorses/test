package br.com.ayto.base.dto;

import java.io.File;

public class AnexoEmail extends BaseDTO {

	private static final long serialVersionUID = -2200470918918614586L;
	private String nome;
	private File arquivo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

}
