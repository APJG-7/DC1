import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IPC_Writer {
	public static void main(String[] args) {
		String filePath = "message.txt";
		String message = "Hello! I'm Akash...";
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
			writer.write(message);
			System.out.println("Message is written to :"+filePath);
		}catch(IOException e) {
            e.printStackTrace();
		}
	}
}
