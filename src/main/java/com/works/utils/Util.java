package com.works.utils;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Formatter;
import java.util.Random;

public class Util {

    public static String encrypt( String plainText, int count ) {
        String pass = plainText;
        for (int i = 0; i < count; i++) {
            pass = encryptPassword( pass );
        }
        return pass;
    }

    private static String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


    public static String sifrele(String data, int i) {
        byte[] dizi = null;
        Random rd = new Random();
        int ri = rd.nextInt(899) + 100;
        for (int j = 0; j < i; j++) {
            dizi = Base64.getEncoder().encode(data.getBytes());
            data = new String(dizi);
        }
        String sifrelenmis = new String(dizi) + MD5("" + ri);
        System.out.println("sifrelenmis" + sifrelenmis);
        return sifrelenmis;
    }

    public static String sifreCoz(String data, int i) {
        byte[] dizi = null;
        data = data.substring(0, data.length() - 32);
        for (int j = 0; j < i; j++) {
            dizi = Base64.getDecoder().decode(data.getBytes());
            data = new String(dizi);
        }
        String cozulmus = new String(dizi);
        System.out.println("cozulmus" + cozulmus);
        return cozulmus;
    }



}
