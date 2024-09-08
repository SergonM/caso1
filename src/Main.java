import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        //cinta - operadores - depositos - productores - distribuidores
        Cinta cinta = new Cinta();

        Operador operadorProduccion = new Operador();
        Operador operadorDistribucion = new Operador();

        System.out.print("Ingrese la capacidad del Depósito de Producción: ");
        int capDepProd = scanner.nextInt();
        System.out.print("Ingrese la capacidad del Depósito de Distribución: ");
        int capDepDist = scanner.nextInt();

        Deposito depositoProduccion = new Deposito(capDepProd, operadorProduccion);
        Deposito depositoDistribucion = new Deposito(capDepDist, operadorDistribucion);

        Tipo tipoVal = Tipo.A;
        for (int i = 0 ; i < 4 ; i++){
            tipoVal = i%2 == 0 ? Tipo.A : Tipo.B;
            System.out.print("Ingrese el número de productps a generar para el productor " + (i+1) + " que es de tipo " + tipoVal + ":" );
            int numProductos = scanner.nextInt();
            Productor productor = new Productor(tipoVal, numProductos, depositoProduccion);
            productor.start();
        }
        for (int i = 0 ; i < 4 ; i++){
            tipoVal = i%2 == 0 ? Tipo.A : Tipo.B;
            Distribuidor distribuidor = new Distribuidor(tipoVal, depositoDistribucion);
            distribuidor.start();
        }
        scanner.close();
    }
} 