package model;
import org.apache.commons.codec.binary.Hex;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class KeyGeneratorFile {
	
	
	private String password;
    private String salt;
    private int iterations;
    private int keyLength;
    private char[] passwordChars;
    private byte[] saltBytes;
    private byte[] key;
    
    
    public KeyGeneratorFile(String password) {
    	
    	
    	this.password= password;
    	salt = "1234";
    	iterations = 10000;
    	keyLength=128;
    	passwordChars = password.toCharArray();
    	saltBytes = salt.getBytes();
    	
    	hashPassword();
    	printKey();
    }
    
    
    private void hashPassword() {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( passwordChars, saltBytes, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            this.key= res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
    
    private void printKey() {
    	
    	
        String hashedString = Hex.encodeHexString(key);

        
        System.out.println(hashedString);
    }
    
    
    
    public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}


	public int getIterations() {
		return iterations;
	}


	public void setIterations(int iterations) {
		this.iterations = iterations;
	}


	public int getKeyLength() {
		return keyLength;
	}


	public void setKeyLength(int keyLength) {
		this.keyLength = keyLength;
	}


	public char[] getPasswordChars() {
		return passwordChars;
	}


	public void setPasswordChars(char[] passwordChars) {
		this.passwordChars = passwordChars;
	}


	public byte[] getSaltBytes() {
		return saltBytes;
	}


	public void setSaltBytes(byte[] saltBytes) {
		this.saltBytes = saltBytes;
	}


	public byte[] getKey() {
		return key;
	}


	public void setKey(byte[] key) {
		this.key = key;
	}


	


	public static void main(String[] args) {
		
    	KeyGeneratorFile pruebita = new KeyGeneratorFile("micontra");
    	
    	System.out.println(pruebita.getKey().length);
    	
	}

}
