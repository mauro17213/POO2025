/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.administracion.servicio.EmpresaServicioIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class EmpresaBean extends Url {

    @Autowired
    private EmpresaServicioIface empresaServicio;
    private Empresa objeto;
    private List<Empresa> registros;
    private LazyDataModel<Empresa> lazyEmpresa;
    private ParamConsulta paramConsulta2;
    private List<CntPrestador> listaIps;
    private LazyDataModel<CntPrestador> lazyIps;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Ubicacion> hashUbicaciones;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    public EmpresaBean() {
        this.objeto = new Empresa();
        Modulo _mod = super.validarModulo(Modulo.ID_EMPRESAS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyEmpresa = new LazyDataModel<Empresa>() {

                private List<Empresa> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<Empresa> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(Empresa objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Empresa getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Empresa objeto : lista) {
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
        getEmpresaServicio().cargaInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public Empresa getObjeto() {
        return objeto;
    }

    public void setObjeto(Empresa objeto) {
        this.objeto = objeto;
    }

    public List<Empresa> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Empresa> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Empresa> getLazyEmpresa() {
        return lazyEmpresa;
    }

    public void setLazyEmpresa(LazyDataModel<Empresa> lazyEmpresa) {
        this.lazyEmpresa = lazyEmpresa;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public EmpresaServicioIface getEmpresaServicio() {
        return empresaServicio;
    }

    public void setEmpresaServicio(EmpresaServicioIface empresaServicio) {
        this.empresaServicio = empresaServicio;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getEmpresaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().ajax().update("frmVer:pIpsVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getEmpresaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getEmpresaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getEmpresaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:pIpsEditar");
        PrimeFaces.current().ajax().update("frmEditar:pEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getEmpresaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getEmpresaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getEmpresaServicio().Accion(this);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmEmpresas");
                    break;
            }
            refrescar();
        }
        generarMensajes();
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

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setCiudad(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public void listarIps() {
        consultarIps();
        PrimeFaces.current().ajax().update("frmIpsLista");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
    }

    private void consultarIps() {
        this.setParamConsulta2(new ParamConsulta());
        this.getParamConsulta2().setEmpresaId(super.getConexion().getEmpresa().getId());
        lazyIps = new LazyDataModel<CntPrestador>() {

            private List<CntPrestador> listaIps;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta2().getCantidadRegistros();
            }

            @Override
            public List<CntPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta2().setPrimerRegistro(primerRegistro);
                getParamConsulta2().setRegistrosPagina(registrosPagina);
                getParamConsulta2().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta2().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarIps();
                listaIps = getListaIps();
                setRowCount(getParamConsulta2().getCantidadRegistros());
                return listaIps;
            }

            @Override
            public String getRowKey(CntPrestador ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestador getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestador ips : listaIps) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }

    public ParamConsulta getParamConsulta2() {
        return paramConsulta2;
    }

    public void setParamConsulta2(ParamConsulta paramConsulta2) {
        this.paramConsulta2 = paramConsulta2;
    }

    public List<CntPrestador> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestador> listaIps) {
        this.listaIps = listaIps;
    }

    public LazyDataModel<CntPrestador> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestador> lazyIps) {
        this.lazyIps = lazyIps;
    }

    public void refrescarIps() {
        getEmpresaServicio().listarIps(this);
    }

    public String obtenerTipoDocumentoIps(int id) {
        try {
            return hashTipoDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestador ips = (CntPrestador) event.getObject();
        getObjeto().setCntPrestador(ips);
        getParamConsulta2().setFiltros(new HashMap<>());
        PrimeFaces.current().ajax().update("frmCrear:pIpsCrear");
        PrimeFaces.current().ajax().update("frmEditar:pIpsCrear");
        PrimeFaces.current().ajax().update("frmIpsLista:tablaRegistrosIps");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

}
