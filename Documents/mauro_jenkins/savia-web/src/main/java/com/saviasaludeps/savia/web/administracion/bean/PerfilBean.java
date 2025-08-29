/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.administracion.Permiso;
import com.saviasaludeps.savia.web.administracion.servicio.PerfilServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import com.saviasaludeps.savia.web.administracion.servicio.PerfilServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class PerfilBean extends Url {

    private PerfilServicioIface perfilServicio;
    private Perfil objeto;
    private List<Perfil> registros;
    private LazyDataModel<Perfil> lazyPerfil;

    private List<Permiso> listaPermisos;
    private Permiso objetoPermiso;

    public PerfilBean() {
        this.objeto = new Perfil();
        this.objetoPermiso = new Permiso();
        Modulo _mod = super.validarModulo(Modulo.ID_PERFILES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyPerfil = new LazyDataModel<Perfil>() {

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }
                private List<Perfil> lista;

                @Override
                public List<Perfil> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(Perfil objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Perfil getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Perfil objeto : lista) {
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
        listar();
    }

    public PerfilServicioIface getPerfilServicio() {
        if (perfilServicio == null) {
            perfilServicio = new PerfilServicioImpl();
        }
        return perfilServicio;
    }

    public void setPerfilServicio(PerfilServicioIface perfilServicio) {
        this.perfilServicio = perfilServicio;
    }

    public Perfil getObjeto() {
        return objeto;
    }

    public void setObjeto(Perfil objeto) {
        this.objeto = objeto;
    }

    public Permiso getObjetoPermiso() {
        return objetoPermiso;
    }

    public void setObjetoPermiso(Permiso objetoPermiso) {
        this.objetoPermiso = objetoPermiso;
    }

    public List<Perfil> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Perfil> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Perfil> getLazyPerfil() {
        return lazyPerfil;
    }

    public void setLazyPerfil(LazyDataModel<Perfil> lazyPerfil) {
        this.lazyPerfil = lazyPerfil;
    }

    public List<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getPerfilServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getPerfilServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getPerfilServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getPerfilServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getPerfilServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getPerfilServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getPerfilServicio().Accion(this);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmPerfiles");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    if (getDoAccion() == ACCION_EDITAR) {
                        crearLog("Editar Permisos", getObjetoPermiso().toString());
                    } else if (getDoAccion() == ACCION_MODIFICAR) {
                        crearLog("Modificar Permisos", getObjetoPermiso().toString());
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public void editarPermisos(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        getPerfilServicio().Accion(this);
//        PrimeFaces.current().resetInputs("frmEditarPermisos:panelEditarPermisos");
        PrimeFaces.current().ajax().update("frmEditarPermisos:panelEditarPermisos");
        PrimeFaces.current().executeScript("PF('dialogoEditarPermisos').show()");
        procesoFinal();
    }

    public void modificarPermiso(RowEditEvent event) {
        setObjetoPermiso((Permiso) event.getObject());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getPerfilServicio().Accion(this);
        generarMensajes();
    }

}
