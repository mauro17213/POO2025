package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author bsgomez
 */
public class MpPrescripcionDetalle extends Auditoria {

    public static final String ID_AMBITO_AMBULATORIO_PRIORIZADO = "11";
    public static final String ID_AMBITO_AMBULATORIO_NO_PRIORIZADO = "12";
    public static final String ID_AMBITO_HOSPITALARIO_DOMICILIARIO = "21";
    public static final String ID_AMBITO_HOSPITALARIO_INTERNACION = "22";
    public static final String ID_AMBITO_URGENCIAS = "30";

    public static final int ID_TIPO_TECNOLOGIA_MEDICAMENTO = 1;
    public static final int ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS = 2;
    public static final int ID_TIPO_TECNOLOGIA_DISPOSITIVO = 3;
    public static final int ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES = 4;
    public static final int ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO = 5;

    private Integer idPrescripcion;
    private String numeroPrescripcion;
    private String codigoAmbitoAtencion;
    private Date fechaPrescripcion;
    private Date horaPrescripcion;
    private Integer estadoPrescripcion;

    private Integer idAfiliado;
    private String primerNombreAfiliado;
    private String primerApellidoAfiliado;
    private String segundoNombreAfiliado;
    private String segundoApellidoAfiliado;

    private Integer maeTipoDocumentoAfiliadoId;
    private String maeTipoDocumentoAfiliadoCodigo;
    private String tipoDocumentoAfiliado;
    private String numeroDocumentoAfiliado;
    private String regimenAfiliado;
    private Ubicacion afiliacionUbicacion;

    private Integer maeTipoDocumentoAfiliadoIdMp;
    private String maeTipoDocumentoAfiliadoCodigoMp;
    private String tipoDocumentoAfiliadoMp;
    private String numeroDocumentoAfiliadoMp;

    private Integer idItem;
    private Integer tipoTecnologiaItem;
    private Integer estadoItem;
    private Integer juntaP;
    private String nombreItem;
    private int consecutivoItem;

    private String obtenerTipoTecnologia;
    private String obtenerjunta;
    private String obtenerAmbito;
    private Boolean referenciaContra;
    private String regimen;

    private String colorTecnologia;
    private String colorEstado;
    private String colorJunta;

    private String usuarioAtiende;
    private String terminalAtiende;
    private Date fechaHoraAtiende;
    private Boolean atendido;
    private Boolean banderaAtencion;

    private String documentoPrestador;
    private String primerNombrePrestador;
    private String primerApellidoPrestador;
    private String segundoNombrePrestador;
    private String segundoApellidoPrestador;
    private String numeroDocumentoPrestador;

    public MpPrescripcionDetalle() {
    }

    public MpPrescripcionDetalle(Integer idPrescripcion) {
        this.idPrescripcion = idPrescripcion;
    }

    public Integer getIdPrescripcion() {
        return idPrescripcion;
    }

