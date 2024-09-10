public class Operador extends Thread {
    private Deposito deposito;
    private Cinta cinta;
    private Tipo tipo;
    private boolean esFaseProduccion;
    private int finAContados = 0;
    private int finBContados = 0;
    private String id;

    public Operador(String id, Deposito deposito, Cinta cinta, boolean esFaseProduccion,Tipo  tipo) {
        this.deposito = deposito;
        this.cinta = cinta;
        this.esFaseProduccion = esFaseProduccion;
        this.tipo= tipo;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (esFaseProduccion) {
                    Tipo producto = deposito.retirarProducto(tipo, this.id);
                    if (producto == Tipo.FIN_A || producto == Tipo.FIN_B) {
                        cinta.agregarACinta(this.id ,producto);
                        contarProductoFin(producto);
                        if (finAContados == 2 && finBContados == 2) break;
                    } else {
                        cinta.agregarACinta(this.id,producto);
                    }
                } else {
                    
                    Tipo producto = cinta.retirarDeCinta(this.id);
                    if (producto == Tipo.FIN_A || producto == Tipo.FIN_B) {
                        deposito.agregarProducto(producto, this.id);
                        contarProductoFin(producto);
                        if (finAContados == 2 && finBContados == 2) break;
                    } else {
                        deposito.agregarProducto(producto, this.id);
                    }
                }
                Thread.yield();
            }
            System.out.println("Operador " + this.id + " terminó su trabajo");
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
