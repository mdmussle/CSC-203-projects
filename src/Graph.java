/*
Author: Michael Mussler 
11/15/2016
*/
public class Graph {

    private Node[] vertices;
    private int size;
    private MinPriorityQueue k;

    public Graph(int size) {
        this.size = size;
        vertices = new Node[size];
        addNodes();
        k = new MinPriorityQueue(size);
    }

    public class connect {

        int index;
        int weight;
        connect next;

        public connect(int index, connect next, int weight) {
            this.index = index;
            this.next = next;
            this.weight = weight;
        }
    }

    private void addNodes() {
        for (int i = 1; i <= size; i++) {
            addNode(i);
        }
    }

    public void addNode(int name) {
        vertices[name - 1] = new Node(name);
    }

    public void addEdge(int sourceName, int destiName, int weight) {
        int srcIndex = sourceName - 1;
        int destiIndex = destiName - 1;
        Node srcNode = vertices[srcIndex];
        Node destiNode = vertices[destiIndex];
        srcNode.List = new connect(destiIndex, srcNode.List, weight);
        destiNode.List = new connect(srcIndex, destiNode.List, weight);
    }

    public void FindDijkstraAlgorithm(int NodeName) {
        for (int i = 0; i < size; i++) {
            if (vertices[i].name == NodeName) {
                DijkstraAlgorithm(vertices[i]);
                break;
            }
        }
    }

    private void DijkstraAlgorithm(Node sourceNode) {
        k.add(sourceNode);
        sourceNode.state = State.Q;
        sourceNode.cost = 0;
        while (!k.isEmpty()) {
            Node visitedNode = k.remove();
            visitedNode.state = State.VISITED;
            connect connectedEdge = visitedNode.List;
            while (connectedEdge != null) {
                Node neighbour = vertices[connectedEdge.index];
                if (neighbour.state == State.NEW) {
                    k.add(neighbour);
                    neighbour.state = State.Q;
                }

                if (neighbour.state != State.VISITED && ((connectedEdge.weight + visitedNode.cost) < neighbour.cost)) {
                    neighbour.cost = connectedEdge.weight + visitedNode.cost;
                }
                connectedEdge = connectedEdge.next;
            }
        }

        int charVal = 65;
        for (int i = 0; i < size; i++) {
            System.out.println(((char) charVal) + " \t\t " + +vertices[i].cost);
            charVal++;
        }
    }

    public enum State {

        NEW, Q, VISITED
    };

    public class MinPriorityQueue {

        Node[] q;
        int maxSize;
        int back = -1, front = -1;

        public MinPriorityQueue(int maxSize) {
            this.maxSize = maxSize;
            q = new Node[maxSize];
        }

        public void add(Node node) {
            q[++back] = node;
        }
//remove node

        public Node remove() {
            Node minValuedNode = null;
            int minValue = Integer.MAX_VALUE;
            int minValueIndex = -1;
            front++;
            for (int i = front; i <= back; i++) {
                if (q[i].state == State.Q && q[i].cost < minValue) {
                    minValue = q[i].cost;
                    minValuedNode = q[i];
                    minValueIndex = i;
                }
            }

            swap(front, minValueIndex);
            q[front] = null;
            return minValuedNode;
        }
//swap with minimum weight 
        public void swap(int i, int j) {
            Node temp = q[i];
            q[i] = q[j];
            q[j] = temp;
        }

        public boolean isEmpty() {
            return front == back;
        }
    }

    public static void main(String[] args) {
        //create graph with list 
        Graph graph = new Graph(9);
        graph.addEdge(1, 2, 4);  //A to B
        graph.addEdge(1, 8, 8);  //A to H
        graph.addEdge(2, 3, 8);  //B to C
        graph.addEdge(2, 8, 11); //B to H
        graph.addEdge(3, 9, 2);  //C to I
        graph.addEdge(3, 6, 4);  //C to F
        graph.addEdge(3, 4, 7);  //C to D
        graph.addEdge(4, 5, 9);  //D to E
        graph.addEdge(4, 6, 14); //D to F
        graph.addEdge(5, 6, 10); //E to F
        graph.addEdge(6, 7, 2);  //F to G
        graph.addEdge(7, 9, 6);  //G to I
        graph.addEdge(7, 8, 1);  //G to H
        graph.addEdge(8, 9, 7);  //H to I
        System.out.println("Vertex    Distance from source vertex");
        graph.FindDijkstraAlgorithm(1); //Start from A
    }

//create node class
    public class Node {

        int name;
        int cost;
        connect List;
        State state;

        Node(int name) {
            this.name = name;
            state = State.NEW;
            cost = Integer.MAX_VALUE;
        }
    }
}
