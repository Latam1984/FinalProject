package finalProjectOurs;

/**
 * Created by Aleksey on 29.01.2017.
 */
public interface DAO <T> {
    void add (T t);
    void delete (T t);
    boolean find (T t);
    void addToBase(T t);
}
