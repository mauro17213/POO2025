package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public class AuAnexo2Rescate extends Auditoria {

    public final static int ESTADO_NO_REQUIERE = 0;
    public final static int ESTADO_PENDIENTE = 1;
    public final static int ESTADO_RESCATADO = 2;
    public final static int ESTADO_RECHAZADO = 3;
    public final static int ESTADO_CANCELADO = 4;
    public final static int ESTADO_GESTION = 5;
    public final static short TAMANIO_OBSERVACION = 30;

    public final static short FUENTE_ORIGEN_HOSPITALIZACION = 1;
    public final static short FUENTE_ORIGEN_ANEXO2 = 2;
    public final static short FUENTE_ORIGEN_ANEXO3 = 3;

    public final static short TIPO_RESCATE_GESTION_RIESGO = 1;
    public final static short TIPO_RESCATE_GESTION_CAPITA = 2;

    private Integer id;
    private int maeAfiliadoTipoDocumentoId;
    private String maeAfiliadoTipoDocumentoCodigo;
    private String maeAfiliadoTipoDocumentoValor;
    private String afiliadoNumeroDocumento;
    private String afiliadoPrimerNombre;
    private String afiliadoSegundoNombre;
    private String afiliadoPrimerApellido;
    private String afiliadoSegundoApellido;
    private String motivoConsulta;
    private Integer estado;
    private String descripcion;
    private Date fechaHoraRescate;
    private short fuenteOrigen;
    private int tipoRescate;

    private AsegAfiliado asegAfiliado;
    private CntPrestadorSede cntPrestadorSedeOrigen;
    private CntPrestadorSede cntPrestadorSedeDestino;
    private CntPrestador cntPrestadorOrigen;
    private CntPrestador cntPrestadorDestino;
    private AuAnexo2 auAnexo2;
    private AuAnexo3 auAnexo3;
    private AucHospitalizacion aucHospitalizacion;
    private Empresa empresa;
    private PePrograma pePrograma;
    private List<AuAnexo2RescateGestion> auAnexo2RescateGestionList;
    private List<AuSolicitudAdjunto> auSolicitudAdjuntosList;
    //aux
    private Integer maeTipoArchivoId;
    private String observacionCorto;
    private String colorAnexo2Anexo3;
    //motivo y fecha direccionamieno en rescate gestion
    private Date fechaHoraDireccionamiento;
    private String maeMotivoRescateCodigo;
    private String maeMotivoRescateValor;

    public AuAnexo2Rescate() {
    }

    public AuAnexo2Rescate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHoraRescate() {
        return fechaHoraRescate;
    }

    public void setFechaHoraRescate(Date fechaHoraRescate) {
        this.fechaHoraRescate = fechaHoraRescate;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntPrestadorSede getCntPrestadorSedeOrigen() {
        return cntPrestadorSedeOrigen;
    }

    public void setCntPrestadorSedeOrigen(CntPrestadorSede cntPrestadorSedeOrigen) {
        this.cntPrestadorSedeOrigen = cntPrestadorSedeOrigen;
    }

    public CntPrestadorSede getCntPrestadorSedeDestino() {
        return cntPrestadorSedeDestino;
    }

    public void setCntPrestadorSedeDestino(CntPrestadorSede cntPrestadorSedeDestino) {
        this.cntPrestadorSedeDestino = cntPrestadorSedeDestino;
    }

    public CntPrestador getCntPrestadorOrigen() {
        return cntPrestadorOrigen;
    }

    public void setCntPrestadorOrigen(CntPrestador cntPrestadorOrigen) {
        this.cntPrestadorOrigen = cntPrestadorOrigen;
    }

    public CntPrestador getCntPrestadorDestino() {
        return cntPrestadorDestino;
    }

    public void setCntPrestadorDestino(CntPrestador cntPrestadorDestino) {
        this.cntPrestadorDestino = cntPrestadorDestino;
    }

    public AuAnexo2 getAuAnexo2() {
        return auAnexo2;
    }

    public void setAuAnexo2(AuAnexo2 auAnexo2) {
        this.auAnexo2 = auAnexo2;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public PePrograma getPePrograma() {
        return pePrograma;
    }

    public void setPePrograma(PePrograma pePrograma) {
        this.pePrograma = pePrograma;
    }

    public List<AuAnexo2RescateGestion> getAuAnexo2RescateGestionList() {
        return auAnexo2RescateGestionList;
    }

    public void setAuAnexo2RescateGestionList(List<AuAnexo2RescateGestion> auAnexo2RescateGestionList) {
        this.auAnexo2RescateGestionList = auAnexo2RescateGestionList;
    }

    public short getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(short fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public AuAnexo3 getAuAnexo3() {
        return auAnexo3;
    }

    public void setAuAnexo3(AuAnexo3 auAnexo3) {
        this.auAnexo3 = auAnexo3;
    }

    public AucHospitalizacion getAucHospitalizacion() {
        return aucHospitalizacion;
    }

    public void setAucHospitalizacion(AucHospitalizacion aucHospitalizacion) {
        this.aucHospitalizacion = aucHospitalizacion;
    }

    public String getColorAnexo2Anexo3() {
        return colorAnexo2Anexo3;
    }

    public void setColorAnexo2Anexo3(String colorAnexo2Anexo3) {
        this.colorAnexo2Anexo3 = colorAnexo2Anexo3;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjunto> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public Integer getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(Integer maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    public int getTipoRescate() {
        return tipoRescate;
    }

    public void setTipoRescate(int tipoRescate) {
        this.tipoRescate = tipoRescate;
    }

    public Date getFechaHoraDireccionamiento() {
        return fechaHoraDireccionamiento;
    }

    public void setFechaHoraDireccionamiento(Date fechaHoraDireccionamiento) {
        this.fechaHoraDireccionamiento = fechaHoraDireccionamiento;
    }

    public String getMaeMotivoRescateCodigo() {
        return maeMotivoRescateCodigo;
    }

    public void setMaeMotivoRescateCodigo(String maeMotivoRescateCodigo) {
        this.maeMotivoRescateCodigo = maeMotivoRescateCodigo;
    }

    public String getMaeMotivoRescateValor() {
        return maeMotivoRescateValor;
    }

    public void setMaeMotivoRescateValor(String maeMotivoRescateValor) {
        this.maeMotivoRescateValor = maeMotivoRescateValor;
    }
    
    public int codigoSolicitud() {
        if (this.aucHospitalizacion != null) {
            return this.aucHospitalizacion.getId();
        }
        if (this.auAnexo2 != null) {
            return this.auAnexo2.getId();
        }
        if (this.auAnexo3 != null) {
            return this.auAnexo3.getId();
        }
        return 0;
    }

    public String getFuenteOrigenStr() {
        switch (this.fuenteOrigen) {
            case FUENTE_ORIGEN_HOSPITALIZACION:
                return "Hospitalización";
            case FUENTE_ORIGEN_ANEXO2:
                return "Anexo2";
            case FUENTE_ORIGEN_ANEXO3:
                return "Anexo3";
            default:
                return "";
        }
    }

    public String getEstadoStr() {
        switch (this.estado) {
            case ESTADO_NO_REQUIERE:
                return "No Requiere";
            case ESTADO_PENDIENTE:
                return "Pendiente";
            case ESTADO_RESCATADO:
                return "Rescatado";
            case ESTADO_RECHAZADO:
                return "Rechazado";
            case ESTADO_CANCELADO:
                return "Cancelado";
            case ESTADO_GESTION:
                return "En Gestión";
            default:
                return "";
        }
    }

    public String getDescripcionCorto() {
        if (getDescripcion() != null) {
            observacionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    public String getTipoRescateStr() {
        return AuTipoRescate.getNombreById(this.tipoRescate);
    }

    @Override
    public String toString() {
        return "AuAnexo2Rescate{" + "id=" + id + ", maeAfiliadoTipoDocumentoId=" + maeAfiliadoTipoDocumentoId + ", maeAfiliadoTipoDocumentoCodigo="
                + maeAfiliadoTipoDocumentoCodigo + ", maeAfiliadoTipoDocumentoValor=" + maeAfiliadoTipoDocumentoValor + ", afiliadoNumeroDocumento="
                + afiliadoNumeroDocumento + ", afiliadoPrimerNombre=" + afiliadoPrimerNombre + ", afiliadoSegundoNombre=" + afiliadoSegundoNombre
                + ", afiliadoPrimerApellido=" + afiliadoPrimerApellido + ", afiliadoSegundoApellido=" + afiliadoSegundoApellido + ", motivoConsulta="
                + motivoConsulta + ", estado=" + estado + ", descripcion=" + descripcion + ", cntPrestadorSedeOrigen="
                + (cntPrestadorSedeOrigen != null ? cntPrestadorSedeOrigen.getId() : cntPrestadorSedeOrigen) + ", cntPrestadorSedeDestino=" 
                + (cntPrestadorSedeDestino != null ? cntPrestadorSedeDestino.getId() : cntPrestadorSedeDestino) 
                 + "fechaHoraDireccionamiento=" + fechaHoraDireccionamiento + ", maeMotivoRescateCodigo=" + maeMotivoRescateCodigo + ", maeMotivoRescateValor=" + maeMotivoRescateValor 
                + ", tipoRescate="+ tipoRescate + '}';
    }

}
