package com.backend.clinica.odontologica.service.impl;

import com.backend.clinica.odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinica.odontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinica.odontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinica.odontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.odontologica.entity.Turno;
import com.backend.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    /*
    private Long pacienteId;
    private Long odontologoId;

    @BeforeEach
    void setUp() throws ResourceNotFoundException {
        // Crea un paciente
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Diaz", 111111, LocalDate.of(2023, 12, 9), new DomicilioEntradaDto("calle", 1232, "localidad", "provincia"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        pacienteId = pacienteSalidaDto.getId();

        // Crea un odontólogo con una matrícula única
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("Matricula" + System.currentTimeMillis(), "Dr. Garzon", "ApellidoOdontologo");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        odontologoId = (long) odontologoSalidaDto.getId();
    }

    @Test
    @Order(1)
    void deberiaInsertarYRecuperarUnTurno() throws ResourceNotFoundException {
        // Crea un turno
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto();
        turnoEntradaDto.setOdontologoId(odontologoId);
        turnoEntradaDto.setPacienteId(pacienteId);
        turnoEntradaDto.setFechaYHora(LocalDateTime.of(2023, 12, 10, 14, 0));

        // Registra el turno
        TurnoSalidaDto turnoSalidaDto = null;
        try {
            turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(turnoSalidaDto.getId());

        // Recupera el turno y verifica que los datos sean consistentes
        TurnoSalidaDto turnoRecuperado = turnoService.buscarTurnoPorId(turnoSalidaDto.getId());

        // Utiliza ModelMapper para mapear los objetos DTO a las entidades
        Turno turnoMapeado = modelMapper.map(turnoRecuperado, Turno.class);  // Asumiendo que tienes una entidad "Turno"

        // Verifica las propiedades mapeadas
        assertEquals(odontologoId, turnoMapeado.getOdontologo().getId());
        assertEquals(pacienteId, turnoMapeado.getPaciente().getId());
        assertEquals(LocalDateTime.of(2023, 12, 10, 14, 0), turnoMapeado.getFechaYHora());
    }*/

    @Test
    void deberiaRetornarNullSiElIdNoExiste() {
        TurnoSalidaDto turnoSalidaDto = turnoService.buscarTurnoPorId(2L);
        assertNull(turnoSalidaDto);
    }

    @Test
    void deberiaBuscarTurnoPorId1() {
        TurnoSalidaDto turnoSalidaDto = turnoService.buscarTurnoPorId(1L);
        assertNull(turnoSalidaDto);
    }

    /*@Test
    void deberiaRetornarUnaListaNoVaciaDeTurnos() {
        // Listar turnos
        List<TurnoSalidaDto> turnos = turnoService.listarTurnos();
        assertTrue(turnos.size() > 0);
    }*/



    @Test
    void alIntentarEliminarUnTurnoInexistente_deberiaLanzarseUnaResourceNotFoundException() {
        // Eliminar un turno que no existe
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(9999L));
    }
}
