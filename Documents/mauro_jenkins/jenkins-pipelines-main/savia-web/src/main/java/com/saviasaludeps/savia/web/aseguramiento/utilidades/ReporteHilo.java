/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegInforme;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.NovedadAseguramiento;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegCartillaDerechoDeber;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegDigitacionesNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegMaestrosSubsidiado;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegNovedadSubsidiado;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegTraslado;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
//import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.NovedadAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.NovedadAseguramientoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.ReporteRemoto;
//import com.saviasaludeps.savia.negocio.aseguramiento.TrasladoRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jyperez
 */
public class ReporteHilo extends Thread {
    
    public static final int REPORTE_AFILIADOS_NUEVOS_MS = 1;
    public static final int REPORTE_NOVEDADES_AFILIADO_NS = 2;
    public static final int REPORTE_SOLICITUD_TRASLADO_AFILIADOS_S1 = 3;
    public static final int REPORTE_PORTABILIDAD = 4;
    public static final int REPORTE_DIGITACION_USUARIOS = 5;
    public static final int REPORTE_ENCUESTAS_AFILIADOS = 6;
    public static final int REPORTE_NOVEDADES_ASEGURAMIENTO = 7;
    
    public final static int ESTADO_EN_PROCESO = 1;
    public final static int ESTADO_PROCESADO = 2;
    public final static int ESTADO_RECHAZADO = 3;
    
    public final static String ESTADO_NOVEDAD_PENDIENTE = "PENDIENT";// estos datos no son válidos. No se ha entregado y no se configuró maestro
    public final static String ESTADO_NOVEDAD_REPORTADO = "GENERADO"; // estos datos no son válidos. No se ha entregado y no se configuró maestro . DATO propuesto por nosotros
    public final static String CODIGO_NOVEDAD_NUEVO_AFILIADO = "IS-01";
    
    private int tipoReporte;
    private AsegInforme reporte;
    // normativos
    List<VAsegMaestrosSubsidiado> listaAfiliadosNuevosMS;
    List<VAsegNovedadSubsidiado> listaNovedadesAfiliadoNS;
    List<VAsegTraslado> listaTrasladosAfiliadosS1;
    //no normativos
    List<VAsegPortabilidad> listaPortabilidad;
    List<VAsegDigitacionesNovedad> listaDigitacionUsuarios;
    List<VAsegCartillaDerechoDeber> listaEncuestaAfiliados;
    List<VAsegNovedad> listaNovedadesAseguramiento;
    public ReporteHilo(int tipoReporte, AsegInforme reporte) {
        this.tipoReporte = tipoReporte;
        this.reporte = reporte;
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }
    
