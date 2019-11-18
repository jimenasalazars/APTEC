/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import ControllerPackage.*;
/**
 *
 * @author samue
 * @param <T>
 */
public class Vertex<T> {
    //atributos de la clase vertice
    private final LinkedList<Edge> aristas = new LinkedList<>();
    private Task element;

    /**
     * Constructor #1: Crea un nuevo vertice (nodo) que se agrega al grafo.
     * @param element Elemento que identifica el vertice.
     */
    public Vertex(Task element){
         this.element = element;
         element.setNumTask(Controller.listTask.getSize()+1);
         Controller.listTask.insert(element);
         
         
    }

    /**
     * Metodo que devuelve el valor del vertice que se esta consultando.
     * @return Valor del vertice.
     */
    public Task getElement() {
         return element;
    }

    /**
     * Metodo que permite modificar el valor de un vertice deseado.
     * @param element Valor del vertice.
     */
    public void setElement(Task element){
        this.element = element;
    }

    /**
     * Metodo que permite agregar una adyacencia entre dos nodos.
     * @param reference Vertex a conectar.
     * @param weight Peso del vertice.
    */

    public void nuevaRuta(Vertex reference, int weight, int time){
        Edge newArista = new Edge(reference, weight,time);
        aristas.append(newArista);
    }
    
    /**
     * Metodo que elimina una ruta entre dos vertices.
     * @param referencia referencia de la ruta que se desea eliminar.
     * @param weight peso de la arista.
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
     * Metodo que devuelve las aristas asociadas a un vertice.
     * @return LinkedList con las aristas.
     */
    public LinkedList<Edge> getAristas(){
        return this.aristas;
     }
    
}
