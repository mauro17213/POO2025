/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegAnexo1 extends Auditoria{
    private Integer id;
    private int tipoDocumentoInconsistencia;
    private int numeroDocumentoInconsistencia;
    private int apellido1Inconsistencia;
    private int apellido2Inconsistencia;
    private int nombre1Inconsistencia;
    private int nombre2Inconsistencia;
    private int fechaNacimientoInconsistencia;
    private int sexoInconsistencia;
    private int direccionInconsistencia;
    private int telefonoInconsistencia;
    private int celularInconsistencia;
    private int fechaExpedicionCedulaInconsistencia;
    private String tipoDocumentoNuevo;
    private String numeroDocumentoNuevo;
    private String apellido1Nuevo;
    private String apellido2Nuevo;
    private String nombre1Nuevo;
    private String nombre2Nuevo;
    private Date fechaNacimientoNuevo;
    private String sexoNuevo;
    private String direccionNuevo;
    private String telefonoNuevo;
    private String celularNuevo;
    private Date fechaExpedicionCedulaNuevo;
    private String observacion;
    private int estado;
    private Boolean tratamientoDatosAutoriza;
    private Date tratamientoDatosFechaHora;
    //2023-03-22 jyperez RES 2335
    private boolean version;
    private int emailInconsistencia;
    private int residenciaUbicacionInconsistencia;
    private int direccionAlternaContactoInconsistencia;
    private int nombreContactoEmergenciaInconsistencia;
    private int telefonoContactoEmergenciaInconsistencia;
    private Integer cntPrestadoresId;
    private Integer cntPrestadorSedesId;
    private Ubicacion residenciaUbicacionNuevo;
    private String emailNuevo;
    private String direccionAlternaContactoNuevo;
    private String nombreContactoEmergenciaNuevo;
    private String telefonoContactoEmergenciaNuevo;
    //2024-04-12 jyperez nuevo campo RES 2335
    private String consecutivo;
    //variables necesarias
    //private Ubicacion ubicacion;
    private CntPrestadorSede cntPrestadorSede;
    private CntPrestador cntPrestador;

    private AsegAfiliado asegAfiliadosId;
    private Empresa gnEmpresa;
    
    private List<AsegAnexo1Adjunto> listaAnexo1Adjuntos;
    
    //constantes
    public static int ESTADO_INCONSISTENCIA_NO_APLICA = 0;
    public static int ESTADO_INCONSISTENCIA_PENDIENTE_GESTION = 1;
    public static int ESTADO_INCONSISTENCIA_ACEPTADO = 2;
    public static int ESTADO_INCONSISTENCIA_RECHAZADO = 3;
    
    // variables necesarias manejo de actualizaciones en el front
    private boolean tipoDocumentoHabilitar;
    private boolean numeroDocumentoHabilitar;
    private boolean apellido1Habilitar;
    private boolean apellido2Habilitar;
    private boolean nombre1Habilitar;
    private boolean nombre2Habilitar;
    private boolean fechaNacimientoHabilitar;
    private boolean sexoHabilitar;
    private boolean direccionHabilitar;
    private boolean telefonoHabilitar;
    private boolean celularHabilitar;
    private boolean fechaExpedicionCedulaHabilitar;
    private boolean emailHabilitar;
    private boolean residenciaUbicacionIdHabilitar;
    private boolean direccionAlternaContactoHabilitar;
    private boolean nombreContactoEmergenciaHabilitar;
    private boolean telefonoContactoEmergenciaHabilitar;
    
    public AsegAnexo1() {
        tipoDocumentoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        numeroDocumentoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        apellido1Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        apellido2Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        nombre1Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        nombre2Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        fechaNacimientoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        sexoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        direccionInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        telefonoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        celularInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        fechaExpedicionCedulaInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
    }
    
    public AsegAnexo1(Integer id) {
        this.id = id;
        tipoDocumentoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        numeroDocumentoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        apellido1Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        apellido2Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        nombre1Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        nombre2Inconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        fechaNacimientoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        sexoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        direccionInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        telefonoInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        celularInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
        fechaExpedicionCedulaInconsistencia = ESTADO_INCONSISTENCIA_NO_APLICA;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the asegAfiliadosId
     */
    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    /**
     * @param asegAfiliadosId the asegAfiliadosId to set
     */
    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    /**
     * @return the apellido1Inconsistencia
     */
    public int getApellido1Inconsistencia() {
        return apellido1Inconsistencia;
    }

    /**
     * @param apellido1Inconsistencia the apellido1Inconsistencia to set
     */
    public void setApellido1Inconsistencia(int apellido1Inconsistencia) {
        this.apellido1Inconsistencia = apellido1Inconsistencia;
    }

    /**
     * @return the apellido2Inconsistencia
     */
    public int getApellido2Inconsistencia() {
        return apellido2Inconsistencia;
    }

    /**
     * @param apellido2Inconsistencia the apellido2Inconsistencia to set
     */
    public void setApellido2Inconsistencia(int apellido2Inconsistencia) {
        this.apellido2Inconsistencia = apellido2Inconsistencia;
    }

    /**
     * @return the nombre1Inconsistencia
     */
    public int getNombre1Inconsistencia() {
        return nombre1Inconsistencia;
    }

    /**
     * @param nombre1Inconsistencia the nombre1Inconsistencia to set
     */
    public void setNombre1Inconsistencia(int nombre1Inconsistencia) {
        this.nombre1Inconsistencia = nombre1Inconsistencia;
    }

    /**
     * @return the nombre2Inconsistencia
     */
    public int getNombre2Inconsistencia() {
        return nombre2Inconsistencia;
    }

    /**
     * @param nombre2Inconsistencia the nombre2Inconsistencia to set
     */
    public void setNombre2Inconsistencia(int nombre2Inconsistencia) {
        this.nombre2Inconsistencia = nombre2Inconsistencia;
    }

    /**
     * @return the fechaNacimientoInconsistencia
     */
    public int getFechaNacimientoInconsistencia() {
        return fechaNacimientoInconsistencia;
    }

    /**
     * @param fechaNacimientoInconsistencia the fechaNacimientoInconsistencia to set
     */
    public void setFechaNacimientoInconsistencia(int fechaNacimientoInconsistencia) {
        this.fechaNacimientoInconsistencia = fechaNacimientoInconsistencia;
    }

    /**
     * @return the sexoInconsistencia
     */
    public int getSexoInconsistencia() {
        return sexoInconsistencia;
    }

    /**
     * @param sexoInconsistencia the sexoInconsistencia to set
     */
    public void setSexoInconsistencia(int sexoInconsistencia) {
        this.sexoInconsistencia = sexoInconsistencia;
    }

    /**
     * @return the direccionInconsistencia
     */
    public int getDireccionInconsistencia() {
        return direccionInconsistencia;
    }

    /**
     * @param direccionInconsistencia the direccionInconsistencia to set
     */
    public void setDireccionInconsistencia(int direccionInconsistencia) {
        this.direccionInconsistencia = direccionInconsistencia;
    }

    /**
     * @return the telefonoInconsistencia
     */
    public int getTelefonoInconsistencia() {
        return telefonoInconsistencia;
    }

    /**
     * @param telefonoInconsistencia the telefonoInconsistencia to set
     */
    public void setTelefonoInconsistencia(int telefonoInconsistencia) {
        this.telefonoInconsistencia = telefonoInconsistencia;
    }

    /**
     * @return the celularInconsistencia
     */
    public int getCelularInconsistencia() {
        return celularInconsistencia;
    }

    /**
     * @param celularInconsistencia the celularInconsistencia to set
     */
    public void setCelularInconsistencia(int celularInconsistencia) {
        this.celularInconsistencia = celularInconsistencia;
    }

    /**
     * @return the tipoDocumentoNuevo
     */
    public String getTipoDocumentoNuevo() {
        return tipoDocumentoNuevo;
    }

    /**
     * @param tipoDocumentoNuevo the tipoDocumentoNuevo to set
     */
    public void setTipoDocumentoNuevo(String tipoDocumentoNuevo) {
        this.tipoDocumentoNuevo = tipoDocumentoNuevo;
    }

    /**
     * @return the numeroDocumentoNuevo
     */
    public String getNumeroDocumentoNuevo() {
        return numeroDocumentoNuevo;
    }

    /**
     * @param numeroDocumentoNuevo the numeroDocumentoNuevo to set
     */
    public void setNumeroDocumentoNuevo(String numeroDocumentoNuevo) {
        this.numeroDocumentoNuevo = numeroDocumentoNuevo;
    }

    /**
     * @return the apellido1Nuevo
     */
    public String getApellido1Nuevo() {
        return apellido1Nuevo;
    }

    /**
     * @param apellido1Nuevo the apellido1Nuevo to set
     */
    public void setApellido1Nuevo(String apellido1Nuevo) {
        this.apellido1Nuevo = apellido1Nuevo;
    }

    /**
     * @return the apellido2Nuevo
     */
    public String getApellido2Nuevo() {
        return apellido2Nuevo;
    }

    /**
     * @param apellido2Nuevo the apellido2Nuevo to set
     */
    public void setApellido2Nuevo(String apellido2Nuevo) {
        this.apellido2Nuevo = apellido2Nuevo;
    }

    /**
     * @return the nombre1Nuevo
     */
    public String getNombre1Nuevo() {
        return nombre1Nuevo;
    }

    /**
     * @param nombre1Nuevo the nombre1Nuevo to set
     */
    public void setNombre1Nuevo(String nombre1Nuevo) {
        this.nombre1Nuevo = nombre1Nuevo;
    }

    /**
     * @return the nombre2Nuevo
     */
    public String getNombre2Nuevo() {
        return nombre2Nuevo;
    }

    /**
     * @param nombre2Nuevo the nombre2Nuevo to set
     */
    public void setNombre2Nuevo(String nombre2Nuevo) {
        this.nombre2Nuevo = nombre2Nuevo;
    }

    /**
     * @return the fechaNacimientoNuevo
     */
    public Date getFechaNacimientoNuevo() {
        return fechaNacimientoNuevo;
    }

    /**
     * @param fechaNacimientoNuevo the fechaNacimientoNuevo to set
     */
    public void setFechaNacimientoNuevo(Date fechaNacimientoNuevo) {
        this.fechaNacimientoNuevo = fechaNacimientoNuevo;
    }

    /**
     * @return the sexoNuevo
     */
    public String getSexoNuevo() {
        return sexoNuevo;
    }

    /**
     * @param sexoNuevo the sexoNuevo to set
     */
    public void setSexoNuevo(String sexoNuevo) {
        this.sexoNuevo = sexoNuevo;
    }

    /**
     * @return the direccionNuevo
     */
    public String getDireccionNuevo() {
        return direccionNuevo;
    }

    /**
     * @param direccionNuevo the direccionNuevo to set
     */
    public void setDireccionNuevo(String direccionNuevo) {
        this.direccionNuevo = direccionNuevo;
    }

    /**
     * @return the telefonoNuevo
     */
    public String getTelefonoNuevo() {
        return telefonoNuevo;
    }

    /**
     * @param telefonoNuevo the telefonoNuevo to set
     */
    public void setTelefonoNuevo(String telefonoNuevo) {
        this.telefonoNuevo = telefonoNuevo;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the listaAnexo1Adjuntos
     */
    public List<AsegAnexo1Adjunto> getListaAnexo1Adjuntos() {
        return listaAnexo1Adjuntos;
    }

    /**
     * @param listaAnexo1Adjuntos the listaAnexo1Adjuntos to set
     */
    public void setListaAnexo1Adjuntos(List<AsegAnexo1Adjunto> listaAnexo1Adjuntos) {
        this.listaAnexo1Adjuntos = listaAnexo1Adjuntos;
    }

    /**
     * @return the tipoDocumentoInconsistencia
     */
    public int getTipoDocumentoInconsistencia() {
        return tipoDocumentoInconsistencia;
    }

    /**
     * @param tipoDocumentoInconsistencia the tipoDocumentoInconsistencia to set
     */
    public void setTipoDocumentoInconsistencia(int tipoDocumentoInconsistencia) {
        this.tipoDocumentoInconsistencia = tipoDocumentoInconsistencia;
    }

    /**
     * @return the numeroDocumentoInconsistencia
     */
    public int getNumeroDocumentoInconsistencia() {
        return numeroDocumentoInconsistencia;
    }

    /**
     * @param numeroDocumentoInconsistencia the numeroDocumentoInconsistencia to set
     */
    public void setNumeroDocumentoInconsistencia(int numeroDocumentoInconsistencia) {
        this.numeroDocumentoInconsistencia = numeroDocumentoInconsistencia;
    }

    /**
     * @return the celularNuevo
     */
    public String getCelularNuevo() {
        return celularNuevo;
    }

    /**
     * @param celularNuevo the celularNuevo to set
     */
    public void setCelularNuevo(String celularNuevo) {
        this.celularNuevo = celularNuevo;
    }

    /**
     * @return the fechaExpedicionCedulaInconsistencia
     */
    public int getFechaExpedicionCedulaInconsistencia() {
        return fechaExpedicionCedulaInconsistencia;
    }

    /**
     * @param fechaExpedicionCedulaInconsistencia the fechaExpedicionCedulaInconsistencia to set
     */
    public void setFechaExpedicionCedulaInconsistencia(int fechaExpedicionCedulaInconsistencia) {
        this.fechaExpedicionCedulaInconsistencia = fechaExpedicionCedulaInconsistencia;
    }

    /**
     * @return the fechaExpedicionCedulaNuevo
     */
    public Date getFechaExpedicionCedulaNuevo() {
        return fechaExpedicionCedulaNuevo;
    }

    /**
     * @param fechaExpedicionCedulaNuevo the fechaExpedicionCedulaNuevo to set
     */
    public void setFechaExpedicionCedulaNuevo(Date fechaExpedicionCedulaNuevo) {
        this.fechaExpedicionCedulaNuevo = fechaExpedicionCedulaNuevo;
    }

    /**
     * @return the tipoDocumentoHabilitar
     */
    public boolean isTipoDocumentoHabilitar() {
        return tipoDocumentoHabilitar;
    }

    /**
     * @param tipoDocumentoHabilitar the tipoDocumentoHabilitar to set
     */
    public void setTipoDocumentoHabilitar(boolean tipoDocumentoHabilitar) {
        this.tipoDocumentoHabilitar = tipoDocumentoHabilitar;
    }

    /**
     * @return the numeroDocumentoHabilitar
     */
    public boolean isNumeroDocumentoHabilitar() {
        return numeroDocumentoHabilitar;
    }

    /**
     * @param numeroDocumentoHabilitar the numeroDocumentoHabilitar to set
     */
    public void setNumeroDocumentoHabilitar(boolean numeroDocumentoHabilitar) {
        this.numeroDocumentoHabilitar = numeroDocumentoHabilitar;
    }

    /**
     * @return the apellido1Habilitar
     */
    public boolean isApellido1Habilitar() {
        return apellido1Habilitar;
    }

    /**
     * @param apellido1Habilitar the apellido1Habilitar to set
     */
    public void setApellido1Habilitar(boolean apellido1Habilitar) {
        this.apellido1Habilitar = apellido1Habilitar;
    }

    /**
     * @return the apellido2Habilitar
     */
    public boolean isApellido2Habilitar() {
        return apellido2Habilitar;
    }

    /**
     * @param apellido2Habilitar the apellido2Habilitar to set
     */
    public void setApellido2Habilitar(boolean apellido2Habilitar) {
        this.apellido2Habilitar = apellido2Habilitar;
    }

    /**
     * @return the nombre1Habilitar
     */
    public boolean isNombre1Habilitar() {
        return nombre1Habilitar;
    }

    /**
     * @param nombre1Habilitar the nombre1Habilitar to set
     */
    public void setNombre1Habilitar(boolean nombre1Habilitar) {
        this.nombre1Habilitar = nombre1Habilitar;
    }

    /**
     * @return the nombre2Habilitar
     */
    public boolean isNombre2Habilitar() {
        return nombre2Habilitar;
    }

    /**
     * @param nombre2Habilitar the nombre2Habilitar to set
     */
    public void setNombre2Habilitar(boolean nombre2Habilitar) {
        this.nombre2Habilitar = nombre2Habilitar;
    }

    /**
     * @return the fechaNacimientoHabilitar
     */
    public boolean isFechaNacimientoHabilitar() {
        return fechaNacimientoHabilitar;
    }

    /**
     * @param fechaNacimientoHabilitar the fechaNacimientoHabilitar to set
     */
    public void setFechaNacimientoHabilitar(boolean fechaNacimientoHabilitar) {
        this.fechaNacimientoHabilitar = fechaNacimientoHabilitar;
    }

    /**
     * @return the sexoHabilitar
     */
    public boolean isSexoHabilitar() {
        return sexoHabilitar;
    }

    /**
     * @param sexoHabilitar the sexoHabilitar to set
     */
    public void setSexoHabilitar(boolean sexoHabilitar) {
        this.sexoHabilitar = sexoHabilitar;
    }

    /**
     * @return the direccionHabilitar
     */
    public boolean isDireccionHabilitar() {
        return direccionHabilitar;
    }

    /**
     * @param direccionHabilitar the direccionHabilitar to set
     */
    public void setDireccionHabilitar(boolean direccionHabilitar) {
        this.direccionHabilitar = direccionHabilitar;
    }

    /**
     * @return the telefonoHabilitar
     */
    public boolean isTelefonoHabilitar() {
        return telefonoHabilitar;
    }

    /**
     * @param telefonoHabilitar the telefonoHabilitar to set
     */
    public void setTelefonoHabilitar(boolean telefonoHabilitar) {
        this.telefonoHabilitar = telefonoHabilitar;
    }

    /**
     * @return the celularHabilitar
     */
    public boolean isCelularHabilitar() {
        return celularHabilitar;
    }

    /**
     * @param celularHabilitar the celularHabilitar to set
     */
    public void setCelularHabilitar(boolean celularHabilitar) {
        this.celularHabilitar = celularHabilitar;
    }

    /**
     * @return the fechaExpedicionCedulaHabilitar
     */
    public boolean isFechaExpedicionCedulaHabilitar() {
        return fechaExpedicionCedulaHabilitar;
    }

    /**
     * @param fechaExpedicionCedulaHabilitar the fechaExpedicionCedulaHabilitar to set
     */
    public void setFechaExpedicionCedulaHabilitar(boolean fechaExpedicionCedulaHabilitar) {
        this.fechaExpedicionCedulaHabilitar = fechaExpedicionCedulaHabilitar;
    }

    /**
     * @return the gnEmpresa
     */
    public Empresa getGnEmpresa() {
        return gnEmpresa;
    }

    /**
     * @param gnEmpresa the gnEmpresa to set
     */
    public void setGnEmpresa(Empresa gnEmpresa) {
        this.gnEmpresa = gnEmpresa;
    }

    @Override
    public String toString() {
        return "AsegAnexo1{" + "id=" + id + ", tipoDocumentoInconsistencia=" + tipoDocumentoInconsistencia + ", numeroDocumentoInconsistencia=" + numeroDocumentoInconsistencia + ", apellido1Inconsistencia=" + apellido1Inconsistencia + ", apellido2Inconsistencia=" + apellido2Inconsistencia + ", nombre1Inconsistencia=" + nombre1Inconsistencia + ", nombre2Inconsistencia=" + nombre2Inconsistencia + ", fechaNacimientoInconsistencia=" + fechaNacimientoInconsistencia + ", sexoInconsistencia=" + sexoInconsistencia + ", direccionInconsistencia=" + direccionInconsistencia + ", telefonoInconsistencia=" + telefonoInconsistencia + ", celularInconsistencia=" + celularInconsistencia + ", fechaExpedicionCedulaInconsistencia=" + fechaExpedicionCedulaInconsistencia + ", tipoDocumentoNuevo=" + tipoDocumentoNuevo + ", numeroDocumentoNuevo=" + numeroDocumentoNuevo + ", apellido1Nuevo=" + apellido1Nuevo + ", apellido2Nuevo=" + apellido2Nuevo + ", nombre1Nuevo=" + nombre1Nuevo + ", nombre2Nuevo=" + nombre2Nuevo + ", fechaNacimientoNuevo=" + fechaNacimientoNuevo + ", sexoNuevo=" + sexoNuevo + ", direccionNuevo=" + direccionNuevo + ", telefonoNuevo=" + telefonoNuevo + ", celularNuevo=" + celularNuevo + ", fechaExpedicionCedulaNuevo=" + fechaExpedicionCedulaNuevo + ", observacion=" + observacion + ", estado=" + estado + ", tratamientoDatosAutoriza=" + tratamientoDatosAutoriza + ", tratamientoDatosFechaHora=" + tratamientoDatosFechaHora + ", asegAfiliadosId=" + asegAfiliadosId + ", gnEmpresa=" + gnEmpresa + ", listaAnexo1Adjuntos=" + listaAnexo1Adjuntos + '}';
    }
    
    /**
     * Función auxiliar que actualizará los campos booleanos del objeto según esten habilitados para gestión
     */
    public void habilitarCamposInconsistencia(){
        
        tipoDocumentoHabilitar = tipoDocumentoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        numeroDocumentoHabilitar = numeroDocumentoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        apellido1Habilitar = apellido1Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        apellido2Habilitar = apellido2Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        nombre1Habilitar = nombre1Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        nombre2Habilitar = nombre2Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        fechaNacimientoHabilitar = fechaNacimientoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        sexoHabilitar = sexoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        direccionHabilitar = direccionInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        telefonoHabilitar = telefonoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        celularHabilitar = celularInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        fechaExpedicionCedulaHabilitar = fechaExpedicionCedulaInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        //2023-03-26 jyperez campos nuevos RES 2335
        residenciaUbicacionIdHabilitar = residenciaUbicacionInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        emailHabilitar = emailInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        direccionAlternaContactoHabilitar = direccionAlternaContactoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        nombreContactoEmergenciaHabilitar = nombreContactoEmergenciaInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
        telefonoContactoEmergenciaHabilitar = telefonoContactoEmergenciaInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION;
    }

    /**
     * Función para validar que el objeto se encuentre en estados finales (Aceptado-Rechazado)
     * @return 
     */
    public boolean validarGestion() {
        if (tipoDocumentoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (numeroDocumentoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (apellido1Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (apellido2Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (nombre1Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (nombre2Inconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (fechaNacimientoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (sexoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (direccionInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (telefonoInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (celularInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        if (fechaExpedicionCedulaInconsistencia == ESTADO_INCONSISTENCIA_PENDIENTE_GESTION) {
            return false;
        }
        return true;
    }

    /**
     * @return the tratamientoDatosAutoriza
     */
    public Boolean getTratamientoDatosAutoriza() {
        return tratamientoDatosAutoriza;
    }

    /**
     * @param tratamientoDatosAutoriza the tratamientoDatosAutoriza to set
     */
    public void setTratamientoDatosAutoriza(Boolean tratamientoDatosAutoriza) {
        this.tratamientoDatosAutoriza = tratamientoDatosAutoriza;
    }

    /**
     * @return the tratamientoDatosFechaHora
     */
    public Date getTratamientoDatosFechaHora() {
        return tratamientoDatosFechaHora;
    }

    /**
     * @param tratamientoDatosFechaHora the tratamientoDatosFechaHora to set
     */
    public void setTratamientoDatosFechaHora(Date tratamientoDatosFechaHora) {
        this.tratamientoDatosFechaHora = tratamientoDatosFechaHora;
    }

    /**
     * @return the cntPrestadoresId
     */
    public Integer getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    /**
     * @param cntPrestadoresId the cntPrestadoresId to set
     */
    public void setCntPrestadoresId(Integer cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    /**
     * @return the cntPrestadorSedesId
     */
    public Integer getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    /**
     * @param cntPrestadorSedesId the cntPrestadorSedesId to set
     */
    public void setCntPrestadorSedesId(Integer cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    /**
     * @return the residenciaUbicacionNuevo
     */
    public Ubicacion getResidenciaUbicacionNuevo() {
        return residenciaUbicacionNuevo;
    }

    /**
     * @param residenciaUbicacionNuevo the residenciaUbicacionNuevo to set
     */
    public void setResidenciaUbicacionNuevo(Ubicacion residenciaUbicacionNuevo) {
        this.residenciaUbicacionNuevo = residenciaUbicacionNuevo;
    }

    /**
     * @return the emailNuevo
     */
    public String getEmailNuevo() {
        return emailNuevo;
    }

    /**
     * @param emailNuevo the emailNuevo to set
     */
    public void setEmailNuevo(String emailNuevo) {
        this.emailNuevo = emailNuevo;
    }

    /**
     * @return the direccionAlternaContactoNuevo
     */
    public String getDireccionAlternaContactoNuevo() {
        return direccionAlternaContactoNuevo;
    }

    /**
     * @param direccionAlternaContactoNuevo the direccionAlternaContactoNuevo to set
     */
    public void setDireccionAlternaContactoNuevo(String direccionAlternaContactoNuevo) {
        this.direccionAlternaContactoNuevo = direccionAlternaContactoNuevo;
    }

    /**
     * @return the nombreContactoEmergenciaNuevo
     */
    public String getNombreContactoEmergenciaNuevo() {
        return nombreContactoEmergenciaNuevo;
    }

    /**
     * @param nombreContactoEmergenciaNuevo the nombreContactoEmergenciaNuevo to set
     */
    public void setNombreContactoEmergenciaNuevo(String nombreContactoEmergenciaNuevo) {
        this.nombreContactoEmergenciaNuevo = nombreContactoEmergenciaNuevo;
    }

    /**
     * @return the telefonoContactoEmergenciaNuevo
     */
    public String getTelefonoContactoEmergenciaNuevo() {
        return telefonoContactoEmergenciaNuevo;
    }

    /**
     * @param telefonoContactoEmergenciaNuevo the telefonoContactoEmergenciaNuevo to set
     */
    public void setTelefonoContactoEmergenciaNuevo(String telefonoContactoEmergenciaNuevo) {
        this.telefonoContactoEmergenciaNuevo = telefonoContactoEmergenciaNuevo;
    }

    /**
     * @return the emailInconsistencia
     */
    public int getEmailInconsistencia() {
        return emailInconsistencia;
    }

    /**
     * @param emailInconsistencia the emailInconsistencia to set
     */
    public void setEmailInconsistencia(int emailInconsistencia) {
        this.emailInconsistencia = emailInconsistencia;
    }

    /**
     * @return the residenciaUbicacionInconsistencia
     */
    public int getResidenciaUbicacionInconsistencia() {
        return residenciaUbicacionInconsistencia;
    }

    /**
     * @param residenciaUbicacionInconsistencia the residenciaUbicacionInconsistencia to set
     */
    public void setResidenciaUbicacionInconsistencia(int residenciaUbicacionInconsistencia) {
        this.residenciaUbicacionInconsistencia = residenciaUbicacionInconsistencia;
    }

    /**
     * @return the direccionAlternaContactoInconsistencia
     */
    public int getDireccionAlternaContactoInconsistencia() {
        return direccionAlternaContactoInconsistencia;
    }

    /**
     * @param direccionAlternaContactoInconsistencia the direccionAlternaContactoInconsistencia to set
     */
    public void setDireccionAlternaContactoInconsistencia(int direccionAlternaContactoInconsistencia) {
        this.direccionAlternaContactoInconsistencia = direccionAlternaContactoInconsistencia;
    }

    /**
     * @return the nombreContactoEmergenciaInconsistencia
     */
    public int getNombreContactoEmergenciaInconsistencia() {
        return nombreContactoEmergenciaInconsistencia;
    }

    /**
     * @param nombreContactoEmergenciaInconsistencia the nombreContactoEmergenciaInconsistencia to set
     */
    public void setNombreContactoEmergenciaInconsistencia(int nombreContactoEmergenciaInconsistencia) {
        this.nombreContactoEmergenciaInconsistencia = nombreContactoEmergenciaInconsistencia;
    }

    /**
     * @return the telefonoContactoEmergenciaInconsistencia
     */
    public int getTelefonoContactoEmergenciaInconsistencia() {
        return telefonoContactoEmergenciaInconsistencia;
    }

    /**
     * @param telefonoContactoEmergenciaInconsistencia the telefonoContactoEmergenciaInconsistencia to set
     */
    public void setTelefonoContactoEmergenciaInconsistencia(int telefonoContactoEmergenciaInconsistencia) {
        this.telefonoContactoEmergenciaInconsistencia = telefonoContactoEmergenciaInconsistencia;
    }

    /**
     * @return the cntPrestadorSede
     */
    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    /**
     * @param cntPrestadorSede the cntPrestadorSede to set
     */
    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    /**
     * @return the cntPrestador
     */
    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    /**
     * @param cntPrestador the cntPrestador to set
     */
    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    /**
     * @return the emailHabilitar
     */
    public boolean isEmailHabilitar() {
        return emailHabilitar;
    }

    /**
     * @param emailHabilitar the emailHabilitar to set
     */
    public void setEmailHabilitar(boolean emailHabilitar) {
        this.emailHabilitar = emailHabilitar;
    }

    /**
     * @return the residenciaUbicacionIdHabilitar
     */
    public boolean isResidenciaUbicacionIdHabilitar() {
        return residenciaUbicacionIdHabilitar;
    }

    /**
     * @param residenciaUbicacionIdHabilitar the residenciaUbicacionIdHabilitar to set
     */
    public void setResidenciaUbicacionIdHabilitar(boolean residenciaUbicacionIdHabilitar) {
        this.residenciaUbicacionIdHabilitar = residenciaUbicacionIdHabilitar;
    }

    /**
     * @return the direccionAlternaContactoHabilitar
     */
    public boolean isDireccionAlternaContactoHabilitar() {
        return direccionAlternaContactoHabilitar;
    }

    /**
     * @param direccionAlternaContactoHabilitar the direccionAlternaContactoHabilitar to set
     */
    public void setDireccionAlternaContactoHabilitar(boolean direccionAlternaContactoHabilitar) {
        this.direccionAlternaContactoHabilitar = direccionAlternaContactoHabilitar;
    }

    /**
     * @return the nombreContactoEmergenciaHabilitar
     */
    public boolean isNombreContactoEmergenciaHabilitar() {
        return nombreContactoEmergenciaHabilitar;
    }

    /**
     * @param nombreContactoEmergenciaHabilitar the nombreContactoEmergenciaHabilitar to set
     */
    public void setNombreContactoEmergenciaHabilitar(boolean nombreContactoEmergenciaHabilitar) {
        this.nombreContactoEmergenciaHabilitar = nombreContactoEmergenciaHabilitar;
    }

    /**
     * @return the telefonoContactoEmergenciaHabilitar
     */
    public boolean isTelefonoContactoEmergenciaHabilitar() {
        return telefonoContactoEmergenciaHabilitar;
    }

    /**
     * @param telefonoContactoEmergenciaHabilitar the telefonoContactoEmergenciaHabilitar to set
     */
    public void setTelefonoContactoEmergenciaHabilitar(boolean telefonoContactoEmergenciaHabilitar) {
        this.telefonoContactoEmergenciaHabilitar = telefonoContactoEmergenciaHabilitar;
    }

    /**
     * @return the version
     */
    public boolean isVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(boolean version) {
        this.version = version;
    }

    /**
     * @return the consecutivo
     */
    public String getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }
}
