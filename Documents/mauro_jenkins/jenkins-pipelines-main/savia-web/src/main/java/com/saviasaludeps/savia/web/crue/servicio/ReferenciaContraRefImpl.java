/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Adjunto;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9DatoClinico;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Diagnostico;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Estado;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Gestion;
import com.saviasaludeps.savia.dominio.crue.RefTransporte;
import com.saviasaludeps.savia.dominio.crue.RefTransporteInsumo;
import com.saviasaludeps.savia.dominio.crue.ReporteReferencia;
import com.saviasaludeps.savia.dominio.crue.ReporteReferenciaContraReferencia;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaRelacion;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9AdjuntoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9DatoClinicoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9DiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9EstadoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9GestionaRemoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9Remoto;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9SemaforoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefTransSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefTransporteInsumoRemoto;
import com.saviasaludeps.savia.negocio.crue.RefTransporteRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaRelacionRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.crue.bean.DTO.RefAnexo9TriageDTO;
import com.saviasaludeps.savia.web.crue.bean.ReferenciaContraRefBean;
import static com.saviasaludeps.savia.web.crue.bean.ReferenciaContraRefBean.CODIGO_SERVICIOS_HOBILITACION;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Log;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
public class ReferenciaContraRefImpl extends AccionesBO implements ReferenciaContraRefIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private RefAnexo9Remoto getRefAnexo9Remoto() throws Exception {
        return (RefAnexo9Remoto) RemotoEJB.getEJBRemoto("RefAnexo9Servicio", RefAnexo9Remoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private RefAnexo9DatoClinicoRemoto getRefAnexo9DatoClinicoRemoto() throws Exception {
        return (RefAnexo9DatoClinicoRemoto) RemotoEJB.getEJBRemoto("RefAnexo9DatoClinicoServicio", RefAnexo9DatoClinicoRemoto.class.getName());
    }

    private MaServicioHabilitacionRemoto getMaServicioHabilitacionRemoto() throws Exception {
        return (MaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("MaServicioHabilitacionServicio", MaServicioHabilitacionRemoto.class.getName());
    }

    private CntProfesionalRemoto getCntProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private RefAnexo9AdjuntoRemoto getRefAnexo9AdjuntoRemoto() throws Exception {
        return (RefAnexo9AdjuntoRemoto) RemotoEJB.getEJBRemoto("RefAnexo9AdjuntoServicio", RefAnexo9AdjuntoRemoto.class.getName());
    }

    private RefAnexo9EstadoRemoto getRefAnexo9EstadoRemoto() throws Exception {
        return (RefAnexo9EstadoRemoto) RemotoEJB.getEJBRemoto("RefAnexo9EstadoServicio", RefAnexo9EstadoRemoto.class.getName());
    }

    private RefAnexo9DiagnosticoRemoto getRefAnexo9DiagnosticoRemoto() throws Exception {
        return (RefAnexo9DiagnosticoRemoto) RemotoEJB.getEJBRemoto("RefAnexo9DiagnosticoServicio", RefAnexo9DiagnosticoRemoto.class.getName());
    }

    private RefAnexo9GestionaRemoto getRefAnexo9GestionaRemoto() throws Exception {
        return (RefAnexo9GestionaRemoto) RemotoEJB.getEJBRemoto("RefAnexo9GestionaServicio", RefAnexo9GestionaRemoto.class.getName());
    }

    private RefAnexo9SemaforoRemoto getRefAnexo9SemaforoRemoto() throws Exception {
        return (RefAnexo9SemaforoRemoto) RemotoEJB.getEJBRemoto("RefAnexo9SemaforoServicio", RefAnexo9SemaforoRemoto.class.getName());
    }

    private RefTransporteRemoto getRefTransporteRemoto() throws Exception {
        return (RefTransporteRemoto) RemotoEJB.getEJBRemoto("RefTransporteServicio", RefTransporteRemoto.class.getName());
    }

    private RefTransporteInsumoRemoto getRefTransporteInsumoRemoto() throws Exception {
        return (RefTransporteInsumoRemoto) RemotoEJB.getEJBRemoto("RefTransporteInsumoServicio", RefTransporteInsumoRemoto.class.getName());
    }

    private RefTransSeguimientoRemoto getRefTransSeguimientoRemoto() throws Exception {
        return (RefTransSeguimientoRemoto) RemotoEJB.getEJBRemoto("RefTransSeguimientoServicio", RefTransSeguimientoRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getMaDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }
    
    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }
    
    private MaRelacionRemoto getMaRelacionRemoto() throws Exception {
        return (MaRelacionRemoto) RemotoEJB.getEJBRemoto("MaRelacionServicio", MaRelacionRemoto.class.getName());
    }
    
    @Override
    public void Accion(ReferenciaContraRefBean bean) {
        if (super.ValidarSesion(bean)) {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(null);
            } else {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crear(bean);
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarDiagnostico(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verGestionar(bean);
                            break;
                        case Url.ACCION_LISTAR:
                            listarGestionar(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearGestionar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestionar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearAdjuntoGestionar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            guardarAdjuntoGestionar(bean);
                            break;
                        case ReferenciaContraRefBean.ACCION_BORRAR_CONTACTOS_PERSONAS:
                            borrarAdjuntoReferenciaGestionar(bean);
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_LISTAR:
                            listarTransporte(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearTransporte(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarTransporte(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            guardarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verSeguimiento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    reporteReferencia(bean);
                    break;
                case Url.ACCION_ADICIONAL_5:
                    reporteReferenciaContraReferencia(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(ReferenciaContraRefBean bean) {
        try {
            //Maestro Tipo Documento 
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(new HashMap());
            bean.getListaTiposDocumento().forEach(m -> {
                bean.getHashTiposDocumento().put(m.getId(), m);
            });
            //Maestro Tipo Documento Persona
            bean.setListaTiposDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            //Maestro Tipo Documento Empresa
            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            //Maestro Tipo Documento Profesional
            bean.setListaTiposDocumentoProfesional(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL));
            bean.setHashTiposDocumentoProfesional(new HashMap());
            bean.getListaTiposDocumentoProfesional().forEach(m -> {
                bean.getHashTiposDocumentoProfesional().put(m.getId(), m);
            });
            //Maestro Sexo(Genero)
            bean.setListaTiposGenero(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            //Maestro Canal Comunicacion
            bean.setListaCanalComunicacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A9_CANAL_COMUNICACION));
            bean.setHashCanalComunicacion(new HashMap<Integer, Maestro>());
            bean.getListaCanalComunicacion().forEach(m -> {
                bean.getHashCanalComunicacion().put(m.getId(), m);
            });
            //Ubicacion
            bean.setListaUbicacion(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicacion(UbicacionSingle.getInstance().getHashUbicaciones());
            //MaServicio Habilitacion
            bean.setListaMaServicioHabilitacion(getMaServicioHabilitacionRemoto().consultarTodos());
            //Tipo Adjunto
            bean.setListaTipoAdjunto(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_ADJUNTO_TIPO));
            bean.setHashTipoAdjunto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_ADJUNTO_TIPO));
            //Tipo Gestion
            bean.setListaTipoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_GESTION_TIPO));
            bean.setHashTipoGestion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_GESTION_TIPO));
            //Tipo Motivo
            bean.setListaMotivoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_GESTION_MOTIVO));
            bean.setListaMotivoGestionFiltro(
                    bean.getListaMotivoGestion()
            );
//            bean.getListaMotivoGestionFiltro().addAll(
//                    getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CR_GESTION_MOTIVO, RefAnexo9Gestion.ESTADO_CANCELADA)
//            );
            //Semaforo
            bean.setListaRefAnexo9Semaforo(getRefAnexo9SemaforoRemoto().consularTodos());
            //Tipo Transporte
            bean.setListaTipoTransporte(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_TRANSPORTE_TIPO));
            bean.setHashTipoTransporte(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_TRANSPORTE_TIPO));
            //Clase Transporte           
            bean.setListaClaseTransporteFiltro(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_TRANSPORTE_CLASE));
            //Insumo Transporte
            bean.setListaInsumoTransporte(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_TRANSPORTE_INSUMO));
            bean.setHashInsumoTransporte(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_TRANSPORTE_INSUMO));
            //Tipo Motivo Seguimiento 
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_TRANSPORTE_SEGUIMIENTO_TIPO_REPORTE));
            bean.setHashTipoSeguimiento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_TRANSPORTE_SEGUIMIENTO_TIPO_REPORTE));
            //Transporte Liquidacion
            bean.setListaTransporteLiquidacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_TRANSPORTE_LIQUIDACION));
            bean.setHashTransporteLiquidacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_TRANSPORTE_LIQUIDACION));
            //Estado Solicitud
            bean.setListaEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A9_ESTADO));
            bean.setHashEstadoSolicitud(new HashMap<Integer, Maestro>());
            bean.getListaEstadoSolicitud().forEach(m -> {
                bean.getHashEstadoSolicitud().put(m.getId(), m);
            });
            //Carga Triage DTO
            bean.setListaTriage(new ArrayList<>());
            bean.getListaTriage().add(new RefAnexo9TriageDTO(1, "Triage Nivel 1", "../resources/images/triage1.png"));
            bean.getListaTriage().add(new RefAnexo9TriageDTO(2, "Triage Nivel 2", "../resources/images/triage2.png"));
            bean.getListaTriage().add(new RefAnexo9TriageDTO(3, "Triage Nivel 3", "../resources/images/triage3.png"));
            bean.getListaTriage().add(new RefAnexo9TriageDTO(4, "Triage Nivel 4", "../resources/images/triage4.png"));
            bean.getListaTriage().add(new RefAnexo9TriageDTO(5, "Triage Nivel 5", "../resources/images/triage5.png"));

            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
//            bean.setHashEstadosAfiliado(AuConstantes.obtenerHashMaestro(bean.getListaEstadosAfiliado()));
            bean.setListaRegimen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
//            bean.setHashRegimen(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGIMEN));

            //bean.setListaCausaExterna(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_ORIGEN_ATENCION));
            bean.setListaCausaExterna(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_ORIGEN_ATENCION_RESOLUCION_2335));
            bean.setHashCausaExterna(AuConstantes.obtenerHashMaestro(bean.getListaCausaExterna()));

            bean.setListaCondicionDestino(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.CR_A2_DESTINO_PACIENTE_RESOLUCION_2335));
            bean.setHashCondicionDestino(AuConstantes.obtenerHashMaestro(bean.getListaCondicionDestino()));

            //bean.setListaTipoAtencion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_SERVICIO));
            bean.setListaTipoAtencion(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_TIPO_SERVICIO_RESOLUCION_2335));
            bean.setHashTipoAtencion(AuConstantes.obtenerHashMaestro(bean.getListaTipoAtencion()));

            bean.setListaModalidadTecnologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_A1_MODALIDAD_TECNOLOGIA));
            bean.setHashModalidadTecnologia(AuConstantes.obtenerHashMaestro(bean.getListaModalidadTecnologia()));

            bean.setListaTecnologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_A1_FINALIDAD_TECNOLOGIA));
            bean.setHashTecnologia(AuConstantes.obtenerHashMaestro(bean.getListaTecnologia()));
            
            //bean.setListaGrupoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_UBICACION));
            bean.setListaGrupoServicio(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_UBICACION_RESOLUCION_2335));
            bean.setHashGrupoServicio(AuConstantes.obtenerHashMaestro(bean.getListaGrupoServicio()));
            
            bean.setListaTipoAislamiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A9_TIPO_AISLAMIENTO));
            bean.setHashTipoAislamiento(AuConstantes.obtenerHashMaestro(bean.getListaTipoAislamiento()));
            
            bean.setListaMaternoPerinata(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A9_MATERNO_PERINATAL));
            bean.setHashMaternoPerinata(AuConstantes.obtenerHashMaestro(bean.getListaMaternoPerinata()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarAfiliado(ReferenciaContraRefBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(1)));
            bean.setRegistrosAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(1)));
