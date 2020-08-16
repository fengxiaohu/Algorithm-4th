import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onstack;
    private Stack<DirectedEdge> cycle;
    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G)
    {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onstack = new boolean[G.V()];
        cycle = new Stack<DirectedEdge>();
        for(int v=0;v<G.V();v++)
        {
            if(!marked[v]) 
            {
                dfs(G,v);
            }
        }
        
    }
    private void dfs(EdgeWeightedDigraph G,int v)
    {
        marked[v] = true;
        onstack[v] = true;
        for(DirectedEdge e: G.adj(v))
        {
            int w = e.to();
            if(cycle != null)
            {
                return;
            }
            else if(!marked[w])
            {
                edgeTo[w] = e;
                dfs(G,w);
            }
            else if(onstack[w])
            {
                for(DirectedEdge f = e;f.from()!=w;f=edgeTo[f.from()])
                {
                    cycle.push(f);
                }
                cycle.push(e);
                return;
                
            }
        }
        onstack[v] =false; 
    }
    public boolean hasCycle()
    {
        return cycle!=null;
        
    }
    public Iterable<DirectedCycle> cycle()
    {
        return cycle;
    }
    private boolean check()
    {
        DirectedEdge first = null, last = null;
        for (DirectedEdge e : cycle()) {
            if (first == null) first = e;
            if (last != null) {
                if (last.to() != e.from()) {
                    System.err.printf("cycle edges %s and %s not incident\n", last, e);
                    return false;
                }
            }
            last = e;
        }

        if (last.to() != first.from()) {
            System.err.printf("cycle edges %s and %s not incident\n", last, first);
            return false;
        }
    }


    return true;  
    }
    public static void main(String[] args)
    {
        int V = Integer.parseInt(args[0]); // Vertices
        int E = Integer.parseInt(args[1]); // Edges
        int F = Integer.parseInt(args[2]); // extra F edges
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        for(int i=0;i<G.V();i++)
        {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            G.addEdge(e);
        }
        for(int i=0;i<F;i++)
        {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            G.addEdge(e);
        }
        StdOut.println(G);
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if(finder.hasCycle())
        {
            StdOut.print("Cycle: ");
            for (DirectedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
        }
        else 
        {
            StdOut.println("No directed cycle") ;
        }

    }




}