    private ReporteRemoto getReporteRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("ReporteServicio", ReporteRemoto.class.getName());
        return (ReporteRemoto) object;
    }
    
    private NovedadAfiliadoRemoto getNovedadAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("NovedadAfiliadoServicio", NovedadAfiliadoRemoto.class.getName());
        return (NovedadAfiliadoRemoto) object;
    }
    
    private NovedadAseguramientoRemoto getNovedadAseguramientoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("NovedadAseguramientoServicio", NovedadAseguramientoRemoto.class.getName());
        return (NovedadAseguramientoRemoto) object;
    }
    
    @Override
    public void run() {
        
        switch(tipoReporte) {
            
            case REPORTE_AFILIADOS_NUEVOS_MS:
                generarReporteAfiliadosNuevosMS();
                break;
            case REPORTE_NOVEDADES_AFILIADO_NS:
                generarReporteNovedadesAfiliadoNS();
                break;
            case REPORTE_SOLICITUD_TRASLADO_AFILIADOS_S1:
                generarReporteTrasladoAfiliadosS1();
                break;
            case REPORTE_PORTABILIDAD:
                generarReportePortabilidad();
                break;
            case REPORTE_DIGITACION_USUARIOS:
                generarReporteDigitacionUsuarios();
                break;
            case REPORTE_ENCUESTAS_AFILIADOS:
                generarReporteEncuestasAfiliados();
                break;
//            case REPORTE_NOVEDADES_ASEGURAMIENTO:
//                generarReporteNovedadesAseguramiento();
//            break;

        }
    }

    private void generarReporteAfiliadosNuevosMS() {
        int registros;
        AsegRegistroNovedad novedad = new AsegRegistroNovedad();
        try {
            novedad.setAsegInformesIdMarcacion(reporte.getId());
            novedad.setFechaMarcacion(reporte.getFechaHoraCrea());
            //novedad.setMaeEstadoNovedad(ESTADO_NOVEDAD_REPORTADO);
            // ponemos los campos de auditoria, tomados del objeto de Reporte
            novedad.setUsuarioModifica(reporte.getUsuarioCrea());
            novedad.setTerminalModifica(reporte.getTerminalCrea());
            novedad.setFechaHoraModifica(reporte.getFechaHoraCrea());
            //1. actualizamos todos los registros y los marcamos con el identificador del Reporte
            getNovedadAfiliadoRemoto().actualizarNovedadesReporteAfiliadosNuevosMS(novedad, reporte.getFechaDesde(), reporte.getFechaHasta());
            reporte.setEstado(ESTADO_PROCESADO);
            reporte.setObservacion("");
        } catch (Exception ex) {
            reporte.setObservacion("Ocurrió un error actualizando las novedades del Reporte. Mensaje : " + ex.getMessage());
            reporte.setEstado(ESTADO_RECHAZADO);
        }
        //2. Realizamos la consulta de la vista del reporte, siempre y cuando no haya fallado la actualización
        if (reporte.getObservacion().trim().equals("")) {
            try {
                listaAfiliadosNuevosMS = getReporteRemoto().consultarReporteAfiliadosNuevosMS(reporte.getId());
            } catch (Exception ex) {
                reporte.setObservacion("Ocurrió un error consultando la vista del Reporte. Mensaje : " + ex.getMessage());
                reporte.setEstado(ESTADO_RECHAZADO);
            }
        }
        //3. validamos que el reporte haya obtenido registros
        if (listaAfiliadosNuevosMS != null && listaAfiliadosNuevosMS.size() > 0) {
            //3.1 procedemos a realizar el proceso de generación del reporte
                registros = generarArchivoReporte(REPORTE_AFILIADOS_NUEVOS_MS);
            try {
                //2.3 procedemos a la acualización del registro del Reporte (AsegInforme)
                reporte.setRegistros(registros);
                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
                if (registros == 0) {
                    reporte.setEstado(ESTADO_RECHAZADO);
                    reporte.setObservacion("No existen registros en el rango de fechas del reporte.");
                    //procedemos a desmarcar las novedades que fueron marcadas por el reporte
                    getNovedadAfiliadoRemoto().actualizarNovedadesPorIdReporte(reporte.getId());
                } else {
                    reporte.setEstado(ESTADO_PROCESADO);
                }
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteAfiliadosNuevosMS] Error acualizando AsegInforme con Id " + reporte.getId(), ex);
            }
        }else {
            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
            // creamos un archivo vacio, por el momento
            try {
                //3.1 ERR procedemos a desmarcar las novedades que fueron marcadas por el reporte
                getNovedadAfiliadoRemoto().actualizarNovedadesPorIdReporte(reporte.getId());
                //3.2 ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
                reporte.setRegistros(0);
                if (listaAfiliadosNuevosMS != null) {
                    reporte.setObservacion("No existen registros en el rango de fechas del reporte.");
                }
                reporte.setEstado(ESTADO_RECHAZADO);
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteAfiliadosNuevosMS] Error acualizando AsegInforme con Id " + reporte.getId(), ex);
            }
        }
        
    }

    private void generarReporteNovedadesAfiliadoNS() {
        int registros;
        AsegRegistroNovedad novedad = new AsegRegistroNovedad();
        try {
            novedad.setAsegInformesIdMarcacion(reporte.getId());
            novedad.setFechaMarcacion(reporte.getFechaHoraCrea());
            //novedad.setMaeEstadoNovedad(ESTADO_NOVEDAD_REPORTADO);
            // ponemos los campos de auditoria, tomados del objeto de Reporte
            novedad.setUsuarioModifica(reporte.getUsuarioCrea());
            novedad.setTerminalModifica(reporte.getTerminalCrea());
            novedad.setFechaHoraModifica(reporte.getFechaHoraCrea());
            //1. actualizamos todos los registros y los marcamos con el identificador del Reporte
            getNovedadAfiliadoRemoto().actualizarNovedadesReporteNovedadesNS(novedad, reporte.getFechaDesde(), reporte.getFechaHasta());
            reporte.setEstado(ESTADO_PROCESADO);
            reporte.setObservacion("");
        } catch (Exception ex) {
            reporte.setObservacion("Ocurrió un error actualizando las novedades del Reporte. Mensaje : " + ex.getMessage());
            reporte.setEstado(ESTADO_RECHAZADO);
        }
        //2. Realizamos la consulta de la vista del reporte, siempre y cuando no haya fallado la actualización
        if (reporte.getObservacion().trim().equals("")) {
            try {
                //1. tomamos los datos del reporte, para realizar la consulta de la vista
                listaNovedadesAfiliadoNS = getReporteRemoto().consultarReporteNovedadesAfiliadoNS(this.reporte.getId());
            } catch (Exception ex) {
                reporte.setObservacion("Ocurrió un error consultando la vista del Reporte. Mensaje : " + ex.getMessage());
                reporte.setEstado(ESTADO_RECHAZADO);
            }
        }
        //3. validamos la cantidad de registros, que no sea nulo y no sean 0
        if (listaNovedadesAfiliadoNS != null && listaNovedadesAfiliadoNS.size() > 0) {
            //3.1 procedemos a realizar el proceso de generación del reporte
            registros = generarArchivoReporte(REPORTE_NOVEDADES_AFILIADO_NS);
            try {
                //3.2 procedemos a la acualización del registro del Reporte (AsegInforme)
                reporte.setRegistros(registros);
                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
                if (registros == 0) {
                    reporte.setEstado(ESTADO_RECHAZADO);
                    reporte.setObservacion("No existen registros en el rango de fechas del reporte.");
                    //procedemos a desmarcar las novedades que fueron marcadas por el reporte
                    getNovedadAfiliadoRemoto().actualizarNovedadesPorIdReporte(reporte.getId());
                } else {
                    reporte.setEstado(ESTADO_PROCESADO);
                }
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteNovedadesAfiliadoNS] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }else {
            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
            // creamos un archivo vacio, por el momento
            try {
                //3.1 ERR procedemos a desmarcar las novedades que fueron marcadas por el reporte
                getNovedadAfiliadoRemoto().actualizarNovedadesPorIdReporte(reporte.getId());
                //3.2 ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
                reporte.setRegistros(0);
                if (listaNovedadesAfiliadoNS != null) {
                    reporte.setObservacion("No existen registros en el rango de fechas del reporte.");
                }
                reporte.setEstado(ESTADO_RECHAZADO);
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteNovedadesAfiliadoNS] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }
        
    }

    private void generarReporteTrasladoAfiliadosS1() {
        int registros;
        AsegRegistroNovedad novedad = new AsegRegistroNovedad();
        try {
            novedad.setAsegInformesIdMarcacion(reporte.getId());
            novedad.setFechaMarcacion(reporte.getFechaHoraCrea());
            //novedad.setMaeEstadoNovedad(ESTADO_NOVEDAD_REPORTADO);
            // ponemos los campos de auditoria, tomados del objeto de Reporte
            novedad.setUsuarioModifica(reporte.getUsuarioCrea());
            novedad.setTerminalModifica(reporte.getTerminalCrea());
            novedad.setFechaHoraModifica(reporte.getFechaHoraCrea());
            //1. actualizamos todos los registros y los marcamos con el identificador del Reporte
            getNovedadAfiliadoRemoto().actualizarNovedadesReporteTrasladosS1(novedad, reporte.getFechaDesde(), reporte.getFechaHasta());
            reporte.setEstado(ESTADO_PROCESADO);
            reporte.setObservacion("");
        } catch (Exception ex) {
            reporte.setObservacion("Ocurrió un error actualizando las novedades del Reporte. Mensaje : " + ex.getMessage());
            reporte.setEstado(ESTADO_RECHAZADO);
        }
        //2. Realizamos la consulta de la vista del reporte, siempre y cuando no haya fallado la actualización
        if (reporte.getObservacion().trim().equals("")) {
            try {
                //1. tomamos los datos del reporte, para realizar la consulta de la vista
                listaTrasladosAfiliadosS1 = getReporteRemoto().consultarReporteTrasladoS1(this.reporte.getId());
            } catch (Exception ex) {
                reporte.setObservacion("Ocurrió un error consultando la vista del Reporte. Mensaje : " + ex.getMessage());
                reporte.setEstado(ESTADO_RECHAZADO);
            }
        }
        //3. validamos la cantidad de registros, que no sea nulo y no sean 0
        if (listaTrasladosAfiliadosS1 != null && listaTrasladosAfiliadosS1.size() > 0) {
            //3.1 procedemos a realizar el proceso de generación del reporte
            registros = generarArchivoReporte(REPORTE_SOLICITUD_TRASLADO_AFILIADOS_S1);
            try {
                //3.2 procedemos a la acualización del registro del Reporte (AsegInforme)
                reporte.setRegistros(registros);
                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
                if (registros == 0) {
                    reporte.setEstado(ESTADO_RECHAZADO);
                    reporte.setObservacion("No existen registros en el rango de fechas del reporte.");
                    //procedemos a desmarcar las novedades que fueron marcadas por el reporte
                    getNovedadAfiliadoRemoto().actualizarNovedadesPorIdReporte(reporte.getId());
                } else {
                    reporte.setEstado(ESTADO_PROCESADO);
                }
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteTrasladoAfiliadosS1] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }else {
            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
            // creamos un archivo vacio, por el momento
            try {
                //3.1 ERR procedemos a desmarcar las novedades que fueron marcadas por el reporte
                getNovedadAfiliadoRemoto().actualizarNovedadesPorIdReporte(reporte.getId());
                //3.2 ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
                reporte.setRegistros(0);
                if (listaTrasladosAfiliadosS1 != null) {
                    reporte.setObservacion("No existen registros en el rango de fechas del reporte.");
                }
                reporte.setEstado(ESTADO_RECHAZADO);
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteTrasladoAfiliadosS1] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }
        
    }

    private void generarReportePortabilidad() {
        int registros;
        try {
            //1. tomamos los datos del reporte, para realizar la consulta de la vista
            listaPortabilidad = getReporteRemoto().consultarReportePortabilidad(this.reporte.getFechaDesde(),this.reporte.getFechaHasta());
        } catch (Exception ex) {
            Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //2. validamos la cantidad de registros, que no sea nulo y no sean 0
        if (listaPortabilidad != null && listaPortabilidad.size() > 0) {
                //2.1 procedemos a realizar el proceso de generación del reporte
                registros = generarArchivoReporte(REPORTE_PORTABILIDAD);
            try {
                //2.2 procedemos a la acualización del registro del Reporte (AsegInforme)
                reporte.setRegistros(registros);
                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
                if (registros == 0) {
                    reporte.setEstado(ESTADO_RECHAZADO);
                } else {
                    reporte.setEstado(ESTADO_PROCESADO);
                }
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReportePortabilidad] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }else {
            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
            // creamos un archivo vacio, por el momento
            try {
                //2.1ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
                reporte.setRegistros(0);
                reporte.setEstado(ESTADO_RECHAZADO);
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReportePortabilidad] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }
    }

    private void generarReporteDigitacionUsuarios() {
        int registros;
        try {
            //1. tomamos los datos del reporte, para realizar la consulta de la vista
            listaDigitacionUsuarios = getReporteRemoto().consultarReporteDigitacionUsuarios(this.reporte.getFechaDesde(),this.reporte.getFechaHasta());
        } catch (Exception ex) {
            Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //2. validamos la cantidad de registros, que no sea nulo y no sean 0
        if (listaDigitacionUsuarios != null && listaDigitacionUsuarios.size() > 0) {
                //2.1 procedemos a realizar el proceso de generación del reporte
                registros = generarArchivoReporte(REPORTE_DIGITACION_USUARIOS);
            try {
                //2.2 procedemos a la acualización del registro del Reporte (AsegInforme)
                reporte.setRegistros(registros);
                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
                if (registros == 0) {
                    reporte.setEstado(ESTADO_RECHAZADO);
                } else {
                    reporte.setEstado(ESTADO_PROCESADO);
                }
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteDigitacionUsuarios] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }else {
            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
            // creamos un archivo vacio, por el momento
            try {
                //2.1ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
                reporte.setRegistros(0);
                reporte.setEstado(ESTADO_RECHAZADO);
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteDigitacionUsuarios] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }
    }

    private void generarReporteEncuestasAfiliados() {
        int registros;
        try {
            //1. tomamos los datos del reporte, para realizar la consulta de la vista
            listaEncuestaAfiliados = getReporteRemoto().consultarReporteEncuestasAfiliados(this.reporte.getFechaDesde(),this.reporte.getFechaHasta());
        } catch (Exception ex) {
            Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //2. validamos la cantidad de registros, que no sea nulo y no sean 0
        if (listaEncuestaAfiliados != null && listaEncuestaAfiliados.size() > 0) {
                //2.1 procedemos a realizar el proceso de generación del reporte
                registros = generarArchivoReporte(REPORTE_ENCUESTAS_AFILIADOS);
            try {
                //2.2 procedemos a la acualización del registro del Reporte (AsegInforme)
                reporte.setRegistros(registros);
                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
                if (registros == 0) {
                    reporte.setEstado(ESTADO_RECHAZADO);
                } else {
                    reporte.setEstado(ESTADO_PROCESADO);
                }
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteEncuestasAfiliados] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }else {
            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
            // creamos un archivo vacio, por el momento
            try {
                //2.1ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
                reporte.setRegistros(0);
                reporte.setEstado(ESTADO_RECHAZADO);
                reporte.setFechaHoraFin(new Date());
                getReporteRemoto().actualizar(reporte);
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteEncuestasAfiliados] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
            }
        }
    }

    private int generarArchivoReporte(int tipoReporte) {
        int cant = 0;
        File archivo;
        BufferedWriter bw= null;
        String encabezado;
        try {
            archivo = new File(reporte.getRuta(), reporte.getArchivo());
            // validamos que el archivo exista
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bw = new BufferedWriter (new FileWriter(archivo));
            //validamos el tipo de reporte, con el objetivo de obtener los registros de la lista
            switch (tipoReporte) {
                case REPORTE_AFILIADOS_NUEVOS_MS:
                    // empezamos a escribir cada linea de la vista del reporte que haya actualizado la novedad
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegMaestrosSubsidiado.getEncabezado());
                    for (VAsegMaestrosSubsidiado reg: listaAfiliadosNuevosMS) {
                            bw.write(reg.toString());
                            cant++;
                    }
                break;
                case REPORTE_NOVEDADES_AFILIADO_NS:
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegNovedadSubsidiado.getEncabezado());
                    // empezamos a escribir cada linea de la vista del reporte que haya actualizado la novedad
                    for (VAsegNovedadSubsidiado reg: listaNovedadesAfiliadoNS) {
                            bw.write(reg.toString());
                            cant++;
                    }
                break;
                case REPORTE_SOLICITUD_TRASLADO_AFILIADOS_S1:
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegTraslado.getEncabezado());
                    // empezamos a escribir cada linea de la vista del reporte que haya actualizado la novedad
                    for (VAsegTraslado reg: listaTrasladosAfiliadosS1) {
                            bw.write(reg.toString());
                            cant++;
                    }
                break;
                case REPORTE_PORTABILIDAD:
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegPortabilidad.getEncabezado());
                    for (VAsegPortabilidad reg: listaPortabilidad) {
                        bw.write(reg.toString());
                        cant++;
                    }
                break;
                case REPORTE_DIGITACION_USUARIOS:
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegDigitacionesNovedad.getEncabezado());
                    for (VAsegDigitacionesNovedad reg: listaDigitacionUsuarios) {
                        bw.write(reg.toString());
                        cant++;
                    }
                break;
                case REPORTE_ENCUESTAS_AFILIADOS:
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegCartillaDerechoDeber.getEncabezado());
                    for (VAsegCartillaDerechoDeber reg: listaEncuestaAfiliados) {
                        bw.write(reg.toString());
                        cant++;
                    }
                break;
                case REPORTE_NOVEDADES_ASEGURAMIENTO:
                    // 2020-08-14 jyperez INC270 adicionarle los encabezados a los informes.
                    // 2020-08-24 jyperez INC 280 cambios Reportes - cambiamos el encabezado a función estatica para mantenimiento desde el objeto de negocio
                    bw.write(VAsegNovedad.getEncabezado());
                    // empezamos a escribir cada linea de la vista del reporte que haya actualizado la novedad
                    for (VAsegNovedad reg: listaNovedadesAseguramiento) {
                        bw.write(reg.toString());
                        cant++;
                    }
                break;
            }
            bw.close();
        } catch (IOException ex) {
           // no se pdo crear el archivo
           reporte.setObservacion("[generarArchivoReporte - " + getTipoReporte(tipoReporte) +"] Error creando el archivo " + reporte.getRuta() + reporte.getArchivo() + ". Mensaje : " + ex.getMessage());
           if ( bw != null ) {
               try {
                   bw.close();
               } catch (IOException ex1) {
                   
               }
           }
        } catch (Exception e) {
            reporte.setObservacion("[generarArchivoReporte - " + getTipoReporte(tipoReporte) +"] Error creando el archivo " + reporte.getRuta() + reporte.getArchivo() + ". Mensaje : " + e.getMessage());
            if ( bw != null ) {
               try {
                   bw.close();
               } catch (IOException ex1) {
                   
               }
           }
        }
        return cant;
    }
    
    public String getTipoReporte(int tipo) {
        String descripcion;
        switch(tipo) {
            case REPORTE_AFILIADOS_NUEVOS_MS:
                descripcion = "Afiliados Nuevos (MS)";
            break;
            case REPORTE_NOVEDADES_AFILIADO_NS:
                descripcion = "Novedades de Afiliado (NS)";
            break;
            case REPORTE_SOLICITUD_TRASLADO_AFILIADOS_S1:
                descripcion = "Solicitud Traslado de Afiliados (S1)";
            break;
            case REPORTE_PORTABILIDAD:
                descripcion = "Portabilidad";
            break;
            case REPORTE_DIGITACION_USUARIOS:
                descripcion = "Digitación de Usuarios";
            break;
            case REPORTE_ENCUESTAS_AFILIADOS:
                descripcion = "Encuestas Afiliados";
            break;
            case REPORTE_NOVEDADES_ASEGURAMIENTO:
                descripcion = "Novedades Aseguramiento";
            break;
            default:
                descripcion = "";
            break;
        }
        
        return descripcion;
    }
    
    
