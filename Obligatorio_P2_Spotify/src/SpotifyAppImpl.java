import ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable.MyClosedHashTable;
import ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable.MyClosedHashTableImpl;
import ADTs.src.uy.edu.um.prog2.adt.HashTable.MyHashTable;
import ADTs.src.uy.edu.um.prog2.adt.HashTable.MyHashTableImpl;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementAlreadyInHash;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SpotifyAppImpl {
    private MyHashTable<String,Song> songsHash;
    private MyHashTable<String, MyClosedHashTable> dateCountryHash;

    public SpotifyAppImpl(){
        songsHash = new MyHashTableImpl<>(14000); //cant songs
        dateCountryHash =  new MyHashTableImpl<>(360); //tam fechas
    }


    //"C:/Users/Joaco/Desktop/CSV_labP2/universal_top_spotify_songs.csv"     Mi URL pruebas
    public void loadData(String url){
        try {
            Scanner scanner = new Scanner(new File(url));
            scanner.useDelimiter("\n");

            boolean test = false;
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (test) {

                    //CREAR CANCION --------------------------------------------------

                    String[] data = line.split("\",\"");
                    String songKey = data[0];
                    data[0] = songKey.substring(1);
                    String[] artists = data[2].split(",");
                    int tempo = Integer.parseInt(data[23]);
                    Song newSong = new Song(data[1], data[0], artists, tempo);

                    //INSERTAR CANCION AL MAIN HASH ----------------------------------

                    this.songsHash.put(newSong.getSpotifyId(), newSong);
                    int ranking = Integer.parseInt(data[3]);

                    // INSERTAMOS KEY DE CANCION EN EL DATECOUNTRYHASH ----------------

                        // Caso que no existe la fecha
                    int dateIndex = dateCountryHash.contains(data[7]);
                    if (dateIndex == -1) {
                        MyClosedHashTable countryHash = new MyClosedHashTableImpl(100,50);
                        dateCountryHash.put(data[7],countryHash);
                        dateCountryHash.get(data[7]).put(data[6],data[0],ranking);
                    } else {
                        // Caso que existe la fecha pero no pais
                        dateCountryHash.get(data[7]).put(data[6],data[0],ranking);
                    }




                } else {
                    test = true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encuentra: " + e.getMessage());
        } catch (ElementAlreadyInHash e) {
            throw new RuntimeException(e);
        } catch (ElementNotFound e) {
            throw new RuntimeException(e);
        }
    }



}

