package day01.ex03;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private int amount;
    private Transaction next;
    private Transaction prev;

    public void setNext(Transaction next) {
        this.next = next;
    }

    public void setPrev(Transaction prev) {
        this.prev = prev;
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction getNext() {
        return next;
    }

    public Transaction getPrev() {
        return prev;
    }

    public Transaction() {}

    public Transaction(UUID id, User recipient, User sender, TransferCategory transferCategory, int amount) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.amount = amount;
        this.prev = null;
        this.next = null;
        if (!validateTransaction())
            return;
        makeTransaction();
        System.out.println("Transaction #" + id + " SUCCESS: " +
                sender.getName() + "  ->  " + recipient.getName() + " amount " + amount );
    }

    private boolean validateTransaction() {
        if (transferCategory == TransferCategory.CREDITS) {
            if ((recipient.getBalance() + amount) < 0) {
                System.out.println("Transaction #" + id + " is invalid: insufficient funds");
                return false;
            }
            if (amount >= 0) {
                System.out.println("Transaction #" + id + " is invalid: amount must be negative");
                return false;
            }
        }
        else {
            if ((sender.getBalance() - amount) < 0) {
                System.out.println("Transaction #" + id + " is invalid: insufficient funds");
                return false;
            }
            if (amount <= 0) {
                System.out.println("Transaction #" + id + " is invalid: amount must be positive");
                return false;
            }
        }
        return true;
    }

    private void makeTransaction() {
            recipient.addBalance(amount);
    }
}

enum TransferCategory {
    DEBITS, CREDITS
}
