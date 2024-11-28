package vista;

import javax.swing.*;


public class PrincipalFrame extends JFrame {

    public PrincipalFrame(String nombre) {
        setTitle("Gestión de Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);        
        
        JLabel lblCorreo = new JLabel("Bienvenido " + nombre);
        lblCorreo.setBounds(150, 50, 800, 30);
        add(lblCorreo);        

        JButton btnProductos = new JButton("Gestión de Productos");
        btnProductos.setBounds(150, 100, 200, 30);
        btnProductos.addActionListener(e -> abrirGestionProductos());
        add(btnProductos);

        JButton btnProveedores = new JButton("Gestión de Proveedores");
        btnProveedores.setBounds(150, 150, 200, 30);
        btnProveedores.addActionListener(e -> abrirGestionProveedores());
        add(btnProveedores);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(150, 200, 200, 30);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        add(btnCerrarSesion);
    }

    private void abrirGestionProductos() {
        new GestionProductoFrame(this).setVisible(true);      
    }

    private void abrirGestionProveedores() {
        new GestionProveedorFrame(this).setVisible(true); 
    }

    public void cerrarSesion() {
        dispose();  // Cierra la ventana actual (PrincipalFrame)
        new LoginFrame().setVisible(true);  // Abre LoginFrame
    }
}

