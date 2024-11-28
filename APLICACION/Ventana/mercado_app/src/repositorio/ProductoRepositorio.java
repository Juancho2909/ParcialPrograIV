package repositorio;

import modelo.Producto;
import java.text.ParseException;  // Importar ParseException
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductoRepositorio {
    private static List<Producto> productos = new ArrayList<>();
    
    
    // Crear (Agregar Producto)
    public Producto agregarProducto(String nombre, String categoria, int cantidad, double precioUnitario, String fechaExpiracion, String proveedor) {
        // Generar un nuevo producto con los datos proporcionados
        Producto nuevoProducto = new Producto(
            productos.size() + 1, // Asignación de ID único (por ejemplo, tamaño de la lista + 1)
            nombre,
            categoria,
            cantidad,
            precioUnitario,
            fechaExpiracion,    
            proveedor
        );
        productos.add(nuevoProducto);
        return nuevoProducto;
        
    }
    
    public boolean eliminarPorId(String id){
        BDProductos bd = new BDProductos();
        return bd.eliminarPorId(id);
    }
    
public boolean actualizarProductos(String id, String columna, String nuevoValor) {
    BDProductos bd = new BDProductos();  // Instanciar BDProductos
    return bd.actualizarProducto(id, columna, nuevoValor);  // Llamar correctamente al método de BDProductos
}

  
    public void cargarProductos(){
        BDProductos bd = new BDProductos();
        productos=bd.obtener(); 
    }
    
    public List<String> obtenerTodosLosProductos() {
        List<String> productosString = new ArrayList<>();
        BDProductos bd = new BDProductos();
        productos=bd.obtener(); 
        for (Producto producto : productos) {
        productosString.add(producto.toString()); // Convierte cada Producto a String
    }
        return productosString;
    }


public Producto obtenerProductoPorId(int id) {
    // Lista para almacenar los productos convertidos a cadena
    List<String> productosString = new ArrayList<>();
    // Instancia de la base de datos de productos
    BDProductos bd = new BDProductos();
    // Lista de productos obtenidos de la base de datos
    List<Producto> productos = bd.obtener();

    // Convertir cada Producto a String y agregarlo a productosString
    for (Producto producto : productos) {
        productosString.add(producto.toString());  // Convierte cada Producto a String
    }

    // Recorrer los productosString para buscar el id
    for (String productoString : productosString) {
        // Verificar si la cadena contiene "id:" seguido de algún valor
        if (productoString.contains("id:")) {
            // Extraer el valor del id de la cadena
            String[] parts = productoString.split(",");
            for (String part : parts) {
                if (part.trim().startsWith("id:")) {
                    // Obtener el id de la cadena (debe ser el valor después de "id:")
                    int productoId = Integer.parseInt(part.split(":")[1].trim());

                    // Verificar si el id coincide
                    if (productoId == id) {
                        // Devolver el producto si el id coincide
                        // Puedes obtener el producto original buscando en la lista de productos
                        for (Producto producto : productos) {
                            if (producto.getId() == id) {
                                return producto;
                            }
                        }
                    }
                }
            }
        }
    }

    // Si no se encuentra el producto con el id especificado, retornar null
    return null;
}


    // Actualizar
    public void actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                return;
            }
        }
    }
    

    
    // Eliminar
    public boolean eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
        return true;
    }
}
