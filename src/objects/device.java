package objects;

public class device {
	String deviceName;
	int deviceID;
	messageQueue queueSent = new messageQueue();
	messageQueue queueRecive = new messageQueue();
	
	//set
	public device() {
		deviceName = null;
		deviceID = 0;
	}
	
	public device(String deviceName) {
		this.deviceName = deviceName;
		deviceID = 0;
	}
	
	public device(int deviceID) {
		deviceName = null;
		this.deviceID = deviceID;
	}
	
	public device(String deviceName, int deviceID) {
		this.deviceName = deviceName;
		this.deviceID = deviceID;
	}
	
	public void setReviceMessage(subMessage message) {
		this.queueRecive.offer(message);
	}
	
	public void setSentMessage(String message,int destination) {
		this.queueSent = segmentingMSG(message,destination);
	}
	
	//get
	public String getDeviceName() {
		return deviceName;
	}
	
	public int getDeviceID() {
		return deviceID;
	}
	
	public void getQueueSent(deviceQueue list) {
		System.out.print("\n--message that "+this.deviceName+" are about to sent--\n");
		this.queueSent.showSubMessageQueue(list);
	}
	
	public void getQueueRecive(deviceQueue list) {
		System.out.print("\n--message that "+this.deviceName+" have recive form other device--\n");
		this.queueRecive.showSubMessageQueue(list);
	}
	
	public subMessage getSentMessage() {
		return this.queueSent.peek();
	}
	
	public int getSentLength() {
		return this.queueSent.size();
	}
	
	public int getReciveLength() {
		return this.queueRecive.size();
	}
	
	//remove from queue
	public void removeFromSent() {
		this.queueSent.poll();
	}
	
	//to string
	@Override
	public String toString() {
		return deviceName+" "+deviceID;
	}
	
	//message interaction
	public String assembleMSG(deviceQueue list) {
		try {
			tcpHeader header = this.queueRecive.peek().getHeader();
			if(this.queueRecive.peek().getHeader().getFlag() != 1) {
				String message = this.queueRecive.peek().getBody();
				int flag = this.queueRecive.peek().getHeader().getFlag();
				this.queueRecive.poll();
				for(int i = 2; i <= flag;i++) {
					message = message+this.queueRecive.getSequence(header.getSourcePort(),i,flag);
				}
				return this.deviceName+"'s message :"+message+"|total number of packages: "
				+header.getFlag()+"| sender IP: "+header.getSourcePort()+"|sender name:"
				+list.getDeviceByID(header.getSourcePort()).getDeviceName();
			}
			else {
				return this.deviceName+"'s message :"+this.queueRecive.peek().getBody()+"|total number of packages: "
						+this.queueRecive.peek().getHeader().getFlag()+"| sender IP: "+
						this.queueRecive.peek().getHeader().getSourcePort()+"|sender name:"
						+list.getDeviceByID(header.getSourcePort()).getDeviceName();
			}
		}
		catch(Exception e) {
			return "there are currently no message sent to the device";
		}
	}
	
	/*public messageQueue segmentingMSG(String message, int destination) {
		String[] subMessage = message.split(" ");
		int x = subMessage.length;
		messageQueue queue = new messageQueue();
		for(int i = 0; i<x;i++) {
			queue.offer(new subMessage (subMessage[i],new tcpHeader(this.deviceID,destination,i+1,x)));
		}
		return queue;
	}*/
	public messageQueue segmentingMSG(String message, int destination) {
	    messageQueue queue = new messageQueue();
	    if (message.length() <= 5) {
	        queue.offer(new subMessage(message, new tcpHeader(this.deviceID, destination, 1, 1)));
	    } else {
	        int numSegments = (int) Math.ceil((double) message.length() / 5);
	        for (int i = 0; i < numSegments; i++) {
	            int startIndex = i * 5;
	            int endIndex = Math.min(startIndex + 5, message.length());
	            String segment = message.substring(startIndex, endIndex);
	            queue.offer(new subMessage(segment, new tcpHeader(this.deviceID, destination, i + 1, numSegments)));
	        }
	    }
	    return queue;
	}
}
