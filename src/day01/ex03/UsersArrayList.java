package day01.ex03;

public class UsersArrayList implements UsersList {
    private User[] data;
    private int num;

    public UsersArrayList() {
        this.data = new User[10];
        this.num = 0;
    }

    @Override
    public void addUser(User user) {
        if (num == data.length) {
            User[] newdata = new User[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newdata[i] = data[i];
            }
            data = newdata;
        }
        data[num] = user;
        num++;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < num; i++) {
            if (data[i].getId() == id)
                return data[i];
        }
        throw new UserNotFoundException("User with id # " + id + " not found");
    }

    @Override
    public User getUserByIndex(int index) {
        return data[index];
    }

    @Override
    public int getNumberOfUsers() {
        return num;
    }

    @Override
    public String toString() {
        String users = "";
        for (int i = 0; i < data.length; i++) {
            users += data[i];
            users += " ";
        }
        return "List[data=" + users + ']';
    }
}
