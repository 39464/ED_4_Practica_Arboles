/**
 * Clase de datos que representa la informacion de un paciente (ID, nombre y gravedad).
 * Esta clase se entrega completamente desarrollada y no requiere modificaciones.
 *
 * @author profesores ED
 * @version 1.0
 */
package ges;

public class Paciente {
    private int id;
    private String nombre;
    private double gravedad;

    public Paciente(int id, String nombre, double gravedad) {
        this.id = id;
        this.nombre = nombre;
        this.gravedad = gravedad;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getGravedad() { return gravedad; }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " (Grav: " + gravedad + ")";
    }
}