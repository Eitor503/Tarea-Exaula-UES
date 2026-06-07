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
    // Responsable: Fatima
    
    public static void registrarDatos() {
        System.out.println("Módulo 2 - Registrar datos de nuevos municipios");

//Validación de que no se exceda el límite de los 500 registros
        if (totalRegistros >= datos.length) {
            System.out.println("Error, no se pueden registrar más municipios. Límite alcanzado de 500 registros.");
            return;
        }

        // =========================================
    // 1. INGRESO Y VALIDACIÓN DEL ID AUTO-COMPLETADO
    // =========================================
    String idInput = "";
    int indiceEncontrado = -1;
    // El bucle se repetirá hasta que el usuario ingrese un ID que exista en los registros de referencia
    while (indiceEncontrado == -1) { // Mientras no se haya encontrado un índice válido para el ID ingresado, seguimos pidiendo al usuario que ingrese un ID
        System.out.print("Ingrese ID del municipio: ");
        idInput = sc.nextLine().trim();

        // Búsqueda secuencial manual en los registros ya existentes
        for (int i = 0; i < totalRegistros; i++) { // Recorremos sólo hasta el total de registros cargados, no toda la matriz
            if (datos[i][ID_MUNICIPIO] != null && datos[i][ID_MUNICIPIO].equals(idInput)) { // Validamos que el ID no sea nulo y que coincida con el ingresado
                indiceEncontrado = i; // Guardamos el índice donde hallamos este ID
                break;
            }
        }
// Si después de recorrer los registros no se encontró el ID, mostramos un mensaje de error y el bucle se repetirá para pedir un nuevo ID
        if (indiceEncontrado == -1) {
            System.out.println("Error, el ID ingresado no existe en los registros. Intente de nuevo por favor.");
        }
    }

    // Extraemos de forma automática los campos correspondientes gracias al índice hallado es decir como el autocompletado que se pidió en el punto anterior, 
    // para evitar errores de tipeo y agilizar el proceso de registro
    String zonaAuto = datos[indiceEncontrado][ZONA];
    String deptoAuto = datos[indiceEncontrado][DEPARTAMENTO];
    String municipioAuto = datos[indiceEncontrado][NOMBRE_MUNICIPIO];

    System.out.println("-> [Autocompletado] Zona: " + zonaAuto);
    System.out.println("-> [Autocompletado] Departamento: " + deptoAuto);
    System.out.println("-> [Autocompletado] Municipio: " + municipioAuto);

    //Para la validacion de los siguientes campos, se uso la misma estructura igual para año, mes y el monto, se han implementado validaciones generales 
    // para asegurar que los datos ingresados sean del tipo y formato correcto.
    // =========================================
    // 2. VALIDACIÓN DEL AÑO (Desde 2020)
    // =========================================
    int anio = 0;
    //ARREGLA ESO, NO SE PERMITE WHILE(TRUE) BAJO NINGUNA CIRCUNSTANCIA
    while (true) { // Bucle infinito hasta que se ingrese un año válido 
        System.out.print("Ingrese año (desde el año 2020 por favor): "); 
        try { // Intentamos convertir la entrada a un número entero, si el usuario ingresa algo que no es un número, 
        // se lanzará una excepción para mostrar un mensaje de error y pedir la entrada nuevamente
            anio = Integer.parseInt(sc.nextLine().trim());
            if (anio >= 2020) {
                break; // Entrada válida, rompemos el bucle
            } // Si el año es menor a 2020, mostramos un mensaje de error y el bucle se repetirá para pedir un nuevo año
            System.out.println("Error. El año debe ser igual o mayor a 2020.");
        } catch (NumberFormatException e) {
            System.out.println("Error, por favor Ingrese un número entero válido.");
        }
    }

    // =========================================
    // 3. VALIDACIÓN DEL MES (Entre 1 y 12)
    // =========================================
    int mes = 0;
    while (true) { //ARREGLA ESO, NO SE PERMITE WHILE(TRUE) BAJO NINGUNA CIRCUNSTANCIA
        System.out.print("Ingrese mes (1 a 12 por favor): ");
        try {
            mes = Integer.parseInt(sc.nextLine().trim());
            if (mes >= 1 && mes <= 12) {
                break; // Entrada válida
            }
            System.out.println("Error, el mes debe estar entre 1 y 12. Por favor intentar de nuevo.");
        } catch (NumberFormatException e) {
            System.out.println("Erro, Ingrese un número entero válido.");
        }
    }

    // =========================================
    // 4. VALIDACIÓN DEL MONTO (Positivo)
    // =========================================
    double monto = 0.0;
    while (true) { //ARREGLA ESO, NO SE PERMITE WHILE(TRUE) BAJO NINGUNA CIRCUNSTANCIA
        System.out.print("Ingrese monto (positivo por favor): ");
        try {
            monto = Double.parseDouble(sc.nextLine().trim());
            if (monto > 0) { //EL MONTO PUEDE SER 0, ARREGLA ESO
                break; // Entrada válida
            } // Si el monto no es positivo, mostramos un mensaje de error y el bucle se repetirá para pedir un nuevo monto
            System.out.println("Error. El monto debe ser estrictamente mayor a 0.");
        } catch (NumberFormatException e) {
            System.out.println("Error. Ingrese un valor numérico decimal válido.");
        }
    }

    // =========================================
    // 5. GUARDAR NUEVO REGISTRO EN LA MATRIZ PRINCIPAL
    // =========================================
    // Una vez validados todos los datos, los guardamos en la siguiente fila disponible de la matriz principal utilizando el control de registros 
    // totalRegistros para saber dónde está la siguiente posición/fila disponible
    datos[totalRegistros][ANIO] = String.valueOf(anio);
    datos[totalRegistros][MES] = String.valueOf(mes);
    datos[totalRegistros][ZONA] = zonaAuto;
    datos[totalRegistros][DEPARTAMENTO] = deptoAuto;
    datos[totalRegistros][ID_MUNICIPIO] = idInput;
    datos[totalRegistros][NOMBRE_MUNICIPIO] = municipioAuto;
    datos[totalRegistros][MONTO] = String.valueOf(monto);

    // Incrementamos el control de registros de la matriz para apuntar a la siguiente fila disponible para el próximo registro
    totalRegistros++; // Ahora totalRegistros apunta a la siguiente fila disponible, es decir, el número total de registros cargados en la matriz

    System.out.println("\n¡Registro exitoso! Datos validados correctamente y almacenados en la matriz.");

}


    // =========================================
    // MÓDULO 3
    // Responsable: Fátima
    // =========================================

    public static void guardarDatosCSV() {

        System.out.println("Módulo 3 Guardar datos de nuevos municipios en CSV");
// 1. Validar que la matriz no esté completamente vacía antes de intentar guardar, para evitar crear un archivo con líneas vacías o sin datos importantes. Si totalRegistros es 0, significa que no hay datos cargados en la matriz, por lo que no tiene sentido intentar guardar un archivo CSV.
    if (totalRegistros == 0) {
        System.out.println("No hay datos en la matriz para guardar.");
        return; // Salimos del método para evitar crear un archivo vacío
    }

    String nombreArchivo = "Impuestos.csv";

    // 2. Abrimos el archivo en modo APPEND (true) es decir en el  modo SOBREESCRITURA, para añadir al final sin borrar nada
    try (java.io.PrintWriter escritor = new java.io.PrintWriter(new java.io.FileWriter(nombreArchivo, true))) {
        
        // 3. Tomamos la posición del ÚLTIMO registro ingresado en la memoria
        // Como totalRegistros ya aumentó en registrarDatos(), el último está en (totalRegistros - 1)
        int ultimoIndice = totalRegistros - 1; // El índice del último registro ingresado en la matriz

        // Construimos la línea de texto con el formato CSV usando las constantes
        String lineaCsv = datos[ultimoIndice][ANIO] + "," +
                          datos[ultimoIndice][MES] + "," +
                          datos[ultimoIndice][ZONA] + "," +
                          datos[ultimoIndice][DEPARTAMENTO] + "," +
                          datos[ultimoIndice][ID_MUNICIPIO] + "," +
                          datos[ultimoIndice][NOMBRE_MUNICIPIO] + "," +
                          datos[ultimoIndice][MONTO];
        // 4. Escribimos esta línea al final del archivo físico, gracias a que abrimos el archivo en modo APPEND, 
        // no se borrará nada de lo que ya estaba y se añadirá esta nueva línea al final del archivo
        // Escribimos únicamente este registro nuevo al final del archivo físico
        escritor.println(lineaCsv);

        System.out.println("El nuevo registro se ha agregado de forma exitosa permanente al archivo CSV.");

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

        System.out.println("Módulo 5");
    }


    // =========================================
    // MÓDULO 6
    // Responsable: Fátima
    // =========================================

    public static void reporteZonaAnioElegible() {

    System.out.println("Módulo 6 Reporte por zona en año específico");

    // Validar si existen datos cargados en el sistema 
    if (totalRegistros == 0) { 
        // Si totalRegistros es 0, significa que no hay datos cargados en la matriz, por lo que no tiene sentido intentar generar un reporte 
        System.out.println("No hay datos en la matriz principal para generar reportes."); 
        return; 
    } 

    // Solicitar y validar el año elegido por el usuario (desde 2020) 
    int anioElegido = 0; // Variable para almacenar el año ingresado por el usuario 
    while (true) { // Bucle infinito hasta que se ingrese un año válido 
        System.out.print("Ingrese el año a consultar (desde 2020) por favor: "); 
        try { 
            // Intentamos convertir la entrada a un número entero, si el usuario ingresa algo que no es un número, se lanzará una excepción para mostrar un mensaje de error y pedir la entrada nuevamente 
            anioElegido = Integer.parseInt(sc.nextLine().trim()); 
            if (anioElegido >= 2020) { // Si el año es igual o mayor a 2020, es válido, por lo que rompemos el bucle para continuar con el reporte 
                break; // Año válido, salimos del bucle 
            } 
            System.out.println("Error. El año debe ser igual o mayor a 2020."); // Si el año es menor a 2020, mostramos un mensaje de error y el bucle se repetirá para pedir un nuevo año 
        } catch (NumberFormatException e) { 
            // Si el usuario ingresa algo que no es un número entero, se lanzará esta excepción, por lo que mostramos un mensaje de error y el bucle se repetirá para pedir un nuevo año 
            System.out.println("Error, por favor dígite un número entero válido."); 
        } 
    } 

    // 3. Variables acumuladoras tradicionales para las tres zonas geográficas 
    double totalOccidente = 0.0; 
    double totalOriente = 0.0; 
    double totalCentralParacentral = 0.0; 
    boolean encontroDatos = false; 

    // Entra el año elegido por el usuario, lo convertimos a String para comparar textualmente con los datos de la matriz principal, ya que en la matriz los años están almacenados como texto (String) y no como números enteros, por lo que para hacer una comparación correcta entre el año ingresado por el usuario y los años almacenados en la matriz, es necesario convertir el año ingresado a String. De esta forma, podemos comparar directamente el año ingresado con los valores de la matriz sin problemas de tipo de dato. 
    // Conviértelo a String para comparar textualmente con tu matriz global 
    String anioBuscadoStr = String.valueOf(anioElegido); 

    // 4. Recorrer la matriz principal una sola vez para filtrar y acumular los montos 
    for (int j = 0; j < totalRegistros; j++) { 
        // Validación preventiva contra celdas vacías o nulas 
        if (datos[j][ANIO] == null || datos[j][ZONA] == null || datos[j][MONTO] == null) { 
            continue; 
        } 

        // Si la fila de la matriz coincide con el año elegido por el usuario 
        if (datos[j][ANIO].equals(anioBuscadoStr)) { 
            encontroDatos = true; 
            String zona = datos[j][ZONA]; 
            double monto = Double.parseDouble(datos[j][MONTO]); 

            // Clasificación manual por zonas utilizando tus constantes globales 
            if (zona.equalsIgnoreCase("Occidente") || zona.equalsIgnoreCase("Occidental")) { 
                totalOccidente += monto; 
            } else if (zona.equalsIgnoreCase("Oriente") || zona.equalsIgnoreCase("Oriental")) { 
                totalOriente += monto; 
            } else { 
                // Central / Paracentral 
                totalCentralParacentral += monto; // <-- CORREGIDO: Tenía el nombre viejo
            } 
        } 
    } 

    // 5. Imprimir el reporte formateado en pantalla con formato de dos decimales 
    System.out.println("\n======================================================="); 
    System.out.println("REPORTE DE RECAUDACIÓN PARA EL AÑO: " + anioElegido); 
    System.out.println("======================================================="); 

    if (!encontroDatos) { 
        System.out.println("No se encontraron registros de impuestos para el año " + anioElegido); 
    } else { 
        System.out.printf("ZONA OCCIDENTAL: $%.2f\n", totalOccidente); 
        System.out.printf("ZONA ORIENTAL: $%.2f\n", totalOriente); 
        System.out.printf("ZONA CENTRAL/PARACENTRAL: $%.2f\n", totalCentralParacentral); // <-- CORREGIDO: Tenía el nombre viejo
        System.out.println("-------------------------------------------------------"); 
        System.out.printf("TOTAL RECAUDADO EN %d: $%.2f\n", anioElegido, (totalOccidente + totalOriente + totalCentralParacentral)); // <-- CORREGIDO: Tenía el nombre viejo
    } 
    System.out.println("======================================================="); 
    }


    // =========================================
    // MÓDULO 7
    // Responsable: Diego
    // =========================================

    public static void totalIngresosDesde2020() {

    double totalIngresos = 0;

    // Recorremos todos los registros cargados
    for (int i = 0; i < totalRegistros; i++) {

        // Validamos que exista información en la fila
        if (datos[i][ANIO] == null) {
            continue;
        }

        // Obtenemos año y monto
        int anio = Integer.parseInt(datos[i][ANIO]);
        double monto = Double.parseDouble(datos[i][MONTO]);

        // Se suman únicamente registros desde 2020
        if (anio >= 2020) {
            totalIngresos += monto;
        }
    }

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

    }


    // =========================================
    // MÓDULO 9
    // Responsable: Isamara
    // =========================================

    public static void municipioMenorIngreso() {
        System.out.println("Modulo 9");
        int anio;
        int mes;
        //=====================================
        // Valiación del año
        // ====================================
        do {
            System.out.println("Ingrese el año a consultar (2020-2026):");
            anio = sc.nextInt();
        } while (anio < 2020 || anio > 2026);

        //=====================================
        // Validación del mes
        //=====================================
        do{
            System.out.println("Ingrese el mes a consultar (1-12):");
            mes = sc.nextInt();
        } while (mes < 1 || mes > 12);
        double menorMonto = 0;
        String nombreMunicipio = "";
        boolean encontrado = false;

        //===================================
        // Busqueda del menor ingreso
        //===================================
        for (int i = 0; i < totalRegistros; i++) {
            if (Integer.parseInt(datos[i][ANIO]) == anio && Integer.parseInt(datos[i][MES]) == mes) {
                double monto = Double.parseDouble(datos[i][MONTO]);
                if (!encontrado || monto < menorMonto) {
                    menorMonto = monto;
                    nombreMunicipio = datos[i][NOMBRE_MUNICIPIO];
                    encontrado = true;
                }
            }
        }

        //===================================
        // Impresión de resultados
        //===================================
        if (encontrado) {
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

    int anioBuscar;

    // Validación del año
    while (true) {

        System.out.print("Ingrese el año a consultar: ");

        try {

            anioBuscar = Integer.parseInt(sc.nextLine());

            if (anioBuscar >= 2020) {
                break;
            }

            System.out.println("Error. El año debe ser igual o mayor a 2020.");

        } catch (Exception e) {

            System.out.println("Ingrese un número válido.");
        }
    }

    // Vector para guardar los montos de cada mes
    double[] totalMeses = new double[12];

    // Recorremos la matriz principal
    for (int i = 0; i < totalRegistros; i++) {

        if (datos[i][ANIO] == null) {
            continue;
        }

        int anio = Integer.parseInt(datos[i][ANIO]);

        // Sólo se trabaja con el año solicitado
        if (anio == anioBuscar) {

            int mes = Integer.parseInt(datos[i][MES]);
            double monto = Double.parseDouble(datos[i][MONTO]);

            totalMeses[mes - 1] += monto;
        }
    }

    System.out.println("----------------------------------");
    System.out.println("REPORTE MENSUAL DEL AÑO " + anioBuscar);
    System.out.println("----------------------------------");

    for (int i = 0; i < totalMeses.length; i++) {

        System.out.printf("Mes %d : $%.2f\n",
                (i + 1),
                totalMeses[i]);
    }
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