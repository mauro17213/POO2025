package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.dominio.juridico.CntjTerceroUt;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorBean;
import com.saviasaludeps.savia.web.juridico.servicio.TerceroServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
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

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class TerceroBean extends Url{
    
    private TerceroServicioIface cntjTerceroServicio;
    
    private CntjTercero objeto;
    private List<Maestro> listaTipoTercero;
    private List<Usuario> listaUsuario;
    private LazyDataModel<Usuario> lazyUsuarios;
    private SelPrestadorBean selPrestadorBean;
    private LazyDataModel<CntjTerceroUt> lazyTerceroUt;
    
    private LazyDataModel<CntjTercero> lazyTercero;
    private List<CntjTercero> registro;
    private List<Ubicacion> listaMunicipios;
    private HashMap<Integer,Ubicacion> hashListaMunicipios;
    private boolean habilitadoCampoDireccion = false;
    private boolean habilitadoSelecUsuario = false;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaNaturaleza;
    private List<Maestro> listaTiposDocumentoRepLegal;
    private HashMap<Integer, Maestro> hashTiposDocumentoRepLegal;
    private boolean optCrear=false;
    private List<Maestro> listaTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposDocumentoPersona;
    private List<CntjTercero> listaTercerosUt;
    private HashMap<Integer, CntjTercero> hashlistaTercerosUt;
    private List<Maestro> listaetapaDesignacion;
    private CntjTercero objetoUt;
    private CntjTerceroUt objetoTerceroUt;
    private List<CntjTerceroUt> registrosUnionTemporal;
    private boolean verOpcionGuardar;
    private List<CntjTerceroUt> eliminadosUnionTemporal;

    
    public TerceroBean() {
        this.objeto = new CntjTercero();
        this.objetoTerceroUt = new CntjTerceroUt();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_TERCEROS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            super.addListaParamConsultas(new ParamConsulta());
            lazyTercero = new LazyDataModel<CntjTercero>() {

                private List<CntjTercero> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjTercero> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistro();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CntjTercero objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjTercero getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjTercero objeto : lista) {
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
        getCntjTerceroServicio().cargaInicial(this);
    }
    
    
    //getters and setters
    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">

    public List<CntjTerceroUt> getEliminadosUnionTemporal() {
        return eliminadosUnionTemporal;
    }

    public void setEliminadosUnionTemporal(List<CntjTerceroUt> eliminadosUnionTemporal) {
        this.eliminadosUnionTemporal = eliminadosUnionTemporal;
    }

    public boolean isVerOpcionGuardar() {
        return verOpcionGuardar;
    }

    public void setVerOpcionGuardar(boolean verOpcionGuardar) {
        this.verOpcionGuardar = verOpcionGuardar;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoPersona() {
        return hashTiposDocumentoPersona;
    }

    public void setHashTiposDocumentoPersona(HashMap<Integer, Maestro> hashTiposDocumentoPersona) {
        this.hashTiposDocumentoPersona = hashTiposDocumentoPersona;
    }

    public List<CntjTerceroUt> getRegistrosUnionTemporal() {
        return registrosUnionTemporal;
    }

    public void setRegistrosUnionTemporal(List<CntjTerceroUt> registrosUnionTemporal) {
        this.registrosUnionTemporal = registrosUnionTemporal;
    }
    
    public LazyDataModel<CntjTerceroUt> getLazyTerceroUt() {
        return lazyTerceroUt;
    }

    public void setLazyTerceroUt(LazyDataModel<CntjTerceroUt> lazyTerceroUt) {
        this.lazyTerceroUt = lazyTerceroUt;
    }
    
    public CntjTerceroUt getObjetoTerceroUt() {
        return objetoTerceroUt;
    }

    public void setObjetoTerceroUt(CntjTerceroUt objetoTerceroUt) {
        this.objetoTerceroUt = objetoTerceroUt;
    }   
    
    public CntjTercero getObjetoUt() {
        return objetoUt;
    }

    public void setObjetoUt(CntjTercero objetoUt) {
        this.objetoUt = objetoUt;
    }
    
    public List<Maestro> getListaetapaDesignacion() {    
        return listaetapaDesignacion;
    }
    public void setListaetapaDesignacion(List<Maestro> listaetapaDesignacion) {
        this.listaetapaDesignacion = listaetapaDesignacion;
    }    

    public HashMap<Integer, CntjTercero> getHashlistaTercerosUt() {
        return hashlistaTercerosUt;
    }
    
    public void setHashlistaTercerosUt(HashMap<Integer, CntjTercero> hashlistaTercerosUt) {
        this.hashlistaTercerosUt = hashlistaTercerosUt;    
    }

    public List<CntjTercero> getListaTercerosUt() {
        return listaTercerosUt;
    }
    
    public void setListaTercerosUt(List<CntjTercero> listaTercerosUt) {    
        this.listaTercerosUt = listaTercerosUt;
    }

    public TerceroServicioIface getCntjTerceroServicio() {
        return cntjTerceroServicio;
    }

    public void setCntjTerceroServicio(TerceroServicioIface cntjTerceroServicio) {
        this.cntjTerceroServicio = cntjTerceroServicio;
    }

    public CntjTercero getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjTercero objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getListaTipoTercero() {
        return listaTipoTercero;
    }

    public void setListaTipoTercero(List<Maestro> listaTipoTercero) {
        this.listaTipoTercero = listaTipoTercero;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public SelPrestadorBean getSelPrestadorBean() {
        selPrestadorBean = (SelPrestadorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorBean");
        return selPrestadorBean;
    }

    public void setSelPrestadorBean(SelPrestadorBean selPrestadorBean) {
        this.selPrestadorBean = selPrestadorBean;
    }

    public List<Ubicacion> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<Ubicacion> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public HashMap<Integer, Ubicacion> getHashListaMunicipios() {
        return hashListaMunicipios;
    }

    public void setHashListaMunicipios(HashMap<Integer, Ubicacion> hashListaMunicipios) {
        this.hashListaMunicipios = hashListaMunicipios;
    }

    public boolean isHabilitadoCampoDireccion() {
        return habilitadoCampoDireccion;
    }

    public void setHabilitadoCampoDireccion(boolean habilitadoCampoDireccion) {
        this.habilitadoCampoDireccion = habilitadoCampoDireccion;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public boolean isHabilitadoSelecUsuario() {
        return habilitadoSelecUsuario;
    }

    public void setHabilitadoSelecUsuario(boolean habilitadoSelecUsuario) {
        this.habilitadoSelecUsuario = habilitadoSelecUsuario;
    }

    public List<Maestro> getListaNaturaleza() {
        return listaNaturaleza;
    }

    public void setListaNaturaleza(List<Maestro> listaNaturaleza) {
        this.listaNaturaleza = listaNaturaleza;
    }

    public List<Maestro> getListaTiposDocumentoRepLegal() {
        return listaTiposDocumentoRepLegal;
    }

    public void setListaTiposDocumentoRepLegal(List<Maestro> listaTiposDocumentoRepLegal) {
        this.listaTiposDocumentoRepLegal = listaTiposDocumentoRepLegal;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoRepLegal() {
        return hashTiposDocumentoRepLegal;
    }

    public void setHashTiposDocumentoRepLegal(HashMap<Integer, Maestro> hashTiposDocumentoRepLegal) {
        this.hashTiposDocumentoRepLegal = hashTiposDocumentoRepLegal;
    }

    public LazyDataModel<CntjTercero> getLazyTercero() {
        return lazyTercero;
    }

    public void setLazyTercero(LazyDataModel<CntjTercero> lazyTercero) {
        this.lazyTercero = lazyTercero;
    }

    public List<CntjTercero> getRegistro() {
        return registro;
    }

    public void setRegistro(List<CntjTercero> registro) {
        this.registro = registro;
    }

    public boolean isOptCrear() {
        return optCrear;
    }

    public void setOptCrear(boolean optCrear) {
        this.optCrear = optCrear;
    }

    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }
    
    // </editor-fold>

    //Metodos
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCntjTerceroServicio().Accion(this);
    }
    
    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjTerceroServicio().Accion(this);
    }
    
    public void refrescarUnionTemporal() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjTerceroServicio().Accion(this);
    }
    
    public void ver(int idtercero){
        getObjeto().setId(idtercero);
        super.setAccion(ACCION_VER);
        getCntjTerceroServicio().Accion(this);
        procesoFinal();
    }
    
    public void crear(){
        super.setAccion(ACCION_CREAR);
        getCntjTerceroServicio().Accion(this);
        validarHabilitarSelUsuario();
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }
    
    public void guardar(){
        super.setAccion(ACCION_GUARDAR);
        getCntjTerceroServicio().Accion(this);
        if(!this.isError()){
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }
    
    public void editar(int id){
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getCntjTerceroServicio().Accion(this);
        validarHabilitarSelUsuario();
        procesoFinal();
    }
    
    public void modificar(){
        super.setAccion(ACCION_MODIFICAR);
        getCntjTerceroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void cambioTipoTercero(boolean iscrear){
        Integer tipoTercero = this.objeto.getTipoTercero();
        this.objeto = new CntjTercero(this.objeto.getId());
        this.objeto.setTipoTercero(tipoTercero);
        validarTipoSeleccionUsuario(iscrear);
    }
    
    public void validarTipoSeleccionUsuario(boolean iscrear){
        setOptCrear(iscrear);
        habilitarTodosCampos();
        Integer tipoTercero = this.objeto.getTipoTercero();
        if(tipoTercero == null){
            this.addError("Debe seleccionar el tipo de tercero.");
        }else{           
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            getCntjTerceroServicio().Accion(this);
            if (this.objeto.getTipoTercero().equals(CntjConstantes.TIPO_SUPERVISOR)) {
                habilitarCamposSupervisor();
                cargarLazyUsuario();
                PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
            }else if(this.objeto.getTipoTercero().equals(CntjConstantes.TIPO_PRESTADOR)){
                habilitarCamposPrestador();
                PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
                PrimeFaces.current().executeScript("PF('frmPrestadorLista:tablaRegistrosPrestador').clearFilters()");
            }else if(this.objeto.getTipoTercero().equals(CntjConstantes.TIPO_PROVEEDOR))  {   
                this.objeto = new CntjTercero(this.objeto.getId());
                this.objeto.setTipoTercero(tipoTercero);
                habilitarCamposPrestador();
                if(getSelPrestadorBean() != null){
                    getSelPrestadorBean().setObjeto(new CntPrestador());
                }                
                PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
                PrimeFaces.current().executeScript("PF('frmPrestadorLista:tablaRegistrosPrestador').clearFilters()");                
            }
            validarHabilitarSelUsuario();
            procesoFinal();
        }
    }
    
    private void validarHabilitarSelUsuario(){
        if (this.objeto.getTipoTercero() == null) {
            setHabilitadoSelecUsuario(false);
            return;
        } else {
            setHabilitadoSelecUsuario(true);
        }
        
        if (this.objeto.getTipoTercero().equals(CntjConstantes.TIPO_SUPERVISOR)) {
            habilitarCamposSupervisor();
        }else {
            if(this.getObjeto().getCntPrestadorId() != null){
                habilitarCamposPrestador();
            } else{
                habilitarTodosCampos();
            }           
        }
        
    }
    
    private void cargarLazyUsuario() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                listaUsuario = getListaUsuario();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaUsuario;
            }

            @Override
            public String getRowKey(Usuario usuario) {
                return usuario.getId().toString();
            }

            @Override
            public Usuario getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (Usuario user : listaUsuario) {
                    if (id.equals(user.getId())) {
                        return user;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectUsuario(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        Integer tipoTercero = this.objeto.getTipoTercero();
        this.objeto = new CntjTercero(this.objeto.getId());
        this.objeto.setTipoTercero(tipoTercero);
        this.objeto.setGnUsuarioId(usuario);
        this.objeto.setMaeTipoDocumentoId(usuario.getMaeTipoDocumentoId());
        this.objeto.setMaeTipoDocumentoCodigo(usuario.getMaeTipoDocumentoCodigo());
        this.objeto.setMaeTipoDocumentoValor(usuario.getMaeTipoDocumentoValor());
        this.objeto.setNumeroDocumento(usuario.getDocumento());
        this.objeto.setRazonSocial(usuario.getNombre());
        this.objeto.setCorreoElectronico(usuario.getCorreoElectronico());  
        this.objeto.setMaeCargoId(usuario.getMaeCargoId());
        this.objeto.setMaeCargoCodigo(usuario.getMaeCargoCodigo());
        this.objeto.setMaeCargoValor(usuario.getMaeCargoValor());
        this.objeto.setMaeAreaId(usuario.getMaeAreaId());
        this.objeto.setMaeAreaCodigo(usuario.getMaeAreaCodigo());
        this.objeto.setMaeAreaValor(usuario.getMaeAreaValor());
        if (this.optCrear) {
            PrimeFaces.current().ajax().update("frmCrear");
            PrimeFaces.current().ajax().update("pnlInfo");
            PrimeFaces.current().ajax().update("frmCrear:ctipoDoc");
        } else {
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().ajax().update("pnlInfo");
            PrimeFaces.current().ajax().update("frmEditar:ctipoDoc");
        }
        
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
        
    }
    
    public void cerrarDialogoCntPrestador(){
        CntPrestador prestador = getSelPrestadorBean().getObjeto();
        Integer tipoTercero = this.objeto.getTipoTercero();
        this.objeto = new CntjTercero(this.objeto.getId());
        this.objeto.setTipoTercero(tipoTercero);
        this.objeto.setCntPrestadorId(prestador);
        this.objeto.setMaeTipoDocumentoId(prestador.getMaeTipoDocumentoId());
        this.objeto.setMaeTipoDocumentoCodigo(prestador.getMaeTipoDocumentoCodigo());
        this.objeto.setMaeTipoDocumentoValor(prestador.getMaeTipoDocumentoValor());
        this.objeto.setNumeroDocumento(prestador.getNumeroDocumento());
        this.objeto.setNaturalezaJuridica(CntjConstantes.getIdNaturalezaValor(prestador.getNaturalezaJuridica()));
        this.objeto.setRazonSocial(prestador.getRazonSocial());
        this.objeto.setMaeRepresentanteTipoDocumentoId(prestador.getMaeTipoDocumentoRepId());
        this.objeto.setMaeRepresentanteTipoDocumentoCodigo(prestador.getMaeTipoDocumentoRepCodigo());
        this.objeto.setMaeRepresentanteTipoDocumentoValor(prestador.getMaeTipoDocumentoRepValor());
        this.objeto.setRepresentanteNumeroDocumento(prestador.getNumeroDocumentoRep());
        this.objeto.setNombreRepresentanteLegal(prestador.getNombreRepresentanteLegal());
        habilitarCamposPrestador();
        if(this.optCrear){
            PrimeFaces.current().ajax().update("frmCrear");
        }else{
            PrimeFaces.current().ajax().update("frmEditar");
        }        
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");
    }
    
    public void ocultarDialogoPrestador(){
        CntPrestador prestador = getSelPrestadorBean().getObjeto();
        if (this.objeto.getTipoTercero().equals(CntjConstantes.TIPO_PROVEEDOR) && prestador.getId() == null) {
            habilitarTodosCampos();
            PrimeFaces.current().ajax().update("frmCrear");
            PrimeFaces.current().ajax().update("frmEditar");
        }
    }
        
    public String retornarCadena(String campo) {
        if (campo == null || campo.trim().equals("")) {
            return "";
        } else {
            campo = campo + " ";
            return campo;
        }
    }
    
    public String getTipoTerceroStr(Integer id){
        return CntjConstantes.getTipoTercero(id);
    }
    
    public String getTipoNaturalezaStr(Integer id){
        return CntjConstantes.getTipoNaturaleza(id);
    }
    
    private void habilitarCamposPrestador() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        CntjConstantes.desactivarSelect("frmCrear:ctipoDoc", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cnuDoc", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cnombre", facesContext);
        CntjConstantes.desactivarSelect("frmCrear:cnaturaleza", facesContext);
        CntjConstantes.activarSelectBooleanButton("frmCrear:cuniontemporal", facesContext);
        
        CntjConstantes.desactivarSelect("frmEditar:ctipoDoc", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cnuDoc", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cnombre", facesContext);
        CntjConstantes.desactivarSelect("frmEditar:cnaturaleza", facesContext); 
        CntjConstantes.activarSelectBooleanButton("frmEditar:cuniontemporal", facesContext);
        if(this.objeto.getCntPrestadorId() != null){
            CntjConstantes.desactivarSelect("frmCrear:ctipodocreplegal", facesContext);
            CntjConstantes.desactivarInput("frmCrear:cnunreplegal", facesContext);
            CntjConstantes.desactivarInput("frmCrear:cnomreplegal", facesContext);
            CntjConstantes.desactivarSelect("frmEditar:ctipodocreplegal", facesContext);
            CntjConstantes.desactivarInput("frmEditar:cnunreplegal", facesContext);
            CntjConstantes.desactivarInput("frmEditar:cnomreplegal", facesContext);
        }
    }
    
    private void habilitarCamposSupervisor(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        CntjConstantes.desactivarSelect("frmCrear:ctipoDoc", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cnuDoc", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cnombre", facesContext);
        CntjConstantes.desactivarInput("frmCrear:ccorreo", facesContext);
        //CntjConstantes.desactivarInput("frmCrear:cdirecion", facesContext);
        CntjConstantes.desactivarInput("frmCrear:carea", facesContext);
        CntjConstantes.desactivarInput("frmCrear:ccargo", facesContext);
        CntjConstantes.desactivarselectBooleanButton("frmCrear:cuniontemporal", facesContext);
        
        CntjConstantes.desactivarSelect("frmEditar:ctipoDoc", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cnuDoc", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cnombre", facesContext);
        CntjConstantes.desactivarInput("frmEditar:ccorreo", facesContext);
        //CntjConstantes.desactivarInput("frmEditar:cdirecion", facesContext);
        CntjConstantes.desactivarInput("frmEditar:carea", facesContext);
        CntjConstantes.desactivarInput("frmEditar:ccargo", facesContext);
        CntjConstantes.desactivarselectBooleanButton("frmEditar:cuniontemporal", facesContext);
    }
    
    private void habilitarTodosCampos(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        CntjConstantes.activarSelect("frmCrear:ctipoDoc", facesContext);
        CntjConstantes.activarInput("frmCrear:cnuDoc", facesContext);
        CntjConstantes.activarSelect("frmCrear:cnaturaleza", facesContext);
        CntjConstantes.activarInput("frmCrear:cnombre", facesContext);
        CntjConstantes.activarSelect("frmCrear:cmunicipio", facesContext);
        CntjConstantes.activarInput("frmCrear:cdirecion", facesContext);
        CntjConstantes.activarInput("frmCrear:ccorreo", facesContext);
        CntjConstantes.activarSelect("frmCrear:ctipodocreplegal", facesContext);
        CntjConstantes.activarInput("frmCrear:cnunreplegal", facesContext);
        CntjConstantes.activarInput("frmCrear:cnomreplegal", facesContext);
        CntjConstantes.activarSelectBooleanButton("frmCrear:cuniontemporal", facesContext);
        
        CntjConstantes.activarSelect("frmEditar:ctipoDoc", facesContext);
        CntjConstantes.activarInput("frmEditar:cnuDoc", facesContext);
        CntjConstantes.activarSelect("frmEditar:cnaturaleza", facesContext);
        CntjConstantes.activarInput("frmEditar:cnombre", facesContext);
        CntjConstantes.activarSelect("frmEditar:cmunicipio", facesContext);
        CntjConstantes.activarInput("frmEditar:cdirecion", facesContext);
        CntjConstantes.activarInput("frmEditar:ccorreo", facesContext);
        CntjConstantes.activarSelect("frmEditar:ctipodocreplegal", facesContext);
        CntjConstantes.activarInput("frmEditar:cnunreplegal", facesContext);
        CntjConstantes.activarInput("frmEditar:cnomreplegal", facesContext);
        CntjConstantes.activarSelectBooleanButton("frmEditar:cuniontemporal", facesContext);
    }
    
    private void deshabilitarTodosCampos(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        CntjConstantes.desactivarSelect("frmCrear:ctipoDoc", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cnuDoc", facesContext);
        CntjConstantes.desactivarSelect("frmCrear:cnaturaleza", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cnombre", facesContext);
        CntjConstantes.desactivarSelect("frmCrear:cmunicipio", facesContext);
        CntjConstantes.desactivarInput("frmCrear:cdirecion", facesContext);
        CntjConstantes.desactivarInput("frmCrear:ccorreo", facesContext);
        CntjConstantes.desactivarSelect("frmEditar:ctipoDoc", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cnuDoc", facesContext);
        CntjConstantes.desactivarSelect("frmEditar:cnaturaleza", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cnombre", facesContext);
        CntjConstantes.desactivarSelect("frmEditar:cmunicipio", facesContext);
        CntjConstantes.desactivarInput("frmEditar:cdirecion", facesContext);
        CntjConstantes.desactivarInput("frmEditar:ccorreo", facesContext);
    }
    
    public void listaUnionTemporal(int idTercero){
        this.objeto.setId(idTercero);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getCntjTerceroServicio().Accion(this);      
        setVerOpcionGuardar(false);
        procesoFinal();
    }
    
    private void cargarLazyUnionTemporal(){
        lazyTerceroUt = new LazyDataModel<CntjTerceroUt>() {

                private List<CntjTerceroUt> listado;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<CntjTerceroUt> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                    filtrosHash.put(CntjConstantes.ID_TERCERO, getObjeto().getId());
                    getParamConsulta(0).setFiltros(filtrosHash);
                    refrescarUnionTemporal();
                    listado = getRegistrosUnionTemporal();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return listado;
                }

                @Override
                public String getRowKey(CntjTerceroUt objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjTerceroUt getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjTerceroUt objeto : listado) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
    }
    
    public void agregarIntegranteUt(){
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getCntjTerceroServicio().Accion(this);
        procesoFinal();
    }
    
    public boolean isSupervisor(Integer id){
        if(id == null){
            return false;
        }
        return id.equals(CntjConstantes.TIPO_SUPERVISOR);
    }
    
    public String getEtapaDesignacionStr(Integer id){
        return CntjConstantes.getEtapaDesignacion(id);
    }
    
    public void guardarIntegranteUt(){
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjTerceroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogAgregaIntegranteUt').hide()");
            PrimeFaces.current().ajax().update("frmVerUt");
        }
        procesoFinal();
    }
    
    public void guardarListaIntegrantesUt(){
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCntjTerceroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoVerUt').hide()");
            PrimeFaces.current().ajax().update("frmVerUt");
        }
        procesoFinal();
    }
    
    public String getNaturalezaJuridicaStr(Integer id){
        return CntjConstantes.getTipoNaturaleza(id);
    }
    
    public void eliminarTemporal(int idUt){
        this.objetoTerceroUt.setId(idUt);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_BORRAR);
        getCntjTerceroServicio().Accion(this);
        procesoFinal();
    }
    
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_VER:
                PrimeFaces.current().ajax().update("frmVer");
                PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                break;
            case Url.ACCION_CREAR:
                break;
            case Url.ACCION_EDITAR:                
                PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                PrimeFaces.current().ajax().update("frmEditar");
                break;
            case Url.ACCION_ADICIONAL_1:
                switch (getDoAccion()) {
                    case Url.ACCION_ADICIONAL_1:
                        PrimeFaces.current().ajax().update("frmCrear");
                        PrimeFaces.current().ajax().update("frmEditar");
                        break;
                }
                break; 
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmVerUt");
                        PrimeFaces.current().executeScript("PF('dialogoVerUt').show()");
                        
                        break;
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().resetInputs("frmAgregaUt");
                        PrimeFaces.current().ajax().update("frmAgregaUt");
                        PrimeFaces.current().executeScript("PF('dialogAgregaIntegranteUt').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().resetInputs("frmAgregaUt");
                        PrimeFaces.current().ajax().update("frmAgregaUt");                        
                        break;
                    case Url.ACCION_BORRAR:
                        PrimeFaces.current().executeScript("PF('dialogAgregaIntegranteUt').hide()");
                        PrimeFaces.current().ajax().update("frmVerUt");                        
                        break;
                }
                break; 
        }
        generarMensajes();
    }
    
}
