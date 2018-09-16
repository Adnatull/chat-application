package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ui.ChatFrame;

public class ClientSocket implements Runnable{
	
	ChatFrame ui;
	String server = "127.0.0.1";
	int port = 1234;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	Thread T;
	
	public ClientSocket(ChatFrame Frame) throws IOException, Exception {
		this.ui = Frame;
		this.socket = new Socket(this.server, this.port);
		this.in = new DataInputStream(socket.getInputStream());
		this.out = new DataOutputStream(socket.getOutputStream());
		T=new Thread(this);
        T.start();
	}

	@Override
	public void run() {
		String msg = "";
		// TODO Auto-generated method stub
		while (!msg.equals("OVER")) {
			try {
				msg = in.readUTF();
				System.out.println(msg);
				ui.textArea.append(msg  + "\n");
			} catch (IOException e) {
				String s = e.getMessage();
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ui.textArea.append(s + "\n");
			}
		}
		
	}

	public void send(String msg) {
		// TODO Auto-generated method stub
		try {
			
			System.out.println(msg);
			ui.textArea.append(msg  + "\n");
			out.writeUTF(msg);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String s = e.getMessage();
			ui.textArea.append(s  + "\n");
		}
		
	}

}
