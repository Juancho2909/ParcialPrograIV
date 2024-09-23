package gestioninventario;


public class UsuarioServicio {
    // Instancia de UsuarioRepositorio como miembro de la clase
    private UsuarioRepositorio usuarioRepositorio;

    
    public UsuarioServicio() {
        usuarioRepositorio = new UsuarioRepositorio();
    }

   
    public boolean validarUsuario(String correo, String contrasena) {
       
        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorCorreo(correo);
        
        if (usuario != null) {
            
            return usuario.getContrasena().equals(contrasena);
        }
        
       
        return false;
    }
}
