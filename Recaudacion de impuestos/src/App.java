import java.io.File;
import java.util.Scanner;

public class App {

    // =========================================
    // VARIABLES Y CONSTANTES GLOBALES
    // =========================================

    // Constantes para acceder al índice de los elementos de la tabla
    static final int ANIO = 0;
    static final int MES = 1;
    static final int ZONA = 2;
    static final int DEPARTAMENTO = 3;
    static final int ID_MUNICIPIO = 4;
    static final int NOMBRE_MUNICIPIO = 5;
    static final int MONTO = 6;


    static Scanner sc = new Scanner(System.in);

    // Matriz principal
    static String[][] datos = new String[500][7];

    // Control de registros
    static int totalRegistros = 0;

    /*
        COLUMNAS MATRIZ
        0 = Año
        1 = Mes
        2 = Zona
        3 = Departamento
        4 = ID Municipio
        5 = Nombre de municipio
        6 = Monto
    */


    // =========================================
    // MAIN
    // =========================================

    public static void main(String[] args) {
        
        inicializarDatos();
        int opcion;

        do {

        System.out.println("-------------------------------------------");
        System.out.println("|   SISTEMA DE RECAUDACIÓN DE IMPUESTOS   |");
        System.out.println("-------------------------------------------");

        System.out.println("| 1. Registrar nuevos municipios          |");
        System.out.println("| 2. Guardar nuevos datos en CSV          |");
        System.out.println("| 3. Reporte de recaudación por zona      |");
        System.out.println("| 4. Reporte de recaudación por municipio |");
        System.out.println("| 5. Reporte por zona en año específico   |");
        System.out.println("| 6. Total ingresos desde 2020            |");
        System.out.println("| 7. Municipio con mayor ingreso          |");
        System.out.println("| 8. Municipio con menor ingreso          |");
        System.out.println("| 9. Reporte mensual por año              |");
        System.out.println("| 10. Total ingresos por año              |");
        System.out.println("| 11. Ordenar datos (Bubble Sort)         |");
        System.out.println("| 12. Salir                               |");

        System.out.println("-------------------------------------------");

            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

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

            System.out.printf("Presione cualquier tecla para continuar...");
            sc.nextLine();

        } while(opcion != 12);

    }

    // =========================================
    // MÓDULO 1
    // Responsable: Ángel
    // =========================================

    public static void inicializarDatos() {

        // Se inicializa el archivo csv a leer
        File archivo = new File("Impuestos.csv");

        // Se valida su existencia
        if(!archivo.exists()){
            System.out.println("El archivo no se pudo abrir");
            return;
        }

        try (Scanner lector = new Scanner(archivo)){

            // Saltarse el encabezado del archivo
            if (lector.hasNextLine()){
                lector.nextLine();
            }

            // Variable para guardar los registros recorridos
            int i = 0;

            while (lector.hasNextLine() && i < datos.length){
                // Variable donde se guarda la línea actual recorrida
            String lineaElementos = lector.nextLine();
            
            String[] columnas = lineaElementos.split(",");

            // Se guardarán datos sí y sólo sí:
            // Hay 7 columnas en la línea (Para evitar datos sucios o incompletos)
            if (columnas.length == 7){

                for (int j = 0; j < 7; j++){
                    datos[i][j]  = columnas[j]; // Se guarda cada elemento en la fila y columna correspondiente
                }

                i++;
                totalRegistros++;
            }
            }

        }catch (Exception e){

            System.out.println("Error al leer el archivo.");
            e.printStackTrace();

        }

    }


    // =========================================
    // MÓDULO 2
    // Responsable: Fátima
    
    public static void registrarDatos() {


        System.out.println("Módulo 2 - Registrar datossdfsdsdfadfdafdsfsd");
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

        // Matriz divida en tres secciones
        // 0 año
        // 1 total occidental
        // 2 total oriental
        // 3 total paracentral/central
        String[][] reporte = {

        {"2020", "0", "0", "0"},
        {"2021", "0", "0", "0"},
        {"2022", "0", "0", "0"},
        {"2023", "0", "0", "0"},
        {"2024", "0", "0", "0"},
        {"2025", "0", "0", "0"},
        {"2026", "0", "0", "0"}
    };

        
        // Recorro cada fila de la matriz reporte
        for (int i = 0; i < reporte.length; i++){
            // Obtenemos el año y el valor de cada recaudación por zona
            int anio = Integer.parseInt(reporte[i][0]);
            double recaudacionOccidental = Double.parseDouble(reporte[i][1]);
            double recaudacionOriental = Double.parseDouble(reporte[i][2]); 
            double recaudacionCentral = Double.parseDouble(reporte[i][3]);

            // Ahora recorremos la matriz principal para comparar datos
            for (int j = 0; j < totalRegistros; j++){

                if ( datos[j][ANIO] == null){
                    continue; // Por si acaso hay algún dato vacío colado, nos saltamos esa línea
                }
                // Obtenemos el año de la fila de la matriz principal
                int aniodatos = Integer.parseInt(datos[j][ANIO]);

                // Validamos que el año filtrado sea el deseado
                if (aniodatos == anio){
                    
                    // Obtenemos la zona a filtrar y el monto
                    String zona_filtrar = datos[j][ZONA];
                    double monto_filtrar = Double.parseDouble(datos[j][MONTO]);

                    // Validamos de qué zona es la fila y la guardamos en el monto
                    // correspondiente
                    if (zona_filtrar.equals("Occidente")){
                        recaudacionOccidental += monto_filtrar;
                    }

                    else if (zona_filtrar.equals("Oriente")){
                        recaudacionOriental += monto_filtrar;
                    }

                    else {
                        recaudacionCentral += monto_filtrar;
                    }

                }
            }

            // Guardamos los valores que obtuvimos
            reporte[i][1] = String.format("%.2f", recaudacionOccidental);
            reporte[i][2] = String.format("%.2f",recaudacionOriental);
            reporte[i][3] = String.format("%.2f",recaudacionCentral);

        }

        // Mostramos los datos ordenados al final
        System.out.println("AÑO\tOCCIDENTAL\tORIENTAL\tCENTRAL");
        for(int i = 0; i < reporte.length; i++) {

            System.out.print(reporte[i][0] + "\t");
            System.out.print("$" + reporte[i][1] + "\t");
            System.out.print("$" + reporte[i][2] + "\t");
            System.out.println("$" + reporte[i][3]);
        }
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