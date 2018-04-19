public class DFS {
    public int time;
    public Vertex[] vertices;

    public DFS(int vs) {
        this.time = 0;
        this.vertices = new Vertex[vs];
    }

    public class Vertex {

        public Color color;
        public String pi;
        public int vertexNum;
        public int d;
        public int f;

        public Vertex(int u, String p, Color c) {
            this.vertexNum = u;
            this.pi = p;
            this.color = c;
        }
    }

    public enum Color {
        WHITE, GREY, BLACK
    }
    public void DepthFirstSearch(Graph G) {
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
        u.d = time;
        u.color = Color.GREY;
        for(int i = 0; i < G.getAdjacenciesOfVertex(u.vertexNum).size(); i++) {
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4, 0.6);
        System.out.println("The graph is");
        System.out.println( g.toString());
        System.out.println("It had " + g.numberOfVertices() + " vertices and "
                + g.numberOfEdges() + " edges.");
    }
}
