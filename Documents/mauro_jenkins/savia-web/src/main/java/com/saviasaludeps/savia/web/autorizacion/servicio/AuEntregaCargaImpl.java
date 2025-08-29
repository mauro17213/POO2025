/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuEntregaCarga;
import com.saviasaludeps.savia.negocio.autorizacion.AuEntregaCargaDetalleRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuEntregaCargaRemoto;
import com.saviasaludeps.savia.negocio.generico.AuEntregaCargaMasivaRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuEntregaCargaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author iavenegas
 */
public class AuEntregaCargaImpl extends AccionesBO implements AuEntregaCargaIface {

    private AuEntregaCargaRemoto getAuEntregaCargaRemoto() throws Exception {
        return (AuEntregaCargaRemoto) RemotoEJB.getEJBRemoto("AuEntregaCargaServicio", AuEntregaCargaRemoto.class.getName());
    }

    private AuEntregaCargaDetalleRemoto getAuEntregaCargaDetalleRemoto() throws Exception {
        return (AuEntregaCargaDetalleRemoto) RemotoEJB.getEJBRemoto("AuEntregaCargaDetalleServicio", AuEntregaCargaDetalleRemoto.class.getName());
    }

    private AuEntregaCargaMasivaRemoto getAuEntregaCargaMasivaRemoto() throws Exception {
        return (AuEntregaCargaMasivaRemoto) RemotoEJB.getEJBRemotoGenerico("AuEntregaCargaMasivaServicio", AuEntregaCargaMasivaRemoto.class.getName());
    }

    @Override
    public void Accion(AuEntregaCargaBean bean) {
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
                            listarFallos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    break;

            }
        }
    }

    @Override
    public void cargasInicial(AuEntregaCargaBean bean) {
    }

    private void listar(AuEntregaCargaBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAuEntregaCargaRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuEntregaCargaRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar al adminitrador");
        }
    }

    private void crear(AuEntregaCargaBean bean) {
        try {
            bean.setObjeto(new AuEntregaCarga());
            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa().getId());
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            bean.getObjeto().setRegistros(0);
            bean.getObjeto().setEstado(AuEntregaCarga.ESTADO_EN_COLA);
            bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.AU_RUTA_CARGA_ENTREGA));
//            bean.getObjeto().setRuta("D:\\Adjuntos\\");
        } catch (Exception e) {
            bean.addError("Hubo un error al crear, favor contactar al adminitrador");
        }
    }

    private void guardar(AuEntregaCargaBean bean) {
        try {
            //VALIDAR DUPLICIDAD
            List<AuEntregaCarga> listaArchivos = getAuEntregaCargaRemoto().consultarArchivoNombre(bean.getObjeto().getNombre());
            if (!listaArchivos.isEmpty()) {
                bean.addError("Ya existe una carga con el mismo nombre de archivo");
            }
            if (!bean.isError()) {
                String mensajeValidacion = validarArchivo(bean.getObjeto());
                if (mensajeValidacion.isEmpty()) {
                    bean.auditoriaGuardar(bean.getObjeto());
                    bean.getObjeto().setFechaHoraInicio(new Date());
                    bean.getObjeto().setId(getAuEntregaCargaRemoto().insertar(bean.getObjeto()));
                } else {
                    bean.addError(mensajeValidacion);
                }
            }

        } catch (IOException e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al adminitrador");
        } catch (Exception ex) {
            Logger.getLogger(AuEntregaCargaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ver(AuEntregaCargaBean bean) {
        try {
            bean.setObjeto(getAuEntregaCargaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo, favor contactar al adminitrador");
        }
    }

    private void listarFallos(AuEntregaCargaBean bean) {
        try {
            bean.getParamConsulta(1).setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getAuEntregaCargaDetalleRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaDetalle(getAuEntregaCargaDetalleRemoto().consultarLista(bean.getParamConsulta(1)));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al generar el reporte de fallo, favor contactar al adminitrador");
        }
    }

    private String validarArchivo(AuEntregaCarga objeto) {
        String mensaje = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStream());
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
                    //Codigo tecnologia
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoTecnologia = dataFormatter.formatCellValue(celda);
                    if (codigoTecnologia.isEmpty()) {
                        mensaje += "El codigo tecnologia en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    // validacioon no prestado
                    celda = fila.getCell(AuEntregaCarga.COLUMNA_NO_PRESTADO);
                    String noPrestado = dataFormatter.formatCellValue(celda);
                    if (noPrestado.isEmpty()) {
                        //Fecha Ingreso
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
                        //Cantidad Autorizada
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
                        if (consecutivo == null && numeroAutorizacion == null && codigoTecnologia == null && fechaHoraPrestacion == null
                                && reclamaAfiliado == null && cantAutorizada == null && cantEntregada == null) {
                            break;
                        }
                    }else{
                        //No Prestado
                        columna = columna + 9;
                        celda = fila.getCell(columna);
                        String strNoPrestado = dataFormatter.formatCellValue(celda);
                        if(!strNoPrestado.equals("1")){
                            mensaje += "El NO prestado en la fila #" + (inicioFila + 1) + "no cumple con el formato numérico.\n";
                        }
                        //Justificacion no prestado
                        columna++;
                        celda = fila.getCell(columna);
                        String justificacionNoPrestado = dataFormatter.formatCellValue(celda);
                        if (justificacionNoPrestado.isEmpty()) {
                            mensaje += "La justifiación no prestado en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                        }
                        //Causa No Entrega - no obligatorio
                        if (consecutivo == null && numeroAutorizacion == null && codigoTecnologia == null) {
                            break;
                        }
                    }
                    if (!mensaje.isEmpty()) {
                        finalizo = false;
                    } else {
                        inicioFila++;
                    }
                }
            }

            try {
                //2023-12-22 jyperez validamos que no haya mensaje de error para guardar el archivo y proceder a almacenar el registro
                if (mensaje.isEmpty()) {
                    objeto.setRegistros(inicioFila - 1);
                    //Se genera el documento
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
}
