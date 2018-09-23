package socket;

/**
 *
 * @author user
 */
// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Server class
public class SocketServer {

    //Hint #1: ArrayList to store active clients
    public static ArrayList<ClientHandler> ClientList = new ArrayList<>();

    // counter for clients
    static int ClientCount = 0;

    private ServerSocket serversocket;
    private Socket socket;
    private DataInputStream dis;

    //#Hint 2
    private DataOutputStream dos;

    public SocketServer(int port) {
        try {
            serversocket = new ServerSocket(port);

            System.out.println("Server Started");
            // running infinite loop for getting
            // client request
            while (true) {
                // Accept the incoming request
                socket = serversocket.accept();

                System.out.println("New client request received : " + socket);
                System.out.println("Client Number is: " + ++ClientCount);

                // obtain input and output streams
                dis = new DataInputStream(socket.getInputStream());

                dos = new DataOutputStream(socket.getOutputStream()); //Added

                System.out.println("Creating a new handler for this client...");


                //#Hint 1

                // Create a new handler object for handling this request.
                ClientHandler C = new ClientHandler(socket, "client " + ClientCount, dis, dos);

                // add this client to active clients list
                ClientList.add(C);


            }
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void removeConnection(String s) {
        for ( ClientHandler x: ClientList) {
            if (x.name.equals(s)) {
                ClientList.remove(x);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // server is listening on port 1234
        SocketServer S = new SocketServer(1234);
    }
}

// ClientHandler class
class ClientHandler implements Runnable {

    Scanner scn = new Scanner(System.in);
    final String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket socket;
    private Thread T;

    // constructor
    public ClientHandler(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.socket = socket;

        System.out.println("Adding this client to active client list");

        // Create a new Thread with this object.
        T = new Thread(this);

        T.start();
    }

    @Override
    public void run() {


        String received = "";
        String pp="";
        while (!pp.equals("OVER")) {
            try {
                // receive the string
                received = dis.readUTF();


                System.out.println(name + " says: " + received);

                String[] words = received.split(" ");;
                String s = words[0];

                pp = words[0];
                if (words.length>1) {
                    received = words[1];
                    for (int i = 2; i < words.length; i++) {
                        received = received + " " + words[i];
                    }
                    s = "client " + s.substring(1);

                    //System.out.println(received);

                    boolean fond = false;
                    for (ClientHandler x : SocketServer.ClientList) {


                        if (x.name.equals(s)) {
                            if (x.socket.isClosed()) {
                                dos.writeUTF(s + " is closed");
                            }
                            else {
                                x.dos.writeUTF(name + " says: " + received);
                            }

                            fond = true;
                            break;
                        }

                    }
                    if (!fond) {
                        dos.writeUTF(s + " is not available now");
                    }
                } else if(pp.contains("USERS")) {
                    for (ClientHandler xx : SocketServer.ClientList) {
                        if (xx.socket.isConnected()) {
                            dos.writeUTF(xx.name + " is connected!");
                        }
                    }
                }
                /*if (pp.equals("OVER")) {
                    dos.writeUTF("OVER");
                }*/

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try {

            System.out.println("Closing Connection for "+ name);
            // closing resources
            this.dis.close();
            //this.dos.close();
            this.socket.close();
            SocketServer.removeConnection(this.name);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}