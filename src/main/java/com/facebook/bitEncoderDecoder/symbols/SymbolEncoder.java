package com.facebook.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.app.Encoder;
import com.facebook.bitEncoderDecoder.exception.InputIsNullException;

public class SymbolEncoder implements Encoder {

    public static final int MULTIPLICATION_FACTOR = 3;

    @Override
    public String encode(String input) {
        StringBuilder encodeString = new StringBuilder();
        if (input == null) {
            throw new InputIsNullException();
        } else if (input.equalsIgnoreCase(" ")) {
            return "";
        }
        for (int i = 0; i < input.length(); i++) {
            String multipliedChar = String.valueOf(input.charAt(i)).repeat(MULTIPLICATION_FACTOR);
            encodeString.append(multipliedChar);

        }
        return encodeString.toString();
    }

}
