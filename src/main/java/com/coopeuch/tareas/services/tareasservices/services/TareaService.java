package com.coopeuch.tareas.services.tareasservices.services;

import com.coopeuch.tareas.services.tareasservices.models.Tarea;

import java.util.List;

public interface TareaService {
    void createTarea(Tarea tarea);
    void updateTarea(Tarea tarea);
    Tarea retrieveTarea(Integer identificador);
    List<Tarea> retrieveAllTarea();
    void removeTarea(Integer identificador);
}
