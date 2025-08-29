/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadorMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.web.maestro.servicio.MedicamentosServicioIface;
import com.saviasaludeps.savia.web.maestro.servicio.MedicamentosServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.ArrayList;
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
public class MedicamentosBean extends Url {

    private MedicamentosServicioIface medicamentosServicio;
    private MaMedicamento objeto;
    private List<MaMedicamento> registros;
    private LazyDataModel<MaMedicamento> lazyMedicamentos;

    // buscador de agrupadores
    private List<MaAgrupadorMedicamento> agrupadores;
    private HashMap<Integer, MaAgrupadorMedicamento> agrupadoresRecursivo;
    //lista de Maestros
    private List<Maestro> listaTipos;
    private HashMap<Integer, Maestro> hashTipos;
    private List<Maestro> listaConcentracion;
    private HashMap<Integer, Maestro> hashConcentracion;
    private List<Maestro> listaFormaFarmeceutica;
    private HashMap<Integer, Maestro> hashFormaFarmeceutica;
    private List<Maestro> listaPrincipioActivo;
    private HashMap<Integer, Maestro> hashPrincipioActivo;
    private List<Maestro> listaTipoPpm;
    private HashMap<Integer, Maestro> hashTipoPpm;
    //2021-06-21 jyperez Sprint 1 nuevos maestros campos adicionales
    private List<Maestro> listaGrupoAnatomico;
    private HashMap<Integer, Maestro> hashGrupoAnatomico;
    private List<Maestro> listaGrupoTerapeutico;
    private HashMap<Integer, Maestro> hashGrupoTerapeutico;
    private List<Maestro> listaATC;
    private HashMap<Integer, Maestro> hashATC;
    //2021-06-28 jyperez nuevo maestro estado registro sanitario
    private List<Maestro> listaEstadoRegistroSanitario;
    private HashMap<Integer, Maestro> hashEstadoRegistroSanitario;
    //2021-04-22 jyperez Se adiciona variable que almacenara estado de la tecnologia
    private boolean estadoInicialTecnologia;

