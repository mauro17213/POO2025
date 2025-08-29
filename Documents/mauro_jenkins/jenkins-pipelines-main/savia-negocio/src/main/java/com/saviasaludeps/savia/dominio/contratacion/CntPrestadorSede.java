package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CntPrestadorSede extends Auditoria {
    
    public static final int ID_SEDE_SIN_ESPECIFICAR = 1;

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
    private CntPrestador cntPrestador;
    //2024-11-08 jyperez nuevos campos
    private Date fechaFacturaElectronica;
    private Integer grupoRipsMinisterio;
    // campos Contratacion
    private boolean nuevoRegistro;
    private boolean editado;
    //2022-05-26 jpyerez nuevos campos
    private List<CntPrestadorContacto> listaCntPrestadorContactos;
    //lista necesaria para almacenar los registros a borrar
    private List<CntPrestadorContacto> listaCntPrestadorContactosBorrar;
    
    //campo CRUE
    private Ubicacion ubicacion;

    public CntPrestadorSede() {
        nuevoRegistro = false;
        editado = false;
        listaCntPrestadorContactos = new ArrayList<>();
        listaCntPrestadorContactosBorrar = new ArrayList<>();
    }

    public CntPrestadorSede(Integer id) {
        this.id = id;
        nuevoRegistro = false;
        editado = false;
        listaCntPrestadorContactos = new ArrayList<>();
        listaCntPrestadorContactosBorrar = new ArrayList<>();
    }
    
    public CntPrestadorSede(Integer id, String codigoHabilitacion) {
        this.id = id;
        this.codigoHabilitacionSede = codigoHabilitacion;
        nuevoRegistro = false;
        editado = false;
        listaCntPrestadorContactos = new ArrayList<>();
        listaCntPrestadorContactosBorrar = new ArrayList<>();
    }

    public CntPrestadorSede(Integer id, String nombreSede, CntPrestador cntPrestador) {
        this.id = id;
        this.nombreSede = nombreSede;
        this.cntPrestador = cntPrestador;
        nuevoRegistro = false;
        editado = false;
        listaCntPrestadorContactos = new ArrayList<>();
        listaCntPrestadorContactosBorrar = new ArrayList<>();
    }
    public CntPrestadorSede(Integer id,Integer idPrestador, String codigoPrestador,
            Integer idUbicacion,String nombre,String codigoHabilitacion, Boolean estado ) {
        this.id = id;
        this.cntPrestador = new CntPrestador(idPrestador);
        this.codigoPrestador = codigoPrestador;
        this.ubicacionId = idUbicacion;
        this.nombreSede = nombre;
        this.codigoHabilitacionSede = codigoHabilitacion;
        this.estadoSede = estado;
        nuevoRegistro = false;
        editado = false;
        listaCntPrestadorContactos = new ArrayList<>();
        listaCntPrestadorContactosBorrar = new ArrayList<>();
    }

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

    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    /**
     * @return the ubicacionId
     */
    public int getUbicacionId() {
        return ubicacionId;
    }

    /**
     * @param ubicacionId the ubicacionId to set
     */
    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    /**
     * @return the estadoSede
     */
    public Boolean getEstadoSede() {
        return estadoSede;
    }

    /**
     * @param estadoSede the estadoSede to set
     */
    public void setEstadoSede(Boolean estadoSede) {
        this.estadoSede = estadoSede;
    }

    /**
     * @return the nivelAtencion
     */
    public Integer getNivelAtencion() {
        return nivelAtencion;
    }

    /**
     * @param nivelAtencion the nivelAtencion to set
     */
    public void setNivelAtencion(Integer nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    /**
     * @return the clasePrestador
     */
    public Integer getClasePrestador() {
        return clasePrestador;
    }

    /**
     * @param clasePrestador the clasePrestador to set
     */
    public void setClasePrestador(Integer clasePrestador) {
        this.clasePrestador = clasePrestador;
    }

    /**
     * @return the capitacion
     */
    public Boolean getCapitacion() {
        return capitacion;
    }

    /**
     * @param capitacion the capitacion to set
     */
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

    /**
     * @return the nuevoRegistro
     */
    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    /**
     * @param nuevoRegistro the nuevoRegistro to set
     */
    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }

    /**
     * @return the editado
     */
    public boolean isEditado() {
        return editado;
    }

    /**
     * @param editado the editado to set
     */
    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    /**
     * @return the maeRegionId
     */
    public Integer getMaeRegionId() {
        return maeRegionId;
    }

    /**
     * @param maeRegionId the maeRegionId to set
     */
    public void setMaeRegionId(Integer maeRegionId) {
        this.maeRegionId = maeRegionId;
    }

    /**
     * @return the maeRegionCodigo
     */
    public String getMaeRegionCodigo() {
        return maeRegionCodigo;
    }

    /**
     * @param maeRegionCodigo the maeRegionCodigo to set
     */
    public void setMaeRegionCodigo(String maeRegionCodigo) {
        this.maeRegionCodigo = maeRegionCodigo;
    }

    /**
     * @return the maeRegionValor
     */
    public String getMaeRegionValor() {
        return maeRegionValor;
    }

    /**
     * @param maeRegionValor the maeRegionValor to set
     */
    public void setMaeRegionValor(String maeRegionValor) {
        this.maeRegionValor = maeRegionValor;
    }

    /**
     * @return the maeClasePrestadorCodigo
     */
    public String getMaeClasePrestadorCodigo() {
        return maeClasePrestadorCodigo;
    }

    /**
     * @param maeClasePrestadorCodigo the maeClasePrestadorCodigo to set
     */
    public void setMaeClasePrestadorCodigo(String maeClasePrestadorCodigo) {
        this.maeClasePrestadorCodigo = maeClasePrestadorCodigo;
    }

    /**
     * @return the maeClasePrestadorValor
     */
    public String getMaeClasePrestadorValor() {
        return maeClasePrestadorValor;
    }

    /**
     * @param maeClasePrestadorValor the maeClasePrestadorValor to set
     */
    public void setMaeClasePrestadorValor(String maeClasePrestadorValor) {
        this.maeClasePrestadorValor = maeClasePrestadorValor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "CntPrestadorSede{" + "id=" + id + ", codigoPrestador=" + codigoPrestador + ", ubicacionId=" + ubicacionId + ", maeRegionId=" + maeRegionId + ", maeRegionCodigo=" + maeRegionCodigo + ", maeRegionValor=" + maeRegionValor + ", direccion=" + direccion + ", nombreSede=" + nombreSede + ", codigoSede=" + codigoSede + ", codigoHabilitacionSede=" + codigoHabilitacionSede + ", zonaPrecedencia=" + zonaPrecedencia + ", estadoSede=" + estadoSede + ", nivelAtencion=" + nivelAtencion + ", clasePrestador=" + clasePrestador + ", maeClasePrestadorCodigo=" + maeClasePrestadorCodigo + ", maeClasePrestadorValor=" + maeClasePrestadorValor + ", fax=" + fax + ", telefonoCitas=" + telefonoCitas + ", correoElectronico=" + correoElectronico + ", telefonoAdministrativo=" + telefonoAdministrativo + ", capitacion=" + capitacion + ", cntPrestador=" + cntPrestador + '}';
    }

    /**
     * @return the listaCntPrestadorContactos
     */
    public List<CntPrestadorContacto> getListaCntPrestadorContactos() {
        return listaCntPrestadorContactos;
    }

    /**
     * @param listaCntPrestadorContactos the listaCntPrestadorContactos to set
     */
    public void setListaCntPrestadorContactos(List<CntPrestadorContacto> listaCntPrestadorContactos) {
        this.listaCntPrestadorContactos = listaCntPrestadorContactos;
    }

    /**
     * @return the listaCntPrestadorContactosBorrar
     */
    public List<CntPrestadorContacto> getListaCntPrestadorContactosBorrar() {
        return listaCntPrestadorContactosBorrar;
    }

    /**
     * @param listaCntPrestadorContactosBorrar the listaCntPrestadorContactosBorrar to set
     */
    public void setListaCntPrestadorContactosBorrar(List<CntPrestadorContacto> listaCntPrestadorContactosBorrar) {
        this.listaCntPrestadorContactosBorrar = listaCntPrestadorContactosBorrar;
    }

    /**
     * @return the fechaFacturaElectronica
     */
    public Date getFechaFacturaElectronica() {
        return fechaFacturaElectronica;
    }

    /**
     * @param fechaFacturaElectronica the fechaFacturaElectronica to set
     */
    public void setFechaFacturaElectronica(Date fechaFacturaElectronica) {
        this.fechaFacturaElectronica = fechaFacturaElectronica;
    }

    /**
     * @return the grupoRipsMinisterio
     */
    public Integer getGrupoRipsMinisterio() {
        return grupoRipsMinisterio;
    }

    /**
     * @param grupoRipsMinisterio the grupoRipsMinisterio to set
     */
    public void setGrupoRipsMinisterio(Integer grupoRipsMinisterio) {
        this.grupoRipsMinisterio = grupoRipsMinisterio;
    }
    
    /**
     * @return the grupoRipsMinisterio
     */
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
