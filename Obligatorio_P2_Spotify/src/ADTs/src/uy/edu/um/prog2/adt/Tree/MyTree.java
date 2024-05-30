package ADTs.src.uy.edu.um.prog2.adt.Tree;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.EmptyTreeException;

public interface MyTree<K, T> {

    void printPreOrder();

    void printInOrder();

    void printPostOrder();

    T findSort(K key);

    int countLeaf();

    int countCompleteElements();

    void insert(T value, K key);

    void delete(K key) throws ElementNotFound, EmptyTreeException;

}
