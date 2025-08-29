/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaFalloRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.generico.CargaMasivaAuditoriaConcurrenteRemoto;
import com.saviasaludeps.savia.web.auditoria.concurrente.bean.AucCargaMasivaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author sgiraldov
 */
public class AucCargaMasivaServicioImpl extends AccionesBO implements AucCargaMasivaServicioIface {

    private AucCargaRemoto getAucCargaRemoto() throws Exception {
        return (AucCargaRemoto) RemotoEJB.getEJBRemoto("AucCargaServicio", AucCargaRemoto.class.getName());
    }

    private AucCargaFalloRemoto getAucCargaFalloRemoto() throws Exception {
        return (AucCargaFalloRemoto) RemotoEJB.getEJBRemoto("AucCargaFalloServicio", AucCargaFalloRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private CargaMasivaAuditoriaConcurrenteRemoto getCargaMasivaAuditoriaConcurrenteRemoto() throws Exception {
        return (CargaMasivaAuditoriaConcurrenteRemoto) RemotoEJB.getEJBRemotoGenerico("AuditoriaConcurrenteGenericoServicio", CargaMasivaAuditoriaConcurrenteRemoto.class.getName());
    }
    
    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    @Override
    public void Accion(AucCargaMasivaBean bean) {
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
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    abortar(bean);
                    break;

            }
        }
    }

    @Override
    public void cargasInicial(AucCargaMasivaBean bean) {
    }

