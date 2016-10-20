package br.com.ayto.base.util;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.ayto.base.dto.AnexoEmail;
import br.com.ayto.base.dto.Email;

public class MailUtil {
	private static final Log LOG = LogFactory.getLog(MailUtil.class);

	private static MailUtil instance;

	private Session session;

	private MailUtil() throws NamingException {
		configurar();
	}

	public static MailUtil getInstance() throws NamingException {
		if (instance == null) {
			instance = new MailUtil();
		}
		return instance;
	}

	private void configurar() throws NamingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", ConfigUtil.getValueContextParm("mail.smtp.host"));
		props.put("mail.smtp.socketFactory.port", ConfigUtil.getValueContextParm("mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", ConfigUtil.getValueContextParm("mail.smtp.auth"));
		props.put("mail.smtp.port", ConfigUtil.getValueContextParm("mail.smtp.port"));
		props.put("mail.smtp.starttls.enable", ConfigUtil.getValueContextParm("mail.smtp.starttls.enable"));

		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				try {
					return new PasswordAuthentication(ConfigUtil.getValueContextParm("mail.smtp.username"), ConfigUtil.getValueContextParm("mail.smtp.password"));
				} catch (NamingException e) {
					LOG.error(e);
				}
				return null;
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(Boolean.getBoolean(ConfigUtil.getValueContextParm("mail.session.debug")));

		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
	}

	public void enviar(Email email) {
		try {
			Thread.currentThread().setContextClassLoader(javax.mail.Message.class.getClassLoader());
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ConfigUtil.getValueContextParm("mail.smtp.username"), ConfigUtil.getValueContextParm("mail.smtp.personal")));

			if (ConfigUtil.isAmbienteProd()) {
				Address[] toUser = montarAddress(email.getDestPara());
				Address[] ccUser = montarAddress(email.getDestCopia());
				Address[] bccUser = montarAddress(email.getDestCopiaOculta());

				if (toUser != null) {
					message.setRecipients(Message.RecipientType.TO, toUser);
				}
				if (ccUser != null) {
					message.setRecipients(Message.RecipientType.CC, ccUser);
				}
				if (bccUser != null) {
					message.setRecipients(Message.RecipientType.BCC, bccUser);
				}
			} else {
				message.setRecipients(Message.RecipientType.TO, montarAddress(ConfigUtil.getValueContextParm("ambiente.teste.email.destinatario")));
			}
			message.setSubject(email.getAssunto());

			Multipart multipart = new MimeMultipart();

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(email.getConteudo(), "text/html");
			multipart.addBodyPart(mimeBodyPart);

			if (!CollectionUtils.isEmpty(email.getAnexos())) {
				for (AnexoEmail anexo : email.getAnexos()) {
					MimeBodyPart attachPart = new MimeBodyPart();
					attachPart.attachFile(anexo.getArquivo());
					attachPart.setFileName(anexo.getNome());
					multipart.addBodyPart(attachPart);
				}
			}
			message.setContent(multipart);

			Transport.send(message);
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}

	private static Address[] montarAddress(String... address) throws AddressException {
		if (address == null || address.length == 0) {
			return null;
		}
		Address[] list = new Address[address.length];
		for (int i = 0; i < address.length; i++) {
			list[i] = new InternetAddress(address[i]);
		}
		return list;
	}

}
