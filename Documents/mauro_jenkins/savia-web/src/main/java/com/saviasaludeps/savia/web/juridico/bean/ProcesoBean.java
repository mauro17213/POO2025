package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoGrupo;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjGrupo;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantillaCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjTransicion;
import com.saviasaludeps.savia.web.juridico.servicio.ProcesoServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
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
public class ProcesoBean extends Url {

    private ProcesoServicioIface procesoServicio;
    private CntjProceso objeto;
    private List<CntjProceso> registros;
    private LazyDataModel<CntjProceso> lazyProceso;
    private LazyDataModel<CntjCampo> lazyCampos;
    private LazyDataModel<CntjEstado> lazyEstados;
    private LazyDataModel<CntjTransicion> lazyTransiciones;
    private LazyDataModel<CntjProcesoDocumento> lazyDocumentos;

    private List<CntjCampo> registrosCampos;
    private List<CntjEstado> registrosEstados;
    private List<Maestro> listaTipoDato;
    private List<Maestro> listaTipoEstado;
    private List<CntjEstado> listaEstados;
    private List<CntjPlantilla> listaPlantilla;
    private List<CntjTransicion> registrosTransiciones;
    private List<CntjProcesoDocumento> registrosDocumentos;
    private List<CntjGrupo> listaGrupos;
    private List<Maestro> listaEjecucion;
    private List<MaestroTipo> listaMaestroTipo;
    private HashMap<Integer, CntjPlantilla> hashListaPlantilla;
    private List<Maestro> listaTipoProceso;
    private List<CntjEstadoProcesoDocumento> listaEstadoProcesoDocumento;
    private List<CntjEstadoProcesoDocumento> listaEstadoProcesoDocumentoBorrar;
    private CntjEstadoProcesoDocumento objEstadoProcesoDocumento;
    private List<Maestro> listaTipoDocumentos;
    private List<Maestro> listaEtapaDesignacion;
    private List<CntjProcesoDocumento> listaDocumentosProceso;
    private HashMap<Integer, CntjProcesoDocumento> hashListaDocumentosProceso;
    private List<String> listaTablas;
    private List<String> listaCampos;
    private List<String> listaValorLista;
    private Maestro maeValorLista;
    private List<CntjCampo> listaCamposReferenciados;
    private List<Maestro> listaValoresReferencias;
    private List<CntjProceso> listaProceso;

    private CntjCampo objetoCampo;
    private CntjPlantillaCampo objetoPlantillaCampo;
    private CntjEstado objetoEstado;
    private CntjTransicion objetoTransicion;
    private CntjEstadoGrupo objetoEstadoGrupo;
    private boolean requiereInfoTransicion;
    private Integer idTemporal;
    private boolean aplicaMaestro;
    private boolean tipoLista;
    private CntjProcesoDocumento objetoDocumento;

