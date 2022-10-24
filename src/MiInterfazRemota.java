/*
 * Interfaz donde se declaran los metodos que recibira 
 */
public interface MiInterfazRemota extends java.rmi.Remote {
    public  void miMetodo1(String newmessege, String name) throws java.rmi.RemoteException;
    public void getData(int rowsA, int colsA, int colsB) throws java.rmi.RemoteException;
    public void imprimirMatrices()throws java.rmi.RemoteException;
    public void calcularRMI() throws java.rmi.RemoteException;
    public void imprimirRMI() throws java.rmi.RemoteException;
}