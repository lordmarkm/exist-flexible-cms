package com.mynt.core.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtil {
    private static final BCryptPasswordEncoder BCRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();
    public static String encryptPassword(String password) {
        return BCRYPT_PASSWORD_ENCODER.encode(password);
    }
}
