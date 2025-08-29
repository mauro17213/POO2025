/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnSmsEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoValor;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Afiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Historico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Tutela;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Automatico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Historico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuRango;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazoItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoPrestadorAsignado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuTopeAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteNegacionServicio;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsCopagoModeradoraHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoItemRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoValorRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3DiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3HistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3TutelaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4HistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuItemBitacoraRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuRechazoItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuRechazoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoGestionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoPrestadorAsignadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuTopeAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsCopagoModeradoraHistoricoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoSugeridoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeSugeridoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaRemoto;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.solicitud.negocio.AuAnexo3SolicitudRemoto;
import com.saviasaludeps.savia.solicitud.negocio.AuSeguimientoSolicitudRemoto;
import com.saviasaludeps.savia.solicitud.negocio.PeDireccionadoSolicitudRemoto;
import com.saviasaludeps.savia.solicitud.negocio.ValidadorRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuSolicitudBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Mensaje;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.RemotoEJBSolicitud;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Stiven Giraldo
 */
public class AuSolicitudServicioImpl extends AccionesBO implements AuSolicitudServicioIface {

    public final static String AUTOMATICA = PropApl.getInstance().get(PropApl.AU_AUTORIZACION_AUTOMATICA);

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private AuAnexo3SolicitudRemoto getAuAnexo3SolicitudRemoto() throws Exception {
        return (AuAnexo3SolicitudRemoto) RemotoEJB.getEJBSolicitudRemoto("AuAnexo3Servicio", AuAnexo3SolicitudRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }

    private AuAnexo3DiagnosticoRemoto getAuAnexo3DiagnosticoRemoto() throws Exception {
        return (AuAnexo3DiagnosticoRemoto) RemotoEJB.getEJBRemoto("AuAnexo3DiagnosticoServicio", AuAnexo3DiagnosticoRemoto.class.getName());
    }

    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }

    private CntProfesionalRemoto getProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AuAnexo3AfiliadoRemoto getAuAnexo3AfiliadoRemoto() throws Exception {
        return (AuAnexo3AfiliadoRemoto) RemotoEJB.getEJBRemoto("AuAnexo3AfiliadoServicio", AuAnexo3AfiliadoRemoto.class.getName());
    }

    private AuAnexo3TutelaRemoto getAuAnexo3TutelaRemoto() throws Exception {
        return (AuAnexo3TutelaRemoto) RemotoEJB.getEJBRemoto("AuAnexo3TutelaServicio", AuAnexo3TutelaRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }

    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }

    private AuRechazoRemoto getAuRechazoRemoto() throws Exception {
        return (AuRechazoRemoto) RemotoEJB.getEJBRemoto("AuRechazoServicio", AuRechazoRemoto.class.getName());
    }

    private AuRechazoItemRemoto getAuRechazoItemRemoto() throws Exception {
        return (AuRechazoItemRemoto) RemotoEJB.getEJBRemoto("AuRechazoItemServicio", AuRechazoItemRemoto.class.getName());
    }

