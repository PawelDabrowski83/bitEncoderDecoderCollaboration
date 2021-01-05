package com.facebook.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.exception.InputIsNullException;

public class SymbolEncoder implements Encoder {

    public static final int MULTIPLICATION_FACTOR = 3;

    @Override
    public String encode(String input) {
        input = validateInput(input);
        StringBuilder encodedString = getEncodedString(input);
        return encodedString.toString();
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

    private StringBuilder getEncodedString(String input) {
        StringBuilder encodedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            String multipliedChar = String.valueOf(c).repeat(MULTIPLICATION_FACTOR);
            encodedString.append(multipliedChar);
        }
        return encodedString;
    }

}
