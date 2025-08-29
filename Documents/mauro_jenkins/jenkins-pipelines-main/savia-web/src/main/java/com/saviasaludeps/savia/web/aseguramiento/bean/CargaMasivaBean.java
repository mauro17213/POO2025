/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegCarga;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegDetalleCarga;
import com.saviasaludeps.savia.web.aseguramiento.servicio.CargaMasivaServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.servicio.CargaMasivaServicioImpl;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class CargaMasivaBean extends Url {

    private CargaMasivaServicioIface cargaMasivaServicio;
    private AsegCarga objeto;
    private List<AsegCarga> registros;
    private LazyDataModel<AsegCarga> lazyCargaMasiva;
    
    private List<AsegDetalleCarga> listaDetalleCarga;
//    private List<Maestro> listaTipoDocumento;
//    private HashMap<Integer, Maestro> hashTipoDocumento;

    public CargaMasivaBean() {
        this.objeto = new AsegCarga();
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_CARGA_MASIVA);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCargaMasiva = new LazyDataModel<AsegCarga>() {
                private List<AsegCarga> listaCargas;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<AsegCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AsegCarga portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public AsegCarga getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (AsegCarga portabilidad : listaCargas) {
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
        getCargaMasivaServicio().cargaInicial(this);
        listar();
    }

    public CargaMasivaServicioIface getCargaMasivaServicio() {
        if (cargaMasivaServicio == null) {
            cargaMasivaServicio = new CargaMasivaServicioImpl();
        }
        return cargaMasivaServicio;
    }

    public void setCargaMasivaServicio(CargaMasivaServicioIface cargaMasivaServicio) {
        this.cargaMasivaServicio = cargaMasivaServicio;
    }

    public AsegCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegCarga objeto) {
        this.objeto = objeto;
    }

    public List<AsegCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegCarga> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<AsegCarga> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaMasivaServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getCargaMasivaServicio().Accion(this);
        /*PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        */
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCargaMasivaServicio().Accion(this);
        this.objeto.setTipo(AfiliadoParametro.TIPO_ARCHIVO_AFILIADOS);
        PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            getCargaMasivaServicio().Accion(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
            procesoFinal();
        }else {
            this.addError("No se ha cargado el archivo.");
            procesoFinal();
        }
    }

    public void editar(int id) {
        
        procesoFinal();
    }

    public void modificar() {
        procesoFinal();
    }

    public void borrar(int id) {

        procesoFinal();
    }
    
    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        this.objeto = new AsegCarga(id);
        getCargaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void generarCertificado(int id) {
        procesoFinal();
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
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
        }
        generarMensajes();
    }
    
    public String getTipoArchivo(int tipo) {
        String descripcion;
        switch(tipo) {
            case 1:
                descripcion = "Afiliados";
            break;
            case 2:
                descripcion = "Novedades de afiliados";
            break;
            case 3:
                descripcion = "Novedades de afiliados V2";
            break;
            default:
                descripcion = "";
            break;
        }
        
        return descripcion;
    }
    
    public String getEstado(int tipo) {
        String descripcion;
        switch(tipo) {
            case AfiliadoParametro.ESTADO_EN_COLA:
                descripcion = "En Cola";
            break;
            case AfiliadoParametro.ESTADO_PROCESANDO:
                descripcion = "En Proceso";
            break;
            case AfiliadoParametro.ESTADO_PROCESADO:
                descripcion = "Procesado";
            break;
            case AfiliadoParametro.ESTADO_ABORTADO:
                descripcion = "Cancelado";
            break;
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }
    
    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (this.objeto.getTipo() != 0 ) {
                UploadedFile archivo = event.getFile();
                this.objeto.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                //int indiceExtension = nombreAdjunto.lastIndexOf(".");
                //String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
                this.objeto.setNombre(nombreAdjunto);
                // llamamos al guardar
                this.guardar();
            } else {
                this.addError("Tipo Archivo: Este campo es obligatorio.");
                procesoFinal();
            }
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == AfiliadoParametro.ESTADO_PROCESADO || estado == AfiliadoParametro.ESTADO_ABORTADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == AfiliadoParametro.ESTADO_PROCESANDO || estado == AfiliadoParametro.ESTADO_EN_COLA) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public StreamedContent generarArchivoResultados(int id) {
        StreamedContent streamedContent2 = null;
        int indiceExtension;
        String extension;
        String nombre;
        String texto = "" ;
        try {
            listaDetalleCarga = new ArrayList<>();
            getObjeto().setId(id);
            super.setAccion(ACCION_ADICIONAL_1);
            getCargaMasivaServicio().Accion(this);
            // adicionamos encabezado en la primera línea
            switch(getObjeto().getTipo()) {
                case AfiliadoParametro.TIPO_ARCHIVO_AFILIADOS:
                    texto = AfiliadoParametro.ENCABEZADO_IMPRESION_ARCHIVO_AFILIADOS;
                    break;
                case AfiliadoParametro.TIPO_ARCHIVO_NOVEDADES:
                    texto = AfiliadoParametro.ENCABEZADO_IMPRESION_ARCHIVO_NOVEDADES;
                    break;
                case AfiliadoParametro.TIPO_ARCHIVO_NOVEDADES_V2:
                    texto = AfiliadoParametro.ENCABEZADO_IMPRESION_ARCHIVO_NOVEDADES_V2;
                    break;
            }
            
            if(getListaDetalleCarga().size() > 0){
                //Stream streamDetalle = this.listaDetalleCarga.stream();
                for (AsegDetalleCarga dc: this.listaDetalleCarga) {
                    texto = texto + dc.toString();
                }
                // construimos el nombre del archivo
                indiceExtension = this.objeto.getArchivo().lastIndexOf(".");
                extension = this.objeto.getArchivo().substring(indiceExtension, this.objeto.getArchivo().length());
                nombre = this.objeto.getArchivo().substring(0, indiceExtension) + "_" + this.objeto.getId() + extension;
                //debemos generar la cadena de bytes a partir de los registros en la lista
                byte[] bytes = texto.getBytes();
                //byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
            }else{
                addError("No se encontraron datos para generar el archivo");
                generarMensajes();
                PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
                PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }

    /**
     * @return the listaDetalleCarga
     */
    public List<AsegDetalleCarga> getListaDetalleCarga() {
        return listaDetalleCarga;
    }

    /**
     * @param listaDetalleCarga the listaDetalleCarga to set
     */
    public void setListaDetalleCarga(List<AsegDetalleCarga> listaDetalleCarga) {
        this.listaDetalleCarga = listaDetalleCarga;
    }
    
    public void cargarDato() {
        
    }

}
