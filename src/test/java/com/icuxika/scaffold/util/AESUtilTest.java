package com.icuxika.scaffold.util;

import org.junit.jupiter.api.Test;

class AESUtilTest {

    @Test
    void AESDecryptWeChatPhone() {

        try {
            String data = AESUtil.AESDecryptWeChatPhone("ix7Qd3Cr+CP0bT6tPqB/0XegueQkiKkND/NRhflmdjCSrzglZJJEWKlUJHiQZ1AWSpb4wVXJ+EbHwucg4147/f6C1c9mrZXLwkngVqlUIQ9prJbyzSCbn6KZjBqM2cGQ/b1abjujDs46A10tqBG44xhjLCc5ah8wFZvEFFLVmlUYLhSs6ANE45+odIYMZb7pf5/hqPwJkWnljbKxAuZiqA==", "R54eXHtRWySrpy6ujyPfkQ==", "0GTCJ0ww+ZbudFHgGkAO2w==");
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void AESDecrypt() {
        String key = "1234567890abcdef";
        String iv = "1234567890abcdef";
        String data = "Hello, world!";
        try {
            String cipherText = AESUtil.AESEncrypt(data, key, iv);
            System.out.println(cipherText);
            String plainText = AESUtil.AESDecrypt(cipherText, key, iv);
            System.out.println(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}