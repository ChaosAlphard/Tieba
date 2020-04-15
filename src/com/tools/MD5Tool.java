package com.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tool {
    public static String md5(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final char[] HEX="0123456789ABCDEF".toCharArray();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(s.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder(b.length*2);
        for (byte i : b) {
            sb.append(HEX[(b[i] >> 4) & 0x0f]);
            sb.append(HEX[b[i] & 0x0f]);
        }

        return String.valueOf(sb);
    }
}