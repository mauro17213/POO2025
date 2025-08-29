/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCobertura;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoNotaTecnica;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorBean;
import com.saviasaludeps.savia.web.consulta.bean.SelPrestadorSedesGenericoBean;
import com.saviasaludeps.savia.web.contratacion.servicio.ContratosServicioIface;
import com.saviasaludeps.savia.web.contratacion.servicio.ContratosServicioImpl;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2000Bean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2001Bean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelSoatBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
//import com.saviasaludeps.savia.web.singleton.ContratacionSingle;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class ContratosBean extends Url {

    private ContratosServicioIface contratosServicio;
    private CntContratoSede objeto;
    private CntContrato objetoContrato;
    private CntContratoDetalle objetoContratoDetalle;
    private CntContratoNotaTecnica objetoContratoNotaTecnica;
    private List<CntContratoSede> registros;
    private LazyDataModel<CntContratoSede> lazyContratoSedes;
    //lista Tecnologias - Opción Gestion tecnologías
    private List<CntContratoDetalle> listaContratoDetalle;
    private LazyDataModel<CntContratoDetalle> lazyContratoDetalle;
    private ParamConsulta paramConsultaContratoDetalle;    
    //lista de Maestros
    private List<Maestro> listaModalidadContrato;
    private HashMap<Integer, Maestro> hashModalidadContrato;
    private List<Maestro> listaEstadoContrato;
    private HashMap<String, Maestro> hashEstadoContrato;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaClasePrestador;
    private HashMap<Integer, Maestro> hashClasePrestador;
    private List<Maestro> listaAmbito;
    private HashMap<Integer, Maestro> hashAmbito;
    private List<Maestro> listaRegimenContrato;
    private HashMap<Integer, Maestro> hashRegimenContrato;
    //objeto Buscador Prestador
    private SelPrestadorBean selPrestadorBean;
    //objetos para lista de Creación/Edición
    private List<CntContratoSede> listaContratoSedes;
    private List<CntContratoSede> listaContratoSedesBorrar;
    private int contadorIdSede;
    private boolean deshabilitaValorUPC;
    //objeto Buscador Prestador Sedes Generico
    private SelPrestadorSedesGenericoBean selPrestadorSedesGenericoBean;
    private boolean seleccionoPrestador;
    // objetos buscadores de Maestros
    private SelInsumosBean selInsumosBean;
    private SelMedicamentoBean selMedicamentoBean;
    private SelTecnologiasBean selTecnologiasBean;
    private SelPaquetesBean selPaquetesBean;
    private SelIss2000Bean selIss2000Bean;
    private SelIss2001Bean selIss2001Bean;
    private SelSoatBean selSoatBean;
    //lista Ubicaciones
    private List<Ubicacion> listaUbicaciones;
    //lista Servicios habilitacion
    private List<MaTecnologiaServicioHabilitacion> listaServicios;
    //lista Año Tarifa Soat
    private List<MaSoatTarifarioValor> listaValoresSoat;
    private boolean paqueteTecnologia;
    private boolean deshabilitarServicio;
    //2021-06-30 nuevos campos
    private Date fechaActual;
    //2021-07-06 nuevos campos funcionalidad imprimir tecnologias sede
    private List<CntContratoDetalle> listaDetalleTecnologias;
    //2022-06-10 jyperez nuevas variables nota tecnica
    private List<CntContratoNotaTecnica> listaContratoNotaTecnica;
    private LazyDataModel<CntContratoNotaTecnica> lazyContratoNotaTecnica;
    private ParamConsulta paramConsultaContratoNotaTecnica;    
    private List<CntContratoNotaTecnica> listaImpresionNotaTecnica;
    //2023-02-17 jyperez variables para historico de contrato detalle
    private CntContratoHistorico objetoHistorico;
    private LazyDataModel<CntContratoHistorico> lazyHistorico;
    private List<CntContratoHistorico> registrosHistorico;
    private ParamConsulta paramConsultaHistorico;
    
    //2022-04-07 jyperez variable para almacenar la fecha final antes de modificación
    private Date fechaFinContrato;
    private boolean actualizarContratoDetalles;
    
    //2022-06-14 jyperez variables nota tecnica
    private boolean actualizarNotaTecnica;
    
    //2022-07-14 jyperez variables cobertura
    private List<Ubicacion> listaMunicipiosCobertura;
    
