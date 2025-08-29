/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaNovedad;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaUsuarioActual;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio.CmAuditoriaLiderFacturaServicioIface;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jperezn
 */
@Named("cmAuditoriaLiderFacturaBean")
@ViewScoped
public  class CmAuditoriaLiderFacturaBean extends Url {

    @Autowired
    private CmAuditoriaLiderFacturaServicioIface cmAuditoriaLiderFacturaServicio;
    private CmFactura objeto;
    public static final String TEXTO_ITEM_NO_DEFINIDO = "No definido";
       
    private LazyDataModel<CmFactura> lazyCmAuditoriaFactuar;
    private LazyDataModel<CmDetalle> lazyCmAuditoriaDetalles;
    private LazyDataModel<CmAuditoriaNovedad> lazyCmAuditoriaNovedad;
    private LazyDataModel<CmFactura> lazyGlosaFactura;
    private LazyDataModel<CmDetalle> lazyGlosaFacturaDetalle;
    
    private List<CmFactura> registros;   
    private List<CmDetalle> registrosAuditoriaDetalles;
    private List<CmFactura> registrosGlosaFactura;
    private List<CmDetalle> registrosGlosaFacturaDetalle; 
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjuto; 
    private List<CmDetalle> registrosDetallesSeleccionadoMasivos; 
    private List<CmDetalle> registrosDetallesGlosar;
 
    private CmFactura glosaFactura;
    private CmDetalle objetoItemServicio;
    private List<CmAuditoriaMotivoGlosa> listaMotivos;
    private List<CmAuditoriaAutorizacion> listaAutorizacion;  
    private List<CmAuditoriaConceptoContable> listaConceptosContables;  
    private List<CmAuditoriaDiagnostico> listaDiagnosticos; 
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private CmAuditoriaMotivoGlosa cmAuditoriaMotivoGlosa;
    private CmAuditoriaAutorizacion cmAuditoriaAutorizacion;
    private CmAuditoriaConceptoContable cmAuditoriaConceptoContable;
    private CmAuditoriaDiagnostico cmAuditoriaDiagnostico;
    private CmAuditoriaNovedad cmAuditoriaNovedad;
    private CmAuditoriaCapitaDescuento cmAuditoriaCapitaDescuento;
    private CmAuditoriaUsuarioActual cmAuditoriaUsuarioActual;
      
    private ParamConsulta paramConsultaUtilitario;
    private ParamConsulta paramConsultaServiciosAuditoria;
    private ParamConsulta paramConsultaGlosaFacturaDetalles;
    private ParamConsulta paramConsultaRespuestaFactura;
    private ParamConsulta paramConsultaAuditoriaAdjunto;

    public static final char ACCION_VER_AUDITORIA_FACTURA = 'A';
    public static final char ACCION_LISTAR_AUDITORIA_DETALLES = 'B';
    public static final char ACCION_VER_FORMULARIO_REASIGNACION = 'C';
    public static final char ACCION_LISTAR_GLOSA_FACTURA_DETALLES = 'D';
    public static final char ACCION_VER_AUDITORIA_DETALLE = 'F';
    public static final char ACCION_LISTAR_ADJUNTOS = 'K';
     
    private String descripcionGenerica;
    
    private String facturasBloquedas;
    private String facturasAfectadasLog;
    
    private boolean esGestionIndividual;
    private  Map< String , Integer> listaTiposEstadoAuditoria;
    
    private SelDiagnosticosBean diagnosticosBean;
    
    private List<CmFactura> registrosFacturaSelected;
    private HashMap<Integer, CmFactura> hashFacturasSelecionadas;
    private boolean seleccionTodasFacturas = false;
    private List<Usuario> listaGnUsuarios;
    private int tipoUsuarioAsignar;
    private Usuario gnUsuario;
    private CmGrupoUsuario cmGrupoUsuario;
    private List<CmFactura> listAsignar = new ArrayList<>();
    private boolean debeConfirmar;
    private BigDecimal sumatoriaValorFacturado;
    
    private CmGrupoUsuarioRemoto getCmGrupoUsuarioRemoto() throws Exception {
        return (CmGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("CmGrupoUsuarioServicio", CmGrupoUsuarioRemoto.class.getName());
    }
    
    public CmFactura getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFactura objeto) {
        this.objeto = objeto;
    }
 
