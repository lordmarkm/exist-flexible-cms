package com.mynt.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EncryptionGeneratorUtil {

    public static String generateEncryptionKey(String salt, String appSecret) {
        return encryptUsing256(salt, appSecret);
    }

    public static String generateToken(String encryptionKey, DateTime timeStamp) {
        return encryptUsing256(encryptionKey, timeStamp);
    }

    public static String generateToken(String encryptionKey, String txnReference, DateTime timeStamp) {
        return encryptUsing256(encryptionKey, txnReference, timeStamp);
    }

    public static String generateEncryptedValue(int value, String token, String encryptionKey) {
        return encryptUsing256(value, token, encryptionKey);
    }

    public static String gcashEncryption(String userName, String password) {
        String result = null;
        if (userName != null && password != null) {
            result = userName.toLowerCase().concat(password);
            result = DigestUtils.sha1Hex(result).toLowerCase();
        }
        return result;
    }

    private static String encryptUsing256(Object ... args) {
        String valueToBeEncrypt = Arrays.stream(args)
                .map(Object::toString)
                .collect(Collectors.joining("_"));
        return DigestUtils.sha256Hex(valueToBeEncrypt);
    }
}
