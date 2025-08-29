/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegInforme;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.aseguramiento.bean.ReportesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import com.saviasaludeps.savia.negocio.aseguramiento.ReporteRemoto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.ReporteHilo;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;

/**
 *
 * @author jyperez
 */
public class ReportesServicioImpl extends AccionesBO implements ReportesServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private ReporteRemoto getReporteRemoto() throws Exception {
        return (ReporteRemoto) RemotoEJB.getEJBRemoto(("ReporteServicio"), ReporteRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    @Override
    public void Accion(ReportesBean bean) {
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
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    cargarListaDetalleCarga(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(ReportesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getReporteRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getReporteRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<CntPrestadorSede> listarSedesPorMunicipio(String divipoliMunicipio) throws Exception{
        return getPrestadoresRemoto().consultarListaSedes(divipoliMunicipio);
    }


    private void ver(ReportesBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ReportesBean bean) {
        try {
            bean.setObjeto(new AsegInforme());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ReportesBean bean) {
        try {
            int id = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            //PropApl propiedades = new PropApl();
            ReporteHilo reporteHilo;
            String ruta;
            ruta = PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_REPORTES);
            //validamos que no exista un reporte del mismo tipo en Proceso
            mostrarMensajeErrorPantalla(bean,validarReporteProceso(bean));
            if (bean.getObjeto().getTipo() != AfiliadoParametro.TIPO_ARCHIVO_REPORTE_NOVEDADES_ASEGURAMIENTO) {
                mostrarMensajeErrorPantalla(bean, validarReporteExistente(bean));
            } else {
                // ponemos la fecha del sistema, debido a que en BD es requerida y por pantalla no permite su ingreso.
                bean.getObjeto().setFechaDesde(new Date());
                bean.getObjeto().setFechaHasta(bean.getObjeto().getFechaDesde());
            }
            //validarFechasValidas
            mostrarMensajeErrorPantalla(bean, validarFechasValidas(bean));
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                // el id de radicado es autoincremental, por eso no se asigna valor. El tipo se seleccionó en la lista de la pantalla
                bean.getObjeto().setRuta(ruta);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //adicionamos la fecha hora crea, similar al a fecha hora inicio
                bean.getObjeto().setFechaHoraInicio(bean.getObjeto().getFechaHoraCrea());
                // generamos el nombre de archivo
                bean.getObjeto().setArchivo(obtenerNombreArchivo(bean));
                //2020-07-17 jyperez los registros, iniciaran en el estado Procesando, debido a que automáticamente quedan
                // trabajando sobre un hilo
                bean.getObjeto().setEstado(AfiliadoParametro.ESTADO_REPORTE_EN_PROCESO);
                //guardamos el registro en asegInforme
                id = getReporteRemoto().insertar(bean.getObjeto());
                bean.getObjeto().setId(id);
                // Actualizamos el nombre del archivo con el correspondiente Id
                bean.getObjeto().setArchivo(obtenerNombreArchivo(bean));
                getReporteRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("El reporte " + bean.getObjeto().getArchivo() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
                // aca se llama al proceso que ejecutará el hilo
                if (id != 0) {
                    reporteHilo = new ReporteHilo(bean.getObjeto().getTipo(), bean.getObjeto());
                    reporteHilo.start();
                    //el procesamiento del archivo se realiza en el hilo, y se actualiza solo.
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ReportesBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ReportesBean bean) {
        try {
            
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(ReportesBean bean) {
        try {
            
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(ReportesBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(ReportesBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(ReportesBean bean) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getObjeto().getRuta(), bean.getObjeto().getArchivo());
            destino = new FileOutputStream(archivo);
            //IOUtils.copy(bean.getObjeto().getAdjuntoStream(), destino);
            //IOUtils.closeQuietly(bean.getObjeto().getAdjuntoStream());
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
                Logger.getLogger(ReportesServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return esArchivoGuardado;
    }

    private void cargarListaDetalleCarga(ReportesBean bean) {
        try {
            bean.setObjeto(getReporteRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            Logger.getLogger(ReportesServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int contarCaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada                                 
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
    }

    /**
     * Función para validar que la fecha Inicial y la Fecha final cumplan con 30 días calendario de diferencia y que,
     * la fecha inicial no sea mayor que la fecha final
     * @param bean
     * @return 
     */
    private String validarFechasValidas(ReportesBean bean) {
        String mensaje = "";
        int dias = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if( bean.getObjeto().getFechaDesde().compareTo(bean.getObjeto().getFechaHasta()) > 0) {
            mensaje = "La Fecha Inicial no debe ser mayor que la Fecha Final.";
        } else  {
            dias =(int) ((bean.getObjeto().getFechaHasta().getTime()- bean.getObjeto().getFechaDesde().getTime())/86400000);
            //2020-10-16 jyperez INC 316 para el reporte de portabilidad, se permitirá 3 años (1095 dias)
            if (bean.getObjeto().getTipo()== AfiliadoParametro.TIPO_ARCHIVO_REPORTE_PORTABILIDAD) {
                if ( dias > 1095) {
                    mensaje = "El rango entre la Fecha Inicial y la Fecha Final no debe ser mayor a 3 años.";
                }
            } else {
                if ( dias > 30) {
                    mensaje = "El rango entre la Fecha Inicial y la Fecha Final no debe ser mayor a 30 días.";
                }
            }
        }
        return mensaje;
    }

    private String obtenerNombreArchivo(ReportesBean bean) {
        String archivo = "archivoPrueba";
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        switch(bean.getObjeto().getTipo() ) {
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_AFILIADOS_NUEVOS:
                archivo = "MSEPSS40"+ sdf.format(bean.getObjeto().getFechaHoraCrea()) + "_" + bean.getObjeto().getId() + ".txt";// pendiente agregarle algo mas al reporte porque se puede sobreescribir si generan dos el mismo dia
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_NOVEDADES:
                archivo = "NSEPSS40"+ sdf.format(bean.getObjeto().getFechaHoraCrea()) + "_" + bean.getObjeto().getId() + ".txt";// pendiente agregarle algo mas al reporte porque se puede sobreescribir si generan dos el mismo dia
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_TRASLADOS:
                archivo = "S1EPSS40"+ sdf.format(bean.getObjeto().getFechaHoraCrea()) + "_" + bean.getObjeto().getId() + ".txt";// pendiente agregarle algo mas al reporte porque se puede sobreescribir si generan dos el mismo dia
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_PORTABILIDAD:
                archivo = "Portabilidad_"+ sdf1.format(bean.getObjeto().getFechaHoraCrea()) + "_" + bean.getObjeto().getId() + ".txt";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_DIGITACION_USUARIOS:
                archivo = "Digitacion_Novedades_"+ sdf1.format(bean.getObjeto().getFechaHoraCrea()) + "_" + bean.getObjeto().getId() + ".txt";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_ENCUESTAS_AFILIADOS:
                archivo = "Encuestas_"+ sdf1.format(bean.getObjeto().getFechaHoraCrea()) +"_" + bean.getObjeto().getId() + ".txt";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_NOVEDADES_ASEGURAMIENTO:
                archivo = "Actualizacion_Datos_"+ sdf1.format(bean.getObjeto().getFechaHoraCrea()) +"_" + bean.getObjeto().getId() + ".txt";
            break;
        }
        return archivo;
    }
    
    private void mostrarMensajeErrorPantalla(ReportesBean bean, String msj) {
        if (msj != null && !msj.trim().equals("")) {
            bean.addError(msj);
        }
    }

    /**
     * Función para validar que no se encuentra en estado "En Proceso" un Reporte del mismo tipo que el que se está enviando.
     * 
     * @param bean
     * @return 
     */
    private String validarReporteProceso(ReportesBean bean) {
        String mensaje = "";
        int cantidad;
        try {
            cantidad = getReporteRemoto().consultarCantidadPorTipoYEstado(bean.getObjeto().getTipo(), AfiliadoParametro.ESTADO_REPORTE_EN_PROCESO);
            if (cantidad > 0) {
                mensaje = "No se puede crear un reporte de tipo " + bean.getTipoArchivo(bean.getObjeto().getTipo()) + " si existe uno en proceso.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportesServicioImpl.class.getName()).log(Level.SEVERE, "[validarReporteProceso] Error en ejecución de consulta.", ex);
        }
        
        return mensaje;
    }

    /**
     * Función que valida que no exista un Reporte del mismo tipo, con el mismo Rango de Fechas que el solicitado.
     * @param bean
     * @return 
     */
    private String validarReporteExistente(ReportesBean bean) {
        String mensaje = "";
        int cantidad;
        try {
            cantidad = getReporteRemoto().consultarCantidadPorTipoYRangoFechas(bean.getObjeto().getTipo(), bean.getObjeto().getFechaDesde(), bean.getObjeto().getFechaHasta());
            if (cantidad > 0) {
                mensaje = "No se puede crear el reporte de tipo " + bean.getTipoArchivo(bean.getObjeto().getTipo()) + ". Ya existe uno en el sistema con los mismos rangos de Fecha.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportesServicioImpl.class.getName()).log(Level.SEVERE, "[validarReporteExistente] Error en ejecución de consulta.", ex);
        }
        
        return mensaje;
    }
}
