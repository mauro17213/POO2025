/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Destino;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Zona;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioIface;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Stiven Giraldo
 */
@Named
@ViewScoped
public class AuZonaBean extends Url {

    @Autowired
    private AuZonaServicioIface auZonaServicio;
    private AuAnexo4Zona objeto;
    private AuAnexo4Destino objetoDestino;
    private List<AuAnexo4Zona> registros;
    private LazyDataModel<AuAnexo4Zona> lazyZonas;

    //Variables auxiliares
    private List<Ubicacion> listaMunicipiosCobertura;
    private List<Ubicacion> listaMunicipios;
    private HashMap<Integer, Ubicacion> hashMunicipios;
    private List<AuAnexo4Destino> listaDestinosEliminar;

    public AuZonaBean() {
        this.objeto = new AuAnexo4Zona();
        this.objetoDestino = new AuAnexo4Destino();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_ZONAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId() == 1 ? null : super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyZonas = new LazyDataModel<AuAnexo4Zona>() {

                private List<AuAnexo4Zona> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo4Zona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AuAnexo4Zona objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuAnexo4Zona getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo4Zona objeto : lista) {
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
        getAuZonaServicio().cargaInicial(this);
        listar();
    }

    public AuZonaServicioIface getAuZonaServicio() {
        if (auZonaServicio == null) {
            auZonaServicio = new AuZonaServicioImpl();
        }
        return auZonaServicio;
    }

    public void setAuZonaServicio(AuZonaServicioIface auZonaServicio) {
        this.auZonaServicio = auZonaServicio;
    }

    public AuAnexo4Zona getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo4Zona objeto) {
        this.objeto = objeto;
    }

    public AuAnexo4Destino getObjetoDestino() {
        return objetoDestino;
    }

    public void setObjetoDestino(AuAnexo4Destino objetoDestino) {
        this.objetoDestino = objetoDestino;
    }

    public List<AuAnexo4Zona> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo4Zona> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo4Zona> getLazyZonas() {
        return lazyZonas;
    }

    public void setLazyZonas(LazyDataModel<AuAnexo4Zona> lazyZonas) {
        this.lazyZonas = lazyZonas;
    }

    public List<Ubicacion> getListaMunicipiosCobertura() {
        return listaMunicipiosCobertura;
    }

    public void setListaMunicipiosCobertura(List<Ubicacion> listaMunicipiosCobertura) {
        this.listaMunicipiosCobertura = listaMunicipiosCobertura;
    }

    public List<Ubicacion> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<Ubicacion> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public HashMap<Integer, Ubicacion> getHashMunicipios() {
        return hashMunicipios;
    }

    public void setHashMunicipios(HashMap<Integer, Ubicacion> hashMunicipios) {
        this.hashMunicipios = hashMunicipios;
    }

    public List<AuAnexo4Destino> getListaDestinosEliminar() {
        return listaDestinosEliminar;
    }

    public void setListaDestinosEliminar(List<AuAnexo4Destino> listaDestinosEliminar) {
        this.listaDestinosEliminar = listaDestinosEliminar;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuZonaServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAuZonaServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int idZona) {
        getObjeto().setId(idZona);
        super.setAccion(Url.ACCION_VER);
        getAuZonaServicio().Accion(this);
        procesoFinal();
    }

