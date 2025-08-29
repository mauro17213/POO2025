package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaAnulada;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaAnuladaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3CargaAnuladaSucesoRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import com.saviasaludeps.savia.web.autorizacion.bean.AuAnexo3CargaAnuladaBean;
import com.saviasaludeps.savia.web.utilidades.PropApl;
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
public class AuAnexo3CargaAnuladaServicioImpl extends AccionesBO implements AuAnexo3CargaAnuladaServicioIface {


    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    
    private AuAnexo3CargaAnuladaRemoto getAuAnexo3CargaAnuladaRemoto() throws Exception {
        return (AuAnexo3CargaAnuladaRemoto) RemotoEJB.getEJBRemoto("AuAnexo3CargaAnuladaServicio", AuAnexo3CargaAnuladaRemoto.class.getName());
    }
    
    private AuAnexo3CargaAnuladaSucesoRemoto getAuAnexo3CargaAnuladaSucesoRemoto() throws Exception {
        return (AuAnexo3CargaAnuladaSucesoRemoto) RemotoEJB.getEJBRemoto("AuAnexo3CargaAnuladaSucesoServicio", AuAnexo3CargaAnuladaSucesoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuAnexo3CargaAnuladaBean bean) {
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

    private void cargas(AuAnexo3CargaAnuladaBean bean) {
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

    private void listar(AuAnexo3CargaAnuladaBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getAuAnexo3CargaAnuladaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAuAnexo3CargaAnuladaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void ver(AuAnexo3CargaAnuladaBean bean) {
        try {
            bean.setObjeto(getAuAnexo3CargaAnuladaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }

    public void crear(AuAnexo3CargaAnuladaBean bean) {
        try {
            bean.setObjeto(new AuAnexo3CargaAnulada());
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
    
    @SuppressWarnings("UnusedAssignment")
    private void guardar(AuAnexo3CargaAnuladaBean bean) {
        try {   
            int id = 0;
            String mensajeValidacion = "";
            
//            String ruta = "D:\\Adjuntos\\";
            //VALIDAR DUPLICIDAD
            List<AuAnexo3CargaAnulada> listaArchivos = getAuAnexo3CargaAnuladaRemoto().consultarArchivoNombre(bean.getObjeto().getNombre());
            if (!listaArchivos.isEmpty()) {
                bean.addError("Ya existe una carga con el mismo nombre de archivo");
            }
            // validacion de estado de carga para no permitir subir mas archivos
            List<AuAnexo3CargaAnulada> estadoCarga = getAuAnexo3CargaAnuladaRemoto().consultarEstadoProceso(bean.getObjeto().getGnEmpresasId().getId());
            if(!estadoCarga.isEmpty()){
                bean.addError("Tiene una carga en proceso, esperar hasta que termine para realizar la próxima carga");
            }
            //valida archivo y guarda archivo
            if(!bean.isError()){
                bean.getObjeto().setArchivo(bean.getObjeto().getGenerarNombreArchivo());
                mensajeValidacion = validarArchivoSolicitudes(bean.getObjeto());
                if(!mensajeValidacion.isBlank()){
                    bean.addError(mensajeValidacion);
                }
                
            }
            if (!bean.isError() && mensajeValidacion.isBlank()) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setFechaHoraInicio(new Date());
                id = getAuAnexo3CargaAnuladaRemoto().insertar(bean.getObjeto());
                if (id > 0) {
                    bean.addMensaje("Se ha puesto la carga en cola, por favor consulte más tarde el estado consecutivo de carga " + id);
                }
            } 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarSucesos(AuAnexo3CargaAnuladaBean bean) {
        try {
            if (bean.getParamConsulta(0).getFiltros() == null) {
                bean.getParamConsulta(0).setFiltros(new HashMap());
            }
            bean.getParamConsulta(0).getFiltros().put("auAnexo3CargaAnuladasId.id", bean.getObjeto().getId());
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo3CargaAnuladaSucesoRemoto().consultarCantidadSucesoLista(bean.getParamConsulta(0)));
            bean.setListaSucesos(getAuAnexo3CargaAnuladaSucesoRemoto().consultarSucesoLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cambiarEstadoCarga(AuAnexo3CargaAnuladaBean bean) {
        try {
            AuAnexo3CargaAnulada objeto = bean.getObjeto();
            bean.auditoriaModificar(objeto);
            getAuAnexo3CargaAnuladaRemoto().actualizarEstado(objeto);
            bean.addMensaje("Se ha actualizado correctamente el registro" );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @SuppressWarnings({"UseSpecificCatch", "ConvertToTryWithResources"})
    private String validarArchivoSolicitudes(AuAnexo3CargaAnulada objeto) {
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
                    //Numero Solicitud
                    columna++;
                    celda = fila.getCell(columna);
                    String numeroSolicitud = null;
                    if (celda != null) {
                        try {
                            numeroSolicitud = dataFormatter.formatCellValue(celda);
                            if (numeroSolicitud.isEmpty()) {
                                mensaje += "El número solicitud en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                            } else if(!numeroSolicitud.isEmpty()){
                                int solicitud = Integer.parseInt(numeroSolicitud);
                            }
                        } catch (Exception e) {
                            mensaje += "El número solicitud en la fila #" + (inicioFila + 1) + " no es un número.\n";
                        }
                    } else {
                        mensaje += "El número solicitud en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    if (consecutivo == null && numeroSolicitud == null) {
                        break;
                    }
                    //motivo
                    columna++;
                    celda = fila.getCell(columna);
                    @SuppressWarnings("UnusedAssignment")
                    String motivoStr = null;
                    if (celda != null) {
                        try {
                            motivoStr = dataFormatter.formatCellValue(celda);
                            if (motivoStr.isEmpty()) {
                                mensaje += "El motivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                            } else if (motivoStr.length() > 3) {
                                mensaje += "El motivo en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
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
    public AuAnexo3CargaAnulada consultarCarga(AuAnexo3CargaAnuladaBean bean) {
        AuAnexo3CargaAnulada obj = null;
        try {
            obj = getAuAnexo3CargaAnuladaRemoto().consultar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
        return obj;
    }
    
    @Override
    public void cargaInicial(AuAnexo3CargaAnuladaBean bean) {
        try {
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
