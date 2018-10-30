package sample;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o1.priority - o2.priority;
    }
}
