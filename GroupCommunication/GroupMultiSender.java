import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupMultiSender {
    final static int PORT = 4446;
    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName("230.0.0.0");
        try (MulticastSocket socket = new MulticastSocket()) {
            String message = "Hello! From Sender....";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
            socket.send(packet);
            System.out.println("Message sent " + message);
        }


    }
}