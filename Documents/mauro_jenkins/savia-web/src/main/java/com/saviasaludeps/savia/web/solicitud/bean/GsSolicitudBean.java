/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import com.saviasaludeps.savia.dominio.solicitud.GsGestion;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.web.solicitud.servicio.GsSolicitudServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jramirez
 */
@ManagedBean
@ViewScoped
public class GsSolicitudBean extends Url {

    @Autowired
    private GsSolicitudServicioIface gsSolicitudServicio;

    private GsSolicitud objeto;
    private List<GsSolicitud> registros;
    private LazyDataModel<GsSolicitud> lazyRegistro;

    private GsGestion gsGestion;
    private GsMensaje gsMensaje;

    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private List<GsZona> listaZonas;

    private boolean refrescarGestion = false;
    private List<Maestro> listaTiposDocumento;

    public GsSolicitudBean() {
        this.objeto = new GsSolicitud();
        this.objeto.setGsZona(new GsZona());
        gsGestion = new GsGestion();
        listaTiposDocumento = new ArrayList();
        Modulo mod = super.validarModulo(Modulo.ID_SOLICITUD_SOLICITUDES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistro = new LazyDataModel<GsSolicitud>() {

                private List<GsSolicitud> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<GsSolicitud> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(GsSolicitud caso) {
                    return caso.getId().toString();
                }

                @Override
                public GsSolicitud getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (GsSolicitud gsSolicitud : lista) {
                        if (id.equals(gsSolicitud.getId())) {
                            return gsSolicitud;
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
        getGsSolicitudServicio().cargaInial(this);
        listar();
    }

    public GsSolicitudServicioIface getGsSolicitudServicio() {
        return gsSolicitudServicio;
    }

    public void setGsSolicitudServicio(GsSolicitudServicioIface gsSolicitudServicio) {
        this.gsSolicitudServicio = gsSolicitudServicio;
    }

    public GsSolicitud getObjeto() {
        return objeto;
    }

    public void setObjeto(GsSolicitud objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<GsSolicitud> getLazyRegistro() {
        return lazyRegistro;
    }

    public void setLazyRegistro(LazyDataModel<GsSolicitud> lazyRegistro) {
        this.lazyRegistro = lazyRegistro;
    }

    public List<GsSolicitud> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GsSolicitud> registros) {
        this.registros = registros;
    }

    public GsGestion getGsGestion() {
        return gsGestion;
    }

    public void setGsGestion(GsGestion gsGestion) {
        this.gsGestion = gsGestion;
    }

    public GsMensaje getGsMensaje() {
        return gsMensaje;
    }

    public void setGsMensaje(GsMensaje gsMensaje) {
        this.gsMensaje = gsMensaje;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<GsZona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(List<GsZona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGsSolicitudServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getGsSolicitudServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getGsSolicitudServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getGsSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getGsSolicitudServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().ajax().update("frmAuditoriaEditar:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getGsSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getGsSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void gestionar(int id) {
        refrescarGestion = false;
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getObjeto().setId(id);
        getGsSolicitudServicio().Accion(this);
        refrescarGestion = getObjeto().isCambioEstado();
        getObjeto().setCambioEstado(false);
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().ajax().update("frmAuditoriaGestionar:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        procesoFinal();
    }

    public void crearGestion() {
        gsGestion = new GsGestion();
        listaMensajes = new ArrayList();
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getGsSolicitudServicio().Accion(this);
        if (!isError()) {
            //RequestContext.getCurrentInstance().reset("frmCrearGestion");
            PrimeFaces.current().ajax().update("frmCrearGestion");
            PrimeFaces.current().executeScript("PF('dialogoCrearGestion').show()");
        }
        procesoFinal();
    }

    public void guardarGestion() {
        refrescarGestion = false;
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getGsSolicitudServicio().Accion(this);
        if (!super.isError()) {
            refrescarGestion = getObjeto().isCambioEstado();
            getObjeto().setCambioEstado(false);
            PrimeFaces.current().executeScript("PF('dialogoCrearGestion').hide();");
            PrimeFaces.current().ajax().update("frmGestionar");
        }
        procesoFinal();
    }

    public void crearReasignacion(int id) {
        setObjeto(getGsSolicitudServicio().consultarSolicitud(id));
        switch (getObjeto().getEstado()) {
            case GsSolicitud.ESTADO_RESUELTO:
                addError("Esta solicitud esta en RESUELTO, por lo cual no se puede reasignar");
                break;
            case GsSolicitud.ESTADO_NO_TRAMITADO:
                addError("Esta solicitud esta NO TRAMITADO, por lo cual no se puede reasignar");
                break;
            default:
                super.setAccion(ACCION_ADICIONAL_3);
                super.setDoAccion(ACCION_CREAR);
                getGsSolicitudServicio().Accion(this);
                PrimeFaces.current().ajax().update("frmReasignar");
                PrimeFaces.current().executeScript("PF('dialogoReasignar').show()");
                break;
        }
        procesoFinal();
    }

    public void guardarReasignacion() {
        GsZona zona = getObjeto().getGsZona();
        setObjeto(getGsSolicitudServicio().consultarSolicitud(getObjeto().getId()));
        switch (getObjeto().getEstado()) {
            case GsSolicitud.ESTADO_RESUELTO:
                addError("Esta solicitud esta en RESUELTO, por lo cual no se puede reasignar");
                break;
            case GsSolicitud.ESTADO_NO_TRAMITADO:
                addError("Esta solicitud esta en NO TRAMITADO, por lo cual no se puede reasignar");
                break;
            default:
                super.setAccion(ACCION_ADICIONAL_3);
                super.setDoAccion(ACCION_GUARDAR);
                getObjeto().setGsZona(new GsZona(zona.getId()));
                getGsSolicitudServicio().Accion(this);
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoReasignar').hide();");
                }
                procesoFinal();
                break;
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
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
                    PrimeFaces.current().ajax().update("frmSolicitudes");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Gestionar", "Lista de Registro");
                            if (refrescarGestion) {
                                PrimeFaces.current().ajax().update("frmSolicitudes");
                            }
                            break;
                        case Url.ACCION_CREAR:
                            crearLog("Crear Gestión", "Creación de Registro");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Gestión", getGsGestion().toString());
                            if (refrescarGestion) {
                                PrimeFaces.current().ajax().update("frmSolicitudes");
                            }
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            crearLog("Reasignación", getObjeto().toString());
                            break;
                    }
                    PrimeFaces.current().ajax().update("frmSolicitudes");
                    break;
            }
        }
        generarMensajes();
    }

    public void descargarAdjunto(GsAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
            gestionar(objeto.getId());
        }
        procesoFinal();
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = ubicacionesRecursiva.get(id);
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    private List<GsMensaje> listaMensajes;

    public List<GsMensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<GsMensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public void cargarMensajes() {
        listaMensajes = new ArrayList();
        if (getGsGestion().getEstado() == GsGestion.ESTADO_RESUELTO
                || getGsGestion().getEstado() == GsGestion.ESTADO_NO_TRAMITADO) {
            listaMensajes = getGsSolicitudServicio().consultarMenajesPorTipoEstado(getObjeto().getTipo(), getGsGestion().getEstado());
        }
    }

    public boolean isObligatorioPorCierre() {
        return getGsGestion().getEstado() == GsGestion.ESTADO_RESUELTO
                || getGsGestion().getEstado() == GsGestion.ESTADO_NO_TRAMITADO;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

}
