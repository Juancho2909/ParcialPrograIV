package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class Archivo {
    private String nombre;
    public Archivo(String nombre){
        this.nombre=nombre;
    }
    
    private File obtenerArchivo(){
        try{
            URL url = getClass().getClassLoader().getResource("modelo/resources/archivos/"+nombre);
            return new File(url.toURI());
        }catch(URISyntaxException ex){
            ex.printStackTrace();
        return null;
        }
        
    }
    
    
    
    
    public boolean registrar(String linea){
        File archivo = obtenerArchivo();
        try{
        if(archivo.exists()){
            FileWriter fw = new FileWriter(archivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(linea);
            pw.flush();
            pw.close();
            return true;
        }
    }catch(Exception error){
        error.printStackTrace();
    }
       return false;
    }
    
    
    

    
    public LinkedList<String> obtenerTextoDelArchivo(){
        LinkedList<String> lineasDeTexto = null;
        try{
            File archivo = obtenerArchivo();
            if(archivo.exists()){
                lineasDeTexto = new LinkedList();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine())!=null){
                        System.out.println(linea);
                        lineasDeTexto.add(linea);
                }
                br.close();
            }
            else{
                JOptionPane.showMessageDialog(null,"El archivo de texto no existe");
            }
        } catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Se produjo un Error");
        }
        return lineasDeTexto;
    }
    
        // Método para eliminar una línea basada en el id
// Método para eliminar una línea basada en el id
public boolean eliminarPorId(String id) {
    File archivo = obtenerArchivo();
    LinkedList<String> lineasDeTexto = obtenerTextoDelArchivo();  // Obtener las líneas actuales

    if (lineasDeTexto == null || lineasDeTexto.isEmpty()) {
        return false;  // No hay líneas para procesar
    }

    boolean eliminado = false;
    LinkedList<String> lineasActualizadas = new LinkedList<>();

    // Filtramos las líneas que no contienen el id especificado exactamente
    for (String linea : lineasDeTexto) {
        // Dividimos la línea por el delimitador `;` para obtener el id
        String[] campos = linea.split(";");
        if (campos.length > 0 && !campos[0].equals(id)) { 
            // Si el primer campo (id) no coincide, la línea se mantiene
            lineasActualizadas.add(linea);
        } else {
            eliminado = true;  // Si encontramos el id, marcamos como eliminado
        }
    }

    // Si se eliminó la línea, sobrescribimos el archivo con las líneas restantes
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
            return true;  // Retornamos true si el producto fue eliminado
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró el producto con id: " + id);
    }

    return false;  // Retornamos false si no se eliminó nada
}

    public boolean actualizarPorProducto(String id, String columna, String nuevoValor) {
    File archivo = obtenerArchivo();
    LinkedList<String> lineasDeTexto = obtenerTextoDelArchivo(); // Obtener las líneas actuales del archivo

    if (lineasDeTexto == null || lineasDeTexto.isEmpty()) {
        return false; // No hay datos en el archivo para procesar
    }

    boolean actualizado = false; // Indicador de si el producto fue actualizado
    LinkedList<String> lineasActualizadas = new LinkedList<>();

    // Iterar por cada línea en el archivo
    for (String linea : lineasDeTexto) {
        String[] campos = linea.split(";"); // Dividir la línea por el delimitador ";"

        if (campos.length == 7 && campos[0].equals(id)) { // Si el ID coincide y tiene el formato esperado

            switch (columna.toLowerCase()) {
                case "nombre":
                    campos[1] = nuevoValor; // Actualizar el nombre
                    break;
                case "categoria":
                    campos[2] = nuevoValor; // Actualizar la categoría
                    break;
                case "categoría":
                    campos[2] = nuevoValor; // Actualizar la categoría
                    break;                
                case "cantidad":
                    campos[3] = nuevoValor; // Actualizar la cantidad
                    break;
                case "preciounitario":
                    campos[4] = nuevoValor; // Actualizar el precio unitario
                    break;
                case "precio unitario":
                    campos[4] = nuevoValor; // Actualizar el precio unitario
                    break;                    
                case "fechaexpiracion":
                    campos[5] = nuevoValor; // Actualizar la fecha de expiración
                    break;   
                case "fechaexpiración":
                    campos[5] = nuevoValor; // Actualizar la categoría
                    break; 
                case "fecha de expiracion":
                    campos[5] = nuevoValor; // Actualizar la categoría
                    break;    
                case "fecha de expiración":
                    campos[5] = nuevoValor; // Actualizar la categoría
                    break;                        
                case "proveedor":
                    campos[6] = nuevoValor; // Actualizar el proveedor
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Columna no válida: " + columna);
                    return false; // Si la columna no existe, salimos del método
            }

            // Reconstruir la línea con los campos actualizados
            String lineaActualizada = String.join(";", campos);
            lineasActualizadas.add(lineaActualizada);
            actualizado = true;
        } else {
            lineasActualizadas.add(linea); // Mantener las líneas que no coinciden
        }
    }

    if (actualizado) {
        try {
            // Sobrescribir el archivo con las líneas actualizadas
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (String linea : lineasActualizadas) {
                pw.println(linea);
            }

            pw.flush();
            pw.close();
            return true; // Producto actualizado con éxito
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró un producto con el id: " + id);
    }

    return false; // Retornamos false si no se actualizó nada
}



}
