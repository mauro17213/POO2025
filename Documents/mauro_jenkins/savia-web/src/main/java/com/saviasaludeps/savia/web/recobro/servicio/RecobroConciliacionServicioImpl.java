package com.saviasaludeps.savia.web.recobro.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.recobro.RcoActa;
import com.saviasaludeps.savia.dominio.recobro.RcoActaAsistente;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacion;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionAdjunto;
import com.saviasaludeps.savia.dominio.recobro.RcoConciliacionGestion;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoConciliacionAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoConciliacionGestionRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoConciliacionRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoFacturaDetalleRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoFacturaRemoto;
//import com.saviasaludeps.savia.negocio.recobro.ReporteConciliacion;
import com.saviasaludeps.savia.web.recobro.bean.RecobroConciliacionBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.util.MessageFactory;

/**
 *
 * @author sgiraldov
 */
public class RecobroConciliacionServicioImpl extends AccionesBO implements RecobroConciliacionServicioIface {

    private RcoConciliacionRemoto getRcoConciliacionRemoto() throws Exception {
        return (RcoConciliacionRemoto) RemotoEJB.getEJBRemoto("RcoConciliacionServicio", RcoConciliacionRemoto.class.getName());
    }
    
    private RcoFacturaRemoto getRcoFacturaRemoto() throws Exception {
        return (RcoFacturaRemoto) RemotoEJB.getEJBRemoto("RcoFacturaServicio", RcoFacturaRemoto.class.getName());
    }

