package ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;

public class MyClosedHashTableImpl implements MyClosedHashTable {
    private ClosedHashNode[] array;
    private int innerArraylength ;
    private int sizeArray;


    public void MyClosedHashTableImpl(int sizeArray, int innerArraylength) {
        this.array = new ClosedHashNode[sizeArray];
        this.innerArraylength = innerArraylength;
        this.sizeArray = sizeArray;
    }

    private int index(String key) {
        return Math.abs(key.hashCode() % sizeArray);
    }

    public void put(String keyCountry, String keySong, int rankSong) throws ElementAlreadyInHash {
        int index = this.index(keyCountry);
        if (this.containsCountry(keyCountry) == -1) {
            ClosedHashNode newNode = new ClosedHashNode(keyCountry, 50);
            this.array[index] = newNode;
            this.array[index].putSong(keySong, rankSong);
        } else if (this.array[index].containsSong(rankSong)) {
            throw new ElementAlreadyInHash();
        } else {
            this.array[index].putSong(keySong, rankSong);
        }
    }


    public int containsCountry(String keyCountry) {
        int index = this.index(keyCountry);
        for (int i = index; i < this.sizeArray; i++) {
            if (array[i] == null) {
                return -1;
            } else if (array[i].getKey().equals(keyCountry)) {
                return i;
            }
        }
        for (int i = 0; i < index; i++) {
            if (array[i] == null) {
                return -1;
            } else if (array[i].getKey().equals(keyCountry)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getRanking(String keyCountry) {
        int index = this.index(keyCountry);
        return this.array[index].getInnerArray();
    }


    public ClosedHashNode[] getArray() {
        return array;
    }

    public void setArray(ClosedHashNode[] array) {
        this.array = array;
    }

    public int getInnerArraylength() {
        return innerArraylength;
    }

    public void setInnerArraylength(int innerArraylength) {
        this.innerArraylength = innerArraylength;
    }

    public int getSizeArray() {
        return sizeArray;
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }
}
