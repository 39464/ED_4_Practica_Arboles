package ges;

public class PruebasP {
    public static void main(String[] args) {
        ArbolBinarioBusqueda b = new ArbolBinarioBusqueda();
        Paciente p1 = new Paciente(1, "Jorge", 1.0);
        Paciente p2 = new Paciente(2, "Maria", 1.0);
        Paciente p3 = new Paciente(3, "Juan", 1.0);
        b.insertar(p1);
        b.insertar(p2);
        b.insertar(p3);
        b.imprimirArbol();
        System.out.println("Estabas buscando a "+ b.buscar(3).getNombre());
    }
}
