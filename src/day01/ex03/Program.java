package day01.ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User( "John", 5000);
        User Mike = new User( "Mike", 200);

        UUID id1 = UUID.randomUUID();
        Transaction t1 = new Transaction(id1, John, Mike, TransferCategory.CREDITS, -500);
        Transaction t2 = new Transaction(id1, Mike, John, TransferCategory.DEBITS, 500);
        UUID id2 = UUID.randomUUID();
        Transaction t3 = new Transaction(id2, John, Mike, TransferCategory.CREDITS, -21);
        Transaction t4 = new Transaction(id2, Mike, John, TransferCategory.DEBITS, 21);
        UUID id3 = UUID.randomUUID();
        Transaction t5 = new Transaction(id3, Mike, John, TransferCategory.CREDITS, -42);
        Transaction t6 = new Transaction(id3, John, Mike, TransferCategory.DEBITS, 42);

        TransactionsList MikeTransactions = new TransactionsLinkedList();
        MikeTransactions.add(t2);
        MikeTransactions.add(t4);
        MikeTransactions.add(t5);

        for (Transaction t : MikeTransactions.toArray()) {
            System.out.println("id: " + t.getId() + ", prev = " + t.getPrev().getId() + ", next = " + t.getNext().getId());
        }
        System.out.println();
        System.out.println("removing: # " + id2);
        MikeTransactions.removeById(id2);
        System.out.println();
        for (Transaction t : MikeTransactions.toArray()) {
            System.out.println("id: " + t.getId() + ", prev = " + t.getPrev().getId() + ", next = " + t.getNext().getId());
        }
    }
}
