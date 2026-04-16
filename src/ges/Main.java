/**
 * Clase principal de ejecucion del programa.
 * Contiene el entorno de pruebas para los metodos del alumno y el Stress Test
 * comparativo de rendimiento (O(N) vs O(log N)).
 * Esta clase se entrega completamente desarrollada y no requiere modificaciones
 * salvo que el alumno quiera probar de otras formas las funciones.
 *
 * @author profesores ED
 * @version 1.0
 */

package ges;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String archivoDatosArbol;
        int totalPacientes;

        // fUsoFicheroMasivo =
        // 0 --> datos_prueba.csv
        // 1 --> pacientes_ges_nacional_20000000.csv --> Usarlo solo cuando esté terminada la practica
        int fUsoFicheroMasivo = 0;
        if (fUsoFicheroMasivo == 1) {
            // Datos masivos: 14M (COVID-19) + 6M (otras patologias e ingresos). Solo usar al final.
            totalPacientes = 20_000_000;
            archivoDatosArbol = "pacientes_ges_nacional_" + totalPacientes + ".csv";
            System.out.println("=== INICIANDO SISTEMA GES (NIVEL NACIONAL) ===");
            System.out.println("Carga estimada: " + totalPacientes + " de pacientes activos.");
        }else{
            // Datos de prueba: Solo 8 pacientes. Usar este para las pruebas.
            totalPacientes = 8;
            archivoDatosArbol = "datos_prueba.csv";
            System.out.println("=== INICIANDO SISTEMA GES (MODO DEPURACION) ===");
        }

        // Comprobamos si el fichero existe para no pisarlo.
        File ficheroCsv = new File(archivoDatosArbol);
        if (!ficheroCsv.exists()) {
            System.out.println("\nGenerando CSV masivo (puede tardar unos segundos)...");
            GeneradorMasivo.generarFichero(archivoDatosArbol, totalPacientes);
        } else {
            System.out.println("\nCSV detectado. Omitiendo generación para ahorrar tiempo.");
        }

        // Cargar el .csv en RAM.
        System.out.println("Volcando datos del fichero a memoria RAM...");
        long tCargaIni = System.currentTimeMillis();
        Arbol redSanitaria = Utilidades.cargarEstructura(archivoDatosArbol);
        long tiempoCargaMs = System.currentTimeMillis() - tCargaIni;
        System.out.printf("Carga finalizada en: %d ms (%.3f segundos).\n", tiempoCargaMs, tiempoCargaMs / 1000.0);

        // --- IMPRIMIR ARBOL GENERAL  ---
        redSanitaria.imprimirArbol();

        // Limpiamos la memoria residual y damos un respiro antes del test critico.
        System.gc();
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        Ges sistemaGes = new Ges(redSanitaria);


        // =========================================================
        // VERIFICACION DE EJERCICIOS
        // =========================================================
        if (fUsoFicheroMasivo == 0) {
            System.out.println("\n=== ZONA DE PRUEBAS: VERIFICANDO METODOS DEL ALUMNO ===");

            // EJERCICIO 1: Analizar gravedad de un nodo
            System.out.println("\nProbando Ejercicio 1 (analizarGravedad en 'Zendal'):");
            double gravedadMedia = sistemaGes.analizarGravedad("Zendal");
            System.out.printf("   Gravedad media: %.2f\n", gravedadMedia);
            // Resultado esperado:
            // Gravedad media: 7,23

            System.out.println("\nProbando Ejercicio 1 (analizarGravedad en 'REANIMACION'):");
            gravedadMedia = sistemaGes.analizarGravedad("REANIMACION");
            System.out.printf("   Gravedad media: %.2f\n", gravedadMedia);
            // Resultado esperado:
            // Gravedad media: 7,15

            System.out.println("\nProbando Ejercicio 1 (analizarGravedad en 'PABLO'):");
            gravedadMedia = sistemaGes.analizarGravedad("PABLO");
            System.out.printf("   Gravedad media: %.2f\n", gravedadMedia);
            // Resultado esperado:
            // Gravedad media: 5,50

            // EJERCICIO 2: Buscar cama (triaje) en una unidad con capacidad
            System.out.println("\nProbando Ejercicio 2 (buscarCama para 'URGENCIAS'):");
            String rutaCama = sistemaGes.buscarCama("URGENCIAS");
            System.out.println("   Ruta encontrada: " + (rutaCama != null ? rutaCama : "No hay camas disponibles"));
            // Resultado esperado:
            // Ruta encontrada: Mando Central -> Sur -> La Paz -> Urgencias

            // System.out.println("\nProbando Ejercicio 2 (buscarCama para 'UCI'):");
            // rutaCama = sistemaGes.buscarCama("UCI");
            // System.out.println("   Ruta encontrada: " + (rutaCama != null ? rutaCama : "No hay camas disponibles"));
            // Resultado esperado:
            // Ruta encontrada: No hay camas disponibles

            // EJERCICIO 3: Dar de alta a un paciente (borrar del arbol general)
            // Usamos un ID que sepamos que existe en los datos de prueba (ej: 350)
            // int idPrueba = 350;
            // System.out.println("\nProbando Ejercicio 3 (darDeAlta al paciente " + idPrueba + "):");
            // boolean exito = sistemaGes.darAltaPaciente(idPrueba);
            // System.out.println("   Alta completada: " + exito);
            // if (exito) {
            //     totalPacientes = totalPacientes - 1;
            //     System.out.println("   Arbol tras el alta (el paciente " + idPrueba + " ya no deberia estar):");
            //     redSanitaria.imprimirArbol();
            // }
            // Resultado esperado:
            // Alta completada: true
            // Arbol tras el alta (el paciente 350 ya no deberia estar):


            // Usamos un ID que sepamos que no existe en los datos de prueba (ej: 351)
            // idPrueba = 351;
            // System.out.println("\nProbando Ejercicio 3 (darDeAlta al paciente " + idPrueba + "):");
            // exito = sistemaGes.darAltaPaciente(idPrueba);
            // System.out.println("   Alta completada: " + exito);
            // if (exito) {
            //      totalPacientes = totalPacientes - 1;
            //      System.out.println("   Arbol tras el alta (el paciente " + idPrueba + " ya no deberia estar):");
            //      redSanitaria.imprimirArbol();
            //  }
            // Resultado esperado:
            // Probando Ejercicio 3 (darDeAlta al paciente 351):
            // Alta completada: false

            // System.out.println("=========================================================\n");
        } else {
            System.out.println("\n[!] Omitiendo verificacion de ejercicios (Modo Masivo activado).");
        }



        // Crear el Indice Binario

        // EJERCICIO 4: El motor del índice binario (ABB) --> insertarRec()
        // EJERCICIO 5: Generar el indice binario del ABB
        System.out.println("\nProbando Ejercicios 4 y 5 (insertarRec() e indexacion masiva): Generando Indice ABB de " + totalPacientes + " de registros... ");
        long tIndexIni = System.currentTimeMillis();
        sistemaGes.generarIndice();
        long tiempoIndexMs = System.currentTimeMillis() - tIndexIni;
        System.out.printf("Completado en: %d ms (%.3f segundos).\n", tiempoIndexMs, tiempoIndexMs / 1000.0);

        // --- IMPRIMIR INDICE BINARIO  ---
        if (sistemaGes.getIndicePacientes() != null) {
            sistemaGes.getIndicePacientes().imprimirArbol();
        }
        // Resultado esperado:
        //        === INDICE DE PACIENTES (ABB) ===
        //                ┌── [850] Lucia
        //        ┌── [750] Elena
        //                └── [600] Pablo
        //── [500] Maria
        //        └── [300] Juan
        //                └── [250] Luis
        //                        └── [125] Ana
        // [!] Mostrando la copa del arbol (hasta nivel 3).


        // ---------------- STRESS TEST ----------------
        // Extraemos dinamicamente el paciente al final del arbol para forzar el peor caso O(N).
        int idBuscado = obtenerIdPeorCaso(redSanitaria.getRaiz());


        // EJERCICIO 4: El motor del índice binario (ABB) --> buscarRec()
        // EJERCICIO 6: Localizacion rapida por id de paciente --> buscarFamiliar()
        System.out.print("\nProbando Ejercicios 4 y 6 (buscarRec() y localizacion rapida de paciente " + idBuscado + " con el ABB): ");

        System.out.println("\n--- INICIANDO BUSQUEDA CRITICA DEL PEOR CASO POSIBLE (ID: " + idBuscado + ") ---");
        // A) BUSQUEDA LENTA (Arbol General)
        long t1 = System.nanoTime();
        NodoArbol nodoP = redSanitaria.buscarPorId(idBuscado);
        long t2 = System.nanoTime();

        double seqMs = (t2 - t1) / 1_000_000.0;
        double seqSeg = seqMs / 1000.0;
        String enc1 = (nodoP != null) ? nodoP.getPaciente().getNombre() : "Null";

        System.out.printf(" -> Busqueda Secuencial O(N): %8.4f ms  ( %7.6f s ) [%s]\n", seqMs, seqSeg, enc1);
        // Resultado esperado:
        // -> Busqueda Secuencial O(N):   0,0237 ms  ( 0,000024 s ) [Lucia]


        // B) BUSQUEDA RAPIDA (Indice ABB)
        long t3 = System.nanoTime();
        Paciente p2 = sistemaGes.buscarFamiliar(idBuscado);
        long t4 = System.nanoTime();

        double abbMs = (t4 - t3) / 1_000_000.0;
        double abbSeg = abbMs / 1000.0;
        String enc2 = (p2 != null) ? p2.getNombre() : "Null";

        System.out.printf(" -> Busqueda ABB O(log N):    %8.4f ms  ( %7.6f s ) [%s]\n", abbMs, abbSeg, enc2);
        // Resultado esperado:
        // -> Busqueda ABB O(log N):      0,0160 ms  ( 0,000016 s ) [Lucia]

        // =========================================================
        // CALCULO DE LA GANANCIA DE VELOCIDAD
        // =========================================================
        if (abbMs > 0 && p2 != null) {
            double multiplicador = seqMs / abbMs;
            System.out.println("\n=======================================================================");
            System.out.printf("RESULTADO: El Indice ABB ha sido %.0f veces mas rapido!!\n", multiplicador);
            System.out.println("=======================================================================");
        } else {
            System.out.println("\n[!] Advertencia: El Indice ABB no devolvio resultados correctos.");
            System.out.println("Asegurate de haber implementado bien los ejercicios 4 y 5.");
        }
    }






    /**
     * Metodo auxiliar que navega hasta la ultima hoja (extremo derecho) del arbol.
     * Esto garantiza que el algoritmo de búsqueda secuencial tenga que visitar
     * el 100% de los nodos antes de encontrarlo (peor caso real).
     */
    private static int obtenerIdPeorCaso(NodoArbol nodo) {
        if (nodo == null) return -1;
        if (nodo.getTipo().equals("PACIENTE")) {
            return nodo.getPaciente().getId();
        }

        List<NodoArbol> hijos = nodo.getHijos();
        if (hijos != null && !hijos.isEmpty()) {
            // Recorremos los hijos de derecha a izquierda.
            for (int i = hijos.size() - 1; i >= 0; i--) {
                int id = obtenerIdPeorCaso(hijos.get(i));
                if (id != -1) return id;
            }
        }
        return -1;
    }
}