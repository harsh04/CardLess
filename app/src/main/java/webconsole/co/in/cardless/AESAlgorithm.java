package webconsole.co.in.cardless;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * Created by raul_Will on 3/17/2017.
 */

public class AESAlgorithm {

    public static String algo="AES";
    public byte[] keyValue;
    public static final String KEY_ALGORITHM = "AES";
    public static final String PASSWORD_HASH_ALGORITHM = "SHA-256";

    public AESAlgorithm(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        keyValue= key.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        keyValue = sha.digest(keyValue);
        keyValue = Arrays.copyOf(keyValue, 16); // use only first 128 bit

    }

    public Key generateKey() throws Exception{
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyValue, "AES");
        return secretKeySpec;
    }

    public String encrypt(String msg) throws Exception{
        Key key=generateKey();
        Cipher c=Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal= c.doFinal(msg.getBytes());
        String encryptedValue= new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public String decrypt(String msg) throws Exception {
        Key key=generateKey();
        Cipher c=Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue= new BASE64Decoder().decodeBuffer(msg);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
}
