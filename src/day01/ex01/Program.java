package day01.ex01;

public class Program {
    public static void main(String[] args) {
        User John = new User("John", 5000);
        User Mike = new User("Mike", 200);
        User Serge = new User("Serge", 89749382);
        User Alfonso = new User("Alfonso", 21);
        User Niel = new User("Niel", 272727);
        User Tom = new User("Tom", 42);

        System.out.println(John);
        System.out.println(Mike);
        System.out.println(Serge);
        System.out.println(Alfonso);
        System.out.println(Niel);
        System.out.println(Tom);

        UserIdsGenerator id1 = UserIdsGenerator.getInstance();
        UserIdsGenerator id2 = UserIdsGenerator.getInstance();
        System.out.println(id1 == id2);
    }
}
