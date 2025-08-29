/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.web.maestro.servicio.EspecialidadesServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.EspecialidadesServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class EspecialidadesBean extends Url {

    private EspecialidadesServicioIface especialidadesServicio;
    private MaEspecialidad objeto;
    private List<MaEspecialidad> registros;
    private LazyDataModel<MaEspecialidad> lazyEspecialidades;

    public EspecialidadesBean() {
        this.objeto = new MaEspecialidad();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_ESPECIALIDADES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyEspecialidades = new LazyDataModel<MaEspecialidad>() {
                private List<MaEspecialidad> listaEspecialidades;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaEspecialidad> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaEspecialidades = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaEspecialidades;
                }

                @Override
                public String getRowKey(MaEspecialidad reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaEspecialidad getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaEspecialidad especialidades : listaEspecialidades) {
                        if (id.equals(especialidades.getId())) {
                            return especialidades;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getEspecialidadesServicio().cargaInicial(this);
        listar();
    }

    public EspecialidadesServicioIface getEspecialidadesServicio() {
        if (especialidadesServicio == null) {
            especialidadesServicio = new EspecialidadesServicioImpl();
        }
        return especialidadesServicio;
    }

    public void setEspecialidadesServicio(EspecialidadesServicioIface especialidadesServicio) {
        this.especialidadesServicio = especialidadesServicio;
    }

    public MaEspecialidad getObjeto() {
        return objeto;
    }

    public void setObjeto(MaEspecialidad objeto) {
        this.objeto = objeto;
    }

    public List<MaEspecialidad> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaEspecialidad> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaEspecialidad> getLazyEspecialidades() {
        return lazyEspecialidades;
    }

    public void setLazyEspecialidades(LazyDataModel<MaEspecialidad> lazyEspecialidades) {
        this.lazyEspecialidades = lazyEspecialidades;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getEspecialidadesServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getEspecialidadesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getEspecialidadesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getEspecialidadesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getEspecialidadesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getEspecialidadesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getEspecialidadesServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmEspecialidades");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmEspecialidades");
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
