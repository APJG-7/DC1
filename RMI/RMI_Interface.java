package RMI;
import java.rmi.*;

public interface RMI_Interface extends Remote {
	public int factorial(int n) throws RemoteException;
}
