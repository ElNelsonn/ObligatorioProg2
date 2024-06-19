import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int opcion;
    private boolean prendido = true;
    private String fGris = "\u001B[47m";
    private String fVerde = "\033[42m";
    private String negro = "\033[30m";
    private String b = "\u001B[0m";

    private boolean fechaValida(String fecha){
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!fecha.matches(regex)) {
            return false;
        } else {
            return true;
        }
    }

    private float temposValidos(String temp1){
        try {
            return Float.parseFloat(temp1);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isDate1AfterDate2(String date1, String date2) {
        try {
            LocalDate d1 = LocalDate.parse(date1);
            LocalDate d2 = LocalDate.parse(date2);
            return d2.isAfter(d1);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return false;
        }
    }

    public void showMenu(SpotifyAppImpl app) {
        Scanner scanner1 = new Scanner(System.in);
        int opcion;


        while (this.prendido) {

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
                            app.consulta1(fecha, pais);
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
                                        app.consulta1(fecha1, pais);
                                        inicioFuncion1 = false;
                                    }
                                }
                            }
                        }
                    }
                    break;

                //Top 5 canciones que aparecen en más top 50 en un día dado.
                case 2:
                    boolean inicioFuncion2 = true;
                    while (inicioFuncion2) {
                        Scanner scanner3 = new Scanner(System.in);
                        System.out.println("Top 5 canciones que aparecen en más top 50 en un día dado.");
                        System.out.print("    Ingrese la fecha del ranking (YYYY-MM-DD): ");
                        String fecha = scanner3.nextLine();

                        if (fechaValida(fecha)) {

                            app.consulta2(fecha);


                            inicioFuncion2 = false;

                        } else {
                            boolean validez = fechaValida(fecha);
                            while (!validez) {
                                System.out.println();
                                System.out.println("Fecha incorrecta, esta debe de tener el formato (YYYY-MM-DD)");
                                System.out.println("En caso de querer volver al menu oprima 0. ");
                                System.out.print("  Ingrese un valor: ");
                                String fecha1 = scanner3.nextLine();

                                if (fecha1.equals("0")) {
                                    inicioFuncion2 = false;
                                    validez = true;

                                } else {
                                    validez = fechaValida(fecha1);
                                    if (validez) {
                                        app.consulta2(fecha1);
                                        inicioFuncion2 = false;
                                    }
                                }
                            }
                        }
                    }
                    break;

                //Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.
                case 3:
                    boolean inicioFuncion3 = true;
                    while (inicioFuncion3) {
                        Scanner scanner4 = new Scanner(System.in);
                        System.out.println("Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
                        System.out.print("    Ingrese la fecha inicial (YYYY-MM-DD): ");
                        String fecha1 = scanner4.nextLine();
                        System.out.print("    Ingrese la fecha final (YYYY-MM-DD): ");
                        Scanner scanner5 = new Scanner(System.in);
                        String fecha2 = scanner5.nextLine();

                        if (fechaValida(fecha1) && fechaValida(fecha2) && fecha1.equals(fecha2)) {
                            app.consulta3(fecha1, fecha2);
                            inicioFuncion3 = false;

                        } else if (fechaValida(fecha1) && fechaValida(fecha2) && isDate1AfterDate2(fecha1, fecha2)) {
                            app.consulta3(fecha1, fecha2);
                            inicioFuncion3 = false;
                        } else {
                            boolean validez = false;
                            while (!validez) {
                                System.out.println();
                                System.out.println("Rango de fechas incorrecto, estas deben tener el formato (YYYY-MM-DD)");
                                System.out.println("En caso de querer volver al menu oprima 0. ");
                                System.out.print("    Ingrese un valor: ");
                                fecha1 = scanner4.nextLine();

                                if (fecha1.equals("0")) {
                                    inicioFuncion3 = false;
                                    validez = true;

                                } else {
                                    System.out.print("    Ingrese la fecha final (YYYY-MM-DD): ");
                                    fecha2 = scanner4.nextLine();
                                    validez = (fechaValida(fecha1) && fechaValida(fecha2) && isDate1AfterDate2(fecha1, fecha2));
                                    if (validez) {
                                        app.consulta3(fecha1, fecha2);
                                        inicioFuncion3 = false;
                                        validez = true;
                                    } else if (fechaValida(fecha1) && fechaValida(fecha2) && fecha1.equals(fecha2)) {
                                        app.consulta3(fecha1, fecha2);
                                        inicioFuncion3 = false;
                                        validez = true;
                                    }
                                }
                            }
                        }
                    }
                    break;


                case 4:
                    boolean inicioFuncion4 = true;
                    while (inicioFuncion4) {
                        Scanner scanner8 = new Scanner(System.in);
                        System.out.println("Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada: ");
                        System.out.print("    Ingrese la fecha del ranking (YYYY-MM-DD): ");
                        String fecha = scanner8.nextLine();

                        if (fechaValida(fecha)) {
                            System.out.print("    Ingrese el pais: ");
                            String pais = scanner8.nextLine();
                            System.out.print("    Ingrese el artista: ");
                            String artista = scanner8.nextLine();
                            app.consulta4(fecha, artista, pais);
                            inicioFuncion4 = false;
                        } else {
                            boolean validez = fechaValida(fecha);
                            while (!validez) {
                                System.out.println();
                                System.out.println("Fecha incorrecta, esta debe de tener el formato (YYYY-MM-DD)");
                                System.out.print("En caso de querer volver al menu oprima 0. ");
                                System.out.print("Ingrese un valor: ");
                                String fecha1 = scanner8.nextLine();

                                if (fecha1.equals("0")) {
                                    inicioFuncion4 = false;
                                    validez = true;

                                } else {
                                    validez = fechaValida(fecha1);
                                    if (validez) {
                                        System.out.print("  Ingrese el pais: ");
                                        String pais1 = scanner8.nextLine();
                                        System.out.println("    Ingrese el artista: ");
                                        String artista1 = scanner8.nextLine();
                                        app.consulta4(fecha1, artista1, pais1);
                                        inicioFuncion4 = false;
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 5:
                    boolean inicioFuncion5 = true;
                    while (inicioFuncion5) {
                        Scanner scanner9 = new Scanner(System.in);
                        System.out.println("Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
                        System.out.print("    Ingrese la fecha inicial (YYYY-MM-DD): ");
                        String fecha1 = scanner9.nextLine();
                        System.out.print("    Ingrese la fecha final (YYYY-MM-DD): ");
                        Scanner scanner10 = new Scanner(System.in);
                        String fecha2 = scanner9.nextLine();

                        if (fechaValida(fecha1) && fechaValida(fecha2) && fecha1.equals(fecha2)) {
                            System.out.print("    Ingrese el tempo inicial: ");
                            String temp1 = scanner10.nextLine();
                            if (temposValidos(temp1) != -1){
                                System.out.print("    Ingrese el tempo final: ");
                                String temp2 = scanner10.nextLine();
                                if(temposValidos(temp2) != -1){
                                    app.consulta5(fecha1, fecha2, temposValidos(temp1), temposValidos(temp2));
                                    inicioFuncion5 = false;
                                } else {
                                    System.out.println("    Tempos invalidos ");
                                    inicioFuncion5 = false;
                                }
                            }
                        } else if (fechaValida(fecha1) && fechaValida(fecha2) && isDate1AfterDate2(fecha1, fecha2)) {
                            System.out.print("    Ingrese el tempo inicial: ");
                            String temp1 = scanner9.nextLine();
                            if (temposValidos(temp1) != -1){

                                System.out.print("    Ingrese el tempo final: ");
                                String temp2 = scanner9.nextLine();
                                if(temposValidos(temp2) != -1){
                                    app.consulta5(fecha1, fecha2, temposValidos(temp1), temposValidos(temp2));
                                    inicioFuncion5 = false;
                                } else {
                                    System.out.println("    Tempos invalidos ");
                                    inicioFuncion5 = false;
                                }
                            }
                        } else {
                            boolean validez = false;
                            while (!validez) {
                                System.out.println();
                                System.out.println("Rango de fechas incorrecto, estas deben tener el formato (YYYY-MM-DD)");
                                System.out.println("En caso de querer volver al menu oprima 0. ");
                                System.out.print("    Ingrese fecha inicial nuevamente (YYYY-MM-DD): ");
                                fecha1 = scanner9.nextLine();

                                if (fecha1.equals("0")) {
                                    inicioFuncion5 = false;
                                    validez = true;

                                } else {
                                    System.out.print("    Ingrese la fecha final (YYYY-MM-DD): ");
                                    fecha2 = scanner9.nextLine();
                                    validez = (fechaValida(fecha1) && fechaValida(fecha2) && isDate1AfterDate2(fecha1, fecha2));
                                    if (validez) {
                                        System.out.print("    Ingrese el tempo inicial: ");
                                        String temp1 = scanner9.nextLine();
                                        if (temposValidos(temp1) != -1){
                                            System.out.print("    Ingrese el tempo final: ");
                                            String temp2 = scanner9.nextLine();
                                            if(temposValidos(temp2) != -1){
                                                app.consulta5(fecha1, fecha2, temposValidos(temp1), temposValidos(temp2));
                                                validez = true;
                                                inicioFuncion5 = false;
                                            } else {
                                                System.out.println("    Tempos invalidos ");
                                                inicioFuncion5 = false;
                                            }
                                        }

                                    } else if (fechaValida(fecha1) && fechaValida(fecha2) && fecha1.equals(fecha2)) {
                                        System.out.print("    Ingrese el tempo inicial: ");
                                        String temp1 = scanner9.nextLine();
                                        if (temposValidos(temp1) != -1){
                                            System.out.print("    Ingrese el tempo final: ");
                                            String temp2 = scanner9.nextLine();
                                            if(temposValidos(temp2) != -1){
                                                app.consulta5(fecha1, fecha2, temposValidos(temp1), temposValidos(temp2));
                                                inicioFuncion5 = false;
                                                validez = true;
                                            } else {
                                                System.out.println("    Tempos invalidos ");
                                                inicioFuncion5 = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;

                case 6:
                    this.prendido = false;

                    break;

                default:
                    System.out.println("Opcion no valida, ingrese un numero del 1 al 6: ");
            }
        }
        scanner1.close();
    }












}
