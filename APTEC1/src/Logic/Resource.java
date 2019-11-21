/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import ControllerPackage.Controller;
import java.io.Serializable;

/**
 *
 * @author Samuel and Jimena
 */
public class Resource implements Serializable {
    //Atributes
    private String ID;
    private String NameResouce;
    private String Type;
    private String Capacity;
    private String Available;
    private String inCharge;

    /**
     * Constructor
     * @param ID
     * @param NameResouce
     * @param Type
     * @param Capacity
     * @param Available
     * @param inCharge 
     */
    public Resource(String ID, String NameResouce, String Type, String Capacity, String Available, String inCharge) {
        this.ID = ID;
        this.NameResouce = NameResouce;
        this.Type = Type;
        this.Capacity = Capacity;
        this.Available = Available;
        this.inCharge = inCharge;
    }

    /**
     * to add
     */
    public void addlist() {
        Controller.listResources.insert(this);
    }
    
    /**
     * gets an id
     * @return 
     */
    public String getID() {
        return ID;
    }

    /**
     * gets a name
     * @return 
     */
    public String getNameResouce() {
        return NameResouce;
    }

    /**
     * gets a type
     * @return 
     */
    public String getType() {
        return Type;
    }

    /**
     * gets capacity
     * @return 
     */
    public String getCapacity() {
        return Capacity;
    }

    /**
     * gets availability
     * @return 
     */
    public String getAvailable() {
        return Available;
    }

    /**
     * gets person in charge
     * @return 
     */
    public String getInCharge() {
        return inCharge;
    }
    
    
    
    
    
}
