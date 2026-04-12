/**
 * Clase que implementa un Arbol Binario de Busqueda (ABB) para indexar pacientes.
 * Esta clase se entrega parcialmente desarrollada. El alumno debe implementar
 * los metodos recursivos de insercion y busqueda por ID.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;


public class ArbolBinarioBusqueda {

    // Objeto auxiliar para mantener el conteo persistente de nodos
    // visitados durante el recorrido recursivo del árbol.
    private static class ContadorNodos {
        int valor = 0;
    }

    // Clase interna privada para los nodos del ABB
    private class NodoABB {
        Paciente paciente;
        NodoABB izq, der;

        NodoABB(Paciente p) {
            this.paciente = p;
        }
    }

    private NodoABB raiz;

    // =========================================================
    // METODO PUBLICO DE INSERCION
    // =========================================================
    public void insertar(Paciente p) {
        if (p != null) raiz = insertarRec(raiz, p);
    }

    // =========================================================
    // METODO RECURSIVO DE INSERCI0N (A implementar por el alumno)
    // =========================================================
    private NodoABB insertarRec(NodoABB nodo, Paciente p) {
        if(nodo == null){
            nodo = new NodoABB(p);
        }else if(p.getId() < nodo.paciente.getId()){
            nodo.izq = this.insertarRec(nodo.izq, p);
        }else if(p.getId() > nodo.paciente.getId()){
            nodo.der = this.insertarRec(nodo.der, p);
        }else{
            System.out.println("Error. El paciente "+ p.getId()+" ya existe.");
        }
        return nodo;
    }

    // =========================================================
    // METODO PUBLICO DE BUSQUEDA
    // =========================================================
    public Paciente buscar(int id) {
        Paciente resultado = null;
        NodoABB res = buscarRec(raiz, id);
        if (res != null){
            resultado = res.paciente;
        }
        return resultado;
    }

    // =========================================================
    // METODO RECURSIVO DE BUSQUEDA (A implementar por el alumno)
    // =========================================================
    private NodoABB buscarRec(NodoABB nodo, int id) {
        NodoABB res = null;
        if(nodo != null){
            if(nodo.paciente.getId() == id){ res = nodo; }
            else if(id < nodo.paciente.getId()) res = buscarRec(nodo.izq, id);
            else if(id > nodo.paciente.getId()) res = buscarRec(nodo.der, id);
        }
        return res;
    }



    // =========================================================
    // METODO DE DEPURACION: Imprimir ABB por consola (Rotado 90º)
    // =========================================================
    public void imprimirArbol() {
        System.out.println("\n=== INDICE DE PACIENTES (ABB) ===");
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
        } else {

            ContadorNodos contador = new ContadorNodos();

            int limiteMaximo = 50; // Limite de nodos para no saturar la consola
            int limiteNivel = 3;   // Profundidad maxima visual (copa del arbol)

            // El ultimo parametro indica el tipo de nodo: 0=Raiz, 1=Derecho, -1=Izquierdo
            imprimirABBRec(raiz, 0, contador, limiteMaximo, limiteNivel, 0);

            System.out.println("\n[!] Mostrando la copa del arbol (hasta nivel " + limiteNivel + ").");
        }
        System.out.println("=================================\n");
    }

    private void imprimirABBRec(NodoABB nodo, int nivel, ContadorNodos contador, int limite, int limiteNivel, int tipoNodo) {
        // Caso base: si el nodo es nulo, hemos llegado al limite de nodos o de profundidad
        if (nodo == null || contador.valor >= limite || nivel > limiteNivel) return;

        // Procesar subarbol derecho primero (aparecera arriba en la consola)
        imprimirABBRec(nodo.der, nivel + 1, contador, limite, limiteNivel, 1);

        if (contador.valor >= limite) return;

        // Procesar el nodo actual
        contador.valor++;

        // Generamos la sangria segun el nivel
        StringBuilder sangria = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sangria.append("        "); // 8 espacios por nivel
        }

        // Determinamos el conector visual segun la posicion del hijo
        String conector;
        if (nivel == 0) {
            conector = "── ";   // Nodo Raiz
        } else if (tipoNodo == 1) {
            conector = "┌── ";  // Hijo derecho (arriba)
        } else {
            conector = "└── ";  // Hijo izquierdo (abajo)
        }

        // Imprimimos la linea con el ID y el nombre del paciente
        System.out.println(sangria.toString() + conector + "[" + nodo.paciente.getId() + "] " + nodo.paciente.getNombre());

        if (contador.valor >= limite) return;

        // Pocesar subarbol izquierdo (aparecera abajo en la consola)
        imprimirABBRec(nodo.izq, nivel + 1, contador, limite, limiteNivel, -1);
    }

}