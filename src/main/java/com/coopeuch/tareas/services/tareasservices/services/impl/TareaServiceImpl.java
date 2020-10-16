package com.coopeuch.tareas.services.tareasservices.services.impl;

import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import com.coopeuch.tareas.services.tareasservices.repository.TareasRepository;
import com.coopeuch.tareas.services.tareasservices.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {
    private final TareasRepository tareasRepository;

    @Override
    public void createTarea(Tarea tarea) {
        tareasRepository.saveAndFlush(tarea);
    }

    @Override
    public void updateTarea(Tarea tarea) {
        tareasRepository.saveAndFlush(tarea);
    }

    @Override
    public Tarea retrieveTarea(Integer identificador) {
        Tarea tarea = tareasRepository.getOne(identificador);
        return Tarea.builder()
                .identificador(identificador)
                .descripcion(tarea.getDescripcion())
                .fechaCreacion(tarea.getFechaCreacion())
                .vigente(tarea.getVigente()).build();
    }

    @Override
    public List<Tarea> retrieveAllTarea() {
        return tareasRepository.findAll();
    }

    @Override
    public void removeTarea(Integer identificador) {
        Tarea tarea = tareasRepository.getOne(identificador);
        tareasRepository.delete(tarea);
    }
}
