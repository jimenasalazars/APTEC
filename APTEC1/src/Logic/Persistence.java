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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistence implements Serializable {
    
    public void saveTasks (LinkedList x) throws FileNotFoundException, IOException{
        ObjectOutputStream xToSave = new ObjectOutputStream(new FileOutputStream("Tasks.object"));
        xToSave.writeObject(x);
        xToSave.close();
    }
    public LinkedList retrieveTasks() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream xToGet = new ObjectInputStream(new FileInputStream("Tasks.object"));
        LinkedList x = (LinkedList) xToGet.readObject();
        xToGet.close();
        return x;
    }
    
    public void saveGraph (Graph x) throws FileNotFoundException, IOException{
        ObjectOutputStream xToSave = new ObjectOutputStream(new FileOutputStream("Graph.object"));
        xToSave.writeObject(x);
        xToSave.close();
    }
    public Graph retrieveGraph() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream xToGet = new ObjectInputStream(new FileInputStream("Graph.object"));
        Graph x = (Graph) xToGet.readObject();
        xToGet.close();
        return x;
    }
    public void saveResource (LinkedList x) throws FileNotFoundException, IOException{
        ObjectOutputStream xToSave = new ObjectOutputStream(new FileOutputStream("x.object"));
        xToSave.writeObject(x);
        xToSave.close();
    }
    public LinkedList retrieveResource () throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream xToGet = new ObjectInputStream(new FileInputStream("x.object"));
        LinkedList x = (LinkedList) xToGet.readObject();
        xToGet.close();
        return x;
    }
}
