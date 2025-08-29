package com.saviasaludeps.savia.web.tutela.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuPersonaContacto;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.web.tutela.servicio.TuTutelaItemServicioIface;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

@ManagedBean
@ViewScoped
public final class TuTutelaItemBean extends Url {

    private TuTutelaItemServicioIface tuTutelaItemServicio;
    private TuTutelaItem objeto;

    private List<TuTutelaItem> registros;
    private LazyDataModel<TuTutelaItem> lazyTutelaItem;
    private List<Maestro> listaCausaTutela;
    private HashMap<Integer, Maestro> hashCausaTutela;
    private List<Maestro> listaTipoPrestacion;
    private HashMap<Integer, Maestro> hashTipoPrestacion;
    private List<Maestro> listaPresentacion;
    private HashMap<Integer, Maestro> hashPresentacion;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaEstadoItem;
    private HashMap<Integer, Maestro> hashEstadoItem;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private List<TuPersonaContacto> listaTuPersonaContacto;
    private List<Maestro> listaEstadoSolicitud;
    private HashMap<Integer, Maestro> hashEstadoSolicitud;
    private List<Maestro> listaEstadoItemCierre;
    
    //2025-07-15 jyperez variables manejo adjuntos item
    private int sizeLimitByAnexo;
    private int maxCantAnexos;
    private int contadorArchivos = 0;
    //2025-07-24 jyperez campo para mensaje emergente
    private String descripcionGenerica;

    public static final char ACCION_BORRAR_ADJUNTOS_FIRMA = 'A';

