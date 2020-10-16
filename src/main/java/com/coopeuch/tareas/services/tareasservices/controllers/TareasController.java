package com.coopeuch.tareas.services.tareasservices.controllers;

import com.coopeuch.tareas.services.tareasservices.exceptions.TareaNotFoundException;
import com.coopeuch.tareas.services.tareasservices.exceptions.TareaValidationException;
import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import com.coopeuch.tareas.services.tareasservices.services.TareaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Slf4j
@Api("Conjunto de endpoints para crear, obtener, actualizar y borrar tareas")
@RequestMapping("/v1/tareas/")
public class TareasController {
    @Autowired
    private TareaService tareaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Crear nueva Tarea.")
    public void create(@ApiParam("Informacion de la tarea para ser creado.")
                       @RequestBody Tarea request) {
        log.info("id = {}", request.getIdentificador());
        tareaService.createTarea(request);
    }

    @PutMapping(value = "/{identificador}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Actualiza Tarea.")
    public void update(@ApiParam("Informacion de la tarea a ser actualizado.")
                       @PathVariable Integer identificador, @RequestBody Tarea request) throws TareaNotFoundException, TareaValidationException {
        log.info("id = {}", identificador);

        Tarea tarea = null;
        try {
            tareaService.retrieveTarea(identificador);
        } catch (EntityNotFoundException ex) {
            throw new TareaNotFoundException();
        }

        if (tarea != null && !tarea.getIdentificador().equals(identificador)) {
            throw new TareaValidationException();
        }

        try {
            tareaService.updateTarea(request);
        } catch (EntityNotFoundException ex) {
            throw new TareaNotFoundException();
        }
    }

    @GetMapping(value = "/{identificador}")
    @ApiOperation("Retorna una tarea por su identificador. 404 si el identificador de la tarea no existe")
    public Tarea get(@ApiParam("Identificador de la tarea a ser eliminada. No puede estar vacio.")
                     @PathVariable Integer identificador) throws TareaNotFoundException {
        log.info("id = {}", identificador);
        try {
            return tareaService.retrieveTarea(identificador);
        } catch (EntityNotFoundException ex) {
            throw new TareaNotFoundException();
        }
    }

    @GetMapping()
    @ApiOperation("Retorna una lista de todas las tareas en el Sistema.")
    public List<Tarea> getAll() {
        log.info("GetAll ");
        return tareaService.retrieveAllTarea();
    }

    @DeleteMapping("/{identificador}")
    @ApiOperation("Elimina una Tarea del sistema. 404 si el identificador de la tarea no existe")
    public void delete(
            @ApiParam("Identificador de la tarea a ser eliminado. No puede estar vacio.")
            @PathVariable Integer identificador) throws TareaNotFoundException {
        log.info("id = {}", identificador);
        try {
            tareaService.removeTarea(identificador);
        } catch (EntityNotFoundException ex) {
            throw new TareaNotFoundException();
        }
    }
}
