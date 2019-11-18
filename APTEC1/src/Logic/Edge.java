/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Samuel and Jimena
 */
public class Edge {
    //Edge's atributess
    private Vertex reference;
    private int weight;
    private int time;

    /**Builder # 1: The path of the edge is labeled.
     * @param reference Vertex a conectar con otro nodo.
     * @param weight Peso de la arista.
     */
    public Edge(Vertex reference,  int weight){               
        this.reference = reference;
        this.weight = weight;
    }

    /** Builder # 2
     * @param reference
     * @param weight
     * @param time 
     */
    public Edge(Vertex reference, int weight, int time) {
        this.reference = reference;
        this.weight = weight;
        this.time = time;
    }

    /**Method to know the hole time
     * @return time
     * @restrictions inputs must be ints
     */
    public int getTime() {
        return time;
    }
    /**Method to set the time
     * @return 
     * @restrictions inputs must be ints
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**Method that returns the reference of the edge.
     * @return Vertex adyacente.
     */
    public Vertex getReference() {
        return this.reference;
    }
    
    /**Method that sets a vertex's reference
     * @param reference Valor de la referencia.
     */
    public void setReference(Vertex reference) {
        this.reference = reference;
    }
    
    /**method that returns the edge's weight.
     * @return edge's weight.
     */
    public int getWeight() {
        return weight;
    }
    
    /**Method that sets edge's weight.
     * @param weight
     * @restrictions inputs must be ints
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}
