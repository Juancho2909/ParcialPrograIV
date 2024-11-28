package modelo;

public class Proveedor {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;

    // Constructor
    public Proveedor(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método toString
    @Override
    public String toString() {
        return "Proveedor ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion 
               + ", Teléfono: " + telefono;
    }
}
