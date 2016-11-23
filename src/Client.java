import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
	}
	
	public static void main(String args[]){
		Client client = new Client();
	}
	
	
}
