/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.bean;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.web.gestion.atencion.servicio.GatTurnoServicioIface;
import com.saviasaludeps.savia.web.gestion.atencion.utilidades.GatContantes;
import com.saviasaludeps.savia.web.gestion.atencion.utilidades.Utilidad;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class GatTurnoBean extends Url {

    @Autowired
    private GatTurnoServicioIface gatTurnoServicio;
    private GatTiquete objeto;
    private List<GatTiquete> registros;
    private LazyDataModel<GatTiquete> lazyTiquetes;
    private GnSede objetoSede;
    private List<Maestro> listaTipoDocumentos;
    private HashMap<Integer, Maestro> hashTipoDocumentos;
    private List<Maestro> listaServicios;
    private List<GnSede> listaSedes;
    private HashMap<Integer, Maestro> hashServicios;
    private boolean salir;
    private GatSedeConfiguracion configuracion;
    private Date fechaMaxima;
    private StreamedContent reporteTicket;
    private String reporteTicketString;

    public GatTurnoBean() {
        this.objeto = new GatTiquete();
        this.objetoSede = new GnSede();
        Modulo _mod = super.validarModulo(Modulo.ID_GAT_TURNO);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyTiquetes = new LazyDataModel<GatTiquete>() {

                private List<GatTiquete> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GatTiquete> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(GatTiquete objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GatTiquete getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GatTiquete objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getGatTurnoServicio().cargaInicial(this);
        if (getObjetoSede() != null && getObjetoSede().getId() != null) {
            listar();
        } else {
            PrimeFaces.current().ajax().update("frmSede");
            PrimeFaces.current().executeScript("PF('dialogoSede').show()");
        }
    }

    public GatTurnoServicioIface getGatTurnoServicio() {
        return gatTurnoServicio;
    }

    public void setGatTurnoServicio(GatTurnoServicioIface gatTurnoServicio) {
        this.gatTurnoServicio = gatTurnoServicio;
    }

    public GatTiquete getObjeto() {
        return objeto;
    }

    public void setObjeto(GatTiquete objeto) {
        this.objeto = objeto;
    }

    public List<GatTiquete> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GatTiquete> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GatTiquete> getLazyTiquetes() {
        return lazyTiquetes;
    }

    public void setLazyTiquetes(LazyDataModel<GatTiquete> lazyTiquetes) {
        this.lazyTiquetes = lazyTiquetes;
    }

    public GnSede getObjetoSede() {
        return objetoSede;
    }

    public void setObjetoSede(GnSede objetoSede) {
        this.objetoSede = objetoSede;
    }

    public List<Maestro> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<Maestro> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentos() {
        return hashTipoDocumentos;
    }

    public void setHashTipoDocumentos(HashMap<Integer, Maestro> hashTipoDocumentos) {
        this.hashTipoDocumentos = hashTipoDocumentos;
    }

    public List<Maestro> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Maestro> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<GnSede> getListaSedes() {
        return listaSedes;
    }

    public void setListaSedes(List<GnSede> listaSedes) {
        this.listaSedes = listaSedes;
    }

    public HashMap<Integer, Maestro> getHashServicios() {
        return hashServicios;
    }

    public void setHashServicios(HashMap<Integer, Maestro> hashServicios) {
        this.hashServicios = hashServicios;
    }

    public boolean isSalir() {
        return salir;
    }

    public void setSalir(boolean salir) {
        this.salir = salir;
    }

    public GatSedeConfiguracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(GatSedeConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public StreamedContent getReporteTicket() {
        return reporteTicket;
    }

    public void setReporteTicket(StreamedContent reporteTicket) {
        this.reporteTicket = reporteTicket;
    }

    public String getReporteTicketString() {
        return reporteTicketString;
    }

    public void setReporteTicketString(String reporteTicketString) {
        this.reporteTicketString = reporteTicketString;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGatTurnoServicio().Accion(this);
        if (getObjetoSede().getId() == null) {
            setObjetoSede(new GnSede());
            PrimeFaces.current().ajax().update("frmSede");
            PrimeFaces.current().executeScript("PF('dialogoSede').show()");
        }
    }

    public void listar() {
        if (!isSalir()) {
            setSalir(true);
        }
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR);
        getGatTurnoServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion((Url.ACCION_CREAR));
        getGatTurnoServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getGatTurnoServicio().Accion(this);
        if (!this.isError()) {
            imprimirTicket();
        }
        procesoFinal();
    }

    public void buscarAfiliado() {
        if (getObjeto().getGatUsuario().getMaeTipoDocumentoId() > 0 && getObjeto().getGatUsuario().getNumeroDocumento() != null) {
            super.setAccion(Url.ACCION_CREAR);
            super.setDoAccion(Url.ACCION_LISTAR);
            getGatTurnoServicio().Accion(this);
            PrimeFaces.current().ajax().update("frmCrear");
            this.generarMensajes();
        }
    }

    public void seleccionar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getGatTurnoServicio().Accion(this);
        procesoFinal();
    }

    public void salir() {
        if (isSalir()) {
            super.redireccionar("/savia/home.faces");
        } else {
            setSalir(true);
            PrimeFaces.current().executeScript("PF('dialogoSede').hide()");
        }

    }

    public void cambiarSede() {
        PrimeFaces.current().ajax().update("frmSede");
        PrimeFaces.current().executeScript("PF('dialogoSede').show()");
        setSalir(false);
    }

    public void buscarSede() {
        for (GnSede sede : getListaSedes()) {
            if (sede.getId().equals(getObjetoSede().getId())) {
                setObjetoSede(sede);
                break;
            }
        }
    }

    private void imprimirTicket() {
        try {
            InputStream is = getClass().getResourceAsStream("/reportes/ticket.jasper");
            Map parameters = new HashMap();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
            parameters.put(GatContantes.TURNO, objeto.getNumero());
            parameters.put(GatContantes.SERVICIO, objeto.getMaeTipoServicioValor());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
            // Genera el JasperPrint (el informe lleno de datos)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            reporteTicketString = Utilidad.reporteString(jasperPrint);
            //JasperPrintManager.printReport(jasperPrint, false);
            try {
                PrimeFaces.current().executeScript("imprimirContenido();");
            } catch (Exception e) {
                addError("No fue posible visualizar la vista de impresión. (" + e.getMessage() + ")");
            }

        } catch (Exception e) {
            addError("No fue posible imprimir el ticket, verifique la impresora predeterminada.(" + e.getMessage() + ")");
            e.printStackTrace();
        }
    }

    public void reIprimirTicket(String numero, String tipo) {
        try {
            InputStream is = getClass().getResourceAsStream("/reportes/ticket.jasper");
            Map parameters = new HashMap();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
            parameters.put(GatContantes.TURNO, numero);
            parameters.put(GatContantes.SERVICIO, tipo);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
            // Genera el JasperPrint (el informe lleno de datos)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            reporteTicketString = Utilidad.reporteString(jasperPrint);
            //JasperPrintManager.printReport(jasperPrint, false);
            try {
                PrimeFaces.current().executeScript("imprimirContenido();");
            } catch (Exception e) {
                addError("No fue posible visualizar la vista de impresión. (" + e.getMessage() + ")");
            }

        } catch (Exception e) {
            addError("No fue posible imprimir el ticket, verifique la impresora predeterminada.(" + e.getMessage() + ")");
            e.printStackTrace();
        }
    }

    public void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmTurnos");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmTurnos");
                            PrimeFaces.current().executeScript("PF('dialogoSede').hide()");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmTurnos");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

}
