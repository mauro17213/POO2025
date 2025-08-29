/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Historico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSedeServicioHabilitacion;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2DatoAtencion;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateSede;
import com.saviasaludeps.savia.dominio.crue.AuTipoRescate;
import com.saviasaludeps.savia.dominio.crue.ReporteAnexo2;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4HistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2DiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2Remoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2SemaforoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2ItemRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateGestionRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateSedeRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioValorRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.crue.bean.SolicitudesUrgenciasBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.DateUtil;
import com.saviasaludeps.savia.web.utilidades.Log;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Alexander Diaz
 */
public class SolicitudesUrgenciasImpl extends AccionesBO implements SolicitudesUrgenciasIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AuAnexo2Remoto getAuAnexo2Remoto() throws Exception {
        return (AuAnexo2Remoto) RemotoEJB.getEJBRemoto("AuAnexo2Servicio", AuAnexo2Remoto.class.getName());
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private MaServicioHabilitacionRemoto getMaServicioHabilitacionRemoto() throws Exception {
        return (MaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("MaServicioHabilitacionServicio", MaServicioHabilitacionRemoto.class.getName());
    }

    private CntProfesionalRemoto getCntProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }

    private AuAnexo2DiagnosticoRemoto getAuAnexo2DiagnosticoRemoto() throws Exception {
        return (AuAnexo2DiagnosticoRemoto) RemotoEJB.getEJBRemoto("AuAnexo2DiagnosticoServicio", AuAnexo2DiagnosticoRemoto.class.getName());
    }

    private AuAnexo2SemaforoRemoto getAuAnexo2SemaforoRemoto() throws Exception {
        return (AuAnexo2SemaforoRemoto) RemotoEJB.getEJBRemoto("AuAnexo2SemaforoServicio", AuAnexo2SemaforoRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }

    private MaTecnologiaRemoto getMaTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicio", MaTecnologiaRemoto.class.getName());
    }

    private AuAnexo2ItemRemoto getAuAnexo2ItemRemoto() throws Exception {
        return (AuAnexo2ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo2ItemServicio", AuAnexo2ItemRemoto.class.getName());
    }

    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
        return (EmpresaRemoto) object;
    }

    private CntPrestadorSedeServicioHabilitacionRemoto getCntPrestadorSedeServicioHabilitacionRemoto() throws Exception {
        return (CntPrestadorSedeServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicioHabilitacionServicio", CntPrestadorSedeServicioHabilitacionRemoto.class.getName());
    }

    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }

    private MaSoatTarifarioValorRemoto getTarifarioValorRemoto() throws Exception {
        return (MaSoatTarifarioValorRemoto) RemotoEJB.getEJBRemoto(("MaSoatTarifarioValorServicio"), MaSoatTarifarioValorRemoto.class.getName());
    }

