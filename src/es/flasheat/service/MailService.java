package es.flasheat.service;

import es.flasheat.util.MailException;


public interface MailService {

	public void sendMail(String subject, String message, String... to)
			throws MailException;

}
