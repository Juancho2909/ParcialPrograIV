package gestioninventario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private UsuarioServicio usuarioServicio;

    public LoginFrame() {
        usuarioServicio = new UsuarioServicio();

        // Configuraciones del JFrame
        setTitle("Login de Usuario");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear los componentes
        JLabel lblCorreo = new JLabel("Correo:");
        JLabel lblPassword = new JLabel("Contraseña:");
        txtCorreo = new JTextField(20);
        txtPassword = new JPasswordField(20);
        JButton btnIngresar = new JButton("Ingresar");

        // Crear el panel y agregar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnIngresar);

        // Agregar panel al JFrame
        add(panel);

        // Acción para el botón "Ingresar"
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contraseña = new String(txtPassword.getPassword());

                // Validar usuario
                boolean validacion = usuarioServicio.validarUsuario(correo, contraseña);
                if (validacion) {
                    Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorCorreo(correo);
                    mostrarPantallaPrincipal(usuario);
                    dispose(); // Cerrar la ventana de login
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para mostrar la pantalla principal
    private void mostrarPantallaPrincipal(Usuario usuario) {
        new PantallaPrincipal(usuario).setVisible(true);
    }
}