    private AuAnexo4HistoricoRemoto getAuAnexo4HistoricoRemoto() throws Exception {
        return (AuAnexo4HistoricoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4HistoricoServicio", AuAnexo4HistoricoRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private AuAnexo2RescateRemoto getAuAnexo2RescateRemoto() throws Exception {
        return (AuAnexo2RescateRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateServicio", AuAnexo2RescateRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private AuAnexo2RescateGestionRemoto getAuAnexo2RescateGestionRemoto() throws Exception {
        return (AuAnexo2RescateGestionRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateGestionServicio", AuAnexo2RescateGestionRemoto.class.getName());
    }

    private CntPrestadorContactoRemoto getCntPrestadorContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }
    
    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception { 
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }
    
    private AuAnexo2RescateSedeRemoto getAuAnexo2RescateSedeRemoto() throws Exception {
        return (AuAnexo2RescateSedeRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateSedeServicio", AuAnexo2RescateSedeRemoto.class.getName());
    }
    
    @Override
    public void Accion(SolicitudesUrgenciasBean bean) {
        if (super.ValidarSesion(bean)) {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
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
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2://Gestionar
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verGestion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarGestion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3://Generar reporte anexos
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_LISTAR:
                            break;
                        case Url.ACCION_CREAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(SolicitudesUrgenciasBean bean) {
        try {
            //Maestro Tipo AmbitoAtencion
            bean.setListaAmbitoAtencion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));

            //Maestro Tipo Regimen
            bean.setListaRegimen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashRegimen(new HashMap());
            bean.getListaRegimen().forEach(m -> {
                bean.getHashRegimen().put(m.getId(), m);
            });
            //Maestro Tipo ManejoIntegral
            bean.setListaManejoIntegral(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A4_GUIA_MANEJO_INTEGRAL));
            bean.setHashManejoIntegral(new HashMap());
            bean.getListaManejoIntegral().forEach(m -> {
                bean.getHashManejoIntegral().put(m.getValor(), m);
            });
            //Maestro Tipo Documento
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            //Maestro Tipo Documento
            bean.setListaTiposDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            //Maestro Tipo Documento Profesional
            bean.setListaTiposDocumentoProfesional(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL));
            bean.setHashTiposDocumentoProfesional(new HashMap());
            bean.getListaTiposDocumentoProfesional().forEach(m -> {
                bean.getHashTiposDocumentoProfesional().put(m.getId(), m);
            });
            //Maestro Sexo(Genero)no va por cambios en aseguramiento maeGeneroValor
            //Ubicacion
            bean.setListaUbicacion(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicacion(UbicacionSingle.getInstance().getHashMunicipios());
            //Maestro Estado afiliación
            bean.setListaEstadosAfiliacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosAfiliacion(new HashMap());
            bean.getListaEstadosAfiliacion().forEach(m -> {
                bean.getHashEstadosAfiliacion().put(m.getId(), m);
            });
            //MaServicio Habilitacion
            bean.setListaMaServicioHabilitacion(getMaServicioHabilitacionRemoto().consultarTodos());
            bean.setHashMapMaServicioHabilitacion(new HashMap());
            bean.getListaMaServicioHabilitacion().forEach(m -> {
                bean.getHashMapMaServicioHabilitacion().put(m.getId(), m);
            });
            //Tipo Adjunto
            bean.setListaTipoAdjunto(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_ADJUNTO_TIPO));
            bean.setHashTipoAdjunto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_ADJUNTO_TIPO));

            //Tipo Gestion
            bean.setListaTipoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_GESTION_TIPO));
            bean.setHashTipoGestion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_GESTION_TIPO));
            //Tipo Motivo
            bean.setListaMotivoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_GESTION_MOTIVO));
            bean.setHashMotivoGestion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_GESTION_MOTIVO));
            //Semaforo
            bean.setListaAuAnexo2Semaforo(getAuAnexo2SemaforoRemoto().consularTodos());
            //Tipo Motivo Seguimiento 
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_TRANSPORTE_SEGUIMIENTO_TIPO_REPORTE));
            bean.setHashTipoSeguimiento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CR_TRANSPORTE_SEGUIMIENTO_TIPO_REPORTE));

            bean.setListaTipoDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TIPO_DIAGNOSTICO));
            bean.setHashTipoDiagnostico(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.AU_TIPO_DIAGNOSTICO));

            //Origen
            /*bean.setListaOrigen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_ORIGEN_ATENCION));
            bean.setHashOrigen(new HashMap());
            bean.getListaOrigen().forEach(m -> {
                bean.getHashOrigen().put(m.getId(), m);
            });*/
            //Destino
            bean.setListaDestinoTotal(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A2_DESTINO_PACIENTE));
            bean.setHashDestinoTotal(AuConstantes.obtenerHashMaestro(bean.getListaDestinoTotal()));
         
            try {
                Maestro maeDiasVigencia = getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A4_DIAS_VIGENCIA).get(0);
                if (maeDiasVigencia.getValor() != null && !maeDiasVigencia.getValor().isEmpty()) {
                    bean.setDiasVigencia(Integer.parseInt(maeDiasVigencia.getValor()));
                } else {
                    bean.setDiasVigencia(1);
                }
            } catch (Exception e) {
                bean.setDiasVigencia(1);
            }
            //codigos tecnologias
            String cnfiguracionMedica
                    = PropApl.getInstance().get(PropApl.REF_URGENCIA_MEDICA_TECNOLOGIA);

            String cnfiguracionOdontologica
                    = PropApl.getInstance().get(PropApl.REF_URGENCIA_ODONTOLOGICA_TECNOLOGIA);

            String configuracionHabilitacion
                    = PropApl.getInstance().get(PropApl.REF_URGENCIA_HABILITACION);

            bean.setCodigosUrgenciasMedica(cnfiguracionMedica != null ? cnfiguracionMedica : "");
            bean.setCodigosUrgenciasOdontologica(cnfiguracionOdontologica != null ? cnfiguracionOdontologica : "");
            bean.setCodigosServicioHabilitacion(configuracionHabilitacion != null ? configuracionHabilitacion : "");

            String[] codigosServicioUrgencias = bean.getCodigosUrgenciasMedica().split(",");
            if (codigosServicioUrgencias.length > 1) {
                bean.setCodigosUrgenciasMedica(codigosServicioUrgencias[0]);
            }
            String[] codigosServicioUrgenciasOdontologia = bean.getCodigosUrgenciasOdontologica().split(",");
            if (codigosServicioUrgenciasOdontologia.length > 1) {
                bean.setCodigosUrgenciasOdontologica(codigosServicioUrgenciasOdontologia[0]);
            }
            // MaTecnologias                      
            bean.setHashMaTecnologias(new HashMap());
            for (String codigosServicioUrgencia : codigosServicioUrgencias) {
                MaTecnologia mtc = getMaTecnologiaRemoto().consultarPorCodigo(codigosServicioUrgencia);
                bean.getHashMaTecnologias().put(mtc.getCodigoPropio(), mtc);
            }
            for (String codigosServicioUrgencia : codigosServicioUrgenciasOdontologia) {
                MaTecnologia mtc = getMaTecnologiaRemoto().consultarPorCodigo(codigosServicioUrgencia);
                bean.getHashMaTecnologias().put(mtc.getCodigoPropio(), mtc);
            }

            //MaServicioHabilitacionItems
            ParamConsulta paramConsultaMaServicioHabilitacionItems = new ParamConsulta();
            HashMap<String, Object> filtrosMaServicioHabilitacionItemsHash = new HashMap<>();
            filtrosMaServicioHabilitacionItemsHash.put("codigo", SolicitudesUrgenciasBean.CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS);
            paramConsultaMaServicioHabilitacionItems.setFiltros(filtrosMaServicioHabilitacionItemsHash);
            bean.setListaMaServicioHabilitacionItems(getMaServicioHabilitacionRemoto().consultarListaTodo(paramConsultaMaServicioHabilitacionItems));

            bean.setHashMaServicioHabilitacionItems(new HashMap());
            for (MaServicioHabilitacion msh : bean.getListaMaServicioHabilitacionItems()) {
                bean.getHashMaServicioHabilitacionItems().put(String.valueOf(msh.getCodigo()), msh);

            }
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());

            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosAfiliado(AuConstantes.obtenerHashMaestro(bean.getListaEstadosAfiliado()));

            bean.setListaCondicionDestino(getMaestroRemoto().consultarPorTipo(MaestroTipo.RIPS_AU_DESTINO_SALIDA));
            bean.setHashCondicionDestino(AuConstantes.obtenerHashMaestro(bean.getListaCondicionDestino()));
            
            bean.setListaViaIngreso(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A2_VIA_INGRESO));
            bean.setHashViaIngreso(AuConstantes.obtenerHashMaestro(bean.getListaViaIngreso()));
            
        } catch (Exception e) {
            //bean.addError(e.getMessage());
        }
    }

    private void listar(SolicitudesUrgenciasBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo2Remoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuAnexo2Remoto().consultarLista(bean.getParamConsulta(0)));
            /*for (AuAnexo2 registro : bean.getRegistros()) {
                registro.setAsegAfiliado(getAfiliadoRemoto().consultar(registro.getAsegAfiliado().getId()));
                if (registro.getCntPrestadorSede() != null) {
                    registro.setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(registro.getCntPrestadorSede().getId()));
                    registro.getCntPrestadorSede().setCntPrestador(getCntPrestadorRemoto().consultar(registro.getCntPrestadorSede().getCntPrestador().getId()));
                }
            }
             */
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(SolicitudesUrgenciasBean bean) {
        try {
            bean.setObjeto(getAuAnexo2Remoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjeto().getAsegAfiliado().getFechaNacimiento()));
            if (bean.getObjeto().getCntProfesionales() != null) {
                bean.setObjetoProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(
                        bean.getObjeto().getCntProfesionales().getId(),
                        bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()
                ));
            }
            contactosAfiliado(bean);
            bean.getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_ORIGEN_ATENCION);
            bean.getObjeto().setLabelDestino(AuAnexo2.LABEL_DESTINO);
            if(bean.getObjeto().getVersion().equals(1)){
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_CAUSA_MOTIVA_ATENCION);
                bean.getObjeto().setLabelDestino(AuAnexo2.LABEL_CONDICION_DESTINO_PERSONA);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(SolicitudesUrgenciasBean bean) {
        try {
            bean.setObjeto(new AuAnexo2());
            bean.getObjeto().setAsegAfiliado(new AsegAfiliado());
            bean.getObjeto().setCntPrestadorSede(new CntPrestadorSede());
            bean.getObjeto().getCntPrestadorSede().setCntPrestador(new CntPrestador());
            bean.getObjeto().setCntProfesionales(new CntProfesional());
//            bean.getObjeto().getCntProfesionales().setGuardar(true);
            bean.getObjeto().setListaAuAnexo2Diagnostico(new ArrayList<>());
            bean.setObjetoDatosClinicos(new AuAnexo2DatoAtencion());
            bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
            bean.getObjeto().setListaAuSolicitudAdjunto(new ArrayList());
            bean.getObjeto().setHabilitarCampoDireccionAlternativa(true);
            bean.getObjeto().setHabilitarCampoViaIngresoPersonaServicioSalud(true);
            bean.getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_ORIGEN_ATENCION);
            bean.getObjeto().setLabelDestino(AuAnexo2.LABEL_DESTINO);
            completarMaestro(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(SolicitudesUrgenciasBean bean) {
        try {
            //Consultar profesional seleccionado
            CntPrestador prestador = bean.getObjeto().getCntPrestadorSede().getCntPrestador();
            CntProfesional profesional = null;
            if (bean.getObjeto().getCntProfesionales() != null && bean.getObjeto().getCntProfesionales().getId() != null) {
                profesional = getCntProfesionalRemoto().consultar(
                        bean.getObjeto().getCntProfesionales().getId()
                );
            }
            if (profesional != null) {
                profesional.setListaCntProfesionalPrestador(
                        getCntPrestadorProfesionalRemoto().consultarListaPorProfesionalYPrestador(
                                profesional.getId(), prestador.getId()
                        )
                );
            }
            
            //Vallidación de campos
            boolean validar = true;
            if (bean.getObjeto().getMotivo().isBlank()) {
                bean.addError("Descripción : Error de validación: se necesita un valor.");
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
            if (bean.getObjeto().getListaAuAnexo2Diagnostico().isEmpty()) {
                bean.addError("Diagnosticos :Error de validación: se necesita un valor.");
                validar = false;
            }
            
            if (bean.getObjeto().isRemitido()) {
                if (bean.getObjeto().getRemiteNit() == null || bean.getObjeto().getRemiteNombre() == null) {
                    bean.addError("Prestador IPS Remitente :Error de validación: se necesita un valor.");
                    validar = false;
                }
            }
            
            if ( bean.getObjetoProfesionalPrestador() != null && bean.getObjetoProfesionalPrestador().getMaEspecialidadId() == 0) {
                bean.addError("Profesional especialidad : Error de validación: se necesita un valor.");
                validar = false;
            }
            
            boolean noPrincipal = true;
            for (AuAnexo2Diagnostico auAnexo2Diagnostico : bean.getObjeto().getListaAuAnexo2Diagnostico()) {
                if (auAnexo2Diagnostico.isPrincipal()) {
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
            //Validación, Creación y/o actualización de profesional
            try {
                //Asignación de empresa
                if (bean.getObjeto().getCntPrestadorSede() != null) {
                    try {
                        Empresa emp = getEmpresaRemoto().consultarPorPrestador(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId());
                        if (emp != null) {
                            bean.getObjeto().setGnEmpresa(emp);
                        }
                    } catch (Exception e) {
                        bean.getObjeto().setGnEmpresa(null);
                    }
                } else {
                    bean.getObjeto().setGnEmpresa(null);
                }
                //Gestión en profesional
                if (profesional == null) {
                    if(bean.getObjeto().getCntProfesionales() != null && bean.getObjeto().getCntProfesionales().getMaeTipoCodumentoId() != 0){
                        Maestro maestroProTipoDocumento = bean.getHashTiposDocumentoProfesional().get(bean.getObjeto().getCntProfesionales().getMaeTipoCodumentoId());
                        bean.getObjeto().getCntProfesionales().setMaeTipoCodumentoId(maestroProTipoDocumento.getId());
                        bean.getObjeto().getCntProfesionales().setMaeTipoDocumentoCodigo(maestroProTipoDocumento.getValor());
                        bean.getObjeto().getCntProfesionales().setMaeTipoDocumentoValor(maestroProTipoDocumento.getNombre());
                        bean.auditoriaGuardar(bean.getObjeto().getCntProfesionales());
                        bean.getObjeto().getCntProfesionales().setId(
                                getCntProfesionalRemoto().insertar(bean.getObjeto().getCntProfesionales())
                        );
                        bean.getObjetoProfesionalPrestador().setCntPrestador(
                                new CntPrestador(prestador.getId())
                        );
                        bean.getObjetoProfesionalPrestador().setCntProfesionalesId(new CntProfesional(bean.getObjeto().getCntProfesionales().getId()));
                        bean.getObjetoProfesionalPrestador().setActivo(true);
                        bean.auditoriaGuardar(bean.getObjetoProfesionalPrestador());
                        bean.getObjetoProfesionalPrestador().setId(getCntPrestadorProfesionalRemoto().insertar(bean.getObjetoProfesionalPrestador()));
                    }
                    
                } else {
                    boolean contieneEspecialidad = false;
                    for (CntProfesionalPrestador proPres : bean.getObjeto().getCntProfesionales().getListaCntProfesionalPrestador()) {
                        if (proPres.getMaEspecialidadId() == bean.getObjetoProfesionalPrestador().getMaEspecialidadId()) {
                            contieneEspecialidad = true;
                            break;
                        }
                    }
                    if (!contieneEspecialidad) {
                        bean.getObjetoProfesionalPrestador().setCntPrestador(
                                new CntPrestador(prestador.getId())
                        );
                        bean.getObjetoProfesionalPrestador().setCntProfesionalesId(new CntProfesional(bean.getObjeto().getCntProfesionales().getId()));
                        bean.getObjetoProfesionalPrestador().setActivo(true);
                        bean.auditoriaGuardar(bean.getObjetoProfesionalPrestador());
                        bean.getObjetoProfesionalPrestador().setId(
                                getCntPrestadorProfesionalRemoto().insertar(bean.getObjetoProfesionalPrestador())
                        );
                    }
                }
            } catch (Exception e) {
                bean.addMensaje("Se presento un problema con la matricula del profesional");
            }
            /* Maestros Origen, Destino, Estado */
            Maestro destino = bean.getHashDestino().get(bean.getObjeto().getMaeDestinoPacienteId());
            if (destino != null) {
                bean.getObjeto().setMaeDestinoPacienteCodigo(destino.getValor());
                bean.getObjeto().setMaeDestinoPacienteValor(destino.getNombre());
            }
            Maestro origen = bean.getHashOrigen().get(bean.getObjeto().getMaeOrigenAtencionId());
            if (origen != null) {
                bean.getObjeto().setMaeOrigenAtencionCodigo(origen.getValor());
                bean.getObjeto().setMaeOrigenAtencionValor(origen.getNombre());
            }

//            Maestro estadoPendiente = bean.getHashEstadoUrgenciaByCode().get(SolicitudesUrgenciasBean.CODIGO_ESTADO_PENDIENTE);           
            bean.getObjeto().setEstado(AuAnexo2.ESTADO_PENDIENTE_AUDITORIA);
             
            //Obtener el ultimo id para crear el consecutivo
            //List<AuAnexo2> lista = getAuAnexo2Remoto().consultarLista(bean.getParamConsulta(0));
            //int ultimoId = lista.get(0).getId() + 1;
            //bean.getObjeto().setVersion(0);
            //nuevos maestros resolucion 2335
            Maestro maeViaIngreso = bean.getHashViaIngreso().get(bean.getObjeto().getMaeViaIngresoId());
            if(maeViaIngreso != null){
                bean.getObjeto().setMaeViaIngresoCodigo(maeViaIngreso.getValor());
                bean.getObjeto().setMaeViaIngresoValor(maeViaIngreso.getNombre());
            }
            
            Maestro maeCondicionDestino = bean.getHashCondicionDestino().get(bean.getObjeto().getMaeCondicionDestinoId());
            if(maeCondicionDestino != null){
                bean.getObjeto().setMaeCondicionDestinoCodigo(maeCondicionDestino.getValor());
                bean.getObjeto().setMaeCondicionDestinoValor(maeCondicionDestino.getNombre());
            }
            
            // Guardar AuAnexo2
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setFuenteOrigen(AuAnexo2.FUENTE_ORIGEN_MANUAL);
            bean.getObjeto().setId(getAuAnexo2Remoto().insertar(bean.getObjeto()));
            
            //Guardar Diagnosticos
            for (AuAnexo2Diagnostico diagnostico : bean.getObjeto().getListaAuAnexo2Diagnostico()) {
                bean.auditoriaGuardar(diagnostico);
                diagnostico.setAuAnexo2(new AuAnexo2(bean.getObjeto().getId()));
                getAuAnexo2DiagnosticoRemoto().insertar(diagnostico);
            }
            //Guardar AuAnexos2Adjuntos 
            if (!bean.getObjeto().getListaAuSolicitudAdjunto().isEmpty()) {
                guardarAdjunto(bean);
            }
            //Guardar AuAnexos2Items
            AuAnexo2Item auAnexos2Item = new AuAnexo2Item();
            auAnexos2Item.setAuAnexos2Id(bean.getObjeto());
            auAnexos2Item.setCantidadSolicitada(1);
            bean.auditoriaGuardar(auAnexos2Item);

            List<CntPrestadorSedeServicioHabilitacion> listCntPrestadorSedeServicioHabilitacion;
            if (bean.getObjeto().getTipo() == 0) { //Medica
//                MaTecnologia mtc = bean.getHashMaTecnologias().get(AuAnexo2.URGENCIA_MEDICA); //Cups
                auAnexos2Item = asignarTecnologiaServicioAnexo2Item(auAnexos2Item, bean.getCodigosUrgenciasMedica(), bean);
                //Se valida por REPS
                listCntPrestadorSedeServicioHabilitacion = getCntPrestadorSedeServicioHabilitacionRemoto()
                        .consultarPorSedeTecnologiaYServiciosHabilitacion(
                                bean.getObjeto().getCntPrestadorSede().getId(),
                                bean.getCodigosUrgenciasMedica(),
                                bean.getCodigosServicioHabilitacion()
                        );
            } else {//Odontologia
                auAnexos2Item = asignarTecnologiaServicioAnexo2Item(auAnexos2Item, bean.getCodigosUrgenciasOdontologica(), bean);
                //ivanegaa 2022-02-03 valida que el prestador sea odontologico
                listCntPrestadorSedeServicioHabilitacion = getCntPrestadorSedeServicioHabilitacionRemoto()
                        .consultarPorSedeTecnologiaYServiciosHabilitacion(
                                bean.getObjeto().getCntPrestadorSede().getId(),
                                bean.getCodigosUrgenciasOdontologica(),
                                SolicitudesUrgenciasBean.CODIGO_SERVICIO_HABILITACION_OODONTOLOGIA
                        );
                if (listCntPrestadorSedeServicioHabilitacion.stream()
                        .findFirst().orElse(null) != null) {
                    //Se valida por REPS que sea urgencias
                    listCntPrestadorSedeServicioHabilitacion = getCntPrestadorSedeServicioHabilitacionRemoto()
                            .consultarPorSedeTecnologiaYServiciosHabilitacion(
                                    bean.getObjeto().getCntPrestadorSede().getId(),
                                    bean.getCodigosUrgenciasOdontologica(),
                                    bean.getCodigosServicioHabilitacion()
                            );
                }
            }
            int idAnexo2Item = getAuAnexo2ItemRemoto().insertar(auAnexos2Item);
            auAnexos2Item.setId(idAnexo2Item);
            List<AuAnexo2Item> listaAnexo2Items = new ArrayList();
            listaAnexo2Items.add(auAnexos2Item);
            bean.getObjeto().setListaAuAnexo2Item(listaAnexo2Items);
            //Crear anexo4
            AuAnexo4 au4 = null;
            try {
                //ya no se valida por contrato
//                    ValidaRespuestaDTO validacion = getValidadorRemoto().validarValContrato(
//                            bean.getObjeto().getCntPrestadorSede().getCodigoHabilitacionSede(),
//                            codigoTecnologia,
//                            bean.getObjeto().getAsegAfiliado().getRegimen()
//                    );

                CntPrestadorSedeServicioHabilitacion prestadorSedeServicioHabilitacion = listCntPrestadorSedeServicioHabilitacion.stream()
                        .findFirst().orElse(null);

                if (prestadorSedeServicioHabilitacion != null) {//La tecnología está contratada
                    //Se crea el Anexo4 y se actualiza el anexo2
                    bean.getObjeto().setComentarioEstado(SolicitudesUrgenciasBean.APROBACION_ANEXO2);
                    au4 = crearAnexo4(bean.getObjeto(), bean, AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO);
//                    Maestro estadoAutorizado = bean.getHashEstadoUrgenciaByCode().get(SolicitudesUrgenciasBean.CODIGO_ESTADO_AUTORIZADA);
                    bean.getObjeto().setEstado(AuAnexo2.ESTADO_AUTORIZADA_AUTOMATICA);
                    bean.auditoriaModificar(bean.getObjeto());
                    getAuAnexo2Remoto().actualizarEstado(bean.getObjeto());
                }
            } catch (Exception e) {
                reversarCreacionAnexo4(au4);
                throw e;
            }
            crearAnexo2Rescate(bean);
            crearAnexo2RescateSede(bean);//flujo ips capita
            bean.addMensaje("El registro se creo correctamente con número de solicitud " + bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private AuAnexo2Item asignarTecnologiaServicioAnexo2Item(AuAnexo2Item auAnexos2Item, String codigoUrgencia, SolicitudesUrgenciasBean bean) {
        MaTecnologia mtc = bean.getHashMaTecnologias().get(codigoUrgencia); //Cups
        auAnexos2Item.setMaTecnologiaId(mtc.getId());
        auAnexos2Item.setMaTecnologiaCodigo(mtc.getCodigoPropio());
        auAnexos2Item.setMaTecnologiaValor(mtc.getPropioDescripcion());
        MaServicioHabilitacion servicio = bean.getHashMaServicioHabilitacionItems().get(SolicitudesUrgenciasBean.CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS);
        auAnexos2Item.setMaServicioSolicitadoId(servicio.getId());
        auAnexos2Item.setMaServicioSolicitadoCodigo(servicio.getCodigo() + "");
        auAnexos2Item.setMaServicioSolicitadoValor(servicio.getNombre());
        return auAnexos2Item;
    }

    private void borrar(SolicitudesUrgenciasBean bean) {

    }

    private void verGestion(SolicitudesUrgenciasBean bean) {
        try {
            bean.setObjeto(getAuAnexo2Remoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.getObjeto().setCntPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getCntPrestadorSede().getId()));
            bean.getObjeto().getCntPrestadorSede().setCntPrestador(getCntPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorSede().getCntPrestador().getId()));
            bean.getObjeto().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjeto().getAsegAfiliado().getFechaNacimiento()));
            bean.getObjeto().getAsegAfiliado().setPrimariaPrestadorSede(getCntPrestadorRemoto().consultarSede(bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getId()));
            bean.getObjeto().setListaAuAnexo2Diagnostico(getAuAnexo2DiagnosticoRemoto().consultarPorAuAnexo2(bean.getObjeto().getId()));
            bean.getObjeto().setCntProfesionales(getCntProfesionalRemoto().consultar(bean.getObjeto().getCntProfesionales().getId()));
            if (bean.getObjeto().getCntProfesionales() != null) {
                List<CntProfesionalPrestador> listaCntProfesionalPrestador = getCntPrestadorProfesionalRemoto().consultarPorProfesional(bean.getObjeto().getCntProfesionales().getId());
                for (CntProfesionalPrestador cntProfesionalPrestador : listaCntProfesionalPrestador) {
                    bean.setObjetoProfesionalPrestador(cntProfesionalPrestador);
                    break;
                }
            }
            contactosAfiliado(bean);
            bean.getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_ORIGEN_ATENCION);
            bean.getObjeto().setLabelDestino(AuAnexo2.LABEL_DESTINO);
            if(bean.getObjeto().getVersion().equals(1)){
                bean.getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_CAUSA_MOTIVA_ATENCION);
                bean.getObjeto().setLabelDestino(AuAnexo2.LABEL_CONDICION_DESTINO_PERSONA);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarGestion(SolicitudesUrgenciasBean bean) {
        try {
            //Consultar el estado real del anexo2
            AuAnexo2 anexo2Actual = getAuAnexo2Remoto().consultar(bean.getObjeto().getId());
            AuAnexo4 anexo4Consulta = getAuAnexo4Remoto().consultarByIdAnexo2(bean.getObjeto().getId());
//            if (anexo2Actual.getMaeEstadoCodigo().equals(SolicitudesUrgenciasBean.CODIGO_ESTADO_PENDIENTE)) {
            if (anexo2Actual.getEstado() == AuAnexo2.ESTADO_PENDIENTE_AUDITORIA) {
                if (anexo4Consulta == null) {//No existe Anexo4 (Crear)
                    AuAnexo4 au4 = null;
                    try {
                        if (bean.getObjeto().getEstado() == AuAnexo2.ESTADO_AUTORIZADA) {
                            au4 = crearAnexo4(bean.getObjeto(), bean, AuAnexo4.ESTADO_AUTORIZADA);
                        }
                        bean.auditoriaModificar(bean.getObjeto());
                        getAuAnexo2Remoto().actualizarEstado(bean.getObjeto());
                    } catch (Exception e) {
                        reversarCreacionAnexo4(au4);
                        throw e;
                    }
                    if (bean.getObjeto().getEstado() == AuAnexo2.ESTADO_RECHAZADA_AUDITORIA) {
                        //Cambiar estado de rescate
                        List<AuAnexo2Rescate> rescates = getAuAnexo2RescateRemoto().consultarxAnexo2(bean.getObjeto().getId());
                        for (AuAnexo2Rescate rescate : rescates) {
                            if (rescate.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE) {
                                bean.auditoriaModificar(rescate);
                                rescate.setEstado(AuAnexo2Rescate.ESTADO_RECHAZADO);
                                rescate.setDescripcion(bean.getObjeto().getComentarioEstado());
                                getAuAnexo2RescateRemoto().actualizarEstado(rescate);
                            }
                        }
                    }

                } else {//Ya existia Anexo4 (Actualizar estado anexo2)
                    //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
                    if (anexo4Consulta.getEstado() == AuAnexo4.ESTADO_ANULADA || anexo4Consulta.getEstado() == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO) {
                        bean.getObjeto().setEstado(AuAnexo2.ESTADO_ANULADA);
                    } else {
                        bean.getObjeto().setEstado(AuAnexo2.ESTADO_AUTORIZADA);
                    }
                    bean.auditoriaModificar(bean.getObjeto());
                    getAuAnexo2Remoto().actualizarEstado(bean.getObjeto());
                }
                bean.addMensaje("Se gestionó el estado correctamente");
            } else {
                bean.addError("El registro ya fue gestionado y se encuentra en estado " + anexo2Actual.getEstadoStr());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarAfiliado(SolicitudesUrgenciasBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(1)));
            bean.setRegistrosAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(1)));
        } catch (Exception e) {
            //bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarIPS(SolicitudesUrgenciasBean bean) {

        try {
            boolean consultarXCodidoHabilitacion = false;
            if (!bean.getConexion().getEmpresa().isAdministradora() && bean.getDialogo().equals(SolicitudesUrgenciasBean.DIALOGO_CREAR)) {
                consultarXCodidoHabilitacion = true;
            }

            bean.getParamConsulta(2).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(2).getFiltros().put("fecha", Util.fechaDateAString(new Date()));

            if (consultarXCodidoHabilitacion) {
                bean.getParamConsulta(2).setParametroConsulta1(bean.getConexion().getEmpresa().getCodigoHabilitacion());
            }

            bean.getParamConsulta(2).setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(2)));
            bean.setRegistroIPS(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta(2)));

        } catch (Exception e) {
            //bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarProfesional(SolicitudesUrgenciasBean bean) {
        try {
            CntPrestador prestador = bean.getObjeto().getCntPrestadorSede().getCntPrestador();
            int tipoDocumento = bean.getObjeto().getCntProfesionales().getMaeTipoCodumentoId();
            String documento = bean.getObjeto().getCntProfesionales().getDocumento();
            CntProfesional profesional = getCntProfesionalRemoto().consultarNumDocumento(tipoDocumento, documento);
            if (profesional != null) {
                bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
                profesional.setListaCntProfesionalPrestador(
                        getCntPrestadorProfesionalRemoto().consultarListaPorProfesionalYPrestador(
                                profesional.getId(), prestador.getId()
                        )
                );
                bean.getObjeto().setCntProfesionales(profesional);
                if (!profesional.getListaCntProfesionalPrestador().isEmpty()) {
                    bean.setObjetoProfesionalPrestador(profesional.getListaCntProfesionalPrestador().get(0));
                    bean.getObjeto().setNewProfesional(true);
                } else {
                    bean.getObjeto().setNewProfesional(false);
                }
            } else {
                bean.getObjeto().setCntProfesionales(new CntProfesional());
                bean.getObjeto().getCntProfesionales().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjeto().getCntProfesionales().setDocumento(documento);
                bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
                bean.getObjeto().setNewProfesional(false);
            }
        } catch (Exception e) {
            //bean.addError(e.getMessage());
        }
    }

    private void guardarAdjunto(SolicitudesUrgenciasBean bean) {
        try {
            boolean error = false;
            String ruta = PropApl.getInstance().get(PropApl.REF_RUTA_ADJUNTOS); //#PEND PREGUNTAR CUAL ID ES?
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los adjuntos");
                error = true;
            }
            if (bean.getObjeto().getListaAuSolicitudAdjunto().isEmpty()) {
                bean.addError("No hay adjuntos para guardar");
                error = true;
            }
            if (error) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo;
//            String tipoDocumento = bean.getHashTiposDocumentoPersona().get(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumento()).getValor();
            String tipoDocumento = bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo();
            for (AuSolicitudAdjunto auSolicitudAdjunto : bean.getObjeto().getListaAuSolicitudAdjunto()) {
                nombreArchivo = new StringBuilder();
                nombreArchivo.append(tipoDocumento)
                        .append(bean.getObjeto().getAsegAfiliado().getNumeroDocumento())
                        .append("_")
                        .append(sdf.format(new Date()));
                nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
//                bean.auditoriaGuardar(auAnexo2Adjunto);
                auSolicitudAdjunto.setArchivo(nombreArchivo.append(".").append(auSolicitudAdjunto.getExtension()).toString());
                Maestro maeTipoAdjunto = getMaestroRemoto().consultar(auSolicitudAdjunto.getMaeTipoArchivoId());
                auSolicitudAdjunto.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
                auSolicitudAdjunto.setMaeTipoArchivoId(maeTipoAdjunto.getId());
                auSolicitudAdjunto.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
                //2021-01-23 jyperez se debe mantener el nombre del archivo original en este campo
//                auSolicitudAdjunto.setNombre(auSolicitudAdjunto.getNombre().concat(auSolicitudAdjunto.getExtension()));
                auSolicitudAdjunto.setAuAnexo2(new AuAnexo2(bean.getObjeto().getId()));
                auSolicitudAdjunto.setRuta(ruta);
                auSolicitudAdjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_ANEXO2);
                File archivo = new File(ruta, auSolicitudAdjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(auSolicitudAdjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(auSolicitudAdjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                bean.auditoriaGuardar(auSolicitudAdjunto);
                auSolicitudAdjunto.setAdjuntoStream(null);
                auSolicitudAdjunto.setId(getAuSolicitudAdjuntoRemoto().insertar(auSolicitudAdjunto));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<ReporteAnexo2> generarReporteAnexo2(int id, SolicitudesUrgenciasBean bean) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm:ss");

        List<ReporteAnexo2> listaReportes = new ArrayList();
        ReporteAnexo2 reporte;
        try {
            AuAnexo2 anexo = getAuAnexo2Remoto().consultar(id);
            if (anexo != null) {
                int contador = 1;
                String diagnosticoP = "";
                String descripcionDiagnosticoP = "";
                String diagnostico1 = "";
                String descripcionDiagnostico1 = "";
                String diagnostico2 = "";
                String descripcionDiagnostico2 = "";
                String diagnostico3 = "";
                String descripcionDiagnostico3 = "";
                for (AuAnexo2Diagnostico diagnostico : anexo.getListaAuAnexo2Diagnostico()) {

                    if (diagnostico.isPrincipal()) {
                        diagnosticoP = diagnostico.getMaDiagnosticosCodigo();
                        descripcionDiagnosticoP = diagnostico.getMaDiagnosticosValor();
                    } else {
                        switch (contador) {
                            case 1:
                                diagnostico1 = diagnostico.getMaDiagnosticosCodigo();
                                descripcionDiagnostico1 = diagnostico.getMaDiagnosticosValor();
                                break;
                            case 2:
                                diagnostico2 = diagnostico.getMaDiagnosticosCodigo();
                                descripcionDiagnostico2 = diagnostico.getMaDiagnosticosValor();
                                break;
                            case 3:
                                diagnostico3 = diagnostico.getMaDiagnosticosCodigo();
                                descripcionDiagnostico3 = diagnostico.getMaDiagnosticosValor();
                                break;
                        }
                        contador++;
                    }
                }
                CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultar(anexo.getCntPrestadorSede().getId());
                Ubicacion ubicacionPrestador = bean.getHashUbicaciones().get(prestadorSede.getUbicacionId());
                Ubicacion ubicacionPadre = bean.getHashUbicaciones().get(ubicacionPrestador.getUbicacionPadre().getId());
                ubicacionPrestador.setUbicacionPadre(ubicacionPadre);
                prestadorSede.setUbicacion(ubicacionPrestador);
                anexo.setCntPrestadorSede(prestadorSede);

                CntPrestador prestador = getCntPrestadorRemoto().consultar(prestadorSede.getCntPrestador().getId());
                anexo.getCntPrestadorSede().setCntPrestador(prestador);

                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(anexo.getAsegAfiliado().getId());
                Ubicacion ubicacionAfiliado = bean.getHashUbicaciones().get(afiliado.getResidenciaUbicacion().getId());
                Ubicacion ubicacionAfiliadoPadre = bean.getHashUbicaciones().get(ubicacionAfiliado.getUbicacionPadre().getId());
                ubicacionAfiliado.setUbicacionPadre(ubicacionAfiliadoPadre);
                afiliado.setResidenciaUbicacion(ubicacionAfiliado);
                anexo.setAsegAfiliado(afiliado);
                bean.setObjeto(anexo);
                contactosAfiliado(bean);

                reporte = new ReporteAnexo2();
                reporte.setStrNumeroAtencion(anexo.getId() + "");
                reporte.setStrNumeroIpsAtencion(anexo.getCodigoAtencionIps());
                reporte.setStrFechaAtencion(dateFormat.format(anexo.getFechaHoraCrea()));
                reporte.setStrHoraAtencion(horaFormat.format(anexo.getFechaHoraCrea()));
                reporte.setStrNombrePrestador(anexo.getCntPrestadorSede().getCntPrestador().getRazonSocial());
                reporte.setStrTipoDocPrestador(anexo.getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
                reporte.setStrNumeroDocPrestador(anexo.getCntPrestadorSede().getCntPrestador().getNumeroDocumento());
                reporte.setStrCodigoPrestador(anexo.getCntPrestadorSede().getCodigoHabilitacionSede());
                reporte.setStrDireccionPrestador(anexo.getCntPrestadorSede().getDireccion());
                if(anexo.getCntPrestadorSede().getTelefonoAdministrativo() != null){
                    reporte.setStrTelefonoPrestador(anexo.getCntPrestadorSede().getTelefonoAdministrativo() + " - " + anexo.getCntPrestadorSede().getTelefonoAdministrativo());
                }
                
                reporte.setStrDepartamentoPrestador(anexo.getCntPrestadorSede().getUbicacion().getUbicacionPadre().getNombre() + "     " + anexo.getCntPrestadorSede().getUbicacion().getUbicacionPadre().getPrefijo());
                reporte.setStrMunicipioPrestador(anexo.getCntPrestadorSede().getUbicacion().getNombre() + "     " + anexo.getCntPrestadorSede().getUbicacion().getPrefijo());
                reporte.setStrNombreEntidad(bean.getConexion().getEmpresa().getNombreComercial());
                if (anexo.getAsegAfiliado().getRegimen().equals(SolicitudesUrgenciasBean.REGIMEN_SUBSIDIADO)) {
                    reporte.setStrCodigoEntidad(SolicitudesUrgenciasBean.CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO);
                } else {
                    reporte.setStrCodigoEntidad(SolicitudesUrgenciasBean.CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO);
                }
                reporte.setStrPrimerApellidoPaciente(anexo.getAsegAfiliado().getPrimerApellido());
                reporte.setStrSegundoApellidoPaciente(anexo.getAsegAfiliado().getSegundoApellido());
                reporte.setStrPrimerNombrePaciente(anexo.getAsegAfiliado().getPrimerNombre());
                reporte.setStrSegundoNombrePaciente(anexo.getAsegAfiliado().getSegundoNombre());
                reporte.setStrTipoDocPaciente(anexo.getAsegAfiliado().getMaeTipoDocumentoCodigo());
                reporte.setStrNumDocPaciente(anexo.getAsegAfiliado().getNumeroDocumento());
                reporte.setStrFechaNacPaciente(dateFormat.format(anexo.getAsegAfiliado().getFechaNacimiento()));

                reporte.setStrRegimenPaciente(anexo.getAsegAfiliado().getRegimen());
                reporte.setStrDireccionPaciente(anexo.getAsegAfiliado().getDireccionResidencia());
                reporte.setStrTelefonoFijoPaciente(bean.getTelefonoFijoAfiliado());
                reporte.setStrDepartamentoPaciente(anexo.getAsegAfiliado().getResidenciaUbicacion().getUbicacionPadre().getNombre() + "     " + anexo.getAsegAfiliado().getResidenciaUbicacion().getUbicacionPadre().getPrefijo());
                reporte.setStrMunicipioPaciente(anexo.getAsegAfiliado().getResidenciaUbicacion().getNombre() + "     " + anexo.getAsegAfiliado().getResidenciaUbicacion().getPrefijo());
                reporte.setStrTelefonoCelularPaciente(bean.getTelefonoMovilAfiliado());
                reporte.setStrCorreoPaciente(anexo.getAsegAfiliado().getEmail());
                reporte.setStrConsecutivo(anexo.getConsecutivoGen());
                reporte.setStrDireccionAlternativa(anexo.getDireccionAlternativa());
                reporte.setStrViaIngreso(anexo.getMaeViaIngresoValor());
                reporte.setStrCondicionDestinoValor(anexo.getMaeDestinoPacienteValor());
                reporte.setStrTipoCobertura(anexo.getAsegAfiliado().getRegimen());
                reporte.setStrTipoOrigen(anexo.getMaeOrigenAtencionCodigo());

                String strTriage = "";
                switch (anexo.getTriage()) {
                    case 1:
                        strTriage = "RO";//Rojo
                        break;
                    case 2:
                        strTriage = "NA";//Naranja
                        break;
                    case 3:
                        strTriage = "AM";//Amarillo
                        break;
                    case 4:
                        strTriage = "VE";//Verde
                        break;
                    case 5:
                        strTriage = "AZ";//Azul
                        break;
                }

                reporte.setStrTipoTriage(strTriage);
                reporte.setStrFechaIngreso(dateFormat.format(anexo.getFechaHoraAtencion()));
                reporte.setStrHoraIngreso(horaFormat.format(anexo.getFechaHoraAtencion()));
                reporte.setStrRemitido(anexo.isRemitido() ? "SI" : "NO");

                reporte.setStrNombreSalud(anexo.getCntPrestadorSede().getCntPrestador().getRazonSocial());
                reporte.setStrCodigoSalud(anexo.getCntPrestadorSede().getCodigoHabilitacionSede());
                reporte.setStrDepartamentoSalud(anexo.getCntPrestadorSede().getUbicacion().getUbicacionPadre().getNombre() + "     " + anexo.getCntPrestadorSede().getUbicacion().getUbicacionPadre().getPrefijo());
                reporte.setStrMunicipioSalud(anexo.getCntPrestadorSede().getUbicacion().getNombre() + "     " + anexo.getCntPrestadorSede().getUbicacion().getPrefijo());

                reporte.setStrMotivo(anexo.getMotivo());

                reporte.setStrCodigoDP(diagnosticoP);
                reporte.setStrDescripcionDP(descripcionDiagnosticoP);
                reporte.setStrCodigoDR1(diagnostico1);
                reporte.setStrDescripcionDR1(descripcionDiagnostico1);
                reporte.setStrCodigoDR2(diagnostico2);
                reporte.setStrDescripcionDR2(descripcionDiagnostico2);
                reporte.setStrCodigoDR3(diagnostico3);
                reporte.setStrDescripcionDR3(descripcionDiagnostico3);

                reporte.setStrTipoDestino(anexo.getMaeDestinoPacienteCodigo());
                reporte.setStrNombreInformante(anexo.getInformaNombre());
                reporte.setStrTelefonoInformante(anexo.getInformaTelefono());
                reporte.setStrCargoInformante(anexo.getInformaCargo());
                reporte.setStrMaeOrigenAtencionValor(anexo.getMaeOrigenAtencionValor());
                listaReportes.add(reporte);
            }
        } catch (Exception e) {
            listaReportes = new ArrayList();
            bean.setObjeto(new AuAnexo2());
        }
        return listaReportes;
    }

    @Override
    public AuAnexo4 consultarByIdAnexo2(int idAnexo2) {
        AuAnexo4 anexo4Consulta;
        try {
            anexo4Consulta = getAuAnexo4Remoto().consultarByIdAnexo2(idAnexo2);
        } catch (Exception e) {
            anexo4Consulta = null;
        }
        return anexo4Consulta;
    }

    @Override//esta desabilitado
    public List<ReporteAnexo4> generarReporteAnexo4(int id, SolicitudesUrgenciasBean bean) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm:ss");
        List<ReporteAnexo4> listaReportes = new ArrayList();
        try {
            ReporteAnexo4 reporte = new ReporteAnexo4();
            AuAnexo4 anexo4 = getAuAnexo4Remoto().consultarByIdAnexo2(id);
            if (anexo4 != null) {
                anexo4.setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(anexo4.getId()));

                CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultar(anexo4.getCntPrestadorSedeId().getId());
                Ubicacion ubicacionPrestador = bean.getHashUbicaciones().get(prestadorSede.getUbicacionId());
                Ubicacion ubicacionPadre = bean.getHashUbicaciones().get(ubicacionPrestador.getUbicacionPadre().getId());
                ubicacionPrestador.setUbicacionPadre(ubicacionPadre);
                prestadorSede.setUbicacion(ubicacionPrestador);
                anexo4.setCntPrestadorSedeId(prestadorSede);

                CntPrestador prestador = getCntPrestadorRemoto().consultar(prestadorSede.getCntPrestador().getId());
                anexo4.getCntPrestadorSedeId().setCntPrestador(prestador);

                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(anexo4.getAsegAfiliadoId().getId());
                Ubicacion ubicacionAfiliado = bean.getHashUbicaciones().get(afiliado.getResidenciaUbicacion().getId());
                Ubicacion ubicacionAfiliadoPadre = bean.getHashUbicaciones().get(ubicacionAfiliado.getUbicacionPadre().getId());
                ubicacionAfiliado.setUbicacionPadre(ubicacionAfiliadoPadre);
                afiliado.setResidenciaUbicacion(ubicacionAfiliado);
                anexo4.setAsegAfiliadoId(afiliado);

                if (!anexo4.getAuAnexo4ItemsList().isEmpty()) {
                    for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                        //horaFormat.format(
                        reporte.setStrNumeroAutorizacion(Integer.toString(anexo4.getId()));
                        reporte.setDateFechaAutorizacion(anexo4.getFechaAutorizacion());
                        reporte.setStrHoraAutorizacion(horaFormat.format(anexo4.getFechaAutorizacion()));
                        reporte.setStrEntidadResponsable(anexo4.getCntPrestadorSedeId().getNombreSede());
                        reporte.setStrCodigoEntidad(anexo4.getCntPrestadorSedeId().getCodigoPrestador());
                        reporte.setStrNombrePrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getRazonSocial());
                        reporte.setStrTipoDocPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getMaeTipoDocumentoCodigo());
                        reporte.setStrNumDocPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getNumeroDocumento());
                        reporte.setStrCodigoPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getCodigoMinSalud());
                        reporte.setStrCorreoPrestador(anexo4.getCntPrestadorSedeId().getCorreoElectronico());
                        reporte.setStrTelefono1Prestador(anexo4.getCntPrestadorSedeId().getTelefonoCitas());
                        reporte.setStrTelefono2Prestador(anexo4.getCntPrestadorSedeId().getTelefonoAdministrativo());
                        reporte.setStrDireccionPrestador(anexo4.getCntPrestadorSedeId().getDireccion());
                        reporte.setStrDepartamentoPrestador(bean.getHashUbicacion().get(anexo4.getCntPrestadorSedeId().getUbicacion().getId()).getNombre());
                        reporte.setStrMunicipioPrestador(bean.getHashUbicacion().get(anexo4.getCntPrestadorSedeId().getUbicacion().getId()).getNombre());
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
                        reporte.setStrUbicacionPaciente("U");
                        reporte.setStrManejoIntegral(anexo4.getMaeGuiaManejoIntegralCodigo());
                        reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                        reporte.setStrCantidad("" + item.getCantidadAutorizada());
                        reporte.setStrDescripcion(item.getMaTecnologiaValor());
                        reporte.setStrNumeroOrigen(anexo4.getNumeroPrescripcion());
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
                        reporte.setStrNombreAutoriza(""); //Falta preguntar
                        reporte.setStrCargoAutoriza(""); //Falta preguntar
                        reporte.setStrDias("" + anexo4.getDiasVigencia());
                        reporte.setStrCama(""); //Falta preguntar
                        reporte.setStrServicio(anexo4.getMaServicioHabilitadoCodigo());

                        reporte.setStrCuotaModeradora("NO");
                        reporte.setStrCopago(anexo4.getAplicaCopago() != null ? anexo4.getAplicaCopago() ? "SI" : "NO" : "NO");
                        reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() != null ? anexo4.getAplicaCuotaRecuperacion() ? "SI" : "NO" : "NO");
                        reporte.setStrExcentoCuota(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "SI" : "NO" : "NO");

                        listaReportes.add(reporte);
                    }
                }
            }
        } catch (Exception e) {
            listaReportes = new ArrayList();
        }
        return listaReportes;
    }

    private AuAnexo4 crearAnexo4(AuAnexo2 au2, SolicitudesUrgenciasBean bean, int estado) throws Exception {
        CntPrestador prestador = au2.getCntPrestadorSede().getCntPrestador();
        AuAnexo4 au4 = null;
        try {
            au4 = new AuAnexo4();
            au4.setVersion((au2.getVersion().equals(1)));
            au4.setAuAnexo2Id(bean.getObjeto());
            au4.setDireccionAlternativa(bean.getObjeto().getDireccionAlternativa());
            if (au2.getCntPrestadorSede() != null) {
                try {
                    Empresa emp = getEmpresaRemoto().consultarPorPrestador(au2.getCntPrestadorSede().getCntPrestador().getId());
                    if (emp != null) {
                        au4.setGnEmpresaId(emp);
                    }
                } catch (Exception e) {
                    au4.setGnEmpresaId(null);
                }
            } else {
                au4.setGnEmpresaId(null);
            }
            au4.setAsegAfiliadoId(au2.getAsegAfiliado());
            au4.setSemanasAfiliacion(DateUtil.getTotalSemanasActuales(au2.getAsegAfiliado().getFechaAfiliacionEps()));
            //Afiliados         
            au4.setAfiliadoTipoDocumento(au2.getAsegAfiliado().getMaeTipoDocumentoCodigo());
            au4.setAfiliadoNumeroDocumento(au2.getAsegAfiliado().getNumeroDocumento());
            au4.setAfiliadoPrimerApellido(au2.getAsegAfiliado().getPrimerApellido());
            au4.setAfiliadoSegundoApellido(au2.getAsegAfiliado().getSegundoApellido());
            au4.setAfiliadoPrimerNombre(au2.getAsegAfiliado().getPrimerNombre());
            au4.setAfiliadoSegundoNombre(au2.getAsegAfiliado().getSegundoNombre());
            au4.setAfiliadoFechaNacimiento(au2.getAsegAfiliado().getFechaNacimiento());
            au4.setAfiliadoUbicacion(au2.getAsegAfiliado().getAfiliacionUbicacion().getId());
            au4.setAfiliadoDepartamento(bean.obtenerDepartamento(au2.getAsegAfiliado().getAfiliacionUbicacion().getId()));
            au4.setAfiliadoMunicipio(bean.obtenerMunicipio(au2.getAsegAfiliado().getAfiliacionUbicacion().getId()));
            au4.setAfiliadoDireccion(au2.getAsegAfiliado().getDireccionResidencia());
            au4.setAfiliadoTelefono(bean.getTelefonoFijoAfiliado());
            au4.setAfiliadoCelular(bean.getTelefonoMovilAfiliado());
            au4.setAfiliadoCorreo(au2.getAsegAfiliado().getEmail());
            au4.setCntPrestadorSedeId(au2.getCntPrestadorSede());
//                CntPrestador prestador = getCntPrestadorRemoto().consultar(au2.getCntPrestadorSede().getCntPrestador().getId());           
            au4.setPrestadorTipoDocumento(prestador.getMaeTipoDocumentoCodigo());
            au4.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
            au4.setPrestadorNombre(au2.getCntPrestadorSede().getNombreSede());
            au4.setPrestadorCodigoHabilitacion(au2.getCntPrestadorSede().getCodigoHabilitacionSede());
            au4.setPrestadorTelefonoCita(au2.getCntPrestadorSede().getTelefonoCitas());
            au4.setPrestadorDireccion(au2.getCntPrestadorSede().getDireccion());
            au4.setPrestadorDepartamento(bean.obtenerDepartamento(au2.getCntPrestadorSede().getUbicacionId()));
            au4.setPrestadorMunicipio(bean.obtenerMunicipio(au2.getCntPrestadorSede().getUbicacionId()));
            bean.auditoriaGuardar(au4);
            Maestro mae_causa;
            if (estado == AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO) {
                au4.setUsuarioCrea("Sistema");
                au4.setNombreAutoriza("Sistema");
                au4.setObservacion(SolicitudesUrgenciasBean.AU4_OBSERVACION_1);
                au4.setMedioAutorizacion(AuAnexo4.MEDIO_AUTOMATICA);
                mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUTOMATICO);
            } else {
                au4.setNombreAutoriza(au2.getUsuarioCrea());
                au4.setObservacion(SolicitudesUrgenciasBean.AU4_OBSERVACION_0);
                au4.setMedioAutorizacion(AuAnexo4.MEDIO_MANUAL);
                mae_causa = getMaestroRemoto().consultar(AuAnexo4Historico.ESTADO_APROBADO_AUDITORIA);
            }
            au4.setCargoActividadAutoriza(SolicitudesUrgenciasBean.CARGO_AUTORIZA);

            Empresa savia = getEmpresaRemoto().consultar(1);
            au4.setEpsTelefono(SolicitudesUrgenciasBean.EPS_TELEFONO);
            au4.setEntidadPago(savia.getRazonSocial());
            String codigo = SolicitudesUrgenciasBean.CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO;
            if (au2.getAsegAfiliado().getRegimen().equals("1")) {
                codigo = SolicitudesUrgenciasBean.CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO;
            }
            au4.setCodigoEntidadPago(codigo);
            au4.setAplicaFactura(false);
            au4.setAplicaNobps(false);
            au4.setAplicaPac(false);
            au4.setAplicaTopeMaximo(false);
            au4.setAplicaNoRed(false);
            au4.setAplicaAutorizacionAutomatica(false);
            au4.setAplicaTiqueteBonoVale(false);
            au4.setAplicaCapitaRecobro(false);
            Date fechaAfiliacion = au2.getAsegAfiliado().getFechaAfiliacionEps();
            Date fechaActual = new Date();
            float diff = fechaActual.getTime() - fechaAfiliacion.getTime();
            diff = diff / (24 * 60 * 60 * 1000);
            float semanas = diff / 7;
            au4.setSemanasAfiliacion((int) semanas);
            au4.setNumeroPrescripcion("" + au2.getId());
            au4.setImpresionesRealizadas(0);

            au4.setDiagnosticoPrincipal(
                    au2.getListaAuAnexo2Diagnostico().stream()
                            .filter(diagnostico -> diagnostico.isPrincipal())
                            .findFirst()
                            .map(diagnostico -> diagnostico.getMaDiagnosticosCodigo() + " - " + diagnostico.getMaDiagnosticosValor())
                            .orElse(null)
            );
            for (Maestro reg : bean.getListaRegimen()) {
                if (Integer.parseInt(reg.getValor()) == Integer.parseInt(au2.getAsegAfiliado().getRegimen())) {
                    au4.setMaeRegimenId(reg.getId());
                    au4.setMaeRegimenCodigo(reg.getValor());
                    au4.setMaeRegimenValor(reg.getNombre());
                    break;
                }
            }
            for (Maestro amb : bean.getListaAmbitoAtencion()) {
                if (SolicitudesUrgenciasBean.AMBITO_URGENCIA_VALOR.equalsIgnoreCase(amb.getValor())) {
                    au4.setMaeAmbitoAtencionId(amb.getId());
                    au4.setMaeAmbitoAtencionCodigo(amb.getValor());
                    au4.setMaeAmbitoAtencionValor(amb.getNombre());
                    break;
                }
            }
            if (au2.getTipo() == 0) { //Medica
                Maestro manejoIntegral = bean.getHashManejoIntegral().get("0");
                au4.setMaeGuiaManejoIntegralId(manejoIntegral.getId());
                au4.setMaeGuiaManejoIntegralCodigo(manejoIntegral.getValor());
                au4.setMaeGuiaManejoIntegralValor(manejoIntegral.getNombre());
            } else {//Odontologica
                Maestro manejoIntegral = bean.getHashManejoIntegral().get("0");
                au4.setMaeGuiaManejoIntegralId(manejoIntegral.getId());
                au4.setMaeGuiaManejoIntegralCodigo(manejoIntegral.getValor());
                au4.setMaeGuiaManejoIntegralValor(manejoIntegral.getNombre());
            }
            au4.setAplicaFactura(false);
            au4.setAplicaNobps(false);
            au4.setAplicaPac(false);
            au4.setAplicaCuotaModeradora(false);
            au4.setAplicaCopago(false);
            au4.setAplicaCuotaRecuperacion(false);
            au4.setAplicaOtro(false);
            au4.setAplicaAltocosto(false);
            au4.setAplicaTutela(false);
            au4.setAplicaTopeMaximo(false);
            au4.setAplicaNoRed(false);
            au4.setAplicaAutorizacionAutomatica(false);
            au4.setAplicaTiqueteBonoVale(false);
            au4.setAplicaCapitaRecobro(false);
            au4.setValorCuotaModeradora(BigDecimal.ZERO);
            au4.setPorcentajeRecuperacion(BigDecimal.ZERO);
            au4.setValorCopago(BigDecimal.ZERO);
            MaServicioHabilitacion servicioHab = bean.getHashMaServicioHabilitacionItems().get(SolicitudesUrgenciasBean.CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS);
            au4.setMaServicioHabilitadoId(servicioHab.getId());
            au4.setMaServicioHabilitadoCodigo(servicioHab.getCodigo() + "");
            au4.setMaServicioHabilitadoValor(servicioHab.getNombre());
            Date fInicio = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(fInicio);
            c.add(Calendar.DATE, bean.getDiasVigencia());
            Date fFin = c.getTime();
            au4.setFechaInicio(fInicio);
            au4.setFechaFin(fFin);
            au4.setDiasVigencia(bean.getDiasVigencia());
            au4.setPosfechada(false);
            au4.setFechaAutorizacion(fInicio);
            au4.setNumeroAutorizacion("0");
            au4.setEstado(estado);
            au4.setEstadoJustificacion(au2.getComentarioEstado());
            //guarda anexo 4
            au4.setId(getAuAnexo4Remoto().insertar(au4));
            //Guardar AuAnexos4Items
            if (!au2.getListaAuAnexo2Item().isEmpty()) {
                List<AuAnexo4Item> listaAuAnexo4Item = new ArrayList();
                for (AuAnexo2Item auAnexos2Item : au2.getListaAuAnexo2Item()) {
                    AuAnexo4Item auAnexos4Item = new AuAnexo4Item();
                    auAnexos4Item.setAuAnexo4Id(new AuAnexo4(au4.getId()));
                    auAnexos4Item.setTipoTecnologia(1);//Cups
                    auAnexos4Item.setCantidadAutorizada(1);
                    auAnexos4Item.setAuAnexo2ItemId(auAnexos2Item);

                    auAnexos4Item.setMaTecnologiaId(auAnexos2Item.getMaTecnologiaId());
                    auAnexos4Item.setMaTecnologiaCodigo(auAnexos2Item.getMaTecnologiaCodigo());
                    auAnexos4Item.setMaTecnologiaValor(auAnexos2Item.getMaTecnologiaValor());
                    //asignar valorizacion
                    auAnexos4Item = asignarTecnologiaCostoAnexo4Item(
                            auAnexos4Item, auAnexos2Item.getMaTecnologiaCodigo(), bean.getCodigosServicioHabilitacion(), au4
                    );

                    bean.auditoriaGuardar(auAnexos4Item);
                    auAnexos4Item.setId(getAuAnexo4ItemRemoto().insertar(auAnexos4Item));
                    listaAuAnexo4Item.add(auAnexos4Item);
                    au4.setAuAnexo4ItemsList(listaAuAnexo4Item);
                }
            }
            au4 =  getAuAnexo4Remoto().consultar(au4.getId());
            //Archivo adjunto
            String ruta = PropApl.getInstance().get(PropApl.AU_A4_ANEXOS);
            au4.setArchivo(AuConstantes.nombreReporteAnexo4(au4));
            au4.setArchivoNombre(AuConstantes.nombreArchivoReporteAnexo4(au4));
            AuReporte reporte = new AuReporte(bean.getHashUbicaciones());
            String mensaje = reporte.generarReporteAnexo4(ruta, au4);
            //StreamedContent streamedContentAnexo4 = bean.generarReporteAnexo4(au4.getAuAnexo2Id().getId());
            //bean.almacenarReporteDisco(streamedContentAnexo4, nombreArchivoBuilder.toString(), ruta);
            if (!mensaje.equals("")) {
                bean.addMensaje(mensaje);
            }
            au4.setRuta(ruta);
            getAuAnexo4Remoto().actualizarArchivoReporte(au4);
            //historico
            AuAnexo4Historico historico = new AuAnexo4Historico();
            historico.setAuAnexos4Id(au4);
            historico.setEstado(au4.getEstado());
            historico.setMaeCausaId(mae_causa.getId());
            historico.setMaeCausaCodigo(mae_causa.getValor());
            historico.setMaeCausaValor(mae_causa.getNombre());
            historico.setObservacionAutorizacion(mae_causa.getNombre());
            bean.auditoriaGuardar(historico);
            if (estado == AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO) {
                historico.setUsuarioCrea("Sistema");
            }
            getAuAnexo4HistoricoRemoto().insertar(historico);
        } catch (Exception e) {
            Log.getInstance().error("Generación Anexo4 a partir del Anexo2", "ERROR al intentar crear el Anexo4 a partir del Anexo2", e);
            throw new Exception("ERROR al intentar crear el Anexo4");
        }
        return au4;
    }

    private AuAnexo4Item asignarTecnologiaCostoAnexo4Item(AuAnexo4Item auAnexos4Item, String codigoUrgencia, String serviciosHabilitacion, AuAnexo4 au4) throws Exception {
        CntContratoDetalle cntContratoDetalle = null;
        String modalidadContrato;
        //verifica que la sede primaria del afiliado sea la misma del anexo
        if (au4.getAsegAfiliadoId().getPrimariaPrestadorSede() != null
                && Objects.equals(au4.getAsegAfiliadoId().getPrimariaPrestadorSede().getId(), au4.getCntPrestadorSedeId().getId())) {
            modalidadContrato = CntContratoSede.MODALIDAD_CAPITA;
            cntContratoDetalle = getContratoDetalleRemoto().consultarPorSedeTecnologiaTipoYServicioHabilitacion(
                    au4.getCntPrestadorSedeId().getId(), codigoUrgencia, serviciosHabilitacion, 1, modalidadContrato
            );
        }

        if (cntContratoDetalle == null) {
            modalidadContrato = CntContratoSede.MODALIDAD_EVENTO;
            cntContratoDetalle = getContratoDetalleRemoto().consultarPorSedeTecnologiaTipoYServicioHabilitacion(
                    au4.getCntPrestadorSedeId().getId(), codigoUrgencia, serviciosHabilitacion, 1, modalidadContrato
            );
        }

        if (cntContratoDetalle != null && cntContratoDetalle.getValorContratado() != null) {
            auAnexos4Item.setCostoServicio(cntContratoDetalle.getValorContratado());
        } else {//si no tiene valor busca por manual tarifario SOAT
            MaSoatTarifarioValor maSoatTarifarioValor = getTarifarioValorRemoto().consultarTarifarioTecnolgia(codigoUrgencia);
            if (maSoatTarifarioValor != null && maSoatTarifarioValor.getValor() != null) {
                auAnexos4Item.setCostoServicio(maSoatTarifarioValor.getValor());
            } else {
                auAnexos4Item.setCostoServicio(BigDecimal.ZERO);
            }
        }
        return auAnexos4Item;
    }

    private void reversarCreacionAnexo4(AuAnexo4 au4) {
        try {
            if (au4 != null && au4.getId() != null) {
                getAuAnexo4ItemRemoto().eliminarPorIdAnexo4(au4.getId());
                getAuAnexo4Remoto().eliminar(au4.getId());
            }
        } catch (Exception ex) {
            Log.getInstance().error("ERROR", "ERROR reversando el proceso de Anexo4", ex);
        }
    }

    private boolean validarEstadoAfiliado(int maeEstadoAfiliacion, SolicitudesUrgenciasBean bean) {
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
    public void asignarAfiliado(int idAfiliacion, SolicitudesUrgenciasBean bean) {
        try {
            //consulta afiliado completo
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(idAfiliacion);
            boolean isActivo = validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), bean);
            bean.setIsActivoAfiliado(isActivo);
            if (isActivo) {
                bean.getObjeto().setAsegAfiliado(afiliado);
                bean.getParamConsulta(1).setFiltros(new HashMap<>());
                bean.setColorEstadoAfiliado(bean.obtenerColorEstado());
                contactosAfiliado(bean);
            }
        } catch (Exception ex) {
            bean.addError("Hubo un seleccionando el afiliado, intentelo nuevamente");
        }
    }

    @Override
    public void verRescatesAnexo2(SolicitudesUrgenciasBean bean) {
        try {
            bean.getObjeto().setListaAuAnexo2Rescate(
                    getAuAnexo2RescateRemoto().consultarxAnexo2(bean.getObjeto().getId())
            );
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los rescates");
        }
    }

    @Override
    public void verRescate(SolicitudesUrgenciasBean bean) {
        try {
            bean.setObjetoRescate(getAuAnexo2RescateRemoto().consultar(bean.getObjetoRescate().getId()));
            bean.getObjetoRescate().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjetoRescate().getAsegAfiliado().getFechaNacimiento()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando el rescate");
        }
    }

    private void contactosAfiliado(SolicitudesUrgenciasBean bean) {
        //09-06-2022 ivanegaa
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

    private void crearAnexo2Rescate(SolicitudesUrgenciasBean bean) {
        try {
            List<PeAfiliadosPrograma> programasAfiliado = getPeAfiliadosProgramaRemoto().consultarAfiliados(bean.getObjeto().getAsegAfiliado().getId());
            for (PeAfiliadosPrograma programaAfiliado : programasAfiliado) {
                if (programaAfiliado.getActivo() && !programaAfiliado.getCntPrestadorSede().getId().equals(CntPrestadorSede.ID_SEDE_SIN_ESPECIFICAR)) {
                    PePrograma programa = getPeProgramaRemoto().consultar(programaAfiliado.getPePrograma().getId());
                    if (programa.getAplicaRescateAnexo2Urgencia() && programa.isActivo()) {
                        AuAnexo2Rescate rescate = new AuAnexo2Rescate();
                        rescate.setAuAnexo2(bean.getObjeto());
                        rescate.setFuenteOrigen(AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO2);
                        rescate.setPePrograma(programa);
                        rescate.setMotivoConsulta(bean.getObjeto().getMotivo());
                        rescate.setTipoRescate(AuTipoRescate.GESTION_RIESGO.getId());
                        //afiliado
                        rescate.setAsegAfiliado(bean.getObjeto().getAsegAfiliado());
                        rescate.setMaeAfiliadoTipoDocumentoId(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumento());
                        rescate.setMaeAfiliadoTipoDocumentoCodigo(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo());
                        rescate.setMaeAfiliadoTipoDocumentoValor(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoValor());
                        rescate.setAfiliadoNumeroDocumento(bean.getObjeto().getAsegAfiliado().getNumeroDocumento());
                        rescate.setAfiliadoPrimerNombre(bean.getObjeto().getAsegAfiliado().getPrimerNombre());
                        rescate.setAfiliadoSegundoNombre(bean.getObjeto().getAsegAfiliado().getSegundoNombre());
                        rescate.setAfiliadoPrimerApellido(bean.getObjeto().getAsegAfiliado().getPrimerApellido());
                        rescate.setAfiliadoSegundoApellido(bean.getObjeto().getAsegAfiliado().getSegundoApellido());
                        //sedes
                        rescate.setCntPrestadorOrigen(bean.getObjeto().getCntPrestadorSede().getCntPrestador());
                        rescate.setCntPrestadorSedeOrigen(bean.getObjeto().getCntPrestadorSede());
                        CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultar(programaAfiliado.getCntPrestadorSede().getId());
                        rescate.setCntPrestadorDestino(prestadorSede.getCntPrestador());
                        rescate.setCntPrestadorSedeDestino(prestadorSede);
                        if (prestadorSede.getId().equals(bean.getObjeto().getCntPrestadorSede().getId())) {
                            rescate.setEstado(AuAnexo2Rescate.ESTADO_NO_REQUIERE);
                        } else {
                            rescate.setEstado(AuAnexo2Rescate.ESTADO_PENDIENTE);
                        }
                        Empresa emp = getEmpresaRemoto().consultarPorPrestador(prestadorSede.getCntPrestador().getId());
                        if (emp == null) {
                            emp = new Empresa(AuConstantes.EMPRESA_SAVIA);
                        }
                        rescate.setEmpresa(emp);
                        bean.auditoriaGuardar(rescate);
                        rescate.setId(getAuAnexo2RescateRemoto().insertar(rescate));
                        //crear gestion
                        AuAnexo2RescateGestion gestionCambio = new AuAnexo2RescateGestion();
                        gestionCambio.setAuAnexo2Rescate(rescate);
                        gestionCambio.setTipo(AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO);
                        gestionCambio.setObservacion(rescate.getEstadoStr());
                        gestionCambio.setFechaHoraGestion(new Date());
                        bean.auditoriaGuardar(gestionCambio);
                        getAuAnexo2RescateGestionRemoto().insertar(gestionCambio);
                        if (rescate.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE) {
                            List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto().consultarPorCntContratoSedeTipoYArea(
                                    prestadorSede.getCntPrestador().getId(), prestadorSede.getId(), AuConstantes.TIPO_CONTACTO_CORREO, AuConstantes.AREA_CONTACTO_CRUE
                            );
                            CntPrestadorContacto correo = contactos.stream()
                                    .filter(c -> c.isActivo() && c.isAutorizaEnvio())
                                    .findFirst().orElse(null);
                            if (correo != null) {
                                String encabezado = "Paciente para rescate";
                                StringBuilder mensaje = new StringBuilder();
                                mensaje.append("Ingresa Anexo 2 de paciente ");
                                mensaje.append(rescate.getAsegAfiliado().getNombreCompleto());
                                mensaje.append(" con ID ");
                                mensaje.append(rescate.getAsegAfiliado().getNumeroDocumento());
                                mensaje.append(" a la IPS ");
                                mensaje.append(rescate.getCntPrestadorSedeOrigen().getNombreSede());
                                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_CRUE_SOLICITUDES, correo.getContacto(), encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                                getGnCorreoEnvioRemoto().insertar(envio);
                            }
                            bean.addMensaje("El afiliado aplica para rescate ".concat(programa.getDescripcionPrograma()));
                        }
                    }
                }

            }
        } catch (Exception e) {
            bean.addError("Hubo un error creando el rescate del afiliado. ".concat(e.getMessage()));
        }
    }
    
    @Override
    public void completarMaestro(SolicitudesUrgenciasBean bean) {
        try {
            bean.setListaOrigen(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_ORIGEN_ATENCION_RESOLUCION_3047));
            bean.setHashOrigen(AuConstantes.obtenerHashMaestro(bean.getListaOrigen()));
            bean.setListaDestino(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.CR_A2_DESTINO_PACIENTE_RESOLUCION_3047));
            bean.setHashDestino(AuConstantes.obtenerHashMaestro(bean.getListaDestino()));         
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void completarMaestroVersion2335(SolicitudesUrgenciasBean bean) {
        try {
            bean.setListaOrigen(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GN_ORIGEN_ATENCION_RESOLUCION_2335));
            bean.setHashOrigen(AuConstantes.obtenerHashMaestro(bean.getListaOrigen()));
            bean.setListaDestino(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.CR_A2_DESTINO_PACIENTE_RESOLUCION_2335));
            bean.setHashDestino(AuConstantes.obtenerHashMaestro(bean.getListaDestino()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crearAnexo2RescateSede(SolicitudesUrgenciasBean bean) {
        try {
            Integer idPrestadorSedeCapita = bean.getObjeto().getAsegAfiliado().getPrimariaPrestadorSede().getId();
            Integer idPrestadorSedeOrigen = bean.getObjeto().getCntPrestadorSede().getId();
            AuAnexo2RescateSede rescateSede = getAuAnexo2RescateSedeRemoto().consultarIdSedeCapitaIdSedeOrigen(idPrestadorSedeCapita, idPrestadorSedeOrigen);
            if (rescateSede != null && rescateSede.isActivo() && rescateSede.isAplicaRescateAnexo2() 
                    && bean.getObjeto().getAsegAfiliado().getModeloLiquidacion().equals("0")) { //modelo de liquidacion capita       
                AuAnexo2Rescate rescate = new AuAnexo2Rescate();
                rescate.setAuAnexo2(bean.getObjeto());
                rescate.setFuenteOrigen(AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO2);
                rescate.setMotivoConsulta(bean.getObjeto().getMotivo());
                rescate.setTipoRescate(AuTipoRescate.GESTION_CAPITA.getId());
                //afiliado
                rescate.setAsegAfiliado(bean.getObjeto().getAsegAfiliado());
                rescate.setMaeAfiliadoTipoDocumentoId(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumento());
                rescate.setMaeAfiliadoTipoDocumentoCodigo(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo());
                rescate.setMaeAfiliadoTipoDocumentoValor(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoValor());
                rescate.setAfiliadoNumeroDocumento(bean.getObjeto().getAsegAfiliado().getNumeroDocumento());
                rescate.setAfiliadoPrimerNombre(bean.getObjeto().getAsegAfiliado().getPrimerNombre());
                rescate.setAfiliadoSegundoNombre(bean.getObjeto().getAsegAfiliado().getSegundoNombre());
                rescate.setAfiliadoPrimerApellido(bean.getObjeto().getAsegAfiliado().getPrimerApellido());
                rescate.setAfiliadoSegundoApellido(bean.getObjeto().getAsegAfiliado().getSegundoApellido());
                //sedes
                rescate.setCntPrestadorOrigen(bean.getObjeto().getCntPrestadorSede().getCntPrestador());
                rescate.setCntPrestadorSedeOrigen(bean.getObjeto().getCntPrestadorSede());
               
                CntPrestadorSede prestadorSedeDestino = getCntPrestadorSedeRemoto().consultar(idPrestadorSedeCapita);
                rescate.setCntPrestadorDestino(prestadorSedeDestino.getCntPrestador());
                rescate.setCntPrestadorSedeDestino(prestadorSedeDestino);
                
                if (prestadorSedeDestino.getId().equals(bean.getObjeto().getCntPrestadorSede().getId())) {
                    rescate.setEstado(AuAnexo2Rescate.ESTADO_NO_REQUIERE);
                } else {
                    rescate.setEstado(AuAnexo2Rescate.ESTADO_PENDIENTE);
                }

                Empresa emp = getEmpresaRemoto().consultarPorPrestador(prestadorSedeDestino.getCntPrestador().getId());
                if (emp == null) {
                    emp = new Empresa(AuConstantes.EMPRESA_SAVIA);
                }
                rescate.setEmpresa(emp);
                bean.auditoriaGuardar(rescate);
                rescate.setId(getAuAnexo2RescateRemoto().insertar(rescate));
               
                //crear gestion
                AuAnexo2RescateGestion gestionCambio = new AuAnexo2RescateGestion();
                gestionCambio.setAuAnexo2Rescate(rescate);
                gestionCambio.setTipo(AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO);
                gestionCambio.setObservacion(rescate.getEstadoStr());
                gestionCambio.setFechaHoraGestion(new Date());
                bean.auditoriaGuardar(gestionCambio);
                getAuAnexo2RescateGestionRemoto().insertar(gestionCambio);
                if (rescate.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE) {
                    List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto().consultarPorCntContratoSedeTipoYArea(
                            prestadorSedeDestino.getCntPrestador().getId(), prestadorSedeDestino.getId(), AuConstantes.TIPO_CONTACTO_CORREO, AuConstantes.AREA_CONTACTO_CRUE
                    );
                    CntPrestadorContacto correo = contactos.stream()
                            .filter(c -> c.isActivo() && c.isAutorizaEnvio())
                            .findFirst().orElse(null);
                    if (correo != null) {
                        String encabezado = "Paciente para rescate";
                        StringBuilder mensaje = new StringBuilder();
                        mensaje.append("Ingresa Anexo 2 de paciente ");
                        mensaje.append(rescate.getAsegAfiliado().getNombreCompleto());
                        mensaje.append(" con ID ");
                        mensaje.append(rescate.getAsegAfiliado().getNumeroDocumento());
                        mensaje.append(" a la IPS ");
                        mensaje.append(rescate.getCntPrestadorSedeOrigen().getNombreSede());
                        GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_CRUE_SOLICITUDES, correo.getContacto(), encabezado, mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
                        getGnCorreoEnvioRemoto().insertar(envio);
                    }
                    bean.addMensaje("El afiliado aplica para rescate. ");
                }
            }
        } catch (Exception e) {
             bean.addError("Hubo un error creando el rescate del afiliado. ".concat(e.getMessage()));
        }
        
    }

}
