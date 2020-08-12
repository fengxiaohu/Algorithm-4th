import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TransitiveClosure;
public class KosarajuSharirSCC {
    private boolean[] marked;
    private int[] id; // id[v] = id of strong component containing v
    private int count;
    

    public KosarajuSharirSCC(Digraph G)
    {
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v:dfs.reversePost())
        {
            if(!marked[v])
            {
                dfs(G,v);
                count++;
            }
        }
    }
    private void dfs(Digraph G,int v)
    {
        marked[v] = true;
        id[v] = count;
        for(int w:G.adj(v))
        {
            if(!marked[w])
            {
                dfs(G, w);
            }
        }
    }
    public int count()
    {
        return count;
    }
    public boolean stronglyConnected(int v,int w)
    {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];

    }
    public int id(int v)
    {
        validateVertex(v);
        return id[v];
    }
    private boolean check(Digraph G)
    {
        TransitiveClosure tc = new TransitiveClosure(G);
        for(int v=0;v<G.V();v++)
        {
            for(int w=0;w<G.V();w++)
            {
                if(stronglyConnected(v, w)==(tc.reachable(v, w) && tc.reachable(w, v)))
                {
                    return true;
                }
            }
        }
        return false;
    
    }
    private void validateVertex(int v)
    {
        int V = marked.length;
        if (v < 0 || v >= V)
        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

        // number of connected components
        int m = scc.count();
        StdOut.println(m + " strong components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

    }

}
