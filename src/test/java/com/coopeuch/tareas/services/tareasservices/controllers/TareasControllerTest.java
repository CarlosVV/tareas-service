package com.coopeuch.tareas.services.tareasservices.controllers;

import com.coopeuch.tareas.services.tareasservices.exceptions.TareaNotFoundException;
import com.coopeuch.tareas.services.tareasservices.exceptions.TareaValidationException;
import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import com.coopeuch.tareas.services.tareasservices.services.impl.TareaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName("Test Tarea Controller")
class TareasControllerTest {
    @InjectMocks
    TareasController controller;

    @Mock
    TareaServiceImpl tareaService;

    Tarea tarea = Tarea.builder().identificador(1)
            .descripcion("descripion")
            .fechaCreacion(Instant.now())
            .vigente(true).build();

    List<Tarea> tareaList = Arrays.asList(Tarea.builder().identificador(1)
            .descripcion("descripion 1")
            .fechaCreacion(Instant.now())
            .vigente(true).build(), Tarea.builder().identificador(1)
            .descripcion("descripion 2")
            .fechaCreacion(Instant.now())
            .vigente(true).build());

    Integer identificador = 1;

    @Test
    @DisplayName("Crear Tarea")
    void createTest() {
        controller.create(tarea);
        verify(tareaService).createTarea(tarea);
    }

    @Test
    @DisplayName("Actualizar Tarea")
    void updateTest() throws TareaNotFoundException, TareaValidationException {
        controller.update(identificador, tarea);
        verify(tareaService).updateTarea(tarea);
    }

    @Test
    @DisplayName("Retornar una Tarea")
    void getTest() throws TareaNotFoundException {
        when(tareaService.retrieveTarea(identificador)).thenReturn(tarea);
        Tarea res = controller.get(identificador);
        assertNotNull(res);
        verify(tareaService).retrieveTarea(identificador);
    }

    @Test
    @DisplayName("Retornar todas las Tareas")
    void getAllTest() {
        when(tareaService.retrieveAllTarea()).thenReturn(tareaList);
        List<Tarea> res = controller.getAll();
        assertNotNull(res);
        verify(tareaService).retrieveAllTarea();
    }

    @Test
    @DisplayName("Eliminar Tarea")
    void deleteTest() throws TareaNotFoundException {
        controller.delete(identificador);
        verify(tareaService).removeTarea(identificador);
    }
}
