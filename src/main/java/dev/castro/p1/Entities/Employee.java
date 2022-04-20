package dev.castro.p1.Entities;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    public Employee(){

    }

    public Employee(String eName, int eID) {
        this.eName = eName;
        this.eID = eID;
    }

    private String eName;

    private int eID;

}
