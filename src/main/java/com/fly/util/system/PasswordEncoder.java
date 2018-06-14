package com.fly.util.system;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类
 */

public class PasswordEncoder {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private Object salt;
    private String algorithm;

    /**
     * 加密器
     *
     * @param salt      盐值
     * @param algorithm 算法
     */
    public PasswordEncoder(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String encode(String pass, Integer count) {
        while (count-- > 0) {
            pass = this.encode(pass);
        }
        return pass;
    }

    /**
     * 对密码加密
     *
     * @param pass
     * @return
     */
    public String encode(String pass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            result = byteArrayHexString(
                    md.digest(this.mergePassAndSalt(pass).getBytes("utf-8"))
            );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对密码加盐
     *
     * @param pass
     * @return
     */
    private String mergePassAndSalt(String pass) {
        if (pass == null) {
            return "";
        }
        if (salt == null || "".equals(salt)) {
            return pass;
        } else {
            return pass + salt.toString();
        }
    }

    private static String byteArrayHexString(byte[] b) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            stringBuffer.append(byteToHexString(b[i]));
        }
        return stringBuffer.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

}
