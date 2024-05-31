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
import java.util.Arrays;
import java.util.Scanner;

public class SpotifyAppImpl {
    private MyHashTable<String, Song> songsHash;
    private MyHashTable<String, MyClosedHashTable> dateCountryHash;

    public SpotifyAppImpl() {
        songsHash = new MyHashTableImpl<>(14000); //cant songs
        dateCountryHash = new MyHashTableImpl<>(360); //tam fechas
    }


    //"C:/Users/Joaco/Desktop/CSV_labP2/universal_top_spotify_songs.csv"     Mi URL pruebas
    public void loadData() {
        try {
            int count = 0;
            Scanner scanner = new Scanner(new File("C:/Users/Joaco/Desktop/universal_top_spotify_songs33.csv"));
            scanner.useDelimiter("\n");

            boolean test = false;
            while (scanner.hasNext()) {
                count++;
                System.out.println(count);
                String line = scanner.next();
                if (test) {

                    //CREAR CANCION --------------------------------------------------

                    String[] data = line.split("\",\"");
                    if (data[6] == null || data[6].equals("")) {
                        data[6] = "global";
                    }
                    String songKey = data[0];
                    data[0] = songKey.substring(1);
                    String[] artists = data[2].split(",");
                    data[23] = data[23].replace(".","");
                    int tempo = Integer.parseInt(data[23]);
                    Song newSong = new Song(data[1], data[0], artists, tempo);

                    //INSERTAR CANCION AL MAIN HASH ----------------------------------
                    int ranking = Integer.parseInt(data[3]);
                    try {
                        this.songsHash.put(newSong.getSpotifyId(), newSong);
                    } catch (ElementAlreadyInHash ignore) {
                    }
                    // INSERTAMOS KEY DE CANCION EN EL DATECOUNTRYHASH ----------------

                    // Caso que no existe la fecha
                    int dateIndex = dateCountryHash.contains(data[7]);
                    if (dateIndex == -1) {
                        MyClosedHashTable countryHash = new MyClosedHashTableImpl(100, 50);
                        dateCountryHash.put(data[7], countryHash);
                        dateCountryHash.get(data[7]).put(data[6], data[0], ranking);
                    } else {
                        // Caso que existe la fecha pero no pais
                        dateCountryHash.get(data[7]).put(data[6], data[0], ranking);
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


    public void consulta1(String fechaRanking, String pais) {
        boolean estado = true;
        while (estado) {
            String[] keySongs = new String[10];
            try {
                String[] ranking = this.dateCountryHash.get(fechaRanking).getRankingArray(pais);
                System.out.println("Ranking " + fechaRanking + " " + pais + ":");
                for (int i = 0; i < 10; i++) {
                    Song song = songsHash.get(ranking[i]);
                    String artists = String.join(", ", song.getArtists());
                    System.out.println("    " + (i + 1) + ") " + "Name: " + song.getName() + " Artists: " + artists);

                }

            } catch (ElementNotFound e) {
                System.out.println("País o fecha invalida.");
            }


        }
    }


//    public void infoErroneaTomeUnaDecsion() {
//        Scanner scanner1 = new Scanner(System.in);
//        String opcion = scanner1.nextLine();
//
//        switch (opcion) {
//            case 1:
//                System.out.println("Has elegido la opción 1");
//                // Lógica para la opción 1
//                break;
//            case 2:
//                System.out.println("Has elegido la opción 2");
//                // Lógica para la opción 2
//                break;
//            case 3:
//
//        }
//
//
//    }


}
