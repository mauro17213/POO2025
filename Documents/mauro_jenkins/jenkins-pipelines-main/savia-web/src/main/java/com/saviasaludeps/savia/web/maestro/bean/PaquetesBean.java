/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.maestro.servicio.PaquetesServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.PaquetesServicioImpl;
import com.saviasaludeps.savia.web.mipres.seleccion.bean.SelCodigoMipresBean;
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
public class PaquetesBean extends Url {

    private PaquetesServicioIface paquetesServicio;
    private MaPaquete objeto;
    private List<MaPaquete> registros;
    private LazyDataModel<MaPaquete> lazyPaquetes;
    //lista de Maestros
    private List<Maestro> listaAmbito;
    private HashMap<Integer, Maestro> hashAmbito;
    // objetos buscadores de Maestros
    private SelInsumosBean selInsumosBean;
    private SelMedicamentoBean selMedicamentoBean;
    private SelTecnologiasBean selTecnologiasBean;
    //2021-04-22 jyperez Se adiciona variable que almacenara estado de la tecnologia
    private boolean estadoInicialTecnologia;
    //2024-08-06 jyperez nueva lista para relacionar codigos mipres
    private List<MpCodigoInsumo> listaCodigoInsumo;
    private List<MpCodigoInsumo> listaCodigoInsumoBorrar;
    private SelCodigoMipresBean selCodigoMipresBean;

    public PaquetesBean() {
        this.objeto = new MaPaquete();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_PAQUETES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyPaquetes = new LazyDataModel<MaPaquete>() {
                private List<MaPaquete> listaPaquetes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaPaquete> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaPaquetes = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaPaquetes;
                }

                @Override
                public String getRowKey(MaPaquete reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaPaquete getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaPaquete paquetes : listaPaquetes) {
                        if (id.equals(paquetes.getId())) {
                            return paquetes;
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
        getPaquetesServicio().cargaInicial(this);
        listar();
    }

    public PaquetesServicioIface getPaquetesServicio() {
        if (paquetesServicio == null) {
            paquetesServicio = new PaquetesServicioImpl();
        }
        return paquetesServicio;
    }

    public void setPaquetesServicio(PaquetesServicioIface paquetesServicio) {
        this.paquetesServicio = paquetesServicio;
    }

    public MaPaquete getObjeto() {
        return objeto;
    }

    public void setObjeto(MaPaquete objeto) {
        this.objeto = objeto;
    }

    public List<MaPaquete> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaPaquete> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaPaquete> getLazyPaquetes() {
        return lazyPaquetes;
    }

    public void setLazyPaquetes(LazyDataModel<MaPaquete> lazyPaquetes) {
        this.lazyPaquetes = lazyPaquetes;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getPaquetesServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getPaquetesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getPaquetesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getPaquetesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getPaquetesServicio().Accion(this);
        //2021-04-22 jyperez obtenemos el valor del estado del objeto
        estadoInicialTecnologia = objeto.isActivo();
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getPaquetesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getPaquetesServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmPaquetes");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmPaquetes");
                break;
        }
        generarMensajes();
    }

    public void cerrarDialogoMaInsumo() {
        getObjeto().setMaInsumo(getSelInsumosBean().getObjeto());
        getSelInsumosBean().setObjeto(new MaInsumo());
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
        //como solo podemos escoger uno de los 3, entonces los otros objetos se resetean
        getObjeto().setMaTecnologia(null);
        getObjeto().setMaMedicamento(null);
        // actualizar lo que debamos actualizar
        //PrimeFaces.current().ajax().update("frmCrear:insumo");
        //PrimeFaces.current().ajax().update("frmEditar:insumo");
        //PrimeFaces.current().ajax().update("frmCrear:medicamento");
        //PrimeFaces.current().ajax().update("frmEditar:medicamento");
        PrimeFaces.current().ajax().update("frmCrear:tecnologia");
        PrimeFaces.current().ajax().update("frmEditar:tecnologia");
    }