    private MaTecnologiaServicioHabilitacionRemoto getMaTecnologiaServicioHabilitacionRemoto() throws Exception {
        return (MaTecnologiaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicioHabilitacionServicio", MaTecnologiaServicioHabilitacionRemoto.class.getName());
    }

    private CntContratoSedeRemoto getCntContratoSedeRemoto() throws Exception {
        return (CntContratoSedeRemoto) RemotoEJB.getEJBRemoto("CntContratoSedeServicio", CntContratoSedeRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private ValidadorRemoto getValidadorRemoto() throws Exception {
        return (ValidadorRemoto) RemotoEJBSolicitud.getEJBRemoto("ValidadorServicio", ValidadorRemoto.class.getName());
    }

    private AuCotizacionRemoto getAuCotizacionRemoto() throws Exception {
        return (AuCotizacionRemoto) RemotoEJB.getEJBRemoto("AuCotizacionServicio", AuCotizacionRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }


    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private AuAnexo4HistoricoRemoto getAuAnexo4HistoricoRemoto() throws Exception {
        return (AuAnexo4HistoricoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4HistoricoServicio", AuAnexo4HistoricoRemoto.class.getName());
    }

    private AuCotizacionItemRemoto getAuCotizacionItemRemoto() throws Exception {
        return (AuCotizacionItemRemoto) RemotoEJB.getEJBRemoto("AuCotizacionItemServicio", AuCotizacionItemRemoto.class.getName());
    }

    private AuItemBitacoraRemoto getAuItemBitacoraRemoto() throws Exception {
        return (AuItemBitacoraRemoto) RemotoEJB.getEJBRemoto("AuItemBitacoraServicio", AuItemBitacoraRemoto.class.getName());
    }

    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
    }

    private AuGrupoRemoto getAuGrupoRemoto() throws Exception {
        return (AuGrupoRemoto) RemotoEJB.getEJBRemoto("AuGrupoServicio", AuGrupoRemoto.class.getName());
    }

    private AuGrupoUsuarioRemoto getAuGrupoUsuarioRemoto() throws Exception {
        return (AuGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("AuGrupoUsuarioServicio", AuGrupoUsuarioRemoto.class.getName());
    }

    private AuGrupoTecnologiaRemoto getAuGrupoTecnologiaRemoto() throws Exception {
        return (AuGrupoTecnologiaRemoto) RemotoEJB.getEJBRemoto("AuGrupoTecnologiaServicio", AuGrupoTecnologiaRemoto.class.getName());
    }

    private CsCopagoModeradoraHistoricoRemoto getCsCopagoModeradoraHistoricoRemoto() throws Exception {
        return (CsCopagoModeradoraHistoricoRemoto) RemotoEJB.getEJBRemoto("CsCopagoModeradoraHistoricoServicio", CsCopagoModeradoraHistoricoRemoto.class.getName());
    }

    private AuAnexo3HistoricoRemoto getAuAnexo3HistoricoRemoto() throws Exception {
        return (AuAnexo3HistoricoRemoto) RemotoEJB.getEJBRemoto("AuAnexo3HistoricoServicio", AuAnexo3HistoricoRemoto.class.getName());
    }

    private PortabilidadRemoto getPortabilidadRemoto() throws Exception {
        return (PortabilidadRemoto) RemotoEJB.getEJBRemoto("PortabilidadServicio", PortabilidadRemoto.class.getName());
    }

    private TuTutelaRemoto getTutelaRemoto() throws Exception {
        return (TuTutelaRemoto) RemotoEJB.getEJBRemoto("TuTutelaServicio", TuTutelaRemoto.class.getName());
    }

    private PeDireccionadoSolicitudRemoto getPeDireccionadoSolicitudRemoto() throws Exception {
        return (PeDireccionadoSolicitudRemoto) RemotoEJB.getEJBSolicitudRemoto("PeDireccionadoServicio", PeDireccionadoSolicitudRemoto.class.getName());
    }

    private AuSeguimientoSolicitudRemoto getAuSeguimientoSolicitudRemoto() throws Exception {
        return (AuSeguimientoSolicitudRemoto) RemotoEJB.getEJBSolicitudRemoto("AuSeguimientoSolicitudServicio", AuSeguimientoSolicitudRemoto.class.getName());
    }

    private AuAnexo2RescateRemoto getAuAnexo2RescateRemoto() throws Exception {
        return (AuAnexo2RescateRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateServicio", AuAnexo2RescateRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private AuSeguimientoRemoto getAuSeguimientoRemoto() throws Exception {
        return (AuSeguimientoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoServicio", AuSeguimientoRemoto.class.getName());
    }

    private MaTecnologiaRemoto getMaTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicio", MaTecnologiaRemoto.class.getName());
    }

    private AuSeguimientoGestionRemoto getAuSeguimientoGestionRemoto() throws Exception {
        return (AuSeguimientoGestionRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoGestionServicio", AuSeguimientoGestionRemoto.class.getName());
    }

    private AuSeguimientoPrestadorAsignadoRemoto getAuSeguimientoPrestadorAsignadoRemoto() throws Exception {
        return (AuSeguimientoPrestadorAsignadoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoPrestadorAsignadoServicio", AuSeguimientoPrestadorAsignadoRemoto.class.getName());
    }

    private PeAfiliadoSugeridoRemoto getPeAfiliadoSugeridoRemoto() throws Exception {
        return (PeAfiliadoSugeridoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoSugeridoServicio", PeAfiliadoSugeridoRemoto.class.getName());
    }

    private PeAfiliadoDiagnosticoRemoto getPeAfiliadoDiagnosticoRemoto() throws Exception {
        return (PeAfiliadoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoDiagnosticoServicio", PeAfiliadoDiagnosticoRemoto.class.getName());
    }

    private PeSugeridoAdjuntoRemoto getPeSugeridoAdjuntoRemoto() throws Exception {
        return (PeSugeridoAdjuntoRemoto) RemotoEJB.getEJBRemoto("PeSugeridoAdjuntoServicio", PeSugeridoAdjuntoRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private AuTopeAfiliadoRemoto getTopeAfiliadoRemoto() throws Exception {
        return (AuTopeAfiliadoRemoto) RemotoEJB.getEJBRemoto("AuTopeAfiliadoServicio", AuTopeAfiliadoRemoto.class.getName());
    }

    private AnticipoRemoto getAnticipoRemoto() throws Exception {
        return (AnticipoRemoto) RemotoEJB.getEJBRemoto("AnticipoServicio", AnticipoRemoto.class.getName());
    }

    private AnticipoItemRemoto getAnticipoItemRemoto() throws Exception {
        return (AnticipoItemRemoto) RemotoEJB.getEJBRemoto("AnticipoItemServicio", AnticipoItemRemoto.class.getName());
    }

    private AnticipoAdjuntoRemoto getAnticipoAdjuntoRemoto() throws Exception {
        return (AnticipoAdjuntoRemoto) RemotoEJB.getEJBRemoto("AnticipoAdjuntoServicio", AnticipoAdjuntoRemoto.class.getName());
    }

    private AnticipoValorRemoto getAnticipoValorRemoto() throws Exception {
        return (AnticipoValorRemoto) RemotoEJB.getEJBRemoto("AnticipoValorServicio", AnticipoValorRemoto.class.getName());
    }

    @Override
    public void Accion(AuSolicitudBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardarCentralizado(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            auditar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1://APROBAR
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    consultarCotizacion(bean);
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    aprobar(bean);
                                    break;
                                case Url.ACCION_ADICIONAL_1:
                                    consutarCapitaPGP(bean);
                                    break;
                                case Url.ACCION_ADICIONAL_2:
                                    consultarContrato(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2://APROBAR TODOS
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    gestionarAprobarTodo(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_3://RECHAZAR
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    rechazar(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_4://RECHAZAR TODOS
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    rechazarTodo(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_5://DEVOLVER
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    devolver(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_6://DEVOLVER TODO
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    devolverTodo(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_7://DERIVAR
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    verDerivacion(bean);
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    derivar(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_8://DERIVAR TODOS
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
//                                    derivarTodo(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_9://Anular
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    AnularAuditoria(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_10://Negar Todos
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    negarTodo(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestionRiesgo(bean);
                            break;
                        case Url.ACCION_VER:
                            verSugerido(bean);
                            break;
                        case AuSolicitudBean.ACCION_CREAR_SUGERIDO_ADJUNTO:
                            crearAdjuntoSugerido(bean);
                            break;
                        case AuSolicitudBean.ACCION_GUARDAR_SUGERIDO_ADJUNTO:
                            guardarAdjuntoSugerido(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            ver(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            anular(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verDevoluciones(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarDevoluciones(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            gestionarPosfechado(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarPosfechado(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    modificarPosfechado(bean);
                    break;
            }
        } else {
            System.err.println("Sesión inactiva");
        }
    }

    @Override
    public void cargaInicial(AuSolicitudBean bean) {
        try {

            bean.setListaTipoCargue(AuConstantes.obtenerTiposCargue());
            bean.setHashTipoCargue(AuConstantes.obtenerHashMaestro(bean.getListaTipoCargue()));

            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumento(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));

            bean.setListaTipoAmbito(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));
            if (!bean.getListaTipoAmbito().isEmpty()) {
                List<Maestro> nuevaLista = new ArrayList();
                for (Maestro maestro : bean.getListaTipoAmbito()) {
                    if (!maestro.getValor().equalsIgnoreCase(AuConstantes.CODIGO_AMBITO_MIXTO)) {
                        nuevaLista.add(maestro);
                    }
                }
                bean.setListaTipoAmbito(nuevaLista);
            }
            bean.setHashTipoAmbito(AuConstantes.obtenerHashMaestro(bean.getListaTipoAmbito()));

            bean.setListaRegimenAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
//            bean.setHashRegimenAfiliado(AuConstantes.obtenerHashMaestro(bean.getListaRegimenAfiliado()));

            bean.setListaGeneroAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
//            bean.setHashGeneroAfiliado(AuConstantes.obtenerHashMaestro(bean.getListaGeneroAfiliado()));

            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());

            bean.setListaTipoDocumentoArchivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ADJUNTO_TIPO));
            bean.setHashTipoDocumentoArchivo(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoArchivo()));

            bean.setListaTipoDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TIPO_DIAGNOSTICO));
            bean.setHashTipoDiagnostico(AuConstantes.obtenerHashMaestro(bean.getListaTipoDiagnostico()));

            bean.setListaNiveles(AuConstantes.obtenerNiveles());

            bean.setListaCausaExterna(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_CAUSA_EXTERNA));
            bean.setHashCausaExterna(AuConstantes.obtenerHashMaestro(bean.getListaCausaExterna()));

            bean.setListaTipoFinalidad(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_DIAGNOSTICO_FINALIDAD));
            bean.setHashTipoFinalidad(AuConstantes.obtenerHashMaestro(bean.getListaTipoFinalidad()));

            bean.setListaTipoCatastrofico(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_PATOLOGIA_CATASTROFICA));
            bean.setHashTipoCatastrofico(AuConstantes.obtenerHashMaestro(bean.getListaTipoCatastrofico()));

            bean.setListaTipoViaAdministracion(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_MEDICAMENTO_VIA_ADMINISTRACION));
            bean.setHashTipoViaAdministracion(AuConstantes.obtenerHashMaestro(bean.getListaTipoViaAdministracion()));

            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
//            bean.setHashEstadosAfiliado(AuConstantes.obtenerHashMaestro(bean.getListaEstadosAfiliado()));

//            bean.setListaGrupoPoblacional(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_GRUPO_POBLACIONAL));
//            bean.setHashGrupoPoblacional(AuConstantes.obtenerHashMaestro(bean.getListaGrupoPoblacional()));
            bean.setListaTipoDocumentoProfesional(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL));
            bean.setHashTipoDocumentoProfesional(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoProfesional()));

            bean.setListaRechazos(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_RECH_CAUSA_RECHAZO));
            bean.setHashRechazos(AuConstantes.obtenerHashMaestro(bean.getListaRechazos()));

            bean.setListaAnulaciones(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ESTADO_MOTIVO));
            bean.setHashAnulaciones(AuConstantes.obtenerHashMaestro(bean.getListaAnulaciones()));

            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setListaDepartamentos(UbicacionSingle.getInstance().getListaDepartamentos());

            bean.setListaEstadoSolicitud(AuConstantes.obtenerEstadosSolicitud());

//            bean.setIdEstadoAfiliado(getMaestroRemoto().consultarPorValorTipo("101", MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setListaGruposActivos(getAuGrupoRemoto().consultarActivos());

            bean.setUsuarioGruposAsigandos(getAuGrupoUsuarioRemoto().consultarGruposUsuario(bean.getConexion().getUsuario().getId()));
            //SEGUIMIENTO
            bean.setHashEstadosSeguimiento(getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.AU_SEGUIMIENTO_ESTADO));
            bean.setHashEstadoGestion(getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.AU_SEGUIMIENTO_ESTADO));
            bean.setHashMotivoGestion(getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.AU_SEGUIMIENTO_MOTIVO));

            //finalidad de tecnologia
            bean.setListaModalidadTecnologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_A1_MODALIDAD_TECNOLOGIA));
            bean.setHashModalidadTecnologia(AuConstantes.obtenerHashMaestro(bean.getListaModalidadTecnologia()));
            //modalidad de tecnologia
            bean.setListaFinalidadTecnologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_A1_FINALIDAD_TECNOLOGIA));
            bean.setHashFinalidadTecnologia(AuConstantes.obtenerHashMaestro(bean.getListaFinalidadTecnologia()));
            
            //programas especiales
            bean.setListaProgramaEspeciales(getPeProgramaRemoto().consultarTodosEstado(1));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(AuSolicitudBean bean) {
        try {
            /*if (bean.isPrimera()) {
                bean.getParamConsulta().setFiltros(new HashMap());
                bean.getParamConsulta().getFiltros().put("objetoTecnologia.auditor", bean.getUsuario());
                bean.setPrimera(false);
            }*/
//            getAuGrupoUsuarioRemoto().consultarLista(bean.getParamConsulta());
            bean.getParamConsulta(0).setParametroConsulta1(bean.getFechaInicio());
            bean.getParamConsulta(0).setParametroConsulta2(bean.getFechaFin());
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo3Remoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuAnexo3Remoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AuSolicitudBean bean) {
        try {
            bean.setObjeto(getAuAnexo3Remoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getVersion()) {
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_CAUSA_MOTIVA_ATENCION);
                bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_ATENCION_SOLICITADA);
                bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_GRUPO_SERVICIOS);
            } else {
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_ORIGEN_ATENCION);
                bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_SERVICIO_SOLICITADO);
                bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_UBICACION_PACIENTE);
            }
            bean.getObjeto().setAucSugeridolList(getPeAfiliadoSugeridoRemoto().consultarSugeridosAfiliado(bean.getObjeto().getAsegAfiliadoId().getId()));
            buscarProfesional(bean);
            contactosAfiliado(bean);
            consultarProgramaAfiliado(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AuSolicitudBean bean) {
        try {
            bean.setIdProgramaEspecial(0);
            bean.setListaProgramaEspecial(new ArrayList());
            bean.setObjeto(new AuAnexo3());
            bean.getObjeto().setHabilitarCampoDireccionAlternativa(true);
            bean.getObjeto().setHabilitarCampoModalidadTecnologia(true);
            bean.getObjeto().setHabilitarCampoFinalidadTecnologia(true);
            bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_ORIGEN_ATENCION);
            bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_SERVICIO_SOLICITADO);
            bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_UBICACION_PACIENTE);
            bean.setTelefonoFijoAfiliado(null);
            bean.setTelefonoMovilAfiliado(null);
            completarMaestro(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarCentralizado(AuSolicitudBean bean) {
        try {
            //Se valida que se tenga como minimo una prescripcion en la solicitud
            if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty()) {
                bean.addError("La solicitud debe tener minimo un item");
            } else {
                //Se valida que los medicamentos que se hayan agregado tengan
                //toda la información necesaria
                validarItemsMedicamentos(bean, bean.getObjeto().getAuAnexo3ItemsList());
            }

            //Se valida que la justificacion clinica no este vacia o con espacios
            if (bean.getObjeto().getJustificacionClinica() == null || bean.getObjeto().getJustificacionClinica().trim().isEmpty()) {
                bean.addError("La Justificacion no puede estar vacia o tener espacios");
            }

            //Se valida que se haya ingresado la especialidad del profesional
            //necesaria para cuando no existe crear el profesional en el prestador
            if (bean.getObjeto().getObjetoEspecialidad() == null) {
                bean.addError("Se debe seleccionar la especialidad del profesional");
            }

            //Se valida que se haya diligenciado el servicio de habilitación
            if (bean.getObjeto().getMaServicioHabilitadoId() == null || bean.getObjeto().getMaServicioHabilitadoId() == 0) {
                bean.addError("No se ha seleccionado un servicio de habilitación");
            }

            //Validamos que exista un diagnostico principal en el listado de 
            //diagnosticos
            if (!validarDiagnosticoPrincipal(bean)) {
                bean.addError("No se ha seleccionado un diganostico como principal");
            }

            /*//Se valida que exita modalidad tecnologia
            if(bean.getObjeto().getMaeModalidadTecnologiaId() == null){
                 bean.addError("No se ha seleccionado la modalidad de tecnología");
            }
            
            //Se valida que exita finalidad tecnologia
            if(bean.getObjeto().getMaeFinalidadTecnologiaId() == null){
                 bean.addError("No se ha seleccionado la finalidad de tecnología");
            }*/
            //Se validan las fechas medicas de las prescripciones
            validarItemsFechaOrdenMedica(bean);

            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                AuAnexo3 anexo3 = new AuAnexo3();
                try {
                    //Se establecen valores por defecto
                    bean.getObjeto().setCargaMasiva(false);
                    bean.getObjeto().setWebService(false);
                    //Se establecen valores de atributos tipo maestros
                    completarMaestroHistoricos(bean);
                    bean.getObjeto().setFuenteOrigen(AuConstantes.ID_CARGUE_MANUAL);
                    //se establecen nuevos valores segun resolucion 2335
                    //bean.getObjeto().setVersion(AuConstantes.VERSION_0);
                    //Se hace llamado al insertar solicitud
                    anexo3 = getAuAnexo3SolicitudRemoto().insertar(bean.getObjeto());
                    //Si se guardo correctamente la solicitud se pasa a guardar
                    //los adjuntos de la solicitud.
                    if (anexo3.getErrores().isEmpty()) {
                        for (AuSolicitudAdjunto adjunto : bean.getObjeto().getAuSolicitudAdjuntosList()) {
                            adjunto.setAuAnexo3(anexo3);
                            bean.auditoriaGuardar(adjunto);
                            guardarAdjunto(bean, adjunto);
                        }
                    }
                } catch (Exception ex) {
                    if (anexo3.getErrores() == null) {
                        anexo3.setErrores(new ArrayList<>());
                    }
                    anexo3.getErrores().add(ex.toString());
                }

                if (!anexo3.getErrores().isEmpty()) {
                    bean.addErrores(anexo3.getErrores());
                } else {
                    bean.setObjeto(anexo3);
                    guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                    evaluarEstadoSolicitud(bean);
                    bean.addMensaje("La solicitud " + anexo3.getId() + " fue creada exitosamente");
                }
            }
        } catch (Exception e) {
            bean.addError("Ha ocurrido un inconveniente, no fue posible crear la solicitud, intentelo nuevamente.");
        }
    }

    private void editar(AuSolicitudBean bean) {
        try {
            bean.setObjeto(getAuAnexo3Remoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getVersion()) {
                bean.getObjeto().setHabilitarCampoDireccionAlternativa(false);
                bean.getObjeto().setHabilitarCampoModalidadTecnologia(false);
                bean.getObjeto().setHabilitarCampoFinalidadTecnologia(false);
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_CAUSA_MOTIVA_ATENCION);
                bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_ATENCION_SOLICITADA);
                bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_GRUPO_SERVICIOS);
                completarMaestroVersion2335(bean);
            } else {
                bean.getObjeto().setHabilitarCampoDireccionAlternativa(true);
                bean.getObjeto().setHabilitarCampoModalidadTecnologia(true);
                bean.getObjeto().setHabilitarCampoFinalidadTecnologia(true);
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_ORIGEN_ATENCION);
                bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_SERVICIO_SOLICITADO);
                bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_UBICACION_PACIENTE);
                completarMaestro(bean);
            }
            bean.getObjeto().setAucSugeridolList(getPeAfiliadoSugeridoRemoto().consultarSugeridosAfiliado(bean.getObjeto().getAsegAfiliadoId().getId()));
            int idAfiliado = bean.getObjeto().getAsegAfiliadoId().getId();
            //Buscar programas especiales asociados al afiliado
            if (idAfiliado > 0) {
                bean.setListaProgramaEspecial(getAuAnexo3Remoto().consultarProgramasAfiliado(idAfiliado));
                if (bean.getListaProgramaEspecial() != null && !bean.getListaProgramaEspecial().isEmpty()) {
                    HashMap hashPrograma = new HashMap();
                    for (PePrograma programa : bean.getListaProgramaEspecial()) {
                        hashPrograma.put(programa.getId(), programa);
                    }
                    bean.setHashProgramaEspecial(hashPrograma);
                }
            }
            bean.setListaItemsBorrar(new ArrayList());
            bean.setListaDiagnosticosBorrar(new ArrayList());
            contactosAfiliado(bean);
            buscarProfesional(bean);
            if (bean.getObjeto().getEstadoProcesoActual() == AuAnexo3.ESTADO_PROCESO_ACTUAL_LIBRE) {
                bean.getObjeto().setEstadoProcesoActual(AuAnexo3.ESTADO_PROCESO_ACTUAL_EDICION);
                getAuAnexo3Remoto().actualizarEstadoProcesoActual(bean.getObjeto());
            } else if (bean.getObjeto().getEstadoProcesoActual() == AuAnexo3.ESTADO_PROCESO_ACTUAL_AUDITORIA) {
                bean.addError("No se puede editar porque se encuentra en auditoría.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @SuppressWarnings("UnusedAssignment")
    private void modificar(AuSolicitudBean bean) {
        try {
            if (bean.getObjeto().getObjetoEspecialidad() == null) {
                bean.addError("Se debe seleccionar la especialidad del profesional");
            }
            if (bean.getObjeto().getMaServicioHabilitadoId() == null) {
                bean.addError("Debe indicar el servicio habilitado");
            }
            if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty()) {
                bean.addError("Debe agregar al menos un ítem para modificar la solicitud");
            }
            //ivanegaa validar item modificado en otra pestaña
            AuAnexo3 solicitudDB = getAuAnexo3Remoto().consultar(bean.getObjeto().getId());
            if (bean.validarModificar(solicitudDB)) {
                bean.addError("La solicitud fue modificada, por favor refrescar la pagina.");
            }
            if (solicitudDB.getEstadoProcesoActual() == AuAnexo3.ESTADO_PROCESO_ACTUAL_AUDITORIA) {
                bean.addError("No se puede editar porque se encuentra en auditoría.");
            }
            validarItemsFechaOrdenMedica(bean);
            if (!bean.isError()) {
                completarMaestroHistoricos(bean);
                AuAnexo3 solicitud = bean.getObjeto();
                bean.auditoriaModificar(solicitud);
                //Borrar diagnosticos
                bean.getListaDiagnosticosBorrar().forEach(diagnostico -> {
                    borrarDiagnostico(diagnostico.getId(), bean);
                });
                //Guardar diagnosticos
                for (AuAnexo3Diagnostico diagnostico : solicitud.getAuAnexo3DiagnosticosList()) {
                    diagnostico.setAuAnexos3Id(solicitud);
                    if (diagnostico.getId() == null) {
                        bean.auditoriaGuardar(diagnostico);
                        getAuAnexo3DiagnosticoRemoto().insertar(diagnostico);
                    } else {
                        bean.auditoriaModificar(diagnostico);
                        getAuAnexo3DiagnosticoRemoto().actualizar(diagnostico);
                    }
                }

                List<Integer> grupos_existentes = new ArrayList();
                //items para borrar
                bean.getListaItemsBorrar().forEach(auAnexo3Item -> {
                    borrarTecnologia(auAnexo3Item.getId(), bean);
                });
                //Guardar ítems                              
                for (AuAnexo3Item item : solicitud.getAuAnexo3ItemsList()) {
                    if (item.getId() == null) {
                        item.setAuAnexo3Id(solicitud);
                        completarItems(bean);
                        if (bean.getParamConsulta4() == null) {
                            bean.setParamConsulta4(new ParamConsulta());
                        }
                        bean.getParamConsulta4().setFiltros(new HashMap());
                        bean.getParamConsulta4().setParametroConsulta1(item.getTipoTecnologia());
                        bean.getParamConsulta4().setParametroConsulta2("" + item.getMaTecnologiaId());
                        int contratos = getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta4());
                        if (contratos == 0) {
                            item.setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                        } else {
                            item.setEstado(AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA);
                        }
                        List<AuGrupo> lista_grupos = getAuGrupoRemoto().consultarPorTecnologia(item.getMaTecnologiaId());
                        if (!lista_grupos.isEmpty()) {
                            Integer posicion_grupo_existente = 0;
                            if (!grupos_existentes.isEmpty()) {
                                if (lista_grupos.size() > 1) {
                                    for (int index = 0; index < lista_grupos.size(); index++) {
                                        for (int i = 0; i < grupos_existentes.size(); i++) {
                                            if (Objects.equals(lista_grupos.get(index).getId(), grupos_existentes.get(i))) {
                                                posicion_grupo_existente = index;
                                            }
                                        }
                                    }
                                }
                            }
                            item.setAuGrupoId(lista_grupos.get(posicion_grupo_existente));
                        }

                        bean.auditoriaGuardar(item);
                        if (item.isPosfechado()) {
                            int contador = 0;
                            for (AuRango rango : item.getListaRangos()) {
                                if (contador == 0) {
                                    item.setPosfechadoPrincipal(true);
                                } else {
                                    item.setPosfechadoPrincipal(false);
                                }
                                item.setPosfechado(true);
                                item.setCantidadSolicitada(rango.getCantidad());
                                item.setFechaPosfechado(rango.getFechaLiberacion());
                                int id = getAuAnexo3ItemRemoto().insertar(item);
                                guardarItemBitacora(new AuAnexo3Item(id), bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                                contador++;
                            }
                        } else {
                            int id = getAuAnexo3ItemRemoto().insertar(item);
                            guardarItemBitacora(new AuAnexo3Item(id), bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                        }
                    } else {
                        if (item.getAuGrupoId() != null && !grupos_existentes.contains(item.getAuGrupoId().getId())) {
                            grupos_existentes.add(item.getAuGrupoId().getId());
                        }
                        AuAnexo3Item itemBd = getAuAnexo3ItemRemoto().consultar(item.getId());
                        if(!itemBd.isPosfechado()){
                            item.setMaeAmbitoId(solicitud.getMaeAmbitoAtencionId());
                            item.setMaeAmbitoCodigo(solicitud.getMaeAmbitoAtencionCodigo());
                            item.setMaeAmbitoValor(solicitud.getMaeAmbitoAtencionValor());
                            bean.auditoriaModificar(item);
                            getAuAnexo3ItemRemoto().actualizar(item);
                            //Posfechado
                            if (item.isPosfechado()) {
                                if (item.getListaRangos() != null) {
                                    int contador = 0;
                                    int idPrincipal = 0;
                                    for (AuRango rango : item.getListaRangos()) {
                                        //item.setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                                        if (contador == 0) {
                                            item.setCantidadSolicitada(rango.getCantidad());
                                            item.setPosfechadoPrincipal(true);
                                            item.setPosfechado(true);
                                            item.setFechaPosfechado(rango.getFechaLiberacion());
                                            idPrincipal = item.getId();
                                            getAuAnexo3ItemRemoto().actualizarPosfechados(item);
                                        } else {
                                            item.setPosfechadoPrincipal(false);
                                            item.setPosfechadoPrincipal(false);
                                            item.setPosfechado(true);
                                            item.setCantidadSolicitada(rango.getCantidad());
                                            item.setFechaPosfechado(rango.getFechaLiberacion());
                                            item.setId(null);
                                            item.setId(getAuAnexo3ItemRemoto().insertar(item));
                                            guardarItemBitacora(item, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                                        }
                                        contador++;
                                    }
                                    item.setId(idPrincipal);//posfechado principal
                                }
                            }
                        }
                    }
                }
                //Guardar adjuntos
                for (AuSolicitudAdjunto adjunto : solicitud.getAuSolicitudAdjuntosList()) {
                    adjunto.setAuAnexo3(solicitud);
                    if (adjunto.getId() == null) {
                        bean.auditoriaGuardar(adjunto);
                        guardarAdjunto(bean, adjunto);
                    }
                }
                //Guardar tutelas
                guardarTutelas(bean);
                //Borrar tutelas cambio afiliado
                if (bean.getListTutelasBorrar() != null) {
                    for (AuAnexo3Tutela tutela : bean.getListTutelasBorrar()) {
                        if (tutela.getId() != null) {
                            getAuAnexo3TutelaRemoto().eliminar(tutela.getId());
                        }
                    }
                }
                //Guardar el afiliado
                int idAfiliado = 0;
                AuAnexo3Afiliado afiliado = new AuAnexo3Afiliado();
                for (AuAnexo3Afiliado afil : solicitud.getAuAnexo3AfiliadosList()) {
                    if (afil.getMaeTipoDocumentoId() == solicitud.getAsegAfiliadoId().getMaeTipoDocumento()
                            && afil.getNumeroIdentificacion().equals(solicitud.getAsegAfiliadoId().getNumeroDocumento())) {
                        idAfiliado = afil.getId();
                        afiliado = afil;
                    }
                }
                if (idAfiliado == 0) {
                    afiliado = casteoAuAnexo3Afiliado(solicitud.getAsegAfiliadoId());
                    Maestro tipoDoc = bean.obtenerMaestroTipoDocumento(afiliado.getMaeTipoDocumentoId());
                    if (tipoDoc != null) {
                        afiliado.setAuAnexo3Id(solicitud);
                        afiliado.setMaeTipoDocumentoCodigo(tipoDoc.getValor());
                        afiliado.setMaeTipoDocumentoValor(tipoDoc.getNombre());
                        bean.auditoriaGuardar(afiliado);
                        getAuAnexo3AfiliadoRemoto().insertar(afiliado);
                    }
                } else {
                    afiliado.setId(idAfiliado);
                    bean.auditoriaModificar(afiliado);
                    getAuAnexo3AfiliadoRemoto().actualizar(afiliado);
                }
                bean.setListaItemsBorrar(new ArrayList());
                bean.setListaDiagnosticosBorrar(new ArrayList());

                CntProfesional profesional = bean.getObjeto().getCntProfesionaleId();
                
                CntProfesional profesionalRegistrado = null;
                if (profesional.getId() == null) {
                    //Validar existencia del profesional
                    profesionalRegistrado = getProfesionalRemoto().consultarNumDocumento(profesional.getMaeTipoCodumentoId(), profesional.getDocumento());
                    if (profesionalRegistrado == null) {
                        Maestro tipoDocProfesional = bean.getHashTipoDocumentoProfesional().get(profesional.getMaeTipoCodumentoId());
                        profesional.setMaeTipoDocumentoCodigo(tipoDocProfesional.getValor());
                        profesional.setMaeTipoDocumentoValor(tipoDocProfesional.getNombre());
                        if (profesional.getRegistroMedico() == null) {
                            profesional.setRegistroMedico("Sin registro");
                        }
                        profesional.setUsuarioCrea(bean.getObjeto().getUsuarioCrea());
                        profesional.setTerminalCrea(bean.getObjeto().getTerminalCrea());
                        profesional.setFechaHoraCrea(bean.getObjeto().getFechaHoraCrea());
                        int idProfesional = getProfesionalRemoto().insertar(profesional);
                        profesional.setId(idProfesional);
                        //Crear el profesional en el prestador
                        if (bean.getObjeto().getObjetoEspecialidad() != null) {
                            CntProfesionalPrestador especialidad = new CntProfesionalPrestador();
                            especialidad.setCntProfesionalesId(profesional);
                            especialidad.setCntPrestador(bean.getObjeto().getCntPrestadorSedeId().getCntPrestador());
                            especialidad.setMaEspecialidadId(bean.getObjeto().getObjetoEspecialidad().getId());
                            especialidad.setMaEspecialidadCodigo(bean.getObjeto().getObjetoEspecialidad().getCodigo());
                            especialidad.setMaEspecialidadValor(bean.getObjeto().getObjetoEspecialidad().getNombre());
                            especialidad.setActivo(true);
                            especialidad.setUsuarioCrea(bean.getObjeto().getUsuarioCrea());
                            especialidad.setTerminalCrea(bean.getObjeto().getTerminalCrea());
                            especialidad.setFechaHoraCrea(bean.getObjeto().getFechaHoraCrea());
                            especialidad.setId(getCntPrestadorProfesionalRemoto().insertar(especialidad));
                        }
                    }
                }
                String str = "";
                str += (profesional.getPrimerNombre() == null) ? "" : profesional.getPrimerNombre();
                str += (profesional.getSegundoNombre() == null) ? "" : " " + profesional.getSegundoNombre();
                bean.getObjeto().setNombreProfesional(str);
                getAuAnexo3Remoto().actualizar(solicitud);
//                evaluarEstadoSolicitud(bean);
                bean.addMensaje("La solicitud " + solicitud.getId() + " fue actualizada exitosamente");
                bean.getObjeto().setEstadoProcesoActual(AuAnexo3.ESTADO_PROCESO_ACTUAL_LIBRE);
                getAuAnexo3Remoto().actualizarEstadoProcesoActual(bean.getObjeto());
                guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_EDITADO, bean.getObjeto().getEstadoStr());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(AuSolicitudBean bean) {
        try {
            bean.setObjeto(getAuAnexo3Remoto().consultar(bean.getObjeto().getId()));
            for (AuAnexo3Item item : bean.getObjeto().getAuAnexo3ItemsList()) {
                if (item.isPosfechado()) {
                    List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                    listaItems = filtroPosfechado(listaItems);
                    if (listaItems.isEmpty()) {
                        getAuAnexo3ItemRemoto().eliminar(item.getId());
                    } else {
                        for (AuAnexo3Item ítems : listaItems) {
                            getAuAnexo3ItemRemoto().eliminar(ítems.getId());
                        }
                    }
                } else {
                    getAuAnexo3ItemRemoto().eliminar(item.getId());
                }

            }
            for (AuAnexo3Diagnostico diagnostico : bean.getObjeto().getAuAnexo3DiagnosticosList()) {
                getAuAnexo3DiagnosticoRemoto().eliminar(diagnostico.getId());
            }
            for (AuSolicitudAdjunto adjunto : bean.getObjeto().getAuSolicitudAdjuntosList()) {
                getAuSolicitudAdjuntoRemoto().eliminar(adjunto.getId());
            }
            for (AuAnexo3Afiliado afiliado : bean.getObjeto().getAuAnexo3AfiliadosList()) {
                getAuAnexo3AfiliadoRemoto().eliminar(afiliado.getId());
            }
            for (AuAnexo3Tutela tutela : bean.getObjeto().getAuAnexo3TutelasList()) {
                getAuAnexo3TutelaRemoto().eliminar(tutela.getId());
            }
            getAuAnexo3Remoto().eliminar(bean.getObjeto().getId());
            bean.addMensaje("Se eliminó la solicitud " + bean.getObjeto().getId() + " exitosamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void auditar(AuSolicitudBean bean) {
        try {
            bean.setObjeto(getAuAnexo3Remoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getVersion()) {
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_CAUSA_MOTIVA_ATENCION);
                bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_ATENCION_SOLICITADA);
                bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_GRUPO_SERVICIOS);
            } else {
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_ORIGEN_ATENCION);
                bean.getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_SERVICIO_SOLICITADO);
                bean.getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_UBICACION_PACIENTE);
            }
            bean.getObjeto().setAucSugeridolList(getPeAfiliadoSugeridoRemoto().consultarSugeridosAfiliado(bean.getObjeto().getAsegAfiliadoId().getId()));
//            bean.getObjeto().getAsegAfiliadoId().setPrimariaPrestadorSede(getPrestadorRemoto().consultarSede(bean.getObjeto().getAsegAfiliadoId().getPrimariaPrestadorSede().getId()));//esta en cast
            buscarProfesional(bean);
            contactosAfiliado(bean);
            consultarProgramaAfiliado(bean);
            bean.getObjeto().setEstadoProcesoActual(AuAnexo3.ESTADO_PROCESO_ACTUAL_AUDITORIA);
            getAuAnexo3Remoto().actualizarEstadoProcesoActual(bean.getObjeto());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @SuppressWarnings("UnusedAssignment")
    private void aprobar(AuSolicitudBean bean) {
        try {
            if (validarAprobacion(bean)) {
                if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                    List<AuAnexo3Item> listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                    listaPosfechado = filtroPosfechado(listaPosfechado);
//                    if (listaPosfechado.size() > 1) {
//                        Date fechaUno = listaPosfechado.get(0).getFechaPosfechado();
//                        Date fechaDos = listaPosfechado.get(1).getFechaPosfechado();
//                        int mili = 86400000;
//                        diasFecha = (int) ((fechaDos.getTime() - fechaUno.getTime()) / mili);
//                    }
                    
                    Date fechaAutorizacion = new Date();
                    int itemsSinAprobar = 0;
                    AuAnexo3Item itemPrincipal = null;
                    for (AuAnexo3Item itemAnexo3 : listaPosfechado) {
                        Date fechaValida = new Date();
//                        if (itemAnexo3.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA) {
                        fechaAutorizacion = itemAnexo3.getFechaPosfechado();
//                        }
                        if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                            fechaValida = bean.getObjeto().getObjetoContratoDetalle().getFechaHoraFin();
                        } else {
                            if (bean.getObjeto().getObjetoCotizacion() != null) {
                                fechaValida = bean.getObjeto().getObjetoCotizacion().getFechaFinVigencia();
                            }
                        }
                        if (fechaValida != null) {
                            if (fechaAutorizacion.after(fechaValida)) {
                                if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                                    bean.addError("No se pudo autorizar " + itemAnexo3.getMaTecnologiaValor() + " debido a que el servicio no cuenta con contrato vigente para la fecha del posfechado de la autorización.");
                                } else {
                                    bean.addError("No se pudo autorizar " + itemAnexo3.getMaTecnologiaValor() + " debido a que la cotización no se encuentra vigente para la fecha del posfechado asignada a la autorización.");
                                }
//                                itemAnexo3.setPosfechado(false);
//                                if (!itemAnexo3.getPosfechadoPrincipal()) {
//                                    Calendar calendar = Calendar.getInstance();
//                                    calendar.setTime(fechaAutorizacion);
//                                    calendar.set(Calendar.HOUR, 0);
//                                    calendar.set(Calendar.MINUTE, 1);
//                                    calendar.set(Calendar.SECOND, 0);
//                                    calendar.add(Calendar.DAY_OF_MONTH, diasFecha);
//                                    fechaAutorizacion = calendar.getTime();
//                                }
//                                itemAnexo3.setFechaPosfechado(fechaAutorizacion);
                                if (itemsSinAprobar == 0) {
                                    itemAnexo3.setPosfechadoPrincipal(true);
                                }
                                getAuAnexo3ItemRemoto().actualizarPosfechado(itemAnexo3);
                                itemsSinAprobar++;
                                continue;
                            }
                        }
                        if (itemAnexo3.getPosfechadoPrincipal()) {
                            aprobarUno(bean, itemAnexo3, fechaAutorizacion);
                            itemPrincipal = itemAnexo3;
                        } else {
//                            Calendar calendar = Calendar.getInstance();
//                            calendar.setTime(fechaAutorizacion);
//                            calendar.set(Calendar.HOUR, 0);
//                            calendar.set(Calendar.MINUTE, 1);
//                            calendar.set(Calendar.SECOND, 0);
//                            calendar.add(Calendar.DAY_OF_MONTH, diasFecha);
//                            fechaAutorizacion = calendar.getTime();
                            aprobarUno(bean, itemAnexo3, fechaAutorizacion);
                        }
                    }
                    if (itemsSinAprobar > 0) {
                        if (itemPrincipal != null) {
                            itemPrincipal.setPosfechadoPrincipal(false);
                            getAuAnexo3ItemRemoto().actualizarPosfechado(itemPrincipal);
                        }
                    } else {//por si un item posfechado no se autoriza
                        if (!bean.getErrores().isEmpty()) {
                            if (itemPrincipal != null) {
                                listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                                listaPosfechado = filtroPosfechado(listaPosfechado);
                                if (!listaPosfechado.isEmpty()) {
                                    itemPrincipal.setPosfechadoPrincipal(false);
                                    getAuAnexo3ItemRemoto().actualizarPosfechado(itemPrincipal);

                                    AuAnexo3Item item = listaPosfechado.get(0);
                                    item.setPosfechadoPrincipal(true);
                                    getAuAnexo3ItemRemoto().actualizarPosfechado(item);
                                }
                            }
                        }
                    }
                } else {
                    Date fechaAutorizacion = new Date();
                    if (bean.getObjeto().getObjetoTecnologia().getFechaPosfechado() != null) {
                        fechaAutorizacion = bean.getObjeto().getObjetoTecnologia().getFechaPosfechado();
                    }
                    aprobarUno(bean, bean.getObjeto().getObjetoTecnologia(), fechaAutorizacion);
                }
                evaluarEstadoSolicitud(bean);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
            bean.addError("Fallo la aprobacion");
            evaluarEstadoSolicitud(bean);
        }
    }
    
    @SuppressWarnings("UnusedAssignment")
    private void aprobarUno(AuSolicitudBean bean, AuAnexo3Item itemAnexo3, Date fechaAutorizacion) {
        try {
            boolean fallo = false;
            AuAnexo4 anexo4 = castearAnexo3Anexo4(bean);
            anexo4.setPosfechada(itemAnexo3.isPosfechado());
            //2023-09-27 jyperez pago anticipado
            if (itemAnexo3.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO) {
                anexo4.setPagoAnticipado(true);
            } else {
                anexo4.setPagoAnticipado(false);
            }
            if (itemAnexo3.getCapitaPGP() != null && itemAnexo3.getCapitaPGP().equals(AuConstantes.TEXTO_SI)) {
                anexo4.setContratoAnticipado(true);
                if (itemAnexo3.getContratosCapita() != null) {
                    bean.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion() + " | " + itemAnexo3.getContratosCapita());
                }
                if (itemAnexo3.getProgramasPGP() != null) {
                    bean.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion() + " | " + itemAnexo3.getProgramasPGP());
                }
                if (bean.getContratoAnticipadoObservacion().length() > 510) {
                    bean.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion().substring(0, 510));
                }
                anexo4.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion());
            }
            String idAfiliado = bean.getObjeto().getAsegAfiliadoId().getId().toString();
            String tipoTecnologia = "" + itemAnexo3.getTipoTecnologia();
            String idTecnologia = "" + itemAnexo3.getMaTecnologiaId();
            String valorTecnologia = "0";
            String mensajeAlerta = "";
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaAutorizacion);
            fechaAutorizacion = calendar.getTime();
            anexo4.setFechaAutorizacion(fechaAutorizacion);
            anexo4.setFechaAutorizacionImpresion(fechaAutorizacion);
            anexo4.setFechaInicio(fechaAutorizacion);
            BigDecimal ejecucionContratoAutorizado = new BigDecimal(0);
            BigDecimal ejecucionContratoPrestado = new BigDecimal(0);
            BigDecimal ejecucionTotalContrato = new BigDecimal(0);
            int dias = 180;
            if (itemAnexo3.getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO
                    || itemAnexo3.getTipoTecnologia() == AuConstantes.ID_AGRUPADOR_MEDICAMENTO) {
                dias = 30;
            } else if (itemAnexo3.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA) {
//                if (AuConstantes.CODIGO_TCNOLOGIAS_ESPECIFICAS_30_VIGENCIAS.contains(itemAnexo3.getMaTecnologiaCodigo())) {
//                    dias = 30;
//                }
                MaTecnologia tecnologia = getMaTecnologiaRemoto().consultar(itemAnexo3.getMaTecnologiaId());
                if (tecnologia.getVigenciaAutorizacion() != null) {
                    dias = tecnologia.getVigenciaAutorizacion();
                }
            }
            calendar.add(Calendar.DAY_OF_MONTH, dias);
            anexo4.setFechaFin(calendar.getTime());
            anexo4.setDiasVigencia(dias);
            anexo4.setCantidadEntregas(itemAnexo3.getCantidadSolicitada());
            BigDecimal cantidad = new BigDecimal(itemAnexo3.getCantidadSolicitada());
            if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                valorTecnologia = bean.getObjeto().getObjetoContratoDetalle().getValorContratado().multiply(cantidad).toString();
                anexo4.setCntContratoId(bean.getObjeto().getObjetoContratoDetalle().getCntContrato());
                ejecucionContratoAutorizado = (bean.getObjeto().getObjetoContratoDetalle().getCntContrato().getEjecucionContratoAutorizado() == null) ? bean.getObjeto().getObjetoContratoDetalle().getValorContratado() 
                    : bean.getObjeto().getObjetoContratoDetalle().getCntContrato().getEjecucionContratoAutorizado().add(bean.getObjeto().getObjetoContratoDetalle().getValorContratado());
                bean.getObjeto().getObjetoContratoDetalle().getCntContrato().setEjecucionContratoAutorizado(ejecucionContratoAutorizado);
            } else {
                if (bean.getObjeto().getObjetoCotizacion() != null) {
                    valorTecnologia = bean.getObjeto().getObjetoCotizacion().getValorTecnologia().multiply(cantidad).toString();
                    anexo4.setValorCotizacion(bean.getObjeto().getObjetoCotizacion().getValorTecnologia());
                }
            }
            String numTutela = "0";
            if (!bean.getObjeto().getAuAnexo3TutelasList().isEmpty()) {
                for (AuAnexo3Tutela tutela : bean.getObjeto().getAuAnexo3TutelasList()) {
                    if (tutela.isExoneracionCopago()) {
                        numTutela = "" + tutela.getNumeroTutela();
                        anexo4.setExcentoCopago(true);
                        anexo4.setAplicaTutela(true);
                    }
                }
            }
            String programaEspecial = "0";
            if (bean.getObjeto().getPeProgramaEspecialId() != null) {
                try {
                    PePrograma programa = getPeProgramaRemoto().consultar(bean.getObjeto().getPeProgramaEspecialId());
                    if (programa != null && programa.isExoneradoCopago()) {
                        programaEspecial = bean.getObjeto().getPeProgramaEspecialId().toString();
                        anexo4.setAplicaAltocosto(true);
                    }
                } catch (Exception e) {
                    bean.addError("Hubo un fallo consultando el programa especial, favor notificar al administrador");
                    fallo = true;
                }
            }
            if (!fallo) {
                try {
                    ValidaRespuestaCopagoDTO copago = getValidadorRemoto().validarCalCopagoModeradora(idAfiliado, tipoTecnologia, idTecnologia, valorTecnologia, bean.getObjeto().getMaeAmbitoAtencionCodigo(), numTutela, programaEspecial);
                    anexo4.setAplicaCopago((copago.getCodigo4() == 1));
                    anexo4.setAplicaCuotaModeradora((copago.getCodigo5() == 1));
                    anexo4.setAplicaCuotaRecuperacion((copago.getCodigo6() == 1));
                    anexo4.setExcentoCopago(copago.getCodigo7() == 1);
                    anexo4.setMotivoExentoCobro(copago.getMotivoExentoCobro());
                    anexo4.setValorCopago(new BigDecimal(copago.getCodigo()));
                    anexo4.setValorCuotaModeradora(new BigDecimal(copago.getValor()));
                    anexo4.setObservacion(bean.getObjeto().getObservacionAuditar());
                    anexo4.setPorcentajeRecuperacion(new BigDecimal(copago.getCodigo3()));
                } catch (Exception e) {
                    bean.addError("Hubo un fallo calculando el copago, favor comunicarse con el adminsitrador");
                    fallo = true;
                }
                AuTopeAfiliado auTopeAfiliado = getTopeAfiliadoRemoto().consultarAfiliadoActivo(anexo4.getAsegAfiliadoId().getId());
                if (auTopeAfiliado != null) {
                    anexo4.setTopeAplicado(Boolean.TRUE);
                }
                String valorRegimen = bean.getObjeto().getAsegAfiliadoId().getMaeRegimenCodigo();
                Maestro regimen = getMaestroRemoto().consultarPorValorTipo(valorRegimen, "08");
                if (regimen != null) {
                    anexo4.setMaeRegimenId(regimen.getId());
                    anexo4.setMaeRegimenCodigo(regimen.getValor());
                    anexo4.setMaeRegimenValor(regimen.getNombre());
                }

                bean.auditoriaGuardar(anexo4);
                if (bean.isAutomatica()) {
                    anexo4.setUsuarioCrea("Sistema");
                    anexo4.setNombreAutoriza("Sistema");
                    anexo4.setEstado(AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO);
                    anexo4.setEstadoJustificacion("Aprobado Automático");
                    anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_AUTOMATICA);
                    mensajeAlerta = "autorización";
                } else if (bean.getObjeto().getObjetoContratoDetalle() != null
                        && bean.getObjeto().getObjetoContratoDetalle().isPreautorizacion()) {
                    anexo4.setNombreAutoriza(anexo4.getUsuarioCrea());
                    anexo4.setEstado(AuAnexo4.ESTADO_PREAUTORIZADO);
                    anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_MANUAL);
                    mensajeAlerta = "Preautorización";
                    //2023-09-27 jyperez pago anticipado
                } else if (anexo4.isPagoAnticipado()) {
                    anexo4.setNombreAutoriza(anexo4.getUsuarioCrea());
                    anexo4.setEstado(AuAnexo4.ESTADO_AUTORIZADO_PAGO_ANTICIPADO);
                    anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_COTIZACION);
                    mensajeAlerta = "autorización";
                } else {
                    anexo4.setNombreAutoriza(anexo4.getUsuarioCrea());
                    if (itemAnexo3.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                            || itemAnexo3.getEstado() == AuAnexo3Item.ESTADO_SIN_COTIZACION) {
                        //AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(itemAnexo3.getId());
                        //if (itemCotizacion != null) {
                        anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_COTIZACION);
                        //}
                    } else {
                        anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_MANUAL);
                    }
                    mensajeAlerta = "autorización";
                }
                String ruta = PropApl.getInstance().get(PropApl.AU_A4_ANEXOS);
                //String ruta = "D:\\SERVICIOS\\sistemsavia\\autorizacion\\A4\\anexos\\";
                anexo4.setRuta(ruta);
            }
            if (!fallo) {
                try {
                    int idAnexo4 = getAuAnexo4Remoto().insertar(anexo4);
                    if(bean.getObjeto().getObjetoContratoDetalle() != null){
                        getCntContratoRemoto().actualizarEjecucionContratoAutorizado(bean.getObjeto().getObjetoContratoDetalle().getCntContrato());
                    }
                    //Se consulta nuevamente para recuperar informacion generada por trigger en base de datos al guardar el registro
                    anexo4 = getAuAnexo4Remoto().consultar(idAnexo4);
                    String nombre = AuConstantes.nombreReporteAnexo4(anexo4);
                    anexo4.setArchivo(nombre);
                    anexo4.setArchivoNombre(AuConstantes.nombreArchivoReporteAnexo4(anexo4));
                    getAuAnexo4Remoto().actualizar(anexo4);
                    anexo4 = getAuAnexo4Remoto().consultar(anexo4.getId());
                } catch (Exception e) {
                    bean.addError("Hubo un fallo al guardar la autorizacion, favor notificar al adminsitrador");
                    fallo = true;
                }
            }
            if (!fallo && anexo4.getId() != null) {
                try {
                    AuAnexo4Item item = castearItem3Item4(itemAnexo3);
                    item.setAuAnexo4Id(anexo4);
                    BigDecimal multiplicando = new BigDecimal(item.getCantidadAutorizada());
                    if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                        item.setCostoServicio(bean.getObjeto().getObjetoContratoDetalle().getValorContratado().multiply(multiplicando));
                        item.setCntContratoDetalle(bean.getObjeto().getObjetoContratoDetalle());
                        if (itemAnexo3.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
                            item.setMaMedicamentoId(bean.getObjeto().getObjetoContratoDetalle().getMaTecnologiaId());
                            item.setMaMedicamentoCodigo(bean.getObjeto().getObjetoContratoDetalle().getMaTecnologiaCodigo());
                            item.setMaMedicamentoValor(bean.getObjeto().getObjetoContratoDetalle().getMaTecnologiaValor());
                        }
                    } else {
                        if (bean.getObjeto().getObjetoCotizacion() != null) {
                            item.setCostoServicio(bean.getObjeto().getObjetoCotizacion().getValorTecnologia().multiply(multiplicando));
                            item.setAuCotizacion(bean.getObjeto().getObjetoCotizacion());
                            if (itemAnexo3.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
                                item.setMaMedicamentoId(bean.getObjeto().getObjetoCotizacion().getMaMedicamentoId());
                                item.setMaMedicamentoCodigo(bean.getObjeto().getObjetoCotizacion().getMaMedicamentoCodigo());
                                item.setMaMedicamentoValor(bean.getObjeto().getObjetoCotizacion().getMaMedicamentoValor());
                            }
                        }
                    }
                    bean.auditoriaGuardar(item);
                    int idItem = getAuAnexo4ItemRemoto().insertar(item);
                    if (idItem > 0) {
                        anexo4.setAuAnexo4ItemsList(new ArrayList());
                        anexo4.getAuAnexo4ItemsList().add(item);
                        if (bean.isAutomatica()) {
                            itemAnexo3.setEstado(AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO);
                        } else if (anexo4.getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
                            itemAnexo3.setEstado(AuAnexo3Item.ESTADO_PREAUTORIZADO);
                        } else {
                            itemAnexo3.setEstado(AuAnexo3Item.ESTADO_APROBADO_AUDITORIA);
                        }
                        bean.auditoriaModificar(itemAnexo3);
                        getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                        //enviar mensaje SMS
                        if (bean.getObjeto().getAsegAfiliadoId().getAutorizaEnvioSms() != null
                                && bean.getObjeto().getAsegAfiliadoId().getAutorizaEnvioSms()
                                && anexo4.getEstado() == AuAnexo4.ESTADO_AUTORIZADA
                                && anexo4.getAfiliadoCelular() != null) {
                            Maestro maeAmbito = bean.obtenerMaestroAmbito(anexo4.getMaeAmbitoAtencionId());
                            if (maeAmbito.contieneAccion(MaestroAccion.GN_AMBITO_APLICA_MENSAJE)) {
                                String sms = AuConstantes.mensajeAutorizacionSMS(item);
                                Mensaje mensaje = new Mensaje();
                                mensaje.guardar(GnSmsEnvio.ORIGEN_AUTORIZACIONES, anexo4.getAfiliadoCelular(), sms);
                            }
                        }
                    }

                } catch (Exception e) {
                    bean.addError("Hubo un error creando el ítem, favor comunicarse con el administrador");
                    fallo = true;
                }
            }
            if (!fallo) {
                try {
                    CsCopagoModeradoraHistorico historico = castearCopagoModeradora(anexo4);
                    bean.auditoriaGuardar(historico);
                    historico.setId(getCsCopagoModeradoraHistoricoRemoto().insertar(historico));
                } catch (Exception e) {
                    bean.addError("Hubo un error creando el historico del copago, favor contactar con el administrador");
                    fallo = true;
                }
            }
            int idHistorico = 0;
            if (!fallo) {
                try {
                    AuAnexo4Historico historico = new AuAnexo4Historico();
                    historico.setAuAnexos4Id(anexo4);
                    historico.setEstado(anexo4.getEstado());
                    Maestro mae_causa;
                    if (anexo4.getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
                        mae_causa = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_PREAUTORIZADO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A3);
                    } else {
                        mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUDITORIA);
                    }
                    historico.setMaeCausaId(mae_causa.getId());
                    historico.setMaeCausaCodigo(mae_causa.getValor());
                    historico.setMaeCausaValor(mae_causa.getNombre());
                    historico.setObservacionAutorizacion(anexo4.getEstadoStr());
                    bean.auditoriaGuardar(historico);
                    if (bean.isAutomatica()) {
                        mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUTOMATICO);
                        historico.setUsuarioCrea("Sistema");
                        historico.setMaeCausaId(mae_causa.getId());
                        historico.setMaeCausaCodigo(mae_causa.getValor());
                        historico.setMaeCausaValor(mae_causa.getNombre());

                    }
                    idHistorico = getAuAnexo4HistoricoRemoto().insertar(historico);
                } catch (Exception e) {
                    bean.addError("Hubo un fallo creando el historico, favor comunicarse con el administrador");
                    fallo = true;
                }
            } else {
                for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                    if (item.getId() != null) {
                        getAuAnexo4ItemRemoto().eliminar(item.getId());
                    }
                }
                if (idHistorico > 0) {
                    getAuAnexo4HistoricoRemoto().eliminar(idHistorico);
                }
                if (anexo4.getId() != null) {
                    getAuAnexo4Remoto().eliminar(anexo4.getId());
                }
            }
            if (!fallo) {
                try {
                    //ya no se registra, ahora solo histiricos
//                    AuAnexo4Estado anexo4Estado = new AuAnexo4Estado();
//                    anexo4Estado.setAuAnexo4Id(anexo4);
//                    anexo4Estado.setMaeEstadoId(0);
//                    anexo4Estado.setMaeEstadoCodigo(String.valueOf(anexo4.getEstado()));
//                    anexo4Estado.setMaeEstadoValor(anexo4.getEstadoStr());
//                    bean.auditoriaGuardar(anexo4Estado);
//                    if (bean.isAutomatica()) {
//                        anexo4Estado.setUsuarioCrea("Sistema");
//                    }
//                    int idAnexo4Estado = getAuAnexo4EstadoRemoto().insertar(anexo4Estado);
//                    anexo4Estado.setId(idAnexo4Estado);
                } catch (Exception e) {
                    bean.addError("Hubo un error guardando el estado, favor comunicarse con el administrador");
                    fallo = true;
                }
            }
            if (!fallo) {
                try {
                    if (anexo4.getEstado() != AuAnexo4.ESTADO_PREAUTORIZADO) {
                        AuSeguimiento seguimiento = getAuSeguimientoRemoto().seguimientoPorAnexo3Item(itemAnexo3.getId());
                        if (seguimiento != null) {
                            Maestro maestroEstado = bean.getHashEstadosSeguimiento().get(AuSeguimiento.ESTADO_AUTORIZADO);
                            seguimiento.setMaeEstadoSeguimientoId(maestroEstado.getId());
                            seguimiento.setMaeEstadoSeguimientoCodigo(maestroEstado.getValor());
                            seguimiento.setMaeEstadoSeguimientoValor(maestroEstado.getNombre());
                            seguimiento.setEstadoTecnologia(itemAnexo3.getEstado());
                            seguimiento.setAuAnexos4Id(anexo4);
                            bean.auditoriaModificar(seguimiento);

                            //el usuario tiene la posibilidad de cambiar el prestador al seleccionar contrato
                            if (bean.getObjeto().getObjetoContratoDetalle() != null && seguimiento.getCntPrestadorSedeAsignadoId() != null) {
                                if (!Objects.equals(bean.getObjeto().getObjetoContratoDetalle().getCntContratoSede().getCntPrestadorSede().getId(), seguimiento.getCntPrestadorSedeAsignadoId().getId())) {
                                    seguimiento.setCntPrestadorSedeAsignadoId(bean.getObjeto().getObjetoContratoDetalle().getCntContratoSede().getCntPrestadorSede());
                                    Empresa empresa = getEmpresaRemoto().consultarPorPrestador(
                                            seguimiento.getCntPrestadorSedeAsignadoId().getCntPrestador().getId()
                                    );
                                    seguimiento.setGnEmpresasId(empresa);
                                    guardarPrestadorSedeSeguimiento(bean, seguimiento);
                                    guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_GESTION_NOTA, "Reasignación automatica de prestador aprobado " + seguimiento.getCntPrestadorSedeAsignadoId().getNombreSede());
                                }
                            }
                            getAuSeguimientoRemoto().actualizarEstado(seguimiento);
                            guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_AUTORIZADO, bean.getObjeto().getObservacionAuditar());
                        }
                    }

                } catch (Exception e) {
                    bean.addError("Hubo un error actualizando el seguimiento, favor comunicarse con el administrador");
                    fallo = true;
                }
            }
            if (!fallo) {
                bean.setObjetoAnexo4(anexo4);
                if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty()) {
                    bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_ANULADO);
                    getAuAnexo3Remoto().actualizar(bean.getObjeto());
                } else {
                    evaluarEstadoSolicitud(bean);
                }
                guardarReporteAnexo4(bean);
                bean.addMensaje("Se genera la " + mensajeAlerta + " numero " + anexo4.getId() + " de manera exitosa");
            } else {
                for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                    if (item.getId() != null) {
                        getAuAnexo4ItemRemoto().eliminar(item.getId());
                    }
                }
                if (idHistorico > 0) {
                    getAuAnexo4HistoricoRemoto().eliminar(idHistorico);
                }
                if (anexo4.getId() != null) {
                    getAuAnexo4Remoto().eliminar(anexo4.getId());
                }
                bean.addError("No se genero la autorizacion debido a un fallo");
            }
        } catch (Exception e) {
            bean.addError("Fallo la aprobación, favor contactar al administrador.");
            evaluarEstadoSolicitud(bean);
        }
    }
    
    @SuppressWarnings("UnusedAssignment")
    private void gestionarAprobarTodo(AuSolicitudBean bean) {
        try {
            boolean agregar = false;
            if (bean.isAutomatica()) {
                for (AuAnexo4Automatico automatico : bean.getListaAutomaticos()) {
                    if (!automatico.getItem().isPosfechado()) {
                        agregar = true;
                    }
                }
            } else {
                for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                    if (!item.isPosfechado()) {
                        agregar = true;
                    }
                }
            }

            if (agregar) {
                if (bean.isAutomatica()) {
                    aprobarTodoAutomatico(bean);
                } else {
                    aprobarTodo(bean);
                }
            } else {
                if (bean.isAutomatica()) {
                    for (AuAnexo4Automatico automatico : bean.getListaAutomaticos()) {
                        if (automatico.getItem().isPosfechado()) {
                            List<AuAnexo3Item> listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), automatico.getItem().getMaTecnologiaId());
                            listaPosfechado = filtroPosfechado(listaPosfechado);
                            
                            Date fechaAutorizacion = new Date();
                            int itemsSinAprobar = 0;
                            AuAnexo3Item itemPrincipal = null;
                            for (AuAnexo3Item itemAnexo3 : listaPosfechado) {
                                Date fechaValida = new Date();
                                fechaAutorizacion = itemAnexo3.getFechaPosfechado();
                                if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                                    fechaValida = bean.getObjeto().getObjetoContratoDetalle().getFechaHoraFin();
                                } else {
                                    if (bean.getObjeto().getObjetoCotizacion() != null) {
                                        fechaValida = bean.getObjeto().getObjetoCotizacion().getFechaFinVigencia();
                                    }
                                }
                                if (fechaValida != null) {
                                    if (fechaAutorizacion.after(fechaValida)) {
                                        if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                                            bean.addError("No se pudo autorizar " + automatico.getItem().getMaTecnologiaValor() + " debido a que el servicio no cuenta con contrato vigente para la fecha del posfechado de la autorización.");
                                        } else {
                                            bean.addError("No se pudo autorizar " + automatico.getItem().getMaTecnologiaValor() + " debido a que la cotización no se encuentra vigente para la fecha del posfechado asignada a la autorización.");
                                        }
                                        if (itemsSinAprobar == 0) {
                                            itemAnexo3.setPosfechadoPrincipal(true);
                                        }
                                        itemsSinAprobar++;
                                        continue;
                                    }
                                }
                                if (itemAnexo3.getPosfechadoPrincipal()) {
                                    aprobarUno(bean, itemAnexo3, fechaAutorizacion);
                                    itemPrincipal = itemAnexo3;
                                } else {
                                    aprobarUno(bean, itemAnexo3, fechaAutorizacion);
                                }
                            }
                            if (itemsSinAprobar > 0) {
                                if (itemPrincipal != null) {
                                    itemPrincipal.setPosfechadoPrincipal(false);
                                    getAuAnexo3ItemRemoto().actualizarPosfechado(itemPrincipal);
                                }
                            } else {//por si un item posfechado no se autoriza
                                if (!bean.getErrores().isEmpty()) {
                                    if (itemPrincipal != null) {
                                        listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), automatico.getItem().getMaTecnologiaId());
                                        listaPosfechado = filtroPosfechado(listaPosfechado);
                                        if (!listaPosfechado.isEmpty()) {
                                            itemPrincipal.setPosfechadoPrincipal(false);
                                            getAuAnexo3ItemRemoto().actualizarPosfechado(itemPrincipal);

                                            AuAnexo3Item item = listaPosfechado.get(0);
                                            item.setPosfechadoPrincipal(true);
                                            getAuAnexo3ItemRemoto().actualizarPosfechado(item);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (validarAprobacion(bean)) {
                        for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                            if (item.isPosfechado()) {
                                List<AuAnexo3Item> listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                                listaPosfechado = filtroPosfechado(listaPosfechado);
                                Date fechaAutorizacion = new Date();
                                int itemsSinAprobar = 0;
                                AuAnexo3Item itemPrincipal = null;
                                for (AuAnexo3Item itemAnexo3 : listaPosfechado) {
                                    Date fechaValida = new Date();
                                    fechaAutorizacion = itemAnexo3.getFechaPosfechado();
                                    if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                                        fechaValida = bean.getObjeto().getObjetoContratoDetalle().getFechaHoraFin();
                                    } else {
                                        if (bean.getObjeto().getObjetoCotizacion() != null) {
                                            fechaValida = bean.getObjeto().getObjetoCotizacion().getFechaFinVigencia();
                                        }
                                    }
                                    if (fechaValida != null) {
                                        if (fechaAutorizacion.after(fechaValida)) {
                                            if (bean.getObjeto().getObjetoContratoDetalle() != null) {
                                                bean.addError("No se pudo autorizar " + item.getMaTecnologiaValor() + " debido a que el servicio no cuenta con contrato vigente para la fecha del posfechado de la autorización.");
                                            } else {
                                                bean.addError("No se pudo autorizar " + item.getMaTecnologiaValor() + " debido a que la cotización no se encuentra vigente para la fecha del posfechado asignada a la autorización.");
                                            }
                                            if (itemsSinAprobar == 0) {
                                                itemAnexo3.setPosfechadoPrincipal(true);
                                            }
                                            getAuAnexo3ItemRemoto().actualizarPosfechado(itemAnexo3);
                                            itemsSinAprobar++;
                                            continue;
                                        }
                                    }
                                    if (itemAnexo3.getPosfechadoPrincipal()) {
                                        aprobarUno(bean, itemAnexo3, fechaAutorizacion);
                                        itemPrincipal = itemAnexo3;
                                    } else {
                                        aprobarUno(bean, itemAnexo3, fechaAutorizacion);
                                    }
                                }
                                if (itemsSinAprobar > 0) {
                                    if (itemPrincipal != null) {
                                        itemPrincipal.setPosfechadoPrincipal(false);
                                        getAuAnexo3ItemRemoto().actualizarPosfechado(itemPrincipal);
                                    }
                                } else {//por si un item posfechado no se autoriza
                                    if (!bean.getErrores().isEmpty()) {
                                        if (itemPrincipal != null) {
                                            listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                                            listaPosfechado = filtroPosfechado(listaPosfechado);
                                            if (!listaPosfechado.isEmpty()) {
                                                itemPrincipal.setPosfechadoPrincipal(false);
                                                getAuAnexo3ItemRemoto().actualizarPosfechado(itemPrincipal);

                                                AuAnexo3Item itemError = listaPosfechado.get(0);
                                                itemError.setPosfechadoPrincipal(true);
                                                getAuAnexo3ItemRemoto().actualizarPosfechado(itemError);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
            evaluarEstadoSolicitud(bean);
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la autorizacion, favor comunicarse con el administrador");
            evaluarEstadoSolicitud(bean);
        }
    }

    private void aprobarTodo(AuSolicitudBean bean) {
        try {
            if (validarAprobacion(bean)) {
                boolean fallo = false;
                AuAnexo4 anexo4 = castearAnexo3Anexo4(bean);
                anexo4.setPosfechada(false);//AQUI no entran posfechados
                String idAfiliado = bean.getObjeto().getAsegAfiliadoId().getId().toString();
                double calculoCopago = 0;
                double calculoModeradora = 0;
                float porcentaje = 0;
                int cantidadEntregas = 0;
                int dias = 180;
                AuCotizacionItem itemCotizacion = null;
                //listas aux
                AuTopeAfiliado auTopeAfiliado = getTopeAfiliadoRemoto().consultarAfiliadoActivo(anexo4.getAsegAfiliadoId().getId());
                if (auTopeAfiliado != null) {
                    anexo4.setTopeAplicado(Boolean.TRUE);
                }
                List<AuAnexo3Item> selectedTecnologiaAutorizacion = new ArrayList();
                for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                    if (!item.isPosfechado()) {
                        Date fechaAutorizacion = item.getFechaPosfechado() == null ? new Date() : item.getFechaPosfechado();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaAutorizacion);
                        fechaAutorizacion = calendar.getTime();
                        anexo4.setFechaAutorizacion(fechaAutorizacion);
                        anexo4.setFechaAutorizacionImpresion(fechaAutorizacion);
                        anexo4.setFechaInicio(fechaAutorizacion);
                        BigDecimal valorTecnologia = new BigDecimal("0");
                        try {
                            BigDecimal multiplicando = new BigDecimal(item.getCantidadSolicitada());
                            CntContratoDetalle contrato = getAuAnexo3Remoto().consultarContratoDetalle(bean.getObjeto().getObjetoIps().getId(), item.getMaTecnologiaId(), item.getTipoTecnologia());
                            //si contrato es preautorizado aprueba individual
                            if (contrato != null && contrato.isPreautorizacion()) {
                                bean.getObjeto().setObjetoContratoDetalle(contrato);
                                aprobarUno(bean, item, fechaAutorizacion);
                                continue;
                            } else if (contrato != null) {
                                valorTecnologia = contrato.getValorContratado().multiply(multiplicando);
                                selectedTecnologiaAutorizacion.add(item);
                            } else {//cotizacion
                                selectedTecnologiaAutorizacion.add(item);
                            }
                            if (item.getCapitaPGP() != null && item.getCapitaPGP().equals(AuConstantes.TEXTO_SI)) {
                                anexo4.setContratoAnticipado(true);
                                if (item.getContratosCapita() != null) {
                                    bean.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion() + " | " + item.getContratosCapita());
                                }
                                if (item.getProgramasPGP() != null) {
                                    bean.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion() + " | " + item.getProgramasPGP());
                                }
                                if (bean.getContratoAnticipadoObservacion().length() > 510) {
                                    bean.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion().substring(0, 510));
                                }
                                anexo4.setContratoAnticipadoObservacion(bean.getContratoAnticipadoObservacion());
                            }
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo consultando el contrato del ítem " + item.getMaeTipoCatastroficoCodigo() + ". Favor informar al administrador.");
                            fallo = true;
                            break;
                        }
                        if (item.getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO
                                || item.getTipoTecnologia() == AuConstantes.ID_AGRUPADOR_MEDICAMENTO) {
                            dias = 30;
                        } else if (item.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA) {
                            MaTecnologia tecnologia = getMaTecnologiaRemoto().consultar(item.getMaTecnologiaId());
                            if (tecnologia.getVigenciaAutorizacion() != null) {
                                dias = tecnologia.getVigenciaAutorizacion();
                            }
                        }
                        cantidadEntregas += item.getCantidadSolicitada();
                        String tipoTecnologia = "" + item.getTipoTecnologia();
                        String idTecnologia = "" + item.getMaTecnologiaId();
                        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                        if (itemCotizacion == null && (item.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION || item.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO)) {
                            itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(item.getId());
                        }

                        String numTutela = "0";
                        if (!bean.getObjeto().getAuAnexo3TutelasList().isEmpty()) {
                            for (AuAnexo3Tutela tutela : bean.getObjeto().getAuAnexo3TutelasList()) {
                                if (tutela.isExoneracionCopago()) {
                                    numTutela = "" + tutela.getNumeroTutela();
                                    anexo4.setAplicaTutela(true);
                                }
                            }
                        }
                        String programaEspecial = "0";
                        try {
                            if (bean.getObjeto().getPeProgramaEspecialId() != null) {
                                PePrograma programa = getPeProgramaRemoto().consultar(bean.getObjeto().getPeProgramaEspecialId());
                                if (programa != null && programa.isExoneradoCopago()) {
                                    programaEspecial = bean.getObjeto().getPeProgramaEspecialId().toString();
                                    anexo4.setAplicaAltocosto(true);
                                }
                            }
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo consultando el programa especial, favor consultar al administrador");
                            fallo = true;
                            break;
                        }
                        ValidaRespuestaCopagoDTO copago = getValidadorRemoto().validarCalCopagoModeradora(idAfiliado, tipoTecnologia, idTecnologia, valorTecnologia.toString(), bean.getObjeto().getMaeAmbitoAtencionCodigo(), numTutela, programaEspecial);
                        if (copago.getCodigo4() == 1) {
                            anexo4.setAplicaCopago((copago.getCodigo4() == 1));
                            calculoCopago = calculoCopago + copago.getCodigo();
                        } else if (copago.getCodigo5() == 1) {
                            anexo4.setAplicaCuotaModeradora((copago.getCodigo5() == 1));
                            calculoModeradora = Integer.parseInt(copago.getValor());
                        } else if (copago.getCodigo6() == 1) {
                            anexo4.setAplicaCuotaRecuperacion((copago.getCodigo6() == 1));
                        } else if (copago.getCodigo7() == 1){
                            anexo4.setExcentoCopago(copago.getCodigo7() == 1);
                            anexo4.setMotivoExentoCobro(copago.getMotivoExentoCobro());
                        }
                        //anexo4.setAplicaCopago((copago.getCodigo4() == 1));
                        //anexo4.setAplicaCuotaModeradora((copago.getCodigo5() == 1));
                        //anexo4.setAplicaCuotaRecuperacion((copago.getCodigo6() == 1));
                        //anexo.setExcentoCopago(copago.getCodigo7() == 1 ? true : false);
                        //calculoCopago = calculoCopago + copago.getCodigo();
                        //calculoModeradora = Integer.parseInt(copago.getValor());
                        if (anexo4.getAplicaCopago() != null && anexo4.isAplicaCuotaModeradora() && anexo4.getAplicaCopago()) {
                            bean.addError("No se puede tener items con couta de copago y moderadora al mismo tiempo");
                            fallo = true;
                            break;
                        }
                        anexo4.setValorCuotaModeradora(new BigDecimal(copago.getValor()));
                        if (copago.getCodigo3() > 0) {
                            if (porcentaje < copago.getCodigo3()) {
                                porcentaje = (float) copago.getCodigo3();
                            }
                        }
                    }
                }
                if (!fallo) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(anexo4.getFechaAutorizacion());
                        calendar.add(Calendar.DAY_OF_MONTH, dias);
                        anexo4.setFechaFin(calendar.getTime());
                        anexo4.setDiasVigencia(dias);
                        anexo4.setValorCopago(new BigDecimal(calculoCopago));
                        anexo4.setValorCuotaModeradora(new BigDecimal(calculoModeradora));
                        anexo4.setPorcentajeRecuperacion(new BigDecimal(porcentaje));
                        anexo4.setCantidadEntregas(cantidadEntregas);
                        anexo4.setObservacion(bean.getObjeto().getObservacionAuditar());
                        anexo4.setMaeRegimenId(bean.getObjeto().getAsegAfiliadoId().getMaeRegimenId());
                        anexo4.setMaeRegimenCodigo(bean.getObjeto().getAsegAfiliadoId().getMaeRegimenCodigo());
                        anexo4.setMaeRegimenValor(bean.getObjeto().getAsegAfiliadoId().getMaeRegimenValor());
                        bean.auditoriaGuardar(anexo4);
                        anexo4.setNombreAutoriza(anexo4.getUsuarioCrea());
                        String ruta = PropApl.getInstance().get(PropApl.AU_A4_ANEXOS);
                        //String ruta = "D:\\SERVICIOS\\sistemsavia\\autorizacion\\A4\\anexos\\";
                        anexo4.setRuta(ruta);
                        if (bean.isAutomatica()) {
                            anexo4.setUsuarioCrea("Sistema");
                            anexo4.setNombreAutoriza("Sistema");
                            anexo4.setEstado(AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO);
                            anexo4.setEstadoJustificacion("Aprobado Automático");
                            anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_AUTOMATICA);
                        } else {
                            if (itemCotizacion != null) {
                                anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_COTIZACION);
                            } else {
                                anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_MANUAL);
                            }
                        }

                        int idAnexo4 = getAuAnexo4Remoto().insertar(anexo4);
                        anexo4.setId(idAnexo4);
                        String nombre = AuConstantes.nombreReporteAnexo4(anexo4);
                        anexo4.setArchivo(nombre);
                        anexo4.setArchivoNombre(AuConstantes.nombreArchivoReporteAnexo4(anexo4));
                        getAuAnexo4Remoto().actualizar(anexo4);
                    } catch (Exception e) {
                        bean.addError("Hubo un fallo guardando la autorizacion, favor comunicarse con el administrador");
                        fallo = true;
                    }
                }
                if (!fallo) {
                    anexo4.setAuAnexo4ItemsList(new ArrayList());
                    if (anexo4.getId() != null) {
                        for (AuAnexo3Item itemAnexo3 : selectedTecnologiaAutorizacion) {
                            if (!itemAnexo3.isPosfechado()) {
                                AuAnexo4Item item = castearItem3Item4(itemAnexo3);
                                item.setAuAnexo4Id(anexo4);
                                BigDecimal multiplicando = new BigDecimal(item.getCantidadAutorizada());
                                try {
                                    CntContratoDetalle contrato = getAuAnexo3Remoto().consultarContratoDetalle(bean.getObjeto().getObjetoIps().getId(), item.getMaTecnologiaId(), item.getTipoTecnologia());
                                    if (contrato != null) {
                                        item.setCostoServicio(contrato.getValorContratado().multiply(multiplicando));
                                        item.setCntContratoDetalle(contrato);
                                        if (itemAnexo3.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
                                            item.setMaMedicamentoId(contrato.getMaTecnologiaId());
                                            item.setMaMedicamentoCodigo(contrato.getMaTecnologiaCodigo());
                                            item.setMaMedicamentoValor(contrato.getMaTecnologiaValor());
                                        }
                                    }
                                } catch (Exception e) {
                                    bean.addError("Hubo un fallo buscando el contrato de un ítem " + itemAnexo3.getMaTecnologiaCodigo() + ". Favor contactar al administrador");
                                    fallo = true;
                                    break;
                                }
                                bean.auditoriaGuardar(item);
                                try {
                                    int idItem = getAuAnexo4ItemRemoto().insertar(item);
                                    if (idItem > 0) {
                                        anexo4.getAuAnexo4ItemsList().add(item);
                                        bean.auditoriaModificar(itemAnexo3);
                                        if (bean.isAutomatica()) {
                                            itemAnexo3.setUsuarioCrea("Sistema");
                                            itemAnexo3.setEstado(AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO);
                                        } else if (anexo4.getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
                                            itemAnexo3.setEstado(AuAnexo3Item.ESTADO_PREAUTORIZADO);
                                        } else {
                                            itemAnexo3.setEstado(AuAnexo3Item.ESTADO_APROBADO_AUDITORIA);
                                        }
                                        getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                                        //enviar mensaje SMS
                                        if (bean.getObjeto().getAsegAfiliadoId().getAutorizaEnvioSms() != null
                                                && bean.getObjeto().getAsegAfiliadoId().getAutorizaEnvioSms()
                                                && anexo4.getEstado() == AuAnexo4.ESTADO_AUTORIZADA
                                                && anexo4.getAfiliadoCelular() != null) {
                                            Maestro maeAmbito = bean.obtenerMaestroAmbito(anexo4.getMaeAmbitoAtencionId());
                                            if (maeAmbito.contieneAccion(MaestroAccion.GN_AMBITO_APLICA_MENSAJE)) {
                                                String sms = AuConstantes.mensajeAutorizacionSMS(item);
                                                Mensaje mensaje = new Mensaje();
                                                mensaje.guardar(GnSmsEnvio.ORIGEN_AUTORIZACIONES, anexo4.getAfiliadoCelular(), sms);
                                            }

                                        }
                                    }
                                } catch (Exception e) {
                                    bean.addError("Hubo un fallo guardando un ítem");
                                    fallo = true;
                                    break;
                                }

                                if (!fallo) {
                                    try {
                                        if (anexo4.getEstado() != AuAnexo4.ESTADO_PREAUTORIZADO) {
                                            AuSeguimiento seguimiento = getAuSeguimientoRemoto().seguimientoPorAnexo3Item(itemAnexo3.getId());
                                            if (seguimiento != null) {
                                                Maestro maestroEstado = bean.getHashEstadosSeguimiento().get(AuSeguimiento.ESTADO_AUTORIZADO);
                                                seguimiento.setMaeEstadoSeguimientoId(maestroEstado.getId());
                                                seguimiento.setMaeEstadoSeguimientoCodigo(maestroEstado.getValor());
                                                seguimiento.setMaeEstadoSeguimientoValor(maestroEstado.getNombre());
                                                seguimiento.setEstadoTecnologia(itemAnexo3.getEstado());
                                                seguimiento.setAuAnexos4Id(anexo4);
                                                bean.auditoriaModificar(seguimiento);

                                                //el usuario tiene la posibilidad de cambiar el prestador al seleccionar contrato
                                                if (bean.getObjeto().getObjetoIps() != null && seguimiento.getCntPrestadorSedeAsignadoId() != null) {
                                                    if (!Objects.equals(bean.getObjeto().getObjetoIps().getId(), seguimiento.getCntPrestadorSedeAsignadoId().getId())) {
                                                        seguimiento.setCntPrestadorSedeAsignadoId(bean.getObjeto().getObjetoIps());
                                                        Empresa empresa = getEmpresaRemoto().consultarPorPrestador(
                                                                seguimiento.getCntPrestadorSedeAsignadoId().getCntPrestador().getId()
                                                        );
                                                        seguimiento.setGnEmpresasId(empresa);
                                                        guardarPrestadorSedeSeguimiento(bean, seguimiento);
                                                        guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_GESTION_NOTA, "Reasignación automatica de prestador aprobado " + seguimiento.getCntPrestadorSedeAsignadoId().getNombreSede());
                                                    }
                                                }
                                                getAuSeguimientoRemoto().actualizarEstado(seguimiento);
                                                guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_AUTORIZADO, bean.getObjeto().getObservacionAuditar());
                                            }
                                        }

                                    } catch (Exception e) {
                                        bean.addError("Hubo un fallo actualizando el seguimiento, favor comunicarse con el administrador");
                                        fallo = true;
                                    }
                                }
                            }
                        }
                        if (!fallo) {
                            try {
                                CsCopagoModeradoraHistorico historico = castearCopagoModeradora(anexo4);
                                bean.auditoriaGuardar(historico);
                                historico.setId(getCsCopagoModeradoraHistoricoRemoto().insertar(historico));
                            } catch (Exception e) {
                                bean.addError("Hubo un error creando el historico del copago, favor contactar con el administrador");
                                fallo = true;
                            }
                        }
                        int idHistorico = 0;
                        if (!fallo) {
                            try {
                                Maestro mae_causa;
                                if (anexo4.getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
                                    mae_causa = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_PREAUTORIZADO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A3);
                                } else {
                                    mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUDITORIA);
                                }
                                AuAnexo4Historico historico = new AuAnexo4Historico();
                                historico.setAuAnexos4Id(anexo4);
                                historico.setEstado(anexo4.getEstado());
                                historico.setMaeCausaId(mae_causa.getId());
                                historico.setMaeCausaCodigo(mae_causa.getValor());
                                historico.setMaeCausaValor(mae_causa.getNombre());
                                historico.setObservacionAutorizacion(anexo4.getEstadoStr());
                                bean.auditoriaGuardar(historico);
                                if (bean.isAutomatica()) {
                                    mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUTOMATICO);
                                    historico.setUsuarioCrea("Sistema");
                                    historico.setMaeCausaId(mae_causa.getId());
                                    historico.setMaeCausaCodigo(mae_causa.getValor());
                                    historico.setMaeCausaValor(mae_causa.getNombre());
                                }
                                idHistorico = getAuAnexo4HistoricoRemoto().insertar(historico);
                            } catch (Exception e) {
                                bean.addError("Hubo un fallo guardando el historial");
                                fallo = true;
                            }
                        }
                        if (!fallo) {
                            try {
//                                AuAnexo4Estado anexo4Estado = new AuAnexo4Estado();
//                                anexo4Estado.setAuAnexo4Id(anexo4);
//                                anexo4Estado.setMaeEstadoId(0);
//                                anexo4Estado.setMaeEstadoCodigo(String.valueOf(anexo4.getEstado()));
//                                anexo4Estado.setMaeEstadoValor(anexo4.getEstadoStr());
//                                anexo4Estado.setObservacion(anexo4.getObservacion());
//                                bean.auditoriaGuardar(anexo4Estado);
//                                if (bean.isAutomatica()) {
//                                    anexo4Estado.setUsuarioCrea("Sistema");
//                                }
//                                int idAnexo4Estado = getAuAnexo4EstadoRemoto().insertar(anexo4Estado);
//                                anexo4Estado.setId(idAnexo4Estado);
                                bean.setObjetoAnexo4(anexo4);
                                if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty()) {
                                    bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_ANULADO);
                                    getAuAnexo3Remoto().actualizar(bean.getObjeto());
                                } else {
                                    evaluarEstadoSolicitud(bean);
                                }
                            } catch (Exception e) {
                                bean.addError("Hubo un fallo guardando el estado, por favor contactar con el adiministrador");
                                fallo = true;
                            }
                        }
                        if (!fallo) {
                            bean.addMensaje("Se genera la autorizacion numero " + anexo4.getId() + " de manera exitosa");
                            guardarReporteAnexo4(bean);
                        } else {
                            for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                                if (item.getId() != null) {
                                    getAuAnexo3ItemRemoto().eliminar(item.getId());
                                }
                            }
                            if (idHistorico > 0) {
                                getAuAnexo4HistoricoRemoto().eliminar(idHistorico);
                            }
                            if (anexo4.getId() != null) {
                                getAuAnexo4Remoto().eliminar(anexo4.getId());
                            }

                        }
                    }
                } else {
                    if (anexo4.getId() != null) {
                        getAuAnexo4Remoto().eliminar(anexo4.getId());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @SuppressWarnings("UnusedAssignment")
    public void rechazar(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean)) {
                int estadoRechazado = AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA;
                Maestro motivoRechazo = bean.obtenerMaestroRechazo(bean.getObjeto().getIdMotivo());
                if (motivoRechazo != null) {
                    List<AuAnexo3Item> listaItems = new ArrayList();
                    if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                        listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                        listaItems = filtroPosfechado(listaItems);
                        for (AuAnexo3Item item : listaItems) {
                            if (item.getPosfechadoPrincipal()) {
                                bean.getObjeto().getObjetoTecnologia().setEstado(estadoRechazado);
                                bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                                bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                                bean.getObjeto().getObjetoTecnologia().setEstadoJustificacion(bean.getObjeto().getComentario());
                            }
                            item.setEstado(estadoRechazado);
                            item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                            item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                            item.setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                            item.setEstadoJustificacion(bean.getObjeto().getComentario());
                            bean.auditoriaModificar(item);
                            getAuAnexo3ItemRemoto().actualizarEstado(item);
                            guardarItemBitacora(item, bean, AuItemBitacora.ID_RECHAZO_ITEM, bean.getObjeto().getComentario());
                            guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                        }
                    } else {
                        bean.getObjeto().getObjetoTecnologia().setEstado(estadoRechazado);
                        bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemId(motivoRechazo.getId());
                        bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                        bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                        bean.getObjeto().getObjetoTecnologia().setEstadoJustificacion(bean.getObjeto().getComentario());
                        bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
                        getAuAnexo3ItemRemoto().actualizarEstado(bean.getObjeto().getObjetoTecnologia());
                        // funcionalidad para devolver anticipo
                        AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
                        if (cotizacion != null) {
                            if (cotizacion.getAntAnticiposId() != null) {
                                AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                                AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                                AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId());
                                BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                                anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                                bean.auditoriaModificar(anticipo);
                                getAnticipoRemoto().actualizarValorDisponible(anticipo);
                                procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_RECHAZO_ITEM);
                            }
                        }

                        guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_RECHAZO_ITEM, bean.getObjeto().getComentario());
                        guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getObjetoTecnologia().getEstadoStr());
                    }
                    evaluarEstadoSolicitud(bean);
                    bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    bean.addMensaje("Se Rechazo el ítem con Exito");
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
            evaluarEstadoSolicitud(bean);
        }
    }

    public void rechazarTodo(AuSolicitudBean bean) {
        try {
            if (validarEstadoItems(bean)) {
                int estadoRechazado = AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA;
                Maestro motivoRechazo = bean.obtenerMaestroRechazo(bean.getObjeto().getIdMotivo());
                if (motivoRechazo != null) {
                    for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                        if (item.isPosfechado()) {
                            List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                            listaItems = filtroPosfechado(listaItems);
                            if (listaItems.isEmpty()) {
                                item.setEstado(estadoRechazado);
                                item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                                item.setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                                item.setEstadoJustificacion(bean.getObjeto().getComentario());
                                bean.auditoriaModificar(item);
                                getAuAnexo3ItemRemoto().actualizarEstado(item);

                                guardarItemBitacora(item, bean, AuItemBitacora.ID_RECHAZO_ITEM, bean.getObjeto().getComentario());
                                guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                            } else {
                                for (AuAnexo3Item ítems : listaItems) {
                                    if (ítems.getPosfechadoPrincipal()) {
                                        item.setEstado(estadoRechazado);
                                        item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                        item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                                        item.setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                                        item.setEstadoJustificacion(bean.getObjeto().getComentario());
                                    }
                                    ítems.setEstado(estadoRechazado);
                                    ítems.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                    ítems.setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                                    ítems.setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                                    ítems.setEstadoJustificacion(bean.getObjeto().getComentario());
                                    bean.auditoriaModificar(ítems);
                                    getAuAnexo3ItemRemoto().actualizarEstado(ítems);

                                    guardarItemBitacora(ítems, bean, AuItemBitacora.ID_RECHAZO_ITEM, bean.getObjeto().getComentario());
                                    guardarItemBitacora(ítems, bean, AuItemBitacora.ID_CAMBIO_ESTADO, ítems.getEstadoStr());
                                }
                            }
                        } else {
                            item.setEstado(estadoRechazado);
                            item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                            item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getValor());
                            item.setMaeEstadoMotivoItemValor(motivoRechazo.getNombre());
                            item.setEstadoJustificacion(bean.getObjeto().getComentario());
                            bean.auditoriaModificar(item);
                            getAuAnexo3ItemRemoto().actualizarEstado(item);
                            // funcionalidad para devolver anticipo
                            AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(item.getId());
                            if (cotizacion != null) {
                                if (cotizacion.getAntAnticiposId() != null) {
                                    AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                                    AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                                    AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(item.getId());
                                    BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                                    anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                                    bean.auditoriaModificar(anticipo);
                                    getAnticipoRemoto().actualizarValorDisponible(anticipo);
                                    procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_RECHAZO_ITEM);
                                }
                            }
                            guardarItemBitacora(item, bean, AuItemBitacora.ID_RECHAZO_ITEM, bean.getObjeto().getComentario());
                            guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                        }
                    }
                    bean.addMensaje("Se Rechazaron los items con Exito");
                    evaluarEstadoSolicitud(bean);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un error rechazando, favor contactar con el adminitrador");
            evaluarEstadoSolicitud(bean);
        }
    }

    public void negarTodo(AuSolicitudBean bean) {
        try {
            if (validarEstadoItems(bean)) {
                int estadoAnulado = AuAnexo3Item.ESTADO_NEGADO_AUDITORIA;
                AuAnexo3 motivoRechazo = bean.getObjeto();
                if (motivoRechazo != null) {
                    AuRechazo rechazo = new AuRechazo();
                    rechazo.setEmpresasId(bean.getConexion().getEmpresa());
                    rechazo.setAsegAfiliadoId(bean.getObjeto().getAsegAfiliadoId());
                    rechazo.setAuAnexo3Id(bean.getObjeto());
                    rechazo.setMaeCausaRechazoId(motivoRechazo.getId());
                    rechazo.setMaeCausaRechazoCodigo(motivoRechazo.getMaeEstadoMotivoCodigo());
                    rechazo.setMaeCausaRechazoValor(motivoRechazo.getMaeEstadoMotivoValor());
                    rechazo.setJustificacion(motivoRechazo.getEstadoJustificacion());
                    rechazo.setAlternativa(motivoRechazo.getAlternativa());
                    bean.auditoriaGuardar(rechazo);
                    int idRechazo = getAuRechazoRemoto().insertar(rechazo);
                    if (idRechazo > 0) {
                        for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                            if (item.isPosfechado()) {
                                List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                                listaItems = filtroPosfechado(listaItems);
                                if (listaItems.isEmpty()) {
                                    item.setEstado(estadoAnulado);
                                    item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                    item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getMaeEstadoMotivoCodigo());
                                    item.setMaeEstadoMotivoItemValor(motivoRechazo.getMaeEstadoMotivoValor());
                                    item.setEstadoJustificacion(motivoRechazo.getEstadoJustificacion());
                                    bean.auditoriaModificar(item);
                                    getAuAnexo3ItemRemoto().actualizarEstado(item);
                                    rechazo.setId(idRechazo);
                                    AuRechazoItem rechazoItem = new AuRechazoItem();
                                    rechazoItem.setAuRechazoId(rechazo);
                                    rechazoItem.setAuAnexo3ItemId(item);
                                    bean.auditoriaGuardar(rechazoItem);
                                    getAuRechazoItemRemoto().insertar(rechazoItem);

                                    guardarItemBitacora(item, bean, AuItemBitacora.ID_ALTERNATIVA, bean.getObjeto().getEstadoJustificacion());
                                    guardarItemBitacora(item, bean, AuItemBitacora.ID_NEGACION_ITEM, motivoRechazo.getEstadoJustificacion());
                                    guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                                } else {
                                    for (AuAnexo3Item ítems : listaItems) {
                                        if (ítems.getPosfechadoPrincipal()) {
                                            item.setEstado(estadoAnulado);
                                            item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                            item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getMaeEstadoMotivoCodigo());
                                            item.setMaeEstadoMotivoItemValor(motivoRechazo.getMaeEstadoMotivoValor());
                                            item.setEstadoJustificacion(motivoRechazo.getEstadoJustificacion());
                                        }
                                        ítems.setEstado(estadoAnulado);
                                        ítems.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                        ítems.setMaeEstadoMotivoItemCodigo(motivoRechazo.getMaeEstadoMotivoCodigo());
                                        ítems.setMaeEstadoMotivoItemValor(motivoRechazo.getMaeEstadoMotivoValor());
                                        ítems.setEstadoJustificacion(motivoRechazo.getEstadoJustificacion());
                                        bean.auditoriaModificar(ítems);
                                        getAuAnexo3ItemRemoto().actualizarEstado(ítems);
                                        rechazo.setId(idRechazo);
                                        AuRechazoItem rechazoItem = new AuRechazoItem();
                                        rechazoItem.setAuRechazoId(rechazo);
                                        rechazoItem.setAuAnexo3ItemId(ítems);
                                        bean.auditoriaGuardar(rechazoItem);
                                        getAuRechazoItemRemoto().insertar(rechazoItem);

                                        guardarItemBitacora(ítems, bean, AuItemBitacora.ID_ALTERNATIVA, bean.getObjeto().getAlternativa());
                                        guardarItemBitacora(ítems, bean, AuItemBitacora.ID_NEGACION_ITEM, motivoRechazo.getEstadoJustificacion());
                                        guardarItemBitacora(ítems, bean, AuItemBitacora.ID_CAMBIO_ESTADO, ítems.getEstadoStr());
                                    }
                                }
                            } else {
                                item.setEstado(estadoAnulado);
                                item.setMaeEstadoMotivoItemId(motivoRechazo.getId());
                                item.setMaeEstadoMotivoItemCodigo(motivoRechazo.getMaeEstadoMotivoCodigo());
                                item.setMaeEstadoMotivoItemValor(motivoRechazo.getMaeEstadoMotivoValor());
                                item.setEstadoJustificacion(motivoRechazo.getEstadoJustificacion());
                                bean.auditoriaModificar(item);
                                getAuAnexo3ItemRemoto().actualizarEstado(item);
                                rechazo.setId(idRechazo);
                                AuRechazoItem rechazoItem = new AuRechazoItem();
                                rechazoItem.setAuRechazoId(rechazo);
                                rechazoItem.setAuAnexo3ItemId(item);
                                bean.auditoriaGuardar(rechazoItem);
                                getAuRechazoItemRemoto().insertar(rechazoItem);
                                // funcionalidad para devolver anticipo
                                AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(item.getId());
                                if (cotizacion != null) {
                                    if (cotizacion.getAntAnticiposId() != null) {
                                        AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                                        AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                                        AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(item.getId());
                                        BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                                        anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                                        bean.auditoriaModificar(anticipo);
                                        getAnticipoRemoto().actualizarValorDisponible(anticipo);
                                        procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_NEGACION_ITEM);
                                    }
                                }
                                guardarItemBitacora(item, bean, AuItemBitacora.ID_ALTERNATIVA, bean.getObjeto().getAlternativa());
                                guardarItemBitacora(item, bean, AuItemBitacora.ID_NEGACION_ITEM, motivoRechazo.getEstadoJustificacion());
                                guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                            }
                        }
                        bean.addMensaje("Se Negaron los items con exito");
                        evaluarEstadoSolicitud(bean);
                    }
                }

            }
        } catch (Exception e) {
            bean.addError("Hubo un error Negando, favor contactar con el adminitrador");
            evaluarEstadoSolicitud(bean);
        }
    }

    public void guardarGestionRiesgo(AuSolicitudBean bean) {
        try {

            List<PeAfiliadoDiagnostico> listaDiagnosticos = new ArrayList<>();
            bean.getObjeto().getAuAnexo3DiagnosticosList().stream().filter(diagnostico -> (true)).forEachOrdered(diagnostico -> {
                PeAfiliadoDiagnostico iten = new PeAfiliadoDiagnostico();
                iten.setMaDiagnosticosId(String.valueOf(diagnostico.getMaDiagnosticosId()));
                iten.setMaDiagnosticosCodigo(diagnostico.getMaDiagnosticosCodigo());
                iten.setMaDiagnosticosValor(diagnostico.getMaDiagnosticosValor());
                bean.auditoriaGuardar(iten);
                if (diagnostico.getPrincipal()) {
                    iten.setPrincipal(true);
                }
                listaDiagnosticos.add(iten);
            });
            if (listaDiagnosticos.isEmpty()) {
                bean.addMensaje("Faltan diagnosticos de ingreso");
                return;
            }
            for (PeAfiliadoSugerido obj : bean.getListaSegueridoMemoria()) {
                PePrograma programa = getPeProgramaRemoto().consultar(obj.getPePrograma().getId());
                if (programa.getRegistroAfiliadoSolicitud() == PePrograma.REGISTRO_AFILIADO_SUGERIDO) {
                    obj.setAsegAfiliado(bean.getObjeto().getAsegAfiliadoId());
                    obj.setAuAnexos3(bean.getObjeto());
                    obj.setOrigen(3);
                    obj.setEstado(1);
                    bean.auditoriaGuardar(obj);
                    int idAfiliadoSugerido = getPeAfiliadoSugeridoRemoto().insertar(obj);
                    obj.setId(idAfiliadoSugerido);
                    procesarSugeridosAdjuntos(obj.getListaAdjunto(), obj, bean);
                    bean.getObjeto().getAucSugeridolList().add(obj);
                } else if (programa.getRegistroAfiliadoSolicitud() == PePrograma.REGISTRO_AFILIADO_AUTOMATICO) {
                    PeAfiliadosPrograma ent = new PeAfiliadosPrograma();
                    if (bean.getObjeto().getAsegAfiliadoId() != null) {
                        ent.setAsegAfiliado(new AsegAfiliado(bean.getObjeto().getAsegAfiliadoId().getId()));
                    }
                    ent.setActivo(true);
                    ent.setFechaHoraCrea(new Date());
                    ent.setFechaInicioPrograma(new Date());
                    ent.setIdSolicitudOrigen(bean.getObjeto().getId());
                    List<Maestro> causas_activo = getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.PE_CAUSA_ESTADO_ACTIVO_AUTOMATICO);
                    if (!causas_activo.isEmpty()) {
                        ent.setMaeCausaActivoCodigo(causas_activo.get(0).getTipo());
                        ent.setMaeCausaActivoId(causas_activo.get(0).getId());
                        ent.setMaeCausaActivoValor(causas_activo.get(0).getNombre());
                    } else {
                        continue;
                    }

                    PeAfiliadosPrograma existe_afiliado_programa = getPeAfiliadosProgramaRemoto().consultarAfiliadoPorPrograma(bean.getObjeto().getAsegAfiliadoId().getId(), programa.getId());
                    if (existe_afiliado_programa.getId() != null) {
                        continue;
                    }

                    ent.setPePrograma(new PePrograma(programa.getId()));
                    ent.setCntPrestadorSede(new CntPrestadorSede(CntPrestadorSede.ID_SEDE_SIN_ESPECIFICAR));
                    ent.setFuenteOrigen(PeAfiliadosPrograma.ORIGEN_ANEXOS3);
                    ent.setGnUsuario(new Usuario());
                    ent.setTipoPaciente(0);
                    bean.auditoriaGuardar(ent);
                    Integer idAfiliadoPrograma = getPeAfiliadosProgramaRemoto().insertar(ent);
                    for (PeAfiliadoDiagnostico itenDiagnostico : listaDiagnosticos) {
                        if (itenDiagnostico.getId() == null) {
                            itenDiagnostico.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(idAfiliadoPrograma));
                            itenDiagnostico.setId(getPeAfiliadoDiagnosticoRemoto().insertar(itenDiagnostico));
                        }
                    }
                    bean.getListaAfiliadoPrograma().add(ent);
                }
            }
            bean.addMensaje("El paciente fue sugerido de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void crearAdjuntoSugerido(AuSolicitudBean bean) {
        try {
            bean.setListaTiposSugeridoAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_ADJUNTO_TIPO));
            bean.setHashTiposSugeridoAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposSugeridoAdjuntos()));

            bean.setObjetoSugerido(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjetoSugerido().getId()));
            if (bean.getObjetoSugerido() != null) {
                bean.getObjetoSugerido().setListaAdjunto(getPeSugeridoAdjuntoRemoto().listar(bean.getObjetoSugerido().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    public void guardarAdjuntoSugerido(AuSolicitudBean bean) {
        procesarSugeridosAdjuntos(bean.getObjetoSugerido().getListaAdjunto(), bean.getObjetoSugerido(), bean);
    }

    private void verSugerido(AuSolicitudBean bean) {
        try {
            bean.setObjetoSugerido(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjetoSugerido().getId()));
            if (bean.getObjetoSugerido() != null) {
                bean.getObjetoSugerido().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjetoSugerido().getAsegAfiliado().getId()));
                bean.getObjetoSugerido().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjetoSugerido().getPePrograma().getId()));
                bean.getObjetoSugerido().getPePrograma().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjetoSugerido().getPePrograma().getUsuariosId().getId()));
                bean.getObjetoSugerido().getAsegAfiliado().setAfiliacionUbicacion(UbicacionSingle.getInstance().getHashMunicipios().get(bean.getObjetoSugerido().getAsegAfiliado().getAfiliacionUbicacion().getId()));
                //bean.getObjetoSugerido().getAsegAfiliado().setAfiliacionUbicacion(getUbicacionRemoto().consultar(bean.getObjetoSugerido().getAsegAfiliado().getAfiliacionUbicacion().getId()));
                bean.getObjetoSugerido().setListaAdjunto(getPeSugeridoAdjuntoRemoto().listar(bean.getObjetoSugerido().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void procesarSugeridosAdjuntos(List<PeSugeridoAdjunto> adjuntosIn, PeAfiliadoSugerido sugerido, AuSolicitudBean bean) {
        try {
            for (PeSugeridoAdjunto adjunto : adjuntosIn) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    if (sugerido.getId() > 0) {
                        adjunto.setPeAfiliadoSugerido(sugerido);
                    }
                    int idAdjunto = getPeSugeridoAdjuntoRemoto().insertar(adjunto);
                    adjunto.setId(idAdjunto);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void AnularAuditoria(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean)) {
                List<AuAnexo3Item> listaItems = new ArrayList();
                if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                    listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                    listaItems = filtroPosfechado(listaItems);
                    for (AuAnexo3Item item : listaItems) {
                        if (item.getPosfechadoPrincipal()) {
                            bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_NEGADO_AUDITORIA);
                            bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemId(bean.getObjeto().getMaeEstadoMotivoId());
                            bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemCodigo(bean.getObjeto().getMaeEstadoMotivoCodigo());
                            bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemValor(bean.getObjeto().getMaeEstadoMotivoValor());
                            bean.getObjeto().getObjetoTecnologia().setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                        }
                        item.setEstado(AuAnexo3Item.ESTADO_NEGADO_AUDITORIA);
                        item.setMaeEstadoMotivoItemId(bean.getObjeto().getMaeEstadoMotivoId());
                        item.setMaeEstadoMotivoItemCodigo(bean.getObjeto().getMaeEstadoMotivoCodigo());
                        item.setMaeEstadoMotivoItemValor(bean.getObjeto().getMaeEstadoMotivoValor());
                        item.setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                        bean.auditoriaModificar(item);
                        getAuAnexo3ItemRemoto().actualizarEstado(item);

                        guardarItemBitacora(item, bean, AuItemBitacora.ID_ALTERNATIVA, bean.getObjeto().getAlternativa());
                        guardarItemBitacora(item, bean, AuItemBitacora.ID_NEGACION_ITEM, bean.getObjeto().getEstadoJustificacion());
                        guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                    }
                } else {
                    bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_NEGADO_AUDITORIA);
                    bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemId(bean.getObjeto().getMaeEstadoMotivoId());
                    bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemCodigo(bean.getObjeto().getMaeEstadoMotivoCodigo());
                    bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemValor(bean.getObjeto().getMaeEstadoMotivoValor());
                    bean.getObjeto().getObjetoTecnologia().setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                    bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
                    getAuAnexo3ItemRemoto().actualizarEstado(bean.getObjeto().getObjetoTecnologia());
                    // funcionalidad para devolver anticipo
                    AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
                    if (cotizacion != null) {
                        if (cotizacion.getAntAnticiposId() != null) {
                            AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                            AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                            AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId());
                            BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                            anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                            bean.auditoriaModificar(anticipo);
                            getAnticipoRemoto().actualizarValorDisponible(anticipo);
                            procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_NEGACION_ITEM);
                        }
                    }
                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_ALTERNATIVA, bean.getObjeto().getAlternativa());
                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_NEGACION_ITEM, bean.getObjeto().getEstadoJustificacion());
                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getObjetoTecnologia().getEstadoStr());
                }

                AuRechazo rechazo = new AuRechazo();
                rechazo.setEmpresasId(bean.getConexion().getEmpresa());
                rechazo.setAsegAfiliadoId(bean.getObjeto().getAsegAfiliadoId());
                rechazo.setAuAnexo3Id(bean.getObjeto());
                rechazo.setMaeCausaRechazoId(bean.getObjeto().getMaeEstadoMotivoId());
                rechazo.setMaeCausaRechazoCodigo(bean.getObjeto().getMaeEstadoMotivoCodigo());
                rechazo.setMaeCausaRechazoValor(bean.getObjeto().getMaeEstadoMotivoValor());
                rechazo.setJustificacion(bean.getObjeto().getEstadoJustificacion());
                rechazo.setAlternativa(bean.getObjeto().getAlternativa());
                bean.auditoriaGuardar(rechazo);
                int idRechazo = getAuRechazoRemoto().insertar(rechazo);
                if (idRechazo > 0) {
                    rechazo.setId(idRechazo);
                    int idRechazoItem = 0;
                    if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                        for (AuAnexo3Item item : listaItems) {
                            AuRechazoItem rechazoItem = new AuRechazoItem();
                            rechazoItem.setAuRechazoId(rechazo);
                            rechazoItem.setAuAnexo3ItemId(item);
                            bean.auditoriaGuardar(rechazoItem);
                            idRechazoItem = getAuRechazoItemRemoto().insertar(rechazoItem);
                        }
                    } else {
                        AuRechazoItem rechazoItem = new AuRechazoItem();
                        rechazoItem.setAuRechazoId(rechazo);
                        rechazoItem.setAuAnexo3ItemId(bean.getObjeto().getObjetoTecnologia());
                        bean.auditoriaGuardar(rechazoItem);
                        idRechazoItem = getAuRechazoItemRemoto().insertar(rechazoItem);
                    }
                    if (idRechazoItem > 0) {
                        bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                        bean.addMensaje("Se Nego el ítem con Exito");
                        evaluarEstadoSolicitud(bean);
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
            evaluarEstadoSolicitud(bean);
        }
    }

    public void derivar(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean)) {
                //buscar Grupo
                AuGrupo grupo = getAuGrupoRemoto().consultar(bean.getIdGrupo());
                for (AuAnexo3Item item : bean.getListaItemsDerivar()) {
                    item.setGnUsuarioId(null);
                    bean.auditoriaModificar(item);
                    item.setAuGrupoId(grupo);
                    getAuAnexo3ItemRemoto().actualizarGrupo(item);

                    guardarItemBitacora(item, bean, AuItemBitacora.ID_DERIVACION, bean.getComentarioDevolucion());
                }
                bean.addMensaje("Se derivo con éxito");
                bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un error derivando, favor contactar con el adminitrador");
        }
    }

    public void devolver(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean) && validarAcciones(bean)) {
                int estadoDevuelto = AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA;
                Maestro motivoDevolucion = bean.obtenerMaestroRechazo(bean.getObjeto().getIdMotivo());
                if (motivoDevolucion != null) {
                    if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                        List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                        listaItems = listaItems.stream()
                                .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                                || p.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                                || p.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                                ).collect(Collectors.toList());
                        for (AuAnexo3Item itemAnexo3 : listaItems) {
                            itemAnexo3.setEstado(estadoDevuelto);
                            itemAnexo3.setMaeEstadoMotivoItemId(motivoDevolucion.getId());
                            itemAnexo3.setMaeEstadoMotivoItemCodigo(motivoDevolucion.getValor());
                            itemAnexo3.setMaeEstadoMotivoItemValor(motivoDevolucion.getNombre());
                            bean.auditoriaModificar(itemAnexo3);
                            getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                            guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_SOLICITUD_DEVOLUCION, bean.getObjeto().getComentario());
                            guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                        }
                        bean.addMensaje("Se realiza la devolución del ítem");
                        bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_DEVUELTO);
                        bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                        bean.auditoriaModificar(bean.getObjeto());
                        getAuAnexo3Remoto().actualizarEstado(bean.getObjeto());

                        bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    } else {
                        bean.getObjeto().getObjetoTecnologia().setEstado(estadoDevuelto);
                        bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemId(motivoDevolucion.getId());
                        bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemCodigo(motivoDevolucion.getValor());
                        bean.getObjeto().getObjetoTecnologia().setMaeEstadoMotivoItemValor(motivoDevolucion.getNombre());
                        bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
                        getAuAnexo3ItemRemoto().actualizarEstado(bean.getObjeto().getObjetoTecnologia());
                        bean.addMensaje("Se realiza la devolución del ítem");
                        bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_DEVUELTO);
                        bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                        getAuAnexo3Remoto().actualizarEstado(bean.getObjeto());
                        guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_SOLICITUD_DEVOLUCION, bean.getObjeto().getComentario());
                        guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getObjetoTecnologia().getEstadoStr());
                        bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    }
                    guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                } else {
                    bean.addError("No se encontro el motivo de devolución");
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void devolverTodo(AuSolicitudBean bean) {
        try {
            if (validarEstadoItems(bean) && validarAcciones(bean)) {
                int estadoDevuelto = AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA;
                Maestro motivoDevolucion = bean.obtenerMaestroRechazo(bean.getObjeto().getIdMotivo());
                if (motivoDevolucion != null) {
                    for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                        if (item.isPosfechado()) {
                            //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                            List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                            listaItems = listaItems.stream()
                                    .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                                    || p.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                                    || p.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                                    ).collect(Collectors.toList());
                            for (AuAnexo3Item itemAnexo3 : listaItems) {
                                itemAnexo3.setEstado(estadoDevuelto);
                                itemAnexo3.setMaeEstadoMotivoItemId(motivoDevolucion.getId());
                                itemAnexo3.setMaeEstadoMotivoItemCodigo(motivoDevolucion.getValor());
                                itemAnexo3.setMaeEstadoMotivoItemValor(motivoDevolucion.getNombre());
                                bean.auditoriaModificar(itemAnexo3);
                                getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_SOLICITUD_DEVOLUCION, bean.getObjeto().getComentario());
                                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                            }
                        } else {
                            item.setEstado(estadoDevuelto);
                            item.setMaeEstadoMotivoItemId(motivoDevolucion.getId());
                            item.setMaeEstadoMotivoItemCodigo(motivoDevolucion.getValor());
                            item.setMaeEstadoMotivoItemValor(motivoDevolucion.getNombre());
                            bean.auditoriaModificar(item);
                            getAuAnexo3ItemRemoto().actualizarEstado(item);

                            guardarItemBitacora(item, bean, AuItemBitacora.ID_SOLICITUD_DEVOLUCION, bean.getObjeto().getComentario());
                            guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                        }
                        guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                    }
                    bean.addMensaje("Se realizaron las devoluciones de los ítems");
                    //evaluarEstadoSolicitud(bean);
                    bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_DEVUELTO);
                    bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    getAuAnexo3Remoto().actualizarEstado(bean.getObjeto());
                } else {
                    bean.addError("No se encontro el motivo de devolución");
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void anular(AuSolicitudBean bean) {
        try {
            boolean anular = true;
            if (!bean.validarEstadoPendiente(bean.getObjeto().getEstado())) {
                anular = false;
            }
            if (!bean.getObjeto().getAuAnexo3ItemsList().isEmpty()) {
                for (AuAnexo3Item item : bean.getObjeto().getAuAnexo3ItemsList()) {
                    if (item.isPosfechado()) {
                        List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                        for (AuAnexo3Item listaItem : listaItems) {
                            if (listaItem.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUDITORIA
                                    || listaItem.getEstado() == AuAnexo3Item.ESTADO_DIRECCIONADO_AUTOMATICO
                                    || listaItem.getEstado() == AuAnexo3Item.ESTADO_DIRECCIONADO
                                    || listaItem.getEstado() == AuAnexo3Item.ESTADO_PREAUTORIZADO
                                    || listaItem.getEstado() == AuAnexo3Item.ESTADO_AUTORIZADA_PREAUTORIZACION
                                    || listaItem.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO) {
                                anular = false;
                                break;
                            }
                        }
                    } else {
                        if (item.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUDITORIA
                                || item.getEstado() == AuAnexo3Item.ESTADO_DIRECCIONADO_AUTOMATICO
                                || item.getEstado() == AuAnexo3Item.ESTADO_DIRECCIONADO
                                || item.getEstado() == AuAnexo3Item.ESTADO_PREAUTORIZADO
                                || item.getEstado() == AuAnexo3Item.ESTADO_AUTORIZADA_PREAUTORIZACION
                                || item.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO) {
                            anular = false;
                            break;
                        }
                    }

                }
            }
            if (anular) {
                for (AuAnexo3Item item : bean.getObjeto().getAuAnexo3ItemsList()) {
                    if (item.isPosfechado()) {
                        List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                        listaItems = filtroPosfechado(listaItems);
                        for (AuAnexo3Item itemPosf : listaItems) {
                            itemPosf.setEstado(AuAnexo3Item.ESTADO_ANULADO);
                            itemPosf.setMaeEstadoMotivoItemId(bean.getObjeto().getMaeEstadoMotivoId());
                            itemPosf.setMaeEstadoMotivoItemCodigo(bean.getObjeto().getMaeEstadoMotivoCodigo());
                            itemPosf.setMaeEstadoMotivoItemValor(bean.getObjeto().getMaeEstadoMotivoValor());
                            itemPosf.setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                            bean.auditoriaModificar(itemPosf);
                            getAuAnexo3ItemRemoto().actualizarEstado(itemPosf);

                            guardarItemBitacora(itemPosf, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemPosf.getEstadoStr());
                        }
                    } else {
                        item.setEstado(AuAnexo3Item.ESTADO_ANULADO);
                        item.setMaeEstadoMotivoItemId(bean.getObjeto().getMaeEstadoMotivoId());
                        item.setMaeEstadoMotivoItemCodigo(bean.getObjeto().getMaeEstadoMotivoCodigo());
                        item.setMaeEstadoMotivoItemValor(bean.getObjeto().getMaeEstadoMotivoValor());
                        item.setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                        bean.auditoriaModificar(item);
                        getAuAnexo3ItemRemoto().actualizarEstado(item);
                        // funcionalidad para devolver anticipo
                        AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(item.getId());
                        if (cotizacion != null) {
                            if (cotizacion.getAntAnticiposId() != null) {
                                AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                                AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                                AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(item.getId());
                                BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                                anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                                bean.auditoriaModificar(anticipo);
                                getAnticipoRemoto().actualizarValorDisponible(anticipo);
                                procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_ANULA_SOLICITUD);
                            }
                        }
                        guardarItemBitacora(item, bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                    }
                    //cancela seguimientos
                    AuSeguimiento seguimiento = getAuSeguimientoRemoto().seguimientoPorAnexo3Item(item.getId());
                    if (seguimiento != null) {
                        Maestro maestroEstado = bean.getHashEstadosSeguimiento().get(AuSeguimiento.ESTADO_CANCELADO);
                        seguimiento.setMaeEstadoSeguimientoId(maestroEstado.getId());
                        seguimiento.setMaeEstadoSeguimientoCodigo(maestroEstado.getValor());
                        seguimiento.setMaeEstadoSeguimientoValor(maestroEstado.getNombre());
                        seguimiento.setEstadoTecnologia(item.getEstado());
                        bean.auditoriaModificar(seguimiento);
                        getAuSeguimientoRemoto().actualizarEstado(seguimiento);
                        guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_CANCELADO, bean.getObjeto().getEstadoJustificacion());
                    }
                }
                bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_ANULADO);
                bean.getObjeto().setFuenteAnula(AuAnexo3.FUENTE_ANULADA_MANUAL);
                bean.auditoriaModificar(bean.getObjeto());
                getAuAnexo3Remoto().actualizar(bean.getObjeto());

                guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_ANULADO, bean.getObjeto().getEstadoJustificacion());
                bean.addMensaje("Se anuló la solicitud de manera exitosa");
            } else {
                bean.addError("No se puede realizar la anulacion");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void completarMaestroHistoricos(AuSolicitudBean bean) {
        //Origen atencion
        Maestro origenAtencion = bean.obtenerMaestroOrigenAtencion(bean.getObjeto().getMaeOrigenAtencionId());
        if (origenAtencion != null) {
            bean.getObjeto().setMaeOrigenAtencionCodigo(origenAtencion.getValor());
            bean.getObjeto().setMaeOrigenAtencionValor(origenAtencion.getNombre());
        }
        //Servicio solicitado
        Maestro servicioSolicitado = bean.obtenerMaestroServicioSolicitado(bean.getObjeto().getMaeTipoServicioId());
        if (servicioSolicitado != null) {
            bean.getObjeto().setMaeTipoServicioId(servicioSolicitado.getId());
            bean.getObjeto().setMaeTipoServicioCodigo(servicioSolicitado.getValor());
            bean.getObjeto().setMaeTipoServicioValor(servicioSolicitado.getNombre());
        }
        //Ubicacion
        Maestro ubicacionPaciente = bean.obtenerMaestroUbicacion(bean.getObjeto().getMaeUbicacionId());
        if (ubicacionPaciente != null) {
            bean.getObjeto().setMaeUbicacionCodigo(ubicacionPaciente.getValor());
            bean.getObjeto().setMaeUbicacionValor(ubicacionPaciente.getNombre());
        }
        Maestro modalidad = bean.obtenerMaestroModalidadTecnologia(bean.getObjeto().getMaeModalidadTecnologiaId());
        if (modalidad != null) {
            bean.getObjeto().setMaeModalidadTecnologiaCodigo(modalidad.getValor());
            bean.getObjeto().setMaeModalidadTecnologiaValor(modalidad.getNombre());
        }
        Maestro finalidad = bean.obtenerMaestroFinalidadTecnologia(bean.getObjeto().getMaeFinalidadTecnologiaId());
        if (finalidad != null) {
            bean.getObjeto().setMaeFinalidadTecnologiaCodigo(finalidad.getValor());
            bean.getObjeto().setMaeFinalidadTecnologiaValor(finalidad.getNombre());
        }
    }

    @Override
    public void listarAfiliado(AuSolicitudBean bean) {
        try {
            bean.getParamConsulta(2).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(2)));
            bean.setListaAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(2)));
