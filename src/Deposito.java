import java.util.ArrayList;

public class Deposito {
     private ArrayList<Tipo> productos = new ArrayList<>();
     private int capacidad;
     //private Cinta cinta;

     public Deposito(int capacidad){
          this.capacidad = capacidad;
     }
     public synchronized void agregarProducto(Tipo producto) throws InterruptedException {
          while (productos.size() == capacidad) {
              wait();
          }
          productos.add(producto);
          System.out.println("Producto almacenado: " + producto);
          notifyAll();
      }

    public synchronized Tipo retirarProducto(Tipo tipo) throws InterruptedException {
        while (productos.isEmpty() || !productos.contains(tipo)) {
            wait();
        }

        int indice = -1;
        if (tipo == Tipo.A) {
            indice =  productos.indexOf(Tipo.A) != -1 ? productos.indexOf(Tipo.A) : productos.indexOf(Tipo.FIN_A);
        } else if (tipo == Tipo.B) {
            indice = productos.indexOf(Tipo.B) != -1 ? productos.indexOf(Tipo.B) : productos.indexOf(Tipo.FIN_B);
        } else if (tipo == Tipo.OP) {
            indice = 0;
        }


        Tipo producto = productos.remove(indice);
        System.out.println("Producto retirado: " + producto);
        notifyAll();
        return producto;
    }

}
