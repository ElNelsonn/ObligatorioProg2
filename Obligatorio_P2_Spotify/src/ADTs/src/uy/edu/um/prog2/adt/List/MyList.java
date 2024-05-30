package ADTs.src.uy.edu.um.prog2.adt.List;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.*;

public interface MyList<T> {

    void add(T value);

    T get(int position) throws EmptyListException, OutOfRangeException;

    boolean contains(T value);

    void remove(T value) throws ElementNotFound, EmptyListException;

    int size();


}
