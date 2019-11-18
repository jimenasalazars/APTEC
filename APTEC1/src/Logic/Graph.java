/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * Estructura de datos Grafo.
 * @author samuels
 */
/**
 *
 * @author samue
 */
public class Graph {
     //atributos de la clase grafo
    public Vertex[] vertices;
    public Vertex[] wbs;
    private int verticesSize;
    private int contadorVertices;
    
    /**
     * Constructor #!: Inicializa el grafo con un tamaño n.
     * @param n Cantidad de vertices para el grafo.
     */
    public Graph(int n){
        vertices = new Vertex[n];
        wbs = new Vertex[n];
        this.verticesSize = n;
        contadorVertices = 0;
    }
    
    /**
     * Metodo que permite añadir un nuevo vertice al grafo.
     * @param element Valor del elemento del vertice.
     */
    public void nuevoVertice(Task element){
        Vertex newVertice = new Vertex(element);
        this.vertices[contadorVertices] = newVertice;
        this.wbs[contadorVertices] = newVertice;
        this.contadorVertices++;
    }
    
    
    /**
     * Metodo que permite agregar una nueva ruta entre un vertice y otro.
     * @param vertice Vertex al que se le agrega la ruta.
     * @param ref Referencia del vertice destino.
     * @param weight Peso de la arista.
     */
    public void agregarRuta(Vertex vertice, Vertex ref, int weight, int time){
        if(this.verticesSize == 0){
            System.out.println("Grafo vacío");
        }
        else{
            vertice.nuevaRuta(ref, weight,time);
            //int indice = buscarPosVertice(vertice, this.vertices);
            //this.vertices[indice].nuevaRuta(ref, weight);
        }  
    }
    
    /**
     * Metodo que permite eliminar un vertice del grafo.
     * @param vertice Vertex que se va a eliminar.
     */
    public void eliminarVertice(int vertice){
        if(this.verticesSize == 0){
            System.out.println("Grafo vacío");
        }
        else if(vertice < 0 || vertice > this.verticesSize){
            System.out.println("Posición de vértice inválida");
        }
        else{
            int a = buscarPosVertice(vertice, vertices);
            this.verticesSize--;
            swap(a, this.verticesSize);
            Vertex[] temp = new Vertex[verticesSize];
            System.arraycopy(vertices, 0, temp, 0, verticesSize);
            this.vertices = temp;
        }
    }
    
    /**
     * Metodo que elimina una ruta existente
     * @param vertice Vertex al que se le va a eliminar la ruta.
     * @param referencia Referencia del vertice destino.
     * @param weight Peso de la arista.
     */
    public void eliminarRuta(int vertice, Vertex referencia, int weight){
        if(this.verticesSize == 0){
            System.out.println("Grafo vacío");
        }
        else if(vertice < 0 || vertice > this.verticesSize){
            System.out.println("Posición de vértice inválida");
        }
        else{
            int i = buscarPosVertice(vertice, vertices);
            vertices[i].eliminarRuta(referencia, weight);
        }
    }
    
    /**
     * Metodo que encuentra la posicion del vertice dentro del grafo
     * @param vertice Vertex al que se le busca la posicion.
     * @param arreglo Array con los elementos a buscar.
     * @return Posicion del vertice.
     */ 
    public int buscarPosVertice(int vertice, Vertex[] arreglo){
        for(int i = 0; i < arreglo.length; i++){
            if(arreglo[i].getElement().getID() == vertice){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Metodo que intercambia dos vertices de posicion.
     * @param i Posicion del vertice a intercambiar.
     * @param j Ultima posicion.
     */
    public void swap(int i, int j){
        Vertex temp = vertices[i];
        vertices[i] = vertices[j];
        vertices[j] = temp;
    }
    
    /**
     * Metodo que busca las adyacencias de cada vertice dentro del grafo.
     * @return 
     */

        
    public void ArchivoMapa() throws IOException{
        String ruta = "/home/pazbloise/archivoMapa.dot";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("digraph G");
        bw.newLine();
        bw.write("{");
        bw.newLine();
        bw.write( "node [shape=circle];");
        bw.newLine();
        bw.write( "node [style=filled];");
        bw.newLine();
        for(Vertex vertice: vertices){
            Vertex verticeActual = vertice;
            bw.write(verticeActual.getElement()+";");
            bw.newLine();
        }
        for (Vertex vertice: vertices){
            Vertex verticeActual = vertice;
            LinkedList<Edge> arista = verticeActual.getAristas();
            arista.goToStart();
            for (int m = 0; m< arista.getSize();m++){
                if(m != arista.getSize()){
                     Edge dato1 = (Edge) arista.getElement();
                     Task dato2 = (Task) dato1.getReference().getElement();
                     bw.write(verticeActual.getElement()+" "+"->"+" "+dato2);
                     bw.newLine();
                     arista.next();
                }
            }
        }
        bw.write("}");
        bw.close(); 
       //GraphvizJava graphvizJava = new GraphvizJava("/home/pazbloise/Escritorio/archivoMapa.dot","/home/pazbloise/Escritorio/archivoMapa.png" );
    }

    /**
     * Metodo que devuelve la cantidad de vertices de un grafo.
     * @return Cantidad de vertices del grafo.
     */
    public int getVerticesLength(){
        return this.verticesSize;
    }
    
    /**
     * Metodo que devuelve los vertices del grafo.
     * @return Un array con los vertices del grafo.
     */   
    public Vertex[] getVertices(){
        return this.vertices;
    }

    public Vertex[] getWbs() {
        return wbs;
    }
    
    
    /**
     * Metodo que extrae los vertices origen de un grafo a partir de un documento txt.
     * @param ruta Ruta del archivo txt.
     * @throws FileNotFoundException
     * @throws IOException 
     *//*
    public void crearOrigen(String ruta) throws FileNotFoundException, IOException{
        String linea;
        String palabra;

        File archivo = new File(ruta);
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        
        while ((linea = br.readLine()) != null){
            if("MAPA".equals(linea)){
                while ((linea = br.readLine()) != null){
                    StringTokenizer st = new StringTokenizer(linea);                    
                    palabra = st.nextToken();                        
                    int origen = new Integer(palabra);
                    nuevoVertice(origen);
                }
            }
        }
    } 
    */
    /**
     * Metodo que extrae las aristas de un documento txt y las agrega a sus vertices origen correspondientes.
     * @param ruta Ruta del documento txt.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    /*
    public void agregarRutas(String ruta) throws FileNotFoundException, IOException{
        String linea;
        String palabra;
        int contador = 0;

        File archivo = new File(ruta);
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        
        while ((linea = br.readLine()) != null){
            if("MAPA".equals(linea)){
                while ((linea = br.readLine()) != null){
                    int numTokens = 0;
                    StringTokenizer st = new StringTokenizer(linea);
                    while(st.hasMoreTokens()){
                        palabra = st.nextToken();
                        if(numTokens != 0){
                            String arista = "";
                            String vertice = "";
                            for(int i = 0; i < palabra.length(); i++){
                                if(palabra.charAt(i)== '('){
                                    vertice = palabra.substring(0, i);
                                    arista = palabra.substring(i+1, palabra.length()-1);
                                    int verticeN = buscarPosVertice(new Integer(vertice), this.vertices);
                                    int aristaN = new Integer(arista);
                                    agregarRuta(this.vertices[contador], this.vertices[verticeN], aristaN);                                                                     
                                }
                            }                    
                        }
                        numTokens++;
                    }
                    contador++;                   
                }
            }
        }
    }*/
    
    
}
