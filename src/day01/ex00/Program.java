package day01.ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User(1, "John", 5000);
        User Mike = new User(2, "Mike", 200);

        UUID id1 = UUID.randomUUID();
        Transaction transaction1 = new Transaction(id1, John, Mike, TransferCategory.CREDITS, -500);
        Transaction transaction2 = new Transaction(id1, Mike, John, TransferCategory.DEBITS, 500);

        UUID id2 = UUID.randomUUID();
        Transaction transaction3 = new Transaction(id2, John, Mike, TransferCategory.CREDITS, -5000);
        Transaction transaction4 = new Transaction(id2, Mike, John, TransferCategory.DEBITS, 5000);

        // test other cases
    }
}
