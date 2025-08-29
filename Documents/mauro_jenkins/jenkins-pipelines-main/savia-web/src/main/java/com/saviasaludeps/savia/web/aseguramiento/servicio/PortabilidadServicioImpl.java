/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import com.saviasaludeps.savia.web.aseguramiento.bean.PortabilidadBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoCertificadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoHistoricoRemoto;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.CorreoEstadoPortabilidad;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PortabilidadServicioImpl extends AccionesBO implements PortabilidadServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private PortabilidadRemoto getPortabilidadRemoto() throws Exception {
        return (PortabilidadRemoto) RemotoEJB.getEJBRemoto(("PortabilidadServicio"), PortabilidadRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    private AfiliadoHistoricoRemoto getAfiliadoHistoricoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoHistoricoServicio", AfiliadoHistoricoRemoto.class.getName());
        return (AfiliadoHistoricoRemoto) object;
    }
    
    private AfiliadoCertificadoRemoto getAfiliadoCertificadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoCertificadoServicio", AfiliadoCertificadoRemoto.class.getName());
        return (AfiliadoCertificadoRemoto) object;
    }

    @Override
    public void Accion(PortabilidadBean bean) {
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
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    //editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    //modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    //generarCertificado(bean);
                    reportePortabilidad(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(PortabilidadBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPortabilidadRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPortabilidadRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<CntPrestadorSede> listarSedesPorMunicipio(String divipoliMunicipio) throws Exception{
        return getPrestadoresRemoto().consultarListaSedes(divipoliMunicipio);
    }

    @Override
    public AsegAfiliado consultarAfiliado(int tipoDocumento, String numeroDocumento) throws Exception{
        AsegAfiliado asegAfiliados = getPortabilidadRemoto().consultarAfiliado(tipoDocumento, numeroDocumento);
            if (asegAfiliados == null) {
                throw new Exception("No se encontro ningún afiliado");
            } else {
                if (asegAfiliados.getId() != null && asegAfiliados.getMaeEstadoAfiliacion() != AfiliadoParametro.IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION) {
                    throw new Exception("El afiliado con número de documento " + asegAfiliados.getNumeroDocumento() + ", no se encuentra en estado activo");
                }
                List<AsegPortabilidad> listPortabilidad = getPortabilidadRemoto().consultarPorAfiliado(asegAfiliados.getId());
                if (!listPortabilidad.isEmpty()) {
                    for (AsegPortabilidad asegPortabilidad : listPortabilidad) {
                        if (asegPortabilidad.getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_APROBADA)) {
                            throw new Exception("El Afiliado con número de documento " + asegAfiliados.getNumeroDocumento() + ", tiene una portabilidad en estado aprobada; si desea generar una nueva portabilidad deberá cancelar la actual.");
                        }
                    }
                }
            }
            return asegAfiliados;        
    }


    private void ver(PortabilidadBean bean) {
        try {
            bean.setObjeto(getPortabilidadRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(PortabilidadBean bean) {
        try {
            bean.setObjeto(new AsegPortabilidad());
            bean.getObjeto().setAsegAfiliado(new AsegAfiliado());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(PortabilidadBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setEnvioCorreo(AsegPortabilidad.ENVIO_CORREO_NOAPLICA);
            bean.getObjeto().setId(getPortabilidadRemoto().insertar(bean.getObjeto()));
            //Actualizar afiliado            
            if (bean.getObjeto().getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_APROBADA)) {
                bean.auditoriaModificar(bean.getObjeto().getAsegAfiliado());
                bean.getObjeto().getAsegAfiliado().setTienePortabilidad(true);
                bean.getObjeto().getAsegAfiliado().setFechaPortabilidad(bean.getObjeto().getPeriodoFinal());
                if (bean.getObjeto().getCntPrestadorSede() != null) {
                    bean.getObjeto().getAsegAfiliado().setPortabilidadPrestadorSede(new CntPrestadorSede(bean.getObjeto().getCntPrestadorSede().getId()));
                }
                //2025-07-28 jyperez CORRECCIÓN POR FALLO EN PDN Validación Portabilidad Modelo de liquidación
                // debemos validar que para las portabilidades hacia Medellín no se hace cambio de modelo de liquidación
                if (bean.getObjeto().getUbicacion().getId() != AfiliadoParametro.IDENTIFICADOR_MEDELLIN) {
                    bean.getObjeto().getAsegAfiliado().setModeloLiquidacion(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
                    //2022-02-10 actualización campos modelo liquidación
                    Maestro maestro = bean.getHashValorModeloLiquidacion().get(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
                    if (maestro != null) {
                        bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionId(maestro.getId());
                        bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionCodigo(maestro.getValor());
                        bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionValor(maestro.getNombre());
                    }
                }
                //2024-10-07 jyperez actualizamos los valores de los nuevos campos
                bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadId(bean.getObjeto().getMaeTipoPortabilidadId());
                bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadCodigo(bean.getObjeto().getMaeTipoPortabilidadCodigo());
                bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadValor(bean.getObjeto().getMaeTipoPortabilidadValor());
                bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadId(bean.getObjeto().getMaeMotivoPortabilidadId());
                bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadCodigo(bean.getObjeto().getMaeMotivoPortabilidadCodigo());
                bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadValor(bean.getObjeto().getMaeMotivoPortabilidadValor());
                bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadId(bean.getObjeto().getMaeOrigenSolicitudId());
                bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadCodigo(bean.getObjeto().getMaeOrigenSolicitudCodigo());
                bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadValor(bean.getObjeto().getMaeOrigenSolicitudValor());
                bean.getObjeto().getAsegAfiliado().setPeriodoInicialPortabilidad(bean.getObjeto().getPeriodoInicial());
                bean.getObjeto().getAsegAfiliado().setPeriodoFinalPortabilidad(bean.getObjeto().getPeriodoFinal());
                bean.getObjeto().getAsegAfiliado().setTelefonoContactoPortabilidad(bean.getObjeto().getTelefonoContacto());
                bean.getObjeto().getAsegAfiliado().setDireccionPortabilidad(bean.getObjeto().getDireccion());
                bean.getObjeto().getAsegAfiliado().setCorreoElectronicoPortabilidad(bean.getObjeto().getCorreoElectronico());
                bean.getObjeto().getAsegAfiliado().setObservacionPortabilidad(bean.getObjeto().getObservacionAseguramiento());
                getAfiliadoRemoto().actualizarAfiliadoPortabilidad(bean.getObjeto().getAsegAfiliado());
                // 2020-11-20 jyperez 
                guardarHistorialAfiliado(bean);
            }            
            bean.addMensaje("Portabilidad con radicado #" + bean.getObjeto().getId() + " fue guardada de manera exitosa con estado " + bean.getEstadoPortabilidad(bean.getObjeto().getMaeEstadoPortabilidadId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(PortabilidadBean bean) {
        try {
            bean.setObjeto(getPortabilidadRemoto().consultar(bean.getObjeto().getId()));
            Date fecha = bean.getObjeto().getFechaSolicitudProrroga() == null ? new Date()
                                    : bean.getObjeto().getFechaSolicitudProrroga();
             bean.getObjeto().setFechaSolicitudProrroga(fecha);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(PortabilidadBean bean) {
        try {
            if(bean.getObjeto().getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_CANCELADA_USUARIO)){
                bean.getObjeto().setFechaCancelacion(new Date());
                bean.getObjeto().setUsuarioCancela(bean.getConexion().getUsuario().getUsuarioNombre());
            }
            bean.auditoriaModificar(bean.getObjeto());
            getPortabilidadRemoto().actualizar(bean.getObjeto());
            switch (bean.getObjeto().getMaeEstadoPortabilidadCodigo()) {
                case AsegPortabilidad.ESTADO_CANCELADA_USUARIO:
                    bean.getObjeto().setUsuarioCancela(bean.getObjeto().getUsuarioModifica());
                    if (bean.getObjeto().getEstadoInicialPortab().equals(AsegPortabilidad.ESTADO_APROBADA)) {
                        bean.auditoriaModificar(bean.getObjeto().getAsegAfiliado());
                        bean.getObjeto().getAsegAfiliado().setTienePortabilidad(false);
                        bean.getObjeto().getAsegAfiliado().setFechaPortabilidad(null);
                        bean.getObjeto().getAsegAfiliado().setPortabilidadPrestadorSede(null);
                        //2024-09-19 jyperez reseteamos los valores de los nuevos campos
                        bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadId(null);
                        bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadCodigo("");
                        bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadValor("");
                        bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadId(null);
                        bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadCodigo("");
                        bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadValor("");
                        bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadId(null);
                        bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadCodigo("");
                        bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadValor("");
                        bean.getObjeto().getAsegAfiliado().setPeriodoInicialPortabilidad(null);
                        bean.getObjeto().getAsegAfiliado().setPeriodoFinalPortabilidad(null);
                        bean.getObjeto().getAsegAfiliado().setTelefonoContactoPortabilidad("");
                        bean.getObjeto().getAsegAfiliado().setDireccionPortabilidad("");
                        bean.getObjeto().getAsegAfiliado().setCorreoElectronicoPortabilidad("");
                        bean.getObjeto().getAsegAfiliado().setObservacionPortabilidad("");
                        getAfiliadoRemoto().actualizarAfiliadoPortabilidad(bean.getObjeto().getAsegAfiliado());
                        // 2020-11-20 jyperez 
                        guardarHistorialAfiliado(bean);
                    }
                    break;
                case AsegPortabilidad.ESTADO_FINALIZADA:
                    bean.auditoriaModificar(bean.getObjeto().getAsegAfiliado());
                    bean.getObjeto().getAsegAfiliado().setTienePortabilidad(false);
                    bean.getObjeto().getAsegAfiliado().setFechaPortabilidad(null);
                    bean.getObjeto().getAsegAfiliado().setPortabilidadPrestadorSede(null);
                    //2024-09-19 jyperez reseteamos los valores de los nuevos campos
                    bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadId(null);
                    bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadCodigo("");
                    bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadValor("");
                    bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadId(null);
                    bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadCodigo("");
                    bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadValor("");
                    bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadId(null);
                    bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadCodigo("");
                    bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadValor("");
                    bean.getObjeto().getAsegAfiliado().setPeriodoInicialPortabilidad(null);
                    bean.getObjeto().getAsegAfiliado().setPeriodoFinalPortabilidad(null);
                    bean.getObjeto().getAsegAfiliado().setTelefonoContactoPortabilidad("");
                    bean.getObjeto().getAsegAfiliado().setDireccionPortabilidad("");
                    bean.getObjeto().getAsegAfiliado().setCorreoElectronicoPortabilidad("");
                    bean.getObjeto().getAsegAfiliado().setObservacionPortabilidad("");
                    getAfiliadoRemoto().actualizarAfiliadoPortabilidad(bean.getObjeto().getAsegAfiliado());
                    // 2020-11-20 jyperez 
                    guardarHistorialAfiliado(bean);
                    break;
                case AsegPortabilidad.ESTADO_APROBADA:
                    bean.auditoriaModificar(bean.getObjeto().getAsegAfiliado());
                    bean.getObjeto().getAsegAfiliado().setTienePortabilidad(true);
                    bean.getObjeto().getAsegAfiliado().setFechaPortabilidad(bean.getObjeto().getPeriodoFinal());
                    if (bean.getObjeto().getCntPrestadorSede() != null) {
                        bean.getObjeto().getAsegAfiliado().setPortabilidadPrestadorSede(new CntPrestadorSede(bean.getObjeto().getCntPrestadorSede().getId()));
                    }
                    //2025-03-04 jyperez Validación Portabilidad Modelo de liquidación
                    // debemos validar que para las portabilidades hacia Medellín no se hace cambio de modelo de liquidación
                    if (bean.getObjeto().getUbicacion().getId() != AfiliadoParametro.IDENTIFICADOR_MEDELLIN) {
                        bean.getObjeto().getAsegAfiliado().setModeloLiquidacion(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
                        //2022-02-10 actualización campos modelo liquidación
                        Maestro maestro = bean.getHashValorModeloLiquidacion().get(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
                        if (maestro != null) {
                            bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionId(maestro.getId());
                            bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionCodigo(maestro.getValor());
                            bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionValor(maestro.getNombre());
                        }
                    }
                    //2024-09-19 jyperez actualizamos los valores de los nuevos campos
                    bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadId(bean.getObjeto().getMaeTipoPortabilidadId());
                    bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadCodigo(bean.getObjeto().getMaeTipoPortabilidadCodigo());
                    bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadValor(bean.getObjeto().getMaeTipoPortabilidadValor());
                    bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadId(bean.getObjeto().getMaeMotivoPortabilidadId());
                    bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadCodigo(bean.getObjeto().getMaeMotivoPortabilidadCodigo());
                    bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadValor(bean.getObjeto().getMaeMotivoPortabilidadValor());
                    bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadId(bean.getObjeto().getMaeOrigenSolicitudId());
                    bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadCodigo(bean.getObjeto().getMaeOrigenSolicitudCodigo());
                    bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadValor(bean.getObjeto().getMaeOrigenSolicitudValor());
                    bean.getObjeto().getAsegAfiliado().setPeriodoInicialPortabilidad(bean.getObjeto().getPeriodoInicial());
                    bean.getObjeto().getAsegAfiliado().setPeriodoFinalPortabilidad(bean.getObjeto().getPeriodoFinal());
                    bean.getObjeto().getAsegAfiliado().setTelefonoContactoPortabilidad(bean.getObjeto().getTelefonoContacto());
                    bean.getObjeto().getAsegAfiliado().setDireccionPortabilidad(bean.getObjeto().getDireccion());
                    bean.getObjeto().getAsegAfiliado().setCorreoElectronicoPortabilidad(bean.getObjeto().getCorreoElectronico());
                    bean.getObjeto().getAsegAfiliado().setObservacionPortabilidad(bean.getObjeto().getObservacionAseguramiento());
                    getAfiliadoRemoto().actualizarAfiliadoPortabilidad(bean.getObjeto().getAsegAfiliado());
                    // 2020-11-20 jyperez 
                    guardarHistorialAfiliado(bean);
                    // Se establecen valores antes de generar reporte portabilidad
                    bean.setReporte(new ReportePortabilidad());
                    bean.getReporte().setId(bean.getObjeto().getId().toString());
                    bean.getReporte().setStrUsuarioImprime(bean.getConexion().getUsuario().getNombre());
                    //Se indica que genere el certificado de portabilidad paraadjuntarlo al correo
                    reportePortabilidad(bean);
                    //Se llama la utilidad que arma el cuerpo del correo y realiza el envio
                    CorreoEstadoPortabilidad correoAprobacion = new CorreoEstadoPortabilidad(bean.getObjeto(), bean.getReporte(), bean.getHashUbicaciones().get(bean.getObjeto().getUbicacion().getId()) , true);
                    correoAprobacion.start();
                    break;
                case AsegPortabilidad.ESTADO_PRORROGA:
                    AsegAfiliado asegAfiliados = bean.getObjeto().getAsegAfiliado();
                    if (asegAfiliados == null) {
                        throw new Exception("No se encontro ningún afiliado");
                    } else {
                        if (asegAfiliados.getId() != null && asegAfiliados.getMaeEstadoAfiliacion() != AfiliadoParametro.IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION) {
                            throw new Exception("El afiliado con número de documento " + asegAfiliados.getNumeroDocumento() + ", no se encuentra en estado activo");
                        }
                        List<AsegPortabilidad> listPortabilidad = getPortabilidadRemoto().consultarPorAfiliado(asegAfiliados.getId());
                        if (!listPortabilidad.isEmpty()) {
                            for (AsegPortabilidad asegPortabilidad : listPortabilidad) {
                                if (asegPortabilidad.getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_APROBADA)) {
                                    throw new Exception("El Afiliado con número de documento " + asegAfiliados.getNumeroDocumento() + ", tiene una portabilidad en estado aprobada; si desea generar una nueva portabilidad deberá cancelar la actual.");
                                }
                            }
                        }
                        //2021-05-25 jyperez INC XXX se solicita que cuando el estado sea de prorroga, se actualicen la información en el afiliado
                        bean.auditoriaModificar(bean.getObjeto().getAsegAfiliado());
                        bean.getObjeto().getAsegAfiliado().setTienePortabilidad(true);
                        bean.getObjeto().getAsegAfiliado().setFechaPortabilidad(bean.getObjeto().getPeriodoFinal());
                        //2024-09-19 jyperez actualizamos los valores de los nuevos campos
                        bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadId(bean.getObjeto().getMaeTipoPortabilidadId());
                        bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadCodigo(bean.getObjeto().getMaeTipoPortabilidadCodigo());
                        bean.getObjeto().getAsegAfiliado().setMaeTipoPortabilidadValor(bean.getObjeto().getMaeTipoPortabilidadValor());
                        bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadId(bean.getObjeto().getMaeMotivoPortabilidadId());
                        bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadCodigo(bean.getObjeto().getMaeMotivoPortabilidadCodigo());
                        bean.getObjeto().getAsegAfiliado().setMaeMotivoPortabilidadValor(bean.getObjeto().getMaeMotivoPortabilidadValor());
                        bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadId(bean.getObjeto().getMaeOrigenSolicitudId());
                        bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadCodigo(bean.getObjeto().getMaeOrigenSolicitudCodigo());
                        bean.getObjeto().getAsegAfiliado().setMaeOrigenSolicitudPortabilidadValor(bean.getObjeto().getMaeOrigenSolicitudValor());
                        bean.getObjeto().getAsegAfiliado().setPeriodoInicialPortabilidad(bean.getObjeto().getPeriodoInicial());
                        bean.getObjeto().getAsegAfiliado().setPeriodoFinalPortabilidad(bean.getObjeto().getPeriodoFinal());
                        bean.getObjeto().getAsegAfiliado().setTelefonoContactoPortabilidad(bean.getObjeto().getTelefonoContacto());
                        bean.getObjeto().getAsegAfiliado().setDireccionPortabilidad(bean.getObjeto().getDireccion());
                        bean.getObjeto().getAsegAfiliado().setCorreoElectronicoPortabilidad(bean.getObjeto().getCorreoElectronico());
                        bean.getObjeto().getAsegAfiliado().setObservacionPortabilidad(bean.getObjeto().getObservacionProrroga());
                        if (bean.getObjeto().getCntPrestadorSede() != null) {
                            bean.getObjeto().getAsegAfiliado().setPortabilidadPrestadorSede(new CntPrestadorSede(bean.getObjeto().getCntPrestadorSede().getId()));
                        }
                        //2025-07-28 jyperez CORRECCIÓN POR FALLO EN PDN Validación Portabilidad Modelo de liquidación
                        // debemos validar que para las portabilidades hacia Medellín no se hace cambio de modelo de liquidación
                        if (bean.getObjeto().getUbicacion().getId() != AfiliadoParametro.IDENTIFICADOR_MEDELLIN) {
                            bean.getObjeto().getAsegAfiliado().setModeloLiquidacion(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
                            //2022-02-10 actualización campos modelo liquidación
                            Maestro maestroModelo = bean.getHashValorModeloLiquidacion().get(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
                            if (maestroModelo != null) {
                                bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionId(maestroModelo.getId());
                                bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionCodigo(maestroModelo.getValor());
                                bean.getObjeto().getAsegAfiliado().setMaeModeloLiquidacionValor(maestroModelo.getNombre());
                            }
                        }
                        getAfiliadoRemoto().actualizarAfiliadoPortabilidad(bean.getObjeto().getAsegAfiliado());
                    }
                    break;
                case AsegPortabilidad.ESTADO_RECHAZADA :
                    // Se establecen valores antes de generar reporte portabilidad
                    bean.setReporte(new ReportePortabilidad());
                    bean.getReporte().setId(bean.getObjeto().getId().toString());
                    bean.getReporte().setStrUsuarioImprime(bean.getConexion().getUsuario().getNombre());
                    //Se indica que genere el certificado de portabilidad paraadjuntarlo al correo
                    reportePortabilidad(bean); 
                    //Se llama la utilidad que arma el cuerpo del correo y realiza el envio
                    CorreoEstadoPortabilidad correoRechazo = new CorreoEstadoPortabilidad(bean.getObjeto(), bean.getReporte(),null,false);
                    correoRechazo.start();
                    break;
            }
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(PortabilidadBean bean) {
        try {
            bean.setObjeto(getPortabilidadRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void generarCertificado(PortabilidadBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(PortabilidadBean bean) {
        Maestro estado;
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    //Estado
                    bean.setListaEstadoPortabilidadInterno(new ArrayList());
                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_EN_TRAMITE);
                    if (estado != null) {
                        bean.getListaEstadoPortabilidadInterno().add(estado);
                    }
                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_APROBADA);
                    if (estado != null) {
                        bean.getListaEstadoPortabilidadInterno().add(estado);
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    //Estado
                    switch(bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            bean.setListaEstadoPortabilidadInterno(new ArrayList());
                            switch (bean.getObjeto().getMaeEstadoPortabilidadCodigo()) {
                                case AsegPortabilidad.ESTADO_APROBADA:
                                    Date fecha = bean.getObjeto().getFechaSolicitudProrroga() == null ? new Date()
                                            : bean.getObjeto().getFechaSolicitudProrroga();
                                    bean.getObjeto().setFechaSolicitudProrroga(fecha);
                                    bean.getObjeto().setObservacionProrroga("");

                                    Date fechaActual = new Date();
                                    Date fechFinalizacion = bean.getObjeto().getPeriodoFinal();
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(fechFinalizacion);
                                    calendar.add(Calendar.HOUR_OF_DAY, 23);
                                    calendar.add(Calendar.MINUTE, 59);
                                    fechFinalizacion = calendar.getTime();

                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_APROBADA);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    if (fechFinalizacion.after(fechaActual)) {
                                        long diff = fechFinalizacion.getTime() - fechaActual.getTime();
                                        long diasVencimiento = diff / 1000 / 60 / 60 / 24;
                                        if (diasVencimiento >= 0 && AfiliadoParametro.LIMITE_HABILITACION_ESTADO_APROBADO >= diasVencimiento) {
                                            //bean.getHashEstadoPortabilidadInterno().put(AsegPortabilidad.ESTADO_PRORROGA, AsegPortabilidad.ESTADO_PRORROGA_STRING);
                                            estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_PRORROGA);
                                            if (estado != null) {
                                                bean.getListaEstadoPortabilidadInterno().add(estado);
                                            }
                                        }
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_CANCELADA_USUARIO);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    break;
                                case AsegPortabilidad.ESTADO_EN_TRAMITE:
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_EN_TRAMITE);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_APROBADA);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_RECHAZADA);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_CANCELADA_USUARIO);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    break;
                                case AsegPortabilidad.ESTADO_FINALIZADA:
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_FINALIZADA);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_PRORROGA);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    break;
                                case AsegPortabilidad.ESTADO_PRORROGA:
                                    if (bean.getObjeto().getFechaSolicitudProrrogaAnterior() != null
                                            && (bean.getObjeto().getMesesProrrogaAnterior()!= null &&
                                            bean.getObjeto().getMesesProrrogaAnterior() > 0)) {
                                        bean.getObjeto().setHayProrrogaAnterior(true);
                                        bean.getObjeto().setFechaSolicitudProrroga(new Date());
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_PRORROGA);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    estado = bean.getHashValorEstadoPortabilidad().get(AsegPortabilidad.ESTADO_CANCELADA_USUARIO);
                                    if (estado != null) {
                                        bean.getListaEstadoPortabilidadInterno().add(estado);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        break;
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
             bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInial(PortabilidadBean bean) {
        try {
            //Maestro Tipo Documento
            bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));
            //Maestro Sexo(Genero)
            bean.setListaTiposGenero(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashTiposGenero(Util.convertToHash(bean.getListaTiposGenero()));
            //Maestro Estado afiliación
            bean.setListaEstadosAfiliacion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosAfiliacion(Util.convertToHash(bean.getListaEstadosAfiliacion()));
            bean.setHashValorEstadosAfiliacion(Util.convertToHashValor(bean.getListaEstadosAfiliacion()));
            //Ubicaciones
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            //Tipo
            //2021-10-26 jyperez obtenemos el maestro
            bean.setListaTipoPortabilidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_PORTABILIDAD_TIPO));
            bean.setHashTipoPortabilidad(Util.convertToHash(bean.getListaTipoPortabilidad()));
            bean.setHashValorTipoPortabilidad(Util.convertToHashValor(bean.getListaTipoPortabilidad()));
            //Origen
            bean.setListaOrigenPortabilidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_PORTABILIDAD_ORIGEN));
            bean.setHashOrigenPortabilidad(Util.convertToHash(bean.getListaOrigenPortabilidad()));
            //Estado
            bean.setListaEstadoPortabilidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_PORTABILIDAD_ESTADO));
            bean.setHashEstadoPortabilidad(Util.convertToHash(bean.getListaEstadoPortabilidad()));
            bean.setHashValorEstadoPortabilidad(Util.convertToHashValor(bean.getListaEstadoPortabilidad()));
            //2021-11-23 jyperez cargamos nuevos maestros usados en el certificado
            bean.setHashSubGrupoSisben(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA));
            bean.setHashGrupoPoblacional(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_GRUPO_POBLACIONAL));
            //2022-02-10 jyperez corección adición del modelo de liquidación
            bean.setHashValorModeloLiquidacion(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.ASEG_MODELO_LIQUIDACION));
            //Maestro tipo portabilidad
            bean.setListaMotivoPortabilidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_PORTABILIDAD_MOTIVO));
            bean.setHashMotivoPortabilidad(Util.convertToHash(bean.getListaMotivoPortabilidad()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void reportePortabilidad(PortabilidadBean bean) {
        AsegAfiliadoCertificado obj = new AsegAfiliadoCertificado();
        Maestro maestro;
        ReportePortabilidad reporte;
        List<ReportePortabilidad> listaReportesPortabilidad = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            reporte = getPortabilidadRemoto().reportePortabilidad(bean.getReporte().getId());
            reporte.setStrUsuarioImprime(bean.getReporte().getStrUsuarioImprime());
            bean.setReporte(reporte);
            //2021-11-23 jyperez almacenamos el reporte en la BD
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId());
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
                generarArchivoCertificado(bean, streamImpresion);
                // al streamedContent y no generarlo en el bean. De igual forma necesitamos almacenarlo en ruta.
            } else {
                throw new Exception("No se encontró información del afiliado para almacenar el certificado en base de datos.");
            }
        } catch (Exception ex) {
            //Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception: "+ex);
        }
    }
    
    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, PortabilidadBean bean){
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            if(maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)){
                return true;
            }else{
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra en estado " + maeEstado.getNombre());
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }
    
    @Override
    public boolean validarPortabilidadesPendientesAfiliado(AsegAfiliado afiliado, PortabilidadBean bean){
        boolean resultado = true;
        try {
            List<AsegPortabilidad> listPortabilidad = getPortabilidadRemoto().consultarPorAfiliado(afiliado.getId());
            if (listPortabilidad != null && !listPortabilidad.isEmpty()) {
                for (AsegPortabilidad asegPortabilidad : listPortabilidad) {
                    Maestro maeEstado = getMaestroRemoto().consultar(asegPortabilidad.getMaeEstadoPortabilidadId());
                    if (maeEstado.contieneAccion(MaestroAccion.ASEG_PORTABILIDAD_ESTADO_PORTABILIDAD_VIGENTE)) {
                        bean.addError("El Afiliado con número de documento " + afiliado.getNumeroDocumento() + ", tiene una portabilidad en estado "+ maeEstado.getNombre() + ", si desea generar una nueva portabilidad deberá cancelar la actual.");
                        return true;
                    } else {
                        resultado = false;
                    }
                }
            } else {
                resultado = false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando las portabilidades pendientes del afiliado, intentelo nuevamente");
            resultado = true;
        }
        return resultado;
    }

    private void guardarHistorialAfiliado(PortabilidadBean bean) {
        try {
            AsegAfiliadoHistorico afiliadoHistorico;
            AsegAfiliado objetoAnterior= null;
            //2020-11-20 jyperez adicion de almacenamiento de histórico afiliado
            // se solicita que en modificar se guarde la información anterior
            afiliadoHistorico = new AsegAfiliadoHistorico();
            //obtenemos los datos anteriores del afiliado
            objetoAnterior = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId());
            afiliadoHistorico.setTostringAfiliado(objetoAnterior.toString());
            bean.auditoriaGuardar(afiliadoHistorico);
            afiliadoHistorico.setAsegAfiliado(new AsegAfiliado(objetoAnterior.getId()));
            getAfiliadoHistoricoRemoto().insertar(afiliadoHistorico);
            
        } catch (Exception ex) {
            Logger.getLogger(PortabilidadServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean generarArchivoCertificado(PortabilidadBean bean, InputStream impresion) throws Exception {
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
        long difMs = ms2 - ms1;
        long msPorWeek = 1000 * 60 * 60 * 24 * 7;
        semanas = (int) (difMs / msPorWeek);
        return semanas;
    }
    
    public static Date sumarDiasFecha(Date fechaActual, int dias) {
        Calendar calendar = Calendar.getInstance();
        //le sumamos los días definidos a la fecha actual
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    /**
     * Metodo encargado de listar las ips segun el municipio seleccionado
     * @author idbohorquez
     * @fechaCreacion 04/04/2023
     * @param bean
     * 
     */
    @Override
    public void consultarSedesMunicipio(PortabilidadBean bean) {
        try {
            if(bean.getObjeto().getUbicacion() != null){
                if (bean.getObjeto().getUbicacion().isCobertura()) {
                    bean.setListaCntPrestadorSedes(getPrestadoresRemoto().consultarListaSedesCapitadas(bean.getObjeto().getUbicacion().getId().toString()));
                } else { 
                    bean.setListaCntPrestadorSedes(getPrestadoresRemoto().consultarListaSedesSinNivelAtencion(bean.getObjeto().getUbicacion().getId().toString()));
                }                
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
