import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;
public class CC 
{
    private boolean[] marked;
    private int numberOfelements;
    private int[] size;
    private int[] id;
    private int count;
    
    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        numberOfelements = G.V();
        for(int v=0;v<G.V();v++)
        {
            if(!marked[v])
            {
                dfs(G,v);
                count++;
            }
        }

     }
    public CC(EdgeWeightedGraph G)
    {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for(int v=0;v<G.V();v++)
        {
            if(!marked[v])
            {
                dfs(G,v);
                count++;
            }
        }
        
    }
    private void dfs(Graph G,int v)
    {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for(int w:G.adj(v))
        {
            if(!marked[w])
            {
                dfs(G, w);
            }
        }
    }
    private void dfs(EdgeWeightedGraph G,int v)
    {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for(Edge e:G.adj(v))
        {
            int w = e.other(v);
            if(!marked[w])
            {
                dfs(G,w);
            }

        }
    }
    public int id(int v)
    {
        validateVertex(v);
        return id[v];
    }
    public int size(int v)
    {
        validateVertex(v);
        return size[id[v]];
    }
    public int count()
    {
        return count;
    }
    public boolean connected(int v,int w)
    {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }
    public boolean areconnected(int v,int w)
    {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }
    private void validateVertex(int v)
    {
        if(v<0 || v>numberOfelements)
        {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (numberOfelements-1));
        }

    }
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);
        
        int m = cc.count();
        StdOut.println(m+" commponents");
        Queue<Integer>[] commponents = (Queue<Integer>[]) new Queue[m];
        for(int i=0;i<m;i++)
        {
            commponents[i] = new Queue<Integer>();
        }
        for(int v=0;v<G.V();v++)
        {
            commponents[cc.id(v)].enqueue(v);
        }
        for (int i = 0; i < m; i++) {
            for (int v : commponents[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        
        

    }
}