//            for (AsegAfiliado registro : bean.getRegistrosAfiliados()) {
//                if (registro.getResidenciaUbicacion() != null) {
//                    registro.setResidenciaUbicacion(getUbicacionRemoto().consultar(registro.getResidenciaUbicacion().getId()));
//                }
//            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarIPS(ReferenciaContraRefBean bean) {
        try {
            boolean consultarXCodidoHabilitacion = false;
            if (!bean.getConexion().getEmpresa().isAdministradora() && bean.getDialogo().equals(ReferenciaContraRefBean.DIALOGO_CREAR)) {
                consultarXCodidoHabilitacion = true;
            }

            bean.getParamConsulta(2).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(2).getFiltros().put("fecha", Util.fechaDateAString(new Date()));

            if (consultarXCodidoHabilitacion) {
                Empresa empresa = getEmpresaRemoto().consultar(bean.getConexion().getEmpresa().getId());
//                bean.getParamConsulta(2).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
                bean.getParamConsulta(2).setParametroConsulta5(empresa.getCntPrestador().getId());
            }

            bean.getParamConsulta(2).setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(2)));
            bean.setRegistroIPS(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta(2)));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarProfesional(ReferenciaContraRefBean bean) {
        try {
            int tipoDocumento = bean.getObjeto().getCntProfesionales().getMaeTipoCodumentoId();
            String documento = bean.getObjeto().getCntProfesionales().getDocumento();
            CntProfesional cntProfesional = getCntProfesionalRemoto().consultarNumDocumento(tipoDocumento, documento);

            if (cntProfesional != null) {
                bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
                CntProfesionalPrestador profesionalesP = getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(cntProfesional.getId(), bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId());
                if (profesionalesP != null) {
                    bean.setObjetoProfesionalPrestador(profesionalesP);
                    bean.getObjeto().setNewProfesional(true);
                } else {
                    bean.getObjeto().setNewProfesional(false);
                }
                bean.getObjeto().setCntProfesionales(cntProfesional);
                bean.getObjeto().getCntProfesionales().setGuardar(false);
            } else {
                bean.getObjeto().setCntProfesionales(new CntProfesional());
                bean.getObjeto().getCntProfesionales().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjeto().getCntProfesionales().setDocumento(documento);
                bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
                bean.getObjeto().getCntProfesionales().setGuardar(true);
                bean.getObjeto().setNewProfesional(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarAfiliadoPortabilidad(ReferenciaContraRefBean bean) {
        try {
            bean.getObjeto().getAsegAfiliado().setPrimariaPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getId()));
//            bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getUbicacionId()));
//            if (bean.getObjeto().getAsegAfiliado().getPortabilidadPrestadorSede() != null) {
//                bean.getObjeto().getAsegAfiliado().getPortabilidadPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getObjeto().getAsegAfiliado().getPortabilidadPrestadorSede().getUbicacionId()));
//            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(ReferenciaContraRefBean bean) {
        try {
            bean.getParamConsulta().setParametroConsulta1(bean.getFechaInicio());
            bean.getParamConsulta().setParametroConsulta2(bean.getFechaFin());
            bean.getParamConsulta().setCantidadRegistros(getRefAnexo9Remoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getRefAnexo9Remoto().consultarLista(bean.getParamConsulta()));
//            for (RefAnexo9 registro : bean.getRegistros()) {
//                registro.setRefAnexo9DatoClinico(getRefAnexo9DatoClinicoRemoto().consultarPorRefAnexo9(registro.getId()));
            /*if (registro.getMaeEstadoId().intValue() == RefAnexo9Estado.ESTADO_RECHAZADA
                        || registro.getMaeEstadoId().intValue() == RefAnexo9Estado.ESTADO_ANULADA
                        || registro.getMaeEstadoId().intValue() == RefAnexo9Estado.ESTADO_CANCELADA) {
                    registro.setRefAnexo9Gestion(getRefAnexo9GestionaRemoto().consultarPorRefAnexo9(registro.getId()));
                }*/
//            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ReferenciaContraRefBean bean) {
        try {
            bean.setObjeto(getRefAnexo9Remoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.getObjeto().setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getCntPrestadorSede().getId()));
            bean.getObjeto().getCntPrestadorSede().setCntPrestador(getCntPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()));
            bean.getObjeto().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjeto().getAsegAfiliado().getFechaNacimiento()));
            bean.getObjeto().getAsegAfiliado().setPrimariaPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getId()));
            bean.getObjeto().setListaRefAnexo9Diagnostico(getRefAnexo9DiagnosticoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));

            bean.setListaRefAnexo9Adjuntos(getRefAnexo9AdjuntoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));
            for (RefAnexo9Adjunto adjunto : bean.getListaRefAnexo9Adjuntos()) {
                int indiceExtension = adjunto.getNombreArchivo().lastIndexOf(".") + 1;
                adjunto.setExtension(adjunto.getNombreArchivo().substring(indiceExtension, adjunto.getNombreArchivo().length()));
                adjunto.setNombre(adjunto.getNombreArchivo().substring(0, indiceExtension));
            }
            if (bean.getObjeto().getCntProfesionales() != null) {
                bean.getObjeto().setCntProfesionales(getCntProfesionalRemoto().consultar(bean.getObjeto().getCntProfesionales().getId()));
                if (bean.getObjeto().getCntProfesionales() != null) {
                    //                List<CntProfesionalPrestador> listaCntProfesionalPrestador = getCntPrestadorProfesionalRemoto().consultarPorProfesional(bean.getObjeto().getCntProfesionales().getId());
                    bean.setObjetoProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(
                            bean.getObjeto().getCntProfesionales().getId(),
                            bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()
                    ));
                }
            }

            bean.setObjetoDatosClinicos(getRefAnexo9DatoClinicoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));
            bean.getObjeto().setRefAnexo9DatoClinico(bean.getObjetoDatosClinicos());
            bean.setListaAfiliadoPrograma(getPeAfiliadosProgramaRemoto().consultarAfiliadoActivo(bean.getObjeto().getAsegAfiliado().getId()));
            contactosAfiliado(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ReferenciaContraRefBean bean) {
        try {
            bean.setObjeto(new RefAnexo9());
            bean.getObjeto().setAsegAfiliado(new AsegAfiliado());
            bean.getObjeto().setCntPrestadorSede(new CntPrestadorSede());
            bean.getObjeto().getCntPrestadorSede().setCntPrestador(new CntPrestador());
            bean.getObjeto().setCntProfesionales(new CntProfesional());
            bean.getObjeto().getCntProfesionales().setGuardar(true);
            bean.getObjeto().setListaRefAnexo9Diagnostico(new ArrayList<>());
            bean.getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(true);
            bean.getObjeto().setVersion(RefAnexo9.VERSION_0);
            bean.setObjetoDatosClinicos(new RefAnexo9DatoClinico());
            bean.setObjectoTriage(new RefAnexo9TriageDTO());
            bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
            bean.setListaRefAnexo9Adjuntos(new ArrayList<>());
//            if (bean.getConexion().getEmpresa().getId().intValue() != ReferenciaContraRefBean.ID_GNEMPRESACRUE) {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                Maestro maestro = bean.getHashCanalComunicacion().get(ReferenciaContraRefBean.CANAL_IPS);
                bean.getObjeto().setMaeCanalComunicacionCodigo(maestro.getValor());
                bean.getObjeto().setMaeCanalComunicacionId(maestro.getId());
                bean.getObjeto().setMaeEstadoValor(maestro.getNombre());
                bean.setDeshabilitadoCanal(true);
                maestro = null;
            } else {
                bean.setDeshabilitadoCanal(false);
            }
            bean.getObjeto().setHabilitarCups(true);
            bean.getObjeto().setHabilitarDireccionAlternativa(true);
            bean.getObjeto().setHabilitarNombreContactoEmergencia(true);
            bean.getObjeto().setHabilitarTelefonoContactoEmergencia(true);
            bean.getObjeto().setHabilitarMaeCausaExternaId(true);
            bean.getObjeto().setHabilitarPrioridad(true);
            bean.getObjeto().setHabilitarMaeTipoAtencionId(true);
            bean.getObjeto().setHabilitarGrupoServicio(true);
            bean.getObjeto().setHabilitarMaeModalidadTecnologiaId(true);
            bean.getObjeto().setHabilitarMaeCondicionDestinoId(true);
            bean.getObjeto().setHabilitarMaeMaternoPerinatalId(true);
            //llena toda la lista
            bean.setListaTipoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_GESTION_TIPO));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ReferenciaContraRefBean bean) {
        try {
            boolean validar = true;

            Maestro maeCausaExterna = bean.getHashCausaExterna().get(bean.getObjeto().getMaeCausaExternaId());
            if (maeCausaExterna != null) {
                bean.getObjeto().setMaeCausaExternaCodigo(maeCausaExterna.getValor());
                bean.getObjeto().setMaeCausaExternaValor(maeCausaExterna.getNombre());
            }

            Maestro maeCondicionDestino = bean.getHashCondicionDestino().get(bean.getObjeto().getMaeCondicionDestinoId());
            if (maeCondicionDestino != null) {
                bean.getObjeto().setMaeCondicionDestinoCodigo(maeCondicionDestino.getValor());
                bean.getObjeto().setMaeCondicionDestinoValor(maeCondicionDestino.getNombre());
            }

            Maestro maeTipoAtencion = bean.getHashTipoAtencion().get(bean.getObjeto().getMaeTipoAtencionId());
            if (maeTipoAtencion != null) {
                bean.getObjeto().setMaeTipoAtencionCodigo(maeTipoAtencion.getValor());
                bean.getObjeto().setMaeTipoAtencionValor(maeTipoAtencion.getNombre());
            }

            Maestro maeUbicacion = bean.getHashGrupoServicio().get(bean.getObjeto().getMaeUbicacionId());
            if (maeUbicacion != null) {
                bean.getObjeto().setMaeUbicacionCodigo(maeUbicacion.getValor());
                bean.getObjeto().setMaeUbicacionValor(maeUbicacion.getNombre());
            }

            Maestro maeModalidadTecnologia = bean.getHashModalidadTecnologia().get(bean.getObjeto().getMaeModalidadTecnologiaId());
            if (maeModalidadTecnologia != null) {
                bean.getObjeto().setMaeModalidadTecnologiaCodigo(maeModalidadTecnologia.getValor());
                bean.getObjeto().setMaeModalidadTecnologiaValor(maeModalidadTecnologia.getNombre());
            }
            
            Maestro maeMaternoPerinata = bean.getHashMaternoPerinata().get(bean.getObjeto().getMaeMaternoPerinatalId());
            if (maeMaternoPerinata != null) {
                bean.getObjeto().setMaeMaternoPerinatalCodigo(maeMaternoPerinata.getValor());
                bean.getObjeto().setMaeMaternoPerinatalValor(maeMaternoPerinata.getNombre());
                bean.getObjeto().setMaeMaternoPerinatalTipo(maeMaternoPerinata.getTipo());
            }
            
            //List<RefAnexo9> lista = getRefAnexo9Remoto().consultarLista(bean.getParamConsulta(0));
            //int ultimoId = lista.get(0).getId() + 1;
            ///bean.getObjeto().setVersion(0);

            if (bean.getObjetoDatosClinicos().getImc() == null || bean.getObjetoDatosClinicos().getImc().intValue() >= 1000) {
                bean.addError("IMC : Error de validación: El valor debe estar entre 0 y 999.");
                validar = false;
            }
            if (bean.getObjetoDatosClinicos().getResumenClinico().isBlank()) {
                bean.addError("Resumen enfermedad Actual : Error de validación: se necesita un valor.");
                validar = false;
            }
            if (bean.getObjetoDatosClinicos().getAntecedentes().isBlank()) {
                bean.addError("Descripción : Error de validación: se necesita un valor.");
                validar = false;
            }
            if (bean.getObjetoDatosClinicos().getHallazgosExamenFisico().isBlank()) {
                bean.addError("Descripción : Error de validación: se necesita un valor.");
                validar = false;
            }
            if (bean.getObjetoDatosClinicos().getMotivoRemision().isBlank()) {
                bean.addError("Descripción : Error de validación: se necesita un valor.");
                validar = false;
            }
            /*if(!bean.getObjeto().isHabilitarCodigoCUPSprocedimientoRequerido()){
                if(bean.getObjeto().getMaTecnologiaId() == null){
                    bean.addError("Codigo CUPS del procedimiento requerido: Error de validación: se necesita un valor.");
                    validar = false;
                }
            }*/
            if(!bean.getObjeto().isHabilitarCups()){
                if(bean.getObjeto().getMaTecnologiaId() == null){
                    bean.addError("Codigo CUPS del procedimiento requerido: Error de validación: se necesita un valor.");
                    validar = false;
                }
            }
            //se inabilita validacion mientras se hanilitan la resoluccion 2335
            /*if (bean.getObjeto().getMaTecnologiaId() == null) {s
                bean.addError("Codigo CUPS del procedimiento requerido : Error de validación: se necesita un valor.");
                validar = false;
            }*/
            
            if ((bean.getObjeto().getMaeAcompananteTipoDocumentoId() != null && bean.getObjeto().getMaeAcompananteTipoDocumentoId() != 0)
                    || (bean.getObjeto().getAcompananteDocumento() != null && !bean.getObjeto().getAcompananteDocumento().isBlank())
                    || (bean.getObjeto().getAcompanantePrimerNombre() != null && !bean.getObjeto().getAcompanantePrimerNombre().isBlank())
                    || (bean.getObjeto().getAcompanantePrimerApellido() != null && !bean.getObjeto().getAcompanantePrimerApellido().isBlank())) {
                if (bean.getObjeto().getMaeAcompananteTipoDocumentoId() == null || bean.getObjeto().getMaeAcompananteTipoDocumentoId() == 0) {
                    bean.addError("Acompañante Tipo Documento : Error de validación: se necesita un valor.");
                    validar = false;
                }
                if (bean.getObjeto().getAcompananteDocumento() == null
                        || bean.getObjeto().getAcompananteDocumento().isBlank()) {
                    bean.addError("Acompañante Documento : Error de validación: se necesita un valor.");
                    validar = false;
                }
                if (bean.getObjeto().getAcompanantePrimerNombre() == null
                        || bean.getObjeto().getAcompanantePrimerNombre().isBlank()) {
                    bean.addError("Acompañante Primer Nombre : Error de validación: se necesita un valor.");
                    validar = false;
                }
                if (bean.getObjeto().getAcompanantePrimerApellido() == null
                        || bean.getObjeto().getAcompanantePrimerApellido().isBlank()) {
                    bean.addError("Acompañante Primer Apellido : Error de validación: se necesita un valor.");
                    validar = false;
                }
            }

            if (bean.getObjetoDatosClinicos().getEscalaGlasgow() != null && (bean.getObjetoDatosClinicos().getEscalaGlasgow() < 3 || bean.getObjetoDatosClinicos().getEscalaGlasgow() > 15)) {
                bean.addError("Escala de glasglow (3-15) : Error de validación: el valor debe estar entre 3 y 15.");
                validar = false;
            }

            if (bean.getObjeto().getAsegAfiliado() == null || bean.getObjeto().getAsegAfiliado().getId() == null || bean.getObjeto().getAsegAfiliado().getId() == 0) {
                bean.addError("Afiliado : Error de validación: se necesita un valor.");
                validar = false;
            }
            if (bean.getObjeto().getCntPrestadorSede() == null || bean.getObjeto().getCntPrestadorSede().getId() == null || bean.getObjeto().getCntPrestadorSede().getId() == 0) {
                bean.addError("Prestador IPS: Error de validación: se necesita un valor.");
                validar = false;
            }
            if (bean.getObjeto().getListaRefAnexo9Diagnostico().isEmpty()) {
                bean.addError("Diagnosticos :Error de validación: se necesita un valor.");
                validar = false;
            }
            if (bean.getObjeto().getMaServicioSolicitaId() == 0) {
                bean.addError("Servicio solicita :Error de validación: se necesita un valor.");
                validar = false;
            }
            
            if (bean.getObjetoProfesionalPrestador() != null && bean.getObjetoProfesionalPrestador().getMaEspecialidadId() == 0) {
                bean.addError("Profesional espcialidad :Error de validación: se necesita un valor.");
                validar = false;
            }
            
            boolean noPrincipal = true;
            for (RefAnexo9Diagnostico refAnexo9Diagnostico : bean.getObjeto().getListaRefAnexo9Diagnostico()) {
                if (refAnexo9Diagnostico.isPrincipal()) {
                    noPrincipal = false;
                }
            }
            if (noPrincipal) {
                bean.addError("Diagnosticos :Error de validación: se necesita un diagnostico principal.");
                validar = false;
            }
            if (!validar) {
                return;
            }
            if (bean.getObjetoUbicacionAcomp() != null) {
                bean.getObjeto().setAcompananteMunicipio(bean.getObjetoUbicacionAcomp().getNombre());

                if (bean.getObjetoUbicacionAcomp().getUbicacionPadre() != null) {
                    bean.getObjeto().setAcompananteDepartamento(bean.getObjetoUbicacionAcomp().getUbicacionPadre().getNombre());
                }
            } else {
                bean.getObjeto().setAcompananteMunicipio(null);
                bean.getObjeto().setAcompananteDepartamento(null);
            }
            if (bean.getObjeto().getCntProfesionales().isGuardar()) {
                Maestro maestroProTipoDocumento = bean.getHashTiposDocumentoProfesional().get(bean.getObjeto().getCntProfesionales().getMaeTipoCodumentoId());
                bean.getObjeto().getCntProfesionales().setMaeTipoDocumentoCodigo(maestroProTipoDocumento.getValor());
                bean.getObjeto().getCntProfesionales().setMaeTipoDocumentoValor(maestroProTipoDocumento.getNombre());
                bean.auditoriaGuardar(bean.getObjeto().getCntProfesionales());
                bean.getObjeto().getCntProfesionales().setId(getCntProfesionalRemoto().insertar(bean.getObjeto().getCntProfesionales()));
                bean.getObjetoProfesionalPrestador().setActivo(true);
                bean.getObjetoProfesionalPrestador().setCntPrestador(new CntPrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()));
                bean.getObjetoProfesionalPrestador().setCntProfesionalesId(new CntProfesional(bean.getObjeto().getCntProfesionales().getId()));
                bean.auditoriaGuardar(bean.getObjetoProfesionalPrestador());
                getCntPrestadorProfesionalRemoto().insertar(bean.getObjetoProfesionalPrestador());
            } else {
                if (bean.getObjeto().getCntProfesionales() != null && bean.getObjeto().getCntProfesionales().getId() != null) {
                    if (getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(bean.getObjeto().getCntProfesionales().getId(), bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()) == null) {
                        CntProfesionalPrestador cntProfesionalPrestador = new CntProfesionalPrestador();
                        cntProfesionalPrestador.setActivo(true);
                        cntProfesionalPrestador.setCntPrestador(new CntPrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()));
                        cntProfesionalPrestador.setCntProfesionalesId(new CntProfesional(bean.getObjeto().getCntProfesionales().getId()));
                        cntProfesionalPrestador.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                        cntProfesionalPrestador.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                        cntProfesionalPrestador.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                        bean.auditoriaGuardar(cntProfesionalPrestador);
                        getCntPrestadorProfesionalRemoto().insertar(cntProfesionalPrestador);
                    }
                }

            }
            if (bean.getObjeto().getMaeAcompananteTipoDocumentoId() != null) {
                Maestro maestroTipoDocumento = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeAcompananteTipoDocumentoId());
                bean.getObjeto().setMaeAcompananteTipoDocumentoCodigo(maestroTipoDocumento.getValor());
                bean.getObjeto().setMaeAcompananteTipoDocumentoValor(maestroTipoDocumento.getNombre());
            }
            bean.getObjeto().setFechaHoraRegistro(new Date());
            bean.getObjeto().setFechaHoraSolicitud(new Date());
            Empresa empresa = getEmpresaRemoto().consultarPorPrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId());
            if (empresa != null) {
                bean.getObjeto().setGnEmpresa(empresa);
            } else {
                bean.getObjeto().setGnEmpresa(null);
            }
            Maestro maestro = bean.getHashCanalComunicacion().get(bean.getObjeto().getMaeCanalComunicacionId());
            bean.getObjeto().setMaeCanalComunicacionCodigo(maestro.getValor());
            bean.getObjeto().setMaeCanalComunicacionId(maestro.getId());
            bean.getObjeto().setMaeCanalComunicacionValor(maestro.getNombre());
            Maestro maestroEstadoSol;
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_ADMITIDA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_ADMITIDA);
                bean.getObjeto().setDiagnosticoEmergente(false);
            } else {
                maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_PREADMITIDA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_PREADMITIDA);
                if (bean.getObjeto().isDiagnosticoEmergente()) {
                    bean.addMensaje("Aplica Diagnostico Emergente.");
                }
            }

            //Cargar RefAnexo9
            bean.getObjeto().setMaeEstadoId(maestroEstadoSol.getId());
            bean.getObjeto().setMaeEstadoCodigo(maestroEstadoSol.getValor());
            bean.getObjeto().setMaeEstadoValor(maestroEstadoSol.getNombre());
            bean.getObjeto().setEstado(Integer.parseInt(maestroEstadoSol.getValor()));
            bean.getObjeto().setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
            bean.getObjeto().setMaEspecialidadesId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
            bean.getObjeto().setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
            bean.auditoriaGuardar(bean.getObjeto());
            //Cargar RefAnexo9DatoClinico
            if (bean.getObjectoTriage() != null && bean.getObjectoTriage().getId() != null) {
                bean.getObjetoDatosClinicos().setTriage(bean.getObjectoTriage().getId());
            }
            //Cargar RefAnexo9Estado
            RefAnexo9Estado anexo9Estado = new RefAnexo9Estado();
            if (maestroEstadoSol != null) {
                anexo9Estado.setEstado(maestroEstadoSol.getId());
                anexo9Estado.setMaeEstadoId(maestroEstadoSol.getId());
                anexo9Estado.setMaeEstadoCodigo(maestroEstadoSol.getValor());
                anexo9Estado.setMaeEstadoValor(maestroEstadoSol.getNombre());
            }
            bean.auditoriaGuardar(anexo9Estado);
            Maestro maestroAislamiento = bean.getHashTipoAislamiento().get(bean.getObjeto().getMaeTipoAislamientoId());
            if(maestroAislamiento != null){
                bean.getObjeto().setMaeTipoAislamientoCodigo(maestroAislamiento.getValor());
                bean.getObjeto().setMaeTipoAislamientoValor(maestroAislamiento.getNombre());
                bean.getObjeto().setMaeTipoAislamientoTipo(maestroAislamiento.getTipo());
            }
            
            //Guardar RefAnexo9
            bean.getObjeto().setFuenteOrigen(RefAnexo9.FUENTE_ORIGEN_MANUAL);
            bean.getObjeto().setId(getRefAnexo9Remoto().insertar(bean.getObjeto()));
            bean.getObjeto().setNumeroSolicitud(bean.getObjeto().getId() + "");
            
            //Guardar RefAnexo9DatoClinico
            try {
                bean.getObjetoDatosClinicos().setFechaHoraDatos(new Date());
                bean.auditoriaGuardar(bean.getObjetoDatosClinicos());
                bean.getObjetoDatosClinicos().setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                bean.getObjetoDatosClinicos().setId(getRefAnexo9DatoClinicoRemoto().insertar(bean.getObjetoDatosClinicos()));
            } catch (Exception e) {
                try {
                    bean.getObjetoDatosClinicos().setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                    bean.getObjetoDatosClinicos().setId(getRefAnexo9DatoClinicoRemoto().insertar(bean.getObjetoDatosClinicos()));
                } catch (Exception e2) {
                    bean.addError(" Datos clinicos: " + e2.getMessage());
                    Log.getInstance().error("CREAR REFERECIA Datos clinicos ", bean.getObjetoDatosClinicos().toString(), e2);
                }
            }
            bean.getObjeto().setRefAnexo9DatoClinico(bean.getObjetoDatosClinicos());
            //Guardar Diagnosticos
            for (RefAnexo9Diagnostico diagnostico : bean.getObjeto().getListaRefAnexo9Diagnostico()) {
                diagnostico.setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                bean.auditoriaGuardar(diagnostico);
                diagnostico.setId(getRefAnexo9DiagnosticoRemoto().insertar(diagnostico));
            }

            //Guardar RefAnexo9Estado            
            anexo9Estado.setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
            anexo9Estado.setId(getRefAnexo9EstadoRemoto().insertar(anexo9Estado));
            bean.getObjeto().setRefAnexo9Estado(anexo9Estado);
            //Guardar RefAnexos9Adjuntos 
            if (!bean.getListaRefAnexo9Adjuntos().isEmpty()) {
                guardarAdjunto(bean);
                bean.getObjeto().setListaRefAnexo9Adjunto(bean.getListaRefAnexo9Adjuntos());
            }
            bean.addMensaje("El registro se creo correctamente con número de solicitud " + bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
            Log.getInstance().error("CREAR REFERECIA", bean.getObjeto().toString(), e);
        }
    }

    private void editar(ReferenciaContraRefBean bean) {
        try {
            bean.setObjeto(getRefAnexo9Remoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.getObjeto().setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getCntPrestadorSede().getId()));
            bean.getObjeto().getCntPrestadorSede().setCntPrestador(getCntPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()));
            bean.getObjeto().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjeto().getAsegAfiliado().getFechaNacimiento()));
            bean.getObjeto().getAsegAfiliado().setPrimariaPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getId()));

            bean.getObjeto().setListaRefAnexo9Diagnostico(getRefAnexo9DiagnosticoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));
            bean.setListaRefAnexo9Adjuntos(getRefAnexo9AdjuntoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));
            for (RefAnexo9Adjunto adjunto : bean.getListaRefAnexo9Adjuntos()) {
                int indiceExtension = adjunto.getNombreArchivo().lastIndexOf(".") + 1;
                adjunto.setExtension(adjunto.getNombreArchivo().substring(indiceExtension, adjunto.getNombreArchivo().length()));
                adjunto.setNombre(adjunto.getNombreArchivo().substring(0, indiceExtension));
            }
            bean.getObjeto().setCntProfesionales(getCntProfesionalRemoto().consultar(bean.getObjeto().getCntProfesionales().getId()));
            if (bean.getObjeto().getCntProfesionales() != null) {
                List<CntProfesionalPrestador> listaCntProfesionalPrestador = getCntPrestadorProfesionalRemoto().consultarPorProfesional(bean.getObjeto().getCntProfesionales().getId());
                for (CntProfesionalPrestador cntProfesionalPrestador : listaCntProfesionalPrestador) {
                    bean.setObjetoProfesionalPrestador(cntProfesionalPrestador);
                    break;
                }
            }

            bean.setObjetoDatosClinicos(getRefAnexo9DatoClinicoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));
            if (bean.getObjetoDatosClinicos() == null) {
                bean.setObjetoDatosClinicos(new RefAnexo9DatoClinico());
                bean.setExisteDatoClinico(false);
            } else {
                bean.setExisteDatoClinico(true);
            }
            bean.setObjectoTriage(new RefAnexo9TriageDTO());
            for (RefAnexo9TriageDTO refAnexo9TriageDTO : bean.getListaTriage()) {
                if (refAnexo9TriageDTO.getId().intValue() == bean.getObjetoDatosClinicos().getTriage()) {
                    bean.setObjectoTriage(refAnexo9TriageDTO);
                }
            }
            //habilitar campo CUPS
            bean.getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(true);
            if(bean.getObjeto().getMaServicioSolicitaCodigo() != null){
                if(bean.getObjeto().getMaServicioSolicitaCodigo().equals(String.valueOf(CODIGO_SERVICIOS_HOBILITACION))){
                    bean.getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(false);
                }
            }
            
            //habilitar campo MaeMaternoPerinata
            bean.getObjeto().setHabilitarMaeMaternoPerinatalId(true);
            if(bean.getObjeto().getMaServicioSolicitaCodigo() != null){
                if(bean.getObjeto().getMaServicioSolicitaCodigo().equals(String.valueOf(ReferenciaContraRefBean.CODIGO_SERVICIOS_GINECOBOSTETRICIA))){
                    bean.getObjeto().setHabilitarMaeMaternoPerinatalId(false);
                }
            }
            
            //llena toda la lista
            bean.setListaTipoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_GESTION_TIPO));
            bean.setDiagnosticosBorrar(new ArrayList());
            contactosAfiliado(bean);
            bean.setListaAfiliadoPrograma(getPeAfiliadosProgramaRemoto().consultarAfiliadoActivo(bean.getObjeto().getAsegAfiliado().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ReferenciaContraRefBean bean) {
        try {
            boolean validar = true;
            Maestro maeCausaExterna = bean.getHashCausaExterna().get(bean.getObjeto().getMaeCausaExternaId());
            if (maeCausaExterna != null) {
                bean.getObjeto().setMaeCausaExternaCodigo(maeCausaExterna.getValor());
                bean.getObjeto().setMaeCausaExternaValor(maeCausaExterna.getNombre());
            }

            Maestro maeCondicionDestino = bean.getHashCondicionDestino().get(bean.getObjeto().getMaeCondicionDestinoId());
            if (maeCondicionDestino != null) {
                bean.getObjeto().setMaeCondicionDestinoCodigo(maeCondicionDestino.getValor());
                bean.getObjeto().setMaeCondicionDestinoValor(maeCondicionDestino.getNombre());
            }

            Maestro maeTipoAtencion = bean.getHashTipoAtencion().get(bean.getObjeto().getMaeTipoAtencionId());
            if (maeTipoAtencion != null) {
                bean.getObjeto().setMaeTipoAtencionCodigo(maeTipoAtencion.getValor());
                bean.getObjeto().setMaeTipoAtencionValor(maeTipoAtencion.getNombre());
            }

            Maestro maeUbicacion = bean.getHashGrupoServicio().get(bean.getObjeto().getMaeUbicacionId());
            if (maeUbicacion != null) {
                bean.getObjeto().setMaeUbicacionCodigo(maeUbicacion.getValor());
                bean.getObjeto().setMaeUbicacionValor(maeUbicacion.getNombre());
            }

            Maestro maeModalidadTecnologia = bean.getHashModalidadTecnologia().get(bean.getObjeto().getMaeModalidadTecnologiaId());
            if (maeModalidadTecnologia != null) {
                bean.getObjeto().setMaeModalidadTecnologiaCodigo(maeModalidadTecnologia.getValor());
                bean.getObjeto().setMaeModalidadTecnologiaValor(maeModalidadTecnologia.getNombre());
            }
            
            Maestro maeTipoAislamiento = bean.getHashTipoAislamiento().get(bean.getObjeto().getMaeTipoAislamientoId());
            if (maeTipoAislamiento != null) {
                bean.getObjeto().setMaeTipoAislamientoCodigo(maeTipoAislamiento.getValor());
                bean.getObjeto().setMaeTipoAislamientoValor(maeTipoAislamiento.getNombre());
                bean.getObjeto().setMaeTipoAislamientoTipo(maeTipoAislamiento.getTipo());
            }
            
            Maestro maeMaternoPerinata = bean.getHashMaternoPerinata().get(bean.getObjeto().getMaeMaternoPerinatalId());
            if (maeMaternoPerinata != null) {
                bean.getObjeto().setMaeMaternoPerinatalCodigo(maeMaternoPerinata.getValor());
                bean.getObjeto().setMaeMaternoPerinatalValor(maeMaternoPerinata.getNombre());
                bean.getObjeto().setMaeMaternoPerinatalTipo(maeMaternoPerinata.getTipo());
            }
            
            if(!bean.getObjeto().isHabilitarCodigoCUPSprocedimientoRequerido()){
                if(bean.getObjeto().getMaTecnologiaId() == null){
                    bean.addError("Codigo CUPS del procedimiento requerido: Error de validación: se necesita un valor.");
                    validar = false;
                }
            }
            
            if ((bean.getObjeto().getMaeAcompananteTipoDocumentoId() != null && bean.getObjeto().getMaeAcompananteTipoDocumentoId() != 0)
                    || (bean.getObjeto().getAcompananteDocumento() != null && !bean.getObjeto().getAcompananteDocumento().isBlank())
                    || (bean.getObjeto().getAcompanantePrimerNombre() != null && !bean.getObjeto().getAcompanantePrimerNombre().isBlank())
                    || (bean.getObjeto().getAcompanantePrimerApellido() != null && !bean.getObjeto().getAcompanantePrimerApellido().isBlank())) {
                if (bean.getObjeto().getMaeAcompananteTipoDocumentoId() == null || bean.getObjeto().getMaeAcompananteTipoDocumentoId() == 0) {
                    validar = false;
                }
                if (bean.getObjeto().getAcompananteDocumento() == null
                        || bean.getObjeto().getAcompananteDocumento().isBlank()) {
                    validar = false;
                }
                if (bean.getObjeto().getAcompanantePrimerNombre() == null
                        || bean.getObjeto().getAcompanantePrimerNombre().isBlank()) {
                    validar = false;
                }
                if (bean.getObjeto().getAcompanantePrimerApellido() == null
                        || bean.getObjeto().getAcompanantePrimerApellido().isBlank()) {
                    validar = false;
                }
            }

            if (bean.getObjetoDatosClinicos().getEscalaGlasgow() != null) {
                if (bean.getObjetoDatosClinicos().getEscalaGlasgow() < 3 || bean.getObjetoDatosClinicos().getEscalaGlasgow() > 15) {
                    validar = false;
                }
            }

            if (bean.getObjeto().getCntProfesionales().isGuardar()) {
                if (bean.getObjeto().getCntProfesionales().isGuardar() && (bean.getObjetoProfesionalPrestador() == null || bean.getObjetoProfesionalPrestador().getMaEspecialidadId() == 0)) {
                    validar = false;
                }
                if (bean.getObjeto().getCntProfesionales().isGuardar()
                        && (bean.getObjeto().getCntProfesionales().getPrimerNombre() == null || bean.getObjeto().getCntProfesionales().getPrimerNombre().isBlank())) {
                    validar = false;
                }
                if (bean.getObjeto().getCntProfesionales().isGuardar()
                        && (bean.getObjeto().getCntProfesionales().getPrimerApellido() == null || bean.getObjeto().getCntProfesionales().getPrimerApellido().isBlank())) {
                    validar = false;
                }
                if (bean.getObjeto().getCntProfesionales().isGuardar()
                        && (bean.getObjeto().getProfesionalSolicitaTelefono() == null || bean.getObjeto().getProfesionalSolicitaTelefono().isBlank())) {
                    validar = false;
                }
            }
            if (bean.getObjeto().getAsegAfiliado() == null || bean.getObjeto().getAsegAfiliado().getId() == null || bean.getObjeto().getAsegAfiliado().getId() == 0) {
                validar = false;
            }
            if (bean.getObjeto().getCntPrestadorSede() == null || bean.getObjeto().getCntPrestadorSede().getId() == null || bean.getObjeto().getCntPrestadorSede().getId() == 0) {
                validar = false;
            }
            if (bean.getObjeto().getMaServicioSolicitaId() == 0) {
                validar = false;
            }
            boolean noPrincipal = true;
            for (RefAnexo9Diagnostico refAnexo9Diagnostico : bean.getObjeto().getListaRefAnexo9Diagnostico()) {
                if (refAnexo9Diagnostico.isPrincipal()) {
                    noPrincipal = false;
                }
            }
            if (bean.getObjeto().getListaRefAnexo9Diagnostico().isEmpty()) {
                validar = false;
            }
            if (noPrincipal) {
                validar = false;
            }
            if (!validar) {
                bean.addError("La solicitud no posee la informacion requerida por lo tanto no puede ser editable ");
                return;
            }
            //Modificar Diagnosticos
            for (RefAnexo9Diagnostico diagnostico : bean.getObjeto().getListaRefAnexo9Diagnostico()) {
                if (diagnostico.getId() == null || diagnostico.getId() == 0) {
                    diagnostico.setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setId(getRefAnexo9DiagnosticoRemoto().insertar(diagnostico));
                } else {
                    bean.auditoriaModificar(diagnostico);
                    getRefAnexo9DiagnosticoRemoto().actualizar(diagnostico);
                }
            }
            if (Objects.equals(bean.getObjeto().getMaeEstadoCodigo(), RefAnexo9Estado.ESTADO_PREADMITIDA_CODIGO)) {
            //if (Objects.equals(bean.getObjeto().getMaeEstadoId(), RefAnexo9Estado.ESTADO_PREADMITIDA)) {
                if (verificarDiagnosticoEmergente(bean)) {
                    bean.getObjeto().setDiagnosticoEmergente(true);
                    bean.addMensaje("Aplica Diagnostico Emergente.");
                } else {
                    bean.getObjeto().setDiagnosticoEmergente(false);
                }
            }
            //Modificar RefAnexo9
            bean.auditoriaModificar(bean.getObjeto());
            getRefAnexo9Remoto().actualizar(bean.getObjeto());

            //Guardar RefAnexo9DatoClinico
            if (bean.getObjectoTriage() != null && bean.getObjectoTriage().getId() != null) {
                bean.getObjetoDatosClinicos().setTriage(bean.getObjectoTriage().getId());
            }
            if (!bean.isExisteDatoClinico()) {
                bean.getObjetoDatosClinicos().setFechaHoraDatos(new Date());
                bean.auditoriaGuardar(bean.getObjetoDatosClinicos());
                bean.getObjetoDatosClinicos().setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                bean.getObjetoDatosClinicos().setId(getRefAnexo9DatoClinicoRemoto().insertar(bean.getObjetoDatosClinicos()));
            } else {
                bean.auditoriaModificar(bean.getObjetoDatosClinicos());
                getRefAnexo9DatoClinicoRemoto().actualizar(bean.getObjetoDatosClinicos());
            }

            //Modificar RefAnexos9Adjuntos   
            boolean hayAdjuntosGuardar = false;
            for (RefAnexo9Adjunto refAnexo9Adjunto : bean.getListaRefAnexo9Adjuntos()) {
                if (refAnexo9Adjunto.getId() == null) {
                    hayAdjuntosGuardar = true;
                }
            }
            for (RefAnexo9Diagnostico diagnostico : bean.getDiagnosticosBorrar()) {
                getRefAnexo9DiagnosticoRemoto().eliminar(diagnostico.getId());
            }

            if (hayAdjuntosGuardar) {
                guardarAdjunto(bean);
            }
            bean.setExisteDatoClinico(true);
            bean.addMensaje("El registro se modificó correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public boolean verificarDiagnosticoEmergente(ReferenciaContraRefBean bean) {
        boolean aplica = false;
        try {
            for (RefAnexo9Diagnostico diagnostico : bean.getObjeto().getListaRefAnexo9Diagnostico()) {
                MaDiagnostico diag = getMaDiagnosticoRemoto().consultar(diagnostico.getMaDiagnosticosId());
                if (diag.getPriorizarCrue() != null && diag.getPriorizarCrue()) {
                    aplica = true;
                    break;
                }
            }
        } catch (Exception e) {
        }
        return aplica;
    }

    private void borrar(ReferenciaContraRefBean bean) {
        try {
            //#PEND verificar el borrado 
            bean.setObjeto(getRefAnexo9Remoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarAdjunto(ReferenciaContraRefBean bean) {
        try {
            boolean error = false;
            boolean hayAdjuntos = true;
            String ruta = PropApl.getInstance().get(PropApl.REF_RUTA_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los adjuntos");
                error = true;
            }

            for (RefAnexo9Adjunto refAnexo9Adjunto : bean.getListaRefAnexo9Adjuntos()) {
                if (refAnexo9Adjunto.getId() == null) {
                    hayAdjuntos = false;
                }
            }

            if (hayAdjuntos) {
                bean.addError("No hay adjuntos para guardar");
            }

            if (error || hayAdjuntos) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
//            String tipoDocumento = bean.getHashTiposDocumentoPersona().get(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumento()).getValor();
            String tipoDocumento = bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo();

            for (RefAnexo9Adjunto refAnexo9Adjunto : bean.getListaRefAnexo9Adjuntos()) {
                if (refAnexo9Adjunto.getId() == null) {
                    nombreArchivo = new StringBuilder();
                    nombreArchivo.append(tipoDocumento)
                            .append(bean.getObjeto().getAsegAfiliado().getNumeroDocumento())
                            .append("_")
                            .append(sdf.format(new Date()));
                    nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                    bean.auditoriaGuardar(refAnexo9Adjunto);
                    refAnexo9Adjunto.setArchivo(nombreArchivo.append(".").append(refAnexo9Adjunto.getExtension()).toString());
                    Maestro maeTipoAdjunto = getMaestroRemoto().consultar(refAnexo9Adjunto.getMaeTipoArchivoId());
                    refAnexo9Adjunto.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
                    refAnexo9Adjunto.setMaeTipoArchivoId(maeTipoAdjunto.getId());
                    refAnexo9Adjunto.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
                    refAnexo9Adjunto.setNombreArchivo(refAnexo9Adjunto.getNombreArchivo());
                    refAnexo9Adjunto.setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                    refAnexo9Adjunto.setExiste(1);
                    refAnexo9Adjunto.setRuta(ruta);
                    File archivo = new File(ruta, refAnexo9Adjunto.getArchivo());
                    //Se remueve por fallo en el condicional siempre entra en falso
//                    if (!archivo.isDirectory()) {
//                        bean.addError("No existe la ruta " + ruta +  "  para guardar el adjunto");
//                        return;
//                    }
                    OutputStream destino = new FileOutputStream(archivo);
                    IOUtils.copy(refAnexo9Adjunto.getAdjuntoStream(), destino);
                    IOUtils.closeQuietly(refAnexo9Adjunto.getAdjuntoStream());
                    IOUtils.closeQuietly(destino);
                    bean.auditoriaGuardar(refAnexo9Adjunto);
                    refAnexo9Adjunto.setAdjuntoStream(null);
                    refAnexo9Adjunto.setId(getRefAnexo9AdjuntoRemoto().insertar(refAnexo9Adjunto));
                    Maestro estadoAdjunto = getMaestroRemoto().consultar(maeTipoAdjunto.getId());
                    if (estadoAdjunto != null) {
                        if (estadoAdjunto.contieneAccion(MaestroAccion.CR_ADJUNTO_TIPO_SEMAFORO_ADJUNTO)) {
                            bean.getObjeto().setFechaHoraAdjuntoEvolucion(new Date());
                            getRefAnexo9Remoto().actualizarUltimaEvolucion(bean.getObjeto());
                        }
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarGestionar(ReferenciaContraRefBean bean) {
        try {
            bean.getParamConsulta(3).setCantidadRegistros(getRefAnexo9GestionaRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setRegistrosRefAnexos9Gestion(getRefAnexo9GestionaRemoto().consultarLista(bean.getParamConsulta(3)));
            for (RefAnexo9Gestion refAnexo9Gestion : bean.getRegistrosRefAnexos9Gestion()) {
                refAnexo9Gestion.setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(refAnexo9Gestion.getCntPrestadorSede().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearGestionar(ReferenciaContraRefBean bean) {
        try {
            bean.setObjetoGestion(new RefAnexo9Gestion());
            bean.getObjetoGestion().setCntPrestadorSede(new CntPrestadorSede());
            bean.setObjetoAdjunto(new RefAnexo9Adjunto());
            bean.setInhabilitarMotivo(false);
            this.verGestionar(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarGestionar(ReferenciaContraRefBean bean) {
        try {
            bean.limpiarMensajes();

            if (!getRefAnexo9GestionaRemoto().consultarPorRefAnexo9YEstado(bean.getObjeto().getId(), RefAnexo9Gestion.ESTADO_GESTION_TIPO, RefAnexo9Gestion.ESTADO_CERRADA_CODIGO).isEmpty()) {
                bean.addError("La solicitud N° " + bean.getObjeto().getId() + " tiene una gestión en estado Cerrada, no se puede gestionar.");
                bean.setGestionar(false);
                return;
            }
            
            Maestro maestroGestion = getMaestroRemoto().consultar(bean.getObjetoGestion().getMaeTipoId());
            if(maestroGestion != null){
                if (maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)
                    && bean.getObjetoDatosClinicos() == null) {
                    bean.addError("La solicitud N° " + bean.getObjeto().getId() + " no tiene datos clinicos.");
                    bean.setGestionar(false);
                    return;
                }
                if(maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CANCELADA_CODIGO)
                    ||  maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_ANULADA_CODIGO)
                    || maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO) ){
                    bean.getObjeto().setFechaHoraFinGestion(new Date());
                    bean.getObjeto().setDiasGestion(bean.getObjeto().calcularDiasGestion());
                    getRefAnexo9Remoto().actualizarFechaHoraFinGestion(bean.getObjeto());
                }
                /*if(bean.getObjetoGestion().getMaeTipoId() == RefAnexo9Gestion.ESTADO_CANCELADA 
                        || bean.getObjetoGestion().getMaeTipoId() == RefAnexo9Gestion.ESTADO_ANULADA
                    || bean.getObjetoGestion().getMaeTipoId() == RefAnexo9Gestion.ESTADO_CERRADA){
                    bean.getObjeto().setFechaHoraFinGestion(new Date());
                    bean.getObjeto().setDiasGestion(bean.getObjeto().calcularDiasGestion());
                    getRefAnexo9Remoto().actualizarFechaHoraFinGestion(bean.getObjeto());
                }*/
                if( !maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CANCELADA_CODIGO)
                    || !maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_ANULADA_CODIGO) 
                    || !maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)){
                /*if (bean.getObjetoGestion().getMaeTipoId() != RefAnexo9Gestion.ESTADO_CANCELADA
                        && bean.getObjetoGestion().getMaeTipoId() != RefAnexo9Gestion.ESTADO_ANULADA
                        && bean.getObjetoGestion().getMaeTipoId() != RefAnexo9Gestion.ESTADO_CERRADA) {*/
                    boolean error = true;

                    if (bean.getObjetoGestion().getCntPrestadorSede() == null || bean.getObjetoGestion().getCntPrestadorSede().getId() == null || bean.getObjetoGestion().getCntPrestadorSede().getId() == 0) {
                        bean.addError("Prestador IPS: Error de validación: se necesita un valor.");
                        error = false;
                    }

                    if (bean.getObjetoGestion().getObservacion().isBlank()) {
                        bean.addError("Descripción: Error de validación: se necesita un valor.");
                        error = false;
                    }

                    if(maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_GESTION_DE_REGULACION_CODIGO)
                            && getRefAnexo9GestionaRemoto().consultarPorRefAnexo9GestionRegulacion(bean.getObjeto().getId()) == null){
                        bean.getObjeto().setFechaHoraInicioGestion(new Date());
                        getRefAnexo9Remoto().actualizarFechaHoraInicioGestion(bean.getObjeto());
                    }

                    if (maestroGestion.contieneAccion(MaestroAccion.CR_GESTION_TIPO_SEMAFORO_GESTION_TIPO)) {
                        bean.getObjeto().setFechaHoraUltimaGestion(new Date());
                        getRefAnexo9Remoto().actualizarUltimaGestion(bean.getObjeto());
                    }

                    if (!error) {
                        return;
                    }

                    boolean validar = true;
                    if ((bean.getObjeto().getMaeAcompananteTipoDocumentoId() != null && bean.getObjeto().getMaeAcompananteTipoDocumentoId() != 0)
                            || (bean.getObjeto().getAcompananteDocumento() != null && !bean.getObjeto().getAcompananteDocumento().isBlank())
                            || (bean.getObjeto().getAcompanantePrimerNombre() != null && !bean.getObjeto().getAcompanantePrimerNombre().isBlank())
                            || (bean.getObjeto().getAcompanantePrimerApellido() != null && !bean.getObjeto().getAcompanantePrimerApellido().isBlank())) {
                        if (bean.getObjeto().getMaeAcompananteTipoDocumentoId() == null || bean.getObjeto().getMaeAcompananteTipoDocumentoId() == 0) {
                            validar = false;
                        }
                        if (bean.getObjeto().getAcompananteDocumento() == null
                                || bean.getObjeto().getAcompananteDocumento().isBlank()) {
                            validar = false;
                        }
                        if (bean.getObjeto().getAcompanantePrimerNombre() == null
                                || bean.getObjeto().getAcompanantePrimerNombre().isBlank()) {
                            validar = false;
                        }
                        if (bean.getObjeto().getAcompanantePrimerApellido() == null
                                || bean.getObjeto().getAcompanantePrimerApellido().isBlank()) {
                            validar = false;
                        }
                    }

                    if (bean.getObjetoDatosClinicos() != null && bean.getObjetoDatosClinicos().getEscalaGlasgow() != null) {
                        if (bean.getObjetoDatosClinicos().getEscalaGlasgow() < 3 || bean.getObjetoDatosClinicos().getEscalaGlasgow() > 15) {
                            validar = false;
                        }
                    }

                    if (bean.getObjeto().getCntProfesionales() != null) {
                        if (bean.getObjeto().getCntProfesionales().isGuardar()) {
                            if (bean.getObjeto().getCntProfesionales().isGuardar() && (bean.getObjetoProfesionalPrestador() == null || bean.getObjetoProfesionalPrestador().getMaEspecialidadId() == 0)) {
                                validar = false;
                            }
                            if (bean.getObjeto().getCntProfesionales().isGuardar()
                                    && (bean.getObjeto().getCntProfesionales().getPrimerNombre() == null || bean.getObjeto().getCntProfesionales().getPrimerNombre().isBlank())) {
                                validar = false;
                            }
                            if (bean.getObjeto().getCntProfesionales().isGuardar()
                                    && (bean.getObjeto().getCntProfesionales().getPrimerApellido() == null || bean.getObjeto().getCntProfesionales().getPrimerApellido().isBlank())) {
                                validar = false;
                            }
                            if (bean.getObjeto().getCntProfesionales().isGuardar()
                                    && (bean.getObjeto().getProfesionalSolicitaTelefono() == null || bean.getObjeto().getProfesionalSolicitaTelefono().isBlank())) {
                                validar = false;
                            }
                        }
                    }
                    if (bean.getObjeto().getAsegAfiliado() == null || bean.getObjeto().getAsegAfiliado().getId() == null || bean.getObjeto().getAsegAfiliado().getId() == 0) {
                        validar = false;
                    }
                    if (bean.getObjeto().getCntPrestadorSede() == null || bean.getObjeto().getCntPrestadorSede().getId() == null || bean.getObjeto().getCntPrestadorSede().getId() == 0) {
                        validar = false;
                    }
                    if (bean.getObjeto().getListaRefAnexo9Diagnostico().isEmpty()) {
                        validar = false;
                    }
                    if (bean.getObjeto().getMaServicioSolicitaId() == 0) {
                        validar = false;
                    }
                    boolean noPrincipal = true;
                    for (RefAnexo9Diagnostico refAnexo9Diagnostico : bean.getObjeto().getListaRefAnexo9Diagnostico()) {
                        if (refAnexo9Diagnostico.isPrincipal()) {
                            noPrincipal = false;
                        }
                    }
                    if (noPrincipal) {
                        validar = false;
                    }
                    if (!validar) {
                        bean.addError("La solicitud no posee la informacion requerida por lo tanto no puede ser editable ");
                        return;
                    }
                }
                
                bean.getObjetoGestion().setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                Maestro maeTipo = bean.getHashTipoGestion().get(bean.getObjetoGestion().getMaeTipoId());
                if(maeTipo != null){
                    bean.getObjetoGestion().setMaeTipoCodigo(maeTipo.getValor());
                    bean.getObjetoGestion().setMaeTipoValor(maeTipo.getNombre());
                }
                Maestro maeMotivo = bean.getListaGestionarMotivos().stream()
                        .filter(motivo -> Objects.equals(motivo.getId(), bean.getObjetoGestion().getMaeMotivoId()))
                        .findFirst().get();
    //            Maestro maeMotivo = bean.getHashMotivoGestion().get(bean.getObjetoGestion().getMaeMotivoId());
                bean.getObjetoGestion().setMaeMotivoCodigo(maeMotivo.getValor());
                bean.getObjetoGestion().setMaeMotivoValor(maeMotivo.getNombre());
                if (bean.getConexion().getEmpresa().isAdministradora()) {
                    bean.getObjetoGestion().setOrigen(RefAnexo9Gestion.ORIGEN_EPS);
                } else {
                    bean.getObjetoGestion().setOrigen(RefAnexo9Gestion.ORIGEN_IPS);
                }
                bean.auditoriaGuardar(bean.getObjetoGestion());
                bean.getObjetoGestion().setId(getRefAnexo9GestionaRemoto().insertar(bean.getObjetoGestion()));
                Maestro maestroEstadoSol = new Maestro();

                boolean cambiarEstado = false, actualizar_ips = false;
                if (maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)) {
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_CERRADA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_CERRADA);
                    cambiarEstado = true;
                    bean.setGestionar(false);
                    actualizar_ips = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_ADMITIDA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_ADMITIDA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_ADMITIDA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_ANULADA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_ANULADA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_ANULADA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CANCELADA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_CANCELADA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_CANCELADA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_DIRECCIONA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_DIRECCIONA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_DIRECCIONA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_PREADMITIDA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_PREADMITIDA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_PREADMITIDA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_RECHAZADA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_RECHAZADA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_RECHAZADA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_GESTION_DE_REGULACION_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_GESTION_DE_REGULACION_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_GESTION_DE_REGULACION);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_AUDITORIA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_AUDITORIA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_AUDITORIA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_PERTINENCIA_MEDICA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_PERTINENCIA_MEDICA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_PERTINENCIA_MEDICA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_GESTION_TELEASISTENCIA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_GESTION_TELEASISTENCIA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_GESTION_TELEASISTENCIA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CONCEPTO_TELEASISTENCIA_CODIGO)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Estado.ESTADO_CONCEPTO_TELEASISTENCIA_CODIGO, RefAnexo9Estado.ESTADO_TIPO);
                    //maestroEstadoSol = bean.getHashEstadoSolicitud().get(RefAnexo9Estado.ESTADO_CONCEPTO_TELEASISTENCIA);
                    cambiarEstado = true;
                }else if(maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_CAMA_FIJA)){
                    maestroEstadoSol = getMaestroRemoto().consultarPorValorTipo(RefAnexo9Gestion.ESTADO_CAMA_FIJA, RefAnexo9Estado.ESTADO_TIPO);
                    cambiarEstado = true;
                }
                if (cambiarEstado) {
                    //Guardar estado RefAnexo9
                    RefAnexo9Estado anexo9Estado = new RefAnexo9Estado();
                    anexo9Estado.setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
                    anexo9Estado.setEstado(maestroEstadoSol.getId());
                    anexo9Estado.setMaeEstadoId(maestroEstadoSol.getId());
                    anexo9Estado.setMaeEstadoCodigo(maestroEstadoSol.getValor());
                    anexo9Estado.setMaeEstadoValor(maestroEstadoSol.getNombre());
                    anexo9Estado.setMaeMotivoId(maeMotivo.getId());
                    anexo9Estado.setMaeMotivoCodigo(maeMotivo.getValor());
                    anexo9Estado.setMaeMotivoValor(maeMotivo.getNombre());
                    bean.auditoriaGuardar(anexo9Estado);
                    getRefAnexo9EstadoRemoto().insertar(anexo9Estado);
                    //Guardar RefAnexos9
                    if (maestroGestion.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && !maestroGestion.getValor().equals(RefAnexo9Gestion.ESTADO_PREADMITIDA_CODIGO)) {
                        bean.getObjeto().setDiagnosticoEmergente(false);//ya no va order by emergente                    
                    }
                    bean.getObjeto().setMaeEstadoCodigo(maestroEstadoSol.getValor());
                    bean.getObjeto().setMaeEstadoId(maestroEstadoSol.getId());
                    bean.getObjeto().setMaeEstadoValor(maestroEstadoSol.getNombre());
                    bean.getObjeto().setEstado(Integer.parseInt(maestroEstadoSol.getValor()));
                    //Se actualiza el prestadorSedeUbicacion cuando el estado es cerrado segun ticket: 1094
                    if (actualizar_ips) {
                        bean.getObjeto().setCntPrestadorSedesUbicacion(bean.getObjetoGestion().getCntPrestadorSede());
                    }
    //                getRefAnexo9Remoto().actualizar(bean.getObjeto());
                    getRefAnexo9Remoto().actualizarEstado(bean.getObjeto());
                }
            }
            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearAdjuntoGestionar(ReferenciaContraRefBean bean) {
        try {
            bean.getObjeto().setListaRefAnexo9Adjunto(new ArrayList<>());
            bean.setObjetoAdjunto(new RefAnexo9Adjunto());
            bean.setNombreDocumento("");
            bean.setMostrarBorrarDocumento(false);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarAdjuntoGestionar(ReferenciaContraRefBean bean) {
        try {
            guardarAdjunto(bean);
            if (!bean.isError()) {
                bean.addMensaje("El registro se creo correctamente");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarAdjuntoReferenciaGestionar(ReferenciaContraRefBean bean) {
        try {
            RefAnexo9Adjunto obj = bean.getObjetoAdjunto();
            obj.setBorrado(1);
            bean.auditoriaModificar(obj);
            getRefAnexo9AdjuntoRemoto().borradoLogico(obj);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crearTransporte(ReferenciaContraRefBean bean) {
        try {
            bean.setObjetoTransporte(new RefTransporte());
            bean.getObjetoTransporte().setCntPrestadorSede(new CntPrestadorSede());
            bean.setMaeClaseTransporte(new Maestro());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarTransporte(ReferenciaContraRefBean bean) {
        try {
            bean.getParamConsulta(5).setCantidadRegistros(getRefTransporteRemoto().consultarCantidadLista(bean.getParamConsulta(5)));
            bean.setRegistrosRefTransportes(getRefTransporteRemoto().consultarLista(bean.getParamConsulta(5)));
            for (RefTransporte transporte : bean.getRegistrosRefTransportes()) {
                if (transporte.getCntPrestadorSede() != null) {
                    transporte.setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(transporte.getCntPrestadorSede().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void listarAdjuntos(ReferenciaContraRefBean bean) {
        try {
            bean.setListaRefAnexo9Adjuntos(getRefAnexo9AdjuntoRemoto().consultarPorRefAnexo9(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void consultarTransporte(ReferenciaContraRefBean bean) {
        try {
            bean.setRegistrosRefTransportes(getRefTransporteRemoto().consultarLista(bean.getParamConsulta(6)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public Maestro consultarMaestroTipoDocumento(ReferenciaContraRefBean bean) {
        Maestro tipoDocumento = null;
        try {
            tipoDocumento = getMaestroRemoto().consultarPorValorTipo("CC", MaestroTipo.GN_TIPO_DOCUMENTO);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return tipoDocumento;
    }
    
    @Override
    public Maestro consultarMaestroGestionEstado(int maeEstadoId, ReferenciaContraRefBean bean) {
        Maestro estado = null;
        try {
            if(maeEstadoId > 0){
                estado = getMaestroRemoto().consultar(maeEstadoId);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return estado;
    }
    
    private void guardarTransporte(ReferenciaContraRefBean bean) {
        try {
            boolean validar = true;
            if (bean.getObjetoTransporte().getMaeTipoTransporteId() == null || bean.getObjetoTransporte().getMaeTipoTransporteId() == 0) {
                bean.addError("Tipo Transporte: Error de validación: se necesita un valor.");
                validar = false;
            }

            if (bean.getMaeClaseTransporte().getId() == null || bean.getMaeClaseTransporte().getId() == 0) {
                bean.addError("Clase Transporte: Error de validación: se necesita un valor.");
                validar = false;
            }

            if ((bean.getObjetoTransporte().getCntPrestadorSede() == null || bean.getObjetoTransporte().getCntPrestadorSede().getId() == null
                    || bean.getObjetoTransporte().getCntPrestadorSede().getId() == 0)
                    && (bean.getMaeClaseTransporte().contieneAccion(MaestroAccion.REF_CLASE_TRANSPORTE_CON_PROVEEDOR))) {
                bean.addError("Proveedor: Error de validación: se necesita un valor.");
                validar = false;
            }

            if (bean.getObjetoTransporte().getObservacion().isBlank()) {
                bean.addError("Decripción: Error de validación: se necesita un valor.");
                validar = false;
            }

            if (bean.getObjetoTransporte().getFechaHoraOrigen() == null) {
                bean.addError("Fecha Hora Origen: Error de validación: se necesita un valor.");
                validar = false;
            }

            if (bean.getObjetoTransporte().getMaeTransporteLiquidacionId() == null || bean.getObjetoTransporte().getMaeTransporteLiquidacionId() == 0) {
                bean.addError("Gestión Transporte: Error de validación: se necesita un valor.");
                validar = false;
            }

            if (!validar) {
                return;
            }

            bean.getObjetoTransporte().setRefAnexo9(new RefAnexo9(bean.getObjeto().getId()));
            Maestro maeTipoTransporte = bean.getHashTipoTransporte().get(bean.getObjetoTransporte().getMaeTipoTransporteId());
            bean.getObjetoTransporte().setMaeTipoTransporteCodigo(maeTipoTransporte.getValor());
            bean.getObjetoTransporte().setMaeTipoTransporteValor(maeTipoTransporte.getNombre());
//            Maestro maeClaseTransporte = bean.getListaClaseTransporte().stream()
//                    .filter(clase -> Objects.equals(clase.getId(), bean.getObjetoTransporte().getMaeClaseTransporteId()))
//                    .findFirst().get();
            bean.getObjetoTransporte().setMaeClaseTransporteId(bean.getMaeClaseTransporte().getId());
            bean.getObjetoTransporte().setMaeClaseTransporteCodigo(bean.getMaeClaseTransporte().getValor());
            bean.getObjetoTransporte().setMaeClaseTransporteValor(bean.getMaeClaseTransporte().getNombre());
            Maestro maeTransporteLiquidacion = bean.getHashTransporteLiquidacion().get(bean.getObjetoTransporte().getMaeTransporteLiquidacionId());
            bean.getObjetoTransporte().setMaeTransporteLiquidacionCodigo(maeTransporteLiquidacion.getValor());
            bean.getObjetoTransporte().setMaeTransporteLiquidacionValor(maeTransporteLiquidacion.getNombre());
            bean.auditoriaGuardar(bean.getObjetoTransporte());
            bean.getObjetoTransporte().setId(getRefTransporteRemoto().insertar(bean.getObjetoTransporte()));
            RefTransporteInsumo refTransporteInsumo;
            bean.getObjetoTransporte().setListaRefTransporteInsumo(new ArrayList<>());
            for (Integer insumo : bean.getInsumosSeleccionados()) {
                refTransporteInsumo = new RefTransporteInsumo();
                Maestro insumoM = bean.getHashInsumoTransporte().get(insumo);
                refTransporteInsumo.setMaInsumoCodigo(insumoM.getValor());
                refTransporteInsumo.setMaInsumoId(insumoM.getId());
                refTransporteInsumo.setMaInsumoValor(insumoM.getNombre());
                refTransporteInsumo.setRefTransporte(new RefTransporte(bean.getObjetoTransporte().getId()));
                bean.auditoriaGuardar(refTransporteInsumo);
                refTransporteInsumo.setId(getRefTransporteInsumoRemoto().insertar(refTransporteInsumo));
                bean.getObjetoTransporte().getListaRefTransporteInsumo().add(refTransporteInsumo);
            }
            bean.addMensaje("El registro se creo correctamente");
            listarTransporte(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarSeguimiento(ReferenciaContraRefBean bean) {
        try {
            bean.getObjetoTransporte().setListaRefTransporteSeguimiento(getRefTransSeguimientoRemoto().consultarByRefTransporte(bean.getObjetoTransporte().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarSeguimiento(ReferenciaContraRefBean bean) {
        try {
            boolean validar = true;
            if (bean.getObjetoTransporteSeguimiento().getObservacion().isBlank()) {
                bean.addError("Decripción: Error de validación: se necesita un valor.");
                validar = false;
            }

            if (!validar) {
                return;
            }

            bean.getObjetoTransporteSeguimiento().setRefTransporte(new RefTransporte(bean.getObjetoTransporte().getId()));
            Maestro maeTipoSeguimiento = bean.getHashTipoSeguimiento().get(bean.getObjetoTransporteSeguimiento().getMaeTipoReporteId());
            bean.getObjetoTransporteSeguimiento().setMaeTipoReporteCodigo(maeTipoSeguimiento.getValor());
            bean.getObjetoTransporteSeguimiento().setMaeTipoReporteValor(maeTipoSeguimiento.getNombre());
            bean.auditoriaGuardar(bean.getObjetoTransporteSeguimiento());
            getRefTransSeguimientoRemoto().insertar(bean.getObjetoTransporteSeguimiento());
            bean.addMensaje("El registro se creo correctamente");
            listarSeguimiento(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verSeguimiento(ReferenciaContraRefBean bean) {
        try {
            bean.setObjetoTransporte(getRefTransporteRemoto().consultar(bean.getObjetoTransporte().getId()));
            bean.getObjetoTransporte().setListaRefTransporteSeguimiento(getRefTransSeguimientoRemoto().consultarByRefTransporte(bean.getObjetoTransporte().getId()));
            bean.getObjetoTransporte().setListaRefTransporteInsumo(getRefTransporteInsumoRemoto().consultarPorRefTransporteId(bean.getObjetoTransporte().getId()));
            if (bean.getObjetoTransporte().getCntPrestadorSede() != null) {
                bean.getObjetoTransporte().setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjetoTransporte().getCntPrestadorSede().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void reporteReferencia(ReferenciaContraRefBean bean) {
        try {
            ver(bean);
            bean.getObjeto().getAsegAfiliado().setAfiliacionUbicacion(bean.getHashUbicacion().get(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getId()));
            ReporteReferencia reporte = new ReporteReferencia();
            reporte.setDtmFechaHora(bean.getObjeto().getFechaHoraCrea());
            reporte.setDtmFechaNacimientoPaciente(bean.getObjeto().getAsegAfiliado().getFechaNacimiento());
            reporte.setIntNumeroSolicitud(bean.getObjeto().getId());
            reporte.setIntTipoAnexo(bean.getObjeto().getTipo());
            if (bean.getObjeto().getTipo() == 9) {
                reporte.setStrTipoAnexo(ReporteReferencia.ENCABEZADO_ANEXO_9);
            }
            if (bean.getObjeto().getTipo() == 10) {
                reporte.setStrTipoAnexo(ReporteReferencia.ENCABEZADO_ANEXO_10);
            }

            reporte.setStrCelularProfesional("");
            if (bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion() != null) {
                reporte.setStrCodigoDepartamentoPaciente(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getUbicacionPadre().getPrefijo());
                reporte.setStrCodigoMunicipioPaciente(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getPrefijo());
            }
            Ubicacion ubicacionPrestador = bean.getHashUbicacion().get(bean.getObjeto().getCntPrestadorSede().getUbicacionId());
            Ubicacion ubicacionPadre = bean.getHashUbicacion().get(ubicacionPrestador.getUbicacionPadre().getId());
            ubicacionPrestador.setUbicacionPadre(ubicacionPadre);
            bean.getObjeto().getCntPrestadorSede().setUbicacion(ubicacionPrestador);

            if (bean.getObjeto().getCntPrestadorSede().getUbicacion() != null) {
                String prefijoPadre = "";
                if (bean.getObjeto().getCntPrestadorSede().getUbicacion().getUbicacionPadre() != null) {
                    prefijoPadre = bean.getObjeto().getCntPrestadorSede().getUbicacion().getUbicacionPadre().getPrefijo();
                }
                reporte.setStrCodigoDepartamentoPrestador(prefijoPadre);
                reporte.setStrCodigoMunicipioPrestador(bean.getObjeto().getCntPrestadorSede().getUbicacion().getPrefijo());
            }
            reporte.setStrCodigoDepartamentoResponsable("");
            reporte.setStrCodigoEntidadResponsablePago(bean.getObjeto().getAsegAfiliado().getRegimen().equals("1") ? ReferenciaContraRefBean.CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO : ReferenciaContraRefBean.CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO);
            reporte.setStrCodigoMunicipioResponsable("");
            reporte.setStrCodigoPrestador(bean.getObjeto().getCntPrestadorSede().getCodigoPrestador());
            reporte.setStrDepartamentoPaciente(bean.getObjeto().getAsegAfiliado().getResidenciaUbicacion().getUbicacionPadre().getNombre());
            if (bean.getObjeto().getCntPrestadorSede() != null && bean.getObjeto().getCntPrestadorSede().getUbicacion().getUbicacionPadre() != null) {
                reporte.setStrDepartamentoPrestador(bean.getObjeto().getCntPrestadorSede().getUbicacion().getUbicacionPadre().getNombre());
            }
            reporte.setStrDepartamentoResponsable(bean.getObjeto().getAcompananteDepartamento());
            reporte.setStrDireccionPaciente(bean.getObjeto().getAsegAfiliado().getDireccionResidencia());
            reporte.setStrDireccionPrestador(bean.getObjeto().getCntPrestadorSede().getDireccion());
            reporte.setStrDireccionResponsable(bean.getObjeto().getAcompananteDireccion());
            reporte.setStrDocumentoPaciente(bean.getObjeto().getAsegAfiliado().getNumeroDocumento());
            reporte.setStrDocumentoPrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getNumeroDocumento());
            reporte.setStrDocumentoResponsable(bean.getObjeto().getAcompananteDocumento());
            reporte.setStrEntidadResponsablePago(getEmpresaRemoto().consultar(ReferenciaContraRefBean.ID_GNEMPRESACRUE).getRazonSocial());
            reporte.setStrMunicipioPaciente(bean.getObjeto().getAsegAfiliado().getResidenciaUbicacion().getNombre());
            reporte.setStrMunicipioPrestador(bean.getObjeto().getCntPrestadorSede().getUbicacion().getNombre());
            reporte.setStrMunicipioResponsable(bean.getObjeto().getAcompananteMunicipio());
            reporte.setStrNombrePrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getRazonSocial());
            if (bean.getObjeto().getCntProfesionales() != null) {
                reporte.setStrNombreProfesional(bean.getObjeto().getCntProfesionales().getPrimerNombre() + " "
                        + (bean.getObjeto().getCntProfesionales().getSegundoNombre() != null ? bean.getObjeto().getCntProfesionales().getSegundoNombre().trim() + " " : "")
                        + bean.getObjeto().getCntProfesionales().getPrimerApellido() + " "
                        + (bean.getObjeto().getCntProfesionales().getSegundoApellido() != null ? bean.getObjeto().getCntProfesionales().getSegundoApellido().trim() : ""));
            }

            reporte.setStrObservacionEvolucion("");
            reporte.setStrObservacionRelevante("");
            if (bean.getObjetoDatosClinicos() != null) {
                reporte.setStrObservacionEvolucion(bean.getObjetoDatosClinicos().getAntecedentes());
                reporte.setStrObservacionRelevante(" Escala de Glasgow: " + bean.getObjetoDatosClinicos().getEscalaGlasgow() + " \n"
                        + "Resumen de la Enfermedad Actual: \n" + bean.getObjetoDatosClinicos().getResumenClinico() + " \n"
                        + "Antecedentes: \n" + bean.getObjetoDatosClinicos().getAntecedentes() + " \n"
                        + "Signos Vitales: \n"
                        + "Temperatura " + bean.getObjetoDatosClinicos().getTemperatura().toString() + "°C "
                        + "Frecuencia Cardiaca: " + bean.getObjetoDatosClinicos().getFrecuenciaCardiaca() + " "
                        + "Tensión arterial: " + bean.getObjetoDatosClinicos().getTensionArterialSistole() + "/" + bean.getObjetoDatosClinicos().getTensionArtedialDiastole() + " "
                        + "Frecuencia Respiratoria: " + bean.getObjetoDatosClinicos().getFrecuenciaRespiratoria() + " "
                        + "Saturación de oxígeno: " + bean.getObjetoDatosClinicos().getSaturacionOxigeno() + " \n"
                        + "Hallazgos del Examen Físico: \n" + bean.getObjetoDatosClinicos().getHallazgosExamenFisico() + " \n"
                        + "Motivo de Consulta \n" + bean.getObjetoDatosClinicos().getMotivoRemision() + " \n"
                        + "Enfermedad Actual \n" + bean.getObjetoDatosClinicos().getResumenClinico() + " \n");
            }
            reporte.setStrPrimerApellidoPaciente(bean.getObjeto().getAsegAfiliado().getPrimerApellido());
            reporte.setStrPrimerApellidoResponsable(bean.getObjeto().getAcompanantePrimerApellido());
            reporte.setStrPrimerNombrePaciente(bean.getObjeto().getAsegAfiliado().getPrimerNombre());
            reporte.setStrPrimerNombreResponsable(bean.getObjeto().getAcompanantePrimerNombre());
            reporte.setStrRegimenPaciente(bean.getObjeto().getAsegAfiliado().getRegimen().equals("1") ? "Subsidiado" : "Contributivo");
            reporte.setStrSegundoApellidoPaciente(bean.getObjeto().getAsegAfiliado().getSegundoApellido());
            reporte.setStrSegundoApellidoResponsable(bean.getObjeto().getAcompananteSegundoApellido());
            reporte.setStrSegundoNombrePaciente(bean.getObjeto().getAsegAfiliado().getSegundoNombre());
            reporte.setStrSegundoNombreResponsable(bean.getObjeto().getAcompananteSegundoNombre());
            reporte.setStrServicioCualSolicita(bean.getObjeto().getMaServicioSolicitaValor());
            reporte.setStrServicioQueSolicita(bean.getObjeto().getMaServicioRemiteValor());
            reporte.setStrTelefonoPaciente(bean.getTelefonoMovilAfiliado());
            reporte.setStrTelefonoPrestador(bean.getObjeto().getCntPrestadorSede().getTelefonoAdministrativo());
            reporte.setStrTelefonoProfesional(bean.getObjeto().getProfesionalSolicitaTelefono());
            reporte.setStrTelefonoResponsable(bean.getObjeto().getAcompananteTelefono());
            reporte.setStrTipoDocumentoPaciente(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo());
            reporte.setStrTipoDocumentoPrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
            reporte.setStrTipoDocumentoResponsable(bean.getObjeto().getMaeAcompananteTipoDocumentoCodigo());
            reporte.setStrConsecutivo(bean.getObjeto().getConsecutivoGen());
            reporte.setStrCorreoContacto(bean.getObjeto().getAsegAfiliado().getEmail());
            //reporte.setStrCorreoContacto(bean.getObjeto().getTelefonoContactoEmergencia());
            reporte.setStrDireccionAlternativa(bean.getObjeto().getAfiliadoDireccionAlternativa());
            reporte.setStrCauseMotivaAtencion(bean.getObjeto().getMaeCausaExternaValor());
            reporte.setStrPrivacidadAtencion(bean.getObjeto().getPrioridadStr());
            reporte.setStrGruposServicios(bean.getObjeto().getMaeUbicacionValor());
            reporte.setStrModalidadrealizacionTecnologia(bean.getObjeto().getMaeModalidadTecnologiaValor());

            reporte.setStrServicioCualSolicitaCodigo(bean.getObjeto().getMaServicioSolicitaCodigo());
            reporte.setStrCodigoPrestadoRemite(bean.getObjeto().getCntPrestadorSede().getCodigoHabilitacionSede());
            reporte.setStrCodigoCUPSProcedimiento(bean.getObjeto().getMaTecnologiaValor());
            if (bean.getObjeto().getCantidadTecnologiaSolicitada() != null) {
                reporte.setStrCantidad(bean.getObjeto().getCantidadTecnologiaSolicitada().toString());
            }
            reporte.setStrDestinoPersona(bean.getObjeto().getMaeCondicionDestinoValor());
            reporte.setStrTipoAtencionSolicita(bean.getObjeto().getMaeTipoAtencionValor());
            reporte.setListaDiagnosticosIngreso(bean.getObjeto().getListaRefAnexo9Diagnostico());
            bean.setReporte(reporte);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void reporteReferenciaContraReferencia(ReferenciaContraRefBean bean) {
        try {
            ver(bean);
            LocalDate fecha = LocalDate.now();
            ReporteReferenciaContraReferencia reporte = new ReporteReferenciaContraReferencia();
            reporte.setStrFechaDescarga(fecha.toString());
            reporte.setStrTipoSolicitud(bean.getObjeto().getTipoSolicitudStr());
            reporte.setStrEstadoSolicitud(bean.getObjeto().getMaeEstadoValor());
            reporte.setIntNumeroSolicitud(bean.getObjeto().getId());
            reporte.setStrCanalComunicacion(bean.getObjeto().getMaeCanalComunicacionValor());
            reporte.setStrUsuarioCrea(bean.getObjeto().getUsuarioCrea());
            reporte.setStrFechaCrea(bean.getObjeto().getFechaHoraCrea());
            reporte.setStrTipoDocumento(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoValor());
            reporte.setStrNumeroDocumento(bean.getObjeto().getAsegAfiliado().getNumeroDocumento());
            reporte.setStrNombres(bean.getObjeto().getAsegAfiliado().getNombres());
            reporte.setStrApellidos(bean.getObjeto().getAsegAfiliado().getApellidos());
            reporte.setStrGenero(bean.getObjeto().getAsegAfiliado().getMaeGeneroValor());
            reporte.setStrEdad(bean.getObjeto().getAsegAfiliado().getEdad());
            reporte.setStrFechaNacimiento(bean.getObjeto().getAsegAfiliado().getFechaNacimiento());
            reporte.setStrRegimen((bean.getObjeto().getAsegAfiliado().getRegimen().equals("1")) ? "Subsidiado" : (bean.getObjeto().getAsegAfiliado().getRegimen().equals("2")) ? "Contributivo" : "");
            reporte.setStrNivelSisben(bean.getObjeto().getAsegAfiliado().getMaeNivelSisbenValor());
            reporte.setStrTieneDiscapacidad(bean.getObjeto().getAsegAfiliado().getDiscapacidadStr());
            reporte.setStrTipoAfiliado(bean.getObjeto().getAsegAfiliado().getMaeTipoAfiliadoValor());
            reporte.setStrEstadoAfiliado(bean.getObjeto().getAsegAfiliado().getMaeEstadoAfiliacionValor());
            reporte.setStrExisteBDUA(bean.getObjeto().getAsegAfiliado().getRegistroBduaStr());
            reporte.setStrCodigoUnico(bean.getObjeto().getAsegAfiliado().getIdAfiliado());
            reporte.setStrGrupoPoblacional(bean.getObjeto().getAsegAfiliado().getMaeGrupoPoblacionalValor());
            reporte.setStrGrupoEtnico(bean.getObjeto().getAsegAfiliado().getMaeEtniaValor());
            reporte.setStrIpsPrimaria(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getCntPrestador().getRazonSocial());
            reporte.setStrSedeIpsPrimaria(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getNombreSede());
            reporte.setStrCiudadMunicipioIpsPrimaria(bean.obtenerMunicipio(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getUbicacionId()));
            reporte.setStrDepartamentoIpsPrimaria(bean.obtenerDepartamento(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getUbicacionId()));
            reporte.setStrTienePortabilidad(bean.getObjeto().getAsegAfiliado().getTienePortabilidadStr());
            if (bean.getObjeto().getAsegAfiliado().getPortabilidadPrestadorSede() != null) {
                reporte.setStrSedePrestadorPortabilidad(bean.getObjeto().getAsegAfiliado().getPortabilidadPrestadorSede().getNombreSede());
                reporte.setStrMunicipioPrestadorPortabilidad(bean.obtenerMunicipio(bean.getObjeto().getAsegAfiliado().getPortabilidadPrestadorSede().getUbicacionId()));
            }
            reporte.setStrTipoDocumentoCF(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCfValor());
            reporte.setStrNumeroDocumentoCF(bean.getObjeto().getAsegAfiliado().getNumeroDocumentoCf());
            reporte.setStrModeloLiquidacion(bean.getObjeto().getAsegAfiliado().getMaeModeloLiquidacionValor());
            reporte.setStrFechaMovilidad(bean.getObjeto().getAsegAfiliado().getFechaMovilidad());
            if (bean.getObjeto().getAsegAfiliado().getFechaReactivacion() != null) {
                reporte.setStrFechaReactivacion(bean.getObjeto().getAsegAfiliado().getFechaReactivacion().toString());
            }
            reporte.setStrFechaRetiro(bean.getObjeto().getAsegAfiliado().getFechaEgresoEps());
            reporte.setStrFechaAfiliacionEPS(bean.getObjeto().getAsegAfiliado().getFechaAfiliacionEps());
            reporte.setStrMunicipioAfiliacion(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getNombre());
            reporte.setStrMunicipioResidencia(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getNombre());
            reporte.setStrDireccion(bean.getObjeto().getAsegAfiliado().getDireccionResidencia());
            reporte.setStrZona(bean.getObjeto().getAsegAfiliado().getMaeZonaValor());
            reporte.setStrTelefono(bean.getTelefonoFijoAfiliado());
            reporte.setStrCelular(bean.getTelefonoMovilAfiliado());
            reporte.setStrEmail(bean.getObjeto().getAsegAfiliado().getEmail());
            reporte.setStrTipoDocumentoIPS(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
            reporte.setStrNumeroDocumentoIPS(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getNumeroDocumento());
            reporte.setStrRazonSocialIPS(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getRazonSocial());
            reporte.setStrCiudadMunicipioIPS(bean.obtenerMunicipio(bean.getObjeto().getCntPrestadorSede().getUbicacionId()));
            reporte.setStrDepartamentoIPS(bean.obtenerDepartamento(bean.getObjeto().getCntPrestadorSede().getUbicacionId()));
            reporte.setStrDireccionIPS(bean.getObjeto().getCntPrestadorSede().getDireccion());
            if(bean.getObjeto().getCntProfesionales() != null){
                reporte.setStrTipoDocumentoProfesionalReferencia(bean.getObjeto().getCntProfesionales().getMaeTipoDocumentoValor());
                reporte.setStrNumeroDocumentoProfesionalReferencia(bean.getObjeto().getCntProfesionales().getDocumento());
                reporte.setStrPrimerNombreProfesionalReferencia(bean.getObjeto().getCntProfesionales().getPrimerNombre());
                reporte.setStrSegundoNombreProfesionalReferencia(bean.getObjeto().getCntProfesionales().getSegundoNombre());
                reporte.setStrPrimerApellidoProfesionalReferencia(bean.getObjeto().getCntProfesionales().getPrimerApellido());
                reporte.setStrSegundoApellidoProfesionalReferencia(bean.getObjeto().getCntProfesionales().getSegundoApellido());
                reporte.setStrRegistroMedicoProfesionalReferencia(bean.getObjeto().getCntProfesionales().getRegistroMedico());
                
            }
            if(bean.getObjetoProfesionalPrestador() != null){
                reporte.setStrEsécialidadProfesionalReferencia(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                reporte.setStrTelefonoProfesionalReferencia(bean.getObjeto().getProfesionalSolicitaTelefono());
            }

            reporte.setStrServicioRefiere(bean.getObjeto().getMaServicioRemiteValor());
            reporte.setStrServicioSolicita(bean.getObjeto().getMaServicioSolicitaValor());
            reporte.setStrServicioCama(bean.getObjeto().getCama());
            reporte.setStrServicioPiso(bean.getObjeto().getUbicacion());
            reporte.setStrNombreInformante(bean.getObjeto().getInformanteNombre());
            reporte.setStrCargoInformante(bean.getObjeto().getInformanteCargo());
            reporte.setStrTelefonoInformante(bean.getObjeto().getInformanteTelefono());

            RefAnexo9Gestion gestionDireccionada = getRefAnexo9GestionaRemoto().consultarPorRefAnexo9UltimoDireccionamiento(bean.getObjeto().getId());
            RefAnexo9Gestion gestionCerrada = getRefAnexo9GestionaRemoto().consultarPorRefAnexo9EstadoCerrado(bean.getObjeto().getId());
            if (gestionDireccionada != null) {
                reporte.setStrFechaHoraAceptacionReceptora(gestionDireccionada.getFechaHoraAceptacion().toString());
                completarReporteBitacora(reporte, gestionDireccionada, bean);
            }
            if (gestionCerrada != null) {
                completarReporteBitacora(reporte, gestionCerrada, bean);
                reporte.setIntTotalDiasRemision(calculoDiasBitacora(bean.getObjeto().getFechaHoraSolicitud(), gestionCerrada.getFechaHoraCrea(), bean));
            } else {
                reporte.setIntTotalDiasRemision(calculoDiasBitacora(bean.getObjeto().getFechaHoraSolicitud(), null, bean));
            }

            reporte.setStrTemperaturaC(bean.getObjetoDatosClinicos().getTemperatura());
            reporte.setStrFrecuenciaCardiaca(bean.getObjetoDatosClinicos().getFrecuenciaCardiaca());
            reporte.setStrFrecuenciaRespiratoria(bean.getObjetoDatosClinicos().getFrecuenciaRespiratoria());
            reporte.setStrTensionArterialS(bean.getObjetoDatosClinicos().getTensionArterialSistole());
            reporte.setStrTensionArterialD(bean.getObjetoDatosClinicos().getTensionArtedialDiastole());
            reporte.setStrSaturacionOxigeno(bean.getObjetoDatosClinicos().getSaturacionOxigeno());
            reporte.setStrPeso(bean.getObjetoDatosClinicos().getPeso());
            reporte.setStrTalla(bean.getObjetoDatosClinicos().getTalla());
            reporte.setStrImc(bean.getObjetoDatosClinicos().getImc());
            reporte.setStrEscalaGlasglow(bean.getObjetoDatosClinicos().getEscalaGlasgow());
            reporte.setStrEscalaGlasglowDescripcion(bean.getObjetoDatosClinicos().getHallazgosExamenFisico());
            reporte.setStrResumenEnfermedadActual(bean.getObjetoDatosClinicos().getResumenClinico());
            reporte.setStrAntecedentes(bean.getObjetoDatosClinicos().getAntecedentes());
            reporte.setStrTriageNivel(bean.getObjetoDatosClinicos().getTriage());
            reporte.setStrMotivosRemisionDescripcion(bean.getObjetoDatosClinicos().getMotivoRemision());
            reporte.setListaDiagnosticos(bean.getObjeto().getListaRefAnexo9Diagnostico());
            reporte.setListaRefAnexo9Gestion(getRefAnexo9GestionaRemoto().consultarPorRefAnexo9PorId(bean.getObjeto().getId()));
            for (RefAnexo9Gestion refAnexo9Gestion : reporte.getListaRefAnexo9Gestion()) {
                refAnexo9Gestion.setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(refAnexo9Gestion.getCntPrestadorSede().getId()));
                refAnexo9Gestion.setPrestadorSede(refAnexo9Gestion.getCntPrestadorSede().getCntPrestador().getRazonSocial());
            }
            bean.setReporteReferenciaContraReferencia(reporte);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void completarReporteBitacora(ReporteReferenciaContraReferencia reporte, RefAnexo9Gestion refAnexo9Gestion, ReferenciaContraRefBean bean) {
        try {
            if (refAnexo9Gestion != null) {
                if (refAnexo9Gestion.getCntPrestadorSede() != null) {
                    CntPrestadorSede prestadorSede = getCntPrestadorRemoto().consultarSede(refAnexo9Gestion.getCntPrestadorSede().getId());
                    if (prestadorSede != null) {
                        reporte.setStrTipoDocumentoIPSReceptora(prestadorSede.getCntPrestador().getMaeTipoDocumentoValor());
                        reporte.setStrNumeroDocumentoIPSReceptora(prestadorSede.getCntPrestador().getNumeroDocumento());
                        reporte.setStrRazonSocialIPSReceptora(prestadorSede.getCntPrestador().getRazonSocial());
                        reporte.setStrCiudadMunicipioIPSReceptora(bean.obtenerMunicipio(prestadorSede.getUbicacionId()));
                        reporte.setStrDepartamentoIPSReceptora(bean.obtenerDepartamento(prestadorSede.getUbicacionId()));
                        reporte.setStrDireccionIPSReceptora(prestadorSede.getDireccion());
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaContraRefImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer calculoDiasBitacora(Date FechaIngreso, Date fechaCerrada, ReferenciaContraRefBean bean) {
        int totalDiasReision = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strFechaActal = simpleDateFormat.format(new Date());
            if (FechaIngreso != null) {
                if (fechaCerrada != null) {
                    Date fechaConvertidaCerrada = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCerrada.toString());
                    totalDiasReision = diasEntreFechas(FechaIngreso.toString(), fechaConvertidaCerrada);
                } else {
                    Date fechaConvertidaIngreso = new SimpleDateFormat("yyyy-MM-dd").parse(FechaIngreso.toString());
                    totalDiasReision = diasEntreFechas(strFechaActal, fechaConvertidaIngreso);
                }
                if (totalDiasReision < 0) {
                    totalDiasReision = totalDiasReision * -1;
                }

            }
        } catch (ParseException e) {
            bean.addMensaje(e.getMessage());
        }

        return totalDiasReision;
    }

    private static int diasEntreFechas(String fechaInicial, Date fechaFinal) throws ParseException {
        Date newFechaInicial = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
        int dias = (int) ((fechaFinal.getTime() - newFechaInicial.getTime()) / 86400000);
        return dias;
    }

    private void borrarDiagnostico(ReferenciaContraRefBean bean) {
        try {
            getRefAnexo9DiagnosticoRemoto().eliminar(bean.getObjetoDiagnosticoBorrar().getId());
            bean.addMensaje("El registro se eliminó correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verGestionar(ReferenciaContraRefBean bean) {
        try {
            ver(bean);
            bean.setGestionar(true);
            if (bean.getObjeto().getMaeEstadoCodigo().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)) {
//            if (!getRefAnexo9GestionaRemoto().consultarPorRefAnexo9YEstado(bean.getObjeto().getId(), RefAnexo9Gestion.ESTADO_CERRADA).isEmpty()) {
                bean.setGestionar(false);
            }

            gestionEstadosEstadoSolicitud(bean);
            contactosAfiliado(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void asignarAfiliado(int idAfiliacion, ReferenciaContraRefBean bean) {
        try {
            //consulta afiliado completo
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(idAfiliacion);
            boolean isActivo = validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), bean);
            bean.setIsActivoAfiliado(isActivo);
            if (isActivo) {
                bean.getObjeto().setAsegAfiliado(afiliado);
                consultarAfiliadoPortabilidad(bean);
                bean.getParamConsulta(1).setFiltros(new HashMap<>());
                bean.setColorEstadoAfiliado(bean.obtenerColorEstado());
                bean.getObjeto().getAsegAfiliado().setEdad(Util.calcularEdad(afiliado.getFechaNacimiento()));
                contactosAfiliado(bean);
                String estados = "'" + RefAnexo9Estado.ESTADO_CERRADA_CODIGO + "'" + "," + "'" + RefAnexo9Estado.ESTADO_ANULADA_CODIGO + "'" + "," + "'" + RefAnexo9Estado.ESTADO_RECHAZADA_CODIGO + "'" + "," + "'" +RefAnexo9Estado.ESTADO_CANCELADA_CODIGO + "'";
                bean.setListaAnexos9Activos(
                        getRefAnexo9Remoto().anexos9ByAfiliadoByEstados(afiliado.getId(), estados)
                );
                bean.setListaAfiliadoPrograma(getPeAfiliadosProgramaRemoto().consultarAfiliadoActivo(bean.getObjeto().getAsegAfiliado().getId()));
            }
        } catch (Exception ex) {
            bean.addError("Hubo un seleccionando el afiliado, intentelo nuevamente");
        }
    }

    private boolean validarEstadoAfiliado(int maeEstadoAfiliacion, ReferenciaContraRefBean bean) {
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
    public boolean consultarEstadoAfiliado(ReferenciaContraRefBean bean) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(bean.getObjeto().getAsegAfiliado().getMaeEstadoAfiliacion());
            if (maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }
 
    @Override
    public List<Maestro> motivosGestionEstado(ReferenciaContraRefBean bean) {
        try {
            return getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CR_GESTION_MOTIVO, bean.getObjetoGestion().getMaeTipoId());
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaContraRefImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            bean.addError("Hubo un fallo consultando los motivos del estado, intentelo nuevamente");
        }
        return new ArrayList();
    }

    private void gestionEstadosEstadoSolicitud(ReferenciaContraRefBean bean) {
        try {
            bean.setListaTipoGestion(
                    getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CR_GESTION_TIPO, bean.getObjeto().getMaeEstadoId())
            );
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaContraRefImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            bean.addError("Hubo un fallo consultando los estados gestión, intentelo nuevamente");
        }
    }

    @Override
    public void claseTransporteTipo(ReferenciaContraRefBean bean) {
        try {
            bean.setListaClaseTransporte(
                    getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CR_TRANSPORTE_CLASE, bean.getObjetoTransporte().getMaeTipoTransporteId())
            );
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaContraRefImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            bean.addError("Hubo un fallo consultando los motivos del estado, intentelo nuevamente");
        }
    }

    private void contactosAfiliado(ReferenciaContraRefBean bean) {
        //10-06-2022 ivanegaa
        if (bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto() != null) {
            bean.setTelefonoFijoAfiliado(
                    bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_TELEFONO))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
            bean.setTelefonoMovilAfiliado(
                    bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_CELULAR))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
        } else {
            bean.setTelefonoFijoAfiliado(null);
            bean.setTelefonoMovilAfiliado(null);
        }
    }

    @Override
    public RefAnexo9Gestion consultarUltimaGestionAnexo9(RefAnexo9 anexo9) {
        try {
            return this.getRefAnexo9GestionaRemoto().consultarPorRefAnexo9(anexo9.getId());
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaContraRefImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public RefAnexo9Gestion consultarUltimoDireccionamiento(RefAnexo9 anexo9) {
         try {
            return this.getRefAnexo9GestionaRemoto().consultarPorRefAnexo9UltimoDireccionamiento(anexo9.getId());
        } catch (Exception ex) {
            Logger.getLogger(ReferenciaContraRefImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public List<MaRelacion> consultarServiciosHabilitacionId(SelServiciosHabilitacionBean servicioHabilitadicoId) {
        try {
            List<MaRelacion> lista = getMaRelacionRemoto().consultarPorMaServicioHabilitacionId(servicioHabilitadicoId.getObjeto().getId());
            return lista;
        } catch (Exception ex) {
            return null;
        }
    }
    
}
