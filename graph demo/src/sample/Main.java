package sample;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph();

        List<Vertex> pathBFS = graph.bfs("start", "goal");
        if (pathBFS != null) {
            for (Vertex v : pathBFS) {
                System.out.println(v.label);
            }
        }

        List<Vertex> pathDijkstra = graph.dijkstra("start", "goal");
        if (pathDijkstra != null) {
            for (Vertex v : pathDijkstra) {
                System.out.println(v.label);
            }
        }
    }
}
