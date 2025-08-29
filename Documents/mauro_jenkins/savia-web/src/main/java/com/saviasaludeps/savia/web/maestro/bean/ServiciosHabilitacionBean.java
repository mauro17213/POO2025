/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.web.maestro.servicio.ServiciosHabilitacionServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.ServiciosHabilitacionServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class ServiciosHabilitacionBean extends Url {

    private ServiciosHabilitacionServicioIface serviciosHabilitacionServicio;
    private MaServicioHabilitacion objeto;
    private List<MaServicioHabilitacion> registros;
    private LazyDataModel<MaServicioHabilitacion> lazyServiciosHabilitacion;
    //lista de Maestros
    private List<Maestro> listaTipos;
    private HashMap<Integer, Maestro> hashTipos;
    private List<Maestro> listaGrupos;
    private HashMap<Integer, Maestro> hashGrupos;
    //2021-04-22 jyperez Se adiciona variable que almacenara estado de la tecnologia
    private boolean estadoInicialTecnologia;

    public ServiciosHabilitacionBean() {
        this.objeto = new MaServicioHabilitacion();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_SERVICIOS_HABILITACION);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyServiciosHabilitacion = new LazyDataModel<MaServicioHabilitacion>() {
                private List<MaServicioHabilitacion> listaServiciosHabilitacion;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaServicioHabilitacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaServiciosHabilitacion = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaServiciosHabilitacion;
                }

                @Override
                public String getRowKey(MaServicioHabilitacion reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaServicioHabilitacion getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaServicioHabilitacion serviciosHabilitacion : listaServiciosHabilitacion) {
                        if (id.equals(serviciosHabilitacion.getId())) {
                            return serviciosHabilitacion;
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
        //jyperez aca se llama la carga inicial
        getServiciosHabilitacionServicio().cargaInicial(this);
        listar();
    }

    public ServiciosHabilitacionServicioIface getServiciosHabilitacionServicio() {
        if (serviciosHabilitacionServicio == null) {
            serviciosHabilitacionServicio = new ServiciosHabilitacionServicioImpl();
        }
        return serviciosHabilitacionServicio;
    }

    public void setServiciosHabilitacionServicio(ServiciosHabilitacionServicioIface serviciosHabilitacionServicio) {
        this.serviciosHabilitacionServicio = serviciosHabilitacionServicio;
    }

    public MaServicioHabilitacion getObjeto() {
        return objeto;
    }

    public void setObjeto(MaServicioHabilitacion objeto) {
        this.objeto = objeto;
    }

    public List<MaServicioHabilitacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaServicioHabilitacion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaServicioHabilitacion> getLazyServiciosHabilitacion() {
        return lazyServiciosHabilitacion;
    }

    public void setLazyServiciosHabilitacion(LazyDataModel<MaServicioHabilitacion> lazyServiciosHabilitacion) {
        this.lazyServiciosHabilitacion = lazyServiciosHabilitacion;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getServiciosHabilitacionServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getServiciosHabilitacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getServiciosHabilitacionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getServiciosHabilitacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getServiciosHabilitacionServicio().Accion(this);
        //2021-04-26 jyperez obtenemos el valor del estado del objeto
        estadoInicialTecnologia = objeto.isActivo();
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getServiciosHabilitacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getServiciosHabilitacionServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmServiciosHabilitacion");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmServiciosHabilitacion");
                break;
        }
        generarMensajes();
    }

    public String getActivo(boolean valor) {
        if (valor) {
            return "Si";
        } else {
            return "No";
        }
    }

    /**
     * @return the listaTipos
     */
    public List<Maestro> getListaTipos() {
        return listaTipos;
    }

    /**
     * @param listaTipos the listaTipos to set
     */
    public void setListaTipos(List<Maestro> listaTipos) {
        this.listaTipos = listaTipos;
    }

    /**
     * @return the hashTipos
     */
    public HashMap<Integer, Maestro> getHashTipos() {
        return hashTipos;
    }

    /**
     * @param hashTipos the hashTipos to set
     */
    public void setHashTipos(HashMap<Integer, Maestro> hashTipos) {
        this.hashTipos = hashTipos;
    }

    /**
     * @return the estadoInicialTecnologia
     */
    public boolean isEstadoInicialTecnologia() {
        return estadoInicialTecnologia;
    }

    /**
     * @param estadoInicialTecnologia the estadoInicialTecnologia to set
     */
    public void setEstadoInicialTecnologia(boolean estadoInicialTecnologia) {
        this.estadoInicialTecnologia = estadoInicialTecnologia;
    }

    /**
     * @return the listaGrupos
     */
    public List<Maestro> getListaGrupos() {
        return listaGrupos;
    }

    /**
     * @param listaGrupos the listaGrupos to set
     */
    public void setListaGrupos(List<Maestro> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    /**
     * @return the hashGrupos
     */
    public HashMap<Integer, Maestro> getHashGrupos() {
        return hashGrupos;
    }

    /**
     * @param hashGrupos the hashGrupos to set
     */
    public void setHashGrupos(HashMap<Integer, Maestro> hashGrupos) {
        this.hashGrupos = hashGrupos;
    }

}
