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
public class Dijkstra {
    private final Vertex[] vertices;
    private Vertex[] verticesVisitados;
    private Vertex[] verticesNoVisitados;
    private TableMap<Vertex, Integer> distancia;
    private TableMap<Vertex, Vertex> predecesores;
    private final int size; // Cantidad de vertices en el grafo.
    private int cV; // Contador de vertices visitados.
    private int cNV; // Contador de vertices no visitados.
    
    /**
     * Constructor de la clase Dijkstra.
     * @param grafo Recibe el grafo con el que se buscan las rutas mas cortas.
     */
    
    public Dijkstra(Graph grafo){
        this.size = grafo.getVerticesLength();
        this.vertices = grafo.getVertices();
        this.cV = 0;
        this.cNV = 0;
    }
    
    /**
     * Metodo para ejecutar el algoritmo de Dijkstra.
     * @param origen Recibe el vertice que se usa como referencia para realizar la ruta.
     */
    
    public Vertex[] getVertices(){
        return vertices;
    }

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
    
    private boolean isVisited(Vertex vertice){
        int a = buscarPosVertice(vertice.getElement().getEffort(), this.verticesVisitados);
        if(a < 0){
            return false;
        }
        return true;
    }
    
    private int getShortestDistance(Vertex destino){
        Integer d = distancia.get(destino);
        if(d == null){
            return Integer.MAX_VALUE;
        }else{
            return d;
        }
    }
    

    
    /**
     * Metodo para calcular la ruta mas corta del origen al vertice deseado.
     * @param destino Vertice al cual se quiere llegar.
     * @return Arreglo con los vertices que contienen la ruta al destino.
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
    
    public void reiniciar(){
       this.cV = 0;
       this.cNV = 0; 
    }

}
