package dev.castro.p1.Exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(int id) {
        super("The resource with id:" + id + " was not found");
    }
}
