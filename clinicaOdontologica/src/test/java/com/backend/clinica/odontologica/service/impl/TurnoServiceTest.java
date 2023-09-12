package com.backend.clinica.odontologica.service.impl;

import com.backend.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.odontologica.entity.Odontologo;
import com.backend.clinica.odontologica.entity.Paciente;
import com.backend.clinica.odontologica.entity.Turno;
import com.backend.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    void deberiaInsertarUnTurnoConIdGenerado() {
        // Arrange
        Odontologo odontologo = new Odontologo("123", "Max", "Stravos");
        Paciente paciente = new Paciente("Juan", "Perez", 111111);
        LocalDateTime fechaYHora = LocalDateTime.of(2023, 12, 9, 10, 0);

        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(odontologo, paciente, fechaYHora);

        // Act
        ResponseEntity<TurnoSalidaDto> response = turnoService.registrarTurno(turnoEntradaDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

    @Test
    @Order(2)
    void deberiaRetornarUnTurnoPorId() throws ResourceNotFoundException {
        // Arrange
        Long idTurno = 1L;
        Odontologo odontologo = new Odontologo("123", "Max", "Stravos");
        Paciente paciente = new Paciente("Juan", "Perez", 111111);
        LocalDateTime fechaYHora = LocalDateTime.of(2023, 12, 9, 10, 0);

        Turno turno = new Turno(odontologo, paciente, fechaYHora);
        turno.setId(idTurno);

        // Act
        TurnoSalidaDto turnoSalidaDto = turnoService.buscarTurnoPorId(idTurno);

        // Assert
        assertNotNull(turnoSalidaDto);
        assertEquals(idTurno, turnoSalidaDto.getId());
    }

    @Test
    @Order(3)
    void alIntentarBuscarUnTurnoInexistente_deberiaLanzarUnaResourceNotFoundException() {
        // Arrange
        Long idInexistente = 999L;

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurnoPorId(idInexistente));
    }
}
