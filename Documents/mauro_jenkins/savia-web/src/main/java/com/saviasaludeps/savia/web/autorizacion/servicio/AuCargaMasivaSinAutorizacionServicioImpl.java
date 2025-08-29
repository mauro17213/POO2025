package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaDetalleRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaSucesoRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCargaMasivaSinAutorizacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class AuCargaMasivaSinAutorizacionServicioImpl extends AccionesBO implements AuCargaMasivaSinAutorizacionServicioIface {

    private AuNoSolicitudCargaRemoto getAuNoSolicitudCargaRemoto() throws Exception {
        return (AuNoSolicitudCargaRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudCargaServicio", AuNoSolicitudCargaRemoto.class.getName());
    }
    
    private AuNoSolicitudCargaSucesoRemoto getAuNoSolicitudCargaSucesoRemoto() throws Exception {
        return (AuNoSolicitudCargaSucesoRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudCargaSucesoServicio", AuNoSolicitudCargaSucesoRemoto.class.getName());
    }
    
    private AuNoSolicitudCargaDetalleRemoto getAuNoSolicitudCargaDetalleRemoto() throws Exception {
        return (AuNoSolicitudCargaDetalleRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudCargaDetalleServicio", AuNoSolicitudCargaDetalleRemoto.class.getName());
    }

    @Override
    public void Accion(AuCargaMasivaSinAutorizacionBean bean) {
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
                    }
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
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    listarDetalles(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    listarSucesos(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            cambiarEstadoCarga(bean);
                            break;
                    }
                    break;
            }
        }
    }
    
    private void listar(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(null);
            } else {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getAuNoSolicitudCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAuNoSolicitudCargaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void ver(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuNoSolicitudCargaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }
    
    public void crear(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            bean.setObjeto(new AuNoSolicitudCarga());
            bean.getObjeto().setGnEmpresasId(bean.getConexion().getEmpresa());
            bean.getObjeto().setRegistrosExitosos(0);
            bean.getObjeto().setRegistrosRechazados(0);
            bean.getObjeto().setRegistrosTotal(0);
            bean.getObjeto().setEstado(AuNoSolicitudCarga.ESTADO_EN_COLA);     
            bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.AU_A3_CARGA_MASIVA));            
            bean.getObjeto().setExiste(true);
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }
    
    private void guardar(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            int id = 0;
//            String ruta = "D:\\Adjuntos\\";
            //VALIDAR DUPLICIDAD
            List<AuNoSolicitudCarga> listaArchivos = getAuNoSolicitudCargaRemoto().consultarArchivoNombre(bean.getObjeto().getNombreArchivo());
            if (!listaArchivos.isEmpty()) {
                bean.addError("Ya existe una carga con el mismo nombre de archivo");
            }
            // validacion de estado de carga para no permitir subir mas archivos
 
            List<AuNoSolicitudCarga> estadoCarga = getAuNoSolicitudCargaRemoto().consultarEstadoProceso(bean.getObjeto().getGnEmpresasId().getId());
            if(!estadoCarga.isEmpty()){
                bean.addError("Tiene una carga en proceso, esperar hasta que termine para realizar la próxima carga");
            }
            //GUARDAR ARCHIVO
            bean.getObjeto().setArchivo(bean.getObjeto().getGenerarNombreArchivo());
            boolean crearArchivo = generarArchivo(bean.getObjeto());
            if (crearArchivo && !bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setFechaHoraInicio(new Date());
          
                //bean.getObjeto().setCntPrestadorSede(new Cnt)
                bean.getObjeto().setEstado(AuNoSolicitudCarga.ESTADO_EN_COLA);
                id = getAuNoSolicitudCargaRemoto().insertar(bean.getObjeto());
                if (id > 0) {
                    bean.addMensaje("Se ha puesto la carga en cola, por favor consulte más tarde el estado consecutivo de carga " + id);
                }
            } else if (bean.isError() == false) {
                bean.addError("Ocurrió un error guardando el adjunto");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarSucesos(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            bean.getParamConsulta(0).setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsulta(0).setCantidadRegistros(getAuNoSolicitudCargaSucesoRemoto().consultarCantidadSucesoLista(bean.getParamConsulta(0)));
            bean.setListaSucesos(getAuNoSolicitudCargaSucesoRemoto().consultarSucesoLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarDetalles(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            bean.getParamConsulta(1).setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getAuNoSolicitudCargaDetalleRemoto().consultarCantidadDetalleLista(bean.getParamConsulta(1)));
            bean.setListaDetalles(getAuNoSolicitudCargaDetalleRemoto().consultarDetalleLista(bean.getParamConsulta(1)));
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
    
    private void cambiarEstadoCarga(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            AuNoSolicitudCarga objeto = bean.getObjeto();
            bean.auditoriaModificar(objeto);
            objeto.setUsuarioGestionEstado(objeto.getUsuarioModifica());
            objeto.setTerminalGestionEstado(objeto.getTerminalModifica());
            objeto.setFechaHoraGestionEstado(objeto.getFechaHoraModifica());
            getAuNoSolicitudCargaRemoto().actualizarEstado(objeto);
            bean.addMensaje("Se ha actualizado correctamente el registro" );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(AuNoSolicitudCarga adjunto) throws Exception {
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
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {

            }
        }
        return esArchivoGuardado;
    } 
    
    @Override
    public AuNoSolicitudCarga consultarCarga(AuCargaMasivaSinAutorizacionBean bean) {
        AuNoSolicitudCarga obj = null;
        try {
            obj = getAuNoSolicitudCargaRemoto().consultar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
        return obj;
    }
    
    @Override
    public void cargaInicial(AuCargaMasivaSinAutorizacionBean bean) {
        try {
            /*bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
            bean.setHashClasificacion(AuConstantes.obtenerHashMaestro(bean.getListaClasificacion()));*/
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
