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
public class Task {
    
    
    private int ID;
    private String description;
    private int effort;
    private String inCharge;
    private String type;
    private String NumTask;

    public Task(int ID, String description, int effort, String inCharge, String type) {
        this.ID = ID;
        this.description = description;
        this.effort = effort;
        this.inCharge = inCharge;
        this.type = type;
        //this.NumTask="Task #"+Task.listTask.getsize();
    }

    public String getNumTask() {
        return NumTask;
    }

    public void setNumTask(int numtask) {
        this.NumTask="Task #"+numtask;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public String getInCharge() {
        return inCharge;
    }

    public void setInCharge(String inCharge) {
        this.inCharge = inCharge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
