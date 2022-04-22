package dev.castro.p1.Entities;

import lombok.Data;

@Data
    public class Expense {
    public Expense() {
    }

    private int eid;
    private int expid;
    private double expammount;
    private String approval;

    public Expense(int eid, int expid, double expammount, String approval) {
        this.eid = eid;
        this.expid = expid;
        this.expammount = expammount;
        this.approval = approval;
    }
}
