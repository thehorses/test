package br.com.ayto.base.dto;

import java.util.ArrayList;
import java.util.List;

public class Email extends BaseDTO {
	private static final long serialVersionUID = -901500066059882271L;

	private String assunto;
	private String conteudo;
	private String[] destPara;
	private String[] destCopia;
	private String[] destCopiaOculta;
	private List<AnexoEmail> anexos;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setConteudo(StringBuilder conteudo) {
		this.conteudo = conteudo.toString();
	}

	public String[] getDestPara() {
		return destPara;
	}

	public void setDestPara(String... destPara) {
		this.destPara = destPara;
	}

	public String[] getDestCopia() {
		return destCopia;
	}

	public void setDestCopia(String... destCopia) {
		this.destCopia = destCopia;
	}

	public String[] getDestCopiaOculta() {
		return destCopiaOculta;
	}

	public void setDestCopiaOculta(String... destCopiaOculta) {
		this.destCopiaOculta = destCopiaOculta;
	}

	public List<AnexoEmail> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<AnexoEmail> anexos) {
		this.anexos = anexos;
	}

	public void addAnexo(AnexoEmail anexo) {
		if (anexos == null) {
			anexos = new ArrayList<AnexoEmail>();
		}
		anexos.add(anexo);
	}

}
