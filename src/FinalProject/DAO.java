package FinalProject;



import java.io.File;

import java.util.List;

/**
 * Created by Aleksey on 28.01.2017.
 */

public interface DAO <T> {

    boolean add(T t);

    boolean edit(T t);

    boolean remove(T t);

    List<T> getBase();

}
