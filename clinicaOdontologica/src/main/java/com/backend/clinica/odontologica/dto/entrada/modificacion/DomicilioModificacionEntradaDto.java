package com.backend.clinica.odontologica.dto.entrada.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioModificacionEntradaDto {

    @NotNull(message = "Indique el id del domicilio que se desea modificar")
    private Long id;

    @NotNull(message = "El campo calle esta vacio")
    @NotBlank(message = "El campo calle esta vacio")
    private String calle;

    @NotNull(message = "El numero esta vacio")
    @Digits(integer = 8, fraction = 0, message = "El número puede tener máximo 8 dígitos")
    private Integer numero;

    @NotNull(message = "La localidad no debe estar vacia")
    @NotBlank(message = "Llene el campo localidad")
    private String localidad;

    @NotNull(message = "La provincia no debe estar vacia")
    @NotBlank(message = "llene el nombre de la provincia")
    private String provincia;

    public DomicilioModificacionEntradaDto() {
    }

    public DomicilioModificacionEntradaDto(Long id, String calle, int numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
