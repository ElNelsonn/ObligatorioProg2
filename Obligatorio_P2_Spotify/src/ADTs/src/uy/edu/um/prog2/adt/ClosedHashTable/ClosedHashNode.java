package ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;

public class ClosedHashNode {
    private String key;
    private String[] innerArray;


    public ClosedHashNode(String key, int size){
        this.key = key;
        this.innerArray = new String[size];
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String[] getValue() {
        return innerArray;
    }

    public void setValue(String[] newArray) {
        this.innerArray = newArray;
    }



    public void putSong(String keySong, int rankSong) throws ElementAlreadyInHash {
        if (this.containsSong(rankSong)) {
            throw new ElementAlreadyInHash();
        } else {
            this.innerArray[rankSong - 1] = keySong;
        }
    }

    public boolean containsSong(int rankSong) {
        return (innerArray[rankSong-1] != null);
    }

    public String[] getInnerArray() {
        return innerArray;
    }

    public void setInnerArray(String[] innerArray) {
        this.innerArray = innerArray;
    }
}
