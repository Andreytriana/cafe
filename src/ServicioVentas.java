import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServicioVentas {

    private List<VentaCafe> ventas;

    public ServicioVentas(String archivo) throws IOException {

        Path ruta = Paths.get(archivo);

        ventas = Files.lines(ruta)
                .skip(1)
                .map(linea -> linea.split(","))
                .filter(datos -> datos.length >= 7)
                .map(datos -> new VentaCafe(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        Double.parseDouble(datos[2]),
                        datos[3],
                        datos[4],
                        datos[5],
                        datos[6]
                ))
                .collect(Collectors.toList());
    }

    // ============================================================
    // 1. TOTAL DE REGISTROS
    // ============================================================

    public void totalRegistros() {

        System.out.println("\nTOTAL REGISTROS: " + ventas.size());
    }

    // ============================================================
    // 2. PRIMEROS 10 REGISTROS
    // ============================================================

    public void mostrarPrimeros10() {

        System.out.println("\nPRIMEROS 10 REGISTROS");

        ventas.stream()
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 3. ORDENAR POR JORNADA
    // ============================================================

    public void ordenarPorJornada() {

        System.out.println("\nVENTAS ORDENADAS POR JORNADA");

        ventas.stream()
                .sorted(
                        Comparator.comparing(
                                VentaCafe::getMomentoDia
                        )
                )
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 4. COMPARATOR
    // ============================================================

    public void ejemploComparator() {

        System.out.println("\nCOMPARATOR");

        ventas.stream()
                .sorted(
                        Comparator.comparing(
                                VentaCafe::getDinero
                        )
                )
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 5. CONSUMER
    // ============================================================

    public void ejemploConsumer() {

        System.out.println("\nCONSUMER");

        Consumer<VentaCafe> consumidor =
                venta -> System.out.println(
                        venta.getCafe()
                                + " -> $"
                                + venta.getDinero()
                );

        ventas.stream()
                .limit(10)
                .forEach(consumidor);
    }

    // ============================================================
    // 6. PREDICATE
    // ============================================================

    public void ejemploPredicate() {

        System.out.println("\nPREDICATE");

        Predicate<VentaCafe> ventaMayor35 =
                venta -> venta.getDinero() > 35;

        ventas.stream()
                .filter(ventaMayor35)
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 7. FUNCTION
    // ============================================================

    public void ejemploFunction() {

        System.out.println("\nFUNCTION");

        Function<VentaCafe, String> descripcion =
                venta ->
                        venta.getCafe()
                                + " - $"
                                + venta.getDinero();

        ventas.stream()
                .limit(10)
                .map(descripcion)
                .forEach(System.out::println);
    }

    // ============================================================
    // 8. BIFUNCTION
    // ============================================================

    public void ejemploBiFunction() {

        System.out.println("\nBIFUNCTION");

        BiFunction<String, Double, String> reporte =
                (cafe, dinero) ->
                        "Producto: " + cafe
                                + " | Venta: $" + dinero;

        ventas.stream()
                .limit(10)
                .forEach(v ->
                        System.out.println(
                                reporte.apply(
                                        v.getCafe(),
                                        v.getDinero()
                                )
                        )
                );
    }

    // ============================================================
    // 9. CAFES DISTINTOS
    // ============================================================

    public void cafesDistintos() {

        System.out.println("\nCAFES DISTINTOS");

        ventas.stream()
                .map(VentaCafe::getCafe)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    // ============================================================
    // 10. FILTRO LATTE
    // ============================================================

    public void filtroLatte() {

        System.out.println("\nSOLO LATTE");

        ventas.stream()
                .filter(
                        v -> v.getCafe()
                                .equalsIgnoreCase("Latte")
                )
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 11. MAP MAYUSCULAS
    // ============================================================

    public void mapMayusculas() {

        System.out.println("\nMAP");

        ventas.stream()
                .map(
                        v -> v.getCafe().toUpperCase()
                )
                .distinct()
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 12. PROMEDIO DE VENTAS
    // ============================================================

    public void promedioVentas() {

        double promedio =
                ventas.stream()
                        .mapToDouble(
                                VentaCafe::getDinero
                        )
                        .average()
                        .orElse(0);

        System.out.println(
                "\nPROMEDIO: " + promedio
        );
    }

    // ============================================================
    // 13. ESTADISTICAS
    // ============================================================

    public void estadisticas() {

        double total =
                ventas.stream()
                        .mapToDouble(
                                VentaCafe::getDinero
                        )
                        .sum();

        double maximo =
                ventas.stream()
                        .mapToDouble(
                                VentaCafe::getDinero
                        )
                        .max()
                        .orElse(0);

        double minimo =
                ventas.stream()
                        .mapToDouble(
                                VentaCafe::getDinero
                        )
                        .min()
                        .orElse(0);

        System.out.println(
                "\nTOTAL VENDIDO: " + total
        );

        System.out.println(
                "MAXIMO: " + maximo
        );

        System.out.println(
                "MINIMO: " + minimo
        );
    }

    // ============================================================
    // 14. TOP 10 CAFES VENDIDOS
    // ============================================================

    public void topCafesVendidos() {

        System.out.println("\nTOP 10 CAFES");

        Map<String, Long> resultado =
                ventas.stream()
                        .collect(
                                Collectors.groupingBy(
                                        VentaCafe::getCafe,
                                        Collectors.counting()
                                )
                        );

        resultado.entrySet()
                .stream()
                .sorted(
                        Map.Entry
                                .<String, Long>
                                        comparingByValue()
                                .reversed()
                )
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 15. GENERAR REPORTE
    // ============================================================

    public void generarReporte() throws IOException {

        long registros = ventas.size();

        double total =
                ventas.stream()
                        .mapToDouble(
                                VentaCafe::getDinero
                        )
                        .sum();

        double promedio =
                ventas.stream()
                        .mapToDouble(
                                VentaCafe::getDinero
                        )
                        .average()
                        .orElse(0);

        List<String> reporte = List.of(
                "REPORTE DE VENTAS",
                "------------------",
                "Total registros: " + registros,
                "Total vendido: " + total,
                "Promedio venta: " + promedio
        );

        Files.write(
                Paths.get("reporte_ventas.txt"),
                reporte
        );

        System.out.println(
                "\nReporte generado correctamente."
        );
    }

    // ============================================================
    // 16. VENTAS DEL DIA / MORNING
    // ============================================================

    public void obtenerVentasdia() {

        System.out.println("\nVENTAS DEL DIA");

        ventas.stream()
                .filter(
                        v -> v.getMomentoDia()
                                .equalsIgnoreCase("Morning")
                )
                .limit(10)
                .forEach(System.out::println);
    }

    // ============================================================
    // 17. VENTAS POR FRANJA
    // ============================================================

    public void obtenerVentasFranja() {

        System.out.println(
                "\nVENTAS POR FRANJA"
        );

        Map<String, Long> ventasPorMomento =
                ventas.stream()
                        .collect(
                                Collectors.groupingBy(
                                        VentaCafe::getMomentoDia,
                                        Collectors.counting()
                                )
                        );

        ventasPorMomento.forEach(
                (momento, cantidad) ->
                        System.out.println(
                                momento + ": " + cantidad
                        )
        );
    }
}