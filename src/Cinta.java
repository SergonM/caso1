public class Cinta {
    private Tipo producto;
    private boolean lleno = false;

    public synchronized void agregarACinta(String id, Tipo producto) throws InterruptedException {
        while (lleno) {
            wait();  // Si la cinta está llena, espera
        }
        this.producto = producto;
        lleno = true;
        System.out.println("Producto " + producto + " depositado en la cinta gracias a " + id);
        notifyAll();  // Notifica que se ha agregado un producto a la cinta
    }

    public synchronized Tipo retirarDeCinta(String id) throws InterruptedException {
        while (!lleno) {
            wait();  // Si la cinta está vacía, espera
        }
        Tipo productoRetirado = this.producto;
        this.producto = null;
        lleno = false;
        System.out.println("Producto " + productoRetirado + " retirado de la cinta gracias a " + id);
        notifyAll();  // Notifica que la cinta está vacía
        return productoRetirado;
    }
}
