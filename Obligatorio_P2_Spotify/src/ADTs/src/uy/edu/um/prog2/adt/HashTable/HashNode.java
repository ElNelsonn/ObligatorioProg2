package ADTs.src.uy.edu.um.prog2.adt.HashTable;

public class HashNode<K,V> {
    private K key;
    private V value;

    public HashNode(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
