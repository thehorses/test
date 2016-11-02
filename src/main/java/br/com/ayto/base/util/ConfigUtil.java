package br.com.ayto.base.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConfigUtil {

	public static final String getValueContextParm(String key) throws NamingException {
		InitialContext initialContext = new InitialContext();
		Context environmentContext = (Context) initialContext.lookup("java:/comp/env");
		String value = (String) environmentContext.lookup(key);
		return value;
	}

	public static final boolean isAmbienteProd() throws NamingException {
		return Boolean.parseBoolean(ConfigUtil.getValueContextParm("ambiente.producao"));
	}
}
