/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.web.aseguramiento.seleccion.bean.SelAfiliadoBean;
import com.saviasaludeps.savia.web.aseguramiento.servicio.PortabilidadServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.Utilidades;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Direccion;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class PortabilidadBean extends Url {

    private PortabilidadServicioIface portabilidadServicio;
    private AsegPortabilidad objeto;
    private List<AsegPortabilidad> registros;
    private LazyDataModel<AsegPortabilidad> lazyPortabilidad;
    private Direccion direccion;

    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaTiposGenero;
    private HashMap<Integer, Maestro> hashTiposGenero;

    private List<Maestro> listaEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<String, Maestro> hashValorEstadosAfiliacion;

    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Ubicacion> listaUbicaciones;

    private List<Maestro> listaMotivoPortabilidad;
    private HashMap<Integer, Maestro> hashMotivoPortabilidad;

    private List<Maestro> listaTipoPortabilidad;
    private List<Maestro> listaEstadoPortabilidad;
    private List<Maestro> listaEstadoPortabilidadInterno;
    private List<Maestro> listaOrigenPortabilidad;
    private HashMap<Integer, Maestro> hashEstadoPortabilidad;
    private HashMap<String, Maestro> hashValorEstadoPortabilidad;
    private HashMap<Integer, Maestro> hashOrigenPortabilidad;
    private HashMap<Integer, Maestro> hashTipoPortabilidad;
    private HashMap<String, Maestro> hashValorTipoPortabilidad;
    //2021-11-23 jyperez nueva variables certificado
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    private HashMap<Integer, Maestro> hashSubGrupoSisben;

    private transient InputStream adjuntoStream;

    private List<CntPrestadorSede> listaCntPrestadorSedes;

    //Objetos para reporte portabilidad
    private ReportePortabilidad reporte;
    private List<ReportePortabilidad> listaReportesPortabilidad;

    //2021-11-17 jyperez se adiciona el buscador de afiliados
    private SelAfiliadoBean selAfiliadoBean;

    private HashMap<String, Maestro> hashValorModeloLiquidacion;

//    @Autowired
//    private AseguramientoSingle aseguramientoSingle;

    public PortabilidadBean() {
        this.objeto = new AsegPortabilidad();
        objeto.setAsegAfiliado(new AsegAfiliado());
        this.reporte = new ReportePortabilidad();
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_GESTION_PORTABILIDAD);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyPortabilidad = new LazyDataModel<AsegPortabilidad>() {
                private List<AsegPortabilidad> portabilidades;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AsegPortabilidad> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    portabilidades = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return portabilidades;
                }

                @Override
                public String getRowKey(AsegPortabilidad portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public AsegPortabilidad getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (AsegPortabilidad portabilidad : portabilidades) {
                        if (id.equals(portabilidad.getId())) {
                            return portabilidad;
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
        getPortabilidadServicio().cargaInial(this);
        listar();
    }

    public PortabilidadServicioIface getPortabilidadServicio() {
        return portabilidadServicio;
    }

    public void setPortabilidadServicio(PortabilidadServicioIface portabilidadServicio) {
        this.portabilidadServicio = portabilidadServicio;
    }

    public AsegPortabilidad getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegPortabilidad objeto) {
        this.objeto = objeto;
    }

    public List<AsegPortabilidad> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegPortabilidad> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegPortabilidad> getLazyPortabilidad() {
        return lazyPortabilidad;
    }

    public void setLazyPortabilidad(LazyDataModel<AsegPortabilidad> lazyPortabilidad) {
        this.lazyPortabilidad = lazyPortabilidad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashEstadoPortabilidad() {
        return hashEstadoPortabilidad;
    }

    public void setHashEstadoPortabilidad(HashMap<Integer, Maestro> hashEstadoPortabilidad) {
        this.hashEstadoPortabilidad = hashEstadoPortabilidad;
    }

    public HashMap<Integer, Maestro> getHashOrigenPortabilidad() {
        return hashOrigenPortabilidad;
    }

    public void setHashOrigenPortabilidad(HashMap<Integer, Maestro> hashOrigenPortabilidad) {
        this.hashOrigenPortabilidad = hashOrigenPortabilidad;
    }

    public HashMap<Integer, Maestro> getHashTipoPortabilidad() {
        return hashTipoPortabilidad;
    }

    public void setHashTipoPortabilidad(HashMap<Integer, Maestro> hashTipoPortabilidad) {
        this.hashTipoPortabilidad = hashTipoPortabilidad;
    }

    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public List<CntPrestadorSede> getListaCntPrestadorSedes() {
        return listaCntPrestadorSedes;
    }

    public void setListaCntPrestadorSedes(List<CntPrestadorSede> listaCntPrestadorSedes) {
        this.listaCntPrestadorSedes = listaCntPrestadorSedes;
    }

    public List<Maestro> getListaMotivoPortabilidad() {
        return listaMotivoPortabilidad;
    }

    public void setListaMotivoPortabilidad(List<Maestro> listaMotivoPortabilidad) {
        this.listaMotivoPortabilidad = listaMotivoPortabilidad;
    }

    public HashMap<Integer, Maestro> getHashMotivoPortabilidad() {
        return hashMotivoPortabilidad;
    }

    public void setHashMotivoPortabilidad(HashMap<Integer, Maestro> hashMotivoPortabilidad) {
        this.hashMotivoPortabilidad = hashMotivoPortabilidad;
    }

    public String getTipoGenero(String id) {
        try {
            return hashTiposGenero.get(Integer.parseInt(id)).getNombre();
        } catch (NumberFormatException e) {
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTipoPortabilidad(Integer id) {
        String mensaje = "";
        try {
            Maestro mae = hashTipoPortabilidad.get(id);
            if (mae != null) {
                mensaje = mae.getNombre();
            }
        } catch (Exception e) {
            mensaje = "";
        }
        return mensaje;
    }

    public String getEstadoPortabilidad(Integer id) {
        String mensaje = "";
        try {
            Maestro mae = hashEstadoPortabilidad.get(id);
            if (mae != null) {
                mensaje = mae.getNombre();
            }
        } catch (Exception e) {
            mensaje = "";
        }
        return mensaje;
    }

    public String getEstadoAfiliacion(int id) {
        try {
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getPortabilidadServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getPortabilidadServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer1");
        PrimeFaces.current().ajax().update("frmVer:panelVer2");
        PrimeFaces.current().ajax().update("frmVer:panelVer3");
        PrimeFaces.current().ajax().update("frmVer:panelVer4");
        PrimeFaces.current().ajax().update("frmVer:panelVer5");
        PrimeFaces.current().ajax().update("frmVer:panelVer6");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getPortabilidadServicio().Accion(this);
//        PrimeFaces.current().resetInputs("frmAfiliado:panelAfiliado");
//        PrimeFaces.current().resetInputs("frmAfiliado:panelAfiliado");
//        PrimeFaces.current().resetInputs("frmCrear:panelCrear1");
//        PrimeFaces.current().resetInputs("frmCrear:panelCrear2");
//        PrimeFaces.current().resetInputs("frmCrear:panelCrear3");
        PrimeFaces.current().ajax().update("frmAfiliado:frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        Maestro maestro = null;

        if (objeto.getAsegAfiliado().getId() == null) {
            addError("Debe seleccionar un Afiliado previamente");
        }
        if (objeto.getPeriodoInicial() != null
                && objeto.getPeriodoFinal() != null
                && objeto.getPeriodoInicial().after(objeto.getPeriodoFinal())) {
            addError("El periodo inicial debe ser menor al periodo final");
        }
        if ((objeto.getTelefonoContacto() == null || objeto.getTelefonoContacto().trim().isEmpty())
                && (objeto.getTelefonoContacto2() == null || objeto.getTelefonoContacto2().trim().isEmpty())) {
            addError("Debe diligenciar el teléfono o celular");
        }

        //Municiopio debe ser diferente
        if (objeto.getAsegAfiliado() != null
                && objeto.getAsegAfiliado().getAfiliacionUbicacion() != null
                && objeto.getAsegAfiliado().getAfiliacionUbicacion().getId() > 0
                && objeto.getUbicacion() != null) {
            if (Objects.equals(objeto.getUbicacion().getId(), objeto.getAsegAfiliado().getAfiliacionUbicacion().getId())) {
                addError("El municipio de portabilidad debe ser diferente al municipio de Afiliación");
            }
        }

        calcularTipoPortabilidad();

        //periodo debe ser menor o igual a  meses maximos portabilidad.
        if (objeto.getPeriodoFinal() != null
                && objeto.getPeriodoInicial() != null) {
            Calendar fechaInicioCalendar = new GregorianCalendar();
            fechaInicioCalendar.setTime(objeto.getPeriodoInicial());
            Calendar fechaFinCalendar = new GregorianCalendar();
            fechaFinCalendar.setTime(objeto.getPeriodoFinal());

            int diferenciaAnios = fechaFinCalendar.get(Calendar.YEAR) - fechaInicioCalendar.get(Calendar.YEAR);
            int diferenciaMeses = diferenciaAnios * 12 + fechaFinCalendar.get(Calendar.MONTH) - fechaInicioCalendar.get(Calendar.MONTH);

            if (AfiliadoParametro.PERIODO_MAXIMO_PORTABILIDAD_CREACION < diferenciaMeses) {
                addError("El período de de portabiliad no debe superar los " + AfiliadoParametro.PERIODO_MAXIMO_PORTABILIDAD_CREACION + " meses.");
            } else {
                if (AfiliadoParametro.PERIODO_MAXIMO_PORTABILIDAD_CREACION == diferenciaMeses) {
                    Calendar fechaInicialCalendar = Calendar.getInstance();
                    fechaInicialCalendar.setTime(objeto.getPeriodoInicial());
                    Calendar fechaFinalCalendar = Calendar.getInstance();
                    fechaFinalCalendar.setTime(objeto.getPeriodoFinal());
                    int diaFechaInicial = fechaInicialCalendar.get(Calendar.DAY_OF_MONTH);
                    int diaFechaFinal = fechaFinalCalendar.get(Calendar.DAY_OF_MONTH);
                    if (diaFechaFinal > diaFechaInicial) {
                        addError("El período de de portabiliad no debe superar los " + AfiliadoParametro.PERIODO_MAXIMO_PORTABILIDAD_CREACION + " meses.");
                    }
                }
            }
        }

        //2021-11-23 jyperez actualizamos los valores en los maestros
        //origen
        maestro = hashOrigenPortabilidad.get(this.objeto.getMaeOrigenSolicitudId());
        if (maestro != null) {
            this.objeto.setMaeOrigenSolicitudCodigo(maestro.getValor());
            this.objeto.setMaeOrigenSolicitudValor(maestro.getNombre());
            //variable antigua
            try {
                this.objeto.setOrigenSolicitud(Integer.valueOf(maestro.getValor()));
            } catch (Exception e) {
                this.objeto.setOrigenSolicitud(0);
            }
        } else {
            addError("No es posible obtener los valores del maestro Origen Portabilidad.");
        }
        //estado
        maestro = hashEstadoPortabilidad.get(this.objeto.getMaeEstadoPortabilidadId());
        if (maestro != null) {
            this.objeto.setMaeEstadoPortabilidadCodigo(maestro.getValor());
            this.objeto.setMaeEstadoPortabilidadValor(maestro.getNombre());
            //variable antigua
            try {
                this.objeto.setEstadoPortabilidad(Integer.valueOf(maestro.getValor()));
            } catch (Exception e) {
                this.objeto.setEstadoPortabilidad(0);
            }
        } else {
            addError("No es posible obtener los valores del maestro Estado Portabilidad.");
        }
        //motivo portabilidad
        maestro = hashMotivoPortabilidad.get(this.objeto.getMaeMotivoPortabilidadId());
        if (maestro != null) {
            this.objeto.setMaeMotivoPortabilidadCodigo(maestro.getValor());
            this.objeto.setMaeMotivoPortabilidadValor(maestro.getNombre());
        } else {
            addError("No es posible obtener los valores del maestro Motivo Portabilidad.");
        }

        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getPortabilidadServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
            }
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getPortabilidadServicio().Accion(this);
        cargarSedes();
        if (getObjeto().getCntPrestadorSede() != null) {
            for (CntPrestadorSede sedeIps : listaCntPrestadorSedes) {
                if (getObjeto().getCntPrestadorSede().getId().intValue() == sedeIps.getId().intValue()) {
                    getObjeto().setCntPrestadorSede(sedeIps);
                    break;
                }
            }
        }
        //2021-11-18 jyperez cambiamos variable de estado inicial portabilidad
        getObjeto().setEstadoInicialPortab(getObjeto().getMaeEstadoPortabilidadCodigo());
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar1");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar2");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar3");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar4");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar5");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar6");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar7");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar8");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar1");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar2");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar3");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar4");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar5");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar6");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar7");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar8");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {

        //cargamos el maestro de estado
        Maestro maestro = hashEstadoPortabilidad.get(this.objeto.getMaeEstadoPortabilidadId());
        if (maestro != null) {
            this.objeto.setMaeEstadoPortabilidadCodigo(maestro.getValor());
            this.objeto.setMaeEstadoPortabilidadValor(maestro.getNombre());
            //variable antigua
            try {
                this.objeto.setEstadoPortabilidad(Integer.valueOf(maestro.getValor()));
            } catch (Exception e) {
                this.objeto.setEstadoPortabilidad(0);
            }
        } else {
            addError("No es posible obtener los valores del maestro Estado Portabilidad.");
        }
        //2024-09-19 jyperez validaciones necesarias para los nuevos campos
        if (objeto.getPeriodoInicial() != null
                && objeto.getPeriodoFinal() != null
                && objeto.getPeriodoInicial().after(objeto.getPeriodoFinal())) {
            addError("El periodo inicial debe ser menor al periodo final");
        }
        //2024-09-19 jyperez cargamos el maestro motivo
        //origen
        maestro = hashOrigenPortabilidad.get(this.objeto.getMaeOrigenSolicitudId());
        if (maestro != null) {
            this.objeto.setMaeOrigenSolicitudCodigo(maestro.getValor());
            this.objeto.setMaeOrigenSolicitudValor(maestro.getNombre());
            //variable antigua
            try {
                this.objeto.setOrigenSolicitud(Integer.valueOf(maestro.getValor()));
            } catch (Exception e) {
                this.objeto.setOrigenSolicitud(0);
            }
        } else {
            addError("No es posible obtener los valores del maestro Origen Portabilidad.");
        }
        //motivo portabilidad
        maestro = hashMotivoPortabilidad.get(this.objeto.getMaeMotivoPortabilidadId());
        if (maestro != null) {
            this.objeto.setMaeMotivoPortabilidadCodigo(maestro.getValor());
            this.objeto.setMaeMotivoPortabilidadValor(maestro.getNombre());
        } else {
            addError("No es posible obtener los valores del maestro Motivo Portabilidad.");
        }
        
        if (getObjeto().getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_CANCELADA_USUARIO)
                && objeto.getFechaSolicitudCancelacion().after(new Date())) {
            addError("La Fecha de Solicitud de Cancelación no puede ser superior a la fecha actual");
        }

        if (AsegPortabilidad.ESTADO_PRORROGA.equals(getObjeto().getMaeEstadoPortabilidadCodigo())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaActual = sdf.format(objeto.getFechaSolicitudProrroga());
            validarTiempoProrroga();
            if (!super.isError()) {
                int numeroProrroga = objeto.getNumeroProrroga() == null
                        || objeto.getNumeroProrroga() == 0 ? 1
                        : objeto.getNumeroProrroga() + 1;

                if (objeto.getFechaSolicitudProrrogaAnterior() != null) {
                    String fechaAnterior = sdf.format(objeto.getFechaSolicitudProrrogaAnterior());
                    if (fechaAnterior.equals(fechaActual) && Objects.equals(objeto.getMesesProrroga(), objeto.getMesesProrrogaAnterior())) {
                        numeroProrroga = objeto.getNumeroProrroga();
                    }
                }
                objeto.setNumeroProrroga(numeroProrroga);
                objeto.setFechaSolicitudProrroga(new Date());

                if (objeto.getObservacionProrroga() != null) {
                    StringBuilder observacionBuilder = new StringBuilder();
                    observacionBuilder.append("Observación prórroga ");
                    observacionBuilder.append(fechaActual);
                    observacionBuilder.append("  : { ");
                    observacionBuilder.append(objeto.getObservacionProrroga());
                    observacionBuilder.append(" }, ");
                    observacionBuilder.append(objeto.getObservacionAseguramiento());
                    objeto.setObservacionProrroga(observacionBuilder.toString());
                }
            }
        }

        if (!super.isError()) {
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_MODIFICAR);
            getPortabilidadServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            }
        }

        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getPortabilidadServicio().Accion(this);
        procesoFinal();
    }

    /*public void generarCertificado(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        getPortabilidadServicio().Accion(this);
        procesoFinal();
    }*/
    public void consultarAfiliado() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmAfiliadoBusqueda");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());// para que??
            addError(ex.getMessage());
        }
    }

    public void crearDireccion() {
        direccion = new Direccion();
        PrimeFaces.current().ajax().update("frmDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').show()");
    }

    public void retornarDireccion() {
        if (this.getDireccion().getVia().equalsIgnoreCase("SD")) {
            if (getDireccion().getDescripcion().trim().isEmpty()) {
                addError("Descripción: Este campo es obligatorio.");
                generarMensajes();
                return;
            }
        }
        this.objeto.setDireccion(direccion.getDireccion());
        PrimeFaces.current().ajax().update("frmCrear:txtDireccion");
        PrimeFaces.current().ajax().update("frmEditar:txtDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
    }

    /**
     * Método para calcular el TIPO DE PORTABILIDAD a partir de rango de fechas
     */
    public void calcularTipoPortabilidad() {
        Maestro mae;
        try {
            if (objeto.getPeriodoInicial() != null && objeto.getPeriodoFinal() != null) {
                float meses = Utilidades.getMesesPeriodo(objeto.getPeriodoInicial(), objeto.getPeriodoFinal());
                if (meses <= 1) {
                    getObjeto().setTipoPortabilidad(Integer.parseInt(AsegPortabilidad.TIPO_OCASIONAL));
                    mae = hashValorTipoPortabilidad.get(AsegPortabilidad.TIPO_OCASIONAL);
                    if (mae != null) {
                        getObjeto().setMaeTipoPortabilidadId(mae.getId());
                        getObjeto().setMaeTipoPortabilidadCodigo(mae.getValor());
                        getObjeto().setMaeTipoPortabilidadValor(mae.getNombre());
                    }
                } else if (meses > 1 && meses <= 12) {
                    getObjeto().setTipoPortabilidad(Integer.parseInt(AsegPortabilidad.TIPO_TEMPORAL));
                    mae = hashValorTipoPortabilidad.get(AsegPortabilidad.TIPO_TEMPORAL);
                    if (mae != null) {
                        getObjeto().setMaeTipoPortabilidadId(mae.getId());
                        getObjeto().setMaeTipoPortabilidadCodigo(mae.getValor());
                        getObjeto().setMaeTipoPortabilidadValor(mae.getNombre());
                    }
                } else if (meses > 12) {
                    getObjeto().setTipoPortabilidad(Integer.parseInt(AsegPortabilidad.TIPO_PERMANENTE));
                    mae = hashValorTipoPortabilidad.get(AsegPortabilidad.TIPO_PERMANENTE);
                    if (mae != null) {
                        getObjeto().setMaeTipoPortabilidadId(mae.getId());
                        getObjeto().setMaeTipoPortabilidadCodigo(mae.getValor());
                        getObjeto().setMaeTipoPortabilidadValor(mae.getNombre());
                    }
                }
            }
        } catch (NumberFormatException e) {
            this.addError("Error en calcularTipoPortabilidad " + e.getMessage());
        } catch (Exception e) {
            this.addError("Error en calcularTipoPortabilidad " + e.getMessage());
        }
    }

    public boolean habilitarBotonParaProrroga(AsegPortabilidad objeto) {
        boolean habilitarBoton = false;
        try {
            if (AsegPortabilidad.ESTADO_FINALIZADA.equals(objeto.getMaeEstadoPortabilidadCodigo())
                    || AsegPortabilidad.ESTADO_PRORROGA.equals(objeto.getMaeEstadoPortabilidadCodigo())) {
                Date fechaActual = new Date();
                Date fechFinalizacion = objeto.getPeriodoFinal();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechFinalizacion);

                if (AsegPortabilidad.ESTADO_PRORROGA.equals(objeto.getMaeEstadoPortabilidadCodigo())) {
                    habilitarBoton = true;
                }

                if (AsegPortabilidad.ESTADO_FINALIZADA.equals(objeto.getMaeEstadoPortabilidadCodigo())) {
                    fechFinalizacion = calendar.getTime();
                    if (fechaActual.after(fechFinalizacion)) {
                        long diff = fechaActual.getTime() - fechFinalizacion.getTime();
                        long diasVencerProrroga = diff / 1000 / 60 / 60 / 24;
                        if (diasVencerProrroga >= 0 && AfiliadoParametro.LIMITE_HABILITACION_ESTADO_TERMINADO >= diasVencerProrroga) {
                            habilitarBoton = true;
                        }
                    } else {
                        habilitarBoton = true;
                    }
                }
            }
        } catch (Exception e) {
            this.addError("Error en habilitarBotonParaProrroga " + e.getMessage());
        }
        return habilitarBoton;
    }

    public void validarTiempoProrroga() {
        try {
            if (getObjeto().getMesesProrroga() > 0) {
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                Calendar fechaTentativaPeriodoFinalCalendar = new GregorianCalendar();
                fechaTentativaPeriodoFinalCalendar.setTime(objeto.getPeriodoFinal());
                fechaTentativaPeriodoFinalCalendar.add(Calendar.MONTH, (int) getObjeto().getMesesProrroga());

                Calendar fechaMaximaPortabilidadCalendar = new GregorianCalendar();
                fechaMaximaPortabilidadCalendar.setTime(objeto.getPeriodoInicial());
                fechaMaximaPortabilidadCalendar.add(Calendar.MONTH, AfiliadoParametro.PERIODO_MAXIMO_PORTABILIDAD_PRORROGA);
                fechaMaximaPortabilidadCalendar.add(Calendar.HOUR_OF_DAY, 23);
                fechaMaximaPortabilidadCalendar.add(Calendar.MINUTE, 59);
                String fechaMaxima = format1.format(fechaMaximaPortabilidadCalendar.getTime());

                Calendar fechaPeriodoFinalCalendar = new GregorianCalendar();
                fechaPeriodoFinalCalendar.setTime(objeto.getPeriodoFinal());
                fechaPeriodoFinalCalendar.add(Calendar.HOUR_OF_DAY, 23);
                fechaPeriodoFinalCalendar.add(Calendar.MINUTE, 59);
                objeto.setPeriodoFinal(fechaPeriodoFinalCalendar.getTime());

                Calendar fechaPeriodoInicialCalendar = new GregorianCalendar();
                fechaPeriodoInicialCalendar.setTime(objeto.getPeriodoInicial());
                objeto.setPeriodoInicial(fechaPeriodoInicialCalendar.getTime());

                if (fechaTentativaPeriodoFinalCalendar.after(fechaMaximaPortabilidadCalendar)) {
                    addError("Los meses de prórrogan agregados a fecha actual superan la fecha máxima de " + fechaMaxima + ", hasta la cual es posible prórrogar.");
                } else {
                    if (fechaTentativaPeriodoFinalCalendar.after(fechaPeriodoFinalCalendar)) {
                        this.getObjeto().setPeriodoFinal(fechaTentativaPeriodoFinalCalendar.getTime());
                        calcularTipoPortabilidad();
                    }
                }
            }
        } catch (Exception e) {
            this.addError("Error en validarTiempoProrroga " + e.getMessage());
        }

    }

    /**
     * Método que retorna el nombre del maestro ORIGEN DE PORTABILIDAD a partir
     * del ID
     *
     * @param id
     * @return
     */
    public String getOrigenPortabilidad(Integer id) {
        String mensaje = "";
        try {
            Maestro mae = hashOrigenPortabilidad.get(id);
            if (mae != null) {
                mensaje = mae.getNombre();
            }

        } catch (Exception e) {
            mensaje = "";
        }
        return mensaje;
    }

    /**
     * Método quye carga lista de sedes a partir de una ubicación (Código
     * ubicación)
     */
    public void cargarSedes() {
        this.setListaCntPrestadorSedes(new ArrayList());
        try {
            if (hashUbicaciones.get(objeto.getUbicacion().getId()) != null) {
                objeto.setUbicacion(hashUbicaciones.get(objeto.getUbicacion().getId()));
            } else {
                for (Ubicacion ubi : listaUbicaciones) {
                    if (objeto.getUbicacion().getId().intValue() == ubi.getId().intValue()) {
                        objeto.setUbicacion(ubi);
                        break;
                    }
                }
            }

            if (objeto.getUbicacion() != null) {
                getPortabilidadServicio().consultarSedesMunicipio(this);
            } else {
                addError("No se estableció la ubicación portabilidad");
            }
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        generarMensajes();
    }

    public void cargarEstadoCancelar() {
        getObjeto().setFechaSolicitudCancelacion(null);
        getObjeto().setFechaCancelacion(null);
        getObjeto().setObservacionCancelacion(null);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().ajax().update("frmEditar");
    }

    /**
     * Valida si el estado de portabilidad es cancelado por usuario
     *
     * @return
     */
    public boolean isCancelacion() {
        //cargamos el maestro de estado
        if (getObjeto().getMaeEstadoPortabilidadId() != null) {
            Maestro estado = hashEstadoPortabilidad.get(this.objeto.getMaeEstadoPortabilidadId());
            if (estado != null) {
                this.objeto.setMaeEstadoPortabilidadCodigo(estado.getValor());
                this.objeto.setMaeEstadoPortabilidadValor(estado.getNombre());
                //variable antigua
                this.objeto.setEstadoPortabilidad(this.objeto.getMaeEstadoPortabilidadId());
            }
            return (getObjeto().getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_CANCELADA_USUARIO));
        } else {
            return false;
        }
    }

    public boolean isCancelacionORechazo() {
        return ((AsegPortabilidad.ESTADO_CANCELADA_USUARIO.equals(getObjeto().getMaeEstadoPortabilidadCodigo()))
                || (AsegPortabilidad.ESTADO_RECHAZADA.equals(getObjeto().getMaeEstadoPortabilidadCodigo())))
                || (AsegPortabilidad.TIPO_OCASIONAL.equals(getObjeto().getMaeTipoPortabilidadCodigo()));
    }

    public boolean isProrroga() {
        return (getObjeto().getMaeEstadoPortabilidadCodigo() != null && getObjeto().getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_PRORROGA));
    }

    public boolean isCancelacionVer() {
        return (getObjeto().getMaeEstadoPortabilidadCodigo() != null && getObjeto().getMaeEstadoPortabilidadCodigo().equals(AsegPortabilidad.ESTADO_CANCELADA_USUARIO));
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmPortabilidad");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog("Generar Formulario Portabilidad", getReporte().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_EDITAR:
                        crearLog(getObjeto().toString());
                        break;
                    case Url.ACCION_MODIFICAR:
                        crearLog("Gestionar", getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmPortabilidad");
                        break;
                }
                break;
        }
        generarMensajes();
    }

    public ReportePortabilidad getReporte() {
        return reporte;
    }

    public void setReporte(ReportePortabilidad reporte) {
        this.reporte = reporte;
    }

    public List<ReportePortabilidad> getListaReportesPortabilidad() {
        return listaReportesPortabilidad;
    }

    public void setListaReportesPortabilidad(List<ReportePortabilidad> listaReportesPortabilidad) {
        this.listaReportesPortabilidad = listaReportesPortabilidad;
    }

    public StreamedContent generarReporte(AsegPortabilidad portabilidad) {
        StreamedContent streamedContent2 = null;
        try {
            setAdjuntoStream(null);
            setReporte(new ReportePortabilidad());
            getReporte().setId(portabilidad.getId().toString());
            getReporte().setStrUsuarioImprime(getConexion().getUsuario().getNombre());
            //2021-11-23 jyperez guardamos el objeto de portabilidad para obtener los datos a almacenar en aseg_certificados
            this.objeto = portabilidad;
            super.setAccion(ACCION_ADICIONAL_1);
            getPortabilidadServicio().Accion(this);
            //getReporte().setStrUsuarioImprime(getConexion().getUsuario().getNombre());
            if (getAdjuntoStream() != null) {
                /*
                listaReportesPortabilidad = new ArrayList();
                listaReportesPortabilidad.add(getReporte());
                //Estrucutra de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/certificado_portabilidad.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportesPortabilidad);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                 */
                InputStream stream = getAdjuntoStream();
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(getReporte().getNombreArchivo()).build();
            } else {
                this.addError("Error no hay datos para generar el formulario");
                //generarMensajes();
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            System.out.println("Error generarReporte: " + ex.toString() + ex.getMessage());
            //generarMensajes();
        }
        procesoFinal();
        return streamedContent2;
    }

    /**
     * Método para la auto-completación de la ubicación a partir de un texto
     * ingresado
     *
     * @param query
     * @return
     */
    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getListaUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setUbicacion(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    /**
     * Metodo que retona el nombre completo mde la ubicacion a partir de un ID
     *
     * @param id
     * @return
     */
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion municipio = null;
        if (getHashUbicaciones() != null) {
            municipio = getHashUbicaciones().get(id);
        }
        if (municipio != null && municipio.getUbicacionPadre() != null) {
            Ubicacion departamento = municipio.getUbicacionPadre();
//            if (departamento.getUbicacionPadre() != null) {
//                Ubicacion pais = departamento.getUbicacionPadre();
//                ubicacionStr = pais.getNombre();
//            }
            ubicacionStr = departamento.getNombre();
//            ubicacionStr = departamento.getNombre() + " - " + ubicacionStr;
            ubicacionStr = municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("portabilidadBean");
    }

    /**
     * @return the listaTipoPortabilidad
     */
    public List<Maestro> getListaTipoPortabilidad() {
        return listaTipoPortabilidad;
    }

    /**
     * @param listaTipoPortabilidad the listaTipoPortabilidad to set
     */
    public void setListaTipoPortabilidad(List<Maestro> listaTipoPortabilidad) {
        this.listaTipoPortabilidad = listaTipoPortabilidad;
    }

    /**
     * @return the listaEstadoPortabilidad
     */
    public List<Maestro> getListaEstadoPortabilidad() {
        return listaEstadoPortabilidad;
    }

    /**
     * @param listaEstadoPortabilidad the listaEstadoPortabilidad to set
     */
    public void setListaEstadoPortabilidad(List<Maestro> listaEstadoPortabilidad) {
        this.listaEstadoPortabilidad = listaEstadoPortabilidad;
    }

    /**
     * @return the listaOrigenPortabilidad
     */
    public List<Maestro> getListaOrigenPortabilidad() {
        return listaOrigenPortabilidad;
    }

    /**
     * @param listaOrigenPortabilidad the listaOrigenPortabilidad to set
     */
    public void setListaOrigenPortabilidad(List<Maestro> listaOrigenPortabilidad) {
        this.listaOrigenPortabilidad = listaOrigenPortabilidad;
    }

    public int calcularEdad(Date fecha) {
        int years = 0;
        if (fecha != null) {
            Calendar fechaNac = Calendar.getInstance();
            Calendar fechaActual = Calendar.getInstance();
            // cargamos la fecha a evaluar
            fechaNac.setTime(fecha);
            // Cálculo de las diferencias.
            years = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
            int months = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
            int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
            // Hay que comprobar si el día de su cumpleaños es posterior
            // a la fecha actual, para restar 1 a la diferencia de años,
            // pues aún no ha sido su cumpleaños.
            if (months < 0 // Aún no es el mes de su cumpleaños
                    || (months == 0 && days < 0)) { // o es el mes pero no ha llegado el día.
                years--;
            }
        }
        return years;
    }

    public String calcularEdadEnDiasMesesAnios(Date fecha) {
        SimpleDateFormat formato5 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public void cerrarDialogoAfiliado() {
        //se valida el estado activo del afiliado, mediante la validación de la acción del afiliado.
        boolean isActivo = getPortabilidadServicio().validarEstadoAfiliado(getSelAfiliadoBean().getObjeto().getMaeEstadoAfiliacion(), this);
        if (isActivo) {
            //2022-01-06 jyperez validamos si existen portabilidades asociadas al afiliado. De ser asi, tampoco se permitirá su selección
            boolean portPendientes = getPortabilidadServicio().validarPortabilidadesPendientesAfiliado(getSelAfiliadoBean().getObjeto(), this);
            if (portPendientes) {
                generarMensajes();
            } else {
                // se adiciona el afiliado al objetoAfiliado, que es el que cargará los datos que se muestran.
                getObjeto().setAsegAfiliado(getSelAfiliadoBean().getObjeto());
                //CAMBIAR!! DEBEMOS VALIDAR EL ESTADO DEL AFILIADO, SI LA ACCIÓN DEL MAESTRO ACCIONES DICE QUE NO ESTÁ ACTIVO
                // ENTONCES DEBEMOS LANZAR UN MENSAJE DE ALERTA INDICANDO QUE EL AFLIADO SE ENCUENTRA RETIRADO O INACTIVO Y NO LO SELECCIONE
                //actualizamos el objeto de selección
                getSelAfiliadoBean().setObjeto(new AsegAfiliado());
                //actualizamos los formularios y los paneles que deben actualizarse
                PrimeFaces.current().ajax().update("frmAfiliado:panelAfiliado");
                //cerramos la ventana
                PrimeFaces.current().executeScript("PF('dialogoAfiliadoBusqueda').hide()");
            }
        } else {
            generarMensajes();
        }
    }

    /**
     * @return the selAfiliadoBean
     */
    public SelAfiliadoBean getSelAfiliadoBean() {
        selAfiliadoBean = (SelAfiliadoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selAfiliadoBean");
        return selAfiliadoBean;
    }

    /**
     * @param selAfiliadoBean the selAfiliadoBean to set
     */
    public void setSelAfiliadoBean(SelAfiliadoBean selAfiliadoBean) {
        this.selAfiliadoBean = selAfiliadoBean;
    }

    /**
     * @return the hashValorTipoPortabilidad
     */
    public HashMap<String, Maestro> getHashValorTipoPortabilidad() {
        return hashValorTipoPortabilidad;
    }

    /**
     * @param hashValorTipoPortabilidad the hashValorTipoPortabilidad to set
     */
    public void setHashValorTipoPortabilidad(HashMap<String, Maestro> hashValorTipoPortabilidad) {
        this.hashValorTipoPortabilidad = hashValorTipoPortabilidad;
    }

    /**
     * @return the hashValorEstadosAfiliacion
     */
    public HashMap<String, Maestro> getHashValorEstadosAfiliacion() {
        return hashValorEstadosAfiliacion;
    }

    /**
     * @param hashValorEstadosAfiliacion the hashValorEstadosAfiliacion to set
     */
    public void setHashValorEstadosAfiliacion(HashMap<String, Maestro> hashValorEstadosAfiliacion) {
        this.hashValorEstadosAfiliacion = hashValorEstadosAfiliacion;
    }

    /**
     * @return the hashGrupoPoblacional
     */
    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    /**
     * @param hashGrupoPoblacional the hashGrupoPoblacional to set
     */
    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    /**
     * @return the hashSubGrupoSisben
     */
    public HashMap<Integer, Maestro> getHashSubGrupoSisben() {
        return hashSubGrupoSisben;
    }

    /**
     * @param hashSubGrupoSisben the hashSubGrupoSisben to set
     */
    public void setHashSubGrupoSisben(HashMap<Integer, Maestro> hashSubGrupoSisben) {
        this.hashSubGrupoSisben = hashSubGrupoSisben;
    }

    /**
     * @return the adjuntoStream
     */
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     */
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    /**
     * @return the hashValorEstadoPortabilidad
     */
    public HashMap<String, Maestro> getHashValorEstadoPortabilidad() {
        return hashValorEstadoPortabilidad;
    }

    /**
     * @param hashValorEstadoPortabilidad the hashValorEstadoPortabilidad to set
     */
    public void setHashValorEstadoPortabilidad(HashMap<String, Maestro> hashValorEstadoPortabilidad) {
        this.hashValorEstadoPortabilidad = hashValorEstadoPortabilidad;
    }

    /**
     * @return the listaEstadoPortabilidadInterno
     */
    public List<Maestro> getListaEstadoPortabilidadInterno() {
        return listaEstadoPortabilidadInterno;
    }

    /**
     * @param listaEstadoPortabilidadInterno the listaEstadoPortabilidadInterno
     * to set
     */
    public void setListaEstadoPortabilidadInterno(List<Maestro> listaEstadoPortabilidadInterno) {
        this.listaEstadoPortabilidadInterno = listaEstadoPortabilidadInterno;
    }

    /**
     * @return the hashValorModeloLiquidacion
     */
    public HashMap<String, Maestro> getHashValorModeloLiquidacion() {
        return hashValorModeloLiquidacion;
    }

    /**
     * @param hashValorModeloLiquidacion the hashValorModeloLiquidacion to set
     */
    public void setHashValorModeloLiquidacion(HashMap<String, Maestro> hashValorModeloLiquidacion) {
        this.hashValorModeloLiquidacion = hashValorModeloLiquidacion;
    }

//    /**
//     * @return the aseguramientoSingle
//     */
//    public AseguramientoSingle getAseguramientoSingle() {
//        return aseguramientoSingle;
//    }
//
//    /**
//     * @param aseguramientoSingle the aseguramientoSingle to set
//     */
//    public void setAseguramientoSingle(AseguramientoSingle aseguramientoSingle) {
//        this.aseguramientoSingle = aseguramientoSingle;
//    }

}
