package com.saviasaludeps.savia.web.tutela.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialFirma;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialPersona;
import com.saviasaludeps.savia.web.tutela.servicio.TuPersonalServicioIface;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

@ManagedBean
@ViewScoped
public final class TuPersonalBean extends Url {

    private TuPersonalServicioIface tuPersonalServicio;
    private TuMemorialPersona objeto;
    private TuMemorialFirma tuMemorialFirma;

    private List<TuMemorialPersona> registros;
    private LazyDataModel<TuMemorialPersona> lazyTuPersonal;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    private List<Maestro> listaCargoPersonal;
    private HashMap<Integer, Maestro> hashCargoPersonal;

    private int maxCantAnexos;
    private int contadorArchivos = 0;
    private int sizeLimitByAnexo;

    public static final char ACCION_BORRAR_ADJUNTOS_FIRMA = 'A';

    public TuPersonalBean() {
        this.objeto = new TuMemorialPersona();
        setMaxCantAnexos(1);
        setSizeLimitByAnexo(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_TAMANO_ANEXO)));
        Modulo mod = super.validarModulo(Modulo.ID_TU_PERSONAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyTuPersonal = new LazyDataModel<TuMemorialPersona>() {
                private List<TuMemorialPersona> tuMemorialPersona;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<TuMemorialPersona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    tuMemorialPersona = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return tuMemorialPersona;
                }

                @Override
                public String getRowKey(TuMemorialPersona TuMemorialPersona) {
                    return TuMemorialPersona.getId().toString();
                }

                @Override
                public TuMemorialPersona getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (TuMemorialPersona tuMemorialPersona : tuMemorialPersona) {
                        if (id.equals(tuMemorialPersona.getId())) {
                            return tuMemorialPersona;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getTuPersonalServicio().cargaInial(this);
        listar();
    }

    public TuMemorialPersona getObjeto() {
        return objeto;
    }

    public void setObjeto(TuMemorialPersona objeto) {
        this.objeto = objeto;
    }

    public TuMemorialFirma getTuMemorialFirma() {
        return tuMemorialFirma;
    }

    public void setTuMemorialFirma(TuMemorialFirma tuMemorialFirma) {
        this.tuMemorialFirma = tuMemorialFirma;
    }

    public List<TuMemorialPersona> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TuMemorialPersona> registros) {
        this.registros = registros;
    }

    public TuPersonalServicioIface getTuPersonalServicio() {
        return tuPersonalServicio;
    }

    public void setTuPersonalServicio(TuPersonalServicioIface tuPersonalServicio) {
        this.tuPersonalServicio = tuPersonalServicio;
    }

    public LazyDataModel<TuMemorialPersona> getLazyTuPersonal() {
        return lazyTuPersonal;
    }

    public void setLazyTuPersonal(LazyDataModel<TuMemorialPersona> lazyTuPersonal) {
        this.lazyTuPersonal = lazyTuPersonal;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public List<Maestro> getListaCargoPersonal() {
        return listaCargoPersonal;
    }

    public void setListaCargoPersonal(List<Maestro> listaCargoPersonal) {
        this.listaCargoPersonal = listaCargoPersonal;
    }

    public HashMap<Integer, Maestro> getHashCargoPersonal() {
        return hashCargoPersonal;
    }

    public void setHashCargoPersonal(HashMap<Integer, Maestro> hashCargoPersonal) {
        this.hashCargoPersonal = hashCargoPersonal;
    }

    public int getMaxCantAnexos() {
        return maxCantAnexos;
    }

    public void setMaxCantAnexos(int maxCantAnexos) {
        this.maxCantAnexos = maxCantAnexos;
    }

    public int getContadorArchivos() {
        return contadorArchivos;
    }

    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    public int getSizeLimitByAnexo() {
        return sizeLimitByAnexo;
    }

    public void setSizeLimitByAnexo(int sizeLimitByAnexo) {
        this.sizeLimitByAnexo = sizeLimitByAnexo;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getTuPersonalServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_VER);
        getTuPersonalServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmVer");
            PrimeFaces.current().ajax().update("frmVer");
            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        }
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getTuPersonalServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmCrear");
            PrimeFaces.current().ajax().update("frmCrear");
            PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        }
        procesoFinal();

    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_EDITAR);
        getTuPersonalServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        }
        procesoFinal();
    }

    public void verGestion(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        getTuPersonalServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        if (getObjeto().getTuMemorialFirmasList().size() > 1) {
            addError("Solo se permite un archivo");
        }

        if (!super.isError()) {
            super.setAccion(Url.ACCION_GUARDAR);
            getTuPersonalServicio().Accion(this);
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
            PrimeFaces.current().resetInputs("frmPersonal");
            PrimeFaces.current().ajax().update("frmPersonal");

        }
        procesoFinal();
    }

    public void modificar() {

        if (getObjeto().getTuMemorialFirmasList().size() > 1) {
            addError("Solo se permite un archivo");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_MODIFICAR);
            getTuPersonalServicio().Accion(this);
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            PrimeFaces.current().resetInputs("frmPersonal");
            PrimeFaces.current().ajax().update("frmPersonal");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR);
        getTuPersonalServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmPersonal");
            PrimeFaces.current().ajax().update("frmPersonal");
        }
        procesoFinal();

    }

    public void modificarGestion() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        getTuPersonalServicio().Accion(this);
        procesoFinal();
    }

    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 15000000 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    public void handleFileUploadMemorial(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            //int i = file.lastIndexOf(".");
            //String ext = file.substring(i, file.length());
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            //String nombre = "adjunto_estado_";
            //String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            //InputStream input = new ByteArrayInputStream(arreglo);

            /*String ruta = PropApl.getInstance().get(PropApl.TU_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            }*/
            //OutputStream output = new FileOutputStream(new File(ruta, filename));
            //IOUtils.copy(input, output);
            //IOUtils.closeQuietly(input);
            //IOUtils.closeQuietly(output);
            if (this.getObjeto().getTuMemorialFirmasList() == null) {
                this.getObjeto().setTuMemorialFirmasList(new ArrayList<>());
            }
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuMemorialFirma adjunto = new TuMemorialFirma();
            adjunto.setNombre(file);
            adjunto.setId(null);
            adjunto.setFirma(arreglo);
            this.auditoriaGuardar(adjunto);

            this.getObjeto().getTuMemorialFirmasList().add(adjunto);

            PrimeFaces.current().resetInputs("frmCrear:tablaCrearAnexosFirma");
            PrimeFaces.current().ajax().update("frmCrear:tablaCrearAnexosFirma");

        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void borrarAdjuntoFirmaMemoria(TuMemorialFirma sugerido) {
        if (sugerido.getId() != null) {
            this.setTuMemorialFirma(new TuMemorialFirma());
            this.getTuMemorialFirma().setId(sugerido.getId());
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(ACCION_BORRAR_ADJUNTOS_FIRMA);
            getTuPersonalServicio().Accion(this);
        }
        int posicionEliminar = this.getObjeto().getTuMemorialFirmasList().indexOf(sugerido);
        this.getObjeto().getTuMemorialFirmasList().remove(posicionEliminar);
        addMensaje("Se eliminó un registro de manera exitosa");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaCrearAnexosFirma");
        PrimeFaces.current().ajax().update("frmCrear:tablaCrearAnexosFirma");
        PrimeFaces.current().ajax().update("frmEditar:tablaCrearAnexosFirma");
        PrimeFaces.current().ajax().update("frmEditar:tablaCrearAnexosFirma");
    }

    /*public void descargarAnexo(TuMemorialFirma adjunto) {
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
    }*/
    public void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmPersonal");
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
                        case TuPersonalBean.ACCION_BORRAR_ADJUNTOS_FIRMA:
                            crearLog(getTuMemorialFirma().toString());
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
}
