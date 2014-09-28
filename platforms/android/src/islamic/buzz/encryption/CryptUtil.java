
package islamic.buzz.encryption;

import javax.crypto.SecretKey;

public class CryptUtil {

	private String passcode;
    public CryptUtil(String passcode) {
    	this.passcode = passcode;
	}
    public String doCrypto(String plaintext) {
        return PBKDF2_ENCRYPTOR.encrypt(plaintext, this.passcode);
    }

    public String doDeCrypto(String ciphertext) {
        return PBKDF2_ENCRYPTOR.decrypt(ciphertext, this.passcode);
    }

    private abstract class Encryptor {
        SecretKey key;

        abstract SecretKey deriveKey(String passpword,
                byte[] salt);

        abstract String encrypt(String plaintext,
                String password);

        abstract String decrypt(String ciphertext,
                String password);

        String getRawKey() {
            if (key == null) {
                return null;
            }

            return Crypt.toHex(key.getEncoded());
        }
    }

    private final Encryptor PBKDF2_ENCRYPTOR = new Encryptor() {

        @Override
        public SecretKey deriveKey(String password,
                byte[] salt) {
            return Crypt.deriveKeyPbkdf2(salt, password);
        }

        @Override
        public String encrypt(String plaintext,
                String password) {
            byte[] salt = Crypt.generateSalt();
            key = deriveKey(password, salt);
            return Crypt.encrypt(plaintext, key, salt);
        }

        @Override
        public String decrypt(String ciphertext,
                String password) {
            return Crypt.decryptPbkdf2(ciphertext, password);
        }
    };

}
