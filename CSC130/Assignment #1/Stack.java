//class stack
public class Stack<T> {
	private LinkedList<T> list= new LinkedList();
	public Stack() {}
	public int size() {
		return list.size();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public void push(T element) {
		list.addFirst(element);
	}
	public T pop() {
		return list.removeFirst();
	}
	public T top() {
		return list.first();
	}
}
