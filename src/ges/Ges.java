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
        /// === INICIO CODIGO ALUMNO === ///
        return 0.0; // TODO: Borrar este valor por defecto. Es solo para que el proyecto compile.
        /// === FIN CODIGO ALUMNO === ///
    }

    /// === INICIO CODIGO ALUMNO (Metodo Auxiliar E1) === ///

    /// === FIN CODIGO ALUMNO === ///


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
        /// === INICIO CODIGO ALUMNO === ///

        /// === FIN CODIGO ALUMNO === ///
    }

    /// === INICIO CODIGO ALUMNO (Metodo Auxiliar E5) === ///

    /// === FIN CODIGO ALUMNO === ///

    // =====================================================================
    // EJERCICIO 6: Localizacion rapida
    // =====================================================================
    public Paciente buscarFamiliar(int idPaciente) {
        /// === INICIO CODIGO ALUMNO === ///
        return null; // TODO: Borrar este valor por defecto. Es solo para que el proyecto compile.
        /// === FIN CODIGO ALUMNO === ///
    }
}