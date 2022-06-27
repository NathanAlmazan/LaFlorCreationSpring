package com.nathanael.florcreation.errors;

public class EntityExceptions extends RuntimeException {
    public EntityExceptions(String entity, String reason) {
        super(entity + ": " + reason);
    }
}
