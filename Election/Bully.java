import java.util.*;

class Process{
	int id;
	boolean coordinator;
	boolean active;
	
	public Process(int id) {
		this.id = id;
		this.coordinator = false;
		this.active = true;
	}
}

public class Bully{
	ArrayList<Process> processes;
	Bully(int numProcesses){
		processes = new ArrayList<>();
		for(int i=0;i<numProcesses;i++) {
			processes.add(new Process(i));
		}
	}
	
	public void startElection(int processId) {
		Process initiator = processes.get(processId);
		
		for(int i=processes.size()-1;i>=processId;i--) {
			Process process = processes.get(i);
			if(process.active) {
				process.coordinator = true;
				initiator.coordinator = false;
				System.out.println("Process "+process.id + " becomes coordinator");
				notifyCordinator(process.id);
				return;
			}
		}
		
		initiator.coordinator = true;
		System.out.println("Process "+initiator.id + " becomes coordinator");
		notifyCordinator(initiator.id);
		return;
	}
	
	public void notifyCordinator(int processId) {
		for(int i=0;i<processId;i++) {
			Process process = processes.get(i);
			if(process.active) {
				System.out.println("Process "+i+" recieves coordinator message");
			}
		}
	}
	public static void main(String[] args) {
		Bully bully = new Bully(5);
		
		bully.startElection(2);
	}
}