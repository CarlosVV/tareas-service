package com.coopeuch.tareas.services.tareasservices.exceptions;

import lombok.Data;

@Data
public class TareaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private String errorMsg;

    public TareaNotFoundException() {
        super();
    }

    public TareaNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
