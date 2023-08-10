import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph_BFS_DFS {
    private int vertices;
    private List<List<Integer>> adjacentList;
    public Graph_BFS_DFS(int vertices){
        this.vertices = vertices;
        adjacentList = new ArrayList<>(vertices);
        for(int i =0;i<vertices;i++){
            adjacentList.add(new ArrayList<>());
        }
    }
    public void addEdge(int src, int dest) {
        adjacentList.get(src).add(dest);
        adjacentList.get(dest).add(src); // we can comment this for unidirectional graoh
    }

    public List<Integer> getNeighbors(int vertex){
        return adjacentList.get(vertex);
    }
    public int getVertexCount(){
        return  vertices;
    }

    public void printGraph(){
        for(int i =0 ;i < vertices; i++){
            System.out.print("Vertex "+i+" is connected to : ");
            for(int neighbor : adjacentList.get(i)){
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void DFS_wrapper(int startNode){
        boolean[] isVisited = new boolean[vertices];
        DFS(startNode,isVisited);
    }
    public void DFS(int vertex,boolean[] isVisited){
        isVisited[vertex]= true;
        System.out.print(vertex+ " ");
        List<Integer> neighbors = getNeighbors(vertex);
        for(int neighbor: neighbors){
            if(!isVisited[neighbor]){
                DFS(neighbor,isVisited);
            }

        }
    }
    public void BFS(int startIndex){
        boolean[] visited = new boolean[vertices];
        Queue<Integer> q = new LinkedList<>();
        q.add(startIndex);
        visited[startIndex]=true;
        while(!q.isEmpty()){
            int currNode = q.poll();
            System.out.print(currNode+" ");
            for(int neighbor : getNeighbors(currNode)){
                if(!visited[neighbor]){
                    visited[neighbor]=true;
                    q.add(neighbor);
                }
            }
        }
    }

    public static void main(String args[]){
        Graph_BFS_DFS graph = new Graph_BFS_DFS(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        graph.printGraph();

        System.out.print("Depth-First Search (DFS) traversal starting from vertex 0: ");
        graph.DFS_wrapper(0);
        System.out.println();

        System.out.print("Breadth-First Search (BFS) traversal starting from vertex 0: ");
        graph.BFS(0);



    }


}
