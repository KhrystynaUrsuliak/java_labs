package labs.lab0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVariant14 {

    @ParameterizedTest
    @MethodSource("rotateDigitProvider")
    public void rotateDigitTest(int input, int expected) {
        assertEquals(expected, new Variant14().rotateDigit(input));
    }

    private static Stream<Arguments> rotateDigitProvider() {
        return Stream.of(
            Arguments.of(123, 312),
            Arguments.of(456, 645),
            Arguments.of(789, 978)
        );
    }

    @Test
    public void rotateDigitNegativeTest() {
        Assertions.assertThrows(AssertionError.class, () -> {
            new Variant14().rotateDigit(45); // не тризначне число, має викликати AssertionError
        });
    }


    @ParameterizedTest
    @MethodSource("isExactlyOnePositiveProvider")
    public void isExactlyOnePositiveTest(int A, int B, int C, boolean expected) {
        assertEquals(expected, new Variant14().isExactlyOnePositive(A, B, C));
    }

    private static Stream<Arguments> isExactlyOnePositiveProvider() {
        return Stream.of(
            Arguments.of(1, -2, -3, true),
            Arguments.of(-1, 2, -3, true),
            Arguments.of(-1, -2, 3, true),
            Arguments.of(1, 2, -3, false),
            Arguments.of(0, 0, 0, false),
            Arguments.of(-1, -2, -3, false)
        );
    }


    @ParameterizedTest
    @MethodSource("findMinAndMaxProvider")
    public void findMinAndMaxTest(int A, int B, int C, int[] expected) {
        assertArrayEquals(expected, new Variant14().findMinAndMax(A, B, C));
    }

    private static Stream<Arguments> findMinAndMaxProvider() {
        return Stream.of(
            Arguments.of(1, 2, 3, new int[]{1, 3}),
            Arguments.of(10, -5, 7, new int[]{-5, 10}),
            Arguments.of(0, 0, 0, new int[]{0, 0}),
            Arguments.of(-1, -2, -3, new int[]{-3, -1}),
            Arguments.of(15, 8, 23, new int[]{8, 23})
        );
    }
}
