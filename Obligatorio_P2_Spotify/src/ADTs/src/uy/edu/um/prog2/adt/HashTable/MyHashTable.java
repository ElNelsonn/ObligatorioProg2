package ADTs.src.uy.edu.um.prog2.adt.HashTable;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;

public interface MyHashTable<K,V> {

    public void put(K key, V value) throws ElementAlreadyInHash;

    public int contains(K key);

    public V get(K key) throws ElementNotFound;

    public void remove(K key) throws ElementNotFound;

    public HashNode<K, V>[] getArray();

    void setValue(K nombre, V value);

}
