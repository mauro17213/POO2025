/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.web.maestro.servicio.InsumosServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.InsumosServicioImpl;
import com.saviasaludeps.savia.web.mipres.seleccion.bean.SelCodigoMipresBean;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
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
public class InsumosBean extends Url {

    private InsumosServicioIface insumosServicio;
    private MaInsumo objeto;
    private List<MaInsumo> registros;
    private LazyDataModel<MaInsumo> lazyInsumos;
    //lista de Maestros
    private List<Maestro> listaTipos;
    private HashMap<Integer, Maestro> hashTipos;
    //2021-04-22 jyperez Se adiciona variable que almacenara estado de la tecnologia
    private boolean estadoInicialTecnologia;
    //2024-08-06 jyperez nueva lista para relacionar codigos mipres
    private List<MpCodigoInsumo> listaCodigoInsumo;
    private List<MpCodigoInsumo> listaCodigoInsumoBorrar;
    
    private SelCodigoMipresBean selCodigoMipresBean;
    
    private MaestroSingle maestroSingle;

    public InsumosBean() {
        this.objeto = new MaInsumo();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_INSUMOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyInsumos = new LazyDataModel<MaInsumo>() {
                private List<MaInsumo> listaInsumos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaInsumo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaInsumos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaInsumos;
                }

                @Override
                public String getRowKey(MaInsumo reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaInsumo getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaInsumo validacionDerechos : listaInsumos) {
                        if (id.equals(validacionDerechos.getId())) {
                            return validacionDerechos;
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
        getInsumosServicio().cargaInicial(this);
        listar();
    }

    public InsumosServicioIface getInsumosServicio() {
        if (insumosServicio == null) {
            insumosServicio = new InsumosServicioImpl();
        }
        return insumosServicio;
    }

    public void setInsumosServicio(InsumosServicioIface insumosServicio) {
        this.insumosServicio = insumosServicio;
    }

    public MaInsumo getObjeto() {
        return objeto;
    }

    public void setObjeto(MaInsumo objeto) {
        this.objeto = objeto;
    }

    public List<MaInsumo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaInsumo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaInsumo> getLazyInsumos() {
        return lazyInsumos;
    }

    public void setLazyInsumos(LazyDataModel<MaInsumo> lazyInsumos) {
        this.lazyInsumos = lazyInsumos;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getInsumosServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getInsumosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getInsumosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getInsumosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getInsumosServicio().Accion(this);
        //2021-04-22 jyperez obtenemos el valor del estado del objeto
        estadoInicialTecnologia = objeto.isActivo();
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getInsumosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getInsumosServicio().Accion(this);
        procesoFinal();
    }
    
    public void consultarMpCodigoInsumo() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoCodigoInsumoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmCodigoInsumoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoSelCodigoMipres() {
        boolean agregar = true;
        for (MpCodigoInsumo codigo : listaCodigoInsumo) {
            if (codigo.getId().equals(getSelCodigoMipresBean().getObjeto().getId())) {
                agregar = false;
                addMensaje("El código Mipres " + codigo.getCodigoMipres()+ " ya fue agregado.");
            }
        }
        if (agregar) {
            getSelCodigoMipresBean().getObjeto().setNuevoRegistro(true);
            listaCodigoInsumo.add(getSelCodigoMipresBean().getObjeto()) ;
        } else {
            generarMensajes();
        }
        getSelCodigoMipresBean().setObjeto(new MpCodigoInsumo());
        PrimeFaces.current().executeScript("PF('dialogoCodigoInsumoBusqueda').hide()");
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:tablaTecnologiaCodigoMipres");
        PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiaCodigoMipres");
    }

    public void borrarCodigoMipres(MpCodigoInsumo codigo) {
        listaCodigoInsumo.remove(codigo);
        PrimeFaces.current().ajax().update("frmCrear:tablaTecnologiaCodigoMipres");
        PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiaCodigoMipres");
    }

    public void eliminarCodigosMipres(MpCodigoInsumo codigo) {
        //adicionamos el grupo a eliminar a la lista de grupos.
        listaCodigoInsumoBorrar.add(codigo);
        listaCodigoInsumo.remove(codigo);
        PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiaCodigoMipres");
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
                PrimeFaces.current().ajax().update("frmInsumos");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmInsumos");
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

    public String getCobertura(Integer valor) {
        String mensaje = "";
        if (valor != null) {
            switch (valor) {
                case 1:
                    mensaje = "POS";
                    break;
                case 2:
                    mensaje = "NO POS";
                    break;
                case 3:
                    mensaje = "Condicional";
                    break;
            }
        }
        return mensaje;
    }
    
    /**
     * @return the selCodigoMipresBean
     */
    public SelCodigoMipresBean getSelCodigoMipresBean() {
        selCodigoMipresBean = (SelCodigoMipresBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selCodigoMipresBean");
        return selCodigoMipresBean;
    }

    /**
     * @param selCodigoMipresBean the selCodigoMipresBean to set
     */
    public void setSelCodigoMipresBean(SelCodigoMipresBean selCodigoMipresBean) {
        this.selCodigoMipresBean = selCodigoMipresBean;
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
     * @return the maestroSingle
     */
    public MaestroSingle getMaestroSingle() {
        return maestroSingle;
    }

    /**
     * @param maestroSingle the maestroSingle to set
     */
    public void setMaestroSingle(MaestroSingle maestroSingle) {
        this.maestroSingle = maestroSingle;
    }

    /**
     * @return the listaCodigoInsumo
     */
    public List<MpCodigoInsumo> getListaCodigoInsumo() {
        return listaCodigoInsumo;
    }

    /**
     * @param listaCodigoInsumo the listaCodigoInsumo to set
     */
    public void setListaCodigoInsumo(List<MpCodigoInsumo> listaCodigoInsumo) {
        this.listaCodigoInsumo = listaCodigoInsumo;
    }

    /**
     * @return the listaCodigoInsumoBorrar
     */
    public List<MpCodigoInsumo> getListaCodigoInsumoBorrar() {
        return listaCodigoInsumoBorrar;
    }

    /**
     * @param listaCodigoInsumoBorrar the listaCodigoInsumoBorrar to set
     */
    public void setListaCodigoInsumoBorrar(List<MpCodigoInsumo> listaCodigoInsumoBorrar) {
        this.listaCodigoInsumoBorrar = listaCodigoInsumoBorrar;
    }
}
