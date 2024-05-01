import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupMultiReceiver {
    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName("230.0.0.0");
        try (MulticastSocket socket = new MulticastSocket(4446)) {
            socket.joinGroup(group);
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received Packet :"+message);
            }
        }
    }
}