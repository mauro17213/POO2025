/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cargas.CarCarga;
import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoPrestador;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;

import com.saviasaludeps.savia.web.cargas.servicio.CargaServicioIface;
import com.saviasaludeps.savia.web.cargas.servicio.CargaServicioImpl;
import com.saviasaludeps.savia.web.cargas.utilidades.RtConstantesCargas;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;

import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.text.Normalizer;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author aguevara
 */
@ManagedBean
@ViewScoped
public class CargaBean extends Url {

    private CargaServicioIface cargaServicio;
    private CarCarga objeto;
    private CarProceso objetoProceso;
    private CarPeriodo objetoPeriodo;
    private CarCargaRegistro objetoCargaRegistros;
    private CarProcesoPrestador objetoPrestador;
    private LazyDataModel<CarCarga> lazyCargas;
    private List<CarCarga> registros;

    private List<CarProcesoPrestador> listaProcesosPrestadores;
    private List<CarPeriodo> listaPeriodos;
    private List<CarPeriodo> listaPeriodosProceso;
    private List<CarProcesoUsuario> listaProcesoUsuarios;
    private List<CarCarga> listaAdjuntos;
    private List<CarCargaRegistro> listaCargaRegistros;

    //Lista de procesos - usuario
    private List<CarProceso> listaProcesos;

    private List<CntPrestador> listaPrestadores;

    //Lista Prestadores seleccionados - carga
    private List<CntPrestador> listaSeleccionadaPrestadores;
    private Integer objetoProcesoId;
    private Integer objetoPrestadorId;
    private Integer objetoPeriodoId;

    //Lista Errores de carga
    private List<String> listaErroresCarga;

