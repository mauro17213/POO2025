/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorBean;
import com.saviasaludeps.savia.web.contratacion.servicio.ProfesionalesServicioIface;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
//import com.saviasaludeps.savia.web.singleton.ContratacionSingle;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class ProfesionalesBean extends Url {

    private ProfesionalesServicioIface profesionalesServicio;
    private CntProfesionalPrestador objeto;
    private CntPrestadorSede objetoPrestadorSede;
    private List<CntProfesionalPrestador> listaCntProfesionalPrestador;
    private List<CntProfesionalPrestador> registros;
    private LazyDataModel<CntProfesionalPrestador> lazyProfesionales;
    private HashMap<Integer, Maestro> hashTiposDocumentoProfesional;
    private List<Maestro> listaTiposDocumentoProfesional;
    private SelEspecialidadesBean selEspecialidadesBean;
    private SelPrestadorBean selPrestadorBean;
    private boolean buscaPrestador;
    private String dialogo;
    private boolean agregarEspecialidad;
    private List<CntProfesionalPrestador> listaCntProfesonalPrestadorBorrar;
    
//    @Autowired
//    private ContratacionSingle contratacionSingle;

    public ProfesionalesBean() {
        this.objeto = new CntProfesionalPrestador();
        Modulo mod = super.validarModulo(Modulo.ID_CONTRATACION_PROFESIONALES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getCntPrestador() != null ? super.getConexion().getEmpresa().getCntPrestador().getId() : null);
            lazyProfesionales = new LazyDataModel<>() {
                private List<CntProfesionalPrestador> listaProfesionalesPrestadores;

                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
                @Override
                public List<CntProfesionalPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaProfesionalesPrestadores = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaProfesionalesPrestadores;
                }

                @Override
                public String getRowKey(CntProfesionalPrestador reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public CntProfesionalPrestador getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (CntProfesionalPrestador item : listaProfesionalesPrestadores) {
                        if (id.equals(item.getId())) {
                            return item;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getProfesionalesServicio().cargaInicial(this);
        listar();
    }

    public ProfesionalesServicioIface getProfesionalesServicio() {
        return profesionalesServicio;
    }

    public void setProfesionalesServicio(ProfesionalesServicioIface profesionalesServicio) {
        this.profesionalesServicio = profesionalesServicio;
    }

    public CntProfesionalPrestador getObjeto() {
        return objeto;
    }

    public void setObjeto(CntProfesionalPrestador objeto) {
        this.objeto = objeto;
    }

    public List<CntProfesionalPrestador> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntProfesionalPrestador> registros) {
        this.registros = registros;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public LazyDataModel<CntProfesionalPrestador> getLazyProfesionales() {
        return lazyProfesionales;
    }

    public void setLazyProfesionales(LazyDataModel<CntProfesionalPrestador> lazyProfesionales) {
        this.lazyProfesionales = lazyProfesionales;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoProfesional() {
        return hashTiposDocumentoProfesional;
    }

    public void setHashTiposDocumentoProfesional(HashMap<Integer, Maestro> hashTiposDocumentoProfesional) {
        this.hashTiposDocumentoProfesional = hashTiposDocumentoProfesional;
    }

    public List<Maestro> getListaTiposDocumentoProfesional() {
        return listaTiposDocumentoProfesional;
    }

    public void setListaTiposDocumentoProfesional(List<Maestro> listaTiposDocumentoProfesional) {
        this.listaTiposDocumentoProfesional = listaTiposDocumentoProfesional;
    }

    public SelEspecialidadesBean getSelEspecialidadesBean() {
        selEspecialidadesBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return selEspecialidadesBean;
    }

    public void setSelEspecialidadesBean(SelEspecialidadesBean selEspecialidadesBean) {
        this.selEspecialidadesBean = selEspecialidadesBean;
    }

    public SelPrestadorBean getSelPrestadorBean() {
        selPrestadorBean = (SelPrestadorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorBean");
        return selPrestadorBean;
    }

    public void setSelPrestadorBean(SelPrestadorBean selPrestadorBean) {
        this.selPrestadorBean = selPrestadorBean;
    }

    public boolean getBuscaPrestador() {
        return buscaPrestador;
    }

    public void setBuscaPrestador(boolean buscaPrestador) {
        this.buscaPrestador = buscaPrestador;
    }

    public List<CntProfesionalPrestador> getListaCntProfesionalPrestador() {
        return listaCntProfesionalPrestador;
    }

    public void setListaCntProfesionalPrestador(List<CntProfesionalPrestador> listaCntProfesionalPrestador) {
        this.listaCntProfesionalPrestador = listaCntProfesionalPrestador;
    }

    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }

    public boolean getAgregarEspecialidad() {
        return agregarEspecialidad;
    }

    public void setAgregarEspecialidad(boolean agregarEspecialidad) {
        this.agregarEspecialidad = agregarEspecialidad;
    }

    public List<CntProfesionalPrestador> getListaCntProfesonalPrestadorBorrar() {
        return listaCntProfesonalPrestadorBorrar;
    }

    public void setListaCntProfesonalPrestadorBorrar(List<CntProfesionalPrestador> listaCntProfesonalPrestadorBorrar) {
        this.listaCntProfesonalPrestadorBorrar = listaCntProfesonalPrestadorBorrar;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getProfesionalesServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        setObjeto(new CntProfesionalPrestador());
        getObjeto().setCntProfesionalesId(new CntProfesional(id));
        super.setAccion(ACCION_VER);
        getProfesionalesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_CREAR);
        setDialogo(ContratacionParametro.DIALOGO_CREAR);
        getProfesionalesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void agregarProfesionalPrestador() {
        getProfesionalesServicio().agregarProfesionalPrestador(this);
        PrimeFaces.current().ajax().update("frmEditar:panelPrestador");
        PrimeFaces.current().ajax().update("frmCrear:panelPrestador");
        PrimeFaces.current().executeScript("PF('dialogoConsultarEspPres').hide()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getProfesionalesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        setObjeto(new CntProfesionalPrestador());
        getObjeto().setId(id);
        setDialogo(ContratacionParametro.DIALOGO_EDITAR);
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_EDITAR);
        getProfesionalesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getProfesionalesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            procesoFinal();
        } else {
            generarMensajes();
        }
        
    }

    public void cancelar(CntProfesionalPrestador obj) {
        getListaCntProfesionalPrestador().remove(obj);
        if (dialogo.equals(ContratacionParametro.DIALOGO_CREAR)) {
            PrimeFaces.current().ajax().update("frmCrear");
        } else {
            PrimeFaces.current().ajax().update("frmEditar");
        }
    }

    public void borrarProfesionalIps(CntProfesionalPrestador obj) {
        if (getListaCntProfesonalPrestadorBorrar() == null) {
            setListaCntProfesonalPrestadorBorrar(new ArrayList<>());
        }
        getListaCntProfesonalPrestadorBorrar().add(obj);
        getListaCntProfesionalPrestador().remove(obj);
        PrimeFaces.current().ajax().update("frmCrear:panelPrestador");
        PrimeFaces.current().ajax().update("frmEditar");
    }

    public void borrar(int id) {
        setObjeto(new CntProfesionalPrestador());
        getObjeto().setCntProfesionalesId(new CntProfesional(id));
        super.setAccion(ACCION_BORRAR);
        getProfesionalesServicio().Accion(this);
        if (super.isError()) {
            generarMensajes();
        } else {
            procesoFinal();
        }
    }

    public void validarDocumentoCrear() {
        switch (getObjeto().getCntProfesionalesId().getMaeTipoCodumentoId()) {
            case ContratacionParametro.ID_TIPO_DOCUMENTO_CC:
                PrimeFaces.current().executeScript("crearSoloNumero()");
                break;
            case ContratacionParametro.ID_TIPO_DOCUMENTO_CE:
                PrimeFaces.current().executeScript("crearAlfaNumrico()");
                break;
            case ContratacionParametro.ID_TIPO_DOCUMENTO_PE:
                PrimeFaces.current().executeScript("crearAlfaNumrico()");
                break;
            default:
                break;
        };
        getObjeto().getCntProfesionalesId().setDocumento("");
    }

    public void consultarProfesional() {
        try {
            if (objeto.getCntProfesionalesId().getMaeTipoCodumentoId() == 0) {
                addError("Tipo documento: : Error de validación: se necesita un valor.");
            }

            if (objeto.getCntProfesionalesId().getDocumento() == null || objeto.getCntProfesionalesId().getDocumento().isBlank()) {
                addError("Número documento: : Error de validación: se necesita un valor.");
            }

            if (!getErrores().isEmpty()) {
                generarMensajes();
                return;
            }
            getProfesionalesServicio().consultarProfesional(this);
            PrimeFaces.current().ajax().update("frmCrear");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarEspecialidad() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
            PrimeFaces.current().ajax().update("frmEspecialidadBusqueda");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoEspecialidad() {
        getObjeto().setMaEspecialidadCodigo(getSelEspecialidadesBean().getObjeto().getCodigo());
        getObjeto().setMaEspecialidadValor(getSelEspecialidadesBean().getObjeto().getNombre());
        getObjeto().setMaEspecialidadId(getSelEspecialidadesBean().getObjeto().getId());
        getSelEspecialidadesBean().setObjeto(new MaEspecialidad());
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmConsultarEspPres");

    }

    public void consultarPrestador() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
            PrimeFaces.current().ajax().update("frmPrestadorLista");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoSelPrestador() {
        
        getObjeto().setCntPrestador(getSelPrestadorBean().getObjeto());
        getProfesionalesServicio().cerrarDialogoSelPrestador(this);
        getSelPrestadorBean().setObjeto(new CntPrestador());
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");
        PrimeFaces.current().ajax().update("frmConsultarEspPres");
    }

    public void abrirConsultarPrestador() {
        try {
            getObjeto().setMaEspecialidadValor("");
            if (getConexion().getEmpresa().getId() == ContratacionParametro.ID_EMPRESA_SAVIA) {
                getObjeto().setCntPrestador(new CntPrestador());
            }            
            PrimeFaces.current().executeScript("PF('dialogoConsultarEspPres').show()");
            PrimeFaces.current().ajax().update("frmConsultarEspPres");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_VER:
                crearLog(getObjeto().getCntProfesionalesId().toString());
                break;
            case Url.ACCION_CREAR:
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        crearLog(getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmProfesionales");
                        break;
                    case Url.ACCION_BORRAR:
                        crearLog("Borrar Especialidad", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmProfesionales");
                        break;
                    default:
                        break;
                }
                break;
            case Url.ACCION_EDITAR:
                switch (getDoAccion()) {
                    case Url.ACCION_EDITAR:
                        crearLog(getObjeto().getCntProfesionalesId().toString());
                        PrimeFaces.current().ajax().update("frmProfesionales");
                        break;
                    case Url.ACCION_BORRAR:
                        crearLog("Borrar Especialidad", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmProfesionales");
                        break;
                    default:
                        break;
                }
                break;
            case Url.ACCION_GUARDAR:
                crearLog(getObjeto().getCntProfesionalesId().toString());
                PrimeFaces.current().ajax().update("frmProfesionales");
                break;
            case Url.ACCION_MODIFICAR:
                crearLog(getObjeto().getCntProfesionalesId().toString());
                PrimeFaces.current().ajax().update("frmProfesionales");
                break;
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().getCntProfesionalesId().toString());
                PrimeFaces.current().ajax().update("frmProfesionales");
                break;
            default:
                break;
        }
        generarMensajes();
    }

//    /**
//     * @return the contratacionSingle
//     */
//    public ContratacionSingle getContratacionSingle() {
//        return contratacionSingle;
//    }
//
//    /**
//     * @param contratacionSingle the contratacionSingle to set
//     */
//    public void setContratacionSingle(ContratacionSingle contratacionSingle) {
//        this.contratacionSingle = contratacionSingle;
//    }
}
