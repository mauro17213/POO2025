/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class VAsegPortabilidad extends Auditoria {

    private int radicadoPortabilidad;
    private Date fechaRadicacion;
    private String contratoAfiliado;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    // 2020-08-25 jyperez INC 280 cambios Reportes
    private String codigoDepartamentoAfiliacion;
    private String codigoMunicipioAfiliacion;
    private String departamentoAfiliacion;
    private String municipioAfiliacion;
    private String direccionResidencia;
    private String telefono1Afiliado;
    private String telefono2Afiliado;
    private String telefono3Portabilidad;
    private String telefono4Portabilidad;
    private String correoElectronicoPortabilidad;
    private String grupoPoblacional;
    private String estadoPortabilidad;
    private String ipsPrimariaSede;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String codigoDepartamentoPortabilidad;
    private String codigoMunicipioPortabilidad;
    private String departamentoPortabilidad;
    private String municipioPortabilidad;
    private String codigoHabilitacionIpsPortabilidad;
    private String ipsPortabilidad;
    private String fichaSisben;
    private String descMigracion;
    private Date periodoInicial;
    private Date periodoFinal;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private BigInteger duracionPortabilidad;
    private String usuarioCrea;
    private String observacionAseguramiento;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String origenSolicitudPortabilidad;
    private Integer cantidadProrrogas;
    private Date fechaProrroga;
    private Integer mesesAdicionProrroga;
    private Date fechaCancelacion;
    
    // campos adicionales
    private boolean novedadActualizada;

    public VAsegPortabilidad() {
    }

    public int getRadicadoPortabilidad() {
        return radicadoPortabilidad;
    }

    public void setRadicadoPortabilidad(int radicadoPortabilidad) {
        this.radicadoPortabilidad = radicadoPortabilidad;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefono1Afiliado() {
        return telefono1Afiliado;
    }

    public void setTelefono1Afiliado(String telefono1Afiliado) {
        this.telefono1Afiliado = telefono1Afiliado;
    }

    public String getTelefono2Afiliado() {
        return telefono2Afiliado;
    }

    public void setTelefono2Afiliado(String telefono2Afiliado) {
        this.telefono2Afiliado = telefono2Afiliado;
    }

    public String getTelefono3Portabilidad() {
        return telefono3Portabilidad;
    }

    public void setTelefono3Portabilidad(String telefono3Portabilidad) {
        this.telefono3Portabilidad = telefono3Portabilidad;
    }

    public String getTelefono4Portabilidad() {
        return telefono4Portabilidad;
    }

    public void setTelefono4Portabilidad(String telefono4Portabilidad) {
        this.telefono4Portabilidad = telefono4Portabilidad;
    }

    public String getCorreoElectronicoPortabilidad() {
        return correoElectronicoPortabilidad;
    }

    public void setCorreoElectronicoPortabilidad(String correoElectronicoPortabilidad) {
        this.correoElectronicoPortabilidad = correoElectronicoPortabilidad;
    }

    public String getGrupoPoblacional() {
        return grupoPoblacional;
    }

    public void setGrupoPoblacional(String grupoPoblacional) {
        this.grupoPoblacional = grupoPoblacional;
    }

    public String getEstadoPortabilidad() {
        return estadoPortabilidad;
    }

    public void setEstadoPortabilidad(String estadoPortabilidad) {
        this.estadoPortabilidad = estadoPortabilidad;
    }

    public String getIpsPrimariaSede() {
        return ipsPrimariaSede;
    }

    public void setIpsPrimariaSede(String ipsPrimariaSede) {
        this.ipsPrimariaSede = ipsPrimariaSede;
    }

    public String getCodigoDepartamentoPortabilidad() {
        return codigoDepartamentoPortabilidad;
    }

    public void setCodigoDepartamentoPortabilidad(String codigoDepartamentoPortabilidad) {
        this.codigoDepartamentoPortabilidad = codigoDepartamentoPortabilidad;
    }

    public String getCodigoMunicipioPortabilidad() {
        return codigoMunicipioPortabilidad;
    }

    public void setCodigoMunicipioPortabilidad(String codigoMunicipioPortabilidad) {
        this.codigoMunicipioPortabilidad = codigoMunicipioPortabilidad;
    }

    public String getCodigoHabilitacionIpsPortabilidad() {
        return codigoHabilitacionIpsPortabilidad;
    }

    public void setCodigoHabilitacionIpsPortabilidad(String codigoHabilitacionIpsPortabilidad) {
        this.codigoHabilitacionIpsPortabilidad = codigoHabilitacionIpsPortabilidad;
    }

    public String getIpsPortabilidad() {
        return ipsPortabilidad;
    }

    public void setIpsPortabilidad(String ipsPortabilidad) {
        this.ipsPortabilidad = ipsPortabilidad;
    }

    public String getFichaSisben() {
        return fichaSisben;
    }

    public void setFichaSisben(String fichaSisben) {
        this.fichaSisben = fichaSisben;
    }

    public String getDescMigracion() {
        return descMigracion;
    }

    public void setDescMigracion(String descMigracion) {
        this.descMigracion = descMigracion;
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public BigInteger getDuracionPortabilidad() {
        return duracionPortabilidad;
    }

    public void setDuracionPortabilidad(BigInteger duracionPortabilidad) {
        this.duracionPortabilidad = duracionPortabilidad;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getObservacionAseguramiento() {
        return observacionAseguramiento;
    }

    public void setObservacionAseguramiento(String observacionAseguramiento) {
        this.observacionAseguramiento = observacionAseguramiento;
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
        String mensaje = radicadoPortabilidad + "," +
        fechaRadicacion + "," +
        contratoAfiliado + "," +
        tipoDocumento + "," +
        numeroDocumento + "," +
        primerNombre + "," +
        segundoNombre + "," +
        primerApellido + "," +
        segundoApellido + "," +
        fechaNacimiento + "," +
        codigoDepartamentoAfiliacion + "," +
        codigoMunicipioAfiliacion + "," +
        departamentoAfiliacion + "," +
        municipioAfiliacion + "," +
        direccionResidencia + "," +
        telefono1Afiliado + "," +
        telefono2Afiliado + "," +
        telefono3Portabilidad + "," +
        telefono4Portabilidad + "," +
        correoElectronicoPortabilidad + "," +
        grupoPoblacional + "," +
        estadoPortabilidad + "," +
        ipsPrimariaSede + "," +
        codigoDepartamentoPortabilidad + "," +
        codigoMunicipioPortabilidad + "," +
        departamentoPortabilidad + "," +
        municipioPortabilidad + "," +
        codigoHabilitacionIpsPortabilidad + "," +
        ipsPortabilidad + "," +
        fichaSisben + "," +
        descMigracion + "," +
        periodoInicial + "," +
        periodoFinal + "," +
        duracionPortabilidad + "," +
        origenSolicitudPortabilidad + "," +
        cantidadProrrogas + "," +
        fechaProrroga + "," +
        mesesAdicionProrroga + "," +
        fechaCancelacion + "," +
        usuarioCrea + ",";
        if (observacionAseguramiento != null) {
            mensaje = mensaje + observacionAseguramiento.replace("\n", "").replace("\r", "").replace(",","") + "\n";
        } else {
            mensaje = mensaje + "\n";
        }
        return mensaje;
    }
    
    public static String getEncabezado() {
        return "radicado_portabilidad,fecha_radicacion,contrato_afiliado,tipo_documento,numero_documento,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,fecha_nacimiento,"
                            + "codigo_departamento_afiliacion,codigo_municipio_afiliacion,departamento_afiliacion,municipio_afiliacion,direccion_residencia,telefono1_afiliado,telefono2_afiliado,telefono3_portabilidad,telefono4_portabilidad,correo_electronico_portabilidad,"
                            + "grupo_poblacional,estado_portabilidad,ips_primaria_sede,codigo_departamento_portabilidad,codigo_municipio_portabilidad,departamento_portabilidad,municipio_portabilidad,codigo_habilitacion_ips_portabilidad,"
                            + "ips_portabilidad,ficha_sisben,desc_migracion,periodo_inicial,periodo_final,duracion_portabilidad,origen_solicitud_portabilidad,cantidad_prorrogas,fecha_prorroga,meses_adicion_prorroga,"
                + "fecha_cancelacion,usuario_crea,observacion_aseguramiento\n";
    }

    /**
     * @return the origenSolicitudPortabilidad
     */
    public String getOrigenSolicitudPortabilidad() {
        return origenSolicitudPortabilidad;
    }

    /**
     * @param origenSolicitudPortabilidad the origenSolicitudPortabilidad to set
     */
    public void setOrigenSolicitudPortabilidad(String origenSolicitudPortabilidad) {
        this.origenSolicitudPortabilidad = origenSolicitudPortabilidad;
    }

    /**
     * @return the cantidadProrrogas
     */
    public Integer getCantidadProrrogas() {
        return cantidadProrrogas;
    }

    /**
     * @param cantidadProrrogas the cantidadProrrogas to set
     */
    public void setCantidadProrrogas(Integer cantidadProrrogas) {
        this.cantidadProrrogas = cantidadProrrogas;
    }

    /**
     * @return the fechaProrroga
     */
    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    /**
     * @param fechaProrroga the fechaProrroga to set
     */
    public void setFechaProrroga(Date fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
    }

    /**
     * @return the mesesAdicionProrroga
     */
    public Integer getMesesAdicionProrroga() {
        return mesesAdicionProrroga;
    }

    /**
     * @param mesesAdicionProrroga the mesesAdicionProrroga to set
     */
    public void setMesesAdicionProrroga(Integer mesesAdicionProrroga) {
        this.mesesAdicionProrroga = mesesAdicionProrroga;
    }

    /**
     * @return the fechaCancelacion
     */
    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    /**
     * @param fechaCancelacion the fechaCancelacion to set
     */
    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    /**
     * @return the codigoDepartamentoAfiliacion
     */
    public String getCodigoDepartamentoAfiliacion() {
        return codigoDepartamentoAfiliacion;
    }

    /**
     * @param codigoDepartamentoAfiliacion the codigoDepartamentoAfiliacion to set
     */
    public void setCodigoDepartamentoAfiliacion(String codigoDepartamentoAfiliacion) {
        this.codigoDepartamentoAfiliacion = codigoDepartamentoAfiliacion;
    }

    /**
     * @return the codigoMunicipioAfiliacion
     */
    public String getCodigoMunicipioAfiliacion() {
        return codigoMunicipioAfiliacion;
    }

    /**
     * @param codigoMunicipioAfiliacion the codigoMunicipioAfiliacion to set
     */
    public void setCodigoMunicipioAfiliacion(String codigoMunicipioAfiliacion) {
        this.codigoMunicipioAfiliacion = codigoMunicipioAfiliacion;
    }

    /**
     * @return the departamentoPortabilidad
     */
    public String getDepartamentoPortabilidad() {
        return departamentoPortabilidad;
    }

    /**
     * @param departamentoPortabilidad the departamentoPortabilidad to set
     */
    public void setDepartamentoPortabilidad(String departamentoPortabilidad) {
        this.departamentoPortabilidad = departamentoPortabilidad;
    }

    /**
     * @return the municipioPortabilidad
     */
    public String getMunicipioPortabilidad() {
        return municipioPortabilidad;
    }

    /**
     * @param municipioPortabilidad the municipioPortabilidad to set
     */
    public void setMunicipioPortabilidad(String municipioPortabilidad) {
        this.municipioPortabilidad = municipioPortabilidad;
    }
    
}
