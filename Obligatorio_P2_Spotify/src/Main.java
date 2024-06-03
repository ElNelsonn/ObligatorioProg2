
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//
//
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        SpotifyAppImpl spoti = new SpotifyAppImpl();
        spoti.loadData();
        menu.showMenu(spoti);

    }
}
