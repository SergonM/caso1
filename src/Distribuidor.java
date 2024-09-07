public class Distribuidor extends Thread {
    private tipo tipo;
    private Deposito depositoDistri;

    public Distribuidor(tipo tipo, Deposito depositoDistri){
        this.tipo = tipo;
        this.depositoDistri = depositoDistri;
    }
}
