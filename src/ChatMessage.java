import java.io.Serializable;

public class ChatMessage implements Serializable{


	private static final long serialVersionUID = -50181201681235828L;
	
	static final int WHOISIN=0, MESSAGE=1, LOGOUT=2;
	private int type;
	private String message;
	
	public ChatMessage(int type,String message){
		this.type= type;
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
