
package repositorio;

import java.util.LinkedList;
import modelo.ArchivoProveedores;
import modelo.Proveedor;

public class BDProveedores {

    public LinkedList<Proveedor> obtener() {
        LinkedList<Proveedor> proveedores = new LinkedList<>();
        ArchivoProveedores archivo = new ArchivoProveedores("proveedores.txt");
        LinkedList<String> lineas = archivo.obtenerTextoDelArchivo();

        if (lineas != null) {
            for (String linea : lineas) {
                // Verificar si la línea no está vacía y tiene al menos los campos esperados
                String[] tokens = linea.split(";");
                if (tokens.length == 4) { // Cambiar según el número de atributos del proveedor
                    try {
                        int id = Integer.parseInt(tokens[0].trim()); // ID del proveedor
                        String nombre = tokens[1].trim(); // Nombre del proveedor
                        String direccion = tokens[2].trim(); // Dirección del proveedor
                        String telefono = tokens[3].trim();
                        // Crear el proveedor y agregarlo a la lista
                        proveedores.add(new Proveedor(id, nombre, direccion,telefono));
                    } catch (Exception e) {
                        System.out.println("Error al procesar la línea: " + linea);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Línea ignorada (formato incorrecto): " + linea);
                }
            }
        }
        return proveedores;
    }

    public boolean registrarProveedor(Proveedor p) {
        ArchivoProveedores archivo = new ArchivoProveedores("proveedores.txt");
        // Registrar el proveedor en formato de texto
        return archivo.registrar(p.getId() + ";" + p.getNombre() + ";" + p.getDireccion() + ";" + p.getTelefono());
    }

    public boolean eliminarPorId(String id) {
        ArchivoProveedores archivo = new ArchivoProveedores("proveedores.txt");
        return archivo.eliminarPorId(id);
    }

    public boolean actualizarProveedor(String id, String columna, String nuevoValor) {
        ArchivoProveedores archivo = new ArchivoProveedores("proveedores.txt");
        return archivo.actualizarProveedor(id, columna, nuevoValor);
    }
}
