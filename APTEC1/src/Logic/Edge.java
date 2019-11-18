/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author samue
 */
public class Edge {
    //atributos de las aristas
    private Vertex reference;
    private int weight;
    private int time;

    /**
     * Contructor #1: Se etiqueta el camino de la arista.
     * @param reference Vertex a conectar con otro nodo.
     * @param weight Peso de la arista.
     */
    public Edge(Vertex reference,  int weight){               
        this.reference = reference;
        this.weight = weight;
    }

    public Edge(Vertex reference, int weight, int time) {
        this.reference = reference;
        this.weight = weight;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * Metodo que devuelve la referencia de la arista.
     * @return Vertex adyacente.
     */
    public Vertex getReference() {
        return this.reference;
    }
    
    /**
     * Metodo que establece el valor de la referencia.
     * @param reference Valor de la referencia.
     */
    public void setReference(Vertex reference) {
        this.reference = reference;
    }
    
    /***
     * Metodo que devuelve el valor del peso de la arista.
     * @return Peso de la arista.
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Metodo que establece el valor de la distancia en una arista
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}
