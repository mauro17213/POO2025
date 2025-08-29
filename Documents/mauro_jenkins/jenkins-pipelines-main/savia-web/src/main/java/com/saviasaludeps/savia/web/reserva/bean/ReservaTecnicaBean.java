package com.saviasaludeps.savia.web.reserva.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.reserva.RtReserva;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivo;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivoProceso;
import com.saviasaludeps.savia.web.reserva.servicio.ReservaTecnicaServicioIface;
import com.saviasaludeps.savia.web.reserva.utilidades.RtConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class ReservaTecnicaBean extends Url {

    private ReservaTecnicaServicioIface reservaTecnicaServicio;

    private LazyDataModel<RtReserva> lazyReserva;
    private LazyDataModel<RtReservaArchivo> lazyReservaArchivo;
    private List<RtReserva> registros;
    private RtReserva objeto;
    private RtReservaArchivo objetoArchivo;
    private RtReservaArchivoProceso objetoArchivoProceso;

    private List<Maestro> listaEstadoReserva;
    private HashMap<Integer, Maestro> hashListaEstadoReserva;
    private List<RtReservaArchivo> archivoReservaList;
    private List<RtReservaArchivoProceso> objetoArchivoProcesoList;

    public ReservaTecnicaBean() {
        this.objeto = new RtReserva();
        Modulo mod = super.validarModulo(Modulo.ID_RESERVA_OPCINES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.addListaParamConsultas(new ParamConsulta());
            lazyReserva = new LazyDataModel<RtReserva>() {
                private List<RtReserva> reservas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<RtReserva> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    reservas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return reservas;
                }

                @Override
                public String getRowKey(RtReserva reserva) {
                    return reserva.getId().toString();
                }

                @Override
                public RtReserva getRowData(String reservaId) {
                    Integer id = Integer.valueOf(reservaId);
                    for (RtReserva item : reservas) {
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
    public void postConstruct() {
        getReservaTecnicaServicio().cargasInicial(this);
        listar();
    }

    public ReservaTecnicaServicioIface getReservaTecnicaServicio() {
        return reservaTecnicaServicio;
    }

    public void setReservaTecnicaServicio(ReservaTecnicaServicioIface reservaTecnicaServicio) {
        this.reservaTecnicaServicio = reservaTecnicaServicio;
    }

    public LazyDataModel<RtReserva> getLazyReserva() {
        return lazyReserva;
    }

    public void setLazyReserva(LazyDataModel<RtReserva> lazyReserva) {
        this.lazyReserva = lazyReserva;
    }

    public List<RtReserva> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RtReserva> registros) {
        this.registros = registros;
    }

    public RtReserva getObjeto() {
        return objeto;
    }

    public void setObjeto(RtReserva objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getListaEstadoReserva() {
        return listaEstadoReserva;
    }

    public void setListaEstadoReserva(List<Maestro> listaEstadoReserva) {
        this.listaEstadoReserva = listaEstadoReserva;
    }

    public HashMap<Integer, Maestro> getHashListaEstadoReserva() {
        return hashListaEstadoReserva;
    }

    public void setHashListaEstadoReserva(HashMap<Integer, Maestro> hashListaEstadoReserva) {
        this.hashListaEstadoReserva = hashListaEstadoReserva;
    }

    public List<RtReservaArchivo> getArchivoReservaList() {
        return archivoReservaList;
    }

    public void setArchivoReservaList(List<RtReservaArchivo> archivoReservaList) {
        this.archivoReservaList = archivoReservaList;
    }

    public RtReservaArchivo getObjetoArchivo() {
        return objetoArchivo;
    }

    public void setObjetoArchivo(RtReservaArchivo objetoArchivo) {
        this.objetoArchivo = objetoArchivo;
    }

    public LazyDataModel<RtReservaArchivo> getLazyReservaArchivo() {
        return lazyReservaArchivo;
    }

    public void setLazyReservaArchivo(LazyDataModel<RtReservaArchivo> lazyReservaArchivo) {
        this.lazyReservaArchivo = lazyReservaArchivo;
    }

    public RtReservaArchivoProceso getObjetoArchivoProceso() {
        return objetoArchivoProceso;
    }

    public void setObjetoArchivoProceso(RtReservaArchivoProceso objetoArchivoProceso) {
        this.objetoArchivoProceso = objetoArchivoProceso;
    }

    public List<RtReservaArchivoProceso> getObjetoArchivoProcesoList() {
        return objetoArchivoProcesoList;
    }

    public void setObjetoArchivoProcesoList(List<RtReservaArchivoProceso> objetoArchivoProcesoList) {
        this.objetoArchivoProcesoList = objetoArchivoProcesoList;
    }

    //metodos
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getReservaTecnicaServicio().Accion(this);
    }

    public void refrescarArchivoReserva() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_LISTAR);
        getReservaTecnicaServicio().Accion(this);
    }

    public void refrescarArchivosReserva() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_LISTAR);
        getReservaTecnicaServicio().Accion(this);
    }

    public String getEstadoReserva(Integer idEstado) {
        return RtConstantes.getNombreEstadoReserva(idEstado);
    }

    public String getEstadoArchivoReserva(Integer idEstado) {
        return RtConstantes.getNombreEstadoArchivoReserva(idEstado);
    }
    
    public String getEstadoArchivoReservaProceso(Integer idEstado){
        return RtConstantes.getNombreEstadoArchivoReservaProceso(idEstado);
    }

    public void ver(RtReserva reserva) {
        setObjeto(reserva);
        cargarLazyReservaArchivo();
        super.setAccion(Url.ACCION_VER);
        getReservaTecnicaServicio().Accion(this);
        procesoFinal();
    }

    private void cargarLazyReservaArchivo() {
        lazyReservaArchivo = new LazyDataModel<RtReservaArchivo>() {
            private List<RtReservaArchivo> listaArchivos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<RtReservaArchivo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarArchivoReserva();
                listaArchivos = getArchivoReservaList();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaArchivos;
            }

            @Override
            public String getRowKey(RtReservaArchivo archivo) {
                return archivo.getId().toString();
            }

            @Override
            public RtReservaArchivo getRowData(String archivoId) {
                Integer id = Integer.valueOf(archivoId);
                for (RtReservaArchivo ar : listaArchivos) {
                    if (id.equals(ar.getId())) {
                        return ar;
                    }
                }
                return null;
            }
        };
    }

    public void descargarArchivoReserva(RtReservaArchivo archivo) {
        this.setObjetoArchivo(archivo);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getReservaTecnicaServicio().Accion(this);
        String rutaCompleta = this.objetoArchivo.getRuta() + this.objetoArchivo.getArchivo();
        FileInputStream fis = null;
        OutputStream output = null;

        try {
            File file = new File(rutaCompleta);

            if (!file.exists()) {
                throw new FileNotFoundException("El archivo no existe: " + rutaCompleta);
            }

            // Verificar que el archivo sea .7z
            if (!rutaCompleta.toLowerCase().endsWith(".7z")) {
                throw new IllegalArgumentException("Tipo de archivo no soportado. Solo se permiten archivos .7z");
            }

            byte[] exportContent = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(exportContent);
            fis.close();

            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);

            String attachmentName = "attachment; filename=\"" + this.objetoArchivo.getArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            ec.setResponseContentType("application/x-7z-compressed");

            output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_ADICIONAL_3);
            getReservaTecnicaServicio().Accion(this);
            PrimeFaces.current().ajax().update("frmVer");
            PrimeFaces.current().executeScript("PF('dialogoVer').hide()");

        } catch (FileNotFoundException e) {
            this.addError("El archivo no fue encontrado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            this.addError("Ocurrió un error al intentar descargar el archivo: " + e.getMessage());
        } catch (IOException e) {
            this.addError("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            this.addError("Ocurrió un error inesperado: " + e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                this.addError("Error al cerrar recursos: " + e.getMessage());
            }
        }
        procesoFinal();
    }

    public void autorizarReserva(RtReserva reserva) {
        this.setObjeto(reserva);
        super.setAccion(ACCION_ADICIONAL_1);
        getReservaTecnicaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmReserva");
        procesoFinal();
    }

    public void rechazarReserva(RtReserva reserva) {
        this.setObjeto(reserva);
        this.objeto.setObservacion("");
        PrimeFaces.current().ajax().update("frmRechazarReserva");
        PrimeFaces.current().executeScript("PF('dialogoRechazarReserva').show()");
    }

    public void confirmarRechazarReserva() {
        
        super.setAccion(ACCION_ADICIONAL_2);
        getReservaTecnicaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmReserva");
        PrimeFaces.current().executeScript("PF('dialogoRechazar').hide()");
        procesoFinal();
    }

    public void regenerarArchivosReserva(RtReserva archivo) {

    }

    public void cancelarReserva(RtReserva reserva) {
        this.setObjeto(reserva);
        super.setAccion(ACCION_ADICIONAL_4);
        getReservaTecnicaServicio().Accion(this);
        procesoFinal();
    }

    public void confirmarArchivo(RtReservaArchivo archivo) {
        this.setObjetoArchivo(archivo);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getReservaTecnicaServicio().Accion(this);
        refrescarArchivosReserva();
        PrimeFaces.current().ajax().update("frmVer");
        procesoFinal();
    }

    public void rechazarArchivo(RtReservaArchivo archivo) {
        this.setObjetoArchivo(archivo);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getReservaTecnicaServicio().Accion(this);
        refrescarArchivosReserva();
        PrimeFaces.current().ajax().update("frmVer");
        procesoFinal();
    }

    public void regenerarArchivo(RtReservaArchivo archivo) {
        this.setObjetoArchivo(archivo);
        super.setAccion(ACCION_ADICIONAL_6);
        getReservaTecnicaServicio().Accion(this);
        procesoFinal();

    }

    public String getNombreTipoArchivo(Integer tipo) {
        return RtConstantes.getNombreTipoArchivo(tipo);
    }

    public void verReservaArchivoProceso(RtReservaArchivo archivo) {
        this.setObjetoArchivo(archivo);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_VER);
        getReservaTecnicaServicio().Accion(this);
        procesoFinal();
    }

    public String calcularTiempo(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return "0 hrs 0 min 0 seg";
        } else {
            long diferencia = fechaFin.getTime() - fechaInicio.getTime();

            long segundosEnMilisegundos = 1000;
            long minutosEnMilisegundos = segundosEnMilisegundos * 60;
            long horasEnMilisegundos = minutosEnMilisegundos * 60;

            long tiempoEnHoras = diferencia / horasEnMilisegundos;
            diferencia %= horasEnMilisegundos;

            long tiempoEnMinutos = diferencia / minutosEnMilisegundos;
            diferencia %= minutosEnMilisegundos;

            long tiempoEnSegundos = diferencia / segundosEnMilisegundos;

            return tiempoEnHoras + " hrs " + tiempoEnMinutos + " min " + tiempoEnSegundos + " seg";
        }
    }
    
    
    public void cancelarArchivo(RtReservaArchivo archivo){
        this.setObjetoArchivo(archivo);
        super.setAccion(ACCION_ADICIONAL_3);
        getReservaTecnicaServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmReserva");
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmReserva");
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog("Reserva", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_3:
                    PrimeFaces.current().ajax().update("frmVer");
                    break;
                case Url.ACCION_ADICIONAL_4:
                    PrimeFaces.current().ajax().update("frmReserva");
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVerArchivoProceso");
                            PrimeFaces.current().executeScript("PF('dialogoVerArchivoProceso').show()");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

}
