package com.coopeuch.tareas.services.tareasservices.controllers;

import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TareasController {
    @PostMapping("/tareas")
    public void create(@RequestBody Tarea request) {
        log.info("id = {}", request.getIdentificador());
    }

    @PutMapping("/tareas/{identificador}")
    public void update(@PathVariable Integer identificador, @RequestBody Tarea request) {
        log.info("id = {}", request.getIdentificador());
    }

    @GetMapping("/tareas/{identificador}")
    public void get(@PathVariable Integer identificador) {
        log.info("id = {}", identificador);
    }

    @DeleteMapping("/tareas/{identificador}")
    public void delete(@PathVariable Integer identificador) {
        log.info("id = {}", identificador);
    }
}
