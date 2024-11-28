package servicio;

import modelo.Usuario;
import repositorio.UsuarioRepositorio;

public class UsuarioServicio {
    private UsuarioRepositorio repositorio;

    public UsuarioServicio() {
        repositorio = new UsuarioRepositorio();
    }


    
    public boolean validarUsuario(String correo,String password){
        Usuario usuario = repositorio.obtenerUsuarioPorCorreo(correo);
        if (usuario != null && password.equals("password123") && usuario.isEstado()) {
            return true;
        }
        return false;
    }    
    
    public String bienvenidaUsuario(String correo){
        Usuario usuario = repositorio.obtenerUsuarioPorCorreo(correo);
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        String completo = nombre+" "+apellido;
        String error = "Error";
        if (usuario != null) {
            return completo;
        }
            return error;
    }
  
        
    

    public void activarUsuario(String correo) {
        Usuario usuario = repositorio.obtenerUsuarioPorCorreo(correo);
        if (usuario != null) {
            usuario.setEstado(true);
        }
    }

    public void inactivarUsuario(String correo) {
        Usuario usuario = repositorio.obtenerUsuarioPorCorreo(correo);
        if (usuario != null) {
            usuario.setEstado(false);
        }
    }
}
