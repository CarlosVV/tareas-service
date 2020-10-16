package com.coopeuch.tareas.services.tareasservices;

import com.coopeuch.tareas.services.tareasservices.controllers.TareasController;
import com.coopeuch.tareas.services.tareasservices.models.Tarea;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import javax.swing.text.html.parser.Entity;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
@DisplayName("Integration Test con Crear, Actualizar, Eliminar, y obtener")
class TareasServicesApplicationTests {
    @Autowired
    TareasController controller;
    Tarea tarea1 = Tarea.builder().identificador(1)
            .descripcion("descripion")
            .fechaCreacion(Instant.now())
            .vigente(true).build();
	Tarea tarea2 = Tarea.builder().identificador(2)
			.descripcion("descripion")
			.fechaCreacion(Instant.now())
			.vigente(true).build();
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
	@DisplayName("Crear Tarea")
    void createTarea() {
        HttpEntity<Tarea> entity1 = new HttpEntity<>(tarea1);
		HttpEntity<Tarea> entity2 = new HttpEntity<>(tarea2);
        log.info("Crear Tareas");
        restTemplate.postForObject("http://localhost:" + port + "/v1/tareas/", entity1, Void.class);
		restTemplate.postForObject("http://localhost:" + port + "/v1/tareas/", entity2, Void.class);

		log.info("Obtener Tareas");
        ResponseEntity<Tarea> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/v1/tareas/1", Tarea.class);
		ResponseEntity<Tarea> responseEntity2 = this.restTemplate.getForEntity("http://localhost:" + port + "/v1/tareas/2", Tarea.class);
		ResponseEntity<Tarea[]> responseEntity3 = this.restTemplate.getForEntity("http://localhost:" + port + "/v1/tareas/", Tarea[].class);

        assertNotNull(responseEntity);
		assertEquals(responseEntity.getStatusCodeValue(), 200);
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getDescripcion(), entity1.getBody().getDescripcion());
		assertEquals(responseEntity.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS), entity1.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(responseEntity.getBody().getVigente(), entity1.getBody().getVigente());

		assertNotNull(responseEntity2);
		assertEquals(responseEntity2.getStatusCodeValue(), 200);
		assertNotNull(responseEntity2.getBody());
		assertEquals(responseEntity2.getBody().getDescripcion(), entity2.getBody().getDescripcion());
		assertEquals(responseEntity2.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS), entity2.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(responseEntity2.getBody().getVigente(), entity2.getBody().getVigente());


		assertNotNull(responseEntity3);
		assertEquals(responseEntity3.getStatusCodeValue(), 200);
		assertNotNull(responseEntity3.getBody());
		assertEquals(responseEntity3.getBody().length, 2);
		assertEquals(responseEntity3.getBody()[0].getDescripcion(), entity1.getBody().getDescripcion());
		assertEquals(responseEntity3.getBody()[0].getFechaCreacion().truncatedTo(ChronoUnit.SECONDS), entity1.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(responseEntity3.getBody()[0].getVigente(), entity1.getBody().getVigente());
		assertEquals(responseEntity3.getBody()[1].getDescripcion(), entity2.getBody().getDescripcion());
		assertEquals(responseEntity3.getBody()[1].getFechaCreacion().truncatedTo(ChronoUnit.SECONDS), entity1.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(responseEntity3.getBody()[1].getVigente(), entity2.getBody().getVigente());

		log.info("Actualizar Tarea");
		tarea2.setDescripcion("Descripcion cambiada");
		tarea2.setVigente(false);

		HttpEntity<Tarea> entity3 = new HttpEntity<>(tarea2);
		restTemplate.put("http://localhost:" + port + "/v1/tareas/2", entity3, Void.class);
		ResponseEntity<Tarea> responseEntity4 = this.restTemplate.getForEntity("http://localhost:" + port + "/v1/tareas/2", Tarea.class);

		assertNotNull(responseEntity4);
		assertEquals(responseEntity4.getStatusCodeValue(), 200);
		assertNotNull(responseEntity4.getBody());
		assertEquals(responseEntity4.getBody().getDescripcion(), entity3.getBody().getDescripcion());
		assertEquals(responseEntity4.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS), entity3.getBody().getFechaCreacion().truncatedTo(ChronoUnit.SECONDS));
		assertEquals(responseEntity4.getBody().getVigente(), entity3.getBody().getVigente());


		log.info("Eliminar Tarea");
		restTemplate.delete("http://localhost:" + port + "/v1/tareas/1", entity1, Void.class);
		ResponseEntity<Tarea> responseEntity5 = this.restTemplate.getForEntity("http://localhost:" + port + "/v1/tareas/1", Tarea.class);
		assertNotNull(responseEntity5);
		assertEquals(responseEntity5.getStatusCodeValue(), 404);
	}
}