    private RcoFacturaDetalleRemoto getRcoFacturaDetalleRemoto() throws Exception {
        return (RcoFacturaDetalleRemoto) RemotoEJB.getEJBRemoto("RcoFacturaDetalleServicio", RcoFacturaDetalleRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
    }
    
    private RcoConciliacionAdjuntoRemoto getRcoConciliacionAdjuntoRemoto() throws Exception {
        return (RcoConciliacionAdjuntoRemoto) RemotoEJB.getEJBRemoto("RcoConciliacionAdjuntoServicio", RcoConciliacionAdjuntoRemoto.class.getName());
    }
    
    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private RcoConciliacionGestionRemoto getRcoConciliacionGestionRemoto() throws Exception {
        return (RcoConciliacionGestionRemoto) RemotoEJB.getEJBRemoto("RcoConciliacionGestionServicio", RcoConciliacionGestionRemoto.class.getName());
    }
    
    @Override
    public void Accion(RecobroConciliacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_CREAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearConciliacion(bean);;
                            break;
                        case Url.ACCION_LISTAR:
                            listarPrestadores(bean);
                            break; 
                        case Url.ACCION_ADICIONAL_1:
                            listarContratos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verConciliacion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verDetalleFactura(bean);
                            break;
                        case Url.ACCION_CREAR:
                            verConciliacionGestion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            gestionar(bean);
                            break;    
                        case Url.ACCION_VER:
                            verDetalleFactura(bean);
                            break; 
                        case Url.ACCION_ADICIONAL_1:
                            verConciliacionGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_CREAR:
                                    crearConciliacionGestion(bean);
                                    break;
                                case Url.ACCION_GUARDAR:
                                    guardarConciliacionGestion(bean);
                                    break;
                            }
                            
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarAdjuntos(bean);
                            break;  
                        case Url.ACCION_CREAR:
                            crearAdjunto(bean);
                            break; 
                        case Url.ACCION_GUARDAR:
                            guardarAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearCorreo(bean);
                            break; 
                        case Url.ACCION_GUARDAR:
                            enviarCorreo(bean);
                            break;
                    }
                    
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearActaConciliacion(bean);
                            break; 
                        case Url.ACCION_GUARDAR:
                            generarReporteConciliacion(bean);
                            break;
                        case Url.ACCION_VER:
                            crearAsistente(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            guardarAsistente(bean);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(RecobroConciliacionBean bean) {
        try {
            //Singleton Ubicaciones
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
           
        } catch (Exception e) {
            bean.addError("Hubo un error en la carga inicial, favor contactar con el administrador");
        }
    }
    
    public void cargarMaestros(RecobroConciliacionBean bean){
        try {
            //Singleton Ubicaciones
            //bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            //bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
         
            // listas lazy afiliado, prestado
            bean.setListaTipoDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
        } catch (Exception ex) {
            Logger.getLogger(RecobroConciliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listar(RecobroConciliacionBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getRcoConciliacionRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getRcoConciliacionRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar al administrador");
        }
    }
    
    public void listarPrestadores(RecobroConciliacionBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(1)));
            bean.setListaPrestadores(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarContratos(RecobroConciliacionBean bean) {
        try {
            if (bean.getParamConsulta(2).getFiltros() == null) {
                bean.getParamConsulta(2).setFiltros(new HashMap());
            }
            bean.getParamConsulta(2).getFiltros().put("activo", true);
            bean.getParamConsulta(2).setCantidadRegistros(getCntContratoRemoto().consultarCantidadListaConciliacion(bean.getParamConsulta(2)));
            bean.setListaContratos(getCntContratoRemoto().consultarListaConciliacion(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar el contrato, favor contactar al administrador");
        }
    }
    
    
    @Override
    public void listarFacturas(RecobroConciliacionBean bean) {
        try {
            if (bean.getParamConsulta(3).getFiltros() == null) {
                bean.getParamConsulta(3).setFiltros(new HashMap());
            }
            bean.getParamConsulta(3).getFiltros().put("cntPrestadoresId.id", bean.getObjeto().getCntPresadoresSedesId().getCntPrestador().getId());
            bean.getParamConsulta(3).getFiltros().put("maeEstadoRecobroCodigo", RcoConciliacion.ESTADO_FACTURA_CODIGO_POTENCIAL_RECOBRO);
            bean.getParamConsulta(3).setCantidadRegistros(getRcoFacturaRemoto().consultarCantidadListaConciliacion(bean.getParamConsulta(3)));
            bean.setRegistrosFacturas(getRcoFacturaRemoto().consultarListaConciliacion(bean.getParamConsulta(3)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar las Facturas, favor contactar al administrador");
        }
    }
    
    @Override
    public void listarFacturaDetalles(RecobroConciliacionBean bean) {
        try {
            if (bean.getParamConsulta(4).getFiltros() == null) {
                bean.getParamConsulta(4).setFiltros(new HashMap());
            }
            //bean.getParamConsulta(4).getFiltros().put("cntPrestadoresSedesId.id", bean.getObjeto().getCntPresadoresSedesId().getId());
            //bean.getParamConsulta(4).getFiltros().put("maeEstadoCodigo", RcoConciliacion.ESTADO_AUDITORIA_CODIGO_AUDITADO);
            bean.getParamConsulta(4).getFiltros().put("rcoConciliacionId.id", bean.getObjeto().getId());
            bean.getParamConsulta(4).setCantidadRegistros(getRcoFacturaDetalleRemoto().consultarCantidadListaConciliacion(bean.getParamConsulta(4)));
            bean.setRegistrosFacturaDetalles(getRcoFacturaDetalleRemoto().consultarListaConciliacion(bean.getParamConsulta(4)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar el Detalle de la Factura, favor contactar al administrador");
        }
    }
    
    @Override
    public RcoConciliacionGestion consultarConciliacionesGestion(int facturaDetalleId, RecobroConciliacionBean bean) {
        RcoConciliacionGestion obj = null;
        try {
           obj = getRcoConciliacionGestionRemoto().consultarByFacturaDetallesId(facturaDetalleId);
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
        return obj;
    }
    
    @Override
    public RcoConciliacion consultarConciliacion(int conciliacionId, RecobroConciliacionBean bean) {
        RcoConciliacion obj = null;
        try {
           obj = getRcoConciliacionRemoto().consultar(conciliacionId);
           obj.setCntPresadoresSedesId(getCntPrestadorSedeRemoto().consultar(obj.getCntPresadoresSedesId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
        return obj;
    }
    
    
    @Override
    public List<RcoConciliacionGestion> consultarListaConciliacionesGestion(int conciliacionId, RecobroConciliacionBean bean) {
        List<RcoConciliacionGestion> obj = new ArrayList<>();
        try {
           obj = getRcoConciliacionGestionRemoto(). consultarByConciliacionId(conciliacionId);
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
        return obj;
    }
    
    @Override
    public List<RcoFacturaDetalle> consultarListaFacturasDetalles(int conciliacionId, RecobroConciliacionBean bean) {
        List<RcoFacturaDetalle> obj = new ArrayList<>();
        try {
           obj = getRcoFacturaDetalleRemoto().consultarConciliacionPorId(conciliacionId) ;
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
        return obj;
    }
    
    private void gestionar(RecobroConciliacionBean bean) {
        try {
            bean.setObjeto(getRcoConciliacionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCntPresadoresSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPresadoresSedesId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }
    
    private void verDetalleFactura(RecobroConciliacionBean bean) {
        try {
            bean.setObjetoFacturaDetalle(getRcoFacturaDetalleRemoto().consultar(bean.getObjetoFacturaDetalle().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }
    
    private void verConciliacionGestion(RecobroConciliacionBean bean) {
        try {
            bean.setObjetoConciliacionGestion(getRcoConciliacionGestionRemoto().consultarByFacturaDetallesId(bean.getObjetoConciliacionGestion().getRcoFacturaDetallesId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }
    
    
    private void crearConciliacion(RecobroConciliacionBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(new RcoConciliacion());
            bean.getObjeto().setEstado(RcoConciliacion.PENDIENTE_SOPORTE);
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }
    
    private void crearConciliacionGestion(RecobroConciliacionBean bean) {
        try {
            bean.setObjetoConciliacionGestion(new RcoConciliacionGestion());
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }
    
    private void guardarConciliacionGestion(RecobroConciliacionBean bean) {
        try {
            RcoConciliacionGestion obj = bean.getObjetoConciliacionGestion();
            bean.auditoriaGuardar(obj);
            obj.setId(getRcoConciliacionGestionRemoto().insertar(obj));
            
            //actualiza estado de la conciliacion
            RcoConciliacion objConciliacionDB = getRcoConciliacionRemoto().consultar(bean.getObjeto().getId());
            if(objConciliacionDB.getEstado() == RcoConciliacion.NOTIFICIACION_PRESTADOR){
                objConciliacionDB.setEstado(RcoConciliacion.EN_CONCILIACION);
                getRcoConciliacionRemoto().actualizarEstado(objConciliacionDB);
            }
            //actualizar valor total recobro
            if(obj.getAcuerdoRecobro() == RcoConciliacionGestion.SI_ACUERDO_RECOBRO){
                if(objConciliacionDB.getValorTotalConciliado() == null){
                    objConciliacionDB.setValorTotalConciliado(0L);
                }
                if(obj.getRcoFacturaDetallesId().getValorTotalRecobro() == null){
                    obj.getRcoFacturaDetallesId().setValorTotalRecobro(new BigDecimal(0));
                }
                objConciliacionDB.setValorTotalConciliado(obj.getRcoFacturaDetallesId().getValorTotalRecobro().longValue() + objConciliacionDB.getValorTotalConciliado());
                bean.auditoriaModificar(objConciliacionDB);
                getRcoConciliacionRemoto().actualizarValorTotalRecobro(objConciliacionDB);
                bean.setObjeto(getRcoConciliacionRemoto().consultar(bean.getObjeto().getId()));         
            }
            //se actualiza el estado conciliacion de factura detalle
            obj.getRcoFacturaDetallesId().setEstadoConciliacion(Boolean.TRUE);
            getRcoFacturaDetalleRemoto().actulizarEstadoConciliacion(obj.getRcoFacturaDetallesId());
            if(!bean.isError()){
                bean.addMensaje("La consiliacion gestion se creo exitosamente " + obj.getId());
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void verConciliacion(RecobroConciliacionBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(getRcoConciliacionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCntPresadoresSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPresadoresSedesId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }
    
    private void guardar(RecobroConciliacionBean bean) {
        try {
            RcoConciliacion obj = bean.getObjeto();
            bean.auditoriaGuardar(obj);
            /*Maestro ambitoAtencion = bean.getHashAmbitosAtencion().get(obj.getMaeAmbitoAtencionId());
            if (ambitoAtencion != null) {
                obj.setMaeAmbitoAtencionCodigo(ambitoAtencion.getValor());
                obj.setMaeAmbitoAtencionValor(ambitoAtencion.getNombre());
                obj.setMaeAmbitoAtencionTipo(ambitoAtencion.getTipo());
            }*/
            obj.setId(getRcoConciliacionRemoto().insertar(obj));
            //funcionalidad para actualizar el id de la conciliacion en la tabla rco_factura_detalles
            List<RcoFacturaDetalle> detallefasFacturas = getRcoFacturaDetalleRemoto().consultarFacturaDetallesPorPrestadoryCodigo(obj.getCntPresadoresSedesId().getId(), RcoConciliacion.ESTADO_AUDITORIA_CODIGO_AUDITADO);
            for(RcoFacturaDetalle detalle: detallefasFacturas){
                detalle.setRcoConciliacionId(new RcoConciliacion(obj.getId()));
                getRcoFacturaDetalleRemoto().actulizarIdConciliacion(detalle);
            }
            if(!bean.isError()){
                bean.addMensaje("La consiliacion se creo exitosamente " + obj.getId());
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void generarReporteConciliacion(RecobroConciliacionBean bean) {
        try {
            RcoActa obj = bean.getObjetoRcoActa();
            bean.auditoriaGuardar(obj);
            obj.setStrUsuarioCrea(obj.getUsuarioCrea());
            obj.setStrFechaHoraCrea(obj.getFechaHoraCrea());
            //getRcoConciliacionRemoto().actualizarEstado(obj.getRcoConciliacionesId());

            bean.getReporteActaConciliacion().add(obj);
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al generar reporte, favor contactar al administrador");
        }
    }
    
    private void crearActaConciliacion(RecobroConciliacionBean bean) {
        try {
            RcoConciliacion obj = bean.getObjeto();
            bean.auditoriaModificar(obj);
            obj.setEstado(RcoConciliacion.ACTA_GENERADA);
            bean.setObjetoRcoActa(new RcoActa());
            LocalDate fechaActl = LocalDate.now();
            bean.getObjetoRcoActa().setStrFechaActaConciliacion(fechaActl.toString());
            bean.getObjetoRcoActa().setRcoConciliacionesId(obj);
            bean.getObjetoRcoActa().setListaRcoActaAsistente(new ArrayList<>());
            bean.setReporteActaConciliacion(new ArrayList<>());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al generar reporte, favor contactar al administrador");
        }
    }
    
    private void crearAsistente(RecobroConciliacionBean bean) {
        try {
            bean.setObjetoRcoActaAsistente(new RcoActaAsistente());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al generar reporte, favor contactar al administrador");
        }
    }
    
    private void guardarAsistente(RecobroConciliacionBean bean) {
        try {
            bean.getObjetoRcoActaAsistente().setPosicion(bean.getObjetoRcoActa().getListaRcoActaAsistente().size() + 1);
            bean.getObjetoRcoActa().getListaRcoActaAsistente().add(bean.getObjetoRcoActaAsistente());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al generar reporte, favor contactar al administrador");
        }
    }
    
    private void consultarAdjuntos(RecobroConciliacionBean bean) {
        try {
            bean.setObjeto(getRcoConciliacionRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaAdjuntos(getRcoConciliacionAdjuntoRemoto().consultarListaPorConciliacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar adjuntos, favor contactar al administrador");
        }
    }
    
    private void crearAdjunto(RecobroConciliacionBean bean) {
        try {
            bean.setObjetoAdjunto(new RcoConciliacionAdjunto());
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar adjuntos, favor contactar al administrador");
        }
    }
    
    public void guardarAdjunto(RecobroConciliacionBean bean) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date fecha = new Date();
        try {
            //String ruta = "C:\\adjuntos\\conciliacion\\";
            String ruta = PropApl.getInstance().get(PropApl.RCO_RUTA_CARGA_ADJUNTO);
            if(ruta == null ){
                bean.addError("Hubo un error al ruta adjuntos, favor contactar al administrador");
                return;
            }
            //Validar origen de afiliado y actualizar 
            int indiceExtension = bean.getObjetoAdjunto().getNombreArchivo().lastIndexOf(".");
            String extension = bean.getObjetoAdjunto().getNombreArchivo().substring(indiceExtension, bean.getObjetoAdjunto().getNombreArchivo().length());
            String archivo = "rco_conciliacion" + "_" + bean.getObjetoAdjunto().getTipo() + "_" + sdf.format(fecha) + extension;
            bean.getObjetoAdjunto().setArchivo(archivo);
            bean.auditoriaGuardar(bean.getObjetoAdjunto());
            bean.getObjetoAdjunto().setRuta(ruta);
            generarArchivo(bean.getObjetoAdjunto(), bean);
            if(!bean.isError()){
                bean.getObjetoAdjunto().setAdjuntoStream(null);
                bean.getObjetoAdjunto().setId(getRcoConciliacionAdjuntoRemoto().insertar(bean.getObjetoAdjunto()));
                bean.addMensaje("El archivo " +bean.getObjetoAdjunto().getNombreArchivo() + " se cargó con éxito.");
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al guardar adjuntos, favor contactar al administrador");
        }
    }

    @SuppressWarnings("null")
    private boolean generarArchivo(RcoConciliacionAdjunto adjunto, RecobroConciliacionBean bean) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(adjunto.getRuta(), adjunto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(adjunto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            bean.addError("Hubo un error al subir adjuntos, favor contactar al administrador"+ ex.getMessage());
        } catch (IOException ex) {
            bean.addError("Hubo un error al subir adjuntos, favor contactar al administrador");
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {

            }
        }

        return esArchivoGuardado;
    }
    
    private void crearCorreo(RecobroConciliacionBean bean) {
        try {
            bean.setEmail(new String());
            bean.setAsuntoCorreo(new String());
            bean.setMensajeCorreo(new String());
            bean.setObjeto(getRcoConciliacionRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaAdjuntosEnvioCorreo(getRcoConciliacionAdjuntoRemoto().consultarListaPorConciliacion(bean.getObjeto().getId()));
            bean.setListaFaturasDetalles(getRcoFacturaDetalleRemoto().consultarConciliacionPorId(bean.getObjeto().getId()));
            for(RcoFacturaDetalle facturaDetalle: bean.getListaFaturasDetalles()){
                if(facturaDetalle.getRcoFacturasId() != null){
                    facturaDetalle.setRcoFacturasId(getRcoFacturaRemoto().consultar(facturaDetalle.getRcoFacturasId().getId()));
                }
                if(facturaDetalle.getCntPrestadoresSedesId() != null){
                    facturaDetalle.setCntPrestadoresSedesId(getCntPrestadorSedeRemoto().consultar(facturaDetalle.getCntPrestadoresSedesId().getId()));
                    if(facturaDetalle.getCntPrestadoresSedesId().getCntPrestador() != null){
                        facturaDetalle.getCntPrestadoresSedesId().setCntPrestador(getPrestadorRemoto().consultar(facturaDetalle.getCntPrestadoresSedesId().getCntPrestador().getId()));
                    }
                }
                if(facturaDetalle.getRcoFacturasId().getCmFacturaId() != null){
                    facturaDetalle.getRcoFacturasId().setCmFacturaId(getCmFacturaRemoto().consultar(facturaDetalle.getRcoFacturasId().getCmFacturaId().getId()));
                }
                if(facturaDetalle.getRcoFacturasId().getCmFacturaId().getCntPrestador() != null){
                    facturaDetalle.getRcoFacturasId().getCmFacturaId().setCntPrestador(getPrestadorRemoto().consultar(facturaDetalle.getRcoFacturasId().getCmFacturaId().getCntPrestador().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar adjuntos, favor contactar al administrador");
        }
    }
    
    private void enviarCorreo(RecobroConciliacionBean bean) {
        try {
            //enviar Correo
            notificarPorCorreoServicio(bean);
            if(!bean.isError()){
                //actualizar datos conciliacion
                bean.getObjeto().setEstado(RcoConciliacion.NOTIFICIACION_PRESTADOR);
                bean.auditoriaModificar(bean.getObjeto());
                bean.getObjeto().setCorreoEnvio(bean.getEmail());
                getRcoConciliacionRemoto().actualizarEstadoEnvio(bean.getObjeto());
                if(!bean.isError()){
                    bean.addMensaje("Se envió el correo de manera exitosa");
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void notificarPorCorreoServicio(RecobroConciliacionBean bean) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            String ruta = PropApl.getInstance().get(PropApl.RCO_RUTA_ENVIO_COMPRIMIDOS);
            //String ruta = "C:\\adjuntos\\conciliacion_zip\\";
            if(ruta == null){
                bean.addError("Hubo un error la ruta del archivo no esta creada, favor contactar al administrador");
                return;
            }
            String ubicacionArchivoZip = ruta + "rco_conciliacion" + "_" + bean.getObjeto().getId() + "_" + bean.getObjeto().getCntPresadoresSedesId().getId() + "_" + sdf.format(new Date()) +".zip";
            String nombreFacturasDetalles = ruta + "rco_conciliacion" + "_" + bean.getObjeto().getId() + "_" + bean.getObjeto().getCntPresadoresSedesId().getId() + "_" + sdf.format(new Date()) +".xlsx";
            StringBuilder mensaje = new StringBuilder();
            mensaje.append(bean.getMensajeCorreo());
            GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_RCO_CONCILIACIONES, bean.getEmail(), bean.getAsuntoCorreo(), mensaje.toString(), GnCorreoEnvio.TIPO_TEXTO);
            //se escribe la url donde se guardá el envio y se le adiciona el nombre del archivo
            crearArchivoZip(bean.getListaAdjuntosEnvioCorreo(), ubicacionArchivoZip, nombreFacturasDetalles, bean);
            envio.setAdjunto1(ubicacionArchivoZip);
            envio.setFechaHoraCrea(new Date());
            envio.setFechaHoraEnvio(envio.getFechaHoraCrea());
            envio.setEstado(GnCorreoEnvio.ESTADO_PENDIENTE);
            getGnCorreoEnvioRemoto().insertar(envio);
        } catch (Exception ex) {
            bean.addError("Hubo un error al enviando correo, favor contactar al administrador");
        }
    }
    
    @SuppressWarnings("null")
    public static void crearArchivoZip(List<RcoConciliacionAdjunto> files, String zipFilePath,String nombreArchivoExcel, RecobroConciliacionBean bean )  {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(zipFilePath);
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {
                files.stream().filter(file -> (file.isExiste())).forEachOrdered(file -> {
                    String rutaCompleta = file.getRuta() + file.getArchivo();
                    agregarArchivoZip(rutaCompleta, zos, bean);
                });
                //System.out.println("Creando Archivo Excel");
                crearFacturasDetallesExcel(nombreArchivoExcel, bean);
                //System.out.println("guardando Archivo Excel en Zip");
                agregarArchivoZip(nombreArchivoExcel, zos, bean);
                //System.out.println("Eliminar Archivo Excel en la ruta");
                Path eliminarArchivoExcel = Paths.get(nombreArchivoExcel);
                Files.deleteIfExists(eliminarArchivoExcel);
                //System.out.println("Elimino Archivo Excel en la ruta");
            } catch (IOException ex) {
                bean.addError("Hubo un error al creando zip correo, favor contactar al administrador");
                //System.out.println("Error crear zip"+ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            bean.addError("Hubo un error al no encontro archivo para el zip, favor contactar al administrador");
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                bean.addError("Hubo un error al no encontro archivo para el zip, favor contactar al administrador");
            }
        }
    }
    
    public static void agregarArchivoZip(String rutaCompleta, ZipOutputStream zos, RecobroConciliacionBean bean){
        File inputFile = new File(rutaCompleta);
        try ( FileInputStream fis = new FileInputStream(inputFile)) {
            ZipEntry zipEntry = new ZipEntry(inputFile.getName());
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        } catch (FileNotFoundException ex) {
            bean.addError("Hubo un error al encontrar archivo para creancion del zip correo, favor contactar al administrador");
            //System.out.println("Encontrar archivo para creancion del zip correo" + ex);
        } catch (IOException ex) {
            bean.addError("Hubo un error al escribir zip en la creancion zip correo, favor contactar al administrador");
            //System.out.println("error al escribir zip en la creancion zip correo" + ex);
        }
    }
    
    public static void crearFacturasDetallesExcel(String ruta, RecobroConciliacionBean bean){
        try{
            // crear un libro
            Workbook libro = new XSSFWorkbook();
            // crear la hoja
            Sheet hoja1 = libro.createSheet("Facturas Detalles");
            // crear fila
            Row fila = hoja1.createRow(0);
            //estilos de celda
            CellStyle cellStyleFecha = libro.createCellStyle();
            CreationHelper createHelperFecha = libro.getCreationHelper();
            // crear encabezados
            for(int i = 0; i < RcoConciliacion.ENCABEZADOS.length; i++){
                // crear columnas
                Cell celda = fila.createCell(i);
                celda.setCellValue(RcoConciliacion.ENCABEZADOS[i]);
            }
            // crear informacion
            int filaR = 1;
            int columna = 0;
            for(RcoFacturaDetalle facturaDetalle: bean.getListaFaturasDetalles()){
                //numero radicado
                hoja1.autoSizeColumn(columna);
                fila = hoja1.createRow(filaR);
                Cell celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getRcoFacturasId().getNumeroRadicado());
                columna++;
                //numero factura
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getRcoFacturasId().getNumeroFacturado());
                columna++;
                //nit ips recobro
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getCntPrestadoresSedesId().getCntPrestador().getNumeroDocumento());
                columna++;
                //nombre ips recobro
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getCntPrestadoresSedesId().getNombreSede());
                columna++;
                //nit ips factura
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getRcoFacturasId().getCmFacturaId().getNit());
                columna++;
                //nombre ips factura
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getRcoFacturasId().getCmFacturaId().getCntPrestador().getRazonSocial());
                columna++;
                //codigo servicio
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getMaServicioCodigo());
                columna++;
                //descripcion servicio
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getMaServicioValor());
                columna++;
                //nombre completo
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getNombreCompleto());
                columna++;
                //tipo documento afiliado
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getMaeTipoDocumentoValor());
                columna++;
                //documento
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getDocumento());
                columna++;
                //valor total recobro 
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                double valorRecobro = (facturaDetalle.getValorTotalRecobro() != null)  ? facturaDetalle.getValorTotalRecobro().doubleValue(): 0;
                celda.setCellValue(valorRecobro);
                columna++;
                //fecha prestacion  
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getFechaHoraPrestacion());
                cellStyleFecha.setDataFormat(createHelperFecha.createDataFormat().getFormat("m/d/yy h:mm"));
                celda.setCellStyle(cellStyleFecha);
                columna++;
                //fecha prestacion  
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getMaDiagnostico());
                columna++;
                //observacion  
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(facturaDetalle.getObservacionDetalle());
                columna++;
                //fecha reporte  
                hoja1.autoSizeColumn(columna);
                celda = fila.createCell(columna);
                celda.setCellValue(new Date());
                cellStyleFecha.setDataFormat(createHelperFecha.createDataFormat().getFormat("m/d/yy h:mm"));
                celda.setCellStyle(cellStyleFecha);
                columna = 0;
                filaR++;
            }
     
            try (OutputStream output = new FileOutputStream(ruta)) {
                libro.write(output);
                output.close();
            }
        }catch(IOException e){
            bean.addError("Hubo un error al creando excel correo, favor contactar al administrador");
        } catch (Exception ex) {
            Logger.getLogger(RecobroConciliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
