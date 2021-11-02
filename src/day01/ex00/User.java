package day01.ex00;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance; // cannot be negative

    public User(Integer identifier, String name, Integer balance) {
        this.identifier = identifier;
        this.name = name;
        this.balance = balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void addBalance(Integer amount) {
        this.balance += amount;
    }
}
