/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaGrupo;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2000Bean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2001Bean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelSoatBean;
import com.saviasaludeps.savia.web.maestro.servicio.TecnologiasServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.TecnologiasServicioImpl;
import com.saviasaludeps.savia.web.mipres.seleccion.bean.SelCodigoMipresBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class TecnologiasBean extends Url {

    private TecnologiasServicioIface tecnologiasServicio;
    private MaTecnologia objeto;
    private MaTecnologiaGrupo objetoGrupo;
    private List<MaTecnologia> registros;
    private LazyDataModel<MaTecnologia> lazyTecnologias;
    //lista de Maestros
    private List<Maestro> listaGrupoTecnologia;
    private HashMap<Integer, Maestro> hashGrupoTecnologia;
    //objetos buscadores de Maestros
    private SelIss2000Bean selIss2000Bean;
    private SelIss2001Bean selIss2001Bean;
    private SelSoatBean selSoatBean;
    private SelServiciosHabilitacionBean selServiciosHabilitacionBean;
    private SelCodigoMipresBean selCodigoMipresBean;
    //lista Servicios habilitacion
    private List<MaServicioHabilitacion> listaServicios;
    private List<MaServicioHabilitacion> listaServiciosBorrar;
    //lista Tecnologia Servicios Habilitacion
    private List<MaTecnologiaServicioHabilitacion> listaTecnologiaServicios;
    //lista Tecnologia Grupos
    private List<MaTecnologiaGrupo> listaTecnologiaGrupo;
    private List<MaTecnologiaGrupo> listaTecnologiaGrupoBorrar;
    //2021-04-07 INC 758 incluimos campos de maestro de cobertura
    //lista de Maestros
    private List<Maestro> listaCoberturas;
    private HashMap<Integer, Maestro> hashCoberturas;
    //2021-04-22 jyperez Se adiciona variable que almacenara estado de la tecnologia
    private boolean estadoInicialTecnologia;
    
    //2024-08-02 jyperez nueva lista para relacionar codigos mipres
    private List<MpCodigoInsumo> listaCodigoInsumo;
    private List<MpCodigoInsumo> listaCodigoInsumoBorrar;

    public TecnologiasBean() {
        this.objeto = new MaTecnologia();
        this.objetoGrupo = new MaTecnologiaGrupo();
        this.objeto.setListaTecnologiaMipres(new ArrayList());
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_TECNOLOGIAS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyTecnologias = new LazyDataModel<MaTecnologia>() {
                private List<MaTecnologia> listaTecnologias;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaTecnologia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaTecnologias = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaTecnologias;
                }

                @Override
                public String getRowKey(MaTecnologia reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaTecnologia getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaTecnologia tecnologia : listaTecnologias) {
                        if (id.equals(tecnologia.getId())) {
                            return tecnologia;
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
        getTecnologiasServicio().cargaInicial(this);
        listar();
    }

    public TecnologiasServicioIface getTecnologiasServicio() {
        if (tecnologiasServicio == null) {
            tecnologiasServicio = new TecnologiasServicioImpl();
        }
        return tecnologiasServicio;
    }

    public void setTecnologiasServicio(TecnologiasServicioIface tecnologiasServicio) {
        this.tecnologiasServicio = tecnologiasServicio;
    }

    public MaTecnologia getObjeto() {
        return objeto;
    }

    public void setObjeto(MaTecnologia objeto) {
        this.objeto = objeto;
    }

    public List<MaTecnologia> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaTecnologia> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaTecnologia> getLazyTecnologias() {
        return lazyTecnologias;
    }

    public void setLazyTecnologias(LazyDataModel<MaTecnologia> lazyTecnologias) {
        this.lazyTecnologias = lazyTecnologias;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getTecnologiasServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getTecnologiasServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getTecnologiasServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getTecnologiasServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getTecnologiasServicio().Accion(this);
        //2021-04-22 jyperez obtenemos el valor del estado del objeto
        estadoInicialTecnologia = objeto.isActivo();
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getTecnologiasServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getTecnologiasServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmTecnologias");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmTecnologias");
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

    public String getSexoAplica(int valor) {
        String mensaje = "";
        switch (valor) {
            case 0:
                mensaje = "Masculino";
                break;
            case 1:
                mensaje = "Femenino";
                break;
            case 2:
                mensaje = "Ambos";
                break;
        }
        return mensaje;
    }

    public String getUnidad(int valor) {
        String mensaje = "";
        switch (valor) {
            case 1:
                mensaje = "Año";
                break;
            case 2:
                mensaje = "Mes";
                break;
            case 3:
                mensaje = "Día";
                break;
        }
        return mensaje;
    }

    public String getCobertura(Integer valor) {
        String mensaje = "";
        try {
            mensaje = hashCoberturas.get(valor).getNombre();
        } catch (Exception ex) {
            mensaje = "";
        }
        return mensaje;
    }

    /**
     * @return the selIss2000Bean
     */
    public SelIss2000Bean getSelIss2000Bean() {
        selIss2000Bean = (SelIss2000Bean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selIss2000Bean");
        return selIss2000Bean;
    }

    /**
     * @param selIss2000Bean the selIss2000Bean to set
     */
    public void setSelIss2000Bean(SelIss2000Bean selIss2000Bean) {
        this.selIss2000Bean = selIss2000Bean;
    }

    /**
     * @return the selIss2001Bean
     */
    public SelIss2001Bean getSelIss2001Bean() {
        selIss2001Bean = (SelIss2001Bean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selIss2001Bean");
        return selIss2001Bean;
    }

    /**
     * @param selIss2001Bean the selIss2001Bean to set
     */
    public void setSelIss2001Bean(SelIss2001Bean selIss2001Bean) {
        this.selIss2001Bean = selIss2001Bean;
    }

    /**
     * @return the selSoatBean
     */
    public SelSoatBean getSelSoatBean() {
        selSoatBean = (SelSoatBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selSoatBean");
        return selSoatBean;
    }

    /**
     * @param selSoatBean the selSoatBean to set
     */
    public void setSelSoatBean(SelSoatBean selSoatBean) {
        this.selSoatBean = selSoatBean;
    }

    public void cerrarDialogoMaIss2000Tarifario() {
        getObjeto().setMaIss2000Tarifario(getSelIss2000Bean().getObjeto());
        getSelIss2000Bean().setObjeto(new MaIss2000Tarifario());
        PrimeFaces.current().executeScript("PF('dialogoIss2000Busqueda').hide()");
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:tarifaIss2000");
        PrimeFaces.current().ajax().update("frmEditar:tarifaIss2000");
    }

    public void consultarMaIss2000Tarifario() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoIss2000Busqueda').show()");
            PrimeFaces.current().ajax().update("frmIss2000Busqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoMaIss2001Tarifario() {
        getObjeto().setMaIss2001Tarifario(getSelIss2001Bean().getObjeto());
        getSelIss2001Bean().setObjeto(new MaIss2001Tarifario());
        PrimeFaces.current().executeScript("PF('dialogoIss2001Busqueda').hide()");
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:tarifaIss2001");
        PrimeFaces.current().ajax().update("frmEditar:tarifaIss2001");
    }

    public void consultarMaIss2001Tarifario() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoIss2001Busqueda').show()");
            PrimeFaces.current().ajax().update("frmIss2001Busqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoMaSoatTarifario() {
        getObjeto().setMaSoatTarifario(getSelSoatBean().getObjeto());
        getSelSoatBean().setObjeto(new MaSoatTarifario());
        PrimeFaces.current().executeScript("PF('dialogoSoatBusqueda').hide()");
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:tarifaSoat");
        PrimeFaces.current().ajax().update("frmEditar:tarifaSoat");
    }

    public void consultarMaSoatTarifario() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoSoatBusqueda').show()");
            PrimeFaces.current().ajax().update("frmSoatBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoMaServicioHabilitacion() {
        boolean agregar = true;
        for (MaServicioHabilitacion serv : listaServicios) {
            if (serv.getId().compareTo(getSelServiciosHabilitacionBean().getObjeto().getId()) == 0) {
                agregar = false;
                addMensaje("El servicio de habilitación " + getSelServiciosHabilitacionBean().getObjeto().getNombreCorto() + " ya fue agregado.");
            }
        }
        if (agregar) {
            //usamos la variable para validar los registros nuevos en Editar.
            getSelServiciosHabilitacionBean().getObjeto().setNuevoRegistro(true);
            listaServicios.add(getSelServiciosHabilitacionBean().getObjeto());
        } else {
            generarMensajes();
        }
        getSelServiciosHabilitacionBean().setObjeto(new MaServicioHabilitacion());
        PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').hide()");
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:tablaServicios");
        PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
    }

    public void consultarMaServicioHabilitacion() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').show()");
            PrimeFaces.current().ajax().update("frmServiciosHabilitacionBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void onRowSelectGrupos(SelectEvent event) {
        Maestro maestro = (Maestro) event.getObject();
        objetoGrupo = new MaTecnologiaGrupo();
        objetoGrupo.setMaTecnologia(objeto);
        objetoGrupo.setMaeGrupoTecnologiaId(maestro.getId());
        objetoGrupo.setMaeGrupoTecnologiaCodigo(maestro.getValor());
        objetoGrupo.setMaeGrupoTecnologiaValor(maestro.getNombre());
    }

    public void cerrarDialogoSelGrupo() {
        boolean agregar = true;
        for (MaTecnologiaGrupo grupo : listaTecnologiaGrupo) {
            if (grupo.getMaeGrupoTecnologiaId() == objetoGrupo.getMaeGrupoTecnologiaId()) {
                agregar = false;
                addMensaje("El grupo " + objeto.getMaeGrupoTecnologiaValor() + " ya fue agregado.");
            }
        }
        if (agregar) {
            objetoGrupo.setNuevoRegistro(true);
            listaTecnologiaGrupo.add(objetoGrupo);
        } else {
            generarMensajes();
        }
        objetoGrupo = new MaTecnologiaGrupo();
        PrimeFaces.current().executeScript("PF('dialogoGrupos').hide()");
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:tablaGrupos");
        PrimeFaces.current().ajax().update("frmEditar:tablaGrupos");
    }

    public void consultarGrupos() {
        PrimeFaces.current().executeScript("PF('dialogoGrupos').show()");
        PrimeFaces.current().ajax().update("frmGrupos");
    }

    public void borrarGrupo(MaTecnologiaGrupo grupo) {
        listaTecnologiaGrupo.remove(grupo);
        PrimeFaces.current().ajax().update("frmCrear:tablaGrupos");
        PrimeFaces.current().ajax().update("frmEditar:tablaGrupos");
    }

    public void eliminarGrupo(MaTecnologiaGrupo grupo) {
        //adicionamos el grupo a eliminar a la lista de grupos.
        listaTecnologiaGrupoBorrar.add(grupo);
        listaTecnologiaGrupo.remove(grupo);
        PrimeFaces.current().ajax().update("frmEditar:tablaGrupos");
    }

    public void borrarServicio(MaServicioHabilitacion servicio) {
        listaServicios.remove(servicio);
        PrimeFaces.current().ajax().update("frmCrear:tablaServicios");
        PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
    }

    public void eliminarServicio(MaServicioHabilitacion servicio) {
        //adicionamos el servicio a eliminar a la lista de servicios.
        getListaServiciosBorrar().add(servicio);
        listaServicios.remove(servicio);
        PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
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

    /**
     * @return the listaGrupoTecnologia
     */
    public List<Maestro> getListaGrupoTecnologia() {
        return listaGrupoTecnologia;
    }

    /**
     * @param listaGrupoTecnologia the listaGrupoTecnologia to set
     */
    public void setListaGrupoTecnologia(List<Maestro> listaGrupoTecnologia) {
        this.listaGrupoTecnologia = listaGrupoTecnologia;
    }

    /**
     * @return the hashGrupoTecnologia
     */
    public HashMap<Integer, Maestro> getHashGrupoTecnologia() {
        return hashGrupoTecnologia;
    }

    /**
     * @param hashGrupoTecnologia the hashGrupoTecnologia to set
     */
    public void setHashGrupoTecnologia(HashMap<Integer, Maestro> hashGrupoTecnologia) {
        this.hashGrupoTecnologia = hashGrupoTecnologia;
    }

    /**
     * @return the listaServicios
     */
    public List<MaServicioHabilitacion> getListaServicios() {
        return listaServicios;
    }

    /**
     * @param listaServicios the listaServicios to set
     */
    public void setListaServicios(List<MaServicioHabilitacion> listaServicios) {
        this.listaServicios = listaServicios;
    }

    /**
     * @return the selServiciosHabilitacionBean
     */
    public SelServiciosHabilitacionBean getSelServiciosHabilitacionBean() {
        selServiciosHabilitacionBean = (SelServiciosHabilitacionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selServiciosHabilitacionBean");
        return selServiciosHabilitacionBean;
    }

    /**
     * @param selServiciosHabilitacionBean the selServiciosHabilitacionBean to
     * set
     */
    public void setSelServiciosHabilitacionBean(SelServiciosHabilitacionBean selServiciosHabilitacionBean) {
        this.selServiciosHabilitacionBean = selServiciosHabilitacionBean;
    }

    /**
     * @return the listaTecnologiaServicios
     */
    public List<MaTecnologiaServicioHabilitacion> getListaTecnologiaServicios() {
        return listaTecnologiaServicios;
    }

    /**
     * @param listaTecnologiaServicios the listaTecnologiaServicios to set
     */
    public void setListaTecnologiaServicios(List<MaTecnologiaServicioHabilitacion> listaTecnologiaServicios) {
        this.listaTecnologiaServicios = listaTecnologiaServicios;
    }

    /**
     * @return the listaServiciosBorrar
     */
    public List<MaServicioHabilitacion> getListaServiciosBorrar() {
        return listaServiciosBorrar;
    }

    /**
     * @param listaServiciosBorrar the listaServiciosBorrar to set
     */
    public void setListaServiciosBorrar(List<MaServicioHabilitacion> listaServiciosBorrar) {
        this.listaServiciosBorrar = listaServiciosBorrar;
    }

    /**
     * @return the listaTecnologiaGrupo
     */
    public List<MaTecnologiaGrupo> getListaTecnologiaGrupo() {
        return listaTecnologiaGrupo;
    }

    /**
     * @param listaTecnologiaGrupo the listaTecnologiaGrupo to set
     */
    public void setListaTecnologiaGrupo(List<MaTecnologiaGrupo> listaTecnologiaGrupo) {
        this.listaTecnologiaGrupo = listaTecnologiaGrupo;
    }

    /**
     * @return the listaTecnologiaGrupoBorrar
     */
    public List<MaTecnologiaGrupo> getListaTecnologiaGrupoBorrar() {
        return listaTecnologiaGrupoBorrar;
    }

    /**
     * @param listaTecnologiaGrupoBorrar the listaTecnologiaGrupoBorrar to set
     */
    public void setListaTecnologiaGrupoBorrar(List<MaTecnologiaGrupo> listaTecnologiaGrupoBorrar) {
        this.listaTecnologiaGrupoBorrar = listaTecnologiaGrupoBorrar;
    }

    /**
     * @return the objetoGrupo
     */
    public MaTecnologiaGrupo getObjetoGrupo() {
        return objetoGrupo;
    }

    /**
     * @param objetoGrupo the objetoGrupo to set
     */
    public void setObjetoGrupo(MaTecnologiaGrupo objetoGrupo) {
        this.objetoGrupo = objetoGrupo;
    }

    /**
     * @return the listaCoberturas
     */
    public List<Maestro> getListaCoberturas() {
        return listaCoberturas;
    }

    /**
     * @param listaCoberturas the listaCoberturas to set
     */
    public void setListaCoberturas(List<Maestro> listaCoberturas) {
        this.listaCoberturas = listaCoberturas;
    }

    /**
     * @return the hashCoberturas
     */
    public HashMap<Integer, Maestro> getHashCoberturas() {
        return hashCoberturas;
    }

    /**
     * @param hashCoberturas the hashCoberturas to set
     */
    public void setHashCoberturas(HashMap<Integer, Maestro> hashCoberturas) {
        this.hashCoberturas = hashCoberturas;
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
