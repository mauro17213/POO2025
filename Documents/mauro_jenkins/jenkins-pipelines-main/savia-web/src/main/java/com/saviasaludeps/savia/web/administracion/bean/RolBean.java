/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.administracion.RolPerfil;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.administracion.servicio.RolServicioIface;
import com.saviasaludeps.savia.web.administracion.servicio.RolServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
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
public class RolBean extends Url {

    private RolServicioIface rolServicio;
    private Rol objeto;
    private List<Rol> registros;
    private LazyDataModel<Rol> lazyRol;
    private List<Perfil> perfilesList;
    private List<Integer> selectedPerfiles = new ArrayList();
    private List<Maestro> maestros;

    public RolBean() {
        this.objeto = new Rol();
        Modulo _mod = super.validarModulo(Modulo.ID_ROL);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyRol = new LazyDataModel<Rol>() {

                private List<Rol> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<Rol> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(Rol objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Rol getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Rol objeto : lista) {
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

    public RolServicioIface getRolServicio() {
        if (rolServicio == null) {
            rolServicio = new RolServicioImpl();
        }
        return rolServicio;
    }

    public void setRolServicio(RolServicioIface rolServicio) {
        this.rolServicio = rolServicio;
    }

    public Rol getObjeto() {
        return objeto;
    }

    public void setObjeto(Rol objeto) {
        selectedPerfiles = new ArrayList();
        if (objeto != null && objeto.getRolesPerfilesList() != null && objeto.getRolesPerfilesList().size() > 0) {
            for (RolPerfil perfil : objeto.getRolesPerfilesList()) {
                selectedPerfiles.add(perfil.getPerfiles().getId());
            }
        }
        this.objeto = objeto;
    }

    public List<Rol> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Rol> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Rol> getLazyRol() {
        return lazyRol;
    }

    public void setLazyRol(LazyDataModel<Rol> lazyRol) {
        this.lazyRol = lazyRol;
    }

    public List<Perfil> getPerfilesList() {
        return perfilesList;
    }

    public void setPerfilesList(List<Perfil> perfilesList) {
        this.perfilesList = perfilesList;
    }

    public String getPerfilesNombre(List<RolPerfil> perfiles) {
        String per = "";
        if (perfiles != null) {
            for (RolPerfil rp : perfiles) {
                per += rp.getPerfiles().getNombre() + ", ";
            }
        }
        return per;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public List<Integer> getSelectedPerfiles() {
        return selectedPerfiles;
    }

    public void setSelectedPerfiles(List<Integer> selectedPerfiles) {
        this.selectedPerfiles = selectedPerfiles;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getRolServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getRolServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getRolServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getRolServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getRolServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getRolServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getRolServicio().Accion(this);
    }

    public void restaurarContrasena(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        getRolServicio().Accion(this);
        crearLog("Restauración de Contraseña", getObjeto().toString());
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRols");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    crearLog("Restauración de Contraseña", getObjeto().toString());
                    break;
            }
            refrescar();
        }
        generarMensajes();
    }

}
