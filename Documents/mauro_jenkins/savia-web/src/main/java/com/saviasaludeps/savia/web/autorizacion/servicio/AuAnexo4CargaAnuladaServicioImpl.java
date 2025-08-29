package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4CargaAnulada;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4CargaAnuladaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4CargaAnuladaSucesoRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.web.autorizacion.bean.AuAnexo4CargaAnuladaBean;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author raul-palacios
 */
public class AuAnexo4CargaAnuladaServicioImpl extends AccionesBO implements AuAnexo4CargaAnuladaServicioIface {


    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    
    private AuAnexo4CargaAnuladaRemoto getAuAnexo4CargaAnuladaRemoto() throws Exception {
        return (AuAnexo4CargaAnuladaRemoto) RemotoEJB.getEJBRemoto("AuAnexo4CargaAnuladaServicio", AuAnexo4CargaAnuladaRemoto.class.getName());
    }
    
    private AuAnexo4CargaAnuladaSucesoRemoto getAuAnexo4CargaAnuladaSucesoRemoto() throws Exception {
        return (AuAnexo4CargaAnuladaSucesoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4CargaAnuladaSucesoServicio", AuAnexo4CargaAnuladaSucesoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuAnexo4CargaAnuladaBean bean) {
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
                    // se utiliza para descarga el archivo
                    break;
                case Url.ACCION_ADICIONAL_2:
                    listarSucesos(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            cambiarEstadoCarga(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                   
                    break;
            }
        }
    }

    private void cargas(AuAnexo4CargaAnuladaBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    private void listar(AuAnexo4CargaAnuladaBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getAuAnexo4CargaAnuladaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAuAnexo4CargaAnuladaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void ver(AuAnexo4CargaAnuladaBean bean) {
        try {
            bean.setObjeto(getAuAnexo4CargaAnuladaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }

    public void crear(AuAnexo4CargaAnuladaBean bean) {
        try {
            bean.setObjeto(new AuAnexo4CargaAnulada());
            bean.getObjeto().setGnEmpresasId(bean.getConexion().getEmpresa());
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            bean.getObjeto().setRegistros(0);
            bean.getObjeto().setEstado(AuNoSolicitudCarga.ESTADO_EN_COLA);     
            bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.AU_A3_CARGA_MASIVA));            
            bean.getObjeto().setExiste(true);
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }
    
    private void guardar(AuAnexo4CargaAnuladaBean bean) {
        try {
            int id = 0;
            String mensajeValidacion = "";
            
//            String ruta = "D:\\Adjuntos\\";
            //VALIDAR DUPLICIDAD
            List<AuAnexo4CargaAnulada> listaArchivos = getAuAnexo4CargaAnuladaRemoto().consultarArchivoNombre(bean.getObjeto().getNombre());
            if (!listaArchivos.isEmpty()) {
                bean.addError("Ya existe una carga con el mismo nombre de archivo");
            }
            // validacion de estado de carga para no permitir subir mas archivos
            List<AuAnexo4CargaAnulada> estadoCarga = getAuAnexo4CargaAnuladaRemoto().consultarEstadoProceso(bean.getObjeto().getGnEmpresasId().getId());
            if(!estadoCarga.isEmpty()){
                bean.addError("Tiene una carga en proceso, esperar hasta que termine para realizar la próxima carga");
            }
            //valida archivo y guarda archivo
            if(!bean.isError()){
                bean.getObjeto().setArchivo(bean.getObjeto().getGenerarNombreArchivo());
                mensajeValidacion = validarArchivoAutorizaciones(bean.getObjeto());
                if(!mensajeValidacion.isBlank()){
                    bean.addMensaje(mensajeValidacion);
                }
            }
            if (!bean.isError() && mensajeValidacion.isBlank()) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setFechaHoraInicio(new Date());
                id = getAuAnexo4CargaAnuladaRemoto().insertar(bean.getObjeto());
                if (id > 0) {
                    bean.addMensaje("Se ha puesto la carga en cola, por favor consulte más tarde el estado consecutivo de carga " + id);
                }
            } 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarSucesos(AuAnexo4CargaAnuladaBean bean) {
        try {
            if (bean.getParamConsulta(0).getFiltros() == null) {
                bean.getParamConsulta(0).setFiltros(new HashMap());
            }
            bean.getParamConsulta(0).getFiltros().put("auAnexo4CargaAnuladasId.id", bean.getObjeto().getId());
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo4CargaAnuladaSucesoRemoto().consultarCantidadSucesoLista(bean.getParamConsulta(0)));
            bean.setListaSucesos(getAuAnexo4CargaAnuladaSucesoRemoto().consultarSucesoLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cambiarEstadoCarga(AuAnexo4CargaAnuladaBean bean) {
        try {
            AuAnexo4CargaAnulada objeto = bean.getObjeto();
            bean.auditoriaModificar(objeto);
            getAuAnexo4CargaAnuladaRemoto().actualizarEstado(objeto);
            bean.addMensaje("Se ha actualizado correctamente el registro" );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @SuppressWarnings("UseSpecificCatch")
    private String validarArchivoAutorizaciones(AuAnexo4CargaAnulada objeto) {
        String mensaje = "";
        try {
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStream());
            Sheet hoja = libro.getSheetAt(0);
            int inicioFila = 1;
            int columna;
            boolean finalizo = true;
            while (finalizo) {
                Row fila = hoja.getRow(inicioFila);
                if (fila == null) {
                    finalizo = false;
                } else {
                    columna = 0;
                    //consecutivo                    
                    Cell celda = fila.getCell(columna);
                    String consecutivo = null;
                    if (celda != null) {
                        try {
                            consecutivo = dataFormatter.formatCellValue(celda);
                            if (consecutivo.isEmpty()) {
                                mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                            }else if(!consecutivo.isEmpty()){
                                int consecutivoi = Integer.parseInt(consecutivo);
                            }
                        } catch (Exception e) {
                            mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " no es un número.\n";
                        }
                    } else {
                        mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Numero Autorizacion
                    columna++;
                    celda = fila.getCell(columna);
                    String numeroAutorizacion = null;
                    if (celda != null) {
                        try {
                            numeroAutorizacion = dataFormatter.formatCellValue(celda);
                            if (numeroAutorizacion.isEmpty()) {
                                mensaje += "El número autorización en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                            } else if(!numeroAutorizacion.isEmpty()){
                                int autorizacion = Integer.parseInt(numeroAutorizacion);
                            }
                        } catch (Exception e) {
                            mensaje += "El número autorización en la fila #" + (inicioFila + 1) + " no es un número.\n";
                        }
                    } else {
                        mensaje += "El número autorización en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    if (consecutivo == null && numeroAutorizacion == null) {
                        break;
                    }
                    //motivo
                    columna++;
                    celda = fila.getCell(columna);
                    @SuppressWarnings("UnusedAssignment")
                    String motivo = null;
                    if (celda != null) {
                        try {
                            motivo = dataFormatter.formatCellValue(celda);
                            if (motivo.isEmpty()) {
                                mensaje += "El motivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                            } else if (motivo.length() > 2) {
                                mensaje += "El motivo en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                            }else if(!motivo.isEmpty()){
                                int autorizacion = Integer.parseInt(numeroAutorizacion);
                            }
                        } catch (Exception e) {
                            mensaje += "El motivo en la fila #" + (inicioFila + 1) + " no es un número.\n";
                        }
                    } else {
                        mensaje += "El motivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                   
                    //comentario
                    columna++;
                    celda = fila.getCell(columna);
                    String comentario = dataFormatter.formatCellValue(celda);
                    if (comentario.isEmpty()) {
                        mensaje += "El comentario en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    if (!mensaje.isEmpty()) {
                        finalizo = false;
                    } else {
                        inicioFila++;
                    }
                }
            }
            //Guardar archivo
            try {
                objeto.setRegistros(inicioFila - 1);
                //Se genera el documento
                if(mensaje.isEmpty()){
                    FileOutputStream out = new FileOutputStream(new File(objeto.getRuta() + objeto.getArchivo()));
                    libro.write(out);
                    out.close();
                }
            } catch (IOException e) {
                mensaje = "El archivo no se pudo almacenar.\n";
            }
        } catch (Exception e) {
            mensaje = "Hubo un fallo al validar favor contactar al adminitrador.\n";
        }
        return mensaje;
    } 
    
    @Override
    public AuAnexo4CargaAnulada consultarCarga(AuAnexo4CargaAnuladaBean bean) {
        AuAnexo4CargaAnulada obj = null;
        try {
            obj = getAuAnexo4CargaAnuladaRemoto().consultar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
        return obj;
    }
    
    @Override
    public void cargaInicial(AuAnexo4CargaAnuladaBean bean) {
        try {
            /*bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
            bean.setHashClasificacion(AuConstantes.obtenerHashMaestro(bean.getListaClasificacion()));*/
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
