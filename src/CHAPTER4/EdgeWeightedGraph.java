import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
public class EdgeWeightedGraph {
    private static final String NEWLLINE = System.getProperty("line.separtor");
    
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    
    public EdgeWeightedGraph(int V,int E)
    {
        this.V = V;
        this.E = E;
        if(V<0)  throw new IllegalArgumentException("Number of vertices must be nonnegative");
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for(int v=0;v<V;v++)
        {
            adj[v] = new Bag<Edge>();
        }
        
        
    }
    public EdgeWeightedGraph(int V)
    {
        if(V<0)  throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V =V;
        this.E = E;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v=0;v<V;v++)
        {
            adj[v] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in)
    {
        
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            V = in.readInt();
            adj = (Bag<Edge>[]) new Bag[V];
            for(int v=0;v<V;v++)
            {
                adj[v] = new Bag<Edge>();
                
            }
            int E = in.readInt();
            if(E<0) throw new IllegalArgumentException("Number of edges must be nonnegative");
            for(int i=0;i<E;i++)
            {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                validateVertex(v);
                validateVertex(w);
                Edge e = new Edge(v, w, weight);
                addEdge(e);
            }
            
        } catch (NoSuchElementException e) {
            //TODO: handle exception
            throw new IllegalArgumentException("invalid input format in EdgeWeightedGraph constructor", e);
        }
    }
    public EdgeWeightedGraph(EdgeWeightedGraph G)
    {
        this.V = G.V();
        this.E = G.E();
        for(int v=0;v<G.V();v++)
        {
            Stack<Edge> reverse = new Stack<Edge>();
            for(Edge e : G.adj[v])
            {
                reverse.push(e);
            }
            for(Edge e:reverse)
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
    private void validateVertex(int v)
    {
        if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    public void addEdge(Edge e)
    {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;        
    }
    public Iterable<Edge> adj(int v)
    {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v)
    {
        return adj[v].size();
    }
    public Iterable<Edge> edges()
    {
        Bag<Edge> list = new Bag<Edge>();
        for(int v=0;v<V;v++)
        {
            int selfloop = 0;
            for(Edge e:adj[v])
            {
                if(e.other(v)>v)
                {
                    list.add(e);
                }
                else if(e.other(v) == v)
                {
                    if(selfloop % 2 ==0) list.add(e);
                    selfloop++;
                }
            }
            
        }
        return list;

    }
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(V+" "+E+NEWLLINE);
        for(int v=0;v<V;v++)
        {
            s.append(v+":");
            for(Edge e :adj[v])
            {
                s.append(e+" ");
            }
            s.append(NEWLLINE);
        }
        return s.toString();
    }
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.print(G.toString());
        StdOut.println(G);

    }
}