    public List<CmFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFactura> registros) {
        this.registros = registros;
    }

    public List<CmAuditoriaMotivoGlosa> getListaMotivos() {
        if (listaMotivos == null) {
            listaMotivos = new ArrayList<>();
        }
        return listaMotivos;
    }

    public void setListaMotivos(List<CmAuditoriaMotivoGlosa> listaMotivos) {
        this.listaMotivos = listaMotivos;
    }

    public List<CmAuditoriaAutorizacion> getListaAutorizacion() {
        if (listaAutorizacion == null) {
            listaAutorizacion = new ArrayList<>();
        }
        return listaAutorizacion;
    }

    public void setListaAutorizacion(List<CmAuditoriaAutorizacion> listaAutorizacion) {
        this.listaAutorizacion = listaAutorizacion;
    }

    public List<CmAuditoriaDiagnostico> getListaDiagnosticos() {
        if (listaDiagnosticos == null) {
            listaDiagnosticos = new ArrayList<>();
        }
        return listaDiagnosticos;
    }

    public void setListaDiagnosticos(List<CmAuditoriaDiagnostico> listaDiagnosticos) {
        this.listaDiagnosticos = listaDiagnosticos;
    }
    
   
    public List<CmAuditoriaConceptoContable> getListaConceptosContables() {
        if (listaConceptosContables == null) {
            listaConceptosContables = new ArrayList<>();
        }
        return listaConceptosContables;
    }

    public void setListaConceptosContables(List<CmAuditoriaConceptoContable> listaConceptosContables) {
        this.listaConceptosContables = listaConceptosContables;
    }
    
    public ParamConsulta getParamConsultaUtilitario() {
        if (paramConsultaUtilitario == null) {
            paramConsultaUtilitario = new ParamConsulta();
        }
        return paramConsultaUtilitario;
    }

    public ParamConsulta getParamConsultaServiciosAuditoria() {
        if (paramConsultaServiciosAuditoria == null) {
            paramConsultaServiciosAuditoria = new ParamConsulta();
        }
        return paramConsultaServiciosAuditoria;
    }

    public ParamConsulta getParamConsultaAuditoriaAdjunto() {
        if (paramConsultaAuditoriaAdjunto == null) {
            paramConsultaAuditoriaAdjunto = new ParamConsulta();
        }
        return paramConsultaAuditoriaAdjunto;
    }

   
    public void setParamConsultaAuditoriaAdjunto(ParamConsulta paramConsultaAuditoriaAdjunto) {
        this.paramConsultaAuditoriaAdjunto = paramConsultaAuditoriaAdjunto;
    }
    
    public void setParamConsultaServiciosAuditoria(ParamConsulta paramConsultaServiciosAuditoria) {
        this.paramConsultaServiciosAuditoria = paramConsultaServiciosAuditoria;
    }
    
    public void setParamConsultaUtilitario(ParamConsulta paramConsultaUtilitario) {
        this.paramConsultaUtilitario = paramConsultaUtilitario;
    }

    public CmAuditoriaLiderFacturaServicioIface getCmAuditoriaLiderFacturaServicio() {
        return cmAuditoriaLiderFacturaServicio;
    }

    public void setCmAuditoriaLiderFacturaServicio(CmAuditoriaLiderFacturaServicioIface cmAuditoriaLiderFacturaServicio) {
        this.cmAuditoriaLiderFacturaServicio = cmAuditoriaLiderFacturaServicio;
    }

    public LazyDataModel<CmFactura> getLazyCmAuditoriaFactuar() {
        return lazyCmAuditoriaFactuar;
    }

    public void setLazyCmAuditoriaFactuar(LazyDataModel<CmFactura> lazyCmAuditoriaFactuar) {
        this.lazyCmAuditoriaFactuar = lazyCmAuditoriaFactuar;
    }

    public LazyDataModel<CmDetalle> getLazyCmAuditoriaDetalles() {
        return lazyCmAuditoriaDetalles;
    }

    public void setLazyCmAuditoriaDetalles(LazyDataModel<CmDetalle> lazyCmAuditoriaDetalles) {
        this.lazyCmAuditoriaDetalles = lazyCmAuditoriaDetalles;
    }

    public LazyDataModel<CmAuditoriaNovedad> getLazyCmAuditoriaNovedad() {
        return lazyCmAuditoriaNovedad;
    }

    public void setLazyCmAuditoriaNovedad(LazyDataModel<CmAuditoriaNovedad> lazyCmAuditoriaNovedad) {
        this.lazyCmAuditoriaNovedad = lazyCmAuditoriaNovedad;
    }

    public List<CmDetalle> getRegistrosAuditoriaDetalles() {
        if (registrosAuditoriaDetalles == null) {
            registrosAuditoriaDetalles = new ArrayList<>();
        }
        return registrosAuditoriaDetalles;
    }

    public void setRegistrosAuditoriaDetalles(List<CmDetalle> registrosAuditoriaDetalles) {
        this.registrosAuditoriaDetalles = registrosAuditoriaDetalles;
    }

    public List<CmFactura> getRegistrosGlosaFactura() {
        return registrosGlosaFactura;
    }

    public void setRegistrosGlosaFactura(List<CmFactura> registrosGlosaFactura) {
        this.registrosGlosaFactura = registrosGlosaFactura;
    }


    public ParamConsulta getParamConsultaRespuestaFactura() {
        if (paramConsultaRespuestaFactura == null) {
            paramConsultaRespuestaFactura = new ParamConsulta();
        }
        return paramConsultaRespuestaFactura;
    }

    public void setParamConsultaRespuestaFactura(ParamConsulta paramConsultaRespuestaFactura) {
        this.paramConsultaRespuestaFactura = paramConsultaRespuestaFactura;
    }

    public List<CmDetalle> getRegistrosGlosaFacturaDetalle() {
        return registrosGlosaFacturaDetalle;
    }

    public void setRegistrosGlosaFacturaDetalle(List<CmDetalle> registrosGlosaFacturaDetalle) {
        this.registrosGlosaFacturaDetalle = registrosGlosaFacturaDetalle;
    }

    public ParamConsulta getParamConsultaGlosaFacturaDetalles() {
        if (paramConsultaGlosaFacturaDetalles == null) {
            paramConsultaGlosaFacturaDetalles = new ParamConsulta();
        }
        return paramConsultaGlosaFacturaDetalles;
    }

    public void setParamConsultaGlosaFacturaDetalles(ParamConsulta paramConsultaGlosaFacturaDetalles) {
        this.paramConsultaGlosaFacturaDetalles = paramConsultaGlosaFacturaDetalles;
    }

    public LazyDataModel<CmDetalle> getLazyGlosaFacturaDetalle() {
        return lazyGlosaFacturaDetalle;
    }

    public void setLazyGlosaFacturaDetalle(LazyDataModel<CmDetalle> lazyGlosaFacturaDetalle) {
        this.lazyGlosaFacturaDetalle = lazyGlosaFacturaDetalle;
    }
    
    public LazyDataModel<CmFactura> getLazyGlosaFactura() {
        return lazyGlosaFactura;
    }    

    public void setLazyGlosaFactura(LazyDataModel<CmFactura> lazyGlosaFactura) {
        this.lazyGlosaFactura = lazyGlosaFactura;
    }

    public CmFactura getGlosaFactura() {
        return glosaFactura;
    }

    public void setGlosaFactura(CmFactura glosaFactura) {
        this.glosaFactura = glosaFactura;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public List<CmAuditoriaAdjunto> getRegistrosAuditoriaAdjuto() {
        if (registrosAuditoriaAdjuto == null) {
            registrosAuditoriaAdjuto = new ArrayList<>();
        }
        return registrosAuditoriaAdjuto;
    }

    public void setRegistrosAuditoriaAdjuto(List<CmAuditoriaAdjunto> registrosAuditoriaAdjuto) {
        this.registrosAuditoriaAdjuto = registrosAuditoriaAdjuto;
    }

    public List<CmDetalle> getRegistrosDetallesSeleccionadoMasivos() {
        if (registrosDetallesSeleccionadoMasivos == null) {
            registrosDetallesSeleccionadoMasivos = new ArrayList<>();
        }
        return registrosDetallesSeleccionadoMasivos;
    }

    public void setRegistrosDetallesSeleccionadoMasivos(List<CmDetalle> registrosDetallesSeleccionadoMasivos) {
        this.registrosDetallesSeleccionadoMasivos = registrosDetallesSeleccionadoMasivos;
    }   

    public List<CmDetalle> getRegistrosDetallesGlosar() {
        if (registrosDetallesGlosar == null) {
            registrosDetallesGlosar = new ArrayList<>();
        }
        return registrosDetallesGlosar;
    }

    public void setRegistrosDetallesGlosar(List<CmDetalle> registrosDetallesGlosar) {
        this.registrosDetallesGlosar = registrosDetallesGlosar;
    }
     

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
    }

    public CmAuditoriaMotivoGlosa getCmAuditoriaMotivoGlosa() {
        if (cmAuditoriaMotivoGlosa == null) {
            cmAuditoriaMotivoGlosa = new CmAuditoriaMotivoGlosa();
        }
        return cmAuditoriaMotivoGlosa;
    }

    public void setCmAuditoriaMotivoGlosa(CmAuditoriaMotivoGlosa cmAuditoriaMotivoGlosa) {
        this.cmAuditoriaMotivoGlosa = cmAuditoriaMotivoGlosa;
    }

    public CmAuditoriaAutorizacion getCmAuditoriaAutorizacion() {
        if (cmAuditoriaAutorizacion == null) {
            cmAuditoriaAutorizacion = new CmAuditoriaAutorizacion();
        }
        return cmAuditoriaAutorizacion;
    }

    public void setCmAuditoriaAutorizacion(CmAuditoriaAutorizacion cmAuditoriaAutorizacion) {
        this.cmAuditoriaAutorizacion = cmAuditoriaAutorizacion;
    }

    public CmAuditoriaConceptoContable getCmAuditoriaConceptoContable() {
        if (cmAuditoriaConceptoContable == null) {
            cmAuditoriaConceptoContable = new CmAuditoriaConceptoContable();
        }
        return cmAuditoriaConceptoContable;
    }

    public void setCmAuditoriaConceptoContable(CmAuditoriaConceptoContable cmAuditoriaConceptoContable) {
        this.cmAuditoriaConceptoContable = cmAuditoriaConceptoContable;
    }

    public CmAuditoriaDiagnostico getCmAuditoriaDiagnostico() {
        if (cmAuditoriaDiagnostico == null) {
            cmAuditoriaDiagnostico= new CmAuditoriaDiagnostico();
        }
        return cmAuditoriaDiagnostico;
    }

    public void setCmAuditoriaDiagnostico(CmAuditoriaDiagnostico cmAuditoriaDiagnostico) {
        this.cmAuditoriaDiagnostico = cmAuditoriaDiagnostico;
    }

    public CmAuditoriaNovedad getCmAuditoriaNovedad() {
        if (cmAuditoriaNovedad == null) {
            cmAuditoriaNovedad= new CmAuditoriaNovedad();
        }
        return cmAuditoriaNovedad;
    }

    public void setCmAuditoriaNovedad(CmAuditoriaNovedad cmAuditoriaNovedad) {
        this.cmAuditoriaNovedad = cmAuditoriaNovedad;
    }

    public CmAuditoriaUsuarioActual getCmAuditoriaUsuarioActual() {
        if(cmAuditoriaUsuarioActual == null){
          cmAuditoriaUsuarioActual = new CmAuditoriaUsuarioActual();
        }
        return cmAuditoriaUsuarioActual;
    }

    public void setCmAuditoriaUsuarioActual(CmAuditoriaUsuarioActual cmAuditoriaUsuarioActual) {
        this.cmAuditoriaUsuarioActual = cmAuditoriaUsuarioActual;
    }
    
    

    public CmAuditoriaCapitaDescuento getCmAuditoriaCapitaDescuento() {
        if (cmAuditoriaCapitaDescuento == null) {
            cmAuditoriaCapitaDescuento= new CmAuditoriaCapitaDescuento();
        }
        return cmAuditoriaCapitaDescuento;
    }

    public void setCmAuditoriaCapitaDescuento(CmAuditoriaCapitaDescuento cmAuditoriaCapitaDescuento) {
        this.cmAuditoriaCapitaDescuento = cmAuditoriaCapitaDescuento;
    } 
    
    public CmDetalle getObjetoItemServicio() {
        if(objetoItemServicio == null){
            objetoItemServicio = new CmDetalle();
        }
        return objetoItemServicio;
    }

    public void setObjetoItemServicio(CmDetalle objetoItemServicio) {
        this.objetoItemServicio = objetoItemServicio;
    }

    public boolean isEsGestionIndividual() {
        return esGestionIndividual;
    }

    public void setEsGestionIndividual(boolean esGestionIndividual) {
        this.esGestionIndividual = esGestionIndividual;
    }

    public Map<String, Integer> getListaTiposEstadoAuditoria() {
         if(listaTiposEstadoAuditoria == null){
             listaTiposEstadoAuditoria = new HashMap<>();
        }
        return listaTiposEstadoAuditoria;
    }

    public void setListaTiposEstadoAuditoria(Map<String, Integer> listaTiposEstadoAuditoria) {
        this.listaTiposEstadoAuditoria = listaTiposEstadoAuditoria;
    }

    public String getFacturasBloquedas() {
        return facturasBloquedas;
    }

    public void setFacturasBloquedas(String facturasBloquedas) {
        this.facturasBloquedas = facturasBloquedas;
    }

    public String getFacturasAfectadasLog() {
        return facturasAfectadasLog;
    }

    public void setFacturasAfectadasLog(String facturasAfectadasLog) {
        this.facturasAfectadasLog = facturasAfectadasLog;
    }
    
     public boolean isDebeConfirmar() {
        return debeConfirmar;
    }

    public void setDebeConfirmar(boolean debeConfirmar) {
        this.debeConfirmar = debeConfirmar;
    } 

    public List<CmFactura> getListAsignar() {
        return listAsignar;
    }

    public void setListAsignar(List<CmFactura> listAsignar) {
        this.listAsignar = listAsignar;
    }
    
    public List<Usuario> getListaGnUsuarios() {
        return listaGnUsuarios;
    }

    public void setListaGnUsuarios(List<Usuario> listaGnUsuarios) {
        this.listaGnUsuarios = listaGnUsuarios;
    }

    public int getTipoUsuarioAsignar() {
        return tipoUsuarioAsignar;
    }

    public void setTipoUsuarioAsignar(int tipoUsuarioAsignar) {
        this.tipoUsuarioAsignar = tipoUsuarioAsignar;
    }

    public CmGrupoUsuario getCmGrupoUsuario() {
        if(cmGrupoUsuario == null){
            cmGrupoUsuario = new CmGrupoUsuario();
        }
        return cmGrupoUsuario;
    }

    public void setCmGrupoUsuario(CmGrupoUsuario cmGrupoUsuario) {
        this.cmGrupoUsuario = cmGrupoUsuario;
    }
    
    

    public List<CmFactura> getRegistrosFacturaSelected() {
        return registrosFacturaSelected;
    }

    public void setRegistrosFacturaSelected(List<CmFactura> registrosFacturaSelected) {
        this.registrosFacturaSelected = registrosFacturaSelected;
    }

    public HashMap<Integer, CmFactura> getHashFacturasSelecionadas() {
        if (hashFacturasSelecionadas == null) {
            hashFacturasSelecionadas = new HashMap<>();
        }
        return hashFacturasSelecionadas;
    }

    public void setHashFacturasSelecionadas(HashMap<Integer, CmFactura> hashFacturasSelecionadas) {
        this.hashFacturasSelecionadas = hashFacturasSelecionadas;
    }

    public boolean isSeleccionTodasFacturas() {
        return seleccionTodasFacturas;
    }

    public void setSeleccionTodasFacturas(boolean seleccionTodasFacturas) {
        this.seleccionTodasFacturas = seleccionTodasFacturas;
    }

    public BigDecimal getSumatoriaValorFacturado() {
        return sumatoriaValorFacturado;
    }

    public void setSumatoriaValorFacturado(BigDecimal sumatoriaValorFacturado) {
        this.sumatoriaValorFacturado = sumatoriaValorFacturado;
    }
    


    public CmAuditoriaLiderFacturaBean() {
        this.objeto = new CmFactura();
        this.cmAuditoriaDevolucion = new CmAuditoriaDevolucion();
        Modulo mod = super.validarModulo(Modulo.ID_FACTURA_AUDITORIA_LIDER);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
     
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCmAuditoriaFactuar = new LazyDataModel<CmFactura>() {
                private List<CmFactura> auditoriaFacturas;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
                @Override
                public List<CmFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    auditoriaFacturas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    
                    //agrega registros hash datos
                    if (auditoriaFacturas != null) {
                        for (CmFactura factura : auditoriaFacturas) {
                            if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                                getRegistrosFacturaSelected().add(factura);
                            }
                        }
                    }
                    return auditoriaFacturas;
                }

                @Override
                public String getRowKey(CmFactura auditoriaFactura) {
                    return auditoriaFactura.getId().toString();
                }

                @Override
                public CmFactura getRowData(String auditoriaFacturaId) {
                    Integer id = Integer.valueOf(auditoriaFacturaId);
                    for (CmFactura auditoriaFactura : auditoriaFacturas) {
                        if (id.equals(auditoriaFactura.getId())) {
                            return auditoriaFactura;
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
        getCmAuditoriaLiderFacturaServicio().cargaInicial(this);
        listar();
    }
    
    @PreDestroy
    public void preDestroy() {
        this.objeto = null;
        this.cmAuditoriaDevolucion = null;
    }
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }
    
    public void verFactura(int idFactura){
        this.getObjeto().setId(idFactura);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_FACTURA);
        getCmAuditoriaLiderFacturaServicio() .Accion(this);
        inicializarTablaDetallesAuditoria();
        refrescarAdjuntos();
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        super.setDoAccion(ACCION_VER_AUDITORIA_FACTURA);
        procesoFinal();
    }
    
     public void verRespuestaDestallesGlosa(CmFactura factura) {
        this.setGlosaFactura(factura);
        inicializarTablaGlosaFacturaDetalles(factura.getId());
        PrimeFaces.current().resetInputs("frmVerGlosaRespuesas");
        PrimeFaces.current().ajax().update("frmVerGlosaRespuesas");
        PrimeFaces.current().executeScript("PF('dialogoVerGlosaRespuestaDetalles').show()");
        procesoFinal();
    }
    
    public SelDiagnosticosBean getSelDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }
      
     private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                  switch (getDoAccion()) {
                      case ACCION_VER_AUDITORIA_FACTURA:
                           crearLog("Ver Factura Auditar",getObjeto().toString());
                      break;
                        case ACCION_VER_FORMULARIO_REASIGNACION:
                           CmFactura factura = listAsignar != null && listAsignar.size() > 0 ? listAsignar.get(0) : getObjeto();
                           crearLog("Ver Fomulario Reasignacion", factura.toString());
                      break;
                    }
                break;
            case Url.ACCION_CREAR:
                crearLog("Crear Reporte CM", getObjeto().toString());
                PrimeFaces.current().ajax().update("frmConsultaInformes");
                break;
            case Url.ACCION_LISTAR:
                crearLog("Listar Facturas Reasignar", getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
            case Url.ACCION_ADICIONAL_4:
                CmFactura factura = listAsignar != null && listAsignar.size() > 0 ? listAsignar.get(0) : getObjeto();
                crearLog("Guardar Reasignación", factura.toString());
                PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                break;
            
        }
        generarMensajes();
    }
     
      

    public boolean validarExisenciaNitIps() {
        boolean isValido = true;
        if (this.getObjeto().getNit().equals("") && this.getObjeto().getIps().equals("")) {
            this.addError(" Para realizar la búsqueda debe incluir el Nit o Ips. ");
            isValido = false;
        }
        return isValido;
    }
    
   //Borrar
    public void inicializarTablaDetallesAuditoria() {
        this.setParamConsultaServiciosAuditoria(new ParamConsulta());
        lazyCmAuditoriaDetalles = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaServiciosAuditoria().getCantidadRegistros();
            }

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaServiciosAuditoria().setPrimerRegistro(primerRegistro);
                getParamConsultaServiciosAuditoria().setRegistrosPagina(registrosPagina);
                getParamConsultaServiciosAuditoria().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaServiciosAuditoria().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetalles();
                lista = getRegistrosAuditoriaDetalles();
                setRowCount(getParamConsultaServiciosAuditoria().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle detalle) {
                return detalle.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmDetalle cmDetalle : lista) {
                    if (id.equals(cmDetalle.getId())) {
                        return cmDetalle;
                    }
                }
                return null;
            }
        };
    }
    
 
     public void inicializarTablaGlosaFacturaDetalles(int _idGlosaRespuesta) {
        this.setParamConsultaGlosaFacturaDetalles(new ParamConsulta());
        this.getParamConsultaGlosaFacturaDetalles().setParametroConsulta1(_idGlosaRespuesta);
        this.getParamConsultaGlosaFacturaDetalles().setEmpresaId(super.getConexion().getEmpresa().getId());
        lazyGlosaFacturaDetalle = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;
                
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaGlosaFacturaDetalles().getCantidadRegistros();
            }
			

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaGlosaFacturaDetalles().setPrimerRegistro(primerRegistro);
                getParamConsultaGlosaFacturaDetalles().setRegistrosPagina(registrosPagina);
                getParamConsultaGlosaFacturaDetalles().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaGlosaFacturaDetalles().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGlosaRespuestaDetalle();
                lista = getRegistrosGlosaFacturaDetalle();
                setRowCount(getParamConsultaGlosaFacturaDetalles().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle cmGlosaDetalle) {
                return cmGlosaDetalle.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmDetalle cmGlosaDetalle : lista) {
                    if (id.equals(cmGlosaDetalle.getId())) {
                        return cmGlosaDetalle;
                    }
                }
                return null;
            }
        };
    }
     
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmAuditoriaLiderFacturaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");    
    }
    
    public void refrescarDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUDITORIA_DETALLES);
        getCmAuditoriaLiderFacturaServicio().Accion(this);
    }
    
 
    
     public void refrescarGlosaRespuestaDetalle() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_GLOSA_FACTURA_DETALLES);
        getCmAuditoriaLiderFacturaServicio().Accion(this);
    }
    
  
    public void refrescarAdjuntos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS);
        getCmAuditoriaLiderFacturaServicio().Accion(this);
    }
    
 
    
          
    public StreamedContent generarReporteRespuestaGlosa(Integer id) {
       
        StreamedContent streamedContent2 = null;
        return streamedContent2;
    }
      
     public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
     
     
  
    public void  descargarAdjunto(CmAuditoriaAdjunto adj){
        String rutaCompleta = adj.getArchivoRuta() + adj.getArchivoNombre();
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
            String attachmentName = "attachment; filename=\"" + adj.getArchivoNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/doc");
            } else if (ext.equalsIgnoreCase(".docx")) {
                ec.setResponseContentType("application/docx");
            } else if (ext.equalsIgnoreCase(".xls")) {
                ec.setResponseContentType("application/xls");
            } else if (ext.equalsIgnoreCase(".xlsx")) {
                ec.setResponseContentType("application/xlsx");
            } else if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo:"+e.getMessage());
            generarMensajes();
        }
     }
 
    
    public void cerrarVentanaGestionMasiva(){
        PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
    }
    
    public void limpiarRegistrosTemporales() {
        setRegistrosAuditoriaAdjuto(new ArrayList<>());
    }
    
    public boolean verificarEsReasignable(CmFactura factura) {
        boolean facturaReasignable = false;
        if (factura != null) {
            facturaReasignable = factura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_SIN_AUDITORIA
                    || factura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_EN_AUDITORIA;
        }
        return facturaReasignable;
    }
 
    
    public void verAsignarIndividual(CmFactura fc) {
        
        PrimeFaces.current().resetInputs("frmVerAsignacionIndividual");
        PrimeFaces.current().ajax().update("frmVerAsignacionIndividual");
        this.setListaGnUsuarios(new ArrayList<>());
        this.setGnUsuario(null);
        this.tipoUsuarioAsignar = 0;
        listAsignar.clear();
        List<CmFactura> list = new ArrayList<>();
        if(fc.getTipoAuditoria() > 0 && fc.getTipoAuditoria() <= 8){
            this.setDebeConfirmar(true);
        }
        list.add(fc);
        
        if (!this.isError()) {
            listAsignar = list;
            setSumatoriaValorFacturado(new BigDecimal(BigInteger.ZERO));
            this.setSumatoriaValorFacturado(fc.getValorFactura());
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_VER_FORMULARIO_REASIGNACION);
            PrimeFaces.current().executeScript("PF('dialogoVerAsignacionIndividual').show()");
        }
        
        procesoFinal();
    }
    
    public void verAsignarMasivo(){
        
        PrimeFaces.current().resetInputs("frmVerAsignacionIndividual");
        PrimeFaces.current().ajax().update("frmVerAsignacionIndividual");
        this.setGnUsuario(null);
        this.setListaGnUsuarios(new ArrayList<>());
        this.tipoUsuarioAsignar = 0;
        listAsignar.clear();
        List<CmFactura> list = new ArrayList<>();
        if(getHashFacturasSelecionadas().size() <= 0 ){
            PrimeFaces.current().executeScript("PF('tablaWidget').unselectAllRows();");
            addMensaje("Primero debe seleccionar facturas para asignar");
            generarMensajes();
        }else{
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_VER_FORMULARIO_REASIGNACION);
            setSumatoriaValorFacturado(new BigDecimal(BigInteger.ZERO));
            BigDecimal valorSumatoria = new BigDecimal(BigInteger.ZERO);
            for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
                CmFactura fc = entry.getValue();
                if(fc.getTipoAuditoria() > 0 && fc.getTipoAuditoria() <= 8){
                    this.setDebeConfirmar(true);
                }
                list.add(fc);
                valorSumatoria = valorSumatoria.add(fc.getValorFactura());        
            }
            if (!this.isError()) {
                listAsignar = list;
                this.setSumatoriaValorFacturado(valorSumatoria);
                PrimeFaces.current().ajax().update("frmVerAsignacionIndividual");
                PrimeFaces.current().executeScript("PF('dialogoVerAsignacionIndividual').show()");
            }
             procesoFinal();
        }
            
    }
     
    public void cerrarVentanaAsignacionIndividual(){
        
        PrimeFaces.current().resetInputs("frmVerAsignacionIndividual");
        PrimeFaces.current().ajax().update("frmVerAsignacionIndividual");
        PrimeFaces.current().executeScript("PF('tablaWidget').unselectAllRows();");
        listAsignar.clear();
        getHashFacturasSelecionadas().clear();
        this.setDebeConfirmar(false);
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            procesoFinal();
        }
    }
    
    
    public void procesarFacturasSeleccionadas(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (CmFactura factura : getRegistrosFacturaSelected()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
                    getHashFacturasSelecionadas().put(factura.getId(), factura);
                }
            }     
            
            setSeleccionTodasFacturas(true);
            
        } else {
             for (CmFactura factura : getRegistros()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                    getHashFacturasSelecionadas().remove(factura.getId());
                }
            }
            setSeleccionTodasFacturas(false);
        }
    }
       
    public void onRowSelect(SelectEvent event) {
        CmFactura facturaIn = (CmFactura) event.getObject();
        
        if(isSeleccionTodasFacturas()){
           for (CmFactura factura : getRegistros()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                    getHashFacturasSelecionadas().remove(factura.getId());
                }
            }
            setSeleccionTodasFacturas(false);
        }  
        if (getHashFacturasSelecionadas().get(facturaIn.getId()) == null) {
            getHashFacturasSelecionadas().put(facturaIn.getId(), facturaIn);
        }
    }

    public void onRowUnselect(SelectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
            getHashFacturasSelecionadas().remove(factura.getId());
        }
    }
     
    public void selectCheckbox(SelectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
            getHashFacturasSelecionadas().put(factura.getId(), factura);
        }
    }
     
    public void unSelectCheckbox(UnselectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
            getHashFacturasSelecionadas().remove(factura.getId());
        }
    }
    public Usuario getGnUsuario() {
        return gnUsuario;
    }

    public void setGnUsuario(Usuario gnUsuario) {
        this.gnUsuario = gnUsuario;
    }
    
   
    
    public void listenerChangeTipo() {
        try {
            if (CmGrupoUsuario.TIPO_LIDER == this.tipoUsuarioAsignar) {
                buscarTodosTipoUsurioPorEmpresa(super.getConexion().getEmpresa().getId(), CmGrupoUsuario.TIPO_LIDER);
            }else{
                setListaGnUsuarios(getCmGrupoUsuarioRemoto().consultarListaByNitTipoUsuario(super.getConexion().getEmpresa().getId(), this.tipoUsuarioAsignar, "", buscarGrupos()));
            } 
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
    }
    
    public String buscarGrupos(){
        String idGrupos = "";
        try {
            Map<String, String> mapGrupos = new HashMap<>();
            if (!listAsignar.isEmpty()) {
                for (CmFactura item : listAsignar) {
                    Integer id =  item.getCmGrupo().getId();
                    if (id != null) {
                        if (mapGrupos.get(String.valueOf(id)) == null) {
                            mapGrupos.put(String.valueOf(id), String.valueOf(id));
                        }
                    }
                }
            }
            if (!mapGrupos.isEmpty()) {
                idGrupos = String.join(",", mapGrupos.values());
            }
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
        return idGrupos;
    }
    
    public void buscarTodosTipoUsurioPorEmpresa(Integer idEmpresa, int tipoUsuario) {
        try {
            Map<String, String> mapUsuarios = new HashMap<>();
            List<Usuario> usuarios = new ArrayList<>();
            ParamConsulta param = new ParamConsulta();
            
            param.setParametroConsulta1(idEmpresa);
            param.setParametroConsulta2(tipoUsuario);
            List<CmGrupoUsuario> usuariosGrupos = getCmGrupoUsuarioRemoto().consultarPorAtributosLista(param);
            
            usuariosGrupos.forEach(usuariosGrupo -> {
                Integer id = usuariosGrupo.getGnUsuario().getId();
                if (id != null) {
                    if (mapUsuarios.get(String.valueOf(id)) == null) {
                        mapUsuarios.put(String.valueOf(id), String.valueOf(id));
                        usuarios.add(usuariosGrupo.getGnUsuario());
                    }
                }
            });

            setListaGnUsuarios(usuarios);
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
    }
    
    public List<Usuario> autoCompletarUsuario(String nombreUsuario) {
        try {
            if(this.tipoUsuarioAsignar == 0){
                addMensaje("Seleccione un tipo para consultar un usuario");
                generarMensajes();
                return new ArrayList<>();
            }
            setListaGnUsuarios(getCmGrupoUsuarioRemoto().consultarListaByNitTipoUsuario(super.getConexion().getEmpresa().getId(), this.tipoUsuarioAsignar, nombreUsuario,""));
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
        return listaGnUsuarios;
    }

    public void seleccionarUsuario(SelectEvent event) {
        Object item = event.getObject();
        setGnUsuario((Usuario) item);
    }

    

    public void asignarFacturaUsuario() {
        if(listAsignar.size() > 0){
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_GUARDAR);
            getCmAuditoriaLiderFacturaServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoVerAsignacionIndividual').hide()");
                PrimeFaces.current().executeScript("PF('tablaWidget').unselectAllRows();");
                CmFactura factura = listAsignar != null && listAsignar.size() > 0 ? listAsignar.get(0) : getObjeto();
                setObjeto(factura);
                listAsignar.clear();
                getHashFacturasSelecionadas().clear();
                addMensaje("Se ha realizado el proceso de asignación con exito.");
            }
        }
    }
    
     public void generarPorPaginaXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }
        crearLog("Descargar", getObjeto().toString());
    }
     
    public String asignarIndicadorVisualEstadoFactura(int estadoFactura){
        String estilo = "";
        String colorEstado = "";
        switch (estadoFactura) {
            case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA:
            case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION:
            case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION:
                colorEstado = "#bc241b;";
                break;
            case CmFactura.ESTADO_FACTURA_DEVUELTA:
                colorEstado = "#eadc2a;";
                break;
            default:
                break;
        }  
        if(!colorEstado.isEmpty()){
             estilo = "width: 10px; height: 10px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; background:"+colorEstado+"";
        }
        return estilo;
    }
    
    public void clearViewScopedBeans() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmAuditoriaLiderFacturaBean");
    }
     
}
