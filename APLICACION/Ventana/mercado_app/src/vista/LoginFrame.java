package vista;

import javax.swing.*;
import servicio.UsuarioServicio;

public class LoginFrame extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private UsuarioServicio servicio;
    
   
    
    public LoginFrame() {
        servicio = new UsuarioServicio();
        setTitle("Login");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 50, 100, 30);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 50, 200, 30);
        add(txtCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 100, 100, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 200, 30);
        add(txtPassword);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(150, 150, 100, 30);
        btnLogin.addActionListener(e -> autenticarUsuario());
        add(btnLogin);

        JButton btnCerrar = new JButton("Salir");
        btnCerrar.setBounds(150, 200, 100, 30);
        btnCerrar.addActionListener(e -> salirAplicacion());
        add(btnCerrar);
    }
    private void autenticarUsuario() {
        String correo = txtCorreo.getText();
        String password = new String(txtPassword.getPassword());
        if (servicio.validarUsuario(correo,password)) {
            JOptionPane.showMessageDialog(this, "Bienvenido!");
            dispose();
        String nombreCompleto = servicio.bienvenidaUsuario(correo);
            new PrincipalFrame(nombreCompleto).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
    private void salirAplicacion(){
        dispose();
    }
}
