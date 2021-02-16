package com.desafio.api.spring_boot.utility;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPasswordUtility {

    public static String EncondePassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Erro no encode";
        }
    }
}