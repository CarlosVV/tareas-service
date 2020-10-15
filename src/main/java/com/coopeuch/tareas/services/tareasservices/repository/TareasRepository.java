package com.coopeuch.tareas.services.tareasservices.repository;

import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository extends JpaRepository<Tarea, Integer> {
}
