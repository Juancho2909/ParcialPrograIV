package repositorio;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
    Usuario admin = new Usuario();
    admin.setId(1);
    admin.setNombre("Admin");
    admin.setApellido("Usuario");
    admin.setTipoDocumento("Cédula");
    admin.setNumeroDocumento("123456");
    admin.setCorreo("admin@example.com");
    admin.setTelefono("123456789");
    admin.setEstado(true);
    usuarios.add(admin);
}

    public void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void modificarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getId() == usuario.getId()) {
                u.setNombre(usuario.getNombre());
                u.setApellido(usuario.getApellido());
                u.setTipoDocumento(usuario.getTipoDocumento());
                u.setNumeroDocumento(usuario.getNumeroDocumento());
                u.setCorreo(usuario.getCorreo());
                u.setTelefono(usuario.getTelefono());
                u.setEstado(usuario.isEstado());
            }
        }
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                return u;
            }
        }
        return null;
    }
    
    
    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
    

    public void cambiarEstadoUsuario(int id, boolean estado) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                u.setEstado(estado);
            }
        }
    }
}
