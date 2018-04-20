/**
 * The implementation of Depth First Search.
 * The depth first search takes into the constuctor the number of vertices in the graph and an array is then initalized to hold the vertex objects.
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

    /** the array of Vertex objects within the graph */
    public Vertex[] vertices;


    //////////////////////////////////////////
    //          Enumerations                //
    //////////////////////////////////////////

    /**
     * Enumerations are used to represent the vertex's colors
     * Each vertex can only have white, grey, or black for the color
     */

    public enum Color {
        WHITE, GREY, BLACK
    }
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


    /**
     * creates a DFS object.
     *
     * @param numVertices
     */
    public DepthFirstSearch(int numVertices) {
        this.time = 0;
        this.vertices = new Vertex[numVertices];
    }


    public void DFS(Graph G) {
        for(int i = 0; i < G.numberOfVertices(); i++) {
            this.vertices[i] = new Vertex(i, null, Color.WHITE);
        }
        this.time = 0;
        for(int i = 0; i < G.numberOfVertices(); i++) {
            if (this.vertices[i].color == Color.WHITE) {
                DFSVisit(G,this.vertices[i]);
            }
        }
    }

    public void DFSVisit(Graph G, Vertex u) {
        this.time++;
        u.startTime = this.time;
        u.color = Color.GREY;
        for(int i = 0; i < G.getAdjacenciesOfVertex(u.vertexNum).size(); i++) {
            Vertex v = this.vertices[(int) G.getAdjacenciesOfVertex(u.vertexNum).get(i)];
            if(v.color == Color.WHITE) {
                v.pi = u;
                DFSVisit(G,v);
            }
        }
        u.color = Color.BLACK;
        this.time = this.time + 1;
        u.finishTime = this.time;
    }
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
        System.out.println(d.toString());
    }
}
