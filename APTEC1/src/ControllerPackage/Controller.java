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
 * @author Samuel and Jimena
 */
public class Controller {
    
    //Atributes
    public static LinkedList<Logic.Task> listTask;
    public static LinkedList<Resource> listResources;
    public static Graph grafo;
    
    /**
     * Creates a new vertex (task)
     * @param ID
     * @param description
     * @param effort
     * @param inCharge
     * @param type 
     * @restrictions ID and effort must be int, description, inCharge and type must be String
     */
    public static void register_TaskRegister(int ID, String description, int effort, String inCharge, String type){
        Logic.Task newT = new Logic.Task(ID, description, effort, inCharge, type);
        grafo.nuevoVertice(newT);
    }
    
    /**
     * Creates a new dependency
     * @param start
     * @param end
     * @param effort
     * @param time 
     * @restrictions all the parameters must be ints
     */
    public static void register_CriticalRoute(int start, int end, int effort, int time){
        grafo.agregarRuta(grafo.vertices[start-1],grafo.vertices[end-1], effort, time);
    }
    
    /**
     * Shows all dependencies
     * @return 
     */
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
    
    /**
     * Sort
     * @param A
     * @param izq
     * @param der
     * @return 
     * @restrictions A[] must be Vertex , izq and der must be ints
     */
    public static Vertex[] quicksort(Vertex A[], int izq, int der) {

        Vertex pivote = A[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        Vertex aux;

        while (i < j) {            // mientras no se crucen las búsquedas
            if (A[i] == null) {
                i=i+1;
            }
            if (A[j] == null){ 
                j=j-1;
            } else {
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
    
    /**
     * Puts info in the JTree to show it in the interface
     * @param WBS 
     * @restrictions WBS must be JTree
     */
    public static void FillDataToJTree(JTree WBS){
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Project");
        Vertex[] copia = grafo.getWbs();
        copia = quicksort(copia, 0, copia.length-1);
        for (Vertex vertice : copia) {
            if (vertice == null) {

            } else {
                DefaultMutableTreeNode tarea = new DefaultMutableTreeNode(vertice.getElement().getNumTask());
                raiz.add(tarea);
                Vertex verticeActual = vertice;
                LinkedList<Edge> arista = verticeActual.getAristas();
                arista.goToStart();
                arista.next();
                //DefaultTreeModel finalT = new DefaultTreeModel(raiz);
                //WBS.setModel(finalT);
                for (int m = 0; m < arista.getSize(); m++) {
                    if (m == arista.getSize() - 1) {
                        Edge dato1 = (Edge) arista.getElement();
                        Vertex dato2 = dato1.getReference();
                        Logic.Task dato3 = dato2.getElement();
                        DefaultMutableTreeNode dependencia = new DefaultMutableTreeNode(dato3.getDescription());
                        tarea.add(dependencia);
                        arista.next();
                        DefaultTreeModel finalT = new DefaultTreeModel(raiz);
                        WBS.setModel(finalT);
                    } else {
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
            }
            WBS.setVisible(true);
        }
            
    }
    
    /**
     * Shows the route
     * @param arrayV
     * @param arrayD
     * @return 
     * @restrictions arrayV and arrayD must be Vertex[]
     */
        public static String PrintPath(Vertex[] arrayV, Vertex[] arrayD){
        if (arrayV == null) {
            String diagram = "Begining --> ";
            for (Vertex vertice : arrayD) {
                if (vertice == null) {

                } else {
                    diagram = diagram + vertice.getElement().getNumTask() + " --> ";
                    Vertex verticeActual = vertice;
                    LinkedList<Edge> arista = verticeActual.getAristas();
                    arista.goToStart();
                    arista.next();
                }

            }
            diagram = diagram + "End";
            //System.out.print(diagram);
            return diagram;
        } else {
            String diagram = "Begining --> ";
            for (Vertex vertice : arrayV) {
                if (vertice == null) {

                } else {
                    diagram = diagram + vertice.getElement().getNumTask() + " --> ";
                    Vertex verticeActual = vertice;
                    LinkedList<Edge> arista = verticeActual.getAristas();
                    arista.goToStart();
                    arista.next();
                }

            }
            diagram = diagram + "End";
            //System.out.print(diagram);
            return diagram;
        }
    }
        
        /**
         * Shows all the tasks
         * @return 
         */
        public static String LoadTasks(){
            LinkedList.Node head = listTask.getHead().getNext();
            String data = "";
            for (int i = 0; i < listTask.getSize(); i++) {
                Task ta = (Task) head.getElement();
                data = data +ta.getNumTask()+" ID: "+ta.getID()+" Description: "+ta.getDescription()+" Effort: "+ta.getEffort()+" In Charge: "+ta.getInCharge()+" Type: "+ta.getType();
                data = data + "\n";
                head = head.getNext();
            }
            return data;
        }
        
        /**
         * Shows all the resources
         * @return 
         */
        public static String LoadResource(){
            LinkedList.Node head = listResources.getHead().getNext();
            String data = "";
            for (int i = 0; i < listResources.getSize(); i++) {
                Resource ta = (Resource) head.getElement();
                data = data + ta.getNameResouce()+" ID: "+ta.getID()+" Type: "+ta.getType()+" Capacity: "+ta.getCapacity()+" Availability: "+ta.getAvailable()+" In Charge: "+ta.getInCharge();
                data = data + "\n";
                head = head.getNext();
            }
            return data;
        }
        
        /**
         * Creates a new resource
         * @param ID
         * @param NameResouce
         * @param Type
         * @param Capacity
         * @param Available
         * @param inCharge
         * @restrictions ID, NameResouce, Type, Capacity, Available, and inCharge must be string
         */
        public static void createResource(String ID, String NameResouce, String Type, String Capacity, String Available, String inCharge){
            Logic.Resource Re = new Logic.Resource(ID, NameResouce, Type, Capacity, Available, inCharge);
            listResources.insert(Re);
        }
}
