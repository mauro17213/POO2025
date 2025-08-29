/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegInforme;
import com.saviasaludeps.savia.web.aseguramiento.servicio.ReportesServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.servicio.ReportesServicioImpl;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class ReportesBean extends Url {

    private ReportesServicioIface reporteServicio;
    private AsegInforme objeto;
    private List<AsegInforme> registros;
    private LazyDataModel<AsegInforme> lazyReportes;
    
    private Date fechaActual;
    
    private boolean requeridoReporte;

    public ReportesBean() {
        this.objeto = new AsegInforme();
        fechaActual = new Date();
        requeridoReporte = false;
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_REPORTES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyReportes = new LazyDataModel<AsegInforme>() {
                private List<AsegInforme> listaReportes;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
                @Override
                public List<AsegInforme> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaReportes = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaReportes;
                }

                @Override
                public String getRowKey(AsegInforme reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public AsegInforme getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (AsegInforme reporte : listaReportes) {
                        if (id.equals(reporte.getId())) {
                            return reporte;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getReporteServicio().cargaInicial(this);
        listar();
    }

    public ReportesServicioIface getReporteServicio() {
        if (reporteServicio == null) {
            reporteServicio = new ReportesServicioImpl();
        }
        return reporteServicio;
    }

    public void setCargaMasivaServicio(ReportesServicioIface reporteServicio) {
        this.reporteServicio = reporteServicio;
    }

    public AsegInforme getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegInforme objeto) {
        this.objeto = objeto;
    }

    public List<AsegInforme> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegInforme> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegInforme> getLazyReportes() {
        return lazyReportes;
    }

    public void setLazyReportes(LazyDataModel<AsegInforme> lazyReportes) {
        this.lazyReportes = lazyReportes;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getReporteServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getReporteServicio().Accion(this);
        /*PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        */
        procesoFinal();
    }

    public void crear() {
        this.requeridoReporte = true;
        super.setAccion(ACCION_CREAR);
        getReporteServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearReporte:panelCrearReporte");
        PrimeFaces.current().ajax().update("frmCrearReporte:panelCrearReporte");
        PrimeFaces.current().executeScript("PF('dialogoGenerarReporte').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getReporteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGenerarReporte').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        
        procesoFinal();
    }

    public void modificar() {
        procesoFinal();
    }

    public void borrar(int id) {

        procesoFinal();
    }

    public void generarCertificado(int id) {
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmReportes");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmReportes");
                break;
        }
        generarMensajes();
    }
    
    public String getTipoArchivo(int tipo) {
        String descripcion;
        switch(tipo) {
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_AFILIADOS_NUEVOS:
                descripcion = "Afiliados Nuevos (MS)";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_NOVEDADES:
                descripcion = "Novedades de Afiliado (NS)";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_TRASLADOS:
                descripcion = "Solicitud Traslado de Afiliados (S1)";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_PORTABILIDAD:
                descripcion = "Portabilidad";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_DIGITACION_USUARIOS:
                descripcion = "Digitado por Usuario";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_ENCUESTAS_AFILIADOS:
                descripcion = "Encuestas Afiliados";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_NOVEDADES_ASEGURAMIENTO:
                descripcion = "Actualización de Datos";
            break;
            case AfiliadoParametro.TIPO_ARCHIVO_REPORTE_AFILIADOS_RESPALDO:
                descripcion = "Reporte Afiliados - Respaldo";
            break;
            default:
                descripcion = "";
            break;
        }
        
        return descripcion;
    }
    
    public String getEstado(int tipo) {
        String descripcion;
        switch(tipo) {
            case AfiliadoParametro.ESTADO_REPORTE_EN_PROCESO:
                descripcion = "En Proceso";
            break;
            case AfiliadoParametro.ESTADO_REPORTE_PROCESADO:
                descripcion = "Procesado";
            break;
            case AfiliadoParametro.ESTADO_REPORTE_RECHAZADO:
                descripcion = "Rechazado";
            break;
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }
    
    public boolean validarEstadoCarga(int estado, int tipo) {
        boolean deshabilitar = true;
        //2020-09-17 jyperez adición nuevo reporte respaldo - No se imprime
        if (estado == AfiliadoParametro.ESTADO_REPORTE_PROCESADO && tipo != AfiliadoParametro.TIPO_ARCHIVO_REPORTE_AFILIADOS_RESPALDO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == AfiliadoParametro.ESTADO_REPORTE_EN_PROCESO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public StreamedContent generarArchivoReporte(AsegInforme obj) {
        StreamedContent streamedContent = null;
        this.objeto = obj;
        super.setAccion(ACCION_ADICIONAL_1);
        try {
            String archivo = obj.getRuta() + obj.getArchivo();
            Path path = Paths.get(archivo);
            byte[] bytes = Files.readAllBytes(path);
            InputStream stream = new ByteArrayInputStream(bytes);
            stream.mark(0); //remember to this position!
            streamedContent =DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(obj.getArchivo()).build(); 
        } catch (Exception ex) {
            streamedContent = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            addError("Ocurrió un error descargando el archivo desde la ruta especificada.");
            generarMensajes();
        }
        procesoFinal();
        return streamedContent;
    }
    
    public void validarReporteObligatorio() {
        if (this.objeto.getTipo() == AfiliadoParametro.TIPO_ARCHIVO_REPORTE_NOVEDADES_ASEGURAMIENTO) {
            this.requeridoReporte = false;
        }else {
            this.requeridoReporte = true;
        }
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the requeridoReporte
     */
    public boolean isRequeridoReporte() {
        return requeridoReporte;
    }

    /**
     * @param requeridoReporte the requeridoReporte to set
     */
    public void setRequeridoReporte(boolean requeridoReporte) {
        this.requeridoReporte = requeridoReporte;
    }

}
