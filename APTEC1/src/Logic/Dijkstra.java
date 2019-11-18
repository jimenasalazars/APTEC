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
public class Dijkstra {
    
    //atributes
    private final Vertex[] vertices; //vertex
    private Vertex[] verticesVisitados; //visited vertex
    private Vertex[] verticesNoVisitados; //not visited vertex
    private TableMap<Vertex, Integer> distancia; //distance between vertex
    private TableMap<Vertex, Vertex> predecesores; //dependencies
    private final int size; //amount of vertex
    private int cV; //amount of visited vertex
    private int cNV; //amount of not ivsited vertex
    
    /**
     * Dijkstra's contructor.
     * @param grafo
     * @restriction inputs must be gaph objects
     */
    
    public Dijkstra(Graph grafo){
        this.size = grafo.getVerticesLength();
        this.vertices = grafo.getVertices();
        this.cV = 0;
        this.cNV = 0;
    }
    
    /**Method to execute the Dijkstra's algorithm.
     * @param origen 
     */
    public Vertex[] getVertices(){
        return vertices;
    }

    /**Method to show the Dijkstra's algorithm.
     * @param origen 
     * @restriction inputs must be vertex objects
     */
    public void DijkstraEnAccion(Vertex origen) {
        verticesVisitados = new Vertex[this.size];
        verticesNoVisitados = new Vertex[this.size];
        distancia = new TableMap<>();
        predecesores = new TableMap<>();
        distancia.put(origen, 0);
        verticesNoVisitados[cNV] = origen;
        cNV++;
        int contador=0;
        while (this.cNV < this.verticesNoVisitados.length) {
            Vertex vertice = getMinimum(this.verticesNoVisitados);
            if (vertice == null) {
                cNV++;
            } else {
                //System.out.println(vertice.getElement().getDescription());
                this.verticesVisitados[cV] = vertice;
                cV++;
                int i = this.buscarPosVertice(vertice.getElement().getID() - 1, this.verticesNoVisitados);
                //vertice.getElement().getID()-1; 
                //this.buscarPosVertice(vertice.getElement().getID()-1, this.verticesNoVisitados);
                //System.out.println("I: "+i);
                this.verticesNoVisitados[i] = null;
                this.findMinimalDistances(vertice);
            }
        }
    }
    
