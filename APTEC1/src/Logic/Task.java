/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.Serializable;

/**
 *
 * @author Samuel and Jimena
 */
public class Task implements Serializable {
    
    //Atributes
    private int ID;
    private String description;
    private int effort;
    private String inCharge;
    private String type;
    private String NumTask;

    /**
     * Constructor
     * @param ID
     * @param description
     * @param effort
     * @param inCharge
     * @param type 
     */
    public Task(int ID, String description, int effort, String inCharge, String type) {
        this.ID = ID;
        this.description = description;
        this.effort = effort;
        this.inCharge = inCharge;
        this.type = type;
        //this.NumTask="Task #"+Task.listTask.getsize();
    }

    /**
     * Gets task
     * @return 
     */
    public String getNumTask() {
        return NumTask;
    }

    /**
     * Sets numTask
     * @param numtask 
     * @restriction numtask must be int
     */
    public void setNumTask(int numtask) {
        this.NumTask="Task #"+numtask;
    }
    
    /**
     *  Gets ID
     * @return 
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets ID
     * @param ID 
     * @restriction ID must be int
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *  Gets description
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     * @param description 
     * @restriction description must be string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  Gets effort
     * @return 
     */
    public int getEffort() {
        return effort;
    }

    /**
     * Sets effort
     * @param effort 
     * @restriction effort must be int
     */
    public void setEffort(int effort) {
        this.effort = effort;
    }

    /**
     *  Gets person in charge
     * @return 
     */
    public String getInCharge() {
        return inCharge;
    }

    /**
     * Sets in charge
     * @param inCharge 
     * @restriction inCharge must be string
     */
    public void setInCharge(String inCharge) {
        this.inCharge = inCharge;
    }

    /**
     *  Gets type
     * @return 
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type
     * @param type 
     * @restriction type must be string
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
