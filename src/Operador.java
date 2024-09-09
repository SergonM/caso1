public class Operador extends Thread {
    private Deposito deposito;
    private Cinta cinta;
    private Tipo tipo;
    private boolean esFaseProduccion;
    private int finAContados = 0;
    private int finBContados = 0;

    public Operador(Deposito deposito, Cinta cinta, boolean esFaseProduccion,Tipo  tipo) {
        this.deposito = deposito;
        this.cinta = cinta;
        this.esFaseProduccion = esFaseProduccion;
        this.tipo= tipo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                
                if (esFaseProduccion) {
                    Tipo producto = deposito.retirarProducto(tipo);
                    if (producto == Tipo.FIN_A || producto == Tipo.FIN_B) {
                        System.out.println("Operario ha visto un producto de fin en la fase de producción (" + producto + ").");
                        cinta.agregarACinta(producto);
                        contarProductoFin(producto);
                        if (finAContados == 2 && finBContados == 2) break;
                    } else {
                        cinta.agregarACinta(producto);
                        System.out.println("Operario movió un producto " + producto + " a la cinta.");
                    }
                } else {
                    
                    Tipo producto = cinta.retirarDeCinta();
                    if (producto == Tipo.FIN_A || producto == Tipo.FIN_B) {
                        System.out.println("Operario ha visto un producto de fin en la fase de distribución (" + producto + ").");
                        deposito.agregarProducto(producto);
                        contarProductoFin(producto);
                        if (finAContados == 2 && finBContados == 2) break;
                    } else {
                        deposito.agregarProducto(producto);
                        System.out.println("Operario movió un producto " + producto + " al depósito de distribución.");
                    }
                }
                Thread.yield(); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void contarProductoFin(Tipo producto) {
        if (producto == Tipo.FIN_A) {
            finAContados++;
        } else if (producto == Tipo.FIN_B) {
            finBContados++;
        }
    }
}
