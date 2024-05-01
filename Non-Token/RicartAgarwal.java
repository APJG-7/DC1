import java.util.ArrayList;
import java.util.Scanner;

class Message {
    int siteId;
    String messageType;
    int timeStamp;

    Message(int siteId, String messageType, int timeStamp) {
        this.siteId = siteId;
        this.messageType = messageType;
        this.timeStamp = timeStamp;
    }
}

class Site {
    int siteId;
    boolean requesting;
    boolean executing;
    int timeStamp;
    ArrayList<Message> deferredQueue;

    Site(int siteId) {
        this.siteId = siteId;
        this.requesting = false;
        this.executing = false;
        this.timeStamp = 0;
        this.deferredQueue = new ArrayList<>();
    }

    public void requestCriticalSection(ArrayList<Site> sites) {
        this.requesting = true;
        this.timeStamp++;
        for (Site site : sites) {
            if (site.siteId != this.siteId) {
                Message requestMessage = new Message(this.siteId, "REQUEST", this.timeStamp);
                sendMessage(site, requestMessage);
            }
        }
        waitForReplies(sites);
    }

    void waitForReplies(ArrayList<Site> sites) {
        int repliesExpected = sites.size() - 1;
        int repliesReceived = 0;
        while (repliesReceived < repliesExpected) {
            // Wait for replies
        }
    }

    public void sendMessage(Site destination, Message requestMessage) {
        System.out.println(
                requestMessage.messageType + " message is sent by " + this.siteId + " to " + destination.siteId);
        destination.receiveMessage(requestMessage, this);
    }

    public void receiveMessage(Message requestMessage, Site sender) {
        System.out
                .println("Site " + this.siteId + " receives " + requestMessage.messageType + " from " + sender.siteId);
        if (requestMessage.messageType.equals("REQUEST")) {
            if (!this.requesting && !this.executing) {
                this.sendMessage(sender, new Message(this.siteId, "REPLY", 0));
            } else if (this.requesting && requestMessage.timeStamp < this.timeStamp) {
                this.deferredQueue.add(requestMessage);
            }
        } else if (requestMessage.messageType.equals("REPLY")) {
            if (this.requesting) {
                this.deferredQueue.removeIf(m -> m.siteId == sender.siteId);
                if (this.deferredQueue.isEmpty()) {
                    this.executing = true;
                    System.out.println("Site " + this.siteId + " enters into critical section");
                }
            }
        }
    }

    void releaseCriticalSection(ArrayList<Site> sites) {
        this.requesting = false;
        this.executing = false;
        for (Site site : sites) {
            if (site.siteId != this.siteId) {
                for (Message message : this.deferredQueue) {
                    this.sendMessage(site, (new Message(this.siteId, "REPLY", 0)));
                }
            }
        }
        this.deferredQueue.clear();
        System.out.println("Site " + this.siteId + " releases critical section.");
    }
}
public class RicartAgarwal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Site> sites = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sites.add(new Site(i + 1));
        }

        for (Site site : sites) {
            site.requestCriticalSection(sites);
            site.releaseCriticalSection(sites);
        }
    }
}
