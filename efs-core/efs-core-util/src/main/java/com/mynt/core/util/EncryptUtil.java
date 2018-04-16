package com.mynt.core.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jbvillanueva on 5/26/17.
 */
public class EncryptUtil {
    private static final BCryptPasswordEncoder BCRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private static final ShaPasswordEncoder SHA_PASSWORD_ENCODER = new ShaPasswordEncoder();

    public static String encryptPassword(String password) {
        return BCRYPT_PASSWORD_ENCODER.encode(password);
    }

    public static String encryptDataStore(String name) {
        return SHA_PASSWORD_ENCODER.encodePassword(name, null);
    }
}
