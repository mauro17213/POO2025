/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class VAsegNovedad extends Auditoria {

    private int id;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaExpedicionDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    private String sexo;
    private String departamentoAfiliacion;
    private String municipioAfiliacion;
    private String estadoAfiliacion;
    private String contratoAfiliado;
    private String zona;
    private String direccionAfiliado;
    private String telefono;
    private String celular;
    private String email;
    private String observacion;
    private Date fechaHoraNotificacion;
    
    // campos adicionales
    private boolean novedadActualizada;

    public VAsegNovedad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDepartamentoAfiliacion() {
        return departamentoAfiliacion;
    }

    public void setDepartamentoAfiliacion(String departamentoAfiliacion) {
        this.departamentoAfiliacion = departamentoAfiliacion;
    }

    public String getMunicipioAfiliacion() {
        return municipioAfiliacion;
    }

    public void setMunicipioAfiliacion(String municipioAfiliacion) {
        this.municipioAfiliacion = municipioAfiliacion;
    }

    public String getEstadoAfiliacion() {
        return estadoAfiliacion;
    }

    public void setEstadoAfiliacion(String estadoAfiliacion) {
        this.estadoAfiliacion = estadoAfiliacion;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDireccionAfiliado() {
        return direccionAfiliado;
    }

    public void setDireccionAfiliado(String direccionAfiliado) {
        this.direccionAfiliado = direccionAfiliado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraNotificacion() {
        return fechaHoraNotificacion;
    }

    public void setFechaHoraNotificacion(Date fechaHoraNotificacion) {
        this.fechaHoraNotificacion = fechaHoraNotificacion;
    }

    /**
     * @return the novedadActualizada
     */
    public boolean isNovedadActualizada() {
        return novedadActualizada;
    }

    /**
     * @param novedadActualizada the novedadActualizada to set
     */
    public void setNovedadActualizada(boolean novedadActualizada) {
        this.novedadActualizada = novedadActualizada;
    }
    
    @Override
    public String toString() {
        String mensaje = id + "," +
        tipoDocumento + "," +
        numeroDocumento + "," +
        fechaExpedicionDocumento + "," +
        primerApellido + "," +
        segundoApellido + "," +
        primerNombre + "," +
        segundoNombre + "," +
        fechaNacimiento + "," +
        sexo + "," +
        departamentoAfiliacion + "," +
        municipioAfiliacion + "," +
        estadoAfiliacion + "," +
        contratoAfiliado + "," +
        zona + "," +
        direccionAfiliado + "," +
        telefono + "," +
        celular + "," +
        email + ",";
        if (observacion != null) {
            mensaje = mensaje + observacion.replace("\n", "").replace("\r", "") + ",";
        } else {
            mensaje = mensaje + ",";
        }
        mensaje = mensaje + fechaHoraNotificacion + "\n";
        
        return mensaje;
    }
    
    public static String getEncabezado() {
        return "id,tipo_documento,numero_documento,fecha_expedicion_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,sexo,departamento_afiliacion,municipio_afiliacion,"
                            + "estado_afiliacion,contrato_afiliado,zona,direccion_afiliado,telefono,celular,email,observacion,fecha_hora_notificacion\n";
    }
}
