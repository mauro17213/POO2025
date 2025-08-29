/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmInforme;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.Reporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmInformeRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.ReporteCuentasMedicasBean;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.utilidades.ReportesCargaMasivaHilo;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jperez
 */
public class ReporteCuentasMedicasServicioImpl extends AccionesBO implements ReporteCuentasMedicasServicioIface {

    public static final int TIPO_CONCILIACION_INDIVIDUAL = 1;
    public static final int TIPO_CONCILIACION_MASIVA = 2;
    public static final int TIPO_RESPUESTA_GLOSA = 3;
    
    
    private CmInformeRemoto getCmInformeRemoto() throws Exception {
        return (CmInformeRemoto) RemotoEJB.getEJBRemoto(("CmInformeServicio"), CmInformeRemoto.class.getName());
    }

    @Override
    public void Accion(ReporteCuentasMedicasBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_CREAR:
                    procesarTipoInforme(bean);
                    break;
            }
        }
    }


    @Override
    public void cargaIncial(ReporteCuentasMedicasBean bean) {
        try {
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void procesarTipoInforme(ReporteCuentasMedicasBean bean) {
           try {
            String mensajeProceso ;
            CmInforme informe = bean.getObjeto();
            String ruta = PropApl.getInstance().get(PropApl.CM_RUTA_REPORTES);
            informe.setEstado(CmInforme.ESTADO_PROCESO);
            informe.setFechaHoraInicio(new Date());
            informe.setFechaHoraFin(new Date());
            informe.setRuta(ruta);
            informe.setArchivo(informe.getAsignarcionNombre());
            informe.setObservacion("");
            bean.auditoriaGuardar(informe);
            int idInforme =  getCmInformeRemoto().insertar(informe);
            informe.setId(idInforme);
            informe.setArchivo(informe.getAsignarcionNombre());
            getCmInformeRemoto().actualizar(informe);
            
            mensajeProceso = "El reporte " + bean.getObjeto().getArchivo() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId();
                
            if (informe.getId() != null) {
                ReportesCargaMasivaHilo reporteHilo = new ReportesCargaMasivaHilo(informe, bean.getParamConsulta());
                reporteHilo.start();
            }
          
            bean.setParamConsulta(new ParamConsulta());
            bean.addMensaje(mensajeProceso);
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getMessage().equals("Java heap space")){
              bean.addError("Ha ocurrido un error por la cantidad de información a procesar,"
                      + " por favor intente segmentación de búsqueda más especifica.");  
            }else{
              bean.addError(e.getMessage());
            }        
        }
    }

    
    private void listar(ReporteCuentasMedicasBean bean) {
        try {
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmInformeRemoto().consultarCantidadLista(bean.getParamConsultaUtilitario()));
            bean.setRegistros(getCmInformeRemoto().consultarLista(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void generarInforme(ReporteCuentasMedicasBean bean, Reporte reporte) {
        try {
            byte[] exportContent = reporte.getContenidoEnBytes();
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + reporte.getNombreReporte() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            int i = reporte.getNombreReporte().lastIndexOf(".");
            String ext = reporte.getNombreReporte().substring(i, reporte.getNombreReporte().length());
            if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/doc");
            } else if (ext.equalsIgnoreCase(".docx")) {
                ec.setResponseContentType("application/docx");
            } else if (ext.equalsIgnoreCase(".xls")) {
                ec.setResponseContentType("application/xls");
            } else if (ext.equalsIgnoreCase(".xlsx")) {
                ec.setResponseContentType("application/xlsx");
            }else if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            }
            
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (IOException e) {
            bean.addError("No es posible descargar este archivo, por favor contacte con el administrador : " + e.getMessage());
        }
    }

}
