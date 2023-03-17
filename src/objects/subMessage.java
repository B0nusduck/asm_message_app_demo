package objects;

public class subMessage {
	String body;
	tcpHeader header;
	
	//set
	public subMessage(String body){
		this.body = body;
		header = null;
	}
	public subMessage(String body, tcpHeader header) {
		this.body = body;
		this.header = header;
	}
	public void removeHeader() {
		header = null;
	}
	//get
	public String getBody() {
		return body;
	}
	public tcpHeader getHeader() {
		return header;
	}
	
	//to sting
	@Override
	public String toString() {
		return body+" "+header;
	}
}
