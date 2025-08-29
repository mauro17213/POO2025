/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeTipoVariable;
import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import com.saviasaludeps.savia.web.especial.servicio.GestionVariablesEspecificasServicioIface;

/**
 *
 * @author jdlopez
 */
@ManagedBean
@ViewScoped
public class GestionVariablesEspecificasBean extends Url {

    @Autowired
    private GestionVariablesEspecificasServicioIface gestionVariablesEspecificasServicio;
    private PeVariable variable;
    private List<PePrograma> programas;
    private PePrograma programa;
    private List<PeTipoVariable> listadoTipoVariables;
    private LazyDataModel<PeVariable> lazyVariables;
    private List<PeVariable> registros;
    private List<Maestro> listaSiNo;
    private String validacionesJson;
    private String insumoCalculoJson;
    private boolean mostrarPanelValidaciones;

    /*
     Metodos de inicializacion
     */
    public GestionVariablesEspecificasBean() {

        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_VARIABLES_ESPECIFICAS);

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            this.programas = new ArrayList<>();
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());

            this.lazyVariables = new LazyDataModel<PeVariable>() {
                private List<PeVariable> variables;

                @Override
                public int count(Map<String, FilterMeta> map) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeVariable> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    listar();
                    variables = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return variables;
                }

                @Override
                public String getRowKey(PeVariable variable) {
                    return variable.getId().toString();
                }

                @Override
                public PeVariable getRowData(String programaId) {
                    Integer id = Integer.valueOf(programaId);
                    for (PeVariable variable : variables) {
                        if (id.equals(variable.getId())) {
                            return variable;
                        }
                    }
                    return null;
                }
            };
        }

    }

    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        gestionVariablesEspecificasServicio.cargaInicial(this);
    }

    /*
     Metodos de logica y eventos
     */
    public void seleccionarPrograma() {
        if (Objects.nonNull(this.getParamConsulta().getParametroConsulta1())) {

            PePrograma programaEncontrado = this.getProgramas().stream()
                    .filter(p -> p.getId().equals(Integer.valueOf(this.getParamConsulta().getParametroConsulta1().toString())))
                    .findFirst().orElse(null);

            this.setPrograma(programaEncontrado);
        } else {
            this.getParamConsulta().setParametroConsulta1(null);
            this.setPrograma(null);
        }
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        gestionVariablesEspecificasServicio.Accion(this);
        this.procesoFinal();
    }

    public void crear() {
        if (this.programa == null) {
            this.addError("Error, debes seleccionar un programa para proceder a la creacion de variable");
            this.generarMensajes();
            return;
        }
        PrimeFaces.current().resetInputs("frmCrear");
        super.setAccion(ACCION_CREAR);
        gestionVariablesEspecificasServicio.Accion(this);
        procesoFinal();
    }

    public void detallePrograma() {
        PrimeFaces.current().executeScript("PF('dialogoDetallePrograma').show()");
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        gestionVariablesEspecificasServicio.Accion(this);
        this.procesoFinal();
    }

    public void ver(int id) {
        this.setValidacionesJson(null);
        this.setInsumoCalculoJson(null);
        this.variable.setId(id);
        super.setAccion(ACCION_VER);
        gestionVariablesEspecificasServicio.Accion(this);
        this.procesoFinal();
    }

    public void editar(int id) {
        PrimeFaces.current().resetInputs("frmEditar");
        this.setValidacionesJson(null);
        this.setInsumoCalculoJson(null);
        this.variable.setId(id);
        super.setAccion(ACCION_EDITAR);
        gestionVariablesEspecificasServicio.Accion(this);
        this.procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        gestionVariablesEspecificasServicio.Accion(this);
        this.procesoFinal();
    }

    public void borrar(int id) {
        this.variable.setId(id);
        super.setAccion(ACCION_BORRAR);
        gestionVariablesEspecificasServicio.Accion(this);
        this.procesoFinal();
    }

    private void restaurar() {
        PePrograma pePrograma = new PePrograma();
        PeVariable peVariable = new PeVariable();
        peVariable.setPePrograma(pePrograma);
        this.setVariable(peVariable);
    }

    private boolean validarMostrarPanelValidaciones(Integer tipoVariable) {
        return tipoVariable != null
                && !tipoVariable.equals(PeTipoVariable.TEXTO.getId())
                && !tipoVariable.equals(PeTipoVariable.TEXTO_LARGO.getId())
                && !tipoVariable.equals(PeTipoVariable.CALCULO.getId());
    }
  
    public void validarTipoVariable(Integer tipo){
        this.setMostrarPanelValidaciones(this.validarMostrarPanelValidaciones(tipo));
        this.setValidacionesJson(null);
        this.setInsumoCalculoJson(null);
    }

    private void procesoFinal() {
        switch (this.getAccion()) {
            case Url.ACCION_LISTAR:
                break;
            case Url.ACCION_VER:
                PrimeFaces.current().ajax().update("frmDetalleVariable");
                PrimeFaces.current().executeScript("PF('dialogoDetalleVariable').show()");
                break;
            case Url.ACCION_CREAR:
                PrimeFaces.current().ajax().update("frmCrear");
                PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                break;
            case Url.ACCION_GUARDAR:
                if (!super.isError()) {
                    PrimeFaces.current().ajax().update("frmSeleccionPrograma");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    this.getParamConsulta().setParametroConsulta1(null);
                    this.setPrograma(null);
                    this.setValidacionesJson(null);
                    this.setInsumoCalculoJson(null);
                    this.restaurar();
                }
                break;
            case Url.ACCION_EDITAR:
                this.setMostrarPanelValidaciones(this.validarMostrarPanelValidaciones(this.getVariable().getTipo())); 
                PrimeFaces.current().ajax().update("frmEditar");
                PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                break;
            case Url.ACCION_MODIFICAR:
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
                    this.restaurar();
                }
                break;

            case Url.ACCION_BORRAR:
                if (!super.isError()) {
                    this.restaurar();
                }
                break;
        }
        this.generarMensajes();
    }

    /*
     Metodos de conversion de datos
     */
    public String getRegistrosAfiliados(int id) {
        return PeConstantes.getRegistrosAfiliados(id);
    }

    public String getSexoAplicaDescripcion(Integer id) {
        return PeConstantes.getListaSexoAplicaDescripcion(id);
    }

    public String getCantidadRegistrosStr(Integer id) {
        return PeConstantes.getCantidadRegistrosStr(id);
    }

    /*
     Metodos getters y setters de atributos relacionados al bean
     */
    public PeVariable getVariable() {
        return variable;
    }

    public void setVariable(PeVariable variable) {
        this.variable = variable;
    }

    public List<PePrograma> getProgramas() {
        return programas;
    }

    public void setProgramas(List<PePrograma> programas) {
        this.programas = programas;
    }

    public PePrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PePrograma programa) {
        this.programa = programa;
    }

    public List<PeTipoVariable> getListadoTipoVariables() {
        return listadoTipoVariables;
    }

    public void setListadoTipoVariables(List<PeTipoVariable> listadoTipoVariables) {
        this.listadoTipoVariables = listadoTipoVariables;
    }

    public List<PeVariable> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeVariable> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PeVariable> getLazyVariables() {
        return lazyVariables;
    }

    public void setLazyVariables(LazyDataModel<PeVariable> lazyVariables) {
        this.lazyVariables = lazyVariables;
    }

    public List<Maestro> getListaSiNo() {
        return listaSiNo;
    }

    public void setListaSiNo(List<Maestro> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    public String getValidacionesJson() {
        return validacionesJson;
    }

    public void setValidacionesJson(String validacionesJson) {
        this.validacionesJson = validacionesJson;
    }

    public boolean isMostrarPanelValidaciones() {
        return mostrarPanelValidaciones;
    }

    public void setMostrarPanelValidaciones(boolean mostrarPanelValidaciones) {
        this.mostrarPanelValidaciones = mostrarPanelValidaciones;
    }

    public String getInsumoCalculoJson() {
        return insumoCalculoJson;
    }

    public void setInsumoCalculoJson(String insumoCalculoJson) {
        this.insumoCalculoJson = insumoCalculoJson;
    }
}
