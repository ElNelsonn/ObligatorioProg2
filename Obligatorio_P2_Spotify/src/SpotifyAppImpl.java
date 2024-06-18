import ADTs.src.uy.edu.um.prog2.adt.ClosedHashTable.ClosedHashNode;
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
import java.security.spec.RSAOtherPrimeInfo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class SpotifyAppImpl {
    private MyHashTable<String, Song> songsHash;
    private MyHashTable<String, MyClosedHashTable> dateCountryHash;
    private MyHashTable<String, Integer> artistHash;

    public SpotifyAppImpl() {
        songsHash = new MyHashTableImpl<>(14000); //cant songs
        dateCountryHash = new MyHashTableImpl<>(360); //tam fechas
        artistHash = new MyHashTableImpl<>(10000);
    }

    private String nextDateString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(date, formatter);
        LocalDate nuevaFecha = date1.plusDays(1);
        return nuevaFecha.format(formatter);
    }

    public void loadData() {
        System.out.println();
        System.out.println("Loading data, please wait...");
        System.out.println();
        //"C:/Users/Joaco/Desktop/universal_top_spotify_songs33.csv"
        //"C:/Users/Joaco/Desktop/CSV_labP2/universal_top_spotify_songs.csv"
        //"C:/Users/santb/OneDrive - Universidad de Montevideo/Escritorio/universal_top_spotify_songs.csv"
        try {
            Scanner scanner = new Scanner(new File("C:/Users/Joaco/Desktop/universal_top_spotify_songs33.csv"));
            scanner.useDelimiter("\n");

            boolean test = false;
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (test) {

                    //CREAR CANCION --------------------------------------------------

                    String[] data = line.split("\",\"");
                    if (data[6] == null || data[6].equals("")) {
                        data[6] = "global";
                    }
                    String songKey = data[0];
                    data[0] = songKey.substring(1);
                    String[] artists = data[2].split(", ");
                    data[23] = data[23].replace(".", "");
                    int tempo = Integer.parseInt(data[23]);
                    Song newSong = new Song(data[1], data[0], artists, tempo);

                    // Inserto artistas --------------------------------
                    for (int i = 0; i < artists.length; i++) {
                        try {
                            artistHash.put(artists[i], 0);
                        } catch (ElementAlreadyInHash ignore) {
                        }
                    }
                    // INSERTAR CANCION AL MAIN HASH ----------------------------------
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
                        try {
                            dateCountryHash.put(data[7], countryHash);
                        } catch (ElementAlreadyInHash ignore) {
                        }
                        dateCountryHash.get(data[7]).put(data[6], data[0], ranking);
                    } else {
                        // Caso que existe la fecha pero no pais
                        dateCountryHash.get(data[7]).put(data[6], data[0], ranking);
                    }
                } else {
                    test = true;
                }
            }
            System.out.println("Data loaded.");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encuentra: " + e.getMessage());
        } catch (ElementAlreadyInHash e) {
            throw new RuntimeException(e);
        } catch (ElementNotFound e) {
            throw new RuntimeException(e);
        }

    }


    // Top 10 canciones en un país en un día dado.
    public void consulta1(String fechaRanking, String pais) {
        String[] keySongs = new String[10];
        try {
            String[] ranking = this.dateCountryHash.get(fechaRanking).getRankingArray(pais);
            System.out.println();
            System.out.println("Ranking " + fechaRanking + " " + pais + ":");
            System.out.println();
            for (int i = 0; i < 10; i++) {
                Song song = songsHash.get(ranking[i]);
                String artists = String.join(", ", song.getArtists());
                System.out.println("    " + (i + 1) + ") " + "Song name: " + song.getName());
                System.out.println("       Artists: " + artists);
                System.out.println();
            }
            Scanner temp = new Scanner(System.in);
            System.out.print("Para volver al menu ingrese cualquier valor: ");
            temp.nextLine();

            System.out.println();
            System.out.println();

        } catch (ElementNotFound e) {
            System.out.println();
            System.out.println("Ha ingresado una fecha o pais invalido.");
        }
    }

    public void consulta2(String fechaDeConsulta) {
        try {
            ClosedHashNode[] arrayPaises = this.dateCountryHash.get(fechaDeConsulta).getArray();
            int sizeArray = arrayPaises.length;
            String idSong;
            for (int i = 0; i < sizeArray; i++) {
                if (!(arrayPaises[i] == null)) {
                    for (int j = 0; j < 50; j++) {
                        idSong = arrayPaises[i].getInnerArray()[j];
                        if (!(idSong == null)) {
                            this.songsHash.get(idSong).incCounter();
                        }
                    }
                }
            }

            Song[] top5Songs = new Song[5];
            int sizeSongs = this.songsHash.getArray().length;

            Song songToCheck;
            for (int i = 0; i < sizeSongs; i++) {
                if (!(this.songsHash.getArray()[i] == null)) {
                    songToCheck = this.songsHash.getArray()[i].getValue();

                    if (top5Songs[0] == null) {

                        top5Songs[0] = songToCheck;
                    } else if (top5Songs[1] == null) {

                        top5Songs[1] = songToCheck;
                    } else if (top5Songs[2] == null) {

                        top5Songs[2] = songToCheck;
                    } else if (top5Songs[3] == null) {

                        top5Songs[3] = songToCheck;
                    } else if (top5Songs[4] == null) {

                        top5Songs[4] = songToCheck;
                    } else {

                        int posLowest = 0;
                        for (int j = 1; j < 5; j++) {
                            if (top5Songs[posLowest].getTemp_counter() > top5Songs[j].getTemp_counter()) {
                                posLowest = j;
                            }
                        }

                        if (songToCheck.getTemp_counter() > top5Songs[posLowest].getTemp_counter()) {
                            top5Songs[posLowest] = songToCheck;
                        }
                    }
                }
            }

            Arrays.sort(top5Songs);
            System.out.println();
            System.out.println("Top 5 canciones con mas apariciones en " + fechaDeConsulta + ":");
            System.out.println();
            for (int i = 4; i >= 0; i--) {
                Song song = top5Songs[i];
                String artists = String.join(", ", song.getArtists());
                System.out.println("    " + (5 - i) + ") " + "Song name: " + song.getName());
                System.out.println("       Apariciones: " + song.getTemp_counter());
                System.out.println("       Artists: " + artists);
                System.out.println();
            }

            for (int i = 0; i < sizeSongs; i++) {
                if (!(this.songsHash.getArray()[i] == null)) {
                    this.songsHash.getArray()[i].getValue().resetCounter();
                }
            }

            Scanner temp = new Scanner(System.in);
            System.out.print("Para volver al menu ingrese cualquier valor: ");
            temp.nextLine();

            System.out.println();
            System.out.println();

        } catch (ElementNotFound e) {
        }
    }

    public void consulta3(String fecha1, String fecha2) {
        try {
            System.out.println("ello");
            String fechaTemp = fecha1;
            ClosedHashNode[] arrayPaises;
            boolean tempBoolean = false;
            while (!(fechaTemp.equals(fecha2)) || (tempBoolean)) {

                arrayPaises = this.dateCountryHash.get(fechaTemp).getArray();
                int sizeArray = arrayPaises.length;

                String idSong;
                String[] artists;
                for (int i = 0; i < sizeArray; i++) {
                    if (!(arrayPaises[i] == null)) {
                        for (int j = 0; j < 50; j++) {
                            idSong = arrayPaises[i].getInnerArray()[j];
                            if (!(idSong == null)) {
                                artists = this.songsHash.get(idSong).getArtists();
                                for (int k = 0; k < artists.length; k++) {
                                    if(artists[k]!=null) {
                                        int newValuee = this.artistHash.get(artists[k]);
                                        newValuee++;
                                        this.artistHash.setValue(artists[k], newValuee);
                                    }
                                }
                            }
                        }
                    }
                }
                fechaTemp = this.nextDateString(fechaTemp);

                if (tempBoolean) {
                    break;
                }
                else if (fechaTemp.equals(fecha2)) {
                    tempBoolean = true;
                }
            }
            System.out.println("hello");

            String[] top7Artist = new String[7];
            int sizeSongs = this.artistHash.getArray().length;

            String artistToCheck;
            for (int i = 0; i < sizeSongs; i++) {
                if (!(this.artistHash.getArray()[i] == null)) {
                    artistToCheck = this.artistHash.getArray()[i].getKey();

                    if (top7Artist[0] == null) {

                        top7Artist[0] = artistToCheck;
                    } else if (top7Artist[1] == null) {

                        top7Artist[1] = artistToCheck;
                    } else if (top7Artist[2] == null) {

                        top7Artist[2] = artistToCheck;
                    } else if (top7Artist[3] == null) {

                        top7Artist[3] = artistToCheck;
                    } else if (top7Artist[4] == null) {

                        top7Artist[4] = artistToCheck;
                    } else if (top7Artist[5] == null){

                        top7Artist[5] = artistToCheck;
                    } else if (top7Artist[6] == null){

                        top7Artist[6] = artistToCheck;

                    } else {

                        int posLowest = 0;
                        for (int j = 1; j < 7; j++) {
                            if (artistHash.get(top7Artist[posLowest]) > artistHash.get(top7Artist[j])) {
                                posLowest = j;
                            }
                        }

                        if (artistHash.get(top7Artist[posLowest]) < artistHash.get(artistToCheck)) {
                            top7Artist[posLowest] = artistToCheck;
                        }
                    }
                }
            }

            Arrays.sort(top7Artist);
            System.out.println();
            System.out.println("Top 7 artistas con mas apariciones en " + fecha1 + " - " + fecha2 + ":");
            System.out.println();
            for (int i = 6; i >= 0; i--) {
                String artist = top7Artist[i];
                System.out.println("    " + (7 - i) + ") " + "Artist name: " + artist);
                System.out.println("       Apariciones: " + artistHash.get(artist));
                System.out.println();
            }
            System.out.println(artistHash.get("Feid"));

            for (int i = 0; i < artistHash.getArray().length; i++) {
                if (!(this.artistHash.getArray()[i] == null)) {
                    this.artistHash.getArray()[i].setValue(0);
                }
            }
            System.out.println(artistHash.get("Feid"));


            Scanner temp = new Scanner(System.in);
            System.out.print("Para volver al menu ingrese cualquier valor: ");
            temp.nextLine();

            System.out.println();
            System.out.println();

        } catch (ElementNotFound e) {
            System.out.println("bye");
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
