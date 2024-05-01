import socket
import struct

def receiver(group_ip, port):
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind(('', port))
    group = socket.inet_aton(group_ip)
    mreq = struct.pack('4sL', group, socket.INADDR_ANY)
    s.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)
    while True:
        message, address = s.recvfrom(5002)
        print(f"Received message: {message.decode()}")

if __name__ == "__main__":
    group_ip = '224.0.0.1'  # Multicast group IP address
    port = 5000            # Changed port number
    receiver(group_ip, port)
