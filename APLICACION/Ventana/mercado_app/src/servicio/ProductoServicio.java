package servicio;

import modelo.Producto;
import repositorio.ProductoRepositorio;
import repositorio.BDProductos;

import java.util.Date;

import java.util.List;

public class ProductoServicio {
    private ProductoRepositorio repositorio;

    public ProductoServicio() {
        this.repositorio = new ProductoRepositorio();

    }

    public List<String> obtenerTodosLosProductos() {
        return repositorio.obtenerTodosLosProductos();
    }

    public Producto obtenerProductoPorId(int id) {
        return repositorio.obtenerProductoPorId(id);
    }

    public boolean actualizarProducto(String id,String columna,String nuevoValor) {
        return repositorio.actualizarProductos(id,columna,nuevoValor);
    }

    public boolean eliminarPorId(String id){
        return repositorio.eliminarPorId(id);
    }
    
    public void guardarEnArchivo(String nombre, String categoria, int cantidad, double precioUnitario, String fechaExpiracion, String proveedor){
      Producto producto = repositorio.agregarProducto(nombre, categoria, cantidad, precioUnitario, fechaExpiracion, proveedor);
      BDProductos bd = new BDProductos();
      bd.registrarProducto(producto);
    }
    
}
