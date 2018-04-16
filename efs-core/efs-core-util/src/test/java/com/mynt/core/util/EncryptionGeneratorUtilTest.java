package com.mynt.core.util;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class EncryptionGeneratorUtilTest {

    @Test
    public void should_generateEncryptionKey() {
        String salt = "abc";
        String appKey = "myntCore";
        String encryptionKey = EncryptionGeneratorUtil.generateEncryptionKey(salt, appKey);
        Assert.assertNotNull(encryptionKey);
        Assert.assertTrue(!encryptionKey.equals(appKey));
        Assert.assertTrue(!encryptionKey.equals(salt));
    }

    @Test
    public void should_generateToken_withoutTxnReference() {
        String salt = "abc";
        String appKey = "myntCore";
        String encryptionKey = EncryptionGeneratorUtil.generateEncryptionKey(salt, appKey);
        String token = EncryptionGeneratorUtil.generateToken(encryptionKey, new DateTime());

        Assert.assertNotNull(encryptionKey);
        Assert.assertNotNull(token);

        Assert.assertTrue(!token.equals(salt));
        Assert.assertTrue(!token.equals(appKey));
        Assert.assertTrue(!token.equals(encryptionKey));
    }

    @Test
    public void should_generateToken_withTxnReference() {
        String salt = "abc";
        String appKey = "myntCore";
        String transactionReference = "SAMPLE_01_01_2017";
        String encryptionKey = EncryptionGeneratorUtil.generateEncryptionKey(salt, appKey);
        String token = EncryptionGeneratorUtil.generateToken(encryptionKey, transactionReference, new DateTime());

        Assert.assertNotNull(encryptionKey);
        Assert.assertNotNull(token);

        Assert.assertTrue(!token.equals(salt));
        Assert.assertTrue(!token.equals(appKey));
        Assert.assertTrue(!token.equals(encryptionKey));
        Assert.assertTrue(!token.equals(transactionReference));
    }

    @Test
    public void should_generateEncryptedPinpadValue() {
        int value = 1;
        String salt = "abc";
        String appKey = "myntCore";
        String transactionReference = "SAMPLE_01_01_2017";
        String encryptionKey = EncryptionGeneratorUtil.generateEncryptionKey(salt, appKey);
        String token = EncryptionGeneratorUtil.generateToken(encryptionKey, transactionReference, new DateTime());

        String pinpadEncryptedValue = EncryptionGeneratorUtil.generateEncryptedValue(value, token, encryptionKey);

        Assert.assertNotNull(encryptionKey);
        Assert.assertNotNull(token);

        Assert.assertTrue(!pinpadEncryptedValue.equals(salt));
        Assert.assertTrue(!pinpadEncryptedValue.equals(appKey));
        Assert.assertTrue(!pinpadEncryptedValue.equals(encryptionKey));
        Assert.assertTrue(!pinpadEncryptedValue.equals(transactionReference));
    }

    @Test
    public void should_generateGcashEncryptedValue() {
        String userName = "09056321445";
        String mpin = "12345";

        String encryptedValue = EncryptionGeneratorUtil.gcashEncryption(userName, mpin);

        Assert.assertNotNull(encryptedValue);
        Assert.assertNotEquals(userName, encryptedValue);
        Assert.assertNotEquals(mpin, encryptedValue);
    }

    @Test
    public void should_notGenerateGcashEncrypted_when_OneParamIsNull() {
        String userName = "09056321445";
        String mpin = null;

        String encryptedValue = EncryptionGeneratorUtil.gcashEncryption(userName, mpin);

        Assert.assertNull(encryptedValue);

    }
}
