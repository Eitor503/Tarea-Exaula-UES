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
        System.out.println("| 5. Reporte por municipio en año y       |");
        System.out.println("|    mes elegible                         |");
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
            System.out.println();

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
                    reporteMunicipioMesAnual();
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

            System.out.printf("Presione ENTER para continuar...");
            sc.nextLine();
            System.out.println();

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
    // Responsable: Fatima
    //VARIABLES GLOBALES Y CONSTANTES PARA EL MÓDULO 2
 static String[][] nuevosRegistros = new String[500][7]; 
static int totalNuevos = 0;
// FIN DE VARIABLES GLOBALES Y CONSTANTES PARA EL MÓDULO 2

public static void registrarDatos() {
    System.out.println("Módulo 2 - Registrar nuevos municipios");

    //VARIABLES LOCALES  
    String idInput = ""; 
    int indiceEncontrado = -1; 
    int i = 0; 
    String zonaAuto = ""; 
    String deptoAuto = ""; 
    String municipioAuto = ""; 
    int anio = 0; 
    int controlAnio = 0; 
    int mes = 0; 
    int controlMes = 0;  
    double monto = 0; 
    int controlMonto = 0; 

    // CUADRO DE LOS 44 MUNICIPIOS DE EL SALVADOR POR ZONAS 
    System.out.println("+--------------------------------------+--------------------------------------+--------------------------------------+"); 
    System.out.println("| ID | ZONA OCCIDENTAL (11)            |ID  | ZONA CENTRAL Y PARACENTRAL (23) | ID | ZONA ORIENTAL (10)              |"); 
    System.out.println("+----+---------------------------------+----+---------------------------------+----+---------------------------------+"); 
    System.out.println("|  1 | Ahuachapán Norte                | 12 | Chalatenango Norte              | 35 | Usulután Norte                  |"); 
    System.out.println("|  2 | Ahuachapán Centro               | 13 | Chalatenango Centro             | 36 | Usulután Este                   |"); 
    System.out.println("|  3 | Ahuachapán Sur                  | 14 | Chalatenango Sur                | 37 | Usulután Oeste                  |"); 
    System.out.println("|  4 | Santa Ana Norte                 | 15 | La Libertad Norte               | 38 | San Miguel Norte                |"); 
    System.out.println("|  5 | Santa Ana Centro                | 16 | La Libertad Centro              | 39 | San Miguel Centro               |"); 
    System.out.println("|  6 | Santa Ana Este                  | 17 | La Libertad Oeste               | 40 | San Miguel Oeste                |"); 
    System.out.println("|  7 | Santa Ana Oeste                 | 18 | La Libertad Este                | 41 | Morazán Norte                   |"); 
    System.out.println("|  8 | Sonsonate Norte                 | 19 | La Libertad Sur                 | 42 | Morazán Sur                     |"); 
    System.out.println("|  9 | Sonsonate Centro                | 20 | La Libertad Costa               | 43 | La Unión Norte                  |"); 
    System.out.println("| 10 | Sonsonate Este                  | 21 | San Salvador Norte              | 44 | La Unión Sur                    |"); 
    System.out.println("| 11 | Sonsonate Oeste                 | 22 | San Salvador Oeste              |    |                                 |"); 
    System.out.println("|    |                                 | 23 | San Salvador Centro             |    |                                 |"); 
    System.out.println("|    |                                 | 24 | San Salvador Este               |    |                                 |"); 
    System.out.println("|    |                                 | 25 | San Salvador Sur                |    |                                 |"); 
    System.out.println("|    |                                 | 26 | Cuscatlán Norte                 |    |                                 |"); 
    System.out.println("|    |                                 | 27 | Cuscatlán Sur                   |    |                                 |"); 
    System.out.println("|    |                                 | 28 | La Paz Oeste                    |    |                                 |"); 
    System.out.println("|    |                                 | 29 | La Paz Centro                   |    |                                 |"); 
    System.out.println("|    |                                 | 30 | La Paz Este                     |    |                                 |"); 
    System.out.println("|    |                                 | 31 | Cabañas Este                    |    |                                 |"); 
    System.out.println("|    |                                 | 32 | Cabañas Oeste                   |    |                                 |"); 
    System.out.println("|    |                                 | 33 | San Vicente Norte               |    |                                 |"); 
    System.out.println("|    |                                 | 34 | San Vicente Sur                 |    |                                 |"); 
    System.out.println("+----+---------------------------------+----+---------------------------------+----+---------------------------------+"); 
    System.out.println(); 

    // Validaciones de límites de almacenamiento
    if (totalRegistros >= datos.length) { 
        System.out.println("Error, no se pueden registrar más municipios. Límite alcanzado de 500 registros."); 
        return; 
    } 

    if (nuevosRegistros == null) { 
        System.out.println("Error del sistema: la matriz 'nuevosRegistros' no está inicializada."); 
        return; 
    } 
    if (totalNuevos >= nuevosRegistros.length) { 
        System.out.println("Error: la matriz externa de nuevos registros está llena."); 
        return; 
    } 
    
    while (indiceEncontrado == -1) { 
        System.out.print("Ingrese ID del municipio: "); 
        idInput = sc.nextLine().trim(); 

        for (i = 0; i < totalRegistros && indiceEncontrado == -1; i++) { 
            if (datos[i][ID_MUNICIPIO] != null && datos[i][ID_MUNICIPIO].equals(idInput)) { 
                indiceEncontrado = i; 
            } 
        } 
        if (indiceEncontrado == -1) { 
            System.out.println("El ID ingresado no existe en los registros por favor intente de nuevo (1 al 44): "); 
        } 
    } 

    zonaAuto = datos[indiceEncontrado][ZONA]; 
    deptoAuto = datos[indiceEncontrado][DEPARTAMENTO]; 
    municipioAuto = datos[indiceEncontrado][NOMBRE_MUNICIPIO]; 

    System.out.println("-> [Autocompletado] Zona: " + zonaAuto); 
    System.out.println("-> [Autocompletado] Departamento: " + deptoAuto); 
    System.out.println("-> [Autocompletado] Municipio: " + municipioAuto); 

    while (controlAnio == 0) { 
        System.out.print("Ingrese año (desde 2020-fecha actual): "); 
        try { 
            anio = Integer.parseInt(sc.nextLine().trim()); 
            if (anio >= 2020 && anio <= 2026) { 
                controlAnio = 1; 
            } else { 
                System.out.println("Error. el año debe ser desde 2020 hasta el año actual."); 
            } 
        } catch (NumberFormatException e) { 
            System.out.println("Error. Ingrese un número entero válido."); 
        } 
    } 

    while (controlMes == 0) { 
        System.out.print("Ingrese mes (1 a 12): "); 
        try { 
            mes = Integer.parseInt(sc.nextLine().trim()); 
            if (mes >= 1 && mes <= 12) { 
                controlMes = 1; 
            } else { 
                System.out.println("Error. El mes debe estar entre 1 y 12."); 
            } 
        } catch (NumberFormatException e) { 
            System.out.println("Error. Ingrese un número entero válido."); 
        } 
    } 

    while (controlMonto == 0) { 
        System.out.print("Ingrese monto (0 o mayor): "); 
        try { 
            monto = Double.parseDouble(sc.nextLine().trim()); 
            if (monto >= 0) { 
                controlMonto = 1; 
            } else { 
                System.out.println("Error. El monto no puede ser negativo."); 
            } 
        } catch (NumberFormatException e) { 
            System.out.println("Error. Ingrese un número decimal válido."); 
        } 
    } 

    // 1. GUARDAR EN LA MATRIZ PRINCIPAL (datos)
    datos[totalRegistros][ANIO] = String.valueOf(anio); 
    datos[totalRegistros][MES] = String.valueOf(mes); 
    datos[totalRegistros][ZONA] = zonaAuto; 
    datos[totalRegistros][DEPARTAMENTO] = deptoAuto; 
    datos[totalRegistros][ID_MUNICIPIO] = idInput; 
    datos[totalRegistros][NOMBRE_MUNICIPIO] = municipioAuto; 
    datos[totalRegistros][MONTO] = String.valueOf(monto); 
    totalRegistros++; 

    // 2. GUARDAR EN LA MATRIZ EXTERNA (nuevosRegistros)
    nuevosRegistros[totalNuevos][ANIO] = String.valueOf(anio); 
    nuevosRegistros[totalNuevos][MES] = String.valueOf(mes); 
    nuevosRegistros[totalNuevos][ZONA] = zonaAuto; 
    nuevosRegistros[totalNuevos][DEPARTAMENTO] = deptoAuto; 
    nuevosRegistros[totalNuevos][ID_MUNICIPIO] = idInput; 
    nuevosRegistros[totalNuevos][NOMBRE_MUNICIPIO] = municipioAuto; 
    nuevosRegistros[totalNuevos][MONTO] = String.valueOf(monto); 
    totalNuevos++; 
    
    System.out.println("¡Registro exitoso! Datos almacenados correctamente. ");
}

    // =========================================
    // MÓDULO 3
    // Responsable: Fátima
    // =========================================
    public static void guardarDatosCSV() {
    // DECLARACIÓN DE VARIABLES LOCALES 
    String nombreArchivo = "Impuestos.csv";
    String lineaCsv = "";
    int i = 0; 
    //Fin DECLARACIÓN DE VARIABLES LOCALES
    
    if (totalNuevos == 0) {
        System.out.println("ERROR, aún no se han registrado datos en el sistema.");
        return;
    }

    try (java.io.PrintWriter escritor = new java.io.PrintWriter(new java.io.FileWriter(nombreArchivo, true))) {
        
        for ( i = 0; i < totalNuevos; i++) {
             lineaCsv = nuevosRegistros[i][ANIO] + "," +
                            nuevosRegistros[i][MES] + "," +
                            nuevosRegistros[i][ZONA] + "," +
                            nuevosRegistros[i][DEPARTAMENTO] + "," +
                            nuevosRegistros[i][ID_MUNICIPIO] + "," +
                            nuevosRegistros[i][NOMBRE_MUNICIPIO] + "," +
                            nuevosRegistros[i][MONTO];
            
            // Escribimos cada uno de los nuevos registros al final del archivo físico
            escritor.println(lineaCsv);
        }

        System.out.println("Se han agregado exitosamente " + totalNuevos + " registros nuevos de forma permanente al archivo CSV.");
        totalNuevos = 0;

    } catch (Exception e) {
        System.out.println("Error. No fue posible agregar la información al archivo CSV.");
        e.printStackTrace();
    }
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
        int anio;
        int aniodatos;
        double recaudacionOccidental;
        double recaudacionOriental;
        double recaudacionCentral;
        double montoFiltrar;
        String zonaFiltrar;


        
        // Recorro cada fila de la matriz reporte
        for (int i = 0; i < reporte.length; i++){
            // Obtenemos el año y el valor de cada recaudación por zona
            anio = Integer.parseInt(reporte[i][0]);
            recaudacionOccidental = Double.parseDouble(reporte[i][1]);
            recaudacionOriental = Double.parseDouble(reporte[i][2]); 
            recaudacionCentral = Double.parseDouble(reporte[i][3]);

            // Ahora recorremos la matriz principal para comparar datos
            for (int j = 0; j < totalRegistros; j++){

                // Obtenemos el año de la fila de la matriz principal
                aniodatos = Integer.parseInt(datos[j][ANIO]);

                // Validamos que el año filtrado sea el deseado
                if (aniodatos == anio){
                    
                    // Obtenemos la zona a filtrar y el monto
                    zonaFiltrar = datos[j][ZONA];
                    montoFiltrar = Double.parseDouble(datos[j][MONTO]);

                    // Validamos de qué zona es la fila y la guardamos en el monto
                    // correspondiente
                    if (zonaFiltrar.equals("Occidente")){
                        recaudacionOccidental += montoFiltrar;
                    }

                    else if (zonaFiltrar.equals("Oriente")){
                        recaudacionOriental += montoFiltrar;
                    }

                    else {
                        recaudacionCentral += montoFiltrar;
                    }

                }
            }

            // Guardamos los valores que obtuvimos
            reporte[i][1] = String.format("%.2f", recaudacionOccidental);
            reporte[i][2] = String.format("%.2f",recaudacionOriental);
            reporte[i][3] = String.format("%.2f",recaudacionCentral);

        }

        // Mostramos los datos ordenados al final
        System.out.println("AÑO\tOCCIDENTAL\tORIENTAL\tCENTRAL/PARACENTRAL");
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

        // Hacemos una matriz auxiliar que va a contener todos los valores
        // Guardados en la siguiente estructura
        // 0 = Año
        // 1 = Municipio
        // 2 = Monto total recaudado
    String[][] reporte = new String[500][3];

    int filasReporte = 0; // Variable que controla la cantidad de filas que tenga el reporte
    String anio;
    String municipio;
    double monto;
    double totalActual;
    int anioSiguiente;
    int anioActual;
    String temp;
    double total;
    int encontrado; // variable para determinar si la combinación está

    // Recorremos la matriz principal fila por fila
    for(int i = 0; i < totalRegistros; i++) {

        // Obtenemos los valores que queremos evaluar de cada fila
        anio = datos[i][ANIO];
        municipio = datos[i][NOMBRE_MUNICIPIO];
        monto = Double.parseDouble(datos[i][MONTO]);

        encontrado = 0; // variable para determinar si la combinación está

        // Buscar si ya existe la combinación en la lista auxiliar
        // del año y municipio de los datos, y si sí, sumarlos a los que ya están añadidos
        // si no, crear una nueva fila
        for(int j = 0; j < filasReporte; j++) {

            if(reporte[j][0].equals(anio) && reporte[j][1].equals(municipio)) {

                totalActual = Double.parseDouble(reporte[j][2]);

                totalActual += monto;

                reporte[j][2] = String.valueOf(totalActual);

                encontrado = 1;

            }
        }

        // Si no existe, crear nueva fila
        if(encontrado!=1) {

            reporte[filasReporte][0] = anio;
            reporte[filasReporte][1] = municipio;
            reporte[filasReporte][2] = String.valueOf(monto);

            filasReporte++;
        }
    }


    // Hacemos ordenamiento Bubble Sort en los datos
    // obtenidos para mejor presentación
    for(int i = 0; i < filasReporte - 1; i++) {

        for(int j = 0; j < filasReporte - 1 - i; j++) {

            anioActual = Integer.parseInt(reporte[j][0]);

            anioSiguiente = Integer.parseInt(reporte[j + 1][0]);

            if(anioActual > anioSiguiente) {

                for(int k = 0; k < 3; k++) {

                    temp = reporte[j][k];

                    reporte[j][k] =
                            reporte[j + 1][k];

                    reporte[j + 1][k] =
                            temp;
                }
            }
        }
    }

    // Mostramos los datos en una tabla final con la matriz auxiliar
    System.out.println("TABLA FINAL");
    System.out.println("AÑO\tMUNICIPIO\tTOTAL");

    // Bucle obtener cada valor de la matriz
    for(int i = 0; i < filasReporte; i++) {

        total = Double.parseDouble(reporte[i][2]);

        System.out.println(
                reporte[i][0] + "\t" +
                reporte[i][1] + "\t" +
                String.format("%.2f", total)
        );
    }
    }


    // =========================================
    // MÓDULO 6
    // Responsable: Fátima
    // =========================================
    public static void reporteMunicipioMesAnual() { 
    System.out.println("Módulo 6 - Reporte por municipio en mes y año elegible"); 

    // =====================================================================
    // --- DECLARACIÓN DE TODAS LAS VARIABLES LOCALES AL INICIO ---
    // =====================================================================
    int controlAnio = 0;
    int controlMes = 0;
    int controlID = 0;
    int datosEncontradosContador = 0; 
    
    int anioElegido = 0; 
    int mesElegido = 0; 
    int i = 0;
    int j = 0;
    int k = 0; 
    
    String idMunicipioBuscado = ""; 
    int indiceAuxiliar = -1; 
    
    String nombreMunicipio = "";
    String anioBuscadoStr = "";
    String mesBuscadoStr = "";
    double totalRecaudado = 0.0;
    double monto = 0.0;
    
    String entradaAnio = "";
    int esNumeroAnio = 1;
    
    String entradaMes = "";
    int esNumeroMes = 1;
    
    int controlAcceso = 1;

    if (totalRegistros == 0) { 
        System.out.println("No hay datos en la matriz principal para generar reportes."); 
        controlAcceso = 0;
    } 

    if (controlAcceso == 1) {

        while (controlAnio == 0) { 
            System.out.print("Ingrese el año a consultar (desde 2020): "); 
            entradaAnio = sc.nextLine().trim();
            esNumeroAnio = 1;
            
            if (entradaAnio.length() == 0) {
                esNumeroAnio = 0;
            }
            
            for (k = 0; k < entradaAnio.length() && esNumeroAnio == 1; k++) {
                if (entradaAnio.charAt(k) < '0' || entradaAnio.charAt(k) > '9') {
                    esNumeroAnio = 0;
                }
            }
            
            if (esNumeroAnio == 1) { 
                anioElegido = Integer.parseInt(entradaAnio); 
                if (anioElegido >= 2020) { 
                    controlAnio = 1; 
                } else {
                    System.out.println("Error. El año debe ser igual o mayor a 2020."); 
                }
            } else {
                System.out.println("Error, por favor digite un número entero válido.");
            }
        } 

        while (controlMes == 0) { 
            System.out.print("Ingrese el mes a consultar (1 al 12): "); 
            entradaMes = sc.nextLine().trim();
            esNumeroMes = 1;
            
            if (entradaMes.length() == 0) {
                esNumeroMes = 0;
            }
            
            for (k = 0; k < entradaMes.length() && esNumeroMes == 1; k++) {
                if (entradaMes.charAt(k) < '0' || entradaMes.charAt(k) > '9') {
                    esNumeroMes = 0;
                }
            }
            
            if (esNumeroMes == 1) { 
                mesElegido = Integer.parseInt(entradaMes); 
                if (mesElegido >= 1 && mesElegido <= 12) { 
                    controlMes = 1; 
                } else {
                    System.out.println("Error. El mes debe estar entre 1 y 12."); 
                }
            } else {
                System.out.println("Error, por favor digite un número entero válido.");
            }
        } 

        while (controlID == 0) { 
            System.out.print("Ingrese ID del municipio a consultar (1 al 44): "); 
            idMunicipioBuscado = sc.nextLine().trim(); 
            
            for (i = 0; i < totalRegistros && controlID == 0; i++) { 
                if (datos[i][4] != null && datos[i][4].equals(idMunicipioBuscado)) { 
                    indiceAuxiliar = i; 
                    controlID = 1; 
                } 
            } 
            
            if (controlID == 0) { 
                System.out.println("El ID ingresado no existe en los registros por favor intente de nuevo (1 al 44)."); 
            } 
        } 

        nombreMunicipio = datos[indiceAuxiliar][5]; 
        anioBuscadoStr = String.valueOf(anioElegido); 
        mesBuscadoStr = String.valueOf(mesElegido); 

        for (j = 0; j < totalRegistros; j++) { 
            if (datos[j][0] != null && datos[j][1] != null && datos[j][4] != null && datos[j][6] != null) { 
                if (datos[j][4].equals(idMunicipioBuscado) && datos[j][0].equals(anioBuscadoStr) && datos[j][1].equals(mesBuscadoStr)) { 
                    datosEncontradosContador = datosEncontradosContador + 1; 
                    monto = Double.parseDouble(datos[j][6]); 
                    totalRecaudado += monto; 
                } 
            } 
        } 

        System.out.println("\n-------------------------------------------------------------------------------------"); 
        System.out.println(" REPORTE DE RECAUDACIÓN DE IMPUESTOS "); 
        System.out.println("======================================================================================="); 
        System.out.println("ID MUNICIPIO: " + idMunicipioBuscado);
        System.out.println("NOMBRE MUNICIPIO: " + nombreMunicipio);
        System.out.println("PERIODO: Mes " + mesElegido + " / " + anioElegido);
        System.out.println("---------------------------------------------------------------------------------------"); 
        
        if (datosEncontradosContador == 0) { 
            System.out.println("TOTAL RECAUDADO: $0.00 (Sin datos)"); 
        } else { 
            System.out.println("TOTAL RECAUDADO: $" + totalRecaudado); 
        } 
        System.out.println("--------------------------------------------------------------------------------------"); 
    } 
}


    // =========================================
    // MÓDULO 7
    // Responsable: Diego
    // =========================================

    public static void totalIngresosDesde2020() {

    // Declaración de variables
    int i;
    int anio;
    double monto;
    double totalIngresos;

    // Inicialización de variables
    totalIngresos = 0;

    // Recorrer todos los registros
    for (i = 0; i < totalRegistros; i++) {

        anio = Integer.parseInt(datos[i][ANIO]);
        monto = Double.parseDouble(datos[i][MONTO]);

        // Acumular ingresos desde 2020
        if (anio >= 2020) {

            totalIngresos = totalIngresos + monto;

        }
    }

    // Mostrar resultado
    System.out.println("----------------------------------");
    System.out.println("TOTAL DE INGRESOS DESDE 2020");
    System.out.println("----------------------------------");
    System.out.printf("Total recaudado: $%.2f\n", totalIngresos);
}

    // =========================================
    // MÓDULO 8
    // Responsable: Isamara
    // =========================================

    public static void municipioMayorIngreso() {
        System.out.println("Módulo 8");
        
        //=======================
        // Declaración de vectores
        //========================
        double[] montoMunicipio = new double[44];
        String[] municipio = new String[44];

        //=========================
        // Declaración de variables
        //========================
        int idMunicipio;
        int mayor = 0;
        int i;

        //=====================================
        // Acumular ingresos por municipio
        //====================================
        for (i = 0; i < totalRegistros; i++) {
            idMunicipio = Integer.parseInt(datos[i][ID_MUNICIPIO]) - 1;
            montoMunicipio[idMunicipio] = montoMunicipio[idMunicipio] + Double.parseDouble(datos[i][MONTO]);
            municipio[idMunicipio] = datos[i][NOMBRE_MUNICIPIO];
        }
            //=================================================
            //Buscar el mayor ingreso
            //=================================================
            for (i = 0; i < 44; i++) {
                if (montoMunicipio[i] > montoMunicipio[mayor]) {
                    mayor = i;
                }
            }
           //Resultados 
    System.out.println("-------------------------------------");
    System.out.println("Municipio con mayor ingreso");
    System.out.println("-------------------------------------");
    System.out.println("Municipio: " + municipio[mayor]);
    System.out.println("Monto acumulado: $" + montoMunicipio[mayor]);
    }
    
    // =========================================
    // MÓDULO 9
    // Responsable: Isamara
    // =========================================

    public static void municipioMenorIngreso() {
        System.out.println("Modulo 9");

        //========================
        //Declaración de variables 
        //========================
        int anio;
        int mes;
        int i;
        double menorMonto = 0;
        double monto;
        String nombreMunicipio = "";
        int encontrado = 0;

        //=====================================
        // Valiación del año
        // ====================================
        do {
            System.out.println("Ingrese el año a consultar (2020-2026):");
            anio = Integer.parseInt(sc.nextLine());
        } while (anio < 2020 || anio > 2026);

        //=====================================
        // Validación del mes
        //=====================================
        do{
            System.out.println("Ingrese el mes a consultar (1-12):");
            mes = Integer.parseInt(sc.nextLine());
        } while (mes < 1 || mes > 12);
       
        //===================================
        // Busqueda del menor ingreso
        //===================================
        for (i = 0; i < totalRegistros; i++) {
            if (Integer.parseInt(datos[i][ANIO]) == anio && Integer.parseInt(datos[i][MES]) == mes) {
                monto = Double.parseDouble(datos[i][MONTO]);
                if (encontrado == 0 || monto < menorMonto) {
                    menorMonto = monto;
                    nombreMunicipio = datos[i][NOMBRE_MUNICIPIO];
                    encontrado = 1;
                }
            }
        }

        //===================================
        //Resultados
        //===================================
        if (encontrado == 1) {
            System.out.println("----------------------------------");
            System.out.println("Municipio con menor ingreso");
            System.out.println("----------------------------------");
            System.out.println("Municipio: " + nombreMunicipio);
            System.out.println("Monto: $" + menorMonto); 
        } else {
            System.out.println("No se encontraron registros para el periodo indicado");
        }
    }


    // =========================================
    // MÓDULO 10
    // Responsable: Diego
    // =========================================

    public static void reporteMensualPorAnio() {

    // Declaración de variables
    int anioBuscar;
    int i;
    int anio;
    int mes;
    double monto;

    // Vector para almacenar los totales mensuales
    double[] totalMeses = new double[12];

    // Validación del año
    do {

        System.out.print("Ingrese el año a consultar (2020-2026): ");
        anioBuscar = Integer.parseInt(sc.nextLine());

        if (anioBuscar < 2020 || anioBuscar > 2026) {

            System.out.println("Error. Año fuera de rango.");

        }

    } while (anioBuscar < 2020 || anioBuscar > 2026);

    // Inicializar vector
    for (i = 0; i < 12; i++) {

        totalMeses[i] = 0;

    }

    // Recorrer matriz principal
    for (i = 0; i < totalRegistros; i++) {

        anio = Integer.parseInt(datos[i][ANIO]);

        if (anio == anioBuscar) {

            mes = Integer.parseInt(datos[i][MES]);
            monto = Double.parseDouble(datos[i][MONTO]);

            totalMeses[mes - 1] =
                    totalMeses[mes - 1] + monto;

        }
    }

    // Mostrar reporte
    System.out.println("----------------------------------");
    System.out.println("REPORTE MENSUAL DEL AÑO " + anioBuscar);
    System.out.println("----------------------------------");

    for (i = 0; i < 12; i++) {

        System.out.printf(
                "Mes %d : $%.2f\n",
                (i + 1),
                totalMeses[i]
        );
    }
}

    // =========================================
    // MÓDULO 11
    // Responsable: Diego
    // =========================================

    public static void totalIngresosPorAnio() {

    // Declaración de variables
    int anio;
    int anioRegistro;
    int i;

    double monto;
    double totalAnual;

    // Encabezado
    System.out.println("----------------------------------");
    System.out.println("TOTAL DE INGRESOS POR AÑO");
    System.out.println("----------------------------------");

    // Recorrer años
    for (anio = 2020; anio <= 2026; anio++) {

        totalAnual = 0;

        // Recorrer matriz principal
        for (i = 0; i < totalRegistros; i++) {

            anioRegistro =
                    Integer.parseInt(datos[i][ANIO]);

            if (anioRegistro == anio) {

                monto =
                        Double.parseDouble(datos[i][MONTO]);

                totalAnual =
                        totalAnual + monto;

            }
        }

        System.out.printf(
                "%d -> $%.2f\n",
                anio,
                totalAnual
        );
    }
}


    // =========================================
    // MÓDULO 12
    // Responsable: Isamara
    // =========================================

    public static void ordenarDatosBubbleSort() {
        System.out.println("Módulo 12");

        //Declaración de variables
        int i;
        int j;
        int k;
        String auxiliar;
        String montoFormateado;

        //========================================
        //Datos desordenados
        //=======================================
        System.out.println("---------------------------------------");
        System.out.println("Datos desordenados:");
        System.out.println("-------------------------------------");
        System.out.println("AÑO\tMES\tZONA\tDEPARTAMENTO\tID MUNICIPIO\tMUNICIPIO\tMONTO");

        for (i = 0; i < totalRegistros; i++) {
            montoFormateado = String.format("%.2f", Double.parseDouble(datos[i][MONTO]));
            System.out.println(
                    datos[i][ANIO] + "\t" +
                    datos[i][MES] + "\t" +
                    datos[i][ZONA] + "\t" +
                    datos[i][DEPARTAMENTO] + "\t" +
                    datos[i][ID_MUNICIPIO] + "\t" +
                    datos[i][NOMBRE_MUNICIPIO] + "\t" +
                    "$" + montoFormateado
            );
        }
        System.out.printf("Presione ENTER para ver la tabla ordenada...");
        sc.nextLine();
              //====================================
              // Ordenamiento de Bubble Sort
              //====================================
            for (i = 0; i < totalRegistros -1; i++) {
                for (j = 0; j < totalRegistros -1 -i; j++) {
                    if (Double.parseDouble(datos[j][MONTO]) > Double.parseDouble(datos[j+1][MONTO])) {

                        // Intercambiar registro completo
                        for (k = 0; k < 7; k++) {
                            auxiliar = datos[j][k];
                            datos[j][k] = datos[j+1][k];
                            datos[j+1][k] = auxiliar;
                        }
                    }
                }
            }
            //=====================================
            //Datos ordenados
            //=====================================
    System.out.println("--------------------------------------");
    System.out.println("Datos ordenados");
    System.out.println("-----------------------------------");
    System.out.println("AÑO\tMES\tZONA\tDEPARTAMENTO\tID MUNICIPIO\tMUNICIPIO\tMONTO");
    for (i = 0; i < totalRegistros; i++) {
        montoFormateado = String.format("%.2f", Double.parseDouble(datos[i][MONTO]));
        System.out.println(
                datos[i][ANIO] + "\t" +
                datos[i][MES] + "\t" +
                datos[i][ZONA] + "\t" +
                datos[i][DEPARTAMENTO] + "\t" +
                datos[i][ID_MUNICIPIO] + "\t" +
                datos[i][NOMBRE_MUNICIPIO] + "\t" +
                "$" + montoFormateado
        );
    }
    }

}