/**
 * Clase que representa un nodo del Arbol General N-ario.
 * Contiene la informacion jerarquica y la lista de hijos correspondientes.
 * Esta clase se entrega completamente desarrollada y no requiere modificaciones.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;

import java.util.ArrayList;
import java.util.List;

public class NodoArbol {
    private String tipo; // "MANDO", "ZONA", "HOSPITAL", "UNIDAD", "PACIENTE"
    private String nombre;
    private int capacidadMaxima;
    private Paciente paciente;
    private NodoArbol padre;
    private List<NodoArbol> hijos;

    // Constructor generico para Mando, Zona u Hospital
    public NodoArbol(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    // Constructor especifico para UNIDAD (con capacidad)
    public NodoArbol(String nombre, int capacidadMaxima) {
        this.tipo = "UNIDAD";
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.hijos = new ArrayList<>();
    }

    // Constructor especifico para PACIENTE
    public NodoArbol(Paciente paciente) {
        this.tipo = "PACIENTE";
        this.nombre = paciente.getNombre();
        this.paciente = paciente;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(NodoArbol hijo) {
        hijo.setPadre(this);
        this.hijos.add(hijo);
    }

    // Getters y Setters
    public String getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public Paciente getPaciente() { return paciente; }
    public NodoArbol getPadre() { return padre; }
    public void setPadre(NodoArbol padre) { this.padre = padre; }
    public List<NodoArbol> getHijos() { return hijos; }
}