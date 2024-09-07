
public class Productor extends Thread {
    private tipo tipo ;
    private int cantidad;
    private Deposito depositoProduc;

    public Productor(tipo tipo, int cantidad, Deposito depositoProduc){
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.depositoProduc = depositoProduc;
    }
}
