/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerPackage;
import Logic.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author samue
 */
public class Controller {
    public static LinkedList<Logic.Task> listTask;
    public static Graph grafo;
    
    public static void register_TaskRegister(int ID, String description, int effort, String inCharge, String type){
        Logic.Task newT = new Logic.Task(ID, description, effort, inCharge, type);
        grafo.nuevoVertice(newT);
    }
    
    public static void register_CriticalRoute(int start, int end, int effort, int time){
        grafo.agregarRuta(grafo.vertices[start-1],grafo.vertices[end-1], effort, time);
    }
    
    public static String getDependencies(){
        String diagram = "";
        for (Vertex vertice : grafo.vertices) {
            if (vertice ==null){
                
            }else{
            diagram = diagram+"\n";
            diagram = diagram+"Begining --> "+vertice.getElement().getNumTask() + " --> ";
            Vertex verticeActual = vertice;
            LinkedList<Edge> arista = verticeActual.getAristas();           
            arista.goToStart();
            arista.next();
            for(int m = 0; m < arista.getSize(); m++){
                if(m == arista.getSize() - 1){
                    Edge dato1 = (Edge) arista.getElement();
                    Vertex dato2 = dato1.getReference();
                    Logic.Task dato3 = dato2.getElement();

                    diagram = diagram+ dato3.getDescription() + " --> ";
                    arista.next();
                }else{
                    Edge dato1 = (Edge) arista.getElement();
                    Vertex dato2 = dato1.getReference();
                    Logic.Task dato3 = dato2.getElement();
                    
                    diagram = diagram+dato3.getDescription()+ " --> ";
                    arista.next();
                }
            }
            diagram = diagram+"End";
            diagram = diagram+"\n";
        }
        }
        //System.out.print(diagram);
        return diagram;
    }
    
    public static Vertex[] quicksort(Vertex A[], int izq, int der) {

        Vertex pivote = A[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        Vertex aux;

        while (i < j) {            // mientras no se crucen las búsquedas
            while (A[i].getElement().getEffort() <= pivote.getElement().getEffort() && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (A[j].getElement().getEffort() > pivote.getElement().getEffort()) {
                j--;         // busca elemento menor que pivote
            }
            if (i < j) {                      // si no se han cruzado                      
                aux = A[i];                  // los intercambia
                A[i] = A[j];
                A[j] = aux;
            }
        }
        A[izq] = A[j]; // se coloca el pivote en su lugar de forma que tendremos
        A[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            quicksort(A, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(A, j + 1, der); // ordenamos subarray derecho
        }
        return A;
    }
    public static void FillDataToJTree(JTree WBS){
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Project");
        Vertex[] copia = grafo.getWbs();
        copia = quicksort(copia, 0, copia.length-1);
        for (Vertex vertice : copia) {
            DefaultMutableTreeNode tarea = new DefaultMutableTreeNode(vertice.getElement().getNumTask());
            raiz.add(tarea);
            Vertex verticeActual = vertice;
            LinkedList<Edge> arista = verticeActual.getAristas();           
            arista.goToStart();
            arista.next();
            //DefaultTreeModel finalT = new DefaultTreeModel(raiz);
            //WBS.setModel(finalT);
            for(int m = 0; m < arista.getSize(); m++){
                if(m == arista.getSize() - 1){
                    Edge dato1 = (Edge) arista.getElement();
                    Vertex dato2 = dato1.getReference();
                    Logic.Task dato3 = dato2.getElement();
                    DefaultMutableTreeNode dependencia = new DefaultMutableTreeNode(dato3.getDescription());
                    tarea.add(dependencia);
                    arista.next();
                    DefaultTreeModel finalT = new DefaultTreeModel(raiz);
                    WBS.setModel(finalT);
                }else{
                    Edge dato1 = (Edge) arista.getElement();
                    Vertex dato2 = dato1.getReference();
                    Logic.Task dato3 = dato2.getElement();
                    DefaultMutableTreeNode dependencia = new DefaultMutableTreeNode(dato3.getDescription());
                    tarea.add(dependencia);
                    arista.next();
                    DefaultTreeModel finalT = new DefaultTreeModel(raiz);
                    WBS.setModel(finalT);
                }
            }
            WBS.setVisible(true);
        }
            
    }
    
        public static String PrintPath(Vertex[] arrayV, Vertex[] arrayD){
        if (arrayV == null) {
            String diagram = "Begining --> ";
            for (Vertex vertice : arrayD) {
                diagram = diagram + vertice.getElement().getNumTask() + " --> ";
                Vertex verticeActual = vertice;
                LinkedList<Edge> arista = verticeActual.getAristas();
                arista.goToStart();
                arista.next();

            }
            diagram = diagram + "End";
            //System.out.print(diagram);
            return diagram;
        } else {
            String diagram = "Begining --> ";
            for (Vertex vertice : arrayV) {
                diagram = diagram + vertice.getElement().getNumTask() + " --> ";
                Vertex verticeActual = vertice;
                LinkedList<Edge> arista = verticeActual.getAristas();
                arista.goToStart();
                arista.next();

            }
            diagram = diagram + "End";
            //System.out.print(diagram);
            return diagram;
        }
    }
    
}