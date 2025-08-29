/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.rips.regla.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmControlCierre;

import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import java.util.HashMap;
import org.primefaces.event.SelectEvent;
import com.saviasaludeps.savia.web.cuentamedica.rips.regla.servicio.CmControlCierreServicioIface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author rpalacic SaviaSalud EPS
 */
@ManagedBean
@ViewScoped
public class CmControlCierreBean extends Url {

    public static final int TIPO_OPERACION_INSERCION = 1;
    public static final int TIPO_OPERACION_EDICION   = 2;
    private CmControlCierreServicioIface cmControlCierreServicio;
    private CmControlCierre objeto;
    private List<CmControlCierre> registros;
    private List<CmControlCierre> listaControlCierre;
    private LazyDataModel<CmControlCierre> lazyControlCierre;
    private HashMap<Integer, Maestro> hashModalidadContratos;
    private List<Maestro> maestros;
    private Date fechaActual;
    private boolean deshabilitarNoPbs;

    //lista de Maestros
    private List<Maestro> listaModalidadContrato;

    public CmControlCierreBean() {

        this.objeto = new CmControlCierre();
        Modulo _mod = super.validarModulo(Modulo.ID_CM_CONTROL_CIERRE);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyControlCierre = new LazyDataModel<CmControlCierre>() {

                private List<CmControlCierre> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmControlCierre> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmControlCierre objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmControlCierre getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmControlCierre objeto : lista) {
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
        getCmControlCierreServicio().cargasInicial(this);
        listar();
    }

    public CmControlCierreServicioIface getCmControlCierreServicio() {
        return cmControlCierreServicio;
    }

    public void setCmControlCierreServicio(CmControlCierreServicioIface cmControlCierreServicio) {
        this.cmControlCierreServicio = cmControlCierreServicio;
    }

    public CmControlCierre getObjeto() {
        return objeto;
    }

    public void setObjeto(CmControlCierre objeto) {
        this.objeto = objeto;
    }

    public List<CmControlCierre> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmControlCierre> registros) {
        this.registros = registros;
    }

    public List<CmControlCierre> getListaControlCierre() {
        return listaControlCierre;
    }

    public void setListaControlCierre(List<CmControlCierre> listaControlCierre) {
        this.listaControlCierre = listaControlCierre;
    }

    public LazyDataModel<CmControlCierre> getLazyControlCierre() {
        return lazyControlCierre;
    }

    public void setLazyControlCierre(LazyDataModel<CmControlCierre> lazyControlCierre) {
        this.lazyControlCierre = lazyControlCierre;
    }

    public HashMap<Integer, Maestro> getHashModalidadContratos() {
        return hashModalidadContratos;
    }

    public void setHashModalidadContratos(HashMap<Integer, Maestro> hashModalidadContratos) {
        this.hashModalidadContratos = hashModalidadContratos;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }

    public List<Maestro> getListaModalidadContrato() {
        return listaModalidadContrato;
    }

    public void setListaModalidadContrato(List<Maestro> listaModalidadContrato) {
        this.listaModalidadContrato = listaModalidadContrato;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public boolean isDeshabilitarNoPbs() {
        return deshabilitarNoPbs;
    }

    public void setDeshabilitarNoPbs(boolean deshabilitarNoPbs) {
        this.deshabilitarNoPbs = deshabilitarNoPbs;
    }  

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmControlCierreServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getCmControlCierreServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        fechaActual = new Date();
        super.setAccion(ACCION_CREAR);
        getCmControlCierreServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:pCrearDatos1");
        PrimeFaces.current().ajax().update("frmCrear:modalidad");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        boolean validacion = true;
        super.setAccion(ACCION_GUARDAR);
        
        validacion = validarFechas( TIPO_OPERACION_INSERCION);
         
        if(validacion){
           getCmControlCierreServicio().Accion(this);
        }
   
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        fechaActual = new Date();
        getCmControlCierreServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        
        boolean validacion = true;
        super.setAccion(ACCION_MODIFICAR);
        
        validacion = validarFechas(TIPO_OPERACION_EDICION);

        if (validacion) {
            getCmControlCierreServicio().Accion(this);
        }
        
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
        }
        procesoFinal();
    }

    private boolean validarFechas( int tipoOperacion) {
        boolean validacion = true;
        Date fechaActual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        if ((TIPO_OPERACION_INSERCION == tipoOperacion ||  TIPO_OPERACION_EDICION== tipoOperacion) 
                && this.getObjeto().getFechaHoraDesde().after(this.getObjeto().getFechaHoraHasta())) {
            
            this.addError("La fecha y hora de cierre no debe ser mayor que la fecha de apertura.");
            validacion = false;
        }

        if (TIPO_OPERACION_INSERCION == tipoOperacion
                && validacion && (this.getObjeto().getFechaHoraDesde().compareTo(fechaActual) < 0)) {

            this.addError("La fecha de cierre debe ser mayor o igual a la fecha y hora actual.( " + dateFormat.format(fechaActual) + " )");
            validacion = false;
        }
        
        if (validacion && (this.getObjeto().getFechaHoraHasta().compareTo(fechaActual) < 0)) {
            
            this.addError("La fecha de apertura debe ser mayor o igual a la fecha actual.( " + dateFormat.format(fechaActual) + " )");
            validacion = false;
        }
        
        return validacion;
    }

    public void seleccionarContrato(SelectEvent<CmControlCierre> obj) {
        getObjeto().setMaeContratoModalidadId(obj.getObject().getMaeContratoModalidadId());
        PrimeFaces.current().ajax().update("frmCrear:modalidad");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmControlCierre");
                    crearLog(this.getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
    
    public void deshabilitarCoberturaSegunModalidad(String itemActualizar) {

        this.setDeshabilitarNoPbs(false);
        Maestro meatro = getHashModalidadContratos().get(getObjeto().getMaeContratoModalidadId());

        if (meatro != null && meatro.getNombre() != null) {
            desHabilitarCoberturaParaMotivoCapita(meatro.getNombre());
        }
        PrimeFaces.current().ajax().update(itemActualizar);

    }
    
    public void desHabilitarCoberturaParaMotivoCapita(String nombreModalidadContrato){
        this.setDeshabilitarNoPbs(false);
        if (nombreModalidadContrato != null && nombreModalidadContrato.toUpperCase().equals("CAPITA")) {
                this.setDeshabilitarNoPbs(true);
        }
    }

}
