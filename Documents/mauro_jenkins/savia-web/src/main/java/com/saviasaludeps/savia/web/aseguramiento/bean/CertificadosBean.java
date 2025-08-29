/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegDetalleCarga;
import com.saviasaludeps.savia.web.aseguramiento.servicio.CertificadosServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
//import com.saviasaludeps.savia.web.singleton.AseguramientoSingle;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class CertificadosBean extends Url {

    @Autowired
    private CertificadosServicioIface certificadoServicio;
    private AsegAfiliadoCertificado objeto;
    private List<AsegAfiliadoCertificado> registros;
    private LazyDataModel<AsegAfiliadoCertificado> lazyCertificados;
    
    private List<AsegDetalleCarga> listaDetalleCarga;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;

    //maestros
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    
//    @Autowired
//    private AseguramientoSingle aseguramientoSingle;

    public CertificadosBean() {
        this.objeto = new AsegAfiliadoCertificado();
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_CERTIFICADOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCertificados = new LazyDataModel<AsegAfiliadoCertificado>() {
                private List<AsegAfiliadoCertificado> listaCertificados;

                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
                @Override
                public List<AsegAfiliadoCertificado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaCertificados = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaCertificados;
                }

                @Override
                public String getRowKey(AsegAfiliadoCertificado certificado) {
                    return certificado.getId().toString();
                }

                @Override
                public AsegAfiliadoCertificado getRowData(String certificadoId) {
                    Integer id = Integer.valueOf(certificadoId);
                    for (AsegAfiliadoCertificado portabilidad : listaCertificados) {
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
        getCertificadoServicio().cargaInicial(this);
        listar();
    }

    public CertificadosServicioIface getCertificadoServicio() {
        return certificadoServicio;
    }

    public void setCertificadoServicio(CertificadosServicioIface certificadoServicio) {
        this.certificadoServicio = certificadoServicio;
    }

    public AsegAfiliadoCertificado getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAfiliadoCertificado objeto) {
        this.objeto = objeto;
    }

    public List<AsegAfiliadoCertificado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegAfiliadoCertificado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegAfiliadoCertificado> getLazyCertificados() {
        return lazyCertificados;
    }

    public void setLazyCertificados(LazyDataModel<AsegAfiliadoCertificado> lazyCertificados) {
        this.lazyCertificados = lazyCertificados;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCertificadoServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCertificadoServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getCertificadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCertificadoServicio().Accion(this);
        /*PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");*/
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCertificadoServicio().Accion(this);
        /*PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");*/
        procesoFinal();
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

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                break;
            case Url.ACCION_CREAR:
                break;
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
                break;
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCertificados");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog("Imprimir Certificado",getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                break;
        }
        generarMensajes();
    }
        
    public String getEstado(int tipo) {
        String descripcion;
        switch(tipo) {
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }
    
    public StreamedContent generarArchivoResultados(AsegAfiliadoCertificado obj) {
        StreamedContent streamedContent = null;
        this.objeto = obj;
        super.setAccion(ACCION_ADICIONAL_1);
        try {
            String archivo = obj.getRuta() + obj.getNombreArchivo();
            Path path = Paths.get(archivo);
            byte[] bytes = Files.readAllBytes(path);
            InputStream stream = new ByteArrayInputStream(bytes);
            stream.mark(0); //remember to this position!
            streamedContent =DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(obj.getNombreArchivo()).build(); 
        } catch (IOException ex) {
            streamedContent = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            addError("Ocurrió un error descargando el archivo desde la ruta especificada.");
            generarMensajes();
        } catch (Exception ex) {
            streamedContent = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            addError("Ocurrió un error descargando el archivo desde la ruta especificada.");
            generarMensajes();
        }
        procesoFinal();
        return streamedContent;
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

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }
    
    public void cargarDato() {
        
    }

    /**
     * @return the listaTiposDocumento
     */
    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    /**
     * @param listaTiposDocumento the listaTiposDocumento to set
     */
    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    /**
     * @return the hashTiposDocumento
     */
    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    /**
     * @param hashTiposDocumento the hashTiposDocumento to set
     */
    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }
    
    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @return the listaEstadosAfiliacion
     */
    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    /**
     * @param listaEstadosAfiliacion the listaEstadosAfiliacion to set
     */
    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    /**
     * @return the hashEstadosAfiliacion
     */
    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    /**
     * @param hashEstadosAfiliacion the hashEstadosAfiliacion to set
     */
    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }
    
    /**
     * Función para obtener el identificador del registro de EstadoAfiliación
     *
     * @param id
     * @return
     */
    public String getEstadoAfiliacion(Integer id) {
        try {
            return getHashEstadosAfiliacion().get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getTipo(int id) {
        String mensaje = "";
        switch(id) {
            case AfiliadoParametro.TIPO_CERTIFICADO_AFILIACION:
                mensaje = "Certificado de Afiliación";
            break;
            case AfiliadoParametro.TIPO_CERTIFICADO_PORTABILIDAD:
                mensaje = "Certificado de Portabilidad";
            break;
            case AfiliadoParametro.TIPO_CERTIFICADO_FISCALIA:
                mensaje = "Certificado Fiscalia";
            break;
        }
        
        return mensaje;
    }
    
    public boolean deshabilitarVigenciaCertificado(Date fechaFinVigencia) {
        boolean resultado = true;
        Date fechaActual = new Date();
        if (fechaFinVigencia.compareTo(fechaActual) > 0) {
            resultado = false;
        }
        return resultado;
    };

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