    public void consultarMaInsumo() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmInsumoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoMaMedicamento() {
        getObjeto().setMaMedicamento(getSelMedicamentoBean().getObjeto());
        getSelMedicamentoBean().setObjeto(new MaMedicamento());
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
        //como solo podemos escoger uno de los 3, entonces los otros objetos se resetean
        getObjeto().setMaTecnologia(null);
        getObjeto().setMaInsumo(null);
        // actualizar lo que debamos actualizar
        //PrimeFaces.current().ajax().update("frmCrear:insumo");
        //PrimeFaces.current().ajax().update("frmEditar:insumo");
        //PrimeFaces.current().ajax().update("frmCrear:medicamento");
        //PrimeFaces.current().ajax().update("frmEditar:medicamento");
        PrimeFaces.current().ajax().update("frmCrear:tecnologia");
        PrimeFaces.current().ajax().update("frmEditar:tecnologia");
    }

    public void consultarMaMedicamento() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoMaTecnologia() {
        getObjeto().setMaTecnologia(getSelTecnologiasBean().getObjeto());
        getSelTecnologiasBean().setObjeto(new MaTecnologia());
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
        //como solo podemos escoger uno de los 3, entonces los otros objetos se resetean
        getObjeto().setMaInsumo(null);
        getObjeto().setMaMedicamento(null);
        // actualizar lo que debamos actualizar
        //PrimeFaces.current().ajax().update("frmCrear:insumo");
        //PrimeFaces.current().ajax().update("frmEditar:insumo");
        //PrimeFaces.current().ajax().update("frmCrear:medicamento");
        //PrimeFaces.current().ajax().update("frmEditar:medicamento");
        PrimeFaces.current().ajax().update("frmCrear:tecnologia");
        PrimeFaces.current().ajax().update("frmEditar:tecnologia");
    }

    public void consultarMaTecnologia() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
            PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
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
    
    public String retornarCadena(String cadena1, String cadena2) {
        String mensaje = "";
        if (cadena1 != null) {
            mensaje = mensaje + cadena1 + " - ";
        }
        if (cadena2 != null) {
            mensaje = mensaje + cadena2;
        }
        return mensaje;
    }

    public String getActivo(boolean valor) {
        if (valor) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String getValorBandera(Boolean valor) {
        String mensaje = "";
        if (valor != null) {
            if (valor) {
                mensaje = "Si";
            } else {
                mensaje = "No";
            }
        }
        return mensaje;
    }

    public String getTipoTecnologia(int valor) {
        String mensaje = "";
        switch (valor) {
            case 1:
                mensaje = "Tecnología (CUP)";
                break;
            case 2:
                mensaje = "Medicamento (CUM)";
                break;
            case 3:
                mensaje = "Insumo";
                break;
            case 4:
                mensaje = "Paquete";
                break;
        }
        return mensaje;
    }

    /**
     * @return the listaAmbito
     */
    public List<Maestro> getListaAmbito() {
        return listaAmbito;
    }

    /**
     * @param listaAmbito the listaAmbito to set
     */
    public void setListaAmbito(List<Maestro> listaAmbito) {
        this.listaAmbito = listaAmbito;
    }

    /**
     * @return the hashAmbito
     */
    public HashMap<Integer, Maestro> getHashAmbito() {
        return hashAmbito;
    }

    /**
     * @param hashAmbito the hashAmbito to set
     */
    public void setHashAmbito(HashMap<Integer, Maestro> hashAmbito) {
        this.hashAmbito = hashAmbito;
    }

    /**
     * @return the selInsumosBean
     */
    public SelInsumosBean getSelInsumosBean() {
        selInsumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return selInsumosBean;
    }

    /**
     * @param selInsumosBean the selInsumosBean to set
     */
    public void setSelInsumosBean(SelInsumosBean selInsumosBean) {
        this.selInsumosBean = selInsumosBean;
    }

    /**
     * @return the selMedicamentoBean
     */
    public SelMedicamentoBean getSelMedicamentoBean() {
        selMedicamentoBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return selMedicamentoBean;
    }

    /**
     * @param selMedicamentoBean the selMedicamentoBean to set
     */
    public void setSelMedicamentoBean(SelMedicamentoBean selMedicamentoBean) {
        this.selMedicamentoBean = selMedicamentoBean;
    }

    /**
     * @return the selTecnologiasBean
     */
    public SelTecnologiasBean getSelTecnologiasBean() {
        selTecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return selTecnologiasBean;
    }

    /**
     * @param selTecnologiasBean the selTecnologiasBean to set
     */
    public void setSelTecnologiasBean(SelTecnologiasBean selTecnologiasBean) {
        this.selTecnologiasBean = selTecnologiasBean;
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

}
