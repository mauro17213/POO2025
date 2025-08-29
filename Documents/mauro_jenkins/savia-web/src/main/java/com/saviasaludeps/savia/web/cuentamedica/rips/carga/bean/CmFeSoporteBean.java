package com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporteAdjunto;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio.CmFeSoporteServicioIface;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmUtilidades;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class CmFeSoporteBean extends Url {

    public static final char DO_ACCION_VER_CARGAS_PARA_SOPORTE = 'a';

    private CmFeSoporteServicioIface cmFeSoporteServicio;
    private CmFeSoporte objeto;
    private HashMap<String, Maestro> hashTipoSoporte;
    private List<CmFeSoporte> listaSoportes = new ArrayList<>();
    private List<CmFeSoporteAdjunto> listaSoportesAdjunto = new ArrayList<>();
    private Map<String, CmFeRipsCarga> listaCargasParaSoporte = new HashMap<>();
    private Map<String, String> mapNitFacturaUnicos = new HashMap<>();
    private LazyDataModel<CmFeSoporte> lazyCmSoportes;
    private List<CmFeSoporte> registros;
    

    @PreDestroy
    public void preDestroy() {
        this.objeto = null;
    }

    public CmFeSoporteBean() {
        this.objeto = new CmFeSoporte();
        Modulo _mod = super.validarModulo(Modulo.ID_CM_FE_RIPS_CARGA_SOPORTES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCmSoportes= new LazyDataModel<CmFeSoporte>() {

                private List<CmFeSoporte> lista;

                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			
                @Override
                public List<CmFeSoporte> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmFeSoporte objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmFeSoporte getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmFeSoporte objeto : lista) {
                        if (id.equals(objeto.getId())) {
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
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getCmFeSoporteServicio().cargaInicial(this);
        listar();
    }
    
     public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }
     
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmFeSoporteServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCmFeSoporteServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCargar");
        PrimeFaces.current().ajax().update("frmCargar");
        PrimeFaces.current().executeScript("PF('dialogoCargar').show()");
        procesoFinal();
    }

    public void guardar() {

        boolean esValido = validarExistenciaSoporteAdjuntos();
        esValido = esValido ? validarDatosZip(getListaSoportesAdjunto()) : false;

        if (esValido  && !isError()) {
            super.setAccion(ACCION_GUARDAR);
            getCmFeSoporteServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCargar').hide()");
            }
        }
        procesoFinal();
    }


    public void editar(int id) {
        procesoFinal();
    }

    public void ver(int id) { 
        procesoFinal();
    }
    

   public void modificar() {
        procesoFinal();
    }
    public void borrar(int _id) {
    }
    
    public void adicionarAdjunto(FileUploadEvent event) throws IOException {
        try {
            UploadedFile archivoCarga = event.getFile();
            String nombre = archivoCarga.getFileName();
            InputStream inputStream = archivoCarga.getInputStream();            
            CmFeSoporteAdjunto adjNuevo = crearSoperteAdjunto(nombre,  inputStream); 
            getListaSoportesAdjunto().add(adjNuevo);
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar agregar un anexo: " + CmUtilidades.obtenerErrorStrFormateado(e));
            generarMensajes();
        }
    }
    
    public void retirarAdjunto(String nombreArchivo) {
        if (!"".equals(nombreArchivo)) {
            for (int i = 0; i < getListaSoportesAdjunto().size(); i++) {
                CmFeSoporteAdjunto adj = getListaSoportesAdjunto().get(i);
                if (adj.getIdInsertar() != null && adj.getArchivoNombre() == nombreArchivo) {
                    getListaSoportesAdjunto().remove(i);
                    break;
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearFactura:tablaAdjuntos");
    }
    
    private CmFeSoporteAdjunto crearSoperteAdjunto(String nombreArchivo, InputStream inputStream) throws Exception {
        CmFeSoporteAdjunto adjNuevo = new CmFeSoporteAdjunto();
        try {
            String ruta = PropApl.getInstance().get(PropApl.CM_FE_RUTA_SOPORTE_CARGA);
            if (ruta == null || "".equals(ruta)) {
                throw new Exception("Error: La ruta para realizar el almacenamiento de adjuntos no está definida.");
            }

            String extensionArchivo = CmUtilidades.obtenerExtensionArchivo(nombreArchivo);

            adjNuevo = new CmFeSoporteAdjunto(nombreArchivo, ruta, inputStream);
            adjNuevo.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
            adjNuevo.setTerminalCrea(getConexion().getIp());
            adjNuevo.setFechaHoraCrea(new Date());
            adjNuevo.setArchivoNombreOriginal(nombreArchivo);
            adjNuevo.setIdInsertar(1);
            adjNuevo.setExtension(extensionArchivo.toUpperCase());
        } catch (Exception ex) {
            throw new Exception(" crearSoperteAdjunto  : " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
        return adjNuevo;
    }
    
    private CmFeSoporte crearSoporte(String nombreArchivo, InputStream inputStream) throws Exception {
        CmFeSoporte soporte = new CmFeSoporte();
        try {
            String ruta = PropApl.getInstance().get(PropApl.CM_FE_RUTA_SOPORTE_CARGA);
            if (ruta == null || "".equals(ruta)) {
                throw new Exception("Error: La ruta para realizar el almacenamiento de adjuntos no está definida.");
            }

            Matcher matcherMonoUsuario = CmUtilidades.NOMBRAMIENTO_SOPORTE_MONO_USUARIO.matcher(nombreArchivo);
            Matcher matcherMultiUsuario = CmUtilidades.NOMBRAMIENTO_SOPORTE_MULTI_USUARIO.matcher(nombreArchivo);
            Matcher matcherSoporte = matcherMonoUsuario.matches() ? matcherMonoUsuario : matcherMultiUsuario;

            String prefijoTipo = "";
            if (matcherSoporte.matches()) {
                prefijoTipo = matcherSoporte.group("prefijo");
                soporte.setNitFactura(matcherSoporte.group("nit") + "_" + matcherSoporte.group("factura"));
            }
            soporte.setArchivoRuta(ruta);
            soporte.setArchivoNombre(nombreArchivo);
            soporte.setInputStream(inputStream);

            Maestro maestroTipo = getHashTipoSoporte().get(prefijoTipo);
            if (maestroTipo != null) {
                soporte.setMaeTipoSoporteCodigo(maestroTipo.getValor());
                soporte.setMaeTipoSoporteValor(maestroTipo.getNombre());
                soporte.setMaeTipoSoporteId(maestroTipo.getId());
            }
            soporte.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
            soporte.setTerminalCrea(getConexion().getIp());
            soporte.setFechaHoraCrea(new Date());
            soporte.generarNombreArchivo(nombreArchivo);
            soporte.setEmpresa(new Empresa(this.getConexion().getEmpresa().getId()));
            soporte.setMultiUsuario(matcherMultiUsuario.matches());
            
        } catch (Exception ex) {
            throw new Exception(" crearSoporte  : " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
        return soporte;
    }


    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog("Guardar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsSoportes");
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog("Modificar", getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_LISTAR:
                     crearLog("Listar", getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    crearLog("Ver", getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    crearLog("Crear", getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(" Editar", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2: 
                    break;
                case Url.ACCION_ADICIONAL_3:;
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                case Url.ACCION_ADICIONAL_5:
                    break;
            }
        }
        generarMensajes();
    }
    
    private boolean validarExistenciaSoporteAdjuntos() {
        if(getListaSoportesAdjunto().isEmpty()){
          addError("Debe agregar archivos para realizar la carga de soportes.");
          return false;
        }
        return true;
    }
   
    private boolean validarDatosZip(List<CmFeSoporteAdjunto> adjuntos) {

        try {
            if (adjuntos.isEmpty()) {
                addError("Debe agregar archivos para realizar la carga de soportes.");
                return false;
            }
            CmFeSoporteAdjunto adjunto = adjuntos.get(0);
            InputStream inputStreamZip = CmUtilidades.obtenerCopiaInputStreamSoporteAdjunto(adjunto);

            if (!CmUtilidades.zipTieneContenido(inputStreamZip)) {
                addError("El archivo ZIP está vacío o no tiene contenido.");
                return false;
            }

            CmUtilidades.reiniciarInputStream(inputStreamZip);
            ZipEntry entry;
            
            setMapNitFacturaUnicos( new HashMap<>());
            setListaSoportes(new ArrayList<>());
            
            Set<String> tipos =  getHashTipoSoporte().keySet();
            try (InputStream inputStream = inputStreamZip; ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    String fileName = entry.getName();
                    String validacion  = CmUtilidades.validarEstructuraNombreSoportes(fileName, tipos, getMapNitFacturaUnicos());
                    if (!validacion.isEmpty()) {
                        addError(validacion);
                        return false;
                    }
                    
                    try (InputStream fileStream = CmUtilidades.cloneInputStream(zipInputStream)) { 
                        CmUtilidades.reiniciarInputStream(fileStream);
                        getListaSoportes().add(crearSoporte(fileName, fileStream));      
                    }

                    zipInputStream.closeEntry();
                }
            }
         
            if (getMapNitFacturaUnicos().isEmpty()) {
                addError("Error no pueden obtener nit y facturas");
                return false;
            }
            
            buscarCargasSegunSoportes();

            String validacion = validarEstadosDeCargaAsociadosSoportes(getMapNitFacturaUnicos());
            if (!validacion.isEmpty()) {
                addError(validacion);
                return false;
            }
            
            validacion = validarNombreSoporteContraNumeroUsuariosCarga(getListaSoportes());
            if (!validacion.isEmpty()) {
                addError(validacion);
                return false;
            }

        } catch (Exception ex) {
            addError("Error validarDatosZip: "+CmUtilidades.obtenerErrorStrFormateado(ex));
        }
        return true;
    }

    private String validarNombreSoporteContraNumeroUsuariosCarga(List<CmFeSoporte> soportes) {

        Set<String> PREFIJOS_EXCLUIDOS = Set.of("FEV", "FACT", "DTC");
        for (CmFeSoporte soporte : soportes) {
            boolean omitirValidacion = PREFIJOS_EXCLUIDOS.stream()
                    .anyMatch(prefijo -> soporte.getArchivoNombre() != null && soporte.getArchivoNombre().startsWith(prefijo));
            CmFeRipsCarga carga = getListaCargasParaSoporte().get(soporte.getNitFactura());

            if (!omitirValidacion) {
                if (carga.getMultiusuario() != soporte.isMultiUsuario()) {
                    return "El soporte " + soporte.getArchivoNombre() + " hace referencia a una carga "
                            + (soporte.isMultiUsuario() ? "Multiusuario" : "Monousuario")
                            + " y la carga (" + carga.getId() + ") es " + (carga.getMultiusuario() ? "Multiusuario" : "Monousuario");
                }
            }
            soporte.setCmFeRipsCarga(carga);
        }

        return "";
    }

    private String validarEstadosDeCargaAsociadosSoportes(Map<String, String> nitFacturaUnicos) throws Exception {
        try {
            
            Set<Integer> ESTADOS_VALIDOS = Set.of( CmFeRipsCarga.ESTADO_VALIDACION_PROCESO, CmFeRipsCarga.ESTADO_ENVIO_AUDITORIA_OK);
            
            List<String> listaNitFacturaEncontradasCarga = new ArrayList<>(getListaCargasParaSoporte().keySet());

            Map<String, String> nitFacturaNoEncontrados = nitFacturaUnicos.entrySet().stream()
                    .filter(item -> !listaNitFacturaEncontradasCarga.contains(item.getKey()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue
                    ));
            for (Map.Entry<String, String> entrada : nitFacturaNoEncontrados.entrySet()) {;
                String valor = entrada.getValue();
                return ("El nit y factura del archivo( " + valor + " ) no estan asociados a alguna carga del sistema.");
            }  
            
            List<CmFeRipsCarga> cargasNoPermitidas = getListaCargasParaSoporte().values().stream()
                    .filter(Objects::nonNull)
                    .filter( carga ->!ESTADOS_VALIDOS.contains(carga.getEstado()))
                    .collect(Collectors.toList());
          
            if (cargasNoPermitidas.isEmpty()) {
                return "";
            }
         
            for (CmFeRipsCarga cargaNoPermitida : cargasNoPermitidas) {
                return ("La carga ( "+cargaNoPermitida.getId()+" ) que contiene la factura ( "+
                        cargaNoPermitida.getFacturaNumero()+" ) se encuentra en estado: "+ cargaNoPermitida.getEstadoStr() + ", favor validar.");
            }

            
        } catch (Exception ex) {
            throw new Exception(" validarEstadoNitFacturasDeArchivos  : " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
        return "";
    }
    
    private void buscarCargasSegunSoportes() throws Exception {
        try {
            super.setAccion(ACCION_VER);
            super.setDoAccion(DO_ACCION_VER_CARGAS_PARA_SOPORTE);
            getCmFeSoporteServicio().Accion(this);
        } catch (Exception ex) {
            throw new Exception(" buscarCargasSegunSoportes  : " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    public CmFeSoporte getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFeSoporte objeto) {
        this.objeto = objeto;
    }

    public HashMap<String, Maestro> getHashTipoSoporte() {
        return hashTipoSoporte;
    }

    public void setHashTipoSoporte(HashMap<String, Maestro> hashTipoSoporte) {
        this.hashTipoSoporte = hashTipoSoporte;
    }

    public List<CmFeSoporte> getListaSoportes() {
        return listaSoportes;
    }

    public void setListaSoportes(List<CmFeSoporte> listaSoportes) {
        this.listaSoportes = listaSoportes;
    }

    public List<CmFeSoporteAdjunto> getListaSoportesAdjunto() {
        return listaSoportesAdjunto;
    }

    public void setListaSoportesAdjunto(List<CmFeSoporteAdjunto> listaSoportesAdjunto) {
        this.listaSoportesAdjunto = listaSoportesAdjunto;
    }

    public CmFeSoporteServicioIface getCmFeSoporteServicio() {
        return cmFeSoporteServicio;
    }

    public void setCmFeSoporteServicio(CmFeSoporteServicioIface cmFeSoporteServicio) {
        this.cmFeSoporteServicio = cmFeSoporteServicio;
    }
    
    public void getAuditoriaModificacion(String str) {
        CmUtilidades.getAuditoriaModificacion(str);
    }

    public Map<String, CmFeRipsCarga> getListaCargasParaSoporte() {
        return listaCargasParaSoporte;
    }

    public void setListaCargasParaSoporte(Map<String,CmFeRipsCarga> listaCargasParaSoporte) {
        this.listaCargasParaSoporte = listaCargasParaSoporte;
    }  

    public Map<String, String> getMapNitFacturaUnicos() {
        return mapNitFacturaUnicos;
    }

    public void setMapNitFacturaUnicos(Map<String, String> mapNitFacturaUnicos) {
        this.mapNitFacturaUnicos = mapNitFacturaUnicos;
    }

    public LazyDataModel<CmFeSoporte> getLazyCmSoportes() {
        return lazyCmSoportes;
    }

    public void setLazyCmSoportes(LazyDataModel<CmFeSoporte> lazyCmSoportes) {
        this.lazyCmSoportes = lazyCmSoportes;
    }

    public List<CmFeSoporte> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFeSoporte> registros) {
        this.registros = registros;
    }
    
    
    
}
