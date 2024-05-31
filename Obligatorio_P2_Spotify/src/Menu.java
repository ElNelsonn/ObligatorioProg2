import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int opcion;
    private boolean prendido = true;
    private String fGris ="\u001B[47m";
    private String fVerde = "\033[42m";
    private String negro = "\033[30m";
    private String b = "\u001B[0m";


    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        while (this.prendido) {
            System.out.println(fGris + "                                                                                                                        " + b);
            System.out.println(fGris + "                                                                                                                        " + b);
            System.out.print(fGris + "                                                  " + b); System.out.print(fVerde + negro + " Spotify App " + b);System.out.println(fGris + "                                                         " + b);
            System.out.println(fGris + "                                                                                                                        " + b);
            System.out.println(fGris + negro +  "               1) Top 10 canciones en un país en un día dado.                                                           " + b);
            System.out.println(fGris + negro +  "               2) Top 5 canciones que aparecen en más top 50 en un día dado.                                            " + b);
            System.out.println(fGris + negro +  "               3) Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.                           " + b);
            System.out.println(fGris + negro +  "               4) Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.                   " + b);
            System.out.println(fGris + negro +  "               5) Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.         " + b);
            System.out.println(fGris + negro +  "               6) Finalizar programa                                                                                    " + b);
            System.out.println(fGris + "                                                                                                                        " + b);



            while (true) {
                try {
                    System.out.print("Ingrese el numero de la cosulta que desea realizar: ");
                    opcion = scanner.nextInt();
                    if (opcion < 1 || 6 < opcion) {
                        System.out.println("Opcion no valida, ingrese un numero del 1 al 6: ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opcion no valida, ingrese un numero del 1 al 6: ");
                }
            }

            switch (opcion) {
                case 1:


                    break;
                case 2:


                    break;
                case 3:


                    break;

                case 4:

                    break;

                case 5:

                    break;
                case 6:


                    break;
                default:
                    System.out.println("Opcion no valida, ingrese un numero del 1 al 6: ");
            }



        }






        scanner.close();
    }












}
