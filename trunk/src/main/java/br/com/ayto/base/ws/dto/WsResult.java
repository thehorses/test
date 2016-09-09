package br.com.ayto.base.ws.dto;

import java.util.List;

public class WsResult {

	public enum Status {
		OK, ALERTA, ERRO
	}

	private Status status = Status.OK;
	private String mensagem = "Operação efetuada com sucesso.";
	private List<String> erros;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}
