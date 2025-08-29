package com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacionHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio.CmFeRipsValidacionServicioIface;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmUtilidades;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.FilterMeta;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class CmFeRipsValidacionBean extends Url {

    public static final char DO_ACCION_LISTAR_VALIDACIONES = 'a';
    public static final char DO_ACCION_LISTAR_HISTORICO_VALIDACIONES = 'b';
    public static final int RANGO_FECHA_PRESTACION = 10;

    private int tamanoPagina = 30;
    private CmFeRipsValidacionServicioIface cmFeRipsValidacionServicio;
    private List<CmFeRipsValidacion> registros;
    private List<CmFeRipsValidacionHistorico> registrosValidacionHistorico;
    private CmFeRipsValidacion objeto;
    private CmFeRipsValidacionHistorico objetoValidacionHistorico = new CmFeRipsValidacionHistorico();
    private LazyDataModel<CmFeRipsValidacion> lazyCmFeRipsValidacion;
    private LazyDataModel<CmFeRipsValidacionHistorico> lazyCmFeRipsValidacionHistorico;
    private ParamConsulta paramConsultaValidacionHistorico;


    @PreDestroy
    public void preDestroy() {
        this.objeto = null;
    }

    public CmFeRipsValidacionBean() {
        this.objeto = new CmFeRipsValidacion();
        Modulo _mod = super.validarModulo(Modulo.ID_CM_FE_RIPS_VALIDACIONES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCmFeRipsValidacion = new LazyDataModel<CmFeRipsValidacion>() {

                private List<CmFeRipsValidacion> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmFeRipsValidacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmFeRipsValidacion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmFeRipsValidacion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmFeRipsValidacion objeto : lista) {
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
        getCmFeRipsValidacionServicio().cargaInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(DO_ACCION_LISTAR_VALIDACIONES);
        getCmFeRipsValidacionServicio().Accion(this);
    }
    
     public void refrescarHistorico() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(DO_ACCION_LISTAR_HISTORICO_VALIDACIONES);
        getCmFeRipsValidacionServicio().Accion(this);
    }


    public void crear() {

        super.setAccion(ACCION_CREAR);
        getCmFeRipsValidacionServicio().Accion(this);

        procesoFinal();
    }

    public void guardar() {

        super.setAccion(ACCION_GUARDAR);
        getCmFeRipsValidacionServicio().Accion(this);

        procesoFinal();
    }


    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getCmFeRipsValidacionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void ver(int id) { 
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getCmFeRipsValidacionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }
    
    public void verHistorial(int id) { 
        getObjeto().setId(id);
        inicializarTablaTransacciones(id);
        PrimeFaces.current().resetInputs("frmHistorial");
        PrimeFaces.current().ajax().update("frmHistorial");
        PrimeFaces.current().executeScript("PF('dialgoHistorial').show()");
        procesoFinal();
    }

   public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCmFeRipsValidacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    public void borrar(int _id) {
//        getObjeto().setId(_id);
//        super.setAccion(ACCION_BORRAR);
//        getCmRipsCargaServicio().Accion(this);
//        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog("Guardar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsValidaciones");
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog("Modificar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsValidaciones");
                    break;
                case Url.ACCION_BORRAR:
                    crearLog(" Borrar ", getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Listar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsValidaciones");
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    crearLog("Crear", getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(" Editar", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2: 
                    break;
                case Url.ACCION_ADICIONAL_3:;
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                case Url.ACCION_ADICIONAL_5:
                    break;
            }
        }
        generarMensajes();
    }
    
    public void inicializarTablaTransacciones(int idValidacion) {
        
           getParamConsultaValidacionHistorico().setParametroConsulta1(idValidacion);
        
        lazyCmFeRipsValidacionHistorico = new LazyDataModel<CmFeRipsValidacionHistorico>() {

            private List<CmFeRipsValidacionHistorico> lista;
            
            @Override                      
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaValidacionHistorico().getCantidadRegistros();
            }

            @Override
            public List<CmFeRipsValidacionHistorico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaValidacionHistorico().setPrimerRegistro(primerRegistro);
                getParamConsultaValidacionHistorico().setRegistrosPagina(registrosPagina);
                getParamConsultaValidacionHistorico().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaValidacionHistorico().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorico();
                lista = getRegistrosValidacionHistorico();
                setRowCount(getParamConsultaValidacionHistorico().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmFeRipsValidacionHistorico objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CmFeRipsValidacionHistorico getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CmFeRipsValidacionHistorico objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }
    
    public ParamConsulta getParamConsultaValidacionHistorico() {
        if (paramConsultaValidacionHistorico == null) {
            paramConsultaValidacionHistorico = new ParamConsulta();
        }
        return paramConsultaValidacionHistorico;
    }

    public void setParamConsultaValidacionHistorico(ParamConsulta paramConsultaValidacionHistorico) {
        this.paramConsultaValidacionHistorico = paramConsultaValidacionHistorico;
    }

    public LazyDataModel<CmFeRipsValidacionHistorico> getLazyCmFeRipsValidacionHistorico() {
        return lazyCmFeRipsValidacionHistorico;
    }

    public void setLazyCmFeRipsValidacionHistorico(LazyDataModel<CmFeRipsValidacionHistorico> lazyCmFeRipsValidacionHistorico) {
        this.lazyCmFeRipsValidacionHistorico = lazyCmFeRipsValidacionHistorico;
    }

    public CmFeRipsValidacionServicioIface getCmFeRipsValidacionServicio() {
        return cmFeRipsValidacionServicio;
    }

    public void setCmFeRipsValidacionServicio(CmFeRipsValidacionServicioIface cmFeRipsValidacionServicio) {
        this.cmFeRipsValidacionServicio = cmFeRipsValidacionServicio;
    }

    public List<CmFeRipsValidacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFeRipsValidacion> registros) {
        this.registros = registros;
    }

    public List<CmFeRipsValidacionHistorico> getRegistrosValidacionHistorico() {
        return registrosValidacionHistorico;
    }

    public void setRegistrosValidacionHistorico(List<CmFeRipsValidacionHistorico> registrosValidacionHistorico) {
        this.registrosValidacionHistorico = registrosValidacionHistorico;
    }

    public CmFeRipsValidacionHistorico getObjetoValidacionHistorico() {
        return objetoValidacionHistorico;
    }

    public void setObjetoValidacionHistorico(CmFeRipsValidacionHistorico objetoValidacionHistorico) {
        this.objetoValidacionHistorico = objetoValidacionHistorico;
    }

    public CmFeRipsValidacion getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFeRipsValidacion objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CmFeRipsValidacion> getLazyCmFeRipsValidacion() {
        return lazyCmFeRipsValidacion;
    }

    public void setLazyCmFeRipsValidacion(LazyDataModel<CmFeRipsValidacion> lazyCmFeRipsValidacion) {
        this.lazyCmFeRipsValidacion = lazyCmFeRipsValidacion;
    }
    
    public void getAuditoriaModificacion(String str) {
        CmUtilidades.getAuditoriaModificacion(str);
    }
 
}
