package com.facebook.bitEncoderDecoder.exception;

public class InputNotEcodedCorrectly extends RuntimeException {
    public InputNotEcodedCorrectly(String message) {
        super("Input hasn't been encoded correctly");
    }
}
