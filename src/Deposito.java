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

}
