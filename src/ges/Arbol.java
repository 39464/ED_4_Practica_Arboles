/**
 * Clase que implementa la estructura de un Arbol General (N-ario) para modelar
 * la jerarquia de la red sanitaria (Mando, Zona, Hospital, Unidad, Paciente).
 * Esta clase se entrega completamente desarrollada y no requiere modificaciones.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;

import java.util.Iterator;

public class Arbol {

    // Objeto auxiliar para mantener el conteo persistente de nodos
    // visitados durante el recorrido recursivo del árbol.
    private static class ContadorNodos {
        int valor = 0;
    }

    private NodoArbol raiz;

    public Arbol(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public NodoArbol getRaiz() { return raiz; }

    // Metodo buscar por nombre
    public NodoArbol buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private NodoArbol buscarRecursivo(NodoArbol nodo, String nombre) {
        // Caso base: si el nodo es nulo, no hemos encontrado nada
        if (nodo == null) return null;

        // Si el nombre es igual, devolvemos el nodo. Si hay nodos con el mismo nombre, devolvemos el que encontremos primero
        if (nodo.getNombre().equalsIgnoreCase(nombre)) {
            return nodo;
        }

        // Si no, recorremos todos sus hijos usando Iterator y les pasamos la funcion
        // buscarRecursivo() de nuevo
        Iterator<NodoArbol> it = nodo.getHijos().iterator();
        while (it.hasNext()) {
            // Llamada recursiva para cada hijo
            NodoArbol res = buscarRecursivo(it.next(), nombre);

            // Si la búsqueda en una rama devuelve algo distinto de null,
            // es decir, res es un nodo, devolvemos este como salida.
            if (res != null) return res;
        }

        return null;
    }



    // Metodo buscar por id
    public NodoArbol buscarPorId(int id) {
        return buscarPorIdRecursivo(raiz, id);
    }


    private NodoArbol buscarPorIdRecursivo(NodoArbol nodo, int id) {
        if (nodo == null) return null;
        // Si el nodo es tipo "PACIENTE" y su id coincide, devolvemos el nodo
        if (nodo.getTipo().equals("PACIENTE") && nodo.getPaciente().getId() == id) return nodo;

        // Si no, recorremos todos sus hijos y les pasamos la funcion buscarPorIdRecursivo() de nuevo
        Iterator<NodoArbol> it = nodo.getHijos().iterator();
        while (it.hasNext()) {
            NodoArbol res = buscarPorIdRecursivo(it.next(), id);
            // Si res es un nodo, devolvemos este como salida
            if (res != null) return res;
        }
        return null;
    }



    // =========================================================
    // METODO DE DEPURACION: Imprimir Arbol General por consola
    // =========================================================
    public void imprimirArbol() {
        System.out.println("\n=== ESTRUCTURA DE LA RED SANITARIA ===");
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
        } else {

            ContadorNodos contador = new ContadorNodos();

            int limiteMaximo = 50; // Nodos maximos a imprimir por seguridad
            int limiteNivel = 4;   // Profundidad maxima (Raiz=0 -> Paciente=4)

            imprimirArbolRec(raiz, "", true, contador, limiteMaximo, 0, limiteNivel);

            System.out.println("\n[!] Mostrando red hasta profundidad " + limiteNivel + ".");
        }
        System.out.println("======================================\n");
    }

    private void imprimirArbolRec(NodoArbol nodo, String prefijo, boolean esUltimo, ContadorNodos contador, int limite, int nivel, int limiteNivel) {
        // CORTAMOS si superamos el nivel permitido o el limite de nodos
        if (nodo == null || contador.valor >= limite || nivel > limiteNivel) return;

        contador.valor++;

        // Preparamos el texto a mostrar
        String info = "[" + nodo.getTipo() + "] " + nodo.getNombre();
        if (nodo.getTipo().equals("UNIDAD")) {
            info += " (Capacidad: " + nodo.getCapacidadMaxima() + ")";
        } else if (nodo.getTipo().equals("PACIENTE")) {
            info += " -> ID: " + nodo.getPaciente().getId() + " | Grav: " + nodo.getPaciente().getGravedad();
        }

        // Imprimimos el nodo
        System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + info);

        if (contador.valor >= limite) return;

        // Llamada recursiva a los hijos (sumando 1 al nivel)
        Iterator<NodoArbol> it = nodo.getHijos().iterator();
        while (it.hasNext() && contador.valor < limite) {
            NodoArbol hijo = it.next();

            // Comprobamos si es el ultimo hijo para el formato visual (la ramita └── )
            boolean ultimoHijo = !it.hasNext();
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

            imprimirArbolRec(hijo, nuevoPrefijo, ultimoHijo, contador, limite, nivel + 1, limiteNivel);
        }
    }


}