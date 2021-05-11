//clasee linkedlist
public class LinkedList<T> {
	//Implement a Node
	private static class Node<T>{
		private T element;
		private Node<T> next;
		public Node(T e, Node<T> n) {
			element = e;
			next = n;
		}
		public T getElement() {
			return element;
		}
		public Node<T> getNext(){
			return next;
		}
		public void setNext(Node<T> n) {
			next = n;
		}
	}
	
	// List Implementation
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;
	public LinkedList() {};
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public T first() {
		if(isEmpty()) {
			return null;
		}
		return head.getElement();
	}
	public T last() {
		if(isEmpty()) {
			return null;
		}
		return tail.getElement();
	}
	public void addFirst(T e) {
		head = new Node<>(e, head);
		if(size==0) {
			tail=head;
		}
		size++;
		System.out.println("Added head node with '"+head.getElement()+ "' element. ");
	}
	public void addLast(T e) {
		Node<T> newNode= new Node<>(e, null);
		if(isEmpty())
		{
			head= newNode;
		}else {
			tail.setNext(newNode);
		}
		tail=newNode;
		size++;
		System.out.println("Added head node with '"+tail.getElement()+ "' element. ");
	}
	public T removeFirst() {
		if(isEmpty())
		{
			return null;
		}
		T answer = head.getElement();
		head= head.getNext();
		size--;
		if(size==0)
			tail=null;
		System.out.println("Removed head node with '"+ answer+ "' element. ");
		return answer;
	}
}
