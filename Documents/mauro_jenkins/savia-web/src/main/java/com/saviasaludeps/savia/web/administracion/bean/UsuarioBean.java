/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.administracion.UsuarioRol;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.administracion.servicio.UsuarioServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic SaviaSalud EPS
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends Url {

    private UsuarioServicioIface usuarioServicio;
    private Usuario objeto;
    private List<Usuario> registros;
    private LazyDataModel<Usuario> lazyUsuario;

    private List<Rol> listaRoles;
    private List<Integer> seleccionRoles = new ArrayList();

    private List<Maestro> maestros;

    //Listas Auxiliares
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaAreas;
    private HashMap<Integer, Maestro> hashAreas;
    private List<Maestro> listaCargos;
    private HashMap<Integer, Maestro> hashCargos;

    public UsuarioBean() {
        this.objeto = new Usuario();
        Modulo _mod = super.validarModulo(Modulo.ID_USUARIOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyUsuario = new LazyDataModel<Usuario>() {

                private List<Usuario> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(Usuario objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Usuario getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Usuario objeto : lista) {
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
        getUsuarioServicio().cargasInicial(this);
        listar();
    }

    public UsuarioServicioIface getUsuarioServicio() {
        return usuarioServicio;
    }

    public void setUsuarioServicio(UsuarioServicioIface usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        seleccionRoles = new ArrayList();
        if (objeto != null && objeto.getListaRoles() != null && !objeto.getListaRoles().isEmpty()) {
            for (UsuarioRol rol : objeto.getListaRoles()) {
                seleccionRoles.add(rol.getRol().getId());
            }
        }
        this.objeto = objeto;
    }

    public List<Usuario> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Usuario> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Usuario> getLazyUsuario() {
        return lazyUsuario;
    }

    public void setLazyUsuario(LazyDataModel<Usuario> lazyUsuario) {
        this.lazyUsuario = lazyUsuario;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Integer> getSeleccionRoles() {
        return seleccionRoles;
    }

    public void setSeleccionRoles(List<Integer> seleccionRoles) {
        this.seleccionRoles = seleccionRoles;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public List<Maestro> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Maestro> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public HashMap<Integer, Maestro> getHashAreas() {
        return hashAreas;
    }

    public void setHashAreas(HashMap<Integer, Maestro> hashAreas) {
        this.hashAreas = hashAreas;
    }

    public List<Maestro> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Maestro> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public HashMap<Integer, Maestro> getHashCargos() {
        return hashCargos;
    }

    public void setHashCargos(HashMap<Integer, Maestro> hashCargos) {
        this.hashCargos = hashCargos;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getUsuarioServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getUsuarioServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getUsuarioServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getUsuarioServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getUsuarioServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getUsuarioServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void modificarSegmentado() {
        super.setAccion(ACCION_MODIFICAR);
        getUsuarioServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarSegmentado').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getUsuarioServicio().Accion(this);
        procesoFinal();
    }

    public void restaurarContrasena(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        getUsuarioServicio().Accion(this);
        procesoFinal();
    }

    public void desbloquearUsuario(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_2);
        getUsuarioServicio().Accion(this);
        procesoFinal();
    }

    public boolean validarBloqueo(Boolean bloqueo) {
        if (bloqueo != null) {
            return bloqueo;
        } else {
            return false;
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmUsuarios");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    crearLog("Restauración de Contraseña", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmUsuarios");
                    break;
                case Url.ACCION_ADICIONAL_2:
                    crearLog("Desbloquear usuario", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmUsuarios");
                    break;
            }
        }
        generarMensajes();
    }
}
