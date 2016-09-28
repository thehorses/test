package br.com.ayto.base.bd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.ayto.base.ws.dto.WsResult;
import br.com.ayto.base.ws.dto.WsResult.Status;

public class BaseBD {
	private static final Log LOG = LogFactory.getLog(BaseBD.class);

	public <T extends WsResult> T anexarErroResult(T wsResult, Throwable e, Class<T> clazz) {
		if (wsResult == null) {
			try {
				wsResult = clazz.newInstance();
			} catch (Exception ei) {
				LOG.debug(ei);
				return null;
			}
		}
		wsResult.setStatus(Status.ERRO);
		wsResult.setMensagem(e.getMessage());
		return wsResult;
	} 
}
