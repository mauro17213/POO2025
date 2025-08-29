/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.CsContribucionSolidaria;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazoItem;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo3;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoCertificadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.NovedadAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.ConsultarAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.CsContribucionSolidariaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaTelefonoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAuditorRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucEgresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionAdversoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstanciaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionInoportunidadRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionPatologiaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionServicioRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucIngresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucJustificacionEstanciasProlongadaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ImpresionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuItemBitacoraRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuRechazoItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuRechazoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9Remoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaDiagnosticosRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTecnologiasRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroContactoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuPersonaContactoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.web.aseguramiento.bean.ConsultarAfiliadoBean;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
/**
 *
 * @author jperez
 */
public class ConsultarAfiliadoServicioImpl extends AccionesBO implements ConsultarAfiliadoServicioIface {
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private PortabilidadRemoto getPortabilidadRemoto() throws Exception {
        return (PortabilidadRemoto) RemotoEJB.getEJBRemoto("PortabilidadServicio", PortabilidadRemoto.class.getName());
    }
    
    private ConsultarAfiliadoRemoto getConsultarAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("ConsultarAfiliadoServicio", ConsultarAfiliadoRemoto.class.getName());
        return (ConsultarAfiliadoRemoto) object;
    }
    
    private NovedadAfiliadoRemoto getNovedadAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("NovedadAfiliadoServicio", NovedadAfiliadoRemoto.class.getName());
        return (NovedadAfiliadoRemoto) object;
    }
    
    private PeAfiliadosProgramaRemoto getAfiliadoProgramaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
        return (PeAfiliadosProgramaRemoto) object;
    }
    
    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }
    
    private PeProgramaDiagnosticosRemoto getPeProgramaDiagnosticosRemoto() throws Exception {
        return (PeProgramaDiagnosticosRemoto) RemotoEJB.getEJBRemoto("PeProgramaDiagnosticosServicio", PeProgramaDiagnosticosRemoto.class.getName());
    }

    private PeProgramaTecnologiasRemoto getPeProgramaTecnologiasRemoto() throws Exception {
        return (PeProgramaTecnologiasRemoto) RemotoEJB.getEJBRemoto("PeProgramaTecnologiasServicio", PeProgramaTecnologiasRemoto.class.getName());
    }
    
    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }
    
    private RefAnexo9Remoto getRefAnexo9Remoto() throws Exception {
        return (RefAnexo9Remoto) RemotoEJB.getEJBRemoto("RefAnexo9Servicio", RefAnexo9Remoto.class.getName());
    }
    
    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }
    
    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }
    
    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }
    
    private AfiliadoCertificadoRemoto getAfiliadoCertificadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoCertificadoServicio", AfiliadoCertificadoRemoto.class.getName());
        return (AfiliadoCertificadoRemoto) object;
    }
    
    private MpPrescripcionRemoto getMpPrescripcionRemoto() throws Exception {
        return (MpPrescripcionRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionServicio", MpPrescripcionRemoto.class.getName());
    }
    
    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private MpTecnologiaRemoto getMpTecnologiaRemoto() throws Exception {
        return (MpTecnologiaRemoto) RemotoEJB.getEJBRemoto("MpTecnologiaServicio", MpTecnologiaRemoto.class.getName());
    }

    private MpMedicamentoRemoto getMpMedicamentoRemoto() throws Exception {
        return (MpMedicamentoRemoto) RemotoEJB.getEJBRemoto("MpMedicamentoServicio", MpMedicamentoRemoto.class.getName());
    }

    private MpInsumoRemoto getMpInsumoRemoto() throws Exception {
        return (MpInsumoRemoto) RemotoEJB.getEJBRemoto("MpInsumoServicio", MpInsumoRemoto.class.getName());
    }
    
    private CsContribucionSolidariaRemoto getContribucionSolidariaRemoto() throws Exception {
        return (CsContribucionSolidariaRemoto) RemotoEJB.getEJBRemoto("CsContribucionSolidariaServicio", CsContribucionSolidariaRemoto.class.getName());
    }
    
    private TuTutelaRemoto getTutelaRemoto() throws Exception {
        return (TuTutelaRemoto) RemotoEJB.getEJBRemoto("TuTutelaServicio", TuTutelaRemoto.class.getName());
    }
    
    private TuAdjuntoRemoto getAdjuntoRemoto() throws Exception {
        return (TuAdjuntoRemoto) RemotoEJB.getEJBRemoto("TuAdjuntoServicio", TuAdjuntoRemoto.class.getName());
    }
    
    private TuSeguimientoRemoto getSeguimientoRemoto() throws Exception {
        return (TuSeguimientoRemoto) RemotoEJB.getEJBRemoto("TuSeguimientoServicio", TuSeguimientoRemoto.class.getName());
    }
    
    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }
    
    private AuAnexo4ImpresionRemoto getAuAnexo4ImpresionRemoto() throws Exception {
        return (AuAnexo4ImpresionRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ImpresionServicio", AuAnexo4ImpresionRemoto.class.getName());
    }
    
    private AucHospitalizacionRemoto getAucHospitalizacionRemoto() throws Exception {
        return (AucHospitalizacionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicio", AucHospitalizacionRemoto.class.getName());
    }
    
    private AucAfiliadoRemoto getAucAfiliadoRemoto() throws Exception {
        return (AucAfiliadoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoServicio", AucAfiliadoRemoto.class.getName());
    }
    
    private AucIngresoRemoto getAucIngresoRemoto() throws Exception {
        return (AucIngresoRemoto) RemotoEJB.getEJBRemoto("AucIngresoServicio", AucIngresoRemoto.class.getName());
    }
    
    private AucAuditorRemoto getAucAuditorRemoto() throws Exception {
        return (AucAuditorRemoto) RemotoEJB.getEJBRemoto("AucAuditorServicio", AucAuditorRemoto.class.getName());
    }
    
    private AucEgresoRemoto getAucEgresoRemoto() throws Exception {
        return (AucEgresoRemoto) RemotoEJB.getEJBRemoto("AucEgresoServicio", AucEgresoRemoto.class.getName());
    }
    
    private AucHospitalizacionAdversoRemoto getAucHospitalizacionAdversoRemoto() throws Exception {
        return (AucHospitalizacionAdversoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionAdversoServicio", AucHospitalizacionAdversoRemoto.class.getName());
    }
    
    private AucHospitalizacionSeguimientoRemoto getAucHospitalizacionSeguimientoRemoto() throws Exception {
        return (AucHospitalizacionSeguimientoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionSeguimientoServicio", AucHospitalizacionSeguimientoRemoto.class.getName());
    }
    
    private AucHospitalizacionInoportunidadRemoto getAucHospitalizacionInoportunidadRemoto() throws Exception {
        return (AucHospitalizacionInoportunidadRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionInoportunidadServicio", AucHospitalizacionInoportunidadRemoto.class.getName());
    }
    
    private AucHospitalizacionObjecionRemoto getAucHospitalizacionObjecionRemoto() throws Exception {
        return (AucHospitalizacionObjecionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionObjecionServicio", AucHospitalizacionObjecionRemoto.class.getName());
    }
    
    private AucHospitalizacionPatologiaRemoto getAucHospitalizacionPatologiaRemoto() throws Exception {
        return (AucHospitalizacionPatologiaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionPatologiaServicio", AucHospitalizacionPatologiaRemoto.class.getName());
    }
    
    private AucHospitalizacionServicioRemoto getAucHospitalizacionServicioRemoto() throws Exception {
        return (AucHospitalizacionServicioRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicioServicio", AucHospitalizacionServicioRemoto.class.getName());
    }
    
    private AucHospitalizacionEstanciaRemoto getAucHospitalizacionEstanciaRemoto() throws Exception {
        return (AucHospitalizacionEstanciaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionEstanciaServicio", AucHospitalizacionEstanciaRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    private AucJustificacionEstanciasProlongadaRemoto getAucJustificacionEstanciasProlongadaRemoto() throws Exception {
        return (AucJustificacionEstanciasProlongadaRemoto) RemotoEJB.getEJBRemoto("AucJustificacionEstanciasProlongadaServicio", AucJustificacionEstanciasProlongadaRemoto.class.getName());
    }
    
    private PeDireccionadoRemoto getPeDireccionadoRemoto() throws Exception {
        return (PeDireccionadoRemoto) RemotoEJB.getEJBRemoto("PeDireccionadoServicio", PeDireccionadoRemoto.class.getName());
    }
    
    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }
    
    private CntPrestadorContactoRemoto getCntPrestadorContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }
    
    private AuItemBitacoraRemoto getAuItemBitacoraRemoto() throws Exception {
        return (AuItemBitacoraRemoto) RemotoEJB.getEJBRemoto("AuItemBitacoraServicio", AuItemBitacoraRemoto.class.getName());
    }
    
    private AuCotizacionItemRemoto getAuCotizacionItemRemoto() throws Exception {
        return (AuCotizacionItemRemoto) RemotoEJB.getEJBRemoto("AuCotizacionItemServicio", AuCotizacionItemRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }    

    private TuPersonaContactoRemoto getTuPersonaContactoRemoto() throws Exception {
        return (TuPersonaContactoRemoto) RemotoEJB.getEJBRemoto("TuPersonaContactoServicio", TuPersonaContactoRemoto.class.getName());
    }

    private AusPersonaTelefonoRemoto getAusPersonaTelefonoRemoto() throws Exception {
        return (AusPersonaTelefonoRemoto) RemotoEJB.getEJBRemoto("AusPersonaTelefonoServicio", AusPersonaTelefonoRemoto.class.getName());
    }

    private AucAfiliadoContactoRemoto getAucAfiliadoContactoRemoto() throws Exception {
        return (AucAfiliadoContactoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoContactoServicio", AucAfiliadoContactoRemoto.class.getName());
    }

    private GjTerceroContactoRemoto getGjTerceroContactoRemoto() throws Exception {
        return (GjTerceroContactoRemoto) RemotoEJB.getEJBRemoto("GjTerceroContactoServicio", GjTerceroContactoRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }
    
    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    
    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }
    
    private AuRechazoRemoto getAuRechazoRemoto() throws Exception {
        return (AuRechazoRemoto) RemotoEJB.getEJBRemoto("AuRechazoServicio", AuRechazoRemoto.class.getName());
    }
    
    private AuRechazoItemRemoto getAuRechazoItemRemoto() throws Exception {
        return (AuRechazoItemRemoto) RemotoEJB.getEJBRemoto("AuRechazoItemServicio", AuRechazoItemRemoto.class.getName());
    }
    
    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }
    
    private CntProfesionalRemoto getProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }
    
    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }
    
    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    @Override
    public void Accion(ConsultarAfiliadoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                     switch (bean.getDoAccion()) {
                        case ConsultarAfiliadoBean.ACCION_BUSCAR_AFILIADOS:
                            buscarAfiliados(bean);
                            break;
                        case ConsultarAfiliadoBean.ACCION_VER_AFILIADO:
                            buscarAfiliado(bean);
                            break;
                        /*case ConsultarAfiliadoBean.ACCION_VER_HISTORICO_NOVEDADES:
                            buscarHistorialNovedades(bean);
                            break;*/
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    buscarHistorialNovedades(bean);
                break;
                case Url.ACCION_ADICIONAL_2:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarProgramasAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verPrograma(bean);
                            break;
                    
                    }
                break;
                case Url.ACCION_ADICIONAL_3:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarAutorizacionesAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            consultarAutorizacionAfiliado(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            generarReporte(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_4:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            generarCertificadoAfiliacion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            generarCertificadoPortabilidad(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_5:
                    consultarReferenciasAfiliado(bean);
                break;
                case Url.ACCION_ADICIONAL_6:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarPQRSFAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verPQRSF(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_7:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarMiPresAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verMiPres(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_8:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarTutelasAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verTutela(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_9:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarHospitalizacionAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verHospitalizacion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verJustificacionEstanciaProlongada(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verPatologia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verInoportunidad(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            verAdverso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            verObjecion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            verServicio(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_10:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarDireccionadoAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verDireccionado(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_11:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarSolicitudesAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verSolicitud(bean);
                            break;
                    }
                break;
                case Url.ACCION_ADICIONAL_12:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarPrestacionesAfiliado(bean);
                            break;
                    }
                break;
            }
            //cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(ConsultarAfiliadoBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void buscarAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.setAfiliadoCompleto(getAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaGrupoFamiliarAfiliado(getAfiliadoRemoto().consultarGrupoFamiliar(bean.getAfiliadoCompleto().getMaeTipoDocumento(), bean.getAfiliadoCompleto().getNumeroDocumento()));
            bean.setListaPortabilidadAfiliado(getPortabilidadRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            //2022-07-21 jyperez cargamos el valor de la contribución solidaria
            consultarValorContribucionSolidaria(bean);
                  
            //Contactos programas especiales
            String listacontactoExistente = bean.getAfiliadoCompleto().getListaContactoAfiliado().stream().map(ct -> String.valueOf("'" + ct.getNumeroContacto()) + "'").collect(Collectors.joining(","));
            bean.getAfiliadoCompleto().getListaContactoAfiliado().addAll(getPeTelefonosRemoto().getListaContactoAfiliado(bean.getObjeto().getId(), listacontactoExistente));
            //contactos en tutelas
            listacontactoExistente = bean.getAfiliadoCompleto().getListaContactoAfiliado().stream().map(ct -> String.valueOf("'" + ct.getNumeroContacto()) + "'").collect(Collectors.joining(","));
            bean.getAfiliadoCompleto().getListaContactoAfiliado().addAll(getTuPersonaContactoRemoto().consultarListaContactosPorAsegAfiliado(bean.getObjeto().getId(), listacontactoExistente));
            //Contacto atencion usuario
            listacontactoExistente = bean.getAfiliadoCompleto().getListaContactoAfiliado().stream().map(ct -> String.valueOf("'" + ct.getNumeroContacto()) + "'").collect(Collectors.joining(","));
            bean.getAfiliadoCompleto().getListaContactoAfiliado().addAll(getAusPersonaTelefonoRemoto().consultarListaContactosPorAsegAfiliado(bean.getObjeto().getId(), listacontactoExistente));
            //Contacto auditoria concurrente
            listacontactoExistente = bean.getAfiliadoCompleto().getListaContactoAfiliado().stream().map(ct -> String.valueOf("'" + ct.getNumeroContacto()) + "'").collect(Collectors.joining(","));
            bean.getAfiliadoCompleto().getListaContactoAfiliado().addAll(getAucAfiliadoContactoRemoto().consultarListaContactosPorAsegAfiliado(bean.getObjeto().getId(), listacontactoExistente));
            //Contactos gestion judicial
            listacontactoExistente = bean.getAfiliadoCompleto().getListaContactoAfiliado().stream().map(ct -> String.valueOf("'" + ct.getNumeroContacto()) + "'").collect(Collectors.joining(","));
            bean.getAfiliadoCompleto().getListaContactoAfiliado().addAll(getGjTerceroContactoRemoto().consultarListaContactosAfiliado(bean.getObjeto().getId(), listacontactoExistente));
            //Consultar tutelas con integralidad
            bean.setListaTutelaAfiliado(getTutelaRemoto().consultarListaPorAfiliadoIntegralidad(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void buscarAfiliados(ConsultarAfiliadoBean bean) {
        try {
              //bean.getParamConsulta().setCantidadRegistros(getConsultarAfiliadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
              bean.setRegistros(getConsultarAfiliadoRemoto().consultarLista(bean.getParamConsulta()));
              if (bean.getRegistros() != null) {
                bean.getParamConsulta().setCantidadRegistros(bean.getRegistros().size());
              } else {
                  bean.getParamConsulta().setCantidadRegistros(0);
              }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    
    private void cargas(ConsultarAfiliadoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {

        }
    }
    
    private void buscarHistorialNovedades(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaHistorial().setCantidadRegistros(getNovedadAfiliadoRemoto().consultarCantidadLista(bean.getParamConsultaHistorial()));
            bean.setListaHistorialNovedad(getNovedadAfiliadoRemoto().consultarLista(bean.getParamConsultaHistorial()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInial(ConsultarAfiliadoBean bean) {
        try {
            bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));    
            bean.setHashTiposGenero(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashOrigenAfiliado(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_ORIGEN_AFILIADO));
            bean.setHashEstadosAfiliacion(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashGrupoPoblacional(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_GRUPO_POBLACIONAL));
            bean.setHashSubGrupoSisben(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA));
            //2021-09-06 jyperez adicionamos maestro a utilizar en Referencias
            bean.setListaEstadosReferencia(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CR_A9_ESTADO));
            bean.setHashEstadosReferencia(Util.convertToHash(bean.getListaEstadosReferencia()));
            //2022-03-15 jyperez adicionamos maestro para PQRSF
            bean.setListaEstadosPQRSF(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setHashEstadosPQRSF(Util.convertToHash(bean.getListaEstadosPQRSF()));
            //bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            //2022-05-16 INC 0001235 jyperez adicion lista tipo programa
            bean.setListaTipoPrograma(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.PE_PROGRAMA_TIPO));
            //2022-08-12 jyperez maestros Tutelas
            bean.setListaEstadoProceso(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.TU_TUTELA_PROCESO));
            bean.setHashEstadoProceso(Util.convertToHash(bean.getListaEstadoProceso()));
            //2022-09-13 jyperez carga de las rutas para impresión en autorizaciones
            bean.setRutaAnexo2(PropApl.getInstance().get(PropApl.REF_RUTA_ANEXOS));
            bean.setRutaAnexo3(PropApl.getInstance().get(PropApl.AU_A4_ANEXOS));
            //2022-10-04 jyperez carga de ubicaciones
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            //2022-12-21 jyperez carga de constantes de estado para direccionados
            if (bean.getListaEstadosDireccionado() == null) {
                bean.setListaEstadosDireccionado(PeConstantes.listaEstadoDireccionado());
            }
            //2023-02-07 jyperez nuevo maestro Programas Especiales
            bean.setListaFuenteOrigen(PeConstantes.obtenerTiposOrigen());
            //2023-08-24 jyperez adicionales solicitudes
            bean.setListaTipoCargue(AuConstantes.obtenerTiposCargue());
            bean.setHashTipoCargue(AuConstantes.obtenerHashMaestro(bean.getListaTipoCargue()));
            //2023-09-04 jyperez nueva lista
            bean.setListaEstadoSolicitud(AuConstantes.obtenerEstadosSolicitud());
            //2025-03-13 jyperez cargamos la ruta del google maps con key
            bean.setRutaGoogleMapsEmpresarial(AfiliadoParametro.RUTA_GOOGLE_MAPS_KEY);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void consultarProgramasAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaProgramasEspeciales().setCantidadRegistros(getAfiliadoProgramaRemoto().consultarCantidadListaPorAfiliado(bean.getParamConsultaProgramasEspeciales()));
            bean.setListaProgramasEspeciales(getAfiliadoProgramaRemoto().consultarListaPorAfiliado(bean.getParamConsultaProgramasEspeciales()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verPrograma(ConsultarAfiliadoBean bean) {
        try {
            //falta información de tecnologia RIAS
            bean.getObjetoPeAfiliadoPrograma().getPePrograma().setPeProgramaDiagnosticosList(getPeProgramaDiagnosticosRemoto().consultarDiagnosticosPrograma(bean.getObjetoPeAfiliadoPrograma().getPePrograma().getId()));
            bean.getObjetoPeAfiliadoPrograma().getPePrograma().setPeProgramaTecnologiasList(getPeProgramaTecnologiasRemoto().consultarTecnologiasPrograma(bean.getObjetoPeAfiliadoPrograma().getPePrograma().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarAutorizacionesAfiliado(ConsultarAfiliadoBean bean) {
        try {
            if (bean.getParamConsultaAutorizaciones().getFiltros() != null) {
                //SI LLEVA TIPO ANEXO2 O TIPO ANEXO3
                for (Map.Entry e : bean.getParamConsultaAutorizaciones().getFiltros().entrySet()) {
                    if (e.getKey().equals(AuAnexo4.TIPO_ANEXO)) {
                        if (e.getValue().equals(AuAnexo4.ANEXO_2)) {
                            bean.getParamConsultaAutorizaciones().setParametroConsulta3(null);
                            bean.getParamConsultaAutorizaciones().setParametroConsulta2(AuAnexo4.ANEXO_2);
                            e.setValue(null);
                        } else if (e.getValue().equals(AuAnexo4.ANEXO_3)) {
                            bean.getParamConsultaAutorizaciones().setParametroConsulta2(null);
                            bean.getParamConsultaAutorizaciones().setParametroConsulta3(AuAnexo4.ANEXO_3);
                            e.setValue(null);
                        }
                    }
                }
                //SI LLEVA NUMERO SOLICITUD
                for (Map.Entry e : bean.getParamConsultaAutorizaciones().getFiltros().entrySet()) {
                    if (e.getKey().equals(AuAnexo4.TIPO_SOLICITUD)) {
                        bean.getParamConsultaAutorizaciones().setParametroConsulta4(e.getValue());
                        e.setValue(null);
                    }
                }
            }
            bean.getParamConsultaAutorizaciones().setCantidadRegistros(getAuAnexo4Remoto().consultarCantidadListaPorAfiliado(bean.getParamConsultaAutorizaciones()));
            bean.setListaAutorizaciones(getAuAnexo4Remoto().consultarListaPorAfiliado(bean.getParamConsultaAutorizaciones()));
            bean.getParamConsultaAutorizaciones().setParametroConsulta2(null);
            bean.getParamConsultaAutorizaciones().setParametroConsulta3(null);
            bean.getParamConsultaAutorizaciones().setParametroConsulta4(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarAutorizacionAfiliado(ConsultarAfiliadoBean bean){
        try{
            bean.setObjetoAutorizacion(getAuAnexo4Remoto().consultar(bean.getObjeto().getId()));
            bean.setListaAuAnexo4Items(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(bean.getObjeto().getId()));
            consultarContactosPrestador(bean);
            if (bean.validarEsPreautorizacion(bean.getObjetoAutorizacion().getEstado())) {
                bean.setHeaderDialogVer("Ver Anexo 4 Preautorización de Servicios");
            } else {
                bean.setHeaderDialogVer("Ver Anexo 4 Autorización de Servicios");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarContactosPrestador(ConsultarAfiliadoBean bean) throws Exception {
        List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto().consultarPorCntContratoSedeYArea(bean.getObjetoAutorizacion().getCntPrestadorSedeId().getCntPrestador().getId(), bean.getObjetoAutorizacion().getCntPrestadorSedeId().getId(), AuConstantes.AREA_CONTACTO_AUTORIZACION);
        CntPrestadorContacto correo = contactos.stream()
                .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_CORREO))
                .findFirst().orElse(null);
        if (correo != null) {
            bean.getObjetoAutorizacion().setCorreoElectronicoPrestador(correo.getContacto());
        }
        contactos = contactos.stream()
                .filter(c -> c.isActivo() && (c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_FIJO)
                || c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_MOVIL)
                || c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_PBX)))
                .collect(Collectors.toList());
        if (!contactos.isEmpty()) {
            bean.getObjetoAutorizacion().setTelefono1Prestador(contactos.get(0).getContacto());
            if (contactos.size() > 1) {
                bean.getObjetoAutorizacion().setTelefono2Prestador(contactos.get(1).getContacto());
            }
        }
    }
    
    @Override
    public void verBitacoras(ConsultarAfiliadoBean bean) {
        try {
            if (bean.getObjetoItem().getAuAnexo3ItemId() != null) {
                bean.setListaBitacoras(getAuItemBitacoraRemoto().listarPorIdItem(bean.getObjetoItem().getAuAnexo3ItemId().getId()));
            } else {
                bean.setListaBitacoras(new ArrayList());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las bitacoras");
        }
    }
    
    @Override
    public void consultarAdjuntosCotizacion(ConsultarAfiliadoBean bean) {
        try {
            AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(bean.getObjetoAutorizacion().getAuAnexo3Id().getObjetoTecnologia().getId());
            if (itemCotizacion != null) {
                bean.setListaAdjuntosCotizacion(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdCotizacion(itemCotizacion.getAuCotizacionId().getId()));
            }
        } catch (Exception e) {
            bean.addError("No se encontro una cotizacion, favor comunicarse con el administrador");
        }
    }
    
    private void generarReporte(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoAutorizacion(getAuAnexo4Remoto().consultar(bean.getObjetoAutorizacion().getId()));
            bean.getObjetoAutorizacion().setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(bean.getObjetoAutorizacion().getId()));
        } catch (Exception e) {
            bean.addError("No se encontro la autorización");
        }
    }
    
    @Override
    public void contarImpresion(ConsultarAfiliadoBean bean) {
        try {
            AuAnexo4Impresion historico = new AuAnexo4Impresion();
            int cantidadActual = 1;
            if (bean.getObjetoAutorizacion().getImpresionesRealizadas() != null) {
                cantidadActual = bean.getObjetoAutorizacion().getImpresionesRealizadas();
                if (cantidadActual == 0) {
                    historico.setTipoImpresion(0);
                } else {
                    historico.setTipoImpresion(1);
                }
                cantidadActual++;
            } else {
                historico.setTipoImpresion(0);
            }
            historico.setOrigenImpresion(0);
            historico.setAuAnexo4Id(bean.getObjetoAutorizacion());
            bean.auditoriaGuardar(historico);
            getAuAnexo4ImpresionRemoto().insertar(historico);
            bean.getObjetoAutorizacion().setImpresionesRealizadas(cantidadActual);
            getAuAnexo4Remoto().actualizarImpresion(bean.getObjetoAutorizacion());
        } catch (Exception e) {
            bean.addError("No se pudo actualizar la cantidad de impresiones");
        }
    }
    
    private void consultarReferenciasAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaReferencias().setCantidadRegistros(getRefAnexo9Remoto().consultarCantidadListaPorAfiliado(bean.getParamConsultaReferencias()));
            bean.setListaReferencias(getRefAnexo9Remoto().consultarListaPorAfiliado(bean.getParamConsultaReferencias()));
            bean.getParamConsultaReferencias().setParametroConsulta2(null);
            bean.getParamConsultaReferencias().setParametroConsulta3(null);
            bean.getParamConsultaReferencias().setParametroConsulta4(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarPQRSFAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaPQRSF().setCantidadRegistros(getCasoRemoto().consultarCantidadListaPorAfiliado(bean.getParamConsultaPQRSF()));
            bean.setListaPQRSF(getCasoRemoto().consultarListaPorAfiliado(bean.getParamConsultaPQRSF()));
            /*bean.getParamConsultaPQRSF().setParametroConsulta2(null);
            bean.getParamConsultaPQRSF().setParametroConsulta3(null);
            bean.getParamConsultaPQRSF().setParametroConsulta4(null);*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verPQRSF(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoCaso(getCasoRemoto().consultar(bean.getObjetoCaso().getId()));
            if(bean.getObjetoCaso().getAsuPersonasId() != null){
                List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjetoCaso().getAsuPersonasId().getDocumento());
                bean.getObjetoCaso().getAsuPersonasId().setListaTelefonos(listaTelefonosPersona);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void generarCertificadoAfiliacion(ConsultarAfiliadoBean bean) {
        // obtenemos los datos del afiliado
        AsegAfiliadoCertificado obj = new AsegAfiliadoCertificado();
        Maestro maestro;
        CertificadoAfiliacion certificado = new CertificadoAfiliacion();
        List<CertificadoAfiliacion> listaCertificado = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try{
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getCertificadoAfiliacion().getIdAfiliado());
            if (afiliado != null) {
                //con los datos del afiliado llenamos el objeto del afiliado certificado
                obj.setIdAfiliado(afiliado.getIdAfiliado());
                obj.setMaeTipoDocumentoId(afiliado.getMaeTipoDocumento());
                //traer del maestro
                maestro = bean.getHashTiposDocumento().get(afiliado.getMaeTipoDocumento());
                if (maestro != null) {
                    obj.setMaeTipoDocumentoCodigo(maestro.getValor());
                    obj.setMaeTipoDocumentoValor(maestro.getNombre());
                }
                obj.setNumeroDocumento(afiliado.getNumeroDocumento());
                obj.setPrimerNombre(afiliado.getPrimerNombre());
                obj.setSegundoNombre(afiliado.getSegundoNombre());
                obj.setPrimerApellido(afiliado.getPrimerApellido());
                obj.setSegundoApellido(afiliado.getSegundoApellido());
                obj.setFechaNacimiento(afiliado.getFechaNacimiento());
                obj.setMaeSubgrupoSisbenId(afiliado.getMaeGrupoSisbenId());
                //maestro
                maestro = bean.getHashSubGrupoSisben().get(afiliado.getMaeGrupoSisbenId());
                if (maestro != null) {
                    obj.setMaeSubgrupoSisbenCodigo(maestro.getValor());
                    obj.setMaeSubgrupoSisbenValor(maestro.getNombre());
                }
                if(afiliado.getMaeNivelSisbenId() != null) {
                    obj.setMaeNivelId(afiliado.getMaeNivelSisbenId());
                    obj.setMaeNivelCodigo(afiliado.getMaeNivelSisbenCodigo());
                obj.setMaeNivelValor(afiliado.getMaeNivelSisbenValor());
                }else {
                    obj.setMaeNivelId(0);
                    obj.setMaeNivelCodigo(afiliado.getNivelSisben());
                    obj.setMaeNivelValor(bean.getNivelSisben(afiliado.getNivelSisben()));
                }
                obj.setMaeGrupoPoblacionalId(afiliado.getMaeGrupoPoblacional());
                //maestro
                maestro = bean.getHashGrupoPoblacional().get(afiliado.getMaeGrupoPoblacional());
                if (maestro != null) {
                    obj.setMaeGrupoPoblacionalCodigo(maestro.getValor());
                    obj.setMaeGrupoPoblacionalValor(maestro.getNombre());
                }
                obj.setFechaAfiliacion(afiliado.getFechaAfiliacionEps());
                if (afiliado.getMaeRegimenId() != null) {
                    obj.setMaeRegimenId(afiliado.getMaeRegimenId());
                    obj.setMaeRegimenDescripcion(afiliado.getMaeRegimenValor());
                    obj.setMaeRegimenValor(afiliado.getMaeRegimenCodigo());
                } else {
                    obj.setMaeRegimenId(0);
                    obj.setMaeRegimenDescripcion(bean.getRegimen(afiliado.getRegimen()));
                    obj.setMaeRegimenValor(afiliado.getRegimen());
                }
                obj.setMaeEstadoAfiliacionId(afiliado.getMaeEstadoAfiliacion());
                //maestro
                maestro = bean.getHashEstadosAfiliacion().get(afiliado.getMaeEstadoAfiliacion());
                if (maestro != null) {
                    obj.setMaeEstadoAfiliacionCodigo(maestro.getValor());
                    obj.setMaeEstadoAfiliacionValor(maestro.getNombre());
                }
                try{
                    obj.setModeloLiquidacion(Integer.parseInt(afiliado.getModeloLiquidacion()));
                }catch (Exception e) {
                    obj.setModeloLiquidacion(0);
                }
                obj.setFechaRetiro(afiliado.getFechaEgresoEps());
                obj.setSemanaAfiliacion(calcularDiferenciaSemanas(afiliado.getFechaAfiliacionEps(),fechaActual));
                obj.setDireccionAfiliado(afiliado.getDireccionResidencia());
                obj.setTelefonoAfiliado(afiliado.getTelefonoFijo());
                obj.setCelularAfiliado(afiliado.getTelefonoMovil());
                obj.setAfiliacionUbicacionId(afiliado.getAfiliacionUbicacion().getId());
                obj.setAfiliacionUbicacionValor(afiliado.getAfiliacionUbicacion().getNombre());
                obj.setResidenciaUbicacionId(afiliado.getResidenciaUbicacion().getId());
                obj.setResidenciaUbicacionValor(afiliado.getResidenciaUbicacion().getNombre());
                obj.setCntPrestadorSedesId(afiliado.getPrimariaPrestadorSede().getId());
                obj.setCntPrestadorSedesValor(afiliado.getPrimariaPrestadorSede().getNombreSede());
                obj.setCorreoElectronico(afiliado.getEmail());
                obj.setTipo(AfiliadoParametro.TIPO_CERTIFICADO_AFILIACION);
                obj.setFechaInicioVigencia(fechaActual);
                obj.setFechaFinVigencia(sumarDiasFecha(fechaActual,AfiliadoParametro.CERTIFICADO_DIAS_VIGENCIA));
                obj.setDiasVigencia(AfiliadoParametro.CERTIFICADO_DIAS_VIGENCIA);
                //PropApl propiedades = new PropApl();
                obj.setNombreArchivo("CA"+"_"+obj.getMaeTipoDocumentoValor()+obj.getNumeroDocumento()+sdfHoraCert.format(fechaActual)+".pdf");
                obj.setRuta(PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_CERTIFICADOS));
                obj.setAsegAfiliado(afiliado);
                // auditoria
                bean.auditoriaGuardar(obj);
                // guardamos el registro del certificado
                obj.setId(getAfiliadoCertificadoRemoto().insertar(obj));
                //y procedemos a generar el certificado para impresión
                if (obj.getId() != null) {
                    certificado.setId(obj.getId());
                }else {
                    // lanzar excepción con la no generación del registro
                    throw new Exception ("No se almacenó el Certicado del Afiliado en la Base de Datos.");
                }
                certificado.setNombreArchivo(obj.getNombreArchivo());
                certificado.setRuta(obj.getRuta());
                certificado.setStrCiudad(obj.getResidenciaUbicacionValor());
                certificado.setStrDocumentoCompleto(obj.getMaeTipoDocumentoValor()+ " "+ obj.getNumeroDocumento());
                certificado.setStrEstado(obj.getMaeEstadoAfiliacionValor());
                certificado.setStrFechaAfiliacion(sdf.format(obj.getFechaAfiliacion()));
                certificado.setStrFechaGeneracion(sdfHora.format(fechaActual));
                if (obj.getFechaRetiro() != null) {
                    certificado.setStrFechaRetiro(sdf.format(obj.getFechaRetiro()));
                }else {
                    certificado.setStrFechaRetiro("");
                }
                certificado.setStrModelo(bean.getModeloLiquidacion(""+obj.getModeloLiquidacion()));
                certificado.setStrNivelSisben(obj.getMaeNivelValor());
                String nombreCompleto = "";
                nombreCompleto = obj.getPrimerNombre();
                if (obj.getSegundoNombre() != null && !obj.getSegundoNombre().trim().equals("")) {
                    nombreCompleto = nombreCompleto + " " + obj.getSegundoNombre();
                }
                nombreCompleto = nombreCompleto + " " + obj.getPrimerApellido();
                if (obj.getSegundoApellido() != null && !obj.getSegundoApellido().trim().equals("")) {
                    nombreCompleto = nombreCompleto + " " + obj.getSegundoApellido();
                }
                certificado.setStrNombreCompleto(nombreCompleto);
                certificado.setStrRegimen(obj.getMaeRegimenDescripcion());
                certificado.setStrSemanas(""+obj.getSemanaAfiliacion());
                //2021-05-21 jyperez adición nuevos campos
                certificado.setStrId(""+obj.getId());
                //2022-08-10 INC 0001275 jyperez se cambia para tomar el valor del grupo del afiliado. LA tabla AsegAfiliadoCertificado no lo tiene
                certificado.setStrGrupoSisben(afiliado.getMaeGrupoSisbenValor());
                //2025-05-13 jyperez adición nuevo campo categoría IBC. Si el regimen es SUBSIDIADO, ponemos "NO APLICA"
                if (obj.getMaeRegimenValor() != null && obj.getMaeRegimenValor().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)) {
                    certificado.setStrCategoriaIBC(afiliado.getMaeCategoriaIbcValor());
                } else {
                    certificado.setStrCategoriaIBC(AfiliadoParametro.CATEGORIA_IBC_NO_APLICA);
                }
                bean.setCertificadoAfiliacion(certificado);
                listaCertificado.add(certificado);
                // Generar report para guardarlo en la ruta.
                //Estructura de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/Certificado_Afiliacion.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaCertificado);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream streamArchivo = new ByteArrayInputStream(bytes);
                InputStream streamImpresion = new ByteArrayInputStream(bytes);
                bean.setAdjuntoStream(streamArchivo);
                //el certificado debe guardarse en la ruta.Tener en cuenta si se almacena entonces el flujo de bytes para pasarselo 
                generarArchivoCertificado(bean,streamImpresion);
                // al streamedContent y no generarlo en el bean. De igual forma necesitamos almacenarlo en ruta.
            }
            
        }catch (Exception e) {
            bean.addError("Ocurrió un error en el proceso de Generación del Certificado: " + e.getMessage());
        }
        
    }
    
    private void generarCertificadoPortabilidad(ConsultarAfiliadoBean bean) {
        AsegAfiliadoCertificado obj = new AsegAfiliadoCertificado();
        Maestro maestro;
        ReportePortabilidad reporte;
        List<ReportePortabilidad> listaReportesPortabilidad = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //almacenamos el reporte en la BD
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getId());
            //consultamos la ultima portabilidad del afiliado.
            reporte = getPortabilidadRemoto().reporteUtimaPortabilidadAfiliado(bean.getObjeto().getId().toString());
            if (reporte != null) {
                reporte.setStrUsuarioImprime(bean.getReporte().getStrUsuarioImprime());
                bean.setReporte(reporte);
            } else {
                throw new Exception("No se encontró información de portabilidades asociadas al afiliado.");
            }
            if (afiliado != null) {
                //con los datos del afiliado llenamos el objeto del afiliado certificado
                obj.setIdAfiliado(afiliado.getIdAfiliado());
                obj.setMaeTipoDocumentoId(afiliado.getMaeTipoDocumento());
                //traer del maestro
                maestro = bean.getHashTiposDocumento().get(afiliado.getMaeTipoDocumento());
                if (maestro != null) {
                    obj.setMaeTipoDocumentoCodigo(maestro.getValor());
                    obj.setMaeTipoDocumentoValor(maestro.getNombre());
                }
                obj.setNumeroDocumento(afiliado.getNumeroDocumento());
                obj.setPrimerNombre(afiliado.getPrimerNombre());
                obj.setSegundoNombre(afiliado.getSegundoNombre());
                obj.setPrimerApellido(afiliado.getPrimerApellido());
                obj.setSegundoApellido(afiliado.getSegundoApellido());
                obj.setFechaNacimiento(afiliado.getFechaNacimiento());
                obj.setMaeSubgrupoSisbenId(afiliado.getMaeGrupoSisbenId());
                //maestro
                maestro = bean.getHashSubGrupoSisben().get(afiliado.getMaeGrupoSisbenId());
                if (maestro != null) {
                    obj.setMaeSubgrupoSisbenCodigo(maestro.getValor());
                    obj.setMaeSubgrupoSisbenValor(maestro.getNombre());
                }
                if (afiliado.getMaeNivelSisbenId() != null) {
                    obj.setMaeNivelId(afiliado.getMaeNivelSisbenId());
                    obj.setMaeNivelCodigo(afiliado.getMaeNivelSisbenCodigo());
                    obj.setMaeNivelValor(afiliado.getMaeNivelSisbenValor());
                } else {
                    obj.setMaeNivelId(0);
                    obj.setMaeNivelCodigo(afiliado.getNivelSisben());
                    obj.setMaeNivelValor(afiliado.getNivelSisben());
                }

                obj.setMaeGrupoPoblacionalId(afiliado.getMaeGrupoPoblacional());
                //maestro
                maestro = bean.getHashGrupoPoblacional().get(afiliado.getMaeGrupoPoblacional());
                if (maestro != null) {
                    obj.setMaeGrupoPoblacionalCodigo(maestro.getValor());
                    obj.setMaeGrupoPoblacionalValor(maestro.getNombre());
                }
                obj.setFechaAfiliacion(afiliado.getFechaAfiliacionEps());
                if (afiliado.getMaeRegimenId() != null) {
                    obj.setMaeRegimenId(afiliado.getMaeRegimenId());
                    obj.setMaeRegimenDescripcion(afiliado.getMaeRegimenValor());
                    obj.setMaeRegimenValor(afiliado.getMaeRegimenCodigo());
                } else {
                    obj.setMaeRegimenId(0);
                    obj.setMaeRegimenDescripcion(afiliado.getRegimen());
                    obj.setMaeRegimenValor(afiliado.getRegimen());
                }

                obj.setMaeEstadoAfiliacionId(afiliado.getMaeEstadoAfiliacion());
                //maestro
                maestro = bean.getHashEstadosAfiliacion().get(afiliado.getMaeEstadoAfiliacion());
                if (maestro != null) {
                    obj.setMaeEstadoAfiliacionCodigo(maestro.getValor());
                    obj.setMaeEstadoAfiliacionValor(maestro.getNombre());
                }
                try {
                    obj.setModeloLiquidacion(Integer.parseInt(afiliado.getModeloLiquidacion()));
                } catch (NumberFormatException e) {
                    obj.setModeloLiquidacion(0);
                }catch (Exception e) {
                    obj.setModeloLiquidacion(0);
                }
                obj.setFechaRetiro(afiliado.getFechaEgresoEps());
                obj.setSemanaAfiliacion(calcularDiferenciaSemanas(afiliado.getFechaAfiliacionEps(), fechaActual));
                obj.setDireccionAfiliado(afiliado.getDireccionResidencia());
                obj.setTelefonoAfiliado(afiliado.getTelefonoFijo());
                obj.setCelularAfiliado(afiliado.getTelefonoMovil());
                obj.setAfiliacionUbicacionId(afiliado.getAfiliacionUbicacion().getId());
                obj.setAfiliacionUbicacionValor(afiliado.getAfiliacionUbicacion().getNombre());
                obj.setResidenciaUbicacionId(afiliado.getResidenciaUbicacion().getId());
                obj.setResidenciaUbicacionValor(afiliado.getResidenciaUbicacion().getNombre());
                obj.setCntPrestadorSedesId(afiliado.getPrimariaPrestadorSede().getId());
                obj.setCntPrestadorSedesValor(afiliado.getPrimariaPrestadorSede().getNombreSede());
                obj.setCorreoElectronico(afiliado.getEmail());
                obj.setTipo(AfiliadoParametro.TIPO_CERTIFICADO_PORTABILIDAD);
                obj.setFechaInicioVigencia(fechaActual);
                obj.setFechaFinVigencia(sumarDiasFecha(fechaActual, AfiliadoParametro.CERTIFICADO_DIAS_VIGENCIA));
                obj.setDiasVigencia(AfiliadoParametro.CERTIFICADO_DIAS_VIGENCIA);
                //PropApl propiedades = new PropApl();
                obj.setNombreArchivo("CP" + "_" + obj.getMaeTipoDocumentoCodigo()+ obj.getNumeroDocumento() + sdfHoraCert.format(fechaActual) + ".pdf");
                obj.setRuta(PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_CERTIFICADOS));
                obj.setAsegAfiliado(afiliado);
                // auditoria
                bean.auditoriaGuardar(obj);
                // guardamos el registro del certificado
                obj.setId(getAfiliadoCertificadoRemoto().insertar(obj));
                //y procedemos a generar el certificado para impresión
                if (obj.getId() != null) {
                    //certificado.setId(obj.getId()); CAMBIAR aca no se donde poner este número ya que el certificado de portabilidad se genera desde una consulta
                    reporte.setStrCodigoVerificacion(obj.getId()+"");
                } else {
                    // lanzar excepción con la no generación del registro
                    throw new Exception("No se almacenó el Certicado del Afiliado en la Base de Datos.");
                }
                reporte.setNombreArchivo(obj.getNombreArchivo());
                reporte.setRuta(obj.getRuta());
                listaReportesPortabilidad.add(reporte);
                //asignamos nuevamente copia de los cambios a reporte
                bean.setReporte(reporte);
                // Generar report para guardarlo en la ruta.
                //Estructura de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/certificado_portabilidad.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportesPortabilidad);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream streamArchivo = new ByteArrayInputStream(bytes);
                InputStream streamImpresion = new ByteArrayInputStream(bytes);
                bean.setAdjuntoStream(streamArchivo);
                //el certificado debe guardarse en la ruta.Tener en cuenta si se almacena entonces el flujo de bytes para pasarselo 
                generarArchivoCertificadoPortabilidad(bean, streamImpresion);
                // al streamedContent y no generarlo en el bean. De igual forma necesitamos almacenarlo en ruta.
            } else {
                throw new Exception("No se encontró información del afiliado para almacenar el certificado en base de datos.");
            }
        } catch (Exception ex) {
            //Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception: "+ex);
        }
    }
    
    private boolean generarArchivoCertificado(ConsultarAfiliadoBean bean, InputStream impresion) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getCertificadoAfiliacion().getRuta(), bean.getCertificadoAfiliacion().getNombreArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(impresion, destino);
            IOUtils.closeQuietly(impresion);
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {
                Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return esArchivoGuardado;
    }
    
    private boolean generarArchivoCertificadoPortabilidad(ConsultarAfiliadoBean bean, InputStream impresion) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getReporte().getRuta(), bean.getReporte().getNombreArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(impresion, destino);
            IOUtils.closeQuietly(impresion);
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {
                Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return esArchivoGuardado;
    }

    public static int calcularDiferenciaSemanas(Date fechaInicio, Date fechaFin) {
        int semanas = 0;
        Calendar fechaCalInicio = Calendar.getInstance();
        Calendar fechaCalActual = Calendar.getInstance();
        // cargamos la fecha a evaluar
        fechaCalInicio.setTime(fechaInicio);
        fechaCalActual.setTime(fechaFin);
        long ms1 = fechaCalInicio.getTimeInMillis();
        long ms2 = fechaCalActual.getTimeInMillis();
        // Cálculo de las diferencias.
        long difMs = ms2-ms1;
        long msPorWeek = 1000*60*60*24*7;
        semanas = (int) (difMs/msPorWeek);
        return semanas;
    }

    public static Date sumarDiasFecha(Date fechaActual, int dias) {
        Calendar calendar = Calendar.getInstance();
        //le sumamos los días definidos a la fecha actual
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    private void consultarMiPresAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaMiPres().setCantidadRegistros(getMpPrescripcionRemoto().consultarCantidadListaPorAfiliado(bean.getParamConsultaMiPres()));
            bean.setListaMiPres(getMpPrescripcionRemoto().consultarListaPorAfiliado(bean.getParamConsultaMiPres()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarTutelasAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaTutelas().setCantidadRegistros(getTutelaRemoto().consultarCantidadListaPorAfiliado(bean.getParamConsultaTutelas()));
            bean.setListaTutelas(getTutelaRemoto().consultarListaPorAfiliado(bean.getParamConsultaTutelas()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarHospitalizacionAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaHospitalizacion().setCantidadRegistros(getAucHospitalizacionRemoto().consultarCantidadListaConsultaTrescientosSesenta(bean.getParamConsultaHospitalizacion()));
            bean.setListaHospitalizacion(getAucHospitalizacionRemoto().consultarListaConsultaTrescientosSesenta(bean.getParamConsultaHospitalizacion()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarDireccionadoAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaDireccionado().setCantidadRegistros(getPeDireccionadoRemoto().consultarCantidadListaAfiliado(bean.getParamConsultaDireccionado()));
            bean.setListaDireccionado(getPeDireccionadoRemoto().consultarListaAfiliado(bean.getParamConsultaDireccionado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarSolicitudesAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaSolicitudes().setCantidadRegistros(getAuAnexo3Remoto().consultarCantidadListaAfiliado(bean.getParamConsultaSolicitudes()));
            bean.setListaSolicitudes(getAuAnexo3Remoto().consultarListaAfiliado(bean.getParamConsultaSolicitudes()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarPrestacionesAfiliado(ConsultarAfiliadoBean bean) {
        try {
            bean.getParamConsultaCmDetalleFactura().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaConsultaAfiliado(bean.getParamConsultaCmDetalleFactura()));
            bean.setListaCmDetalleFactura(getCmDetalleRemoto().consultarListaConsultaAfiliado(bean.getParamConsultaCmDetalleFactura()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verMiPres(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoMiPres(getMpPrescripcionRemoto().consultar(bean.getObjetoMiPres().getId()));
            if (bean.getObjetoMiPres() != null
                    && bean.getObjetoMiPres().getSedeCodigoHabilitacion() != null
                    && !bean.getObjetoMiPres().getSedeCodigoHabilitacion().isBlank()) {
                bean.getObjetoMiPres().setCntPrestadorSede(getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(bean.getObjetoMiPres().getSedeCodigoHabilitacion()));
                if (bean.getObjetoMiPres().getCntPrestadorSede() != null) {
                    bean.getObjetoMiPres().getCntPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getObjetoMiPres().getCntPrestadorSede().getUbicacionId()));
                }

            }
            if(bean.getObjetoMiPres().isRecobrante() == true){
                bean.setObjetoRecobrante(getMpPrescripcionRemoto().consultarRecobrante(bean.getObjeto().getId()));
            }
            bean.setListaMpDetalleDTO(new ArrayList<>());
            MpDetalleDTO mpDetalleDTO = null;
            for (MpPrescripcionMedicamento medicamento : getMpMedicamentoRemoto().consultarPorMpPrescripcion(bean.getObjetoMiPres().getId())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(medicamento.getId());
                mpDetalleDTO.setCantidadTotal(medicamento.getCantidadTotalEntrega() != null ? medicamento.getCantidadTotalEntrega().intValue() : null);
                mpDetalleDTO.setCantidadTotalPrescrita(medicamento.getCantidadTotalFormulada() != null ? medicamento.getCantidadTotalFormulada().toString() : null);

                mpDetalleDTO.setCodigoTecnologia("");
                mpDetalleDTO.setCodigoTecnologiaAvalEps("");
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(medicamento.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(medicamento.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(medicamento.getDuracionTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(medicamento.getEstado()));
                mpDetalleDTO.setPendientes(medicamento.getPendientes());
                mpDetalleDTO.setFechaDireccionamiento(medicamento.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(medicamento.getMedPbsUtilizado());
                mpDetalleDTO.setNombreTecnologiaAvalEps(medicamento.getMedPbsUtilizado());
                if (medicamento.getTipoTecnologia() == 4) {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getMaeProductosNutricionalesValor());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getMaeProductosNutricionalesCodigo());
                } else {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getDescripcionMedicamentoPrincipioActivo());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getCodigoFormulaFarmaceutica());
                }
                mpDetalleDTO.setNumeroEntregas(medicamento.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(medicamento.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoMedicamento(medicamento.getTipoMedicamento() == null ? 0 : medicamento.getTipoMedicamento()));
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(medicamento.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(medicamento.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(medicamento.getCantidadTratamiento());
                bean.getListaMpDetalleDTO().add(mpDetalleDTO);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpTecnologiaRemoto().consultarPorMpPrescripcion(bean.getObjetoMiPres().getId())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(tecnologia.getId());
                mpDetalleDTO.setCantidadTotal(tecnologia.getCantidadTotal());
                mpDetalleDTO.setCantidadTotalPrescrita(tecnologia.getCantidadFormulada() != null ? tecnologia.getCantidadFormulada().toString() : null);
                mpDetalleDTO.setCodigoFormaFarmaceutica(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setCodigoTecnologia(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(tecnologia.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(tecnologia.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(tecnologia.getCantidadDuracionTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(tecnologia.getEstado()));
                mpDetalleDTO.setFechaDireccionamiento(tecnologia.getFechaDireccionamiento());
                mpDetalleDTO.setPendientes(tecnologia.getPendientes());
                mpDetalleDTO.setNombreTecnologia(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaAvalEps(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaPrescripta(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNumeroEntregas(tecnologia.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(tecnologia.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(tecnologia.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(tecnologia.getCantidadTotal());
                bean.getListaMpDetalleDTO().add(mpDetalleDTO);
            }

            for (MpPrescripcionInsumo insumo : getMpInsumoRemoto().consultarPorMpPrescripcion(bean.getObjetoMiPres().getId())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(insumo.getId());
                mpDetalleDTO.setCantidadTotal(insumo.getCantidad() != null ? Integer.parseInt(insumo.getCantidad()) : null);
                mpDetalleDTO.setCantidadTotalPrescrita(insumo.getCantidadFormulada());
                mpDetalleDTO.setCodigoTecnologia(insumo.getCodigoDispositivo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(insumo.getCodigoDispositivo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(insumo.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(insumo.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(insumo.getDuracionTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(insumo.getEstado()));
                mpDetalleDTO.setFechaDireccionamiento(insumo.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(insumo.getNombreTecnologiaAvalada());
                mpDetalleDTO.setPendientes(insumo.getPendiente());
                mpDetalleDTO.setNombreTecnologiaAvalEps(insumo.getNombreTecnologiaAvalada());
                switch (insumo.getTipoTecnologia()) {
                    case 3:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeDispositivosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeDispositivosCodigo());
                        break;
                    case 5:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeServiciosComplementariosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeServiciosComplementariosCodigo());
                        break;
                    default:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getNombreTecnologiaAvalada());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getCodigoForma());
                        break;
                }
                mpDetalleDTO.setNumeroEntregas(insumo.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(insumo.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(insumo.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(insumo.getCantidadTotalEntrega().intValue());
                bean.getListaMpDetalleDTO().add(mpDetalleDTO);
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void verTutela(ConsultarAfiliadoBean bean) {
        try {
            bean.setListaSeguimientos(getSeguimientoRemoto().consultarLista(bean.getObjetoTutelas().getId()));
            bean.getObjetoTutelas().setAdjuntosList(getAdjuntoRemoto().consultarPorTutela(bean.getObjetoTutelas().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verHospitalizacion(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoHospitalizacion(getAucHospitalizacionRemoto().consultar(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjetoHospitalizacion().getAucAfiliadoId().getId()));
            bean.getObjetoHospitalizacion().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucIngresoId().getId()));
            bean.getObjetoHospitalizacion().getAucIngresoId().setUltimaFechaIngreso(bean.getObjetoHospitalizacion().getAucIngresoId().getFechaIngreso());
            if (bean.getObjetoHospitalizacion().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(0);
            }
            if (bean.getObjetoHospitalizacion().getAucEgresoId() != null && bean.getObjetoHospitalizacion().getAucEgresoId().getId() != null) {
                bean.getObjetoHospitalizacion().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucEgresoId().getId()));
            }
            bean.getObjetoHospitalizacion().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorId().getId()));
            bean.getObjetoHospitalizacion().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorSedeId().getId()));
            bean.getObjetoHospitalizacion().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verDireccionado(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoDireccionado(getPeDireccionadoRemoto().consultar(bean.getObjetoDireccionado().getId()));
            if (bean.getHashUbicaciones() == null) {
                bean.setHashUbicaciones(PeConstantes.obtenerHashUbicacion(getUbicacionRemoto().consultarActivas()));
            }
            if (bean.getHashTipoDocumentoEmpresa() == null) {
                bean.setListaTipoDocumentoEmpresa(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                bean.setHashTipoDocumentoEmpresa(PeConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoEmpresa()));
            }
            if (bean.getListaTipoGestion() == null) {
                bean.setListaTipoGestion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.PE_GESTION_TIPO));
                bean.setHashTipoGestion(PeConstantes.obtenerHashMaestro(bean.getListaEstadosDireccionado()));
            }
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjetoDireccionado().getAuAnexos3Id().getId()));
            bean.getObjetoAnexo3().setPeProgramaId(getPeProgramaRemoto().consultar(bean.getObjetoDireccionado().getPeProgramasId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verSolicitud(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoSolicitud(getAuAnexo3Remoto().consultar(bean.getObjetoSolicitud().getId()));
            buscarProfesional(bean);
            contactosAfiliado(bean);
            consultarProgramaAfiliado(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void buscarProfesional(ConsultarAfiliadoBean bean) {
        try {
            int tipoDocumento = bean.getObjetoSolicitud().getCntProfesionaleId().getMaeTipoCodumentoId();
            String documento = bean.getObjetoSolicitud().getCntProfesionaleId().getDocumento();
            CntProfesional profesional = getProfesionalRemoto().consultarNumDocumento(tipoDocumento, documento);
            if (profesional != null && profesional.getId() != null) {
                bean.getObjetoSolicitud().setCntProfesionaleId(profesional);
                bean.getObjetoSolicitud().setNombreProfesional(profesional.getPrimerNombre() + profesional.getPrimerApellido());
                CntProfesionalPrestador contrato = getAuAnexo3Remoto().buscarEspeciliadad(bean.getObjetoSolicitud().getCntPrestadorSedeId().getCntPrestador().getId(), profesional.getId());
                if (contrato != null) {
                    MaEspecialidad especialidad = new MaEspecialidad(contrato.getMaEspecialidadId());
                    especialidad.setCodigo(contrato.getMaEspecialidadCodigo());
                    especialidad.setNombre(contrato.getMaEspecialidadValor());
                    bean.getObjetoSolicitud().setMaeEspecialidadProfesional(especialidad.getId());
                    bean.getObjetoSolicitud().setObjetoEspecialidad(especialidad);
                    bean.getObjetoSolicitud().setNewProfesional(true);
                } else {
                    bean.getObjetoSolicitud().setNewProfesional(false);
                }
            } else {
                bean.getObjetoSolicitud().setCntProfesionaleId(new CntProfesional());
                bean.getObjetoSolicitud().getCntProfesionaleId().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjetoSolicitud().getCntProfesionaleId().setDocumento(documento);
                bean.getObjetoSolicitud().setNewProfesional(false);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarProgramaAfiliado(ConsultarAfiliadoBean bean) throws Exception {
        bean.setListaAfiliadoPrograma(
                getPeAfiliadosProgramaRemoto().consultarAfiliadoActivo(bean.getObjetoSolicitud().getAsegAfiliadoId().getId())
        );
    }

    public void consultarValorContribucionSolidaria(ConsultarAfiliadoBean bean) {
        BigDecimal valor = new BigDecimal(0);
        if (bean.getAfiliadoCompleto().getMaeSolidariaPorcentajeId() != null) {
            try {
                CsContribucionSolidaria contribucionSolidaria = getContribucionSolidariaRemoto().consultarPorPorcentaje(bean.getAfiliadoCompleto().getMaeSolidariaPorcentajeId());
                if (contribucionSolidaria != null) {
                    valor = contribucionSolidaria.getValor();
                    bean.getAfiliadoCompleto().setValorContribucionSolidaria(valor);
                }
            } catch(Exception ex) {
                bean.getAfiliadoCompleto().setValorContribucionSolidaria(valor);
            }
        }
    }

    private void verJustificacionEstanciaProlongada(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoJustificacionEstanciasProlongada(getAucJustificacionEstanciasProlongadaRemoto().consultar(bean.getObjetoJustificacionEstanciasProlongada().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void verPatologia(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoPatologia(getAucHospitalizacionPatologiaRemoto().consultar(bean.getObjetoPatologia().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verInoportunidad(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoInoportunidad(getAucHospitalizacionInoportunidadRemoto().consultar(bean.getObjetoInoportunidad().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verAdverso(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoAdverso(getAucHospitalizacionAdversoRemoto().consultar(bean.getObjetoAdverso().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verObjecion(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoObjecion(getAucHospitalizacionObjecionRemoto().consultar(bean.getObjetoObjecion().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verServicio(ConsultarAfiliadoBean bean) {
        try {
            bean.setObjetoServicio(getAucHospitalizacionServicioRemoto().consultar(bean.getObjetoServicio().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }
    
    @Override
    public List<ReporteAnexo3> generarReporteAnexo3(int id, ConsultarAfiliadoBean bean) {
        List<ReporteAnexo3> listaReportes = new ArrayList();
        ReporteAnexo3 reporte = new ReporteAnexo3();
        try {
            Empresa savia = getEmpresaRemoto().consultar(1);
            AuAnexo3 anexo = getAuAnexo3Remoto().consultar(id);
            if (anexo != null) {
                bean.setObjetoSolicitud(anexo);
                contactosAfiliado(bean);
                if (!anexo.getAuAnexo3DiagnosticosList().isEmpty() && !anexo.getAuAnexo3ItemsList().isEmpty()) {
                    int contador = 0;
                    String diagnostico1 = "";
                    String descripcionDiagnostico1 = "";
                    String diagnostico2 = "";
                    String descripcionDiagnostico2 = "";
                    String diagnostico3 = "";
                    String descripcionDiagnostico3 = "";
                    String diagnostico4 = "";
                    String descripcionDiagnostico4 = "";
                    for (AuAnexo3Diagnostico diagnostico : anexo.getAuAnexo3DiagnosticosList()) {
                        if (contador == 0) {
                            diagnostico1 = diagnostico.getMaDiagnosticosCodigo();
                            descripcionDiagnostico1 = diagnostico.getMaDiagnosticosValor();
                        }
                        if (contador == 1) {
                            diagnostico2 = diagnostico.getMaDiagnosticosCodigo();
                            descripcionDiagnostico2 = diagnostico.getMaDiagnosticosValor();
                        }
                        if (contador == 2) {
                            diagnostico3 = diagnostico.getMaDiagnosticosCodigo();
                            descripcionDiagnostico3 = diagnostico.getMaDiagnosticosValor();
                        }
                        if (contador == 3) {
                            diagnostico4 = diagnostico.getMaDiagnosticosCodigo();
                            descripcionDiagnostico4 = diagnostico.getMaDiagnosticosValor();
                        }
                        contador++;
                    }
                    for (AuAnexo3Item item : anexo.getAuAnexo3ItemsList()) {
                        reporte = new ReporteAnexo3();
                        reporte.setStrNumeroSolicitud(anexo.getId());
                        reporte.setDateFechaRadicacion(anexo.getFechaHoraCrea());
                        reporte.setDateFechaOrdenMedica(anexo.getFechaSolicitud());
                        reporte.setStrEntidaPagadora(savia.getNombreComercial());
                        String codigo = AuConstantes.CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO;
                        if (anexo.getAsegAfiliadoId().getRegimen().equals(AuConstantes.UNO)) {
                            codigo = AuConstantes.CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO;
                        }
                        reporte.setStrCodigoPagador(codigo);
                        reporte.setStrTipoDocSol(anexo.getCntPrestadorSedeId().getCntPrestador().getMaeTipoDocumentoCodigo());
                        reporte.setStrNombreSol(anexo.getCntPrestadorSedeId().getNombreSede());
                        reporte.setStrNumDocSol(anexo.getCntPrestadorSedeId().getCntPrestador().getNumeroDocumento());
                        reporte.setStrCodigoSol(anexo.getCntPrestadorSedeId().getCodigoHabilitacionSede());
                        reporte.setStrTelefonoSol(anexo.getCntPrestadorSedeId().getTelefonoCitas());
                        reporte.setStrDireccionSol(anexo.getCntPrestadorSedeId().getDireccion());
                        reporte.setStrDepartamentoSol(bean.obtenerDepartamento(anexo.getCntPrestadorSedeId().getUbicacionId()));
                        reporte.setStrMunicipioSol(bean.obtenerMunicipio(anexo.getCntPrestadorSedeId().getUbicacionId()));
                        reporte.setStrPrimerApellidoPac(anexo.getAsegAfiliadoId().getPrimerApellido());
                        reporte.setStrSegundoApellidoPac(anexo.getAsegAfiliadoId().getSegundoApellido());
                        reporte.setStrPrimerNombrePac(anexo.getAsegAfiliadoId().getPrimerNombre());
                        reporte.setStrSegundoNombrePac(anexo.getAsegAfiliadoId().getSegundoNombre());
                        reporte.setStrTipoDocPac(anexo.getAsegAfiliadoId().getMaeTipoDocumentoCodigo());
                        reporte.setStrNumDocPac(anexo.getAsegAfiliadoId().getNumeroDocumento());
                        reporte.setDateFechaNacPac(anexo.getAsegAfiliadoId().getFechaNacimiento());
                        reporte.setStrDireccionPac(anexo.getAsegAfiliadoId().getDireccionResidencia());
                        reporte.setStrTelefonoFijoPac(bean.getTelefonoFijoAfiliado());
                        reporte.setStrDepartamentoPac(bean.obtenerDepartamento(anexo.getAsegAfiliadoId().getPrimariaPrestadorSede().getUbicacionId())); //Se debe preguntar
                        reporte.setStrMunicipioPac(bean.obtenerMunicipio(anexo.getAsegAfiliadoId().getPrimariaPrestadorSede().getUbicacionId())); //Se debe preguntar
                        reporte.setStrCorreoPac(anexo.getAsegAfiliadoId().getEmail());
                        reporte.setStrTelefonoCelPac(bean.getTelefonoMovilAfiliado());
                        reporte.setStrCobertura("0" + anexo.getAsegAfiliadoId().getRegimen());
                        reporte.setStrOrigenAtencion(anexo.getMaeOrigenAtencionCodigo());
                        reporte.setStrServicioSolicitado(anexo.getMaeTipoServicioCodigo());
                        reporte.setStrPrioridad(anexo.getPrioridad() == true ? "1" : "0");
                        reporte.setStrUbicacion(anexo.getMaeUbicacionCodigo());
                        reporte.setStrServicio(anexo.getMaServicioHabilitadoValor());
                        reporte.setStrCama(anexo.getCama());
                        reporte.setStrManejo(anexo.getMaeGuiaManejoIntegralCodigo());
                        reporte.setStrAmbito(anexo.getMaeAmbitoAtencionValor());
                        reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                        reporte.setStrCantidad("" + item.getCantidadSolicitada());
                        reporte.setStrDescripcion(item.getMaTecnologiaValor());
                        reporte.setStrEstado(item.getEstadoStr());
                        reporte.setStrJustificacion(anexo.getJustificacionClinica());
                        reporte.setStrCodigoDP(diagnostico1);
                        reporte.setStrDescripcionDP(descripcionDiagnostico1);
                        reporte.setStrCodigoDR1(diagnostico2);
                        reporte.setStrDescripcionDR1(descripcionDiagnostico2);
                        reporte.setStrCodigoDR2(diagnostico3);
                        reporte.setStrDescripcionDR2(descripcionDiagnostico3);
                        reporte.setStrCodigoDR3(diagnostico4);
                        reporte.setStrDescripcionDR3(descripcionDiagnostico4);
                        reporte.setStrNombreSolicitante(anexo.getUsuarioCrea());
                        reporte.setStrCargoSolicitante(AuConstantes.CARGO_AUTORIZA);
                        reporte.setStrTelefonoFijoSolicitante(AuConstantes.EPS_TELEFONO);
                        reporte.setStrTelefonoCelularSolicitante(bean.getConexion().getUsuario().getCelular());
                        reporte.setStrAmbito(anexo.getMaeAmbitoAtencionValor());
                        reporte.setStrRegimen(anexo.getAsegAfiliadoId().getMaeRegimenValor());
                        reporte.setStrConsecutivo(anexo.getConsecutivo());
                        reporte.setStrDireccionAlternativa(anexo.getDireccionAlternativa());
                        reporte.setStrModalidadTecnologia(anexo.getMaeModalidadTecnologiaValor());
                        reporte.setStrFinalidadTecnologia(anexo.getMaeFinalidadTecnologiaValor());
                        listaReportes.add(reporte);
                    }
                }
            }
            bean.setObjetoSolicitud(new AuAnexo3());
        } catch (Exception e) {
            listaReportes = new ArrayList();
            bean.setObjetoSolicitud(new AuAnexo3());
        }
        return listaReportes;
    }
    
    private void contactosAfiliado(ConsultarAfiliadoBean bean) {
        if (bean.getObjetoSolicitud().getAsegAfiliadoId().getListaAsegAfiliadoContacto() != null) {
            bean.setTelefonoFijoAfiliado(
                    bean.getObjetoSolicitud().getAsegAfiliadoId().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_TELEFONO))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
            bean.setTelefonoMovilAfiliado(
                    bean.getObjetoSolicitud().getAsegAfiliadoId().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_CELULAR))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
        } else {
            bean.setTelefonoFijoAfiliado(null);
            bean.setTelefonoMovilAfiliado(null);
        }
    }
    
    @Override
    public DiaHabil validarFechaHabil(Date fecha) {
        try {
            DiaHabil dia = getCalendarioRemoto().consultarDia(fecha);
            return dia;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public AuRechazo buscarRechazo(int idItem) {
        AuRechazo rechazo = null;
        try {
            rechazo = getAuRechazoRemoto().consultarPorItem(idItem);
            if (rechazo != null) {
                AuAnexo3 anexo3 = getAuAnexo3Remoto().consultar(rechazo.getAuAnexo3Id().getId());
                rechazo.setAuAnexo3Id(anexo3);
                for (AuRechazoItem item : rechazo.getAuRechazoItemsList()) {
                    if (item.getAuAnexo3ItemId().getId() != null) {
                        AuAnexo3Item anexoItem = getAuAnexo3ItemRemoto().consultar(item.getAuAnexo3ItemId().getId());
                        if (anexoItem != null) {
                            item.setAuAnexo3ItemId(anexoItem);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return rechazo;
    }
    
    @Override
    public void consultarPosfechados(ConsultarAfiliadoBean bean) {
        try {
            bean.getObjetoSolicitud().setListaPosfechados(getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjetoSolicitud().getId(), bean.getObjetoSolicitud().getObjetoTecnologia().getMaTecnologiaId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
    }
    
    @Override
    public void consultarAdjuntosCotizacionSol(ConsultarAfiliadoBean bean
    ) {
        try {
            if (AuAnexo3Item.ESTADO_CON_COTIZACION == bean.getObjetoSolicitud().getObjetoTecnologia().getEstado()
                    || AuAnexo3Item.ESTADO_APROBADO_AUDITORIA == bean.getObjetoSolicitud().getObjetoTecnologia().getEstado()) {
                AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(bean.getObjetoSolicitud().getObjetoTecnologia().getId());
                if (itemCotizacion != null) {
                    bean.setListaAdjuntosCotizacion(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdCotizacion(itemCotizacion.getAuCotizacionId().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError("No se encontro una cotizacion, favor comunicarse con el administrador");
        }
    }
    
}
