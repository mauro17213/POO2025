/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.web.externo.servicio.AtencionConsultaCasoServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_LISTAR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.externo.servicio.AtencionConsultaCasoServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author pavacca
 */
@ManagedBean
@ViewScoped
public class AtencionConsultaCasoBean extends Url {

    private AusCaso objeto;
    private AtencionConsultaCasoServicioIface atencionConsultaCaso;
    private List<AusCaso> registros;
    private LazyDataModel<AusCaso> lazyAusCaso;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    public final static char ACCION_BUSCAR_SOLICITUDES_CASO_SERVICE = '0';
    public boolean mostrarTablaSolicitudes;

    public AtencionConsultaCasoBean() {
        this.objeto = new AusCaso();
        this.objeto.setAusPersonasId(new AusPersona());
        //this.fechaMaxima = new Date();
        this.listaTiposDocumento = new ArrayList<>();
        this.hashTiposDocumento = new HashMap<>();
        this.atencionConsultaCaso = new AtencionConsultaCasoServicioImpl();

        lazyAusCaso = new LazyDataModel<AusCaso>() {

            private List<AusCaso> vendedores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<AusCaso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                vendedores = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return vendedores;
            }

            @Override
            public String getRowKey(AusCaso vendedor) {
                return vendedor.getId().toString();
            }

            @Override
            public AusCaso getRowData(String vendedorId) {
                Integer id = Integer.valueOf(vendedorId);
                for (AusCaso vendedor : vendedores) {
                    if (id.equals(vendedor.getId())) {
                        return vendedor;
                    }
                }
                return null;
            }
        };
    }

    @PostConstruct
    public void postConstruct() {
        getAtencionConsultaCaso().cargaInicial(this);
        //personaBean = new AusPersonaBean();
        this.setTamanoPagina(10);
        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmBusquedaSolicitud");
        }
        procesoFinal();
    }

    public AusCaso getObjeto() {
        return objeto;
    }

    public void setObjeto(AusCaso objeto) {
        this.objeto = objeto;
    }

    public AtencionConsultaCasoServicioIface getAtencionConsultaCaso() {
        return atencionConsultaCaso;
    }

    public void setAtencionConsultaCaso(AtencionConsultaCasoServicioIface atencionConsultaCaso) {
        this.atencionConsultaCaso = atencionConsultaCaso;
    }

    public List<AusCaso> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AusCaso> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AusCaso> getLazyAusCaso() {
        return lazyAusCaso;
    }

    public void setLazyAusCaso(LazyDataModel<AusCaso> lazyAusCaso) {
        this.lazyAusCaso = lazyAusCaso;
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

    public boolean isMostrarTablaSolicitudes() {
        return mostrarTablaSolicitudes;
    }

    public void setMostrarTablaSolicitudes(boolean mostrarTablaSolicitudes) {
        this.mostrarTablaSolicitudes = mostrarTablaSolicitudes;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAtencionConsultaCaso().Accion(this);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case ACCION_BUSCAR_SOLICITUDES_CASO_SERVICE:
                    PrimeFaces.current().ajax().update("frmBusquedaSolicitud");
                    PrimeFaces.current().ajax().update("frmSolicitudConsultar");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void buscarSolicitudesCaso() {
        this.setMostrarTablaSolicitudes(false);
        Maestro tipoDocumento = getHashTiposDocumento().get(getObjeto().getAusPersonasId().getMae_tipo_documento_id());
        if (tipoDocumento != null) {
            getObjeto().getAusPersonasId().setMae_tipo_documento_codigo(tipoDocumento.getValor());
        }
        super.setAccion(ACCION_LISTAR);
        getAtencionConsultaCaso().Accion(this);

        if (!super.isError()) {
            this.setMostrarTablaSolicitudes(true);
        }

        PrimeFaces.current().ajax().update("frmSolicitudConsultar");
//        RequestContext.getCurrentInstance().reset("frmSolicitudConsultar");
        procesoFinal();
    }

    public void limpiarFormularioBusqueda() {
        this.getObjeto().getAsuPersonasId().setDocumento(null);
        this.getObjeto().getAsuPersonasId().setMae_tipo_documento_id(0);
        this.getObjeto().getAsuPersonasId().setFechaNacimiento(null);
        //this.setHabilitarSeccionResultados(false);
        this.setMostrarTablaSolicitudes(false);
        PrimeFaces.current().ajax().update("frmBusquedaSolicitud");
//        RequestContext.getCurrentInstance().reset("frmBusquedaSolicitud");
        PrimeFaces.current().ajax().update("frmSolicitudConsultar");
//        RequestContext.getCurrentInstance().reset("frmSolicitudConsultar");
    }
}