//    @Autowired
//    private ContratacionSingle contratacionSingle;
    
    public ContratosBean() {
        // el contador actuara en forma descendente con números negativos. Esto con el fin de poder obtener el id correcto en el manejo de listas
        contadorIdSede = -1;
        fechaActual =  new Date();
        fechaActual.setHours(0);
        fechaActual.setMinutes(0);
        fechaActual.setSeconds(0);
        paqueteTecnologia = false;
        deshabilitarServicio = false;
        deshabilitaValorUPC = true;
        this.objeto = new CntContratoSede();
        this.objetoContrato = new CntContrato();
        this.objetoContratoDetalle = new CntContratoDetalle();
        this.objetoContratoNotaTecnica = new CntContratoNotaTecnica();
        actualizarContratoDetalles = false;
        Modulo mod = super.validarModulo(Modulo.ID_CONTRATACION_CONTRATOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyContratoSedes = new LazyDataModel<CntContratoSede>() {
                private List<CntContratoSede> listaContratos;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<CntContratoSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaContratos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaContratos;
                }

                @Override
                public String getRowKey(CntContratoSede reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public CntContratoSede getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (CntContratoSede contrato : listaContratos) {
                        if (id.equals(contrato.getId())) {
                            return contrato;
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
        getContratosServicio().cargaInicial(this);
        listar();
    }

    public ContratosServicioIface getContratosServicio() {
        if (contratosServicio == null) {
            contratosServicio = new ContratosServicioImpl();
        }
        return contratosServicio;
    }

    public void setContratosServicio(ContratosServicioIface contratosServicio) {
        this.contratosServicio = contratosServicio;
    }

    public CntContratoSede getObjeto() {
        return objeto;
    }

    public void setObjeto(CntContratoSede objeto) {
        this.objeto = objeto;
    }

    public List<CntContratoSede> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntContratoSede> registros) {
        this.registros = registros;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
    }

    public void ver(int id) {
        getObjetoContrato().setId(id);
        super.setAccion(ACCION_VER);
        getContratosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        //setSeleccionoPrestador(false);
        super.setAccion(ACCION_CREAR);
        getContratosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrearContrato");
        PrimeFaces.current().resetInputs("frmCrear:panelCrearPrestador");
        PrimeFaces.current().ajax().update("frmCrear:panelCrearContrato");
        PrimeFaces.current().ajax().update("frmCrear:panelCrearPrestador");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getContratosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjetoContrato().setId(id);
        super.setAccion(ACCION_EDITAR);
        getContratosServicio().Accion(this);
        //inicializamos el parámetro del buscador de Prestador genérico
        // debemos actualizar el buscador de sedes, para pasarle el parámetro de la empresa
        getSelPrestadorSedesGenericoBean().adicionarPrestador(getObjetoContrato().getCntPrestador());
        PrimeFaces.current().resetInputs("frmEditar:panelEditarContrato");
        PrimeFaces.current().resetInputs("frmEditar:panelEditarPrestador");
        PrimeFaces.current().resetInputs("frmEditar:tablaEditarSedes");
        PrimeFaces.current().ajax().update("frmEditar:panelEditarContrato");
        PrimeFaces.current().ajax().update("frmEditar:panelEditarPrestador");
        PrimeFaces.current().ajax().update("frmEditar:tablaEditarSedes");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getContratosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjetoContrato().setId(id);
        super.setAccion(ACCION_BORRAR);
        getContratosServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjetoContrato().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjetoContrato().toString());
                PrimeFaces.current().ajax().update("frmContratos");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                break;
            case Url.ACCION_ADICIONAL_2:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR :
                            crearLog("Gestionar Detalles Contratos",getObjeto().toString());
                            break;
                        case Url.ACCION_VER :
                            crearLog("Ver Detalle Contrato",getObjetoContratoDetalle().toString());
                            break;
                    }
                break;
            case Url.ACCION_ADICIONAL_3:
                    switch(getDoAccion()) {
                        case Url.ACCION_CREAR :
                            crearLog("Crear Detalle Contrato",getObjetoContratoDetalle().toString());
                            break;
                        case Url.ACCION_GUARDAR :
                            crearLog("Guardar Detalle Contrato",getObjetoContratoDetalle().toString());
                            PrimeFaces.current().ajax().update("frmGestionarTecnologia");
                            break;
                    }
                break;
                
            case Url.ACCION_ADICIONAL_4:
                switch(getDoAccion()) {
                        case Url.ACCION_LISTAR :
                            break;
                        case Url.ACCION_EDITAR :
                            crearLog("Editar Detalle Contrato",getObjetoContratoDetalle().toString());
                            break;
                        case Url.ACCION_MODIFICAR :
                            crearLog("Modificar Detalle Contrato",getObjetoContratoDetalle().toString());
                            PrimeFaces.current().ajax().update("frmGestionarTecnologia");
                            break;
                    }
            break;
            case Url.ACCION_ADICIONAL_5:
                crearLog("Borrar Detalle Contrato",getObjeto().toString());
                PrimeFaces.current().ajax().update("frmGestionarTecnologia");
            break;
            case Url.ACCION_ADICIONAL_6:
                crearLog("Imprimir Detalles Contrato",getObjeto().toString());
            break;
            case Url.ACCION_ADICIONAL_8:
                switch(getDoAccion()) {
                        case Url.ACCION_LISTAR :
                            PrimeFaces.current().ajax().update("frmListarNotaTecnica");
                            break;
                        case Url.ACCION_CREAR :
                            crearLog("Crear Nota Técnica",getObjetoContratoNotaTecnica().toString());
                            break;
                        case Url.ACCION_GUARDAR :
                            crearLog("Guardar Nota Técnica",getObjetoContratoNotaTecnica().toString());
                            PrimeFaces.current().ajax().update("frmListarNotaTecnica");
                            break;
                        case Url.ACCION_EDITAR :
                            crearLog("Editar Nota Técnica",getObjetoContratoNotaTecnica().toString());
                            break;
                        case Url.ACCION_MODIFICAR :
                            crearLog("Modificar Nota Técnica",getObjetoContratoNotaTecnica().toString());
                            PrimeFaces.current().ajax().update("frmListarNotaTecnica");
                            break;
                        case Url.ACCION_BORRAR :
                            crearLog("Borrar Nota Técnica",getObjetoContratoNotaTecnica().toString());
                            PrimeFaces.current().ajax().update("frmListarNotaTecnica");
                            break;
                        case Url.ACCION_ADICIONAL_1 :
                            crearLog("Imprimir Nota Técnica");
                            break;
                    }
            break;
            case Url.ACCION_ADICIONAL_9:
                crearLog("Ver Histórico Detalle Contrato",getObjetoContratoDetalle().toString());
            break;
            case Url.ACCION_ADICIONAL_10:
                crearLog("Autoriza Gestión Contrato",getObjetoContrato().toString());
                PrimeFaces.current().ajax().update("frmContratos");
            break;
        }
        generarMensajes();
    }

    public String getActivo (boolean valor) {
        if (valor) {
            return "Si";
        }else {
            return "No";
        }
    }
    
    public String getValorBandera (Boolean valor) {
        String mensaje = "";
        if (valor != null) {
            if (valor) {
                mensaje = "Si";
            }else {
                mensaje = "No";
            }
        }
        return mensaje;
    }
    
    public String getEstadoSede (Boolean valor) {
        String mensaje = "";
        if (valor != null) {
            if (valor) {
                mensaje = "Activo";
            }else {
                mensaje = "Inactivo";
            }
        }
        return mensaje;
    }
    
    public String getEstadoContrato(String id) {
        String mensaje = "";
        try {
            mensaje = hashEstadoContrato.get(id).getNombre();
        }catch (Exception e) {
        }
            
        return mensaje;
    }

    /**
     * @return the lazyContratoSedes
     */
    public LazyDataModel<CntContratoSede> getLazyContratoSedes() {
        return lazyContratoSedes;
    }

    /**
     * @param lazyContratoSedes the lazyContratoSedes to set
     */
    public void setLazyContratoSedes(LazyDataModel<CntContratoSede> lazyContratoSedes) {
        this.lazyContratoSedes = lazyContratoSedes;
    }

    /**
     * @return the lazyContratoDetalle
     */
    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalle() {
        return lazyContratoDetalle;
    }

    /**
     * @param lazyContratoDetalle the lazyContratoDetalle to set
     */
    public void setLazyContratoDetalle(LazyDataModel<CntContratoDetalle> lazyContratoDetalle) {
        this.lazyContratoDetalle = lazyContratoDetalle;
    }

    /**
     * @return the paramConsultaContratoDetalle
     */
    public ParamConsulta getParamConsultaContratoDetalle() {
        return paramConsultaContratoDetalle;
    }

    /**
     * @param paramConsultaContratoDetalle the paramConsultaContratoDetalle to set
     */
    public void setParamConsultaContratoDetalle(ParamConsulta paramConsultaContratoDetalle) {
        this.paramConsultaContratoDetalle = paramConsultaContratoDetalle;
    }

    /**
     * @return the listaContratoDetalle
     */
    public List<CntContratoDetalle> getListaContratoDetalle() {
        return listaContratoDetalle;
    }

    /**
     * @param listaContratoDetalle the listaContratoDetalle to set
     */
    public void setListaContratoDetalle(List<CntContratoDetalle> listaContratoDetalle) {
        this.listaContratoDetalle = listaContratoDetalle;
    }
    
    public void inicializarTablaTecnologias(int id) {
        this.setParamConsultaContratoDetalle(new ParamConsulta());
        this.getParamConsultaContratoDetalle().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de contrato Sede
        lazyContratoDetalle = new LazyDataModel<CntContratoDetalle>() {

            private List<CntContratoDetalle> tecnologias;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaContratoDetalle().getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaContratoDetalle().setPrimerRegistro(primerRegistro);
                getParamConsultaContratoDetalle().setRegistrosPagina(registrosPagina);
                getParamConsultaContratoDetalle().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaContratoDetalle().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaDetalleContrato();
                tecnologias = getListaContratoDetalle();
                setRowCount(getParamConsultaContratoDetalle().getCantidadRegistros());
                return tecnologias;
            }

            @Override
            public String getRowKey(CntContratoDetalle tecnologias) {
                return tecnologias.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String tecnologiasId) {
                Integer id = Integer.valueOf(tecnologiasId);
                for (CntContratoDetalle novedad : tecnologias) {
                    if (id.equals(novedad.getId())) {
                        return novedad;
                    }
                }
                return null;
            }

        };

    }
    
    public void verTecnologiasSede(int id) {
        inicializarTablaTecnologias(id);
        //necesitamos pasarle el id del contratoSede
        getParamConsultaContratoDetalle().setParametroConsulta1(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVerTecnologia");
        PrimeFaces.current().ajax().update("frmVerTecnologia:tablaRegistrosContratoDetalle");
        PrimeFaces.current().executeScript("PF('dialogoVerTecnologia').show()");
        procesoFinal();
    }
    
    public void refrescarListaDetalleContrato() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
    }
    
    public void refrescarListaGestionDetalleContrato() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
    }
    
    /**
     * 
     * 
     * @param sede 
     */
    public void verDetalleContratoSede(CntContratoSede sede) {
        this.objeto = sede;
        PrimeFaces.current().ajax().update("frmVerContratoSede");
        PrimeFaces.current().executeScript("PF('dialogoVerContratoSede').show()");
        //procesoFinal();
    }
    
    //  FUNCIONES PANTALLA GESTIONAR DETALLES DE CONTRATO SEDE
    
    public void gestionarTecnologiasSede(int id) {
        //guardamos el id del objeto ContratoSede
        this.objeto = new CntContratoSede(id);
        inicializarTablaTecnologias(id);
        //necesitamos pasarle el id del contratoSede
        getParamConsultaContratoDetalle().setParametroConsulta1(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGestionarTecnologia");
        PrimeFaces.current().ajax().update("frmGestionarTecnologia:tablaRegistrosGestionContratoDetalle");
        PrimeFaces.current().executeScript("PF('dialogoGestionarTecnologia').show()");
        procesoFinal();
    }
    
    public void verDetalleContrato(CntContratoDetalle contratoDetalle) {
        this.objetoContratoDetalle = contratoDetalle;
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_VER);
        PrimeFaces.current().resetInputs("frmVerDetalleContrato");
        PrimeFaces.current().ajax().update("frmVerDetalleContrato");
        PrimeFaces.current().executeScript("PF('dialogoVerDetalleContrato').show()");
        procesoFinal();
    }
    
    public void crearDetalleContrato () {
        this.paqueteTecnologia = false;
        this.deshabilitarServicio = true;
        this.objetoContratoDetalle = new CntContratoDetalle();
        this.listaServicios = new ArrayList<>();
        this.listaValoresSoat = new ArrayList<>();
        //TODO se debe garantizar que se agregue el objeto CntContratoSede.. Debe venir desde cuando visualizamos Gestionar Detalle Contrato
        this.objetoContratoDetalle.setCntContratoSede(objeto);
        this.objetoContratoDetalle.setCntContrato(objeto.getCntContrato());
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_CREAR);
        PrimeFaces.current().resetInputs("frmCrearDetalleContrato");
        PrimeFaces.current().ajax().update("frmCrearDetalleContrato");
        PrimeFaces.current().executeScript("PF('dialogoCrearDetalleContrato').show()");
        procesoFinal();
    }
    
    public void guardarDetalleContrato () {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getContratosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearDetalleContrato').hide()");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }
    
    public void editarDetalleContrato (CntContratoDetalle contratoDetalle) {
        this.paqueteTecnologia = false;
        this.deshabilitarServicio = true;
        this.objetoContratoDetalle = contratoDetalle;
        this.listaServicios = new ArrayList<>();
        this.listaValoresSoat = new ArrayList<>();
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_EDITAR);
        getContratosServicio().Accion(this);
        //TODO se debe garantizar que se agregue el objeto CntContratoSede.. Debe venir desde cuando visualizamos Gestionar Detalle Contrato
        this.objetoContratoDetalle.setCntContratoSede(objeto);
        this.objetoContratoDetalle.setCntContrato(objeto.getCntContrato());
        PrimeFaces.current().resetInputs("frmEditarDetalleContrato");
        PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
        PrimeFaces.current().executeScript("PF('dialogoEditarDetalleContrato').show()");
        procesoFinal();
    }
    
    public void modificarDetalleContrato () {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getContratosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarDetalleContrato').hide()");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }
    
    public void borrarDetalleContrato(Integer id) {
        this.objetoContratoDetalle = new CntContratoDetalle(id);
        super.setAccion(ACCION_ADICIONAL_5);
        //super.setDoAccion(Url.ACCION_BORRAR);
        getContratosServicio().Accion(this);
        procesoFinal();
    }
    
    public void verHistoricoDetalleContrato(CntContratoDetalle contratoDetalle) {
        this.objetoContratoDetalle = contratoDetalle;
        paramConsultaHistorico = new ParamConsulta();
        paramConsultaHistorico.setParametroConsulta1(this.objetoContratoDetalle.getCntContrato().getId());
        paramConsultaHistorico.setParametroConsulta2(this.objetoContratoDetalle.getCntContratoSede().getId());
        paramConsultaHistorico.setParametroConsulta3(this.objetoContratoDetalle.getId());
        super.setAccion(ACCION_ADICIONAL_9);
        getContratosServicio().Accion(this);
        // debemos cargar la lista de novedades CAMBIAR
        inicializarTablaHistorico();
        PrimeFaces.current().resetInputs("frmHistoricos");
        PrimeFaces.current().ajax().update("frmHistoricos");
        PrimeFaces.current().executeScript("PF('dialogoHistoricos').show()");
        procesoFinal();
    }
    
    public void inicializarTablaHistorico() {
        lazyHistorico = new LazyDataModel<CntContratoHistorico>() {
            private List<CntContratoHistorico> listaCargasHist;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaHistorico().getCantidadRegistros();
            }

            @Override
            public List<CntContratoHistorico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaHistorico().setPrimerRegistro(primerRegistro);
                getParamConsultaHistorico().setRegistrosPagina(registrosPagina);
                getParamConsultaHistorico().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHistorico().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorico();
                listaCargasHist = getRegistrosHistorico();
                setRowCount(getParamConsultaHistorico().getCantidadRegistros());
                return listaCargasHist;
            }

            @Override
            public String getRowKey(CntContratoHistorico historico) {
                return historico.getId().toString();
            }

            @Override
            public CntContratoHistorico getRowData(String historicoId) {
                Integer id = Integer.valueOf(historicoId);
                for (CntContratoHistorico historico : listaCargasHist) {
                    if (id.equals(historico.getId())) {
                        return historico;
                    }
                }
                return null;
            }

        };
    }
    
    public void refrescarHistorico() {
        super.setAccion(Url.ACCION_ADICIONAL_9);
        getContratosServicio().Accion(this);
        procesoFinal();
    }
    
    public void verDetalleHistorico(CntContratoHistorico obj) {
        // hacemos transformación del objeto en el de la pantalla
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        try {
            this.objetoContratoDetalle = gson.fromJson(obj.getToString(), CntContratoDetalle.class);
            PrimeFaces.current().resetInputs("frmVerHistorico");
            PrimeFaces.current().ajax().update("frmVerHistorico");
            PrimeFaces.current().executeScript("PF('dialogoVerHistorico').show()");
        } catch (JsonSyntaxException e) {
            addError("Ocurrió un error. Mensaje: " + e.getMessage());
        }
        procesoFinal();
    }
    
    public void evaluarManualTarifario() {
        if (this.objetoContratoDetalle.getTipoManualTarifario() != null) {
            switch (this.objetoContratoDetalle.getTipoManualTarifario()) {
                case ContratacionParametro.TIPO_MANUAL_TARIFARIO_SOAT:
                    consultarMaSoat();
                    break;
                case ContratacionParametro.TIPO_MANUAL_TARIFARIO_ISS2000:
                    consultarMaIss2000();
                    break;
                case ContratacionParametro.TIPO_MANUAL_TARIFARIO_ISS2001:
                    consultarMaIss2001();
                    break;
                default:
                    limpiarCamposManualTarifario();
                    break;
            }
        }else {
            limpiarCamposManualTarifario();
        }
    }
    
    public void limpiarCamposManualTarifario() {
        getObjetoContratoDetalle().setMaManualTarifarioId(null);
        getObjetoContratoDetalle().setMaManualTarifarioCodigo("");
        getObjetoContratoDetalle().setMaManualTarifarioValor("");
        getObjetoContratoDetalle().setMaManualTarifarioAgno(null);
        //debemos limpiar la lista Tarifas para Soat
        this.listaValoresSoat = new ArrayList<>();
        PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato1");
        PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
    }
    
    public void cerrarDialogoMaIss2000() {
        //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
        getObjetoContratoDetalle().setMaManualTarifarioId(getSelIss2000Bean().getObjeto().getId());
        getObjetoContratoDetalle().setMaManualTarifarioCodigo(getSelIss2000Bean().getObjeto().getCodigo());
        getObjetoContratoDetalle().setMaManualTarifarioValor(getSelIss2000Bean().getObjeto().getDescripcion());
        //2021-06-11 jyperez Sprint 1 cargar el valor monto en el campo valor base
        getObjetoContratoDetalle().setValorManual(getSelIss2000Bean().getObjeto().getMonto());
        calcularValorContratado();
        //getObjetoContratoDetalle().setTipoManualTarifario(TIPO_MANUAL_TARIFARIO_SOAT);
        //limpiamos los valores que se hayan seleccionado anteriormente en el campo Soat Año
        getObjetoContratoDetalle().setMaManualTarifarioAgno(null);
        //debemos limpiar la lista Tarifas para Soat
        this.listaValoresSoat = new ArrayList<>();
        //limpiamos el objeto del buscador
        getSelIss2000Bean().setObjeto(new MaIss2000Tarifario());
        PrimeFaces.current().executeScript("PF('dialogoIss2000Busqueda').hide()");
        // actualizar las pantalla de Creación del Detalle Contrato
        PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato1");
        PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
    }
    
    public void consultarMaIss2000() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoIss2000Busqueda').show()");
            PrimeFaces.current().ajax().update("frmIss2000Busqueda");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoMaIss2001() {
        //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
        getObjetoContratoDetalle().setMaManualTarifarioId(getSelIss2001Bean().getObjeto().getId());
        getObjetoContratoDetalle().setMaManualTarifarioCodigo(getSelIss2001Bean().getObjeto().getCodigo());
        getObjetoContratoDetalle().setMaManualTarifarioValor(getSelIss2001Bean().getObjeto().getDescripcion());
        //2021-06-11 jyperez Sprint 1 cargar el valor monto en el campo valor base
        getObjetoContratoDetalle().setValorManual(getSelIss2001Bean().getObjeto().getMonto());
        calcularValorContratado();
        //getObjetoContratoDetalle().setTipoManualTarifario(TIPO_MANUAL_TARIFARIO_SOAT);
        //limpiamos los valores que se hayan seleccionado anteriormente en el campo Soat Año
        getObjetoContratoDetalle().setMaManualTarifarioAgno(null);
        //debemos limpiar la lista Tarifas para Soat
        this.listaValoresSoat = new ArrayList<>();
        //limpiamos el objeto del buscador
        getSelIss2001Bean().setObjeto(new MaIss2001Tarifario());
        PrimeFaces.current().executeScript("PF('dialogoIss2001Busqueda').hide()");
        // actualizar las pantalla de Creación del Detalle Contrato
        PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato1");
        PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
    }
    
    public void consultarMaIss2001() {
        try {
        PrimeFaces.current().executeScript("PF('dialogoIss2001Busqueda').show()");
        PrimeFaces.current().ajax().update("frmIss2001Busqueda");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoMaSoat() {
        //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
        getObjetoContratoDetalle().setMaManualTarifarioId(getSelSoatBean().getObjeto().getId());
        getObjetoContratoDetalle().setMaManualTarifarioCodigo(getSelSoatBean().getObjeto().getCodigo());
        getObjetoContratoDetalle().setMaManualTarifarioValor(getSelSoatBean().getObjeto().getDescripcion());
        //debemos llenar la lista de Valores de Soat 
        this.listaValoresSoat = getContratosServicio().consultarValoresSoat(getObjetoContratoDetalle().getMaManualTarifarioId());        
        //getObjetoContratoDetalle().setTipoManualTarifario(TIPO_MANUAL_TARIFARIO_SOAT);
        //limpiamos los valores que se hayan seleccionado anteriormente en el campo Soat Año
        getObjetoContratoDetalle().setMaManualTarifarioAgno(null);
        //limpiamos el objeto del buscador
        getSelSoatBean().setObjeto(new MaSoatTarifario());
        PrimeFaces.current().executeScript("PF('dialogoSoatBusqueda').hide()");
        // actualizar las pantalla de Creación del Detalle Contrato
        PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato1");
        PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
    }
    
    public void consultarMaSoat() {
        try {
        PrimeFaces.current().executeScript("PF('dialogoSoatBusqueda').show()");
        PrimeFaces.current().ajax().update("frmSoatBusqueda");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoMaInsumo() {
        if (actualizarNotaTecnica) {
            //vamos a asignarle los valores del objeto seleccionado a los campos de NotaTecnica
            getObjetoContratoNotaTecnica().setMaTecnologiaId(getSelInsumosBean().getObjeto().getId());
            getObjetoContratoNotaTecnica().setMaTecnologiaCodigo(getSelInsumosBean().getObjeto().getCodigo());
            getObjetoContratoNotaTecnica().setMaTecnologiaValor(getSelInsumosBean().getObjeto().getDescripcion());
            getObjetoContratoNotaTecnica().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_INSUMO);
            //limpiamos el objeto del buscador
            getSelInsumosBean().setObjeto(new MaInsumo());
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");

            // actualizar las pantalla de Creación del Detalle Contrato
            PrimeFaces.current().ajax().update("frmCrearNotaTecnica:panelCrearNotaTecnica");
        } else {
            //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
            getObjetoContratoDetalle().setMaTecnologiaId(getSelInsumosBean().getObjeto().getId());
            getObjetoContratoDetalle().setMaTecnologiaCodigo(getSelInsumosBean().getObjeto().getCodigo());
            getObjetoContratoDetalle().setMaTecnologiaValor(getSelInsumosBean().getObjeto().getDescripcion());
            getObjetoContratoDetalle().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_INSUMO);
            //limpiar los valores que se hayan seleccionado en caso de que se hayan incluido valores del maestro de Tecnologia
            getObjetoContratoDetalle().setMaServicioHabilitacionId(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionCodigo(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionValor(null);
            this.listaServicios = new ArrayList<>();
            this.paqueteTecnologia = false;
            this.deshabilitarServicio = true;
            //limpiamos el objeto del buscador

            getSelInsumosBean().setObjeto(new MaInsumo());
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");

            // actualizar las pantalla de Creación del Detalle Contrato
            PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato");
            PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
        }
    }
    
    public void consultarMaInsumo() {
        try {
            actualizarNotaTecnica = false;
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmInsumoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void consultarMaInsumoNotaTecnica() {
        try {
            actualizarNotaTecnica = true;
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmInsumoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoMaMedicamento() {
        if (actualizarNotaTecnica) {
            //vamos a asignarle los valores del objeto seleccionado a los campos de NotaTecnica
            getObjetoContratoNotaTecnica().setMaTecnologiaId(getSelMedicamentoBean().getObjeto().getId());
            getObjetoContratoNotaTecnica().setMaTecnologiaCodigo(getSelMedicamentoBean().getObjeto().getCum());
            getObjetoContratoNotaTecnica().setMaTecnologiaValor(getSelMedicamentoBean().getObjeto().getDescripcionEstandarizada());
            getObjetoContratoNotaTecnica().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_MEDICAMENTO);
            //limpiamos el objeto del buscador
            getSelMedicamentoBean().setObjeto(new MaMedicamento());
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
            // actualizar las pantalla de Creación de Nota Tecnica
            PrimeFaces.current().ajax().update("frmCrearNotaTecnica:panelCrearNotaTecnica");
        } else {
            //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
            getObjetoContratoDetalle().setMaTecnologiaId(getSelMedicamentoBean().getObjeto().getId());
            getObjetoContratoDetalle().setMaTecnologiaCodigo(getSelMedicamentoBean().getObjeto().getCum());
            //2021-06-10 jyperez Este cambio se hace en relación al "Requerimientos Variables Visuales Contratos"
            getObjetoContratoDetalle().setMaTecnologiaValor(getSelMedicamentoBean().getObjeto().getDescripcionEstandarizada());
            getObjetoContratoDetalle().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_MEDICAMENTO);
            //limpiar los valores que se hayan seleccionado en caso de que se hayan incluido valores del maestro de Tecnologia
            getObjetoContratoDetalle().setMaServicioHabilitacionId(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionCodigo(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionValor(null);
            //2021-06-11 jyperez Sprint 1 actualizamos el campo valor maximo regulado cuando se selecciona un medicamento.
            getObjetoContratoDetalle().setValorMaximoRegulado(getSelMedicamentoBean().getObjeto().getValorMaximoRegulado());
            this.listaServicios = new ArrayList<>();
            this.paqueteTecnologia = false;
            this.deshabilitarServicio = true;
            //limpiamos el objeto del buscador

            getSelMedicamentoBean().setObjeto(new MaMedicamento());
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");

            // actualizar las pantalla de Creación del Detalle Contrato
            PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato");
            PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato2");
            PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
        }
    }
    
    public void consultarMaMedicamento() {
        try {
            actualizarNotaTecnica = false;
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void consultarMaMedicamentoNotaTecnica() {
        try {
            actualizarNotaTecnica = true;
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoMaTecnologia() {
        if (actualizarNotaTecnica) {
            //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
            getObjetoContratoNotaTecnica().setMaTecnologiaId(getSelTecnologiasBean().getObjeto().getId());
            getObjetoContratoNotaTecnica().setMaTecnologiaCodigo(getSelTecnologiasBean().getObjeto().getCodigoPropio());
            getObjetoContratoNotaTecnica().setMaTecnologiaValor(getSelTecnologiasBean().getObjeto().getCupsDescipcion());
            getObjetoContratoNotaTecnica().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA);
            //limpiamos el objeto del buscador
            getSelTecnologiasBean().setObjeto(new MaTecnologia());
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
            // actualizar las pantalla de Creación de Nota Tecnica
            PrimeFaces.current().ajax().update("frmCrearNotaTecnica:panelCrearNotaTecnica");
        } else {
            //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
            getObjetoContratoDetalle().setMaTecnologiaId(getSelTecnologiasBean().getObjeto().getId());
            getObjetoContratoDetalle().setMaTecnologiaCodigo(getSelTecnologiasBean().getObjeto().getCodigoPropio());
            getObjetoContratoDetalle().setMaTecnologiaValor(getSelTecnologiasBean().getObjeto().getCupsDescipcion());
            getObjetoContratoDetalle().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA);
            //valores de servicios de habilitacion se setean cuando es maestro de Tecnología
            //PENDIENTE DEBIDO A QUE ESTO ES UNA TABLA APARTE Y NO EXISTE UNO SOLO
            getObjetoContratoDetalle().setMaServicioHabilitacionId(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionCodigo(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionValor(null);
            //debemos llenar la lista de Servicios de Habilitación relacionada a la tacnologia
            this.listaServicios = getContratosServicio().consultarServiciosTecnologia(getObjetoContratoDetalle().getMaTecnologiaId());
            this.paqueteTecnologia = false;
            this.deshabilitarServicio = false;
            //limpiamos el objeto del buscador

            getSelTecnologiasBean().setObjeto(new MaTecnologia());
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");

            // actualizar las pantalla de Creación del Detalle Contrato
            PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato");
            PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
        }
    }
    
    public void consultarMaTecnologia() {
        try {
            actualizarNotaTecnica = false;
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
            PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void consultarMaTecnologiaNotaTecnica() {
        try {
            actualizarNotaTecnica = true;
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
            PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoMaPaquete() {
        MaPaquete paquete = null;
        paquete = getSelPaquetesBean().getObjeto();
        if (actualizarNotaTecnica) {
            //vamos a asignarle los valores del objeto seleccionado a los campos de NotaTecnica
            getObjetoContratoNotaTecnica().setMaTecnologiaId(getSelPaquetesBean().getObjeto().getId());
            getObjetoContratoNotaTecnica().setMaTecnologiaCodigo(getSelPaquetesBean().getObjeto().getCodigo());
            getObjetoContratoNotaTecnica().setMaTecnologiaValor(getSelPaquetesBean().getObjeto().getNombre());
            getObjetoContratoNotaTecnica().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_PAQUETE);
            //limpiamos el objeto del buscador
            getSelPaquetesBean().setObjeto(new MaPaquete());
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
            // actualizar las pantalla de Creación de Nota Tecnica
            PrimeFaces.current().ajax().update("frmCrearNotaTecnica:panelCrearNotaTecnica");
        } else {
            //vamos a asignarle los valores del objeto seleccionado a los campos de ContratoDetalle
            getObjetoContratoDetalle().setMaTecnologiaId(getSelPaquetesBean().getObjeto().getId());
            getObjetoContratoDetalle().setMaTecnologiaCodigo(getSelPaquetesBean().getObjeto().getCodigo());
            getObjetoContratoDetalle().setMaTecnologiaValor(getSelPaquetesBean().getObjeto().getNombre());
            getObjetoContratoDetalle().setTipoTecnologia(ContratacionParametro.TIPO_TECNOLOGIA_PAQUETE);
            //limpiar los valores que se hayan seleccionado en caso de que se hayan incluido valores del maestro de Tecnologia
            getObjetoContratoDetalle().setMaServicioHabilitacionId(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionCodigo(null);
            getObjetoContratoDetalle().setMaServicioHabilitacionValor(null);
            //debemos llenar la lista de Servicios de Habilitación relacionada a la tacnologia
            if (paquete.getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA) {
                this.listaServicios = getContratosServicio().consultarServiciosTecnologia(paquete.getMaTecnologia().getId());
                this.paqueteTecnologia = true;
                this.deshabilitarServicio = false;
            }else {
                this.listaServicios = new ArrayList<>();
                this.paqueteTecnologia = false;
                this.deshabilitarServicio = true;
            }
            //limpiamos el objeto del buscador
            getSelPaquetesBean().setObjeto(new MaPaquete());
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");

            // actualizar las pantalla de Creación del Detalle Contrato
            PrimeFaces.current().ajax().update("frmCrearDetalleContrato:panelCrearDetalleContrato");
            PrimeFaces.current().ajax().update("frmEditarDetalleContrato");
        }
    }
    
    public void consultarMaPaquete() {
        try {
            actualizarNotaTecnica = false;
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
            PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void consultarMaPaqueteNotaTecnica() {
        try {
            actualizarNotaTecnica = true;
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
            PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
        } catch (Exception ex) {
            //getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    //  FUNCIONES PANTALLA GESTIONAR NOTA TECNICA DE CONTRATO
    
    public void listarNotaTecnica(int id) {
        //guardamos el id del objeto Contrato
        this.objetoContrato = new CntContrato(id);
        inicializarTablaNotaTecnica(id);
        //necesitamos pasarle el id del Contrato
        getParamConsultaContratoNotaTecnica().setParametroConsulta1(id);
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmListarNotaTecnica");
        PrimeFaces.current().ajax().update("frmListarNotaTecnica:tablaRegistrosContratoNotaTecnica");
        PrimeFaces.current().executeScript("PF('dialogoListarNotaTecnica').show()");
        procesoFinal();
    }
    
    public void autorizarGestionContrato(int id) {
        this.objetoContrato = new CntContrato(id);
        super.setAccion(ACCION_ADICIONAL_10);
        //super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
        procesoFinal();
    }
    
    public void inicializarTablaNotaTecnica(int id) {
        this.setParamConsultaContratoNotaTecnica(new ParamConsulta());
        this.getParamConsultaContratoNotaTecnica().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de contrato
        lazyContratoNotaTecnica = new LazyDataModel<CntContratoNotaTecnica>() {

            private List<CntContratoNotaTecnica> notasTecnicas;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaContratoNotaTecnica().getCantidadRegistros();
            }

            @Override
            public List<CntContratoNotaTecnica> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaContratoNotaTecnica().setPrimerRegistro(primerRegistro);
                getParamConsultaContratoNotaTecnica().setRegistrosPagina(registrosPagina);
                getParamConsultaContratoNotaTecnica().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaContratoNotaTecnica().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaNotaTecnica();
                notasTecnicas = getListaContratoNotaTecnica();
                setRowCount(getParamConsultaContratoNotaTecnica().getCantidadRegistros());
                return notasTecnicas;
            }

            @Override
            public String getRowKey(CntContratoNotaTecnica notaTecnica) {
                return notaTecnica.getId().toString();
            }

            @Override
            public CntContratoNotaTecnica getRowData(String notaTecnicaId) {
                Integer id = Integer.valueOf(notaTecnicaId);
                for (CntContratoNotaTecnica nota : notasTecnicas) {
                    if (id.equals(nota.getId())) {
                        return nota;
                    }
                }
                return null;
            }

        };

    }
    
    public void refrescarListaNotaTecnica() {
        super.setAccion(Url.ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_LISTAR);
        getContratosServicio().Accion(this);
    }
    
    
    public void verNotaTecnica(CntContratoNotaTecnica nota) {
        this.objetoContratoNotaTecnica = nota;
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_VER);
        PrimeFaces.current().resetInputs("frmVerNotaTecnica");
        PrimeFaces.current().ajax().update("frmVerNotaTecnica");
        PrimeFaces.current().executeScript("PF('dialogoVerNotaTecnica').show()");
        procesoFinal();
    }
    
    public void crearNotaTecnica() {
        this.objetoContratoNotaTecnica = new CntContratoNotaTecnica();
        //this.actualizarNotaTecnica = true;
        this.objetoContratoNotaTecnica.setCntContrato(objetoContrato);
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_CREAR);
        PrimeFaces.current().resetInputs("frmCrearNotaTecnica");
        PrimeFaces.current().ajax().update("frmCrearNotaTecnica");
        PrimeFaces.current().executeScript("PF('dialogoCrearNotaTecnica').show()");
        procesoFinal();
    }
    
    public void guardarNotaTecnica() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getContratosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearNotaTecnica').hide()");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }
    
    public void editarNotaTecnica(int id) {
        this.objetoContratoNotaTecnica = new CntContratoNotaTecnica(id);
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_EDITAR);
        getContratosServicio().Accion(this);
        //this.objetoContratoNotaTecnica.setCntContrato(objetoContrato);
        PrimeFaces.current().resetInputs("frmEditarNotaTecnica");
        PrimeFaces.current().ajax().update("frmEditarNotaTecnica");
        PrimeFaces.current().executeScript("PF('dialogoEditarNotaTecnica').show()");
        procesoFinal();
    }
    
    public void modificarNotaTecnica() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getContratosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarNotaTecnica').hide()");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }
    
    public void borrarNotaTecnica(int id) {
        this.objetoContratoNotaTecnica = new CntContratoNotaTecnica(id);
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_BORRAR);
        getContratosServicio().Accion(this);
        procesoFinal();
    }
    
    public StreamedContent imprimirNotaTecnica() {
        StreamedContent streamedContent2 = null;
        int consecutivo = 1;
        int actualizar = 1;
        String nombre;
        String texto;
        try {
            setListaDetalleTecnologias(new ArrayList<>());
            super.setAccion(ACCION_ADICIONAL_8);
            super.setDoAccion(ACCION_ADICIONAL_1);//Imprimir
            getContratosServicio().Accion(this);
            // adicionamos encabezado en la primera línea - similar a la carga masiva
            texto = "consecutivo,actualizar,id_nota_tecnica,contrato,tipo_tecnologia,codigo_tecnologia,costo_promedio,frecuencia_uso,tipo_frecuencia,poblacion,ambito,observacion,fecha_inicio,fecha_fin\n";
            
            if(getListaImpresionNotaTecnica().size() > 0){
                for (CntContratoNotaTecnica nt: this.getListaImpresionNotaTecnica()) {
                    texto = texto + nt.generarTextoFormatoCargaMasiva(consecutivo,actualizar);
                    consecutivo++;
                }
                // construimos el nombre del archivo
                nombre = "archivo.txt";
                //debemos generar la cadena de bytes a partir de los registros en la lista
                byte[] bytes = texto.getBytes();
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
            }else{
                addError("No se encontraron datos para generar el archivo");
                generarMensajes();
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }
    
    public void cargarValorBaseSoat(){
        //actualizamos
        try {
            //2021-06-11 jyperez Sprint 1 cargar el valor monto en el campo valor base del seleccionado del soat
            for (MaSoatTarifarioValor val: getListaValoresSoat()) {
                if (val.getAgno() == getObjetoContratoDetalle().getMaManualTarifarioAgno()) {
                    getObjetoContratoDetalle().setValorManual(val.getValor());
                    calcularValorContratado();
                }
            }
        } catch (Exception ex) {
            
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
    
    public String getManualTarifario(Integer id) {
        String mensaje = "";
        if (id != null) {
            switch(id) {
                case 0:
                    mensaje = "PROPIA";
                    break;
                case 1:
                    mensaje = "SOAT";
                    break;
                case 2:
                    mensaje = "ISS2000";
                    break;
                case 3:
                    mensaje = "ISS2001";
                    break;
            }
        }
        return mensaje;
    }
    
    public String getZonaPrecedencia(String zona) {
        String mensaje = "";
        switch(zona) {
            case "U":
                mensaje = "Urbana";
                break;
            case "R":
                mensaje = "Rural";
                break;
        }
        return mensaje;
    }
    
    public String getComplejidad(Integer id) {
        String mensaje = "";
        if (id != null) {
            switch(id) {
                case 1:
                    mensaje = "Alta";
                    break;
                case 2:
                    mensaje = "Mediana";
                    break;
                case 3:
                    mensaje = "Baja";
                    break;
            }
        }
        return mensaje;
    }
    
    
    public void cerrarDialogoCntPrestador() {
        //2021-03-01 jyperez limpiamos la lista de Sedes, para evitar registrar sedes que no pertenecen al prestador.
        if (getObjetoContrato().getCntPrestador() != null && getObjetoContrato().getCntPrestador().getId() != null) {
            if (!getSelPrestadorBean().getObjeto().getId().equals(getObjetoContrato().getCntPrestador().getId())) {
                listaContratoSedes = new ArrayList<>();
            }
        }
        getObjetoContrato().setCntPrestador(getSelPrestadorBean().getObjeto());
        getSelPrestadorBean().setObjeto(new CntPrestador());
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");
        // debemos actualizar el buscador de sedes, para pasarle el parámetro de la empresa
        getSelPrestadorSedesGenericoBean().adicionarPrestador(getObjetoContrato().getCntPrestador());
        //setSeleccionoPrestador(true);
        //getSelPrestadorSedesBean().refrescar();
        // actualizar lo que debamos actualizar
        PrimeFaces.current().ajax().update("frmCrear:panelCrearPrestador");
        PrimeFaces.current().ajax().update("frmCrear:tablaCrearSedes");
        //PrimeFaces.current().ajax().update("frmEditar:panelCrearPrestador");
    }
    
    public void cerrarDialogoCntPrestadorSede() {
        //validar que la sede que se seleccionó no se encuentra ya en la lista de sedes.
        boolean existe = false;
        CntPrestadorSede sede;
        sede = getSelPrestadorSedesGenericoBean().getObjeto();
        /* 2021-04-13 yjperez se comenta la validación, debido a que ahora el filtro de duplicidad
           se realiza por sede y modalidad contrato ( nuevo req enviado al correo)
        for (CntContratoSede contrato: listaContratoSedes) {
            if (contrato.getCntPrestadorSede().getId().equals(sede.getId())) {
                existe = true;
            }
        }*/
        getSelPrestadorSedesGenericoBean().setObjeto(new CntPrestadorSede());
        if (existe) {
            PrimeFaces.current().executeScript("PF('dialogoSelPrestadorSede').hide()");
            addMensaje("La sede ya se encuentra en la lista.");
            generarMensajes();
        }else {
            // esta funcionalidad abré la ventana de Crear Contrato Sede
            PrimeFaces.current().executeScript("PF('dialogoSelPrestadorSede').hide()");
            crearContratoSede(sede);
        }
        //PrimeFaces.current().ajax().update("frmCrear:panelCrearPrestador");
    }
    
    public void consultarCntPrestador() {
        try {
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
        PrimeFaces.current().ajax().update("frmPrestadorLista");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void consultarCntPrestadorSede() {
        try {
            if (getObjetoContrato().getCntPrestador().getId() != null) {
                PrimeFaces.current().executeScript("PF('dialogoSelPrestadorSede').show()");
                PrimeFaces.current().ajax().update("frmSelPrestadorSedesGenerico");
            }else {
                //PrimeFaces.current().executeScript("PF('dialogoSelPrestadorSede').hide()");
                addMensaje("Se debe seleccionar el Prestador.");
                generarMensajes();
            }
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public String getNombreMunicipioDepartamento (int ubicacionId) {
        String nombre = "";
        String nombrePadre = "";
        //obtenemos el objeto asociado por medio de un hash o list
        for (Ubicacion ubi: listaUbicaciones) {
            if (ubi.getId() == ubicacionId) {
                nombre = ubi.getNombre();
                if (ubi.getUbicacionPadre() != null) {
                    nombrePadre = ubi.getUbicacionPadre().getNombre();
                }
                if (!nombrePadre.equals("")) {
                    nombre = nombre + " - " + nombrePadre;
                }
                break;
            }
        }
        return nombre;
    }
    
    public String getClasePrestador (int id) {
        String mensaje = "";
        try{
            mensaje = hashClasePrestador.get(id).getNombre();
        }catch(Exception e) {
            
        }
        return mensaje;
    }
    
    public String getTipoDocumento (int id) {
        String mensaje = "";
        try{
            mensaje = hashTipoDocumento.get(id).getNombre();
        }catch(Exception e) {
            
        }
        return mensaje;
    }
    
    /**
     * Función llamada desde Crear Prestador y desde Editar Prestador, para adicionar una nueva Sede
     * @param sede 
     */
    public void crearContratoSede(CntPrestadorSede sede) {
        objeto = new CntContratoSede();
        objeto.setCntPrestadorSede(sede);
        PrimeFaces.current().resetInputs("frmCrearContratoSede");
        PrimeFaces.current().ajax().update("frmCrearContratoSede");
        PrimeFaces.current().executeScript("PF('dialogoCrearContratoSede').show()");
    }
    
    /**
     * Función usada desde Crear Contratos con el objetivo de eliminar una sede de la lista
     * @param id 
     */
    public void retirarContratoSede(int id) {
        for (int i = 0; i < listaContratoSedes.size(); i++) {
            CntContratoSede agrup = listaContratoSedes.get(i);
            if (agrup.getId() != null && agrup.getId() == id) {
                //if (agrup.getIdInsertar() != null && agrup.getIdInsertar() == id) {
                listaContratoSedes.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaCrearSedes");
        PrimeFaces.current().ajax().update("frmEditar:tablaEditarSedes");
    }
    
    public void editarContratoSede(int id) {
        objeto = new CntContratoSede();
        for (CntContratoSede sed : listaContratoSedes) {
            if (sed.getId() != null && sed.getId() == id) {
                objeto = sed;
                //2022-07-14 actualizamos la lista de seleccion con los ids de los registros existentes en cntContratoCobertura
                // inicializamos siempre el objeto selección, esto debido a que podemos ingresar varias veces a la edición
                // y alterar la lista. entonces se carga completamente cada vez que se ingresa.
                objeto.setSeleccion(new ArrayList());
                if (objeto.getListaCntContratoCobertura() != null && !objeto.getListaCntContratoCobertura().isEmpty()) {
                    for (CntContratoCobertura cobertura: objeto.getListaCntContratoCobertura()) {
                        objeto.getSeleccion().add(cobertura.getUbicacion().getId());
                    }
                }
                break;
            }
        }
        PrimeFaces.current().resetInputs("frmEditarContratoSede");
        PrimeFaces.current().ajax().update("frmEditarContratoSede");
        PrimeFaces.current().executeScript("PF('dialogoEditarContratoSede').show()");
    }

    public void modificarContratoSede() {
        //2022-07-14 jyperez variables nuevas para manejo de los registros de coberturas
        boolean registroExistente = false;
        // 2021-04-13 jyperez se realiza la validación de la sede teniendo en cuenta que no se puede configurar
        // mas de una con la misma modalidad de contrato
        for (CntContratoSede contratoSede: listaContratoSedes) {
            if ( !contratoSede.getId().equals(objeto.getId())
                    && contratoSede.getCntPrestadorSede().getId().equals(objeto.getCntPrestadorSede().getId())
                    && contratoSede.getMaeModalidadContratoId() == objeto.getMaeModalidadContratoId()) {
                this.addError("Existe una sede con la modalidad de contrato ingresada, ya asociada al contrato.");
            }
        }
        //2024-05-03 jyperez validamos que la fecha de inicio no sea superior a la fecha fin
        if (!objeto.getFechaInicio().before((objeto.getFechaFin()))) {
            this.addError("La fecha de inicio no puede ser mayor a la fecha fin.");
        }
        //2024-05-03 jyperez validamos que la fecha de inicio no sea superior a la fecha actual
        if (!objeto.getFechaInicio().before(fechaActual) ){
            this.addError("La fecha de inicio no puede ser mayor a la fecha actual.");
        }
        //2024-05-03 jyperez validamos que la fecha fin no sea menor a la fecha actual
        if (objeto.getFechaFin().before(fechaActual) ){
            this.addError("La fecha fin no puede ser menor a la fecha actual.");
        }
        if (!isError()) {
            for (int i = 0; i < listaContratoSedes.size(); i++) {
                CntContratoSede agrup = listaContratoSedes.get(i);
                if (agrup.getId() != null && objeto.getId().equals(agrup.getId())) {
                    // validamos que sede no sea un registro nuevo
                    if (!objeto.isNuevoRegistro()) {
                        objeto.setEditado(true);
                    }else {
                        objeto.setEditado(false);
                    }
                    //2022-07-14 validamos si hay registros en la lista. Si es asi, todos los items seran creados nuevos, sino se marcaran los items a borrar y los nuevos registros
                    if (objeto.getListaCntContratoCobertura() != null && !objeto.getListaCntContratoCobertura().isEmpty()) {
                        //validamos por cada registro en la selección, si se encuentra asignado. si no es así, se crea. en general se marcaran todos como eliminados
                        // marcamos como borrados todos los registros
                        for (CntContratoCobertura contratoCobertura: objeto.getListaCntContratoCobertura()) {
                            contratoCobertura.setBorrar(true);
                            contratoCobertura.setNuevoRegistro(false);
                        }
                        // validamos los items a crear y existentes.. si existen se marcan borrar como false
                        for (Integer idUbicacion : objeto.getSeleccion()) {
                            registroExistente = false;
                            for (CntContratoCobertura contratoCobertura: objeto.getListaCntContratoCobertura()) {
                                if (idUbicacion.equals(contratoCobertura.getUbicacion().getId())) {
                                    registroExistente = true;
                                    contratoCobertura.setBorrar(false);
                                }
                            }
                            if (!registroExistente) {
                                CntContratoCobertura contratoCobertura = new CntContratoCobertura();
                                contratoCobertura.setActivo(true);
                                contratoCobertura.setUbicacion(new Ubicacion(idUbicacion));//solo es necesario el id para emparejarlo
                                contratoCobertura.setCntContrato(objetoContrato);
                                contratoCobertura.setCntContratoSede(objeto);//recordar que se debe emparejarlo con el id propio luego
                                contratoCobertura.setCntPrestadorSede(objeto.getCntPrestadorSede());
                                contratoCobertura.setNuevoRegistro(true);
                                contratoCobertura.setBorrar(false);
                                //lo adicionamos a la lista de cntContratoCoberturas
                                objeto.getListaCntContratoCobertura().add(contratoCobertura);
                            }
                        }
                        
                    } else {
                        //2022-07-14 jyperez validamos si se deben agregar coberturas, y generamos la lista de CntContratoCoberturas
                        if (objeto.getSeleccion() != null && !objeto.getSeleccion().isEmpty()) {
                            // por cada id seleccionado ubicamos el objeto en ubicación y lo agregamos a la lista de cntContratoCoberturas
                            for (Integer idUbicacion : objeto.getSeleccion()) {
                                CntContratoCobertura contratoCobertura = new CntContratoCobertura();
                                contratoCobertura.setActivo(true);
                                contratoCobertura.setUbicacion(new Ubicacion(idUbicacion));//solo es necesario el id para emparejarlo
                                contratoCobertura.setCntContrato(objetoContrato);
                                contratoCobertura.setCntContratoSede(objeto);//recordar que se debe emparejarlo con el id propio luego
                                contratoCobertura.setCntPrestadorSede(objeto.getCntPrestadorSede());
                                contratoCobertura.setNuevoRegistro(true);
                                contratoCobertura.setBorrar(false);
                                //lo adicionamos a la lista de cntContratoCoberturas
                                objeto.getListaCntContratoCobertura().add(contratoCobertura);
                            }
                        }
                    }
                    listaContratoSedes.set(i, objeto);
                    break;
               }
            }
            PrimeFaces.current().ajax().update("frmEditar:tablaEditarSedes");
            PrimeFaces.current().executeScript("PF('dialogoEditarContratoSede').hide()");
        } else {
            generarMensajes();
        }
    }

    /**
     * Función que se llama desde Editar Sede - para borrar una Sede Existente
     * @param id 
     */
    public void borrarContratoSede(int id) {
        for (int i = 0; i < listaContratoSedes.size(); i++) {
            CntContratoSede agrup = listaContratoSedes.get(i);
            if (agrup.getId() == id) {
                listaContratoSedesBorrar.add(agrup);
                listaContratoSedes.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmEditar:tablaEditarSedes");
    }
    
    /*
        Función para habilitar el valor UPC dependiendo del item seleccionado en modalidad
    */
    public void habilitarUPCAfiliado() {
        try {
            if (hashModalidadContrato.get(objeto.getMaeModalidadContratoId()).getValor().equals(ContratacionParametro.MODALIDAD_CONTRATO_CAPITA) ) {
                setDeshabilitaValorUPC(false);
            }else {
                setDeshabilitaValorUPC(true);
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void guardarContratoSede() {
        Maestro maestro;
        //validaciones
        if (objeto.getFechaFin().compareTo(objeto.getFechaInicio()) < 0) {
            this.addError("La fecha Inicio no puede ser mayor a la Fecha Fin.");
            //generarMensajes();
        }
        // 2021-04-13 jyperez se realiza la validación de la sede teniendo en cuenta que no se puede configurar
        // mas de una con la misma modalidad de contrato
        for (CntContratoSede contratoSede: listaContratoSedes) {
            if (contratoSede.getCntPrestadorSede().getId().equals(objeto.getCntPrestadorSede().getId())
                    && contratoSede.getMaeModalidadContratoId() == objeto.getMaeModalidadContratoId()) {
                this.addError("Existe una sede con la modalidad de contrato ingresada, ya asociada al contrato.");
            }
        }
        if (!isError()) {
            objeto.setId(contadorIdSede);
            objeto.setNuevoRegistro(true);
            //recordar que el contador va a la inversa con valores negativos
            contadorIdSede--;
            //2020-02-19 jyperez INC 685 No se ve el campo modalidad Contrato en la lista de Sedes
            if(objeto.getMaeModalidadContratoId() != 0) {
                maestro = hashModalidadContrato.get(objeto.getMaeModalidadContratoId());
                if (maestro != null) {
                    objeto.setMaeModalidadContratoCodigo(maestro.getValor());
                    objeto.setMaeModalidadContratoValor(maestro.getNombre());
                }
            }
            //2022-07-14 jyperez validamos si se deben agregar coberturas, y generamos la lista de CntContratoCoberturas
            if (objeto.getSeleccion() != null && !objeto.getSeleccion().isEmpty()) {
                // por cada id seleccionado ubicamos el objeto en ubicación y lo agregamos a la lista de cntContratoCoberturas
                for (Integer idUbicacion : objeto.getSeleccion()) {
                    CntContratoCobertura contratoCobertura = new CntContratoCobertura();
                    contratoCobertura.setActivo(true);
                    contratoCobertura.setUbicacion(new Ubicacion(idUbicacion));//solo es necesario el id para emparejarlo
                    contratoCobertura.setCntContrato(objetoContrato);
                    contratoCobertura.setCntContratoSede(objeto);//recordar que se debe emparejarlo con el id propio luego
                    contratoCobertura.setCntPrestadorSede(objeto.getCntPrestadorSede());
                    contratoCobertura.setNuevoRegistro(true);
                    contratoCobertura.setBorrar(false);
                    //lo adicionamos a la lista de cntContratoCoberturas
                    objeto.getListaCntContratoCobertura().add(contratoCobertura);
                }
            }
            listaContratoSedes.add(objeto);
            PrimeFaces.current().ajax().update("frmCrear:tablaCrearSedes");
            PrimeFaces.current().ajax().update("frmEditar:tablaEditarSedes");
            PrimeFaces.current().executeScript("PF('dialogoCrearContratoSede').hide()");
        } else {
            generarMensajes();
        }
    }
    
    public void calcularValorContratado() {
        BigDecimal resultado = new BigDecimal(0);
        //validamos los campos que tengan valor
        if (this.objetoContratoDetalle.getPorcentajeVariacion() != null 
                && this.objetoContratoDetalle.getValorManual() != null) {
            resultado = ((this.objetoContratoDetalle.getValorManual().multiply(this.objetoContratoDetalle.getPorcentajeVariacion())).divide(new BigDecimal(100))).add(this.objetoContratoDetalle.getValorManual());
        }
        this.objetoContratoDetalle.setValorContratado(resultado);
    }
    
    public void cargarEstado(){
        Maestro mae;
        if (this.objetoContrato.getFechaInicio() != null && this.objetoContrato.getFechaFin() != null) {
            //Validamos contrato antes de la fecha actual -- estado NO VIGENTE
            if(fechaAntes(this.objetoContrato.getFechaInicio(),this.objetoContrato.getFechaFin()) &&
                    fechaAntes(this.objetoContrato.getFechaFin(),this.fechaActual) &&
                    !compararFechas(fechaActual,this.objetoContrato.getFechaFin())) {
                mae = hashEstadoContrato.get(ContratacionParametro.ESTADO_CONTRATO_NO_VIGENTE);
                if (mae != null){
                    this.objetoContrato.setMaeEstadoContratoId(mae.getId());
                    this.objetoContrato.setMaeEstadoContratoCodigo(mae.getValor());
                    this.objetoContrato.setMaeEstadoContratoValor(mae.getNombre());
                }else {
                    this.objetoContrato.setMaeEstadoContratoId(0);
                    this.objetoContrato.setMaeEstadoContratoCodigo("");
                    this.objetoContrato.setMaeEstadoContratoValor("");
                }
            //Validamos contrato que contenga la fecha actual - estado VIGENTE
            } else if(fechaAntesOIgual(this.objetoContrato.getFechaInicio(),this.fechaActual) &&
                    fechaAntesOIgual(this.fechaActual,this.objetoContrato.getFechaFin())) {
                mae = hashEstadoContrato.get(ContratacionParametro.ESTADO_CONTRATO_VIGENTE);
                if (mae != null){
                    this.objetoContrato.setMaeEstadoContratoId(mae.getId());
                    this.objetoContrato.setMaeEstadoContratoCodigo(mae.getValor());
                    this.objetoContrato.setMaeEstadoContratoValor(mae.getNombre());
                }else {
                    this.objetoContrato.setMaeEstadoContratoId(0);
                    this.objetoContrato.setMaeEstadoContratoCodigo("");
                    this.objetoContrato.setMaeEstadoContratoValor("");
                }
            //validamos contrato con fechas futuras - estado NO VIGENTE
            }else if(fechaAntes(this.fechaActual,this.objetoContrato.getFechaInicio()) &&
                    fechaAntes(this.objetoContrato.getFechaInicio(),this.objetoContrato.getFechaFin())) {
                mae = hashEstadoContrato.get(ContratacionParametro.ESTADO_CONTRATO_NO_VIGENTE);
                if (mae != null){
                    this.objetoContrato.setMaeEstadoContratoId(mae.getId());
                    this.objetoContrato.setMaeEstadoContratoCodigo(mae.getValor());
                    this.objetoContrato.setMaeEstadoContratoValor(mae.getNombre());
                }else {
                    this.objetoContrato.setMaeEstadoContratoId(0);
                    this.objetoContrato.setMaeEstadoContratoCodigo("");
                    this.objetoContrato.setMaeEstadoContratoValor("");
                }
            //al no cumplirse ninguno de los casos, se actualizaran los valores a vacio
            } else {
                this.objetoContrato.setMaeEstadoContratoId(0);
                this.objetoContrato.setMaeEstadoContratoCodigo("");
                this.objetoContrato.setMaeEstadoContratoValor("");
            }
        //si alguna de las fechas es nula, se actualizaran los valores a vacio
        }else {
            this.objetoContrato.setMaeEstadoContratoId(0);
            this.objetoContrato.setMaeEstadoContratoCodigo("");
            this.objetoContrato.setMaeEstadoContratoValor("");
        }
    }
    
    public StreamedContent imprimirDetalleContrato() {
        StreamedContent streamedContent2 = null;
        int consecutivo = 1;
        int actualizar = 1;
        String nombre;
        String texto;
        try {
            setListaDetalleTecnologias(new ArrayList<>());
            super.setAccion(ACCION_ADICIONAL_6);
            getContratosServicio().Accion(this);
            // adicionamos encabezado en la primera línea
            texto = "consecutivo,actualizar,numero_contrato,codigo_habilitacion_sede,modalidad_contrato,fecha_hora_inicio,fecha_hora_fin,activo,tipo_tecnologia,codigo_tecnologia,codigo_servicio_habilitacion,tipo_manual_tarifario,manual_tarifario_codigo,ma_manual_tarifario_agno,tipo_manual_tarifario_nuevo,manual_tarifario_codigo_nuevo,ma_manual_tarifario_agno_nuevo,valor_manual,porcentaje_variacion,complejidad,observacion_incluye,observacion_excluye,interdependencia,codigo_habilitacion_sedes,codigo_ambito,codigo_ambito_nuevo\n";
            
            if(getListaDetalleTecnologias().size() > 0){
                //Stream streamDetalle = this.listaDetalleCarga.stream();
                for (CntContratoDetalle dc: this.getListaDetalleTecnologias()) {
                    texto = texto + dc.generarTextoFormatoCargaMasiva(consecutivo,actualizar);
                    consecutivo++;
                }
                // construimos el nombre del archivo
                nombre = "archivo.txt";
                //debemos generar la cadena de bytes a partir de los registros en la lista
                byte[] bytes = texto.getBytes();
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
            }else{
                addError("No se encontraron datos para generar el archivo");
                generarMensajes();
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }
    
    /**
     * @return the listaUbicaciones
     */
    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    /**
     * @param listaUbicaciones the listaUbicaciones to set
     */
    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    /**
     * @return the objetoContrato
     */
    public CntContrato getObjetoContrato() {
        return objetoContrato;
    }

    /**
     * @param objetoContrato the objetoContrato to set
     */
    public void setObjetoContrato(CntContrato objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    /**
     * @return the listaTipoDocumento
     */
    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    /**
     * @param listaTipoDocumento the listaTipoDocumento to set
     */
    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    /**
     * @return the hashTipoDocumento
     */
    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    /**
     * @param hashTipoDocumento the hashTipoDocumento to set
     */
    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    /**
     * @return the listaClasePrestador
     */
    public List<Maestro> getListaClasePrestador() {
        return listaClasePrestador;
    }

    /**
     * @param listaClasePrestador the listaClasePrestador to set
     */
    public void setListaClasePrestador(List<Maestro> listaClasePrestador) {
        this.listaClasePrestador = listaClasePrestador;
    }

    /**
     * @return the hashClasePrestador
     */
    public HashMap<Integer, Maestro> getHashClasePrestador() {
        return hashClasePrestador;
    }

    /**
     * @param hashClasePrestador the hashClasePrestador to set
     */
    public void setHashClasePrestador(HashMap<Integer, Maestro> hashClasePrestador) {
        this.hashClasePrestador = hashClasePrestador;
    }

    /**
     * @return the selPrestadorBean
     */
    public SelPrestadorBean getSelPrestadorBean() {
        selPrestadorBean = (SelPrestadorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorBean");
        return selPrestadorBean;
    }

    /**
     * @param selPrestadorBean the selPrestadorBean to set
     */
    public void setSelPrestadorBean(SelPrestadorBean selPrestadorBean) {
        this.selPrestadorBean = selPrestadorBean;
    }

    /**
     * @return the listaContratoSedes
     */
    public List<CntContratoSede> getListaContratoSedes() {
        return listaContratoSedes;
    }

    /**
     * @param listaContratoSedes the listaContratoSedes to set
     */
    public void setListaContratoSedes(List<CntContratoSede> listaContratoSedes) {
        this.listaContratoSedes = listaContratoSedes;
    }

    /**
     * @return the listaContratoSedesBorrar
     */
    public List<CntContratoSede> getListaContratoSedesBorrar() {
        return listaContratoSedesBorrar;
    }

    /**
     * @param listaContratoSedesBorrar the listaContratoSedesBorrar to set
     */
    public void setListaContratoSedesBorrar(List<CntContratoSede> listaContratoSedesBorrar) {
        this.listaContratoSedesBorrar = listaContratoSedesBorrar;
    }

    /**
     * @return the contadorIdSede
     */
    public int getContadorIdSede() {
        return contadorIdSede;
    }

    /**
     * @param contadorIdSede the contadorIdSede to set
     */
    public void setContadorIdSede(int contadorIdSede) {
        this.contadorIdSede = contadorIdSede;
    }

    /**
     * @return the deshabilitaValorUPC
     */
    public boolean isDeshabilitaValorUPC() {
        return deshabilitaValorUPC;
    }

    /**
     * @param deshabilitaValorUPC the deshabilitaValorUPC to set
     */
    public void setDeshabilitaValorUPC(boolean deshabilitaValorUPC) {
        this.deshabilitaValorUPC = deshabilitaValorUPC;
    }

    /**
     * @return the selPrestadorSedesGenericoBean
     */
    public SelPrestadorSedesGenericoBean getSelPrestadorSedesGenericoBean() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        selPrestadorSedesGenericoBean = (SelPrestadorSedesGenericoBean) elContext.getELResolver().getValue(elContext, null, "selPrestadorSedesGenericoBean");
        //selPrestadorSedesGenericoBean = (SelPrestadorSedesGenericoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorSedesGenericoBean");
        return selPrestadorSedesGenericoBean;
    }

    /**
     * @param selPrestadorSedesGenericoBean the selPrestadorSedesGenericoBean to set
     */
    public void setSelPrestadorSedesGenericoBean(SelPrestadorSedesGenericoBean selPrestadorSedesGenericoBean) {
        this.selPrestadorSedesGenericoBean = selPrestadorSedesGenericoBean;
    }

    /**
     * @return the objetoContratoDetalle
     */
    public CntContratoDetalle getObjetoContratoDetalle() {
        return objetoContratoDetalle;
    }

    /**
     * @param objetoContratoDetalle the objetoContratoDetalle to set
     */
    public void setObjetoContratoDetalle(CntContratoDetalle objetoContratoDetalle) {
        this.objetoContratoDetalle = objetoContratoDetalle;
    }

    /**
     * @return the seleccionoPrestador
     */
    public boolean isSeleccionoPrestador() {
        return seleccionoPrestador;
    }

    /**
     * @param seleccionoPrestador the seleccionoPrestador to set
     */
    public void setSeleccionoPrestador(boolean seleccionoPrestador) {
        this.seleccionoPrestador = seleccionoPrestador;
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

    /**
     * @return the listaEstadoContrato
     */
    public List<Maestro> getListaEstadoContrato() {
        return listaEstadoContrato;
    }

    /**
     * @param listaEstadoContrato the listaEstadoContrato to set
     */
    public void setListaEstadoContrato(List<Maestro> listaEstadoContrato) {
        this.listaEstadoContrato = listaEstadoContrato;
    }

    /**
     * @return the hashEstadoContrato
     */
    public HashMap<String, Maestro> getHashEstadoContrato() {
        return hashEstadoContrato;
    }

    /**
     * @param hashEstadoContrato the hashEstadoContrato to set
     */
    public void setHashEstadoContrato(HashMap<String, Maestro> hashEstadoContrato) {
        this.hashEstadoContrato = hashEstadoContrato;
    }

    /**
     * @return the selInsumosBean
     */
    public SelInsumosBean getSelInsumosBean() {
        selInsumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return selInsumosBean;
    }

    /**
     * @param selInsumosBean the selInsumosBean to set
     */
    public void setSelInsumosBean(SelInsumosBean selInsumosBean) {
        this.selInsumosBean = selInsumosBean;
    }

    /**
     * @return the selMedicamentoBean
     */
    public SelMedicamentoBean getSelMedicamentoBean() {
        selMedicamentoBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return selMedicamentoBean;
    }

    /**
     * @param selMedicamentoBean the selMedicamentoBean to set
     */
    public void setSelMedicamentoBean(SelMedicamentoBean selMedicamentoBean) {
        this.selMedicamentoBean = selMedicamentoBean;
    }

    /**
     * @return the selTecnologiasBean
     */
    public SelTecnologiasBean getSelTecnologiasBean() {
        selTecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return selTecnologiasBean;
    }

    /**
     * @param selTecnologiasBean the selTecnologiasBean to set
     */
    public void setSelTecnologiasBean(SelTecnologiasBean selTecnologiasBean) {
        this.selTecnologiasBean = selTecnologiasBean;
    }

    /**
     * @return the selPaquetesBean
     */
    public SelPaquetesBean getSelPaquetesBean() {
        selPaquetesBean = (SelPaquetesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPaquetesBean");
        return selPaquetesBean;
    }

    /**
     * @param selPaquetesBean the selPaquetesBean to set
     */
    public void setSelPaquetesBean(SelPaquetesBean selPaquetesBean) {
        this.selPaquetesBean = selPaquetesBean;
    }

    /**
     * @return the listaServicios
     */
    public List<MaTecnologiaServicioHabilitacion> getListaServicios() {
        return listaServicios;
    }

    /**
     * @param listaServicios the listaServicios to set
     */
    public void setListaServicios(List<MaTecnologiaServicioHabilitacion> listaServicios) {
        this.listaServicios = listaServicios;
    }

    /**
     * @return the selIss2000Bean
     */
    public SelIss2000Bean getSelIss2000Bean() {
        selIss2000Bean = (SelIss2000Bean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selIss2000Bean");
        return selIss2000Bean;
    }

    /**
     * @param selIss2000Bean the selIss2000Bean to set
     */
    public void setSelIss2000Bean(SelIss2000Bean selIss2000Bean) {
        this.selIss2000Bean = selIss2000Bean;
    }

    /**
     * @return the selIss2001Bean
     */
    public SelIss2001Bean getSelIss2001Bean() {
        selIss2001Bean = (SelIss2001Bean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selIss2001Bean");
        return selIss2001Bean;
    }

    /**
     * @param selIss2001Bean the selIss2001Bean to set
     */
    public void setSelIss2001Bean(SelIss2001Bean selIss2001Bean) {
        this.selIss2001Bean = selIss2001Bean;
    }

    /**
     * @return the selSoatBean
     */
    public SelSoatBean getSelSoatBean() {
        this.selSoatBean = (SelSoatBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selSoatBean");
        return selSoatBean;
    }

    /**
     * @param selSoatBean the selSoatBean to set
     */
    public void setSelSoatBean(SelSoatBean selSoatBean) {
        this.selSoatBean = selSoatBean;
    }

    /**
     * @return the PaqueteTecnologia
     */
    public boolean isPaqueteTecnologia() {
        return paqueteTecnologia;
    }

    /**
     * @param paqueteTecnologia the PaqueteTecnologia to set
     */
    public void setPaqueteTecnologia(boolean paqueteTecnologia) {
        this.paqueteTecnologia = paqueteTecnologia;
    }

    /**
     * @return the deshabilitarServicio
     */
    public boolean isDeshabilitarServicio() {
        return deshabilitarServicio;
    }

    /**
     * @param deshabilitarServicio the deshabilitarServicio to set
     */
    public void setDeshabilitarServicio(boolean deshabilitarServicio) {
        this.deshabilitarServicio = deshabilitarServicio;
    }

    /**
     * @return the listaValoresSoat
     */
    public List<MaSoatTarifarioValor> getListaValoresSoat() {
        return listaValoresSoat;
    }

    /**
     * @param listaValoresSoat the listaValoresSoat to set
     */
    public void setListaValoresSoat(List<MaSoatTarifarioValor> listaValoresSoat) {
        this.listaValoresSoat = listaValoresSoat;
    }
    
    public void actualizarServicio() {
        
    }

    /**
     * @return the listaRegimenContrato
     */
    public List<Maestro> getListaRegimenContrato() {
        return listaRegimenContrato;
    }

    /**
     * @param listaRegimenContrato the listaRegimenContrato to set
     */
    public void setListaRegimenContrato(List<Maestro> listaRegimenContrato) {
        this.listaRegimenContrato = listaRegimenContrato;
    }

    /**
     * @return the hashRegimenContrato
     */
    public HashMap<Integer, Maestro> getHashRegimenContrato() {
        return hashRegimenContrato;
    }

    /**
     * @param hashRegimenContrato the hashRegimenContrato to set
     */
    public void setHashRegimenContrato(HashMap<Integer, Maestro> hashRegimenContrato) {
        this.hashRegimenContrato = hashRegimenContrato;
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    
    boolean compararFechas(Date fecha1, Date fecha2) {
        boolean estado = false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);
        int anio1 = cal1.get(Calendar.YEAR);
        int mes1 = cal1.get(Calendar.MONTH);
        int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
        int anio2 = cal2.get(Calendar.YEAR);
        int mes2 = cal2.get(Calendar.MONTH);
        int dia2 = cal2.get(Calendar.DAY_OF_MONTH);
        if(anio1 == anio2 && mes1 == mes2 && dia1 == dia2) {
            estado = true;
        }
        return estado;
    }
    
    /**
     * Método para validar si la fecha 1 se encuentra antes de la fecha 2
     * 
     * @param fecha1
     * @param fecha2
     * @return 
     */
    boolean fechaAntes(Date fecha1, Date fecha2) {
        boolean estado = false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);
        int anio1 = cal1.get(Calendar.YEAR);
        int mes1 = cal1.get(Calendar.MONTH);
        int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
        int anio2 = cal2.get(Calendar.YEAR);
        int mes2 = cal2.get(Calendar.MONTH);
        int dia2 = cal2.get(Calendar.DAY_OF_MONTH);
        
        if(anio1 < anio2) {
            estado = true;
        }else if(anio1 == anio2) {
            if(mes1<mes2) {
                estado = true;
            }else if (mes1 == mes2) {
                if (dia1<dia2) {
                    estado = true;
                }
            }
        }
        return estado;
    }
    /**
     * Método para validar si la fecha 1 se encuentra antes de la fecha 2 o es igual
     * 
     * @param fecha1
     * @param fecha2
     * @return 
     */
    boolean fechaAntesOIgual(Date fecha1, Date fecha2) {
        boolean estado = false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);
        int anio1 = cal1.get(Calendar.YEAR);
        int mes1 = cal1.get(Calendar.MONTH);
        int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
        int anio2 = cal2.get(Calendar.YEAR);
        int mes2 = cal2.get(Calendar.MONTH);
        int dia2 = cal2.get(Calendar.DAY_OF_MONTH);
        
        if(anio1 < anio2) {
            estado = true;
        }else if(anio1 == anio2) {
            if(mes1<mes2) {
                estado = true;
            }else if (mes1 == mes2) {
                if (dia1<=dia2) {
                    estado = true;
                }
            }
        }
        return estado;
    }
    
    /**
     * Método para validar si la fecha 1 se encuentra despues de la fecha 2
     * 
     * @param fecha1
     * @param fecha2
     * @return 
     */
    boolean fechaDespues(Date fecha1, Date fecha2) {
        boolean estado = false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);
        int anio1 = cal1.get(Calendar.YEAR);
        int mes1 = cal1.get(Calendar.MONTH);
        int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
        int anio2 = cal2.get(Calendar.YEAR);
        int mes2 = cal2.get(Calendar.MONTH);
        int dia2 = cal2.get(Calendar.DAY_OF_MONTH);
        
        if(anio1 > anio2) {
            estado = true;
        }else if(anio1 == anio2) {
            if(mes1>mes2) {
                estado = true;
            }else if (mes1 == mes2) {
                if (dia1>dia2) {
                    estado = true;
                }
            }
        }
        return estado;
    }

    /**
     * @return the listaDetalleTecnologias
     */
    public List<CntContratoDetalle> getListaDetalleTecnologias() {
        return listaDetalleTecnologias;
    }

    /**
     * @param listaDetalleTecnologias the listaDetalleTecnologias to set
     */
    public void setListaDetalleTecnologias(List<CntContratoDetalle> listaDetalleTecnologias) {
        this.listaDetalleTecnologias = listaDetalleTecnologias;
    }

    /**
     * @return the fechaFinContrato
     */
    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    /**
     * @param fechaFinContrato the fechaFinContrato to set
     */
    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    /**
     * @return the actualizarContratoDetalles
     */
    public boolean isActualizarContratoDetalles() {
        return actualizarContratoDetalles;
    }

    /**
     * @param actualizarContratoDetalles the actualizarContratoDetalles to set
     */
    public void setActualizarContratoDetalles(boolean actualizarContratoDetalles) {
        this.actualizarContratoDetalles = actualizarContratoDetalles;
    }

    /**
     * @return the listaContratoNotaTecnica
     */
    public List<CntContratoNotaTecnica> getListaContratoNotaTecnica() {
        return listaContratoNotaTecnica;
    }

    /**
     * @param listaContratoNotaTecnica the listaContratoNotaTecnica to set
     */
    public void setListaContratoNotaTecnica(List<CntContratoNotaTecnica> listaContratoNotaTecnica) {
        this.listaContratoNotaTecnica = listaContratoNotaTecnica;
    }

    /**
     * @return the lazyContratoNotaTecnica
     */
    public LazyDataModel<CntContratoNotaTecnica> getLazyContratoNotaTecnica() {
        return lazyContratoNotaTecnica;
    }

    /**
     * @param lazyContratoNotaTecnica the lazyContratoNotaTecnica to set
     */
    public void setLazyContratoNotaTecnica(LazyDataModel<CntContratoNotaTecnica> lazyContratoNotaTecnica) {
        this.lazyContratoNotaTecnica = lazyContratoNotaTecnica;
    }

    /**
     * @return the paramConsultaContratoNotaTecnica
     */
    public ParamConsulta getParamConsultaContratoNotaTecnica() {
        return paramConsultaContratoNotaTecnica;
    }

    /**
     * @param paramConsultaContratoNotaTecnica the paramConsultaContratoNotaTecnica to set
     */
    public void setParamConsultaContratoNotaTecnica(ParamConsulta paramConsultaContratoNotaTecnica) {
        this.paramConsultaContratoNotaTecnica = paramConsultaContratoNotaTecnica;
    }

    /**
     * @return the objetoContratoNotaTecnica
     */
    public CntContratoNotaTecnica getObjetoContratoNotaTecnica() {
        return objetoContratoNotaTecnica;
    }

    /**
     * @param objetoContratoNotaTecnica the objetoContratoNotaTecnica to set
     */
    public void setObjetoContratoNotaTecnica(CntContratoNotaTecnica objetoContratoNotaTecnica) {
        this.objetoContratoNotaTecnica = objetoContratoNotaTecnica;
    }

    /**
     * @return the actualizarNotaTecnica
     */
    public boolean isActualizarNotaTecnica() {
        return actualizarNotaTecnica;
    }

    /**
     * @param actualizarNotaTecnica the actualizarNotaTecnica to set
     */
    public void setActualizarNotaTecnica(boolean actualizarNotaTecnica) {
        this.actualizarNotaTecnica = actualizarNotaTecnica;
    }

    /**
     * @return the listaImpresionNotaTecnica
     */
    public List<CntContratoNotaTecnica> getListaImpresionNotaTecnica() {
        return listaImpresionNotaTecnica;
    }

    /**
     * @param listaImpresionNotaTecnica the listaImpresionNotaTecnica to set
     */
    public void setListaImpresionNotaTecnica(List<CntContratoNotaTecnica> listaImpresionNotaTecnica) {
        this.listaImpresionNotaTecnica = listaImpresionNotaTecnica;
    }

    /**
     * @return the listaMunicipiosCobertura
     */
    public List<Ubicacion> getListaMunicipiosCobertura() {
        return listaMunicipiosCobertura;
    }

    /**
     * @param listaMunicipiosCobertura the listaMunicipiosCobertura to set
     */
    public void setListaMunicipiosCobertura(List<Ubicacion> listaMunicipiosCobertura) {
        this.listaMunicipiosCobertura = listaMunicipiosCobertura;
    }


    /**
     * @return the objetoHistorico
     */
    public CntContratoHistorico getObjetoHistorico() {
        return objetoHistorico;
    }

    /**
     * @param objetoHistorico the objetoHistorico to set
     */
    public void setObjetoHistorico(CntContratoHistorico objetoHistorico) {
        this.objetoHistorico = objetoHistorico;
    }

    /**
     * @return the lazyHistorico
     */
    public LazyDataModel<CntContratoHistorico> getLazyHistorico() {
        return lazyHistorico;
    }

    /**
     * @param lazyHistorico the lazyHistorico to set
     */
    public void setLazyHistorico(LazyDataModel<CntContratoHistorico> lazyHistorico) {
        this.lazyHistorico = lazyHistorico;
    }

    /**
     * @return the registrosHistorico
     */
    public List<CntContratoHistorico> getRegistrosHistorico() {
        return registrosHistorico;
    }

    /**
     * @param registrosHistorico the registrosHistorico to set
     */
    public void setRegistrosHistorico(List<CntContratoHistorico> registrosHistorico) {
        this.registrosHistorico = registrosHistorico;
    }

    /**
     * @return the paramConsultaHistorico
     */
    public ParamConsulta getParamConsultaHistorico() {
        return paramConsultaHistorico;
    }

    /**
     * @param paramConsultaHistorico the paramConsultaHistorico to set
     */
    public void setParamConsultaHistorico(ParamConsulta paramConsultaHistorico) {
        this.paramConsultaHistorico = paramConsultaHistorico;
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
