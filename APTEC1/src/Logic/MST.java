/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**Class MST (Minimun Spanning Tree) for Prim's algorithm
 *The program is for adjacency matrix representation of the graph 
 * @author Samuel and Jimena
 */
import java.util.*;
import java.lang.*;
import java.io.*;

public class MST implements Serializable {

    // Number of vertices in the graph 
    private static final int V = 5;
    
    /**Method to find the vertex with minimum key
     * @param key
     * @param mstSet
     * @return min_index (vertex)
     * @restrictions the inputs have to be only ints.
     */
    public int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    /** Method to print the constructed MST stored in
     * @param parent
     * @param graph
     * @return str of the MST
     * @restrictions inputs have to be only ints
     */
    public void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }
    
    /**Method to construct and print MST for a graph represented using adjacency matrix representation 
     * @param graph 
     * @return void
     * @restrictions inputs have to be only ints
     */
    public void primMST(int graph[][]) {
        // Array to store constructed MST 
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut 
        int key[] = new int[V];

        // To represent set of vertices not yet included in MST 
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE 
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST. 
        key[0] = 0; // Make key 0 so that this vertex is 
        // picked as first vertex 
        parent[0] = -1; // First node is always root of MST 

        // The MST will have V vertices 
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set 
            mstSet[u] = true;

            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int v = 0; v < V; v++) // graph[u][v] is non zero only for adjacent vertices of m 
            // mstSet[v] is false for vertices not yet included in MST 
            // Update the key only if graph[u][v] is smaller than key[v] 
            {
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // print the constructed MST 
        printMST(parent, graph);
    }

}
