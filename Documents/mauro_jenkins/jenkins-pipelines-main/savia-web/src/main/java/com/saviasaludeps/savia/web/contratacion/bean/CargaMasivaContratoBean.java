/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCargaSuceso;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.file.UploadedFile;
import com.saviasaludeps.savia.web.contratacion.servicio.CargaMasivaContratoIface;
import com.saviasaludeps.savia.web.contratacion.servicio.CargaMasivaContratoImpl;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jose Perez
 */
@ManagedBean
@ViewScoped
public class CargaMasivaContratoBean extends Url {

    private CargaMasivaContratoIface cargaMasivaContratoServicio;
    private CntContratoCarga objeto;
    private List<CntContratoCarga> registros;
    private LazyDataModel<CntContratoCarga> lazyCargaMasiva;
    
    private List<CntContratoCargaSuceso> listaDetalleCarga;

    public CargaMasivaContratoBean() {
        this.objeto = new CntContratoCarga();
        Modulo mod = super.validarModulo(Modulo.ID_CONTRATACION_CONTRATOS_CARGA_MASIVA);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyCargaMasiva = new LazyDataModel<CntContratoCarga>() {
                private List<CntContratoCarga> listaCargas;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<CntContratoCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CntContratoCarga portabilidad) {
                    return portabilidad.getId().toString();
                }

                @Override
                public CntContratoCarga getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (CntContratoCarga portabilidad : listaCargas) {
                        if (id.equals(portabilidad.getId())) {
                            return portabilidad;
                        }
                    }
                    return null;
                }

            };
        }
    }

    public CargaMasivaContratoIface getCargaMasivaContratoServicio() {
        if (cargaMasivaContratoServicio == null) {
            cargaMasivaContratoServicio = new CargaMasivaContratoImpl();
        }
        return cargaMasivaContratoServicio;
    }

    public void setCargaMasivaContratoServicio(CargaMasivaContratoIface cargaMasivaContratoServicio) {
        this.cargaMasivaContratoServicio = cargaMasivaContratoServicio;
    }

    public CntContratoCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(CntContratoCarga objeto) {
        this.objeto = objeto;
    }

    public List<CntContratoCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntContratoCarga> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntContratoCarga> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<CntContratoCarga> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    /**
     * @return the listaDetalleCarga
     */
    public List<CntContratoCargaSuceso> getListaDetalleCarga() {
        return listaDetalleCarga;
    }

    /**
     * @param listaDetalleCarga the listaDetalleCarga to set
     */
    public void setListaDetalleCarga(List<CntContratoCargaSuceso> listaDetalleCarga) {
        this.listaDetalleCarga = listaDetalleCarga;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaMasivaContratoServicio().Accion(this);
        procesoFinal();
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
         try {
             if (this.objeto.getTipo() != 0 ) {
                UploadedFile archivo = event.getFile();
                this.objeto.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                this.objeto.setNombreArchivo(nombreAdjunto);
                // llamamos al guardar
                this.guardar();
            } else {
                this.addError("Tipo Archivo: Este campo es obligatorio.");
                procesoFinal();
            }
         } catch (IOException ex) {
             addError(ex.toString());
         }
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCargaMasivaContratoServicio().Accion(this);
        this.objeto.setTipo(CntContratoCarga.TIPO_CONTRATO_DETALLE);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void guardar() {
        if (this.objeto.getAdjuntoStream() != null) {
            super.setAccion(ACCION_GUARDAR);
            getCargaMasivaContratoServicio().Accion(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
        } else {
            this.addError("No se ha cargado el archivo.");
        }
        procesoFinal();
    }

    public String getEstado(int tipo) {
        String descripcion;
        switch (tipo) {
            case ContratacionParametro.ESTADO_EN_COLA:
                descripcion = "En Cola";
                break;
            case ContratacionParametro.ESTADO_PROCESANDO:
                descripcion = "En Proceso";
                break;
            case ContratacionParametro.ESTADO_PROCESADO:
                descripcion = "Procesado";
                break;
            case ContratacionParametro.ESTADO_ABORTADO:
                descripcion = "Cancelado";
                break;
            default:
                descripcion = "";
                break;
        }
        return descripcion;
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
                crearLog("Descargar",getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog("Cancelar",getObjeto().toString());
                PrimeFaces.current().ajax().update("frmCargaMasiva");
                break;
        }
        generarMensajes();
    }

    public void abortar(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        this.objeto = new CntContratoCarga(id);
        getCargaMasivaContratoServicio().Accion(this);
        procesoFinal();
    }

    public StreamedContent generarArchivoResultados(CntContratoCarga cont) {
        StreamedContent streamedContent2 = null;
        int indiceExtension;
        String extension;
        String nombre;
        //por defecto configuramos contrato detalle
        String texto = ContratacionParametro.ENCABEZADO_IMPRESION_CONTRATO_DETALLE;
        try {
            setListaDetalleCarga(new ArrayList<>());
            this.objeto = new CntContratoCarga(cont.getId());
            //getObjeto().setId(id);
            super.setAccion(ACCION_ADICIONAL_1);
            getCargaMasivaContratoServicio().Accion(this);
            // adicionamos encabezado en la primera línea
            //2022-07-18 jyperez validamos el tipo de archivo para poner el encabezado correcto
            switch(cont.getTipo()) {
                case CntContratoCarga.TIPO_NOTA_TECNICA:
                    texto = ContratacionParametro.ENCABEZADO_IMPRESION_NOTA_TECNICA;
                break;
                case CntContratoCarga.TIPO_COBERTURA:
                    texto = ContratacionParametro.ENCABEZADO_IMPRESION_COBERTURA;
                break;
                case CntContratoCarga.TIPO_MARCACION:
                    texto = ContratacionParametro.ENCABEZADO_IMPRESION_MARCACION;
                break;
                case CntContratoCarga.TIPO_REPS_PRESTADORES:
                    texto = ContratacionParametro.ENCABEZADO_IMPRESION_ARCHIVO_REPS_PRESTADORES;
                break;
            }
            if(getListaDetalleCarga().size() > 0){
                //Stream streamDetalle = this.listaDetalleCarga.stream();
                for (CntContratoCargaSuceso dc: this.getListaDetalleCarga()) {
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
            }
        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }

    public boolean validarEstadoCarga(int estado) {
        boolean deshabilitar = true;
        if (estado == ContratacionParametro.ESTADO_PROCESADO || estado == ContratacionParametro.ESTADO_ABORTADO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }

    public boolean validarEstadoCargaProcesando(int estado) {
        boolean deshabilitar = true;
        if (estado == ContratacionParametro.ESTADO_PROCESANDO) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public void cargarDato() {
        
    }

}
