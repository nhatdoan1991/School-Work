import java.util.Comparator;

public class SJFComparator implements Comparator<Processes> {
    @Override
    public int compare(Processes p1, Processes p2) {
        return p1.getCpuBurst() - p2.getCpuBurst();
    }
}
