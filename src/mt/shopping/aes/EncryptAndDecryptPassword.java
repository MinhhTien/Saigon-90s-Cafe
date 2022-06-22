package mt.shopping.aes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptAndDecryptPassword {
	private static Connection con = null;
	public static final String AES = "AES";
	String key = null;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public Connection getLoginKey() {
		try {
			query = "select * from login_key";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			// Get the key value from login_key
			while (rs.next()) {
				key = rs.getString("keyValue");
			}
			// System.out.println("key=" + key);
		} catch (SQLException ex) {
			System.out.println("Exception.." + ex.getMessage());
		}
		return con;
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	// Utility method taking input as String and giving byte
	// array as output
	private static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

	public EncryptAndDecryptPassword(Connection con) {
		this.con = con;

	}

	public String encryptedPassword(String password) {
		getLoginKey();
		String encryptedPassword = encryptPassword(password);
		// closeConnection();
		return encryptedPassword;
	}


	// Get the details from GEEKPORTALLOGIN and update
	// encrypted password
	private String encryptPassword(String password) {
		String encryptedpwd = "";
		try {
			byte[] passwordByte = hexStringToByteArray(key);
			// System.out.println("keyValue.." + key);
			SecretKeySpec sks = new SecretKeySpec(passwordByte, EncryptAndDecryptPassword.AES);
			Cipher cipher = Cipher.getInstance(EncryptAndDecryptPassword.AES);
			cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
			byte[] encrypted = cipher.doFinal(password.getBytes());
			encryptedpwd = byteArrayToHexString(encrypted);
			// System.out.println("****************
			// Encrypted Password ****************");
			// System.out.println(encryptedpwd);
			// System.out.println("****************
			// Encrypted Password ****************");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return encryptedpwd;
	}

	private String decryptPassword(String encryptedPassword) {
		String decryptedPassword = null;
		try {
			byte[] bytekey = hexStringToByteArray(key);
			SecretKeySpec sks = new SecretKeySpec(bytekey, EncryptAndDecryptPassword.AES);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, sks);
			byte[] decrypted = cipher.doFinal(hexStringToByteArray(encryptedPassword));
			decryptedPassword = new String(decrypted);
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
		} catch (NoSuchPaddingException ex) {
			System.out.println(ex.getMessage());
		} catch (InvalidKeyException ex) {
			System.out.println(ex.getMessage());
		} catch (IllegalBlockSizeException ex) {
			System.out.println(ex.getMessage());
		} catch (BadPaddingException ex) {
			System.out.println(ex.getMessage());
		}
		return decryptedPassword;
	}

	public String decryptedPassword(String encryptedPassword) {
		getLoginKey();
		String decryptPassword = decryptPassword(encryptedPassword);
		// closeConnection();
		return decryptPassword;
	}
}
