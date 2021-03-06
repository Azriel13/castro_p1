package dev.castro.p1.Entities;

import dev.castro.p1.Enums.Status;
import lombok.Data;

import java.util.List;

@Data
    public class Expense{

    private int eid;
    private int expid;
    private double expammount;

    private Status approval;

    public Expense() {
    }

    public Expense(int eid, int expid, double expammount, Status approval) {
        this.eid = eid;
        this.expid = expid;
        this.expammount = expammount;
        this.approval = approval;
    }
}
