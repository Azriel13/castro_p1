package dev.castro.p1.Exceptions;

import dev.castro.p1.Enums.Status;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(int id) {
        super("The resource with id:" + id + " was not found");
    }
    public ResourceNotFound(Status approval) {
        super("The resource with approval:" + approval + " was not found");
    }
}
