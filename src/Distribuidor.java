public class Distribuidor extends Thread {
    private Tipo tipo;
    private Deposito depositoDistri;
    private String id;

    public Distribuidor(String id, Tipo tipo, Deposito depositoDistri){
        this.tipo = tipo;
        this.depositoDistri = depositoDistri;
        this.id = id;
    }

    @Override
    public void run(){
        try {
            while(true){
                Tipo producto = depositoDistri.retirarProducto(tipo, this.id);
                if(tipo == Tipo.A ? producto == Tipo.FIN_A : producto == Tipo.FIN_B){
                    break;
                }
                //Thread.sleep(500);
            }
            System.out.println("Distribuidor " + this.id + " de tipo " + this.tipo + " terminó su trabajo");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
