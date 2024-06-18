package ADTs.src.uy.edu.um.prog2.adt.HashTable;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;

public class MyHashTableImpl<K,V> implements MyHashTable<K,V> {
    private HashNode<K, V>[] array;
    private int elementsIn = 0;
    private int sizeArray;

    public MyHashTableImpl(int size) {
        this.array = (HashNode<K, V>[]) new HashNode[size];
        this.sizeArray = size;
    }

    private int index(K key) {
        return Math.abs(key.hashCode() % sizeArray);
    }

    public void setValue(K nombre, V value) {
        int index = contains(nombre);
        if (index != -1) {
            this.array[index].setValue(value);
        }
    }

    private void duplicate(){
        int oldSizeArray = this.sizeArray;
        HashNode<K, V>[] oldArray = this.array;

        this.sizeArray = oldSizeArray * 2;
        this.array = (HashNode<K, V>[]) new HashNode[sizeArray];
        this.elementsIn = 0;

        for (HashNode<K,V> node : oldArray) {
            if (node != null) {
                try {
                    put(node.getKey(), node.getValue());
                } catch (ElementAlreadyInHash ignore){
                }
            }
        }
    }

    private void sort() {
        int oldSizeArray = this.sizeArray;
        HashNode<K, V>[] oldArray = this.array;

        this.sizeArray = oldSizeArray;
        this.array = (HashNode<K, V>[]) new HashNode[sizeArray];
        this.elementsIn = 0;

        for (HashNode<K,V> node : oldArray) {
            if (node != null) {
                try {
                    this.put(node.getKey(), node.getValue());
                } catch (ElementAlreadyInHash ignore) {
                }
            }
        }
    }

    public void put(K key, V value) throws ElementAlreadyInHash {
        if (contains(key) == -1) {
            int indexOriginal = index(key);
            int index = indexOriginal;
            while ((index < sizeArray - 1) && (array[index] != null)) {
                index++;
            }
            if (array[index] == null) {
                array[index] = new HashNode<>(key, value);
                elementsIn++;
            } else {
                index = 0;
                while (array[index] != null) {
                    index++;
                }
                array[index] = new HashNode<>(key, value);
                elementsIn++;
            }
            if ((double) elementsIn / sizeArray >= 0.75) {
                this.duplicate();
            }
        } else {
            throw new ElementAlreadyInHash();
        }
    }

    public int contains(K key) {
        int index = index(key);
        for (int i = index; i < this.sizeArray; i++) {
            if (array[i] == null) {
                return -1;
            } else if (array[i].getKey().equals(key)) {
                return i;
            }
        }
        for (int i = 0; i < index; i++) {
            if (array[i] == null) {
                return -1;
            } else if (array[i].getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void remove(K key) throws ElementNotFound {
        if (contains(key) != -1) {
            array[index(key)] = null;
            elementsIn--;
            this.sort();
        } else {
            throw new ElementNotFound();
        }
    }

    public V get(K key) throws ElementNotFound {
        int index = contains(key);
        if (index != -1) {
            return this.array[index].getValue();
        }
        throw new ElementNotFound();
    }

    public HashNode<K, V>[] getArray() {
        return array;
    }

    public void setArray(HashNode<K, V>[] array) {
        this.array = array;
    }

    public int getElementsIn() {
        return elementsIn;
    }

    public void setElementsIn(int elementsIn) {
        this.elementsIn = elementsIn;
    }

    public int getSizeArray() {
        return sizeArray;
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }
}
