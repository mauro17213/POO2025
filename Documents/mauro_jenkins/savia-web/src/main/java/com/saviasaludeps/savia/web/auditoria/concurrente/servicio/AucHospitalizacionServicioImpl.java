/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliadoContacto;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAuditor;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAuditorHistorico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucDiagnostico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucEgreso;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionAdverso;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstancia;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionHistorico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionInoportunidad;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionSeguimiento;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionServicio;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.ReporteActaJustificacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.ReporteHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.crue.AuTipoRescate;
import com.saviasaludeps.savia.dominio.especial.PeAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAuditorRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucEgresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionAdversoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstanciaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionHistoricoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionInoportunidadRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionPatologiaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionServicioRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucIngresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucJustificacionEstanciasProlongadaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateGestionRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAdjuntoProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoSugeridoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeSugeridoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.web.auditoria.concurrente.bean.AucHospitalizacionBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacionServicioImpl extends AccionesBO implements AucHospitalizacionServiceIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
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

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
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

    private AucDiagnosticoRemoto getAucDiagnosticoRemoto() throws Exception {
        return (AucDiagnosticoRemoto) RemotoEJB.getEJBRemoto("AucDiagnosticoServicio", AucDiagnosticoRemoto.class.getName());
    }

    private AucJustificacionEstanciasProlongadaRemoto getAucJustificacionEstanciasProlongadaRemoto() throws Exception {
        return (AucJustificacionEstanciasProlongadaRemoto) RemotoEJB.getEJBRemoto("AucJustificacionEstanciasProlongadaServicio", AucJustificacionEstanciasProlongadaRemoto.class.getName());
    }

    private AucHospitalizacionHistoricoRemoto getAucHospitalizacionHistoricoRemoto() throws Exception {
        return (AucHospitalizacionHistoricoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionHistoricoServicio", AucHospitalizacionHistoricoRemoto.class.getName());
    }

    private AucAfiliadoContactoRemoto getAucAfiliadoContactoRemoto() throws Exception {
        return (AucAfiliadoContactoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoContactoServicio", AucAfiliadoContactoRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private PeAfiliadoSugeridoRemoto getPeAfiliadoSugeridoRemoto() throws Exception {
        return (PeAfiliadoSugeridoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoSugeridoServicio", PeAfiliadoSugeridoRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private PeAfiliadoDiagnosticoRemoto getPeAfiliadoDiagnosticoRemoto() throws Exception {
        return (PeAfiliadoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoDiagnosticoServicio", PeAfiliadoDiagnosticoRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private PeSugeridoAdjuntoRemoto getPeSugeridoAdjuntoRemoto() throws Exception {
        return (PeSugeridoAdjuntoRemoto) RemotoEJB.getEJBRemoto("PeSugeridoAdjuntoServicio", PeSugeridoAdjuntoRemoto.class.getName());
    }

    private AucHospitalizacionEstadoRemoto getAucHospitalizacionEstadoRemoto() throws Exception {
        return (AucHospitalizacionEstadoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionEstadoServicio", AucHospitalizacionEstadoRemoto.class.getName());
    }

    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }

    private AuAnexo2RescateRemoto getAuAnexo2RescateRemoto() throws Exception {
        return (AuAnexo2RescateRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateServicio", AuAnexo2RescateRemoto.class.getName());
    }

    private AuAnexo2RescateGestionRemoto getAuAnexo2RescateGestionRemoto() throws Exception {
        return (AuAnexo2RescateGestionRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateGestionServicio", AuAnexo2RescateGestionRemoto.class.getName());
    }

    private PeAdjuntoProgramaRemoto getPeAdjuntoProgramaRemoto() throws Exception {
        return (PeAdjuntoProgramaRemoto) RemotoEJB.getEJBRemoto("PeAdjuntoProgramaServicio", PeAdjuntoProgramaRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AucHospitalizacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verPatologia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
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
                        case Url.ACCION_ADICIONAL_8:
                            verJustificacionEstanciaProlongada(bean);
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            verPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            verSugerido(bean);
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            verRescate(bean);
                            break;
                        case Url.ACCION_ADICIONAL_12:
                            verAdjuntoPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_13:
                            verAdjuntoSugerido(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            editarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            editarEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            editarPatologia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            editarInoportunidad(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            editarAdverso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            editarObjecion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            editarServicio(bean);
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            editarJustificacionEstanciaProlongada(bean);
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            verEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            verPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            verSugerido(bean);
                            break;
                        case Url.ACCION_ADICIONAL_12:
                            verAdjuntoPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_13:
                            verAdjuntoSugerido(bean);
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            modificarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            modificarEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            modificarPatologia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            modificarInoportunidad(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            modificarAdverso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            modificarObjecion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            modificarServicio(bean);
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            modificarJustificacionEstanciaProlongada(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            borrarDiagnostico(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            borrarContactoPersona(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            borrarEstancia(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            verGestion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            guardarGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            editarJustificacionEstanciaProlongada(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            modificarJustificacionEstanciaProlongada(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            modificarIngreso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            modificarEgreso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            guardarEgreso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            guardarGestionRiesgo(bean);
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            verPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            verSugerido(bean);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            guardarRescate(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearAdjuntoPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            crearAdjuntoSugerido(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarAdjuntoPrograma(bean);
                            break;
                        case Url.ACCION_ADICIONAL_12:
                            guardarAdjuntoSugerido(bean);
                            break;
                        case Url.ACCION_ADICIONAL_13:
                            guardarNoAptoRescate(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_2:
                            editarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            modificarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            editarEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            modificarEstancia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            editarPatologia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            modificarPatologia(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            switch(bean.getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    editarInoportunidad(bean);
                                    break;
                                case Url.ACCION_GUARDAR:
                                    modificarInoportunidad(bean);
                                    break;
                               
                            }
                            break;
                        /*case AucHospitalizacionBean.ACCION_MODIFICAR_INOPORTUNIDAD:
                            modificarInoportunidad(bean);
                            break;*/
                        case Url.ACCION_ADICIONAL_5:
                            editarObjecion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            modificarObjecion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            editarServicio(bean);
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            modificarServicio(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_3:
                            editarAdverso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            modificarAdverso(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_4:
                            verAnulacion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarSeguimientosAnulacion(bean);
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            borrarEstancia(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            borrarAdverso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            borrarObjecion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            borrarInoportunidad(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            borrarServicio(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            borrarSugerido(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            borrarGestorasRegionales(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            borrarDiagnosticoEstancia(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_7:
                            revertirHospitalizacion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarRevertirHospitalizacion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_10:
                            verGestion(bean);
                            break;
                        case AucHospitalizacionBean.ACCION_GUARDAR:
                            guardarRevertirHospitalizacion(bean);
                            break;
                        case Url.ACCION_VER:
                            verSeguimiento(bean);
                            break;
                        case AucHospitalizacionBean.ACCION_EDITAR:
                            editarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            modificarGestorasRegionales(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            guardarEgreso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            modificarEgreso(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            guardarEstancias(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            modificarIngreso(bean);
                            break;
                    }
                    break;
            }
        } else {
            System.err.println("Sesión inactiva");
        }
    }

    private void listar(AucHospitalizacionBean bean) {
        try {
         
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
           
            if (bean.getParamConsulta(0).getFiltros().containsKey("aucIngresoId.maeAltaTempranaCodigo")) {
                if (bean.getParamConsulta(0).getFiltros().containsKey("estado")) {
                    bean.getParamConsulta(0).getFiltros().remove("estado");
                }
                    bean.getParamConsulta(0).getFiltros().put("estado", "1");//establecemos por defecto el estado del afiliado como hospitalizado
            }
            
            bean.getParamConsulta(0).setCantidadRegistros(getAucHospitalizacionRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAucHospitalizacionRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar al adminitrador");
        }

    }

    private void crear(AucHospitalizacionBean bean) {
        try {
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
            bean.setListaGeneroAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));

            bean.setListaRegimenAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));

            bean.setListaTiposIngreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_INGRESO));
            bean.setHashTiposIngreso(AuConstantes.obtenerHashMaestro(bean.getListaTiposIngreso()));
            
            bean.setListaCtnModalidades(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_MODALIDAD_CONTRATO));
            bean.setHashCtnModalidades(AuConstantes.obtenerHashMaestro(bean.getListaCtnModalidades()));

            bean.setListaRemisionesNoPertinentes(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_REMISION_NO_PERTINENTE));
            bean.setHashRemisionesNoPertinentes(AuConstantes.obtenerHashMaestro(bean.getListaRemisionesNoPertinentes()));

            bean.setListaTiposDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TIPO_DIAGNOSTICO));
            bean.setHashTiposDiagnostico(AuConstantes.obtenerHashMaestro(bean.getListaTiposDiagnostico()));
            
            bean.setListaServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_SERVICIO_ESTANCIA));
            bean.setHashServicio(AuConstantes.obtenerHashMaestro(bean.getListaServicio()));
            
            bean.setListaCausaIngresoPrevenibles(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CAUSA_INGRESO_PREVENIBLE));
            bean.setHashCausaIngresoPrevenible(AuConstantes.obtenerHashMaestro(bean.getListaCausaIngresoPrevenibles()));
            
            bean.setListaAreaResponsableIngresoPrevenible(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_AREA_INGRESO_PREVENIBLE));
            bean.setHashAreaResponsableIngresoPrevenible(AuConstantes.obtenerHashMaestro(bean.getListaAreaResponsableIngresoPrevenible()));
            
            bean.setObjeto(new AucHospitalizacion());
            bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO);
            
            bean.setHabilitarAreaResponsableIngresoPrevenible(true);
            //bean.getObjeto().setGnEmpresaId(bean.getConexion().getEmpresa());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al crear, favor comunicarse con el adminitrador");
        }
    }

    private void ver(AucHospitalizacionBean bean) {
        try {
            bean.setObjeto(getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().isCierreAuditoria()) {
                bean.setBloquearBotonesCierreAuditoria(true);
            } else {
                bean.setBloquearBotonesCierreAuditoria(false);
            }
            bean.getObjeto().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getId()));
            bean.getObjeto().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjeto().getAucIngresoId().getId()));
            bean.getObjeto().getAucIngresoId().setUltimaFechaIngreso(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().setPrimariaPrestadorSede(afiliado.getPrimariaPrestadorSede());
            bean.setListaContactosAseg(afiliado.getListaAsegAfiliadoContacto());
            listarTuPersonaContactos(bean);
            if (bean.getObjeto().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcion(0);
            }
            if(bean.getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId() != null){
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(1);
            }else{
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(0);
            }
            if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getAucEgresoId().getId() != null) {
                bean.getObjeto().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjeto().getAucEgresoId().getId()));
                if (bean.getObjeto().getAucEgresoId().isFallecido()) {
                    consultarLimpiarMaestroDestion(bean);
                } else {
                    consultarMaestroDestino(bean);
                }
            }
            AucHospitalizacionEstado ultimoEstado = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoEgreso(bean.getObjeto().getId());
            if (ultimoEstado != null && bean.getObjeto().getEstado() != AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO) {
                bean.getObjeto().setFuenteOrigenEgreso(ultimoEstado.getFuenteOrigen().toString());
            }
            AucHospitalizacionEstado ultimoEstadoDevuelto = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoDevuelto(bean.getObjeto().getId());
            if (ultimoEstadoDevuelto != null) {
                bean.getObjeto().setDevuelto(true);
                bean.getObjeto().setObservacionDevolucion(ultimoEstadoDevuelto.getObservacion());
            }
            bean.getObjeto().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorId().getId()));
            bean.getObjeto().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedeId().getId()));
            bean.getObjeto().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(getAucDiagnosticoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionGestorasRegionalesList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacionGestorasRegionales(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucProgramaEspecialList(getPeProgramaRemoto().programasMatriculadosSugeridos(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId()));
            bean.getObjeto().setAucRescateList(getAuAnexo2RescateRemoto().rescatesPorHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verAnulacion(AucHospitalizacionBean bean) {
        try {
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_SEGUIMIENTO));
            bean.setHashTipoSeguimiento(AuConstantes.obtenerHashMaestro(bean.getListaTipoSeguimiento()));
            bean.setObjeto(getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO) {
                bean.addError("No puede anular la hospitalización porque ya fue anulada");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void revertirHospitalizacion(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            if (hospitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO
                    || hospitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hospitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                bean.addError("No puede revertir la hospitalizacion por favor refrescar");
            }
            hospitalizacion.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(hospitalizacion.getAucAfiliadoId().getId()));
            boolean dosIps = validarHospitalizacionIpsSoloDosActivas(hospitalizacion.getAucAfiliadoId().getAsegAfiliadoId(), bean);
            if (dosIps) {
                validarHospitalizacionActivas(hospitalizacion.getAucAfiliadoId().getAsegAfiliadoId(), bean, hospitalizacion.getCntPrestadorSedeId().getId());
            }
            validarDiasParaRevertirHospitalizacion(bean);
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void guardarRevertirHospitalizacion(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            AucEgreso egreso = null;
            if (hospitalizacion.getAucEgresoId().getId() != null) {
                egreso = getAucEgresoRemoto().consultar(hospitalizacion.getAucEgresoId().getId());
                registrarHistorico(egreso.toString(), bean);
            }
            hospitalizacion.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(hospitalizacion.getAucAfiliadoId().getId()));
            hospitalizacion.setEstado(AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO);
            hospitalizacion.setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            hospitalizacion.setFechaFinHospitalizacion(null);
            hospitalizacion.setFuenteOrigenEgreso(null);
            hospitalizacion.setAucEgresoId(null);
            hospitalizacion.setDiasHospitalizacion(hospitalizacion.getCalcularDiasHospitalizacionFin());
            getAucHospitalizacionRemoto().actualizaReveirHospitalizacion(hospitalizacion);
            // Borrar El egreso
            if (egreso != null) {
                borrarDiagnosticoEgreso(egreso.getAucDiagnosticosList(), bean);
                hospitalizacion.setAucEgresoId(getAucEgresoRemoto().eliminar(egreso.getId()));
            }
            //Modificar Estancia
            hospitalizacion.setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(hospitalizacion.getId()));
            AucHospitalizacionEstancia ultimaEstancia = hospitalizacion.getAucHospitalizacionEstanciaList().get(hospitalizacion.getAucHospitalizacionEstanciaList().size() - 1);
            ultimaEstancia.setFechaEgreso(null);
            ultimaEstancia.setDias(ultimaEstancia.getCalcularDiasEstancia());
            getAucHospitalizacionEstanciaRemoto().actualizar(ultimaEstancia);

            registroHospitalizacionEstados(bean, (short) AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO_DEVUELTO, (short) AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION_DEVUELTO, bean.getObjetoSeguimiento().getDescripcion());
            registrarHistorico(hospitalizacion.toString(), bean);
            if (!bean.isError()) {
                bean.addMensaje("La reversión de la hospitalización se realizo exitosamente");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verGestion(AucHospitalizacionBean bean) {
        try {
            bean.setListaTiposIngreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_INGRESO));
            bean.setHashTiposIngreso(AuConstantes.obtenerHashMaestro(bean.getListaTiposIngreso()));
            bean.setListaCtnModalidades(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_MODALIDAD_CONTRATO));
            bean.setHashCtnModalidades(AuConstantes.obtenerHashMaestro(bean.getListaCtnModalidades()));
            bean.setListaRemisionesNoPertinentes(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_REMISION_NO_PERTINENTE));
            bean.setHashRemisionesNoPertinentes(AuConstantes.obtenerHashMaestro(bean.getListaRemisionesNoPertinentes()));
            bean.setListaTiposDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TIPO_DIAGNOSTICO));
            bean.setHashTiposDiagnostico(AuConstantes.obtenerHashMaestro(bean.getListaTiposDiagnostico()));
            bean.setListaConductaEgreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CONDUCTA_EGRESO));
            bean.setHashConductaEgreso(AuConstantes.obtenerHashMaestro(bean.getListaConductaEgreso()));
            bean.setListaCausaIngresoPrevenibles(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CAUSA_INGRESO_PREVENIBLE));
            bean.setHashCausaIngresoPrevenible(AuConstantes.obtenerHashMaestro(bean.getListaCausaIngresoPrevenibles()));
            bean.setListaAreaResponsableIngresoPrevenible(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_AREA_INGRESO_PREVENIBLE));
            bean.setHashAreaResponsableIngresoPrevenible(AuConstantes.obtenerHashMaestro(bean.getListaAreaResponsableIngresoPrevenible()));
            bean.setListaSeguimientoGestores(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_SEGUIMIENTO_GESTORES));
            bean.setHashSeguimientoGestores(AuConstantes.obtenerHashMaestro(bean.getListaSeguimientoGestores()));
            bean.setListaSeguimientoEstado(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_SEGUIMIENTO_ESTADO));
            bean.setHashSeguimientoEstado(AuConstantes.obtenerHashMaestro(bean.getListaSeguimientoEstado()));            
            bean.setListaDestinoGR(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.AUC_DESTINO_EGRESO_GESTION));
            bean.setHashDestinoGR(AuConstantes.obtenerHashMaestro(bean.getListaDestinoGR()));
            
            bean.setObjeto(getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().isCierreAuditoria()) {
                bean.setBloquearBotonesCierreAuditoria(true);
            } else {
                bean.setBloquearBotonesCierreAuditoria(false);
            }
            bean.getObjeto().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getId()));
            bean.getObjeto().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjeto().getAucIngresoId().getId()));
            bean.getObjeto().getAucIngresoId().setUltimaFechaIngreso(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            bean.cargarConsultarMaestroMotivoReingreso();
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().setPrimariaPrestadorSede(afiliado.getPrimariaPrestadorSede());
            bean.setListaContactosAseg(afiliado.getListaAsegAfiliadoContacto());
            listarTuPersonaContactos(bean);
            if (bean.getObjeto().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcion(0);
            }
            bean.eventoConsultarMaestroPosibleAltaTemprana();
            if(bean.getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId() != null){
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(1);
                bean.setCampoObligatorioAreaResponsableIngresoPrevenible(true);
                bean.setHabilitarAreaResponsableIngresoPrevenible(false);
            }else{
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(0);
                bean.setCampoObligatorioAreaResponsableIngresoPrevenible(false);
                bean.setHabilitarAreaResponsableIngresoPrevenible(true);
            }
            AucHospitalizacionEstado ultimoEstado = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoEgreso(bean.getObjeto().getId());
            if (ultimoEstado != null && bean.getObjeto().getEstado() != AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO) {
                bean.getObjeto().setFuenteOrigenEgreso(ultimoEstado.getFuenteOrigen().toString());
            }
            AucHospitalizacionEstado ultimoEstadoDevuelto = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoDevuelto(bean.getObjeto().getId());
            if (ultimoEstadoDevuelto != null) {
                bean.getObjeto().setDevuelto(true);
                bean.getObjeto().setObservacionDevolucion(ultimoEstadoDevuelto.getObservacion());
            }
            bean.getObjeto().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorId().getId()));
            bean.getObjeto().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedeId().getId()));
            bean.getObjeto().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(getAucDiagnosticoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionGestorasRegionalesList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacionGestorasRegionales(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            if (!bean.getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date fechaEgreso = (bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                        && !bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) ? bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() : null;
                if (fechaEgreso != null) {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                } else {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                }
            }
            if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getAucEgresoId().getId() != null) {
                bean.getObjeto().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjeto().getAucEgresoId().getId()));
                if (bean.getObjeto().getAucEgresoId().isIpsEntregaValor()) {
                    bean.setHabilitarCampoValorEstancia(false);
                    bean.setCampoObligatorioValorEstancia(true);
                } else {
                    bean.setHabilitarCampoValorEstancia(true);
                    bean.setCampoObligatorioValorEstancia(false);
                }
                if (bean.getObjeto().getAucEgresoId().isFallecido()) {
                    bean.setHabilitarEgresoNumeroCertificado(false);
                    bean.setCampoObligatorioNumeroCertificado(true);
                    consultarLimpiarMaestroDestion(bean);
                } else {
                    bean.setHabilitarEgresoNumeroCertificado(true);
                    bean.setCampoObligatorioNumeroCertificado(false);
                    consultarMaestroDestino(bean);
                }
            } else {
                bean.getObjeto().getAucEgresoId().setIpsEntregaValor(true);
                bean.getObjeto().getAucEgresoId().setValorEstancia(BigDecimal.ZERO);
                bean.setHabilitarCampoValorEstancia(false);
                bean.setCampoObligatorioValorEstancia(true);
                bean.setHabilitarEgresoNumeroCertificado(true);
                bean.setCampoObligatorioNumeroCertificado(false);
            }
            bean.validarMotoSuperiorACienMil();
            bean.getObjeto().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucProgramaEspecialList(getPeProgramaRemoto().programasMatriculadosSugeridos(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId()));
            bean.getObjeto().setAucRescateList(getAuAnexo2RescateRemoto().rescatesPorHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verSeguimiento(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoSeguimiento(getAucHospitalizacionSeguimientoRemoto().consultar(bean.getObjetoSeguimiento().getId()));
            if( bean.getObjetoSeguimiento().getCntPrestadorSedesId() != null){
                bean.getObjetoSeguimiento().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjetoSeguimiento().getCntPrestadorSedesId().getId()));
            }
            if(bean.getObjetoSeguimiento().getCntPrestadoresId() != null){
                bean.getObjetoSeguimiento().setCntPrestadoresId(getPrestadorRemoto().consultar(bean.getObjetoSeguimiento().getCntPrestadoresId().getId()));
            }
            
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verEstancia(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoEstancia(getAucHospitalizacionEstanciaRemoto().consultar(bean.getObjetoEstancia().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verPatologia(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoPatologia(getAucHospitalizacionPatologiaRemoto().consultar(bean.getObjetoPatologia().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verInoportunidad(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoInoportunidad(getAucHospitalizacionInoportunidadRemoto().consultar(bean.getObjetoInoportunidad().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verAdverso(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoAdverso(getAucHospitalizacionAdversoRemoto().consultar(bean.getObjetoAdverso().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verObjecion(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoObjecion(getAucHospitalizacionObjecionRemoto().consultar(bean.getObjetoObjecion().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verServicio(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoServicio(getAucHospitalizacionServicioRemoto().consultar(bean.getObjetoServicio().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    public void verJustificacionEstanciaProlongada(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoJustificacionEstanciasProlongada(getAucJustificacionEstanciasProlongadaRemoto().consultar(bean.getObjetoJustificacionEstanciasProlongada().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void verPrograma(AucHospitalizacionBean bean) {
        try {
            bean.setListCausasActivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_CAUSA_ESTADO_ACTIVO));
            bean.setHashCausasActivo(AuConstantes.obtenerHashMaestro(bean.getListCausasActivo()));

            bean.setListCausasInactivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_CAUSA_ESTADO_INACTIVO));
            bean.setHashCausasInActivo(AuConstantes.obtenerHashMaestro(bean.getListCausasInactivo()));
            
            bean.setObjetoPePregrama(getPeAfiliadosProgramaRemoto().consultar(bean.getObjetoPePregrama().getId()));
            if (bean.getObjetoPePregrama() != null) {
                if (bean.getObjetoPePregrama().getAsegAfiliado() != null) {
                    bean.getObjetoPePregrama().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjetoPePregrama().getAsegAfiliado().getId()));
                }
                if (bean.getObjetoPePregrama().getPePrograma() != null) {
                    bean.getObjetoPePregrama().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjetoPePregrama().getPePrograma().getId()));
                }
                if (bean.getObjetoPePregrama().getPePrograma().getUsuariosId() != null) {
                    bean.getObjetoPePregrama().getPePrograma().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjetoPePregrama().getPePrograma().getUsuariosId().getId()));
                }
                if (bean.getObjetoPePregrama().getAsegAfiliado().getAfiliacionUbicacion() != null) {
                    bean.getObjetoPePregrama().getAsegAfiliado().setAfiliacionUbicacion(UbicacionSingle.getInstance().getHashMunicipios().get(bean.getObjetoPePregrama().getAsegAfiliado().getAfiliacionUbicacion().getId()));
                }
                if (bean.getObjetoPePregrama().getCntPrestadorSede() != null) {
                    bean.getObjetoPePregrama().setCntPrestadorSede(getPrestadorRemoto().consultarSede(bean.getObjetoPePregrama().getCntPrestadorSede().getId()));
                }
                List<AsegAfiliadoContacto> contacto_afiliado = bean.getObjetoPePregrama().getAsegAfiliado().getListaAsegAfiliadoContacto();
                bean.setListaContactos(contacto_afiliado);
                List<PeTelefono> telefonos = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjetoPePregrama().getAsegAfiliado().getId(), "");
                bean.setListaContactosPrograma(telefonos);
                bean.getObjetoPePregrama().setPeAfiliadoDiagnosticoList(getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(bean.getObjetoPePregrama().getId()));
                if (bean.getObjetoPePregrama().getGnUsuario() != null) {
                    bean.getObjetoPePregrama().setGnUsuario(getUsuarioRemoto().consultar(bean.getObjetoPePregrama().getGnUsuario().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void verSugerido(AucHospitalizacionBean bean) {
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

    private void verRescate(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoRescate(getAuAnexo2RescateRemoto().consultar(bean.getObjetoRescate().getId()));
            bean.getObjetoRescate().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjetoRescate().getAsegAfiliado().getFechaNacimiento()));
            contactosAfiliadoRescate(bean);
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    private void guardarRescate(AucHospitalizacionBean bean) {
        try {
            //AuAnexo2Rescate rescateActual = getAuAnexo2RescateRemoto().consultar(bean.getObjetoRescate().getId());
            AucHospitalizacion obj = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            if(obj.getAplicaRescate() == AucHospitalizacion.NO_APTO_RESCATE){
                bean.setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
                Maestro tipoSeguimiento = getMaestroRemoto().consultarPorValorTipo("1", "342");
                if(tipoSeguimiento != null){
                    bean.getObjetoSeguimiento().setMaeTipoSeguimientoId(tipoSeguimiento.getId());
                    bean.getObjetoSeguimiento().setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
                    bean.getObjetoSeguimiento().setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
                    bean.getObjetoSeguimiento().setMaeTipoSeguimientoTipo(tipoSeguimiento.getTipo());
                }
                bean.getObjetoSeguimiento().setAucHospitalizacionId(obj);
                bean.getObjetoSeguimiento().setBorrado(Boolean.FALSE);
                bean.getObjetoSeguimiento().setOrigen(AucHospitalizacionSeguimiento.ORIGEN_MANUAL);
                bean.getObjetoSeguimiento().setDescripcion(AucHospitalizacionSeguimiento.DESCRIPCION_SE_DESMARCA_NO_RESCATE + bean.getObjetoRescate().getMotivoConsulta());
                bean.auditoriaGuardar(bean.getObjetoSeguimiento());
                getAucHospitalizacionSeguimientoRemoto().insertar(bean.getObjetoSeguimiento());
                registrarHistorico(bean.getObjetoSeguimiento().toString(), bean);
                listarSeguimientos(bean);
            }
            obj.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(obj.getAucAfiliadoId().getId()));
            AuAnexo2Rescate objRescate = bean.getObjetoRescate();
            objRescate.setAsegAfiliado(obj.getAucAfiliadoId().getAsegAfiliadoId());
            objRescate.setTipoRescate(AuTipoRescate.GESTION_RIESGO.getId()); 
            objRescate.setMaeAfiliadoTipoDocumentoId(obj.getAucAfiliadoId().getMaeTipoDocumentoId());
            objRescate.setMaeAfiliadoTipoDocumentoCodigo(obj.getAucAfiliadoId().getMaeTipoDocumentoCodigo());
            objRescate.setMaeAfiliadoTipoDocumentoValor(obj.getAucAfiliadoId().getMaeTipoDocumentoValor());
            objRescate.setAfiliadoNumeroDocumento(obj.getAucAfiliadoId().getNumeroDocumento());
            objRescate.setAfiliadoPrimerNombre(obj.getAucAfiliadoId().getPrimerNombre());
            objRescate.setAfiliadoSegundoNombre(obj.getAucAfiliadoId().getSegundoNombre());
            objRescate.setAfiliadoPrimerApellido(obj.getAucAfiliadoId().getPrimerApellido());
            objRescate.setAfiliadoSegundoApellido(obj.getAucAfiliadoId().getSegundoApellido());
            objRescate.setCntPrestadorOrigen(obj.getCntPrestadorId());
            objRescate.setCntPrestadorSedeOrigen(obj.getCntPrestadorSedeId());
            PeAfiliadosPrograma peAfiliadosPrograma = getPeAfiliadosProgramaRemoto().consultarAfiliadoPorPrograma(obj.getAucAfiliadoId().getAsegAfiliadoId().getId(), objRescate.getPePrograma().getId());
            if (peAfiliadosPrograma != null) {
                objRescate.setCntPrestadorDestino(peAfiliadosPrograma.getCntPrestadorSede().getCntPrestador());
                objRescate.setCntPrestadorSedeDestino(peAfiliadosPrograma.getCntPrestadorSede());
                Empresa empresa = getEmpresaRemoto().consultarPorPrestador(objRescate.getCntPrestadorDestino().getId());
                if (empresa != null) {
                    objRescate.setEmpresa(empresa);
                }
            }

            bean.auditoriaGuardar(objRescate);
            int idAuAnexo2Rescate = getAuAnexo2RescateRemoto().insertar(objRescate);
            bean.getObjetoRescate().setId(idAuAnexo2Rescate);
            procesarArhivosRescate(objRescate.getAuSolicitudAdjuntosList(), bean.getObjetoRescate(), bean);
            AuAnexo2RescateGestion auAnexo2RescateGestion = new AuAnexo2RescateGestion();
            auAnexo2RescateGestion.setAuAnexo2Rescate(objRescate);
            auAnexo2RescateGestion.setTipo(AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO);
            auAnexo2RescateGestion.setObservacion(objRescate.getEstadoStr());
            auAnexo2RescateGestion.setFechaHoraGestion(new Date());
            bean.auditoriaGuardar(auAnexo2RescateGestion);
            int idAuAnexo2RescateGestion = getAuAnexo2RescateGestionRemoto().insertar(auAnexo2RescateGestion);
            auAnexo2RescateGestion.setId(idAuAnexo2RescateGestion);

            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            getAucHospitalizacionRemoto().actualizarAplicaRescate(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjeto().toString(), bean);
                registrarHistorico(bean.getObjetoRescate().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            }
            bean.addMensaje("Se creo el rescate de manera exitosa \n ");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }
    
    private void procesarArhivosRescate(List<AuSolicitudAdjunto> adjuntosIn, AuAnexo2Rescate sugerido, AucHospitalizacionBean bean) {
        try {
            for (AuSolicitudAdjunto adjunto : adjuntosIn) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    if (sugerido.getId() > 0) {
                        adjunto.setAuAnexo2Rescate(sugerido);
                    }
                    int idAdjunto = getAuSolicitudAdjuntoRemoto().insertar(adjunto);
                    adjunto.setId(idAdjunto);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void verAdjuntoPrograma(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoPePregrama(getPeAfiliadosProgramaRemoto().consultar(bean.getObjetoPePregrama().getId()));
            bean.getObjetoPePregrama().setListaPeAdjunto(getPeAdjuntoProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjetoPePregrama().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    public void verAdjuntoSugerido(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoSugerido(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjetoSugerido().getId()));
            if (bean.getObjetoSugerido() != null) {
                bean.getObjetoSugerido().setListaAdjunto(getPeSugeridoAdjuntoRemoto().listar(bean.getObjetoSugerido().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }

    public void crearAdjuntoPrograma(AucHospitalizacionBean bean) {
        try {
            bean.setListaTiposSugeridoAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_ADJUNTO_TIPO));
            bean.setHashTiposSugeridoAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposSugeridoAdjuntos()));
            
            bean.setObjetoPePregrama(getPeAfiliadosProgramaRemoto().consultar(bean.getObjetoPePregrama().getId()));
            bean.getObjetoPePregrama().setListaPeAdjunto(getPeAdjuntoProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjetoPePregrama().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        }
    }
    
    @Override
    public Maestro consultarMaestroPeadjuntoTipo(AucHospitalizacionBean bean){
        Maestro maestro = new Maestro();
        try {
            maestro = getMaestroRemoto().consultarPorValorTipo("1", "59");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        } 
        return maestro;
    }
    
    @Override
    public Maestro consultarMaestro(int id, AucHospitalizacionBean bean){
        Maestro maestro = null;
        try {
            if(id > 0){
                maestro = getMaestroRemoto().consultar(id);
            }
           
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        } 
        return maestro;
    }
    
    @Override
    public void consultarMaestroMotivoReingreso(AucHospitalizacionBean bean){
        try {
            bean.setListaMotivoReingreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_REINGRESO_MOTIVO));
            bean.setHashMotivoReingreso(AuConstantes.obtenerHashMaestro(bean.getListaMotivoReingreso()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        } 
       
    }
    
    @Override
    public void consultarMaestroPosibleAltaTemprana(AucHospitalizacionBean bean){
        try {
            bean.setListaProgramaAltaTemprana(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_ALTA_TEMPRANA));
            bean.setHashProgramaAltaTemprana(AuConstantes.obtenerHashMaestro(bean.getListaProgramaAltaTemprana()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consutar la información, favor contactar al administrador");
        } 
       
    }
    
    public void crearAdjuntoSugerido(AucHospitalizacionBean bean) {
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

    public void guardarAdjuntoPrograma(AucHospitalizacionBean bean) {
        try {
            String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_ADJUNTOS_GESTIONES);
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los manuales del sistema");
                return;
            }
            if (bean.getPeAdjuntos().isEmpty()) {
                bean.addError("No hay adjuntos para guardar");
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            bean.auditoriaGuardar(bean.getObjectoAdjunto());
            bean.getObjectoAdjunto().setPeAfiliadosId(new PeAfiliadosPrograma(bean.getObjetoPePregrama().getId()));
            //PeGestion peGestion = new PeGestion();
            //peGestion.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(bean.getObjeto().getId()));
            //peGestion.setUsuariosId(new Usuario(bean.getConexion().getUsuario().getId()));
            //peGestion.setTipo(1); // OJO quemado mientras tanto
            String tipoAdjunto = "";
            StringBuilder prefijoNombreArchivo = new StringBuilder();
            prefijoNombreArchivo = nombreArchivo.append(bean.getHashTipoDocumento().get(bean.getObjeto().getAucAfiliadoId().getMaeTipoDocumentoId()).getValor())
                    .append(bean.getObjeto().getAucAfiliadoId().getNumeroDocumento())
                    .append("_");
            for (PeAdjunto peAdjunto : bean.getPeAdjuntos()) {
                nombreArchivo = new StringBuilder(prefijoNombreArchivo.toString());
                Maestro maestroTipoAdjunto = bean.getHashTiposSugeridoAdjuntos().get(peAdjunto.getMaeTipoArchivoId());
                nombreArchivo.append(maestroTipoAdjunto.getNombre());
                nombreArchivo
                        .append("_")
                        .append(sdf.format(new Date()));
                nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                bean.auditoriaGuardar(peAdjunto);
                //bean.auditoriaGuardar(peGestion);

                peAdjunto.setMaeTipoArchivoCodigo(maestroTipoAdjunto.getValor());
                peAdjunto.setMaeTipoArchivoValor(maestroTipoAdjunto.getNombre());
                peAdjunto.setPeAfiliadosId(new PeAfiliadosPrograma(bean.getObjetoPePregrama().getId()));
                peAdjunto.setNombre(nombreArchivo.toString());
                peAdjunto.setRuta(ruta);
                peAdjunto.setArchivo(nombreArchivo.append(peAdjunto.getExtensión()).toString());
                File archivo = new File(ruta, peAdjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(peAdjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(peAdjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                //peGestion.setDescripcion("Se adjunta el archivo de tipo " + tipoAdjunto + " con el nombre " + peAdjunto.getNombre() + "." + peAdjunto.getExtensión());
                //peAdjunto.setPeGestionesId(new PeGestion(getGestionProgramaRemoto().insertar(peGestion)));
                peAdjunto.setId(getPeAdjuntoProgramaRemoto().insertar(peAdjunto));
            }
            bean.getObjetoPePregrama().setListaPeAdjunto(getPeAdjuntoProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjetoPePregrama().getId()));
            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardarAdjuntoSugerido(AucHospitalizacionBean bean) {
        procesarSugeridosAdjuntos(bean.getObjetoSugerido().getListaAdjunto(), bean.getObjetoSugerido(), bean);
    }
    
    private void guardarNoAptoRescate(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimiento().setBorrado(Boolean.FALSE);
            int idSeguimiento = getAucHospitalizacionSeguimientoRemoto().insertar(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimiento().setId(idSeguimiento);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            bean.getObjeto().setFechaUltimaNota(bean.getObjetoSeguimiento().getFechaHoraCrea());
            getAucHospitalizacionRemoto().actualizarFechaUltimaNota(bean.getObjeto());
            getAucHospitalizacionRemoto().actualizarNoAptoRescate(bean.getObjeto());
            registrarHistorico(bean.getObjeto().toString(), bean);
            List<AuAnexo2Rescate> rescateAnexo2 = getAuAnexo2RescateRemoto().consultarPorNoaptoRescateAnexo2(bean.getObjeto().getCntPrestadorSedeId().getId(), bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            if(rescateAnexo2 != null && !rescateAnexo2.isEmpty()){
                for(AuAnexo2Rescate rescateAnexo: rescateAnexo2){
                    guardarGestionCambio(bean, rescateAnexo, bean.getObjetoSeguimiento().getDescripcion());
                }
            }
            List<AuAnexo2Rescate> rescateAnexo3 =getAuAnexo2RescateRemoto().consultarPorNoaptoRescateAnexo3(bean.getObjeto().getCntPrestadorSedeId().getId(), bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            if(rescateAnexo3 != null && !rescateAnexo3.isEmpty()){
                for(AuAnexo2Rescate rescateAnexo: rescateAnexo3){
                    guardarGestionCambio(bean, rescateAnexo, bean.getObjetoSeguimiento().getDescripcion());
                }            
            }
            listarSeguimientos(bean);
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoSeguimiento().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el seguimiento con id (" + bean.getObjetoSeguimiento().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarGestionCambio(AucHospitalizacionBean bean, AuAnexo2Rescate rescate, String desc) throws Exception {
        AuAnexo2RescateGestion gestionCambio = new AuAnexo2RescateGestion();
        gestionCambio.setAuAnexo2Rescate(rescate);
        gestionCambio.setTipo(AuAnexo2RescateGestion.TIPO_GESTION);
        gestionCambio.setObservacion(desc);
        gestionCambio.setFechaHoraGestion(new Date());
        bean.auditoriaGuardar(gestionCambio);
        getAuAnexo2RescateGestionRemoto().insertar(gestionCambio);
    }
    
    private void contactosAfiliadoRescate(AucHospitalizacionBean bean) {
        if (bean.getObjetoRescate().getAsegAfiliado().getListaAsegAfiliadoContacto() != null) {
            bean.setTelefonoFijoAfiliado(
                    bean.getObjetoRescate().getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_TELEFONO))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
            bean.setTelefonoMovilAfiliado(
                    bean.getObjetoRescate().getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_CELULAR))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
        } else {
            bean.setTelefonoFijoAfiliado(null);
            bean.setTelefonoMovilAfiliado(null);
        }
    }

    private void guardar(AucHospitalizacionBean bean) {
        try {
            //Guardado Afiliado
            bean.auditoriaGuardar(bean.getObjeto().getAucAfiliadoId());
            bean.getObjeto().getAucAfiliadoId().setId(getAucAfiliadoRemoto().insertar(bean.getObjeto().getAucAfiliadoId()));
            guardarTuPersonaContactos(bean);
            //Guardado Ingreso
            procesarIngreso(bean);
            //Guardado Auditor
            //procesarAuditor(bean);
            //Guardado Hospitalizacion
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_INGRESADO);
            Empresa empresa = getEmpresaRemoto().consultarPorPrestador(bean.getObjeto().getCntPrestadorSedeId().getCntPrestador().getId());
            if (empresa != null) {
                bean.getObjeto().setGnEmpresaId(empresa);
            } else {
                bean.getObjeto().setGnEmpresaId(bean.getConexion().getEmpresa());
            }
            bean.getObjeto().setFechaInicioHospitalizacion(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            bean.getObjeto().setFechaUltimaNota(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
            List<PePrograma> programaEspecialesRescate = (getPeProgramaRemoto().programasAplicaRescateHospitalizacion(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId(), bean.getObjeto().getCntPrestadorSedeId().getId()));
            if (!programaEspecialesRescate.isEmpty()) {
                bean.getObjeto().setAplicaRescate(AucHospitalizacion.APLICA_RESCATE);
            }else{
                bean.getObjeto().setAplicaRescate(AucHospitalizacion.NO_APLICA_RESCATE);
            }
            bean.getObjeto().setId(getAucHospitalizacionRemoto().insertar(bean.getObjeto()));
            //guardar estancia
            guardarEstanciaIncial(bean);
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            registrarHistorico(bean.getObjeto().toString(), bean);
            bean.addMensaje("Hospitalización guardada exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }

    }

    public void guardarEstanciaIncial(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionEstancia estancia = new AucHospitalizacionEstancia();
            bean.auditoriaGuardar(estancia);
            estancia.setAucHospitalizacionId(bean.getObjeto());
            estancia.setFechaIngreso(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            estancia.setDias(estancia.getCalcularDiasEstancia());
            Maestro servicio = bean.getHashServicio().get(bean.getObjeto().getAucIngresoId().getMaeServicioInicialId());
            if (servicio != null) {
                estancia.setMaeServicioId(bean.getObjeto().getAucIngresoId().getMaeServicioInicialId());
                estancia.setMaeServicioCodigo(servicio.getValor());
                estancia.setMaeServicioValor(servicio.getNombre());
            }
            int idEstancia = getAucHospitalizacionEstanciaRemoto().insertar(estancia);
            estancia.setId(idEstancia);
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void guardarTuPersonaContactos(AucHospitalizacionBean bean) {
        try {
            for (AucAfiliadoContacto tuPersonaContacto : bean.getListaTuPersonaContacto()) {
                if (tuPersonaContacto.getId() == null) {
                    tuPersonaContacto.setAucAfiliadoId(new AucAfiliado(bean.getObjeto().getAucAfiliadoId().getId()));
                    bean.auditoriaGuardar(tuPersonaContacto);
                    getAucAfiliadoContactoRemoto().insertar(tuPersonaContacto);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void procesarIngreso(AucHospitalizacionBean bean) {

        try {
            //calcularRegingreso(bean);
            completarHistoricoIngreso(bean);
            bean.auditoriaGuardar(bean.getObjeto().getAucIngresoId());
            bean.getObjeto().getAucIngresoId().setFuenteOrigen(0);
            bean.getObjeto().getAucIngresoId().setId(getAucIngresoRemoto().insertar(bean.getObjeto().getAucIngresoId()));
            for (AucDiagnostico diagnostico : bean.getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                if (diagnostico.getId() == null) {
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setAucIngresosId(bean.getObjeto().getAucIngresoId());
                    int idDiagnostico = getAucDiagnosticoRemoto().insertar(diagnostico);
                    diagnostico.setId(idDiagnostico);
                }
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void procesarAuditor(AucHospitalizacionBean bean) {
        try {
            AucAuditor auditor = new AucAuditor();
            auditor.setId(null);
            auditor.setActivo(true);
            auditor.setGnUsuarioId(bean.getObjeto().getGnUsuariosAuditorId());
            auditor.setCntPrestadorId(bean.getObjeto().getCntPrestadorId());
            auditor.setCntPrestadorSedeId(bean.getObjeto().getCntPrestadorSedeId());
            bean.auditoriaGuardar(auditor);
            auditor.setId(getAucAuditorRemoto().insertar(auditor));
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    /*private void borrarDiagnostico(AucHospitalizacionBean bean) {
        try {
            AucDiagnostico diagnosticos = getAucDiagnosticoRemoto().consultar(bean.getObjetoDiagnostico().getId());
            if(diagnosticos != null){
                bean.setObjetoDiagnostico(getAucDiagnosticoRemoto().eliminar(diagnosticos.getId()));
                if(diagnosticos.isPrincipal()){
                    if(diagnosticos.getAucIngresosId() != null){
                        if(diagnosticos.getAucIngresosId().getId() != null){
                            List<AucDiagnostico> lista = getAucDiagnosticoRemoto().consultarPorIdIngreso(diagnosticos.getAucIngresosId().getId());
                            if(!lista.isEmpty()){
                                AucDiagnostico diagnosticoIngreso = lista.get(0);
                                diagnosticoIngreso.setPrincipal(true);
                                getAucDiagnosticoRemoto().actualizar(diagnosticoIngreso);
                            }
                        }
                    }else if(diagnosticos.getAucEgresosId() != null){
                        if(diagnosticos.getAucEgresosId().getId() != null){
                            List<AucDiagnostico> lista = getAucDiagnosticoRemoto().consultarPorIdEgreso(diagnosticos.getAucEgresosId().getId());
                            if(!lista.isEmpty()){
                                AucDiagnostico diagnosticoEgreso = lista.get(0);
                                diagnosticoEgreso.setPrincipal(true);
                                getAucDiagnosticoRemoto().actualizar(diagnosticoEgreso);
                            }
                        }
                    }
                }
                
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }
           
           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }*/
    private void borrarDiagnostico(AucHospitalizacionBean bean) {
        try {
            if (!bean.getListaDiagnosticosBorrar().isEmpty()) {
                for (AucDiagnostico diagnostico : bean.getListaDiagnosticosBorrar()) {
                    AucDiagnostico diagnosticos = getAucDiagnosticoRemoto().consultar(diagnostico.getId());
                    if (diagnosticos != null) {
                        bean.setObjetoDiagnostico(getAucDiagnosticoRemoto().eliminar(diagnosticos.getId()));
                        /*if(diagnosticos.isPrincipal()){
                            if(diagnosticos.getAucIngresosId() != null){
                                if(diagnosticos.getAucIngresosId().getId() != null){
                                    List<AucDiagnostico> lista = getAucDiagnosticoRemoto().consultarPorIdIngreso(diagnosticos.getAucIngresosId().getId());
                                    if(!lista.isEmpty()){
                                        AucDiagnostico diagnosticoIngreso = lista.get(0);
                                        diagnosticoIngreso.setPrincipal(true);
                                        getAucDiagnosticoRemoto().actualizar(diagnosticoIngreso);
                                    }
                                }
                            }else if(diagnosticos.getAucEgresosId() != null){
                                if(diagnosticos.getAucEgresosId().getId() != null){
                                    List<AucDiagnostico> lista = getAucDiagnosticoRemoto().consultarPorIdEgreso(diagnosticos.getAucEgresosId().getId());
                                    if(!lista.isEmpty()){
                                        AucDiagnostico diagnosticoEgreso = lista.get(0);
                                        diagnosticoEgreso.setPrincipal(true);
                                        getAucDiagnosticoRemoto().actualizar(diagnosticoEgreso);
                                    }
                                }
                            }
                        }*/
                    }
                }
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarDiagnosticoEstanciaDb(AucHospitalizacionBean bean) {
        try {
            if (!bean.getListaDiagnosticosEstanciaEspecialidadBorrar().isEmpty()) {
                for (AucDiagnostico diagnostico : bean.getListaDiagnosticosEstanciaEspecialidadBorrar()) {
                    AucDiagnostico diagnosticos = getAucDiagnosticoRemoto().consultar(diagnostico.getId());
                    if (diagnosticos != null) {
                        bean.setObjetoDiagnostico(getAucDiagnosticoRemoto().eliminar(diagnosticos.getId()));
                        registrarHistorico(diagnosticos.toString(), bean);
                    }
                }
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }
            bean.setListaDiagnosticosEstanciaEspecialidadBorrar(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarDiagnosticoEgreso(AucHospitalizacionBean bean) {
        try {
            if (!bean.getListaDiagnosticosEgresoBorrar().isEmpty()) {
                for (AucDiagnostico diagnostico : bean.getListaDiagnosticosEgresoBorrar()) {
                    AucDiagnostico diagnosticos = getAucDiagnosticoRemoto().consultar(diagnostico.getId());
                    if (diagnosticos != null) {
                        bean.setObjetoDiagnostico(getAucDiagnosticoRemoto().eliminar(diagnosticos.getId()));
                        /*if(diagnosticos.isPrincipal()){
                            if(diagnosticos.getAucIngresosId() != null){
                                if(diagnosticos.getAucIngresosId().getId() != null){
                                    List<AucDiagnostico> lista = getAucDiagnosticoRemoto().consultarPorIdIngreso(diagnosticos.getAucIngresosId().getId());
                                    if(!lista.isEmpty()){
                                        AucDiagnostico diagnosticoIngreso = lista.get(0);
                                        diagnosticoIngreso.setPrincipal(true);
                                        getAucDiagnosticoRemoto().actualizar(diagnosticoIngreso);
                                    }
                                }
                            }else if(diagnosticos.getAucEgresosId() != null){
                                if(diagnosticos.getAucEgresosId().getId() != null){
                                    List<AucDiagnostico> lista = getAucDiagnosticoRemoto().consultarPorIdEgreso(diagnosticos.getAucEgresosId().getId());
                                    if(!lista.isEmpty()){
                                        AucDiagnostico diagnosticoEgreso = lista.get(0);
                                        diagnosticoEgreso.setPrincipal(true);
                                        getAucDiagnosticoRemoto().actualizar(diagnosticoEgreso);
                                    }
                                }
                            }
                        }*/
                    }
                }
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarDiagnosticoEgreso(List<AucDiagnostico> diagnosticos, AucHospitalizacionBean bean) {
        try {
            if (!diagnosticos.isEmpty()) {
                for (AucDiagnostico diagnostico : diagnosticos) {
                    AucDiagnostico consultadiagnosticos = getAucDiagnosticoRemoto().consultar(diagnostico.getId());
                    if (consultadiagnosticos != null) {
                        bean.setObjetoDiagnostico(getAucDiagnosticoRemoto().eliminar(consultadiagnosticos.getId()));
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarEstancia(AucHospitalizacionBean bean) {
        try {
            int posicion = -1;
            for (AucHospitalizacionEstancia estancia : bean.getObjeto().getAucHospitalizacionEstanciaList()) {
                if (estancia.getId().equals(bean.getObjetoEstancia().getId())) {
                    posicion = bean.getObjeto().getAucHospitalizacionEstanciaList().indexOf(estancia);
                    break;
                }
            }
            if (posicion != -1) {
                if (posicion != 0) {
                    for (int i = posicion; i < bean.getObjeto().getAucHospitalizacionEstanciaList().size(); i++) {
                        AucHospitalizacionEstancia estancia = getAucHospitalizacionEstanciaRemoto().consultar(bean.getObjeto().getAucHospitalizacionEstanciaList().get(i).getId());
                        if (estancia != null) {
                            getAucHospitalizacionEstanciaRemoto().eliminar(estancia.getId());
                            registrarHistorico(estancia.toString(), bean);
                            bean.getObjeto().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
                            i--;
                        }
                    }
                } else {
                    bean.addError("No puede eliminar la primera estancia");
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarAdverso(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionAdverso adverso = bean.getObjetoAdverso();
            adverso.setBorrado(true);
            bean.auditoriaModificar(adverso);
            getAucHospitalizacionAdversoRemoto().borradoLogico(adverso);
            registrarHistorico(adverso.toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarObjecion(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionObjecion objecion = bean.getObjetoObjecion();
            objecion.setBorrado(true);
            bean.auditoriaModificar(objecion);
            getAucHospitalizacionObjecionRemoto().borradoLogico(objecion);
            registrarHistorico(objecion.toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarInoportunidad(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionInoportunidad inoportunidad = bean.getObjetoInoportunidad();
            inoportunidad.setBorrado(true);
            bean.auditoriaModificar(inoportunidad);
            getAucHospitalizacionInoportunidadRemoto().borradoLogico(inoportunidad);
            registrarHistorico(inoportunidad.toString(), bean);

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarServicio(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionServicio servicio = bean.getObjetoServicio();
            servicio.setBorrado(true);
            bean.auditoriaModificar(servicio);
            getAucHospitalizacionServicioRemoto().borradoLogico(servicio);
            registrarHistorico(servicio.toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarGestorasRegionales(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionSeguimiento seguimiento = bean.getObjetoSeguimiento();
            bean.auditoriaModificar(seguimiento);
            seguimiento.setBorrado(true);
            seguimiento.setUsuarioBorra(seguimiento.getUsuarioBorra());
            seguimiento.setTerminalBorra(seguimiento.getTerminalBorra());
            seguimiento.setFechaHoraBorra(seguimiento.getFechaHoraBorra());
            getAucHospitalizacionSeguimientoRemoto().actualizarBorradoLogico(seguimiento);
            registrarHistorico(seguimiento.toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarDiagnosticoEstancia(AucHospitalizacionBean bean) {
        try {
           borrarDiagnosticoEstanciaDb(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarSugerido(AucHospitalizacionBean bean) {
        try {

            bean.auditoriaModificar(bean.getObjetoSugerido());
            if (bean.getObjetoSugerido().getObservacionRechazo() == null || bean.getObjetoSugerido().getObservacionRechazo().isEmpty()) {
                bean.addError("Se debe ingresar la descripción del rechazo.");
                return;
            }
            bean.getObjetoSugerido().setEstado(PeConstantes.PE_ESTADO_SUGERIDO_RECHAZADO);
            bean.getObjetoSugerido().setOrigenRechazo(PeConstantes.PE_SUGERIDO_ORIGEN_HOSPITALIZACION);
            getPeAfiliadoSugeridoRemoto().rechazar(bean.getObjetoSugerido());
            bean.addMensaje("El registro se rechazó correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarContactoPersona(AucHospitalizacionBean bean) {
        try {
            AucAfiliadoContacto contacto = getAucAfiliadoContactoRemoto().consultar(bean.getAucAfiliadoContacto().getId());
            if (contacto != null) {
                getAucAfiliadoContactoRemoto().eliminar(contacto.getId());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void editar(AucHospitalizacionBean bean) {
        try {
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
            bean.setListaTiposIngreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_INGRESO));
            bean.setHashTiposIngreso(AuConstantes.obtenerHashMaestro(bean.getListaTiposIngreso()));
            bean.setListaCtnModalidades(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_MODALIDAD_CONTRATO));
            bean.setHashCtnModalidades(AuConstantes.obtenerHashMaestro(bean.getListaCtnModalidades()));
            bean.setListaRemisionesNoPertinentes(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_REMISION_NO_PERTINENTE));
            bean.setHashRemisionesNoPertinentes(AuConstantes.obtenerHashMaestro(bean.getListaRemisionesNoPertinentes()));
            bean.setListaTiposDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TIPO_DIAGNOSTICO));
            bean.setHashTiposDiagnostico(AuConstantes.obtenerHashMaestro(bean.getListaTiposDiagnostico()));
            bean.setListaConductaEgreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CONDUCTA_EGRESO));
            bean.setHashConductaEgreso(AuConstantes.obtenerHashMaestro(bean.getListaConductaEgreso()));
            bean.setListaCausaIngresoPrevenibles(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CAUSA_INGRESO_PREVENIBLE));
            bean.setHashCausaIngresoPrevenible(AuConstantes.obtenerHashMaestro(bean.getListaCausaIngresoPrevenibles()));
            bean.setListaAreaResponsableIngresoPrevenible(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_AREA_INGRESO_PREVENIBLE));
            bean.setHashAreaResponsableIngresoPrevenible(AuConstantes.obtenerHashMaestro(bean.getListaAreaResponsableIngresoPrevenible()));
               
            bean.setObjeto(getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().isCierreAuditoria()) {
                bean.setBloquearBotonesCierreAuditoria(true);
            } else {
                bean.setBloquearBotonesCierreAuditoria(false);
            }
            bean.getObjeto().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getId()));
            bean.getObjeto().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjeto().getAucIngresoId().getId()));
            bean.getObjeto().getAucIngresoId().setUltimaFechaIngreso(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            bean.cargarConsultarMaestroMotivoReingreso();
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            bean.setListaContactosAseg(afiliado.getListaAsegAfiliadoContacto());
            listarTuPersonaContactos(bean);
            if (bean.getObjeto().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcion(0);
            }
            bean.eventoConsultarMaestroPosibleAltaTemprana();
            if(bean.getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId() != null){
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(1);
                bean.setCampoObligatorioAreaResponsableIngresoPrevenible(true);
                bean.setHabilitarAreaResponsableIngresoPrevenible(false);
            }else{
                bean.getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(0);
                bean.setCampoObligatorioAreaResponsableIngresoPrevenible(false);
                bean.setHabilitarAreaResponsableIngresoPrevenible(true);
            }
            AucHospitalizacionEstado ultimoEstado = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoEgreso(bean.getObjeto().getId());
            if (ultimoEstado != null && bean.getObjeto().getEstado() != AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO) {
                bean.getObjeto().setFuenteOrigenEgreso(ultimoEstado.getFuenteOrigen().toString());
            }
            AucHospitalizacionEstado ultimoEstadoDevuelto = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoDevuelto(bean.getObjeto().getId());
            if (ultimoEstadoDevuelto != null) {
                bean.getObjeto().setDevuelto(true);
                bean.getObjeto().setObservacionDevolucion(ultimoEstadoDevuelto.getObservacion());
            }
            bean.getObjeto().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorId().getId()));
            bean.getObjeto().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedeId().getId()));
            bean.getObjeto().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(getAucDiagnosticoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionGestorasRegionalesList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacionGestorasRegionales(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucRescateList(getAuAnexo2RescateRemoto().rescatesPorHospitalizacion(bean.getObjeto().getId()));
            if (!bean.getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date fechaEgreso = (bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                        && !bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) ? bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() : null;
                if (fechaEgreso != null) {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                } else {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                }
            }
            if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getAucEgresoId().getId() != null) {
                bean.getObjeto().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjeto().getAucEgresoId().getId()));
                if (bean.getObjeto().getAucEgresoId().isIpsEntregaValor()) {
                    bean.setHabilitarCampoValorEstancia(false);
                    bean.setCampoObligatorioValorEstancia(true);
                } else {
                    bean.setHabilitarCampoValorEstancia(true);
                    bean.setCampoObligatorioValorEstancia(false);
                }
                if (bean.getObjeto().getAucEgresoId().isFallecido()) {
                    bean.setHabilitarEgresoNumeroCertificado(false);
                    bean.setCampoObligatorioNumeroCertificado(true);
                    consultarLimpiarMaestroDestion(bean);
                } else {
                    bean.setHabilitarEgresoNumeroCertificado(true);
                    bean.setCampoObligatorioNumeroCertificado(false);
                    consultarMaestroDestino(bean);
                }

            } else {
                bean.getObjeto().getAucEgresoId().setIpsEntregaValor(true);
                bean.getObjeto().getAucEgresoId().setValorEstancia(BigDecimal.ZERO);
                bean.setHabilitarCampoValorEstancia(false);
                bean.setCampoObligatorioValorEstancia(true);
                bean.setHabilitarEgresoNumeroCertificado(true);
                bean.setCampoObligatorioNumeroCertificado(false);
            }
            bean.getObjeto().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            bean.getObjeto().setAucProgramaEspecialList(getPeProgramaRemoto().programasMatriculadosSugeridos(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarSeguimiento(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoSeguimiento(getAucHospitalizacionSeguimientoRemoto().consultar(bean.getObjetoSeguimiento().getId()));
            if( bean.getObjetoSeguimiento().getCntPrestadorSedesId() != null){
                bean.getObjetoSeguimiento().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjetoSeguimiento().getCntPrestadorSedesId().getId()));                
            }
            if(bean.getObjetoSeguimiento().getCntPrestadoresId() != null){
                bean.getObjetoSeguimiento().setCntPrestadoresId(getPrestadorRemoto().consultar(bean.getObjetoSeguimiento().getCntPrestadoresId().getId()));
            }
            bean.habilitarCampoIpsReceptorasCargar();
            bean.habilitarCampoFechaCierreGestion();
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarEstancia(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoEstancia(getAucHospitalizacionEstanciaRemoto().consultar(bean.getObjetoEstancia().getId()));
            Date fechaEgreso = bean.getObjetoEstancia().getFechaEgreso();
            if (fechaEgreso != null) {
                bean.getObjetoEstancia().setUltimaFechaEgreso(fechaEgreso);
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarJustificacionEstanciaProlongada(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoJustificacionEstanciasProlongada(getAucJustificacionEstanciasProlongadaRemoto().consultar(bean.getObjetoJustificacionEstanciasProlongada().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarPatologia(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoPatologia(getAucHospitalizacionPatologiaRemoto().consultar(bean.getObjetoPatologia().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarInoportunidad(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoInoportunidad(getAucHospitalizacionInoportunidadRemoto().consultar(bean.getObjetoInoportunidad().getId()));
            if (bean.getObjetoInoportunidad() != null) {
                if (bean.getObjetoInoportunidad().getMaeMotivoInoportunidadId() != null) {
                    bean.setCampoObligatorioMotivoInoportidad(true);
                    bean.setHabilitarCampoMotivoInoportidad(false);
                } else {
                    bean.setCampoObligatorioMotivoInoportidad(false);
                    bean.setHabilitarCampoMotivoInoportidad(true);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarAdverso(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoAdverso(getAucHospitalizacionAdversoRemoto().consultar(bean.getObjetoAdverso().getId()));
            listarEstadosDeSubCategoria(bean);
            if (bean.getObjetoAdverso().getUsuarioModifica() != null) {
                boolean empresaAdministradora = consultarEmpresaAdministradora(bean);
                if (empresaAdministradora) {
                    bean.setHabilitarCampoEmpresaAdministra(empresaAdministradora);
                } else {
                    bean.setHabilitarCampoEmpresaAdministra(empresaAdministradora);
                }
            } else {
                bean.setHabilitarCampoEmpresaAdministra(false);
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarObjecion(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoObjecion(getAucHospitalizacionObjecionRemoto().consultar(bean.getObjetoObjecion().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void editarServicio(AucHospitalizacionBean bean) {
        try {
            bean.setObjetoServicio(getAucHospitalizacionServicioRemoto().consultar(bean.getObjetoServicio().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void modificar(AucHospitalizacionBean bean) {
        try {
            //Actualizar Ingreso
            bean.auditoriaModificar(bean.getObjeto().getAucIngresoId());
            if (bean.getObjeto().getAucIngresoId().getFechaIngreso() != bean.getObjeto().getAucIngresoId().getUltimaFechaIngreso()) {
                if (bean.getObjeto().getAucHospitalizacionEstanciaList() != null) {
                    Date fechaIngreso = bean.getObjeto().getAucIngresoId().getFechaIngreso();

                    int autoIncrementable = 0;
                    for (AucHospitalizacionEstancia estancia : bean.getObjeto().getAucHospitalizacionEstanciaList()) {
                        if (autoIncrementable == 0) {
                            estancia.setFechaIngreso(fechaIngreso);
                            if (estancia.getFechaEgreso() != null) {
                                int milisecondsByDay = 86400000;
                                int dias = (int) ((estancia.getFechaEgreso().getTime() - estancia.getFechaIngreso().getTime()) / milisecondsByDay);
                                if (dias == 0) {
                                    estancia.setDias(1);
                                } else {
                                    estancia.setDias(dias);
                                }
                            }
                            bean.auditoriaModificar(estancia);
                            getAucHospitalizacionEstanciaRemoto().actualizar(estancia);
                        }
                        autoIncrementable++;
                    }
                }
            }
            //calcularRegingreso(bean);
            completarHistoricoIngreso(bean);
            getAucIngresoRemoto().actualizar(bean.getObjeto().getAucIngresoId());
            bean.getObjeto().setFechaInicioHospitalizacion(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            getAucHospitalizacionRemoto().actualizaFechaInicioHospitalizacion(bean.getObjeto());
            borrarDiagnostico(bean);
            if (!bean.getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
                for (AucDiagnostico diagnosticoIngreso : bean.getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                    bean.auditoriaModificar(diagnosticoIngreso);
                    getAucDiagnosticoRemoto().actualizar(diagnosticoIngreso);
                }
            }
            borrarDiagnosticoEgreso(bean);
            if (!bean.getObjeto().getAucEgresoId().getAucDiagnosticosList().isEmpty()) {
                for (AucDiagnostico diagnosticoEgreso : bean.getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                    bean.auditoriaModificar(diagnosticoEgreso);
                    getAucDiagnosticoRemoto().actualizar(diagnosticoEgreso);
                }
            }

            registrarHistorico(bean.getObjeto().getAucIngresoId().toString(), bean);
            //actualizar egreso
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            if (bean.getObjeto().getAucEgresoId() != null) {
                if (bean.getObjeto().getAucEgresoId().getId() != null) {
                    bean.auditoriaModificar(bean.getObjeto().getAucEgresoId());
                    completarEgresoMaestro(bean);
                    getAucEgresoRemoto().actualizar(bean.getObjeto().getAucEgresoId());
                    registrarHistorico(bean.getObjeto().getAucEgresoId().toString(), bean);
                }
            }

            //Actualizar Hospitalizacion
            if (!hospitalizacion.getGnUsuariosAuditorId().getId().equals(bean.getObjeto().getGnUsuariosAuditorId().getId())) {
                AucAuditorHistorico historicoAuditor = new AucAuditorHistorico();
                //historicoAuditor.setAucAuditorId(bean.getAuditoria())

                //getAucAuditorHistoricoRemoto().insertar(obj)
            }
            bean.auditoriaModificar(bean.getObjeto());
            getAucHospitalizacionRemoto().actualizar(bean.getObjeto());
            if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getAucEgresoId().getId() != null) {
                bean.getObjeto().setFechaFinHospitalizacion(bean.getObjeto().getAucEgresoId().getFechaEgreso());
                getAucHospitalizacionRemoto().actualizaFechaFinHospitalizacion(bean.getObjeto());
            }
            bean.validarMotoSuperiorACienMil();
            bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
            getAucHospitalizacionRemoto().actualizaDiasHospitalizacion(bean.getObjeto());
            registrarHistorico(bean.getObjeto().toString(), bean);
            bean.setListaDiagnosticosBorrar(new ArrayList<>());
            bean.setListaDiagnosticosEgresoBorrar(new ArrayList<>());
            bean.addMensaje("Hospitalización actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarSeguimiento(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoSeguimiento());
            getAucHospitalizacionSeguimientoRemoto().actualizar(bean.getObjetoSeguimiento());
            registrarHistorico(bean.getObjetoSeguimiento().toString(), bean);
            bean.addMensaje("El Seguimiento se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    public void modificarGestorasRegionales(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoSeguimiento());
            getAucHospitalizacionSeguimientoRemoto().actualizarGestorasRegionales(bean.getObjetoSeguimiento());
            registrarHistorico(bean.getObjetoSeguimiento().toString(), bean);
            bean.addMensaje("El Gestoras Regionales se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    public void modificarEstancia(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoEstancia());
            boolean modificarEgreso = false;
            if (bean.getObjetoEstancia().getFechaEgreso() != bean.getObjetoEstancia().getUltimaFechaEgreso()) {
                boolean cambiar = false;
                Date fechaAnterior = new Date();
                for (AucHospitalizacionEstancia estancia : bean.getObjeto().getAucHospitalizacionEstanciaList()) {
                    if (cambiar) {
                        estancia.setFechaIngreso(fechaAnterior);
                        if (estancia.getFechaEgreso() != null) {
                            modificarEgreso = true;
                            Calendar fecha = Calendar.getInstance();
                            fecha.setTime(fechaAnterior);
                            fecha.add(Calendar.DAY_OF_MONTH, estancia.getDias());
                            estancia.setFechaEgreso(fecha.getTime());
                        }
                        bean.auditoriaModificar(estancia);
                        getAucHospitalizacionEstanciaRemoto().actualizar(estancia);
                    }
                    if (estancia.getId().equals(bean.getObjetoEstancia().getId())) {
                        cambiar = true;
                        fechaAnterior = bean.getObjetoEstancia().getFechaEgreso();
                        bean.auditoriaModificar(estancia);
                        getAucHospitalizacionEstanciaRemoto().actualizar(bean.getObjetoEstancia());
                    }

                }
            } else {
                getAucHospitalizacionEstanciaRemoto().actualizar(bean.getObjetoEstancia());
            }
            if (modificarEgreso) {
                if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getId() != null) {
                    int ultimo = bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1;
                    Date fechaEgreso = bean.getObjeto().getAucHospitalizacionEstanciaList().get(ultimo).getFechaEgreso();
                    if (fechaEgreso != null) {
                        bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                        bean.auditoriaModificar(bean.getObjeto().getAucEgresoId());
                        getAucEgresoRemoto().actualizar(bean.getObjeto().getAucEgresoId());
                    }

                }
            }
            bean.getObjeto().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            if (!bean.getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date fechaEgreso = (bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                        && !bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) ? bean.getObjeto().getAucHospitalizacionEstanciaList().get(bean.getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() : null;
                if (fechaEgreso != null) {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                } else {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(fechaEgreso);
                }
            }
            if (bean.validarEstadoEgresado(bean.getObjeto().getEstado())) {
                bean.getObjeto().setFechaFinHospitalizacion(bean.getObjetoEstancia().getFechaEgreso());
                getAucHospitalizacionRemoto().actualizaFechaFinHospitalizacion(bean.getObjeto());
                if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getAucEgresoId().getId() != null) {
                    bean.getObjeto().getAucEgresoId().setFechaEgreso(bean.getObjetoEstancia().getFechaEgreso());
                    getAucEgresoRemoto().actualizar(bean.getObjeto().getAucEgresoId());
                }
            }
            bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
            getAucHospitalizacionRemoto().actualizaDiasHospitalizacion(bean.getObjeto());
            registrarHistorico(bean.getObjetoEstancia().toString(), bean);
            bean.addMensaje("La Estancia se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarJustificacionEstanciaProlongada(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoJustificacionEstanciasProlongada());
            getAucJustificacionEstanciasProlongadaRemoto().actualizar(bean.getObjetoJustificacionEstanciasProlongada());
            registrarHistorico(bean.getObjetoJustificacionEstanciasProlongada().toString(), bean);
            bean.addMensaje("La justificacion estancia prolongada se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarIngreso(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto().getAucIngresoId());
            if (bean.getObjeto().getAucIngresoId().getFechaIngreso() != bean.getObjeto().getAucIngresoId().getUltimaFechaIngreso()) {
                if (bean.getObjeto().getAucHospitalizacionEstanciaList() != null) {
                    SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyy-MM-dd");
                    String formatFechaIngreso = formatterFecha.format(bean.getObjeto().getAucIngresoId().getFechaIngreso());
                    LocalDate fechaIngreso = LocalDate.parse(formatFechaIngreso);
                    //Date fechaIngreso = bean.getObjeto().getAucIngresoId().getFechaIngreso();
                    int autoIncrementable = 0;
                    for (AucHospitalizacionEstancia estancia : bean.getObjeto().getAucHospitalizacionEstanciaList()) {
                        if (autoIncrementable == 0) {
                            estancia.setFechaIngreso(bean.getObjeto().getAucIngresoId().getFechaIngreso());
                            if (estancia.getFechaEgreso() != null) {
                                String formatFechaEgreso = formatterFecha.format(estancia.getFechaEgreso());
                                LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
                                int dias = (int) DAYS.between(fechaIngreso, fechaEgreso);
                                //int milisecondsByDay = 86400000;
                                //int dias = (int) (( estancia.getFechaEgreso().getTime() - estancia.getFechaIngreso().getTime()) / milisecondsByDay);
                                estancia.setDias(dias);
                            } else {
                                //int milisecondsByDay = 86400000;
                                //Long fechaActual = new Date().getTime();
                                //int dias = (int) ((fechaActual  - estancia.getFechaIngreso().getTime()) / milisecondsByDay);
                                int dias = (int) DAYS.between(fechaIngreso, LocalDate.now());
                                estancia.setDias(dias);
                            }
                            bean.auditoriaModificar(estancia);
                            getAucHospitalizacionEstanciaRemoto().actualizar(estancia);
                        }
                        autoIncrementable++;
                        /*if (estancia.getFechaEgreso() != null) {
                            int milisecondsByDay = 86400000;
                            int dias = (int) ((estancia.getFechaIngreso().getTime() - estancia.getFechaEgreso().getTime()) / milisecondsByDay);
                            Calendar fechaEgreso = Calendar.getInstance();
                            fechaEgreso.setTime(fechaIngreso);
                            fechaEgreso.add(Calendar.DAY_OF_MONTH, dias);
                            estancia.setFechaEgreso(fechaEgreso.getTime());
                            fechaIngreso = fechaEgreso.getTime();
                        }*/

                    }
                }
            }
            //calcularRegingreso(bean);
            completarHistoricoIngreso(bean);
            getAucIngresoRemoto().actualizar(bean.getObjeto().getAucIngresoId());
            // borrarDiagnostico
            borrarDiagnostico(bean);
            if (!bean.getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
                for (AucDiagnostico diagnosticoIngreso : bean.getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                    if (diagnosticoIngreso.getId() == null) {
                        diagnosticoIngreso.setAucIngresosId(bean.getObjeto().getAucIngresoId());
                        bean.auditoriaGuardar(diagnosticoIngreso);
                        int idDiagnostico = getAucDiagnosticoRemoto().insertar(diagnosticoIngreso);
                        diagnosticoIngreso.setId(idDiagnostico);
                    } else {
                        bean.auditoriaModificar(diagnosticoIngreso);
                        getAucDiagnosticoRemoto().actualizar(diagnosticoIngreso);
                    }
                }
            }

            registrarHistorico(bean.getObjeto().getAucIngresoId().toString(), bean);
            getAucHospitalizacionRemoto().actualizaFechaInicioHospitalizacion(bean.getObjeto());
            bean.getObjeto().setFechaInicioHospitalizacion(bean.getObjeto().getAucIngresoId().getFechaIngreso());
            bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
            getAucHospitalizacionRemoto().actualizaDiasHospitalizacion(bean.getObjeto());
            registrarHistorico(bean.getObjeto().toString(), bean);
            bean.setListaDiagnosticosBorrar(new ArrayList<>());
            bean.addMensaje("Ingreso se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarEgreso(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto().getAucEgresoId());
            completarEgresoMaestro(bean);
            getAucEgresoRemoto().actualizar(bean.getObjeto().getAucEgresoId());

            bean.auditoriaModificar(bean.getObjeto());
            if (bean.getObjeto().isCierreAuditoria()) {
                bean.getObjeto().setUsuarioCierreAuditoria(bean.getObjeto().getUsuarioModifica());
                bean.getObjeto().setTerminalCierreAuditoria(bean.getObjeto().getTerminalModifica());
                bean.getObjeto().setFechaHoraCierreAuditoria(bean.getObjeto().getFechaHoraModifica());
                bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_EGRESADO);
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_CERRADO);
            } else {
                bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_EGRESADO);
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EGRESADO);
            }
            borrarDiagnosticoEgreso(bean);
            for (AucDiagnostico diagnostico : bean.getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                if (diagnostico.getId() == null) {
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setAucEgresosId(bean.getObjeto().getAucEgresoId());
                    int idDiagnostico = getAucDiagnosticoRemoto().insertar(diagnostico);
                    diagnostico.setId(idDiagnostico);
                } else {
                    bean.auditoriaModificar(diagnostico);
                    getAucDiagnosticoRemoto().actualizar(diagnostico);
                }
            }
            getAucHospitalizacionRemoto().actualizar(bean.getObjeto());
            bean.getObjeto().setFechaFinHospitalizacion(bean.getObjeto().getAucEgresoId().getFechaEgreso());
            getAucHospitalizacionRemoto().actualizaFechaFinHospitalizacion(bean.getObjeto());
            bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
            getAucHospitalizacionRemoto().actualizaDiasHospitalizacion(bean.getObjeto());
            bean.setListaDiagnosticosEgresoBorrar(new ArrayList<>());
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            registrarHistorico(bean.getObjeto().getAucEgresoId().toString(), bean);
            bean.addMensaje("Se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar Egreso");
        }
    }

    public void modificarPatologia(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoPatologia());
            getAucHospitalizacionPatologiaRemoto().actualizar(bean.getObjetoPatologia());

            registrarHistorico(bean.getObjetoPatologia().toString(), bean);
            bean.addMensaje("La Patologia se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarInoportunidad(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoInoportunidad());
            getAucHospitalizacionInoportunidadRemoto().actualizar(bean.getObjetoInoportunidad());

            registrarHistorico(bean.getObjetoInoportunidad().toString(), bean);
            bean.addMensaje("La Inoportunidad se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarAdverso(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoAdverso());
            getAucHospitalizacionAdversoRemoto().actualizar(bean.getObjetoAdverso());

            registrarHistorico(bean.getObjetoAdverso().toString(), bean);
            bean.addMensaje("El Adverso se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarObjecion(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoObjecion());
            getAucHospitalizacionObjecionRemoto().actualizar(bean.getObjetoObjecion());

            registrarHistorico(bean.getObjetoObjecion().toString(), bean);
            bean.addMensaje("La Objecion se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    public void modificarServicio(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoServicio());
            getAucHospitalizacionServicioRemoto().actualizar(bean.getObjetoServicio());

            registrarHistorico(bean.getObjetoServicio().toString(), bean);
            bean.addMensaje("El servicio se actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void guardarGestion(AucHospitalizacionBean bean) {
        try {
            //Si cambio la fecha de ingreso
            if (bean.getObjeto().getAucIngresoId().getFechaIngreso() != bean.getObjeto().getAucIngresoId().getUltimaFechaIngreso()) {
                if (bean.getObjeto().getAucHospitalizacionEstanciaList() != null) {
                    Date fechaIngreso = bean.getObjeto().getAucIngresoId().getFechaIngreso();
                    for (AucHospitalizacionEstancia estancia : bean.getObjeto().getAucHospitalizacionEstanciaList()) {
                        estancia.setFechaIngreso(fechaIngreso);
                        if (estancia.getFechaEgreso() != null) {
                            int milisecondsByDay = 86400000;
                            int dias = (int) ((estancia.getFechaIngreso().getTime() - estancia.getFechaEgreso().getTime()) / milisecondsByDay);
                            Calendar fechaEgreso = Calendar.getInstance();
                            fechaEgreso.setTime(fechaIngreso);
                            fechaEgreso.add(Calendar.DAY_OF_MONTH, dias);
                            estancia.setFechaEgreso(fechaEgreso.getTime());
                            fechaIngreso = fechaEgreso.getTime();
                        }
                        bean.auditoriaModificar(estancia);
                        getAucHospitalizacionEstanciaRemoto().actualizar(estancia);
                    }
                }
            }

            //guarda Egreso
            procesarEgreso(bean);
            if (bean.getObjeto().isCierreAuditoria()) {
                bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_EGRESADO);
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_CERRADO);
            } else {
                bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_EGRESADO);
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EGRESADO);
            }
            getAucHospitalizacionRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Hospitalización actualizó exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }

    }

    public void guardarEgreso(AucHospitalizacionBean bean) {
        try {
            procesarEgreso(bean);
            bean.auditoriaModificar(bean.getObjeto());
            if (bean.getObjeto().isCierreAuditoria()) {
                bean.getObjeto().setUsuarioCierreAuditoria(bean.getObjeto().getUsuarioModifica());
                bean.getObjeto().setTerminalCierreAuditoria(bean.getObjeto().getTerminalModifica());
                bean.getObjeto().setFechaHoraCierreAuditoria(bean.getObjeto().getFechaHoraModifica());
                bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_EGRESADO);
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_CERRADO);

            } else {
                bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_EGRESADO);
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EGRESADO);
            }
            bean.getObjeto().setFechaFinHospitalizacion(bean.getObjeto().getAucEgresoId().getFechaEgreso());
            getAucHospitalizacionRemoto().actualizar(bean.getObjeto());
            getAucHospitalizacionRemoto().actualizaFechaFinHospitalizacion(bean.getObjeto());
            bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            getAucHospitalizacionRemoto().actualizaDiasHospitalizacion(bean.getObjeto());
            registrarHistorico(bean.getObjeto().getAucEgresoId().toString(), bean);
            bean.addMensaje("Se guardo el egreso exitosamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void completarEgresoMaestro(AucHospitalizacionBean bean) {
        Maestro destino = bean.getHashDestinoEgreso().get(bean.getObjeto().getAucEgresoId().getMaeDestinoEgresoId());
        if (destino != null) {
            bean.getObjeto().getAucEgresoId().setMaeDestinoEgresoCodigo(destino.getValor());
            bean.getObjeto().getAucEgresoId().setMaeDestinoEgresoValor(destino.getNombre());
        }else{
            bean.getObjeto().getAucEgresoId().setMaeDestinoEgresoCodigo(null);
            bean.getObjeto().getAucEgresoId().setMaeDestinoEgresoValor(null);
        }
        Maestro conductaEgreso = bean.getHashConductaEgreso().get(bean.getObjeto().getAucEgresoId().getMaeConductaEgresoId());
        if (conductaEgreso != null) {
            bean.getObjeto().getAucEgresoId().setMaeConductaEgresoCodigo(conductaEgreso.getValor());
            bean.getObjeto().getAucEgresoId().setMaeConductaEgresoValor(conductaEgreso.getNombre());
        }else{
            bean.getObjeto().getAucEgresoId().setMaeConductaEgresoCodigo(null);
            bean.getObjeto().getAucEgresoId().setMaeConductaEgresoValor(null);
        }
    }

    public void procesarEgreso(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto().getAucEgresoId());
            completarEgresoMaestro(bean);
            if (bean.getObjeto().getEstado() == AucHospitalizacion.ESTADO_AFILIADO_EGRESADO) {
                bean.getObjeto().getAucEgresoId().setFuenteOrigen(1);
            } else {
                bean.getObjeto().getAucEgresoId().setFuenteOrigen(0);
            }
            int idEgreso = getAucEgresoRemoto().insertar(bean.getObjeto().getAucEgresoId());
            bean.getObjeto().getAucEgresoId().setId(idEgreso);
            for (AucDiagnostico diagnostico : bean.getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                if (diagnostico.getId() == null) {
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setAucEgresosId(bean.getObjeto().getAucEgresoId());
                    int idDiagnostico = getAucDiagnosticoRemoto().insertar(diagnostico);
                    diagnostico.setId(idDiagnostico);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AucHospitalizacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardarGestionRiesgo(AucHospitalizacionBean bean) {
        try {

            List<PeAfiliadoDiagnostico> listaDiagnosticos = new ArrayList<>();
            bean.getObjeto().getAucIngresoId().getAucDiagnosticosList().stream().filter(diagnostico -> (true)).forEachOrdered(diagnostico -> {
                PeAfiliadoDiagnostico iten = new PeAfiliadoDiagnostico();
                iten.setMaDiagnosticosId(String.valueOf(diagnostico.getMaDiagnosticoId()));
                iten.setMaDiagnosticosCodigo(diagnostico.getMaDiagnosticoCodigo());
                iten.setMaDiagnosticosValor(diagnostico.getMaDiagnosticoValor());
                bean.auditoriaGuardar(iten);
                if (diagnostico.isPrincipal()) {
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
                //Se validan las acciones a realizar segun el valor en el atributo
                //registroAfiliadosolicitud. 1 --> Sugerido | 2 --> Automatico
                if (programa.getRegistroAfiliadoHospitalizacion() == PePrograma.REGISTRO_AFILIADO_SUGERIDO) {
                    obj.setAsegAfiliado(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId());
                    obj.setAucHospitalizaciones(bean.getObjeto());
                    obj.setOrigen(0);
                    obj.setEstado(1);
                    bean.auditoriaGuardar(obj);
                    int idAfiliadoSugerido = getPeAfiliadoSugeridoRemoto().insertar(obj);
                    obj.setId(idAfiliadoSugerido);
                    procesarSugeridosAdjuntos(obj.getListaAdjunto(), obj, bean);
                    registrarHistorico(obj.toString(), bean);
                } else if (programa.getRegistroAfiliadoHospitalizacion() == PePrograma.REGISTRO_AFILIADO_AUTOMATICO) {
                    PeAfiliadosPrograma ent = new PeAfiliadosPrograma();
                    if (bean.getObjeto().getAucAfiliadoId() != null) {
                        ent.setAsegAfiliado(new AsegAfiliado(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId()));
                    }
                    ent.setActivo(true);
                    ent.setFechaHoraCrea(new Date());
                    ent.setFechaInicioPrograma(new Date());
                    //ent.setIdSolicitudOrigen(auAnexo3.getId());
                    List<Maestro> causas_activo = getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.PE_CAUSA_ESTADO_ACTIVO_AUTOMATICO);
                    if (!causas_activo.isEmpty()) {
                        ent.setMaeCausaActivoCodigo(causas_activo.get(0).getTipo());
                        ent.setMaeCausaActivoId(causas_activo.get(0).getId());
                        ent.setMaeCausaActivoValor(causas_activo.get(0).getNombre());
                    } else {
                        continue;
                    }

                    PeAfiliadosPrograma existe_afiliado_programa = getPeAfiliadosProgramaRemoto().consultarAfiliadoPorPrograma(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId(), programa.getId());
                    if (existe_afiliado_programa.getId() != null) {
                        continue;
                    }

                    ent.setPePrograma(new PePrograma(programa.getId()));
                    ent.setCntPrestadorSede(new CntPrestadorSede(CntPrestadorSede.ID_SEDE_SIN_ESPECIFICAR));
                    ent.setFuenteOrigen(PeAfiliadosPrograma.ORIGEN_HOSPITALIZACION);
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
                }

            }
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            bean.addMensaje("El paciente fue sugerido de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void procesarSugeridosAdjuntos(List<PeSugeridoAdjunto> adjuntosIn, PeAfiliadoSugerido sugerido, AucHospitalizacionBean bean) {
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

    private void listarTuPersonaContactos(AucHospitalizacionBean bean) {
        try {
            /*if ( bean.getObjeto().getAucAfiliadoId().getId() != null ) {
                bean.setListaTuPersonaContacto(getAucAfiliadoContactoRemoto().consultarLista(bean.getObjeto().getAucAfiliadoId().getId()));
            }*/
            if (bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId() != null) {
                bean.setListaTuPersonaContacto(getAucAfiliadoContactoRemoto().consultarListaContactosPorAsegAfiliado(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId()));
            }
        } catch (Exception ex) {
            bean.setListaTuPersonaContacto(new ArrayList<>());
        }
    }

    @Override
    public void listarAfiliado(AucHospitalizacionBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(1)));
            bean.setListaAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarSeguimientos(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void listarGestorasRegionales(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionGestorasRegionalesList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacionGestorasRegionales(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void listarDiagnosticoEstancia(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(getAucDiagnosticoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    
    @Override
    public void listarDiagnosticosIngreso(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().getAucIngresoId().setAucDiagnosticosList(getAucDiagnosticoRemoto().consultarPorIdIngreso(bean.getObjeto().getAucIngresoId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarDiagnosticosEgreso(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().getAucEgresoId().setAucDiagnosticosList(getAucDiagnosticoRemoto().consultarPorIdEgreso(bean.getObjeto().getAucEgresoId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarEstancias(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarJustifiacionEstanciasProlongada(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarPalogias(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarInoportunidades(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarAdversos(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarObjeciones(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarServicios(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarGestionRiesgo(AucHospitalizacionBean bean) {
        try {
            bean.getObjeto().setAucProgramaEspecialList(getPeProgramaRemoto().programasMatriculadosSugeridos(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarTelefonosAfiliadoAseguradoYhospitalizacion(AucHospitalizacionBean bean) {
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            if (afiliado != null) {
                bean.setListaContactosAseg(afiliado.getListaAsegAfiliadoContacto());
            }
            //List<AucAfiliadoContacto> listaTelefonosAucAfiliadoContacto = getAucAfiliadoContactoRemoto().consultarLista(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            List<AucAfiliadoContacto> listaTelefonosAucAfiliadoContacto = getAucAfiliadoContactoRemoto().consultarListaContactosPorAsegAfiliado(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            if (listaTelefonosAucAfiliadoContacto != null & !listaTelefonosAucAfiliadoContacto.isEmpty()) {
                bean.setListaTuPersonaContacto(listaTelefonosAucAfiliadoContacto);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public AucHospitalizacion consultarhospitalizacionId(int idHospitalizaion, AucHospitalizacionBean bean) {
        AucHospitalizacion hopitalizacion = null;
        try {
            hopitalizacion = getAucHospitalizacionRemoto().consultar(idHospitalizaion);
            if (hopitalizacion != null) {
                hopitalizacion.setAucHospitalizacionDiagnosticoEstanciaTratanteList(getAucDiagnosticoRemoto().consultarPorIdHospitalizacion(hopitalizacion.getId()));
                hopitalizacion.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(hopitalizacion.getAucAfiliadoId().getId()));
                hopitalizacion.setAucIngresoId(getAucIngresoRemoto().consultar(hopitalizacion.getAucIngresoId().getId()));
                hopitalizacion.setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(hopitalizacion.getId()));
                
                bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_SEGUIMIENTO));
                bean.setHashTipoSeguimiento(AuConstantes.obtenerHashMaestro(bean.getListaTipoSeguimiento()));

                bean.setListaServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_SERVICIO_ESTANCIA));
                bean.setHashServicio(AuConstantes.obtenerHashMaestro(bean.getListaServicio()));

                bean.setListaCausaEstancia(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CAUSA_ESTANCIA));
                bean.setHashCausaEstancia(AuConstantes.obtenerHashMaestro(bean.getListaCausaEstancia()));
                
                bean.setListaPropuestaIntervencion(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_PROPUESTA_INTERVENCION));
                bean.setHashPropuestaIntervencion(AuConstantes.obtenerHashMaestro(bean.getListaPropuestaIntervencion()));

                bean.setListaPatologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_PATOLOGIA_EMERGENTE));
                bean.setHashPatologia(AuConstantes.obtenerHashMaestro(bean.getListaPatologia()));

                bean.setListaTipoInoportunidad(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_INOPORTUNIDAD));
                bean.setHashTipoInoportunidad(AuConstantes.obtenerHashMaestro(bean.getListaTipoInoportunidad()));

                bean.setListaCategoriaEvento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CATEGORIA_EVENTOS_ADVERSOS));
                bean.setHashCategoriaEvento(AuConstantes.obtenerHashMaestro(bean.getListaCategoriaEvento()));

                bean.setListaConclusionEvento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_CONCLUSION_EVENTOS_ADVERSOS));
                bean.setHashConclusionEveno(AuConstantes.obtenerHashMaestro(bean.getListaConclusionEvento()));
                
                bean.setListaMotivoInoportunidad(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_MOTIVO_FIN_INOPORTUNIDAD));
                bean.setHashMotivoInoportunidad(AuConstantes.obtenerHashMaestro(bean.getListaMotivoInoportunidad()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la hospitalización");
        }
        return hopitalizacion;
    }

    @Override
    public void consultarLimpiarMaestroDestion(AucHospitalizacionBean bean) {
        bean.setListaDestinoEgreso(new ArrayList<>());
        bean.setHashDestinoEgreso(new HashMap());
    }

    @Override
    public void consultarMaestroDestino(AucHospitalizacionBean bean) {
        try {
            bean.setListaDestinoEgreso(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.AUC_DESTINO_EGRESO_EGRESO));
            bean.setHashDestinoEgreso(AuConstantes.obtenerHashMaestro(bean.getListaDestinoEgreso()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar el maestro destino");
        }
    }

    @Override
    public void validarSugeridoParaBorrar(AucHospitalizacionBean bean) {

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
    public void guardarAfiliadoContacto(AucHospitalizacionBean bean) {

        try {
            bean.auditoriaGuardar(bean.getAucAfiliadoContacto());
            int id = getAucAfiliadoContactoRemoto().insertar(bean.getAucAfiliadoContacto());
            bean.getAucAfiliadoContacto().setId(id);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarSeguimientos(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoSeguimiento());
            int idSeguimiento = getAucHospitalizacionSeguimientoRemoto().insertar(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimiento().setId(idSeguimiento);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            bean.getObjeto().setFechaUltimaNota(bean.getObjetoSeguimiento().getFechaHoraCrea());
            getAucHospitalizacionRemoto().actualizarFechaUltimaNota(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoSeguimiento().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el seguimiento con id (" + bean.getObjetoSeguimiento().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void guardarGestionRegionales(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimiento().setBorrado(Boolean.FALSE); 
            Maestro tipoSeguimiento = getMaestroRemoto().consultarPorValorTipo("2", "342");
            if(tipoSeguimiento != null){
                bean.getObjetoSeguimiento().setMaeTipoSeguimientoId(tipoSeguimiento.getId());
                bean.getObjetoSeguimiento().setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
                bean.getObjetoSeguimiento().setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
                bean.getObjetoSeguimiento().setMaeTipoSeguimientoTipo(tipoSeguimiento.getTipo());
            }
            int idSeguimiento = getAucHospitalizacionSeguimientoRemoto().insertar(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimiento().setId(idSeguimiento);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoSeguimiento().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el seguimiento con id (" + bean.getObjetoSeguimiento().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void guardarSeguimientosAnulacion(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoSeguimiento());
            int idSeguimiento = getAucHospitalizacionSeguimientoRemoto().insertar(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimiento().setId(idSeguimiento);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_ANULADO);
            bean.getObjeto().setEstado(AucHospitalizacion.ESTADO_AFILIADO_ANULADO);
            getAucHospitalizacionRemoto().actualizarEstadoAnulacion(bean.getObjeto());
            // para calcular los dias hospitalizacion
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            hospitalizacion.setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjeto().getId()));
            hospitalizacion.setFechaFinHospitalizacion(new Date());
            hospitalizacion.setDiasHospitalizacion(hospitalizacion.getCalcularDiasHospitalizacionFin());
            getAucHospitalizacionRemoto().actualizaReveirHospitalizacion(hospitalizacion);
            AucHospitalizacionEstancia ultimaEstancia = hospitalizacion.getAucHospitalizacionEstanciaList().get(hospitalizacion.getAucHospitalizacionEstanciaList().size() - 1);
            ultimaEstancia.setFechaEgreso(new Date());
            ultimaEstancia.setDias(ultimaEstancia.getCalcularDiasEstancia());
            getAucHospitalizacionEstanciaRemoto().actualizar(ultimaEstancia);
            // inserta a la auditoria
            registroHospitalizacionEstados(bean, (short) AucHospitalizacion.ESTADO_AFILIADO_ANULADO, (short) AucHospitalizacion.ESTADO_AUDITORIA_ANULADO, bean.getObjetoSeguimiento().getDescripcion());
            registrarHistorico(bean.getObjeto().toString(), bean);
            bean.addMensaje("Se actualizó la hospitalización de manera exitosa \n ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarEstancias(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoEstancia());
            int rango = getAucHospitalizacionEstanciaRemoto().validarRangoFechasParaInsertar(bean.getObjetoEstancia().getAucHospitalizacionId().getId(), bean.getObjetoEstancia().getFechaIngreso());
            if (rango == 0) {
                int idEstancia = getAucHospitalizacionEstanciaRemoto().insertar(bean.getObjetoEstancia());
                bean.getObjetoEstancia().setId(idEstancia);
                bean.auditoriaModificar(bean.getObjeto());
                bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
                getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
                if (!bean.isError()) {
                    if (bean.validarEstadoEgresado(bean.getObjeto().getEstado())) {
                        bean.getObjeto().setFechaFinHospitalizacion(bean.getObjetoEstancia().getFechaEgreso());
                        getAucHospitalizacionRemoto().actualizaFechaFinHospitalizacion(bean.getObjeto());
                        if (bean.getObjeto().getAucEgresoId() != null && bean.getObjeto().getAucEgresoId().getId() != null) {
                            bean.getObjeto().getAucEgresoId().setFechaEgreso(bean.getObjetoEstancia().getFechaEgreso());
                            getAucEgresoRemoto().actualizar(bean.getObjeto().getAucEgresoId());
                        }
                    }
                    bean.getObjeto().setDiasHospitalizacion(bean.getObjeto().getCalcularDiasHospitalizacionFin());
                    getAucHospitalizacionRemoto().actualizaDiasHospitalizacion(bean.getObjeto());
                    registrarHistorico(bean.getObjetoEstancia().toString(), bean);
                    registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                    bean.addMensaje("Se creo la estancia con id (" + bean.getObjetoEstancia().getId() + ") de manera exitosa \n ");
                }
            } else {
                bean.addError("No puede agregar un intancia la fechas egreso no cuadran");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void guardarDiagnosticoEspecialidad(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getAucHospitalizacionRemoto().actualizarDiagnosticoEspecialidad(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            registrarHistorico(bean.getObjeto().toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void guardarDiagnosticoEstancia(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            if (!bean.getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().isEmpty()) {
                for (AucDiagnostico diagnosticoIngreso : bean.getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList()) {
                    if (diagnosticoIngreso.getId() == null) {
                        diagnosticoIngreso.setAucHospitalizacionId(bean.getObjeto());
                        bean.auditoriaGuardar(diagnosticoIngreso);
                        int idDiagnostico = getAucDiagnosticoRemoto().insertar(diagnosticoIngreso);
                        diagnosticoIngreso.setId(idDiagnostico);
                    } 
                }
            }
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            registrarHistorico(bean.getObjeto().toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void guardarJustificacionEstanciaProlongada(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoJustificacionEstanciasProlongada());
            int idEstancia = getAucJustificacionEstanciasProlongadaRemoto().insertar(bean.getObjetoJustificacionEstanciasProlongada());
            bean.getObjetoJustificacionEstanciasProlongada().setId(idEstancia);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
            registrarHistorico(bean.getObjetoJustificacionEstanciasProlongada().toString(), bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void actualizarEstanciasDescripcion(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoEstancia());
            //getAucHospitalizacionEstanciaRemoto().actualizarEsctanciaDescripcion(bean.getObjetoEstancia());
            bean.addMensaje("Se creo actualizó la estancia con exito");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarPatologias(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoPatologia());
            int idPatologia = getAucHospitalizacionPatologiaRemoto().insertar(bean.getObjetoPatologia());
            bean.getObjetoPatologia().setId(idPatologia);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoPatologia().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo la patologia con id (" + bean.getObjetoPatologia().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarInoportunidad(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoInoportunidad());
            int idInoportunidad = getAucHospitalizacionInoportunidadRemoto().insertar(bean.getObjetoInoportunidad());
            bean.getObjetoInoportunidad().setId(idInoportunidad);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoInoportunidad().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el inoportunidad con id (" + bean.getObjetoInoportunidad().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarAdversos(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoAdverso());
            int idAdverso = getAucHospitalizacionAdversoRemoto().insertar(bean.getObjetoAdverso());
            bean.getObjetoAdverso().setId(idAdverso);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoAdverso().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el adverso con id (" + bean.getObjetoAdverso().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarObjeciones(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoObjecion());
            int idObjecion = getAucHospitalizacionObjecionRemoto().insertar(bean.getObjetoObjecion());
            bean.getObjetoObjecion().setId(idObjecion);
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoObjecion().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el objecion con id (" + bean.getObjetoObjecion().getId() + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarServicios(AucHospitalizacionBean bean) {
        try {

            for (AucHospitalizacionServicio memoria : bean.getListaMemoriaTecnologia()) {
                bean.auditoriaGuardar(memoria);
                memoria.setFechaPrestacion(bean.getObjetoServicio().getFechaPrestacion());
                memoria.setObservacion(bean.getObjetoServicio().getObservacion());
                int idServicio = getAucHospitalizacionServicioRemoto().insertar(memoria);
                memoria.setId(idServicio);
            }
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstadoAuditoria(AucHospitalizacion.ESTADO_AUDITORIA_EN_GESTION);
            getAucHospitalizacionRemoto().actualizarEstadoAauditoria(bean.getObjeto());
            if (!bean.isError()) {
                registrarHistorico(bean.getObjetoServicio().toString(), bean);
                registroHospitalizacionEstados(bean, Short.valueOf(String.valueOf(bean.getObjeto().getEstado())), Short.valueOf(String.valueOf(bean.getObjeto().getEstadoAuditoria())), "");
                bean.addMensaje("Se creo el servicio de manera exitosa \n ");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarDiagnostico(AucHospitalizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoDiagnostico());
            int idDiagnostico = getAucDiagnosticoRemoto().insertar(bean.getObjetoDiagnostico());
            bean.getObjetoDiagnostico().setId(idDiagnostico);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void completarAfiliado(AucHospitalizacionBean bean) {
        try {
            int idAfiliado = bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId();
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(idAfiliado);

            if (afiliado != null) {
                bean.getObjeto().getAucAfiliadoId().setMaeEstadoAfiliadoId(afiliado.getMaeEstadoAfiliacion());
                bean.getObjeto().getAucAfiliadoId().setMaeEstadoAfiliadoCodigo(afiliado.getMaeEstadoAfiliacionCodigo());
                bean.getObjeto().getAucAfiliadoId().setMaeEstadoAfiliadoValor(afiliado.getMaeEstadoAfiliacionValor());
                bean.getObjeto().getAucAfiliadoId().setMaeTipoDocumentoId(afiliado.getMaeTipoDocumento());
                bean.getObjeto().getAucAfiliadoId().setMaeTipoDocumentoCodigo(afiliado.getMaeTipoDocumentoCodigo());
                bean.getObjeto().getAucAfiliadoId().setMaeTipoDocumentoValor(afiliado.getMaeTipoDocumentoValor());
                bean.getObjeto().getAucAfiliadoId().setNumeroDocumento(afiliado.getNumeroDocumento());
                bean.getObjeto().getAucAfiliadoId().setPrimerApellido(afiliado.getPrimerApellido());
                bean.getObjeto().getAucAfiliadoId().setSegundoApellido(afiliado.getSegundoApellido());
                bean.getObjeto().getAucAfiliadoId().setPrimerNombre(afiliado.getPrimerNombre());
                bean.getObjeto().getAucAfiliadoId().setSegundoNombre(afiliado.getSegundoNombre());
                bean.getObjeto().getAucAfiliadoId().setFechaNacimiento(afiliado.getFechaNacimiento());
                bean.getObjeto().getAucAfiliadoId().setMaeGeneroId(afiliado.getMaeGeneroId());
                bean.getObjeto().getAucAfiliadoId().setMaeGeneroCodigo(afiliado.getMaeGeneroCodigo());
                bean.getObjeto().getAucAfiliadoId().setMaeGeneroValor(afiliado.getMaeGeneroValor());
                bean.getObjeto().getAucAfiliadoId().setCorreoElectronico(afiliado.getEmail());
                bean.getObjeto().getAucAfiliadoId().setDireccionResidencia(afiliado.getDireccionResidencia());
                bean.getObjeto().getAucAfiliadoId().setUbicacionAfiliacionId(afiliado.getAfiliacionUbicacion().getId());
                bean.getObjeto().getAucAfiliadoId().setContratoAfiliacion(afiliado.getIdAfiliado());
                bean.getObjeto().getAucAfiliadoId().setMaeRegimenId(afiliado.getMaeRegimenId());
                bean.getObjeto().getAucAfiliadoId().setMaeRegimenCodigo(afiliado.getMaeRegimenCodigo());
                bean.getObjeto().getAucAfiliadoId().setMaeRegimenValor(afiliado.getMaeRegimenValor());

                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaNacimiento = AuConstantes.formato5.format(afiliado.getFechaNacimiento());
                Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
                if (periodo.getYears() >= 80) {
                    bean.getObjeto().getAucIngresoId().setIndiceCharlson(4);
                }
            }

        } catch (Exception e) {
            bean.addError("Hubo un error al completar información del afiliado, favor comunicarse con el adminitrador");
        }
    }

    @Override
    public void calcularRegingreso(AucHospitalizacionBean bean) {
        try {
            if (bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId() != null) {
                AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultarUltimaHospitalizacionAfiliado(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId(), bean.getObjeto().getId());
                if (hospitalizacion != null) {
                    int milisecondsByDay = 86400000;
                    Date fechaIngreso = bean.getObjeto().getAucIngresoId().getFechaIngreso();
                    Date fechaICreaDiagnostico = hospitalizacion.getAucEgresoId().getFechaEgreso();
                    if (fechaICreaDiagnostico != null) {
                        //int dias = (int) (( fechaICreaDiagnostico.getTime() - fechaIngreso.getTime()) / milisecondsByDay);
                        SimpleDateFormat formatterfechaIngreso = new SimpleDateFormat("yyyy-MM-dd");
                        String formatIngreso = formatterfechaIngreso.format(fechaIngreso);

                        //Date fechaEgreso = getObjeto().getAucEgresoId().getFechaEgreso();
                        SimpleDateFormat formatterICreaDiagnostico = new SimpleDateFormat("yyyy-MM-dd");
                        String format = formatterICreaDiagnostico.format(fechaICreaDiagnostico);

                        Date fechaConvertida = formatterfechaIngreso.parse(formatIngreso);
                        Date dataFormateada = formatterICreaDiagnostico.parse(format);
                        int dias = (int) (((dataFormateada.getTime() - fechaConvertida.getTime()) / milisecondsByDay) * -1);
                        if (dias <= 15) {
                            boolean validar = false;
                            for (AucDiagnostico diagnosticoNuevo : bean.getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                                for (AucDiagnostico diagnosticoAnterior : hospitalizacion.getAucIngresoId().getAucDiagnosticosList()) {
                                    if (diagnosticoAnterior.getMaDiagnosticoId() == diagnosticoNuevo.getMaDiagnosticoId() && diagnosticoAnterior.isPrincipal() && diagnosticoNuevo.isPrincipal()) {
                                        bean.getObjeto().getAucIngresoId().setIngreso(Short.parseShort("1"));
                                        validar = true;
                                    }
                                }
                            }
                            if (!validar) {
                                bean.getObjeto().getAucIngresoId().setIngreso(Short.parseShort("0"));
                            }
                        } else {
                            bean.getObjeto().getAucIngresoId().setIngreso(Short.parseShort("0"));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
        }
    }

    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, AucHospitalizacionBean bean) {
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
    public boolean validarHospitalizacionActivas(AsegAfiliado afiliado, AucHospitalizacionBean bean) {
        try {
            int cantidad = getAucHospitalizacionRemoto().consultarHozpitalizacionActivas(afiliado.getId(), bean.getObjeto().getCntPrestadorSedeId().getId());
            if (cantidad == 0) {
                return true;
            } else {
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra con hospitalizaciones activas");
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    public boolean validarHospitalizacionActivas(AsegAfiliado afiliado, AucHospitalizacionBean bean, Integer idPrestadorSedes) {
        try {
            int cantidad = getAucHospitalizacionRemoto().consultarHozpitalizacionActivas(afiliado.getId(), idPrestadorSedes);
            if (cantidad == 0) {
                return true;
            } else {
                bean.addError("No se puede revertir la hospitalización tiene una activa en la IPS");
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    public boolean validarDiasParaRevertirHospitalizacion(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionEstado estado = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYUltimoEstadoEgreso(bean.getObjeto().getId());
            if (estado != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFechaHoraCreaString = sdf.format(estado.getFechaHoraCrea());
                Date fechaConvertda = sdf.parse(dateFechaHoraCreaString);
                String dateFechaActual = sdf.format(new Date());
                Date fechaActualConvertda = sdf.parse(dateFechaActual);
                int rangoDiasHabiles = getCalendarioRemoto().consultarHabilies(fechaConvertda, fechaActualConvertda);
                if (rangoDiasHabiles > 5) {
                    bean.addError("No se puede revertir la hospitalización supero los dias permitidos");
                    return false;
                } else {
                    return true;
                }
            } else {
                bean.addError("No se puede revertir la hospitalización supero los dias permitidos");
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    @Override
    public boolean validarHospitalizacionIpsSoloDosActivas(AsegAfiliado afiliado, AucHospitalizacionBean bean) {
        try {
            int cantidad = getAucHospitalizacionRemoto().consultarHospitalizacionIpsSoloDosActivas(afiliado.getId());
            if (cantidad < 2) {
                return true;
            } else {
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra registrado en 2 ips activas");
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    @Override
    public boolean validarCodigoEvento(AucHospitalizacionBean bean) {
        try {
            List<AucHospitalizacion> cantidad = getAucHospitalizacionRemoto().consultarCodigoEventoHosítalizacion(bean.getObjeto().getCodigoEvento());
            if (cantidad.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return true;
        }
    }

    @Override
    public void listarIps(AucHospitalizacionBean bean) {
        try {
            if(bean.getParamConsulta(2).getFiltros() == null){
                bean.getParamConsulta(2).setFiltros(new HashMap());
            }
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(2).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            
            bean.getParamConsulta(2).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(2).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(2).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(2)));
            bean.setListaIps(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarEstadosDeSubCategoria(AucHospitalizacionBean bean) {
        try {
            bean.setListaSubcategoriaEvento(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AUC_SUBCATEGORIA_EVENTOS_ADVERSOS, bean.getObjetoAdverso().getMaeCategoriaEventoId()));
            bean.setHashSubcategoriaEvento(AuConstantes.obtenerHashMaestro(bean.getListaSubcategoriaEvento()));
        } catch (Exception e) {
            bean.setListaSubcategoriaEvento(new ArrayList<>());
            bean.setHashCategoriaEvento(new HashMap<>());
        }
    }

    @Override
    public void consultarFechaIngresoSeaMenor(AucHospitalizacionBean bean) {
        Integer permiteFecha = null;
        try {
            permiteFecha = getAucHospitalizacionRemoto().consultarFechaIngresoSeaMenor(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId(), bean.getObjeto().getAucIngresoId().getFechaIngreso());
            if (permiteFecha != null) {
                bean.addError("La fecha de ingreso es menor a las fechas de hospitalizaciones de egresos");
            }
        } catch (Exception e) {
            bean.addError("Error al momento de validar fecha de ingreso contra fechas de egresos de todas las hospitalizaciones del paciente");
        }
    }

    @Override
    public void consultarHospitalizacionExceptoAnuladas(AucHospitalizacionBean bean) {
        List<AucHospitalizacion> hospitalizacion = new ArrayList<>();
        try {
            SimpleDateFormat formatterFechaIngreso = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatFechaEgreso = formatterFechaIngreso.format(bean.getObjeto().getAucIngresoId().getFechaIngreso());

            Date fechaIngreso = formatterFechaIngreso.parse(formatFechaEgreso);
            if (bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId() != null) {
                hospitalizacion = getAucHospitalizacionRemoto().consultarHospitalizacionExceptoAnuladas(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId(), bean.getObjeto().getId(), bean.getObjeto().getCntPrestadorSedeId().getId());
                if (!hospitalizacion.isEmpty()) {
                    for (AucHospitalizacion obj : hospitalizacion) {
                        Date fechaEgresoHospitalizacion = obj.getAucEgresoId().getFechaEgreso();
                        if (fechaEgresoHospitalizacion != null) {
                            if (fechaIngreso.before(fechaEgresoHospitalizacion)) {
                                bean.addError("La fecha de ingreso es menor a las fechas de hospitalizaciones de egresos");
                                break;
                            }
                        }
                    }
                }
            } else {
                bean.addError("Se necesita el afiliado");
            }

        } catch (Exception e) {
            bean.addError("Error al momento de validar fecha de ingreso contra fechas de egresos de todas las hospitalizaciones del paciente");
        }
    }

    @Override
    public void consultarHospitalizacionExceptoAnuladasEgreso(AucHospitalizacionBean bean) {
        List<AucHospitalizacion> hospitalizacion = null;
        try {
            SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatFechaEgreso = formatterFechaEgreso.format(bean.getObjeto().getAucEgresoId().getFechaEgreso());

            Date fechaEgreso = formatterFechaEgreso.parse(formatFechaEgreso);
            hospitalizacion = getAucHospitalizacionRemoto().consultarHospitalizacionExceptoAnuladas(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId(), bean.getObjeto().getId(), bean.getObjeto().getCntPrestadorSedeId().getId());
            if (!hospitalizacion.isEmpty()) {
                for (AucHospitalizacion obj : hospitalizacion) {
                    Date fechaIngresoHospitalizacion = obj.getAucIngresoId().getFechaIngreso();
                    if (fechaIngresoHospitalizacion.after(fechaEgreso)) {
                        bean.addError("La fecha de Egreso es menor a las fechas de hospitalizaciones de ingreso");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            bean.addError("Error al momento de validar fecha de ingreso contra fechas de egresos de todas las hospitalizaciones del paciente");
        }
    }

    @Override
    public void consultarHospitalizacionInoportunidad(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionInoportunidad objInoporutinidad = bean.getObjetoInoportunidad();
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            if (hospitalizacion != null) {
                Date fechaIngresoPaciente = hospitalizacion.getAucIngresoId().getFechaIngreso();
                Date fechaEgresoPaciente = hospitalizacion.getAucEgresoId().getFechaEgreso();
                if (objInoporutinidad.getFechaInicioInoportunidad() != null && !objInoporutinidad.getFechaInicioInoportunidad().equals("")) {
                    SimpleDateFormat formatterFechaInicioInoportunidad = new SimpleDateFormat("yyyy-MM-dd");
                    String formatFechaInicioInoportunidad = formatterFechaInicioInoportunidad.format(objInoporutinidad.getFechaInicioInoportunidad());

                    Date fechaInicioInoportunidad = formatterFechaInicioInoportunidad.parse(formatFechaInicioInoportunidad);
                    if (fechaInicioInoportunidad.before(fechaIngresoPaciente)) {
                        bean.addError("la fecha de inicio de la inoportunidad es menor a la fecha de ingreso del paciente");
                    }
                }

                if (objInoporutinidad.getFechaFinInoportunidad() != null && !objInoporutinidad.getFechaFinInoportunidad().equals("")) {
                    SimpleDateFormat formatterFechaFinInoportunidad = new SimpleDateFormat("yyyy-MM-dd");
                    String formatFechaFinInoportunidad = formatterFechaFinInoportunidad.format(objInoporutinidad.getFechaFinInoportunidad());
                    Date fechaFinInoportunidad = formatterFechaFinInoportunidad.parse(formatFechaFinInoportunidad);
                    if (fechaEgresoPaciente != null) {
                        if (fechaFinInoportunidad.after(fechaEgresoPaciente)) {
                            bean.addError("la fecha de fin de la inoportunidad es mayor a la fecha de egreso del paciente");
                        }
                    }
                }
            } else {
                bean.addError("la Hospitalización no se pudo consultar");
            }
        } catch (Exception e) {
            bean.addError("Error al momento de validar fecha de ingreso contra fechas de egresos de todas las hospitalizaciones del paciente");
        }
    }

    @Override
    public void consultarHospitalizacionServicio(AucHospitalizacionBean bean) {
        try {
            SimpleDateFormat formatterUltimaEstanciaFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");

            AucHospitalizacionServicio objServicio = bean.getObjetoServicio();
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            if (hospitalizacion != null) {
                Date fechaEgresoPaciente = hospitalizacion.getAucEgresoId().getFechaEgreso();
                if (fechaEgresoPaciente != null) {
                    String formatEgresoPaciente = formatterUltimaEstanciaFechaEgreso.format(fechaEgresoPaciente);
                    LocalDate convertidaFechaIngreso = LocalDate.parse(formatEgresoPaciente);
                    if (objServicio.getFechaPrestacion() != null && !objServicio.getFechaPrestacion().equals("")) {
                        String formatFechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.format(objServicio.getFechaPrestacion());
                        //Date fechaFinInoportunidad = formatterFechaFinInoportunidad.parse(formatFechaFinInoportunidad);
                        LocalDate fechaFinInoportunidad = LocalDate.parse(formatFechaFinInoportunidad);
                        if (fechaFinInoportunidad.isAfter(convertidaFechaIngreso)) {
                            bean.addError("la fecha de prestación del servicio es mayor a la fecha de egreso del paciente");
                        }
                    }
                }
            } else {
                bean.addError("la Hospitalización no se pudo consultar");
            }
        } catch (Exception e) {
            bean.addError("Error al momento de validar fecha de ingreso contra fechas de egresos de todas las hospitalizaciones del paciente");
        }
    }

    @Override
    public void consultarHospitalizacionAdverso(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionAdverso objAdverso = bean.getObjetoAdverso();
            AucHospitalizacion hospitalizacion = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            if (hospitalizacion != null) {
                Date fechaIngresoPaciente = hospitalizacion.getAucIngresoId().getFechaIngreso();
                if (objAdverso.getFechaEvento() != null && !objAdverso.getFechaEvento().equals("")) {
                    SimpleDateFormat formatterFechaInicioInoportunidad = new SimpleDateFormat("yyyy-MM-dd");
                    String formatFechaInicioInoportunidad = formatterFechaInicioInoportunidad.format(objAdverso.getFechaEvento());

                    Date fechaInicioInoportunidad = formatterFechaInicioInoportunidad.parse(formatFechaInicioInoportunidad);
                    if (fechaInicioInoportunidad.before(fechaIngresoPaciente)) {
                        bean.addError("la fecha del evento es menor a la fecha de ingreso del paciente");
                    }
                }
            } else {
                bean.addError("la Hospitalización no se pudo consultar");
            }
        } catch (Exception e) {
            bean.addError("Error al momento de validar fecha del evento contra fechas de ingreso de la hospitalización");
        }
    }

    @Override
    public List<ReporteActaJustificacion> generarReporteActas(AucHospitalizacionBean bean) {
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm");
        List<ReporteActaJustificacion> listaReportes = new ArrayList<>();
        List<AucHospitalizacion> listResult = new ArrayList<>();
        try {
            listResult = getAucHospitalizacionRemoto().consultarHospitalizacionPorSedes(bean.getObjPrestadorSede().getId());
            int incremente = 1;
            for (AucHospitalizacion hospitalizacion : listResult) {
                if (hospitalizacion.getCalcularDiasHospitalizacionFin() >= 14) {
                    hospitalizacion.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(hospitalizacion.getAucAfiliadoId().getId()));
                    hospitalizacion.getAucIngresoId().setAucDiagnosticosList(getAucDiagnosticoRemoto().consultarPorIdIngreso(hospitalizacion.getAucIngresoId().getId()));
                    hospitalizacion.setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(hospitalizacion.getId()));
                    hospitalizacion.setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(hospitalizacion.getCntPrestadorSedeId().getId()));
                    hospitalizacion.getCalcularDiasHospitalizacionFin();
                    ReporteActaJustificacion reporte = new ReporteActaJustificacion();
                    reporte.setStrFecha(fecha.toString());
                    reporte.setStrHora(hora.toString().substring(0, 5));
                    reporte.setStrNombreIps(hospitalizacion.getCntPrestadorSedeId().getNombreSede());
                    reporte.setStrCodigoHabilitacion(hospitalizacion.getCntPrestadorSedeId().getCodigoHabilitacionSede());
                    reporte.setStrNumero(String.valueOf(incremente));
                    reporte.setStrTipoDoc(hospitalizacion.getAucAfiliadoId().getMaeTipoDocumentoCodigo());
                    reporte.setStrNumeroDoc(hospitalizacion.getAucAfiliadoId().getNumeroDocumento());
                    reporte.setStrNombreCompleto(hospitalizacion.getAucAfiliadoId().getNombreCompleto());
                    reporte.setStrDias(String.valueOf(hospitalizacion.getCalcularDiasHospitalizacionFin()));
                    for (AucDiagnostico diagnostico : hospitalizacion.getAucIngresoId().getAucDiagnosticosList()) {
                        if (diagnostico.isPrincipal()) {
                            reporte.setStrCodigoDx(diagnostico.getMaDiagnosticoCodigo());
                            reporte.setStrNombreDx(diagnostico.getMaDiagnosticoValor());
                        }
                    }
                    reporte.setStrAuditorAsignado(hospitalizacion.getGnUsuariosAuditorId().getNombre());
                    if (!hospitalizacion.getAucHospitalizacionJustificacionEstanciasProlongadaList().isEmpty()) {
                        reporte.setStrResumen(hospitalizacion.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getResumenClinico());
                        reporte.setStrCausa(hospitalizacion.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getMaeCausaEstanciaProlongadaValor());
                        reporte.setStrPropuesta(hospitalizacion.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getMaePropuestaIntervensionValor());
                        reporte.setStrAuditorAsistente(hospitalizacion.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getUsuarioCrea());
                    }
                    listaReportes.add(reporte);
                    incremente++;
                }
            }
        } catch (Exception e) {
            listaReportes = new ArrayList();
        }
        return listaReportes;
    }

    @Override
    public List<ReporteHospitalizacion> descargarHospitalizacion(AucHospitalizacionBean bean) {
        List<ReporteHospitalizacion> listData = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        ReporteHospitalizacion reporteHospitalizacion = new ReporteHospitalizacion();
        try {
            AucHospitalizacion obj = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            obj.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(obj.getAucAfiliadoId().getId()));
            obj.setAucIngresoId(getAucIngresoRemoto().consultar(obj.getAucIngresoId().getId()));
            obj.getAucIngresoId().setAucDiagnosticosList(getAucDiagnosticoRemoto().consultarPorIdIngreso(obj.getAucIngresoId().getId()));
            obj.setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(obj.getCntPrestadorSedeId().getId()));
            obj.setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(obj.getId()));
            obj.setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(obj.getId()));
            obj.setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(obj.getId()));
            if (obj.getAucEgresoId() != null && obj.getAucEgresoId().getId() != null) {
                obj.setAucEgresoId(getAucEgresoRemoto().consultar(obj.getAucEgresoId().getId()));
            }

            reporteHospitalizacion.setStrFechaDescarga(fecha.toString());
            reporteHospitalizacion.setStrNombres(obj.getAucAfiliadoId().getNombres());
            reporteHospitalizacion.setStrApellidos(obj.getAucAfiliadoId().getApellidos());
            reporteHospitalizacion.setStrTipoDocumento(obj.getAucAfiliadoId().getMaeTipoDocumentoValor());
            reporteHospitalizacion.setStrNumeroDocumento(obj.getAucAfiliadoId().getNumeroDocumento());
            reporteHospitalizacion.setStrEdad(bean.calcularEdad(obj.getAucAfiliadoId().getFechaNacimiento()));
            reporteHospitalizacion.setStrSede(obj.getCntPrestadorSedeId().getCntPrestador().getRazonSocial());
            reporteHospitalizacion.setStrFechaIngreso(obj.getAucIngresoId().getFechaIngreso().toString().substring(0, 10));
            reporteHospitalizacion.setStrAuditor(obj.getGnUsuariosAuditorId().getNombre());
            reporteHospitalizacion.setStrEstadoHospitalizacion(obj.getEstadoStr());
            reporteHospitalizacion.setListaDiagnosticosIngreso(obj.getAucIngresoId().getAucDiagnosticosList());
            if (!obj.getAucHospitalizacionSeguimientoList().isEmpty()) {
                reporteHospitalizacion.setStrSeguimientoDescripcion(obj.getAucHospitalizacionSeguimientoList().get(0).getDescripcion());
                reporteHospitalizacion.setStrSeguimientoUsuario(obj.getAucHospitalizacionSeguimientoList().get(0).getUsuarioCrea());
                reporteHospitalizacion.setStrSeguimientoFecha(obj.getAucHospitalizacionSeguimientoList().get(0).getFechaHoraCrea().toString());
            }
            reporteHospitalizacion.setListaEstancias(obj.getAucHospitalizacionEstanciaList());
            reporteHospitalizacion.setStrDiasEstancia(obj.getDiasHospitalizacion().toString());
            if (!obj.getAucHospitalizacionJustificacionEstanciasProlongadaList().isEmpty()) {
                reporteHospitalizacion.setStrInstanciaCausa(obj.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getMaeCausaEstanciaProlongadaValor());
                reporteHospitalizacion.setStrInstanciaPropuesta(obj.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getMaePropuestaIntervensionValor());
                reporteHospitalizacion.setStrInstanciaResumen(obj.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getResumenClinico());
                reporteHospitalizacion.setStrInstanciaFecha(obj.getAucHospitalizacionJustificacionEstanciasProlongadaList().get(0).getFechaHoraCrea().toString());
            }

            if (obj.getAucEgresoId() != null && obj.getAucEgresoId().getId() != null) {
                reporteHospitalizacion.setStrFechaEgreso(obj.getAucEgresoId().getFechaEgreso().toString().substring(0, 10));
                reporteHospitalizacion.setStrDestino(obj.getAucEgresoId().getMaeDestinoEgresoValor());
                reporteHospitalizacion.setListaDiagnosticosEgreso(obj.getAucEgresoId().getAucDiagnosticosList());
            } else {
                reporteHospitalizacion.setListaDiagnosticosEgreso(new ArrayList<>());
            }
            listData.add(reporteHospitalizacion);
        } catch (Exception e) {
            reporteHospitalizacion = new ReporteHospitalizacion();
        }
        return listData;
    }

    @Override
    public void consultarHistorialRescate(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacion obj = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            obj.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(obj.getAucAfiliadoId().getId()));
            bean.setListaHistorialRescate(getAuAnexo2RescateRemoto().rescatesAfiliadoHistorico(obj.getAucAfiliadoId().getAsegAfiliadoId().getId()));
        } catch (Exception e) {
            bean.setListaHistorialRescate(new ArrayList<>());
        }
    }

    @Override
    public void consultarHistorialHospitalizacionesEgreso(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacionHistorico historico = new AucHospitalizacionHistorico();
            historico.setAucHospitalizacionesId(bean.getObjeto());
            bean.setListaHistorialEgreso(getAucHospitalizacionHistoricoRemoto().consultarHistoricoIdHopitalizacionSoloEgresos(historico));
        } catch (Exception e) {
            bean.setListaHistorialRescate(new ArrayList<>());
        }
    }

    @Override
    public void consultarListaAplicaRescateHospitalizacion(AucHospitalizacionBean bean) {
        try {
            AucHospitalizacion obj = getAucHospitalizacionRemoto().consultar(bean.getObjeto().getId());
            obj.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(obj.getAucAfiliadoId().getId()));
            bean.setListaProgramasEspecialesRescate(getPeProgramaRemoto().programasAplicaRescateHospitalizacion(obj.getAucAfiliadoId().getAsegAfiliadoId().getId(), obj.getCntPrestadorSedeId().getId()));
            bean.setHashPePrograma(convertToHashPePrograma(bean.getListaProgramasEspecialesRescate()));
            bean.setListaTiposSugeridoAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_RESCATE_ADJUNTO_TIPO));
            bean.setHashTiposSugeridoAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposSugeridoAdjuntos()));
        } catch (Exception e) {
            bean.setListaHistorialRescate(new ArrayList<>());
        }
    }

    @Override
    public int consultarSiAplicaRescate(AucHospitalizacionBean bean) {
        int aplicaRescate = 0;
        try {
            AucHospitalizacion obj = getAucHospitalizacionRemoto().consultar(bean.getObjetoHospitalizacionAplicaRescate().getId());
            if (obj.getEstado() != AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO) {
                return aplicaRescate;
            }
            obj.setAucAfiliadoId(getAucAfiliadoRemoto().consultar(obj.getAucAfiliadoId().getId()));
            List<PePrograma> programaEspecialesRescate = (getPeProgramaRemoto().programasAplicaRescateHospitalizacion(obj.getAucAfiliadoId().getAsegAfiliadoId().getId(), obj.getCntPrestadorSedeId().getId()));
            if (!programaEspecialesRescate.isEmpty()) {
                Boolean listsRescateHospitalizacion = (getAuAnexo2RescateRemoto().rescatesPendientesHospitalizacion(obj.getId()));
                if (listsRescateHospitalizacion) {
                    aplicaRescate = 2;
                } else {
                    aplicaRescate = 1;
                }
                Boolean NoAptoRescateHospitalizacion = (getAucHospitalizacionRemoto().consultaNoAptoRescate(obj.getId()));
                if(NoAptoRescateHospitalizacion){
                    aplicaRescate = 3;
                }
            }

        } catch (Exception e) {
            bean.setListaHistorialRescate(new ArrayList<>());
        }
        return aplicaRescate;
    }
    
    @Override
    public void consultarMaestrosSeguimiento(AucHospitalizacionBean bean) {
        try {
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUC_TIPO_SEGUIMIENTO));
            bean.setHashTipoSeguimiento(AuConstantes.obtenerHashMaestro(bean.getListaTipoSeguimiento()));
            int aplicataRescate = getAucHospitalizacionRemoto().consultarAplicaRescate(bean.getObjeto().getId());
            if(aplicataRescate == AucHospitalizacion.GESTION_RESCATE){
                bean.addError("El paciente ya fue enviado para rescate");
            }else if(aplicataRescate == AucHospitalizacion.NO_APTO_RESCATE){
                bean.addError("El paciente fue marcado como NO apto para rescate");
            }
        } catch (Exception e) {
             bean.addError("Hubo un fallo consultando el seguimientos, intentelo nuevamente");
        }
    }
    
    @Override
    public void consultarNoAptoRescate(AucHospitalizacionBean bean) {
        try {
     
            int aplicataRescate = getAucHospitalizacionRemoto().consultarAplicaRescate(bean.getObjeto().getId());
            if(aplicataRescate == AucHospitalizacion.NO_APTO_RESCATE){
                bean.addError("El paciente aplico no apto rescate");
            }
        } catch (Exception e) {
             bean.addError("Hubo un fallo consultando el seguimientos, intentelo nuevamente");
        }
    }
    
    @Override
    public boolean consultarAplicaRescateHospitalizacionPrograma(AucHospitalizacionBean bean) {
        boolean validar = true;
        try {
            validar = getAuAnexo2RescateRemoto().aplicaRescateHospitalizacionPrograma(bean.getObjetoRescate().getAucHospitalizacion().getId(), (bean.getObjetoRescate().getPePrograma().getId()));

        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    @Override
    public void validarDiagnosticosNoHayaMasPrincipales(AucHospitalizacionBean bean, AucDiagnostico diagnostico) {

        try {
            List<AucDiagnostico> listDiagnosticos = getAucDiagnosticoRemoto().consultarPorIdIngreso(bean.getObjeto().getAucIngresoId().getId());
            if (!listDiagnosticos.isEmpty()) {
                for (AucDiagnostico diagnoticoConsulta : listDiagnosticos) {
                    if (diagnoticoConsulta.isPrincipal() && diagnostico.isPrincipal()) {
                        bean.addError("No puede haber más de un diagnóstico principal");
                        break;
                    }
                }
            }

        } catch (Exception e) {
            bean.addError("No puedo consultar diagnosticos de ingreso");
        }
    }

    @Override
    public void consultarGestionRiegosSugerido(AucHospitalizacionBean bean) {

        try {
            bean.setListaTiposSugeridoAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_ADJUNTO_TIPO));
            bean.setHashTiposSugeridoAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposSugeridoAdjuntos()));
            
            List<PePrograma> listPreprogramas = getPeProgramaRemoto().programasNoMatriculadosSugeridosHospitalizacion(bean.getObjeto().getAucAfiliadoId().getAsegAfiliadoId().getId());
            if (!listPreprogramas.isEmpty()) {
                bean.getObjeto().setAucSugerirProgramaList(listPreprogramas);
                bean.setHashPePrograma(convertToHashPePrograma(bean.getObjeto().getAucSugerirProgramaList()));
            }

        } catch (Exception e) {
            bean.addError("No puedo consultar diagnosticos de ingreso");
        }
    }

    public HashMap<Integer, PePrograma> convertToHashPePrograma(List<PePrograma> list) {
        HashMap<Integer, PePrograma> map = new HashMap<>();
        for (PePrograma i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public boolean consultarEmpresaAdministradora(AucHospitalizacionBean bean) {
        boolean isAdministradora = false;
        try {
            String[] usuarioSplit = bean.getObjetoAdverso().getUsuarioModifica().split(" ");
            Usuario usuario = getUsuarioRemoto().consultarPorUsuario(usuarioSplit[0]);
            if (usuario != null) {
                Empresa empresa = getEmpresaRemoto().consultar(usuario.getEmpresa().getId());
                if (empresa != null) {
                    boolean admin = (empresa.isAdministradora()) ? false : true;
                    isAdministradora = admin;
                }
            }
        } catch (Exception e) {
            bean.addError("No puedo consultar diagnosticos de ingreso");
        }
        return isAdministradora;
    }

    private void completarHistoricoIngreso(AucHospitalizacionBean bean) {
        Maestro tipoIngreso = bean.getHashTiposIngreso().get(bean.getObjeto().getAucIngresoId().getMaeTipoIngresoId());
        if (tipoIngreso != null) {
            bean.getObjeto().getAucIngresoId().setMaeTipoIngresoCodigo(tipoIngreso.getValor());
            bean.getObjeto().getAucIngresoId().setMaeTipoIngresoValor(tipoIngreso.getNombre());
        }
        Maestro modalidad = bean.getHashCtnModalidades().get(bean.getObjeto().getAucIngresoId().getMaeCntModalidadId());
        if (modalidad != null) {
            bean.getObjeto().getAucIngresoId().setMaeCntModalidadCodigo(modalidad.getValor());
            bean.getObjeto().getAucIngresoId().setMaeCntModalidadValor(modalidad.getNombre());
        }

        Maestro remision = bean.getHashRemisionesNoPertinentes().get(bean.getObjeto().getAucIngresoId().getMaeRemisionNoPertinenteId());
        if (remision != null) {
            bean.getObjeto().getAucIngresoId().setMaeRemisionNoPertinenteCodigo(remision.getValor());
            bean.getObjeto().getAucIngresoId().setMaeRemisionNoPertinenteValor(remision.getNombre());
        }
        
        Maestro causaIngresoPrevenible = bean.getHashCausaIngresoPrevenible().get(bean.getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId());
        if (causaIngresoPrevenible != null) {
            bean.getObjeto().getAucIngresoId().setMaeCausaIngresoPrevalenteCodigo(causaIngresoPrevenible.getValor());
            bean.getObjeto().getAucIngresoId().setMaeCausaIngresoPrevalenteValor(causaIngresoPrevenible.getNombre());
        }else{
            bean.getObjeto().getAucIngresoId().setMaeCausaIngresoPrevalenteCodigo(null);
            bean.getObjeto().getAucIngresoId().setMaeCausaIngresoPrevalenteValor(null);
        }
        
        Maestro areaResponsableIngresoPrevenible = bean.getHashAreaResponsableIngresoPrevenible().get(bean.getObjeto().getAucIngresoId().getMaeAreaIngresoPrevenibleId());
        if (areaResponsableIngresoPrevenible != null) {
            bean.getObjeto().getAucIngresoId().setMaeAreaIngresoPrevenibleCodigo(areaResponsableIngresoPrevenible.getValor());
            bean.getObjeto().getAucIngresoId().setMaeAreaIngresoPrevenibleValor(areaResponsableIngresoPrevenible.getNombre());
        }else{
            bean.getObjeto().getAucIngresoId().setMaeAreaIngresoPrevenibleCodigo(null);
            bean.getObjeto().getAucIngresoId().setMaeAreaIngresoPrevenibleValor(null);
        }
        if(bean.getHashMotivoReingreso() != null){
            Maestro reingresoMotivo = bean.getHashMotivoReingreso().get(bean.getObjeto().getAucIngresoId().getMaeReingresoMotivoId());
            if(reingresoMotivo != null){
                bean.getObjeto().getAucIngresoId().setMaeReingresoMotivoCodigo(reingresoMotivo.getValor());
                bean.getObjeto().getAucIngresoId().setMaeReingresoMotivoValor(reingresoMotivo.getNombre());
                bean.getObjeto().getAucIngresoId().setMaeReingresoMotivoTipo(reingresoMotivo.getTipo());
            }else{
                bean.getObjeto().getAucIngresoId().setMaeReingresoMotivoCodigo(null);
                bean.getObjeto().getAucIngresoId().setMaeReingresoMotivoValor(null);
                bean.getObjeto().getAucIngresoId().setMaeReingresoMotivoTipo(null);
            }
        }
        if(bean.getHashProgramaAltaTemprana() != null){
            Maestro programaAltaTempranaGestionar = bean.getHashProgramaAltaTemprana().get(bean.getObjeto().getAucIngresoId().getMaeAltaTempranaId());
            if(programaAltaTempranaGestionar != null){
                bean.getObjeto().getAucIngresoId().setMaeAltaTempranaCodigo(programaAltaTempranaGestionar.getValor());
                bean.getObjeto().getAucIngresoId().setMaeAltaTempranaValor(programaAltaTempranaGestionar.getNombre());
                bean.getObjeto().getAucIngresoId().setMaeAltaTempranaTipo(programaAltaTempranaGestionar.getTipo());
            }else{
                bean.getObjeto().getAucIngresoId().setMaeAltaTempranaCodigo(null);
                bean.getObjeto().getAucIngresoId().setMaeAltaTempranaValor(null);
                bean.getObjeto().getAucIngresoId().setMaeAltaTempranaTipo(null);
            }
        }
        
    }
    
   
    
    @Override
    public void cargasInicial(AucHospitalizacionBean bean) {
        try {
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());

            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            this.consultarMaestroPosibleAltaTemprana(bean);
            
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumento(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));
            bean.setListaTipoDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));

        } catch (Exception e) {
            bean.addError("Error en carga inicial, favor contactar al adminitrador");
        }
    }

    public void registrarHistorico(String tostring, AucHospitalizacionBean bean) throws Exception {
        AucHospitalizacionHistorico historicoHopitalizacion = new AucHospitalizacionHistorico();
        historicoHopitalizacion.setAucHospitalizacionesId(bean.getObjeto());
        historicoHopitalizacion.setTostringHospitalizacion(tostring);
        bean.auditoriaGuardar(historicoHopitalizacion);
        getAucHospitalizacionHistoricoRemoto().insertar(historicoHopitalizacion);
    }

    public void registroHospitalizacionEstados(AucHospitalizacionBean bean, Short estado, Short estadoAuditoria, String descripcion) throws Exception {
        AucHospitalizacionEstado puedeInsertar = getAucHospitalizacionEstadoRemoto().consultarHospitalizacionYestados(bean.getObjeto().getId());
        AucHospitalizacionEstado hospitalizacionestado = new AucHospitalizacionEstado();
        boolean insertar = false;
        if (puedeInsertar.getEstado() == null && puedeInsertar.getEstadoAuditoria() == null) {
            hospitalizacionestado.setAucHospitalizacionId(bean.getObjeto());
            hospitalizacionestado.setEstado(estado);
            hospitalizacionestado.setEstadoAuditoria(estadoAuditoria);
            hospitalizacionestado.setFuenteOrigen(AucHospitalizacionEstado.FUENTE_ORIGEN_HOSPITALIZACION);
            hospitalizacionestado.setObservacion(descripcion);
            insertar = true;
        }
        if (puedeInsertar.getEstado() != null && puedeInsertar.getEstadoAuditoria() != null) {
            if (!puedeInsertar.getEstado().equals(estado) || !puedeInsertar.getEstadoAuditoria().equals(estadoAuditoria)) {
                hospitalizacionestado.setAucHospitalizacionId(bean.getObjeto());
                hospitalizacionestado.setEstado(estado);
                hospitalizacionestado.setEstadoAuditoria(estadoAuditoria);
                hospitalizacionestado.setFuenteOrigen(AucHospitalizacionEstado.FUENTE_ORIGEN_HOSPITALIZACION);
                hospitalizacionestado.setObservacion(descripcion);
                insertar = true;
            }
        }
        if (insertar) {
            bean.auditoriaGuardar(hospitalizacionestado);
            getAucHospitalizacionEstadoRemoto().insertar(hospitalizacionestado);
        }
    }
}
