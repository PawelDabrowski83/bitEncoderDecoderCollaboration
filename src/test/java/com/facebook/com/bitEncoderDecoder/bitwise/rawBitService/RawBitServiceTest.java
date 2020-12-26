package com.facebook.com.bitEncoderDecoder.bitwise.rawBitService;

import com.facebook.bitEncoderDecoder.bitwise.rawBitService.RawBitService;
import com.facebook.bitEncoderDecoder.exception.InputCorruptedException;
import com.facebook.bitEncoderDecoder.exception.InputNotSentCorrectlyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RawBitServiceTest {

    RawBitService rawBitService = new RawBitService();

    @DisplayName("Should getFirstByte work")
    @ParameterizedTest
    @MethodSource("getFirstByteArgumentsProvider")
    void getFirstByte(int expected, char given) {
        assertEquals(expected, rawBitService.getFirstByte(given));
    }

    private static Stream<Arguments> getFirstByteArgumentsProvider() {
        return Stream.of(
                Arguments.of(0b11001100, (char) 0b11001100),
                Arguments.of(0b1100, (char) 0b1100),
                Arguments.of(0b10010101, (char) 0b100010010101),
                Arguments.of(0, (char) 0)
        );
    }

    @DisplayName("Should checkForTransmissionErrors() work")
    @ParameterizedTest
    @MethodSource("checkForTransmissionErrorsArgumentsProvider")
    void checkForTransmissionErrors(Exception exception, int given) {
        assertThrows(exception.getClass(), () -> rawBitService.checkForTransmissionErrors(given));
    }

    private static Stream<Arguments> checkForTransmissionErrorsArgumentsProvider() {
        return Stream.of(
                Arguments.of(new InputCorruptedException(), 0b1010),
                Arguments.of(new InputCorruptedException(), 0b01010101),
                Arguments.of(new InputNotSentCorrectlyException(), 0b11001100),
                Arguments.of(new InputNotSentCorrectlyException(), 0b00111100)
        );
    }

    @DisplayName("Should removeNoise() return char without noise")
    @ParameterizedTest
    @MethodSource("removeNoiseArgumentsProvider")
    void removeNoise(char expected, char given) {
        assertEquals(expected, given);
    }

    private static Stream<Arguments> removeNoiseArgumentsProvider() {
        return Stream.of(
                Arguments.of((char) 0b11001100, (char) 0b01001100),
                Arguments.of((char) 0b11001100, (char) 0b11101100),
                Arguments.of((char) 0b11001100, (char) 0b01000100),
                Arguments.of((char) 0b11001100, (char) 0b01001110),
                Arguments.of((char) 0b00000000, (char) 0b10000000),
                Arguments.of((char) 0b00000000, (char) 0b00010000),
                Arguments.of((char) 0b00000000, (char) 0b00001000),
                Arguments.of((char) 0b00000000, (char) 0b00000010)
        );
    }
}