package objects;
import linkList.*;

public class deviceQueue extends queue<device>{
	public device getDeviceByID(int ID) {
		qNode<device> currentNode;
		currentNode = head;
		while(currentNode!=null) {
			if(currentNode.getElement().getDeviceID() == ID) {
				return currentNode.getElement();
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}
	
	public device getDeviceByName(String deviceName) {
		qNode<device> currentNode;
		currentNode = head;
		while(currentNode!=null) {
			if(currentNode.getElement().getDeviceName() == deviceName) {
				return currentNode.getElement();
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}
	
	public void sentMSG(String device) {
		try {
			int size = getDeviceByName(device).getSentLength();
			for(int i = 0; i < size; i++) {
			subMessage message = getDeviceByName(device).getSentMessage();
			getDeviceByID(message.header.getDestinationPort()).setReviceMessage(message);
			getDeviceByName(device).removeFromSent();
			}
		}
		catch(Exception e) {
			System.out.print("\n\n\nerror: "+device+" has invalid input, use showDeviceQueue() function to get and input a valid ID");
		}
	}
	
	public void showDeviceQueue() {
		qNode<device> currentNode = head;
		while(currentNode != null) {
			System.out.println("device name:"+currentNode.getElement().getDeviceName()
			+" | device ID:"+currentNode.getElement().getDeviceID());
			currentNode = currentNode.getNext();
		}
	}
}
