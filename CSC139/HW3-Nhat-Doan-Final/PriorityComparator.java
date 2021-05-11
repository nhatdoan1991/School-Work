import java.util.Comparator;

public class PriorityComparator implements Comparator<Processes> {
	@Override
	public int compare(Processes p1, Processes p2) {
		return p1.getPriority() - p2.getPriority();
	}
}
