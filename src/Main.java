import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        //cinta - operadores - depositos - productores - distribuidores
        Cinta cinta = new Cinta();


        System.out.print("Ingrese la capacidad del Depósito de Producción: ");
        int capDepProd = scanner.nextInt();
        System.out.print("Ingrese la capacidad del Depósito de Distribución: ");
        int capDepDist = scanner.nextInt();

        Deposito depositoProduccion = new Deposito(capDepProd);
        Deposito depositoDistribucion = new Deposito(capDepDist);

        Operador operadorProduccion = new Operador(depositoDistribucion, cinta, true,Tipo.OP);
        Operador operadorDistribucion = new Operador(depositoProduccion, cinta, false,Tipo.OP);

        ArrayList<Integer> numProductosD = new ArrayList<>();
        
        Tipo tipoVal = null;
        for (int i = 0 ; i < 4 ; i++){
            tipoVal = i%2 == 0 ? Tipo.A : Tipo.B;
            System.out.print("Ingrese el número de productos a generar para el productor " + (i+1) + " que es de tipo " + tipoVal + ":" );
            numProductosD.add(scanner.nextInt());
        }

        for (int i = 0 ; i < 4 ; i++){
            tipoVal = i%2 == 0 ? Tipo.A : Tipo.B;
            Productor productor = new Productor(tipoVal, numProductosD.get(i), depositoProduccion);
            productor.start();
        }
        for (int i = 0 ; i < 4 ; i++){
            tipoVal = i%2 == 0 ? Tipo.A : Tipo.B;
            Distribuidor distribuidor = new Distribuidor(tipoVal, depositoDistribucion);
            distribuidor.start();
        }
        operadorProduccion.start();
        operadorDistribucion.start();
        scanner.close();
    }
} 