package com.facebook.bitEncoderDecoder.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void shouldGetRandomInRangeReturnNumbersWithinBounds(){
        int loops = 10_000;
        int LOWER = 0;
        int UPPER = 20;

        while(--loops > 0){
            Assertions.assertTrue(Utils.getRandomInRange(LOWER, UPPER) <= UPPER);
        }
    }

    @Test
    public void shouldRandomizeCharWork(){
        // given
        char[] chars = {'a', 'b', 'V', '.', ' ', '9', 'q', ';'};

        // when
        char[] actual = new char[chars.length];
        for (int i = 0; i < chars.length; i++){
            actual[i] = Utils.randomizeChar(chars[i]);
        }

        // then
        for (int i = 0; i < chars.length; i++) {
            Assertions.assertNotEquals(chars[i], actual[i]);
        }
    }
}
