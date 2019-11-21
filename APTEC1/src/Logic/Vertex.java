/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import ControllerPackage.*;
import java.io.Serializable;
/**
 *
 * @author Samuel and Jimena
 * @param <T>
 */
public class Vertex<T> implements Serializable {
    //Atributes
    private final LinkedList<Edge> aristas = new LinkedList<>();
    private Task element;

    /**
     * Constructor # 1: Creates a new vertex (node) that is added to the graph.
     * @param element 
     * @restrictions element must be Task
     */
    public Vertex(Task element){
         this.element = element;
         element.setNumTask(Controller.listTask.getSize()+1);
         Controller.listTask.insert(element);
         
         
    }

    /**
     * Method that returns the value of the vertex being queried.
     * @return vertex value
     */
    public Task getElement() {
         return element;
    }

    /**
     * Method that allows to modify the value of a desired vertex.
     * @param element 
     * @restrictions element must be Task
     */
    public void setElement(Task element){
        this.element = element;
    }

    /**
     * Method that allows adding an adjacency between two nodes.
     * @param reference Vertex a conectar.
     * @param weight Peso del vertice.
     * @restrictions reference must be Vertex, weight must be int and time must be int
    */

    public void nuevaRuta(Vertex reference, int weight, int time){
        Edge newArista = new Edge(reference, weight,time);
        aristas.append(newArista);
    }
    
    /**
     * Method that eliminates a route between two vertices.
     * @param referencia 
     * @param weight
     * @restrictions referencia must be Vertex, and weight must be int
     */
    public void eliminarRuta(Vertex referencia, int weight){
        for(int i = 0; i < aristas.getSize();i++){
            Edge arista=(Edge) aristas.getElement();
            if(arista.getReference() == referencia && arista.getWeight() == weight){
                aristas.remove();
                break;
            }
            aristas.next();
        }
    }

    /**
     * Method that returns the edges associated with a vertex.
     * @return LinkedList with edges
     */
    public LinkedList<Edge> getAristas(){
        return this.aristas;
     }
    
}
