package br.com.ayto.base.util;

import java.util.Properties;

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

import org.apache.commons.collections4.CollectionUtils;

import br.com.ayto.base.dto.AnexoEmail;
import br.com.ayto.base.dto.Email;

public class MailUtil {

	private static Session session;
	static {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");

		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("thehorses@gmail.com", "java0202");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);
	}

	public static void main(String[] args) {

		try {

			Email email = new Email();
			email.setDestCopia("tosani@gmail.com");
			email.setAssunto("Autenticacao do cadastro de usuario no OS - AYTO");
			email.setConteudo("<h1>Titulo</h1><br>Ola!");
//			AnexoEmail anexo = new AnexoEmail();
//			anexo.setNome("prt.pdf");
//			anexo.setArquivo(new File("C:/Users/PRT/Desktop/Manual_Vectra_2011.pdf"));
//			email.addAnexo(anexo);
			enviar(email);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void enviar(Email email) {

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("thehorses@gmail.com", "OS - AYTO"));

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
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static Address[] montarAddress(String[] address) throws AddressException {
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
