package net.caesarlegion.drugimpact.Encryption;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Gabriel Charlebnois on 2017-12-14.
 * NOTE: This is the main part of the extra feature
 * Purpose: To provide encryption and decryption functions, easily accessible.
 */

public class EncryptionManager {

    //Default encryption algo.
    private static final String ALGO = "AES";
    //Default charset
    private static final String CHAR_SET = "UTF-8";
    //Default key that is used before we get the user's key. AES only supports 16, 24 or 32 bytes
    private static final String TO_BE_REPLACED_DEFAULT_KEY = "f1FD34ASf1FD34AS";

    //The key we will decrypt and encrypt with.
    private String keyFromServer = TO_BE_REPLACED_DEFAULT_KEY;

    public EncryptionManager() {}

    public EncryptionManager(String key) {this.setKeyFromServer(key);}

    private SecretKey generateKey()
    {
        return new SecretKeySpec(keyFromServer.getBytes(), ALGO);
    }

    /**
     * Purpose: To encrypt a string
     * @param data
     * @return the encrypted data, or nothing if there was an error.
     */
    public String encryptMsg(String data)
    {
        //Declare the cipher and the resulting encrypted data
        Cipher cipher = null;
        byte[] encryptedData = null;
        try {

            //Initialize the cipher and encrypt the data with the key
            cipher = Cipher.getInstance(ALGO + "/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, generateKey());
            encryptedData = cipher.doFinal(data.getBytes(CHAR_SET));
            return Base64.encodeToString(encryptedData, Base64.NO_WRAP);

        //A lot of exceptions when dealing with encryption...
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Purpose: To decrypt data that was previously encrypted using the same key.
     * @param data
     * @return The decrypted data, or nothing if there was an error.
     */
    public String decryptMsg(String data)
    {
        //Declare the cipher and the resulting decrypted data
        Cipher cipher = null;
        String decryptedData = null;
        try {

            //Decrypt the encrypted data using the correct algorithm
            cipher = Cipher.getInstance(ALGO + "/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, generateKey());
            decryptedData = new String(cipher.doFinal(Base64.decode(data, Base64.NO_WRAP)), CHAR_SET);

        //A lot of exceptions for decryption...
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


        return decryptedData;
    }



    public void setKeyFromServer(String keyFromServer) {
        this.keyFromServer = keyFromServer;
    }
}
