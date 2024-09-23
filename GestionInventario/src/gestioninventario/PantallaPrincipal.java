package gestioninventario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {
    private Usuario usuario;

    public PantallaPrincipal(Usuario usuario) {
        this.usuario = usuario;

        // Configuraciones del JFrame
        setTitle("Pantalla Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear los componentes
        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        // Crear el panel y agregar los componentes
        JPanel panel = new JPanel();
        panel.add(lblBienvenida);
        panel.add(btnCerrarSesion);

        // Agregar el panel al JFrame
        add(panel);

        // Acción para el botón "Cerrar Sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    // Método para cerrar sesión
    private void cerrarSesion() {
        new LoginFrame().setVisible(true); // Volver a mostrar el login
        dispose(); // Cerrar la pantalla principal
    }
}

