package com.mynt.core.web.exceptions;

public class MyntInvalidTokenException extends MyntAbstractException {

    private String paymentToken;

    public MyntInvalidTokenException(String message, String paymentToken) {
        super(message);
        this.paymentToken = paymentToken;
    }

    public MyntInvalidTokenException(String message) {
        super(message);
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }
}
