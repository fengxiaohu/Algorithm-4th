import java.io.*; 
import java.util.*;
import edu.princeton.cs.algs4.*;
class GraphDfs
{
    private int V;
    private LinkedList<Integer> adj[];
    GraphDfs(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++)
        {
            adj[i] = new LinkedList<Integer>();
        }
        
    }
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;
        System.out.print(v+" ");
        Iterator<Integer> i = adj[v].listIterator();
        while(i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
            {
                DFSUtil(v, visited);
            }
        }
    }
    void DFS(int v)
    {
        boolean visited[] = new boolean[v];
        DFSUtil(v, visited);

    }
    public static void main(String args[])
    {
        GraphDfs g  = new GraphDfs(4);
        g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 2); 
        g.addEdge(2, 0); 
        g.addEdge(2, 3); 
        g.addEdge(3, 3); 
  
        System.out.println("Following is Depth First Traversal "+ 
                           "(starting from vertex 2)"); 
        g.DFS(2);

    }
}