package day01.ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User( "John", 5000);
        User Mike = new User( "Mike", 200);
        User Pip = new User( "Pip", 42);

        TransactionsService service = new TransactionsService();
        service.addUser(John);
        service.addUser(Mike);
        service.addUser(Pip);
        service.transferTransaction(John.getId(), Mike.getId(), 100);
        System.out.println(John);
        System.out.println(Mike);
        service.transferTransaction(Pip.getId(), John.getId(), 1000);
        System.out.println(Pip);
        System.out.println(John);
        service.removeUserTransaction(Pip.getId(), Pip.getTransactionsList().toArray()[0].getId());
    }
}
