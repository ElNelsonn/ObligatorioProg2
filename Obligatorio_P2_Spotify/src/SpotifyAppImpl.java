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
    private MyHashTable<String,Song> mainHash;
    private MyHashTable<String, MyClosedHashTable> dateCountryHash;

    public SpotifyAppImpl(){
        mainHash = new MyHashTableImpl<>(10516);
        dateCountryHash =  new MyHashTableImpl<>(1000); //tam fechas
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
                    String[] vec = line.split("\",\"");
                    if(dateCountryHash.contains(vec[7])==-1){
                        dateCountryHash.put(vec[7], dateCountryHash.get(vec[6]));
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

