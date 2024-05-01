import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class IPC_Reader {
	public static void main(String[] args) {
		String filePath = "message.txt";
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line;
			if((line=reader.readLine())!=null) {
				System.out.println("Message in the "+filePath+" is "+line);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
