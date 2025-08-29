package com.saviasaludeps.savia.dominio.mapa;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class MapaPrestadorSede extends Auditoria {

    private Integer id;
    private String codigoPrestador;
    private int ubicacionId;
    private Integer maeRegionId;
    private String maeRegionCodigo;
    private String maeRegionValor;
    private String direccion;
    private String nombreSede;
    private String codigoSede;
    private String codigoHabilitacionSede;
    private String zonaPrecedencia;
    private Boolean estadoSede;
    private Integer nivelAtencion;
    private Integer clasePrestador;
    private String maeClasePrestadorCodigo;
    private String maeClasePrestadorValor;
    private String fax;
    private String telefonoCitas;
    private String correoElectronico;
    private String telefonoAdministrativo;
    private Boolean capitacion;
    private MapaPrestador prestador;
    private Date fechaFacturaElectronica;
    private Integer grupoRipsMinisterio;
    private boolean nuevoRegistro;
    private boolean direccionGeorreferenciada;
    private Double direccionGeorefLatitud;
    private Double direccionGeorefLongitud;

    private Ubicacion ubicacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodigoPrestador() {
        return codigoPrestador;
    }

    public void setCodigoPrestador(String codigoPrestador) {
        this.codigoPrestador = codigoPrestador;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getCodigoHabilitacionSede() {
        return codigoHabilitacionSede;
    }

    public void setCodigoHabilitacionSede(String codigoHabilitacionSede) {
        this.codigoHabilitacionSede = codigoHabilitacionSede;
    }

    public String getZonaPrecedencia() {
        return zonaPrecedencia;
    }

    public void setZonaPrecedencia(String zonaPrecedencia) {
        this.zonaPrecedencia = zonaPrecedencia;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelefonoCitas() {
        return telefonoCitas;
    }

    public void setTelefonoCitas(String telefonoCitas) {
        this.telefonoCitas = telefonoCitas;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoAdministrativo() {
        return telefonoAdministrativo;
    }

    public void setTelefonoAdministrativo(String telefonoAdministrativo) {
        this.telefonoAdministrativo = telefonoAdministrativo;
    }

    public MapaPrestador getPrestador() {
        return prestador;
    }

    public void setPrestador(MapaPrestador prestador) {
        this.prestador = prestador;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Boolean getEstadoSede() {
        return estadoSede;
    }

    public void setEstadoSede(Boolean estadoSede) {
        this.estadoSede = estadoSede;
    }

    public Integer getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(Integer nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    public Integer getClasePrestador() {
        return clasePrestador;
    }

    public void setClasePrestador(Integer clasePrestador) {
        this.clasePrestador = clasePrestador;
    }

    public Boolean getCapitacion() {
        return capitacion;
    }

    public void setCapitacion(Boolean capitacion) {
        this.capitacion = capitacion;
    }
    
    public String getCapitacionStr() {
        String mensaje = "";
        if (capitacion != null) {
            if(capitacion) {
                mensaje = "SI";
            } else {
                mensaje = "NO";
            }
        }
        return mensaje;
    }

    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }

    public boolean isDireccionGeorreferenciada() {
        return direccionGeorreferenciada;
    }

    public void setDireccionGeorreferenciada(boolean direccionGeorreferenciada) {
        this.direccionGeorreferenciada = direccionGeorreferenciada;
    }

    public Double getDireccionGeorefLatitud() {
        return direccionGeorefLatitud;
    }

    public void setDireccionGeorefLatitud(Double direccionGeorefLatitud) {
        this.direccionGeorefLatitud = direccionGeorefLatitud;
    }

    public Double getDireccionGeorefLongitud() {
        return direccionGeorefLongitud;
    }

    public void setDireccionGeorefLongitud(Double direccionGeorefLongitud) {
        this.direccionGeorefLongitud = direccionGeorefLongitud;
    }

    public Integer getMaeRegionId() {
        return maeRegionId;
    }

    public void setMaeRegionId(Integer maeRegionId) {
        this.maeRegionId = maeRegionId;
    }

    public String getMaeRegionCodigo() {
        return maeRegionCodigo;
    }

    public void setMaeRegionCodigo(String maeRegionCodigo) {
        this.maeRegionCodigo = maeRegionCodigo;
    }

    public String getMaeRegionValor() {
        return maeRegionValor;
    }

    public void setMaeRegionValor(String maeRegionValor) {
        this.maeRegionValor = maeRegionValor;
    }

    public String getMaeClasePrestadorCodigo() {
        return maeClasePrestadorCodigo;
    }

    public void setMaeClasePrestadorCodigo(String maeClasePrestadorCodigo) {
        this.maeClasePrestadorCodigo = maeClasePrestadorCodigo;
    }

    public String getMaeClasePrestadorValor() {
        return maeClasePrestadorValor;
    }

    public void setMaeClasePrestadorValor(String maeClasePrestadorValor) {
        this.maeClasePrestadorValor = maeClasePrestadorValor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaFacturaElectronica() {
        return fechaFacturaElectronica;
    }

    public void setFechaFacturaElectronica(Date fechaFacturaElectronica) {
        this.fechaFacturaElectronica = fechaFacturaElectronica;
    }

    public Integer getGrupoRipsMinisterio() {
        return grupoRipsMinisterio;
    }

    public void setGrupoRipsMinisterio(Integer grupoRipsMinisterio) {
        this.grupoRipsMinisterio = grupoRipsMinisterio;
    }

    public String getGrupoRipsMinisterioStr() {
        String msg= "";
        if (grupoRipsMinisterio != null) {
            switch(grupoRipsMinisterio) {
                case 1:
                    msg = "Grupo 1";
                break;
                case 2:
                    msg = "Grupo 2";
                break;
                case 3:
                    msg = "Grupo 3";
                break;                    
            }
        }
        return msg;
    }
    
}
