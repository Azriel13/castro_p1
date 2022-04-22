package dev.castro.p1.Exceptions;

public class ResourceNotFound2 extends RuntimeException {
    public ResourceNotFound2(String Approval) {
        super("The resource was not found");
    }
}
