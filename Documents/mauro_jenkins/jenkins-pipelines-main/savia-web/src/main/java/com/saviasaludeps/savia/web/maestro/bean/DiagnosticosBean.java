/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.maestro.servicio.DiagnosticosServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.DiagnosticosServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.HashMap;
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
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class DiagnosticosBean extends Url {

    private DiagnosticosServicioIface diagnosticosServicio;
    private MaDiagnostico objeto;
    private List<MaDiagnostico> registros;
    private LazyDataModel<MaDiagnostico> lazyDiagnosticos;
    //lista de Maestros
    private List<Maestro> listaCapitulo;
    private HashMap<Integer, Maestro> hashCapitulo;
    private List<Maestro> listaCategoria;
    private HashMap<Integer, Maestro> hashCategoria;
    //2025-05-06 jyperez nuevo maestro DenominacionCac
    private List<Maestro> listaDenominacionCac;
    private HashMap<Integer, Maestro> hashDenominacionCac;

    public DiagnosticosBean() {
        this.objeto = new MaDiagnostico();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_DIAGNOSTICOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyDiagnosticos = new LazyDataModel<MaDiagnostico>() {
                private List<MaDiagnostico> listaDiagnosticos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaDiagnostico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaDiagnosticos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaDiagnosticos;
                }

                @Override
                public String getRowKey(MaDiagnostico reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaDiagnostico getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaDiagnostico diagnosticos : listaDiagnosticos) {
                        if (id.equals(diagnosticos.getId())) {
                            return diagnosticos;
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
        //jyperez aca se llama la carga inicial
        getDiagnosticosServicio().cargaInicial(this);
        listar();
    }

    public DiagnosticosServicioIface getDiagnosticosServicio() {
        if (diagnosticosServicio == null) {
            diagnosticosServicio = new DiagnosticosServicioImpl();
        }
        return diagnosticosServicio;
    }

    public void setEspecialidadesServicio(DiagnosticosServicioIface diagnosticosServicio) {
        this.diagnosticosServicio = diagnosticosServicio;
    }

    public MaDiagnostico getObjeto() {
        return objeto;
    }

    public void setObjeto(MaDiagnostico objeto) {
        this.objeto = objeto;
    }

    public List<MaDiagnostico> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaDiagnostico> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaDiagnostico> getLazyDiagnosticos() {
        return lazyDiagnosticos;
    }

    public void setLazyDiagnosticos(LazyDataModel<MaDiagnostico> lazyDiagnosticos) {
        this.lazyDiagnosticos = lazyDiagnosticos;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getDiagnosticosServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getDiagnosticosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getDiagnosticosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getDiagnosticosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getDiagnosticosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getDiagnosticosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getDiagnosticosServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmDiagnosticos");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmDiagnosticos");
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

    public String getValorBandera(Boolean valor) {
        String mensaje = "";
        if (valor != null) {
            if (valor) {
                mensaje = "Si";
            } else {
                mensaje = "No";
            }
        }
        return mensaje;
    }

    public String getSexoAplica(int valor) {
        String mensaje = "";
        switch (valor) {
            case 0:
                mensaje = "Masculino";
                break;
            case 1:
                mensaje = "Femenino";
                break;
            case 2:
                mensaje = "Ambos";
                break;
        }
        return mensaje;
    }

    /**
     * @return the listaCapitulo
     */
    public List<Maestro> getListaCapitulo() {
        return listaCapitulo;
    }

    /**
     * @param listaCapitulo the listaCapitulo to set
     */
    public void setListaCapitulo(List<Maestro> listaCapitulo) {
        this.listaCapitulo = listaCapitulo;
    }

    /**
     * @return the hashCapitulo
     */
    public HashMap<Integer, Maestro> getHashCapitulo() {
        return hashCapitulo;
    }

    /**
     * @param hashCapitulo the hashCapitulo to set
     */
    public void setHashCapitulo(HashMap<Integer, Maestro> hashCapitulo) {
        this.hashCapitulo = hashCapitulo;
    }

    /**
     * @return the listaCategoria
     */
    public List<Maestro> getListaCategoria() {
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<Maestro> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    /**
     * @return the hashCategoria
     */
    public HashMap<Integer, Maestro> getHashCategoria() {
        return hashCategoria;
    }

    /**
     * @param hashCategoria the hashCategoria to set
     */
    public void setHashCategoria(HashMap<Integer, Maestro> hashCategoria) {
        this.hashCategoria = hashCategoria;
    }

    /**
     * @return the listaDenominacionCac
     */
    public List<Maestro> getListaDenominacionCac() {
        return listaDenominacionCac;
    }

    /**
     * @param listaDenominacionCac the listaDenominacionCac to set
     */
    public void setListaDenominacionCac(List<Maestro> listaDenominacionCac) {
        this.listaDenominacionCac = listaDenominacionCac;
    }

    /**
     * @return the hashDenominacionCac
     */
    public HashMap<Integer, Maestro> getHashDenominacionCac() {
        return hashDenominacionCac;
    }

    /**
     * @param hashDenominacionCac the hashDenominacionCac to set
     */
    public void setHashDenominacionCac(HashMap<Integer, Maestro> hashDenominacionCac) {
        this.hashDenominacionCac = hashDenominacionCac;
    }

}
