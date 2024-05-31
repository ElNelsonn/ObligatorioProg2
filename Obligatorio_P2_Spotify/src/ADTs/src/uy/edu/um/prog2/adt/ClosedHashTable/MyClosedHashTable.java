package ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;

public interface MyClosedHashTable {
    void put(String keyCountry, String keySong, int rankSong) throws ElementAlreadyInHash;
    int containsCountry(String keyCountry);

}
