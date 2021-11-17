package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Illegal number");
        } else if (number == 2) {
            return true;
        } else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public int digitsSum(int number) {
        if (number < 0) {
            number = number * -1;
        }
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number = number / 10;
        }
        return result;
    }
}

class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String message) {
        super(message);
    }
}
