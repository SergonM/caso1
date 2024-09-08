
public class Productor extends Thread {
    private Tipo tipo ;
    private int cantidad;
    private Deposito depositoProduc;

    public Productor(Tipo tipo, int cantidad, Deposito depositoProduc){
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.depositoProduc = depositoProduc;
    }

    @Override
    public void run(){
        try {
            for (int i = 0; i < cantidad; i++) {
                depositoProduc.agregarProducto(tipo);
            }
            depositoProduc.agregarProducto(tipo == Tipo.A ? Tipo.FIN_A: Tipo.FIN_B);
            System.out.println("Productor de tipo " + tipo + " terminó su trabajo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