    public MedicamentosBean() {
        this.objeto = new MaMedicamento();
        Modulo mod = super.validarModulo(Modulo.ID_MAESTRO_MEDICAMENTOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyMedicamentos = new LazyDataModel<MaMedicamento>() {
                private List<MaMedicamento> listaMedicamentos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaMedicamento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaMedicamentos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaMedicamentos;
                }

                @Override
                public String getRowKey(MaMedicamento reporte) {
                    return reporte.getId().toString();
                }

                @Override
                public MaMedicamento getRowData(String reporteId) {
                    Integer id = Integer.valueOf(reporteId);
                    for (MaMedicamento medicamento : listaMedicamentos) {
                        if (id.equals(medicamento.getId())) {
                            return medicamento;
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
        getMedicamentosServicio().cargaInicial(this);
        listar();
    }

    public MedicamentosServicioIface getMedicamentosServicio() {
        if (medicamentosServicio == null) {
            medicamentosServicio = new MedicamentosServicioImpl();
        }
        return medicamentosServicio;
    }

    public void setMedicamentosServicio(MedicamentosServicioIface medicamentosServicio) {
        this.medicamentosServicio = medicamentosServicio;
    }

    public MaMedicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(MaMedicamento objeto) {
        this.objeto = objeto;
    }

    public List<MaMedicamento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaMedicamento> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaMedicamento> getLazyMedicamentos() {
        return lazyMedicamentos;
    }

    public void setLazyMedicamentos(LazyDataModel<MaMedicamento> lazyMedicamentos) {
        this.lazyMedicamentos = lazyMedicamentos;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getMedicamentosServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getMedicamentosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getMedicamentosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getMedicamentosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getMedicamentosServicio().Accion(this);
        //2021-04-22 jyperez obtenemos el valor del estado del objeto
        estadoInicialTecnologia = objeto.isActivo();
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getMedicamentosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getMedicamentosServicio().Accion(this);
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
                PrimeFaces.current().ajax().update("frmMedicamentos");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmMedicamentos");
                break;
        }
        generarMensajes();
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

    public String getSexoAplica(Integer valor) {
        String mensaje = "";
        if (valor != null) {
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
        }
        return mensaje;
    }

    public List<MaAgrupadorMedicamento> completarAgrupador(String query) {
        List<MaAgrupadorMedicamento> agrupadoresFiltrados = new ArrayList<>();
        for (MaAgrupadorMedicamento agrupador : getAgrupadores()) {
            if (agrupador.getNombre().toLowerCase().contains(query.toLowerCase())) {
                agrupadoresFiltrados.add(agrupador);
            }
        }
        if (agrupadoresFiltrados.size() == 1) {
            getObjeto().setMaAgrupadorMedicamento(agrupadoresFiltrados.get(0));
            //actualizarSedes();
        }
        return agrupadoresFiltrados;
    }

    public String getAgrupadorRecursivo(int id) {
        String agrupadorStr = "";
        MaAgrupadorMedicamento _agrupador = null;
        if (getAgrupadoresRecursivo() != null) {
            _agrupador = getAgrupadoresRecursivo().get(id);
        }
        if (_agrupador != null) {
            agrupadorStr = _agrupador.getNombre();
        }
        return agrupadorStr;
    }

    public String getCobertura(Integer valor) {
        String mensaje = "";
        if (valor != null) {
            switch (valor) {
                case 1:
                    mensaje = "POS";
                    break;
                case 2:
                    mensaje = "NO POS";
                    break;
                case 3:
                    mensaje = "Condicional";
                    break;
            }
        }
        return mensaje;
    }

    /**
     * @return the agrupadores
     */
    public List<MaAgrupadorMedicamento> getAgrupadores() {
        return agrupadores;
    }

    /**
     * @param agrupadores the agrupadores to set
     */
    public void setAgrupadores(List<MaAgrupadorMedicamento> agrupadores) {
        this.agrupadores = agrupadores;
    }

    /**
     * @return the agrupadoresRecursivo
     */
    public HashMap<Integer, MaAgrupadorMedicamento> getAgrupadoresRecursivo() {
        return agrupadoresRecursivo;
    }

    /**
     * @param agrupadoresRecursivo the agrupadoresRecursivo to set
     */
    public void setAgrupadoresRecursivo(HashMap<Integer, MaAgrupadorMedicamento> agrupadoresRecursivo) {
        this.agrupadoresRecursivo = agrupadoresRecursivo;
    }

    /**
     * @return the hashFormaFarmeceutica
     */
    public HashMap<Integer, Maestro> getHashFormaFarmeceutica() {
        return hashFormaFarmeceutica;
    }

    /**
     * @param hashFormaFarmeceutica the hashFormaFarmeceutica to set
     */
    public void setHashFormaFarmeceutica(HashMap<Integer, Maestro> hashFormaFarmeceutica) {
        this.hashFormaFarmeceutica = hashFormaFarmeceutica;
    }

    /**
     * @return the listaPrincipioActivo
     */
    public List<Maestro> getListaPrincipioActivo() {
        return listaPrincipioActivo;
    }

    /**
     * @param listaPrincipioActivo the listaPrincipioActivo to set
     */
    public void setListaPrincipioActivo(List<Maestro> listaPrincipioActivo) {
        this.listaPrincipioActivo = listaPrincipioActivo;
    }

    /**
     * @return the hashPrincipioActivo
     */
    public HashMap<Integer, Maestro> getHashPrincipioActivo() {
        return hashPrincipioActivo;
    }

    /**
     * @param hashPrincipioActivo the hashPrincipioActivo to set
     */
    public void setHashPrincipioActivo(HashMap<Integer, Maestro> hashPrincipioActivo) {
        this.hashPrincipioActivo = hashPrincipioActivo;
    }

    /**
     * @return the listaTipoPpm
     */
    public List<Maestro> getListaTipoPpm() {
        return listaTipoPpm;
    }

    /**
     * @param listaTipoPpm the listaTipoPpm to set
     */
    public void setListaTipoPpm(List<Maestro> listaTipoPpm) {
        this.listaTipoPpm = listaTipoPpm;
    }

    /**
     * @return the hashTipoPpm
     */
    public HashMap<Integer, Maestro> getHashTipoPpm() {
        return hashTipoPpm;
    }

    /**
     * @param hashTipoPpm the hashTipoPpm to set
     */
    public void setHashTipoPpm(HashMap<Integer, Maestro> hashTipoPpm) {
        this.hashTipoPpm = hashTipoPpm;
    }

    /**
     * @return the listaTipos
     */
    public List<Maestro> getListaTipos() {
        return listaTipos;
    }

    /**
     * @param listaTipos the listaTipos to set
     */
    public void setListaTipos(List<Maestro> listaTipos) {
        this.listaTipos = listaTipos;
    }

    /**
     * @return the hashTipos
     */
    public HashMap<Integer, Maestro> getHashTipos() {
        return hashTipos;
    }

    /**
     * @param hashTipos the hashTipos to set
     */
    public void setHashTipos(HashMap<Integer, Maestro> hashTipos) {
        this.hashTipos = hashTipos;
    }

    /**
     * @return the listaConcetracion
     */
    public List<Maestro> getListaConcentracion() {
        return listaConcentracion;
    }

    /**
     * @param listaConcetracion the listaConcetracion to set
     */
    public void setListaConcentracion(List<Maestro> listaConcentracion) {
        this.listaConcentracion = listaConcentracion;
    }

    /**
     * @return the hashConcetracion
     */
    public HashMap<Integer, Maestro> getHashConcentracion() {
        return hashConcentracion;
    }

    /**
     * @param hashConcetracion the hashConcetracion to set
     */
    public void setHashConcentracion(HashMap<Integer, Maestro> hashConcentracion) {
        this.hashConcentracion = hashConcentracion;
    }

    /**
     * @return the listaFormaFarmeceutica
     */
    public List<Maestro> getListaFormaFarmeceutica() {
        return listaFormaFarmeceutica;
    }

    /**
     * @param listaFormaFarmeceutica the listaFormaFarmeceutica to set
     */
    public void setListaFormaFarmeceutica(List<Maestro> listaFormaFarmeceutica) {
        this.listaFormaFarmeceutica = listaFormaFarmeceutica;
    }

    /**
     * @return the estadoInicialTecnologia
     */
    public boolean isEstadoInicialTecnologia() {
        return estadoInicialTecnologia;
    }

    /**
     * @param estadoInicialTecnologia the estadoInicialTecnologia to set
     */
    public void setEstadoInicialTecnologia(boolean estadoInicialTecnologia) {
        this.estadoInicialTecnologia = estadoInicialTecnologia;
    }

    public void cargarRegulado() {

    }

    public void cargarDescripcionATC1() {
        if (this.objeto.getMaeAtc1Id() != null) {
            Maestro mae = hashATC.get(this.objeto.getMaeAtc1Id());
            if (mae != null) {
                this.objeto.setMaeAtc1Codigo(mae.getValor());
                this.objeto.setMaeAtc1Valor(mae.getNombre());
            } else {
                this.objeto.setMaeAtc1Codigo("");
                this.objeto.setMaeAtc1Valor("");
            }
        } else {
            this.objeto.setMaeAtc1Codigo("");
            this.objeto.setMaeAtc1Valor("");
        }
    }

    public void cargarDescripcionATC2() {
        if (this.objeto.getMaeAtc2Id() != null) {
            Maestro mae = hashATC.get(this.objeto.getMaeAtc2Id());
            if (mae != null) {
                this.objeto.setMaeAtc2Codigo(mae.getValor());
                this.objeto.setMaeAtc2Valor(mae.getNombre());
            } else {
                this.objeto.setMaeAtc2Codigo("");
                this.objeto.setMaeAtc2Valor("");
            }
        } else {
            this.objeto.setMaeAtc2Codigo("");
            this.objeto.setMaeAtc2Valor("");
        }
    }

    public void cargarDescripcionATC3() {
        if (this.objeto.getMaeAtc3Id() != null) {
            Maestro mae = hashATC.get(this.objeto.getMaeAtc3Id());
            if (mae != null) {
                this.objeto.setMaeAtc3Codigo(mae.getValor());
                this.objeto.setMaeAtc3Valor(mae.getNombre());
            } else {
                this.objeto.setMaeAtc3Codigo("");
                this.objeto.setMaeAtc3Valor("");
            }
        } else {
            this.objeto.setMaeAtc3Codigo("");
            this.objeto.setMaeAtc3Valor("");
        }
    }

    public void cargarDescripcionGrupoAnatomico() {
        if (this.objeto.getMaeGrupoAnatomicoPpalId() != null) {
            Maestro mae = hashGrupoAnatomico.get(this.objeto.getMaeGrupoAnatomicoPpalId());
            if (mae != null) {
                this.objeto.setMaeGrupoAnatomicoPpalCodigo(mae.getValor());
                this.objeto.setMaeGrupoAnatomicoPpalValor(mae.getNombre());
            } else {
                this.objeto.setMaeGrupoAnatomicoPpalCodigo("");
                this.objeto.setMaeGrupoAnatomicoPpalValor("");
            }
        } else {
            this.objeto.setMaeAtc3Codigo("");
            this.objeto.setMaeAtc3Valor("");
        }
    }

    public void cargarDescripcionGrupoTerapeutico() {
        if (this.objeto.getMaeGrupoTerapeuticoPpalId() != null) {
            Maestro mae = hashGrupoTerapeutico.get(this.objeto.getMaeGrupoTerapeuticoPpalId());
            if (mae != null) {
                this.objeto.setMaeGrupoTerapeuticoPpalCodigo(mae.getValor());
                this.objeto.setMaeGrupoTerapeuticoPpalValor(mae.getNombre());
            } else {
                this.objeto.setMaeGrupoTerapeuticoPpalCodigo("");
                this.objeto.setMaeGrupoTerapeuticoPpalValor("");
            }
        } else {
            this.objeto.setMaeGrupoTerapeuticoPpalCodigo("");
            this.objeto.setMaeGrupoTerapeuticoPpalValor("");
        }
    }

    public void cargarDescripcionConcentracion() {
        if (this.objeto.getMaeConcentracionId() != null) {
            Maestro mae = hashConcentracion.get(this.objeto.getMaeConcentracionId());
            if (mae != null) {
                this.objeto.setMaeConcentracionCodigo(mae.getValor());
                this.objeto.setMaeConcentracionValor(mae.getNombre());
            } else {
                this.objeto.setMaeConcentracionCodigo("");
                this.objeto.setMaeConcentracionValor("");
            }
        } else {
            this.objeto.setMaeConcentracionCodigo("");
            this.objeto.setMaeConcentracionValor("");
        }
    }

    public void cargarDescripcionPpioActivo() {
        if (this.objeto.getMaePrincipioActivoId() != null) {
            Maestro mae = hashPrincipioActivo.get(this.objeto.getMaePrincipioActivoId());
            if (mae != null) {
                this.objeto.setMaePrincipioActivoCodigo(mae.getValor());
                this.objeto.setMaePrincipioActivoValor(mae.getNombre());
            } else {
                this.objeto.setMaePrincipioActivoCodigo("");
                this.objeto.setMaePrincipioActivoValor("");
            }
        } else {
            this.objeto.setMaePrincipioActivoCodigo("");
            this.objeto.setMaePrincipioActivoValor("");
        }
    }

    public void cargarDescripcionFormaFarmaceutica() {
        if (this.objeto.getMaeFormaFarmaceuticaId() != null) {
            Maestro mae = hashFormaFarmeceutica.get(this.objeto.getMaeFormaFarmaceuticaId());
            if (mae != null) {
                this.objeto.setMaeFormaFarmaceuticaCodigo(mae.getValor());
                this.objeto.setMaeFormaFarmaceuticaValor(mae.getNombre());
            } else {
                this.objeto.setMaeFormaFarmaceuticaCodigo("");
                this.objeto.setMaeFormaFarmaceuticaValor("");
            }
        } else {
            this.objeto.setMaeFormaFarmaceuticaCodigo("");
            this.objeto.setMaeFormaFarmaceuticaValor("");
        }
    }

    public void cargarDescripcionRegistroSanitario() {
        if (this.objeto.getMaeFormaFarmaceuticaId() != null) {
            Maestro mae = hashEstadoRegistroSanitario.get(this.objeto.getMaeEstadoRegistroSanitarioId());
            if (mae != null) {
                this.objeto.setMaeEstadoRegistroSanitarioCodigo(mae.getValor());
                this.objeto.setMaeEstadoRegistroSanitarioValor(mae.getNombre());
            } else {
                this.objeto.setMaeEstadoRegistroSanitarioValor("");
                this.objeto.setMaeEstadoRegistroSanitarioCodigo("");
            }
        } else {
            this.objeto.setMaeEstadoRegistroSanitarioValor("");
            this.objeto.setMaeEstadoRegistroSanitarioCodigo("");
        }
    }

    public String getBooleanoStr(Boolean dato) {
        String mensaje = "";
        if (dato != null) {
            if (dato) {
                mensaje = "Si";
            } else {
                mensaje = "No";
            }
        }
        return mensaje;
    }

    public String getRegistroSanitario(Integer dato) {
        String mensaje = "";
        if (dato != null) {
            switch (dato) {
                case 1:
                    mensaje = "Activo";
                    break;
                case 2:
                    mensaje = "Inactivo";
                    break;
            }
        }
        return mensaje;
    }

    /**
     * @return the listaGrupoAnatomico
     */
    public List<Maestro> getListaGrupoAnatomico() {
        return listaGrupoAnatomico;
    }

    /**
     * @param listaGrupoAnatomico the listaGrupoAnatomico to set
     */
    public void setListaGrupoAnatomico(List<Maestro> listaGrupoAnatomico) {
        this.listaGrupoAnatomico = listaGrupoAnatomico;
    }

    /**
     * @return the hashGrupoAnatomico
     */
    public HashMap<Integer, Maestro> getHashGrupoAnatomico() {
        return hashGrupoAnatomico;
    }

    /**
     * @param hashGrupoAnatomico the hashGrupoAnatomico to set
     */
    public void setHashGrupoAnatomico(HashMap<Integer, Maestro> hashGrupoAnatomico) {
        this.hashGrupoAnatomico = hashGrupoAnatomico;
    }

    /**
     * @return the listaGrupoTerapeutico
     */
    public List<Maestro> getListaGrupoTerapeutico() {
        return listaGrupoTerapeutico;
    }

    /**
     * @param listaGrupoTerapeutico the listaGrupoTerapeutico to set
     */
    public void setListaGrupoTerapeutico(List<Maestro> listaGrupoTerapeutico) {
        this.listaGrupoTerapeutico = listaGrupoTerapeutico;
    }

    /**
     * @return the hashGrupoTerapeutico
     */
    public HashMap<Integer, Maestro> getHashGrupoTerapeutico() {
        return hashGrupoTerapeutico;
    }

    /**
     * @param hashGrupoTerapeutico the hashGrupoTerapeutico to set
     */
    public void setHashGrupoTerapeutico(HashMap<Integer, Maestro> hashGrupoTerapeutico) {
        this.hashGrupoTerapeutico = hashGrupoTerapeutico;
    }

    /**
     * @return the listaATC
     */
    public List<Maestro> getListaATC() {
        return listaATC;
    }

    /**
     * @param listaATC the listaATC to set
     */
    public void setListaATC(List<Maestro> listaATC) {
        this.listaATC = listaATC;
    }

    /**
     * @return the hashATC
     */
    public HashMap<Integer, Maestro> getHashATC() {
        return hashATC;
    }

    /**
     * @param hashATC the hashATC to set
     */
    public void setHashATC(HashMap<Integer, Maestro> hashATC) {
        this.hashATC = hashATC;
    }

    /**
     * @return the listaEstadoRegistroSanitario
     */
    public List<Maestro> getListaEstadoRegistroSanitario() {
        return listaEstadoRegistroSanitario;
    }

    /**
     * @param listaEstadoRegistroSanitario the listaEstadoRegistroSanitario to
     * set
     */
    public void setListaEstadoRegistroSanitario(List<Maestro> listaEstadoRegistroSanitario) {
        this.listaEstadoRegistroSanitario = listaEstadoRegistroSanitario;
    }

    /**
     * @return the hashEstadoRegistroSanitario
     */
    public HashMap<Integer, Maestro> getHashEstadoRegistroSanitario() {
        return hashEstadoRegistroSanitario;
    }

    /**
     * @param hashEstadoRegistroSanitario the hashEstadoRegistroSanitario to set
     */
    public void setHashEstadoRegistroSanitario(HashMap<Integer, Maestro> hashEstadoRegistroSanitario) {
        this.hashEstadoRegistroSanitario = hashEstadoRegistroSanitario;
    }

}
