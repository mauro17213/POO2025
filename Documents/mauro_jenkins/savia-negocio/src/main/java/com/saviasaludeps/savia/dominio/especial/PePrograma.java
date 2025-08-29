/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PePrograma extends Auditoria  {
    
    private static final long serialVersionUID = 1L;

    public final static int REGISTRO_AFILIADO_NA = 0;
    public final static int REGISTRO_AFILIADO_SUGERIDO = 1;
    public final static int REGISTRO_AFILIADO_AUTOMATICO = 2;
    // ESTOS DE SUGERIDOS
    public final static int ESTADO_SUGERIDO_PENDIENTE = 1;
    public final static int ESTADO_SUGERIDO_MARCADO = 2;
    public final static int ESTADO_SUGERIDO_RECHAZADO = 3;

    private Integer id;
    private String codigoPrograma;
    private String descripcionPrograma;
    private Integer maeTipoProgramaId;
    private String maeTipoProgramaCodigo;
    private String maeTipoProgramaValor;
    private boolean activo;
    private boolean exoneradoCopago;
    private Usuario usuariosId;
    //private boolean marcacionAutomaticaAfiliado;
    private Integer registroAfiliadoSolicitud;
    private Boolean registroAfiliadoAfiliacion;
    private Integer registroAfiliadoHospitalizacion;
    private Integer maeCategoriaId;
    private String maeCategoriaCodigo;
    private String maeCategoriaValor;
    private boolean aplicaRescate;
    private boolean direcciona;
    private boolean aplicaRescateAnexo3Ambulatorio;
    private boolean aplicaRescateAnexo3Hospitalario;
    private boolean aplicaRescateAnexo2Urgencia;
    private Integer maeAgrupadorId;
    private String maeAgrupadorCodigo;
    private String maeAgrupadorValor;
    private Integer sexoAplica;
    private Integer cantidadRegistro;
    private boolean aplicaRecobro;
    private boolean exoneracionObligatoria;

    private List<PeUsuariosPrograma> peUsuariosProgramaList;
//    private List<PeAfiliadosPrograma> peAfiliadosProgramasList;
    private List<PeIpsPrograma> peIpsProgramaList;
    private List<PeProgramaDiagnostico> peProgramaDiagnosticosList;

    private List<PeProgramaTecnologia> peProgramaTecnologiasList;
    private Integer cantidadAfiliados;
    private Integer cantidadAfiliadosAgrupador;

    private PeProgramaDiagnostico objetoDiagnostico;
    private PeProgramaTecnologia objetoTecnologia;

    public static int ALTO_COSTO = 1;
    public static int ENFERMEDADES_HUERFANAS = 2;
    public static int HEMOFILIA = 3;
    public static int RIESCO_CARDIOVASCULAR = 4;
    public static int SALUD_PUBLICA = 5;

    public PePrograma() {
        peProgramaDiagnosticosList = new ArrayList<>();
        peProgramaTecnologiasList = new ArrayList<>();
        peIpsProgramaList = new ArrayList<>();
    }

    public PePrograma(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getDescripcionPrograma() {
        return descripcionPrograma;
    }

    public void setDescripcionPrograma(String descripcionPrograma) {
        this.descripcionPrograma = descripcionPrograma;
    }

    public Integer getMaeTipoProgramaId() {
        return maeTipoProgramaId;
    }

    public void setMaeTipoProgramaId(Integer maeTipoProgramaId) {
        this.maeTipoProgramaId = maeTipoProgramaId;
    }

    public String getMaeTipoProgramaCodigo() {
        return maeTipoProgramaCodigo;
    }

    public void setMaeTipoProgramaCodigo(String maeTipoProgramaCodigo) {
        this.maeTipoProgramaCodigo = maeTipoProgramaCodigo;
    }

    public String getMaeTipoProgramaValor() {
        return maeTipoProgramaValor;
    }

    public void setMaeTipoProgramaValor(String maeTipoProgramaValor) {
        this.maeTipoProgramaValor = maeTipoProgramaValor;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return (isActivo()) ? "SI" : "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuario usuariosId) {
        this.usuariosId = usuariosId;
    }

    public List<PeUsuariosPrograma> getPeUsuariosProgramaList() {
        return peUsuariosProgramaList;
    }

    public void setPeUsuariosProgramaList(List<PeUsuariosPrograma> peUsuariosProgramaList) {
        this.peUsuariosProgramaList = peUsuariosProgramaList;
    }

    public List<PeIpsPrograma> getPeIpsProgramaList() {
        return peIpsProgramaList;
    }

    public void setPeIpsProgramasList(List<PeIpsPrograma> peIpsProgramaList) {
        this.peIpsProgramaList = peIpsProgramaList;
    }

    public List<PeProgramaDiagnostico> getPeProgramaDiagnosticosList() {
        return peProgramaDiagnosticosList;
    }

    public void setPeProgramaDiagnosticosList(List<PeProgramaDiagnostico> peProgramaDiagnosticosList) {
        this.peProgramaDiagnosticosList = peProgramaDiagnosticosList;
    }

    public List<PeProgramaTecnologia> getPeProgramaTecnologiasList() {
        return peProgramaTecnologiasList;
    }

    public void setPeProgramaTecnologiasList(List<PeProgramaTecnologia> peProgramaTecnologiasList) {
        this.peProgramaTecnologiasList = peProgramaTecnologiasList;
    }

    public PeProgramaDiagnostico getObjetoDiagnostico() {
        return objetoDiagnostico;
    }

    public void setObjetoDiagnostico(PeProgramaDiagnostico objetoDiagnostico) {
        this.objetoDiagnostico = objetoDiagnostico;
    }

    public PeProgramaTecnologia getObjetoTecnologia() {
        return objetoTecnologia;
    }

    public void setObjetoTecnologia(PeProgramaTecnologia objetoTecnologia) {
        this.objetoTecnologia = objetoTecnologia;
    }

    public Integer getCantidadAfiliados() {
        return cantidadAfiliados;
    }

    public void setCantidadAfiliados(Integer cantidadAfiliados) {
        this.cantidadAfiliados = cantidadAfiliados;
    }

    public Integer getMaeCategoriaId() {
        return maeCategoriaId;
    }

    public void setMaeCategoriaId(Integer maeCategoriaId) {
        this.maeCategoriaId = maeCategoriaId;
    }

    public String getMaeCategoriaCodigo() {
        return maeCategoriaCodigo;
    }

    public void setMaeCategoriaCodigo(String maeCategoriaCodigo) {
        this.maeCategoriaCodigo = maeCategoriaCodigo;
    }

    public String getMaeCategoriaValor() {
        return maeCategoriaValor;
    }

    public void setMaeCategoriaValor(String maeCategoriaValor) {
        this.maeCategoriaValor = maeCategoriaValor;
    }

    public boolean getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(boolean direcciona) {
        this.direcciona = direcciona;
    }

    /**
     * @return the exoneradoCopago
     */
    public boolean isExoneradoCopago() {
        return exoneradoCopago;
    }

    public String getBooleanToStr(Boolean valor) {
        return (valor) ? "SI" : "NO";
    }

    public boolean getAplicaRescate() {
        return aplicaRescate;
    }

    public void setAplicaRescate(boolean aplicaRescate) {
        this.aplicaRescate = aplicaRescate;
    }

    /**
     * @param exoneradoCopago the exoneradoCopago to set
     */
    public void setExoneradoCopago(boolean exoneradoCopago) {
        this.exoneradoCopago = exoneradoCopago;
    }

    public Integer getRegistroAfiliadoSolicitud() {
        return registroAfiliadoSolicitud;
    }

    public void setRegistroAfiliadoSolicitud(Integer registroAfiliadoSolicitud) {
        this.registroAfiliadoSolicitud = registroAfiliadoSolicitud;
    }

    public Boolean getRegistroAfiliadoAfiliacion() {
        return registroAfiliadoAfiliacion;
    }

    public void setRegistroAfiliadoAfiliacion(Boolean registroAfiliadoAfiliacion) {
        this.registroAfiliadoAfiliacion = registroAfiliadoAfiliacion;
    }

    public Integer getRegistroAfiliadoHospitalizacion() {
        return registroAfiliadoHospitalizacion;
    }

    public void setRegistroAfiliadoHospitalizacion(Integer registroAfiliadoHospitalizacion) {
        this.registroAfiliadoHospitalizacion = registroAfiliadoHospitalizacion;
    }

    public boolean getAplicaRescateAnexo3Ambulatorio() {
        return aplicaRescateAnexo3Ambulatorio;
    }

    public void setAplicaRescateAnexo3Ambulatorio(boolean aplicaRescateAnexo3Ambulatorio) {
        this.aplicaRescateAnexo3Ambulatorio = aplicaRescateAnexo3Ambulatorio;
    }

    public boolean getAplicaRescateAnexo3Hospitalario() {
        return aplicaRescateAnexo3Hospitalario;
    }

    public void setAplicaRescateAnexo3Hospitalario(boolean aplicaRescateAnexo3Hospitalario) {
        this.aplicaRescateAnexo3Hospitalario = aplicaRescateAnexo3Hospitalario;
    }

    public boolean getAplicaRescateAnexo2Urgencia() {
        return aplicaRescateAnexo2Urgencia;
    }

    public void setAplicaRescateAnexo2Urgencia(boolean aplicaRescateAnexo2Urgencia) {
        this.aplicaRescateAnexo2Urgencia = aplicaRescateAnexo2Urgencia;
    }

    public Integer getMaeAgrupadorId() {
        return maeAgrupadorId;
    }

    public void setMaeAgrupadorId(Integer maeAgrupadorId) {
        this.maeAgrupadorId = maeAgrupadorId;
    }

    public String getMaeAgrupadorCodigo() {
        return maeAgrupadorCodigo;
    }

    public void setMaeAgrupadorCodigo(String maeAgrupadorCodigo) {
        this.maeAgrupadorCodigo = maeAgrupadorCodigo;
    }

    public String getMaeAgrupadorValor() {
        return maeAgrupadorValor;
    }

    public void setMaeAgrupadorValor(String maeAgrupadorValor) {
        this.maeAgrupadorValor = maeAgrupadorValor;
    }

    public Integer getCantidadAfiliadosAgrupador() {
        return cantidadAfiliadosAgrupador;
    }

    public void setCantidadAfiliadosAgrupador(Integer cantidadAfiliadosAgrupador) {
        this.cantidadAfiliadosAgrupador = cantidadAfiliadosAgrupador;
    }

    public String getAgrupador() {
        if (maeAgrupadorId == null) {
            return "";
        }
        return maeAgrupadorValor + " (" + cantidadAfiliadosAgrupador + ")";
    }

    public Integer getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(Integer sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public Integer getCantidadRegistro() {
        return cantidadRegistro;
    }

    public void setCantidadRegistro(Integer cantidadRegistro) {
        this.cantidadRegistro = cantidadRegistro;
    }

    public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public boolean isExoneracionObligatoria() {
        return exoneracionObligatoria;
    }

    public void setExoneracionObligatoria(boolean exonoracionObligatoria) {
        this.exoneracionObligatoria = exonoracionObligatoria;
    }

    @Override
    public String toString() {
        return "PePrograma{" + "id=" + id + ", codigoPrograma=" + codigoPrograma + ", descripcionPrograma=" + descripcionPrograma + ", maeTipoProgramaId=" + maeTipoProgramaId + ", maeTipoProgramaCodigo=" + maeTipoProgramaCodigo + ", maeTipoProgramaValor=" + maeTipoProgramaValor + ", activo=" + activo + ", exoneradoCopago=" + exoneradoCopago + ", usuariosId=" + usuariosId + ", registroAfiliadoSolicitud=" + registroAfiliadoSolicitud + ", registroAfiliadoAfiliacion=" + registroAfiliadoAfiliacion + ", registroAfiliadoHospitalizacion=" + registroAfiliadoHospitalizacion + ", maeCategoriaId=" + maeCategoriaId + ", maeCategoriaCodigo=" + maeCategoriaCodigo + ", maeCategoriaValor=" + maeCategoriaValor + ", aplicaRescate=" + aplicaRescate + ", direcciona=" + direcciona + ", aplicaRescateAnexo3Ambulatorio=" + aplicaRescateAnexo3Ambulatorio + ", aplicaRescateAnexo3Hospitalario=" + aplicaRescateAnexo3Hospitalario + ", aplicaRescateAnexo2Urgencia=" + aplicaRescateAnexo2Urgencia + ", maeAgrupadorId=" + maeAgrupadorId + ", maeAgrupadorCodigo=" + maeAgrupadorCodigo + ", maeAgrupadorValor=" + maeAgrupadorValor + ", sexoAplica=" + sexoAplica + ", cantidadRegistro=" + cantidadRegistro + ", aplicaRecobro=" + aplicaRecobro + ", exonoracionObligatoria=" + exoneracionObligatoria + ", peUsuariosProgramaList=" + peUsuariosProgramaList + ", peIpsProgramaList=" + peIpsProgramaList + ", peProgramaDiagnosticosList=" + peProgramaDiagnosticosList + ", peProgramaTecnologiasList=" + peProgramaTecnologiasList + ", cantidadAfiliados=" + cantidadAfiliados + ", cantidadAfiliadosAgrupador=" + cantidadAfiliadosAgrupador + ", objetoDiagnostico=" + objetoDiagnostico + ", objetoTecnologia=" + objetoTecnologia + '}';
    }
    
}
