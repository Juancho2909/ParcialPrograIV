package modelo;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ArchivoProveedores {
    private String nombre;

    public ArchivoProveedores(String nombre) {
        this.nombre = nombre;
    }

    private File obtenerArchivo() {
        try {
            URL url = getClass().getClassLoader().getResource("modelo/resources/archivos/" + nombre);
            return new File(url.toURI());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean registrar(String linea) {
        File archivo = obtenerArchivo();
        try {
            if (archivo.exists()) {
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(linea);
                pw.flush();
                pw.close();
                return true;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }

    public LinkedList<String> obtenerTextoDelArchivo() {
        LinkedList<String> lineasDeTexto = null;
        try {
            File archivo = obtenerArchivo();
            if (archivo.exists()) {
                lineasDeTexto = new LinkedList<>();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    lineasDeTexto.add(linea);
                }
                br.close();
            } else {
                JOptionPane.showMessageDialog(null, "El archivo de proveedores no existe.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se produjo un error.");
        }
        return lineasDeTexto;
    }

    public boolean eliminarPorId(String id) {
        File archivo = obtenerArchivo();
        LinkedList<String> lineasDeTexto = obtenerTextoDelArchivo();

        if (lineasDeTexto == null || lineasDeTexto.isEmpty()) {
            return false;
        }

        boolean eliminado = false;
        LinkedList<String> lineasActualizadas = new LinkedList<>();

        for (String linea : lineasDeTexto) {
            String[] campos = linea.split(";");
            if (campos.length > 0 && !campos[0].equals(id)) {
                lineasActualizadas.add(linea);
            } else {
                eliminado = true;
            }
        }

        if (eliminado) {
            try {
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                for (String linea : lineasActualizadas) {
                    pw.println(linea);
                }
                pw.flush();
                pw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un proveedor con id: " + id);
        }

        return false;
    }

    public boolean actualizarProveedor(String id, String columna, String nuevoValor) {
        File archivo = obtenerArchivo();
        LinkedList<String> lineasDeTexto = obtenerTextoDelArchivo();

        if (lineasDeTexto == null || lineasDeTexto.isEmpty()) {
            return false;
        }

        boolean actualizado = false;
        LinkedList<String> lineasActualizadas = new LinkedList<>();

        for (String linea : lineasDeTexto) {
            String[] campos = linea.split(";");
            if (campos.length == 4 && campos[0].equals(id)) { // Suponiendo 4 campos: id, nombre, dirección, teléfono
                switch (columna.toLowerCase()) {
                    case "nombre":
                        campos[1] = nuevoValor;
                        break;
                    case "direccion":
                    case "dirección":
                        campos[2] = nuevoValor;
                        break;
                    case "telefono":
                    case "teléfono":
                        campos[3] = nuevoValor;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Columna no válida: " + columna);
                        return false;
                }

                String lineaActualizada = String.join(";", campos);
                lineasActualizadas.add(lineaActualizada);
                actualizado = true;
            } else {
                lineasActualizadas.add(linea);
            }
        }

        if (actualizado) {
            try {
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                for (String linea : lineasActualizadas) {
                    pw.println(linea);
                }
                pw.flush();
                pw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un proveedor con id: " + id);
        }

        return false;
    }
}