    public ProcesoBean() {
        this.objeto = new CntjProceso();
        this.objetoCampo = new CntjCampo();
        this.objetoPlantillaCampo = new CntjPlantillaCampo();
        this.objetoEstado = new CntjEstado();
        this.objetoTransicion = new CntjTransicion();
        this.objetoEstadoGrupo = new CntjEstadoGrupo();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_PROCESOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            lazyProceso = new LazyDataModel<CntjProceso>() {

                private List<CntjProceso> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjProceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CntjProceso objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjProceso getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjProceso objeto : lista) {
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
        getProcesoServicio().cargasInicial(this);
        listar();
    }

    // getter and setters

    public List<CntjProceso> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(List<CntjProceso> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public List<Maestro> getListaValoresReferencias() {
        return listaValoresReferencias;
    }

    public void setListaValoresReferencias(List<Maestro> listaValoresReferencias) {
        this.listaValoresReferencias = listaValoresReferencias;
    }

    public List<CntjCampo> getListaCamposReferenciados() {
        return listaCamposReferenciados;
    }

    public void setListaCamposReferenciados(List<CntjCampo> listaCamposReferenciados) {
        this.listaCamposReferenciados = listaCamposReferenciados;
    }
    
    public boolean isCampoTercero(Integer tipo){
        if(tipo == null){
            return false;
        }
        return tipo.equals(CntjConstantes.TIPO_DATO_CAMPO_REFERENCIADO);
    }
    
    public String booleanStr(boolean valor){
        if(valor){
            return "Si";
        }
        return "No";
    }
    
    public String getMaestroTipoTipo(String tipo){
        if(tipo == null){
            return "";
        }
        if(getListaMaestroTipo() == null || getListaMaestroTipo().isEmpty()){
            return "";
        }
        String nombre = getListaMaestroTipo().stream()
        .filter(obj -> obj.getTipo().equals(tipo)) 
        .map(MaestroTipo::getNombre)
        .findFirst() 
        .orElse("");
        return nombre;
    }
    
    public Maestro getMaeValorLista() {
        return maeValorLista;
    }

    public void setMaeValorLista(Maestro maeValorLista) {
        this.maeValorLista = maeValorLista;
    }

    public List<String> getListaValorLista() {
        return listaValorLista;
    }

    public void setListaValorLista(List<String> listaValorLista) {
        this.listaValorLista = listaValorLista;
    }

    public boolean isTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(boolean tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getTipoCampo(Integer id) {
        return CntjConstantes.getTipoDato(id);
    }

    public List<String> getListaTablas() {
        return listaTablas;
    }

    public void setListaTablas(List<String> listaTablas) {
        this.listaTablas = listaTablas;
    }

    public List<String> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<String> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public HashMap<Integer, CntjProcesoDocumento> getHashListaDocumentosProceso() {
        return hashListaDocumentosProceso;
    }

    public void setHashListaDocumentosProceso(HashMap<Integer, CntjProcesoDocumento> hashListaDocumentosProceso) {
        this.hashListaDocumentosProceso = hashListaDocumentosProceso;
    }

    public List<CntjEstadoProcesoDocumento> getListaEstadoProcesoDocumento() {
        return listaEstadoProcesoDocumento;
    }

    public void setListaEstadoProcesoDocumento(List<CntjEstadoProcesoDocumento> listaEstadoProcesoDocumento) {
        this.listaEstadoProcesoDocumento = listaEstadoProcesoDocumento;
    }

    public List<CntjEstadoProcesoDocumento> getListaEstadoProcesoDocumentoBorrar() {
        return listaEstadoProcesoDocumentoBorrar;
    }

    public void setListaEstadoProcesoDocumentoBorrar(List<CntjEstadoProcesoDocumento> listaEstadoProcesoDocumentoBorrar) {
        this.listaEstadoProcesoDocumentoBorrar = listaEstadoProcesoDocumentoBorrar;
    }

    public CntjEstadoProcesoDocumento getObjEstadoProcesoDocumento() {
        return objEstadoProcesoDocumento;
    }

    public void setObjEstadoProcesoDocumento(CntjEstadoProcesoDocumento objEstadoProcesoDocumento) {
        this.objEstadoProcesoDocumento = objEstadoProcesoDocumento;
    }

    public List<CntjPlantilla> getListaPlantilla() {
        return listaPlantilla;
    }

    public void setListaPlantilla(List<CntjPlantilla> listaPlantilla) {
        this.listaPlantilla = listaPlantilla;
    }

    public List<CntjProcesoDocumento> getListaDocumentosProceso() {
        return listaDocumentosProceso;
    }

    public void setListaDocumentosProceso(List<CntjProcesoDocumento> listaDocumentosProceso) {
        this.listaDocumentosProceso = listaDocumentosProceso;
    }

    public String getEtapataDesignacionStr(Integer id) {
        return CntjConstantes.getEtapaDeDesignacion(id);
    }

    public String getTipoDocumento(Integer id) {
        return CntjConstantes.getTipoDocumento(id);
    }

    public List<Maestro> getListaTipoDocumentos() {
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<Maestro> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public List<Maestro> getListaEtapaDesignacion() {
        return listaEtapaDesignacion;
    }

    public void setListaEtapaDesignacion(List<Maestro> listaEtapaDesignacion) {
        this.listaEtapaDesignacion = listaEtapaDesignacion;
    }

    public CntjProcesoDocumento getObjetoDocumento() {
        return objetoDocumento;
    }

    public void setObjetoDocumento(CntjProcesoDocumento objetoDocumento) {
        this.objetoDocumento = objetoDocumento;
    }

    public LazyDataModel<CntjProcesoDocumento> getLazyDocumentos() {
        return lazyDocumentos;
    }

    public void setLazyDocumentos(LazyDataModel<CntjProcesoDocumento> lazyDocumentos) {
        this.lazyDocumentos = lazyDocumentos;
    }

    public List<CntjProcesoDocumento> getRegistrosDocumentos() {
        return registrosDocumentos;
    }

    public void setRegistrosDocumentos(List<CntjProcesoDocumento> registrosDocumentos) {
        this.registrosDocumentos = registrosDocumentos;
    }

    public List<MaestroTipo> getListaMaestroTipo() {
        return listaMaestroTipo;
    }

    public void setListaMaestroTipo(List<MaestroTipo> listaMaestroTipo) {
        this.listaMaestroTipo = listaMaestroTipo;
    }

    public boolean isTipoCampoSelect(Integer tipo) {
        if (tipo == null) {
            return false;
        }
        return tipo.equals(CntjConstantes.TIPO_DATO_LISTA);
    }

    public boolean isAplicaMaestro() {
        return aplicaMaestro;
    }

    public void setAplicaMaestro(boolean aplicaMaestro) {
        this.aplicaMaestro = aplicaMaestro;
    }

    public Integer getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(Integer idTemporal) {
        this.idTemporal = idTemporal;
    }

    public String getTipoProceso(Integer id) {
        return CntjConstantes.getTipoProceso(id);
    }

    public List<Maestro> getListaTipoProceso() {
        return listaTipoProceso;
    }

    public void setListaTipoProceso(List<Maestro> listaTipoProceso) {
        this.listaTipoProceso = listaTipoProceso;
    }

    public ProcesoServicioIface getProcesoServicio() {
        return procesoServicio;
    }

    public void setProcesoServicio(ProcesoServicioIface procesoServicio) {
        this.procesoServicio = procesoServicio;
    }

    public CntjProceso getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjProceso objeto) {
        this.objeto = objeto;
    }

    public List<CntjProceso> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjProceso> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntjProceso> getLazyProceso() {
        return lazyProceso;
    }

    public void setLazyProceso(LazyDataModel<CntjProceso> lazyProceso) {
        this.lazyProceso = lazyProceso;
    }

    public LazyDataModel<CntjCampo> getLazyCampos() {
        return lazyCampos;
    }

    public void setLazyCampos(LazyDataModel<CntjCampo> lazyCampos) {
        this.lazyCampos = lazyCampos;
    }

    public List<CntjCampo> getRegistrosCampos() {
        return registrosCampos;
    }

    public void setRegistrosCampos(List<CntjCampo> registrosCampos) {
        this.registrosCampos = registrosCampos;
    }

    public CntjCampo getObjetoCampo() {
        return objetoCampo;
    }

    public void setObjetoCampo(CntjCampo objetoCampo) {
        this.objetoCampo = objetoCampo;
    }

    public List<Maestro> getListaTipoDato() {
        return listaTipoDato;
    }

    public void setListaTipoDato(List<Maestro> listaTipoDato) {
        this.listaTipoDato = listaTipoDato;
    }

    public HashMap<Integer, CntjPlantilla> getHashListaPlantilla() {
        return hashListaPlantilla;
    }

    public void setHashListaPlantilla(HashMap<Integer, CntjPlantilla> hashListaPlantilla) {
        this.hashListaPlantilla = hashListaPlantilla;
    }

    public CntjPlantillaCampo getObjetoPlantillaCampo() {
        return objetoPlantillaCampo;
    }

    public void setObjetoPlantillaCampo(CntjPlantillaCampo objetoPlantillaCampo) {
        this.objetoPlantillaCampo = objetoPlantillaCampo;
    }

    public List<Maestro> getListaTipoEstado() {
        return listaTipoEstado;
    }

    public void setListaTipoEstado(List<Maestro> listaTipoEstado) {
        this.listaTipoEstado = listaTipoEstado;
    }

    public CntjEstado getObjetoEstado() {
        return objetoEstado;
    }

    public void setObjetoEstado(CntjEstado objetoEstado) {
        this.objetoEstado = objetoEstado;
    }

    public CntjTransicion getObjetoTransicion() {
        return objetoTransicion;
    }

    public void setObjetoTransicion(CntjTransicion objetoTransicion) {
        this.objetoTransicion = objetoTransicion;
    }

    public LazyDataModel<CntjEstado> getLazyEstados() {
        return lazyEstados;
    }

    public void setLazyEstados(LazyDataModel<CntjEstado> lazyEstados) {
        this.lazyEstados = lazyEstados;
    }

    public List<CntjEstado> getRegistrosEstados() {
        return registrosEstados;
    }

    public void setRegistrosEstados(List<CntjEstado> registrosEstados) {
        this.registrosEstados = registrosEstados;
    }

    public List<CntjEstado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<CntjEstado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public LazyDataModel<CntjTransicion> getLazyTransiciones() {
        return lazyTransiciones;
    }

    public void setLazyTransiciones(LazyDataModel<CntjTransicion> lazyTransiciones) {
        this.lazyTransiciones = lazyTransiciones;
    }

    public List<CntjTransicion> getRegistrosTransiciones() {
        return registrosTransiciones;
    }

    public void setRegistrosTransiciones(List<CntjTransicion> registrosTransiciones) {
        this.registrosTransiciones = registrosTransiciones;
    }

    public boolean getRequiereInfoTransicion() {
        return requiereInfoTransicion;
    }

    public void setRequiereInfoTransicion(boolean requiereInfoTransicion) {
        this.requiereInfoTransicion = requiereInfoTransicion;
    }

    public List<CntjGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<CntjGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public CntjEstadoGrupo getObjetoEstadoGrupo() {
        return objetoEstadoGrupo;
    }

    public void setObjetoEstadoGrupo(CntjEstadoGrupo objetoEstadoGrupo) {
        this.objetoEstadoGrupo = objetoEstadoGrupo;
    }

    public List<Maestro> getListaEjecucion() {
        return listaEjecucion;
    }

    public void setListaEjecucion(List<Maestro> listaEjecucion) {
        this.listaEjecucion = listaEjecucion;
    }

    // metodos
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getProcesoServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void listarEstados() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescarEstados() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getProcesoServicio().Accion(this);
    }

    public void refrescarDocumentos() {
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        getProcesoServicio().Accion(this);
    }

    public void listarEstados(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyEstados();
        PrimeFaces.current().executeScript("PF('dialogoEstados').show()");
        procesoFinal();
    }

    private void cargarLazyEstados() {
        lazyEstados = new LazyDataModel<CntjEstado>() {

            private List<CntjEstado> listaEstados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntjEstado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_PROCESO, getObjeto().getId());
                getParamConsulta(1).setFiltros(filtrosHash);
                refrescarEstados();
                listaEstados = getRegistrosEstados();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaEstados;
            }

            @Override
            public String getRowKey(CntjEstado objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjEstado getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CntjEstado objeto : listaEstados) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void verEstado(int id) {
        getObjetoEstado().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_VER);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void crearEstado() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getProcesoServicio().Accion(this);
        requiereInfotransicion();
        PrimeFaces.current().resetInputs("frmCrearEstado");
        PrimeFaces.current().ajax().update("frmCrearEstado");
        PrimeFaces.current().executeScript("PF('dialogoCrearEstado').show()");
        procesoFinal();
    }

    public void guardarEstado() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearEstado').hide();");
        }
        procesoFinal();
    }

    public void editarEstado(int idestado) {
        getObjetoEstado().setId(idestado);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        getProcesoServicio().Accion(this);

        procesoFinal();
    }

    public void modificarEstado() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarEstado').hide();");
        }
        procesoFinal();
    }

    public void gruposEstados(Integer idestado) {
        objetoEstado.setId(idestado);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_LISTAR);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void agregarGrupoEstado() {
        if (this.objetoEstadoGrupo.getGrupoId().getId() == null) {
            this.addError("Debe seleccionar el grupo que desea asociar al estado.");
        } else {
            boolean existe = objetoEstado.getListaEstadoGrupo().stream()
                    .anyMatch(elemento -> elemento.getGrupoId().getId() == objetoEstadoGrupo.getGrupoId().getId());
            if (existe) {
                this.addError("El grupo ya se encuentra asociada al estado.");
                objetoEstadoGrupo.setGrupoId(new CntjGrupo());
                objetoEstadoGrupo.setEjecucion(false);
            } else {
                objetoEstadoGrupo.setEstadoId(objetoEstado);
                super.setAccion(ACCION_ADICIONAL_5);
                super.setDoAccion(ACCION_GUARDAR);
                getProcesoServicio().Accion(this);
                if (!super.isError()) {
                    //objetoEstado.getListaEstadoGrupo().add(objetoEstadoGrupo);
                    objetoEstadoGrupo.setGrupoId(new CntjGrupo());
                    objetoEstadoGrupo.setEjecucion(false);
                }
            }
        }
        procesoFinal();
    }

    public void quitarEstadoGrupo(int idGrupoEstado) {
        this.objetoEstadoGrupo.setId(idGrupoEstado);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_BORRAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            objetoEstado.getListaEstadoGrupo().removeIf(item -> item.getId() == objetoEstadoGrupo.getId());
        }
        procesoFinal();
    }

    public void listarTransiciones() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescarTransiciones() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getProcesoServicio().Accion(this);
    }

    public void listarTransiciones(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyTransiciones();
        PrimeFaces.current().executeScript("PF('dialogoTransiciones').show()");
        procesoFinal();
    }

    private void cargarLazyTransiciones() {
        lazyTransiciones = new LazyDataModel<CntjTransicion>() {

            private List<CntjTransicion> listaTransicion;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<CntjTransicion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_PROCESO, getObjeto().getId());
                getParamConsulta(2).setFiltros(filtrosHash);
                refrescarTransiciones();
                listaTransicion = getRegistrosTransiciones();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaTransicion;
            }

            @Override
            public String getRowKey(CntjTransicion objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjTransicion getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CntjTransicion objeto : listaTransicion) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void verTransicion(int idtransicion) {
        getObjetoTransicion().setId(idtransicion);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void crearTransicion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearTransicion");
        PrimeFaces.current().ajax().update("frmCrearTransicion");
        PrimeFaces.current().executeScript("PF('dialogoCrearTransicion').show()");
        procesoFinal();
    }

    public void editarTransicion(int idtransicion) {
        getObjetoTransicion().setId(idtransicion);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void modificarTransicion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarTransicion').hide();");
        }
        procesoFinal();
    }

    public void guardarTransicion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTransicion').hide();");
        }
        procesoFinal();
    }

    public void listarCampos() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescarCampos() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getProcesoServicio().Accion(this);
    }

    public void listarCampos(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyCampos();
        PrimeFaces.current().executeScript("PF('dialogoVerCaplantilla').show()");
        procesoFinal();
    }

    private void cargarLazyCampos() {
        lazyCampos = new LazyDataModel<CntjCampo>() {

            private List<CntjCampo> listaCampos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<CntjCampo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_PROCESO, getObjeto().getId());
                getParamConsulta(0).setFiltros(filtrosHash);
                refrescarCampos();
                listaCampos = getRegistrosCampos();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaCampos;
            }

            @Override
            public String getRowKey(CntjCampo objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjCampo getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CntjCampo objeto : listaCampos) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void verCampo(int id) {
        getObjetoCampo().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerCampo");
        PrimeFaces.current().executeScript("PF('dialogoVerCampo').show()");
        procesoFinal();
    }

    public void crearCampo() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearCampo");
        PrimeFaces.current().ajax().update("frmCrearCampo");
        PrimeFaces.current().executeScript("PF('dialogoCrearCampo').show()");
        validarAplicaMaestro();
        procesoFinal();
    }

    public void guardarCampo() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearCampo').hide();");
        }
        procesoFinal();
    }

    public void editarCampo(int id) {
        getObjetoCampo().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_EDITAR);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarCampo");
        PrimeFaces.current().executeScript("PF('dialogoEditarCampo').show()");
        validarAplicaMaestro();
        consultarListaMaestros();
        validarCamposReferencias();
        if (getObjetoCampo().getTabla() != null) {
            consultarCamposTabla(getObjetoCampo().getTabla());
        }
        procesoFinal();
    }

    public void modificarCampo() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_MODIFICAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarCampo').hide();");
        }
        procesoFinal();
    }

    public void consultarCamposTabla(String tabla) {
        getObjetoCampo().setTabla(tabla);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void listarPlantillasCampo(int idcampo) {
        getObjetoCampo().setId(idcampo);
        getObjetoCampo().setCntjProcesoId(getObjeto());
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_LISTAR);
        getProcesoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPlantillaCampo");
        PrimeFaces.current().executeScript("PF('dialogoVerPlantillaCampo').show()");
        procesoFinal();
    }

    public void agregarPlantillaCampo() {
        if (this.objetoCampo.getCntjPlantillaId() == null) {
            this.addError("Debe seleccionar la plantilla que desea asociar al campo.");
        } else {
            boolean existe = objetoCampo.getPlantillasCampo().stream()
                    .anyMatch(elemento -> elemento.getCntjPlantillaId().getId() == objetoCampo.getCntjPlantillaId());
            if (existe) {
                this.addError("Ya esta plantilla se encuentra asociada al campo.");
            } else {
                objetoPlantillaCampo = new CntjPlantillaCampo();
                objetoPlantillaCampo.setCntjCampoId(this.objetoCampo);
                objetoPlantillaCampo.setCntjPlantillaId(this.hashListaPlantilla.get(objetoCampo.getCntjPlantillaId()));
                super.setAccion(ACCION_ADICIONAL_4);
                super.setDoAccion(ACCION_GUARDAR);
                getProcesoServicio().Accion(this);
                if (!super.isError()) {
                    objetoCampo.getPlantillasCampo().add(objetoPlantillaCampo);
                    objetoCampo.setCntjPlantillaId(null);
                }
            }
        }
        procesoFinal();
    }

    public void agregarDocumentoEstado() {
        super.setDoAccion(CntjConstantes.ACCION_NA);
        if (this.objEstadoProcesoDocumento.getProcesodocumentoId().getId() == null) {
            this.addError("Debe seleccionar el documento que desea asociar al estado.");
        } else {
            boolean existe = getListaEstadoProcesoDocumento().stream()
                    .anyMatch(elemento -> elemento.getProcesodocumentoId().getId().equals(this.objEstadoProcesoDocumento.getProcesodocumentoId().getId()));
            if (existe) {
                this.addError("Este documento ya se encuentra asociada al estado.");
            } else {
                setIdTemporal(getIdTemporal() - 1);
                this.objEstadoProcesoDocumento.setId(getIdTemporal());
                this.objEstadoProcesoDocumento.setEstadoId(this.objetoEstado);
                CntjProcesoDocumento documento = getHashListaDocumentosProceso().get(this.objEstadoProcesoDocumento.getProcesodocumentoId().getId());
                this.objEstadoProcesoDocumento.setProcesodocumentoId(documento);
                getListaEstadoProcesoDocumento().add(this.objEstadoProcesoDocumento);
                this.objEstadoProcesoDocumento = new CntjEstadoProcesoDocumento();
            }
        }
        procesoFinal();
    }

    public void quitarDocumentoEstado(int iddocumento) {
        getListaEstadoProcesoDocumento().removeIf(item -> item.getProcesodocumentoId().getId().equals(iddocumento));
    }

    public void quitarDocumentoEstadoId(int id) {
        if (id < 0) {
            getListaEstadoProcesoDocumento().removeIf(item -> item.getId().equals(id));
        } else {
            Optional<CntjEstadoProcesoDocumento> estadodocumento = getListaEstadoProcesoDocumento().stream()
                    .filter(item -> item.getId().equals(id))
                    .findFirst();
            getListaEstadoProcesoDocumentoBorrar().add(estadodocumento.get());
            getListaEstadoProcesoDocumento().removeIf(item -> item.getId().equals(id));
        }
    }

    public void quitarPlantilla(int idplantilla) {
        objetoCampo.getPlantillasCampo().removeIf(item -> item.getCntjPlantillaId().getId() == idplantilla);
    }

    public void borrarPlantillaCampo(int idPlantillaCampo) {
        this.objetoPlantillaCampo.setId(idPlantillaCampo);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            objetoCampo.getPlantillasCampo().removeIf(item -> item.getCntjPlantillaId().getId() == objetoPlantillaCampo.getCntjPlantillaId().getId());
        }
        procesoFinal();
    }

    public String getTipoEstadoStr(Integer id) {
        return CntjConstantes.getTipoEstado(id);
    }

    public void requiereInfotransicion() {
        if (this.objetoEstado == null || this.objetoEstado.getTipo() == null) {
            setRequiereInfoTransicion(false);
            return;
        }

        if (this.objetoEstado.getTipo() > 0) {
            setRequiereInfoTransicion(true);
        } else {
            setRequiereInfoTransicion(false);
        }
    }

    public void validarAplicaMaestro() {
        if (getObjetoCampo().getTipoDato() != null && getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA)) {
            setTipoLista(true);
            consultarListaMaestros();
        } else {
            setTipoLista(false);
            setListaValorLista(new ArrayList<>());
            getObjetoCampo().setAplicaMaestro(false);
            setAplicaMaestro(false);
        }
        
        if(getObjetoCampo().getTipoDato() != null && getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_CAMPO_REFERENCIADO)){
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_ADICIONAL_3);
            getProcesoServicio().Accion(this);
            validarCamposReferencias();
            procesoFinal();
        }
    }

    public void consultarListaMaestros() {
        if (getObjetoCampo().isAplicaMaestro()) {
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_ADICIONAL_1);
            setAplicaMaestro(true);
            setListaValorLista(new ArrayList<>());
            getProcesoServicio().Accion(this);            
            procesoFinal();
        } else {
            setAplicaMaestro(false);
        }
    }

    public void listarDocumentos(Integer id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyDocumentos();
        PrimeFaces.current().executeScript("PF('dialogoDocumento').show()");
        procesoFinal();
    }

    private void cargarLazyDocumentos() {
        lazyDocumentos = new LazyDataModel<CntjProcesoDocumento>() {

            private List<CntjProcesoDocumento> listaDocumentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<CntjProcesoDocumento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_PROCESO, getObjeto().getId());
                getParamConsulta(3).setFiltros(filtrosHash);
                refrescarDocumentos();
                listaDocumentos = getRegistrosDocumentos();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaDocumentos;
            }

            @Override
            public String getRowKey(CntjProcesoDocumento objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjProcesoDocumento getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CntjProcesoDocumento objeto : listaDocumentos) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void crearDocumento() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_CREAR);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void guardarDocumento() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_GUARDAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearDocumento').hide();");
        }
        procesoFinal();
    }

    public void verDocumento(int id) {
        getObjetoDocumento().setId(id);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_VER);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void editarDocumento(int id) {
        getObjetoDocumento().setId(id);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_EDITAR);
        getProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void modificarDocumento() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_MODIFICAR);
        getProcesoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarDocumento').hide();");
        }
        procesoFinal();
    }

    public void verDialogoListaValores() {
        PrimeFaces.current().ajax().update("frmEditarEstado");
        PrimeFaces.current().executeScript("PF('dialogoListaValor').show()");
    }

    public void agregarListaValor() {
        try {
            getListaValorLista().add(String.format("%s-%s", getMaeValorLista().getId(), getMaeValorLista().getNombre()));
            setMaeValorLista(new Maestro());
            PrimeFaces.current().ajax().update("frmCrearCampo:ltado");
            PrimeFaces.current().ajax().update("frmEditarCampo:ltado");
            PrimeFaces.current().executeScript("PF('dialogoListaValor').hide();");
        } catch (Exception e) {
        }
    }
        
    public void validarCamposReferencias(){
        if(getObjetoCampo().getCampoReferencia() != null){            
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_ADICIONAL_4);
            getProcesoServicio().Accion(this);     
            procesoFinal();
        }else {
            setListaValoresReferencias(new ArrayList<>());
        }
        
    }
    
    public void validarPlantillasProceso(){
        if(getObjetoCampo().getCntjProcesoId().getId() != null){
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_LISTAR);
            getProcesoServicio().Accion(this);
            procesoFinal();
        }else{
            setListaPlantilla(new ArrayList<>());
        }
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
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        //crearLog("Lista de estados", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmEstados");
                        break;
                    case Url.ACCION_VER:
                        PrimeFaces.current().ajax().update("frmVerEstado");
                        PrimeFaces.current().executeScript("PF('dialogoVerEstado').show()");
                        break;
                    case Url.ACCION_CREAR:
                        crearLog("Crear estado", getObjetoEstado().toString());
                        break;
                    case Url.ACCION_EDITAR:
                        requiereInfotransicion();
                        PrimeFaces.current().ajax().update("frmEditarEstado");
                        PrimeFaces.current().executeScript("PF('dialogoEditarEstado').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_MODIFICAR:
                    case Url.ACCION_BORRAR:
                        crearLog(getObjetoEstado().toString());
                        PrimeFaces.current().ajax().update("frmEstados");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        //crearLog("Lista de estados", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmTransiciones");
                        break;
                    case Url.ACCION_VER:
                        PrimeFaces.current().ajax().update("frmVerTransicion");
                        PrimeFaces.current().executeScript("PF('dialogoVerTransicion').show()");
                        break;
                    case Url.ACCION_CREAR:
                        crearLog("Crear transicion", getObjetoTransicion().toString());
                        break;
                    case Url.ACCION_EDITAR:
                        PrimeFaces.current().ajax().update("frmEditarTransicion");
                        PrimeFaces.current().executeScript("PF('dialogoEditarTransicion').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_MODIFICAR:
                    case Url.ACCION_BORRAR:
                        crearLog(getObjetoTransicion().toString());
                        PrimeFaces.current().ajax().update("frmTransiciones");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_3:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmVerCp");
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_MODIFICAR:
                    case Url.ACCION_BORRAR:
                        crearLog(getObjetoCampo().toString());
                        PrimeFaces.current().ajax().update("frmVerCp");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_5:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().resetInputs(":frmVerGrupoEstado:option, :frmVerGrupoEstado:geejecucion");
                        PrimeFaces.current().ajax().update("frmVerGrupoEstado");
                        PrimeFaces.current().executeScript("PF('dialogoGrupoEstado').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_MODIFICAR:
                    case Url.ACCION_BORRAR:
                        crearLog(getObjetoEstadoGrupo().toString());
                        PrimeFaces.current().ajax().update("frmVerGrupoEstado");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_6:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmDocumento");
                        break;
                    case Url.ACCION_VER:
                        PrimeFaces.current().ajax().update("frmVerdoc");
                        PrimeFaces.current().executeScript("PF('dialogoVerDocumento').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_MODIFICAR:
                    case Url.ACCION_BORRAR:
                        crearLog(getObjetoDocumento().toString());
                        PrimeFaces.current().ajax().update("frmDocumento");
                        break;
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmCreardoc");
                        PrimeFaces.current().executeScript("PF('dialogoCrearDocumento').show()");
                        break;
                    case Url.ACCION_EDITAR:
                        PrimeFaces.current().ajax().update("frmEditardoc");
                        PrimeFaces.current().executeScript("PF('dialogoEditarDocumento').show()");
                        break;
                }
                break;
        }
        generarMensajes();
    }

}
