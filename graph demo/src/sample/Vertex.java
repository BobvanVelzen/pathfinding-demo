package sample;

public class Vertex {
    public String label;
    public boolean visited;
    public int priority;

    public Vertex(String l) {
        label = l;
        visited = false;
        priority = Integer.MAX_VALUE;
    }

    public void reset() {
        visited = false;
        priority = Integer.MAX_VALUE;
    }
}