    public CargaBean() {
        this.objeto = new CarCarga();
        this.objetoProceso = new CarProceso();
        this.objetoPeriodo = new CarPeriodo();
        this.objetoPrestador = new CarProcesoPrestador();
        this.objetoCargaRegistros = new CarCargaRegistro();
        Modulo _mod = super.validarModulo(Modulo.ID_CAR_CARGAS);
        //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta().setEmpresaId(null);
        } else {
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCargas = new LazyDataModel<CarCarga>() {

                private List<CarCarga> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CarCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CarCarga objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CarCarga getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CarCarga objeto : lista) {
                        if (id.equals(CargaBean.this.objeto.getId())) {
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
        listar();
    }

    public CarCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(CarCarga objetoCargas) {
        this.objeto = objetoCargas;
    }

    public LazyDataModel<CarCarga> getLazyCargas() {
        return lazyCargas;
    }

    public void setLazyCargas(LazyDataModel<CarCarga> lazyCargas) {
        this.lazyCargas = lazyCargas;
    }

    public List<CarCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CarCarga> registros) {
        this.registros = registros;
    }

    public CargaServicioIface getCargaServicio() {
        if (cargaServicio == null) {
            cargaServicio = new CargaServicioImpl();
        }
        return cargaServicio;
    }

    public void setCargaServicio(CargaServicioIface cargaServicio) {
        this.cargaServicio = cargaServicio;
    }

    public List<CarProcesoPrestador> getListaProcesosPrestadores() {
        return listaProcesosPrestadores;
    }

    public void setListaProcesosPrestadores(List<CarProcesoPrestador> listaProcesosPrestador) {
        this.listaProcesosPrestadores = listaProcesosPrestador;
    }

    public List<CarPeriodo> getListaPeriodos() {
        return listaPeriodos;
    }

    public void setListaPeriodos(List<CarPeriodo> listaPeriodos) {
        this.listaPeriodos = listaPeriodos;
    }

    public List<CarProcesoUsuario> getListaProcesoUsuarios() {
        return listaProcesoUsuarios;
    }

    public void setListaProcesoUsuarios(List<CarProcesoUsuario> listaProcesoUsuarios) {
        this.listaProcesoUsuarios = listaProcesoUsuarios;
    }

    public CarProceso getObjetoProceso() {
        return objetoProceso;
    }

    public void setObjetoProceso(CarProceso objetoProceso) {
        this.objetoProceso = objetoProceso;
    }

    public List<CarProceso> getListaProcesos() {
        return listaProcesos;
    }

    public CarProcesoPrestador getObjetoPrestador() {
        return objetoPrestador;
    }

    public void setObjetoPrestador(CarProcesoPrestador objetoPrestador) {
        this.objetoPrestador = objetoPrestador;
    }

    public CarCargaRegistro getObjetoCargaRegistros() {
        return objetoCargaRegistros;
    }

    public void setObjetoCargaRegistros(CarCargaRegistro objetoCargaRegistros) {
        this.objetoCargaRegistros = objetoCargaRegistros;
    }

    public void setListaProcesos(List<CarProceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public List<CntPrestador> getListaSeleccionadaPrestadores() {
        return listaSeleccionadaPrestadores;
    }

    public void setListaSeleccionadaPrestadores(List<CntPrestador> listaSeleccionadaPrestadores) {
        this.listaSeleccionadaPrestadores = listaSeleccionadaPrestadores;
    }

    public List<CarCarga> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<CarCarga> listaCargas) {
        this.listaAdjuntos = listaCargas;
    }

    public List<CntPrestador> getListaPrestadores() {
        return listaPrestadores;
    }

    public void setListaPrestadores(List<CntPrestador> listaPrestadores) {
        this.listaPrestadores = listaPrestadores;
    }

    public Integer getObjetoProcesoId() {
        return objetoProcesoId;
    }

    public void setObjetoProcesoId(Integer objetoProcesoId) {
        this.objetoProcesoId = objetoProcesoId;
    }

    public Integer getObjetoPrestadorId() {
        return objetoPrestadorId;
    }

    public void setObjetoPrestadorId(Integer objetoPrestadorId) {
        this.objetoPrestadorId = objetoPrestadorId;
    }

    public CarPeriodo getObjetoPeriodo() {
        return objetoPeriodo;
    }

    public void setObjetoPeriodo(CarPeriodo objetoPeriodo) {
        this.objetoPeriodo = objetoPeriodo;
    }

    public List<CarPeriodo> getListaPeriodosProceso() {
        return listaPeriodosProceso;
    }

    public void setListaPeriodosProceso(List<CarPeriodo> listaPeriodosProceso) {
        this.listaPeriodosProceso = listaPeriodosProceso;
    }

    public Integer getObjetoPeriodoId() {
        return objetoPeriodoId;
    }

    public void setObjetoPeriodoId(Integer objetoPeriodoId) {
        this.objetoPeriodoId = objetoPeriodoId;
    }

    public List<String> getListaErroresCarga() {
        return listaErroresCarga;
    }

    public void setListaErroresCarga(List<String> listaErroresCarga) {
        this.listaErroresCarga = listaErroresCarga;
    }

    public List<CarCargaRegistro> getListaCargaRegistros() {
        return listaCargaRegistros;
    }

    public void setListaCargaRegistros(List<CarCargaRegistro> listaCargaRegistros) {
        this.listaCargaRegistros = listaCargaRegistros;
    }

    public void listar() {
        setListaProcesos(new ArrayList<>());
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaServicio().Accion(this);
    }

    public void ver(CarCarga obj) {
        getObjeto().setId(obj.getId());
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verAdjuntos(int id) {
        setObjeto(new CarCarga(id));
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAdjuntos:pAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoVerAdjuntos').show()");
        procesoFinal();
    }

    public void verErroresEstructura(CarCarga obj) {
        setObjeto(obj);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerErroresEstructura:pVerErroresEstructuraTabla");
        PrimeFaces.current().ajax().update("frmVerErroresEstructura:tablaErroresEstructura");
        PrimeFaces.current().executeScript("PF('dialogoVerErroresEstructura').show()");
        procesoFinal();
    }

    public void crear() {
        if (this.listaAdjuntos != null) {
            this.listaAdjuntos.clear();
        }
        setObjetoProcesoId(null);
        setObjetoPrestadorId(null);
        setObjetoPeriodoId(null);
        setObjeto(new CarCarga());
        super.setAccion(ACCION_CREAR);
        getCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCargaServicio().Accion(this);
        if (!super.isError()) {
            refrescar();
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(CarCarga obj) {
        getObjeto().setId(obj.getId());
        super.setAccion(ACCION_EDITAR);
        getCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCargaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getCargaServicio().Accion(this);
        procesoFinal();
    }

    /**
     *
     * @param idEstado
     * @return
     */
    public String getEstadoArchivo(Integer idEstado) {
        return RtConstantesCargas.getEstado(idEstado);
    }


    /*----------------------
    --------ADJUNTOS--------
    ------------------------*/
    public void adicionarAdjunto(FileUploadEvent event) {
        // Verificar si ya existe un archivo en la lista
        if (listaAdjuntos != null && !listaAdjuntos.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Solo se permite cargar un archivo. Por favor, elimine el archivo existente antes de agregar uno nuevo.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return; // Terminar la ejecución del método
        }

        UploadedFile file = event.getFile();
        String nombreOriginal = file.getFileName();

        // Separar el nombre del archivo y la extensión
        String extension = "";
        int i = nombreOriginal.lastIndexOf('.');
        if (i > 0) {
            extension = nombreOriginal.substring(i);
            nombreOriginal = nombreOriginal.substring(0, i);
        }

        // Limpiar solo el nombre y luego agregarle la extensión
        String nombreArchivo = limpiarCadena(nombreOriginal) + extension;

        if (listaAdjuntos == null) {
            setListaAdjuntos(new ArrayList<>());
        }

        // Crear un nuevo adjunto y agregarlo a la lista
        CarCarga adjunto = new CarCarga();
        adjunto.setNombreArchivo(nombreArchivo);
        adjunto.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
        adjunto.setFechaHoraCrea(new Date());
        adjunto.setTerminalCrea(getConexion().getIp());

        try {
            String ruta = PropApl.getInstance().get(PropApl.CAR_RUTA_CARGAS_MASIVAS);
            String filePath = ruta + nombreArchivo;
            adjunto.setRuta(ruta);
            adjunto.setArchivo(nombreArchivo);

            File outputFile = new File(filePath);
            try (OutputStream outputStream = new FileOutputStream(outputFile)) {
                outputStream.write(file.getContent());
            }
        } catch (IOException e) {
            addError("Error al guardar el archivo -- " + e);
        }

        listaAdjuntos.add(adjunto);
    }

// Método para limpiar el nombre del archivo eliminando espacios y caracteres especiales
    private String limpiarCadena(String cadena) {
        if (cadena == null) {
            return "";
        }

        cadena = cadena.replace(" ", "");  // Quitar espacios
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD); // Quitar acentos
        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        cadena = cadena.replaceAll("[^a-zA-Z0-9]", ""); // Quitar caracteres especiales no deseados

        return cadena;
    }

    // Método para retirar un adjunto (solo de la lista, no del sistema de archivos)
    public void retirarAdjunto(String nombreArchivo) {
        listaAdjuntos.removeIf(adjunto -> adjunto.getNombreArchivo().equals(nombreArchivo));
    }

    // Método para borrar un adjunto del sistema de archivos y de la lista
    public void borrarAdjunto(String nombreArchivo) {
        String ruta = PropApl.getInstance().get(PropApl.CAR_RUTA_CARGAS_MASIVAS);
        CarCarga adjuntoAEliminar = null;
        for (CarCarga adjunto : listaAdjuntos) {
            if (adjunto.getNombreArchivo().equals(nombreArchivo)) {
                adjuntoAEliminar = adjunto;
                break;
            }
        }

        if (adjuntoAEliminar != null) {
            File archivo = new File(ruta + nombreArchivo);
            if (archivo.exists()) {
                archivo.delete(); // Borrar archivo
            }
            listaAdjuntos.remove(adjuntoAEliminar);
        }
    }

    public void descargarAdjunto(CarCarga adj) throws IOException {
        String rutaCompleta = adj.getRuta() + adj.getArchivo();
        FileInputStream fis = null;
        OutputStream output = null;
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adj.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            } else {
                throw new Exception();
            }
            output = ec.getResponseOutputStream();
            output.write(exportContent);
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Carga masiva - Descargar Adjuntos", getObjeto().toString());
        procesoFinal();
    }

    public void cargaProcesoPrestadorPeriodo() {

        if (getListaPrestadores() == null) {
            setListaPrestadores(new ArrayList<>());
        }
        if (getListaPeriodosProceso() == null) {
            setListaPeriodosProceso(new ArrayList<>());
        }

        // Limpiar las listas de prestadores y períodos antes de llenarlas nuevamente
        listaPeriodosProceso.clear();
        listaPrestadores.clear();

        // Obtener la fecha actual sin horas para comparación
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date fechaActual = cal.getTime();

        // Filtrar y agregar solo los períodos que no hayan caducado
        for (CarPeriodo periodo : getListaPeriodos()) {
            if (periodo.getCarProceso().getId().equals(objetoProcesoId)
                    && (periodo.getFechaFin().after(fechaActual) || periodo.getFechaFin().equals(fechaActual))) {
                listaPeriodosProceso.add(periodo);
            }
        }

        // Agregar los prestadores relacionados con el proceso
        for (CarProcesoPrestador prestador : getListaProcesosPrestadores()) {
            if (prestador.getProceso().getId().equals(objetoProcesoId)) {
                listaPrestadores.add(prestador.getPrestador());
            }
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmCargas");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
            }
        }
        generarMensajes();
    }

}