    public TuTutelaItemBean() {
        this.objeto = new TuTutelaItem();
        setMaxCantAnexos(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_CANTIDAD_ANEXOS)));
        setSizeLimitByAnexo(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_TAMANO_ANEXO)));
        
        Modulo mod = super.validarModulo(Modulo.ID_TU_GESTION_ITEMS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        super.getParamConsulta().setParametroConsulta1(TuTutelaItem.ESTADO_ITEM_ASIGNADO);
        super.getParamConsulta().setParametroConsulta2(TuTutelaItem.ESTADO_ITEM_GESTION);
        
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyTutelaItem = new LazyDataModel<TuTutelaItem>() {
                private List<TuTutelaItem> tuTutelaItems;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<TuTutelaItem> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    tuTutelaItems = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return tuTutelaItems;
                }

                @Override
                public String getRowKey(TuTutelaItem TuTutelaItem) {
                    return TuTutelaItem.getId().toString();
                }

                @Override
                public TuTutelaItem getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (TuTutelaItem tuTutelaItem : tuTutelaItems) {
                        if (id.equals(tuTutelaItem.getId())) {
                            return tuTutelaItem;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getTuTutelaItemServicio().cargaInicial(this);
        listar();
    }

    public TuTutelaItem getObjeto() {
        return objeto;
    }

    public void setObjeto(TuTutelaItem objeto) {
        this.objeto = objeto;
    }

    public List<TuTutelaItem> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TuTutelaItem> registros) {
        this.registros = registros;
    }

    public TuTutelaItemServicioIface getTuTutelaItemServicio() {
        return tuTutelaItemServicio;
    }

    public void setTuTutelaItemServicio(TuTutelaItemServicioIface tuTutelaItemServicio) {
        this.tuTutelaItemServicio = tuTutelaItemServicio;
    }

    public LazyDataModel<TuTutelaItem> getLazyTuPersonal() {
        return lazyTutelaItem;
    }

    public void setLazyTuPersonal(LazyDataModel<TuTutelaItem> lazyTutelaItem) {
        this.lazyTutelaItem = lazyTutelaItem;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getTuTutelaItemServicio().Accion(this);
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_EDITAR);
        getTuTutelaItemServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        }
        procesoFinal();
    }

    public void modificar() {
        //carga valor maestro
        if (getObjeto().getMaeEstadoItemId() != null) {
            Maestro estado = hashEstadoItem.get(getObjeto().getMaeEstadoItemId());
            if (estado != null) {
                getObjeto().setMaeEstadoItemCodigo(estado.getValor());
                getObjeto().setMaeEstadoItemValor(estado.getNombre());
            } else {
                super.addError("No se seleccionó un estado para el item.");
            }
        } else {
            super.addError("No se seleccionó un estado para el item.");
        }
        super.setAccion(Url.ACCION_MODIFICAR);
        getTuTutelaItemServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide();");
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
        }
        procesoFinal();
    }

    public void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmGestionItem");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmGestionItem");
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
    
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        if (getUbicacionesRecursiva() != null && !ubicacionesRecursiva.isEmpty()) {
            Ubicacion _municipio = getUbicacionesRecursiva().get(id);
            if (_municipio != null && _municipio.getUbicacionPadre() != null) {
                Ubicacion _departamento = _municipio.getUbicacionPadre();
                if (_departamento.getUbicacionPadre() != null) {
                    Ubicacion _pais = _departamento.getUbicacionPadre();
                    ubicacionStr = _pais.getNombre();
                }
                ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
                ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
            }
        }
        return ubicacionStr;
    }

    public void descargarAnexo(TuAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getNombreArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);;
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {

        }
    }
    
    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 15000000 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }
    
    public void handleFileUploadItem(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_Item_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.TU_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            }
            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuAdjunto adjunto = new TuAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjeto().getTuAdjuntosList().size());

            //listaAnexos.add(adjunto);
            this.getObjeto().getTuAdjuntosList().add(adjunto);

            //llenarSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS_ITEM, listaAnexos);
            PrimeFaces.current().resetInputs("frmEditar:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosServicios");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }
    
    public void borrarAdjuntoItemGestion(int id, int pos) {
        try {
            List<TuAdjunto> lista = new ArrayList();
            int i = 0, j = 0;
            for (TuAdjunto det : this.getObjeto().getTuAdjuntosList()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.getObjeto().setTuAdjuntosList(lista);
                    
        } catch (Exception e) {
            super.addError("No es posible borrar el Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosServicios");

        }
        generarMensajes();
    }
    
    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
    /**
     * @return the listaCausaTutela
     */
    public List<Maestro> getListaCausaTutela() {
        return listaCausaTutela;
    }

    /**
     * @param listaCausaTutela the listaCausaTutela to set
     */
    public void setListaCausaTutela(List<Maestro> listaCausaTutela) {
        this.listaCausaTutela = listaCausaTutela;
    }

    /**
     * @return the hashCausaTutela
     */
    public HashMap<Integer, Maestro> getHashCausaTutela() {
        return hashCausaTutela;
    }

    /**
     * @param hashCausaTutela the hashCausaTutela to set
     */
    public void setHashCausaTutela(HashMap<Integer, Maestro> hashCausaTutela) {
        this.hashCausaTutela = hashCausaTutela;
    }

    /**
     * @return the listaTipoPrestacion
     */
    public List<Maestro> getListaTipoPrestacion() {
        return listaTipoPrestacion;
    }

    /**
     * @param listaTipoPrestacion the listaTipoPrestacion to set
     */
    public void setListaTipoPrestacion(List<Maestro> listaTipoPrestacion) {
        this.listaTipoPrestacion = listaTipoPrestacion;
    }

    /**
     * @return the hashTipoPrestacion
     */
    public HashMap<Integer, Maestro> getHashTipoPrestacion() {
        return hashTipoPrestacion;
    }

    /**
     * @param hashTipoPrestacion the hashTipoPrestacion to set
     */
    public void setHashTipoPrestacion(HashMap<Integer, Maestro> hashTipoPrestacion) {
        this.hashTipoPrestacion = hashTipoPrestacion;
    }

    /**
     * @return the listaPresentacion
     */
    public List<Maestro> getListaPresentacion() {
        return listaPresentacion;
    }

    /**
     * @param listaPresentacion the listaPresentacion to set
     */
    public void setListaPresentacion(List<Maestro> listaPresentacion) {
        this.listaPresentacion = listaPresentacion;
    }

    /**
     * @return the hashPresentacion
     */
    public HashMap<Integer, Maestro> getHashPresentacion() {
        return hashPresentacion;
    }

    /**
     * @param hashPresentacion the hashPresentacion to set
     */
    public void setHashPresentacion(HashMap<Integer, Maestro> hashPresentacion) {
        this.hashPresentacion = hashPresentacion;
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
     * @return the listaEstadoItem
     */
    public List<Maestro> getListaEstadoItem() {
        return listaEstadoItem;
    }

    /**
     * @param listaEstadoItem the listaEstadoItem to set
     */
    public void setListaEstadoItem(List<Maestro> listaEstadoItem) {
        this.listaEstadoItem = listaEstadoItem;
    }

    /**
     * @return the hashEstadoItem
     */
    public HashMap<Integer, Maestro> getHashEstadoItem() {
        return hashEstadoItem;
    }

    /**
     * @param hashEstadoItem the hashEstadoItem to set
     */
    public void setHashEstadoItem(HashMap<Integer, Maestro> hashEstadoItem) {
        this.hashEstadoItem = hashEstadoItem;
    }

    /**
     * @return the ubicacionesRecursiva
     */
    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    /**
     * @param ubicacionesRecursiva the ubicacionesRecursiva to set
     */
    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    /**
     * @return the listaTuPersonaContacto
     */
    public List<TuPersonaContacto> getListaTuPersonaContacto() {
        return listaTuPersonaContacto;
    }

    /**
     * @param listaTuPersonaContacto the listaTuPersonaContacto to set
     */
    public void setListaTuPersonaContacto(List<TuPersonaContacto> listaTuPersonaContacto) {
        this.listaTuPersonaContacto = listaTuPersonaContacto;
    }

    /**
     * @return the listaEstadoSolicitud
     */
    public List<Maestro> getListaEstadoSolicitud() {
        return listaEstadoSolicitud;
    }

    /**
     * @param listaEstadoSolicitud the listaEstadoSolicitud to set
     */
    public void setListaEstadoSolicitud(List<Maestro> listaEstadoSolicitud) {
        this.listaEstadoSolicitud = listaEstadoSolicitud;
    }

    /**
     * @return the hashEstadoSolicitud
     */
    public HashMap<Integer, Maestro> getHashEstadoSolicitud() {
        return hashEstadoSolicitud;
    }

    /**
     * @param hashEstadoSolicitud the hashEstadoSolicitud to set
     */
    public void setHashEstadoSolicitud(HashMap<Integer, Maestro> hashEstadoSolicitud) {
        this.hashEstadoSolicitud = hashEstadoSolicitud;
    }

    /**
     * @return the listaEstadoItemCierre
     */
    public List<Maestro> getListaEstadoItemCierre() {
        return listaEstadoItemCierre;
    }

    /**
     * @param listaEstadoItemCierre the listaEstadoItemCierre to set
     */
    public void setListaEstadoItemCierre(List<Maestro> listaEstadoItemCierre) {
        this.listaEstadoItemCierre = listaEstadoItemCierre;
    }

    /**
     * @return the sizeLimitByAnexo
     */
    public int getSizeLimitByAnexo() {
        return sizeLimitByAnexo;
    }

    /**
     * @param sizeLimitByAnexo the sizeLimitByAnexo to set
     */
    public void setSizeLimitByAnexo(int sizeLimitByAnexo) {
        this.sizeLimitByAnexo = sizeLimitByAnexo;
    }

    /**
     * @return the maxCantAnexos
     */
    public int getMaxCantAnexos() {
        return maxCantAnexos;
    }

    /**
     * @param maxCantAnexos the maxCantAnexos to set
     */
    public void setMaxCantAnexos(int maxCantAnexos) {
        this.maxCantAnexos = maxCantAnexos;
    }
    
    public int getContadorArchivos() {
        return contadorArchivos;
    }

    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    /**
     * @return the descripcionGenerica
     */
    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    /**
     * @param descripcionGenerica the descripcionGenerica to set
     */
    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }
}
