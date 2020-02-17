package com.practice.storesecretkeys.secure

import android.util.Base64
import com.practice.storesecretkeys.BuildConfig
import java.lang.StringBuilder

class SecureUtil {

    /* ToDo : Delete this function after generating secure Data */
    /* This function should only be used to generate scattered data */
    fun generateSecretDataEncodedStringArray(data : String, myKey : String) {

        println("Original Data : $data")

        /* Start :: This Logic is used to encrypt, encode and divide data */
        val encryptedData = AES().encrypt(data, myKey)
        println("Encrypted Data : $encryptedData")

        val encodedEncryptedData = Base64.encodeToString(encryptedData.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        println("Encoded Encrypted String : $encodedEncryptedData")

        /* Here we can apply multiple iterations of encoding to make our algo strong */

        val strSize = encodedEncryptedData.length
        val mySize = strSize/3

        /* Divide encodedEncryptedString into three parts
         * And store these three parts in three different files
         * Increasing parts, will make our algo more complex and strong */
        val firstString = encodedEncryptedData.substring(0, mySize)
        val secondString = encodedEncryptedData.substring(mySize, mySize+mySize)
        val thirdString = encodedEncryptedData.substring(mySize+mySize)
        println("Encoded Encrypted Data in Parts : $firstString$secondString$thirdString")
        println("Data Part 1 : $firstString")
        println("Data Part 2 : $secondString")
        println("Data Part 3 : $thirdString")
        /* End :: This Logic is used to encrypt, encode and divide data */

        /* Start :: This Logic is used to encode and divide key */
        println("Original Key : $myKey")
        val encodedKey = Base64.encodeToString(myKey.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        println("Encoded Key : $encodedKey")

        val strSizeKey = encodedKey.length
        val mySizeKey = strSizeKey/3

        /* Divide encodedKey into three parts
         * And store these three parts in three different files
         * Increasing parts, will make our algo more complex and strong */
        val firstKeyString = encodedKey.substring(0, mySizeKey)
        val secondKeyString = encodedKey.substring(mySizeKey, mySizeKey+mySizeKey)
        val thirdKeyString = encodedKey.substring(mySizeKey+mySizeKey)
        println("Encoded Encrypted Key in Parts : $firstKeyString$secondKeyString$thirdKeyString")
        println("Key Part 1 : $firstKeyString")
        println("Key Part 2 : $secondKeyString")
        println("Key Part 3 : $thirdKeyString")
        /* End :: This Logic is used to encode and divide key */

        /* Here we will manually store this data and key parts in three different files
         * 1. Custom properties file and get those from build specific variables
         * 2. In strings.xml
         * 3. In Project specific constant files */

    }

    /* This function is used to gather all scattered data and bring back original data*/
    fun generateOriginalDataFromScatteredData(data: String, key: String) : String{

        /* Here we will manually accumulate data and key parts from three different files
         * 1. Custom properties file
         * 2. In strings.xml
         * 3. In Project specific constant files */

        var accumulatedData = StringBuilder()
        accumulatedData.append(data)
        accumulatedData.append(BuildConfig.REF_TEXT)
        accumulatedData.append(ProjectConstants.myDataConst)

        var  accumulatedKey = StringBuilder()
        accumulatedKey.append(key)
        accumulatedKey.append(BuildConfig.REF_TEXT_2)
        accumulatedKey.append(ProjectConstants.myKeyConst)

        val myKey = String(Base64.decode(accumulatedKey.toString(), Base64.DEFAULT))
        println("Original Key : $myKey")

        /* Here we need to decode multiple times if we have encoded multiple times */
        val decodedData = String(Base64.decode(accumulatedData.toString(), Base64.DEFAULT))
        println("Decoded Data :  $decodedData")

        val decryptedData = AES().decrypt(decodedData, myKey)
        println("Original Data : $decryptedData")

        return decryptedData
    }
}