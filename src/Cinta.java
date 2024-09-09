public class Cinta {
    private Tipo producto;
    private boolean lleno = false;

    public synchronized void agregarACinta(Tipo producto) throws InterruptedException {
        while (lleno) {
            wait();  // Si la cinta está llena, espera
        }
        this.producto = producto;
        lleno = true;
        notifyAll();  // Notifica que se ha agregado un producto a la cinta
    }

    public synchronized Tipo retirarDeCinta() throws InterruptedException {
        while (!lleno) {
            wait();  // Si la cinta está vacía, espera
        }
        Tipo productoRetirado = this.producto;
        this.producto = null;
        lleno = false;
        notifyAll();  // Notifica que la cinta está vacía
        return productoRetirado;
    }
}
