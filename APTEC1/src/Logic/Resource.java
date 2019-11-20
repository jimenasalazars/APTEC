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
 * @author samue
 */
public class Resource implements Serializable {
    private String ID;
    private String NameResouce;
    private String Type;
    private String Capacity;
    private String Available;
    private String inCharge;

    public Resource(String ID, String NameResouce, String Type, String Capacity, String Available, String inCharge) {
        this.ID = ID;
        this.NameResouce = NameResouce;
        this.Type = Type;
        this.Capacity = Capacity;
        this.Available = Available;
        this.inCharge = inCharge;
    }

    public void addlist() {
        Controller.listResources.insert(this);
    }
    
    public String getID() {
        return ID;
    }

    public String getNameResouce() {
        return NameResouce;
    }

    public String getType() {
        return Type;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getAvailable() {
        return Available;
    }

    public String getInCharge() {
        return inCharge;
    }
    
    
    
    
    
}
