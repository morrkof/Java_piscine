package day01.ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory; // enum
    private Integer amount; // for outgoing negative, for incoming positive

    public Transaction(UUID identifier, User recipient, User sender, TransferCategory transferCategory, Integer amount) {
        this.identifier = identifier;
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.amount = amount;
        validateTransaction();
        makeTransaction();
        System.out.println("Transaction #" + identifier + " SUCCESS");
    }

    private void validateTransaction() {
        if (transferCategory == TransferCategory.CREDITS) {
            if ((recipient.getBalance() + amount) < 0) {
                System.out.println("Transaction #" + identifier + " is invalid: insufficient funds");
                System.exit(1);
            }
            if (amount >= 0) {
                System.out.println("Transaction #" + identifier + " is invalid: amount must be negative");
                System.exit(1);
            }
        }
        if (transferCategory == TransferCategory.DEBITS) {
            if ((sender.getBalance() - amount) < 0) {
                System.out.println("Transaction #" + identifier + " is invalid: insufficient funds");
                System.exit(1);
            }
            if (amount <= 0) {
                System.out.println("Transaction #" + identifier + " is invalid: amount must be positive");
                System.exit(1);
            }
        }
    }

    private void makeTransaction() {
            recipient.addBalance(amount);
    }
}

enum TransferCategory {
    DEBITS, CREDITS
}
