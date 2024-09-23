package gestioninventario;


import java.util.ArrayList;

public class UsuarioRepositorio {
    // ArrayList estático para almacenar las instancias de los usuarios
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    static {
        // Agregar usuarios de ejemplo
        usuarios.add(new Usuario(1, "Juan", "Pérez", "DNI","12345","Calle Falsa 123", "123456789","pepe@gmail.com","123",true));     
    }

    // Método para crear un usuario y agregarlo al array
    public static void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario creado: " + usuario.getNombre());
    }

    // Método para modificar un usuario existente en el array
    public static void modificarUsuario(int id, Usuario nuevoUsuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getId() == id) {
                usuarios.set(i, nuevoUsuario);
                System.out.println("Usuario con ID " + id + " ha sido modificado.");
                return;
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado.");
    }

    // Método para obtener un usuario por su correo electrónico
    public static Usuario obtenerUsuarioPorCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        System.out.println("Usuario con correo " + correo + " no encontrado.");
        return null;
    }

    // Método para obtener el listado de todos los usuarios
    public static ArrayList<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    // Método para activar un usuario
    public static void activarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setEstadoActivo(true);
                System.out.println("Usuario con ID " + id + " activado.");
                return;
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado.");
    }

    // Método para inactivar un usuario
    public static void inactivarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setEstadoActivo(false);
                System.out.println("Usuario con ID " + id + " inactivado.");
                return;
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado.");
    }
}
