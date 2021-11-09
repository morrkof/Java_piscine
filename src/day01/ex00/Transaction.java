package day01.ex00;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private int amount;

    public Transaction(UUID id, User recipient, User sender, TransferCategory transferCategory, int amount) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.amount = amount;
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
