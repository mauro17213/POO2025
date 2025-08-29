/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.informe.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfProcedimiento;
import com.saviasaludeps.savia.dominio.informe.InfRoutine;
import com.saviasaludeps.savia.web.informe.servicio.ProcedimientosServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author aguevara
 */
@ManagedBean
@ViewScoped
public class ProcedimientosBean extends Url {

    private ProcedimientosServicioIface procedimientosServicio;
    private InfProcedimiento objeto;
    private List<InfProcedimiento> registros;
    private LazyDataModel<InfProcedimiento> lazyProcedimiento;
    private ParamConsulta paramConsultaGrupo;

    private InfRoutine objetoInfRoutine;
    private List<InfRoutine> registrosRoutines;
    private LazyDataModel<InfRoutine> lazyInfRoutine;
    private ParamConsulta paramConsultaInfRoutine;

    //Variables auxiliares
    private String scriptTexto;
    private String textoAmpliado;

    public ProcedimientosBean() {
        this.objeto = new InfProcedimiento();
        Modulo _mod = super.validarModulo(Modulo.ID_INFORMES_PROCEDIMIENTOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            paramConsultaGrupo = new ParamConsulta();
            lazyProcedimiento = new LazyDataModel<InfProcedimiento>() {

                private List<InfProcedimiento> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaGrupo().getCantidadRegistros();
                }

                @Override
                public List<InfProcedimiento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaGrupo().setPrimerRegistro(primerRegistro);
                    getParamConsultaGrupo().setRegistrosPagina(registrosPagina);
                    getParamConsultaGrupo().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaGrupo().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsultaGrupo().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(InfProcedimiento objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public InfProcedimiento getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (InfProcedimiento objeto : lista) {
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
        getProcedimientosServicio().cargaInicial(this);
        listar();
    }

    public ProcedimientosServicioIface getProcedimientosServicio() {
        return procedimientosServicio;
    }

    public void setProcedimientosServicio(ProcedimientosServicioIface procedimientosServicio) {
        this.procedimientosServicio = procedimientosServicio;
    }

    public InfProcedimiento getObjeto() {
        return objeto;
    }

    public void setObjeto(InfProcedimiento objeto) {
        this.objeto = objeto;
    }

    public List<InfProcedimiento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<InfProcedimiento> registros) {
        this.registros = registros;
    }

    public LazyDataModel<InfProcedimiento> getLazyProcedimiento() {
        return lazyProcedimiento;
    }

    public void setLazyProcedimiento(LazyDataModel<InfProcedimiento> lazyProcedimiento) {
        this.lazyProcedimiento = lazyProcedimiento;
    }

    public ParamConsulta getParamConsultaGrupo() {
        return paramConsultaGrupo;
    }

    public void setParamConsultaGrupo(ParamConsulta paramConsultaGrupo) {
        this.paramConsultaGrupo = paramConsultaGrupo;
    }

    public String getScriptTexto() {
        return scriptTexto;
    }

    public void setScriptTexto(String scriptTexto) {
        this.scriptTexto = scriptTexto;
    }

    public InfRoutine getInfRoutine() {
        return objetoInfRoutine;
    }

    public void setInfRoutine(InfRoutine infRoutine) {
        this.objetoInfRoutine = infRoutine;
    }

    public List<InfRoutine> getRegistrosRoutines() {
        return registrosRoutines;
    }

    public void setRegistrosRoutines(List<InfRoutine> registrosRoutines) {
        this.registrosRoutines = registrosRoutines;
    }

    public InfRoutine getObjetoInfRoutine() {
        return objetoInfRoutine;
    }

    public void setObjetoInfRoutine(InfRoutine objetoInfRoutine) {
        this.objetoInfRoutine = objetoInfRoutine;
    }

    public LazyDataModel<InfRoutine> getLazyInfRoutine() {
        return lazyInfRoutine;
    }

    public void setLazyInfRoutine(LazyDataModel<InfRoutine> lazyInfRoutine) {
        this.lazyInfRoutine = lazyInfRoutine;
    }

    public ParamConsulta getParamConsultaInfRoutine() {
        return paramConsultaInfRoutine;
    }

    public void setParamConsultaInfRoutine(ParamConsulta paramConsultaInfRoutine) {
        this.paramConsultaInfRoutine = paramConsultaInfRoutine;
    }

    public String getTextoAmpliado() {
        return textoAmpliado;
    }

    public void setTextoAmpliado(String textoAmpliado) {
        this.textoAmpliado = textoAmpliado;
    }
    
    

    public void cargaLazyRoutines() {
        this.objetoInfRoutine = new InfRoutine();
        paramConsultaInfRoutine = new ParamConsulta();
        lazyInfRoutine = new LazyDataModel<InfRoutine>() {
            private List<InfRoutine> listaRoutines;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaInfRoutine().getCantidadRegistros();
            }

            @Override
            public List<InfRoutine> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaInfRoutine().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaInfRoutine().setPrimerRegistro(primerRegistro);
                getParamConsultaInfRoutine().setRegistrosPagina(registrosPagina);
                getParamConsultaInfRoutine().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaInfRoutine().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));

                refrescar();
                listaRoutines = getRegistrosRoutines();

                // 💡 Esto es clave: asegúrate que el Bean también guarde esa lista para usarla en ver()
                setRegistrosRoutines(listaRoutines);

                setRowCount(getParamConsultaInfRoutine().getCantidadRegistros());
                return listaRoutines;
            }

