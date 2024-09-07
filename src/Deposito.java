import java.util.ArrayList;

public class Deposito {
     private ArrayList<tipo> productos = new ArrayList<>();
     private int capacidad;
     private Operador operador;
     //private Cinta cinta;

     public Deposito(int capacidad, Operador operador){
          this.capacidad = capacidad;
          this.operador = operador;
     }
     public synchronized void agregarProducto(tipo producto) throws InterruptedException {
          while (productos.size() == capacidad) {
              wait();  
          }
          productos.add(producto);
          notifyAll();  
      }
      public synchronized tipo retirarProducto(tipo tipo) throws InterruptedException {
          while (productos.isEmpty() || !productos.contains(tipo)) {
              wait();  
          }
          tipo producto = productos.remove(productos.indexOf(tipo));
          notifyAll();  
          return producto;
      }

}
