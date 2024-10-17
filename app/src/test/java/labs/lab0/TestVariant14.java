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


    private static final double EPSILON = 1e-4;

    @ParameterizedTest
    @MethodSource("calculateEquilateralTriangleProvider")
    public void calculateEquilateralTriangleTest(int elementNumber, double value, double[] expected) {
        double[] result = new Variant14().calculateEquilateralTriangle(elementNumber, value);
        assertArrayEquals(expected, result, EPSILON);
    }

    private static Stream<Arguments> calculateEquilateralTriangleProvider() {
        return Stream.of(
            // Відомо: сторона a
            Arguments.of(1, 6.0, new double[]{6.0, 1.73205, 3.46410, 15.58846}),
            // Відомо: радіус вписаного кола R1
            Arguments.of(2, 1.73205, new double[]{6.0, 1.73205, 3.46410, 15.58846}),
            // Відомо: радіус описаного кола R2
            Arguments.of(3, 3.46410, new double[]{6.0, 1.73205, 3.46410, 15.58846}),
            // Відомо: площа S
            Arguments.of(4, 15.58846, new double[]{6.0, 1.73205, 3.46410, 15.58846})
        );
    }

    @Test
    public void invalidElementNumberTest() {
        // Перевірка на викидання винятку при недопустимому номері елемента
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Variant14().calculateEquilateralTriangle(5, 10.0); // Номер елемента поза діапазоном 1-4
        });
    }


    @ParameterizedTest
    @MethodSource("calculateSquaresUpToNProvider")
    public void calculateSquaresUpToNTest(int N, int[] expected) {
        int[] result = new Variant14().calculateSquaresUpToN(N);
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> calculateSquaresUpToNProvider() {
        return Stream.of(
            // N = 1, очікується квадрат [1]
            Arguments.of(1, new int[]{1}),
            // N = 2, очікуються квадрати [1, 4]
            Arguments.of(2, new int[]{1, 4}),
            // N = 3, очікуються квадрати [1, 4, 9]
            Arguments.of(3, new int[]{1, 4, 9}),
            // N = 4, очікуються квадрати [1, 4, 9, 16]
            Arguments.of(4, new int[]{1, 4, 9, 16}),
            // N = 5, очікуються квадрати [1, 4, 9, 16, 25]
            Arguments.of(5, new int[]{1, 4, 9, 16, 25})
        );
    }

    @Test
    public void invalidNTest() {
        // Перевірка на викидання AssertionError, якщо N <= 0
        org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, () -> {
            new Variant14().calculateSquaresUpToN(0); // N = 0, має викликати AssertionError
        });
    }


    @ParameterizedTest
    @MethodSource("findMaxKForSumLessThanAProvider")
    public void findMaxKForSumLessThanATest(double A, double[] expected) {
        double[] result = new Variant14().findMaxKForSumLessThanA(A);
        assertArrayEquals(expected, result, EPSILON);
    }

    private static Stream<Arguments> findMaxKForSumLessThanAProvider() {
        return Stream.of(
            // Приклад: A = 2.5, очікуваний результат: K = 4, сума = 2.08333
            Arguments.of(2.5, new double[]{4, 2.08333}),
            // Приклад: A = 3.5, очікуваний результат: K = 7, сума = 2.59286
            Arguments.of(3.5, new double[]{7, 2.59286}),
            // Приклад: A = 1.5, очікуваний результат: K = 2, сума = 1.5
            Arguments.of(1.5, new double[]{2, 1.5}),
            // Приклад: A = 5.0, очікуваний результат: K = 15, сума = 3.31823
            Arguments.of(5.0, new double[]{15, 3.31823}),
            // Приклад: A = 2.0, очікуваний результат: K = 4, сума = 2.08333
            Arguments.of(2.0, new double[]{4, 2.08333})
        );
    }

    @Test
    public void invalidATest() {
        // Перевірка на викидання AssertionError, якщо A <= 1
        org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, () -> {
            new Variant14().findMaxKForSumLessThanA(1.0); // A = 1.0, має викликати AssertionError
        });
    }


    @ParameterizedTest
    @MethodSource("rearrangeArrayProvider")
    public void rearrangeArrayTest(int[] input, int[] expected) {
        int[] result = new Variant14().rearrangeArray(input);
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> rearrangeArrayProvider() {
        return Stream.of(
            // Вхідний масив має парні та непарні номери елементів
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, new int[]{2, 4, 6, 1, 3, 5}),
            // Вхідний масив з непарною кількістю елементів
            Arguments.of(new int[]{10, 11, 12, 13, 14}, new int[]{11, 13, 10, 12, 14}),
            // Вхідний масив з двома елементами
            Arguments.of(new int[]{100, 200}, new int[]{200, 100}),
            // Вхідний масив з одним елементом
            Arguments.of(new int[]{7}, new int[]{7}),
            // Порожній масив
            Arguments.of(new int[]{}, new int[]{})
        );
    }


    @ParameterizedTest
    @MethodSource("printCornersProvider")
    public void printCornersTest(int[][] matrix, int[] expected) {
        int[] result = new Variant14().printCorners(matrix);
        assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> printCornersProvider() {
        return Stream.of(
            // Квадратна матриця 2x2
            Arguments.of(new int[][]{
                {1, 2},
                {3, 4}
            }, new int[]{1, 3, 4, 2}),

            // Квадратна матриця 3x3
            Arguments.of(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            }, new int[]{1, 4, 7, 8, 9, 6, 3, 2, 5}),

            // Квадратна матриця 4x4
            Arguments.of(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
            }, new int[]{1, 5, 9, 13, 14, 15, 16, 12, 8, 4, 3, 2, 6, 10, 11, 7}),

            // Квадратна матриця 5x5
            Arguments.of(new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
            }, new int[]{1, 6, 11, 16, 21, 22, 23, 24, 25, 20, 15, 10, 5, 4, 3, 2, 7, 12, 17, 18, 19, 14, 9, 8, 13})
        );
    }
}
