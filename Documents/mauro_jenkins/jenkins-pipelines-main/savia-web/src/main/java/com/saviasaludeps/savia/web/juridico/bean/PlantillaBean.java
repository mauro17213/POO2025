package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjProcesoDocumento;
import com.saviasaludeps.savia.web.juridico.servicio.PlantillaServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
public class PlantillaBean extends Url{
    
    private PlantillaServicioIface plantillaServicio;
    
    private CntjPlantilla objeto;
    private CntjCampo objetoCampo;
    private List<CntjPlantilla> registros;
    private LazyDataModel<CntjPlantilla> lazyPlantillas;
    private List<CntjCampo> listaCampos;
    private List<CntjProcesoDocumento> listaDocumentos;
    private List<SelectItem> documentosGroup;
    

    public PlantillaBean() {
        objeto = new CntjPlantilla();
        this.objetoCampo = new CntjCampo();
        this.listaCampos = new ArrayList<>();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_PLANTILLAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            lazyPlantillas = new LazyDataModel<CntjPlantilla>() {

                private List<CntjPlantilla> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjPlantilla> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CntjPlantilla objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjPlantilla getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjPlantilla objeto : lista) {
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
        getPlantillaServicio().cargasInicial(this);
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">

    public List<SelectItem> getDocumentosGroup() {
        return documentosGroup;
    }

    public void setDocumentosGroup(List<SelectItem> documentosGroup) {
        this.documentosGroup = documentosGroup;
    }
    
    public List<CntjProcesoDocumento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CntjProcesoDocumento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
        
    public PlantillaServicioIface getPlantillaServicio() {
        return plantillaServicio;
    }

    public void setPlantillaServicio(PlantillaServicioIface plantillaServicio) {
        this.plantillaServicio = plantillaServicio;
    }

    public CntjPlantilla getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjPlantilla objeto) {
        this.objeto = objeto;
    }

    public List<CntjPlantilla> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjPlantilla> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntjPlantilla> getLazyPlantillas() {
        return lazyPlantillas;
    }

    public void setLazyPlantillas(LazyDataModel<CntjPlantilla> lazyPlantillas) {
        this.lazyPlantillas = lazyPlantillas;
    }

    public CntjCampo getObjetoCampo() {
        return objetoCampo;
    }

    public void setObjetoCampo(CntjCampo objetoCampo) {
        this.objetoCampo = objetoCampo;
    }

    public List<CntjCampo> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<CntjCampo> listaCampos) {
        this.listaCampos = listaCampos;
    }
    // </editor-fold>
    
    
    
    //Metodos
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getPlantillaServicio().Accion(this);
    }
    
    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getPlantillaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }
    
    public void crear(){
        super.setAccion(ACCION_CREAR);
        getPlantillaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }
    
    public void guardar(){
        super.setAccion(ACCION_GUARDAR);
        getPlantillaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }        
        procesoFinal();
    }
    
    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getPlantillaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        }                
        procesoFinal();
    }

    public void modificar() {
        System.err.println(getObjeto().getEstructura());
        super.setAccion(ACCION_MODIFICAR);
        getPlantillaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void duplicarPlantilla(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        getPlantillaServicio().Accion(this);               
        procesoFinal();
    }
    
    public void listaCamposAgregar(){
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getPlantillaServicio().Accion(this);
        procesoFinal();
    }
    
    public void agregarCampo(){
        super.setAccion(CntjConstantes.ACCION_NA);
        if (getObjetoCampo().getEtiqueta() != null) {
            String etiqueta = getObjetoCampo().getEtiqueta();
            PrimeFaces.current().executeScript("copyTextToClipboard('" + etiqueta + "');");
            PrimeFaces.current().executeScript("PF('dialogoAgregaCampo').hide();");
        } else {
            addError("Debe seleccionar la etiqueta a copiar.");
        }
        procesoFinal();
    }
    
    
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                //crearLog(getObjetoGestion().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
            case Url.ACCION_ADICIONAL_2:
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
                        PrimeFaces.current().resetInputs("frmAgregaCampo");
                        PrimeFaces.current().ajax().update("frmAgregaCampo");
                        PrimeFaces.current().executeScript("PF('dialogoAgregaCampo').show()");
                        break;
                }
                break;
        }
        generarMensajes();
    }
    
   
    
}
