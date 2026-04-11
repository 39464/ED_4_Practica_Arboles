/**
 * Clase de utilidad para generar ficheros CSV masivos con millones de pacientes.
 * Se utiliza para preparar el entorno del Stress Test de rendimiento.
 * Esta clase se entrega completamente desarrollada y no requiere modificaciones.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorMasivo {

    private static final String[] ZONAS = {"Norte", "Sur", "Este", "Oeste"};
    private static final String[] HOSPITALES = {"Zendal", "Militar", "La Paz", "Clinico", "Gomez Ulla"};
    private static final String[] UNIDADES = {"UCI", "Triaje", "Aislamiento", "Urgencias", "Reanimacion"};

    public static void generarFichero(String rutaFichero, int numPacientes) {
        System.out.println("Generando fichero CSV con " + numPacientes + " pacientes...");

        List<Integer> ids = new ArrayList<>(numPacientes);
        for (int i = 1; i <= numPacientes; i++) ids.add(i);
        Collections.shuffle(ids); // Mezclamos para no romper el balanceo del ABB

        Random rand = new Random();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
            bw.write("Zona;Hospital;Unidad;Capacidad;ID_Paciente;Nombre_Paciente;Gravedad\n");

            for (int i = 0; i < numPacientes; i++) {
                String zona = ZONAS[rand.nextInt(ZONAS.length)];
                String hospital = HOSPITALES[rand.nextInt(HOSPITALES.length)];
                String unidad = UNIDADES[rand.nextInt(UNIDADES.length)];
                int capacidad = 500000; // Sobredimensionado para que quepan todos en el archivo masivo
                int id = ids.get(i);
                String nombre = "Paciente_" + id;
                double gravedad = Math.round((rand.nextDouble() * 10.0) * 10.0) / 10.0;

                String linea = String.format("%s;%s;%s;%d;%d;%s;%.1f\n",
                        zona, hospital, unidad, capacidad, id, nombre, gravedad);
                bw.write(linea.replace(",", "."));
            }
            System.out.println("Fichero generado con exito: " + rutaFichero);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}