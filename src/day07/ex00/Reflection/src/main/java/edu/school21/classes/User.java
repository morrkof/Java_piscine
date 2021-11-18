package edu.school21.classes;

public class User {
    private String firstName;
    private String lastName;
    private int height;

    public int grow(int value) {
        this.height += value;
        return height;
    }

    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    public User() {
        this.firstName = "Undefined";
        this.lastName = "Undefined";
        this.height = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                '}';
    }
}
