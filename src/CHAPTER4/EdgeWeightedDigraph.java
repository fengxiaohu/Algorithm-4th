import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.*;


import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line separatory");
    

    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private int[] indrgree;
    
    public EdgeWeightedDigraph(int V)
    {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adj = (Bag<DirectedEdge>[])new Bag[V];
        this.indrgree = new int[V];
        for(int v=0;v<V;v++)
        {
            adj[v] = new Bag<DirectedEdge>();
        }
                                                                                        
    }
    public EdgeWeightedDigraph(int V,int E)
    {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        if(E<0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adj = (Bag<DirectedEdge>[])new Bag<>[V];
        this.indrgree = new int[V];
        for(int i=0;i<E;i++)
        {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(E);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
        
    }
    public EdgeWeightedDigraph(In in)
    {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            //initialize
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            indrgree = new int[V];
            adj = (Bag<DirectedEdge>[]) new Bag[V];
            for(int v=0;v<V;v++)
            {
                adj[v] = new Bag<DirectedEdge>();
            } 
            // read and construct the EdgeweightGraph
            int E = in.readInt();
            if(E<0) throw new IllegalArgumentException("Number of edges must be nonnegative");
            for(int i=0;i<E;i++)
            {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                DirectedEdge e = new DirectedEdge(v, w, weight);
                addEdge(e);
            }
        } catch (NoSuchElementException error) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", error);

            
            //TODO: handle exception
        }
        
    }
    public EdgeWeightedDigraph(EdgeWeightedDigraph G)
    {
        this.V = G.V();
        this.E = G.E();
        for(int v=0;v<G.V();v++)
        {
            this.indrgree[v] = G.indrgree(v);
        }
        for(int v=0;v<G.V();v++)
        {
            Stack<DirectedEdge> reverse  = new Stack<DirectedEdge>();
            for(DirectedEdge e:G.adj[v])
            reverse.push(e);
            for(DirectedEdge e:reverse)
            {
                adj[v].add(e);
            }
        }
       

        
    }

    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    public void addEdge(DirectedEdge e)
    {
        int v = e.from();
        int w = e.to();
        validate(v);
        validate(w);
        adj[v].add(e);
        E++;
        indrgree[w]++;
    }
    public Iterable<DirectedEdge> adj(int v)
    {
        validate(v);
        return adj[v];
    }
    
    public int  indrgree(int v)
    {
        validate(v);
        return indrgree[v];
    }
    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for(int v=0;v<V;v++)
        {
            for(DirectedEdge e:adj[v])
            list.add(e);
        }
        return list;
    }
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    private void validate(int v)
    {
        if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    public int outdegree()
    {
        return adj[v].size();       
    }
    public static void main(String[] args)
    {
      In in = new In(args[0]);
      EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(in);
      for(int v=0;v<edgeWeightedDigraph.V();v++)
      {
          
      }
      StdOut.println(edgeWeightedDigraph);   
    }
    
}
