/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.web.solicitud.servicio.GsMensajeServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jramirez
 */
@ManagedBean
@ViewScoped
public class GsMensajeBean extends Url {

    @Autowired
    private GsMensajeServicioIface gsMensajeServicio;
    private GsMensaje objeto;
    private List<GsMensaje> registros;
    private LazyDataModel<GsMensaje> lazyMensaje;

    public GsMensajeBean() {
        this.objeto = new GsMensaje();
        Modulo mod = super.validarModulo(Modulo.ID_SOLICITUD_MENSAJES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyMensaje = new LazyDataModel<GsMensaje>() {

                private List<GsMensaje> mensajes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GsMensaje> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    mensajes = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return mensajes;
                }

                @Override
                public String getRowKey(GsMensaje prestador) {
                    return prestador.getId().toString();
                }

                @Override
                public GsMensaje getRowData(String prestadorId) {
                    Integer id = Integer.valueOf(prestadorId);
                    for (GsMensaje vendedor : mensajes) {
                        if (id.equals(vendedor.getId())) {
                            return vendedor;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        listar();
    }

    public GsMensajeServicioIface getGsMensajeServicio() {
        return gsMensajeServicio;
    }

    public void setGsMensajeServicio(GsMensajeServicioIface gsMensajeServicio) {
        this.gsMensajeServicio = gsMensajeServicio;
    }

    public GsMensaje getObjeto() {
        return objeto;
    }

    public void setObjeto(GsMensaje objeto) {
        this.objeto = objeto;
    }

    public List<GsMensaje> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GsMensaje> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GsMensaje> getLazyMensaje() {
        return lazyMensaje;
    }

    public void setLazyMensaje(LazyDataModel<GsMensaje> lazyMensaje) {
        this.lazyMensaje = lazyMensaje;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGsMensajeServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getGsMensajeServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getGsMensajeServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getGsMensajeServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getGsMensajeServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getGsMensajeServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getGsMensajeServicio().Accion(this);
        procesoFinal();
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
                    PrimeFaces.current().ajax().update("frmMensajes");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
            }
        }
        generarMensajes();
    }

    public String obtenerTipo(int tipo) {
        String valor = "";
        switch (tipo) {
            case 1:
                valor = "Afiliacion";
                break;
            case 2:
                valor = "Desafiliacion";
                break;
            case 3:
                valor = "Movilidad";
                break;
            case 4:
                valor = "Portabilidad";
                break;
            case 10:
                valor = "Autorizacion";
                break;
            case 11:
                valor = "Mipres";
                break;
        }
        return valor;
    }

    public String obtenerEstado(int estado) {
        String valor = "";
        switch (estado) {
            case 2:
                valor = "Resuelto";
                break;
            case 3:
                valor = "Rechazado";
                break;
        }
        return valor;
    }
}
