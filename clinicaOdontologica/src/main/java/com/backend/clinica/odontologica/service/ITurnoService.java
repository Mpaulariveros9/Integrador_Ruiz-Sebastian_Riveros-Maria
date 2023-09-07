package com.backend.clinica.odontologica.service;


import com.backend.clinica.odontologica.dto.entrada.modificacion.TurnoModificadoEntradaDto;
import com.backend.clinica.odontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinica.odontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinica.odontologica.exceptions.BadRequestException;
import com.backend.clinica.odontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId(Long id);
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    TurnoSalidaDto modificarTurno(TurnoModificadoEntradaDto turnoModificado);

}
