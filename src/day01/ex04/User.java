package day01.ex04;

public class User {
    private final int id;
    private String name;
    private int balance;
    private TransactionsList transactionsList;

    public void setTransactionsList(TransactionsList transactionsList) {
        this.transactionsList = transactionsList;
    }

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", name=" + name +
                ", balance=" + balance +
                ']';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public void addBalance(int amount) {
        this.balance += amount;
    }
}
