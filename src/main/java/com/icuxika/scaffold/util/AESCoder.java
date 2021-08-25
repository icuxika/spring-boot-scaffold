package com.icuxika.scaffold.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

public class AESCoder {

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 /工作模式 /填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     */
    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data 密文
     * @param key  密钥
     * @return byte[] 原文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k, generateIV(iv));
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data 原文
     * @param key  密钥
     * @return byte[] 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k, generateIV(iv));
        return cipher.doFinal(data);
    }

    /**
     * 生成 iv
     *
     * @param iv
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidParameterSpecException
     */
    private static AlgorithmParameters generateIV(byte[] iv) throws NoSuchAlgorithmException, InvalidParameterSpecException {
        AlgorithmParameters parameters = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        parameters.init(new IvParameterSpec(iv));
        return parameters;
    }
}