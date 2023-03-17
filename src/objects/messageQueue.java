package objects;

import linkList.queue;

public class messageQueue extends queue<subMessage>{
	public String getSequence(int sourcePort, int sequence,int flag) {
		qNode<subMessage> currentNode = head;
		int i = 1;
		while(currentNode != null) {
			if(sourcePort == currentNode.getElement().getHeader().getSourcePort() &&
			flag == currentNode.getElement().getHeader().getFlag() &&
			sequence == currentNode.getElement().getHeader().getSequenceNumber()) {
				String content = currentNode.getElement().getBody();
				deleteNode(i);
				return content;
			}
			currentNode = currentNode.getNext();
			i++;
		}
		return null;
	}
	public void showSubMessageQueue(deviceQueue list) {
		qNode<subMessage> currentNode = head;
		while (currentNode != null) {
			System.out.print("\n"+"packet content: "+currentNode.getElement().getBody()
			+"| package number:"+currentNode.getElement().getHeader().getSequenceNumber()+"| total package number:"
			+currentNode.getElement().getHeader().getFlag()+"|sender IP:"+currentNode.getElement().getHeader().getSourcePort()
			+"|sender name:"+list.getDeviceByID(currentNode.getElement().getHeader().getSourcePort()).getDeviceName()
			+"|reciver IP:"+currentNode.getElement().getHeader().getDestinationPort()
			+"|reciver name:"+list.getDeviceByID(currentNode.getElement().getHeader().getDestinationPort()).getDeviceName());
			currentNode = currentNode.getNext();
		}
	}
}
