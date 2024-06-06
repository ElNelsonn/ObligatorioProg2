import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int opcion;
    private boolean prendido = true;
    private String fGris ="\u001B[47m";
    private String fVerde = "\033[42m";
    private String negro = "\033[30m";
    private String b = "\u001B[0m";

    public boolean fechaValida(String fecha){
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!fecha.matches(regex)) {
            return false;
        } else {
            return true;
        }
    }

    public void showMenu(SpotifyAppImpl app) {
        Scanner scanner1 = new Scanner(System.in);
        int opcion;


        while (this.prendido) {
//            System.out.println();
//            System.out.println();
//            System.out.println(fGris + "                                                                                                                        " + b);
//            System.out.println(fGris + "                                                                                                                        " + b);
//            System.out.print(fGris + "                                                  " + b); System.out.print(fVerde + negro + " Spotify App " + b);System.out.println(fGris + "                                                         " + b);
//            System.out.println(fGris + "                                                                                                                        " + b);
//            System.out.println(fGris + negro +  "               1) Top 10 canciones en un país en un día dado.                                                           " + b);
//            System.out.println(fGris + negro +  "               2) Top 5 canciones que aparecen en más top 50 en un día dado.                                            " + b);
//            System.out.println(fGris + negro +  "               3) Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.                           " + b);
//            System.out.println(fGris + negro +  "               4) Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.                   " + b);
//            System.out.println(fGris + negro +  "               5) Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.         " + b);
//            System.out.println(fGris + negro +  "               6) Finalizar programa                                                                                    " + b);
//            System.out.println(fGris + "                                                                                                                        " + b);
//            System.out.println(fGris + "                                                                                                                        " + b);

            System.out.println();
            System.out.print("                                                  " + b); System.out.print(fVerde + negro + " Spotify App " + b);System.out.println("                                                         " + b);
            System.out.println("                                                                                                                        " + b);
            System.out.println("               1) Top 10 canciones en un país en un día dado.                                                           " + b);
            System.out.println("               2) Top 5 canciones que aparecen en más top 50 en un día dado.                                            " + b);
            System.out.println("               3) Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.                           " + b);
            System.out.println("               4) Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.                   " + b);
            System.out.println("               5) Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.         " + b);
            System.out.println("               6) Finalizar programa                                                                                    " + b);









            while (true) {
                try {
                    System.out.println();
                    System.out.print("Ingrese el numero de la cosulta que desea realizar: ");
                    opcion = scanner1.nextInt();
                    System.out.println();

                    if (opcion < 1 || 6 < opcion) {
                        System.out.println("Opcion no valida, ingrese un numero del 1 al 6. ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Opcion no valida, ingrese un numero del 1 al 6. ");
                    scanner1.nextLine();
                }
            }


            switch (opcion) {

                // Top 10 canciones en un país en un día dado.
                case 1:
                    boolean inicioFuncion1 = true;
                    while (inicioFuncion1) {
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Top 10 canciones en un país en un día dado.");
                        System.out.print("    Ingrese la fecha del ranking (YYYY-MM-DD): ");
                        String fecha = scanner2.nextLine();

                        if (fechaValida(fecha)) {
                            System.out.print("    Ingrese el pais: ");
                            String pais = scanner2.nextLine();
                            app.consulta1(fecha,pais);
                            inicioFuncion1 = false;

                        } else {
                            boolean validez = fechaValida(fecha);
                            while (!validez) {
                                System.out.println();
                                System.out.println("Fecha incorrecta, esta debe de tener el formato (YYYY-MM-DD)");
                                System.out.println("En caso de querer volver al menu oprima 0. ");
                                System.out.print("  Ingrese un valor: ");
                                String fecha1 = scanner2.nextLine();

                                if (fecha1.equals("0")) {
                                    inicioFuncion1 = false;
                                    validez = true;
                                } else {
                                    validez = fechaValida(fecha1);
                                    if (validez) {
                                        System.out.print("  Ingrese el pais: ");
                                        String pais = scanner2.nextLine();
                                        app.consulta1(fecha1,pais);
                                        inicioFuncion1 = false;
                                    }
                                }
                            }
                        }
                    }
                    break;

                //Top 5 canciones que aparecen en más top 50 en un día dado.
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
        scanner1.close();
    }












}
