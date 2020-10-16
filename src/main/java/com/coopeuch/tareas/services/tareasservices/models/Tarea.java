package com.coopeuch.tareas.services.tareasservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Clase para registro de tareas.")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Identificador unico de la Tarea.")
    private Integer identificador;

    @NotBlank
    @Size(min = 1, max = 255)
    @ApiModelProperty(notes = "Descripcion de la Tarea. No puede estar vacio.")
    private String descripcion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Past
    @NotNull
    @ApiModelProperty(notes = "Fecha de creacion de la tarea. En el formato: yyyy-MM-dd HH:mm:ss", value = "2017-05-04 09:24:20")
    private Instant fechaCreacion;

    @NotNull
    @ApiModelProperty(notes = "Indicador de Vigencia de la Tarea. Valores son true o false.")
    private Boolean vigente;
}
