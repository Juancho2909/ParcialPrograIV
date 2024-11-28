package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
public class MovimientoFrame extends JFrame {
    private JTextField fechaField;
    private JTextField productoField;
    private String tipoMovimientoCombo;
    private JTextField cantidadField;
    private JButton guardarButton;
    private JButton cancelarButton;

    public MovimientoFrame() {
        // Configuración de la ventana
        setTitle("Registrar Movimiento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiquetas y campos de texto
        JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
        fechaField = new JTextField();

        JLabel productoLabel = new JLabel("Producto:");
        productoField = new JTextField();

        JLabel tipoMovimientoLabel = new JLabel("Tipo de Movimiento:");
        String[] tiposMovimiento = {"Entrada", "Salida"};
        tipoMovimientoCombo = new JComboBox<>(tiposMovimiento);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadField = new JTextField();

        // Botones
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        // Agregar componentes al panel
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(productoLabel);
        panel.add(productoField);
        panel.add(tipoMovimientoLabel);
        panel.add(tipoMovimientoCombo);
        panel.add(cantidadLabel);
        panel.add(cantidadField);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarButton);
        buttonPanel.add(cancelarButton);

        // Agregar paneles a la ventana
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acciones de botones
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMovimiento();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void guardarMovimiento() {
        String fecha = fechaField.getText();
        String producto = productoField.getText();
        String tipoMovimiento = (String) tipoMovimientoCombo.getSelectedItem();
        String cantidadTexto = cantidadField.getText();

        try {
            int cantidad = Integer.parseInt(cantidadTexto);

            // Aquí puedes agregar la lógica para guardar el movimiento en la base de datos o archivo
            JOptionPane.showMessageDialog(this, 
                "Movimiento guardado:\nFecha: " + fecha + 
                "\nProducto: " + producto + 
                "\nTipo: " + tipoMovimiento + 
                "\nCantidad: " + cantidad, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "La cantidad debe ser un número entero.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        fechaField.setText("");
        productoField.setText("");
        tipoMovimientoCombo.setSelectedIndex(0);
        cantidadField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovimientoFrame frame = new MovimientoFrame();
            frame.setVisible(true);
        });
    }
}
*/