import java.util.Scanner;

public class App {

    // =========================================
    // VARIABLES GLOBALES
    // =========================================

    static Scanner sc = new Scanner(System.in);

    // Matriz principal
    static String[][] datos = new String[500][6];

    // Control de registros
    static int totalRegistros = 0;

    /*
        COLUMNAS MATRIZ
        0 = Año
        1 = Mes
        2 = Zona
        3 = Departamento
        4 = ID Municipio
        5 = Monto
    */


    // =========================================
    // MAIN
    // =========================================

    public static void main(String[] args) {

        int opcion;

        do {

        System.out.println("*****************************************");
        System.out.println(" SISTEMA DE RECAUDACIÓN DE IMPUESTOS");
        System.out.println("*****************************************");

        System.out.println("1. Registrar nuevos municipios");
        System.out.println("2. Guardar nuevos datos en CSV");
        System.out.println("3. Reporte de recaudación por zona");
        System.out.println("4. Reporte de recaudación por municipio");
        System.out.println("5. Reporte por zona en año específico");
        System.out.println("6. Total ingresos desde 2020");
        System.out.println("7. Municipio con mayor ingreso");
        System.out.println("8. Municipio con menor ingreso");
        System.out.println("9. Reporte mensual por año");
        System.out.println("10. Total ingresos por año");
        System.out.println("11. Ordenar datos (Bubble Sort)");
        System.out.println("12. Salir");

        System.out.println("*****************************************");

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch(opcion) {

                case 1:
                    registrarDatos();
                    break;

                case 2:
                    guardarDatosCSV();
                    break;

                case 3:
                    reporteZonaAnual();
                    break;

                case 4:
                    reporteMunicipioAnual();
                    break;

                case 5:
                    reporteZonaAnioElegible();
                    break;

                case 6:
                    totalIngresosDesde2020();
                    break;

                case 7:
                    municipioMayorIngreso();
                    break;

                case 8:
                    municipioMenorIngreso();
                    break;

                case 9:
                    reporteMensualPorAnio();
                    break;

                case 10:
                    totalIngresosPorAnio();
                    break;

                case 11:
                    ordenarDatosBubbleSort();
                    break;

                case 12:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

            System.out.println();

        } while(opcion != 12);

    }

    // =========================================
    // MÓDULO 1
    // Responsable: Ángel
    // =========================================

    public static void inicializarDatos() {

        System.out.println("Módulo 1");
    }


    // =========================================
    // MÓDULO 2
    // Responsable: Fátima
    
    public static void registrarDatos() {


        System.out.println("Módulo 2 - Registrar datos");
    }


    // =========================================
    // MÓDULO 3
    // Responsable: Fátima
    // =========================================

    public static void guardarDatosCSV() {

        System.out.println("Módulo 3");
    }


    // =========================================
    // MÓDULO 4
    // Responsable: Ángel
    // =========================================

    public static void reporteZonaAnual() {

        System.out.println("Módulo 4");
    }


    // =========================================
    // MÓDULO 5
    // Responsable: Ángel
    // =========================================

    public static void reporteMunicipioAnual() {

        System.out.println("Módulo 5");
    }


    // =========================================
    // MÓDULO 6
    // Responsable: Fátima
    // =========================================

    public static void reporteZonaAnioElegible() {

        System.out.println("Módulo 6");
    }


    // =========================================
    // MÓDULO 7
    // Responsable: Diego
    // =========================================

    public static void totalIngresosDesde2020() {

        System.out.println("Módulo 7");
    }


    // =========================================
    // MÓDULO 8
    // Responsable: Isamara
    // =========================================

    public static void municipioMayorIngreso() {

        System.out.println("Módulo 8");
    }


    // =========================================
    // MÓDULO 9
    // Responsable: Isamara
    // =========================================

    public static void municipioMenorIngreso() {

        System.out.println("Módulo 9");
    }


    // =========================================
    // MÓDULO 10
    // Responsable: Diego
    // =========================================

    public static void reporteMensualPorAnio() {

        System.out.println("Módulo 10");
    }


    // =========================================
    // MÓDULO 11
    // Responsable: Diego
    // =========================================

    public static void totalIngresosPorAnio() {

        System.out.println("Módulo 11");
    }


    // =========================================
    // MÓDULO 12
    // Responsable: Isamara
    // =========================================

    public static void ordenarDatosBubbleSort() {

        System.out.println("Módulo 12");
    }

}