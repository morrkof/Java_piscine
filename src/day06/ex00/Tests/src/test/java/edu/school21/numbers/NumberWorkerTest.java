package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {787, 797, 2, 5, 17})
    public void isPrimeForPrimesTest(int number) {
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 100, 666, 1024})
    public void isPrimeForNotPrimesTest(int number) {
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -27, -100500})
    public void isPrimeForIncorrectNumbersTest(int number) {
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitSumTest(int input, int expected) {
        assertEquals(expected, numberWorker.digitsSum(input));
    }
}