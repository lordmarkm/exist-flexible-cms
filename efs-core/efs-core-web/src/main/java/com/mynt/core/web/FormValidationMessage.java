package com.mynt.core.web;

import java.util.HashMap;
import java.util.Map;

public class FormValidationMessage {
    private Map<String, String> validationMessage = new HashMap<>();

    public void setValidationMessage(String field, String message) {
        validationMessage.put(field, message);
    }

    public Map<String, String> getValidationMessage() {
        return validationMessage;
    }
}