//    private void generarReporteNovedadesAseguramiento() {
//        int registros;
//        try {
//            //1. tomamos los datos del reporte, para realizar la consulta de la vista
//            listaNovedadesAseguramiento = getReporteRemoto().consultarReporteNovedadesAseguramiento();
//        } catch (Exception ex) {
//            Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteNovedadesAseguramiento] Error consultando vista", ex);
//        }
//        //2. validamos la cantidad de registros, que no sea nulo y no sean 0
//        if (listaNovedadesAseguramiento != null && listaNovedadesAseguramiento.size() > 0) {
//            //2.1 procedemos a realizar la marcación de los registros en el reporte
//                actualizarNovedadesReporteNovedadesAseguramiento();
//                //2.2 una vez marcados los registros, procedemos a realizar el proceso de generación del reporte
//                registros = generarArchivoReporte(REPORTE_NOVEDADES_ASEGURAMIENTO);
//            try {
//                //2.3 procedemos a la acualización del registro del Reporte (AsegInforme)
//                reporte.setRegistros(registros);
//                // Controlamos el estado de Registros, si es cero, se rechaza el Reporte
//                if (registros == 0) {
//                    reporte.setEstado(ESTADO_RECHAZADO);
//                } else {
//                    reporte.setEstado(ESTADO_PROCESADO);
//                }
//                reporte.setFechaHoraFin(new Date());
//                getReporteRemoto().actualizar(reporte);
//            } catch (Exception ex) {
//                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteNovedadesAseguramiento] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
//            }
//        }else {
//            // si no hay registros en el reporte, procedemos a hacer la actualización del registro en 0
//            // creamos un archivo vacio, por el momento
//            try {
//                //2.1ERR procedemos a la acualización del registro del Reporte (AsegInforme) como Rechazado con 0 registros
//                reporte.setRegistros(0);
//                reporte.setEstado(ESTADO_RECHAZADO);
//                reporte.setFechaHoraFin(new Date());
//                getReporteRemoto().actualizar(reporte);
//            } catch (Exception ex) {
//                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[generarReporteNovedadesAseguramiento] Error acualizando AsegInforme con Id " + reporte.getId(), ex);                
//            }
//        }
//    }

    private void actualizarNovedadesReporteNovedadesAseguramiento() {
        NovedadAseguramiento novedad = null;
        // recorremos la lista actualizando el registro de novedad relacionado al registro en la vista
        for (VAsegNovedad reg :listaNovedadesAseguramiento){
            try {
                //consultamos el traslado para obtener el id del afiliado
                novedad = getNovedadAseguramientoRemoto().consultar(reg.getId());
            } catch (Exception ex) {
                Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[actualizarNovedadesReporteNovedadesAseguramiento] Error Consultando novedad con id " + reg.getId(), ex);
            }
            // validamos que se obtuvo una y que esta, tiene la fecha de notificación nula
            if (novedad != null && novedad.getFechaHoraNotificacion() == null) {
                // actualizamos la novedad
                try {
                    novedad.setFechaHoraNotificacion(reporte.getFechaHoraCrea());
                    // ponemos los campos de auditoria, tomados del objeto de Reporte
                    novedad.setUsuarioModifica(reporte.getUsuarioCrea());
                    novedad.setTerminalModifica(reporte.getTerminalCrea());
                    novedad.setFechaHoraModifica(reporte.getFechaHoraCrea());

                    getNovedadAseguramientoRemoto().actualizar(novedad);
                    reg.setNovedadActualizada(true);
                }catch(Exception ex) {
                    Logger.getLogger(ReporteHilo.class.getName()).log(Level.SEVERE, "[actualizarNovedadesReporteNovedadesAseguramiento] Error actualizando novedad con id " + novedad.getId(), ex);
                    //marcamos el registro si la novedad, por excepción no fué actualizada.
                    reg.setNovedadActualizada(false);
                }
            }else {
                reg.setNovedadActualizada(false);
            }
        }
    }
}
