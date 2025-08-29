/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.anticipo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoAdjunto;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoGestion;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoValor;
import com.saviasaludeps.savia.dominio.anticipo.ReporteAnticipo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.web.anticipo.servicio.AnticipoIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author NEXOS
 */
@ManagedBean
@ViewScoped
public class AnticipoBean extends Url {

    @Autowired
    private AnticipoIface anticipoServicio;
    private AntAnticipo objeto;
    private List<AntAnticipo> registros;
    private LazyDataModel<AntAnticipo> lazyAnticipo;
    //lista maestros
    private List<Maestro> listaTipoDocumentoPersona;
    private HashMap<Integer, Maestro> hashTipoDocumentoPersona;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private List<Maestro> listaGeneroAfiliado;
    private List<Maestro> listaEstadosAfiliado;
    private List<Maestro> listaRegimenAfiliado;
    private List<Ubicacion> listaCiudades;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaClasificacion;
    private HashMap<Integer, Maestro> hashClasificacion;
    private List<Maestro> listaTiposAdjuntos;
    private HashMap<Integer, Maestro> hashTiposAdjuntos;
    private List<Maestro> listaTiposAutorizarAdjuntos;
    private HashMap<Integer, Maestro> hashTiposAutorizarAdjuntos;
    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashUsuarios;
    
    /// lazy adicionales
    private List<AsegAfiliado> listaAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private List<CntPrestadorSede> listaPrestadores;
    private LazyDataModel<CntPrestadorSede> lazyPrestadores;

    // variables adicionales
    private String descripcionGenerica;
    private AntAnticipoGestion antAnticipoGestion;
    private AntAnticipoAdjunto objectoAdjunto;
    private AntAnticipoItem objetoItem;
    private AntAnticipoValor objetoAnticipoValor;
    private UploadedFile archivoAdjunto;
    private UploadedFile archivoAutorizarAdjunto;
    private List<AntAnticipoAdjunto> listaAntAdjuntos;
    private List<AntAnticipoAdjunto> listaAutorizarAntAdjuntos;

    //variables para tecnologias
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;
    private SelDiagnosticosBean diagnosticosBean;
    //variable  para reportes
    private List<ReporteAnticipo> reporteAnticipo;

