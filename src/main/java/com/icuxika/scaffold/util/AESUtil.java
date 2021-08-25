package com.icuxika.scaffold.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESUtil {

    /**
     * AES 加密
     *
     * @param plainText 原文
     * @param key       密钥
     * @param iv        偏移量
     * @return String 密文
     */
    public static String AESEncrypt(String plainText, String key, String iv) throws Exception {
        return Base64.getEncoder().encodeToString(AESCoder.encrypt(plainText.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @param key        密钥
     * @param iv         偏移量
     * @return String 原文
     */
    public static String AESDecrypt(String cipherText, String key, String iv) throws Exception {
        return new String(AESCoder.decrypt(Base64.getDecoder().decode(cipherText), key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @param key        密钥
     * @param iv         偏移量
     * @return String 原文
     */
    public static String AESDecryptWeChatPhone(String cipherText, String key, String iv) throws Exception {
        return new String(AESCoder.decrypt(Base64.getDecoder().decode(cipherText), Base64.getDecoder().decode(key), Base64.getDecoder().decode(iv)), StandardCharsets.UTF_8);
    }
}
