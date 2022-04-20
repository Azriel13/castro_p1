package dev.castro.p1.Exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(int eid) {
        super("The Employee with EID:" + eid + " was not found");
    }
}
