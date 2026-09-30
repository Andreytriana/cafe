import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try {

            ServicioVentas servicio =
                    new ServicioVentas(
                            "data/Coffe_sales.csv"
                    );

            int opcion;

            do {

                System.out.println("\n====================================");
                System.out.println("       PROYECTO COFFEE - JAVA");
                System.out.println("====================================");
                System.out.println("1. Total de registros");
                System.out.println("2. Mostrar primeros 10 registros");
                System.out.println("3. Ordenar por jornada");
                System.out.println("4. Ejemplo Comparator");
                System.out.println("5. Ejemplo Consumer");
                System.out.println("6. Ejemplo Predicate");
                System.out.println("7. Ejemplo Function");
                System.out.println("8. Ejemplo BiFunction");
                System.out.println("9. Cafes distintos");
                System.out.println("10. Filtrar Latte");
                System.out.println("11. Map en mayusculas");
                System.out.println("12. Promedio de ventas");
                System.out.println("13. Estadisticas");
                System.out.println("14. Top 10 cafes vendidos");
                System.out.println("15. Generar reporte");
                System.out.println("16. Ventas del dia");
                System.out.println("17. Ventas por franja");
                System.out.println("18. Salir");
                System.out.println("====================================");
                System.out.print("Seleccione una opcion: ");

                opcion = teclado.nextInt();

                System.out.println();

                switch (opcion) {

                    case 1:
                        servicio.totalRegistros();
                        break;

                    case 2:
                        servicio.mostrarPrimeros10();
                        break;

                    case 3:
                        servicio.ordenarPorJornada();
                        break;

                    case 4:
                        servicio.ejemploComparator();
                        break;

                    case 5:
                        servicio.ejemploConsumer();
                        break;

                    case 6:
                        servicio.ejemploPredicate();
                        break;

                    case 7:
                        servicio.ejemploFunction();
                        break;

                    case 8:
                        servicio.ejemploBiFunction();
                        break;

                    case 9:
                        servicio.cafesDistintos();
                        break;

                    case 10:
                        servicio.filtroLatte();
                        break;

                    case 11:
                        servicio.mapMayusculas();
                        break;

                    case 12:
                        servicio.promedioVentas();
                        break;

                    case 13:
                        servicio.estadisticas();
                        break;

                    case 14:
                        servicio.topCafesVendidos();
                        break;

                    case 15:
                        servicio.generarReporte();
                        break;

                    case 16:
                        servicio.obtenerVentasdia();
                        break;

                    case 17:
                        servicio.obtenerVentasFranja();
                        break;

                    case 18:
                        System.out.println(
                                "Programa finalizado."
                        );
                        break;

                    default:
                        System.out.println(
                                "Opcion no valida."
                        );
                }

            } while (opcion != 18);

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        }

        teclado.close();
    }
}