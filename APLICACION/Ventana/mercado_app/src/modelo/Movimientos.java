package modelo;


public class Movimientos {
    private String fecha;
    private Producto producto; // Asociación con la clase Producto
    private String tipoMovimiento; // Por ejemplo, "Entrada" o "Salida"
    private int cantidad;

    // Constructor
    public Movimientos(String fecha, Producto producto, String tipoMovimiento, int cantidad) {
        this.fecha = fecha;
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "fecha=" + fecha +
                ", producto=" + producto.getNombre() + // Asume que Producto tiene un método getNombre()
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
