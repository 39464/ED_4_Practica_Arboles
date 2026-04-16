/**
 * Clase principal del sistema de Gestion de Emergencias Sanitarias (GES).
 * Esta clase se entrega como esqueleto. El alumno debe implementar los metodos
 * recursivos de triaje, analisis, altas e indexacion solicitados en la practica.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;

import java.util.Iterator;

public class Ges {
    private Arbol redSanitaria;
    private ArbolBinarioBusqueda indicePacientes;

    public Ges(Arbol red) {
        this.redSanitaria = red;
        this.indicePacientes = null;
    }

    public ArbolBinarioBusqueda getIndicePacientes() { return indicePacientes; }

    // =====================================================================
    // EJERCICIO 1: Analisis de gravedad de un nodo
    // =====================================================================
    // Clase privada auxiliar para acumular los datos en la recursion
    private static class Estadisticas {
        int n_pacientes;
        double s_gravedad;

        public Estadisticas() {
            this.n_pacientes = 0;
            this.s_gravedad = 0.0;
        }
    }

    public double analizarGravedad(String nombreNodo) {
        double res = 0.0;
        NodoArbol inicio = redSanitaria.buscar(nombreNodo); //primero encontrar lo q buscas! no hsce falta recorrerlo desde el principio
        if (nombreNodo != null) {
            Estadisticas es = new Estadisticas();
            analizarGravedad(inicio, es);
            if(es.n_pacientes != 0) res = es.s_gravedad/es.n_pacientes;
        }
        return res;
    }

    private void analizarGravedad(NodoArbol nodo, Estadisticas stats) {
        double resultado = 0.0;
        if(nodo != null){
            if(nodo.getTipo().equals("PACIENTE")){
                stats.n_pacientes++;
                stats.s_gravedad += nodo.getPaciente().getGravedad();
            }else{ //ir haciendo lo mismo para todos los hijos
                Iterator<NodoArbol> it = nodo.getHijos().iterator();
                while(it.hasNext()){
                    analizarGravedad(it.next(), stats);
                }
            }
        }
    }


    // =====================================================================
    // EJERCICIO 2: Algoritmo de triaje (busqueda de cama en una unidad con capacidad)
    // =====================================================================
    public String buscarCama(String especialidad) {
        /// === INICIO CODIGO ALUMNO === ///
        return ""; // TODO: Borrar este valor por defecto. Es solo para que el proyecto compile.
        /// === FIN CODIGO ALUMNO === ///
    }

    /// === INICIO CODIGO ALUMNO (Metodo Auxiliar E2) === ///

    /// === FIN CODIGO ALUMNO === ///



    // =====================================================================
    // EJERCICIO 3: Gestion de altas
    // =====================================================================
    public boolean darAltaPaciente(int idPaciente) {
        /// === INICIO CODIGO ALUMNO === ///
        return false; // TODO: Borrar este valor por defecto. Es solo para que el proyecto compile.
        /// === FIN CODIGO ALUMNO === ///
    }

    /// === INICIO CODIGO ALUMNO (Metodo Auxiliar E3) === ///

    /// === FIN CODIGO ALUMNO === ///



    // =====================================================================
    // EJERCICIO 5: Indexacion masiva
    // =====================================================================
    public void generarIndice() {
        this.indicePacientes = new ArbolBinarioBusqueda();
        generarIndiceRec(this.redSanitaria.getRaiz());
    }

    private void generarIndiceRec(NodoArbol nodo) {
        if(nodo != null){
            if(nodo.getTipo().equals("PACIENTE")){
                this.indicePacientes.insertar(nodo.getPaciente());
            }else{
                Iterator<NodoArbol> it = nodo.getHijos().iterator();
                while(it.hasNext()){
                    generarIndiceRec(it.next());
                }
            }
        }
    }

    // =====================================================================
    // EJERCICIO 6: Localizacion rapida
    // =====================================================================
    public Paciente buscarFamiliar(int idPaciente) {
        /// === INICIO CODIGO ALUMNO === ///
        return null; // TODO: Borrar este valor por defecto. Es solo para que el proyecto compile.
        /// === FIN CODIGO ALUMNO === ///
    }
}