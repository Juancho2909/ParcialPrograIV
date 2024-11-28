
package aplicacion;

import javax.swing.*;

public class Uso_Coche {

   
    public static void main(String[] args) {
     Coche Renault=new Coche();
     
    Renault.establece_color(JOptionPane.showInputDialog("Introduce color"));
     
    
     System.out.println(Renault.dar_color());
 
    Renault.configura_asientos("no");
    System.out.println(Renault.dar_asientos());
}

}