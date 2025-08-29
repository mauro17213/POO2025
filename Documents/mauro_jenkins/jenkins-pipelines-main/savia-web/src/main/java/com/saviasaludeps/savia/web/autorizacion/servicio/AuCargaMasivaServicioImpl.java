package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaDetalle;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaDetalleRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaSucesoRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCargaMasivaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AuCargaMasivaServicioImpl extends AccionesBO implements AuCargaMasivaServicioIface {

    private int registroTotal;

    public int getRegistroTotal() {
        return registroTotal;
    }

    public void setRegistroTotal(int registroTotal) {
        this.registroTotal = registroTotal;
    }

    private AuAnexo3CargaRemoto getCargaRemoto() throws Exception {
        return (AuAnexo3CargaRemoto) RemotoEJB.getEJBRemoto("AuAnexo3CargaServicio", AuAnexo3CargaRemoto.class.getName());
    }

    private AuAnexo3CargaDetalleRemoto getCargaDetalleRemoto() throws Exception {
        return (AuAnexo3CargaDetalleRemoto) RemotoEJB.getEJBRemoto("AuAnexo3CargaDetalleServicio", AuAnexo3CargaDetalleRemoto.class.getName());
    }

    private AuAnexo3CargaSucesoRemoto getCargaSucesoRemoto() throws Exception {
        return (AuAnexo3CargaSucesoRemoto) RemotoEJB.getEJBRemoto("AuAnexo3CargaSucesoServicio", AuAnexo3CargaSucesoRemoto.class.getName());
    }
    
    private AuAnexo3CargaRemoto getAuAnexo3CargaRemoto() throws Exception {
        return (AuAnexo3CargaRemoto) RemotoEJB.getEJBRemoto("AuAnexo3CargaServicio", AuAnexo3CargaRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuCargaMasivaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            listarDetalles(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            listarSucesos(bean);
                            break;
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
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
                    break;
                case Url.ACCION_ADICIONAL_3:
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
    
    public void ver( AuCargaMasivaBean bean) {
        try {
            bean.setObjeto(getCargaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }
    
    private void guardar(AuCargaMasivaBean bean) {
        try {
            int id = 0;
            String ruta = PropApl.getInstance().get(PropApl.AU_A3_CARGA_MASIVA);
            //String ruta = "D:\\Adjuntos\\";
            //VALIDAR DUPLICIDAD
            List<AuAnexo3Carga> listaArchivos = getCargaRemoto().consultarArchivoNombre(bean.getObjeto().getNombreArchivo());
            if (!listaArchivos.isEmpty()) {
                bean.addError("Ya existe una carga con el mismo nombre de archivo");
            }
            // validacion de estado de carga para no permitir subir mas archivos
            bean.getObjeto().setGnEmpresasId(bean.getConexion().getEmpresa());
            List<AuAnexo3Carga> estadoCarga = getAuAnexo3CargaRemoto().consultarEstadoProceso(bean.getObjeto().getGnEmpresasId().getId());
            if(!estadoCarga.isEmpty()){
                bean.addError("Tiene una carga en proceso, esperar hasta que termine para realizar la próxima carga");
            }
            //GUARDAR ARCHIVO
            bean.getObjeto().getGenerarArchivo();
            String crearArchivo = crearArchivo(bean, ruta, bean.getObjeto().getArchivo());
            if (crearArchivo.startsWith("Error") == false && bean.isError() == false) {
                bean.getObjeto().setExiste(true);
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setRuta(ruta);
                bean.getObjeto().setFechaHoraInicio(new Date());
                
                //bean.getObjeto().setCntPrestadorSede(new Cnt)
                bean.getObjeto().setEstado(AuAnexo3Carga.ESTADO_COLA);
                id = getCargaRemoto().insertar(bean.getObjeto());
                if (id > 0) {
                    bean.addMensaje("Se ha puesto la carga en cola, por favor consulte más tarde el estado consecutivo de carga " + id);
                }
            } else if (bean.isError() == false) {
                bean.addError(crearArchivo);
                bean.addError("Ocurrió un error guardando el adjunto");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void cambiarEstadoCarga(AuCargaMasivaBean bean) {
        try {
            AuAnexo3Carga objeto = bean.getObjeto();
            bean.auditoriaModificar(objeto);
            objeto.setUsuarioGestionEstado(objeto.getUsuarioModifica());
            objeto.setTerminalGestionEstado(objeto.getTerminalModifica());
            objeto.setFechaHoraGestionEstado(objeto.getFechaHoraModifica());
            getCargaRemoto().actualizarEstado(objeto);
            bean.addMensaje("Se ha actualizado correctamente el registro" );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private String crearArchivo(AuCargaMasivaBean bean, String ruta, String nombre) throws IOException {
        String respuesta;
        try {
            copyInputStreamToFile(bean.getObjeto().getAdjuntoStreamValidacion(), new File(ruta, nombre));
            respuesta = "Carga de solicitud guardada";
        } catch (IOException e) {
            respuesta = "Error: " + e.toString();
        } finally {
            if (bean.getObjeto().getAdjuntoStreamDocumento() != null) {
                bean.getObjeto().getAdjuntoStreamDocumento().close();
            }
            if (bean.getObjeto().getAdjuntoStreamValidacion() != null) {
                bean.getObjeto().getAdjuntoStreamValidacion().close();
            }
        }
        return respuesta;
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, false);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private void listar(AuCargaMasivaBean bean) {
        try {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(null);
            } else {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCargaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarSucesos(AuCargaMasivaBean bean) {
        try {
            bean.getParamConsultaSucesos().setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsultaSucesos().setCantidadRegistros(getCargaSucesoRemoto().consultarCantidadLista(bean.getParamConsultaSucesos()));
            bean.setListaSucesos(getCargaSucesoRemoto().consultarLista(bean.getParamConsultaSucesos()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarDetalles(AuCargaMasivaBean bean) {
        try {
            bean.getParamConsultaDetalles().setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsultaDetalles().setCantidadRegistros(getCargaDetalleRemoto().consultarCantidadLista(bean.getParamConsultaDetalles()));
            bean.setListaDetalles(getCargaDetalleRemoto().consultarLista(bean.getParamConsultaDetalles()));
            for (AuAnexo3CargaDetalle detalle : bean.getListaDetalles()) {
                if (detalle.getNumeroSolicitud() == null) {
                    detalle.setNumeroSolicitud("Error");
                }
            }
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
    
    @Override
    public AuAnexo3Carga consultarCarga( AuCargaMasivaBean bean) {
        AuAnexo3Carga obj = null;
        try {
            obj = getCargaRemoto().consultar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
        return obj;
    }
}
