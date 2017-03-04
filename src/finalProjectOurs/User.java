package finalProjectOurs;

/**
 * Created by Aleksey on 29.01.2017.
 */
public class User {
    long userID;
    String login;
    String password;


    public User(long userID, String login, String password) {
        this.userID = userID;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserID() != user.getUserID()) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        return getPassword().equals(user.getPassword());

    }

    @Override
    public int hashCode() {
        int result = (int) (getUserID() ^ (getUserID() >>> 32));
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}
