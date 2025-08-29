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
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.aseguramiento.servicio.Anexo1ServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.seleccion.bean.SelAfiliadoBean;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class Anexo1Bean extends Url {

    @Autowired
    private Anexo1ServicioIface anexo1Servicio;
    private AsegAnexo1 objeto;
    private AsegAfiliado objetoAfiliado;
    private AsegAnexo1Adjunto objetoAdjunto;
    private List<AsegAnexo1Adjunto> listaAsegAnexo1Adjuntos;
    
    private List<AsegAnexo1> registros;
    private LazyDataModel<AsegAnexo1> lazyAnexo1;
    
    //maestros
    private List<Maestro> listaTipoDocumento;
    private HashMap<String, Maestro> hashTipoDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumentoPersona;
    private List<Maestro> listaTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private List<Maestro> listaTiposGenero;
    
    //buscador de afiliados
    private SelAfiliadoBean selAfiliadoBean;
    
    //autocompletar ubicación
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Ubicacion> listaUbicaciones;
    
    //autocompletar prestador_sedes
    private List<CntPrestadorSede> listaCntPrestadorSedes;
    private boolean sedeRequerida;
    
    //adjuntos
    private String nombreDocumento;
    private boolean mostrarBorrarDocumento;
    private UploadedFile archivoAdjunto;
    
    private HashMap<String, Maestro> hashValorTiposContacto;
    
    private transient InputStream adjuntoStream;
    
    private Date fechaMaximaCalendario;
    private Date fechaMinimaCalendario;
    
//    @Autowired
//    private AseguramientoSingle aseguramientoSingle;
//    
    public Anexo1Bean() {
        //obtenemos la fecha maxima del calendario, que seria la fecha actual.
        fechaMaximaCalendario = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaMaximaCalendario);
        //primero actualizamos la fecha maxima a un día siguiente, para que puedan buscar los del día de hoy
        calendar.add(Calendar.DAY_OF_YEAR,1);
        fechaMaximaCalendario = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -91);
        //la fecha mínima debe ser, 3 meses anteriores a la fecha actual ( le quito igual el día que le sumé
        fechaMinimaCalendario = calendar.getTime();
        this.objeto = new AsegAnexo1();
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_ANEXO1);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //inicializamos los parámetros de entrada. Se envia el id de la empresa del usuario.
            //
            if (super.getConexion().getEmpresa().getId() != AfiliadoParametro.ID_SAVIA_SALUD) {
                // validamos si el empleado puede consultar para todas las empresas. Si es así no se enviará
                // el id de la empresa
                if (!super.isAccionAdicional1()) {
                    getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
                }
            }
            
            
            lazyAnexo1 = new LazyDataModel<AsegAnexo1>() {
                private List<AsegAnexo1> listaAnexos1;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<AsegAnexo1> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaAnexos1 = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaAnexos1;
                }

                @Override
                public String getRowKey(AsegAnexo1 anexo1) {
                    return anexo1.getId().toString();
                }

                @Override
                public AsegAnexo1 getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (AsegAnexo1 anexo1 : listaAnexos1) {
                        if (id.equals(anexo1.getId())) {
                            return anexo1;
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
        getAnexo1Servicio().cargaInicial(this);
        listar();
    }

    public Anexo1ServicioIface getAnexo1Servicio() {
        return anexo1Servicio;
    }

    public void setAnexo1Servicio(Anexo1ServicioIface anexo1Servicio) {
        this.anexo1Servicio = anexo1Servicio;
    }

    public AsegAnexo1 getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAnexo1 objeto) {
        this.objeto = objeto;
    }

    public List<AsegAnexo1> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegAnexo1> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegAnexo1> getLazyAnexo1() {
        return lazyAnexo1;
    }

    public void setLazyAnexo1(LazyDataModel<AsegAnexo1> lazyAnexo1) {
        this.lazyAnexo1 = lazyAnexo1;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAnexo1Servicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getAnexo1Servicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAfiliado");
        PrimeFaces.current().ajax().update("frmVerAnexo1");
        PrimeFaces.current().ajax().update("frmVerAdjunto");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        //2023-03-26 jyperez validamos si el usuario es savia o es empresa
        if (super.getConexion().getEmpresa().getId() == AfiliadoParametro.ID_SAVIA_SALUD) {
            this.sedeRequerida = false;
        } else {
            this.sedeRequerida = true;
        }
        getAnexo1Servicio().Accion(this);
        PrimeFaces.current().resetInputs("frmConsultarAfiliado");
        PrimeFaces.current().ajax().update("frmConsultarAfiliado");
        PrimeFaces.current().resetInputs("frmCrearAnexo1");
        PrimeFaces.current().ajax().update("frmCrearAnexo1");
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getAnexo1Servicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getAnexo1Servicio().Accion(this);
        PrimeFaces.current().resetInputs("frmInformacionAfiliado");
        PrimeFaces.current().ajax().update("frmInformacionAfiliado");
        PrimeFaces.current().resetInputs("frmEditarAnexo1");
        PrimeFaces.current().ajax().update("frmEditarAnexo1");
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAnexo1Servicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {

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
                PrimeFaces.current().ajax().update("frmAnexo1");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                crearLog("Imprimir",getObjeto().toString());
                PrimeFaces.current().ajax().update("frmAnexo1");
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
            default:
                descripcion = "";
            break;
        }
        
        return descripcion;
    }
    
    public String getEstado(int tipo) {
        String descripcion;
        switch(tipo) {
            case AfiliadoParametro.ESTADO_PENDIENTE:
                descripcion = "Pendiente";
            break;
            case AfiliadoParametro.ESTADO_GESTIONADO:
                descripcion = "Gestionado";
            break;
            case AfiliadoParametro.ESTADO_RECHAZADO:
                descripcion = "Rechazado";
            break;
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }

    public String getEstadoInconsistencia(int tipo) {
        String descripcion;
        switch(tipo) {
            case AfiliadoParametro.ESTADO_INCONSISTENCIA_NO_APLICA:
                descripcion = "No Aplica";
            break;
            case AfiliadoParametro.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION:
                descripcion = "Pendiente de Gestión";
            break;
            case AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO:
                descripcion = "Aceptado";
            break;
            case AfiliadoParametro.ESTADO_INCONSISTENCIA_RECHAZADO:
                descripcion = "Rechazado";
            break;
            default:
                descripcion = "";
            break;
        }
        return descripcion;
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
                UploadedFile archivo = event.getFile();
                //this.objeto.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                //int indiceExtension = nombreAdjunto.lastIndexOf(".");
                //String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
                //this.objeto.setNombre(nombreAdjunto);
                // llamamos al guardar
                this.guardar();

                //this.addError("Tipo Archivo: Este campo es obligatorio.");
                procesoFinal();

        } catch (Exception ex) {
            Logger.getLogger(Anexo1Bean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public StreamedContent imprimirReporteAnexo(AsegAnexo1 obj) {
        StreamedContent streamedContent = null;
        try {
            setAdjuntoStream(null);
            this.objeto =obj;
            super.setAccion(ACCION_ADICIONAL_2);
            getAnexo1Servicio().Accion(this);
            //los datos del reporte, asi como los del streamContent se llenaran en el impl
            if(getAdjuntoStream() != null){
                InputStream stream = getAdjuntoStream();
                stream.mark(0); //remember to this position!
                streamedContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("reporte.pdf" ).build();
            }else{
                addError("No se encontraron datos para generar el archivo");
                generarMensajes();
                PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
                PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
            }
        } catch (Exception ex) {
            streamedContent = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent;
    }
    
    public boolean validarEstadoGestion(int estado) {
        boolean deshabilitar = true;
        if (estado == AfiliadoParametro.ESTADO_PENDIENTE) {
            deshabilitar = false;
        }
        return deshabilitar;
    }
    
    public String getTipoDocumento(String codigo){
        String mensaje = "";
        try {
            mensaje = hashTipoDocumento.get(codigo).getNombre();
        }catch (Exception e) {
            
        }
        return mensaje;
    }
    
    public void consultarAfiliado() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmAfiliadoBusqueda");

        } catch (Exception ex) {
            setObjetoAfiliado(new AsegAfiliado());// para que??
            //addError(ex.getMessage());
        }
    }
    
    public void cerrarDialogoAfiliado() {
        // se adiciona el afiliado al objetoAfiliado, que es el que cargará los datos que se muestran.
        this.objetoAfiliado = getSelAfiliadoBean().getObjeto();
        //se realiza cargue en el objeto Anexo1, los datos que pueden modificarse
        //this.objeto = new AsegAnexo1();
        try{
            this.objeto.setTipoDocumentoNuevo(hashTiposDocumentoPersona.get(this.objetoAfiliado.getMaeTipoDocumento()).getValor());        
        } catch (Exception ex) {
            this.objeto.setTipoDocumentoNuevo(AfiliadoParametro.TIPO_DOCUMENTO_POR_DEFECTO);
        }
        this.objeto.setNumeroDocumentoNuevo(this.objetoAfiliado.getNumeroDocumento());
        this.objeto.setNombre1Nuevo(this.objetoAfiliado.getPrimerNombre());
        this.objeto.setNombre2Nuevo(this.objetoAfiliado.getSegundoNombre());
        this.objeto.setApellido1Nuevo(this.objetoAfiliado.getPrimerApellido());
        this.objeto.setApellido2Nuevo(this.objetoAfiliado.getSegundoApellido());
        
        this.objeto.setFechaNacimientoNuevo(this.objetoAfiliado.getFechaNacimiento());
        this.objeto.setSexoNuevo(this.objetoAfiliado.getGenero());
        this.objeto.setFechaExpedicionCedulaNuevo(this.objetoAfiliado.getFechaExpedicionCedula());
        this.objeto.setDireccionNuevo(this.objetoAfiliado.getDireccionResidencia());
        this.objeto.setTelefonoNuevo(this.objetoAfiliado.getTelefonoFijo());
        this.objeto.setCelularNuevo(this.objetoAfiliado.getTelefonoMovil());
        //actualizamos el objeto de selección
        getSelAfiliadoBean().setObjeto(new AsegAfiliado());
        //actualizamos los formularios y los paneles que deben actualizarse
        PrimeFaces.current().ajax().update("frmConsultarAfiliado:pnlAfiliado");
        PrimeFaces.current().ajax().update("frmCrearAnexo1:pnlAnexo1");
        //cerramos la ventana
        PrimeFaces.current().executeScript("PF('dialogoAfiliadoBusqueda').hide()");
    }

    public String obtenerTipoDocumentoPersona(Integer id) {
        try {
            return getHashTiposDocumentoPersona().get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String obtenerTipoGenero(String id) {
        try {
            return hashTiposGenero.get(Integer.parseInt(id)).getNombre();
        } catch (NumberFormatException e) {
            return id;
        }
    }
    
    public void abrirAdjuntos() {
        setObjetoAdjunto(new AsegAnexo1Adjunto());
        setNombreDocumento("");
        setMostrarBorrarDocumento(false);
        PrimeFaces.current().resetInputs(":frmAdjunto");
        PrimeFaces.current().ajax().update(":frmAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
    }
    
    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        setArchivoAdjunto(event.getFile());
        objetoAdjunto.setAdjuntoStream(getArchivoAdjunto().getInputStream());
        this.mostrarBorrarDocumento = true;
        nombreDocumento = getArchivoAdjunto().getFileName();
    }
    
    public void borrarArchivo() {
        archivoAdjunto = null;
        this.mostrarBorrarDocumento = false;
        nombreDocumento = "";
    }
    
    public void agregarArchivo() {
        try {

            AsegAnexo1Adjunto borrarObj = new AsegAnexo1Adjunto();
            getListaAsegAnexo1Adjuntos().remove(borrarObj);

            String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".") + 1;
            objetoAdjunto.setExtensión(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objetoAdjunto.setNombre(nombreAdjunto.substring(0, indiceExtension));
            objetoAdjunto.setFechaHoraCrea(new Date());
            getListaAsegAnexo1Adjuntos().add(objetoAdjunto);
            objetoAdjunto = new AsegAnexo1Adjunto();
            
            PrimeFaces.current().ajax().update("frmCrearAdjunto:tablaAdjuntosCrear");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide()");
        } catch (Exception ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    public void borrarAdjunto(AsegAnexo1Adjunto adjunto) {
        listaAsegAnexo1Adjuntos.remove(adjunto);
        PrimeFaces.current().ajax().update("frmCrearAdjunto:tablaAdjuntosCrear");
    }
    
    public StreamedContent descargarAdjunto(AsegAnexo1Adjunto adjunto) {
        StreamedContent descarga = null;
        try {
            String archivo = adjunto.getRuta() + adjunto.getArchivo();
            Path path = Paths.get(archivo);
            byte[] bytes = Files.readAllBytes(path);
            InputStream stream = new ByteArrayInputStream(bytes);
            stream.mark(0); //remember to this position!
            descarga = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(adjunto.getArchivo()).build();
        } catch (IOException ex) {
            descarga = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            addError("Ocurrió un error descargando el archivo desde la ruta especificada.");
            generarMensajes();
        } catch (Exception ex) {
            descarga = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            addError("Ocurrió un error descargando el archivo desde la ruta especificada.");
            generarMensajes();
        }
        procesoFinal();
        return descarga;
    }
    
    public String getSexo(int id) {
        String mensaje = "";
        try {
            mensaje = hashTiposGenero.get(id).getNombre();
        }catch (Exception ex) {
            
        }
        return mensaje;
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
            getObjeto().setResidenciaUbicacionNuevo(ubicacionesFiltradas.get(0));
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
    
    /**
     * Metodo que retona el nombre de la ubicacion a partir de un Id
     *
     * @param id
     * @return
     */
    public String getUbicacionStr(int id) {
        String ubicacionStr = "";
        Ubicacion municipio = null;
        if (getHashUbicaciones() != null) {
            municipio = getHashUbicaciones().get(id);
        }
        if (municipio != null ) {
            ubicacionStr = municipio.getNombre();
        }
        return ubicacionStr;
    }
    
    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<String, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<String, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }
    
    public void cargarDato() {
        
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
     * @return the fechaMaximaCalendario
     */
    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    /**
     * @param fechaMaximaCalendario the fechaMaximaCalendario to set
     */
    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
    }

    /**
     * @return the fechaMinimaCalendario
     */
    public Date getFechaMinimaCalendario() {
        return fechaMinimaCalendario;
    }

    /**
     * @param fechaMinimaCalendario the fechaMinimaCalendario to set
     */
    public void setFechaMinimaCalendario(Date fechaMinimaCalendario) {
        this.fechaMinimaCalendario = fechaMinimaCalendario;
    }

    /**
     * @return the objetoAfiliado
     */
    public AsegAfiliado getObjetoAfiliado() {
        return objetoAfiliado;
    }

    /**
     * @param objetoAfiliado the objetoAfiliado to set
     */
    public void setObjetoAfiliado(AsegAfiliado objetoAfiliado) {
        this.objetoAfiliado = objetoAfiliado;
    }

    /**
     * @return the hashTiposDocumentoPersona
     */
    public HashMap<Integer, Maestro> getHashTiposDocumentoPersona() {
        return hashTiposDocumentoPersona;
    }

    /**
     * @param hashTiposDocumentoPersona the hashTiposDocumentoPersona to set
     */
    public void setHashTiposDocumentoPersona(HashMap<Integer, Maestro> hashTiposDocumentoPersona) {
        this.hashTiposDocumentoPersona = hashTiposDocumentoPersona;
    }

    /**
     * @return the listaTiposDocumentoPersona
     */
    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    /**
     * @param listaTiposDocumentoPersona the listaTiposDocumentoPersona to set
     */
    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    /**
     * @return the hashTiposGenero
     */
    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    /**
     * @param hashTiposGenero the hashTiposGenero to set
     */
    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    /**
     * @return the listaTiposGenero
     */
    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    /**
     * @param listaTiposGenero the listaTiposGenero to set
     */
    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
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
     * @return the objetoAdjunto
     */
    public AsegAnexo1Adjunto getObjetoAdjunto() {
        return objetoAdjunto;
    }

    /**
     * @param objetoAdjunto the objetoAdjunto to set
     */
    public void setObjetoAdjunto(AsegAnexo1Adjunto objetoAdjunto) {
        this.objetoAdjunto = objetoAdjunto;
    }

    /**
     * @return the listaAsegAnexo1Adjuntos
     */
    public List<AsegAnexo1Adjunto> getListaAsegAnexo1Adjuntos() {
        return listaAsegAnexo1Adjuntos;
    }

    /**
     * @param listaAsegAnexo1Adjuntos the listaAsegAnexo1Adjuntos to set
     */
    public void setListaAsegAnexo1Adjuntos(List<AsegAnexo1Adjunto> listaAsegAnexo1Adjuntos) {
        this.listaAsegAnexo1Adjuntos = listaAsegAnexo1Adjuntos;
    }

    /**
     * @return the nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * @param nombreDocumento the nombreDocumento to set
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * @return the mostrarBorrarDocumento
     */
    public boolean isMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    /**
     * @param mostrarBorrarDocumento the mostrarBorrarDocumento to set
     */
    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    /**
     * @return the archivoAdjunto
     */
    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    /**
     * @param archivoAdjunto the archivoAdjunto to set
     */
    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    /**
     * @return the hashValorTiposContacto
     */
    public HashMap<String, Maestro> getHashValorTiposContacto() {
        return hashValorTiposContacto;
    }

    /**
     * @param hashValorTiposContacto the hashValorTiposContacto to set
     */
    public void setHashValorTiposContacto(HashMap<String, Maestro> hashValorTiposContacto) {
        this.hashValorTiposContacto = hashValorTiposContacto;
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

    /**
     * @return the hashUbicaciones
     */
    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    /**
     * @param hashUbicaciones the hashUbicaciones to set
     */
    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    /**
     * @return the listaUbicaciones
     */
    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    /**
     * @param listaUbicaciones the listaUbicaciones to set
     */
    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    /**
     * @return the listaCntPrestadorSedes
     */
    public List<CntPrestadorSede> getListaCntPrestadorSedes() {
        return listaCntPrestadorSedes;
    }

    /**
     * @param listaCntPrestadorSedes the listaCntPrestadorSedes to set
     */
    public void setListaCntPrestadorSedes(List<CntPrestadorSede> listaCntPrestadorSedes) {
        this.listaCntPrestadorSedes = listaCntPrestadorSedes;
    }

    /**
     * @return the sedeRequerida
     */
    public boolean isSedeRequerida() {
        return sedeRequerida;
    }

    /**
     * @param sedeRequerida the sedeRequerida to set
     */
    public void setSedeRequerida(boolean sedeRequerida) {
        this.sedeRequerida = sedeRequerida;
    }

}
