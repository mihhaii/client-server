import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	

	boolean stop=false;
	private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	
	public Server() {

		try {
			ServerSocket serverSocket = new ServerSocket(1500);
			while(!stop){
				System.out.println("Server is waiting for clients on port 1500");
				Socket socket = serverSocket.accept();
				ClientThread newClient = new ClientThread(socket);
				clients.add(newClient);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private class ClientThread extends Thread{
		
		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private String username;
		
        public ClientThread(Socket socket) {
        	this.socket=socket;
        	try {
				this.output = new ObjectOutputStream(socket.getOutputStream());
				this.input= new ObjectInputStream(socket.getInputStream());
				username = (String) input.readObject();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	System.out.println(username + "just connected to the server!");
        	
        }
		

		@Override
		public void run() {
			super.run();
		}
		
	}
	
	public static void main(String[] args) {
		Server server = new Server();
	}
}
