// VisitorComparator.java
import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor visitor1, Visitor visitor2) {
        // Compare by name first
        int nameCompare = visitor1.getName().compareTo(visitor2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }

        // If names are equal, compare by age (assuming age is a field in Visitor)
        // If age is not a field, you can choose another field to compare
        return Integer.compare(visitor1.getAge(), visitor2.getAge());
    }
}
