import java.util.ArrayList;
import java.util.Random;

class Machine{
	int id;
	int clock;
	Machine(int id){
		this.id = id;
		this.clock = new Random().nextInt(100);
	}
	public int getId() {
		return this.id;
	}
	public int getClock() {
		return this.clock;
	}
	public void setClock(int clock) {
		this.clock = clock;
	}
}

public class Berkley {
	ArrayList<Machine> machines;
	
	Berkley(int numMachines){
		machines = new ArrayList<>();
		for(int i=0;i<numMachines;i++) {
			machines.add(new Machine(i));
		}
	}
	
	public void synchronizeClock() {
		int sum = 0;
		for(int i=0;i<machines.size();i++) {
			sum += machines.get(i).getClock();
		}
		int average = sum/machines.size();
		
		for(int i=0;i<machines.size();i++) {
			machines.get(i).setClock(average);
		}
		printClock();
	}
	public void printClock() {
		for(int i=0;i<machines.size();i++) {
			System.out.println("Machine "+i+ " clock is "+machines.get(i).getClock());
		}
	}
	
	public static void main(String[] args) {
		Berkley berkey = new Berkley(5);
		System.out.println("Before Synchronize...");
		berkey.printClock();
		System.out.println();
		System.out.println("After Synchronize...");
		berkey.synchronizeClock();
	}
}
