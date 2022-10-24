

/*
 * Interfaz que envia un objeto Matriz a la interfaz
 */

public interface MiInterfazRecibir extends java.rmi.Remote {
    public void recibirMatrixA(Matrix mA) throws java.rmi.RemoteException;
    public void recibirMatrixB(Matrix mB) throws java.rmi.RemoteException;
}