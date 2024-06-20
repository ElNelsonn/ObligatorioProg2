import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int opcion;
    private boolean prendido = true;
    private final String fVerde = "\033[42m";
    private final String negro = "\033[30m";
    private final String b = "\u001B[0m";
    private final Scanner scanner = new Scanner(System.in);

    private boolean fechaValida(String fecha) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return fecha.matches(regex);
    }

    private float temposValidos(String temp) {
        try {
            return Float.parseFloat(temp);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isDate1AfterDate2(String date1, String date2) {
        try {
            if (date1.equals(date2)) {
                return true;
            }
            LocalDate d1 = LocalDate.parse(date1);
            LocalDate d2 = LocalDate.parse(date2);
            return d2.isAfter(d1);
        } catch (DateTimeParseException e) {
            System.out.println("La fecha inicial es ");
            return false;
        }
    }

    private String promptForDate(String message) {
        while (true) {
            System.out.println();
            System.out.print(message);
            String date = scanner.nextLine();
            if (fechaValida(date)) {
                return date;
            }
            System.out.println();
            System.out.println("Fecha incorrecta, esta debe de tener el formato (YYYY-MM-DD)");
            System.out.print("En caso de querer volver al menu oprima 0, si quiere volver a la consulta ingrese cualquier valor: ");
            if (scanner.nextLine().equals("0")) {
                return null;
            }
        }
    }

    private String promptForInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void pedirDatos1(SpotifyAppImpl app) {
        System.out.println();
        System.out.println("Top 10 canciones en un país en un día dado.");
        String fecha = promptForDate("Ingrese la fecha del ranking (YYYY-MM-DD): ");
        if (fecha != null) {
            String pais = promptForInput("Ingrese el pais: ");
            app.consulta1(fecha, pais);
        }
    }

    private void pedirDatos2(SpotifyAppImpl app) {
        System.out.println();
        System.out.println("Top 5 canciones que aparecen en más top 50 en un día dado.");
        String fecha = promptForDate("Ingrese la fecha del ranking (YYYY-MM-DD): ");
        if (fecha != null) {
            app.consulta2(fecha);
        }
    }

    private void pedirDatos3(SpotifyAppImpl app) {
        System.out.println();
        System.out.println("Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
        String fechaInicial = promptForDate("Ingrese la fecha inicial (YYYY-MM-DD): ");
        if (fechaInicial == null) {
            return;
        }
        String fechaFinal = promptForDate("Ingrese la fecha final (YYYY-MM-DD): ");
        if (fechaInicial != null && fechaFinal != null && isDate1AfterDate2(fechaInicial, fechaFinal)) {
            app.consulta3(fechaInicial, fechaFinal);
        }
    }

    private void pedirDatos4(SpotifyAppImpl app) {
        System.out.println();
        System.out.println("Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.");
        String fecha = promptForDate("Ingrese la fecha del ranking (YYYY-MM-DD): ");
        if (fecha != null) {
            String pais = promptForInput("Ingrese el pais: ");
            String artista = promptForInput("Ingrese el artista: ");
            app.consulta4(fecha, artista, pais);
        }
    }

    private void pedirDatos5(SpotifyAppImpl app) {
        System.out.println();
        System.out.println("Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
        String fechaInicial = promptForDate("Ingrese la fecha inicial (YYYY-MM-DD): ");
        if (fechaInicial == null) {
            return;
        }
        String fechaFinal = promptForDate("Ingrese la fecha final (YYYY-MM-DD): ");
        if (fechaInicial != null && fechaFinal != null && isDate1AfterDate2(fechaInicial, fechaFinal)) {
            String tempInicialStr = promptForInput("Ingrese el tempo inicial: ");
            String tempFinalStr = promptForInput("Ingrese el tempo final: ");
            float tempInicial = temposValidos(tempInicialStr);
            float tempFinal = temposValidos(tempFinalStr);
            if (tempInicial != -1 && tempFinal != -1) {
                app.consulta5(fechaInicial, fechaFinal, tempInicial, tempFinal);
            } else {
                System.out.println("Tempos invalidos.");
            }
        }
    }

    public void showMenu(SpotifyAppImpl app) {
        while (prendido) {
            System.out.println();
            System.out.print("                                                  " + b);
            System.out.print(fVerde + negro + " Spotify App " + b);
            System.out.println("                                                         " + b);
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
                    System.out.print("Ingrese el numero de la consulta que desea realizar: ");
                    opcion = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline
                    System.out.println();

                    if (opcion < 1 || 6 < opcion) {
                        System.out.println("Opcion no valida, ingrese un numero del 1 al 6. ");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Opcion no valida, ingrese un numero del 1 al 6. ");
                    scanner.nextLine();  // Consume the invalid input
                }
            }

            switch (opcion) {
                case 1:
                    pedirDatos1(app);
                    break;
                case 2:
                    pedirDatos2(app);
                    break;
                case 3:
                    pedirDatos3(app);
                    break;
                case 4:
                    pedirDatos4(app);
                    break;
                case 5:
                    pedirDatos5(app);
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Cerrando programa...");
                    prendido = false;
                    break;
                default:
                    System.out.println("Opcion no valida, ingrese un numero del 1 al 6: ");
            }
        }
        scanner.close();
    }
}