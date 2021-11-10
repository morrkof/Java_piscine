package day01.ex03;

public class Program {
    public static void main(String[] args) {
        UsersList list = new UsersArrayList();
        System.out.println("create: " + list);

        System.out.println("");
        for (int i = 0; i < 4; i++) {
            list.addUser(new User("username#" + i, i+100));
            System.out.println("added user: " + list);
            System.out.println("number: " + list.getNumberOfUsers());
        }
        System.out.println("get by id: " + list.getUserById(2));
        System.out.println("get by index: " + list.getUserByIndex(3));
    }
}
