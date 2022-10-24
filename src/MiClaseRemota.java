/*
 * En esta clase recibe informacion del cliente
 * Infomracion de filas y columnas a generar para la matriz
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ForkJoinPool;

public class MiClaseRemota extends java.rmi.server.UnicastRemoteObject implements

MiInterfazRemota{

    static Matrix a;
    static Matrix b;
    static Matrix c;
    public MiClaseRemota() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws Exception {

    

        try {
            int port = 1234;
            MiInterfazRemota remote = new MiClaseRemota();
            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("Servidor escuchando en el puerto: " + String.valueOf(port) + " Con la IP: " + java.net.InetAddress.getLocalHost().getHostAddress());
            registry.bind("ServerRMI", remote);
            
        } catch (Exception ex) {
            //TODO: handle exception
        }
    }

    @Override
    public void miMetodo1(String newmessege, String name) throws RemoteException {
        System.out.println(newmessege + " "+name);
    }

    @Override
    public void getData(int rowsA, int colsA, int colsB) throws RemoteException {
        // TODO Auto-generated method stub
        //Imprimir datos recibidos
        System.out.println(rowsA+" " + colsA +" " +colsB);
        Matrix d = new Matrix(rowsA, colsA);
         //Generar matriz aleatoria de tamano rowsAxcolsA
         for (int x=0; x < d.getRows(); x++) {
             for (int y=0; y < d.getCols(); y++) {
                 d.setValue(x, y, (int) (Math.random() * 9) + 1);
             }
         }
         /*El numero de columnas de la matriz A tiene que ser igual al numero de filas de la matriz B*/
         Matrix e = new Matrix(d.getCols(), colsB );
         //Generar segunda matriz aleatoria
         for (int x=0; x < e.getRows(); x++) {
             for (int y=0; y < e.getCols(); y++) {
                 e.setValue(x, y, (int) (Math.random() * 9) + 1);
             }
         }
        //Asignacion de la matriz aleatoria e hacia la matriz estatica a
        a = d;
        //Imprimir matrices generadas
        /* 
        System.out.println("\nMatriz A: \n");
        for (int x=0; x < d.getRows(); x++) {
            System.out.print("|");
            for (int y=0; y < d.getCols(); y++) {
              System.out.print (d.getValue(x,y));
              if (y!=d.getCols()-1) System.out.print("\t");
            }
            System.out.println("|");
        }
        System.out.println("\nMatriz B: \n");
        for (int x=0; x < e.getRows(); x++) {
            System.out.print("|");
            for (int y=0; y < e.getCols(); y++) {
              System.out.print (e.getValue(x,y));
              if (y!=e.getCols()-1) System.out.print("\t");
            }
            System.out.println("|");
        }
        */
        //Asignacion de la matriz e a la matriz static b
        b =e;


        try {
            MiInterfazRecibir mir = (MiInterfazRecibir) java.rmi.Naming.lookup("//" + "192.168.137.3" + ":" + "1099" + "/GUIRMI");
            mir.recibirMatrixA(a);
            mir.recibirMatrixB(b);
        } catch (Exception ex) {
            //TODO: handle exception
            ex.printStackTrace();
        }
    }

    @Override
    public void imprimirMatrices() throws RemoteException {
        // TODO Auto-generated method stub
        System.out.println("\nMatriz A: \n");
        for (int x=0; x < a.getRows(); x++) {
            System.out.print("|");
            for (int y=0; y < a.getCols(); y++) {
              System.out.print (a.getValue(x,y));
              if (y!=a.getCols()-1) System.out.print("\t");
            }
            System.out.println("|");
        }
        System.out.println("\nMatriz B: \n");
        for (int x=0; x < b.getRows(); x++) {
            System.out.print("|");
            for (int y=0; y < b.getCols(); y++) {
              System.out.print (b.getValue(x,y));
              if (y!=b.getCols()-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }

    @Override
    public void calcularRMI() throws RemoteException {
        // TODO Auto-generated method stub
        Matrix f = new Matrix(a.getRows(), b.getCols());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new Calc(a,b,f));
        c=f;
    }

    @Override
    public void imprimirRMI() throws RemoteException {
        // TODO Auto-generated method stub
        System.out.println("\nMatriz RMI: \n");
        for (int x=0; x < c.getRows(); x++) {
            System.out.print("|");
            for (int y=0; y < c.getCols(); y++) {
              System.out.print (c.getValue(x,y));
              if (y!=c.getCols()-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }


}