package com.practice.storesecretkeys.secure

import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class AES() {
    private lateinit var secretKey : SecretKeySpec
    private lateinit var key : ByteArray

    private fun setKey(mykey : String){
        try {
            key = mykey.toByteArray(Charsets.UTF_8)
            val sha = MessageDigest.getInstance("SHA-1")
            key = sha.digest(key)
            key = Arrays.copyOf(key, 16)
            secretKey = SecretKeySpec(key, "AES")
        }catch (e : NoSuchAlgorithmException){
            e.printStackTrace()
        }catch (e : UnsupportedEncodingException){
            e.printStackTrace()
        }
    }

    fun encrypt(content : String, key : String) : String{
        try {
            setKey(key)
            val cipher : Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return Base64.encodeToString(cipher.doFinal(content.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
        }catch (e : Exception){
            println("Error while Encrypting : "+e.printStackTrace())
        }
        return ""
    }

    fun decrypt(encryptedContent : String, key: String) : String{
        try {
            setKey(key)
            val cipher : Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            val decryptedData = cipher.doFinal(Base64.decode(encryptedContent, Base64.DEFAULT))
            return String(decryptedData)
        }catch (e : Exception){
            println("Error while Decrypting : "+e.printStackTrace())
        }
        return ""
    }
}