package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.especial.PeCargaGestion;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.web.especial.servicio.CargaMasivaGestionServicioIface;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class CargaMasivaGestionBean extends Url {

    private CargaMasivaGestionServicioIface cargaMasivaGestionServicio;

    private List<PePrograma> listaPePrograma;
    private HashMap<Integer, PePrograma> hashPePrograma;

    private PeCargaGestion objeto;
    private List<PeCargaGestion> registros;
    private LazyDataModel<PeCargaGestion> lazyCargaMasiva;
    private Integer idPrograma;
    private List<Maestro> listaEstadoCarga;

    public CargaMasivaGestionBean() {
        this.objeto = new PeCargaGestion();
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_CARGA_MASIVA_GESTIONES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            if (!super.getConexion().getEmpresa().isAdministradora()) {
                super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            }
            lazyCargaMasiva = new LazyDataModel<PeCargaGestion>() {
                private List<PeCargaGestion> listaCargas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeCargaGestion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaCargas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaCargas;
                }

                @Override
                public String getRowKey(PeCargaGestion carga) {
                    return carga.getId().toString();
                }

                @Override
                public PeCargaGestion getRowData(String carga) {
                    Integer id = Integer.valueOf(carga);
                    for (PeCargaGestion cg : listaCargas) {
                        if (id.equals(cg.getId())) {
                            return cg;
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
        getCargaMasivaGestionServicio().cargaInicial(this);
        listar();
    }

    public CargaMasivaGestionServicioIface getCargaMasivaGestionServicio() {
        return cargaMasivaGestionServicio;
    }

    public void setCargaMasivaGestionServicio(CargaMasivaGestionServicioIface cargaMasivaGestionServicio) {
        this.cargaMasivaGestionServicio = cargaMasivaGestionServicio;
    }

    public PeCargaGestion getObjeto() {
        return objeto;
    }

    public void setObjeto(PeCargaGestion objeto) {
        this.objeto = objeto;
    }

    public List<PeCargaGestion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeCargaGestion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PeCargaGestion> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<PeCargaGestion> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public List<PePrograma> getListaPePrograma() {
        return listaPePrograma;
    }

    public void setListaPePrograma(List<PePrograma> listaPePrograma) {
        this.listaPePrograma = listaPePrograma;
    }

    public HashMap<Integer, PePrograma> getHashPePrograma() {
        return hashPePrograma;
    }

    public void setHashPePrograma(HashMap<Integer, PePrograma> hashPePrograma) {
        this.hashPePrograma = hashPePrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public List<Maestro> getListaEstadoCarga() {
        return listaEstadoCarga;
    }

    public void setListaEstadoCarga(List<Maestro> listaEstadoCarga) {
        this.listaEstadoCarga = listaEstadoCarga;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaMasivaGestionServicio().Accion(this);
    }

    public void cargarAdjunto(FileUploadEvent event) {
        try {
            UploadedFile archivo = event.getFile();
            this.objeto.setAdjuntoStream(archivo.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            this.objeto.setNombre(nombreAdjunto);
            // llamamos al guardar
            if (this.objeto.getAdjuntoStream() != null) {
                super.setAccion(ACCION_GUARDAR);
                getCargaMasivaGestionServicio().Accion(this);
                PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
            } else {
                this.addError("No se ha cargado el archivo.");
            }
        } catch (IOException ex) {
            addError("Inconveninetes al momento de leer el archivo de carga.");
        }
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCargaMasivaGestionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public String getEstado(Integer tipo) {
        return PeConstantes.obtenerEstadoCargaMasivaStr(tipo);
    }

    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        this.objeto = new PeCargaGestion(id);
        getCargaMasivaGestionServicio().Accion(this);
        procesoFinal();
    }

    public void generarArchivoResultados(int idcarga) {
        this.setObjeto(new PeCargaGestion(idcarga));
        super.setAccion(ACCION_ADICIONAL_1);
        getCargaMasivaGestionServicio().Accion(this);        
        if(objeto.getRespExiste()){
            String rutaCompleta = this.objeto.getRespRuta()+ this.objeto.getRespArchivo();
            descargarArchivo(rutaCompleta, objeto.getRespArchivo());
        }else{
            this.addError("El archivo de resultados no existe en la ruta de descarga.");
        }        
        procesoFinal();
    }
    
    public void generarArchivoCarga(int idcarga) {
        this.setObjeto(new PeCargaGestion(idcarga));
        super.setAccion(ACCION_ADICIONAL_1);
        getCargaMasivaGestionServicio().Accion(this);        
        if(objeto.getExiste()){
            String rutaCompleta = this.objeto.getRuta() + this.objeto.getArchivo();
            descargarArchivo(rutaCompleta, objeto.getArchivo());
        }else{
            this.addError("El archivo de carga no existe en la ruta de descarga.");
        }        
        procesoFinal();
    }
    
    private void descargarArchivo(String rutaCompleta, String archivo){
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
            String attachmentName = "attachment; filename=\"" + archivo + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("text/plain");
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
    }

    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == PeConstantes.ESTADO_PROCESADO || estado == PeConstantes.ESTADO_CANCELADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == PeConstantes.ESTADO_EN_PROCESO) {
            deshabilitar = false;
        }
        return deshabilitar;
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
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
        }
        generarMensajes();
    }

}
