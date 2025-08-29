/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.facturasusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.cuentamedica.facturasusuario.servicio.CmFacturasUsuarioServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class CmFacturasUsuarioBean extends Url {

    public static final char ACCION_BUSCAR_FACTURAS_DOCUMENTO = 'a';
    public static final char ACCION_BUSCAR_FACTURAS_AUTORIAZACION = 'b';
    public static final char ACCION_BUSCAR_FACTURAS = 'c';

    private CmFacturasUsuarioServicioIface cmFacturasUsuarioServicio;
    private CmFactura objeto;
    private LazyDataModel<CmFactura> lazyFacturas;
    private LazyDataModel<CmDetalle> lazyDetalles;
    private List<CmFactura> registros;
    private List<CmDetalle> registrosDetalles;
    private List<Maestro> listaTiposDocumento;
    private int maeTipoDocumento;
    private String numeroDocumento;
    private String numeroAutorizacion;
    private boolean hayBusquedaValida;

    private CmDetalle detalleServicioActual = new CmDetalle();

    public CmFacturasUsuarioBean() {
        this.objeto = new CmFactura();
        Modulo mod = super.validarModulo(Modulo.ID_CM_FACTURAS_USUARIOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
        }
    }

    @PostConstruct
    public void postConstruct() {
        getCmFacturasUsuarioServicio().cargaInicial(this);
    }

    public CmFactura getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFactura objeto) {
        this.objeto = objeto;
    }

    public List<CmFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFactura> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CmFactura> getLazyFacturas() {
        return lazyFacturas;
    }

    public void setLazyFacturas(LazyDataModel<CmFactura> lazyFacturas) {
        this.lazyFacturas = lazyFacturas;
    }

    public CmFacturasUsuarioServicioIface getCmFacturasUsuarioServicio() {
        return cmFacturasUsuarioServicio;
    }

    public void setCmFacturasUsuarioServicio(CmFacturasUsuarioServicioIface cmFacturasUsuarioServicio) {
        this.cmFacturasUsuarioServicio = cmFacturasUsuarioServicio;
    }

    public LazyDataModel<CmDetalle> getLazyDetalles() {
        return lazyDetalles;
    }

    public void setLazyDetalles(LazyDataModel<CmDetalle> lazyDetalles) {
        this.lazyDetalles = lazyDetalles;
    }

    public List<CmDetalle> getRegistrosDetalles() {
        return registrosDetalles;
    }

    public void setRegistrosDetalles(List<CmDetalle> registrosDetalles) {
        this.registrosDetalles = registrosDetalles;
    }

    public CmDetalle getDetalleServicioActual() {
        if (detalleServicioActual == null) {
            detalleServicioActual = new CmDetalle();
        }
        return detalleServicioActual;
    }

    public void setDetalleServicioActual(CmDetalle detalleServicioActual) {
        this.detalleServicioActual = detalleServicioActual;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public int getMaeTipoDocumento() {
        return maeTipoDocumento;
    }

    public void setMaeTipoDocumento(int maeTipoDocumento) {
        this.maeTipoDocumento = maeTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public boolean isHayBusquedaValida() {
        return hayBusquedaValida;
    }

    public void setHayBusquedaValida(boolean hayBusquedaValida) {
        this.hayBusquedaValida = hayBusquedaValida;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
    }

    public void refrescarCmDetalle() {
        super.setAccion(ACCION_BUSCAR_FACTURAS);
        getCmFacturasUsuarioServicio().Accion(this);
        procesoFinal();
    }

    public void listarMotivosGlosa() {
        super.setAccion(ACCION_LISTAR);

    }

    public void listarAutorizaciones() {
        super.setAccion(ACCION_LISTAR);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void buscarFacturas() {
        setHayBusquedaValida(false);
        if (validarBusquedaFactura()) {
            inicializarTablaTransaccionDetalles();
            setHayBusquedaValida(true);
            limpiarBusquedaNoAplica();
            PrimeFaces.current().resetInputs("frmFacturasUsuario");
            PrimeFaces.current().ajax().update("frmFacturasUsuario:panelConsultaFactura");
            PrimeFaces.current().ajax().update("frmFacturasUsuario:tablaFacturasUsuario");
        }
        procesoFinal();
    }

    public boolean validarBusquedaFactura() {
        boolean esBusquedaValida = true;

        if (!validarCamposObligatoriosFormulario()) {
            if (!validarCamposIncompletosDocumento()) {
                addError("Debe seleccionar un criterio de búsqueda para continuar.");
            }
            esBusquedaValida = false;
        }

        if (esBusquedaValida && !validarExclusionConsulta()) {
            addError("Se debe utilizar solo un criterio de búsqueda, ya se por Tipo y número de documento o Autorización");
            esBusquedaValida = false;
        }

        return esBusquedaValida;
    }

    public boolean limpiarBusquedaNoAplica() {
        boolean hayLimpieza = false;
        if (hayBusquedaAutorizacion()) {
            setMaeTipoDocumento(0);
            setNumeroDocumento("");
        }
        if (hayBusquedaDocumento()) {
            setNumeroAutorizacion("");
        }
        return hayLimpieza;
    }

    public boolean validarCamposObligatoriosFormulario() {
        boolean esValido = false;
        if (hayBusquedaDocumento() || hayBusquedaAutorizacion()) {
            esValido = true;
        }
        return esValido;
    }

    public boolean validarExclusionConsulta() {
        boolean esValido = true;
        if (hayBusquedaDocumento() && hayBusquedaAutorizacion()) {
            esValido = false;
        }
        return esValido;
    }

    public boolean validarCamposIncompletosDocumento() {
        boolean hayCamposIncompletos = false;
        if (this.maeTipoDocumento > 0 && (this.numeroDocumento == null || "".equals(this.numeroDocumento))) {
            addError("Para realizar la búsqueda debe ingresar el número documento.");
            hayCamposIncompletos = true;
        }
        if (this.maeTipoDocumento == 0 && (this.numeroDocumento != null && !"".equals(this.numeroDocumento))) {
            addError("Para para realizar la búsqueda debe ingresar tipo documento.");
            hayCamposIncompletos = true;
        }
        return hayCamposIncompletos;
    }

    public boolean hayBusquedaDocumento() {
        return (this.maeTipoDocumento > 0
                && (this.numeroDocumento != null && !"".equals(this.numeroDocumento)));
    }

    public boolean hayBusquedaAutorizacion() {
        return (this.numeroAutorizacion != null && !"".equals(this.numeroAutorizacion));
    }

    public void verDetalle() {
        super.setAccion(Url.ACCION_VER);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        procesoFinal();
    }

    public void editar(int id) {
        super.setAccion(ACCION_EDITAR);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        procesoFinal();
    }

    public void borrar(int id) {
        super.setAccion(ACCION_BORRAR);
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
                PrimeFaces.current().ajax().update("frmFacturasUsuario");
                break;
            case ACCION_BUSCAR_FACTURAS_DOCUMENTO:
            case ACCION_BUSCAR_FACTURAS_AUTORIAZACION:
            case ACCION_BUSCAR_FACTURAS:
                PrimeFaces.current().ajax().update("frmFacturasUsuario");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmFacturasUsuario");
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmFacturasUsuario");
                break;
        }
        generarMensajes();
    }

    public void limpiarFormulario() {
        setNumeroDocumento("");
        setMaeTipoDocumento(0);
        setNumeroAutorizacion("");
        setHayBusquedaValida(false);
        setRegistrosDetalles(new ArrayList<>());
        PrimeFaces.current().ajax().update("frmFacturasUsuario");
    }

    public void inicializarTablaTransaccionDetalles() {

        lazyDetalles = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCmDetalle();
                lista = getRegistrosDetalles();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (CmDetalle cmDetalle : lista) {
                    if (id.equals(cmDetalle.getId())) {
                        return cmDetalle;
                    }
                }
                return null;
            }
        };
    }

}
