package com.backend.clinica.odontologica.service.impl;

import com.backend.clinica.odontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinica.odontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinica.odontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinica.odontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologoConMatricula123ConId1(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("123", "Max", "Stravos");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("123", odontologoSalidaDto.getMatricula());
        assertNotNull(odontologoSalidaDto.getId()); // Verificamos que se haya asignado un ID válido
    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDeOdontologos() {
        // Inserta un odontólogo de prueba en la base de datos antes de verificar la lista
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("456", "John", "Doe");
        odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertTrue(odontologoService.listarOdontologos().size() > 0);
    }


    @Test
    @Order(3)
    void alIntentarActualizarUnOdontologoInexistente_deberiaLanzarseUnaResourceNotFoundException(){
        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoModificacionEntradaDto.setId(00L); //Supongamos que 00L es un ID que no existe
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto));
    }

    @Test
    @Order(4)
    void alIntentarEliminarUnOdontologoYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
        try{
            odontologoService.eliminarOdontologo(1L); // Supongamos que el ID 1L ya ha sido eliminado
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}