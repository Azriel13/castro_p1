package dev.castro.p1.Exceptions;

public class DuplicateResource extends RuntimeException{
    public DuplicateResource(){
        super("The resource already exists");
    }
}