    /**Method to know the vertex's position
     * @param valor
     * @param arreglo
     * @return 
     * @restriction inputs must be int and vertex objects
     */
    private int buscarPosVertice(int valor, Vertex[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            if(arreglo[i] != null){
                if(arreglo[i].getElement().getID()-1 == valor){
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**Method to know the minimal distance from an origin vertex
     * @param origen 
     * @restriction inputs must be vertex objects
     */
    private void findMinimalDistances(Vertex origen){
        Vertex[] verticesAdyacentes = getNeighbors(origen);        
        for(Vertex destino : verticesAdyacentes){
            if(destino != null){
                if(getShortestDistance(destino) > getShortestDistance(origen) + getDistance(origen, destino)){
                    this.distancia.put(destino, getShortestDistance(origen) + getDistance(origen, destino));
                    this.predecesores.put(destino, origen);
                    this.verticesNoVisitados[cNV] = destino;
                    this.cNV++;
                }
            }
        }
    }
    
    /**Method to know the distance between an origin and destiny vertex
     * @param origen
     * @param destino
     * @return 
     * @restriction inputs must be vertex objects
     */
    private int getDistance(Vertex origen, Vertex destino){
        for (int i = 0; i < this.size; i++){
            if(this.vertices[i] == origen){
                Vertex verticeActual = this.vertices[i];
                LinkedList<Edge> aristas = verticeActual.getAristas();
                aristas.goToStart();
                aristas.next();
                for(int m = 0; m < aristas.getSize(); m++){
                    if(m == aristas.getSize() - 1){
                        Edge dato1= (Edge) aristas.getElement();
                        if(dato1.getReference() == destino)
                            return dato1.getWeight();
                    }else{
                        Edge dato1= (Edge) aristas.getElement();
                        if(dato1.getReference() == destino){
                            return dato1.getWeight();
                        }  
                        aristas.next();
                    }                   
                }   
            }
        }
        throw new RuntimeException("Should not happen");
    }
    
    /**Method to know the vertex's neighbors
     * @param vertice
     * @return
     * @restriction inputs must be vertex objects
     */
    private Vertex[] getNeighbors(Vertex vertice){
        int contador = 0;
        Vertex[] neighbors = new Vertex[this.size];
        for (int i = 0; i < this.size; i++){
            if(this.vertices[i] == vertice){
                Vertex verticeActual = this.vertices[i];
                LinkedList<Edge> aristas = verticeActual.getAristas();
                aristas.goToStart();
                aristas.next();
                for(int m = 0; m < aristas.getSize(); m++){
                    if(m == aristas.getSize() - 1){
                        Edge dato1= (Edge) aristas.getElement();
                        if(!isVisited(dato1.getReference())){
                            neighbors[m] = dato1.getReference();
                            contador++;
                        }
                        break;
                    }else{
                        Edge dato1= (Edge) aristas.getElement();
                        if(!isVisited(dato1.getReference())){
                            neighbors[m] = dato1.getReference();
                            contador++;
                        }
                        aristas.next();
                    }                   
                }
                Vertex[] temp = new Vertex[contador];
                int j = 0;
                for(Vertex ver : neighbors){
                    if(ver != null){
                        temp[j] = ver;
                        j++;
                    }   
                }
                neighbors = temp;
                return neighbors;
            }
        }
        return neighbors;
    }
    
    /**Method to get the minimun distance
     * @param vertices
     * @return 
     * @restriction inputs must be vertex objects
     */
    private Vertex getMinimum(Vertex[] vertices){
        Vertex min = null;
        for(Vertex vertice : vertices){
            if(min == null){
                min = vertice;
            }else{
                if(getShortestDistance(vertice) < getShortestDistance(min)){
                    min = vertice;
                }
            }
        }
        return min;
    }
    
    /**Boolean method to know if a vertex is visited
     * @param vertice
     * @return
     * @restriction inputs must be vertex objects
     */
    private boolean isVisited(Vertex vertice){
        int a = buscarPosVertice(vertice.getElement().getEffort(), this.verticesVisitados);
        if(a < 0){
            return false;
        }
        return true;
    }
    
    /**Method to get the shortest distance to a specific destiny
     * @param destino
     * @return 
     * @restriction inputs must be vertex objects
     */
    private int getShortestDistance(Vertex destino){
        Integer d = distancia.get(destino);
        if(d == null){
            return Integer.MAX_VALUE;
        }else{
            return d;
        }
    }
    

    
    /**
     * Method to get the shortest route from the origin to the destiny
     * @param destino vertex where you want to go
     * @return vertex array with the route
     * @restriction inputs must be vertex objects
     */
    public Vertex[] getPath(Vertex destino) {
        Vertex[] path = new Vertex[this.size * this.size];
        Vertex paso = destino;
        int i = 0;
        // Comparacion para revisar si el recorrido existe.
        if (predecesores.get(paso) == null) {
            return null;
        }
        path[i] = paso;
        i++;
        while (predecesores.get(paso) != null) {
            paso = predecesores.get(paso);
            path[i] = paso;
            i++;
        }
        // Invertir el arreglo para obtener el recorrido correcto.
        Vertex[] temp = new Vertex[i];        
        int k = temp.length - 1;  
        for(int j = 0; j < temp.length; j++){
            temp[k] = path[j];
            k--;
        }
        path = temp;
        return path;
    }
    
    /**Method to start again the Dijkstra
     * no param, return or restrictions
     */
    public void reiniciar(){
       this.cV = 0;
       this.cNV = 0; 
    }

}
