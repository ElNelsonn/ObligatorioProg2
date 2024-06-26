package ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;

public class MyClosedHashTableImpl implements MyClosedHashTable {
    private ClosedHashNode[] array;
    private int innerArraylength ;
    private int sizeArray;


    public MyClosedHashTableImpl(int sizeArray, int innerArraylength) {
        this.array = new ClosedHashNode[sizeArray];
        this.innerArraylength = innerArraylength;
        this.sizeArray = sizeArray;
    }

    private int index(String key) {
        return Math.abs(key.hashCode() % sizeArray);
    }

    public void put(String keyCountry, String keySong, int rankSong) throws ElementAlreadyInHash {
        int index = this.index(keyCountry);
        int position = this.containsCountry(keyCountry);
        int i = index;
        if (position == -1) {
            while ((i < this.sizeArray) && (array[i] != null)) {
                i++;
            }
            if (array[i] == null) {
                ClosedHashNode newNode = new ClosedHashNode(keyCountry, innerArraylength);
                this.array[i] = newNode;
                this.array[i].getInnerArray()[rankSong - 1] = keySong;
            } else {
                int j = 0;
                while ((j < index) && (array[j] != null)) {
                    j++;
                }
                if (array[j] == null) {
                    ClosedHashNode newNode = new ClosedHashNode(keyCountry, innerArraylength);
                    this.array[j] = newNode;
                    this.array[j].getInnerArray()[rankSong - 1] = keySong;
                }
            }
        } else {
            //caso que existe pais y no la cancion
            array[position].getInnerArray()[rankSong-1] = keySong;
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

    public String[] getRankingArray(String countryKey) throws ElementNotFound {
        int posicion = this.containsCountry(countryKey);
        if (posicion == -1) {
            throw new ElementNotFound();
        }
        return this.array[posicion].getValue();
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
