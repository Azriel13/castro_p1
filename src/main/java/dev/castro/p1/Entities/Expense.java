package dev.castro.p1.Entities;

import dev.castro.p1.Enums.Status;
import lombok.Data;

import java.util.List;

@Data
    public class Expense{
    public Expense() {
    }

    private int eid;
    private int expid;
    private double expammount;

    private Status approval = Status.Pending;

    public Expense(int eid, int expid, double expammount) {
        this.eid = eid;
        this.expid = expid;
        this.expammount = expammount;
    }
}
