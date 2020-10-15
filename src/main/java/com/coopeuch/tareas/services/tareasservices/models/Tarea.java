package com.coopeuch.tareas.services.tareasservices.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Tarea {
    private Integer identificador;
    private String descripcion;
    private LocalDate fechaCreacion;
    private Boolean vigente;
}
