package com.saviasaludeps.savia.web.facturacionelectronica.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.facturacionelectronica.FeDocumento;
import com.saviasaludeps.savia.web.facturacionelectronica.servicio.FeDocumentoServicioIface;
import com.saviasaludeps.savia.web.facturacionelectronica.servicio.FeDocumentoServicioImpl;
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
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class FeDocumentoBean extends Url {

    private FeDocumentoServicioIface feDocumentoServicio;
    private FeDocumento objeto;
    private List<FeDocumento> registros;
    private LazyDataModel<FeDocumento> lazyFeDocumento;

    private List<String> listaEstadosDocumento;
    private HashMap<Integer, String> hashEstadosDocumento;

    public FeDocumentoBean() {
        this.objeto = new FeDocumento();
        Modulo mod = super.validarModulo(Modulo.ID_FE_DOCUMENTOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyFeDocumento = new LazyDataModel<FeDocumento>() {
                private List<FeDocumento> feDocumentos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<FeDocumento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                  
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros)); // Convierte los filtros correctamente
                    refrescar();
                    feDocumentos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return feDocumentos;
                    
                }

                @Override
                public String getRowKey(FeDocumento feDocumento) {
                    return feDocumento.getId().toString();
                }

                @Override
                public FeDocumento getRowData(String documentoId) {
                    Integer id = Integer.valueOf(documentoId);
                    for (FeDocumento feDocumento : feDocumentos) {
                        if (id.equals(feDocumento.getId())) {
                            return feDocumento;
                        }
                    }
                    return null;
                }
            };
        }
    }

    public FeDocumentoBean(FeDocumento documento) {
        this.objeto = documento;
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getFeDocumentoServicio().cargaInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public FeDocumento getObjeto() {
        return objeto;
    }

    public void setObjeto(FeDocumento objeto) {
        this.objeto = objeto;
    }

    public List<FeDocumento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<FeDocumento> registros) {
        this.registros = registros;
    }

    public LazyDataModel<FeDocumento> getLazyFeDocumento() {
        return lazyFeDocumento;
    }

    public void setLazyFeDocumento(LazyDataModel<FeDocumento> lazyFeDocumento) {
        this.lazyFeDocumento = lazyFeDocumento;
    }

    public List<String> getListaEstadosDocumento() {
        return listaEstadosDocumento;
    }

    public void setListaEstadosDocumento(List<String> listaEstadosDocumento) {
        this.listaEstadosDocumento = listaEstadosDocumento;
    }

    public HashMap<Integer, String> getHashEstadosDocumento() {
        return hashEstadosDocumento;
    }

    public void setHashEstadosDocumento(HashMap<Integer, String> hashEstadosDocumento) {
        this.hashEstadosDocumento = hashEstadosDocumento;
    }

    public FeDocumentoServicioIface getFeDocumentoServicio() {
        if (feDocumentoServicio == null){
            feDocumentoServicio = new FeDocumentoServicioImpl();
        }
        return feDocumentoServicio;
    }

    public void setFeDocumentoServicio(FeDocumentoServicioIface feDocumentoServicio) {
        this.feDocumentoServicio = feDocumentoServicio;
    }

    public String getTipoDocumentoDescripcion(short tipo) {
        switch (tipo) {
            case 0:
                return "Factura";
            case 1:
                return "Nota Débito";
            case 2:
                return "Nota Crédito";
            default:
                return "Desconocido";
        }
    }
    


    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getFeDocumentoServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getFeDocumentoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getFeDocumentoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        PrimeFaces.current().ajax().update("frmDocumentos");
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_EDITAR);
        getFeDocumentoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(Url.ACCION_MODIFICAR);
        getFeDocumentoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_BORRAR);
        getFeDocumentoServicio().Accion(this);
        procesoFinal();
        PrimeFaces.current().ajax().update("frmDocumentos");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmDocumentos");    
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
}
