/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.contingencia.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcionAdjunto;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcProgramacionEntrega;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.mipres.contingencia.servicio.PrescripcionServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic SaviaSalud EPS
 */
@ManagedBean
@ViewScoped
public class PrescripcionBean extends Url {

    private PrescripcionServicioIface prescripcionServicio;
    private MpcPrescripcion objeto;
    private MpcPrescripcionHistorico objetoHistorico;
    private List<MpcPrescripcion> registros;
    private List<MpcPrescripcionHistorico> registrosHistorico;
    private LazyDataModel<MpcPrescripcion> lazyPrescripcion;

    private MpcProgramacionEntrega objetoEntrega;

    private List<Maestro> maestros;

    //Listas Auxiliares
    private List<Maestro> listaTipoDocumentoPrestador;
    private HashMap<Integer, Maestro> hashTipoDocumentoPrestador;//Listas Auxiliares
    private List<Maestro> listaTipoDocumentoAfiliado;
    private HashMap<Integer, Maestro> hashTipoDocumentoAfiliado;

    //Vistas auxiliares
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;

    private List<CntPrestadorSede> listaIps;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private List<Ubicacion> listaCiudades;
    private HashMap<Integer, Ubicacion> hashCiudades;
    private boolean falloEntrega;
    String rechazoJustificacionCorreo = "";

    private boolean mostrarIconoRecobrante;
    private boolean mostrarIconoAmbito;
    private boolean mostrarIconoTecnologia;
    private boolean mostrarIconoCorreo;
    private boolean mostrarIconoReferenciaContra;
    private boolean tieneHistorico;

