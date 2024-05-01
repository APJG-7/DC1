package RMI;

import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Server extends UnicastRemoteObject implements RMI_Interface  {
	public RMI_Server() throws RemoteException {
		super();
	}
	public static void main(String[] args) throws RemoteException,AlreadyBoundException {
		try {
			Registry registry = LocateRegistry.createRegistry(1903);
			registry.bind("hello1", new RMI_Server());
			System.out.println("Server has started and running...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int factorial(int n) throws RemoteException{
		int ans = 1;
		for(int i=1;i<=n;i++) {
			ans = ans * i;
		}
		return ans;
	}
}
