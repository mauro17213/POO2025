/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.dominio.solicitud.GsZonaUsuario;
import com.saviasaludeps.savia.web.solicitud.servicio.GsZonaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jramirez
 */
@ManagedBean
@ViewScoped
public class GsZonaBean extends Url {

    @Autowired
    private GsZonaServicioIface gsZonaServicio;
    private GsZona objeto;
    private List<GsZona> registros;
    private LazyDataModel<GsZona> lazyRegistros;
    private GsZonaUsuario zonaUsuario;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;

    private List<Usuario> usuarios;

    public GsZonaBean() {
        this.objeto = new GsZona();
        Modulo _mod = super.validarModulo(Modulo.ID_SOLICITUD_ZONAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/intrasavia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyRegistros = new LazyDataModel<GsZona>() {

                private List<GsZona> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GsZona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(GsZona objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GsZona getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GsZona objeto : lista) {
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
        getGsZonaServicio().cargaInicial(this);
        listar();
    }

    public GsZonaServicioIface getGsZonaServicio() {
        return gsZonaServicio;
    }

    public void setGsZonaServicio(GsZonaServicioIface gsZonaServicio) {
        this.gsZonaServicio = gsZonaServicio;
    }

    public GsZona getObjeto() {
        return objeto;
    }

    public void setObjeto(GsZona objeto) {
        this.objeto = objeto;
    }

    public List<GsZona> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GsZona> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GsZona> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<GsZona> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public GsZonaUsuario getZonaUsuario() {
        return zonaUsuario;
    }

    public void setZonaUsuario(GsZonaUsuario zonaUsuario) {
        this.zonaUsuario = zonaUsuario;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGsZonaServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getGsZonaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getGsZonaServicio().Accion(this);
        //RequestContext.getCurrentInstance().reset("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getGsZonaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getGsZonaServicio().Accion(this);
        //RequestContext.getCurrentInstance().reset("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getGsZonaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getGsZonaServicio().Accion(this);
        procesoFinal();
    }

    public void listarUsuarios(int id) {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getObjeto().setId(id);
//        objeto = getGsZonaServicio().consultar(id);
        getGsZonaServicio().Accion(this);
        //RequestContext.getCurrentInstance().reset("frmZonaUsuarios");
        PrimeFaces.current().ajax().update("frmZonaUsuarios");
        PrimeFaces.current().executeScript("PF('dialogoZonaUsuarios').show()");
        //procesoFinal();
    }

    public void crearUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getGsZonaServicio().Accion(this);
        //RequestContext.getCurrentInstance().reset("frmZonaUsuarioCrear:panelZonaUsuarioCrear");
        PrimeFaces.current().ajax().update("frmZonaUsuarioCrear:panelZonaUsuarioCrear");
        PrimeFaces.current().executeScript("PF('dialogoZonaUsuarioCrear').show()");
        procesoFinal();
    }

    public void guardarUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getGsZonaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoZonaUsuarioCrear').hide();");
            //RequestContext.getCurrentInstance().reset("frmZonaUsuarios");
            PrimeFaces.current().ajax().update("frmZonaUsuarios");
        } else {
            procesoFinal();
        }
    }

    public void editarUsuario(int id) {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        setZonaUsuario(new GsZonaUsuario(id));
        getGsZonaServicio().Accion(this);
        //RequestContext.getCurrentInstance().reset("frmZonaUsuarioEditar:panelZonaUsuarioEditar");
        PrimeFaces.current().ajax().update("frmZonaUsuarioEditar:panelZonaUsuarioEditar");
        PrimeFaces.current().executeScript("PF('dialogoZonaUsuarioEditar').show()");
        procesoFinal();
    }

    public void modificarUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getGsZonaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoZonaUsuarioEditar').hide();");
        }
        procesoFinal();
    }

    public void borrarUsuario(int id) {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_BORRAR);
        setZonaUsuario(new GsZonaUsuario(id));
        getGsZonaServicio().Accion(this);
        //RequestContext.getCurrentInstance().reset("frmZonaUsuarios");
        PrimeFaces.current().ajax().update("frmZonaUsuarios");
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
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
                    PrimeFaces.current().ajax().update("frmZonas");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                        case Url.ACCION_MODIFICAR:
                        case Url.ACCION_CREAR:
                        case Url.ACCION_EDITAR:
                            //RequestContext.getCurrentInstance().reset("frmZonaUsuarios:tablaRegistros");
                            PrimeFaces.current().ajax().update("frmZonaUsuarios:tablaRegistros");
                            crearLog(getAccionStr(getDoAccion()) + " ZonaUsuario", getZonaUsuario().toString());
                            break;
                        case Url.ACCION_LISTAR:
                            crearLog(getAccionStr(getDoAccion()) + " ZonaUsuario", getZonaUsuario().toString());
                            break;
                        case Url.ACCION_BORRAR:
                            crearLog(getAccionStr(getDoAccion()) + " ZonaUsuario", getZonaUsuario().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = hashUbicaciones.get(id);
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setUbicacion(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public List<Usuario> completarUsuario(String query) {
        getGsZonaServicio().consultaUsuariosCarga(this, query);
        return getUsuarios();
    }

    public String fechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerMunicipioDepartamento(int id) {
        try {
            String municipio = obtenerMunicipio(id);
            String departamento = obtenerDepartamento(id);
            return municipio + " - " + departamento;
        } catch (Exception e) {
            return "";
        }
    }
}
