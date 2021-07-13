package kr.co.maptics.mapticslogin.util;

import kr.co.maptics.mapticslogin.exception.EncryptException;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	private static final Charset CHARSET = StandardCharsets.UTF_8;
	private static final String PADDING = "AES/CBC/PKCS5Padding";

	private String iv;
	private Key keySpec;

	public EncryptUtil(String key) {
		this.iv = key.substring(0, 16);

		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes(CHARSET);
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);

		this.keySpec = new SecretKeySpec(keyBytes, "AES");
	}


	// 암호화
	public String aesEncode(String str) {
		try {
			Cipher c = Cipher.getInstance(PADDING);
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

			byte[] encrypted = c.doFinal(str.getBytes(CHARSET));

			return new String(Base64.encodeBase64(encrypted));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new EncryptException("Encryption error.");
		}
	}


	//복호화
	public String aesDecode(String str) {

		try {
			Cipher c = Cipher.getInstance(PADDING);
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes(CHARSET)));

			byte[] byteStr = Base64.decodeBase64(str.getBytes());

			return new String(c.doFinal(byteStr),CHARSET);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new EncryptException("Decryption error.");
		}
	}

}
