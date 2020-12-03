package com.facebook.com.bitEncoderDecoder.bitwise;

import com.facebook.bitEncoderDecoder.app.Transmitter;
import com.facebook.bitEncoderDecoder.bitwise.BitwiseTransmitterImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BitwiseTransmitterImplTest {

    Transmitter transmitter = new BitwiseTransmitterImpl();

    @DisplayName("Should send() outputs changed string")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "test", "Ala ma kota"})
    void send(String source){
        assertNotEquals(source, transmitter.send(source));
    }

    @DisplayName("Should send() change only 1 bit in each byte")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "test", "Ala ma kota"})
    void sendAndCheckBites(String source){
        // given
        int[] onesInSource = countOnesInString(source);

        // when
        String target = transmitter.send(source);
        int[] onesInTarget = countOnesInString(target);

        // then
        assertEquals(onesInSource.length, onesInTarget.length);
        for (int i = 0; i < onesInSource.length; i++){
            boolean oneBitIsTaken = onesInSource[i] - 1 == onesInTarget[i];
            boolean oneBitIsAdded = onesInSource[i] + 1 == onesInTarget[i];
            assertTrue(oneBitIsTaken || oneBitIsAdded);
        }
    }

    private int[] countOnesInString(String source){
        if (source == null || source.isBlank()){
            return new int[0];
        }
        int[] onesInSource = new int[source.length()];
        for (int i = 0; i < onesInSource.length; i++){
            onesInSource[i] = countOnesInChar(source.charAt(i));
        }
        return onesInSource;
    }

    private int countOnesInChar(char input){
        int ones = 0;
        int charLength = Integer.toBinaryString(input).length();
        int charLengthWithoutOnes = Integer.toBinaryString(input).replaceAll("1", "").length();
        ones += charLength - charLengthWithoutOnes;
        return ones;
    }

}