    public AnticipoBean() {
        this.objeto = new AntAnticipo();
        Modulo _mod = super.validarModulo(Modulo.ID_ANT_ANTICIPOS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAnticipo = new LazyDataModel<AntAnticipo>() {

                private List<AntAnticipo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AntAnticipo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AntAnticipo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AntAnticipo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AntAnticipo objeto : lista) {
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
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAnticipoServicio().cargaInicial(this);
        listar();
    }

    public AnticipoIface getAnticipoServicio() {
        return anticipoServicio;
    }

    public void setAnticipoServicio(AnticipoIface anticipoServicio) {
        this.anticipoServicio = anticipoServicio;
    }

    public AntAnticipo getObjeto() {
        return objeto;
    }

    public void setObjeto(AntAnticipo objeto) {
        this.objeto = objeto;
    }

    public List<AntAnticipo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AntAnticipo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AntAnticipo> getLazyAnticipo() {
        return lazyAnticipo;
    }

    public void setLazyAnticipo(LazyDataModel<AntAnticipo> lazyAnticipo) {
        this.lazyAnticipo = lazyAnticipo;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public List<Maestro> getListaTipoDocumentoPersona() {
        return listaTipoDocumentoPersona;
    }

    public void setListaTipoDocumentoPersona(List<Maestro> listaTipoDocumentoPersona) {
        this.listaTipoDocumentoPersona = listaTipoDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoPersona() {
        return hashTipoDocumentoPersona;
    }

    public void setHashTipoDocumentoPersona(HashMap<Integer, Maestro> hashTipoDocumentoPersona) {
        this.hashTipoDocumentoPersona = hashTipoDocumentoPersona;
    }

    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    public List<Maestro> getListaGeneroAfiliado() {
        return listaGeneroAfiliado;
    }

    public void setListaGeneroAfiliado(List<Maestro> listaGeneroAfiliado) {
        this.listaGeneroAfiliado = listaGeneroAfiliado;
    }

    public List<Maestro> getListaEstadosAfiliado() {
        return listaEstadosAfiliado;
    }

    public void setListaEstadosAfiliado(List<Maestro> listaEstadosAfiliado) {
        this.listaEstadosAfiliado = listaEstadosAfiliado;
    }

    public List<Maestro> getListaRegimenAfiliado() {
        return listaRegimenAfiliado;
    }

    public void setListaRegimenAfiliado(List<Maestro> listaRegimenAfiliado) {
        this.listaRegimenAfiliado = listaRegimenAfiliado;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaClasificacion() {
        return listaClasificacion;
    }

    public void setListaClasificacion(List<Maestro> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
    }

    public HashMap<Integer, Maestro> getHashClasificacion() {
        return hashClasificacion;
    }

    public void setHashClasificacion(HashMap<Integer, Maestro> hashClasificacion) {
        this.hashClasificacion = hashClasificacion;
    }

    public List<Maestro> getListaTiposAdjuntos() {
        return listaTiposAdjuntos;
    }

    public void setListaTiposAdjuntos(List<Maestro> listaTiposAdjuntos) {
        this.listaTiposAdjuntos = listaTiposAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposAdjuntos() {
        return hashTiposAdjuntos;
    }

    public void setHashTiposAdjuntos(HashMap<Integer, Maestro> hashTiposAdjuntos) {
        this.hashTiposAdjuntos = hashTiposAdjuntos;
    }

    public List<Maestro> getListaTiposAutorizarAdjuntos() {
        return listaTiposAutorizarAdjuntos;
    }

    public void setListaTiposAutorizarAdjuntos(List<Maestro> listaTiposAutorizarAdjuntos) {
        this.listaTiposAutorizarAdjuntos = listaTiposAutorizarAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposAutorizarAdjuntos() {
        return hashTiposAutorizarAdjuntos;
    }

    public void setHashTiposAutorizarAdjuntos(HashMap<Integer, Maestro> hashTiposAutorizarAdjuntos) {
        this.hashTiposAutorizarAdjuntos = hashTiposAutorizarAdjuntos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public List<AsegAfiliado> getListaAfiliados() {
        return listaAfiliados;
    }

    public void setListaAfiliados(List<AsegAfiliado> listaAfiliados) {
        this.listaAfiliados = listaAfiliados;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliados() {
        return lazyAfiliados;
    }

    public void setLazyAfiliados(LazyDataModel<AsegAfiliado> lazyAfiliados) {
        this.lazyAfiliados = lazyAfiliados;
    }

    public List<CntPrestadorSede> getListaPrestadores() {
        return listaPrestadores;
    }

    public void setListaPrestadores(List<CntPrestadorSede> listaPrestadores) {
        this.listaPrestadores = listaPrestadores;
    }

    public LazyDataModel<CntPrestadorSede> getLazyPrestadores() {
        return lazyPrestadores;
    }

    public void setLazyPrestadores(LazyDataModel<CntPrestadorSede> lazyPrestadores) {
        this.lazyPrestadores = lazyPrestadores;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public AntAnticipoGestion getAntAnticipoGestion() {
        return antAnticipoGestion;
    }

    public void setAntAnticipoGestion(AntAnticipoGestion antAnticipoGestion) {
        this.antAnticipoGestion = antAnticipoGestion;
    }

    public AntAnticipoAdjunto getObjectoAdjunto() {
        return objectoAdjunto;
    }

    public void setObjectoAdjunto(AntAnticipoAdjunto objectoAdjunto) {
        this.objectoAdjunto = objectoAdjunto;
    }

    public AntAnticipoItem getObjetoItem() {
        return objetoItem;
    }

    public void setObjetoItem(AntAnticipoItem objetoItem) {
        this.objetoItem = objetoItem;
    }

    public AntAnticipoValor getObjetoAnticipoValor() {
        return objetoAnticipoValor;
    }

    public void setObjetoAnticipoValor(AntAnticipoValor objetoAnticipoValor) {
        this.objetoAnticipoValor = objetoAnticipoValor;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public UploadedFile getArchivoAutorizarAdjunto() {
        return archivoAutorizarAdjunto;
    }

    public void setArchivoAutorizarAdjunto(UploadedFile archivoAutorizarAdjunto) {
        this.archivoAutorizarAdjunto = archivoAutorizarAdjunto;
    }

    public List<AntAnticipoAdjunto> getListaAntAdjuntos() {
        return listaAntAdjuntos;
    }

    public void setListaAntAdjuntos(List<AntAnticipoAdjunto> listaAntAdjuntos) {
        this.listaAntAdjuntos = listaAntAdjuntos;
    }

    public List<AntAnticipoAdjunto> getListaAutorizarAntAdjuntos() {
        return listaAutorizarAntAdjuntos;
    }

    public void setListaAutorizarAntAdjuntos(List<AntAnticipoAdjunto> listaAutorizarAntAdjuntos) {
        this.listaAutorizarAntAdjuntos = listaAutorizarAntAdjuntos;
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public SelPaquetesBean getPaquetesBean() {
        paquetesBean = (SelPaquetesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPaquetesBean");
        return paquetesBean;
    }

    public void setPaquetesBean(SelPaquetesBean paquetesBean) {
        this.paquetesBean = paquetesBean;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }

    public List<ReporteAnticipo> getReporteAnticipo() {
        return reporteAnticipo;
    }

    public void setReporteAnticipo(List<ReporteAnticipo> reporteAnticipo) {
        this.reporteAnticipo = reporteAnticipo;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAnticipoServicio().Accion(this);
    }

    public void refrescarAfiliado() {
        getAnticipoServicio().listarAfiliado(this);
    }

    public void refrescarPrestador() {
        getAnticipoServicio().listarPrestadores(this);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        if (getObjeto().getTipo() == 1 && getObjeto().getAsegAfiliadosId() == null) {
            addError("El afiliado es obligatorio");
        }

        if (getObjeto().getTipo() == 1 && getObjeto().getMaDiagnosticoId() == null) {
            addError("El diagnostico es obligatorio");
        }

        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_EDITAR);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        }
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void modificarItemGestionar() {
        if(getObjetoItem().getCantidad() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
        }
        if(getObjetoItem().getValorTecnologiaCotizada().doubleValue() <= 0){
            addError("La valor tecnologia tiene que ser mayor a 0");
        }
        
        if (getObjeto().getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION) && getObjeto().getTipo().equals(AntAnticipo.TIPO_INDIVIDUAL)) {
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                Double sumatoriaValorTecnolofia = 0.0;
                for (AntAnticipoItem item : getObjeto().getAntAnticipoItemsList()) {
                    if(item.getId().equals(getObjetoItem().getId())){
                        Double valorUnitarioPorCantidad = getObjetoItem().getValorTecnologiaCotizada().doubleValue() * getObjetoItem().getCantidad();
                        sumatoriaValorTecnolofia = sumatoriaValorTecnolofia + valorUnitarioPorCantidad;
                    }else{
                        Double valorUnitarioPorCantidad = item.getValorTecnologiaCotizada().doubleValue() * item.getCantidad();
                        sumatoriaValorTecnolofia = sumatoriaValorTecnolofia + valorUnitarioPorCantidad;
                    }
                }
                if (getObjeto().getValorCotizado().doubleValue() != sumatoriaValorTecnolofia) {
                    addError("El valor cotizado no es igual a la sumatoria de los items");
                }
            }
        } else if(getObjeto().getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION) && getObjeto().getTipo().equals(AntAnticipo.TIPO_PAQUETE)){
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                AntAnticipoItem maxValorTecnologia = getObjeto().getAntAnticipoItemsList()
                        .stream()
                        .max(Comparator.comparing(v -> v.getValorTecnologiaCotizada()))
                        .orElseThrow(null);
                if(maxValorTecnologia.getValorTecnologiaCotizada().compareTo(getObjetoItem().getValorTecnologiaCotizada()) == -1 ){
                    if (getObjeto().getValorCotizado().compareTo(getObjetoItem().getValorTecnologiaCotizada()) == -1) {
                        addError("El valor de uno de los items cotizados supera el valor general del anticipo");
                     }
                 }
            } 
        }
        
        if(!super.isError()){
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_ADICIONAL_6);
            super.setTakeAccion(ACCION_MODIFICAR);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionar:pItemGestionar");
            PrimeFaces.current().executeScript("PF('dlgEditarItemGestionar').hide();");
        }
        procesoFinal();
    }

    public void modificarItem() {
        if(getObjetoItem().getCantidad() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
        }
        if(getObjetoItem().getValorTecnologiaCotizada().doubleValue() <= 0){
            addError("La valor tecnologia tiene que ser mayor a 0");
        }
        if(!super.isError()){
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(ACCION_ADICIONAL_1);
            super.setTakeAccion(ACCION_MODIFICAR);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
            PrimeFaces.current().executeScript("PF('dlgEditarItem').hide();");
        }
        procesoFinal();
    }

    public void ventanaGestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmGestionar");
            PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show();");
        }
        procesoFinal();
    }
    
    public void ventanaDescargaAnticipo(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmDescargarAnticipo");
            PrimeFaces.current().ajax().update("frmDescargarAnticipo");
            PrimeFaces.current().executeScript("PF('dialogoDescargarAnticipo').show();");
        }
        procesoFinal();
    }
    
    public void ventanaDevolver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmDevolverAnticipo");
            PrimeFaces.current().ajax().update("frmDevolverAnticipo");
            PrimeFaces.current().executeScript("PF('dialogoDevolverAnticipo').show();");
        }
        procesoFinal();
    }

    public void ventanaAutorizacion(int id) {
        getObjeto().setId(id);
        setObjectoAdjunto(new AntAnticipoAdjunto());
        setListaAutorizarAntAdjuntos(new ArrayList<>());
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_ADICIONAL_4);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAuditoriaAutorizarAdjunto");
            PrimeFaces.current().resetInputs("frmAutorizarAdjunto");
            PrimeFaces.current().ajax().update("frmAutorizarAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoAutorizarAdjuntos').show();");
        }
        procesoFinal();
    }

