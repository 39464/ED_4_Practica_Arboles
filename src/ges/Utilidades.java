/**
 * Clase de utilidad para la carga inicial de datos.
 * Se encarga de leer el fichero CSV y construir el Arbol General en memoria RAM.
 * Esta clase se entrega completamente desarrollada y no requiere modificaciones.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Utilidades {

    public static Arbol cargarEstructura(String rutaFichero) {
        System.out.println("Cargando estructura en memoria desde " + rutaFichero + "...");

        NodoArbol raiz = new NodoArbol("MANDO", "Mando Central");
        Arbol arbol = new Arbol(raiz);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] campos = linea.split(";");
                if (campos.length < 7) continue;

                String nombreZona = campos[0];
                String nombreHospital = campos[1];
                String nombreUnidad = campos[2];
                int capacidad = Integer.parseInt(campos[3]);
                int idPaciente = Integer.parseInt(campos[4]);
                String nombrePaciente = campos[5];
                double gravedad = Double.parseDouble(campos[6]);

                // =========================================================================
                // FASE 1: NAVEGACION Y CONSTRUCCION DE LA RAMA (Top-Down)
                // El metodo buscarOCrearHijo navega por el arbol. Si el nodo ya existe,
                // lo devuelve para seguir bajando. Si no existe, lo crea y lo enlaza.
                // =========================================================================
                // 1. Buscamos la Zona (ej. "Norte") colgando del nodo raíz. Si no esta, la crea.
                NodoArbol nodoZona = buscarOCrearHijo(raiz, "ZONA", nombreZona, 0);
                // 2. Buscamos el Hospital (ej. "Zendal") dentro de esa Zona concreta. Si no esta, lo crea.
                NodoArbol nodoHospital = buscarOCrearHijo(nodoZona, "HOSPITAL", nombreHospital, 0);
                // 3. Buscamos la Unidad (ej. "UCI") dentro de ese Hospital y le pasamos su capacidad. Si no esta
                // (no encuentra el nombreUnidad), la crea.
                NodoArbol nodoUnidad = buscarOCrearHijo(nodoHospital, "UNIDAD", nombreUnidad, capacidad);

                // =========================================================================
                // FASE 2: INSERCIÓN DEL DATO FINAL (Nodo Hoja)
                // =========================================================================
                // 4. Instanciamos el objeto Paciente con los datos leídos de la fila del CSV.
                Paciente p = new Paciente(idPaciente, nombrePaciente, gravedad);
                // 5. Envolvemos al paciente en un nuevo NodoArbol y lo añadimos a la lista
                // de hijos de la Unidad que hemos recuperado o creado en el paso 3.
                nodoUnidad.agregarHijo(new NodoArbol(p));

            }
            System.out.println("Carga finalizada correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
        return arbol;
    }


    private static NodoArbol buscarOCrearHijo(NodoArbol padre, String tipo, String nombre, int capacidad) {
        Iterator<NodoArbol> it = padre.getHijos().iterator();
        while (it.hasNext()) {
            NodoArbol hijo = it.next();
            // Si ya esta insertado, no lo volvemos a insertar, devolvemos el existente
            if (hijo.getNombre().equalsIgnoreCase(nombre) && hijo.getTipo().equals(tipo)) {
                return hijo;
            }
        }

        // Si no esta insertado, lo agregamos al arbol como hijo del nodo padre
        NodoArbol nuevoNodo;
        if (tipo.equals("UNIDAD")) {
            // Las unidades sanitarias requieren capacidad maxima
            nuevoNodo = new NodoArbol(nombre, capacidad);
        } else {
            // "ZONA" u "HOSPITAL" solo requieren tipo y nombre
            nuevoNodo = new NodoArbol(tipo, nombre);
        }

        padre.agregarHijo(nuevoNodo);
        return nuevoNodo;

    }

}