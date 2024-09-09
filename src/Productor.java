
public class Productor extends Thread {
    private Tipo tipo ;
    private int cantidad;
    private Deposito depositoProduc;
    private String id;

    public Productor(String id, Tipo tipo, int cantidad, Deposito depositoProduc){
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.depositoProduc = depositoProduc;
        this.id = id;
    }

    @Override
    public void run(){
        try {
            for (int i = 0; i < cantidad-1; i++) {
                depositoProduc.agregarProducto(tipo, this.id);
                //Thread.sleep(500);
            }
            depositoProduc.agregarProducto(tipo == Tipo.A ? Tipo.FIN_A: Tipo.FIN_B, this.id);
            System.out.println("Productor de tipo " + tipo + " con id: " + this.id + ", terminó su trabajo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


