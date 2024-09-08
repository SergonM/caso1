public class Distribuidor extends Thread {
    private Tipo tipo;
    private Deposito depositoDistri;

    public Distribuidor(Tipo tipo, Deposito depositoDistri){
        this.tipo = tipo;
        this.depositoDistri = depositoDistri;
    }

    @Override
    public void run(){
        try {
            while(true){
                Tipo producto = depositoDistri.retirarProducto(tipo);
                if(tipo == Tipo.A ? producto == Tipo.FIN_A : producto == Tipo.FIN_B){
                    break;
                }
                Thread.sleep(500);
            }
            System.out.println("Distribuidor de tipo " + tipo + " terminó su trabajo");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