//            for (AsegAfiliado registro : bean.getListaAfiliados()) {
//                if (registro.getResidenciaUbicacion() != null) {
//                    registro.setResidenciaUbicacion(getUbicacionRemoto().consultar(registro.getResidenciaUbicacion().getId()));
//                }
//            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarIps(AuSolicitudBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(3).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            bean.getParamConsulta(3).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(3).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(3).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(3)));
            bean.setListaIps(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(3)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void completarAfiliado(AuSolicitudBean bean) {
        try {
            int idAfiliado = bean.getObjeto().getAsegAfiliadoId().getId();
            bean.getObjeto().setAsegAfiliadoId(getAfiliadoRemoto().consultar(idAfiliado));

            //Buscar programas especiales asociados al afiliado
            if (idAfiliado > 0) {
                bean.setListaProgramaEspecial(getAuAnexo3Remoto().consultarProgramasAfiliado(idAfiliado));
                consultarProgramaAfiliado(bean);
                if (bean.getListaProgramaEspecial() != null && !bean.getListaProgramaEspecial().isEmpty()) {
                    HashMap hashPrograma = new HashMap();
                    for (PePrograma programa : bean.getListaProgramaEspecial()) {
                        hashPrograma.put(programa.getId(), programa);
                    }
                    bean.setHashProgramaEspecial(hashPrograma);
                }
            }

            portabilidadAfiliado(bean);
            buscarTutelasAfiliado(bean);
            contactosAfiliado(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void buscarProfesional(AuSolicitudBean bean) {
        try {
            int tipoDocumento = bean.getObjeto().getCntProfesionaleId().getMaeTipoCodumentoId();
            String documento = bean.getObjeto().getCntProfesionaleId().getDocumento();
            CntProfesional profesional = getProfesionalRemoto().consultarNumDocumento(tipoDocumento, documento);
            if (profesional != null && profesional.getId() != null) {
                bean.getObjeto().setCntProfesionaleId(profesional);
                bean.getObjeto().setNombreProfesional(profesional.getPrimerNombre() + profesional.getPrimerApellido());
                CntProfesionalPrestador contrato = getAuAnexo3Remoto().buscarEspeciliadad(bean.getObjeto().getCntPrestadorSedeId().getCntPrestador().getId(), profesional.getId());
                if (contrato != null) {
                    MaEspecialidad especialidad = new MaEspecialidad(contrato.getMaEspecialidadId());
                    especialidad.setCodigo(contrato.getMaEspecialidadCodigo());
                    especialidad.setNombre(contrato.getMaEspecialidadValor());
                    bean.getObjeto().setMaeEspecialidadProfesional(especialidad.getId());
                    bean.getObjeto().setObjetoEspecialidad(especialidad);
                    bean.getObjeto().setNewProfesional(true);
                } else {
                    bean.getObjeto().setNewProfesional(false);
                }
            } else {
                bean.getObjeto().setCntProfesionaleId(new CntProfesional());
                bean.getObjeto().getCntProfesionaleId().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjeto().getCntProfesionaleId().setDocumento(documento);
                bean.getObjeto().setNewProfesional(false);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void borrarAdjunto(int id, AuSolicitudBean bean) {
        try {
            getAuSolicitudAdjuntoRemoto().eliminar(id);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public AuAnexo3Afiliado casteoAuAnexo3Afiliado(AsegAfiliado afiliado) {
        AuAnexo3Afiliado afiliadoAnexo = new AuAnexo3Afiliado();
        afiliadoAnexo.setMaeTipoDocumentoId(afiliado.getMaeTipoDocumento());
        afiliadoAnexo.setNumeroIdentificacion(afiliado.getNumeroDocumento());
        afiliadoAnexo.setPrimerNombre(afiliado.getPrimerNombre());
        afiliadoAnexo.setSegundoNombre(afiliado.getSegundoNombre());
        afiliadoAnexo.setPrimerApellido(afiliado.getPrimerApellido());
        afiliadoAnexo.setSegundoApellido(afiliado.getSegundoApellido());
        afiliadoAnexo.setFechaNacimiento(afiliado.getFechaNacimiento());
        if (afiliado.getDireccionResidencia() != null && !afiliado.getDireccionResidencia().equals("")) {
            afiliadoAnexo.setDireccionAfiliado(afiliado.getDireccionResidencia());
        } else {
            afiliadoAnexo.setDireccionAfiliado("Sin dirección");
        }
        afiliadoAnexo.setTelefonoAfiliado(afiliado.getTelefonoFijo());
        afiliadoAnexo.setCelularAfiliado(afiliado.getTelefonoMovil());
        afiliadoAnexo.setCorreoElectronico(afiliado.getEmail());
        afiliadoAnexo.setMaeRegimenId(Integer.valueOf(afiliado.getRegimen()));
        afiliadoAnexo.setMaeRegimenCodigo(afiliado.getMaeRegimenCodigo());
        afiliadoAnexo.setMaeRegimenValor(afiliado.getMaeRegimenValor());
        return afiliadoAnexo;
    }

    @SuppressWarnings("null")
    private void guardarAdjunto(AuSolicitudBean bean, AuSolicitudAdjunto adjunto) {
        try {
            String ruta = PropApl.getInstance().get(PropApl.AU_A3_ADJUNTOS);
            if (ruta != null || !ruta.isEmpty()) {
                //Generar nombre del archivo
                SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
                StringBuilder nombreArchivo = new StringBuilder();
                String tipoDocumento = adjunto.getMaeTipoArchivoCodigo();
                nombreArchivo.append(tipoDocumento)
                        .append("_")
                        .append(sdf.format(new Date()));
                nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                adjunto.setArchivo(nombreArchivo.append(adjunto.getExtension()).toString());
                Maestro maeTipoAdjunto = getMaestroRemoto().consultar(adjunto.getMaeTipoArchivoId());
                adjunto.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
                adjunto.setMaeTipoArchivoId(maeTipoAdjunto.getId());
                adjunto.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
                adjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_ANEXO3);
                //adjunto.setNombreArchivo(nombreArchivo.toString());
                adjunto.setRuta(ruta);
                File archivo = new File(ruta, adjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(adjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(adjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                adjunto.setAdjuntoStream(null);
                adjunto.setId(getAuSolicitudAdjuntoRemoto().insertar(adjunto));
            } else {
                bean.addError("No fue posible guardar el adjunto " + adjunto.getNombre() + ", ya que la ruta no es valida.");
            }
        } catch (Exception e) {
            bean.addError("No fue posible guardar el adjunto " + adjunto.getNombre());
        }
    }

//    private void auditoriaAdjuntos(AuSolicitudBean bean) {
//        if (bean.getObjeto().getAuSolicitudAdjuntosList() != null) {
//            for (AuSolicitudAdjunto adjunto : bean.getObjeto().getAuSolicitudAdjuntosList()) {
//                adjunto.setUsuarioCrea(bean.getConexion().getUsuario().getNombre());
//                adjunto.setFechaHoraCrea(new Date());
//                adjunto.setTerminalCrea(bean.getConexion().getIp());
//            }
//        }
//    }
    private void completarItems(AuSolicitudBean bean) {
        for (AuAnexo3Item item : bean.getObjeto().getAuAnexo3ItemsList()) {
            item.setMaeAmbitoId(bean.getObjeto().getMaeAmbitoAtencionId());
            item.setMaeAmbitoCodigo(bean.getObjeto().getMaeAmbitoAtencionCodigo());
            item.setMaeAmbitoValor(bean.getObjeto().getMaeAmbitoAtencionValor());

            int idDiagnostico = item.getMaDiagnosticoId();
            for (AuAnexo3Diagnostico diagnostico : bean.getObjeto().getAuAnexo3DiagnosticosList()) {
                if (diagnostico.getMaDiagnosticosId() == idDiagnostico) {
                    item.setMaDiagnosticoId(diagnostico.getMaDiagnosticosId());
                    item.setMaDiagnosticoCodigo(diagnostico.getMaDiagnosticosCodigo());
                    item.setMaDiagnosticoValor(diagnostico.getMaDiagnosticosValor());
                }
            }
            if (item.getMaServicioSolicitadoId() == null) {
                item.setMaServicioSolicitadoId(bean.getObjeto().getMaServicioSolicitadoId());
                item.setMaServicioSolicitadoCodigo(bean.getObjeto().getMaServicioSolicitadoCodigo());
                item.setMaServicioSolicitadoValor(bean.getObjeto().getMaServicioSolicitadoValor());
            }

            if (item.getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO) {
                item.setFrecuencia(item.getFrecuencia() != null ? item.getFrecuencia() : "0");
                if (item.getMaeCausaExternaId() != null) {
                    Maestro causaExterna = bean.obtenerMaestroCausaExterna(item.getMaeCausaExternaId());
                    if (causaExterna != null) {
                        item.setMaeCausaExternaId(causaExterna.getId());
                        item.setMaeCausaExternaCodigo(causaExterna.getValor());
                        item.setMaeCausaExternaValor(causaExterna.getNombre());
                    }
                }
                if (item.getMaeFinalidadId() != null) {
                    Maestro finalidad = bean.obtenerMaestroFinalidad(item.getMaeFinalidadId());
                    if (finalidad != null) {
                        item.setMaeFinalidadId(finalidad.getId());
                        item.setMaeFinalidadCodigo(finalidad.getValor());
                        item.setMaeFinalidadValor(finalidad.getNombre());
                    }
                }
                if (item.getMaeTipoCatastroficoId() != null) {
                    Maestro catastrofico = bean.obtenerMaestroCatastrofico(item.getMaeTipoCatastroficoId());
                    if (catastrofico != null) {
                        item.setMaeTipoCatastroficoId(catastrofico.getId());
                        item.setMaeTipoCatastroficoCodigo(catastrofico.getValor());
                        item.setMaeTipoCatastroficoValor(catastrofico.getNombre());
                    }
                }
                if (item.getMaeViaAdministracionId() != null) {
                    Maestro viaAdministracion = bean.obtenerMaestroViaAdministracion(item.getMaeViaAdministracionId());
                    if (viaAdministracion != null) {
                        item.setMaeViaAdministracionId(viaAdministracion.getId());
                        item.setMaeViaAdministracionCodigo(viaAdministracion.getValor());
                        item.setMaeViaAdministracionValor(viaAdministracion.getNombre());
                    }
                }
            }
        }
    }

    private AuAnexo4 castearAnexo3Anexo4(AuSolicitudBean bean) throws Exception {
        AuAnexo4 anexo = new AuAnexo4();
        anexo.setAuAnexo3Id(bean.getObjeto());
        Empresa empresa = getEmpresaRemoto().consultarPorPrestador(bean.getObjeto().getObjetoIps().getCntPrestador().getId());
//        Maestro mae_estado = getMaestroRemoto().consultar(AuAnexo3Item.ESTADO_APROBADO_AUDITORIA);
        Maestro mae_estado = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUDITORIA);
        if (empresa != null) {
            anexo.setGnEmpresaId(empresa);
        } else {
            anexo.setGnEmpresaId(null);
        }
        anexo.setNumeroAutorizacion("0");
        if (mae_estado != null) {
            anexo.setEstadoJustificacion(mae_estado.getDescripcion());
        }
        anexo.setAsegAfiliadoId(bean.getObjeto().getAsegAfiliadoId());
        anexo.setAfiliadoNumeroDocumento(bean.getObjeto().getAsegAfiliadoId().getNumeroDocumento());
        anexo.setAfiliadoPrimerApellido(bean.getObjeto().getAsegAfiliadoId().getPrimerApellido());
        anexo.setAfiliadoSegundoApellido(bean.getObjeto().getAsegAfiliadoId().getSegundoApellido());
        anexo.setAfiliadoPrimerNombre(bean.getObjeto().getAsegAfiliadoId().getPrimerNombre());
        anexo.setAfiliadoSegundoNombre(bean.getObjeto().getAsegAfiliadoId().getSegundoNombre());
//        anexo.setAfiliadoDireccion(bean.getObjeto().getAsegAfiliadoId().getDireccionDescripcion());
        anexo.setAfiliadoDireccion(bean.getObjeto().getAsegAfiliadoId().getDireccionResidencia());
        anexo.setAfiliadoFechaNacimiento(bean.getObjeto().getAsegAfiliadoId().getFechaNacimiento());
        anexo.setAfiliadoTelefono(bean.getTelefonoFijoAfiliado());
        anexo.setAfiliadoCelular(bean.getTelefonoMovilAfiliado());//de tabla contactos afiliado
        anexo.setAfiliadoCorreo(bean.getObjeto().getAsegAfiliadoId().getEmail());
        anexo.setAfiliadoUbicacion(bean.getObjeto().getAsegAfiliadoId().getAfiliacionUbicacion().getId());
        anexo.setPrestadorNumeroDocumento(bean.getObjeto().getObjetoIps().getCntPrestador().getNumeroDocumento());
        anexo.setPrestadorCodigoHabilitacion(bean.getObjeto().getObjetoIps().getCodigoHabilitacionSede());
        anexo.setPrestadorDireccion(bean.getObjeto().getObjetoIps().getDireccion());
        anexo.setPrestadorNombre(bean.getObjeto().getObjetoIps().getNombreSede());
        anexo.setPrestadorTelefonoCita(bean.getObjeto().getObjetoIps().getTelefonoCitas());
        if (bean.getObjeto().getObjetoContratoDetalle() != null) {
            anexo.setCntContratoId(bean.getObjeto().getObjetoContratoDetalle().getCntContrato());
        }
        anexo.setCntPrestadorSedeId(bean.getObjeto().getObjetoIps());
        anexo.setMaeAmbitoAtencionId(bean.getObjeto().getMaeAmbitoAtencionId());
        anexo.setMaeAmbitoAtencionCodigo(bean.getObjeto().getMaeAmbitoAtencionCodigo());
        anexo.setMaeAmbitoAtencionValor(bean.getObjeto().getMaeAmbitoAtencionValor());
        anexo.setMaServicioHabilitadoId(bean.getObjeto().getMaServicioHabilitadoId());
        anexo.setMaServicioHabilitadoCodigo(bean.getObjeto().getMaServicioHabilitadoCodigo());
        anexo.setMaServicioHabilitadoValor(bean.getObjeto().getMaServicioHabilitadoValor());
        for (AuAnexo3Diagnostico diag : bean.getObjeto().getAuAnexo3DiagnosticosList()) {
            if (diag.getPrincipal()) {
                anexo.setDiagnosticoPrincipal(diag.getMaDiagnosticosCodigo() + " - " + diag.getMaDiagnosticosValor());
            }
        }
        anexo.setImpresionesRealizadas(0);
        anexo.setMaEspecialidadId(0);
        anexo.setMaEspecialidadCodigo(" ");
        anexo.setMaEspecialidadValor(" ");
        anexo.setAplicaFactura(false);
        anexo.setAplicaNobps(false);
        anexo.setAplicaPac(false);
        anexo.setAplicaTopeMaximo(false);
        anexo.setAplicaNoRed(false);
        anexo.setAplicaAutorizacionAutomatica(false);
        anexo.setAplicaTiqueteBonoVale(false);
        anexo.setAplicaCapitaRecobro(false);
        Date fechaAfiliacion = bean.getObjeto().getAsegAfiliadoId().getFechaAfiliacionEps();
        Date fechaActual = new Date();
        float diff = fechaActual.getTime() - fechaAfiliacion.getTime();
        diff = diff / (24 * 60 * 60 * 1000);
        float semanas = diff / 7;
        anexo.setSemanasAfiliacion((int) semanas);
        anexo.setTipoServicioHabilitado(bean.getObjeto().getMaServicioHabilitadoValor());
        anexo.setCargoActividadAutoriza(AuConstantes.CARGO_AUTORIZA);
        Empresa savia = getEmpresaRemoto().consultar(1);
        anexo.setEpsTelefono(AuConstantes.EPS_TELEFONO);
        anexo.setEntidadPago(savia.getRazonSocial());
        String codigo = AuConstantes.CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO;
        if (bean.getObjeto().getAsegAfiliadoId().getRegimen().equals("1")) {
            codigo = AuConstantes.CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO;
        }
        anexo.setCodigoEntidadPago(codigo);
        anexo.setAnexo3Cama(bean.getObjeto().getCama());
        anexo.setNumeroPrescripcion("" + bean.getObjeto().getId());

        anexo.setAfiliadoTipoDocumento(bean.getObjeto().getAsegAfiliadoId().getMaeTipoDocumentoCodigo());
        anexo.setAfiliadoDepartamento(bean.obtenerDepartamento(bean.getObjeto().getAsegAfiliadoId().getAfiliacionUbicacion().getId()));
        anexo.setAfiliadoMunicipio(bean.obtenerMunicipio(bean.getObjeto().getAsegAfiliadoId().getAfiliacionUbicacion().getId()));
        CntPrestador prestador = getPrestadorRemoto().consultar(bean.getObjeto().getObjetoIps().getCntPrestador().getId());
        anexo.setPrestadorTipoDocumento(prestador.getMaeTipoDocumentoCodigo());
        anexo.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
        anexo.setPrestadorDepartamento(bean.obtenerDepartamento(bean.getObjeto().getObjetoIps().getUbicacionId()));
        anexo.setPrestadorMunicipio(bean.obtenerMunicipio(bean.getObjeto().getObjetoIps().getUbicacionId()));
        anexo.setVersion(bean.getObjeto().getVersion());
        anexo.setDireccionAlternativa(bean.getObjeto().getDireccionAlternativa());
        anexo.setMaeModalidadTecnologiaId(bean.getObjeto().getMaeModalidadTecnologiaId());
        anexo.setMaeModalidadTecnologiaCodigo(bean.getObjeto().getMaeModalidadTecnologiaCodigo());
        anexo.setMaeModalidadTecnologiaValor(bean.getObjeto().getMaeModalidadTecnologiaValor());
        anexo.setMaeFinalidadTecnologiaId(bean.getObjeto().getMaeFinalidadTecnologiaId());
        anexo.setMaeFinalidadTecnologiaCodigo(bean.getObjeto().getMaeFinalidadTecnologiaCodigo());
        anexo.setMaeFinalidadTecnologiaValor(bean.getObjeto().getMaeFinalidadTecnologiaValor());
        anexo.setMaeUbicacionId(bean.getObjeto().getMaeUbicacionId());
        anexo.setMaeUbicacionCodigo(bean.getObjeto().getMaeUbicacionCodigo());
        anexo.setMaeUbicacionValor(bean.getObjeto().getMaeUbicacionValor());
        return anexo;
    }

    private AuAnexo4Item castearItem3Item4(AuAnexo3Item itemAnexo3) {
        AuAnexo4Item item = new AuAnexo4Item();
        item.setAuAnexo3ItemId(itemAnexo3);
        item.setTipoTecnologia(itemAnexo3.getTipoTecnologia());
        item.setMaTecnologiaId(itemAnexo3.getMaTecnologiaId());
        item.setMaTecnologiaCodigo(itemAnexo3.getMaTecnologiaCodigo());
        item.setMaTecnologiaValor(itemAnexo3.getMaTecnologiaValor());
        item.setCantidadAutorizada(itemAnexo3.getCantidadSolicitada());
        return item;
    }

    @Override
    public void listarContratoDetalle(AuSolicitudBean bean) {
        try {
            bean.getParamConsulta4().setCantidadRegistros(getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta4()));
            bean.setListaContratosDetalles(getAuAnexo3Remoto().consultarListaContratos(bean.getParamConsulta4()));
            for (CntContratoDetalle contratoDetalle : bean.getListaContratosDetalles()) {
                CntContratoSede contratoSede = getCntContratoSedeRemoto().consultar(contratoDetalle.getCntContratoSede().getId());
                contratoDetalle.setCntContratoSede(contratoSede);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public List<ReporteAnexo3> generarReporteAnexo3(int id, AuSolicitudBean bean) {
        List<ReporteAnexo3> listaReportes = new ArrayList();
        ReporteAnexo3 reporte = new ReporteAnexo3();
        try {
            Empresa savia = getEmpresaRemoto().consultar(1);
            AuAnexo3 anexo = getAuAnexo3Remoto().consultar(id);
            if (anexo != null) {
                bean.setObjeto(anexo);
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
                        reporte.setStrCobertura(anexo.getAsegAfiliadoId().getMaeRegimenValor());
                        reporte.setStrOrigenAtencion(anexo.getMaeOrigenAtencionValor());
                        reporte.setStrServicioSolicitado(anexo.getMaeTipoServicioValor());
                        reporte.setStrPrioridad(anexo.getPrioridad() == true ? "Prioritaria" : "No Prioritaria");
                        reporte.setStrUbicacion(anexo.getMaeUbicacionValor());
                        reporte.setStrServicio(anexo.getMaServicioHabilitadoValor());
                        reporte.setStrCama(anexo.getCama());
                        reporte.setStrManejo(anexo.getMaeGuiaManejoIntegralValor());
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
                        reporte.setStrConsecutivo(anexo.getConsecutivoGen());
                        reporte.setStrDireccionAlternativa(anexo.getDireccionAlternativa());
                        reporte.setStrModalidadTecnologia(anexo.getMaeModalidadTecnologiaValor());
                        reporte.setStrFinalidadTecnologia(anexo.getMaeFinalidadTecnologiaValor());
                        listaReportes.add(reporte);
                    }
                }
            }
            bean.setObjeto(new AuAnexo3());
        } catch (Exception e) {
            listaReportes = new ArrayList();
            bean.setObjeto(new AuAnexo3());
        }
        return listaReportes;
    }

    @Override
    public List<ReporteNegacionServicio> generarReporteRechazo(int id, AuSolicitudBean bean) {
        List<ReporteNegacionServicio> listaReportes = new ArrayList();
        try {

        } catch (Exception e) {
            listaReportes = new ArrayList();
        }
        return listaReportes;
    }

    @Override
    public void listarServiocioHabilitacionTecnologia(AuSolicitudBean bean) {
        try {
            bean.setListaServicioTecnologia(new ArrayList());
            bean.setListaServicioTecnologia(getMaTecnologiaServicioHabilitacionRemoto().consultarPorTecnologia(bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void borrarTecnologia(int id, AuSolicitudBean bean) {
        try {
            AuAnexo3Item item = getAuAnexo3ItemRemoto().consultar(id);
            if (item.isPosfechado()) {
                List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), item.getMaTecnologiaId());
                listaItems = filtroPosfechado(listaItems);
                if (listaItems.isEmpty()) {
                    getAuAnexo3ItemRemoto().eliminar(id);
                } else {
                    for (AuAnexo3Item anexo3Item : listaItems) {
                        getAuAnexo3ItemRemoto().eliminar(anexo3Item.getId());
                    }
                }
            } else {
                getAuAnexo3ItemRemoto().eliminar(id);
            }

        } catch (Exception e) {
            bean.addError("No es posible borrar la tecnología, en algún momento se gestionó la cotización.");
        }
    }

    @Override
    public void borrarDiagnostico(int id, AuSolicitudBean bean) {
        try {
            getAuAnexo3DiagnosticoRemoto().eliminar(id);
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el borrado del diagnostico");
        }
    }

    @Override
    public void borrarTutela(int id, AuSolicitudBean bean) {
        try {
            getAuAnexo3TutelaRemoto().eliminar(id);
            bean.addMensaje("Se eliminó exitosamente la tutela");
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el borrado de la tutela");
        }
    }

    @Override
    public String validarTecnologia(String tipoTecnologia, String codigoTecnologia, String tipoDocumento, String numeroDocumento) {
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarCodigoTecnologia(tipoTecnologia, codigoTecnologia, tipoDocumento, numeroDocumento);
            if (respuesta.getCodigo() == 1) {
                return respuesta.getMensaje();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return "Se presento un fallo en la validación de la tecnologia, por favor ponerse en contacto con el adminitrador";
        }
    }

    @Override
    public String validarDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento) {
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarDiagnostico(codigoDiagnostico, tipoDocumento, numeroDocumento);
            if (respuesta.getCodigo() == 1) {
                return respuesta.getMensaje();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @Override
    public void buscarCotizacion(AuSolicitudBean bean) {
        try {
            AuCotizacion cotizacion = getAuCotizacionRemoto().consultarUltimoPorTipoYCodigo(bean.getObjeto().getObjetoTecnologia().getTipoTecnologia(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaCodigo());
            CntPrestadorSede sede = getCntPrestadorSedeRemoto().consultar(cotizacion.getCntPrestadorSede().getId());
            bean.getObjeto().setObjetoCotizacion(cotizacion);
            bean.getObjeto().setObjetoIps(sede);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void evaluarEstadoSolicitud(AuSolicitudBean bean) {
        try {
            boolean validar = true;
            int estado = 0;
            int estadoBkp = bean.getObjeto().getEstado();
            int contarGestionados = 0;
            bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
            for (AuAnexo3Item item : bean.getObjeto().getAuAnexo3ItemsList()) {
                if (item.getEstado() != AuAnexo3Item.ESTADO_ANULADO
                        && item.getEstado() != AuAnexo3Item.ESTADO_RECHAZO
                        && item.getEstado() != AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA
                        && item.getEstado() != AuAnexo3Item.ESTADO_APROBADO_AUDITORIA
                        && item.getEstado() != AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO
                        && item.getEstado() != AuAnexo3Item.ESTADO_DIRECCIONADO
                        && item.getEstado() != AuAnexo3Item.ESTADO_DIRECCIONADO_AUTOMATICO
                        && item.getEstado() != AuAnexo3Item.ESTADO_PREAUTORIZADO
                        && item.getEstado() != AuAnexo3Item.ESTADO_ANULADO_AUTORIZACION
                        && item.getEstado() != AuAnexo3Item.ESTADO_NEGADO_AUDITORIA) {
                    validar = false;
                    contarGestionados++;
                } else {
                    estado = AuAnexo3.ESTADO_EN_GESTION;
                }
                if (item.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_SEGUIMIENTO) {
                    validar = false;
                    estado = AuAnexo3.ESTADO_EN_GESTION;
                    break;
                }
                if (item.getEstado() == AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA) {
                    validar = false;
                    estado = AuAnexo3.ESTADO_DEVUELTO;
                    break;
                }

            }
            if (validar) {
                if (contarGestionados == bean.getObjeto().getAuAnexo3ItemsList().size()) {
                    bean.getObjeto().setEstado(AuAnexo3.ESTADO_PENDIENTE);
                } else if (contarGestionados == 0) {
                    bean.getObjeto().setEstado(AuAnexo3.ESTADO_GESTIONADO);
                } else if (contarGestionados < bean.getObjeto().getAuAnexo3ItemsList().size()) {
                    bean.getObjeto().setEstado(AuAnexo3.ESTADO_EN_GESTION);
                }
            } else {
                if (estado == 0) {
                    bean.getObjeto().setEstado(AuAnexo3.ESTADO_PENDIENTE);
                } else {
                    bean.getObjeto().setEstado(estado);
                }
            }
            try {
                getAuAnexo3Remoto().actualizarEstado(bean.getObjeto());
            } catch (Exception e) {
                try {
                    getAuAnexo3Remoto().actualizarEstado(bean.getObjeto());
                } catch (Exception e2) {
                    bean.getObjeto().addError("No se pudo actualizar el estado de la solicitud, favor comunicarse con el admnistrador");
                }
            }
            if (estadoBkp != bean.getObjeto().getEstado()) {
                guardarHistoricoAnexo3(bean, AuAnexo3Historico.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al validar los ítems para cambiar el estado de la solicitud, favor comunicarse con el administrador");
        }
    }

    public void guardarReporteAnexo4(AuSolicitudBean bean) {
        String ruta = PropApl.getInstance().get(PropApl.AU_A4_ANEXOS);
        AuReporte util = new AuReporte(bean.getHashUbicaciones());
        String mensaje;
        if (bean.getObjetoAnexo4().getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
            mensaje = util.generarReportePreAutorizacionAnexo4(ruta, bean.getObjetoAnexo4());
        } else {
            mensaje = util.generarReporteAnexo4(ruta, bean.getObjetoAnexo4());
        }
        if (!mensaje.isEmpty()) {
            bean.addError(mensaje);
        }
    }

    private boolean validarAprobacion(AuSolicitudBean bean) {
        boolean validar = true;
        try {
            // AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadoId().getId());
            Maestro maeEstado = getMaestroRemoto().consultar(bean.getObjeto().getAsegAfiliadoId().getMaeEstadoAfiliacion());
            if (maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO)) {
                validar = false;
                bean.addError("El afiliado no esta activo para la aprobación");
            }
            if (validar && bean.getObjeto().getObjetoTecnologia() != null) {
                AuAnexo3Item item = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId());
                if (!bean.validarEstadoItem(item.getEstado())) {
                    validar = false;
                    bean.addError("La tecnologia no puede ser aprobada, Refrescar");
                }
            }
            if (validar && bean.getSelectedTecnologia() != null && !bean.getSelectedTecnologia().isEmpty()) {
                for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                    item = getAuAnexo3ItemRemoto().consultar(item.getId());
                    if (!bean.validarEstadoItemTodo(item.getEstado())) {
                        validar = false;
                        break;
                    }
                }
                if (!validar) {
                    bean.addError("Alguna(s) de la(s) tencologia(s) no se pueden aprobar, Refrescar");
                }
            }
            if (validar && bean.getListaAutomaticos() != null && !bean.getListaAutomaticos().isEmpty()) {
                for (AuAnexo4Automatico automatico : bean.getListaAutomaticos()) {
                    if (!bean.validarEstadoItem(automatico.getItem().getEstado())) {
                        validar = false;
                    }
                }
                if (!validar) {
                    bean.addError("Alguna(s) de la(s) tencologia(s) no se pueden aprobar");
                }
            }
        } catch (Exception e) {
            validar = false;
            bean.addError("Hubo un fallo validando la aprobacion");
        }
        return validar;
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
    public void cambiarEstadoItemACotizacion(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean)) {
                if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                    //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                    List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                    listaItems = listaItems.stream()
                            .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                            || p.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                            || p.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                            || p.getEstado() == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION
                            ).collect(Collectors.toList());
                    for (AuAnexo3Item itemAnexo3 : listaItems) {
                        itemAnexo3.setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                        bean.auditoriaModificar(itemAnexo3);
                        getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_SOLICITUD_COTIZACION, bean.getObjeto().getObservacionAuditar());
                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                    }
                    bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    bean.addMensaje("Se cambio el estado de la tecnologia con exito");
                } else {
                    bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                    bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
                    getAuAnexo3ItemRemoto().actualizarEstado(bean.getObjeto().getObjetoTecnologia());
                    // funcionalidad para devolver anticipo
                    AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
                    if (cotizacion != null) {
                        if (cotizacion.getAntAnticiposId() != null) {
                            AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                            AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                            AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId());
                            BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                            anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                            bean.auditoriaModificar(anticipo);
                            getAnticipoRemoto().actualizarValorDisponible(anticipo);
                            procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_DEVUELVE_COTIZACION);
                        }
                    }

                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_SOLICITUD_COTIZACION, bean.getObjeto().getObservacionAuditar());
                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getObjetoTecnologia().getEstadoStr());
                    bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    bean.addMensaje("Se cambio el estado de la tecnologia con exito");
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void procesarAnticipoValor(AuSolicitudBean bean, AntAnticipo anticipo, BigDecimal valor, AntAnticipoItem item, AuCotizacion cotizacion, Integer tipoDevolucion) {
        try {
            AntAnticipoValor antAnticipoValor = new AntAnticipoValor();
            bean.auditoriaGuardar(antAnticipoValor);
            antAnticipoValor.setAntAnticiposId(anticipo);
            antAnticipoValor.setAntAnticipoItemsId(item);
            antAnticipoValor.setAuCotizacionesId(cotizacion);
            antAnticipoValor.setDevolucion(Boolean.TRUE);
            antAnticipoValor.setTipoDevolucion(tipoDevolucion);
            antAnticipoValor.setValor(valor);
            antAnticipoValor.setId(getAnticipoValorRemoto().insertar(antAnticipoValor));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cambiarEstadoItemADireccionado(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean)) {
                bean.getObjeto().getAuAnexo3ItemsList().clear();
                if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                    //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                    List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                    listaItems = listaItems.stream()
                            .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                            || p.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                            || p.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                            ).collect(Collectors.toList());
                    for (AuAnexo3Item itemAnexo3 : listaItems) {
                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_RESPUESTA_DIRECCIONADO, bean.getObjeto().getObservacionAuditar());
                        itemAnexo3.setAuAnexo3Id(bean.getObjeto());
                        itemAnexo3.setEstado(AuAnexo3Item.ESTADO_DIRECCIONADO);
                        bean.auditoriaModificar(itemAnexo3);
                        bean.getObjeto().getAuAnexo3ItemsList().add(itemAnexo3);
                    }
                } else {
                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_RESPUESTA_DIRECCIONADO, bean.getObjeto().getObservacionAuditar());
                    bean.getObjeto().getObjetoTecnologia().setAuAnexo3Id(bean.getObjeto());
                    bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_DIRECCIONADO);
                    bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
                    bean.getObjeto().getAuAnexo3ItemsList().add(bean.getObjeto().getObjetoTecnologia());
                }
                //realiza registro de direccionado
                getPeDireccionadoSolicitudRemoto().realizarSolicitudDireccionados(bean.getObjeto());
                if (bean.getObjeto().isError()) {
                    bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    bean.addErrores(bean.getObjeto().getErrors());
                } else {
                    evaluarEstadoSolicitud(bean);
                    bean.addMensaje("Se cambio el estado de la tecnologia con exito");
                }

            }
        } catch (Exception e) {
            try {
                bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                evaluarEstadoSolicitud(bean);
            } catch (Exception ex) {
                Logger.getLogger(AuSolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cambiarEstadoItemASeguimiento(AuSolicitudBean bean) {
        try {
            if (validarEstadoItem(bean)) {
                bean.getObjeto().getAuAnexo3ItemsList().clear();

                guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_RESPUESTA_SEGUIMIENTO, bean.getObservacionGenerica());
                bean.getObjeto().getObjetoTecnologia().setAuAnexo3Id(bean.getObjeto());
                bean.getObjeto().getAuAnexo3ItemsList().add(bean.getObjeto().getObjetoTecnologia());

                //realiza seguimiento
                getAuSeguimientoSolicitudRemoto().insertar(bean.getObjeto(), bean.getHashEstadosSeguimiento());
                if (bean.getObjeto().isError()) {
                    bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                    bean.addErrores(bean.getObjeto().getErrors());
                } else {
                    evaluarEstadoSolicitud(bean);
                    bean.addMensaje("Se cambio el estado de la tecnologia con exito");
                }
                bean.setObservacionGenerica(null);
            }
        } catch (Exception e) {
            try {
                bean.setObservacionGenerica(null);
                bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
                evaluarEstadoSolicitud(bean);
            } catch (Exception ex) {
                Logger.getLogger(AuSolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            bean.addError(e.getMessage());
        }
    }

    @Override
    public String validarCantidadTecnologia(String tipoTecnologia, int cantidad, String codigoTecnologia) {
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarAuCantidadTecnologia(tipoTecnologia, "" + cantidad, codigoTecnologia);
            if (respuesta.getCodigo() == 1) {
                return respuesta.getMensaje();
            } else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
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
    public void listarCotizacion(AuSolicitudBean bean) {
        try {
            bean.getParamConsulta(5).setCantidadRegistros(getAuCotizacionRemoto().consultarCantidadLista(bean.getParamConsulta(5)));
            bean.setListaCotizaciones(getAuCotizacionRemoto().consultarLista(bean.getParamConsulta(5)));
            for (AuCotizacion cotizacion : bean.getListaCotizaciones()) {
                CntPrestadorSede sede = getCntPrestadorSedeRemoto().consultar(cotizacion.getCntPrestadorSede().getId());
                cotizacion.setCntPrestadorSede(sede);
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las cotizaciones, favor comunicarse con el administrador");
        }
    }

    @Override
    public boolean validarIpsContrato(AuSolicitudBean bean) {
        try {
            boolean validar = true;
            for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                CntContratoDetalle contrato = getAuAnexo3Remoto().consultarContratoDetalle(bean.getObjeto().getObjetoIps().getId(), item.getMaTecnologiaId(), item.getTipoTecnologia());
                if (contrato == null) {
                    validar = false;
                }
            }
            return validar;
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la validacion de ips, contacte al administrador");
            return false;
        }
    }

    @Override
    public void verificarEstadoItem(AuSolicitudBean bean) {
        try {
            if (bean.getParamConsulta4() == null) {
                bean.setParamConsulta4(new ParamConsulta());
            }
            bean.getParamConsulta4().setFiltros(new HashMap());
            bean.getParamConsulta4().setParametroConsulta1(bean.getObjeto().getObjetoTecnologia().getTipoTecnologia());
            bean.getParamConsulta4().setParametroConsulta2("" + bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
            int contratos = getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta4());
            if (contratos == 0) {
                bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
            } else {
                bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA);
            }
            getAuAnexo3ItemRemoto().actualizar(bean.getObjeto().getObjetoTecnologia());
            bean.addMensaje("Se realizo la verificacion con exito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo validando estado favor comunicarse con el administrador");
        }
    }

    @Override
    public void consultarPosfechados(AuSolicitudBean bean) {
        try {
            bean.getObjeto().setListaPosfechados(getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
    }
    
    @Override
    public List<AuAnexo3Item> consultarListaPosfechados(AuSolicitudBean bean) {
        List<AuAnexo3Item> lista = new ArrayList<>();
                
        try {
            lista = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
        return lista;
    }
    
    public void gestionarPosfechado(AuSolicitudBean bean) {
        try {
            bean.getObjeto().setListaPosfechadosEditar(getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
    }

    public void guardarPosfechado(AuSolicitudBean bean) {
        try {
            int idPrincipal = 0;
            int idAnexoItem = bean.getObjeto().getObjetoTecnologia().getId();
            int contador = 0;
            for (AuRango rango : bean.getObjeto().getObjetoTecnologia().getListaRangos()) {
                AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(idAnexoItem);
                if (itemCotizacion != null) {
                    AuCotizacion cotizacion = getAuCotizacionRemoto().consultar(itemCotizacion.getAuCotizacionId().getId());
                    if (rango.getFechaLiberacion().before(cotizacion.getFechaFinVigencia())) {
                        if (cotizacion.isPagoAnticipado()) {
                            bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO);
                        } else {
                            bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_CON_COTIZACION);
                        }
                    } else {
                        bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                    }
                }
                if (contador == 0) {
                    bean.getObjeto().getObjetoTecnologia().setCantidadSolicitada(rango.getCantidad());
                    bean.getObjeto().getObjetoTecnologia().setPosfechadoPrincipal(true);
                    bean.getObjeto().getObjetoTecnologia().setPosfechado(true);
                    bean.getObjeto().getObjetoTecnologia().setFechaPosfechado(rango.getFechaLiberacion());
                    idPrincipal = bean.getObjeto().getObjetoTecnologia().getId();
                    getAuAnexo3ItemRemoto().actualizarPosfechados(bean.getObjeto().getObjetoTecnologia());
                } else {
                    bean.getObjeto().getObjetoTecnologia().setPosfechadoPrincipal(false);
                    bean.getObjeto().getObjetoTecnologia().setPosfechado(true);
                    bean.getObjeto().getObjetoTecnologia().setCantidadSolicitada(rango.getCantidad());
                    bean.getObjeto().getObjetoTecnologia().setFechaPosfechado(rango.getFechaLiberacion());
                    bean.getObjeto().getObjetoTecnologia().setId(null);
                    bean.getObjeto().getObjetoTecnologia().setId(getAuAnexo3ItemRemoto().insertar(bean.getObjeto().getObjetoTecnologia()));
                    guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getObjetoTecnologia().getEstadoStr());
                }
                contador++;
            }
            bean.getObjeto().getObjetoTecnologia().setId(idPrincipal);
            bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
            bean.addMensaje("El posfechado se realizo con exito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
    }

    public void modificarPosfechado(AuSolicitudBean bean) {
        try {
            for (AuAnexo3Item item : bean.getObjeto().getListaPosfechadosEditar()) {
                getAuAnexo3ItemRemoto().actualizarFechaPosfechado(item);
            }
            bean.addMensaje("Se realizo la actualización del posfechado con exito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
    }

    private void consultarCotizacion(AuSolicitudBean bean) {
        try {
            //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
            if (AuAnexo3Item.ESTADO_CON_COTIZACION == bean.getObjeto().getObjetoTecnologia().getEstado()
                    || AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO == bean.getObjeto().getObjetoTecnologia().getEstado()) {
                AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
                if (itemCotizacion != null) {
                    AuCotizacion cotizacion = itemCotizacion.getAuCotizacionId();
                    if (cotizacion.getActivo() && cotizacion.getFechaFinVigencia().after(new Date())) {
                        bean.getObjeto().setObjetoCotizacion(cotizacion);
                        bean.getObjeto().setObjetoIps(getCntPrestadorSedeRemoto().consultar(cotizacion.getCntPrestadorSede().getId()));
                    } else {
                        bean.getObjeto().setObjetoCotizacion(new AuCotizacion());
                        CntPrestadorSede sede = new CntPrestadorSede();
                        sede.setNombreSede("");
                        sede.setDireccion("");
                        sede.setTelefonoCitas("");
                        bean.getObjeto().setObjetoIps(sede);
                    }
                } else {
                    bean.getObjeto().setObjetoCotizacion(new AuCotizacion());
                    CntPrestadorSede sede = new CntPrestadorSede();
                    sede.setNombreSede("");
                    sede.setDireccion("");
                    sede.setTelefonoCitas("");
                    bean.getObjeto().setObjetoIps(sede);
                }
            }
        } catch (Exception e) {
            bean.addError("No se encontro una cotizacion, favor comunicarse con el administrador");
        }
    }

    private void consutarCapitaPGP(AuSolicitudBean bean) {
        try {
            //capita
            if (!Objects.equals(bean.getObjeto().getAsegAfiliadoId().getPrimariaPrestadorSede().getId(),
                    bean.getObjeto().getCntPrestadorSedeId().getId())) {
                if (bean.getObjeto().getObjetoTecnologia() != null) {
                    asignarContratoCapita(bean, bean.getObjeto().getObjetoTecnologia());
                } else if (bean.getSelectedTecnologia() != null) {
                    for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                        asignarContratoCapita(bean, item);
                        int posicionItem = bean.getSelectedTecnologia().indexOf(item);
                        bean.getSelectedTecnologia().set(posicionItem, item);
                    }
                }
            }
            //PGP
            if (bean.getObjeto().getObjetoTecnologia() != null) {
                asignarProgramasPGP(bean, bean.getObjeto().getObjetoTecnologia());
            } else if (bean.getSelectedTecnologia() != null) {
                for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                    asignarProgramasPGP(bean, item);
                    int posicionItem = bean.getSelectedTecnologia().indexOf(item);
                    bean.getSelectedTecnologia().set(posicionItem, item);
                }
            }

        } catch (Exception exception) {
            bean.addError("Error validacion contratos capita/PGP, favor comunicarse con el administrador");
        }
    }

    private void consultarContrato(AuSolicitudBean bean) {
        try {
            bean.getObjetoContratoDetalle().setCntContrato(getCntContratoRemoto().consultar(bean.getObjetoContratoDetalle().getCntContrato().getId()));
            bean.getObjetoContratoDetalle().setListAutorizacionesContratoDetalle(getAuAnexo4ItemRemoto().consultarAutorizacionByTecnologiaWithContratoDetalle(bean.getObjetoContratoDetalle().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId()));
            bean.getObjetoContratoDetalle().setListAutorizacionesFechasPeriodicasContratoDetalle(getAuAnexo4ItemRemoto().consultarAutorizacionByFechasWithContratoDetalle(bean.getObjetoContratoDetalle().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId()));
            bean.getObjetoContratoDetalle().setListAutorizacionesContratoDetalleAfiliados(getAuAnexo4ItemRemoto().consultarAutorizacionByTecnologiaWithContratoDetalleAsegAfiliado(bean.getObjetoContratoDetalle().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId(),  bean.getObjeto().getAsegAfiliadoId().getId()));
            BigDecimal porcentajeContratoAutorizado = (bean.getObjetoContratoDetalle().getCntContrato().getEjecucionContratoAutorizado() == null) ? new BigDecimal(0):
                bean.getObjetoContratoDetalle().getCntContrato().getEjecucionContratoAutorizado().divide(bean.getObjetoContratoDetalle().getCntContrato().getValorPresupuestoTotal(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            bean.getObjetoContratoDetalle().getCntContrato().setPorcentajeEjecucionContratoAutorizado(porcentajeContratoAutorizado.doubleValue());
        } catch (Exception exception) {
            bean.addError("Error consultando contratos, favor comunicarse con el administrador");
        }
    }

    private void asignarContratoCapita(AuSolicitudBean bean, AuAnexo3Item item) throws Exception {
        if (AuAnexo3Item.TIPO_TECNOLOGIA_CUP == item.getTipoTecnologia()) {
            if (bean.getObjeto().getAsegAfiliadoId().getModeloLiquidacion().equals(AuConstantes.MODELO_LIQUIDACION_CAPITA)) {
                portabilidadAfiliado(bean);
                int idPrestador;
                int idUbicacion;
                if (bean.getObjeto().getAsegAfiliadoId().getTienePortabilidad()) {
                    if (bean.getObjeto().getAsegAfiliadoId().getPortabilidadPrestadorSede() != null) {
                        idPrestador = bean.getObjeto().getAsegAfiliadoId().getPortabilidadPrestadorSede().getCntPrestador().getId();
                        idUbicacion = bean.getObjeto().getAsegAfiliadoId().getPortabilidadPrestadorSede().getUbicacionId();
                    } else {
                        idPrestador = bean.getObjeto().getAsegAfiliadoId().getPrimariaPrestadorSede().getCntPrestador().getId();
                        idUbicacion = bean.getObjeto().getAsegAfiliadoId().getResidenciaUbicacion().getId();
                    }
                } else {
                    idPrestador = bean.getObjeto().getAsegAfiliadoId().getPrimariaPrestadorSede().getCntPrestador().getId();
                    idUbicacion = bean.getObjeto().getAsegAfiliadoId().getResidenciaUbicacion().getId();
                }
                List<CntContrato> list = getCntContratoRemoto().consultarPorPrestadorSede(
                        idPrestador,
                        item.getTipoTecnologia(),
                        item.getMaTecnologiaId(),
                        AuConstantes.MODALIDAD_CONTRATO_CAPITA, new Date(),
                        idUbicacion
                );

                if (list != null && !list.isEmpty()) {
                    item.setCapitaPGP(AuConstantes.TEXTO_SI);
                    item.setContratosCapita(
                            list.stream().map(m -> m.getContrato())
                                    .collect(Collectors.joining(","))
                    );
                    bean.setCapitaPGPAprobarTodo(true);
                } else {
                    item.setCapitaPGP(AuConstantes.TEXTO_NO);
                }
            }

        }
    }

    private void asignarProgramasPGP(AuSolicitudBean bean, AuAnexo3Item item) throws Exception {
        if (AuAnexo3Item.TIPO_TECNOLOGIA_CUP == item.getTipoTecnologia()) {
            List<PePrograma> programas = getPeProgramaRemoto().programasAfiliadosTecnologia(
                    bean.getObjeto().getAsegAfiliadoId().getId(), item.getMaTecnologiaId(),
                    item.getTipoTecnologia()
            );

            if (programas != null && !programas.isEmpty()) {
                item.setCapitaPGP(AuConstantes.TEXTO_SI);
                item.setProgramasPGP(
                        programas.stream().map(m -> m.getDescripcionPrograma())
                                .collect(Collectors.joining(","))
                );
                bean.setCapitaPGPAprobarTodo(true);
            } else {
                if (item.getCapitaPGP() == null
                        || !item.getCapitaPGP().equals(AuConstantes.TEXTO_SI)) {
                    item.setCapitaPGP(AuConstantes.TEXTO_NO);
                }
            }

        }
    }

    @Override
    public void consultarAdjuntosCotizacion(AuSolicitudBean bean
    ) {
        try {
            //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
            if (AuAnexo3Item.ESTADO_CON_COTIZACION == bean.getObjeto().getObjetoTecnologia().getEstado()
                    || AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO == bean.getObjeto().getObjetoTecnologia().getEstado()
                    || AuAnexo3Item.ESTADO_APROBADO_AUDITORIA == bean.getObjeto().getObjetoTecnologia().getEstado()) {
                AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
                if (itemCotizacion != null) {
                    bean.setListaAdjuntosCotizacion(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdCotizacion(itemCotizacion.getAuCotizacionId().getId()));
                }
            }

            AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
            if (cotizacion != null) {
                if (cotizacion.getAntAnticiposId() != null) {
                    bean.setAntAnticipoAdjuntosList(getAnticipoAdjuntoRemoto().consultarAdjuntoPorAnticipoId(cotizacion.getAntAnticiposId().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError("No se encontro una cotizacion, favor comunicarse con el administrador");
        }
    }

    private boolean validarEstadoItem(AuSolicitudBean bean) {
        boolean validar = true;
        try {
            bean.getObjeto().setObjetoTecnologia(getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId()));
            //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
            if (bean.getObjeto().getObjetoTecnologia().getEstado() != AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                    && bean.getObjeto().getObjetoTecnologia().getEstado() != AuAnexo3Item.ESTADO_CON_COTIZACION
                    && bean.getObjeto().getObjetoTecnologia().getEstado() != AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                    && bean.getObjeto().getObjetoTecnologia().getEstado() != AuAnexo3Item.ESTADO_RECHAZO_COTIZACION) {
                validar = false;
                bean.addError("El ítem no tiene una acción valida, por favor refrescar la página");
            }
        } catch (Exception e) {
            bean.addError("El ítem no tiene una acción valida, por favor refrescar la página.");
            validar = false;
        }
        return validar;
    }

    private boolean validarEstadoItems(AuSolicitudBean bean) {
        boolean validar = true;
        try {
            for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                item = getAuAnexo3ItemRemoto().consultar(item.getId());
                //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                if (item.getEstado() != AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                        && item.getEstado() != AuAnexo3Item.ESTADO_CON_COTIZACION
                        && item.getEstado() != AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                        && item.getEstado() != AuAnexo3Item.ESTADO_RECHAZO_COTIZACION) {
                    validar = false;
                    bean.addError("El ítem no tiene una acción valida, por favor refrescar la página");
                    break;
                }
            }
        } catch (Exception e) {
            bean.addError("El ítem no tiene una acción valida, por favor refrescar la página.");
            validar = false;
        }
        return validar;
    }

    private void verDevoluciones(AuSolicitudBean bean) {
        try {
            ver(bean);
            List<AuAnexo3Item> listaDevoluciones = new ArrayList();
            bean.getObjeto().setAuAnexo3ItemsList(getAuAnexo3ItemRemoto().listaItemsByAnexo3Id(bean.getObjeto().getId()));
            for (AuAnexo3Item item : bean.getObjeto().getAuAnexo3ItemsList()) {
                if (item.getEstado() == AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA) {
                    listaDevoluciones.add(item);
                }
            }
            bean.getObjeto().setEsEditar(true);
            bean.getObjeto().setListaDevoluciones(listaDevoluciones);
            if (bean.getObjeto().getAuAnexo3ItemsList().size() == bean.getObjeto().getListaDevoluciones().size()) {
                bean.getObjeto().setEsEditar(false);
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consultar las devoluciones, favor comunicarse con el administrador");
        }
    }

    private void guardarDevoluciones(AuSolicitudBean bean) {
        try {
            if (bean.getObjeto().getMaServicioHabilitadoId() == null) {
                bean.addError("Debe indicar el servicio habilitado");
            }
            if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty() == true) {
                bean.addError("Debe agregar al menos un ítem para crear la solicitud");
            }
            if (bean.isError() == false) {
                completarMaestroHistoricos(bean);
                AuAnexo3 solicitud = bean.getObjeto();

                //Guardar diagnosticos
                for (AuAnexo3Diagnostico diagnostico : solicitud.getAuAnexo3DiagnosticosList()) {
                    diagnostico.setAuAnexos3Id(solicitud);
                    if (diagnostico.getId() == null) {
                        bean.auditoriaGuardar(diagnostico);
                        getAuAnexo3DiagnosticoRemoto().insertar(diagnostico);
                    } else {
                        bean.auditoriaModificar(diagnostico);
                        getAuAnexo3DiagnosticoRemoto().actualizar(diagnostico);
                    }
                }
                //Guardar ítems
                if (bean.getObjeto().getObjetoTecnologia().getId() != null) {
                    if (bean.getParamConsulta4() == null) {
                        bean.setParamConsulta4(new ParamConsulta());
                    }
                    bean.getParamConsulta4().setFiltros(new HashMap());
                    bean.getParamConsulta4().setParametroConsulta1(bean.getObjeto().getObjetoTecnologia().getTipoTecnologia());
                    bean.getParamConsulta4().setParametroConsulta2("" + bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                    int contratos = getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta4());
                    if (bean.getObjeto().getObjetoTecnologia().isPosfechado()) {
                        List<AuAnexo3Item> listaItems = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getId(), bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId());
                        listaItems = listaItems.stream()
                                .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA
                                ).collect(Collectors.toList());
                        for (AuAnexo3Item itemAnexo3 : listaItems) {
                            if (contratos == 0) {
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                            } else {
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA);
                            }
                            bean.auditoriaModificar(itemAnexo3);
                            getAuAnexo3ItemRemoto().actualizar(itemAnexo3);
                            guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_RESPUESTA_DEVOLUCION, bean.getObjeto().getObservacionAuditar());
                            guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                        }
                    } else {
                        if (contratos == 0) {
                            bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                        } else {
                            bean.getObjeto().getObjetoTecnologia().setEstado(AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA);
                        }
                        bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
                        getAuAnexo3ItemRemoto().actualizar(bean.getObjeto().getObjetoTecnologia());

                        guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_RESPUESTA_DEVOLUCION, bean.getObjeto().getObservacionAuditar());
                        guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getObjetoTecnologia().getEstadoStr());
                    }

                    Iterator<AuAnexo3Item> items = bean.getObjeto().getListaDevoluciones().iterator();

                    while (items.hasNext()) {
                        AuAnexo3Item item = items.next();
                        if (item.getId().equals(bean.getObjeto().getObjetoTecnologia().getId())) {
                            items.remove();
                        }
                    }
                }
                //Guardar ítem nuevos
                for (AuAnexo3Item item : solicitud.getAuAnexo3ItemsList()) {
                    if (item.getId() == null) {
                        item.setAuAnexo3Id(solicitud);
                        completarItems(bean);
                        if (bean.getParamConsulta4() == null) {
                            bean.setParamConsulta4(new ParamConsulta());
                        }
                        bean.getParamConsulta4().setFiltros(new HashMap());
                        bean.getParamConsulta4().setParametroConsulta1(item.getTipoTecnologia());
                        bean.getParamConsulta4().setParametroConsulta2("" + item.getMaTecnologiaId());
                        int contratos = getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta4());
                        if (contratos == 0) {
                            item.setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                        } else {
                            item.setEstado(AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA);
                        }
                        bean.auditoriaGuardar(item);
                        if (item.isPosfechado()) {
                            int contador = 0;
                            for (AuRango rango : item.getListaRangos()) {
                                if (contador == 0) {
                                    item.setPosfechadoPrincipal(true);
                                } else {
                                    item.setPosfechadoPrincipal(false);
                                }
                                item.setPosfechado(true);
                                item.setCantidadSolicitada(rango.getCantidad());
                                item.setFechaPosfechado(rango.getFechaLiberacion());
                                int id = getAuAnexo3ItemRemoto().insertar(item);
                                guardarItemBitacora(new AuAnexo3Item(id), bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                                contador++;
                            }
                        } else {
                            int id = getAuAnexo3ItemRemoto().insertar(item);
                            guardarItemBitacora(new AuAnexo3Item(id), bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                        }
                    }
                }
                //Guardar adjuntos
                for (AuSolicitudAdjunto adjunto : solicitud.getAuSolicitudAdjuntosList()) {
                    adjunto.setAuAnexo3(solicitud);
                    if (adjunto.getId() == null) {
                        bean.auditoriaGuardar(adjunto);
                        guardarAdjunto(bean, adjunto);
                    }
                }
                //Guardar tutelas
                guardarTutelas(bean);
                //Guardar el afiliado
                int idAfiliado = 0;
                AuAnexo3Afiliado afiliado = new AuAnexo3Afiliado();
                for (AuAnexo3Afiliado afil : solicitud.getAuAnexo3AfiliadosList()) {
                    if (afil.getMaeTipoDocumentoId() == solicitud.getAsegAfiliadoId().getMaeTipoDocumento()
                            && afil.getNumeroIdentificacion().equals(solicitud.getAsegAfiliadoId().getNumeroDocumento())) {
                        idAfiliado = afil.getId();
                        afiliado = afil;
                    }
                }
                if (idAfiliado == 0) {
                    afiliado = casteoAuAnexo3Afiliado(solicitud.getAsegAfiliadoId());
                    Maestro tipoDoc = bean.obtenerMaestroTipoDocumento(afiliado.getMaeTipoDocumentoId());

                    if (tipoDoc != null) {
                        afiliado.setAuAnexo3Id(solicitud);
                        afiliado.setMaeTipoDocumentoCodigo(tipoDoc.getValor());
                        afiliado.setMaeTipoDocumentoValor(tipoDoc.getNombre());
                        bean.auditoriaGuardar(afiliado);
                        getAuAnexo3AfiliadoRemoto().insertar(afiliado);
                    }
                } else {
                    afiliado.setId(idAfiliado);
                    bean.auditoriaModificar(afiliado);
                    getAuAnexo3AfiliadoRemoto().actualizar(afiliado);
                }
                bean.auditoriaModificar(bean.getObjeto());
                evaluarEstadoSolicitud(bean);
//                getAuAnexo3Remoto().actualizar(solicitud);
                bean.addMensaje("La solicitud " + solicitud.getId() + " fue actualizada exitosamente");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar las devoluciones, favor comunicarse con el administrador");
            evaluarEstadoSolicitud(bean);
        }
    }

    @Override
    public void verBitacoras(AuSolicitudBean bean) {
        try {
            bean.getObjeto().setListaBitacoras(getAuItemBitacoraRemoto().listarPorIdItem(bean.getObjeto().getObjetoTecnologia().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las bitacoras");
        }
    }

    private boolean validarAcciones(AuSolicitudBean bean) {
        boolean validar = true;
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadoId().getId());
            Maestro maeEstado = getMaestroRemoto().consultar(afiliado.getMaeEstadoAfiliacion());
            if (!maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
//            if (afiliado.getMaeEstadoAfiliacion() != bean.getIdEstadoAfiliado().getId()) {
                validar = false;
                bean.addError("El afiliado no esta activo para la aprobación");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo haciendo validaciones para acciones");
            validar = false;
        }
        return validar;
    }

    @Override
    public AuAnexo4Item verAutorizacionAnterior(AuSolicitudBean bean, int idItem, int tipoItem) {
        AuAnexo4Item anexoItem;
        try {
            anexoItem = getAuAnexo4ItemRemoto().consultarUltimoPorItem(idItem, tipoItem, bean.getObjeto().getAsegAfiliadoId().getId());
            if (anexoItem != null) {
                bean.getListaHistoricoAnexo4().add(anexoItem);
            }
        } catch (Exception e) {
            anexoItem = null;
            bean.addError("Hubo un fallo consultando la ultima autorizacion del ítem " + idItem);
        }
        return anexoItem;
    }

    @Override
    public List<AuAnexo4Item> verAutorizaciones(AuSolicitudBean bean, int idItem, int tipoItem) {
        List<AuAnexo4Item> anexoItem;
        try {
            anexoItem = getAuAnexo4ItemRemoto().consultarPorItems(idItem, tipoItem, bean.getObjeto().getAsegAfiliadoId().getId());
            if (anexoItem != null) {
                bean.getListaHistoricoAnexo4().addAll(anexoItem);
            }
        } catch (Exception e) {
            anexoItem = null;
            bean.addError("Hubo un fallo consultando las ultimas autorizaciones del ítem " + idItem);
        }
        return anexoItem;
    }

    private void verDerivacion(AuSolicitudBean bean) {
        try {
            //Carga de items
            List<AuAnexo3Item> lista = new ArrayList();
            if (bean.getObjeto().getObjetoTecnologia() != null) {
                bean.getObjeto().setObjetoTecnologia(getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId()));
                //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                if (bean.getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                        || bean.getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION || bean.getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO) {
                    lista.add(bean.getObjeto().getObjetoTecnologia());
                } else {
                    bean.addError("El ítem " + bean.getObjeto().getObjetoTecnologia().getMaTecnologiaValor() + " no tiene el estado valido");
                    return;
                }
            }
            if (bean.getSelectedTecnologia() != null && !bean.getSelectedTecnologia().isEmpty()) {
                for (AuAnexo3Item item : bean.getSelectedTecnologia()) {
                    item = getAuAnexo3ItemRemoto().consultar(item.getId());
                    //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                    if (item.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA || item.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION || item.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO) {
                        lista.add(item);
                    } else {
                        bean.addError("El ítem " + item.getMaTecnologiaValor() + " no tiene el estado valido");
                        return;
                    }
                }
            }
            if (lista.isEmpty()) {
                bean.addError("No se seleccionaron tecnologías");
                return;
            } else {
                bean.setListaItemsDerivar(lista);
            }
            int grupo = 0;
            for (AuAnexo3Item item : lista) {
                grupo = item.getAuGrupoId().getId();
            }
            //Carga de listas
            bean.setListaGrupos(getAuGrupoRemoto().consultarOtrosActivos(grupo));

        } catch (Exception e) {
            bean.addError("Hubo un fallo cargando la derivación, favor comunicarse con el administrador");
        }
    }

    @Override
    public boolean validarTecnologiaPosfechada(int idAfiliado, int idTecnologia, AuSolicitudBean bean) {
        boolean validar = true;
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarPosfechado(idAfiliado, idTecnologia);
            if (respuesta != null) {
                switch (respuesta.getCodigo()) {
                    case 0:
                        validar = false;
                        break;
                    case 1:
                        bean.setMensajeTecnologia(respuesta.getMensaje());
                        break;
                    case 99:
                        validar = false;
                        bean.addError("Hubo un fallo en la función de validar posfechado, favor contactar al adminitrador");
                        break;
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo validar posfechado, favor contactar al adminitrador");
            validar = false;
        }
        return validar;
    }

    private CsCopagoModeradoraHistorico castearCopagoModeradora(AuAnexo4 anexo4) {
        SimpleDateFormat formatoAgno = new SimpleDateFormat("yyyy");
        String agno = formatoAgno.format(new Date());
        CsCopagoModeradoraHistorico historico = new CsCopagoModeradoraHistorico();
        historico.setAsegAfiliadosId(anexo4.getAsegAfiliadoId());
        historico.setIdAfiliado(anexo4.getAsegAfiliadoId().getIdAfiliado());
        historico.setAuAnexos4Id(anexo4);
        historico.setAgno(Integer.parseInt(agno));
        historico.setMaeRegimenId(anexo4.getMaeRegimenId());
        historico.setMaeRegimenCodigo(anexo4.getMaeRegimenCodigo());
        historico.setMaeRegimenValor(anexo4.getMaeRegimenValor());
        if (anexo4.getAplicaCopago() != null) {
            if (anexo4.getAplicaCopago()) {
                historico.setModeradoraCopago(CsCopagoModeradoraHistorico.COPAGO);
                historico.setValorModeradora(anexo4.getValorCopago().intValue());
            } else if (anexo4.isAplicaCuotaModeradora()) {
                historico.setModeradoraCopago(CsCopagoModeradoraHistorico.MODERADORA);
                historico.setValorModeradora(anexo4.getValorCuotaModeradora().intValue());
            }
        } else if (anexo4.isAplicaCuotaModeradora()) {
            historico.setModeradoraCopago(CsCopagoModeradoraHistorico.MODERADORA);
            historico.setValorModeradora(anexo4.getValorCuotaModeradora().intValue());
        }

        historico.setValorProyectado(historico.getValorModeradora());
        try {
            Maestro tipoAfiliado = getMaestroRemoto().consultarPorValorTipo(anexo4.getAsegAfiliadoId().getTipoBeneficiario(), "38");
            historico.setMaeTipoAfiliadoId(tipoAfiliado.getId());
            historico.setMaeTipoAfiliadoCodigo(tipoAfiliado.getValor());
            historico.setMaeTipoAfiliadoValor(tipoAfiliado.getNombre());
        } catch (Exception e) {
            historico.setMaeTipoAfiliadoId(0);
            historico.setMaeTipoAfiliadoCodigo("Sin tipo");
            historico.setMaeTipoAfiliadoValor("Sin tipo");
        }
        historico.setMaeNivelSisbenId(anexo4.getAsegAfiliadoId().getMaeNivelSisbenId());
        historico.setMaeNivelSisbenCodigo(anexo4.getAsegAfiliadoId().getMaeNivelSisbenCodigo());
        historico.setMaeNivelSisbenValor(anexo4.getAsegAfiliadoId().getMaeNivelSisbenValor());
        historico.setPorcentajeCopago(anexo4.getPorcentajeRecuperacion());
        historico.setCategoriaIbc(anexo4.getAsegAfiliadoId().getCategoriaIbc());
        return historico;
    }

    @Override
    public void verBitacorasAnexo3(AuSolicitudBean bean) {
        try {
//            bean.getObjeto().setListaBitacoras(castEntidadNegocioBitacora(getAuAnexo3HistoricoRemoto().listarPorIdItem(bean.getObjeto().getId())));
            bean.getObjeto().setAuAnexo3HistoricosList(
                    getAuAnexo3HistoricoRemoto().listarPorIdItem(bean.getObjeto().getId())
            );
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las historicos");
        }
    }

    @Override
    public void guardarBitacora(AuSolicitudBean bean) {
        try {
            //AuAnexo3 anexo3DB = getAuAnexo3Remoto().consultar(bean.getObjeto().getId());
            //2024-01-25 jyperez se eliminan las validaciones de estados de anexo3 y anexo3item
            /*if (anexo3DB.getEstado() == AuAnexo3.ESTADO_PENDIENTE || anexo3DB.getEstado() == AuAnexo3.ESTADO_EN_GESTION) {
                AuAnexo3Item item = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getObjetoTecnologia().getId());
                //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                if (item.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                        || item.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                        || item.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                        || item.getEstado() == AuAnexo3Item.ESTADO_SIN_COTIZACION
                        || item.getEstado() == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION) {*/
            guardarItemBitacora(bean.getObjeto().getObjetoTecnologia(), bean, AuItemBitacora.ID_COMENTARIO, bean.getObjeto().getObservacionAuditar());
            bean.getObjeto().getObjetoTecnologia().setAuItemBitacorasList(
                    getAuItemBitacoraRemoto().listarPorIdItem(bean.getObjeto().getObjetoTecnologia().getId())
            );
            bean.getObjeto().setObservacionAuditar("");
            bean.addMensaje("Guardo comentario bitacora de forma correcta");
            /*} else {
                    bean.addError("No se guardo comentario bitacora, debido a que ya cambio de estado.");
                }
            } else {
                bean.addError("No se guardo comentario bitacora, debido a que ya cambio de estado.");
            }*/

        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar la bitacora, intentelo nuevamente");
        }
    }

    @Override
    public void verRescatesAnexo3(AuSolicitudBean bean) {
        try {
            bean.getObjeto().setAuAnexo2RescateList(
                    getAuAnexo2RescateRemoto().consultarPorAnexo3(bean.getObjeto().getId())
            );
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los rescates");
        }
    }

    @Override
    public void verRescate(AuSolicitudBean bean) {
        try {
            bean.setObjetoRescate(getAuAnexo2RescateRemoto().consultar(bean.getObjetoRescate().getId()));
            bean.getObjetoRescate().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjetoRescate().getAsegAfiliado().getFechaNacimiento()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando el rescate");
        }
    }

    @SuppressWarnings("unused")
    private List<AuItemBitacora> castEntidadNegocioBitacora(List<AuAnexo3Historico> entidades) {
        List<AuItemBitacora> lista_bitacoras = new ArrayList<>();
        for (AuAnexo3Historico entidad : entidades) {
            AuItemBitacora negocio = new AuItemBitacora();
            negocio.setId(entidad.getId());
            negocio.setDescripcion(entidad.getObservacion());
            negocio.setTipo(entidad.getTipo() == 3 ? AuItemBitacora.ID_ANULACION_SOLICITUD : entidad.getTipo());
            negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
            negocio.setTerminalCrea(entidad.getTerminalCrea());
            negocio.setUsuarioCrea(entidad.getUsuarioCrea());
            lista_bitacoras.add(negocio);
        }
        return lista_bitacoras;
    }

    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, AuSolicitudBean bean) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            if (maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                return true;
            } else {
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra " + maeEstado.getNombre());
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    @Override
    public AuAnexo3Item consultarTecnologiaId(int id) {
        AuAnexo3Item item = new AuAnexo3Item();
        try {
            item = getAuAnexo3ItemRemoto().consultar(id);
        } catch (Exception ex) {
            Logger.getLogger(AuSolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    @Override
    public void actualizarProcesoActual(AuSolicitudBean bean) {
        try {
            Thread.sleep(2000);
            int estadoProcesoActual = getAuAnexo3Remoto().consultarProcesoActual(bean.getObjeto().getId());
            if (bean.getAccion() == Url.ACCION_ADICIONAL_2
                    && estadoProcesoActual == AuAnexo3.ESTADO_PROCESO_ACTUAL_AUDITORIA
                    || (bean.getAccion() == Url.ACCION_EDITAR
                    && estadoProcesoActual == AuAnexo3.ESTADO_PROCESO_ACTUAL_EDICION)) {
                bean.getObjeto().setEstadoProcesoActual(AuAnexo3.ESTADO_PROCESO_ACTUAL_LIBRE);
                getAuAnexo3Remoto().actualizarEstadoProcesoActual(bean.getObjeto());
            }
        } catch (Exception e) {
            bean.addError("Proceso actual " + e.getMessage());
        }
    }

    @Override
    public boolean validarItemDireccionado(AuSolicitudBean bean) {
        boolean flagValidacion = false;
        List<AuAnexo3Item> listTemp = new ArrayList();
        listTemp.addAll(bean.getObjeto().getAuAnexo3ItemsList());
        try {
            bean.getObjeto().getAuAnexo3ItemsList().clear();
            bean.getObjeto().getAuAnexo3ItemsList().add(bean.getObjeto().getObjetoTecnologia());
            //validar si aplica direccionado
            flagValidacion = getPeDireccionadoSolicitudRemoto().aplicaDireccionado(bean.getObjeto());
            bean.getObjeto().setAuAnexo3ItemsList(listTemp);
        } catch (Exception ex) {
            bean.getObjeto().setAuAnexo3ItemsList(listTemp);
            Logger.getLogger(AuSolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError("Hubo un fallo al validar item direccionado, intentelo nuevamente");
        }
        return flagValidacion;
    }

    @Override
    public void validarSugeridoParaBorrar(AuSolicitudBean bean) {

        try {
            PeAfiliadoSugerido sugerido = getPeAfiliadoSugeridoRemoto().consultar(bean.getObjetoSugerido().getId());
            if (sugerido != null) {
                if (sugerido.getEstado() == PeAfiliadoSugerido.ESTADO_MARCADO) {
                    bean.addError("No puede borrar sugerido se encuentra matriculado");
                } else if (sugerido.getEstado() == PeAfiliadoSugerido.ESTADO_RECHAZADO) {
                    bean.addError("No puede borrar sugerido se encuentra rechazado");
                }
            } else {
                bean.addError("No puede borrar sugerido se encuentra matriculado");
            }
        } catch (Exception e) {
            bean.addError("No puede borrar sugerido se encuentra matriculado");
        }
    }

    @Override
    public void validarItemSeguimiento(AuSolicitudBean bean) {
        boolean flagValidacion;

        try {
            flagValidacion = getAuSeguimientoRemoto().validarExisteSeguimiento(bean.getObjeto().getObjetoTecnologia().getId(), bean.getObjeto().getId());
            if (flagValidacion) {
                bean.addError("Ya existe seguimiento del item seleccionado.");
            } else {
                flagValidacion = getAuSeguimientoSolicitudRemoto().validarAplicaSeguimiento(bean.getObjeto().getObjetoTecnologia());
                if (!flagValidacion) {
                    bean.addError("El item no aplica para seguimiento.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AuSolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError("Hubo un fallo al validar item seguimiento, intentelo nuevamente");
        }
    }

    @Override
    public void buscarTutelasAfiliado(AuSolicitudBean bean) throws Exception {
        //Buscar las tutelas asociadas al usuario
        bean.setRespuestaTutela(new TuTutelaRespuesta());
        List<TuTutelaRespuesta> listaTutela;
//                listaTutela = servicioTutela.consumoServicioTutela(tipoDocumento, bean.getObjeto().getAsegAfiliadoId().getNumeroDocumento());
        listaTutela = getTutelaRemoto().consultarConExoneracionAfiliadoPorId(bean.getObjeto().getAsegAfiliadoId().getId(), Integer.valueOf(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.REST_TIPO_ESTADO_FALLO)));
        if (!listaTutela.isEmpty()) {
            bean.setListaTutelas(listaTutela);
        } else {
            bean.setListaTutelas(new ArrayList());
        }
    }

    @Override
    public void guardarTutelas(AuSolicitudBean bean) throws Exception {
        for (AuAnexo3Tutela tutela : bean.getObjeto().getAuAnexo3TutelasList()) {
            if (tutela.getId() == null) {
                tutela.setAuAnexo3Id(bean.getObjeto());
                bean.auditoriaGuardar(tutela);
                getAuAnexo3TutelaRemoto().insertar(tutela);
            }
        }
    }

    @Override
    public void consultarGestionRiegosSugerido(AuSolicitudBean bean) {

        try {
            bean.setListaTiposSugeridoAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_ADJUNTO_TIPO));
            bean.setHashTiposSugeridoAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposSugeridoAdjuntos()));

            List<PePrograma> listPreprogramas = getPeProgramaRemoto().programasNoMatriculadosSugeridosSolicitud(bean.getObjeto().getAsegAfiliadoId().getId());
            if (!listPreprogramas.isEmpty()) {
                bean.getObjeto().setAucSugerirProgramaList(listPreprogramas);
                bean.setHashPePrograma(convertToHashPePrograma(bean.getObjeto().getAucSugerirProgramaList()));
            }

        } catch (Exception e) {
            bean.addError("No puedo consultar diagnosticos de ingreso");
        }
    }

    @Override
    public void listarGestionRiesgo(AuSolicitudBean bean) {
        try {
            bean.setListaAfiliadoPrograma(getPeAfiliadosProgramaRemoto().consultarAfiliados(bean.getObjeto().getAsegAfiliadoId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public AuCotizacion consultarCotizacionAnticipo(AuSolicitudBean bean) {
        AuCotizacion cotizacion = null;
        try {
            cotizacion = getAuCotizacionRemoto().consultar(bean.getObjeto().getObjetoCotizacion().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return cotizacion;
    }

    @Override
    public AuCotizacion consultarCotizacionAnticipoByAnexo3(AuSolicitudBean bean) {
        AuCotizacion cotizacion = null;
        try {
            cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getObjetoTecnologia().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return cotizacion;
    }

    @Override
    public AuCotizacion consultarCotizacionAnticipoByAnexo3(int idAnexo3Item, AuSolicitudBean bean) {
        AuCotizacion cotizacion = null;
        try {
            cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(idAnexo3Item);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return cotizacion;
    }

    @Override
    public AuAnexo3Item consultarAnxo3Item(int idAnexo3Item, AuSolicitudBean bean) {
        AuAnexo3Item auAnexo3Item = null;
        try {
            auAnexo3Item = getAuAnexo3ItemRemoto().consultar(idAnexo3Item);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return auAnexo3Item;
    }

    @Override
    public AuAnexo3 consultarAnexo3(int idAnexo3, AuSolicitudBean bean) {
        AuAnexo3 anexo3 = null;
        try {
            anexo3 = getAuAnexo3Remoto().consultar(idAnexo3);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return anexo3;
    }

    @Override
    public AntAnticipo consultarAnticipoBYafiliado(int idAfiliado, int idTecnologia, AuSolicitudBean bean) {
        AntAnticipo anticipoAfiliadoYtecnologia = null;
        try {
            anticipoAfiliadoYtecnologia = getAnticipoRemoto().consultarAnticipoAfiliadoYTecnologia(idAfiliado, idTecnologia);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return anticipoAfiliadoYtecnologia;
    }

    @Override
    public AntAnticipo consultarAnticipoBYTecnologia(int idTecnologia, AuSolicitudBean bean) {
        AntAnticipo anticipoTecnologia = null;
        try {
            anticipoTecnologia = getAnticipoRemoto().consultarAnticipoByTecnologia(idTecnologia);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return anticipoTecnologia;
    }

    @Override
    public List<AntAnticipo> consultarAnticipoBYafiliadoList(int idAfiliado, int idTecnologia, AuSolicitudBean bean) {
        List<AntAnticipo> anticipoAfiliadoYtecnologia = null;
        try {
            anticipoAfiliadoYtecnologia = getAnticipoRemoto().consultarAnticipoAfiliadoYTecnologiaList(idAfiliado, idTecnologia);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return anticipoAfiliadoYtecnologia;
    }

    @Override
    public List<AntAnticipo> consultarAnticipoBYTecnologiaList(int idTecnologia, AuSolicitudBean bean) {
        List<AntAnticipo> anticipoTecnologia = null;
        try {
            anticipoTecnologia = getAnticipoRemoto().consultarAnticipoByTecnologiaList(idTecnologia);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return anticipoTecnologia;
    }

    @Override
    public void validarTecnologiaGrupoAprueba(AuSolicitudBean bean) {
        try {
            if (bean.getObjeto().getObjetoTecnologia().getAuGrupoId() != null) {
                int grupo = bean.getObjeto().getObjetoTecnologia().getAuGrupoId().getId();
                boolean validacion = getAuGrupoTecnologiaRemoto().validarTecnologiaGrupoTipoUsuario(
                        bean.getObjeto().getObjetoTecnologia().getMaTecnologiaId(), bean.getObjeto().getObjetoTecnologia().getTipoTecnologia(),
                        grupo, bean.getConexion().getUsuario().getId()
                );

                if (!validacion) {
                    bean.addError(bean.getObjeto().getObjetoTecnologia().getMaTecnologiaValor().concat("-No cumple los criterios para aprobar la tecnología."));
                }
            } else {
                bean.addError("Tecnología sin grupo asignado.");
            }

        } catch (Exception e) {
            bean.addError("Error al consultar validacion de tecnologia");
        }
    }

    public HashMap<Integer, PePrograma> convertToHashPePrograma(List<PePrograma> list) {
        HashMap<Integer, PePrograma> map = new HashMap<>();
        for (PePrograma i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    private void guardarItemBitacora(AuAnexo3Item item, AuSolicitudBean bean, int tipo, String descripcion) throws Exception {
        //ivanegaa registro cambio estado
        AuItemBitacora bitacora = new AuItemBitacora();
        bitacora.setDescripcion(descripcion);
        bitacora.setTipo(tipo);
        bitacora.setAuAnexo3ItemId(item);
        bitacora.setEstado(item.getEstado());
        bean.auditoriaGuardar(bitacora);
        getAuItemBitacoraRemoto().insertar(bitacora);
    }

    private void guardarItemBitacora(AuAnexo3Item item, int tipo, String descripcion) throws Exception {
        //ivanegaa registro cambio estado
        AuItemBitacora bitacora = new AuItemBitacora();
        bitacora.setDescripcion(descripcion);
        bitacora.setTipo(tipo);
        bitacora.setAuAnexo3ItemId(item);
        bitacora.setEstado(item.getEstado());
        if (item.getEstado() == AuAnexo3Item.ESTADO_DIRECCIONADO) {//cambiar estado manual
            bitacora.setUsuarioCrea(item.getUsuarioModifica());
            bitacora.setTerminalCrea(item.getTerminalModifica());
        } else {
            bitacora.setUsuarioCrea(item.getUsuarioCrea());
            bitacora.setTerminalCrea(item.getTerminalCrea());
        }
        bitacora.setFechaHoraCrea(new Date());
        getAuItemBitacoraRemoto().insertar(bitacora);
    }

    public void guardarHistoricoAnexo3(AuSolicitudBean bean, int tipo, String descripcion) throws Exception {
        AuAnexo3Historico historico = new AuAnexo3Historico();
        historico.setAuAnexo3Id(new AuAnexo3(bean.getObjeto().getId()));
        historico.setEstado(bean.getObjeto().getEstado());
        historico.setTipo(tipo);
        historico.setObservacion(descripcion);
        bean.auditoriaGuardar(historico);
        getAuAnexo3HistoricoRemoto().insertar(historico);
    }

    public void guardarGestionSeguimiento(AuSolicitudBean bean, AuSeguimiento seguimiento, String estado, String observacion) throws Exception {
        AuSeguimientoGestion objetoSeguimientoGestion = new AuSeguimientoGestion();
        objetoSeguimientoGestion.setAuSeguimiento(seguimiento);
        Maestro maestroEstadoGestion = bean.getHashEstadoGestion().get(estado);
        objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionId(maestroEstadoGestion.getId());
        objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionCodigo(maestroEstadoGestion.getValor());
        objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionValor(maestroEstadoGestion.getNombre());
        Maestro maestroMotivoGestion = bean.getHashMotivoGestion().get(AuSeguimientoGestion.MOTIVO_OTRO);
        objetoSeguimientoGestion.setMaeMotivoSeguimientoId(maestroMotivoGestion.getId());
        objetoSeguimientoGestion.setMaeMotivoSeguimientoCodigo(maestroMotivoGestion.getValor());
        objetoSeguimientoGestion.setMaeMotivoSeguimientoValor(maestroMotivoGestion.getNombre());
        objetoSeguimientoGestion.setDescripcion(observacion);
        objetoSeguimientoGestion.setTipo(AuSeguimientoGestion.TIPO_CAMBIO_ESTADO);
        bean.auditoriaGuardar(objetoSeguimientoGestion);
        getAuSeguimientoGestionRemoto().insertar(objetoSeguimientoGestion);
    }

    public void guardarPrestadorSedeSeguimiento(AuSolicitudBean bean, AuSeguimiento seguimiento) throws Exception {
        AuSeguimientoPrestadorAsignado prestador = new AuSeguimientoPrestadorAsignado();
        prestador.setCntPrestadorSede(seguimiento.getCntPrestadorSedeAsignadoId());
        prestador.setEstado(AuSeguimientoPrestadorAsignado.ESTADO_APROBADO);
        prestador.setAuSeguimiento(seguimiento);
        bean.auditoriaGuardar(prestador);
        getAuSeguimientoPrestadorAsignadoRemoto().insertar(prestador);
    }

    private List<AuAnexo3Item> filtroPosfechado(List<AuAnexo3Item> listaPosfechado) {
        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
        List<AuAnexo3Item> lista = listaPosfechado.stream()
                .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                || p.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                || p.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                || p.getEstado() == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION
                || p.getEstado() == AuAnexo3Item.ESTADO_SIN_COTIZACION
                ).collect(Collectors.toList());
        return lista;
    }

    private void consultarProgramaAfiliado(AuSolicitudBean bean) throws Exception {
        bean.setListaAfiliadoPrograma(
                getPeAfiliadosProgramaRemoto().consultarAfiliadoActivo(bean.getObjeto().getAsegAfiliadoId().getId())
        );
    }

    private void portabilidadAfiliado(AuSolicitudBean bean) throws Exception {
        //            AsegPortabilidad portabilidad = getPortabilidadRemoto().consultarPortabilidadAfiliadoVigente(bean.getObjeto().getAsegAfiliadoId().getId());
        AsegPortabilidad portabilidad = null;
        List<AsegPortabilidad> listPortabilidad = getPortabilidadRemoto().consultarPorAfiliado(bean.getObjeto().getAsegAfiliadoId().getId());

        if (listPortabilidad != null && !listPortabilidad.isEmpty()) {
            listPortabilidad = listPortabilidad.stream().sorted(Comparator.comparing(AsegPortabilidad::getPeriodoFinal).reversed()).collect(Collectors.toList());
            for (AsegPortabilidad asegPortabilidad : listPortabilidad) {
                Maestro maeEstado = getMaestroRemoto().consultar(asegPortabilidad.getMaeEstadoPortabilidadId());
                if (maeEstado.contieneAccion(MaestroAccion.ASEG_PORTABILIDAD_ESTADO_PORTABILIDAD_VIGENTE)) {
                    portabilidad = asegPortabilidad;
                    break;
                }
            }
        }
        if (portabilidad != null) {
            bean.getObjeto().getAsegAfiliadoId().setTienePortabilidad(true);
            if (portabilidad.getCntPrestadorSede() != null) {
                CntPrestadorSede portabilidadSede = getPrestadorRemoto().consultarSede(portabilidad.getCntPrestadorSede().getId());
                bean.getObjeto().getAsegAfiliadoId().setPortabilidadPrestadorSede(portabilidadSede);
            }
        } else {
            bean.getObjeto().getAsegAfiliadoId().setTienePortabilidad(false);
            bean.getObjeto().getAsegAfiliadoId().setPortabilidadPrestadorSede(new CntPrestadorSede());
        }
        /*if (bean.getObjeto().getAsegAfiliadoId().getTienePortabilidad() != null && bean.getObjeto().getAsegAfiliadoId().getTienePortabilidad() == true) {
                if (bean.getObjeto().getAsegAfiliadoId().getPortabilidadPrestadorSede() != null) {
                    CntPrestadorSede portabilidadSede = getPrestadorRemoto().consultarSede(bean.getObjeto().getAsegAfiliadoId().getPortabilidadPrestadorSede().getId());
                    bean.getObjeto().getAsegAfiliadoId().setPortabilidadPrestadorSede(portabilidadSede);
                }
            }*/
    }

    private void contactosAfiliado(AuSolicitudBean bean) {
        if (bean.getObjeto().getAsegAfiliadoId().getListaAsegAfiliadoContacto() != null) {
            bean.setTelefonoFijoAfiliado(
                    bean.getObjeto().getAsegAfiliadoId().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_TELEFONO))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
            bean.setTelefonoMovilAfiliado(
                    bean.getObjeto().getAsegAfiliadoId().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_CELULAR))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
        } else {
            bean.setTelefonoFijoAfiliado(null);
            bean.setTelefonoMovilAfiliado(null);
        }
    }

    /**
     * Metodo encargado de validar si alguna de las prescripcione que tiene la
     * solicitud ya fue previamente agregada al mismo afilaido en la misma fecha
     *
     * @param bean
     * @throws Exception
     */
    @SuppressWarnings({"null", "RedundantStringToString"})
    private void validarItemsFechaOrdenMedica(AuSolicitudBean bean) throws Exception {
        //Se valida si no se tiene el permiso para omitir la validacion
        //de las fechas de orden medico
        if (!bean.isAccionAdicional7()) {
            //Se obtienen las prescripciones de la solicitud que no sean cum o agrupadores.
            String idItems = bean.getObjeto().getAuAnexo3ItemsList().stream()
                    .filter(item -> item.getTipoTecnologia() != AuAnexo3Item.TIPO_TECNOLOGIA_CUM
                    && item.getTipoTecnologia() != AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO)
                    .map(item -> item.getMaTecnologiaId() + "").collect(Collectors.joining(","));

            //Se obtienen los estados para validar las fechas de orden medico.
            String estados = AuConstantes.anexo3ItemEstadoValidacion();

            //Se valida si hay prescripciones para validar la fecha de orden medico.
            if (!idItems.equals("")) {
                int idSolicitud = (bean.getObjeto().getId() == null ? 0 : bean.getObjeto().getId());
                //Se consulta en base de datos para saber si ya fue agregada una
                //misma prescripcion al mismo afiliado en la misma fecha. 
                List<AuAnexo3Item> itemsActivos = getAuAnexo3ItemRemoto().itemsByAfiliadoByFechaOrdenMedica(
                        bean.getObjeto().getAsegAfiliadoId().getId(), idItems, estados.toString(), bean.getObjeto().getFechaSolicitud(), idSolicitud
                );
                //Si se encuentra que hay prescripciones para el mismo afilaido
                //en la misma fecha se retorna mensaje de error y no se continua
                //el proceso.
                if (!itemsActivos.isEmpty()) {
                    String items = itemsActivos.stream()
                            .map(item -> item.getAuAnexo3Id().getId() + "-" + item.getMaTecnologiaCodigo())
                            .collect(Collectors.joining(", "));
                    bean.addError("Ya existen solicitud-tecnología con misma Fecha Orden Médica: " + items);
                }
            }
        }
    }

    private void validarItemsMedicamentos(AuSolicitudBean bean, List<AuAnexo3Item> lista) {
        for (AuAnexo3Item item : lista) {
            if (item.getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO) {
                if (item.getMaeCausaExternaId() == null) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener causa externa");
                }
                if (item.getMaeFinalidadId() == null) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener finalidad");
                }
                if (item.getMaeTipoCatastroficoId() == null) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener tipo Catastrofico");
                }
                if (item.getDosis() == null || item.getDosis().intValueExact() == 0) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener dosis mayor a cero");
                }
                if (item.getFrecuencia() == null) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener frecuencia");
                }
                if (item.getMaeViaAdministracionId() == null) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener via de administración");
                }
                if (item.getIndicaciones() == null) {
                    bean.addError("El medicamento " + item.getMaTecnologiaCodigo() + " debe tener posologia");
                }
            }
        }
    }

    public boolean validarDiagnosticoPrincipal(AuSolicitudBean bean) {
        boolean validar = false;
        for (AuAnexo3Diagnostico diag : bean.getObjeto().getAuAnexo3DiagnosticosList()) {
            if (diag.getPrincipal()) {
                validar = true;
            }
        }
        return validar;
    }

    @Override
    public void completarMaestro(AuSolicitudBean bean) {
        try {
            bean.setListaOrigenAtencion(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_ORIGEN_ATENCION_RESOLUCION_3047));
            bean.setHashOrigenAtencion(AuConstantes.obtenerHashMaestro(bean.getListaOrigenAtencion()));
            bean.setListaTipoServicioAtencion(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_TIPO_SERVICIO_RESOLUCION_3047));
            bean.setHashTipoServicioAtencion(AuConstantes.obtenerHashMaestro(bean.getListaTipoServicioAtencion()));
            bean.setListaTipoUbicacionPaciente(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_UBICACION_RESOLUCION_304));
            bean.setHashTipoUbicacionPaciente(AuConstantes.obtenerHashMaestro(bean.getListaTipoUbicacionPaciente()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void completarMaestroVersion2335(AuSolicitudBean bean) {
        try {
            bean.setListaOrigenAtencion(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_ORIGEN_ATENCION_RESOLUCION_2335));
            bean.setHashOrigenAtencion(AuConstantes.obtenerHashMaestro(bean.getListaOrigenAtencion()));
            bean.setListaTipoServicioAtencion(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_TIPO_SERVICIO_RESOLUCION_2335));
            bean.setHashTipoServicioAtencion(AuConstantes.obtenerHashMaestro(bean.getListaTipoServicioAtencion()));
            bean.setListaTipoUbicacionPaciente(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_UBICACION_RESOLUCION_2335));
            bean.setHashTipoUbicacionPaciente(AuConstantes.obtenerHashMaestro(bean.getListaTipoUbicacionPaciente()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public static HashMap<String, PePrograma> obtenerHashProgramasEspecialesCodigo(List<PePrograma> listaPePrograma) {
        HashMap<String, PePrograma> hash = new HashMap();
        listaPePrograma.forEach(programa -> {
            hash.put(programa.getCodigoPrograma(), programa);
        });
        return hash;
    }
    
    // <editor-fold defaultstate="collapsed" desc="metodos sin uso">
    @SuppressWarnings("UnusedAssignment")
    private void guardar(AuSolicitudBean bean) {
        try {
            if (bean.getObjeto().getMaServicioHabilitadoId() == null) {
                bean.addError("Debe indicar el servicio habilitado");
            }
            if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty() == true) {
                bean.addError("Debe agregar al menos un ítem para crear la solicitud");
            }
            if (bean.isError() == false) {
                boolean fallo = false;
                completarMaestroHistoricos(bean);
                int fuenteOrigen = AuConstantes.ID_CARGUE_MANUAL;
                bean.getObjeto().setFuenteOrigen(fuenteOrigen);
                AuAnexo3 solicitud = bean.getObjeto();
                solicitud.setEstado(AuConstantes.ESTADO_SOLICITUD_PENDIENTE);
                bean.auditoriaGuardar(solicitud);
                Empresa empresa = getEmpresaRemoto().consultarPorPrestador(solicitud.getCntPrestadorSedeId().getCntPrestador().getId());
                if (empresa != null) {
                    solicitud.setGnEmpresaId(empresa);
                } else {
                    solicitud.setGnEmpresaId(null);
                }
                CntProfesional profesional = bean.getObjeto().getCntProfesionaleId();
                if (profesional.getId() == null) {
                    Maestro tipoDocProfesional = bean.obtenerMaestroTipoDocProfesional(profesional.getMaeTipoCodumentoId());
                    profesional.setMaeTipoDocumentoCodigo(tipoDocProfesional.getValor());
                    profesional.setMaeTipoDocumentoValor(tipoDocProfesional.getNombre());
                    if (profesional.getRegistroMedico() == null) {
                        profesional.setRegistroMedico("Sin registro");
                    }
                    bean.auditoriaGuardar(profesional);
                    int idProfesional = getProfesionalRemoto().insertar(profesional);
                    profesional.setId(idProfesional);
                } else {
                    bean.auditoriaModificar(profesional);
                    getProfesionalRemoto().actualizar(profesional);
                }
                solicitud.setCntProfesionaleId(profesional);
                solicitud.setNombreProfesional(profesional.getPrimerNombre() + " " + profesional.getSegundoNombre());
                try {
                    int idSolicitud = getAuAnexo3Remoto().insertar(solicitud);
                    solicitud.setId(idSolicitud);
                } catch (Exception e) {
                    bean.addError("Hubo un fallo guardando la solitud, favor contactarse con el administrador");
                    fallo = true;
                }
                //Guardar diagnosticos
                if (!fallo) {
                    for (AuAnexo3Diagnostico diagnostico : solicitud.getAuAnexo3DiagnosticosList()) {
                        diagnostico.setAuAnexos3Id(solicitud);
                        bean.auditoriaGuardar(diagnostico);
                        try {
                            getAuAnexo3DiagnosticoRemoto().insertar(diagnostico);
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo al guardar un diagnostico");
                            fallo = true;
                            break;
                        }
                    }
                }
                //Guardar ítems
                if (!fallo) {
                    for (AuAnexo3Item item : solicitud.getAuAnexo3ItemsList()) {
                        item.setAuAnexo3Id(solicitud);
                        bean.auditoriaGuardar(item);
                        completarItems(bean);
                        if (bean.getParamConsulta4() == null) {
                            bean.setParamConsulta4(new ParamConsulta());
                        }
                        bean.getParamConsulta4().setFiltros(new HashMap());
                        bean.getParamConsulta4().setParametroConsulta1(item.getTipoTecnologia());
                        bean.getParamConsulta4().setParametroConsulta2("" + item.getMaTecnologiaId());
                        try {
                            int contratos = getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta4());
                            if (contratos == 0) {
                                item.setEstado(AuAnexo3Item.ESTADO_SIN_COTIZACION);
                            } else {
                                item.setEstado(AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA);
                            }
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo buscando contratos");
                            break;
                        }
                        if (item.isPosfechado()) {
                            int contador = 0;
                            for (AuRango rango : item.getListaRangos()) {
                                if (contador == 0) {
                                    item.setPosfechadoPrincipal(true);
                                } else {
                                    item.setPosfechadoPrincipal(false);
                                }
                                item.setPosfechado(true);
                                item.setCantidadSolicitada(rango.getCantidad());
                                item.setFechaPosfechado(rango.getFechaLiberacion());
                                try {
                                    int id = getAuAnexo3ItemRemoto().insertar(item);
                                    guardarItemBitacora(new AuAnexo3Item(id), bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                                } catch (Exception e) {
                                    bean.addError("Hubo un fallo creando un ítem posfechado");
                                    fallo = true;
                                    break;
                                }
                                contador++;
                            }
                        } else {
                            try {
                                int id = getAuAnexo3ItemRemoto().insertar(item);
                                guardarItemBitacora(new AuAnexo3Item(id), bean, AuItemBitacora.ID_CAMBIO_ESTADO, item.getEstadoStr());
                            } catch (Exception e) {
                                bean.addError("Hubo un fallo al guardar un ítem, favor contactar al administrador");
                                fallo = true;
                                break;
                            }
                        }
                    }
                }
                //Guardar adjuntos
                if (!fallo) {
                    for (AuSolicitudAdjunto adjunto : solicitud.getAuSolicitudAdjuntosList()) {
                        adjunto.setAuAnexo3(solicitud);
                        try {
                            bean.auditoriaGuardar(adjunto);
                            guardarAdjunto(bean, adjunto);
                        } catch (Exception e) {
                            bean.addError("Hubo un problema guardando adjuntos");
                            fallo = true;
                            break;
                        }
                    }
                }
                //Guardar tutelas
                if (!fallo) {
                    for (AuAnexo3Tutela tutela : solicitud.getAuAnexo3TutelasList()) {
                        tutela.setAuAnexo3Id(solicitud);
                        bean.auditoriaGuardar(tutela);
                        try {
                            tutela.setId(getAuAnexo3TutelaRemoto().insertar(tutela));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando tutelas");
                            fallo = true;
                            break;
                        }
                    }
                }
                //Guardar el afiliado
                AuAnexo3Afiliado afiliado = casteoAuAnexo3Afiliado(solicitud.getAsegAfiliadoId());
                if (!fallo) {
                    Maestro tipoDoc = bean.obtenerMaestroTipoDocumento(afiliado.getMaeTipoDocumentoId());
                    if (tipoDoc != null) {
                        afiliado.setAuAnexo3Id(solicitud);
                        afiliado.setMaeTipoDocumentoCodigo(tipoDoc.getValor());
                        afiliado.setMaeTipoDocumentoValor(tipoDoc.getNombre());
                        bean.auditoriaGuardar(afiliado);
                        try {
                            afiliado.setId(getAuAnexo3AfiliadoRemoto().insertar(afiliado));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando el afiliado");
                            fallo = true;
                        }
                    }
                }
                //Agregar Contrato profesional sede
                if (!fallo) {
                    List<CntProfesionalPrestador> contratos = getCntPrestadorProfesionalRemoto().consultarPorProfesional(profesional.getId());
                    CntProfesionalPrestador contrato = new CntProfesionalPrestador();
                    boolean agregarContrato = true;
                    if (!contratos.isEmpty()) {
                        int idPrestador = bean.getObjeto().getCntPrestadorSedeId().getCntPrestador().getId();
                        for (CntProfesionalPrestador cnt : contratos) {
                            if (cnt.getCntPrestador().getId() == idPrestador) {
                                agregarContrato = false;
                            }
                        }
                    }
                    if (agregarContrato) {
                        contrato.setCntProfesionalesId(profesional);
                        contrato.setCntPrestador(bean.getObjeto().getCntPrestadorSedeId().getCntPrestador());
                        contrato.setActivo(true);
                        contrato.setMaEspecialidadId(bean.getObjeto().getObjetoEspecialidad().getId());
                        contrato.setMaEspecialidadCodigo(bean.getObjeto().getObjetoEspecialidad().getCodigo());
                        contrato.setMaEspecialidadValor(bean.getObjeto().getObjetoEspecialidad().getNombre());
                        bean.auditoriaGuardar(contrato);
                        try {
                            getCntPrestadorProfesionalRemoto().insertar(contrato);
                            bean.addMensaje("La solicitud " + solicitud.getId() + " fue creada exitosamente");
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando el profesional");
                            fallo = true;
                        }
                    }
                }
                if (fallo) {
                    for (AuAnexo3Diagnostico diagnostico : solicitud.getAuAnexo3DiagnosticosList()) {
                        if (diagnostico.getId() != null) {
                            getAuAnexo3DiagnosticoRemoto().eliminar(diagnostico.getId());
                        }
                    }
                    for (AuAnexo3Item item : solicitud.getAuAnexo3ItemsList()) {
                        if (item.getId() != null) {
                            getAuAnexo3ItemRemoto().eliminar(item.getId());
                        }
                    }
                    for (AuAnexo3Tutela tutela : solicitud.getAuAnexo3TutelasList()) {
                        if (tutela.getId() != null) {
                            getAuAnexo3TutelaRemoto().eliminar(tutela.getId());
                        }
                    }
                    if (afiliado.getId() != null) {
                        getAfiliadoRemoto().eliminar(afiliado.getId());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
            bean.addError("Ha ocurrido un fallo no se almacenó toda la información");
        }
    }

    //se paso al aprobar centralizado anexo4 savia-solicitud-ejb
    @SuppressWarnings("UnusedAssignment")
    private void aprobarTodoAutomatico(AuSolicitudBean bean) {
        try {
            if (validarAprobacion(bean)) {
                boolean fallo = false;
                AuAnexo4 anexo4 = castearAnexo3Anexo4(bean);

                String idAfiliado = bean.getObjeto().getAsegAfiliadoId().getId().toString();
                int calculoCopago = 0;
                int calculoModeradora = 0;
                float porcentaje = 0;
                int cantidadEntregas = 0;
                int dias = 180;
                AuCotizacionItem itemCotizacion = null;
                for (AuAnexo4Automatico automatico : bean.getListaAutomaticos()) {
                    if (!automatico.getItem().isPosfechado()) {
                        Date fechaAutorizacion = automatico.getItem().getFechaPosfechado() == null ? new Date() : automatico.getItem().getFechaPosfechado();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaAutorizacion);
                        fechaAutorizacion = calendar.getTime();
                        anexo4.setFechaAutorizacion(fechaAutorizacion);
                        anexo4.setFechaAutorizacionImpresion(fechaAutorizacion);
                        anexo4.setFechaInicio(fechaAutorizacion);
                        if (automatico.getItem().getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO) {
                            dias = 30;
                        } else if (automatico.getItem().getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA) {
                            MaTecnologia tecnologia = getMaTecnologiaRemoto().consultar(automatico.getItem().getMaTecnologiaId());
                            if (tecnologia.getVigenciaAutorizacion() != null) {
                                dias = tecnologia.getVigenciaAutorizacion();
                            }
                        }
                        cantidadEntregas += automatico.getItem().getCantidadSolicitada();
                        String tipoTecnologia = "" + automatico.getItem().getTipoTecnologia();
                        String idTecnologia = "" + automatico.getItem().getMaTecnologiaId();
                        BigDecimal valorTecnologia = BigDecimal.ZERO;
                        BigDecimal multiplicando = new BigDecimal(automatico.getItem().getCantidadSolicitada());
                        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                        if (itemCotizacion == null && (automatico.getItem().getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION || automatico.getItem().getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO)) {
                            itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(automatico.getItem().getId());
                        }
                        try {
                            /*CntContratoDetalle contrato = getCntContratoDetalleRemoto().consultar(automatico.getValidacion().getCntPrestadorAutorizadoSedeId());
                            if (contrato != null) {
                                valorTecnologia = contrato.getValorContratado().multiply(multiplicando);
                            }*/
                            BigDecimal valor = new BigDecimal(automatico.getValidacion().getValorContratado());
                            valorTecnologia = valor.multiply(multiplicando);
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo consultando el contrato del ítem " + automatico.getItem().getMaeTipoCatastroficoCodigo() + ". Favor informar al administrador.");
                            fallo = true;
                            break;
                        }
                        String numTutela = "0";
                        if (!bean.getObjeto().getAuAnexo3TutelasList().isEmpty()) {
                            for (AuAnexo3Tutela tutela : bean.getObjeto().getAuAnexo3TutelasList()) {
                                if (tutela.isExoneracionCopago()) {
                                    numTutela = "" + tutela.getNumeroTutela();
                                    anexo4.setAplicaTutela(true);
                                }
                            }
                        }
                        String programaEspecial = "0";
                        try {
                            if (bean.getObjeto().getPeProgramaEspecialId() != null) {
                                PePrograma programa = getPeProgramaRemoto().consultar(bean.getObjeto().getPeProgramaEspecialId());
                                if (programa != null && programa.isExoneradoCopago()) {
                                    programaEspecial = bean.getObjeto().getPeProgramaEspecialId().toString();
                                    anexo4.setAplicaAltocosto(true);
                                }
                            }
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo consultando el programa especial, favor consultar al administrador");
                            fallo = true;
                            break;
                        }
                        ValidaRespuestaCopagoDTO copago = getValidadorRemoto().validarCalCopagoModeradora(idAfiliado, tipoTecnologia, idTecnologia, valorTecnologia.toString(), bean.getObjeto().getMaeAmbitoAtencionCodigo(), numTutela, programaEspecial);
                        anexo4.setAplicaCopago((copago.getCodigo4() == 1));
                        anexo4.setAplicaCuotaModeradora((copago.getCodigo5() == 1));
                        anexo4.setAplicaCuotaRecuperacion((copago.getCodigo6() == 1));
                        //anexo.setExcentoCopago(copago.getCodigo7() == 1 ? true : false);
                        calculoCopago += copago.getCodigo();
                        calculoModeradora = Integer.parseInt(copago.getValor());
                        if (anexo4.getAplicaCopago() != null && anexo4.isAplicaCuotaModeradora() && anexo4.getAplicaCopago()) {
                            bean.addError("No se puede tener items con couta de copago y moderadora al mismo tiempo");
                            fallo = true;
                            break;
                        }
                        anexo4.setValorCuotaModeradora(new BigDecimal(copago.getValor()));
                        if (copago.getCodigo3() > 0) {
                            if (porcentaje < copago.getCodigo3()) {
                                porcentaje = (float) copago.getCodigo3();
                            }
                        }
                    }
                }
                if (!fallo) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(anexo4.getFechaAutorizacion());
                        calendar.add(Calendar.DAY_OF_MONTH, dias);
                        anexo4.setFechaFin(calendar.getTime());
                        anexo4.setDiasVigencia(dias);
                        anexo4.setValorCopago(new BigDecimal(calculoCopago));
                        anexo4.setValorCuotaModeradora(new BigDecimal(calculoModeradora));
                        anexo4.setPorcentajeRecuperacion(new BigDecimal(porcentaje));
                        anexo4.setCantidadEntregas(cantidadEntregas);
                        anexo4.setObservacion(bean.getObjeto().getObservacionAuditar());
//                        String valorRegimen = "0" + bean.getObjeto().getAsegAfiliadoId().getRegimen();
//                        Maestro regimen = bean.obtenerMaestroRegimenValor(valorRegimen);
                        anexo4.setMaeRegimenId(bean.getObjeto().getAsegAfiliadoId().getMaeRegimenId());
                        anexo4.setMaeRegimenCodigo(bean.getObjeto().getAsegAfiliadoId().getMaeRegimenCodigo());
                        anexo4.setMaeRegimenValor(bean.getObjeto().getAsegAfiliadoId().getMaeRegimenValor());

                        bean.auditoriaGuardar(anexo4);
                        anexo4.setNombreAutoriza(anexo4.getUsuarioCrea());
                        String ruta = PropApl.getInstance().get(PropApl.AU_A4_ANEXOS);
                        //String ruta = "D:\\SERVICIOS\\sistemsavia\\autorizacion\\A4\\anexos\\";
                        anexo4.setRuta(ruta);
                        if (bean.isAutomatica()) {
                            anexo4.setUsuarioCrea("Sistema");
                            anexo4.setNombreAutoriza("Sistema");
                            anexo4.setEstado(AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO);
                            anexo4.setEstadoJustificacion("Aprobado Automático");
                            anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_AUTOMATICA);
                        } else {
                            if (itemCotizacion != null) {
                                anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_COTIZACION);
                            } else {
                                anexo4.setMedioAutorizacion(AuAnexo4.MEDIO_MANUAL);
                            }
                        }
                        int idAnexo4 = getAuAnexo4Remoto().insertar(anexo4);
                        anexo4.setId(idAnexo4);
                        String nombre = AuConstantes.nombreReporteAnexo4(anexo4);
                        anexo4.setArchivo(nombre);
                        anexo4.setArchivoNombre(AuConstantes.nombreArchivoReporteAnexo4(anexo4));
                        getAuAnexo4Remoto().actualizar(anexo4);
                    } catch (Exception e) {
                        bean.addError("Hubo un fallo guardando la autorizacion, favor comunicarse con el administrador");
                        fallo = true;
                    }
                }
                if (!fallo) {
                    anexo4.setAuAnexo4ItemsList(new ArrayList());
                    if (anexo4.getId() != null) {
                        for (AuAnexo4Automatico automatico : bean.getListaAutomaticos()) {
                            if (!automatico.getItem().isPosfechado()) {
                                AuAnexo4Item item = castearItem3Item4(automatico.getItem());
                                item.setAuAnexo4Id(anexo4);
                                BigDecimal multiplicando = new BigDecimal(item.getCantidadAutorizada());
                                try {
                                    /*CntContratoDetalle contrato = getCntContratoDetalleRemoto().consultar(automatico.getValidacion().getCntPrestadorAutorizadoSedeId());
                                    if (contrato != null) {
                                        item.setCostoServicio(contrato.getValorContratado().multiply(multiplicando));
                                    }*/
                                    BigDecimal valor = new BigDecimal(automatico.getValidacion().getValorContratado());
                                    item.setCostoServicio(valor.multiply(multiplicando));
                                } catch (Exception e) {
                                    bean.addError("Hubo un fallo buscando el contrato de un ítem " + automatico.getItem().getMaTecnologiaCodigo() + ". Favor contactar al administrador");
                                    fallo = true;
                                    break;
                                }
                                bean.auditoriaGuardar(item);
                                try {
                                    int idItem = getAuAnexo4ItemRemoto().insertar(item);
                                    if (idItem > 0) {
                                        anexo4.getAuAnexo4ItemsList().add(item);
                                        if (bean.isAutomatica()) {
                                            automatico.getItem().setEstado(AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO);
                                        } else {
                                            automatico.getItem().setEstado(AuAnexo3Item.ESTADO_APROBADO_AUDITORIA);
                                        }
                                        bean.auditoriaModificar(automatico.getItem());
                                        getAuAnexo3ItemRemoto().actualizarEstado(automatico.getItem());

                                        guardarItemBitacora(automatico.getItem(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, automatico.getItem().getEstadoStr());
                                    }
                                } catch (Exception e) {
                                    bean.addError("Hubo un fallo guardando un ítem");
                                    fallo = true;
                                    break;
                                }
                            }
                        }
                        int idHistorico = 0;
                        if (!fallo) {
                            try {
                                AuAnexo4Historico historico = new AuAnexo4Historico();
                                historico.setAuAnexos4Id(anexo4);
                                historico.setEstado(anexo4.getEstado());
                                historico.setMaeCausaId(0);
                                historico.setMaeCausaCodigo("00");
                                historico.setMaeCausaValor("Creacion");
                                historico.setObservacionAutorizacion("Creacion");
                                bean.auditoriaGuardar(historico);
                                if (bean.isAutomatica()) {
                                    historico.setUsuarioCrea("Sistema");
                                    historico.setMaeCausaId(1);
                                    historico.setMaeCausaCodigo("01");
                                    historico.setMaeCausaValor("Creacion Automatica");
                                    historico.setObservacionAutorizacion("Creacion Automatica");
                                }
                                idHistorico = getAuAnexo4HistoricoRemoto().insertar(historico);
                            } catch (Exception e) {
                                bean.addError("Hubo un fallo guardando el historial");
                                fallo = true;
                            }
                        }
                        if (!fallo) {
                            try {
//                                AuAnexo4Estado anexo4Estado = new AuAnexo4Estado();
//                                anexo4Estado.setAuAnexo4Id(anexo4);
//                                anexo4Estado.setMaeEstadoId(0);
//                                anexo4Estado.setMaeEstadoCodigo(String.valueOf(anexo4.getEstado()));
//                                anexo4Estado.setMaeEstadoValor(anexo4.getEstadoStr());
//                                anexo4Estado.setObservacion(anexo4.getObservacion());
//                                bean.auditoriaGuardar(anexo4Estado);
//                                if (bean.isAutomatica()) {
//                                    anexo4Estado.setUsuarioCrea("Sistema");
//                                }
//                                int idAnexo4Estado = getAuAnexo4EstadoRemoto().insertar(anexo4Estado);
//                                anexo4Estado.setId(idAnexo4Estado);
                                bean.setObjetoAnexo4(anexo4);
                                if (bean.getObjeto().getAuAnexo3ItemsList().isEmpty()) {
                                    bean.getObjeto().setEstado(AuConstantes.ESTADO_SOLICITUD_ANULADO);
                                    getAuAnexo3Remoto().actualizar(bean.getObjeto());
                                } else {
                                    evaluarEstadoSolicitud(bean);
                                }
                            } catch (Exception e) {
                                bean.addError("Hubo un fallo guardando el estado, por favor contactar con el adiministrador");
                                fallo = true;
                            }
                        }
                        if (!fallo) {
                            bean.addMensaje("Se genera la autorizacion numero " + anexo4.getId() + " de manera exitosa");
                            guardarReporteAnexo4(bean);
                        } else {
                            for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                                if (item.getId() != null) {
                                    getAuAnexo3ItemRemoto().eliminar(item.getId());
                                }
                            }
                            if (idHistorico > 0) {
                                getAuAnexo4HistoricoRemoto().eliminar(idHistorico);
                            }
                            if (anexo4.getId() != null) {
                                getAuAnexo4Remoto().eliminar(anexo4.getId());
                            }

                        }
                    }
                } else {
                    if (anexo4.getId() != null) {
                        getAuAnexo4Remoto().eliminar(anexo4.getId());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    //se utiliza clase util
    @Override
    @SuppressWarnings("null")
    public List<ReporteAnexo4> generarReporteAnexo4(int id, AuSolicitudBean bean) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm");
        List<ReporteAnexo4> listaReportes = new ArrayList();
        try {
            AuAnexo3Item itemAnexo3 = getAuAnexo3ItemRemoto().consultar(id);
            int idAnexo3 = itemAnexo3.getAuAnexo3Id().getId();
//            if (bean.getParamConsulta(2) == null) {
//                bean.setParamConsulta(2new ParamConsulta());
//            }
            bean.getParamConsulta(2).setFiltros(new HashMap());
            bean.getParamConsulta(2).getFiltros().put("anexo3Id", idAnexo3);
            ReporteAnexo4 reporte = new ReporteAnexo4();
            AuAnexo4 anexo4 = getAuAnexo4Remoto().consultarPorAnexo3(idAnexo3);
            anexo4.setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(anexo4.getId()));

            CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultar(anexo4.getCntPrestadorSedeId().getId());
//            Ubicacion ubicacionPrestador = getUbicacionRemoto().consultar(prestadorSede.getUbicacionId());
            Ubicacion ubicacionPrestador = UbicacionSingle.getInstance().getHashMunicipios().get(prestadorSede.getUbicacionId());
//            Ubicacion ubicacionPadre = getUbicacionRemoto().consultar(ubicacionPrestador.getUbicacionPadre().getId());
            Ubicacion ubicacionPadre = UbicacionSingle.getInstance().getHashMunicipios().get(ubicacionPrestador.getUbicacionPadre().getId());
            ubicacionPrestador.setUbicacionPadre(ubicacionPadre);
            prestadorSede.setUbicacion(ubicacionPrestador);
            anexo4.setCntPrestadorSedeId(prestadorSede);

            CntPrestador prestador = getPrestadorRemoto().consultar(prestadorSede.getCntPrestador().getId());
            anexo4.getCntPrestadorSedeId().setCntPrestador(prestador);

            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(anexo4.getAsegAfiliadoId().getId());
//            Ubicacion ubicacionAfiliado = getUbicacionRemoto().consultar(afiliado.getResidenciaUbicacion().getId());
            Ubicacion ubicacionAfiliado = UbicacionSingle.getInstance().getHashMunicipios().get(afiliado.getResidenciaUbicacion().getId());
//            Ubicacion ubicacionAfiliadoPadre = getUbicacionRemoto().consultar(ubicacionAfiliado.getUbicacionPadre().getId());
            Ubicacion ubicacionAfiliadoPadre = UbicacionSingle.getInstance().getHashMunicipios().get(ubicacionAfiliado.getUbicacionPadre().getId());
            ubicacionAfiliado.setUbicacionPadre(ubicacionAfiliadoPadre);
            afiliado.setResidenciaUbicacion(ubicacionAfiliado);
            anexo4.setAsegAfiliadoId(afiliado);

            Empresa savia = getEmpresaRemoto().consultar(1);

            if (anexo4 != null) {
                if (!anexo4.getAuAnexo4ItemsList().isEmpty()) {
                    for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                        reporte.setStrNumeroAutorizacion(anexo4.getId().toString());
                        reporte.setDateFechaAutorizacion(anexo4.getFechaAutorizacion());
                        reporte.setStrHoraAutorizacion(horaFormat.format(anexo4.getFechaAutorizacion()));
                        reporte.setStrEntidadResponsable(savia.getRazonSocial());
                        reporte.setStrCodigoEntidad(savia.getCodigoHabilitacion());
                        reporte.setStrNombrePrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getRazonSocial());
                        reporte.setStrTipoDocPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getMaeTipoDocumentoCodigo());
                        reporte.setStrNumDocPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getNumeroDocumento());
                        reporte.setStrCodigoPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getCodigoMinSalud());
                        reporte.setStrCorreoPrestador(anexo4.getCntPrestadorSedeId().getCorreoElectronico());
                        reporte.setStrTelefono1Prestador(anexo4.getCntPrestadorSedeId().getTelefonoCitas());
                        reporte.setStrTelefono2Prestador(anexo4.getCntPrestadorSedeId().getTelefonoAdministrativo());
                        reporte.setStrDireccionPrestador(anexo4.getCntPrestadorSedeId().getDireccion());
                        reporte.setStrDepartamentoPrestador(bean.obtenerDepartamento(anexo4.getCntPrestadorSedeId().getUbicacion().getId()));
                        reporte.setStrMunicipioPrestador(bean.obtenerMunicipio(anexo4.getCntPrestadorSedeId().getUbicacion().getId()));
                        reporte.setStrPrimerApellidoPaciente(anexo4.getAsegAfiliadoId().getPrimerApellido());
                        reporte.setStrSegundoApellidoPaciente(anexo4.getAsegAfiliadoId().getSegundoApellido());
                        reporte.setStrPrimerNombrePaciente(anexo4.getAsegAfiliadoId().getPrimerNombre());
                        reporte.setStrSegundoNombrePaciente(anexo4.getAsegAfiliadoId().getSegundoNombre());
                        reporte.setStrTipoDocPaciente(anexo4.getAsegAfiliadoId().getMaeTipoDocumentoCodigo());
                        reporte.setStrNumDocPaciente(anexo4.getAsegAfiliadoId().getNumeroDocumento());
                        reporte.setStrFechaNacimientoPaciente(dateFormat.format(anexo4.getAsegAfiliadoId().getFechaNacimiento()));
                        reporte.setStrHoraNacimientoPaciente(horaFormat.format(anexo4.getAsegAfiliadoId().getFechaNacimiento()));
                        reporte.setStrDireccionPaciente(anexo4.getAsegAfiliadoId().getDireccionResidencia());
                        reporte.setStrTelefonoFijoPaciente(bean.getTelefonoFijoAfiliado());
                        reporte.setStrDepartementoPaciente(anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getUbicacionPadre().getNombre() + "     " + anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getUbicacionPadre().getPrefijo());
                        reporte.setStrMunicipioPaciente(anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getNombre() + "     " + anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getPrefijo());
                        reporte.setStrTelefonoCelularPaciente(bean.getTelefonoMovilAfiliado());
                        reporte.setStrCorreoPaciente(anexo4.getAsegAfiliadoId().getEmail());
                        reporte.setStrUbicacionPaciente("");
                        reporte.setStrManejoIntegral(anexo4.getMaeGuiaManejoIntegralValor());
                        reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                        reporte.setStrCantidad("" + item.getCantidadAutorizada());
                        reporte.setStrDescripcion(item.getMaTecnologiaValor());
                        reporte.setStrObservacion(anexo4.getObservacion());
                        reporte.setStrNumeroOrigen(anexo4.getAuAnexo3Id().getId().toString());
                        reporte.setDateFechaOrigen(anexo4.getFechaHoraCrea());
                        reporte.setStrHoraOrigen(horaFormat.format(anexo4.getFechaHoraCrea()));
                        reporte.setStrPorcentajeAutorizado(""); //Falta preguntar
                        reporte.setStrSemanaPaciente("" + anexo4.getSemanasAfiliacion()); //Falta preguntar
                        reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                        reporte.setStrPorcentaje(""); //Falta preguntar
                        if(Double.parseDouble(reporte.getStrValor()) != 0.0){
                            reporte.setStrAplicaCobro("SI");
                        }else{
                            reporte.setStrAplicaCobro("NO");
                        }
                        //reporte.setStrAplicaCobro(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "NO" : "SI" : "");
                        //reporte.setStrTipoCuota(""); //Falta preguntar
                        reporte.setStrNombreAutoriza(anexo4.getUsuarioCrea()); //Falta preguntar
                        reporte.setStrCargoAutoriza(""); //Falta preguntar
//                        anexo4.setDiasVigencia(180);
//                        if (item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_CUM
//                                || item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO) {
//                            anexo4.setDiasVigencia(30);
//                        }
                        reporte.setStrDias("" + anexo4.getDiasVigencia());
                        reporte.setStrCama(""); //Falta preguntar
                        reporte.setStrServicio(anexo4.getMaServicioHabilitadoValor());
                        reporte.setStrValor(anexo4.getValorCopago().toString());
                        reporte.setStrPorcentaje(anexo4.getPorcentajeRecuperacion().toString() + " %");
                        reporte.setStrCuotaModeradora(anexo4.isAplicaCuotaModeradora() == true ? "SI" : "NO");
                        reporte.setStrCopago(anexo4.getAplicaCopago() == true ? "SI" : "NO");
                        reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() == true ? "SI" : "NO");
                        reporte.setStrExcentoCuota("NO");
                        listaReportes.add(reporte);
                    }
                }
            }
        } catch (Exception e) {
            listaReportes = new ArrayList();
        }
        return listaReportes;
    }
// </editor-fold>
}
