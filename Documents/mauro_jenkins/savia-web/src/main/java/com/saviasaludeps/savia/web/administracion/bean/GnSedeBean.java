/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.web.administracion.servicio.GnSedeServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
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
 * @author acuartas
 */
@ManagedBean
@ViewScoped
public class GnSedeBean extends Url {

    @Autowired
    private GnSedeServicioIface gnSedeServicio;
    private GnSede objeto;
    private List<GnSede> registros;
    private LazyDataModel<GnSede> lazySedes;

    private List<Ubicacion> listaUbicaciones;
    private List<Usuario> listaUsuarios;
    private List<Maestro> listaTiposSede;
    private HashMap<Integer, Maestro> hashTiposSede;
    private HashMap<Integer, Empresa> hashEmpresas;
    private HashMap<Integer, Ubicacion> hashUbicaciones;

    public GnSedeBean() {
        this.objeto = new GnSede();
        Modulo _mod = super.validarModulo(Modulo.ID_GN_SEDES);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazySedes = new LazyDataModel<GnSede>() {

                private List<GnSede> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GnSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros
                ) {
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
                public String getRowKey(GnSede objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GnSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GnSede objeto : lista) {
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
        getGnSedeServicio().cargasInicial(this);
        listar();
    }

    public GnSedeServicioIface getGnSedeServicio() {
        return gnSedeServicio;
    }

    public void setGnSedeServicio(GnSedeServicioIface gnSedeServicio) {
        this.gnSedeServicio = gnSedeServicio;
    }

    public GnSede getObjeto() {
        return objeto;
    }

    public void setObjeto(GnSede objeto) {
        this.objeto = objeto;
    }

    public List<GnSede> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GnSede> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GnSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<GnSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Maestro> getListaTiposSede() {
        return listaTiposSede;
    }

    public void setListaTiposSede(List<Maestro> listaTiposSede) {
        this.listaTiposSede = listaTiposSede;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public HashMap<Integer, Maestro> getHashTiposSede() {
        return hashTiposSede;
    }

    public void setHashTiposSede(HashMap<Integer, Maestro> hashTiposSede) {
        this.hashTiposSede = hashTiposSede;
    }

    public HashMap<Integer, Empresa> getHashEmpresas() {
        return hashEmpresas;
    }

    public void setHashEmpresas(HashMap<Integer, Empresa> hashEmpresas) {
        this.hashEmpresas = hashEmpresas;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGnSedeServicio().Accion(this);
    }

    public void ver(int id) {
        setObjeto(new GnSede(id));
        super.setAccion(Url.ACCION_VER);
        getGnSedeServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getGnSedeServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getGnSedeServicio().Accion(this);
        procesoFinal();
    }

    public void editar(int id) {
        setObjeto(new GnSede(id));
        super.setAccion(Url.ACCION_EDITAR);
        getGnSedeServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(Url.ACCION_MODIFICAR);
        getGnSedeServicio().Accion(this);
        procesoFinal();
    }
    

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : getListaUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setGnUbicacionId(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
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

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmSedes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmSedes");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmSedes");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
                    crearLog(getObjeto().toString());
                    break;
               
            }
        }
        generarMensajes();
    }

}
