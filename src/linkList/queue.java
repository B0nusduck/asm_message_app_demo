package linkList;

import objects.deviceQueue;
import objects.subMessage;

public class queue<E> {
	public static class qNode<E>{
		private E element;
		private qNode<E> next;
		
		protected qNode(E element) {
			this.element = element;
		}
		
		public E getElement() {
			return element;
		}
		
		public qNode<E> getNext(){
			return next;
		}
		
		public qNode<E> setNext(qNode<E> next){
			return this.next = next;
		}
	}
	
	public qNode<E> head;
	public int size;
	
	public queue() {
		head = null;
		size = 0;
	}
	
	//add element to queue
	public void offer(E element) {
		qNode<E> newNode = new qNode<E>(element);
		if(this.head==null) {
			this.head=newNode;
		}
		else {
			qNode<E> current = this.head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
		size++;
	}
	
	//show queue
	public void showQueue() {
		qNode<E> currentNode;
		System.out.print("\n---Queue list---");
		currentNode = this.head;
		while (currentNode != null) {
			System.out.print("\n"+currentNode.element);
			currentNode = currentNode.next;
		}
	}
	
	//remove the first element
	public void poll() {
		if(isEmpty()!=true) {
			head = head.next;
			size--;
		}
	}
	
	//remove selected element
	public void deleteNode(int serial)
    {
        qNode<E> currentNode = head, prev = null;
 
        if (currentNode != null && serial == 1) {
            head = currentNode.next;
            size--;
            return;
        }
        
        int i = 1;
        while (currentNode != null && i != serial) {
            prev = currentNode;
            currentNode = currentNode.next;
            i++;
        }
 
        if (currentNode == null)
            return;

        prev.next = currentNode.next;
        size--;
    }
	
	//view the first element
	public E peek() {
	if(head!=null)
		return head.element;
	else
		return null;
	}
	
	//queue size
	public int size() {
		return this.size;
	}
	
	//check empty queue
	public boolean isEmpty() {
		return (this.size <=0);
	}
	
}