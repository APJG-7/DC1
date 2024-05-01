from threading import Thread, Lock
from time import sleep

class SuzukiKasami:
    def __init__(self, num_processes, process_id):
        self.num_processes = num_processes
        self.process_id = process_id
        self.request_timestamp = 0
        self.token_holder = False
        self.requesting = False
        self.deferred_queue = []
        self.mutex = Lock()

    def request_cs(self):
        self.mutex.acquire()
        self.requesting = True
        self.request_timestamp += 1
        print(f"Process {self.process_id} requesting critical section at timestamp {self.request_timestamp}")
        self.mutex.release()

        for i in range(self.num_processes):
            if i != self.process_id:
                self.send_request(i, self.request_timestamp)

        while not self.token_holder:
            sleep(1)

        self.enter_cs()

    def enter_cs(self):
        print(f"Process {self.process_id} entering critical section")
        sleep(2)
        print(f"Process {self.process_id} exiting critical section")
        self.mutex.acquire()
        self.requesting = False
        self.mutex.release()

    def send_request(self, receiver_id, timestamp):
        sleep(0.5)
        print(f"Process {self.process_id} sending request to process {receiver_id}")
        self.deferred_queue.append((receiver_id, timestamp))

    def receive_request(self, sender_id, timestamp):
        self.mutex.acquire()
        print(f"Process {self.process_id} received request from process {sender_id} at timestamp {timestamp}")
        if not self.requesting or (timestamp, sender_id) < (self.request_timestamp, self.process_id):
            self.send_token(sender_id)
        else:
            self.deferred_queue.append((sender_id, timestamp))
        self.mutex.release()

    def send_token(self, receiver_id):
        print(f"Process {self.process_id} sending token to process {receiver_id}")
        self.token_holder = False
        sleep(1)
        self.token_holder = True

    def receive_token(self):
        self.token_holder = True
        print(f"Process {self.process_id} received token")
        for request in self.deferred_queue:
            self.send_token(request[0])
        self.deferred_queue = []

def simulate(process_id):
    sk = SuzukiKasami(num_processes=5, process_id=process_id)
    sk.request_cs()

if __name__ == "__main__":
    processes = []
    for i in range(5):
        process = Thread(target=simulate, args=(i,))
        processes.append(process)
        process.start()

    for process in processes:
        process.join()



#         Suzuki–Kasami algorithm is a token-based algorithm for achieving mutual exclusion in distributed systems.This is modification of Ricart–Agrawala algorithm, a permission based (Non-token based) algorithm which uses REQUEST and REPLY messages to ensure mutual exclusion. 

# In token-based algorithms, A site is allowed to enter its critical section if it possesses the unique token. Non-token based algorithms uses timestamp to order requests for the critical section where as sequence number is used in token based algorithms. 

# Each requests for critical section contains a sequence number. This sequence number is used to distinguish old and current requests. 

# Drawbacks of Suzuki–Kasami Algorithm: 
 

# Non-symmetric Algorithm: A site retains the token even if it does not have requested for critical section. According to definition of symmetric algorithm 
# “No site possesses the right to access its critical section when it has not been requested.”
# Performance: 
 

# Synchronization delay is 0 and no message is needed if the site holds the idle token at the time of its request.
# In case site does not holds the idle token, the maximum synchronization delay is equal to maximum message transmission time and a maximum of N message is required per critical section invocation.
