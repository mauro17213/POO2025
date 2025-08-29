/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.solicitud;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Jhonatan Jimenez
 */
public class ConsultarSolicitudDTO implements Serializable {
    
    
    public String numeroDocumento;
    public String tipoDocumento;
    public LocalDate fechaNacimiento;
}
