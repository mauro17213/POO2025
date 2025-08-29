/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.contratacion.servicio.MarcacionesServicioIface;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
//import com.saviasaludeps.savia.web.singleton.ContratacionSingle;
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
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Jose Perez
 */
@ManagedBean
@ViewScoped
public class MarcacionesBean extends Url {

    private MarcacionesServicioIface marcacionesServicio;
    private CntContratoDetalle objeto;
    private List<CntContratoDetalle> registros;
    private LazyDataModel<CntContratoDetalle> LazyContratoDetalle;
    
    private List<Maestro> listaAmbito;
    private HashMap<Integer, Maestro> hashAmbito;
    private List<Maestro> listaModalidadContrato;
    private HashMap<Integer, Maestro> hashModalidadContrato;
    
//    @Autowired
//    private ContratacionSingle contratacionSingle;
    
    public MarcacionesBean() {
        this.objeto = new CntContratoDetalle();
        Modulo mod = super.validarModulo(Modulo.ID_CONTRATACION_MARCACIONES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getCntPrestador() != null ? super.getConexion().getEmpresa().getCntPrestador().getId() : null);
            LazyContratoDetalle = new LazyDataModel<>() {
                private List<CntContratoDetalle> listaContratoDetalle;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaContratoDetalle = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaContratoDetalle;
                }

                @Override
                public String getRowKey(CntContratoDetalle reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public CntContratoDetalle getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (CntContratoDetalle item : listaContratoDetalle) {
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
        getMarcacionesServicio().cargaInicial(this);
        listar();
    }

    public MarcacionesServicioIface getMarcacionesServicio() {
        return marcacionesServicio;
    }

    public void setMarcacionesServicio(MarcacionesServicioIface marcacionesServicio) {
        this.marcacionesServicio = marcacionesServicio;
    }

    public CntContratoDetalle getObjeto() {
        return objeto;
    }

    public void setObjeto(CntContratoDetalle objeto) {
        this.objeto = objeto;
    }

    public List<CntContratoDetalle> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntContratoDetalle> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalle() {
        return LazyContratoDetalle;
    }

    public void setLazyContratoDetalle(LazyDataModel<CntContratoDetalle> LazyContratoDetalle) {
        this.LazyContratoDetalle = LazyContratoDetalle;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getMarcacionesServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        setObjeto(new CntContratoDetalle(id));
        super.setAccion(ACCION_VER);
        getMarcacionesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        /*super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_CREAR);
        getMarcacionesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();*/
    }

    public void guardar() {
        /*super.setAccion(ACCION_GUARDAR);
        getMarcacionesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();*/
    }

    public void editar(int id) {
        setObjeto(new CntContratoDetalle());
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getMarcacionesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getMarcacionesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        /*setObjeto(new CntContratoDetalle(id));
        super.setAccion(ACCION_BORRAR);
        getMarcacionesServicio().Accion(this);
        if (super.isError()) {
            generarMensajes();
        } else {
            procesoFinal();
        }*/
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_VER:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_CREAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmMarcaciones");
                break;
            case Url.ACCION_MODIFICAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmMarcaciones");
                break;
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmMarcaciones");
                break;
            default:
                break;
        }
        generarMensajes();
    }
    
    public String getBooleanStr (boolean valor) {
        if (valor) {
            return "Si";
        }else {
            return "No";
        }
    }
    
    public String getTipoTecnologia(Integer id) {
        String mensaje = "";
        if (id != null) {
            switch(id) {
                case ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA:
                    mensaje = "Tecnologia (CUP)";
                    break;
                case ContratacionParametro.TIPO_TECNOLOGIA_MEDICAMENTO:
                    mensaje = "Medicamento (CUM)";
                    break;
                case ContratacionParametro.TIPO_TECNOLOGIA_INSUMO:
                    mensaje = "Insumo";
                    break;
                case ContratacionParametro.TIPO_TECNOLOGIA_PAQUETE:
                    mensaje = "Paquete";
                    break;
            }
        }
        return mensaje;
    }

    /**
     * @return the listaAmbito
     */
    public List<Maestro> getListaAmbito() {
        return listaAmbito;
    }

    /**
     * @param listaAmbito the listaAmbito to set
     */
    public void setListaAmbito(List<Maestro> listaAmbito) {
        this.listaAmbito = listaAmbito;
    }

    /**
     * @return the hashAmbito
     */
    public HashMap<Integer, Maestro> getHashAmbito() {
        return hashAmbito;
    }

    /**
     * @param hashAmbito the hashAmbito to set
     */
    public void setHashAmbito(HashMap<Integer, Maestro> hashAmbito) {
        this.hashAmbito = hashAmbito;
    }

    /**
     * @return the listaModalidadContrato
     */
    public List<Maestro> getListaModalidadContrato() {
        return listaModalidadContrato;
    }

    /**
     * @param listaModalidadContrato the listaModalidadContrato to set
     */
    public void setListaModalidadContrato(List<Maestro> listaModalidadContrato) {
        this.listaModalidadContrato = listaModalidadContrato;
    }

    /**
     * @return the hashModalidadContrato
     */
    public HashMap<Integer, Maestro> getHashModalidadContrato() {
        return hashModalidadContrato;
    }

    /**
     * @param hashModalidadContrato the hashModalidadContrato to set
     */
    public void setHashModalidadContrato(HashMap<Integer, Maestro> hashModalidadContrato) {
        this.hashModalidadContrato = hashModalidadContrato;
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
