package vista;

import javax.swing.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import servicio.ProductoServicio;
//import modelo.Movimientos;

public class GestionProductoFrame extends JFrame {

    private ProductoServicio productoServicio; // Instancia de ProductoServicio
    private DefaultTableModel model;  // Modelo para la tabla
    private JTable table;  // Tabla para mostrar los productos
    private JScrollPane scrollPane;  // Panel de desplazamiento para la tabla
    private PrincipalFrame principalFrame;

    public GestionProductoFrame(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        productoServicio = new ProductoServicio(); // Inicializar el servicio

        setTitle("Gestión de Inventario");
        setSize(700, 600); // Ajustar tamaño de la ventana para más espacio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblSaludo = new JLabel("Bienvenido al apartado de Gestión de Productos");
        lblSaludo.setBounds(150, 20, 800, 30);
        add(lblSaludo);

        JButton btnCrearProducto = new JButton("Crear Producto");
        btnCrearProducto.setBounds(50, 70, 200, 30);
        btnCrearProducto.addActionListener(e -> crearProducto());
        add(btnCrearProducto);

        JButton btnMostrarProductos = new JButton("Mostrar Productos");
        btnMostrarProductos.setBounds(50, 120, 200, 30);
        btnMostrarProductos.addActionListener(e -> mostrarProductos());
        add(btnMostrarProductos);

        JButton btnEliminarProducto = new JButton("Eliminar Producto");
        btnEliminarProducto.setBounds(50, 170, 200, 30);
        btnEliminarProducto.addActionListener(e -> eliminarProducto());
        add(btnEliminarProducto);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(50, 220, 200, 30);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        add(btnCerrarSesion);

        JButton btnAtras = new JButton("Atras");
        btnAtras.setBounds(50, 270, 200, 30);
        btnAtras.addActionListener(e -> volverAtras());
        add(btnAtras);

        // Crear el modelo de la tabla y la tabla
        String[] columnNames = {"ID", "Nombre", "Categoría", "Cantidad", "Precio Unitario", "Fecha de Expiración", "Proveedor"};
        model = new DefaultTableModel(columnNames, 0) {
            // Hacer todas las celdas editables
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // No permitir editar la columna ID
            }
        };
        table = new JTable(model);

        // Agregar un JScrollPane a la tabla
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 70, 1000, 500);  // Ajustar ubicación y tamaño
        add(scrollPane);

        // Agregar un TableModelListener para detectar cambios
model.addTableModelListener(new TableModelListener() {
    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String id = model.getValueAt(row, 0).toString();
            String nuevoValor = model.getValueAt(row, column).toString();
            String columnaNombre = model.getColumnName(column);

            // Obtener el valor anterior de la celda para comparar
            String valorAnterior = model.getValueAt(row, column).toString();

            // Validar según el nombre de la columna
            boolean validacionExitosa = false;
            switch (columnaNombre) {
                case "Cantidad":
                    validacionExitosa = esCantidadValida(nuevoValor);
                    if (validacionExitosa) {
                        // Comparar la cantidad antes y después de la edición
                        int cantidadAnterior = Integer.parseInt(valorAnterior);
                        int cantidadNueva = Integer.parseInt(nuevoValor);

                        if (cantidadNueva != cantidadAnterior) {
                            // Verificar si hubo aumento o detrimento
                            String tipoCambio = cantidadNueva > cantidadAnterior ? "aumento" : "detrimento";
                            String mensajeMotivo = JOptionPane.showInputDialog(this, "Ingrese el motivo del " + tipoCambio + " de la cantidad:");
                            
                            if (mensajeMotivo != null && !mensajeMotivo.isEmpty()) {
                                // Mostrar mensaje de confirmación con el motivo
                                String mensaje = "Se " + (tipoCambio.equals("aumento") ? "ingresan" : "eliminan") + " " + Math.abs(cantidadNueva - cantidadAnterior) + " unidades por motivo de: " + mensajeMotivo;
                                JOptionPane.showMessageDialog(null, mensaje);
                            }
                        }
                    }
                    break;
                case "Fecha de Expiración":
                    validacionExitosa = esFechaValida(nuevoValor);
                    break;
                // Agregar más casos para otras columnas si es necesario
                default:
                    validacionExitosa = true; // Por defecto, todas las demás columnas son válidas
            }

            if (validacionExitosa) {
                actualizarProducto(id, columnaNombre, nuevoValor);
            } else {
                JOptionPane.showMessageDialog(null, "El valor ingresado no es válido para la columna " + columnaNombre);
                // Revertir el cambio si es necesario
                model.setValueAt(valorAnterior, row, column);
            }
        }
    }
});

        // Mostrar los productos al iniciar
        mostrarProductos();
    }
    
    
    
    // Función para validar el formato de la fecha
