
package repositorio;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import modelo.Archivo;
import modelo.Producto;

public class BDProductos {
public LinkedList<Producto> obtener() {
    LinkedList<Producto> productos = new LinkedList<>();
    Archivo archivo = new Archivo("productos.txt");
    LinkedList<String> lineas = archivo.obtenerTextoDelArchivo();

    if (lineas != null) {
        for (String linea : lineas) {
            // Verificar si la línea no está vacía y tiene al menos 7 partes (campos)
            String[] tokens = linea.split(";");
            if (tokens.length == 7) {
                try {
                    int id = Integer.parseInt(tokens[0].trim()); // Trim para eliminar posibles espacios extra
                    String nombre = tokens[1].trim();
                    String categoria = tokens[2].trim();
                    int cantidad = Integer.parseInt(tokens[3].trim());
                    Double precioUnitario = Double.parseDouble(tokens[4].trim());
                    String fechaExpiracion = tokens[5].trim();
                    String proveedor = tokens[6].trim();

                    // Crear el producto y agregarlo a la lista
                    productos.add(new Producto(id, nombre, categoria, cantidad, precioUnitario, fechaExpiracion, proveedor));
                } catch (Exception e) {
                    System.out.println("Error al procesar la línea: " + linea);
                    e.printStackTrace();
                }
            } else {
                System.out.println("Línea ignorada (formato incorrecto): " + linea);
            }
        }
    }
    return productos;
}
    public boolean registrarProducto(Producto p){
        Archivo archivo = new Archivo("productos.txt");
        return archivo.registrar(p.getId()+";"+p.getNombre()+";"+p.getCategoria()+";"+p.getCantidad()+";"+p.getPrecioUnitario()+";"+p.getFechaExpiracion()+";"+p.getProveedor()+";");
    }
     public boolean eliminarPorId(String id){
         Archivo archivo = new Archivo("productos.txt");
         return archivo.eliminarPorId(id);
     }
          public boolean actualizarProducto(String id, String columna, String nuevoValor){
         Archivo archivo = new Archivo("productos.txt");
         return archivo.actualizarPorProducto(id,columna,nuevoValor);
     }

}
