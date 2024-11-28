package vista;

import javax.swing.*;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import servicio.ProveedorServicio;

public class GestionProveedorFrame extends JFrame {

    private ProveedorServicio proveedorServicio; // Instancia de ProveedorServicio
    private DefaultTableModel model;  // Modelo para la tabla
    private JTable table;  // Tabla para mostrar los proveedores
    private JScrollPane scrollPane;  // Panel de desplazamiento para la tabla
    private PrincipalFrame principalFrame;

    public GestionProveedorFrame(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        proveedorServicio = new ProveedorServicio(); // Inicializar el servicio

        setTitle("Gestión de Proveedores");
        setSize(700, 600); // Ajustar tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblSaludo = new JLabel("Bienvenido al apartado de Gestión de Proveedores");
        lblSaludo.setBounds(150, 20, 400, 30);
        add(lblSaludo);

        JButton btnCrearProveedor = new JButton("Crear Proveedor");
        btnCrearProveedor.setBounds(50, 70, 200, 30);
        btnCrearProveedor.addActionListener(e -> crearProveedor());
        add(btnCrearProveedor);

        JButton btnMostrarProveedores = new JButton("Mostrar Proveedores");
        btnMostrarProveedores.setBounds(50, 120, 200, 30);
        btnMostrarProveedores.addActionListener(e -> mostrarProveedores());
        add(btnMostrarProveedores);

        JButton btnEliminarProveedor = new JButton("Eliminar Proveedor");
        btnEliminarProveedor.setBounds(50, 170, 200, 30);
        btnEliminarProveedor.addActionListener(e -> eliminarProveedor());
        add(btnEliminarProveedor);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(50, 220, 200, 30);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        add(btnCerrarSesion);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(50, 270, 200, 30);
        btnAtras.addActionListener(e -> volverAtras());
        add(btnAtras);

        // Crear el modelo de la tabla y la tabla
        String[] columnNames = {"ID", "Nombre", "Dirección", "Teléfono"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // No permitir editar la columna ID
            }
        };
        table = new JTable(model);

        // Agregar un JScrollPane a la tabla
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 70, 400, 400); // Ajustar ubicación y tamaño
        add(scrollPane);

        // Listener para actualizaciones
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    String id = model.getValueAt(row, 0).toString();
                    String nuevoValor = model.getValueAt(row, column).toString();
                    String columnaNombre = model.getColumnName(column);

                    boolean actualizado = proveedorServicio.actualizarProveedor(id, columnaNombre, nuevoValor);
                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        mostrarProveedores(); // Mostrar los proveedores al iniciar
    }

    private void crearProveedor() {
        try {
            String nombre = obtenerValorValido("Ingrese el nombre del proveedor:");
            if (nombre == null) return;

            String direccion = obtenerValorValido("Ingrese la dirección del proveedor:");
            if (direccion == null) return;

            String telefono = obtenerValorValido("Ingrese el teléfono del proveedor:");
            if (telefono == null) return;

            proveedorServicio.guardarEnArchivo(nombre, direccion, telefono);
            JOptionPane.showMessageDialog(this, "Proveedor creado exitosamente.");
            mostrarProveedores();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear el proveedor: " + e.getMessage());
        }
    }

    private void mostrarProveedores() {
        model.setRowCount(0); // Limpiar la tabla

        List<String> proveedores = proveedorServicio.obtenerTodosLosProveedores();
        for (String proveedor : proveedores) {
            String[] proveedorData = proveedor.split(",");
            Object[] row = new Object[proveedorData.length];
            for (int i = 0; i < proveedorData.length; i++) {
                row[i] = proveedorData[i].split(":")[1].trim();
            }
            model.addRow(row);
        }
    }

    private void eliminarProveedor() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID del proveedor a eliminar:");
        boolean eliminado = proveedorServicio.eliminarPorId(id);
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente.");
            mostrarProveedores();
        } else {
            JOptionPane.showMessageDialog(this, "Proveedor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerValorValido(String mensaje) {
        return JOptionPane.showInputDialog(this, mensaje);
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
}
