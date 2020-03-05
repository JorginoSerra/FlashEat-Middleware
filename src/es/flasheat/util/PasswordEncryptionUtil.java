package es.flasheat.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordEncryptionUtil {
	// Stataless, asi que no tiene sentido instanciar uno por cada uso, en principio...
		private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();	
		
		public static final String encryptPassword(String password) {
			return PASSWORD_ENCRYPTOR.encryptPassword(password);
		}

		public static final boolean checkPassword(String plainPassword, String encryptedPassword) {
			if (PASSWORD_ENCRYPTOR.checkPassword(plainPassword, encryptedPassword)) {
				return true;
				// correct!
			} else {
				// bad login!
				return false;
			}
		}


	}
