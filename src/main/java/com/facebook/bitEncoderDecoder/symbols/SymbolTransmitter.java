package com.facebook.bitEncoderDecoder.symbols;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.utils.InputValidator;
import com.facebook.bitEncoderDecoder.utils.Utils;

public class SymbolTransmitter implements Transmitter {

    @Override
    public String send(String input){
        InputValidator.validateInput(input);
        String[] triplets = Utils.prepareSegmentedInput(input);
        if (triplets.length == 0){
            return "";
        }
        return sendMessage(triplets);
    }

    private String sendMessage(String[] triplets) {
        StringBuilder target = new StringBuilder();
        for (String triplet : triplets){
            target.append(generateNoise(triplet));
        }
        return target.toString();
    }

    private String generateNoise(String triplet) {
        int randomPosition = Utils.getRandomInRange(0, 2);

        char[] letters = triplet.toCharArray();
        char charToChange = letters[randomPosition];
        char distorted = Utils.randomizeChar(charToChange);
        letters[randomPosition] = distorted;
        return String.valueOf(letters);
    }
}
