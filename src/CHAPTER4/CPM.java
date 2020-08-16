import edu.princeton.cs.algs4.AcyclicLP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class CPM {
    private CPM(){}
    public static void main(String[] args)
    {
        int n = StdIn.readInt(); // job number
        int source = 2*n; // source point
        int sink = 2*n+1; // end point
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*n+2);// construct edge-weighted digraph
        for(int i=0;i<n;i++)
        {
            double duration = StdIn.readDouble(); // job cost
            G.addEdge(new DirectedEdge(source, i, 0.0));// source point to All points
            G.addEdge(new DirectedEdge(i+n, sink, 0.0)); // end points to all points
            G.addEdge(new DirectedEdge(i, i+n, duration)); // self connected
            
            int m = StdIn.readInt();//related job
            for(int j =0;j<m;j++)
            {
                int precedent = StdIn.readInt(); // job priority
                G.addEdge(new DirectedEdge(n+i, precedent, 0.0));
            }

        }
        AcyclicLP lp = new AcyclicLP(G, source); // longtest path search in graph
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+n));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
        
        
        
    }
}
