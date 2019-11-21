/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * Graph Structure
 * @author Samuel and Jimena
 */

public class Graph implements Serializable{
     //atributos de la clase grafo
    public Vertex[] vertices;
    public Vertex[] wbs;
    private int verticesSize;
    private int contadorVertices;
    
    /**Constructor #1: Initializes the graph with a size n.
     * @param n 
     * @restrictions inputs must be ints
     */
    public Graph(int n){
        vertices = new Vertex[n];
        wbs = new Vertex[n];
        this.verticesSize = n;
        contadorVertices = 0;
    }
    
    /**
     * 
     * @param element 
     * @restriction element must be of type Task
     */
    public void nuevoVertice(Task element){
        Vertex newVertice = new Vertex(element);
        this.vertices[contadorVertices] = newVertice;
        this.wbs[contadorVertices] = newVertice;
        this.contadorVertices++;
    }
    
    
    /**
     * Method that allows adding a new route between one vertex and another.
     * @param vertice 
     * @param ref 
     * @param weight Peso de la arista.
     * @restrictions vertice and ref must be vertex, and weight and time must be ints
     */
    public void agregarRuta(Vertex vertice, Vertex ref, int weight, int time){
        if(this.verticesSize == 0){
            System.out.println("Grafo vacío");
        }
        else{
            vertice.nuevaRuta(ref, weight,time);
        }  
    }
    
    /**
     * Method that allows to eliminate a vertex of the graph.
     * @param vertice 
     * @restrictions vertice must be int
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
     * Method that removes an existing route
     * @param vertice 
     * @param referencia 
     * @param weight
     * vertice must be vertex, and weight and time must be ints
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
     * Method that finds the position of the vertex within the graph
     * @param vertice 
     * @param arreglo 
     * @return 
     * @restriction vertice must be an int, arreglo must be Vertex[]
     * 
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
     * Method that exchanges two vertices of position.
     * @param i 
     * @param j 
     * @restrictions i and j must br ints
     */
    public void swap(int i, int j){
        Vertex temp = vertices[i];
        vertices[i] = vertices[j];
        vertices[j] = temp;
    }
    
    /**
     * Method that returns the number of vertices of a graph.
     * @return Cantidad de vertices del grafo.
     */
    public int getVerticesLength(){
        return this.verticesSize;
    }
    
    /**
     * Method that returns the vertices of the graph.
     * @return 
     */   
    public Vertex[] getVertices(){
        return this.vertices;
    }

    /**
     * Method that returns the wbs of the proyect.
     * @return 
     */
    public Vertex[] getWbs() {
        return wbs;
    }    
}