            @Override
            public String getRowKey(InfRoutine specificName) {
                return specificName.getSpecificName();
            }

            @Override
            public InfRoutine getRowData(String specificName) {

                for (InfRoutine routines : listaRoutines) {
                    if (specificName.equals(routines.getSpecificName())) {
                        return routines;
                    }
                }
                return null;
            }

        };
    }

    public void refrescar() {
        //cargaLazyRoutines();
        super.setAccion(Url.ACCION_LISTAR);
        getProcedimientosServicio().Accion(this);
    }

    public void listar() {
        cargaLazyRoutines();
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(String specificName) {
        if (specificName == null) {
            addError("El parámetro specificName está llegando nulo.");
            return;
        }

        if (getRegistrosRoutines() == null) {
            addError("La lista de registros routines está vacía o no ha sido cargada.");
            return;
        }

        for (InfRoutine r : getRegistrosRoutines()) {
            if (specificName.equals(r.getSpecificName())) {
                setObjetoInfRoutine(r);
                break;
            }
        }

        if (getObjetoInfRoutine() == null) {
            addError("No se encontró el procedimiento con nombre específico: " + specificName);
            return;
        }

        super.setAccion(ACCION_VER);
        getProcedimientosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        setScriptTexto("");
        super.setAccion(ACCION_CREAR);
        getProcedimientosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getProcedimientosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(InfRoutine obj) {
        setObjetoInfRoutine(obj);
        super.setAccion(ACCION_EDITAR);
        getProcedimientosServicio().Accion(this);
        if (objeto.getScript() != null) {
            scriptTexto = new String(objeto.getScript(), StandardCharsets.UTF_8);
        }
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getProcedimientosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            PrimeFaces.current().ajax().update("frmProcedimientos");
        }
        procesoFinal();
    }

    public void borrar(InfRoutine obj) {
        setObjetoInfRoutine(obj);
        super.setAccion(ACCION_BORRAR);
        getProcedimientosServicio().Accion(this);
        procesoFinal();
    }
    
     public void verHistorial(String specificName) {
        for (InfRoutine r : getRegistrosRoutines()) {
            if (specificName.equals(r.getSpecificName())) {
                setObjetoInfRoutine(r);
                break;
            }
        }
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_VER);
        getProcedimientosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerHistorial");
        PrimeFaces.current().executeScript("PF('dialogoVerHistorial').show()");
        procesoFinal();
    }
     
     public void verTextoAmpliado(String texto) {
        setTextoAmpliado(texto);
        PrimeFaces.current().resetInputs("frmTextoAmpliado");
        PrimeFaces.current().ajax().update("frmTextoAmpliado");
        PrimeFaces.current().executeScript("PF('dialogoTextoApliado').show();");
    }
     
     public void verScriptAmpliado(InfProcedimiento script) {
        String texto = new String(script.getScript(), StandardCharsets.UTF_8);
        setTextoAmpliado(texto);
        PrimeFaces.current().resetInputs("frmTextoAmpliado");
        PrimeFaces.current().ajax().update("frmTextoAmpliado");
        PrimeFaces.current().executeScript("PF('dialogoTextoApliado').show();");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmProcedimientos");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:

                    break;
            }
        }
        generarMensajes();
    }

}
