package day01.ex02;


public class Program {
    public static void main(String[] args) {
        UsersList list = new UsersArrayList();
        System.out.println("create: " + list);

        System.out.println("");
        for (int i = 0; i < 13; i++) {
            list.addUser(new User("username#" + i, i+100));
            System.out.println("added user: " + list);
        }

        System.out.println("");
        System.out.println(list.getUserById(5));
        try {
            System.out.println(list.getUserById(22));
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        System.out.println("");
        System.out.println(list.getUserByIndex(8));
        try {
            System.out.println(list.getUserByIndex(22));
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }
    }
}
