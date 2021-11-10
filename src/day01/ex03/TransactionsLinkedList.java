package day01.ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{

    private Transaction head;
    private int lenght;

    public TransactionsLinkedList() {
        head = new Transaction();
        head.setNext(head);
        head.setPrev(head);
        lenght = 0;
    }

    @Override
    public void add(Transaction transaction) {
        transaction.setNext(head);
        transaction.setPrev(head.getPrev());
        transaction.getPrev().setNext(transaction);
        head.setPrev(transaction);
        lenght++;
    }

    @Override
    public void removeById(UUID id) {
        for (Transaction tmp = head.getNext(); tmp != head; tmp = tmp.getNext()) {
            if (tmp.getId().equals(id)) {
                tmp.getPrev().setNext(tmp.getNext());
                tmp.getNext().setPrev(tmp.getPrev());
                lenght--;
                return;
            }
        }
        throw new TransactionNotFoundException("Transaction with id # " + id + " not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction [] result = new Transaction[lenght];
        int i = 0;
        for (Transaction tmp = head.getNext(); tmp != head; tmp = tmp.getNext()) {
            result[i] = tmp;
            i++;
        }
        return result;
    }
}
