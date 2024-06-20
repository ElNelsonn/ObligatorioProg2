
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//
//
public class Main {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Menu menu = new Menu();
        SpotifyAppImpl spoti = new SpotifyAppImpl();
        spoti.loadData();
        menu.showMenu(spoti);

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Memoria usada: " + memoryUsed + " bytes");

    }
}
