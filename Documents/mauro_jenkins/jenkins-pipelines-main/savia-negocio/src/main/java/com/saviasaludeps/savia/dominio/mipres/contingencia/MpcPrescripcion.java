/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres.contingencia;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public class MpcPrescripcion extends Auditoria {

    public static final short ESTADO_CREADO = 0;
    public static final short ESTADO_EN_GESTION = 1;
    public static final short ESTADO_DIRECCIONADO = 2;
    public static final short ESTADO_NO_DIRECCIONADO = 3;

    public static final short TIPO_TECNOLOGIA_MEDICAMENTO = 1;
    public static final short TIPO_TECNOLOGIA_PROCEDIMIENTO = 2;
    public static final short TIPO_TECNOLOGIA_DISPOSITIVO = 3;
    public static final short TIPO_TECNOLOGIA_NUTRICIONAL = 4;
    public static final short TIPO_TECNOLOGIA_COMPLEMENTARIO = 5;

    private Integer id;
    private Empresa Empresa;
    private AsegAfiliado asegAfiliado;
    private CntPrestadorSede cntPrescriptorPrestadorSede;
    private CntPrestadorSede cntDireccionadoPrestadorSede;
    private short estado;
    private int maePrescriptorTipoDocumentoId;
    private String maePrescriptorTipoDocumentoCodigo;
    private String maePrescriptorTipoDocumentoValor;
    private String prescriptorNumeroDocumento;
    private String prescriptorCodigoHabilitacion;
    private String prescriptorCorreoElectronico;
    private int maeAfiliadoTipoDocumentoId;
    private String maeAfiliadoTipoDocumentoCodigo;
    private String maeAfiliadoTipoDocumentoValor;
    private String afiliadoNumeroDocumento;
    private String afiliadoPrimerNombre;
    private String afiliadoSegundoNombre;
    private String afiliadoPrimerApellido;
    private String afiliadoSegundoApellido;
    private Date fechaHora;
    private String ambito;    
    private Boolean referenciaAmbitoAtencion;
    private boolean recobrante;
    private short tipoTecnologia;
    private boolean actaJuantaProfesionales;
    private boolean concentimientoInformado;
    private boolean formatoIntegralidad;
    private String rechazoJustificacion;
    private String consecutivo;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer cantidad;
    private Short periodicidad;
    private Short numeroEntregas;
    private List<MpcPrescripcionAdjunto> listaMpcPrescripcionAdjuntos;
    private List<MpcProgramacionEntrega> listaMpcProgramacionEntregas;

    public MpcPrescripcion() {
    }

    public MpcPrescripcion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa) {
        this.Empresa = Empresa;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntPrestadorSede getCntPrescriptorPrestadorSede() {
        return cntPrescriptorPrestadorSede;
    }

    public void setCntPrescriptorPrestadorSede(CntPrestadorSede cntPrescriptorPrestadorSede) {
        this.cntPrescriptorPrestadorSede = cntPrescriptorPrestadorSede;
    }

    public CntPrestadorSede getCntDireccionadoPrestadorSede() {
        return cntDireccionadoPrestadorSede;
    }

    public void setCntDireccionadoPrestadorSede(CntPrestadorSede cntDireccionadoPrestadorSede) {
        this.cntDireccionadoPrestadorSede = cntDireccionadoPrestadorSede;
    }

    public short getEstado() {
        return estado;
    }

    public String getEstadoStr() {
        switch (estado) {
            case ESTADO_CREADO:
                return "Creado";
            case ESTADO_EN_GESTION:
                return "En Gestion";
            case ESTADO_DIRECCIONADO:
                return "Direccionado";
            case ESTADO_NO_DIRECCIONADO:
                return "No Direccionado";
            default:
                return "";
        }
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public int getMaePrescriptorTipoDocumentoId() {
        return maePrescriptorTipoDocumentoId;
    }

    public void setMaePrescriptorTipoDocumentoId(int maePrescriptorTipoDocumentoId) {
        this.maePrescriptorTipoDocumentoId = maePrescriptorTipoDocumentoId;
    }

    public String getMaePrescriptorTipoDocumentoCodigo() {
        return maePrescriptorTipoDocumentoCodigo;
    }

    public void setMaePrescriptorTipoDocumentoCodigo(String maePrescriptorTipoDocumentoCodigo) {
        this.maePrescriptorTipoDocumentoCodigo = maePrescriptorTipoDocumentoCodigo;
    }

    public String getMaePrescriptorTipoDocumentoValor() {
        return maePrescriptorTipoDocumentoValor;
    }

    public void setMaePrescriptorTipoDocumentoValor(String maePrescriptorTipoDocumentoValor) {
        this.maePrescriptorTipoDocumentoValor = maePrescriptorTipoDocumentoValor;
    }

    public String getPrescriptorNumeroDocumento() {
        return prescriptorNumeroDocumento;
    }

    public void setPrescriptorNumeroDocumento(String prescriptorNumeroDocumento) {
        this.prescriptorNumeroDocumento = prescriptorNumeroDocumento;
    }

    public String getPrescriptorCodigoHabilitacion() {
        return prescriptorCodigoHabilitacion;
    }

    public void setPrescriptorCodigoHabilitacion(String prescriptorCodigoHabilitacion) {
        this.prescriptorCodigoHabilitacion = prescriptorCodigoHabilitacion;
    }

    public String getPrescriptorCorreoElectronico() {
        return prescriptorCorreoElectronico;
    }

    public void setPrescriptorCorreoElectronico(String prescriptorCorreoElectronico) {
        this.prescriptorCorreoElectronico = prescriptorCorreoElectronico;
    }

    public int getMaeAfiliadoTipoDocumentoId() {
        return maeAfiliadoTipoDocumentoId;
    }

    public void setMaeAfiliadoTipoDocumentoId(int maeAfiliadoTipoDocumentoId) {
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
    }

    public String getMaeAfiliadoTipoDocumentoCodigo() {
        return maeAfiliadoTipoDocumentoCodigo;
    }

    public void setMaeAfiliadoTipoDocumentoCodigo(String maeAfiliadoTipoDocumentoCodigo) {
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
    }

    public String getMaeAfiliadoTipoDocumentoValor() {
        return maeAfiliadoTipoDocumentoValor;
    }

    public void setMaeAfiliadoTipoDocumentoValor(String maeAfiliadoTipoDocumentoValor) {
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public String getAfiliadoNombres() {
        return ((afiliadoPrimerNombre == null || afiliadoPrimerNombre.equals("")) ? "" : afiliadoPrimerNombre) + ((afiliadoSegundoNombre == null || afiliadoSegundoNombre.equals("")) ? "" : " " + afiliadoSegundoNombre);
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public String getAfiliadoApellidos() {
        return ((afiliadoPrimerApellido == null || afiliadoPrimerApellido.equals("")) ? "" : afiliadoPrimerApellido) + ((afiliadoSegundoApellido == null || afiliadoSegundoApellido.equals("")) ? "" : " " + afiliadoSegundoApellido);
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Boolean getReferenciaAmbitoAtencion() {
        return referenciaAmbitoAtencion;
    }

    public void setReferenciaAmbitoAtencion(Boolean referenciaAmbitoAtencion) {
        this.referenciaAmbitoAtencion = referenciaAmbitoAtencion;
    }
    
    

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isRecobrante() {
        return recobrante;
    }

    public String getRecobranteStr() {
        return (recobrante) ? "Si" : "No";
    }

    public void setRecobrante(boolean recobrante) {
        this.recobrante = recobrante;
    }

    public short getTipoTecnologia() {
        return tipoTecnologia;
    }

    public String getTipoTecnologiaStr() {
        switch (tipoTecnologia) {
            case TIPO_TECNOLOGIA_MEDICAMENTO:
                return "Medicamento";
            case TIPO_TECNOLOGIA_PROCEDIMIENTO:
                return "Procedimiento (Tecnologias)";
            case TIPO_TECNOLOGIA_DISPOSITIVO:
                return "Dispositivo (Insumos)";
            case TIPO_TECNOLOGIA_NUTRICIONAL:
                return "Productos Nutricional (Medicamentos)";
            case TIPO_TECNOLOGIA_COMPLEMENTARIO:
                return "Servicios Complementarios (Insumos)";
            default:
                return "";
        }
    }

    public void setTipoTecnologia(short tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public boolean isActaJuantaProfesionales() {
        return actaJuantaProfesionales;
    }

    public String getActaJuantaProfesionalesStr() {
        return (actaJuantaProfesionales) ? "Si" : "No";
    }

    public void setActaJuantaProfesionales(boolean actaJuantaProfesionales) {
        this.actaJuantaProfesionales = actaJuantaProfesionales;
    }

    public boolean isConcentimientoInformado() {
        return concentimientoInformado;
    }

    public String getConcentimientoInformadoStr() {
        return (concentimientoInformado) ? "Si" : "No";
    }

    public void setConcentimientoInformado(boolean concentimientoInformado) {
        this.concentimientoInformado = concentimientoInformado;
    }

    public boolean isFormatoIntegralidad() {
        return formatoIntegralidad;
    }

    public String getFormatoIntegralidadoStr() {
        return (formatoIntegralidad) ? "SI" : "NO";
    }

    public void setFormatoIntegralidad(boolean formatoIntegralidad) {
        this.formatoIntegralidad = formatoIntegralidad;
    }

    public String getRechazoJustificacion() {
        return rechazoJustificacion;
    }

    public void setRechazoJustificacion(String rechazoJustificacion) {
        this.rechazoJustificacion = rechazoJustificacion;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public Short getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Short periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Short getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Short numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public List<MpcPrescripcionAdjunto> getListaMpcPrescripcionAdjuntos() {
        return listaMpcPrescripcionAdjuntos;
    }

    public void setListaMpcPrescripcionAdjuntos(List<MpcPrescripcionAdjunto> listaMpcPrescripcionAdjuntos) {
        this.listaMpcPrescripcionAdjuntos = listaMpcPrescripcionAdjuntos;
    }

    public List<MpcProgramacionEntrega> getListaMpcProgramacionEntregas() {
        return listaMpcProgramacionEntregas;
    }

    public void setListaMpcProgramacionEntregas(List<MpcProgramacionEntrega> listaMpcProgramacionEntregas) {
        this.listaMpcProgramacionEntregas = listaMpcProgramacionEntregas;
    }

    @Override
    public String toString() {
        return "MpcPrescripcion{" + "id=" + id + ", Empresa=" + Empresa + ", asegAfiliados=" + asegAfiliado + ", cntPrescriptorPrestadorSedes=" + cntPrescriptorPrestadorSede + ", cntDireccionadoPrestadorSedes=" + cntDireccionadoPrestadorSede + ", estado=" + estado + ", maePrescriptorTipoDocumentoId=" + maePrescriptorTipoDocumentoId + ", maePrescriptorTipoDocumentoCodigo=" + maePrescriptorTipoDocumentoCodigo + ", maePrescriptorTipoDocumentoValor=" + maePrescriptorTipoDocumentoValor + ", prescriptorNumeroDocumento=" + prescriptorNumeroDocumento + ", prescriptorCodigoHabilitacion=" + prescriptorCodigoHabilitacion + ", prescriptorCorreoElectronico=" + prescriptorCorreoElectronico + ", maeAfiliadoTipoDocumentoId=" + maeAfiliadoTipoDocumentoId + ", maeAfiliadoTipoDocumentoCodigo=" + maeAfiliadoTipoDocumentoCodigo + ", maeAfiliadoTipoDocumentoValor=" + maeAfiliadoTipoDocumentoValor + ", afiliadoNumeroDocumento=" + afiliadoNumeroDocumento + ", afiliadoPrimerNombre=" + afiliadoPrimerNombre + ", afiliadoSegundoNombre=" + afiliadoSegundoNombre + ", afiliadoPrimerApellido=" + afiliadoPrimerApellido + ", afiliadoSegundoApellido=" + afiliadoSegundoApellido + ", fechaHora=" + fechaHora + ", recobrante=" + recobrante + ", tipoTecnologia=" + tipoTecnologia + ", actaJuantaProfesionales=" + actaJuantaProfesionales + ", concentimientoInformado=" + concentimientoInformado + ", formatoIntegralidad=" + formatoIntegralidad + ", rechazoJustificacion=" + rechazoJustificacion + ", consecutivo=" + consecutivo + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", cantidad=" + cantidad + ", periodicidad=" + periodicidad + ", numeroEntregas=" + numeroEntregas + '}';
    }

}
