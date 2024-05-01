package LoadBalancer;
import java.util.ArrayList;

public class RoundRobinBalancer {
	int numServers;
	ArrayList<Integer>[] servers;
	RoundRobinBalancer(int numservers){
		this.numServers = numservers;
		servers = new ArrayList[numservers];
		for(int i=0;i<servers.length;i++) {
			servers[i] = new ArrayList<>();
		}
	}
	
	public void addProcess(int[] Processes) {
		int currentIndex = 0;
		for(int i=0;i<Processes.length;i++) {
			servers[currentIndex].add(Processes[i]);
			currentIndex = (currentIndex + 1) % numServers;
		}
	}
	
	public void printProcesses(ArrayList<Integer>[] servers) {
		for(int i=0;i<servers.length;i++) {
			System.out.println("Server "+(i+1)+" has processes "+servers[i]);
		}
	}
	public static void main(String[] args) {
		int[] processes = {1,2,3,4,5,6,7,8,9};
		RoundRobinBalancer balancer = new RoundRobinBalancer(5);
		System.out.println("Before Balancing...");
		for(int i=0;i<processes.length;i++) {
			System.out.print(processes[i]+" ");
		}
		System.out.println();
		balancer.addProcess(processes);
		System.out.println("After Balancing...");
		balancer.printProcesses(balancer.servers);
	}
	
}