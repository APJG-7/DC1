package RMI;

import java.rmi.Naming;

public class RMI_Client {
	public static void main(String[] args) {
		try {
		RMI_Interface helloAPI = (RMI_Interface) Naming.lookup("rmi://localhost:1903/hello1");
		int n = 5;
		int ans = helloAPI.factorial(n);
		System.out.println("Factorial of "+n+" is "+ans);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("RMI_Client is not running...");
		}
	}
}
