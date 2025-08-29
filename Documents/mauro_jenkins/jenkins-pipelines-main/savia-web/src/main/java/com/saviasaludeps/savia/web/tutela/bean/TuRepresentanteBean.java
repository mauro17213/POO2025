/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.tutela.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import com.saviasaludeps.savia.web.tutela.servicio.TuRepresentanteServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.springframework.web.jsf.FacesContextUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/**
 *
 * @author pavacca
 */
@ManagedBean
@ViewScoped
public final class TuRepresentanteBean extends Url {

    private TuRepresentanteServicioIface tuRepresentanteServicio;
    private TuRepresentante objeto;
    private List<TuRepresentante> registros;
    private LazyDataModel<TuRepresentante> lazyTuRepresentante;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaSexo;
    private HashMap<Integer, Maestro> hashSexo;
    private List<Maestro> listaTipoEstadoPersona;
    private HashMap<Integer, Maestro> hashTipoEstadoPersona;

    public TuRepresentanteBean() {
        this.objeto = new TuRepresentante();
        Modulo mod = super.validarModulo(Modulo.ID_TU_REPRESENTANTES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyTuRepresentante = new LazyDataModel<TuRepresentante>() {
                private List<TuRepresentante> tuRepresentante;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<TuRepresentante> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    tuRepresentante = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return tuRepresentante;
                }

                @Override
                public String getRowKey(TuRepresentante TuTutela) {
                    return TuTutela.getId().toString();
                }

                @Override
                public TuRepresentante getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (TuRepresentante tuRepresentante : tuRepresentante) {
                        if (id.equals(tuRepresentante.getId())) {
                            return tuRepresentante;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getTuRepresentanteServicio().cargaInicial(this);
        listar();
    }

    public TuRepresentanteServicioIface getTuRepresentanteServicio() {
        return tuRepresentanteServicio;
    }

    public void setTuRepresentanteServicio(TuRepresentanteServicioIface tuRepresentanteServicio) {
        this.tuRepresentanteServicio = tuRepresentanteServicio;
    }

    public TuRepresentante getObjeto() {
        return objeto;
    }

    public void setObjeto(TuRepresentante objeto) {
        this.objeto = objeto;
    }

    public List<TuRepresentante> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TuRepresentante> registros) {
        this.registros = registros;
    }

    public LazyDataModel<TuRepresentante> getLazyTuRepresentante() {
        return lazyTuRepresentante;
    }

    public void setLazyTuRepresentante(LazyDataModel<TuRepresentante> lazyTuRepresentante) {
        this.lazyTuRepresentante = lazyTuRepresentante;
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

    public List<Maestro> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Maestro> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public HashMap<Integer, Maestro> getHashSexo() {
        return hashSexo;
    }

    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadoPersona() {
        return hashTipoEstadoPersona;
    }

    public void setHashTipoEstadoPersona(HashMap<Integer, Maestro> hashTipoEstadoPersona) {
        this.hashTipoEstadoPersona = hashTipoEstadoPersona;
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getTuRepresentanteServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getTuRepresentanteServicio().Accion(this);
        procesoFinal();

    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_EDITAR);
        getTuRepresentanteServicio().Accion(this);
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_BORRAR);
        getTuRepresentanteServicio().Accion(this);
        procesoFinal();

    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getTuRepresentanteServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(Url.ACCION_MODIFICAR);
        getTuRepresentanteServicio().Accion(this);
        procesoFinal();
    }
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }
    
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getTuRepresentanteServicio().Accion(this);
    }

    public void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmRepresentantes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    crearLog(getObjeto().toString());

                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().resetInputs("frmCrear");
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
                    PrimeFaces.current().resetInputs("frmRepresentantes");
                    PrimeFaces.current().ajax().update("frmRepresentantes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().resetInputs("frmEditar");
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    //refrescar();
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
                    PrimeFaces.current().resetInputs("frmRepresentantes");
                    PrimeFaces.current().ajax().update("frmRepresentantes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().resetInputs("frmRepresentantes");
                    PrimeFaces.current().ajax().update("frmRepresentantes");
                    crearLog(getObjeto().toString());
                    break;

            }
        }
        generarMensajes();
    }
}
