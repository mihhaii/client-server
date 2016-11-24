import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Client {
	
	
	private static int noOfClients;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String username;
	
	public Client() {
		try {
			this.socket = new Socket("localhost", 1500);
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input= new ObjectInputStream(socket.getInputStream());
			this.username = "Client " + ++noOfClients;
			output.writeObject(username);
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(username + "is now connected with the server on port " + this.socket.getPort());
	new ListenFromServerThread().start();
	}
	
	public static void main(String args[]){
		Client client = new Client();
		Scanner scan = new Scanner(System.in);
		while(true) {
			    System.out.print("> ");
			    String msg = scan.nextLine();
		if(msg!=null){
			ChatMessage cm = new ChatMessage(ChatMessage.MESSAGE, msg);
			client.sendMessage(cm);
		
		}
		}
	}
	

	private void sendMessage(ChatMessage cm) {
		try {
			output.writeObject(cm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public class ListenFromServerThread extends Thread{
		
		@Override
		public void run() {
			super.run();
			
			try {
				ChatMessage message = (ChatMessage) input.readObject();
				System.out.println("Server ("+ new Date().toString()+ "): "+message.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			
		}
		
	}
	
}
