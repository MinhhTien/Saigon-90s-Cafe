package mt.shopping.aes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CreateLoginKey {
	public static final String AES = "AES";
	private static Connection con = null;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public CreateLoginKey(Connection con) {
		this.con = con;
	}

	// This method generates a random key and stores in
	// login_key table In the full program, let us see whole
	// connection details
	public void oneTimeKeyGeneration()
	{
	    try {
	        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
	        keyGen.init(128);
	        SecretKey sk = keyGen.generateKey();
	        String key = byteArrayToHexString(sk.getEncoded());
	        System.out.println("key:" + key);
	        getAndStoreLoginKey(key);
	    }
	    catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	}
	private static String byteArrayToHexString(byte[] b)
	{
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
	 
	private void getAndStoreLoginKey(String key)
	{
	    try {

	        query
	            = "INSERT INTO login_key(keyValue) VALUES(?)";
	 
	        PreparedStatement pst = con.prepareStatement(query);
	 
	        pst.setString(1, key);
	        pst.executeUpdate();
	    }
	 
	    catch (SQLException ex) {
	        System.out.println("Exception.." + ex.getMessage());
	    }
	}
	
}