    public PrescripcionBean() {
        this.objeto = new MpcPrescripcion();
        Modulo _mod = super.validarModulo(Modulo.ID_CONTINGENCIA_PRESCRIPCIONES);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta(0).setEmpresaId(null);
        } else {
            super.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyPrescripcion = new LazyDataModel<MpcPrescripcion>() {

                private List<MpcPrescripcion> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MpcPrescripcion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(MpcPrescripcion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public MpcPrescripcion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (MpcPrescripcion objeto : lista) {
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
        getPrescripcionServicio().cargasInicial(this);
        listar();
    }

    public PrescripcionServicioIface getPrescripcionServicio() {
        return prescripcionServicio;
    }

    public void setPrescripcionServicio(PrescripcionServicioIface prescripcionServicio) {
        this.prescripcionServicio = prescripcionServicio;
    }

    public MpcPrescripcion getObjeto() {
        return objeto;
    }

    public void setObjeto(MpcPrescripcion objeto) {
        this.objeto = objeto;
    }

    public MpcPrescripcionHistorico getObjetoHistorico() {
        return objetoHistorico;
    }

    public void setObjetoHistorico(MpcPrescripcionHistorico objetoHistorico) {
        this.objetoHistorico = objetoHistorico;
    }

    public List<MpcPrescripcionHistorico> getRegistrosHistorico() {
        return registrosHistorico;
    }

    public void setRegistrosHistorico(List<MpcPrescripcionHistorico> registrosHistorico) {
        this.registrosHistorico = registrosHistorico;
    }

    public List<MpcPrescripcion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MpcPrescripcion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MpcPrescripcion> getLazyPrescripcion() {
        return lazyPrescripcion;
    }

    public void setLazyPrescripcion(LazyDataModel<MpcPrescripcion> lazyPrescripcion) {
        this.lazyPrescripcion = lazyPrescripcion;
    }

    public MpcProgramacionEntrega getObjetoEntrega() {
        return objetoEntrega;
    }

    public void setObjetoEntrega(MpcProgramacionEntrega objetoEntrega) {
        this.objetoEntrega = objetoEntrega;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public List<Maestro> getListaTipoDocumentoPrestador() {
        return listaTipoDocumentoPrestador;
    }

    public void setListaTipoDocumentoPrestador(List<Maestro> listaTipoDocumentoPrestador) {
        this.listaTipoDocumentoPrestador = listaTipoDocumentoPrestador;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoPrestador() {
        return hashTipoDocumentoPrestador;
    }

    public void setHashTipoDocumentoPrestador(HashMap<Integer, Maestro> hashTipoDocumentoPrestador) {
        this.hashTipoDocumentoPrestador = hashTipoDocumentoPrestador;
    }

    public List<Maestro> getListaTipoDocumentoAfiliado() {
        return listaTipoDocumentoAfiliado;
    }

    public void setListaTipoDocumentoAfiliado(List<Maestro> listaTipoDocumentoAfiliado) {
        this.listaTipoDocumentoAfiliado = listaTipoDocumentoAfiliado;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoAfiliado() {
        return hashTipoDocumentoAfiliado;
    }

    public void setHashTipoDocumentoAfiliado(HashMap<Integer, Maestro> hashTipoDocumentoAfiliado) {
        this.hashTipoDocumentoAfiliado = hashTipoDocumentoAfiliado;
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public List<CntPrestadorSede> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestadorSede> listaIps) {
        this.listaIps = listaIps;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestadorSede> lazyIps) {
        this.lazyIps = lazyIps;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public HashMap<Integer, Ubicacion> getHashCiudades() {
        return hashCiudades;
    }

    public void setHashCiudades(HashMap<Integer, Ubicacion> hashCiudades) {
        this.hashCiudades = hashCiudades;
    }

    public boolean isFalloEntrega() {
        return falloEntrega;
    }

    public void setFalloEntrega(boolean falloEntrega) {
        this.falloEntrega = falloEntrega;
    }

    public String getRechazoJustificacionCorreo() {
        return rechazoJustificacionCorreo;
    }

    public void setRechazoJustificacionCorreo(String rechazoJustificacionCorreo) {
        this.rechazoJustificacionCorreo = rechazoJustificacionCorreo;
    }

    public boolean isMostrarIconoRecobrante() {
        return mostrarIconoRecobrante;
    }

    public void setMostrarIconoRecobrante(boolean mostrarIconoRecobrante) {
        this.mostrarIconoRecobrante = mostrarIconoRecobrante;
    }

    public boolean isMostrarIconoAmbito() {
        return mostrarIconoAmbito;
    }

    public void setMostrarIconoAmbito(boolean mostrarIconoAmbito) {
        this.mostrarIconoAmbito = mostrarIconoAmbito;
    }

    public boolean isMostrarIconoTecnologia() {
        return mostrarIconoTecnologia;
    }

    public void setMostrarIconoTecnologia(boolean mostrarIconoTecnologia) {
        this.mostrarIconoTecnologia = mostrarIconoTecnologia;
    }

    public boolean isMostrarIconoCorreo() {
        return mostrarIconoCorreo;
    }

    public void setMostrarIconoCorreo(boolean mostrarIconoCorreo) {
        this.mostrarIconoCorreo = mostrarIconoCorreo;
    }

    public boolean isMostrarIconoReferenciaContra() {
        return mostrarIconoReferenciaContra;
    }

    public void setMostrarIconoReferenciaContra(boolean mostrarIconoReferenciaContra) {
        this.mostrarIconoReferenciaContra = mostrarIconoReferenciaContra;
    }

    public boolean isTieneHistorico() {
        return tieneHistorico;
    }

    public void setTieneHistorico(boolean tieneHistorico) {
        this.tieneHistorico = tieneHistorico;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getPrescripcionServicio().Accion(this);

    }

    public void ver(int _id) {
        objetoHistorico = null;
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getPrescripcionServicio().Accion(this);
        inicializarAlertasEdit();
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmAuditoria");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getPrescripcionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getPrescripcionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id, short id) {
        if (id == 0) {
            if (!getPrescripcionServicio().conultarHistorico(_id)) {
                getObjeto().setId(_id);
                super.setAccion(ACCION_EDITAR);
                getPrescripcionServicio().Accion(this);
                PrimeFaces.current().ajax().update("frmEditar");
                PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
            } else {
                addError("La prescripción excedió el número de ediciones permitidas");
            }
        } else {
            addError("Acción no permitida:  prescripción ya fue gestionada");
        }

        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getPrescripcionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void gestionar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
         crearLog("Gestionar",getObjeto().toString());
        getPrescripcionServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDireccionar').show()");
            PrimeFaces.current().resetInputs("frmDireccionar");
            PrimeFaces.current().ajax().update("frmDireccionar");
        }
        procesoFinal();
    }

    public void guardarDireccionamiento(int _id) {
        if (getObjeto().getCantidad() == null) {
            addError("La cantidad debe tener un valor");
            generarMensajes();
            return;
        }
        if (getObjeto().getNumeroEntregas() == null) {
            addError("El numero de entregas debe tener un valor");
            generarMensajes();
            return;
        }
        getObjeto().setId(_id);
        getObjeto().setEstado(MpcPrescripcion.ESTADO_DIRECCIONADO);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getPrescripcionServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDireccionar').hide();");
        }
        procesoFinal();
    }

    public void abrirJustificarRechazo(MpcPrescripcion prescripcion) {
        getObjeto().setCantidad(null);
        getObjeto().setNumeroEntregas(null);
        getObjeto().setId(prescripcion.getId());
        getObjeto().setEstado(MpcPrescripcion.ESTADO_NO_DIRECCIONADO);
        getObjeto().setRechazoJustificacion("");
        setRechazoJustificacionCorreo("");
        setRechazoJustificacionCorreo(inicializarCorreoRechazo(prescripcion));
        PrimeFaces.current().ajax().update("frmJustificacionRechazo");
        PrimeFaces.current().executeScript("PF('dialogoJustificarRechazo').show();");
    }

    public void rechazar() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getPrescripcionServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoJustificarRechazo').hide();");
            PrimeFaces.current().executeScript("PF('dialogoDireccionar').hide();");
        }
        procesoFinal();
    }

    public void listarEntregas(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getPrescripcionServicio().Accion(this);
         crearLog("Listar Entregas",getObjeto().toString());
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEntregas').show()");
            PrimeFaces.current().resetInputs("frmEntregas");
            PrimeFaces.current().ajax().update("frmEntregas");
        } else {
            super.setAccion(ACCION_LISTAR);
        }
        procesoFinal();
    }

    public void crearEntrega(int _id) {
        setFalloEntrega(false);
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getPrescripcionServicio().Accion(this);
        if (!super.isError() || !isFalloEntrega()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearEntrega').show()");
            PrimeFaces.current().ajax().update("frmCrearEntrega");
        }
        procesoFinal();
    }

    public void guardarEntrega() {
        setFalloEntrega(false);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getPrescripcionServicio().Accion(this);
        if (!super.isError() || !isFalloEntrega()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearEntrega').hide();");
            PrimeFaces.current().ajax().update("frmEntregas:panelEntregaLista");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getPrescripcionServicio().Accion(this);
        procesoFinal();
    }

    public boolean isDireccionado() {
        return (getObjeto().getEstado() != MpcPrescripcion.ESTADO_CREADO);
    }

    public void consultarMedicaMentoNutricional() {
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
    }

    public void consultarProcedimientoDispositivo() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void consultarInsumoComplementario() {
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
    }

    public void consultarProveedor() {
        try {
            if (objeto.getMaTecnologiaId() != null) {

                inicializarLazyProveedor();
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
                }
            } else {
                addError("Se debe seleccionar la tecnologia");
            }
            generarMensajes();
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void refrescarIps() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getPrescripcionServicio().Accion(this);
        procesoFinal();
    }

    public void cerrarDialogoMedicamentoNutricional() {
        if (getMedicamentosBean().getObjeto() != null && getMedicamentosBean().getObjeto().getId() != null) {
            MaMedicamento medicamento = getMedicamentosBean().getObjeto();
            getObjeto().setMaTecnologiaId(medicamento.getId());
            getObjeto().setMaTecnologiaCodigo(medicamento.getCum());
            getObjeto().setMaTecnologiaValor(medicamento.getDescripcionEstandarizada());
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
        } else {
            addError("No se encontro información, del medicamento favor contactar al administrador");
            generarMensajes();
        }
        PrimeFaces.current().ajax().update("frmDireccionar:pVerDireccionamiento");
    }

    public void cerrarDialogoProcedimiento() {
        if (getTecnologiasBean().getObjeto() != null && getTecnologiasBean().getObjeto().getId() != null) {
            MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
            getObjeto().setMaTecnologiaId(tecnologia.getId());
            getObjeto().setMaTecnologiaCodigo(tecnologia.getCodigoPropio());
            getObjeto().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");

        } else {
            addError("No se encontro información, del medicamento favor contactar al administrador");
            generarMensajes();
        }
        PrimeFaces.current().ajax().update("frmDireccionar:pVerDireccionamiento");
    }

    public void cerrarDialogoDispositivoComplementario() {
        if (getInsumosBean().getObjeto() != null && getInsumosBean().getObjeto().getId() != null) {
            MaInsumo insumo = getInsumosBean().getObjeto();
            getObjeto().setMaTecnologiaId(insumo.getId());
            getObjeto().setMaTecnologiaCodigo(insumo.getCodigo());
            getObjeto().setMaTecnologiaValor(insumo.getDescripcion());
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
        } else {
            addError("No se encontro información, del medicamento favor contactar al administrador");
            generarMensajes();
        }
        PrimeFaces.current().ajax().update("frmDireccionar:pVerDireccionamiento");
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntDireccionadoPrestadorSede(ips);
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmDireccionar:pVerDireccionamiento");
    }

    public String obtenerMunicipio(Integer id) {
        String nombre = "";
        Ubicacion ubi = getHashCiudades().get(id);
        if (ubi != null) {
            nombre = ubi.getNombre();
        }
        return nombre;
    }

    public void descargarAdjunto(MpcPrescripcionAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            if (file.exists()) {
                byte[] exportContent = new byte[(int) file.length()];
                FileInputStream fis = new FileInputStream(file);
                fis.read(exportContent);
                int i = rutaCompleta.lastIndexOf(".");
                String ext = rutaCompleta.substring(i, rutaCompleta.length());
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                ec.responseReset();
                ec.setResponseContentLength(exportContent.length);
                String attachmentName = "attachment; filename=\"" + adjunto.getArchivo() + "\"";
                ec.setResponseHeader("Content-Disposition", attachmentName);
                if (ext.equalsIgnoreCase(".pdf")) {
                    ec.setResponseContentType("application/pdf");
                } else {
                    throw new Exception();
                }
                OutputStream output = ec.getResponseOutputStream();
                output.write(exportContent);
                output.close();
                fc.responseComplete();
                 crearLog("Desacergar Adjunto",adjunto.toString());
            } else {
                addError("El archivo no existe");
                generarMensajes();
            }

        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
            generarMensajes();
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmPrescripciones");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmIps");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmPrescripciones");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                //Direccionamiento
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog("Crear gestión", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog("Guardar gestión", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmPrescripciones");
                            break;
                    }
                    break;
                //Entregas
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear entrega", getObjetoEntrega().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar entrega", getObjetoEntrega().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public String inicializarCorreoRechazo(MpcPrescripcion prescripcion) {
        StringBuilder mensaje = new StringBuilder();

        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fechaActual);

        mensaje.append("Mipres con número de radicado ").append("\n");
        mensaje.append("•  Número : ").append(objeto.getId()).append("\n");
        mensaje.append("asociada al paciente").append("\n");
        mensaje.append("•  Paciente : ");
        if (objeto.getAfiliadoPrimerNombre() != null) {
            mensaje.append(objeto.getAfiliadoPrimerNombre());
        }
        if (objeto.getAfiliadoSegundoNombre() != null) {
            mensaje.append(" ").append(objeto.getAfiliadoSegundoNombre());
        }
        if (objeto.getAfiliadoPrimerApellido() != null) {
            mensaje.append(" ").append(objeto.getAfiliadoPrimerApellido());
        }
        if (objeto.getAfiliadoSegundoApellido() != null) {
            mensaje.append(" ").append(objeto.getAfiliadoSegundoApellido());
        }
        mensaje.append(" (").append(objeto.getAsegAfiliado().getMaeTipoDocumentoCodigo()).append(" ");
        mensaje.append(" ").append(objeto.getAsegAfiliado().getNumeroDocumento()).append(")\n");

        mensaje.append("Tiene un estado de No Direccionado").append("\n");
        mensaje.append("•  Fecha : ").append(fechaFormateada).append("\n");
        mensaje.append("•  Hora : ").append(horaFormateada).append("\n");
        mensaje.append("•  SAVIA SALUD E.P.S ").append("\n");
        mensaje.append("").append("\n");

        return mensaje.toString();
    }

    public void inicializarAlertasEdit() {
        if (objetoHistorico != null) {

            if (objeto.isRecobrante() != objetoHistorico.isRecobrante()) {
                setMostrarIconoRecobrante(true);
            } else {
                setMostrarIconoRecobrante(false);
            }

            if (!objeto.getAmbito().equals(objetoHistorico.getAmbito())) {
                setMostrarIconoAmbito(true);
            } else {
                setMostrarIconoAmbito(false);
            }

            if (!objeto.getPrescriptorCorreoElectronico().equals(objetoHistorico.getPrescriptorCorreoElectronico())) {
                setMostrarIconoCorreo(true);
            } else {
                setMostrarIconoCorreo(false);
            }

            if (objeto.getTipoTecnologia() != (objetoHistorico.getTipoTecnologia())) {
                setMostrarIconoTecnologia(true);
            } else {
                setMostrarIconoTecnologia(false);
            }

            if (!objeto.getReferenciaAmbitoAtencion().equals(objetoHistorico.getReferenciaAmbitoAtencion())) {
                setMostrarIconoReferenciaContra(true);
            } else {
                setMostrarIconoReferenciaContra(false);
            }
        }
    }

    public String ambitoStt(String valor) {
        String resultado;
        switch (valor) {
            case "11":
                resultado = "Ambulatorio - Priorizado";
                break;
            case "12":
                resultado = "Ambulatorio - No Priorizado";
                break;
            case "21":
                resultado = "Hospitalario - Domiciliario";
                break;
            case "22":
                resultado = "Hospitalario - Internacion";
                break;
            case "30":
                resultado = "Urgencias";
                break;
            default:
                resultado = "";
                break;
        }

        return resultado;

    }

    public String referContra(boolean valor) {
        String resultado;
        if (valor == true) {
            resultado = "SI";
        } else {
            resultado = "NO";
        }
        return resultado;

    }

    public boolean validarCorreo() {
        String correoElectronico = objeto.getPrescriptorCorreoElectronico();
        boolean esValido = correoElectronico.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        FacesMessage message;
        if (!esValido) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo Electronico: no es válido", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo Electronico: valido", null);
        }
        FacesContext.getCurrentInstance().addMessage("frmMipres:correoElectronicoPrestador", message);

        return esValido;
    }

    public void inicializarLazyProveedor() {
        lazyIps = new LazyDataModel<CntPrestadorSede>() {

            private List<CntPrestadorSede> listaIps;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarIps();
                listaIps = getListaIps();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaIps;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaIps) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }

}
