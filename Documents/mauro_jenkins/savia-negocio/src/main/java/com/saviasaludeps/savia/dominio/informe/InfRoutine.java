/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.informe;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aguevara
 */
public class InfRoutine implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String specificName;
    private String routineName;
    private Date create;
    private Date lastAltered;
    private String definer;
    private String routineDefinition;

    public InfRoutine(String specificName, String routineName, Date create, Date lastAltered, String definer, String routineDefinition) {
        this.specificName = specificName;
        this.routineName = routineName;
        this.create = create;
        this.lastAltered = lastAltered;
        this.definer = definer;
        this.routineDefinition = routineDefinition;
    }

    public InfRoutine() {
    }

    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getLastAltered() {
        return lastAltered;
    }

    public void setLastAltered(Date lastAltered) {
        this.lastAltered = lastAltered;
    }

    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    public String getRoutineDefinition() {
        return routineDefinition;
    }

    public void setRoutineDefinition(String routineDefinition) {
        this.routineDefinition = routineDefinition;
    }

    @Override
    public String toString() {
        return "InfRoutine{" + "specificName=" + specificName + ", routineName=" + routineName + ", create=" + create + ", lastAltered=" + lastAltered + ", definer=" + definer + ", routineDefinition=" + routineDefinition + '}';
    }
    
    
    
    
}
