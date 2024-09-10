import java.util.ArrayList;

public class Deposito {
    private ArrayList<Tipo> productos = new ArrayList<>();
    private int capacidad;
    private String responsabilidad;
    // private Cinta cinta;

    public Deposito(int capacidad, String responsabilidad) {
        this.capacidad = capacidad;
        this.responsabilidad = responsabilidad;
    }

    public synchronized void agregarProducto(Tipo producto, String id) throws InterruptedException {
        while (contarTiposAB() == capacidad) {
            wait();
        }
        productos.add(producto);
        System.out.println("Producto " + producto + " almacenado en dep " + responsabilidad + ", gracias a: " + id);
        notifyAll();
    }

    public synchronized Tipo retirarProducto(Tipo tipo, String id) throws InterruptedException {
        //System.out.println(tipo + "---" + productos.size() + "---------------" + productos.isEmpty());
        while (productos.isEmpty()
        || ((tipo != Tipo.OP && !productos.contains(tipo))
        && (tipo != Tipo.OP && (tipo == Tipo.A? !productos.contains(Tipo.FIN_A): !productos.contains(Tipo.FIN_B))))){
            wait();
        }


        int indice = -1;
        if (tipo == Tipo.A) {
            indice = productos.indexOf(Tipo.A) != -1 ? productos.indexOf(Tipo.A) : productos.indexOf(Tipo.FIN_A);
        } else if (tipo == Tipo.B) {
            indice = productos.indexOf(Tipo.B) != -1 ? productos.indexOf(Tipo.B) : productos.indexOf(Tipo.FIN_B);
        } else if (tipo == Tipo.OP && !productos.isEmpty()) {
            indice = 0;
        }

        Tipo producto = productos.remove(indice);
        System.out.println("Producto " + producto + " retirado de dep " + responsabilidad + ", gracias a :" + id);
        notifyAll();
        return producto;

    }

    private int contarTiposAB() {
        int contador = 0;
        for (Tipo tipo : productos) {
            if (tipo == Tipo.A || tipo == Tipo.B) {
                contador++;
            }
        }
        return contador;
    }

}
