/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.servicio;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierre;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierreSuceso;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaCierreHiloRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaCierreRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucCargaCierreSucesoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.auditoria.concurrente.bean.AucCargaMasivaValorBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author idbohorquez
 */
public class AucCargaMasivaValorServicioImpl extends AccionesBO implements AucCargaMasivaValorServicioIface{

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }
    
    private AucCargaCierreRemoto getAucCargaCierreRemoto() throws Exception {
        return (AucCargaCierreRemoto) RemotoEJB.getEJBRemoto("AucCargaCierreServicio", AucCargaCierreRemoto.class.getName());
    }
    
    private AucCargaCierreSucesoRemoto getAucCargaCierreSucesoRemoto() throws Exception {
        return (AucCargaCierreSucesoRemoto) RemotoEJB.getEJBRemoto("AucCargaCierreSucesoServicio", AucCargaCierreSucesoRemoto.class.getName());
    }
    
    private AucCargaCierreHiloRemoto getAucCargaCierreHiloRemoto() throws Exception {
        return (AucCargaCierreHiloRemoto) RemotoEJB.getEJBRemotoGenerico("AucCargaMasivaCierreServicio", AucCargaCierreHiloRemoto.class.getName());
    }
    
    
    
    @Override
    public void Accion(AucCargaMasivaValorBean bean) {
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

            }
        }
    }
    
    public void cargaInicial(AucCargaMasivaValorBean bean){
        bean.getObjeto().setAucCargaFalloList(new ArrayList<>());
    }

    private void listar(AucCargaMasivaValorBean bean) {
        try {
            if(!bean.getConexion().getEmpresa().isAdministradora()){
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(0).setCantidadRegistros(getAucCargaCierreRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAucCargaCierreRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar al adminitrador");
        }
    }

    private void ver(AucCargaMasivaValorBean bean) {
        try {
            bean.setObjeto(getAucCargaCierreRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAucCargaFalloList(getAucCargaCierreSucesoRemoto().consultarListaPorIdCarga(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al generar el reporte de fallo, favor contactar al adminitrador");
        }
    }
    
    private void listarFallos(AucCargaMasivaValorBean bean) {
        try {
            bean.getParamConsulta(1).setParametroConsulta3(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getAucCargaCierreSucesoRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaFallos(getAucCargaCierreSucesoRemoto().consultarLista(bean.getParamConsulta(1)));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al generar el reporte de fallo, favor contactar al adminitrador");
        }
    }

    private void crear(AucCargaMasivaValorBean bean) {
        try {
            bean.setObjeto(new AucCargaCierre());
            bean.getObjeto().setGnEmpresaId(bean.getConexion().getEmpresa());
            bean.getObjeto().setRegistrosExitosos(0);
            bean.getObjeto().setRegistrosRechazados(0);
            bean.getObjeto().setRegistrosTotal(0);
            bean.getObjeto().setEstado(AucCargaCierre.ESTADO_EN_COLA);     
            bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.AUC_RUTA_CARGA_MASIVA_CIERRES));            
            bean.getObjeto().setExiste(true);
        } catch (Exception e) {
            bean.addError("Hubo un error al crear, favor contactar al adminitrador");
        }
    }

    private void guardar(AucCargaMasivaValorBean bean) {
        try {
            String mensajeValidacion = validarArchivo(bean);
            if (mensajeValidacion.isEmpty()) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setFechaHoraInicio(new Date());
                bean.getObjeto().setId(getAucCargaCierreRemoto().insertar(bean.getObjeto()));
                //getAucCargaCierreHiloRemoto().iniciarCarga(bean.getObjeto());
                if(bean.getObjeto().getId() != null){
                    bean.addMensaje("El archivo " + bean.getObjeto().getNombreArchivo()+ " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
                }
                 
            } else {
                bean.addError(mensajeValidacion);
            }
        } catch (IOException e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al adminitrador");
        } catch (Exception ex) {
            Logger.getLogger(AucCargaMasivaServicioImpl.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
    }
    
    private String validarArchivo(AucCargaMasivaValorBean bean) {
        String mensaje = "";
        String codigo = "";
        AucCargaCierre objeto = bean.getObjeto();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DataFormatter dataFormatter = new DataFormatter();
            Workbook libro = WorkbookFactory.create(objeto.getAdjuntoStream());
            Sheet hoja = libro.getSheetAt(0);
            int inicioFila = 1;
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
                    //valor.
                    columna++;
                    celda = fila.getCell(columna);
                    String valor = dataFormatter.formatCellValue(celda);
                    if (valor.isEmpty()) {
                        mensaje += "El Valor en la fila #" + (inicioFila + 1) + " esta vacio.\n";
                    } else {
                        if (valor.length() < 4) {
                            mensaje += "El valor en la fila #" + (inicioFila + 1) + " no cumple con el tamaño de texto valido.\n";
                        } else {
                            try {
                                Integer ubi = Integer.parseInt(valor);                                
                            } catch (NumberFormatException e) {
                                mensaje += "El valor en la fila #" + (inicioFila + 1) + " no cumple con un valor valido.\n";
                            }
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
                objeto.setRegistrosTotal(inicioFila - 1);
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
    
    
}
