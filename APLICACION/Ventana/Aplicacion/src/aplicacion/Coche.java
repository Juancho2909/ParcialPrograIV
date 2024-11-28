
package aplicacion;


public class Coche {
      private int ruedas;
      private int largo;
      private int ancho;
      private int motor;
      private int peso_plataforma; 
      private String color;
      private int peso_total;
      private boolean asientos_cuero,climatizador;
      
      
      public Coche(){
           ruedas=4;
           largo=2000;
           ancho=300;
           motor=1600;
           peso_plataforma=500;
           
      } 
      
      public String dar_largo(){
          
          return "El largo del coche es " + largo;
      }
      
      public void establece_color(String color_coche){
         color = color_coche;
      }
      
      public String dar_color(){
          return "El color del coche es " + color;
      }
      
      public void configura_asientos(String asientos_cuero){
          if (asientos_cuero.equalsIgnoreCase("si")){
              this.asientos_cuero=true;
          }else{
              this.asientos_cuero=false;
          }
      }
      
      public String dar_asientos(){
          if(asientos_cuero==true){
              return "El coche tiene asientos de cuero";
          }else{
              return "El coche tiene asientos de serie";
          }
      }
      
      
      
      
}
