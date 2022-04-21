package dev.castro.p1.Entities;

import lombok.Data;

@Data
public class Expense {
    public Expense() {
    }

    public Expense(int eid, int expidd, double expammount, String approval) {
        this.eid = eid;
        this.expidd = expidd;
        this.expammount = expammount;
        this.approval = approval;
    }

    private int eid;
    private int expidd;
    private double expammount;
    private String approval;
}