    public void ventanaCancelar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_ADICIONAL_5);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmCancelarAnticipo");
            PrimeFaces.current().ajax().update("frmCancelarAnticipo");
            PrimeFaces.current().executeScript("PF('dialogoCancelarAnticipo').show();");
        }
        procesoFinal();
    }

    public void ventanaPago(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_ADICIONAL_6);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmPagoAnticipo");
            PrimeFaces.current().ajax().update("frmPagoAnticipo");
            PrimeFaces.current().executeScript("PF('dialogoPagoAnticipo').show();");
        }
        procesoFinal();
    }

    public void ventanaGestion() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_CREAR);
        super.setTakeAccion(Url.ACCION_CREAR);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmGestionObservacion");
            PrimeFaces.current().ajax().update("frmGestionObservacion");
            PrimeFaces.current().executeScript("PF('dlgGestionObservacion').show();");
        }
        procesoFinal();
    }

    public void ventanaAdjunto() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        super.setTakeAccion(Url.ACCION_CREAR);
        if (!super.isError()) {
            setObjectoAdjunto(new AntAnticipoAdjunto());
            setListaAntAdjuntos(new ArrayList<>());
            PrimeFaces.current().ajax().update("frmAuditoriaAdjuntoProgramas");
            PrimeFaces.current().resetInputs("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show();");
        }
        procesoFinal();
    }

    public void ventanaItem() {
        AntAnticipo obj = getAnticipoServicio().consultarAnticipoId(getObjeto().getId(), this);
        if (obj.getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)) {
            addError("No se puede agregar un afiliado");
        }
        if (!super.isError()) {
            setObjetoItem(new AntAnticipoItem());
            getObjetoItem().setCantidad(1);
            PrimeFaces.current().resetInputs("frmItemGestionar");
            PrimeFaces.current().ajax().update("frmItemGestionar");
            PrimeFaces.current().executeScript("PF('dlgItemGestionar').show();");
        }
        procesoFinal();
    }
    
    public void verVentanaValores(int id) {
        setObjetoAnticipoValor(new AntAnticipoValor());
        getObjetoAnticipoValor().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmValoresVer");
        PrimeFaces.current().ajax().update("frmValoresVer");
        PrimeFaces.current().executeScript("PF('dialogoValoresVer').show();");
        procesoFinal();
    }
    
    public void verVentanaValoresEditar(int id) {
        setObjetoAnticipoValor(new AntAnticipoValor());
        getObjetoAnticipoValor().setId(id);
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmValoresVer");
        PrimeFaces.current().ajax().update("frmValoresVer");
        PrimeFaces.current().executeScript("PF('dialogoValoresVer').show();");
        procesoFinal();
    }
    
    public void verVentanaValoresGestionar(int id) {
        setObjetoAnticipoValor(new AntAnticipoValor());
        getObjetoAnticipoValor().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_9);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmValoresVer");
        PrimeFaces.current().ajax().update("frmValoresVer");
        PrimeFaces.current().executeScript("PF('dialogoValoresVer').show();");
        procesoFinal();
    }
    
    public void verItem(int id) {
        setObjetoItem(new AntAnticipoItem());
        getObjetoItem().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmItemVer");
            PrimeFaces.current().ajax().update("frmItemVer");
            PrimeFaces.current().executeScript("PF('dlgItemVer').show();");
        }
        procesoFinal();
    }
    
    public void verItemEditar(int id) {
        setObjetoItem(new AntAnticipoItem());
        getObjetoItem().setId(id);
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        super.setTakeAccion(Url.ACCION_VER);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmItemVer");
            PrimeFaces.current().ajax().update("frmItemVer");
            PrimeFaces.current().executeScript("PF('dlgItemVer').show();");
        }
        procesoFinal();
    }
    
    public void verItemGestionar(int id) {
        setObjetoItem(new AntAnticipoItem());
        getObjetoItem().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_6);
        super.setTakeAccion(Url.ACCION_VER);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmItemVer");
            PrimeFaces.current().ajax().update("frmItemVer");
            PrimeFaces.current().executeScript("PF('dlgItemVer').show();");
        }
        procesoFinal();
    }
    
    public void editarItemGestionar(int _id) {
        setObjetoItem(new AntAnticipoItem());
        getObjetoItem().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_6);
        super.setTakeAccion(ACCION_EDITAR);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditarItemGestionar");
        PrimeFaces.current().ajax().update("frmEditarItemGestionar");
        PrimeFaces.current().executeScript("PF('dlgEditarItemGestionar').show()");
        procesoFinal();
    }

    public void editarItem(int _id) {
        setObjetoItem(new AntAnticipoItem());
        getObjetoItem().setId(_id);
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_ADICIONAL_1);
        super.setTakeAccion(ACCION_EDITAR);
        getAnticipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditarItem");
        PrimeFaces.current().ajax().update("frmEditarItem");
        PrimeFaces.current().executeScript("PF('dlgEditarItem').show()");
        procesoFinal();
    }

    public void guardarGestion() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_CREAR);
        super.setTakeAccion(Url.ACCION_GUARDAR);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionar:pGestionGestionar");
            PrimeFaces.current().executeScript("PF('dlgGestionObservacion').hide();");
        }
        procesoFinal();
    }

    public void guardarGestionar() {
        if (getObjeto().getTipo().equals(AntAnticipo.TIPO_INDIVIDUAL)) {
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                Double sumatoriaValorTecnolofia = 0.0;
                //BigDecimal sumatoriaValorTecnolofia = new BigDecimal(0);
                for (AntAnticipoItem item : getObjeto().getAntAnticipoItemsList()) {
                    Double valorUnitarioPorCantidad = item.getValorTecnologiaCotizada().doubleValue() * item.getCantidad();
                    //BigDecimal cantidad = new BigDecimal(item.getCantidad());
                    //BigDecimal valorUnitarioPorCantidad = item.getValorTecnologia().multiply(cantidad);
                    //Double convertirValorTecnolofia = item.getValorTecnologia().doubleValue();
                    sumatoriaValorTecnolofia = sumatoriaValorTecnolofia + valorUnitarioPorCantidad;
                    //sumatoriaValorTecnolofia = sumatoriaValorTecnolofia.add(valorUnitarioPorCantidad);
                }
                if (getObjeto().getValorCotizado().doubleValue() != sumatoriaValorTecnolofia) {
                    addError("El valor cotizado no es igual a la sumatoria de los items");
                }
                /*if (!getObjeto().getValorCotizado().equals(sumatoriaValorTecnolofia)) {
                    addError("El valor cotizado no es igual a la sumatoria de los items");
                }*/
            } else {
                addError("Por lo menos debe tener un item");
            }
        } else {
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                AntAnticipoItem maxValorTecnologia = getObjeto().getAntAnticipoItemsList()
                        .stream()
                        .max(Comparator.comparing(v -> v.getValorTecnologiaCotizada()))
                        .orElseThrow(null);
                if (getObjeto().getValorCotizado().compareTo(maxValorTecnologia.getValorTecnologiaCotizada()) == -1) {
                    addError("El valor de uno de los items cotizados supera el valor general del anticipo");
                }
            } else {
                addError("Por lo menos debe tener un item");
            }
        }

        if (getObjeto().getCntPrestadorSedesId() == null) {
            addError("El prestador es obligatorio ");
        }

        if(getObjeto().getRetencionSugerida() != null){
            if (getObjeto().getRetencionSugerida().compareTo(new BigDecimal(AntAnticipo.PORCENTAJE_RETENCION_SUGERIDA)) == 1) {
                addError("No se permite una retención sugerida mayor al 100%");
            }
        }
                
        if (getObjeto().getAntAnticipoAdjuntosList().isEmpty()) {
            addError("Por lo menos debe tener un adjunto");
        }

        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_5);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide();");
        }
        procesoFinal();
    }

    public void guardarItem() {
      
        if (getObjetoItem().getMaTecnologiaId() == null) {
            addError("La tecnología es obligatoria");
        }      
        if(getObjetoItem().getCantidad() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
        }
        if(getObjetoItem().getValorTecnologiaCotizada().doubleValue() <= 0){
            addError("La valor tecnologia tiene que ser mayor a 0");
        }
        
        /*if (getObjeto().getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION) && getObjeto().getTipo().equals(AntAnticipo.TIPO_INDIVIDUAL)) {
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                Double sumatoriaValorTecnolofia = 0.0;
                for (AntAnticipoItem item : getObjeto().getAntAnticipoItemsList()) {
                    Double valorUnitarioPorCantidad = item.getValorTecnologiaCotizada().doubleValue() * item.getCantidad();
                    sumatoriaValorTecnolofia = sumatoriaValorTecnolofia + valorUnitarioPorCantidad;
                }
                Double valorUnitarioPorCantidad = getObjetoItem().getValorTecnologiaCotizada().doubleValue() * getObjetoItem().getCantidad();
                sumatoriaValorTecnolofia = sumatoriaValorTecnolofia + valorUnitarioPorCantidad;
                if (getObjeto().getValorCotizado().doubleValue() != sumatoriaValorTecnolofia) {
                    addError("El valor cotizado no es igual a la sumatoria de los items");
                }
            }
        } else if(getObjeto().getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION) && getObjeto().getTipo().equals(AntAnticipo.TIPO_PAQUETE)){
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                AntAnticipoItem maxValorTecnologia = getObjeto().getAntAnticipoItemsList()
                        .stream()
                        .max(Comparator.comparing(v -> v.getValorTecnologiaCotizada()))
                        .orElseThrow(null);
                if(maxValorTecnologia.getValorTecnologiaCotizada().compareTo(getObjetoItem().getValorTecnologiaCotizada()) == -1 ){
                    if (getObjeto().getValorCotizado().compareTo(getObjetoItem().getValorTecnologiaCotizada()) == -1) {
                        addError("El valor de uno de los items cotizados supera el valor general del anticipo");
                    }
                }
            } 
        }*/
        
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_6);
            super.setTakeAccion(Url.ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionar:pItemGestionar");
            PrimeFaces.current().executeScript("PF('dlgItemGestionar').hide();");
        }
        procesoFinal();
    }
    
    public void validarItemActual(){
        if (getObjeto().getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION) && getObjeto().getTipo().equals(AntAnticipo.TIPO_INDIVIDUAL)) {
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                Double sumatoriaValorTecnolofia = 0.0;
                for (AntAnticipoItem item : getObjeto().getAntAnticipoItemsList()) {
                    Double valorUnitarioPorCantidad = item.getValorTecnologiaCotizada().doubleValue() * item.getCantidad();
                    sumatoriaValorTecnolofia = sumatoriaValorTecnolofia + valorUnitarioPorCantidad;
                }
                if (getObjeto().getValorCotizado().doubleValue() != sumatoriaValorTecnolofia) {
                    addError("El valor cotizado no es igual a la sumatoria de los items");
                }
            }
        } else if(getObjeto().getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION) && getObjeto().getTipo().equals(AntAnticipo.TIPO_PAQUETE)){
            if (!getObjeto().getAntAnticipoItemsList().isEmpty()) {
                AntAnticipoItem maxValorTecnologia = getObjeto().getAntAnticipoItemsList()
                        .stream()
                        .max(Comparator.comparing(v -> v.getValorTecnologiaCotizada()))
                        .orElseThrow(null);
                if (getObjeto().getValorCotizado().compareTo(maxValorTecnologia.getValorTecnologiaCotizada()) == -1) {
                    addError("El valor de uno de los items cotizados supera el valor general del anticipo");
                }
            } 
        }  
        if (super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        }
        generarMensajes();
    }
    
    public void actualizarEstadoDescargarArchivo(){
        PrimeFaces.current().ajax().update("frmAnticipos");
    }
    
    public void guardarAnticipoAdjunto() {
        if (getListaAntAdjuntos().isEmpty()) {
            addError("No tiene adjuntos para guardar");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_2);
            super.setTakeAccion(Url.ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().ajax().update("frmGestionar:pAdjuntoGestionar");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide();");
        }
        procesoFinal();
    }

    public void guardarAutorizarAdjuntoAnticipo() {
        if (getListaAutorizarAntAdjuntos().isEmpty()) {
            addError("No tiene adjuntos para guardar");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_4);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoAutorizarAdjuntos').hide();");
        }
        procesoFinal();
    }

    public void guardarDevolverAnticipo() {

        if (getObjeto().getId() != null) {
            super.setAccion(Url.ACCION_ADICIONAL_3);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        PrimeFaces.current().executeScript("PF('dialogoDevolverAnticipo').hide();");
        procesoFinal();
    }

    public void guardarCancelarAnticipo() {
        if (getObjeto().getId() != null) {
            super.setAccion(Url.ACCION_ADICIONAL_5);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        PrimeFaces.current().executeScript("PF('dialogoCancelarAnticipo').hide();");
        procesoFinal();
    }

    public void guardarPagoAnticipo() {

        if (getObjeto().getId() != null) {
            super.setAccion(Url.ACCION_ADICIONAL_6);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAnticipoServicio().Accion(this);
        }
        PrimeFaces.current().executeScript("PF('dialogoPagoAnticipo').hide();");
        procesoFinal();
    }
    
    public void verGestion(int id) {
        setAntAnticipoGestion(new AntAnticipoGestion());
        getAntAnticipoGestion().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmVerGestiones");
            PrimeFaces.current().ajax().update("frmVerGestiones");
            PrimeFaces.current().executeScript("PF('dlgVerGestiones').show();");
        }
        procesoFinal();
    }
    
    public void verGestionEditar(int id) {
        setAntAnticipoGestion(new AntAnticipoGestion());
        getAntAnticipoGestion().setId(id);
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmVerGestiones");
            PrimeFaces.current().ajax().update("frmVerGestiones");
            PrimeFaces.current().executeScript("PF('dlgVerGestiones').show();");
        }
        procesoFinal();
    }
    
    public void verGestionGestionar(int id) {
        setAntAnticipoGestion(new AntAnticipoGestion());
        getAntAnticipoGestion().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_CREAR);
        super.setTakeAccion(Url.ACCION_VER);
        getAnticipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmVerGestiones");
            PrimeFaces.current().ajax().update("frmVerGestiones");
            PrimeFaces.current().executeScript("PF('dlgVerGestiones').show();");
        }
        procesoFinal();
    }

    public void ventanaBorrarItem(AntAnticipoItem item) {
        setObjetoItem(new AntAnticipoItem());
        setObjetoItem(item);
        PrimeFaces.current().resetInputs("frmBorrarItem");
        PrimeFaces.current().ajax().update("frmBorrarItem");
        PrimeFaces.current().executeScript("PF('dialogoBorrarItem').show();");
    }

    public void borrarItem() {

        if (getObjetoItem().getId() != null) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_6);
            super.setTakeAccion(ACCION_BORRAR);
            getAnticipoServicio().Accion(this);
        }
        PrimeFaces.current().ajax().update("frmGestionar:pItemGestionar");
        PrimeFaces.current().executeScript("PF('dialogoBorrarItem').hide();");
        procesoFinal();
    }

    public void ventanaBorrarItemEditar(AntAnticipoItem item) {
        setObjetoItem(new AntAnticipoItem());
        setObjetoItem(item);
        PrimeFaces.current().resetInputs("frmBorrarItemEditar");
        PrimeFaces.current().ajax().update("frmBorrarItemEditar");
        PrimeFaces.current().executeScript("PF('dialogoBorrarItemEditar').show();");
    }

    public void borrarItemEditar() {
        if (getObjetoItem().getId() != null) {
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            super.setTakeAccion(Url.ACCION_BORRAR);
            getAnticipoServicio().Accion(this);
        }
        PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
        PrimeFaces.current().executeScript("PF('dialogoBorrarItemEditar').hide();");
        generarMensajes();
    }

    public void borrarDiagnostico() {
        getObjeto().setMaDiagnosticoId(null);
        getObjeto().setMaDiagnosticoCodigo(null);
        getObjeto().setMaDiagnosticoValor(null);
        addMensaje("Se ha realizado la eliminación de la especalidad");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoAntcipoCrear");
        //PrimeFaces.current().ajax().update("frmGestionar:panelEspecialidadGestionar");
        //PrimeFaces.current().ajax().update("frmEditar:panelEspecialidadGestionar");
    }

    public void consultarTecnologia() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void cerrarDialogoTecnologia() {
        MaTecnologia tecnologia = getTecnologiasBean().getObjeto();

        getObjeto().getAntAnticipoItemsList().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA
                && tecnologia.getId().equals(item.getMaTecnologiaId()))).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_TECNOLOGIA, tecnologia.getCupsDescipcion()));
        });

        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_TECNOLOGIA);
            getObjetoItem().setMaTecnologiaId(tecnologia.getId());
            getObjetoItem().setMaTecnologiaCodigo(tecnologia.getCodigoPropio());
            getObjetoItem().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            //Medicamento
            getObjetoItem().setMaMedicamentoId(null);
            getObjetoItem().setMaMedicamentoCodigo(null);
            getObjetoItem().setMaMedicamentoValor(null);
            getObjetoItem().setAntAnticiposId(getObjeto());
            getObjetoItem().setBorrado(Boolean.FALSE);
            PrimeFaces.current().ajax().update("frmItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItem:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItem:idMaMedicamentoCodigo");

            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
        }
        procesoFinal();
    }

    public void consultarMedicamento() {
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
    }

    public void cerrarDialogoMedicamento() {
        MaMedicamento medicamento = getMedicamentosBean().getObjeto();
        getObjeto().getAntAnticipoItemsList().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO
                && medicamento.getMaAgrupadorMedicamento().getId().equals(item.getMaTecnologiaId()))).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_AGRUPADOR_MEDICAMENTO, medicamento.getMaAgrupadorMedicamento().getNombre()));
        });
        if (!super.isError()) {
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_MEDICAMENTO);
            // tecnologia
            getObjetoItem().setMaTecnologiaId(medicamento.getMaAgrupadorMedicamento().getId());
            getObjetoItem().setMaTecnologiaCodigo(medicamento.getMaAgrupadorMedicamento().getCodigo());
            getObjetoItem().setMaTecnologiaValor(medicamento.getMaAgrupadorMedicamento().getNombre());
            //Medicamento
            getObjetoItem().setMaMedicamentoId(medicamento.getId());
            getObjetoItem().setMaMedicamentoCodigo(medicamento.getCum());
            getObjetoItem().setMaMedicamentoValor(medicamento.getDescripcionEstandarizada());
            getObjetoItem().setAntAnticiposId(getObjeto());
            getObjetoItem().setBorrado(Boolean.FALSE);
            PrimeFaces.current().ajax().update("frmItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItem:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItem:idMaMedicamentoCodigo");
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
        }
        procesoFinal();
    }

    public void consultarInsumo() {
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
    }

    public void cerrarDialogoInsumo() {
        MaInsumo insumo = getInsumosBean().getObjeto();
        getObjeto().getAntAnticipoItemsList().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_INSUMO
                && insumo.getId().equals(item.getMaTecnologiaId()))).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_INSUMO, insumo.getDescripcion()));
        });
        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_INSUMO);
            getObjetoItem().setMaTecnologiaId(insumo.getId());
            getObjetoItem().setMaTecnologiaCodigo(insumo.getCodigo());
            getObjetoItem().setMaTecnologiaValor(insumo.getDescripcion());
            //Medicamento
            getObjetoItem().setMaMedicamentoId(null);
            getObjetoItem().setMaMedicamentoCodigo(null);
            getObjetoItem().setMaMedicamentoValor(null);
            getObjetoItem().setAntAnticiposId(getObjeto());
            getObjetoItem().setBorrado(Boolean.FALSE);
            PrimeFaces.current().ajax().update("frmItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItem:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItem:idMaMedicamentoCodigo");
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
        }
        procesoFinal();
    }

    public void consultarPaquete() {
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
        PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
    }

    public void cerrarDialogoPaquete() {
        MaPaquete paquete = getPaquetesBean().getObjeto();
        getObjeto().getAntAnticipoItemsList().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_PAQUETE
                && paquete.getId().equals(item.getMaTecnologiaId()))).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_INSUMO, paquete.getNombre()));
        });
        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_PAQUETE);
            getObjetoItem().setMaTecnologiaId(paquete.getId());
            getObjetoItem().setMaTecnologiaCodigo(paquete.getCodigo());
            getObjetoItem().setMaTecnologiaValor(paquete.getNombre());
            //Medicamento
            getObjetoItem().setMaMedicamentoId(null);
            getObjetoItem().setMaMedicamentoCodigo(null);
            getObjetoItem().setMaMedicamentoValor(null);
            getObjetoItem().setAntAnticiposId(getObjeto());
            getObjetoItem().setBorrado(Boolean.FALSE);
            PrimeFaces.current().ajax().update("frmItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItemGestionar:idMaMedicamentoCodigo");

            PrimeFaces.current().ajax().update("frmEditarItem:idTipoTecnologia");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaCodigo");
            PrimeFaces.current().ajax().update("frmEditarItem:idTecnologiaValor");
            PrimeFaces.current().ajax().update("frmEditarItem:idMaMedicamentoCodigo");

            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
        }
        procesoFinal();
    }

    public void consultarAfiliado() {
        if (getObjeto().getId() != null) {
            AntAnticipo obj = getAnticipoServicio().consultarAnticipoId(getObjeto().getId(), this);
            if (obj.getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)) {
                addError("No se puede agregar un afiliado");
            }
        }
        if (!super.isError()) {
            cargaLazyAfiliado();
            PrimeFaces.current().ajax().update("frmAfiliadoLista");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
        }
        procesoFinal();
    }

    public void cargaLazyAfiliado() {
        //Afiliado
        lazyAfiliados = new LazyDataModel<AsegAfiliado>() {
            private List<AsegAfiliado> afiliados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAfiliado();
                afiliados = getListaAfiliados();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return afiliados;
            }

            @Override
            public String getRowKey(AsegAfiliado afiliado) {
                return afiliado.getId().toString();
            }

            @Override
            public AsegAfiliado getRowData(String afiliadoId) {
                Integer id = Integer.valueOf(afiliadoId);
                for (AsegAfiliado afiliado : afiliados) {
                    if (id.equals(afiliado.getId())) {
                        return afiliado;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        AsegAfiliado afiliado = (AsegAfiliado) event.getObject();
        boolean isActivo = getAnticipoServicio().validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), this);
        if (isActivo) {
            getObjeto().setAsegAfiliadosId(afiliado);
            getParamConsulta(0).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrear:pAfiliadoCrear");
            PrimeFaces.current().ajax().update("frmEditar:panelAfiliadoEditar");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
        }
        generarMensajes();
    }

    public void consultarPrestador() {
        if (getObjeto().getId() != null) {
            AntAnticipo obj = getAnticipoServicio().consultarAnticipoId(getObjeto().getId(), this);
            if (obj.getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)) {
                addError("No se puede agregar un afiliado");
            }
        }
        if (!super.isError()) {
            cargaLazyPrestador();
            PrimeFaces.current().ajax().update("frmIpsLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        }
        procesoFinal();
    }

    public void cargaLazyPrestador() {

        lazyPrestadores = new LazyDataModel<CntPrestadorSede>() {
            private List<CntPrestadorSede> listaPrestadores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPrestador();
                listaPrestadores = getListaPrestadores();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaPrestadores;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaPrestadores) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectPrestador(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntPrestadorSedesId(ips);
        getObjeto().setCntPrestadoresId(ips.getCntPrestador());
        setParamConsulta(new ParamConsulta());
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmCrear:pPrestadorCrear");
        PrimeFaces.current().ajax().update("frmGestionar:pPrestadorGestionar");
        PrimeFaces.current().ajax().update("frmEditar:pPrestadorEditar");
    }

    public void consultarDiagnostico() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void cerrarDialogoDiagnostico() {
        getObjeto().setMaDiagnosticoId(getDiagnosticosBean().getObjeto().getId());
        getObjeto().setMaDiagnosticoCodigo(getDiagnosticosBean().getObjeto().getCodigo());
        getObjeto().setMaDiagnosticoValor(getDiagnosticosBean().getObjeto().getNombre());
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoAntcipoCrear");
        PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoAntcipoEditar");
    }

    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String calcularEdad(Date fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = AuConstantes.formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerUbicacion(int id) {
        if (id == 0) {
            return "";
        }
        String ubi = getHashUbicaciones().get(id).getNombreDepartamentoCiudad();
        return ubi;
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
            if (objectoAdjunto.getMaeTipoArchivoId() == 0) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }

            String ruta = PropApl.getInstance().get(PropApl.ANT_RUTA_ANTICIPOS_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                addError("No esta configurada la ruta para los anticipos del sistema");
                return;
            }

            AntAnticipoAdjunto borrarObj = new AntAnticipoAdjunto();
            for (AntAnticipoAdjunto peAdjunto : listaAntAdjuntos) {
                if (peAdjunto.getMaeTipoArchivoId() == objectoAdjunto.getMaeTipoArchivoId()) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            listaAntAdjuntos.remove(borrarObj);
            objectoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto);
            objectoAdjunto.setOrigen(AntAnticipoAdjunto.ORIGEN_ANTICIPOS);
            objectoAdjunto.setRuta(ruta);
            objectoAdjunto.setExiste(Boolean.TRUE);
            Maestro tipoArchivo = hashTiposAdjuntos.get(objectoAdjunto.getMaeTipoArchivoId());
            if (tipoArchivo != null) {
                objectoAdjunto.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                objectoAdjunto.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                objectoAdjunto.setMaeTipoArchivoTipo(tipoArchivo.getTipo());
            }
            //generar nombre archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append("ant_").append(getObjeto().getId()).append("_").append(objectoAdjunto.getMaeTipoArchivoCodigo())
                    .append("_").append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            objectoAdjunto.setArchivo(nombreArchivo.append(objectoAdjunto.getExtension()).toString());
            listaAntAdjuntos.add(objectoAdjunto);
            objectoAdjunto = new AntAnticipoAdjunto();
            PrimeFaces.current().ajax().update("frmAdjunto");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void cargarAutorizarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAutorizarAdjunto = event.getFile();
        try {
            if (objectoAdjunto.getMaeTipoArchivoId() == 0) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }

            String ruta = PropApl.getInstance().get(PropApl.ANT_RUTA_ANTICIPOS_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                addError("No esta configurada la ruta para los anticipos del sistema");
                return;
            }

            AntAnticipoAdjunto borrarObj = new AntAnticipoAdjunto();
            for (AntAnticipoAdjunto peAdjunto : listaAutorizarAntAdjuntos) {
                if (peAdjunto.getMaeTipoArchivoId() == objectoAdjunto.getMaeTipoArchivoId()) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            listaAutorizarAntAdjuntos.remove(borrarObj);
            objectoAdjunto.setAdjuntoStream(archivoAutorizarAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto);
            objectoAdjunto.setOrigen(AntAnticipoAdjunto.ORIGEN_ANTICIPOS);
            objectoAdjunto.setRuta(ruta);
            objectoAdjunto.setExiste(Boolean.TRUE);
            Maestro tipoArchivo = hashTiposAutorizarAdjuntos.get(objectoAdjunto.getMaeTipoArchivoId());
            if (tipoArchivo != null) {
                objectoAdjunto.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                objectoAdjunto.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                objectoAdjunto.setMaeTipoArchivoTipo(tipoArchivo.getTipo());
            }
            //generar nombre archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append("ant_autorizado_").append(getObjeto().getId())
                    .append("_").append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            objectoAdjunto.setArchivo(nombreArchivo.append(objectoAdjunto.getExtension()).toString());
            listaAutorizarAntAdjuntos.add(objectoAdjunto);
            objectoAdjunto = new AntAnticipoAdjunto();
            PrimeFaces.current().ajax().update("frmAutorizarAdjunto:pnlSubir");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void borrarAnticiposAdjunto(String nombre) {
        AntAnticipoAdjunto borraObjecto = new AntAnticipoAdjunto();
        for (AntAnticipoAdjunto peAdjunto : listaAntAdjuntos) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        listaAntAdjuntos.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjunto");
    }

    public void borrarAnticiposAutorizarAdjunto(String nombre) {
        AntAnticipoAdjunto borraObjecto = new AntAnticipoAdjunto();
        for (AntAnticipoAdjunto peAdjunto : listaAutorizarAntAdjuntos) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        listaAutorizarAntAdjuntos.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAutorizarAdjunto");
    }

    public void descargarAnticipoAdjunto(AntAnticipoAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public StreamedContent descargarAnticipo() {
        StreamedContent streamContent = null;
        LocalDate fechaActl = LocalDate.now();
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAnticipoServicio().Accion(this);
        try {
            if (!super.isError()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anticipo.jasper");
                //JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(null);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource((Collection<?>) reporteAnticipo);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                parameters.put(ReporteAnticipo.LISTA_ITEMS, new JRBeanCollectionDataSource((Collection<?>) reporteAnticipo.get(0).getListItems()));
                //parameters.put(AucHospitalizacion.LISTA_ESTANCIAS, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion.get(0).getListaEstancias()));
                //parameters.put(AucHospitalizacion.LISTA_DIAGNOSTICOS_EGRESO, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion.get(0).getListaDiagnosticosEgreso()));
                //parameters.put(AucHospitalizacion.LISTA_SEGUIMIENTO, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion));
                //parameters.put(AucHospitalizacion.LISTA_ESTANCIAS_PROLONGADAS, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                //nombre del archivo
                //AntAnticipo anticipo = getAnticipoServicio().consultarAnticipoId(idAnticipo, this);
                StringBuilder nombreArchivo = new StringBuilder();
                //nombreArchivo.append(anticipo.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
                //nombreArchivo.append(anticipo.getAsegAfiliadosId().getNumeroDocumento());
                nombreArchivo.append("ant_formato_");
                nombreArchivo.append(getObjeto().getId());
                nombreArchivo.append("_");
                nombreArchivo.append(fechaActl.toString().replace("-", ""));
                //crea el archivo
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").name(nombreArchivo + ".pdf").stream(() -> stream).build();              
            }
        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamContent;
    }

    public boolean eventoBloqueBotonAfiliado() {
        boolean validar = true;
        if (getObjeto().getTipo() != null) {
            if (getObjeto().getTipo().equals(AntAnticipo.TIPO_INDIVIDUAL)) {
                validar = false;
            }

        }
        return validar;
    }

    public void limpiarCaposAfiliado() {

        if (getObjeto().getTipo() != null) {
            if (!getObjeto().getTipo().equals(AntAnticipo.TIPO_INDIVIDUAL)) {
                getObjeto().setAsegAfiliadosId(null);
            }
        }
    }
    
    public void metodoRecalculoValorApagar(){
        getObjeto().setValorPagado(getObjeto().getValorCotizado().subtract(getObjeto().getValorCotizado().multiply(getObjeto().getRetencionAplicada().divide(new BigDecimal(100)))));
    }
    
    public boolean habilitarEditarPorEstado(int estado) {
        boolean validate = false;
        switch (estado) {
            case AntAnticipo.ESTADO_PENDIENTE_COTIZACION:
                validate = true;
                break;
        }
        return validate;
    }

    public boolean habilitarGestionarPorEstado(int estado) {
        boolean validate = true;
        switch (estado) {
            case AntAnticipo.ESTADO_GESTION_FIRMAS:
                validate = false;
                break;
            case AntAnticipo.ESTADO_AUTORIZADO:
                validate = false;
                break;
            case AntAnticipo.ESTADO_CANCELADO:
                validate = false;
                break;
            case AntAnticipo.ESTADO_DEVUELTO:
                validate = false;
                break;
            case AntAnticipo.ESTADO_PAGADO:
                validate = false;
                break;    
            case AntAnticipo.ESTADO_CON_COTIZACION:
                validate = false;
                break;  
        }
        return validate;
    }
    
    public boolean habilitarGestionarPorEstadoAdjunto(int estado) {
        boolean validate = true;
        switch (estado) {
            case AntAnticipo.ESTADO_GESTION_FIRMAS:
                validate = false;
                break;
            case AntAnticipo.ESTADO_AUTORIZADO:
                validate = false;
                break;
            case AntAnticipo.ESTADO_CANCELADO:
                validate = false;
                break;
            case AntAnticipo.ESTADO_DEVUELTO:
                validate = false;
                break;  
        }
        return validate;
    }
    
    public boolean habilitarDescargaAnticipoPorEstado(int estado) {
        boolean validate = false;
        switch (estado) {
            case AntAnticipo.ESTADO_CON_COTIZACION:
                validate = true;
                break;
            case AntAnticipo.ESTADO_GESTION_FIRMAS:
                validate = true;
                break;
        }
        return validate;
    }

    public boolean habilitarDevolverPorEstado(int estado) {
        boolean validate = false;
        switch (estado) {
            case AntAnticipo.ESTADO_GESTION_FIRMAS:
                validate = true;
                break;
            case AntAnticipo.ESTADO_AUTORIZADO:
                validate = true;
                break;
            case AntAnticipo.ESTADO_GESTION_TESORERIA:
                validate = true;
                break;
            case AntAnticipo.ESTADO_CON_COTIZACION:
                validate = true;
                break;
        }
        return validate;
    }

    public boolean habilitarAutorizarPorEstado(int estado) {
        boolean validate = false;
        switch (estado) {
            case AntAnticipo.ESTADO_GESTION_FIRMAS:
                validate = true;
                break;
        }
        return validate;
    }

    public boolean habilitarCancelarPorEstado(int estado) {
        boolean validate = false;
        switch (estado) {
            case AntAnticipo.ESTADO_GESTION_FIRMAS:
                validate = true;
                break;
            case AntAnticipo.ESTADO_PENDIENTE_COTIZACION:
                validate = true;
                break;
            case AntAnticipo.ESTADO_CON_COTIZACION:
                validate = true;
                break;
        }
        return validate;
    }

    public boolean habilitarPagoPorEstado(int estado) {
        boolean validate = false;
        switch (estado) {
            case AntAnticipo.ESTADO_AUTORIZADO:
                validate = true;
                break;
        }
        return validate;
    }
    
    public void metodoHabilitarCampoRetencionSugerida(){
        getObjeto().setHabilitarRetecionSugerida(true);
        if(getObjeto().isAplicaRetencion()){
            getObjeto().setHabilitarRetecionSugerida(false);
        }
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmAnticipos");
                    crearLog("Listar");
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmAnticipos");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmAnticipos");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmAnticipos");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog("Ver Anticipo", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Ver Anticipo Item", getObjetoItem().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Ver Anticipo Gestión", getAntAnticipoGestion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            crearLog("Ver Anticipo Valor", getObjetoAnticipoValor().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog("Editar");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch(getTakeAccion()){
                                case Url.ACCION_VER:
                                   crearLog("Ver Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_EDITAR:
                                    crearLog("Editar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    crearLog("modificar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_BORRAR:
                                   crearLog("Borrar Item", getObjetoItem().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Ver gestión", getAntAnticipoGestion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            crearLog("Ver Anticipo Valor", getObjetoAnticipoValor().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Gestionar");
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch(getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    crearLog("Crear Adjunto Cotización");
                                    break;
                                case Url.ACCION_GUARDAR:
                                    crearLog("Guardar Adjunto Cotización");
                                    break;
                            }
                            break;
                        case Url.ACCION_CREAR:
                            switch(getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    crearLog("Crear gestión");
                                    break;
                                case Url.ACCION_GUARDAR:
                                    crearLog("Guardar gestión", getAntAnticipoGestion().toString());
                                    break;
                                case Url.ACCION_VER:
                                    crearLog("Ver gestión", getAntAnticipoGestion().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            PrimeFaces.current().ajax().update("frmAnticipos");
                            crearLog("Guardar Gestionar", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            switch(getTakeAccion()){
                                case Url.ACCION_EDITAR:
                                    crearLog("Editar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    crearLog("Modificar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_VER:
                                    crearLog("Ver Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_BORRAR:
                                    crearLog("Borrar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_GUARDAR:
                                    crearLog("guardar Item", getObjetoItem().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            crearLog("Ver Anticipo Valor", getObjetoAnticipoValor().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Generar Reporte");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Archivo  Anticipo", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_3:
                            crearLog("Devolver Anticipo");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmAnticipos");
                            crearLog("Guardar Devolver Anticipo", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_4:
                            crearLog("Autorizar Anticipo");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmAnticipos");
                            crearLog("Guardar Autorizar Anticipo", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            crearLog("Cancelar Anticipo");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmAnticipos");
                            crearLog("Guardar Cancelar Anticipo", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_6:
                            crearLog("Pago Anticipo");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmAnticipos");
                            crearLog("Guardar Pago Anticipo", getObjeto().toString());
                            break;
                    }
                    break;

            }
        }
        generarMensajes();
    }
}
