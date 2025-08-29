/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadoresMedicamentos;
import com.saviasaludeps.savia.web.maestro.servicio.AgrupadoresServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.AgrupadoresServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jarodriguez
 */
@Named
@ViewScoped
public class AgrupadoresMedicamentoBean extends Url {

    private AgrupadoresServicioIface agrupadoresServicio;
    private MaAgrupadoresMedicamentos objeto;
    private List<MaAgrupadoresMedicamentos> Registros;
    private LazyDataModel<MaAgrupadoresMedicamentos> LazyAgrupadores;

    public AgrupadoresMedicamentoBean() {
        this.objeto = new MaAgrupadoresMedicamentos();
        Modulo mod = super.validarModulo(Modulo.ID_AGRUPADOR_MEDICAMENTO);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            LazyAgrupadores = new LazyDataModel<MaAgrupadoresMedicamentos>() {
                private List<MaAgrupadoresMedicamentos> listaAgrupadores;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaAgrupadoresMedicamentos> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaAgrupadores = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaAgrupadores;
                }

                @Override
                public String getRowKey(MaAgrupadoresMedicamentos reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaAgrupadoresMedicamentos getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaAgrupadoresMedicamentos agrupadores : listaAgrupadores) {
                        if (id.equals(agrupadores.getId())) {
                            return agrupadores;
                        }
                    }
                    return null;
                }
            };
        }
    }

    public void construct() {
        getAgrupadoresServicio().cargaInicial(this);
        listar();
    }

    public AgrupadoresServicioIface getAgrupadoresServicio() {
        if (agrupadoresServicio == null) {
            agrupadoresServicio = new AgrupadoresServicioImpl();
        }
        return agrupadoresServicio;
    }

    /**
     *
     * @param agrupadoresServicio
     */
    public void setAgrupadoresServicio(AgrupadoresServicioIface agrupadoresServicio) {
        this.agrupadoresServicio = agrupadoresServicio;
    }

    public MaAgrupadoresMedicamentos getObjeto() {
        return objeto;
    }

    public void setObjeto(MaAgrupadoresMedicamentos objeto) {
        this.objeto = objeto;
    }

    public List<MaAgrupadoresMedicamentos> getRegistros() {
        return Registros;
    }

    public void setRegistros(List<MaAgrupadoresMedicamentos> registros) {
        this.Registros = registros;
    }

    public LazyDataModel<MaAgrupadoresMedicamentos> getLazyAgrupadores() {
        return LazyAgrupadores;
    }

    public void setLazyAgrupadores(LazyDataModel<MaAgrupadoresMedicamentos> lazyAgrupadores) {
        this.LazyAgrupadores = lazyAgrupadores;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAgrupadoresServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getAgrupadoresServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAgrupadoresServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getAgrupadoresServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getAgrupadoresServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAgrupadoresServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getAgrupadoresServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmAgrupadores");
                //crearLog(getObjeto().toString());
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmAgrupadores");
                break;
        }
        generarMensajes();
    }

    public String getActivo(boolean valor) {
        if (valor) {
            return "Si";
        } else {
            return "No";
        }
    }
}
