package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaCarga;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaDetalleRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaSucesoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaCargaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaCargaSucesoRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCargaEntregaSinAutorizacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class AuCargaEntregaSinAutorizacionServicioImpl extends AccionesBO implements AuCargaEntregaSinAutorizacionServicioIface {

    private AuNoSolicitudEntregaCargaRemoto getAuNoSolicitudEntregaCargaRemoto() throws Exception {
        return (AuNoSolicitudEntregaCargaRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudEntregaCargaServicio", AuNoSolicitudEntregaCargaRemoto.class.getName());
    }
    
    private AuNoSolicitudEntregaCargaSucesoRemoto getAuNoSolicitudEntregaCargaSucesoRemoto() throws Exception {
        return (AuNoSolicitudEntregaCargaSucesoRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudEntregaCargaSucesoServicio", AuNoSolicitudEntregaCargaSucesoRemoto.class.getName());
    }
    

    @Override
    public void Accion(AuCargaEntregaSinAutorizacionBean bean) {
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
                    listarSucesos(bean);
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
    
    private void listar(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(null);
            } else {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getAuNoSolicitudEntregaCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAuNoSolicitudEntregaCargaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void ver(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuNoSolicitudEntregaCargaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
    }
    
    public void crear(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            bean.setObjeto(new AuNoSolicitudEntregaCarga());
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
    
    private void guardar(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            int id = 0;
//            String ruta = "D:\\Adjuntos\\";
            //VALIDAR DUPLICIDAD
            List<AuNoSolicitudEntregaCarga> listaArchivos = getAuNoSolicitudEntregaCargaRemoto().consultarArchivoNombre(bean.getObjeto().getNombre());
            if (!listaArchivos.isEmpty()) {
                bean.addError("Ya existe una carga con el mismo nombre de archivo");
            }
            // validacion de estado de carga para no permitir subir mas archivos
 
            List<AuNoSolicitudEntregaCarga> estadoCarga = getAuNoSolicitudEntregaCargaRemoto().consultarEstadoProceso(bean.getObjeto().getGnEmpresasId().getId());
            if(!estadoCarga.isEmpty()){
                bean.addError("Tiene una carga en proceso, esperar hasta que termine para realizar la próxima carga");
            }
            //GUARDAR ARCHIVO
            String mensajeValidacion = validarArchivo(bean.getObjeto());
            if(mensajeValidacion.isEmpty() && !bean.isError()){ 
                bean.getObjeto().setArchivo(bean.getObjeto().getGenerarNombreArchivo());
                boolean crearArchivo = generarArchivo(bean.getObjeto());
                if (crearArchivo) {
                    bean.auditoriaGuardar(bean.getObjeto());
                    bean.getObjeto().setFechaHoraInicio(new Date());
                    bean.getObjeto().setEstado(AuNoSolicitudCarga.ESTADO_EN_COLA);
                    id = getAuNoSolicitudEntregaCargaRemoto().insertar(bean.getObjeto());
                    if (id > 0) {
                        bean.addMensaje("Se ha puesto la carga en cola, por favor consulte más tarde el estado consecutivo de carga " + id);
                    }
                } else if (bean.isError() == false) {
                    bean.addError("Ocurrió un error guardando el adjunto");
                }
            }else{
                bean.addError(mensajeValidacion);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarSucesos(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            bean.getParamConsulta(0).setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsulta(0).setCantidadRegistros(getAuNoSolicitudEntregaCargaSucesoRemoto().consultarCantidadSucesoLista(bean.getParamConsulta(0)));
            bean.setListaSucesos(getAuNoSolicitudEntregaCargaSucesoRemoto().consultarSucesoLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }


    private void cambiarEstadoCarga(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            AuNoSolicitudEntregaCarga objeto = bean.getObjeto();
            bean.auditoriaModificar(objeto);
            getAuNoSolicitudEntregaCargaRemoto().actualizarEstado(objeto);
            bean.addMensaje("Se ha actualizado correctamente el registro" );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(AuNoSolicitudEntregaCarga adjunto) throws Exception {
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
    public AuNoSolicitudEntregaCarga consultarCarga(AuCargaEntregaSinAutorizacionBean bean) {
        AuNoSolicitudEntregaCarga obj = null;
        try {
            obj = getAuNoSolicitudEntregaCargaRemoto().consultar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar la carga");
        }
        return obj;
    }
    
    private String validarArchivo(AuNoSolicitudEntregaCarga objeto) {
        String mensaje = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStreamValidaciones());
            Sheet hoja = libro.getSheetAt(0);
            int inicioFila = 1;
            int columna;
            boolean finalizo = true;
            Date fechaActual = new Date();
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
                        consecutivo = dataFormatter.formatCellValue(celda);
                        if (consecutivo.isEmpty()) {
                            mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                        }
                    }
                    //Numero Autorizacion
                    columna++;
                    celda = fila.getCell(columna);
                    Integer numeroAutorizacion = null;
                    if (celda != null) {
                        try {
                            numeroAutorizacion = Integer.parseInt(dataFormatter.formatCellValue(celda));
                            if (numeroAutorizacion == 0) {
                                mensaje += "El numero de autorización en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                            }
                        } catch (NumberFormatException n) {
                            mensaje += "El numero de autorización en la fila #" + (inicioFila + 1) + " no es número.\n";
                        }
                    }
                    //Tipo tecnologia
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoTecnologia = dataFormatter.formatCellValue(celda);
                    if (tipoTecnologia.isEmpty()) {
                        mensaje += "El tipo tecnologia en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Codigo tecnologia
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoTecnologia = dataFormatter.formatCellValue(celda);
                    if (codigoTecnologia.isEmpty()) {
                        mensaje += "El codigo tecnologia en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Fecha prestacion 
                    columna++;
                    celda = fila.getCell(columna);
                    String fechaHoraPrestacion = dataFormatter.formatCellValue(celda);
                    if (fechaHoraPrestacion.isEmpty()) {
                        mensaje += "La fecha hora prestación en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        try {
                            if (DateUtil.isCellDateFormatted(celda)) {
                                Date fechaIng = celda.getDateCellValue();
                                if (fechaIng.compareTo(fechaActual) > 0) {
                                    mensaje += "La fecha hora prestacion en la fila #" + (inicioFila + 1) + " debe ser anterior a la fecha actual.\n";
                                }
                            } else {
                                mensaje += "La fecha hora prestación  en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                            }
                        } catch (Exception e) {
                            mensaje += "La fecha hora prestación en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //2023-12-20 jyperez adicionamos los campos que se evaluaran para medicamentos e insumos
                    /*if (objeto.getTipoTecnologia() == AuEntregaCarga.TIPO_TECNOLOGIA_MEDICAMENTO ||
                            objeto.getTipoTecnologia() == AuEntregaCarga.TIPO_TECNOLOGIA_INSUMO) {*/
                    //Numero Entrega
                    columna++;
                    celda = fila.getCell(columna);
                    try {
                        int numeroEnrega = Integer.parseInt(dataFormatter.formatCellValue(celda));
                        if (numeroEnrega > 12) {
                            mensaje += "El valor de numero entrega en la fila #" + (inicioFila + 1) + " es mayor a 12.\n";
                        }
                    } catch (NumberFormatException n) {
                        mensaje += "El valor de numero entrega en la fila #" + (inicioFila + 1) + " no es un numero.\n";
                    }
                    //Cantidad a Entrgar
                    columna++;
                    celda = fila.getCell(columna);
                    Integer cantAutorizada = null;
                    if (celda != null) {
                        try {
                            cantAutorizada = Integer.parseInt(dataFormatter.formatCellValue(celda));
                            if (cantAutorizada == 0) {
                                mensaje += "La cantidad autorizada en la fila #" + (inicioFila + 1) + " esta vacia.\n";
                            }
                        } catch (NumberFormatException n) {
                            mensaje += "La cantidad autorizada en la fila #" + (inicioFila + 1) + " no es número.\n";
                        }
                    }
                    //Cantidad Entregada
                    columna++;
                    celda = fila.getCell(columna);
                    Integer cantEntregada = null;
                    if (celda != null) {
                        try {
                            cantEntregada = Integer.parseInt(dataFormatter.formatCellValue(celda));
                            if (cantEntregada < 0) {
                                mensaje += "La cantidad entregada en la fila #" + (inicioFila + 1) + " no puede ser menor a cero.\n";
                            }
                        } catch (NumberFormatException n) {
                            mensaje += "La cantidad entregada en la fila #" + (inicioFila + 1) + " no es número.\n";
                        }
                    }
                    //Reclama Afiliado
                    columna++;
                    celda = fila.getCell(columna);
                    Integer reclamaAfiliado = null;
                    if (celda != null) {
                        try {
                            reclamaAfiliado = Integer.parseInt(dataFormatter.formatCellValue(celda));
                            if (!(reclamaAfiliado == 0 || reclamaAfiliado == 1)) {
                                mensaje += "El valor de reclama Afiliado en la fila #" + (inicioFila + 1) + " no es Booleano (valor 1 ó 0).\n";
                            }
                        } catch (NumberFormatException n) {
                            mensaje += "El valor de reclama Afiliado en la fila #" + (inicioFila + 1) + " no es Booleano (valor 1 ó 0).\n";
                        }
                    }
                   
                    //validamos si reclamaAfiliado esta en 1, si es asi los datos no se tendrán en cuenta
                    if (reclamaAfiliado != null && reclamaAfiliado != 1) {
                        //NombreReclama
                        columna++;
                        celda = fila.getCell(columna);
                        String nombreReclama = dataFormatter.formatCellValue(celda);
                        if (nombreReclama.isEmpty()) {
                            mensaje += "El nombre reclama en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                        } else if (nombreReclama.length() > 128) {
                            mensaje += "El nombre reclama en la fila #" + (inicioFila + 1) + " no debe contener mas de 128 caracteres.\n";
                        }
                        //Telefono
                        columna++;
                        celda = fila.getCell(columna);
                        String telef = null;
                        if (celda != null) {
                            try {
                                telef = dataFormatter.formatCellValue(celda);
                                if (telef.matches("\\d*") && telef.length() == 10) {
                                } else if (!telef.matches("\\d*")) {
                                    mensaje += "El teléfono en la fila #" + (inicioFila + 1) + "no cumple con el formato numérico.\n";
                                } else {
                                    mensaje += "El teléfono en la fila #" + (inicioFila + 1) + " no contiene mas de 10 dígitos.\n";
                                }
                            } catch (Exception n) {
                                mensaje += "El teléfono en la fila #" + (inicioFila + 1) + " no es número.\n";
                            }
                        }
                        //Celular
                        columna++;
                        celda = fila.getCell(columna);
                        String cell = null;
                        if (celda != null) {
                            try {
                                cell = dataFormatter.formatCellValue(celda);
                                if (cell.matches("\\d*") && cell.length() == 10) {
                                } else if (!cell.matches("\\d*")) {
                                    mensaje += "El celular en la fila #" + (inicioFila + 1) + "no cumple con el formato numérico.\n";
                                } else {
                                    mensaje += "El celular en la fila #" + (inicioFila + 1) + " no contiene mas de 10 dígitos.\n";
                                }
                            } catch (Exception n) {
                                mensaje += "El celular en la fila #" + (inicioFila + 1) + " no es número.\n";
                            }
                        }
                    }
                    //Causa No Entrega - no obligatorio

                    if (consecutivo == null && numeroAutorizacion == null && codigoTecnologia == null && fechaHoraPrestacion == null &&
                            reclamaAfiliado == null && cantAutorizada == null && cantEntregada == null) {
                        break;
                    }
                    /*} else {
                        if (consecutivo == null && numeroAutorizacion == null && codigoTecnologia == null && fechaHoraPrestacion == null) {
                            break;
                        }
                    }*/
                    if (!mensaje.isEmpty()) {
                        finalizo = false;
                    } else {
                        inicioFila++;
                    }
                }
            }
            //2023-12-22 jyperez validamos que no haya mensaje de error para guardar el archivo y proceder a almacenar el registro
            if (mensaje.isEmpty()) {
                objeto.setRegistros(inicioFila - 1);
            }
        } catch (Exception e) {
            mensaje = "Hubo un fallo al validar favor contactar al adminitrador.\n";
        }
        return mensaje;
    }
    
    @Override
    public void cargaInicial(AuCargaEntregaSinAutorizacionBean bean) {
        try {
            /*bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
            bean.setHashClasificacion(AuConstantes.obtenerHashMaestro(bean.getListaClasificacion()));*/
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
