/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.judicial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.web.judicial.servicio.GjAbogadoServicioIface;

import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;

//import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.SortMeta;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author bsgomez
 */
@ManagedBean
@ViewScoped
public class GjAbogadoBean extends Url {

    private GjAbogadoServicioIface gjAbogadoServicio;
    private GjAbogado objeto;
    private List<GjAbogado> registros;
    private LazyDataModel<GjAbogado> lazyAbogado;
//    @Autowired
//    private UbicacionSingle ubicacionSingle;

    private List<Integer> seleccionRoles = new ArrayList();

    private List<Maestro> maestros;

    //Listas Auxiliares
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaTipoEstadoPersona;
    private HashMap<Integer, Maestro> hashTipoEstadoPersona;
    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashListaUsuarios;

    private boolean abogadoRegistradoEnSistema = false;

    public GjAbogadoBean() {
        this.objeto = new GjAbogado();
        Modulo _mod = super.validarModulo(Modulo.ID_GJ_ABOGADOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);

            lazyAbogado = new LazyDataModel<GjAbogado>() {

                private List<GjAbogado> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GjAbogado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return lista;
                }

                @Override
                public String getRowKey(GjAbogado objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GjAbogado getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GjAbogado objeto : lista) {
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
        getAbogadoServicio().cargasInicial(this);
        listar();
    }

    public GjAbogadoServicioIface getAbogadoServicio() {
        return gjAbogadoServicio;
    }

    public LazyDataModel<GjAbogado> getLazyAbogado() {
        return lazyAbogado;
    }

    public void setLazyAbogado(LazyDataModel<GjAbogado> lazyAbogado) {
        this.lazyAbogado = lazyAbogado;
    }

    public GjAbogado getObjeto() {
        return objeto;
    }

    public void setObjeto(GjAbogado objeto) {
        this.objeto = objeto;
    }

    public List<GjAbogado> getRegistros() {

        return registros;
    }

    public void setRegistros(List<GjAbogado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GjAbogado> getLazyUsuario() {
        return lazyAbogado;
    }

    public void setLazyUsuario(LazyDataModel<GjAbogado> lazyAbogado) {
        this.lazyAbogado = lazyAbogado;
    }

//    public UbicacionSingle getUbicacionSingle() {
//        return ubicacionSingle;
//    }
//
//    public void setUbicacionSingle(UbicacionSingle ubicacionSingle) {
//        this.ubicacionSingle = ubicacionSingle;
//    }
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

    public GjAbogadoServicioIface getGjAbogadoServicio() {
        return gjAbogadoServicio;
    }

    public void setGjAbogadoServicio(GjAbogadoServicioIface gjAbogadoServicio) {
        this.gjAbogadoServicio = gjAbogadoServicio;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadoPersona() {
        return hashTipoEstadoPersona;
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public void setHashTipoEstadoPersona(HashMap<Integer, Maestro> hashTipoEstadoPersona) {
        this.hashTipoEstadoPersona = hashTipoEstadoPersona;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public HashMap<Integer, Usuario> getHashListaUsuarios() {
        return hashListaUsuarios;
    }

    public void setHashListaUsuarios(HashMap<Integer, Usuario> hashListaUsuarios) {
        this.hashListaUsuarios = hashListaUsuarios;
    }

    public String getUsuario(int id) {
        try {
            return hashListaUsuarios.get(id).getNombre() + " " + hashListaUsuarios.get(id).getUsuario();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isAbogadoRegistradoEnSistema() {
        return abogadoRegistradoEnSistema;
    }

    public void setAbogadoRegistradoEnSistema(boolean abogadoRegistradoEnSistema) {
        this.abogadoRegistradoEnSistema = abogadoRegistradoEnSistema;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGjAbogadoServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getAbogadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAbogadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");

        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        //consultar si el documento ingresado no ha sido asignado 
        getAbogadoServicio().consultarAbogado(this);
        //si esta libre deja insertar d elo contrario no podra ingresar
        if (objeto.getGnUsuario().getId() == null) {
            addError("El campo Nombre Abogado es obligatorio");
        }
        if (isAbogadoRegistradoEnSistema() == true) {
            addError("Ya existe un abogado en el sistema");
        }
        if (!super.isError()) {
            getAbogadoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
            }
        }

        procesoFinal();

    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getAbogadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAbogadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getAbogadoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmAbogados");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

//    public void buscarGnUsuarioEnSistema() {
//
//        if (this.getObjeto().getNombre() != null) {
//            try {
//                //REFRESCAR BÚSQUEDA DE PERSONA
//
//                this.getObjeto().getGnUsuario().setId(0);
//
//
//                //Consultar afiliado en BD
//                getGjAbogadoServicio().consultarAbogadoAfiliado(this);
//
//                asignacionResultadosConsultaParaBeanCaso();
//                if (!this.getObjeto().exitePersona()) {
//
//                    //Consultar persona en BD
//                    getGjAbogadoServicio().consultarPersona(this);
//                    if (this.getErrores().size() > 0) {
//                        this.addError(this.getErrores().get(0));
//                        this.getErrores().clear();
//                    }
//                    asignacionResultadosConsultaParaBeanCaso();
//                }
//
//                if (!this.getObjeto().getGjPersona().exitePersona() && !existePersonaAfiliada()) {//aca esta saltando a la excepcion
//                    String documento = this.getObjeto().getGjPersona().getDocumento();
//                    int tipoDoc = this.getObjeto().getGjPersona().getMaeTipoDocumentoId();
//                    this.getObjeto().setGjPersona(new GjAbogado());
//                    this.getObjeto().getGjPersona().setMaeTipoDocumentoId(tipoDoc);
//                    this.getObjeto().getGjPersona().setDocumento(documento);
////                    this.getObjeto().getGjPersona().setMaeEstadoAfiliadoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.ESTADO_PERSONA_DEFECTO_CREAR)));
//
//                    //this.listaPersonaTelefono = new ArrayList<>();
//                }
//
//                PrimeFaces.current().ajax().update("frmCrear:panelCrear");
//                PrimeFaces.current().ajax().update("frmEditar:panelEditar");
//                //PrimeFaces.current().ajax().update("frmCrear:telefonos");
//                //PrimeFaces.current().ajax().update("frmEditar");
//
//                generarMensajes();
//            } catch (Exception ex) {
//                Logger.getLogger(AusCasoBean.class.getName()).log(Level.SEVERE, null, ex);
//                this.addError(ex.getMessage());
//                generarMensajes();
//            }
//        }
//    }
    public List<Usuario> completarUsuarios(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        for (Usuario user : getListaUsuarios()) {
            if (user.getNombre().toLowerCase().contains(query.toLowerCase())) {
                usuariosFiltrado.add(user);
            }
        }
        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getObjeto().getGnUsuario().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    private void asignacionResultadosConsultaParaBeanCaso() {
        this.getObjeto().setGnUsuario(this.getObjeto().getGnUsuario());//aca debe traerme toda la informacion de la persona consultada
        //this.listaPersonaTelefono = personaBean.get ListaausPersonaTelefono();
        //this.listaPersonaTelefono = personaBean.getObjeto().getListaTelefonos();
    }

    private boolean existePersonaAfiliada() {
        return this.getObjeto().getEsAfiliado();
    }

    public void validarDocTar() {
        this.getObjeto().getDocumento();
        this.getObjeto().getTarjetaProfecional();

        if ((this.getObjeto().getDocumento()).equals(this.getObjeto().getTarjetaProfecional())) {

            this.getObjeto().setTarjetaProfecional("");
            PrimeFaces.current().ajax().update("frmCrear:panelCrear");
            PrimeFaces.current().ajax().update("frmEditar:panelEditar");
            FacesContext.getCurrentInstance().addMessage("Alerta", new FacesMessage("El Numero De Documento Y La Tarjeta Profecional No pueden Ser Iguales"));

        }

    }

}
