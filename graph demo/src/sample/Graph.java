package sample;

import java.util.*;

public class Graph {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;

    public Graph() {
        vertexList = new ArrayList<Vertex>();
        edgeList = new ArrayList<Edge>();

        createTestData();
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public int getVertexIndex(String label) {
        for (int i = 0; i < vertexList.size(); i++)
            if (vertexList.get(i).label.equals(label))
                return i;
        return -1;
    }
    public int getVertexIndex(Vertex v) {
        for (int i = 0; i < vertexList.size(); i++)
            if (vertexList.get(i).label.equals(v.label))
                return i;
        return -1;
    }

    public int getCost(int v1, int v2) {
        for (Edge e : edgeList)
            if (e.v1 == v1 && e.v2 == v2)
                return e.cost;
        return Integer.MAX_VALUE;
    }

    public int getUnvisitedNeighbour(int v) {
        for (Edge e : edgeList)
            if (e.v1 == v && !vertexList.get(e.v2).visited)
                return e.v2;
        return -1;
    }

    public void addEdge(int i1, int i2, int c) {
        edgeList.add(new Edge(i1, i2, c));
        edgeList.add(new Edge(i2, i1, c));
    }

    public List<Vertex> bfs(String v1, String v2) {
        int start = getVertexIndex(v1);
        int goal = getVertexIndex(v2);

        if (start == -1 || goal == -1)
            return null;

        resetVisitedStatus();
        return bfs(start, goal);
    }

    private List<Vertex> bfs(int start, int goal) {
        HashMap<Integer, Integer> cameFrom = new HashMap<Integer, Integer>();

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        vertexList.get(start).visited = true;
        int nextVertex = -1;

        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            if (vertex == goal)
                return getPath(start, vertex, cameFrom);

            while((nextVertex = getUnvisitedNeighbour(vertex)) != -1) {
                vertexList.get(nextVertex).visited = true;
                cameFrom.put(nextVertex, vertex);
                queue.add(nextVertex);
            }
        }

        return null;
    }

    public List<Vertex> dijkstra(String v1, String v2){
        int start = getVertexIndex(v1);
        int goal = getVertexIndex(v2);

        if (start == -1 || goal == -1)
            return null;

        resetVisitedStatus();
        return dijkstra(start, goal);
    }

    private List<Vertex> dijkstra(int start, int goal) {
        HashMap<Integer, Integer> cameFrom = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> costSoFar = new HashMap<Integer, Integer>();
        costSoFar.put(start, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(vertexList.size(), new PriorityComparator());
        queue.add(vertexList.get(start));
        vertexList.get(start).visited = true;
        int nextVertex = -1;

        while(!queue.isEmpty()) {
            int vertex = getVertexIndex(queue.remove());
            if (vertex == goal)
                return getPath(start, vertex, cameFrom);

            while((nextVertex = getUnvisitedNeighbour(vertex)) != -1) {
                int newCost = getCost(vertex, nextVertex);
                if (costSoFar.containsKey(vertex))
                    newCost += costSoFar.get(vertex);

                if (!costSoFar.containsKey(nextVertex) || newCost < costSoFar.get(nextVertex)) {
                    vertexList.get(nextVertex).visited = true;

                    queue.add(vertexList.get(nextVertex));
                    costSoFar.put(nextVertex, newCost);
                    cameFrom.put(nextVertex, vertex);
                }
            }
        }

        return null;
    }

    private List<Vertex> getPath(int start, int current, HashMap<Integer, Integer> cameFrom) {
        List<Vertex> path = new ArrayList<Vertex>();

        while (current != start) {
            path.add(0, vertexList.get(current));
            current = cameFrom.get(current);
        }
        path.add(0, vertexList.get(start));

        return path;
    }

    private void resetVisitedStatus() {
        for (Vertex v : vertexList)
            v.reset();
    }

    private void createTestData() {
        addVertex("start");
        addVertex("a");
        addVertex("b");
        addVertex("c");
        addVertex("d");
        addVertex("e");
        addVertex("f");
        addVertex("g");
        addVertex("goal");

        addEdge(0,1,2);
        addEdge(0,2,6);
        addEdge(0,3,3);
        addEdge(1,2,3);
        addEdge(1,4,1);
        addEdge(2,6,4);
        addEdge(3,6,5);
        addEdge(4,5,7);
        addEdge(4,7,2);
        addEdge(5,6,2);
        addEdge(5,8,3);
        addEdge(6,8,1);
        addEdge(7,8,5);
    }
}
