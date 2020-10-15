package com.coopeuch.tareas.services.tareasservices.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    @Id
    private Integer identificador;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Boolean vigente;
}
