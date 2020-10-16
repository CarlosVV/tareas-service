package com.coopeuch.tareas.services.tareasservices.exceptions;

public class TareaValidationException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private String errorMsg;

    public TareaValidationException() {
        super();
    }

    public TareaValidationException(String errorMsg) {
        super(errorMsg);
    }

}