private boolean esFechaValida(String fecha) {
    // Utilizamos la misma expresión regular que en el ejemplo anterior
    Pattern patronFecha = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
    return patronFecha.matcher(fecha).matches();
}
    // Función para validar el formato de la fecha
private boolean esCantidadValida(String cantidad) {
    // Utilizamos la misma expresión regular que en el ejemplo anterior
    Pattern patronFecha = Pattern.compile("\\d+");
    return patronFecha.matcher(cantidad).matches();
}

private void crearProducto() {
    try {
        // Solicitar datos al usuario
        String nombre = obtenerValorValido("Ingrese el nombre del producto:");
        if (nombre == null) {
            return; // Salir del método si se cancela
        }

        String categoria = obtenerValorValido("Ingrese la categoría del producto:");
        if (categoria == null) {
            return; // Salir del método si se cancela
        }

        // Validación de la cantidad
        int cantidad = obtenerCantidadValida();
        if (cantidad == -1) {
            return; // Salir del método si se cancela
        }

        // Validación del precio unitario
        double precioUnitario = obtenerPrecioValido();
        if (precioUnitario == -1) {
            return; // Salir del método si se cancela
        }

        // Validación de la fecha de expiración
        String fechaExpiracion = obtenerFechaValida();
        if (fechaExpiracion == null) {
            return; // Salir del método si se cancela
        }

        // Solicitar el proveedor del producto
        String proveedor = obtenerValorValido("Ingrese el proveedor");
        if (proveedor == null) {
            return; // Salir del método si se cancela
        }

        // Guardar el producto
        productoServicio.guardarEnArchivo(nombre, categoria, cantidad, precioUnitario, fechaExpiracion, proveedor);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Producto creado exitosamente.");
        mostrarProductos(); // Actualizar la tabla después de crear el producto
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error al crear el producto: " + e.getMessage());
    }
}

private String obtenerValorValido(String mensaje) {
    String valor = JOptionPane.showInputDialog(this, mensaje);
    return valor;
}

private int obtenerCantidadValida() {
    while (true) {
        try {
            String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad:");
            if (cantidadStr == null) {
                return -1; // Indicar que se canceló
            }
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                throw new NumberFormatException("La cantidad debe ser mayor a cero.");
            }
            return cantidad;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero positivo.");
        }
    }
}

private double obtenerPrecioValido() {
    // Código similar a obtenerCantidadValida, pero validando un número decimal
    while (true) {
        try {
            String precioStr = JOptionPane.showInputDialog(this, "Ingrese el precio unitario:");
            if (precioStr == null) {
                return -1; // Indicar que se canceló
            }
            double precio = Double.parseDouble(precioStr);
            if (precio <= 0) {
                throw new NumberFormatException("El precio unitario debe ser mayor a cero.");
            }
            return precio;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio unitario debe ser un número decimal positivo.");
        }
    }
}

private String obtenerFechaValida() {
    while (true) {
        String fechaExpiracion = JOptionPane.showInputDialog(null, "Ingrese la fecha de expiración (formato dd/MM/yyyy)");
        if (fechaExpiracion == null) {
            return null; // Indicar que se canceló
        }
        Pattern patronFecha = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        if (patronFecha.matcher(fechaExpiracion).matches()) {
            return fechaExpiracion;
        } else {
            JOptionPane.showMessageDialog(null, "El formato de la fecha es incorrecto. Por favor, ingrese la fecha en formato dd/MM/yyyy");
        }
    }
}
    private void mostrarProductos() {
        model.setRowCount(0); // Limpiar la tabla

        List<String> productos = productoServicio.obtenerTodosLosProductos();

        for (String productoString : productos) {
            String[] productoData = productoString.split(",");
            Object[] row = new Object[productoData.length];
            for (int i = 0; i < productoData.length; i++) {
                row[i] = productoData[i].split(":")[1].trim();
            }
            model.addRow(row);
        }
    }

    private void eliminarProducto() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID del producto a eliminar:");
        boolean eliminado = productoServicio.eliminarPorId(id);
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.");
            mostrarProductos(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarSesion() {
        dispose();
        if (principalFrame != null) {
            principalFrame.dispose();
        }
        new LoginFrame().setVisible(true);
    }

    private void volverAtras() {
        dispose();
    }

    // Método para manejar actualizaciones
    private void actualizarProducto(String id, String columna, String nuevoValor) {
    // Realizar la actualización
    try {
        boolean actualizado = productoServicio.actualizarProducto(id, columna, nuevoValor);
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
            mostrarProductos(); // Actualizar la vista
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
