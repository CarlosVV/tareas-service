package com.coopeuch.tareas.services.tareasservices.controllers;

import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import com.coopeuch.tareas.services.tareasservices.services.TareaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TareasController {
    @Autowired
    private TareaService tareaService;

    @PostMapping("/tareas")
    public void create(@RequestBody Tarea request) {
        log.info("id = {}", request.getIdentificador());
        tareaService.createTarea(request);
    }

    @PutMapping("/tareas/{identificador}")
    public void update(@PathVariable Integer identificador, @RequestBody Tarea request) {
        log.info("id = {}", identificador);
        tareaService.updateTarea(request);
    }

    @GetMapping("/tareas/{identificador}")
    public Tarea get(@PathVariable Integer identificador) {
        log.info("id = {}", identificador);
        return tareaService.retrieveTarea(identificador);
    }

    @GetMapping("/tareas")
    public List<Tarea> getAll() {
        log.info("GetAll ");
        return tareaService.retrieveAllTarea();
    }

    @DeleteMapping("/tareas/{identificador}")
    public void delete(@PathVariable Integer identificador) {
        log.info("id = {}", identificador);
        tareaService.removeTarea(identificador);
    }
}
