package com.practice.storesecretkeys.secure

import android.util.Base64
import com.practice.storesecretkeys.BuildConfig
import java.lang.Exception
import java.lang.StringBuilder


class SecureUtil {

    val myConst = "ejFPSmdlYUVXVEMzWT0K"
    val myConst2 = "VjcmV0S2V5"

    /* ToDo : Delete this function after generating secure Data */
    fun generateSecureDataFromAES(originalData : String) : String {
        try {
            val firstEncodedString = Base64.encodeToString(originalData.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
            println("firstEncodedString $firstEncodedString")

            val secondEncodedString = Base64.encodeToString(firstEncodedString.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
            println("secondEncodedString $secondEncodedString")

            val myKey = "ThisIsMyVerySecretKey"

            val encryptedString = AES().encrypt(secondEncodedString, myKey)
            println("encryptedString $encryptedString")

            val thirdEncodedString = Base64.encodeToString(encryptedString.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
            println("thirdEncodedString $thirdEncodedString")

            return thirdEncodedString

        }catch (e : Exception){
            println("Error while generating secure Data"+ e.printStackTrace())
        }
        return ""
    }

    fun getSecretFromAES(text : String) : String{
        try {
            val thirdDecodedString = String(Base64.decode(text, Base64.DEFAULT))
            println("thirdDecodedString $thirdDecodedString")

            val myKey = "ThisIsMyVerySecretKey"

            val decryptedString = AES().decrypt(thirdDecodedString, myKey)
            println("decryptedString $decryptedString")

            val secondDecodedString = String(Base64.decode(decryptedString, Base64.DEFAULT))
            println("secondDecodedString $secondDecodedString")

            val firstDecodedString = String(Base64.decode(secondDecodedString, Base64.DEFAULT))
            println("firstDecodedString $firstDecodedString")

        }catch (e : Exception){
            println("Error while getSecretFromAES"+ e.printStackTrace())

        }
        return ""
    }

    fun getSecretFromDES(text : String) : String{
        return ""
    }

    /* ToDo : Delete this function after generating secure Data */
    fun generateSecretDataEncodedStringArray(data : String) : Array<String>{

        println(data)

        val myKey = "VtUfrB3leCNMHI6f17DjnLxGfFlaA6gk"

        val encryptedString = AES().encrypt(data, myKey)
        println("encryptedString $encryptedString")

        val thirdEncodedString = Base64.encodeToString(encryptedString.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        println("thirdEncodedString $thirdEncodedString")

        val strSize = thirdEncodedString.length
        val mySize = strSize/3

        val firstString = thirdEncodedString.substring(0, mySize)
        val secondString = thirdEncodedString.substring(mySize, mySize+mySize)
        val thirdString = thirdEncodedString.substring(mySize+mySize)
        println(thirdEncodedString)
        println("$firstString$secondString$thirdString")
        println(firstString)
        println(secondString)
        println(thirdString)


        val encodedKey = Base64.encodeToString(myKey.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        println("encodedKey $encodedKey")

        val strSizeKey = encodedKey.length
        val mySizeKey = strSizeKey/3

        val firstKeyString = encodedKey.substring(0, mySizeKey)
        val secondKeyString = encodedKey.substring(mySizeKey, mySizeKey+mySizeKey)
        val thirdKeyString = encodedKey.substring(mySizeKey+mySizeKey)

        println(firstKeyString)
        println(secondKeyString)
        println(thirdKeyString)

        val strArr  = arrayOf(firstString ,secondString, thirdString)
        return strArr
    }

    fun generateOriginalDataFromScateredData(data: String, key: String) : String{

        var accumulatedText = StringBuilder()
        accumulatedText.append(data)
        accumulatedText.append(BuildConfig.REF_TEXT)
        accumulatedText.append(myConst)

//        val myKey = "ThisIsMyVerySecretKey"

        var  accumulatedKey = StringBuilder()
        accumulatedKey.append(key)
        accumulatedKey.append(BuildConfig.REF_TEXT_2)
        accumulatedKey.append(myConst2)

        val myKey = String(Base64.decode(accumulatedKey.toString(), Base64.DEFAULT))
        println("myKey $myKey")

        val thirdDecodedString = String(Base64.decode(accumulatedText.toString(), Base64.DEFAULT))
        println("thirdDecodedString $thirdDecodedString")

        val decryptedString = AES().decrypt(thirdDecodedString, myKey)
        println("decryptedString $decryptedString")

        return decryptedString
    }
}