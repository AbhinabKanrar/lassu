/**
 * 
 */
package com.lassu.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

/**
 * @author abhinab
 *
 */
public class CommonUtil {

	private static final byte[] ENCRYPTION_KEY = "&^^%44$$&*&Gy&%&".getBytes();
	private static String HOSTNAME = null;
	
	public static String encrypt(String subject)
			throws DataLengthException, IllegalStateException, InvalidCipherTextException {
		BlowfishEngine engine = new BlowfishEngine();
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(engine);
		KeyParameter key = new KeyParameter(ENCRYPTION_KEY);
		cipher.init(true, key);
		byte in[] = subject.getBytes();
		byte out[] = new byte[cipher.getOutputSize(in.length)];
		int len = cipher.processBytes(in, 0, in.length, out, 0);
		cipher.doFinal(out, len);
		return new String(Base64.encode(out));
	}

	public static String decrypt(String subject) throws Exception {
		BlowfishEngine engine = new BlowfishEngine();
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(engine);
		StringBuffer result = new StringBuffer();
		KeyParameter key = new KeyParameter(ENCRYPTION_KEY);
		cipher.init(false, key);
		byte decodedSubject[] = Base64.decode(subject);
		byte generatedSubject[] = new byte[cipher.getOutputSize(decodedSubject.length)];
		int len = cipher.processBytes(decodedSubject, 0, decodedSubject.length, generatedSubject, 0);
		cipher.doFinal(generatedSubject, len);
		String s2 = new String(generatedSubject);
		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			if (c != 0) {
				result.append(c);
			}
		}

		return result.toString();
	}

	public static String getHostname() {
		if (HOSTNAME != null) {
			return HOSTNAME;
		} else {
			try {
				HOSTNAME = InetAddress.getLocalHost().getHostName();
				return HOSTNAME;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
}
