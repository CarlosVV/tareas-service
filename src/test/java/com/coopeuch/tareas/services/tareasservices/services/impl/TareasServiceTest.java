package com.coopeuch.tareas.services.tareasservices.services.impl;

import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import com.coopeuch.tareas.services.tareasservices.repository.TareasRepository;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName("Test Tarea Servicio")
class TareasServiceTest {
    @InjectMocks
    TareaServiceImpl tareaService;
    @Mock
    TareasRepository tareasRepository;

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
        tareaService.createTarea(tarea);
        verify(tareasRepository).saveAndFlush(tarea);
    }

    @Test
    @DisplayName("Actualizar Tarea")
    void updateTest() {
        tareaService.createTarea(tarea);
        verify(tareasRepository).saveAndFlush(tarea);
    }

    @Test
    @DisplayName("Eliminar Tarea")
    void deleteTest() {
        when(tareasRepository.getOne(identificador)).thenReturn(tarea);
        tareaService.removeTarea(identificador);
        verify(tareasRepository).getOne(identificador);
        verify(tareasRepository).delete(tarea);
    }

    @Test
    @DisplayName("Retornar una tarea")
    void getTest() {
        when(tareasRepository.getOne(identificador)).thenReturn(tarea);
        Tarea t = tareaService.retrieveTarea(identificador);
        assertNotNull(t);
        verify(tareasRepository).getOne(identificador);
    }

    @Test
    @DisplayName("Retornar todas las Tareas")
    void getAllTest() {
        when(tareasRepository.findAll()).thenReturn(tareaList);
        List<Tarea> tareas = tareaService.retrieveAllTarea();
        assertNotNull(tareas);
        verify(tareasRepository).findAll();
    }
}
