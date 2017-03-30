package webconsole.co.in.cardless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class EncryptData {

    String TAG="EncryptData";

    public EncryptData(String data1, String data2) {
        crypt(data1,data2);

    }



    public void crypt(String pwd,String data2){
        AESAlgorithm aesAlgo;

        //Now get the account no. and any dtail that you want to encrypt
        //EditText account= (EditText) findViewById(R.id.account);
        try {
            //put pwd here
            aesAlgo=new AESAlgorithm(pwd);

            //put account here for encryption
            String encryptText=aesAlgo.encrypt(data2);

            //decrypt the text here
            String decryptText=aesAlgo.decrypt(encryptText);

            Log.d(TAG,"encrypted text of hello-> "+encryptText);
            Log.d(TAG,"decrypted-> "+decryptText);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
