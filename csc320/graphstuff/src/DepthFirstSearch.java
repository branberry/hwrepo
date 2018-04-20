/**
 * The implementation of Depth First Search.
 * The depth first search takes into the constructor the number of vertices in the graph and an array is then initialized to hold the vertex objects.
 * The start time is initialized to zero as well in the constructor.
 * @author Brandon Ly
 * CSC 320A
 */

public class DepthFirstSearch {

    //////////////////////////////////////
    // Instance Variables for DFS class //
    //////////////////////////////////////

    /** The time during the depth first search*/
    public int time;

    /** The array of Vertex objects within the graph */
    public Vertex[] vertices;

    /** The counter is to keep track of operations done by DFS; is called 16 times in DFS */
    public int counter;

    //////////////////////////////////////////
    //              Enumerations            //
    //////////////////////////////////////////

    /**
     * Enumerations are used to represent the vertex's colors
     * Each vertex can only have white, grey, or black for the color
     */
    public enum Color {
        WHITE, GREY, BLACK
    }

    //////////////////////////////////////////
    //               Vertex Class           //
    //////////////////////////////////////////

    /**
     * The representation of the Vertex.
     * The variable names are the exact same as the representation in the book.
     * The vertex values are zero based.
     */
    public class Vertex {

        //////////////////////////////////////////
        // Instance Variables  for Vertex class //
        //////////////////////////////////////////

        /** The vertex's color  */
        public Color color;

        /** The vertex's parent pointer */
        public Vertex pi;

        /** The vertex's number.  This is used to identify a vertex  */
        public int vertexNum;

        /** The vertex's start time.  Represents the first time a vertex is explored  */
        public int startTime;

        /** The finish time of the vertex.  Represents that the vertex's path has been fully explored  */
        public int finishTime;

        /**
         *  Creates a vertex with a given vertex number, parent vertex, and color
         * @param u represents the vertex number we are passing in
         * @param p represents the parent vertex we are passing in
         * @param c represents the current color of the vertex
         */
        public Vertex(int u, Vertex p, Color c) {
            this.vertexNum = u;
            this.pi = p;
            this.color = c;
        }
    }

    //////////////////////////////////////////
    //       Depth First Search Methods     //
    //////////////////////////////////////////

    /**
     * creates a DFS object, initializes the vertices array.
     * @param numVertices is the number of vertices in the graph we are passing in
     */
    public DepthFirstSearch(int numVertices) {
        /** Initializing Vertex array */
        counter++;
        this.vertices = new Vertex[numVertices];
    }

    /**
     * The depth first search method
     * @param G is the graph we are performing on the dfs on
     */
    public void DFS(Graph G) {
        for(int i = 0; i < G.numberOfVertices(); i++) {
            /**  This counter is incremented 3 times because it assigns 3 different values to the vertex */
            counter += 3;
            this.vertices[i] = new Vertex(i, null, Color.WHITE);

        }

        counter++;
        this.time = 0;

        for(int i = 0; i < G.numberOfVertices(); i++) {
            if (this.vertices[i].color == Color.WHITE) {
                counter++;
                DFSVisit(G,this.vertices[i]);
            }
        }
    }

    /**
     * The DFSVisit method explores each vertex in a given vertex's adjacency matrix
     * @param G The graph we are applying DFSVisit to
     * @param u The vertex we are exploring
     */
    public void DFSVisit(Graph G, Vertex u) {

        counter++;
        this.time++;

        counter++;
        u.startTime = this.time;

        counter++;
        u.color = Color.GREY;

        for(int i = 0; i < G.getAdjacenciesOfVertex(u.vertexNum).size(); i++) {
            /**
             * This line below retrieves the linked list of vertex u, then finds its first adjacency
             *  After that, we use the get method for the LinkedList to find the vertex's number
             *  We then cast this to an int (as it is an Integer in the LinkedList), and then
             *  look for that Vertex object in our vertices array.
             */
            counter++;
            Vertex v = this.vertices[(int) G.getAdjacenciesOfVertex(u.vertexNum).get(i)];
            counter++;
            if(v.color == Color.WHITE) {
                counter++;
                v.pi = u;
                counter++;
                DFSVisit(G,v);
            }
        }
        counter++;
        u.color = Color.BLACK;
        counter++;
        this.time = this.time + 1;
        counter++;
        u.finishTime = this.time;
    }

    /**
     * Takes each vertex from the given graph in the depth first search  and displays
     * The vertex's number, the start time, finish time, parent vertex number, and color.
     * @return the string representation of each vertex in the DFS Graph
     */
    @Override
    public String toString() {
        String answer = "";
        for(int i = 0; i < vertices.length; i++) {
            if(vertices[i].pi == null) {
                answer += "Vertex " + i + " Has a start time of " + vertices[i].startTime + " a finish time of " + vertices[i].finishTime + " a parent vertex of " + vertices[i].pi + " and a color of: " + vertices[i].color.toString() + "\n";
            } else {
                answer += "Vertex " + i + " Has a start time of " + vertices[i].startTime + " a finish time of " + vertices[i].finishTime + " a parent vertex of " + vertices[i].pi.vertexNum + " and a color of: " + vertices[i].color.toString() + "\n";
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        /**
         *  This graph is the same graphical representation as the example
         *  found in page 605 of CLRS.
         *  The results of the code match the results of the example from the book.
         */
        Graph g = new Graph(6);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(2,1);
        g.addEdge(3,2);
        g.addEdge(4,3);
        g.addEdge(4,5);
        g.addEdge(5,5);
        DepthFirstSearch d = new DepthFirstSearch(g.numberOfVertices());
        d.DFS(g);
        System.out.println("The graph is");
        System.out.println( g.toString());
        System.out.println("It had " + g.numberOfVertices() + " vertices and "
                + g.numberOfEdges() + " edges.");

        /**
         * printing the results of the dfs on the example graph.
         */
        System.out.println(d.toString());
        System.out.println(d.counter);
    }
}