    public void setIdPrescripcion(Integer idPrescripcion) {
        this.idPrescripcion = idPrescripcion;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getCodigoAmbitoAtencion() {
        return codigoAmbitoAtencion;
    }

    public void setCodigoAmbitoAtencion(String codigoAmbitoAtencion) {
        this.codigoAmbitoAtencion = codigoAmbitoAtencion;
    }

    public Date getFechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(Date fechaPrescripcion) {
        this.fechaPrescripcion = fechaPrescripcion;
    }

    public Date getHoraPrescripcion() {
        return horaPrescripcion;
    }

    public void setHoraPrescripcion(Date horaPrescripcion) {
        this.horaPrescripcion = horaPrescripcion;
    }

    public Integer getEstadoPrescripcion() {
        return estadoPrescripcion;
    }

    public void setEstadoPrescripcion(Integer estadoPrescripcion) {
        this.estadoPrescripcion = estadoPrescripcion;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getPrimerNombreAfiliado() {
        return primerNombreAfiliado;
    }

    public void setPrimerNombreAfiliado(String primerNombreAfiliado) {
        this.primerNombreAfiliado = primerNombreAfiliado;
    }

    public String getPrimerApellidoAfiliado() {
        return primerApellidoAfiliado;
    }

    public void setPrimerApellidoAfiliado(String primerApellidoAfiliado) {
        this.primerApellidoAfiliado = primerApellidoAfiliado;
    }

    public String getSegundoNombreAfiliado() {
        return segundoNombreAfiliado;
    }

    public void setSegundoNombreAfiliado(String segundoNombreAfiliado) {
        this.segundoNombreAfiliado = segundoNombreAfiliado;
    }

    public String getSegundoApellidoAfiliado() {
        return segundoApellidoAfiliado;
    }

    public void setSegundoApellidoAfiliado(String segundoApellidoAfiliado) {
        this.segundoApellidoAfiliado = segundoApellidoAfiliado;
    }

    public String getNumeroDocumentoAfiliado() {
        return numeroDocumentoAfiliado;
    }

    public void setNumeroDocumentoAfiliado(String numeroDocumentoAfiliado) {
        this.numeroDocumentoAfiliado = numeroDocumentoAfiliado;
    }

    public Integer getMaeTipoDocumentoAfiliadoId() {
        return maeTipoDocumentoAfiliadoId;
    }

    public void setMaeTipoDocumentoAfiliadoId(Integer maeTipoDocumentoAfiliadoId) {
        this.maeTipoDocumentoAfiliadoId = maeTipoDocumentoAfiliadoId;
    }

    public String getMaeTipoDocumentoAfiliadoCodigo() {
        return maeTipoDocumentoAfiliadoCodigo;
    }

    public void setMaeTipoDocumentoAfiliadoCodigo(String maeTipoDocumentoAfiliadoCodigo) {
        this.maeTipoDocumentoAfiliadoCodigo = maeTipoDocumentoAfiliadoCodigo;
    }

    public String getTipoDocumentoAfiliado() {
        return tipoDocumentoAfiliado;
    }

    public void setTipoDocumentoAfiliado(String tipoDocumentoAfiliado) {
        this.tipoDocumentoAfiliado = tipoDocumentoAfiliado;
    }

    public String getRegimenAfiliado() {
        return regimenAfiliado;
    }

    public Ubicacion getAfiliacionUbicacion() {
        return afiliacionUbicacion;
    }

    public void setAfiliacionUbicacion(Ubicacion afiliacionUbicacion) {
        this.afiliacionUbicacion = afiliacionUbicacion;
    }

    public Integer getMaeTipoDocumentoAfiliadoIdMp() {
        return maeTipoDocumentoAfiliadoIdMp;
    }

    public void setMaeTipoDocumentoAfiliadoIdMp(Integer maeTipoDocumentoAfiliadoIdMp) {
        this.maeTipoDocumentoAfiliadoIdMp = maeTipoDocumentoAfiliadoIdMp;
    }

    public String getMaeTipoDocumentoAfiliadoCodigoMp() {
        return maeTipoDocumentoAfiliadoCodigoMp;
    }

    public void setMaeTipoDocumentoAfiliadoCodigoMp(String maeTipoDocumentoAfiliadoCodigoMp) {
        this.maeTipoDocumentoAfiliadoCodigoMp = maeTipoDocumentoAfiliadoCodigoMp;
    }

    public String getTipoDocumentoAfiliadoMp() {
        return tipoDocumentoAfiliadoMp;
    }

    public void setTipoDocumentoAfiliadoMp(String tipoDocumentoAfiliadoMp) {
        this.tipoDocumentoAfiliadoMp = tipoDocumentoAfiliadoMp;
    }

    public String getNumeroDocumentoAfiliadoMp() {
        return numeroDocumentoAfiliadoMp;
    }

    public void setNumeroDocumentoAfiliadoMp(String numeroDocumentoAfiliadoMp) {
        this.numeroDocumentoAfiliadoMp = numeroDocumentoAfiliadoMp;
    }

    public void setRegimenAfiliado(String regimen) {
        this.regimenAfiliado = regimen;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getTipoTecnologiaItem() {
        return tipoTecnologiaItem;
    }

    public void setTipoTecnologiaItem(Integer tipoTecnologiaItem) {
        this.tipoTecnologiaItem = tipoTecnologiaItem;
    }

    public Integer getEstadoItem() {
        return estadoItem;
    }

    public void setEstadoItem(Integer estadoItem) {
        this.estadoItem = estadoItem;
    }

    public Integer getJuntaP() {
        return juntaP;
    }

    public void setJuntaP(Integer juntaP) {
        this.juntaP = juntaP;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public int getConsecutivoItem() {
        return consecutivoItem;
    }

    public void setConsecutivoItem(int consecutivoItem) {
        this.consecutivoItem = consecutivoItem;
    }

    public String getEstadoStr() {
        String valor = "";
        if (estadoItem != null) {
            switch (estadoItem) {
                case 1:
                    valor = "Direccionada";
                    break;
                case 2:
                    valor = "No Direccionado";
                    break;
                case 3:
                    valor = "Pendiente";
                    break;
                case 4:
                    valor = "Auditado - Direccionado";
                    break;
                case 5:
                    valor = "Auditado - No direccionamiento";
                    break;
                case 6:
                    valor = "Ampliación justificación no pbs insuficiente";
                    break;
                case 7:
                    valor = "Error en la prescripción";
                    break;
                case 8:
                    valor = "Avalado Hos_Urg";
                    break;
                case 9:
                    valor = "No Avalado Hos_Urg";
                    break;
                case 10:
                    valor = "No direccionamiento sin causa";
                    break;
                case 11:
                    valor = "Pendiente De Cotizaciòn";
                    break;
                case 12:
                    valor = "Con Cotización";
                    break;
                case 13:
                    valor = "Rechazo De Cotización";
                    break;
                case 14:
                    valor = "Anulado No Direccionamiento";
                default:
                    break;
            }

        }
        return valor;
    }

    public String getObtenerTipoTecnologia() {
        if (tipoTecnologiaItem == null || tipoTecnologiaItem < 1 || tipoTecnologiaItem > 5) {
            obtenerTipoTecnologia = "-- -- --";
        } else {
            switch (tipoTecnologiaItem) {
                case MpPrescripcionDetalle.ID_TIPO_TECNOLOGIA_MEDICAMENTO:
                    obtenerTipoTecnologia = "Medicamento";
                    break;
                case MpPrescripcionDetalle.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS:
                    obtenerTipoTecnologia = "Procedimientos";
                    break;
                case MpPrescripcionDetalle.ID_TIPO_TECNOLOGIA_DISPOSITIVO:
                    obtenerTipoTecnologia = "Dispositivos";
                    break;
                case MpPrescripcionDetalle.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES:
                    obtenerTipoTecnologia = "Productos Nutricionales";
                    break;
                case MpPrescripcionDetalle.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO:
                    obtenerTipoTecnologia = "Servicios Complementarios";
                    break;
                default:
                    obtenerTipoTecnologia = "-- -- --";
                    break;
            }
        }
        return obtenerTipoTecnologia;
    }

    public void setObtenerTipoTecnologia(String obtenerTipoTecnologia) {
        this.obtenerTipoTecnologia = obtenerTipoTecnologia;
    }

    public void setObtenerjunta(String obtenerjunta) {
        this.obtenerjunta = obtenerjunta;
    }

    public String getObtenerAmbito() {
        if (getCodigoAmbitoAtencion() != null) {
            switch (this.getCodigoAmbitoAtencion()) {
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                    obtenerAmbito = "Ambulatorio - Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                    obtenerAmbito = "Ambulatorio - No Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                    obtenerAmbito = "Hospitalario - Domiciliario";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                    obtenerAmbito = "Hospitalario - Internacion";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                    obtenerAmbito = "Urgencias";
                    break;
            }
        } else {
            obtenerAmbito = "Tutela";
        }
        if (getReferenciaContra() != null) {
            if (getReferenciaContra() == true) {
                obtenerAmbito = obtenerAmbito + " Referencia Contrarreferencia";
            }
        }
        return obtenerAmbito;
    }

    public void setObtenerAmbito(String obtenerAmbito) {
        this.obtenerAmbito = obtenerAmbito;
    }

    public Boolean getReferenciaContra() {
        return referenciaContra;
    }

    public void setReferenciaContra(Boolean referenciaContra) {
        this.referenciaContra = referenciaContra;
    }

    public String getRegimen() {
        if (getRegimenAfiliado() != null) {
            if ("1".equals(getRegimenAfiliado())) {
                regimen = "Subsidiado";
            } else {
                regimen = "Contributivo";
            }

        }
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getColorTecnologia() {
        return colorTecnologia;
    }

    public void setColorTecnologia(String colorTecnologia) {
        this.colorTecnologia = colorTecnologia;
    }

    public String getColorEstado() {
        return colorEstado;
    }

    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    public String getColorJunta() {
        return colorJunta;
    }

    public void setColorJunta(String colorJunta) {
        this.colorJunta = colorJunta;
    }

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public String getTerminalAtiende() {
        return terminalAtiende;
    }

    public void setTerminalAtiende(String terminalAtiende) {
        this.terminalAtiende = terminalAtiende;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    public Boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }

    public Boolean getBanderaAtencion() {
        return banderaAtencion;
    }

    public void setBanderaAtencion(Boolean banderaAtencion) {
        this.banderaAtencion = banderaAtencion;
    }

    public String getDocumentoPrestador() {
        return documentoPrestador;
    }

    public void setDocumentoPrestador(String documentoPrestador) {
        this.documentoPrestador = documentoPrestador;
    }

    public String establecerColorEstado() {

        try {
            Integer st = getEstadoItem();

            if (st != null) {

                switch (st) {
                    case 1:
                        colorEstado = "verde";
                        break;
                    case 2:
                        colorEstado = "rojo";
                        break;
                    case 3:
                        colorEstado = "naranja";
                        break;
                    case 4:
                        colorEstado = "verde";
                        break;
                    case 5:
                        colorEstado = "rojo";
                        break;
                    case 6:
                        colorEstado = "rojo";
                        break;
                    case 7:
                        colorEstado = "rojo";
                        break;
                    case 8:
                        colorEstado = "amarillo";
                        break;
                    case 9:
                        colorEstado = "naranja";
                        break;
                    case 10:
                        colorEstado = "rojo";
                        break;
                    case 11:
                        colorEstado = "naranja";
                        break;
                    case 12:
                        colorEstado = "celeste";
                        break;
                    case 13:
                        colorEstado = "morado";
                        break;
                    case 14:
                        colorEstado = "amarillo";
                        break;
                    default:
                        break;
                }

            } else {
                colorEstado = " ";
            }
        } catch (Exception e) {

        }

        return colorEstado;
    }

    public String establecerColorTecnologia() {

        try {
            Integer tT = getTipoTecnologiaItem();

            if (tT >= 1 && tT <= 5) {

                switch (tT) {
                    case 1:
                        colorTecnologia = "azul";
                        break;
                    case 2:
                        colorTecnologia = "verde";
                        break;
                    case 3:
                        colorTecnologia = "celeste";
                        break;
                    case 4:
                        colorTecnologia = "morado";
                        break;
                    case 5:
                        colorTecnologia = "naranja";
                        break;
                    default:
                        break;
                }

            } else {
                colorTecnologia = "negro";
            }
        } catch (Exception e) {

        }

        return colorTecnologia;
    }

    public String getObtenerJunta() {
        if (juntaP != null) {
            obtenerjunta = " ";
            switch (juntaP) {
                case 1:
                    obtenerjunta = "No Requiere";
                    break;
                case 2:
                    obtenerjunta = "Pendiente";
                    break;
                case 3:
                    obtenerjunta = "Aprobada";
                    break;
                case 4:
                    obtenerjunta = "No aprobada";
                    break;
            }
        }
        return obtenerjunta;
    }

    public String establecerColorJunta() {
        Integer tJ = getJuntaP();
        if (tJ != null) {
            switch (tJ) {
                case 1:
                    colorJunta = "azul";
                    break;
                case 2:
                    colorJunta = "naranja";
                    break;
                case 3:
                    colorJunta = "verde";
                    break;
                case 4:
                    colorJunta = "rojo";
                    break;
            }
        } else {
            colorJunta = "blanco";
        }
        return colorJunta;
    }

    public String getPrimerNombrePrestador() {
        return primerNombrePrestador;
    }

    public void setPrimerNombrePrestador(String primerNombrePrestador) {
        this.primerNombrePrestador = primerNombrePrestador;
    }

    public String getPrimerApellidoPrestador() {
        return primerApellidoPrestador;
    }

    public void setPrimerApellidoPrestador(String primerApellidoPrestador) {
        this.primerApellidoPrestador = primerApellidoPrestador;
    }

    public String getSegundoNombrePrestador() {
        return segundoNombrePrestador;
    }

    public void setSegundoNombrePrestador(String segundoNombrePrestador) {
        this.segundoNombrePrestador = segundoNombrePrestador;
    }

    public String getSegundoApellidoPrestador() {
        return segundoApellidoPrestador;
    }

    public void setSegundoApellidoPrestador(String segundoApellidoPrestador) {
        this.segundoApellidoPrestador = segundoApellidoPrestador;
    }

    public String getNumeroDocumentoPrestador() {
        return numeroDocumentoPrestador;
    }

    public void setNumeroDocumentoPrestador(String numeroDocumentoPrestador) {
        this.numeroDocumentoPrestador = numeroDocumentoPrestador;
    }

    @Override
    public String toString() {
        return "MpPrescripcionDetalle{" + "idPrescripcion=" + idPrescripcion + ", numeroPrescripcion=" + numeroPrescripcion + ", codigoAmbitoAtencion=" + codigoAmbitoAtencion + ", fechaPrescripcion=" + fechaPrescripcion + ", idAfiliado=" + idAfiliado + ", primerNombreAfiliado=" + primerNombreAfiliado + ", primerApellidoAfiliado=" + primerApellidoAfiliado + ", numeroDocumentoAfiliado=" + numeroDocumentoAfiliado + ", regimenAfiliado=" + regimenAfiliado + ", idItem=" + idItem + ", tipoTecnologiaItem=" + tipoTecnologiaItem + ", estadoItem=" + estadoItem + ", nombreItem=" + nombreItem + '}';
    }

}
