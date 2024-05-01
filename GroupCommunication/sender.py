import socket
import time

def sender(group_ip, port):
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, 2)
    message = "Hello from sender!"
    while True:
        s.sendto(message.encode(), (group_ip, port))
        print("Message sent to group")
        time.sleep(1)

if __name__ == "__main__":
    group_ip = '224.0.0.1'  # Multicast group IP address
    port = 5002           # Port number
    sender(group_ip, port)



