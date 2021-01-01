package com.facebook.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.exception.InputIsNullException;

public class SymbolEncoder implements Encoder {

    public static final int MULTIPLICATION_FACTOR = 3;

    @Override
    public String encode(String input) {
        input = validateInput(input);

        StringBuilder encodeString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String multipliedChar = String.valueOf(input.charAt(i)).repeat(MULTIPLICATION_FACTOR);
            encodeString.append(multipliedChar);
        }
        return encodeString.toString();
    }

    private String validateInput(String input) {
        if (input == null) {
            throw new InputIsNullException();
        }
        if (input.isBlank()) {
            return "";
        }
        return input;
    }

}
