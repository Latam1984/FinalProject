package finalProjectOurs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 29.01.2017.
 */
public class UserDAO implements DAO<User> {
    private File userBase;

    private  List<User> users = new ArrayList<>();

    public  List<User> getUsers() {
        return users;
    }

    public UserDAO() {
        userBase = new File("src/finalProjectOurs/UserBase.txt");
        try {
            userBase.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override

    public void add(User user) {
        if (users.stream().allMatch(user1 -> user.getUserID() != user1.getUserID())) {
            users.add(user);
            System.out.println("Пользователь добавлен в базу!");
        } else {
            System.out.println("Такой пользователь есть в базе");
        }

    }

    @Override
    public void delete(User user) {
        if (find(user)) {
            users.remove(user);
            System.out.println("Пользователь удален.");
        } else
            System.out.println("Пользователь не может быть удален. ");
    }

    @Override
    public boolean find(User user) {
        boolean someUser = false;
        if (users.stream().anyMatch(user1 -> user.getUserID() == user1.getUserID())) {
            System.out.println("Пользователь найден. " + user);
            return someUser = true;
        }
        System.out.println("Пользователь не найден. ");
        return someUser;
    }

    @Override
    public void addToBase(User user1) {
        try (FileWriter fr = new FileWriter(userBase, true);
             BufferedWriter bw = new BufferedWriter(fr)) {
            if (readBaseUsers().stream().allMatch(user -> !user.getLogin().equals(user1.getLogin()))){
                bw.write(user1.getUserID() +" " + user1.getLogin() + " " + user1.getPassword() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> readBaseUsers (){
        try (
                FileReader fr = new FileReader(userBase);
                BufferedReader br = new BufferedReader(fr)
                ) {
            String someString;
            while ((someString = br.readLine()) != null){
                String [] someStrings = someString.split(" ");
                User user = new User(Long.parseLong(someStrings[0]), someStrings[1], someStrings[2]);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "userBase=" + userBase +
                ", users=" + users +
                '}';
    }
}
