//class Queue 
public class Queue<T> {
	public LinkedList<T> list = new LinkedList<>();
	public Queue() {}
	public int size() {
		return list.size();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void enqueue(T element) {
		list.addLast(element);
	}
	public T dequeue() {
		return list.removeFirst();
	}
	
	public T first() {
		return list.first();
	}
	
}