    private void listar(AucCargaMasivaBean bean) {
        try {
            if(!bean.getConexion().getEmpresa().isAdministradora()){
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(0).setCantidadRegistros(getAucCargaRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistro(getAucCargaRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar al adminitrador");
        }
    }

    private void ver(AucCargaMasivaBean bean) {
        try {
            bean.setObjeto(getAucCargaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAucCargaFalloList(getAucCargaFalloRemoto().consultarListaPorIdCarga(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al generar el reporte de fallo, favor contactar al adminitrador");
        }
    }

    private void listarFallos(AucCargaMasivaBean bean) {
        try {
            bean.getParamConsulta(1).setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getAucCargaFalloRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaFallos(getAucCargaFalloRemoto().consultarLista(bean.getParamConsulta(1)));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al generar el reporte de fallo, favor contactar al adminitrador");
        }
    }

    private void abortar(AucCargaMasivaBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrar(AucCargaMasivaBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void crear(AucCargaMasivaBean bean) {
        try {
            bean.setObjeto(new AucCarga());
            bean.getObjeto().setGnEmpresaId(bean.getConexion().getEmpresa());
            bean.getObjeto().setRegistrosExitosos(0);
            bean.getObjeto().setRegistrosRechazados(0);
            bean.getObjeto().setHopitalizados(Short.valueOf("0"));
            bean.getObjeto().setRegistrosTotal(0);
            bean.getObjeto().setEstado(AucCarga.ESTADO_EN_COLA);
            bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.AUC_RUTA_CARGA_MASIVA_HOSPITALIZACIONES));
//            bean.getObjeto().setRuta("D:\\Adjuntos");
            bean.getObjeto().setExiste(true);
            //2023-09-07 jyperez nuevo campo tipo, por defecto la carga será censo
            bean.getObjeto().setTipo(AucCarga.TIPO_CENSO);
        } catch (Exception e) {
            bean.addError("Hubo un error al crear, favor contactar al adminitrador");
        }
    }

    private void guardar(AucCargaMasivaBean bean) {
        String mensajeValidacion = "";
        try {
            if (bean.getObjeto().getTipo()== AucCarga.TIPO_CENSO) {
                mensajeValidacion = validarArchivoCenso(bean.getObjeto(), bean);
            } else {
                mensajeValidacion = validarArchivoSeguimiento(bean.getObjeto());
            }
            if (mensajeValidacion.isEmpty()) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setFechaHoraInicio(new Date());
                bean.getObjeto().setId(getAucCargaRemoto().insertar(bean.getObjeto()));
                //getCargaMasivaAuditoriaConcurrenteRemoto().cargaMasivaHospitalizacion(bean.getObjeto());
                if(bean.getObjeto().getId() != null){
                    bean.addMensaje("El archivo " + bean.getObjeto().getNombreArchivo()+ " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
                }
            } else {
                bean.addError(mensajeValidacion);
            }
        } catch (IOException e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al adminitrador");
        } catch (Exception ex) {
            Logger.getLogger(AucCargaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String validarArchivo(AucCarga objeto) {
        String mensaje = "";
        String codigo = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStream());
            Sheet hoja = libro.getSheetAt(0);
            int inicioFila = 2;
            int columna;
            boolean finalizo = true;
            Date fechaActual = new Date();
            String primerCodigoHabilitacion = null;
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
                    } else {
                        mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Tipo Documento
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoDocumento = null;
                    if (celda != null) {
                        tipoDocumento = celda.getStringCellValue();
                        if (tipoDocumento.isEmpty()) {
                            mensaje += "El tipo de documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                        } else if (tipoDocumento.length() > 2) {
                            mensaje += "El tipo de documento en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                        }
                    } else {
                        mensaje += "El tipo de documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    if (consecutivo == null && tipoDocumento == null) {
                        break;
                    }
                    //Documento
                    columna++;
                    celda = fila.getCell(columna);
                    String documento = dataFormatter.formatCellValue(celda);
                    if (documento.isEmpty()) {
                        mensaje += "El documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Fecha Ingreso
                    columna++;
                    celda = fila.getCell(columna);
                    String fechaIngreso = dataFormatter.formatCellValue(celda);
                    if (fechaIngreso.isEmpty()) {
                        mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        try {
                            if (DateUtil.isCellDateFormatted(celda)) {
                                Date fechaIng = celda.getDateCellValue();
                                if (fechaIng.after(fechaActual)) {
                                    mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " debe ser anterior a la fecha actual.\n";
                                }
                            } else {
                                mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                            }
                        } catch (Exception e) {
                            mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Codigo Habilitacion
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoHabilitacion = dataFormatter.formatCellValue(celda);
                    if (codigoHabilitacion.isEmpty()) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (codigoHabilitacion.length() < 2) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    } else if (primerCodigoHabilitacion == null) {
                        primerCodigoHabilitacion = codigoHabilitacion;
                    } else if (!codigoHabilitacion.equals(primerCodigoHabilitacion)) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no es igual a: " + primerCodigoHabilitacion + "\n";
                    }
                    //Codigo Evento
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoEvento = dataFormatter.formatCellValue(celda);
                    if (codigoEvento.isEmpty()) {
                        mensaje += "El codigo de evento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        try {
                            Integer codEvento = Integer.parseInt(codigoEvento);
                            if (codEvento == 0) {
                                mensaje += "El codigo de evento en la fila #" + (inicioFila + 1) + " no cumple con la estructura valida.\n";
                            }
                        } catch (NumberFormatException e) {
                            mensaje += "El codigo de evento en la fila #" + (inicioFila + 1) + " no cumple con la estructura valida.\n";
                        }
                    }
                    //Ubicacion.
                    columna++;
                    celda = fila.getCell(columna);
                    String ubicacion = dataFormatter.formatCellValue(celda);
                    if (ubicacion.isEmpty()) {
                        mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        if (ubicacion.length() > 1) {
                            mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                        } else {
                            try {
                                Integer ubi = Integer.parseInt(ubicacion);
                                if (ubi > 4) {
                                    mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " no tiene un valor valido.\n";
                                }
                            } catch (NumberFormatException e) {
                                mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " no cumple con un valor valido.\n";
                            }
                        }

                    }
                    //tipoIngreso
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoIngreso = dataFormatter.formatCellValue(celda);
                    if (tipoIngreso.isEmpty()) {
                        mensaje += "El tipo de ingreso en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (tipoIngreso.length() > 1) {
                        mensaje += "El tipo de ingreso en la fila #" + (inicioFila + 1) + " no cumple con el tamaño valido.\n";
                    } else {
                        try {
                            Integer tipIngreso = Integer.parseInt(tipoIngreso);
                        } catch (NumberFormatException e) {
                            mensaje += "El tipo de ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Modalidad
                    columna++;
                    celda = fila.getCell(columna);
                    String modalidad = dataFormatter.formatCellValue(celda);
                    if (modalidad.isEmpty()) {
                        mensaje += "La modalidad en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (modalidad.length() > 2) {
                        mensaje += "La modalidad en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    }
                    //Codigo diagnostico
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoDiagnostico = dataFormatter.formatCellValue(celda);
                    if (codigoDiagnostico.isEmpty()) {
                        mensaje += "El codigo de diagnostico en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //tipo diagnostico
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoDiagnostico = dataFormatter.formatCellValue(celda);
                    if (tipoDiagnostico.isEmpty()) {
                        mensaje += "El tipo de diagnostico en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //pricipal
                    columna++;
                    celda = fila.getCell(columna);
                    String principal = dataFormatter.formatCellValue(celda);
                    if (principal.isEmpty()) {
                        mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (principal.length() > 1) {
                        mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    } else {
                        try {
                            Integer bo = Integer.parseInt(principal);
                            if (bo < 0 && bo > 1) {
                                mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                            }
                        } catch (NumberFormatException e) {
                            mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Auditor
                    columna++;
                    celda = fila.getCell(columna);
                    String auditor = dataFormatter.formatCellValue(celda);
                    if (auditor.isEmpty()) {
                        mensaje += "El  en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }

                    if (!mensaje.isEmpty()) {
                        finalizo = false;
                    } else {
                        codigo = codigoHabilitacion;
                        inicioFila++;
                    }
                }
            }
            CntPrestadorSede sede = getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(codigo);
            if (sede == null || sede.getId() == null) {
                mensaje = "El codigo de habilitación no existe.\n";
            } else {
                objeto.setCntPrestadorSedeId(sede);
                objeto.setCntPrestadorId(sede.getCntPrestador());
                objeto.setRegistrosTotal(inicioFila - 2);
                //Guardar archivo
                try {
                    //Se genera el documento
                    FileOutputStream out = new FileOutputStream(new File(objeto.getRuta() + objeto.getArchivo()));
                    libro.write(out);
                    out.close();
                } catch (IOException e) {
                    mensaje = "El archivo no se pudo almacenar.\n";
                }
            }
        } catch (Exception e) {
            mensaje = "Hubo un fallo al validar favor contactar al adminitrador.\n";
        }
        return mensaje;
    }
    
    private String validarArchivoCenso(AucCarga objeto, AucCargaMasivaBean bean) {
        String mensaje = "";
        String codigo = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStream());
            Sheet hoja = libro.getSheetAt(0);
            int inicioFila = 2;
            int columna;
            boolean finalizo = true;
            Date fechaActual = new Date();
            String primerCodigoHabilitacion = null;
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
                    } else {
                        mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Tipo Documento
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoDocumento = null;
                    if (celda != null) {
                        tipoDocumento = celda.getStringCellValue();
                        if (tipoDocumento.isEmpty()) {
                            mensaje += "El tipo de documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                        } else if (tipoDocumento.length() > 2) {
                            mensaje += "El tipo de documento en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                        }
                    } else {
                        mensaje += "El tipo de documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    if (consecutivo == null && tipoDocumento == null) {
                        break;
                    }
                    //Documento
                    columna++;
                    celda = fila.getCell(columna);
                    String documento = dataFormatter.formatCellValue(celda);
                    if (documento.isEmpty()) {
                        mensaje += "El documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Fecha Ingreso
                    columna++;
                    celda = fila.getCell(columna);
                    String fechaIngreso = dataFormatter.formatCellValue(celda);
                    if (fechaIngreso.isEmpty()) {
                        mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        try {
                            if (DateUtil.isCellDateFormatted(celda)) {
                                Date fechaIng = celda.getDateCellValue();
                                if (fechaIng.after(fechaActual)) {
                                    mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " debe ser anterior a la fecha actual.\n";
                                }
                            } else {
                                mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                            }
                        } catch (Exception e) {
                            mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Codigo Habilitacion
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoHabilitacion = dataFormatter.formatCellValue(celda);
                    if (codigoHabilitacion.isEmpty()) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (codigoHabilitacion.length() < 2) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    } else if (primerCodigoHabilitacion == null) {
                        primerCodigoHabilitacion = codigoHabilitacion;
                    } else if (!codigoHabilitacion.equals(primerCodigoHabilitacion)) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no es igual a: " + primerCodigoHabilitacion + "\n";
                    }else if(!bean.getConexion().getEmpresa().isAdministradora()){
                        CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(codigoHabilitacion);
                        if(prestadorSede != null){
                            Empresa empresaDb = getEmpresaRemoto().consultarPorPrestador(prestadorSede.getCntPrestador().getId());
                            if(!bean.getConexion().getEmpresa().getId().equals(empresaDb.getId())){
                                mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no es el mismo a la empresa.\n";
                            }
                        }else{
                            mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no existe.\n";
                        }
                    }
                    //Codigo Evento
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoEvento = dataFormatter.formatCellValue(celda);
                    if (codigoEvento.isEmpty()) {
                        mensaje += "El codigo de evento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        try {
                            Integer codEvento = Integer.parseInt(codigoEvento);
                            if (codEvento == 0) {
                                mensaje += "El codigo de evento en la fila #" + (inicioFila + 1) + " no cumple con la estructura valida.\n";
                            }
                        } catch (NumberFormatException e) {
                            mensaje += "El codigo de evento en la fila #" + (inicioFila + 1) + " no cumple con la estructura valida.\n";
                        }
                    }
                    //Ubicacion.
                    columna++;
                    celda = fila.getCell(columna);
                    String ubicacion = dataFormatter.formatCellValue(celda);
                    if (ubicacion.isEmpty()) {
                        mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        if (ubicacion.length() > 1) {
                            mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                        } else {
                            try {
                                Integer ubi = Integer.parseInt(ubicacion);
                                if (ubi > 4) {
                                    mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " no tiene un valor valido.\n";
                                }
                            } catch (NumberFormatException e) {
                                mensaje += "La ubicación en la fila #" + (inicioFila + 1) + " no cumple con un valor valido.\n";
                            }
                        }

                    }
                    //tipoIngreso
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoIngreso = dataFormatter.formatCellValue(celda);
                    if (tipoIngreso.isEmpty()) {
                        mensaje += "El tipo de ingreso en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (tipoIngreso.length() > 1) {
                        mensaje += "El tipo de ingreso en la fila #" + (inicioFila + 1) + " no cumple con el tamaño valido.\n";
                    } else {
                        try {
                            Integer tipIngreso = Integer.parseInt(tipoIngreso);
                        } catch (NumberFormatException e) {
                            mensaje += "El tipo de ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Modalidad
                    columna++;
                    celda = fila.getCell(columna);
                    String modalidad = dataFormatter.formatCellValue(celda);
                    if (modalidad.isEmpty()) {
                        mensaje += "La modalidad en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (modalidad.length() > 2) {
                        mensaje += "La modalidad en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    }
                    //Codigo diagnostico
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoDiagnostico = dataFormatter.formatCellValue(celda);
                    if (codigoDiagnostico.isEmpty()) {
                        mensaje += "El codigo de diagnostico en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //tipo diagnostico
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoDiagnostico = dataFormatter.formatCellValue(celda);
                    if (tipoDiagnostico.isEmpty()) {
                        mensaje += "El tipo de diagnostico en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //pricipal
                    columna++;
                    celda = fila.getCell(columna);
                    String principal = dataFormatter.formatCellValue(celda);
                    if (principal.isEmpty()) {
                        mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (principal.length() > 1) {
                        mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    } else {
                        try {
                            Integer bo = Integer.parseInt(principal);
                            if (bo < 0 && bo > 1) {
                                mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                            }
                        } catch (NumberFormatException e) {
                            mensaje += "Diagnostico principal en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Auditor
                    columna++;
                    celda = fila.getCell(columna);
                    String auditor = dataFormatter.formatCellValue(celda);
                    if (auditor.isEmpty()) {
                        mensaje += "El  en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }

                    if (!mensaje.isEmpty()) {
                        finalizo = false;
                    } else {
                        codigo = codigoHabilitacion;
                        inicioFila++;
                    }
                }
            }
            CntPrestadorSede sede = getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(codigo);
            if (sede == null || sede.getId() == null) {
                mensaje = "El codigo de habilitación no existe.\n";
            } else {
                objeto.setCntPrestadorSedeId(sede);
                objeto.setCntPrestadorId(sede.getCntPrestador());
                objeto.setRegistrosTotal(inicioFila - 2);
                //Guardar archivo
                try {
                    //Se genera el documento
                    FileOutputStream out = new FileOutputStream(new File(objeto.getRuta() + objeto.getArchivo()));
                    libro.write(out);
                    out.close();
                } catch (IOException e) {
                    mensaje = "El archivo no se pudo almacenar.\n";
                }
            }
        } catch (Exception e) {
            mensaje = "Hubo un fallo al validar favor contactar al adminitrador.\n";
        }
        return mensaje;
    }
    
    private String validarArchivoSeguimiento(AucCarga objeto) {
        String mensaje = "";
        String codigo = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStream());
            Sheet hoja = libro.getSheetAt(0);
            int inicioFila = 2;
            int columna;
            boolean finalizo = true;
            Date fechaActual = new Date();
            String primerCodigoHabilitacion = null;
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
                    } else {
                        mensaje += "El consecutivo en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //codigo_habilitacion
                    columna++;
                    celda = fila.getCell(columna);
                    String codigoHabilitacion = dataFormatter.formatCellValue(celda);
                    if (codigoHabilitacion.isEmpty()) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else if (codigoHabilitacion.length() < 2) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                    } else if (primerCodigoHabilitacion == null) {
                        primerCodigoHabilitacion = codigoHabilitacion;
                    } else if (!codigoHabilitacion.equals(primerCodigoHabilitacion)) {
                        mensaje += "El codigo habilitación en la fila #" + (inicioFila + 1) + " no es igual a: " + primerCodigoHabilitacion + "\n";
                    }
                    //Tipo Documento
                    columna++;
                    celda = fila.getCell(columna);
                    String tipoDocumento = dataFormatter.formatCellValue(celda);
                    if (tipoDocumento.isEmpty()) {
                        mensaje += "El tipo documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Número Documento
                    columna++;
                    celda = fila.getCell(columna);
                    String documento = dataFormatter.formatCellValue(celda);
                    if (documento.isEmpty()) {
                        mensaje += "El número documento en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Fecha Ingreso
                    columna++;
                    celda = fila.getCell(columna);
                    String fechaIngreso = dataFormatter.formatCellValue(celda);
                    if (fechaIngreso.isEmpty()) {
                        mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        try {
                            Date fechaIng = formato.parse(fechaIngreso);
                            if (fechaIng.after(fechaActual)) {
                                    mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " debe ser anterior a la fecha actual.\n";
                            }
                        } catch (Exception e) {
                            mensaje += "La fecha ingreso en la fila #" + (inicioFila + 1) + " no cumple con el formato valido.\n";
                        }
                    }
                    //Auditor
                    columna++;
                    celda = fila.getCell(columna);
                    String auditor = dataFormatter.formatCellValue(celda);
                    if (auditor.isEmpty()) {
                        mensaje += "El  en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    }
                    //Descripcion
                    columna++;
                    celda = fila.getCell(columna);
                    String decripcion = dataFormatter.formatCellValue(celda);
                    if (decripcion.isEmpty()) {
                        mensaje += "La descripción en la fila #" + (inicioFila + 1) + " esta vacia.\n";
                    }

                    if (!mensaje.isEmpty()) {
                        finalizo = false;
                    } else {
                        codigo = codigoHabilitacion;
                        inicioFila++;
                    }
                }
            }
            // sólo se almacenará el archivo en caso de que sea válido para continuar.
            if (mensaje.isEmpty()) {
                //PENDIENTE esto no debe usarse pero los campos deben cambiarse a permitir nulos
                CntPrestadorSede sede = getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(codigo);
                if (sede == null || sede.getId() == null) {
                    mensaje = "El codigo de habilitación no existe.\n";
                } else {
                    objeto.setCntPrestadorSedeId(sede);
                    objeto.setCntPrestadorId(sede.getCntPrestador());
                    objeto.setRegistrosTotal(inicioFila - 2);
                    //Guardar archivo
                    try {
                        //Se genera el documento
                        FileOutputStream out = new FileOutputStream(new File(objeto.getRuta() + objeto.getArchivo()));
                        libro.write(out);
                        out.close();
                    } catch (IOException e) {
                        mensaje = "El archivo no se pudo almacenar.\n";
                    }
                }
            }
        } catch (Exception e) {
            mensaje = "Hubo un fallo al validar favor contactar al adminitrador.\n";
        }
        return mensaje;
    }

}