    public void editar(int idZona) {
        getObjeto().setId(idZona);
        setListaDestinosEliminar(new ArrayList());
        super.setAccion(ACCION_EDITAR);
        getAuZonaServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getAuZonaServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAuZonaServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoZonaCrear').hide()");
                    PrimeFaces.current().ajax().update("frmZonas");
                    crearLog("Guardar", getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().executeScript("PF('dialogoZonaEditar').hide()");
                    PrimeFaces.current().ajax().update("frmZonas");
                    crearLog("Modificar", getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmZonaVer");
                    PrimeFaces.current().executeScript("PF('dialogoZonaVer').show()");
                    crearLog("Ver", getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmZonaCrear");
                    PrimeFaces.current().executeScript("PF('dialogoZonaCrear').show()");
                    crearLog("Crear", "Se inicializa la creación de una zona");
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmZonaEditar");
                    PrimeFaces.current().executeScript("PF('dialogoZonaEditar').show()");
                    crearLog("Editar", getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("auZonaBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("auZonaBean");
    }

    public void agregarValorUbicacion() {
        if (getObjeto().getUbicacionId() > 0) {
            Ubicacion ubicacion = obtenerMaestroUbicacion(getObjeto().getUbicacionId());
            if (ubicacion != null) {
                getObjeto().setUbicacionValor(ubicacion.getNombre() + " - " + ubicacion.getUbicacionPadre().getNombre());
            } else {
                addError("No se encontro la ubicación");
                generarMensajes();
            }
        } else {
            addError("No se ha seleccionado una ubicación valida");
            generarMensajes();
        }
    }

    public Ubicacion obtenerMaestroUbicacion(int id) {
        try {
            return hashMunicipios.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public String obtenerBoolean(boolean valor) {
        if (valor) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void subirOrden(AuAnexo4Destino destino) {
        int orden = destino.getOrden();
        int ordenAnterior = orden - 1;
        List<AuAnexo4Destino> nueva = new ArrayList();
        AuAnexo4Destino destinoAnterior = null;
        for (AuAnexo4Destino des : getObjeto().getAuAnexo4DestinoList()) {
            if (des.getOrden() == ordenAnterior) {
                des.setOrden(des.getOrden() + 1);
                destinoAnterior = des;
                continue;
            }
            if (des.getOrden() == orden) {
                des.setOrden(ordenAnterior);
                nueva.add(des);
                if (destinoAnterior != null) {
                    nueva.add(destinoAnterior);
                    destinoAnterior = null;
                }
                continue;
            }
            nueva.add(des);
        }
        getObjeto().setAuAnexo4DestinoList(nueva);
        PrimeFaces.current().ajax().update("frmZonaCrear:pListaDestinoCrear");
        PrimeFaces.current().ajax().update("frmZonaEditar:pListaDestinoEditar");
    }

    public void bajarOrden(AuAnexo4Destino destino) {
        int orden = destino.getOrden();
        int ordenSiguiente = orden + 1;
        List<AuAnexo4Destino> nueva = new ArrayList();
        AuAnexo4Destino destinoSiguiente = null;
        for (AuAnexo4Destino des : getObjeto().getAuAnexo4DestinoList()) {
            if (des.getOrden() == orden) {
                des.setOrden(des.getOrden() + 1);
                destinoSiguiente = des;
                continue;
            }
            if (des.getOrden() == ordenSiguiente) {
                des.setOrden(orden);
                nueva.add(des);
                if (destinoSiguiente != null) {
                    nueva.add(destinoSiguiente);
                    destinoSiguiente = null;
                }
                continue;
            }
            nueva.add(des);
        }
        getObjeto().setAuAnexo4DestinoList(nueva);
        PrimeFaces.current().ajax().update("frmZonaCrear:pListaDestinoCrear");
        PrimeFaces.current().ajax().update("frmZonaEditar:pListaDestinoEditar");
    }

    public void eliminarDestino(AuAnexo4Destino destino) {
        List<AuAnexo4Destino> nueva = new ArrayList();
        for (AuAnexo4Destino des : getObjeto().getAuAnexo4DestinoList()) {
            if (des.getOrden() == destino.getOrden()) {
                continue;
            }
            if (des.getOrden() > destino.getOrden()) {
                des.setOrden(des.getOrden() - 1);
            }
            nueva.add(des);
        }
        getObjeto().setAuAnexo4DestinoList(nueva);
        if (destino.getId() != null) {
            getListaDestinosEliminar().add(destino);
        }
        PrimeFaces.current().ajax().update("frmZonaCrear:pListaDestinoCrear");
        PrimeFaces.current().ajax().update("frmZonaEditar:pListaDestinoEditar");
    }

    public void agregarValorUbicacionDestino() {
        if (getObjeto().getUbicacionId() > 0) {
            Ubicacion ubicacion = obtenerMaestroUbicacion(getObjetoDestino().getUbicacionId());
            if (ubicacion != null) {
                getObjetoDestino().setUbicacionValor(ubicacion.getNombre() + " - " + ubicacion.getUbicacionPadre().getNombre());
            } else {
                addError("No se encontro la ubicación");
                generarMensajes();
            }
        } else {
            addError("No se ha seleccionado una ubicación valida");
            generarMensajes();
        }
    }

    public void agregarDestino() {
        getObjetoDestino().setActivo(true);
        boolean agregar = true;
        if (getObjeto().getAuAnexo4DestinoList() != null) {
            getObjetoDestino().setAuAnexo3ZonaId(getObjeto());
            getObjetoDestino().setOrden(getObjeto().getAuAnexo4DestinoList().size() + 1);
            for (AuAnexo4Destino destino : getObjeto().getAuAnexo4DestinoList()) {
                if (destino.getUbicacionId() == getObjetoDestino().getUbicacionId()) {
                    agregar = false;
                    addError("Ya se agrego esa ubicación");
                }
            }
        } else {
            getObjeto().setAuAnexo4DestinoList(new ArrayList());
            getObjetoDestino().setOrden(1);
        }
        if (agregar) {
            getObjeto().getAuAnexo4DestinoList().add(getObjetoDestino());
            getObjeto().setOrdenMaximo(getObjeto().getAuAnexo4DestinoList().size());
            setObjetoDestino(new AuAnexo4Destino());
            PrimeFaces.current().ajax().update("frmZonaCrear:pDestinoCrear");
            PrimeFaces.current().ajax().update("frmZonaCrear:pListaDestinoCrear");
            PrimeFaces.current().ajax().update("frmZonaEditar:pDestinoEditar");
            PrimeFaces.current().ajax().update("frmZonaEditar:pListaDestinoEditar");
        } else {
            setObjetoDestino(new AuAnexo4Destino());
            PrimeFaces.current().ajax().update("frmZonaCrear:pDestinoCrear");
            PrimeFaces.current().ajax().update("frmZonaEditar:pDestinoEditar");
            generarMensajes();
        }

    }

}
