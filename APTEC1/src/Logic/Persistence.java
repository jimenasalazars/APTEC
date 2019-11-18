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
public class Persistence {
    public void save (int x) throws FileNotFoundException, IOException{
        ObjectOutputStream xToSave = new ObjectOutputStream(new FileOutputStream("x.object"));
        xToSave.writeObject(x);
        xToSave.close();
    }
    public int retrieve() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream xToGet = new ObjectInputStream(new FileInputStream("x.object"));
        int x = (int) xToGet.readObject();
        xToGet.close();
        return x;
    }
}
