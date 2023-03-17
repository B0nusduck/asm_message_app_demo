package objects;

public class tcpHeader {
	int sourcePort, destinationPort, sequenceNumber, flag;
	
	//set
	public tcpHeader() {
		sourcePort = 0;
		destinationPort = 0;
		sequenceNumber = 0;
		flag = 0;
	}
	public tcpHeader(int sourcePort, int destinationPort, int sequenceNumber, int flag) {
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
		this.sequenceNumber = sequenceNumber;
		this.flag = flag;
	}
	public void setsource(int sourcePort) {
		this.sourcePort = sourcePort;
	}
	//get
	public int getSourcePort() {
		return sourcePort;
	}
	public int getDestinationPort() {
		return destinationPort;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public int getFlag() {
		return flag;
	}
	//to sting
	public String toString() {
		return sourcePort+" "+destinationPort+" "+sequenceNumber+" "+flag;
	}
}
