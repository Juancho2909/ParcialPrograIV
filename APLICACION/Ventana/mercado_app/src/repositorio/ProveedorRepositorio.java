package repositorio;

import modelo.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorRepositorio {
    private static List<Proveedor> proveedores = new ArrayList<>();

    // Crear (Agregar Proveedor)
    public Proveedor agregarProveedor(String nombre, String direccion, String telefono) {
        Proveedor nuevoProveedor = new Proveedor(
            proveedores.size() + 1, // ID único basado en el tamaño de la lista
            nombre,
            direccion,
            telefono
        );
        proveedores.add(nuevoProveedor);
        return nuevoProveedor;
    }

    // Cargar proveedores desde la base de datos o archivo
    public void cargarProveedores() {
        BDProveedores bd = new BDProveedores();
        proveedores = bd.obtener();
    }

    // Obtener todos los proveedores
    public List<String> obtenerTodosLosProveedores() {
        List<String> proveedoresString = new ArrayList<>();
        BDProveedores bd = new BDProveedores();
        proveedores = bd.obtener(); 
        for (Proveedor proveedor : proveedores) {
            proveedoresString.add(proveedor.toString()); // Convierte cada Proveedor a String
        }
        return proveedoresString;
    }

    // Obtener un proveedor por ID
    public Proveedor obtenerProveedorPorId(int id) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                return proveedor;
            }
        }
        return null;
    }

    // Actualizar un proveedor
    public boolean actualizarProveedor(String id, String columna, String nuevoValor) {
        BDProveedores bd = new BDProveedores();
        return bd.actualizarProveedor(id, columna, nuevoValor);
    }

    // Eliminar un proveedor por ID
    public boolean eliminarPorId(String id) {
        BDProveedores bd = new BDProveedores();
        return bd.eliminarPorId(id);
    }

    // Eliminar un proveedor localmente
    public boolean eliminarProveedor(int id) {
        return proveedores.removeIf(p -> p.getId() == id);
    }
}
