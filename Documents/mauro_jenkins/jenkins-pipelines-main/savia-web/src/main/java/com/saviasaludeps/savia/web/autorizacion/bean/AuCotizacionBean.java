package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuCotizacionServicioIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.Url;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class AuCotizacionBean extends Url {

    private AuCotizacionServicioIface auCotizacionServicio;

    
    private List<AuAnexo3Item> registros;
    private AuAnexo3Item objeto;
    private AuAnexo3 objetoAnexo3;
    private AuCotizacion objetoCotizacion;
    private CntPrestadorSede objetoPrestadorSede;
    private CntPrestador objetoPrestador;
    private AntAnticipoItem objetoAnticipoItem;
    private LazyDataModel<AuAnexo3Item> lazyAuAnexo3;
    private CntContratoSede cntContratoSede;
    private HashMap<Integer, Maestro> hashTipoDocumentos;
    private int tipoTarifa;
    private boolean valorEditable;
    private List<CntPrestadorSede> listaPrestadoresSedes;
    private boolean tipoEditable;
    private CntPrestadorSede prestadorSede;
    private CntPrestador prestador;
    private LazyDataModel<CntPrestadorSede> lazyIPS;
    private ParamConsulta paramConsulta2;   
    private LazyDataModel<AntAnticipo> lazyAnticipos;
    private List<AntAnticipo> listaAnticipos;

    private HashMap<Integer, Maestro> hashTipoCargue;
    private HashMap<Integer, Maestro> hashTipoAmbito;
    private HashMap<Integer, Maestro> hashTipoServicioAtencion;
    private HashMap<Integer, Usuario> hashUsuarios;
    private HashMap<Integer, Maestro> hashTipoAnulado;
    private HashMap<Integer, Maestro> hashRegimenAfiliado;
    private HashMap<Integer, Maestro> hashGeneroAfiliado;
    private HashMap<Integer, PePrograma> hashProgramaEspecial;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private HashMap<Integer, Maestro> hashOrigenAtencion;
    private HashMap<Integer, Maestro> hashTipoPrioridad;
    private HashMap<Integer, Maestro> hashTipoUbicacionPaciente;
    private HashMap<Integer, Maestro> hashTipoDiagnostico;
    private HashMap<Integer, Maestro> hashTipoDocumentoArchivo;
    private HashMap<Integer, Maestro> hashTipoDocumento2;
    private HashMap<Integer, Maestro> hashEstadosAfiliado;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    private HashMap<Integer, Maestro> hashCausaExterna;
    private HashMap<Integer, Maestro> hashTipoFinalidad;
    private HashMap<Integer, Maestro> hashTipoCatastrofico;
    private HashMap<Integer, Maestro> hashTipoViaAdministracion;
    private HashMap<Integer, Maestro> hashTipoAcciones;
    private HashMap<Integer, Maestro> hashTipoMotivosRechazo;
    private HashMap<Integer, Maestro> hashTipoDocumentoProfesional;
    private HashMap<Integer, Maestro> hashRechazos;
    private HashMap<Integer, Maestro> hashTipoAuditor;
    private HashMap<Integer, Maestro> hashAnulaciones;
    
    private List<Maestro> listaTipoDocumentoArchivo;
    private int maeTipoDocumentoArchivo;
    private List<Maestro> listaTipoAmbito;
    private List<AuAnexo3Item> listaPosfechados;
    private List<Maestro> listaClasificacion;

    //Variable auxiliar
    private String observacion;
    private List<AuItemBitacora> listaBitacoras;
    private List<Maestro> listaTipoTecnologia;
    private List<MaMedicamento> listaMedicamentoDeAgrupador;
    private HashMap<Integer, MaMedicamento> hastMedicamentoDeAgrupador;

    private MaTecnologiaRemoto getMaTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto("MaTecnologiaServicio", MaTecnologiaRemoto.class.getName());
    }

    public AuCotizacionBean() {
        this.objeto = new AuAnexo3Item();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_COTIZACIONES);
        super.addListaParamConsultas(new ParamConsulta());
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAuAnexo3 = new LazyDataModel<AuAnexo3Item>() {

                private List<AuAnexo3Item> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                    public List<AuAnexo3Item> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AuAnexo3Item objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuAnexo3Item getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo3Item objeto : lista) {
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
        getAuCotizacionServicio().cargaInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuCotizacionServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        setListaPrestadoresSedes(new ArrayList<>());
        setObjetoAnexo3(new AuAnexo3());
        setObjetoCotizacion(new AuCotizacion());
        setObjeto(new AuAnexo3Item());
        getObjetoCotizacion().setAuSolicitudAdjuntosList(new ArrayList());
        getAuCotizacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:pCrearDatos");
        PrimeFaces.current().ajax().update("frmCrear:botonCargarRips");
        PrimeFaces.current().ajax().update("frmCrear:prestador");
        PrimeFaces.current().ajax().update("frmCrear:tablaAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void ver(int idObjeto) {
        setObjetoAnexo3(new AuAnexo3());
        getObjetoAnexo3().setId(idObjeto);
        super.setAccion(Url.ACCION_VER);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verCotizacion(int idAnexo3Item) {
        getObjeto().setId(idAnexo3Item);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void gestionar(int id) {
        setListaPrestadoresSedes(new ArrayList<>());
        setObjetoAnexo3(new AuAnexo3());
        setObjetoCotizacion(new AuCotizacion());
        setObjeto(new AuAnexo3Item());
        setObjetoPrestador(new CntPrestador());
        setObjetoPrestadorSede(new CntPrestadorSede());
        setValorEditable(false);
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        if(getObjeto().isPosfechado()){
            if(getObjetoCotizacion().getAntAnticiposId() != null){
                addError("No se permite asociar un anticipo porque la tecnología tiene posfechado");
            }
        }
        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getAuCotizacionServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        }
        procesoFinal();
    }
    
     public void verVentanaAnticipoItem(int id) {
        setObjetoAnticipoItem(new AntAnticipoItem());
        getObjetoAnticipoItem().setAntAnticiposId(new AntAnticipo());
        getObjetoAnticipoItem().getAntAnticiposId().setId(id);
        getObjetoAnticipoItem().setMaTecnologiaId(getObjeto().getMaTecnologiaId());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmAuCotizaciones");
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmAuCotizaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmAuditoriaGestionar:labelDatosAuditoria");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            crearLog("Gestionar", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmVerCotizacion");
                            PrimeFaces.current().executeScript("PF('dialogoVerCotizacion').show()");
                            crearLog("Ver Cotización", getObjetoCotizacion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmAnticipoItem");
                            PrimeFaces.current().executeScript("PF('dlgInfoAnticipoItem').show()");
                            crearLog("Ver Cotización", getObjetoCotizacion().toString());
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVerSolicitud').show()");
                    crearLog("Ver Solicitud", getObjetoAnexo3().toString());
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmGestionarAdjuntos");
                            PrimeFaces.current().executeScript("PF('dialogoGestionarAdjuntos').show()");
                            crearLog("Ver Adjuntos", getObjetoCotizacion().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            setObjeto(new AuAnexo3Item());
                            setObjetoCotizacion(new AuCotizacion());
                            PrimeFaces.current().executeScript("PF('dialogoGestionarAdjuntos').hide()");
                            crearLog("Ver Adjuntos", getObjetoCotizacion().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmMensajeBitacoraRechazo");
                            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraRechazo').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmAuCotizaciones");
                            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraRechazo').hide()");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    PrimeFaces.current().ajax().update("frmVerBitacoras");
                    PrimeFaces.current().executeScript("PF('dialogoVerBitacorass').show()");
                    break;
            }
        }
        generarMensajes();
    }

    public AuCotizacionServicioIface getAuCotizacionServicio() {
        return auCotizacionServicio;
    }

    public void setAuCotizacionServicio(AuCotizacionServicioIface auCotizacionServicio) {
        this.auCotizacionServicio = auCotizacionServicio;
    }

    public List<AuAnexo3Item> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo3Item> registros) {
        this.registros = registros;
    }

    public AuAnexo3Item getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo3Item objeto) {
        this.objeto = objeto;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public AntAnticipoItem getObjetoAnticipoItem() {
        return objetoAnticipoItem;
    }

    public void setObjetoAnticipoItem(AntAnticipoItem objetoAnticipoItem) {
        this.objetoAnticipoItem = objetoAnticipoItem;
    }

    public CntPrestador getObjetoPrestador() {
        return objetoPrestador;
    }

    public void setObjetoPrestador(CntPrestador objetoPrestador) {
        this.objetoPrestador = objetoPrestador;
    }

    public LazyDataModel<AuAnexo3Item> getLazyAuAnexo3() {
        return lazyAuAnexo3;
    }

    public void setLazyAuAnexo3(LazyDataModel<AuAnexo3Item> lazyAuAnexo3) {
        this.lazyAuAnexo3 = lazyAuAnexo3;
    }

    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentos() {
        return hashTipoDocumentos;
    }

    public void setHashTipoDocumentos(HashMap<Integer, Maestro> hashTipoDocumentos) {
        this.hashTipoDocumentos = hashTipoDocumentos;
    }

    public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }

    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerTipoDocumentoIps(int id) {
        try {
            return hashTipoDocumento2.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerBoolean(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    public HashMap<Integer, Maestro> getHashTipoCargue() {
        return hashTipoCargue;
    }

    public void setHashTipoCargue(HashMap<Integer, Maestro> hashTipoCargue) {
        this.hashTipoCargue = hashTipoCargue;
    }

    public HashMap<Integer, Maestro> getHashTipoAmbito() {
        return hashTipoAmbito;
    }

    public void setHashTipoAmbito(HashMap<Integer, Maestro> hashTipoAmbito) {
        this.hashTipoAmbito = hashTipoAmbito;
    }

    public HashMap<Integer, Maestro> getHashTipoServicioAtencion() {
        return hashTipoServicioAtencion;
    }

    public void setHashTipoServicioAtencion(HashMap<Integer, Maestro> hashTipoServicioAtencion) {
        this.hashTipoServicioAtencion = hashTipoServicioAtencion;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public HashMap<Integer, Maestro> getHashTipoAnulado() {
        return hashTipoAnulado;
    }

    public void setHashTipoAnulado(HashMap<Integer, Maestro> hashTipoAnulado) {
        this.hashTipoAnulado = hashTipoAnulado;
    }

    public HashMap<Integer, Maestro> getHashRegimenAfiliado() {
        return hashRegimenAfiliado;
    }

    public void setHashRegimenAfiliado(HashMap<Integer, Maestro> hashRegimenAfiliado) {
        this.hashRegimenAfiliado = hashRegimenAfiliado;
    }

    public HashMap<Integer, Maestro> getHashGeneroAfiliado() {
        return hashGeneroAfiliado;
    }

    public void setHashGeneroAfiliado(HashMap<Integer, Maestro> hashGeneroAfiliado) {
        this.hashGeneroAfiliado = hashGeneroAfiliado;
    }

    public HashMap<Integer, PePrograma> getHashProgramaEspecial() {
        return hashProgramaEspecial;
    }

    public void setHashProgramaEspecial(HashMap<Integer, PePrograma> hashProgramaEspecial) {
        this.hashProgramaEspecial = hashProgramaEspecial;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public HashMap<Integer, Maestro> getHashOrigenAtencion() {
        return hashOrigenAtencion;
    }

    public void setHashOrigenAtencion(HashMap<Integer, Maestro> hashOrigenAtencion) {
        this.hashOrigenAtencion = hashOrigenAtencion;
    }

    public HashMap<Integer, Maestro> getHashTipoPrioridad() {
        return hashTipoPrioridad;
    }

    public void setHashTipoPrioridad(HashMap<Integer, Maestro> hashTipoPrioridad) {
        this.hashTipoPrioridad = hashTipoPrioridad;
    }

    public HashMap<Integer, Maestro> getHashTipoUbicacionPaciente() {
        return hashTipoUbicacionPaciente;
    }

    public void setHashTipoUbicacionPaciente(HashMap<Integer, Maestro> hashTipoUbicacionPaciente) {
        this.hashTipoUbicacionPaciente = hashTipoUbicacionPaciente;
    }

    public HashMap<Integer, Maestro> getHashTipoDiagnostico() {
        return hashTipoDiagnostico;
    }

    public void setHashTipoDiagnostico(HashMap<Integer, Maestro> hashTipoDiagnostico) {
        this.hashTipoDiagnostico = hashTipoDiagnostico;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoArchivo() {
        return hashTipoDocumentoArchivo;
    }

    public void setHashTipoDocumentoArchivo(HashMap<Integer, Maestro> hashTipoDocumentoArchivo) {
        this.hashTipoDocumentoArchivo = hashTipoDocumentoArchivo;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento2() {
        return hashTipoDocumento2;
    }

    public void setHashTipoDocumento2(HashMap<Integer, Maestro> hashTipoDocumento2) {
        this.hashTipoDocumento2 = hashTipoDocumento2;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliado() {
        return hashEstadosAfiliado;
    }

    public void setHashEstadosAfiliado(HashMap<Integer, Maestro> hashEstadosAfiliado) {
        this.hashEstadosAfiliado = hashEstadosAfiliado;
    }

    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    public HashMap<Integer, Maestro> getHashCausaExterna() {
        return hashCausaExterna;
    }

    public void setHashCausaExterna(HashMap<Integer, Maestro> hashCausaExterna) {
        this.hashCausaExterna = hashCausaExterna;
    }

    public HashMap<Integer, Maestro> getHashTipoFinalidad() {
        return hashTipoFinalidad;
    }

    public void setHashTipoFinalidad(HashMap<Integer, Maestro> hashTipoFinalidad) {
        this.hashTipoFinalidad = hashTipoFinalidad;
    }

    public HashMap<Integer, Maestro> getHashTipoCatastrofico() {
        return hashTipoCatastrofico;
    }

    public void setHashTipoCatastrofico(HashMap<Integer, Maestro> hashTipoCatastrofico) {
        this.hashTipoCatastrofico = hashTipoCatastrofico;
    }

    public HashMap<Integer, Maestro> getHashTipoViaAdministracion() {
        return hashTipoViaAdministracion;
    }

    public void setHashTipoViaAdministracion(HashMap<Integer, Maestro> hashTipoViaAdministracion) {
        this.hashTipoViaAdministracion = hashTipoViaAdministracion;
    }

    public HashMap<Integer, Maestro> getHashTipoAcciones() {
        return hashTipoAcciones;
    }

    public void setHashTipoAcciones(HashMap<Integer, Maestro> hashTipoAcciones) {
        this.hashTipoAcciones = hashTipoAcciones;
    }

    public HashMap<Integer, Maestro> getHashTipoMotivosRechazo() {
        return hashTipoMotivosRechazo;
    }

    public void setHashTipoMotivosRechazo(HashMap<Integer, Maestro> hashTipoMotivosRechazo) {
        this.hashTipoMotivosRechazo = hashTipoMotivosRechazo;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoProfesional() {
        return hashTipoDocumentoProfesional;
    }

    public void setHashTipoDocumentoProfesional(HashMap<Integer, Maestro> hashTipoDocumentoProfesional) {
        this.hashTipoDocumentoProfesional = hashTipoDocumentoProfesional;
    }

    public HashMap<Integer, Maestro> getHashRechazos() {
        return hashRechazos;
    }

    public void setHashRechazos(HashMap<Integer, Maestro> hashRechazos) {
        this.hashRechazos = hashRechazos;
    }

    public HashMap<Integer, Maestro> getHashTipoAuditor() {
        return hashTipoAuditor;
    }

    public void setHashTipoAuditor(HashMap<Integer, Maestro> hashTipoAuditor) {
        this.hashTipoAuditor = hashTipoAuditor;
    }

    public HashMap<Integer, Maestro> getHashAnulaciones() {
        return hashAnulaciones;
    }

    public void setHashAnulaciones(HashMap<Integer, Maestro> hashAnulaciones) {
        this.hashAnulaciones = hashAnulaciones;
    }

    public List<Maestro> getListaTipoDocumentoArchivo() {
        return listaTipoDocumentoArchivo;
    }

    public void setListaTipoDocumentoArchivo(List<Maestro> listaTipoDocumentoArchivo) {
        this.listaTipoDocumentoArchivo = listaTipoDocumentoArchivo;
    }

    public List<Maestro> getListaClasificacion() {
        return listaClasificacion;
    }

    public void setListaClasificacion(List<Maestro> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
    }

    public int getMaeTipoDocumentoArchivo() {
        return maeTipoDocumentoArchivo;
    }

    public void setMaeTipoDocumentoArchivo(int maeTipoDocumentoArchivo) {
        this.maeTipoDocumentoArchivo = maeTipoDocumentoArchivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<AuItemBitacora> getListaBitacoras() {
        return listaBitacoras;
    }

    public void setListaBitacoras(List<AuItemBitacora> listaBitacoras) {
        this.listaBitacoras = listaBitacoras;
    }

    public List<Maestro> getListaTipoTecnologia() {
        return listaTipoTecnologia;
    }

    public void setListaTipoTecnologia(List<Maestro> listaTipoTecnologia) {
        this.listaTipoTecnologia = listaTipoTecnologia;
    }

    public AuAnexo3 getObjetoAnexo3() {
        return objetoAnexo3;
    }

    public void setObjetoAnexo3(AuAnexo3 objetoAnexo3) {
        this.objetoAnexo3 = objetoAnexo3;
    }

    public List<Maestro> getListaTipoAmbito() {
        return listaTipoAmbito;
    }

    public void setListaTipoAmbito(List<Maestro> listaTipoAmbito) {
        this.listaTipoAmbito = listaTipoAmbito;
    }

    public List<AuAnexo3Item> getListaPosfechados() {
        return listaPosfechados;
    }

    public void setListaPosfechados(List<AuAnexo3Item> listaPosfechados) {
        this.listaPosfechados = listaPosfechados;
    }

    public List<MaMedicamento> getListaMedicamentoDeAgrupador() {
        return listaMedicamentoDeAgrupador;
    }

    public void setListaMedicamentoDeAgrupador(List<MaMedicamento> listaMedicamentoDeAgrupador) {
        this.listaMedicamentoDeAgrupador = listaMedicamentoDeAgrupador;
    }

    public HashMap<Integer, MaMedicamento> getHastMedicamentoDeAgrupador() {
        return hastMedicamentoDeAgrupador;
    }

    public void setHastMedicamentoDeAgrupador(HashMap<Integer, MaMedicamento> hastMedicamentoDeAgrupador) {
        this.hastMedicamentoDeAgrupador = hastMedicamentoDeAgrupador;
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

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
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

    public String obtenerTipoTencnologia(int id) {
        return AuConstantes.obtenerTipoTecnologia(id);
    }

    public AuCotizacion getObjetoCotizacion() {
        return objetoCotizacion;
    }

    public void setObjetoCotizacion(AuCotizacion objetoCotizacion) {
        this.objetoCotizacion = objetoCotizacion;
    }

    public void obtenerValorTecnologia() {
        try {
            //Desbloquear inserción de valor manual si el tipo PROPIA
            if (getObjetoCotizacion().getTipoTarifa() == AuCotizacion.TIPO_TARIFA_PROPIA) {
                setValorEditable(false);
                getObjetoCotizacion().setValorTecnologia(BigDecimal.ZERO);
            } else {
                setValorEditable(true);
                BigDecimal valor = BigDecimal.ZERO;
                MaTecnologia tecnologia = getMaTecnologiaRemoto().consultar(getObjeto().getMaTecnologiaId());
                if (tecnologia == null) {
                    addError("No se encuentra la tecnología con ID:" + getObjeto().getMaTecnologiaId());
                }
                if (getObjeto().getTipoTecnologia() == AuCotizacion.TIPO_TECNOLOGIA) {
                    switch (getObjetoCotizacion().getTipoTarifa()) {
                        case AuCotizacion.TIPO_TARIFA_ISS2000:
                            if (tecnologia.getMaIss2000Tarifario() != null) {
                                valor = tecnologia.getMaIss2000Tarifario().getMonto();
                            }
                            break;
                        case AuCotizacion.TIPO_TARIFA_ISS2001:
                            if (tecnologia.getMaIss2001Tarifario() != null) {
                                valor = tecnologia.getMaIss2001Tarifario().getMonto();
                            }
                            break;
                        case AuCotizacion.TIPO_TARIFA_SOAT:
                            if (tecnologia.getMaSoatTarifario() != null) {
                                valor = getAuCotizacionServicio().consultarValorSoat(tecnologia.getMaSoatTarifario().getId());
                            }
                            break;
                        default:
                            break;
                    }
                }
                getObjetoCotizacion().setValorTecnologia(valor);
            }
            PrimeFaces.current().ajax().update("frmGestionar:valorTotal");
            PrimeFaces.current().ajax().update("frmGestionar:tipoTarifa");
        } catch (Exception ex) {
            addError("Error el id de tecnología del Item no existe: " + ex.toString());
        } finally {
            generarMensajes();
        }
    }

    public int getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(int tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public boolean isValorEditable() {
        return valorEditable;
    }

    public void setValorEditable(boolean valorEditable) {
        this.valorEditable = valorEditable;
    }
    
    public boolean habilitarValorTotal(){
        boolean validate = true;
        if(valorEditable ){
            validate = valorEditable;
        }else if(!getObjetoCotizacion().isHabilitarCamposAnticipos()){
            validate = false;
        }
        return validate;
    }
    
    public boolean habilitarTarifa(){
        boolean validate = true;
        if(tipoEditable ){
            validate = tipoEditable;
        }else if(!getObjetoCotizacion().isHabilitarCamposAnticipos()){
            validate = false;
        }
        return validate;
    }
    
    public void calcularValor() {
        //Obtener el valor y adicionar el porcentaje
        obtenerValorTecnologia();
        if (getObjetoCotizacion().getValorTecnologia() != null) {
            getObjetoCotizacion().setValorTecnologia(
                    getObjetoCotizacion().getValorTecnologia()
                            .multiply(getObjetoCotizacion().getPorcentajeNegociacion())
                            .divide(new BigDecimal(AuCotizacion.CIEN), RoundingMode.HALF_UP)
                            .add(getObjetoCotizacion().getValorTecnologia())
            );
            PrimeFaces.current().ajax().update("frmGestionar:valorTotal");
        }
    }

    public boolean isTipoEditable() {
        return tipoEditable;
    }

    public void setTipoEditable(boolean tipoEditable) {
        this.tipoEditable = tipoEditable;
    }

    public void consultarSedes() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoSedeLista').show()");
            PrimeFaces.current().ajax().update("frmSedeLista");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public CntPrestadorSede getPrestadorSede() {
        return prestadorSede;
    }

    public void setPrestadorSede(CntPrestadorSede prestadorSede) {
        this.prestadorSede = prestadorSede;
    }

    public CntPrestador getPrestador() {
        return prestador;
    }

    public void setPrestador(CntPrestador prestador) {
        this.prestador = prestador;
    }

    public LazyDataModel<AntAnticipo> getLazyAnticipos() {
        return lazyAnticipos;
    }

    public void setLazyAnticipos(LazyDataModel<AntAnticipo> lazyAnticipos) {
        this.lazyAnticipos = lazyAnticipos;
    }    

    public List<AntAnticipo> getListaAnticipos() {
        return listaAnticipos;
    }

    public void setListaAnticipos(List<AntAnticipo> listaAnticipos) {
        this.listaAnticipos = listaAnticipos;
    }
    
    public void refrescarAnticipo() {
        getAuCotizacionServicio().listarAnticipos(this);
    }
    
    public void refrescarIps() {
        getAuCotizacionServicio().listarIPS(this);
    }

    public void consultarIPS() {
        try {
            this.setParamConsulta2(new ParamConsulta());
            this.getParamConsulta2().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyIPS = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaIPS;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta2().setPrimerRegistro(primerRegistro);
                    getParamConsulta2().setRegistrosPagina(registrosPagina);
                    getParamConsulta2().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta2().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarIps();
                    listaIPS = getListaPrestadoresSedes();
                    setRowCount(getParamConsulta2().getCantidadRegistros());
                    return listaIPS;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntPrestadorSede ips : listaIPS) {
                        if (id.equals(ips.getId())) {
                            return ips;
                        }
                    }
                    return null;
                }
            };
            PrimeFaces.current().ajax().update("frmIpsLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");

        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public LazyDataModel<CntPrestadorSede> getLazyIPS() {
        return lazyIPS;
    }

    public void setLazyIPS(LazyDataModel<CntPrestadorSede> lazyIPS) {
        this.lazyIPS = lazyIPS;
    }

    public ParamConsulta getParamConsulta2() {
        return paramConsulta2;
    }

    public void setParamConsulta2(ParamConsulta paramConsulta2) {
        this.paramConsulta2 = paramConsulta2;
    }

    public List<CntPrestadorSede> getListaPrestadoresSedes() {
        return listaPrestadoresSedes;
    }

    public void setListaPrestadoresSedes(List<CntPrestadorSede> listaPrestadoresSedes) {
        this.listaPrestadoresSedes = listaPrestadoresSedes;
    }


    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        setObjetoPrestadorSede(ips);
        this.paramConsulta2.setFiltros(new HashMap<>());
        PrimeFaces.current().ajax().update("frmGestionar:ips");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
    }

    public String alertaFecha(Date fecha) {
        String iconClass = "";
        Date date = new Date(
                Instant.now().minus(3, ChronoUnit.DAYS)
                        .toEpochMilli()
        );
        if (date.after(fecha)) {
            iconClass = "pi pi-exclamation-circle";
        }
        return iconClass;
    }

    public String obtenerValorBoolean(Boolean valor) {
        if (valor != null && valor) {
            return "Si";
        } else {
            return "No";
        }
    }

    public void ventanaAdjunto() {
        this.maeTipoDocumentoArchivo = 0;
        PrimeFaces.current().resetInputs("frmAdjuntoGestionar");
        PrimeFaces.current().ajax().update("frmAdjuntoGestionar");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').show()");

    }

    public void borrarSoporteCrear(AuSolicitudAdjunto adjunto) {
        getObjetoCotizacion().getAuSolicitudAdjuntosList().remove(adjunto);
        PrimeFaces.current().ajax().update("frmGestionar:pSoporteGestionar");
    }

    public void borrarSoporte(AuSolicitudAdjunto adjunto) {
        getObjetoCotizacion().getAuSolicitudAdjuntosList().remove(adjunto);
        if (adjunto.getId() != null && adjunto.getId() != 0) {
            getAuCotizacionServicio().borrarAdjunto(adjunto.getId(), this);
            generarMensajes();
        }
        PrimeFaces.current().ajax().update("frmGestionarAdjuntos:pSoportesGestionarAdjuntos");
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (getMaeTipoDocumentoArchivo() != 0) {
                AuSolicitudAdjunto adjuntoDocumento = new AuSolicitudAdjunto();
                UploadedFile archivo = event.getFile();
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
                int indiceExtension = nombreAdjunto.lastIndexOf(".");
                String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
                adjuntoDocumento.setNombre(nombreAdjunto);
                adjuntoDocumento.setArchivo(nombreAdjunto);
                adjuntoDocumento.setExtension(extension);
                Maestro tipoArchivo = getHashTipoDocumentoArchivo().get(getMaeTipoDocumentoArchivo());
                adjuntoDocumento.setMaeTipoArchivoId(tipoArchivo.getId());
                adjuntoDocumento.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                adjuntoDocumento.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                getObjetoCotizacion().getAuSolicitudAdjuntosList().add(adjuntoDocumento);
                PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').hide()");
                PrimeFaces.current().ajax().update("frmGestionar:pSoporteGestionar");
                PrimeFaces.current().ajax().update("frmGestionarAdjuntos:pSoportesGestionarAdjuntos");
            } else {
                this.addError("Tipo Archivo: Este campo es obligatorio.");
            }
        } catch (IOException ex) {
            Logger.getLogger(AuCotizacionBean.class.getName()).log(Level.SEVERE, null, ex);
            PrimeFaces.current().ajax().update("frmGestionar:pSoporteGestionar");
        }
    }

    public void descargarAdjunto(AuSolicitudAdjunto adjunto) {
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
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
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

    public void descargarAdjuntoAnexo3(AuSolicitudAdjunto adjunto) {
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
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
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

    public void gestionarAdjuntos(int idItem) {
        getObjeto().setId(idItem);
        setObjetoCotizacion(new AuCotizacion());
        this.setAccion(Url.ACCION_ADICIONAL_2);
        this.setDoAccion(Url.ACCION_VER);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void guardarAdjuntos() {
        this.setAccion(Url.ACCION_ADICIONAL_2);
        this.setDoAccion(Url.ACCION_GUARDAR);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verRechazo(int id) {
        getObjeto().setId(id);
        setObservacion("");
        this.setAccion(ACCION_ADICIONAL_3);
        this.setDoAccion(ACCION_VER);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void rechazar() {
        this.setAccion(ACCION_ADICIONAL_3);
        this.setDoAccion(ACCION_GUARDAR);
        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verBitacoras(AuAnexo3Item item) {
        setObjeto(item);
//        setListaBitacoras(new ArrayList());
        this.setAccion(ACCION_ADICIONAL_4);
//        getAuCotizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verPosfechados(AuAnexo3Item item) {
        setObjeto(item);
        setListaPosfechados(new ArrayList());
        getAuCotizacionServicio().consultarPosfechados(this);
        if (!getListaPosfechados().isEmpty()) {
            PrimeFaces.current().executeScript("PF('dialogoVerPosfechados').show()");
            PrimeFaces.current().ajax().update("frmVerPosfechados");
        }
        generarMensajes();
    }

    public boolean validarEstadoCotizacion(int estado) {
        return (estado == AuAnexo3Item.ESTADO_CON_COTIZACION) || (estado == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO);
    }

    public boolean validarEstadoSinCotizacion(int estado) {
        return estado == AuAnexo3Item.ESTADO_SIN_COTIZACION;
    }

    public boolean validarTipoItemAgrupador(int tipo) {
        return tipo == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO;
    }

    public boolean validarPosfechadoPrincipal(boolean posfechado,boolean posfechadoPrincipal) {
        return !(posfechado && !posfechadoPrincipal) ;
    }

    public void mostrarAfiliadoGeneral() {
        getAuCotizacionServicio().completarAfiliado(this);
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
        generarMensajes();
    }
    
    public boolean habilitarBotonAnticipos(){
        boolean validate = true;
        if(!getObjeto().isPosfechado() && this.isAccionAdicional5()){
            validate = false;
        }
        return validate;
    }
    
    public void consultarAnticipos() {
        cargaLazyAnticipos();
        getAuCotizacionServicio().consultarMaestroParaListarAnticipos(this);
        PrimeFaces.current().ajax().update("frmAnticiposLista");
        PrimeFaces.current().executeScript("PF('dialogoAnticiposLista').show()");
        //procesoFinal();
    }
    
    public void cargaLazyAnticipos() {
        //Afiliado
        lazyAnticipos = new LazyDataModel<AntAnticipo>() {
            private List<AntAnticipo> anticipos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<AntAnticipo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAnticipo();
                anticipos = getListaAnticipos();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return anticipos;
            }

            @Override
            public String getRowKey(AntAnticipo afiliado) {
                return afiliado.getId().toString();
            }

            @Override
            public AntAnticipo getRowData(String afiliadoId) {
                Integer id = Integer.valueOf(afiliadoId);
                for (AntAnticipo anticipo : anticipos) {
                    if (id.equals(anticipo.getId())) {
                        return anticipo;
                    }
                }
                return null;
            }
        };
    }
    
    public void selectAnticipo(AntAnticipo anticipo) {
        //AntAnticipo anticipo = (AntAnticipo) event.getObject();
        int isActivo = 0;
        int isActivoDisponible = 0;
        if(anticipo.getAsegAfiliadosId() != null){
            isActivo = getAuCotizacionServicio().validarAfiliado(anticipo.getAsegAfiliadosId().getId(), this);
        }
        isActivoDisponible = getAuCotizacionServicio().consultarAnticipoValorDisponible(anticipo.getId(), this);
        if (isActivo == 0 && isActivoDisponible == 0) {
            getObjetoCotizacion().setAntAnticiposId(anticipo);
            setObjetoPrestadorSede(anticipo.getCntPrestadorSedesId());
            //getObjetoCotizacion().setCntPrestadorSede(anticipo.getCntPrestadorSedesId());
            AntAnticipoItem item = getAuCotizacionServicio().consultarAticipoItem(anticipo.getId(), getObjeto().getMaTecnologiaId(), this);
            if(item != null){
               getObjetoCotizacion().setAntAnticipoItemsId(item);
               getObjetoCotizacion().setValorTecnologia(item.getValorTecnologiaCotizada());
            }
            getObjetoCotizacion().setPagoAnticipado(Boolean.TRUE);
            getParamConsulta(0).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmGestionar:ips");
            PrimeFaces.current().ajax().update("frmGestionar:numeroAnticipo");
            PrimeFaces.current().ajax().update("frmGestionar:pagoAnticipado");
            PrimeFaces.current().ajax().update("frmGestionar:valorTotal");
            //PrimeFaces.current().ajax().update("frmGestionar:pGestionar");
            //PrimeFaces.current().ajax().update("frmEditar:panelAfiliadoEditar");
            PrimeFaces.current().executeScript("PF('dialogoAnticiposLista').hide()");
        }
        generarMensajes();
    }
}
