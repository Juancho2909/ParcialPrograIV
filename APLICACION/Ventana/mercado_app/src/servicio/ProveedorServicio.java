package servicio;

import modelo.Proveedor;
import repositorio.ProveedorRepositorio;
import repositorio.BDProveedores;

import java.util.List;

public class ProveedorServicio {
    private ProveedorRepositorio repositorio;

    // Constructor
    public ProveedorServicio() {
        this.repositorio = new ProveedorRepositorio();
    }

    // Obtener todos los proveedores
    public List<String> obtenerTodosLosProveedores() {
        return repositorio.obtenerTodosLosProveedores();
    }

    // Obtener un proveedor por ID
    public Proveedor obtenerProveedorPorId(int id) {
        return repositorio.obtenerProveedorPorId(id);
    }

    // Actualizar un proveedor
    public boolean actualizarProveedor(String id, String columna, String nuevoValor) {
        return repositorio.actualizarProveedor(id, columna, nuevoValor);
    }

    // Eliminar un proveedor por ID
    public boolean eliminarPorId(String id) {
        return repositorio.eliminarPorId(id);
    }

    // Guardar un proveedor en el archivo
    public void guardarEnArchivo(String nombre, String direccion, String telefono) {
        Proveedor proveedor = repositorio.agregarProveedor(nombre, direccion, telefono);
        BDProveedores bd = new BDProveedores();
        bd.registrarProveedor(proveedor);
    }
}
