/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cargas.CarCarga;
import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.web.cargas.bean.DTO.JsonDatoDTO;
import com.saviasaludeps.savia.web.cargas.servicio.CargaGestionServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import com.saviasaludeps.savia.web.cargas.utilidades.RtConstantesCargas;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author aguevara
 */
@ManagedBean
@ViewScoped
public class CargaGestionBean extends Url {

    @Autowired
    private CargaGestionServicioIface gestionServicio;
    private CarCargaRegistro objeto;

    private LazyDataModel<CarCargaRegistro> lazyCargaRegistros;
    private List<CarCargaRegistro> registros;

    private LazyDataModel<CarCarga> lazyCarga;
    private List<CarCarga> registrosCarga;

    private List<CntPrestador> listaPrestadores;
    private List<CarProceso> listaProcesos;
    private List<CarProcesoUsuario> ListaProcesosUsuario;

    private List<CarCargaRegistro> listaTipoCargas;
    private Set<String> tiposDeCargaMostrados = new HashSet<>();

    private List<JsonDatoDTO> datosFilaEditables;

    public CargaGestionBean() {
        this.objeto = new CarCargaRegistro();

        Modulo _mod = super.validarModulo(Modulo.ID_CAR_GESTIONES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        super.getParamConsulta().setParametroConsulta1(super.getConexion().getEmpresa());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCargaRegistros = new LazyDataModel<CarCargaRegistro>() {

                private List<CarCargaRegistro> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CarCargaRegistro> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CarCargaRegistro objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CarCargaRegistro getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CarCargaRegistro objeto : lista) {
                        if (id.equals(CargaGestionBean.this.objeto.getId())) {
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
        //getCargaServicio().cargasInicial(this);
        listar();
    }

    public CargaGestionServicioIface getGestionServicio() {
        return gestionServicio;
    }

    public void setGestionServicio(CargaGestionServicioIface gestionServicio) {
        this.gestionServicio = gestionServicio;
    }

    public CarCargaRegistro getObjeto() {
        return objeto;
    }

    public void setObjeto(CarCargaRegistro objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CarCargaRegistro> getLazyCargaRegistros() {
        return lazyCargaRegistros;
    }

    public void setLazyCargaRegistros(LazyDataModel<CarCargaRegistro> lazyCargaRegistros) {
        this.lazyCargaRegistros = lazyCargaRegistros;
    }

    public List<CntPrestador> getListaPrestadores() {
        return listaPrestadores;
    }

    public void setListaPrestadores(List<CntPrestador> listaPrestadores) {
        this.listaPrestadores = listaPrestadores;
    }

    public List<CarProceso> getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(List<CarProceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public List<CarProcesoUsuario> getListaProcesosUsuario() {
        return ListaProcesosUsuario;
    }

    public void setListaProcesosUsuario(List<CarProcesoUsuario> ListaProcesosUsuario) {
        this.ListaProcesosUsuario = ListaProcesosUsuario;
    }

    public LazyDataModel<CarCarga> getLazyCarga() {
        return lazyCarga;
    }

    public void setLazyCarga(LazyDataModel<CarCarga> lazyCarga) {
        this.lazyCarga = lazyCarga;
    }

    public List<CarCargaRegistro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CarCargaRegistro> registros) {
        this.registros = registros;
    }

    public List<CarCarga> getRegistrosCarga() {
        return registrosCarga;
    }

    public void setRegistrosCarga(List<CarCarga> registrosCarga) {
        this.registrosCarga = registrosCarga;
    }

    public String getTipoCarga(CarCargaRegistro obj) {
        RtConstantesCargas constantesCargas = new RtConstantesCargas();  // Crear instancia
        return constantesCargas.getTipoCarga(obj.getTipo());  // Llamar al método no estático
    }

    public Set<String> getTiposDeCargaMostrados() {
        return tiposDeCargaMostrados;
    }

    public void setTiposDeCargaMostrados(Set<String> tiposDeCargaMostrados) {
        this.tiposDeCargaMostrados = tiposDeCargaMostrados;
    }

    /**
     *
     * @param idEstado
     * @return
     */
    public String getEstadoArchivo(Integer idEstado) {
        if (idEstado == null) {
            return "Estado no especificado";
        }
        return RtConstantesCargas.getEstado(idEstado);
    }

    public List<CarCargaRegistro> getListaTipoCargas() {
        return listaTipoCargas;
    }

    public void setListaTipoCargas(List<CarCargaRegistro> listaTipoCargas) {
        this.listaTipoCargas = listaTipoCargas;
    }

    public List<JsonDatoDTO> getDatosFilaEditables() {
        return datosFilaEditables;
    }

    public void setDatosFilaEditables(List<JsonDatoDTO> datosFilaEditables) {
        this.datosFilaEditables = datosFilaEditables;
    }

    public void listar() {
        setListaPrestadores(new ArrayList<>());
        setListaProcesos(new ArrayList<>());
        super.setAccion(ACCION_LISTAR);
        procesoFinal();

    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGestionServicio().Accion(this);
    }

    public void ver(CarCargaRegistro obj) {
        setObjeto(obj);
        super.setAccion(ACCION_VER);
        getGestionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {

        super.setAccion(ACCION_CREAR);
        getGestionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getGestionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(CarCargaRegistro obj) {
        getObjeto().setId(obj.getId());
        super.setAccion(ACCION_EDITAR);
        getGestionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getGestionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getGestionServicio().Accion(this);
        procesoFinal();
    }

    public void verCargas(CarCargaRegistro obj) {
        setObjeto(obj);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_VER);
        getGestionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerCargas");
        PrimeFaces.current().executeScript("PF('dialogoVerCargas').show()");
        procesoFinal();
    }

    public void verFila(CarCargaRegistro obj) {
        setObjeto(obj);
        setDatosFilaEditables(new ArrayList<>());
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getGestionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerFila");
        PrimeFaces.current().executeScript("PF('dialogoVerFila').show()");
        procesoFinal();
    }

    public void editarCarga(CarCargaRegistro obj) {
        getObjeto().setId(obj.getId());
        setDatosFilaEditables(new ArrayList<>());
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getGestionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarCargas");
        PrimeFaces.current().executeScript("PF('dialogoEditarCarga').show()");
        procesoFinal();
    }

    public void modificarFila() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getGestionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarCarga').hide();");
        }
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmGestion");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_EDITAR:
                            crearLog("Editar Fila Carga", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog("Modificar Fila Carga", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerCargas");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

}
