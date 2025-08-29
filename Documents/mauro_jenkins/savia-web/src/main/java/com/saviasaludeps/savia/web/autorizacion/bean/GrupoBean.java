/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoDiagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoPrograma;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoRegion;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoSede;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoTecnologia;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuGrupoServicioIface;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Stiven Giraldo
 */
@ManagedBean
@ViewScoped
public class GrupoBean extends Url {

    @Autowired
    private AuGrupoServicioIface auGrupoServicio;
    private AuGrupo objeto;
    private AuGrupoTecnologia objetoGrupoTecnologia;
    private List<AuGrupo> registros;
    private LazyDataModel<AuGrupo> lazyGrupos;

    //Variables para consultas
    private List<Usuario> listaUsuarios;
    private LazyDataModel<Usuario> lazyUsuarios;
    private List<CntPrestadorSede> listaSedes;
    private LazyDataModel<CntPrestadorSede> lazySedes;
    private List<AuAnexo3Item> listaCasos;
    private LazyDataModel<AuAnexo3Item> lazyCasos;
    private List<AuGrupoUsuario> listaGrupoUsuario;
    private LazyDataModel<AuGrupoUsuario> lazyGrupoUsuario;
    private List<AuGrupoSede> listaGrupoSedes;
    private LazyDataModel<AuGrupoSede> lazyGrupoSede;
    private List<AuGrupoDiagnostico> listaGrupoDiagnosticos;
    private LazyDataModel<AuGrupoDiagnostico> lazyGrupoDiagnosticos;
    private List<AuGrupoTecnologia> listaGrupoTecnologia;
    private LazyDataModel<AuGrupoTecnologia> lazyGrupoTecnologia;
    private List<AuGrupoUsuario> listaGrupoAuditores;
    private LazyDataModel<AuGrupoUsuario> lazyGrupoAuditores;
    private List<AuGrupoPrograma> listaGrupoProgramas;
    private LazyDataModel<AuGrupoPrograma> lazyGrupoProgramas;
    private List<PePrograma> listaProgramas;
    private LazyDataModel<PePrograma> lazyProgramas;

    //Beans
    private SelDiagnosticosBean diagnosticosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;

    //Listas auxiliares
    private List<Maestro> listaAmbitos;
    private HashMap<Integer, Maestro> hashAmbitos;
    private List<Maestro> listaRegiones;
    private HashMap<Integer, Maestro> hashRegiones;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaTipoAuditor;
    private HashMap<Integer, Maestro> hashTipoAuditor;
    private List<Maestro> listaSeguimientoServicio;
    private HashMap<Integer, Maestro> hashSeguimientoServicio;
    private List<Ubicacion> listaCiudades;
    private List<AuAnexo3Item> listaSeleccionCasos;
    private List<AuGrupoUsuario> listaUsuarioModificar;
    private List<AuGrupoSede> listaSedeModificar;
    private List<AuGrupoDiagnostico> listaDiagnosticoModificar;
    private List<AuGrupoTecnologia> listaTecnologiaModificar;
    private List<AuGrupoPrograma> listaProgramaModificar;
    private List<AuGrupo> listaGrupos;
    private List<Maestro> listaTipoTecnologia;
    

    public GrupoBean() {
        this.objeto = new AuGrupo();
        Modulo mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_GRUPOS);

        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyGrupos = new LazyDataModel<AuGrupo>() {

                private List<AuGrupo> lista;
                    
                   @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            } 

                @Override
                public List<AuGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuGrupo objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //Lazy Auditores
            lazyUsuarios = new LazyDataModel<Usuario>() {

                private List<Usuario> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarAuditores();
                    lista = getListaUsuarios();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(Usuario objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Usuario getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Usuario objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //Lazy Sedes
            lazySedes = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(2).setPrimerRegistro(primerRegistro);
                    getParamConsulta(2).setRegistrosPagina(registrosPagina);
                    getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSedes();
                    lista = getListaSedes();
                    setRowCount(getParamConsulta(2).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CntPrestadorSede objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntPrestadorSede objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //Programas
            lazyProgramas = new LazyDataModel<PePrograma>() {

                private List<PePrograma> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<PePrograma> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(10).setPrimerRegistro(primerRegistro);
                    getParamConsulta(10).setRegistrosPagina(registrosPagina);
                    getParamConsulta(10).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(10).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarProgramas();
                    lista = getListaProgramas();
                    setRowCount(getParamConsulta(10).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(PePrograma objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public PePrograma getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (PePrograma objeto : lista) {
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
        getAuGrupoServicio().cargaInicial(this);
        listar();
    }

    public AuGrupoServicioIface getAuGrupoServicio() {
        return auGrupoServicio;
    }

    public void setAuGrupoServicio(AuGrupoServicioIface auGrupoServicio) {
        this.auGrupoServicio = auGrupoServicio;
    }

    public AuGrupo getObjeto() {
        return objeto;
    }

    public void setObjeto(AuGrupo objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getListaTipoTecnologia() {
        return listaTipoTecnologia;
    }

    public void setListaTipoTecnologia(List<Maestro> listaTipoTecnologia) {
        this.listaTipoTecnologia = listaTipoTecnologia;
    }

    public List<AuGrupo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuGrupo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuGrupo> getLazyGrupos() {
        return lazyGrupos;
    }

    public void setLazyGrupos(LazyDataModel<AuGrupo> lazyGrupos) {
        this.lazyGrupos = lazyGrupos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<CntPrestadorSede> getListaSedes() {
        return listaSedes;
    }

    public void setListaSedes(List<CntPrestadorSede> listaSedes) {
        this.listaSedes = listaSedes;
    }

    public LazyDataModel<CntPrestadorSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<CntPrestadorSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public List<AuAnexo3Item> getListaCasos() {
        return listaCasos;
    }

    public void setListaCasos(List<AuAnexo3Item> listaCasos) {
        this.listaCasos = listaCasos;
    }

    public LazyDataModel<AuAnexo3Item> getLazyCasos() {
        return lazyCasos;
    }

    public void setLazyCasos(LazyDataModel<AuAnexo3Item> lazyCasos) {
        this.lazyCasos = lazyCasos;
    }

    public List<AuGrupoUsuario> getListaGrupoUsuario() {
        return listaGrupoUsuario;
    }

    public void setListaGrupoUsuario(List<AuGrupoUsuario> listaGrupoUsuario) {
        this.listaGrupoUsuario = listaGrupoUsuario;
    }

    public LazyDataModel<AuGrupoUsuario> getLazyGrupoUsuario() {
        return lazyGrupoUsuario;
    }

    public void setLazyGrupoUsuario(LazyDataModel<AuGrupoUsuario> lazyGrupoUsuario) {
        this.lazyGrupoUsuario = lazyGrupoUsuario;
    }

    public List<AuGrupoSede> getListaGrupoSedes() {
        return listaGrupoSedes;
    }

    public void setListaGrupoSedes(List<AuGrupoSede> listaGrupoSedes) {
        this.listaGrupoSedes = listaGrupoSedes;
    }

    public LazyDataModel<AuGrupoSede> getLazyGrupoSede() {
        return lazyGrupoSede;
    }

    public void setLazyGrupoSede(LazyDataModel<AuGrupoSede> lazyGrupoSede) {
        this.lazyGrupoSede = lazyGrupoSede;
    }

    public List<AuGrupoDiagnostico> getListaGrupoDiagnosticos() {
        return listaGrupoDiagnosticos;
    }

    public void setListaGrupoDiagnosticos(List<AuGrupoDiagnostico> listaGrupoDiagnosticos) {
        this.listaGrupoDiagnosticos = listaGrupoDiagnosticos;
    }

    public LazyDataModel<AuGrupoDiagnostico> getLazyGrupoDiagnosticos() {
        return lazyGrupoDiagnosticos;
    }

    public void setLazyGrupoDiagnosticos(LazyDataModel<AuGrupoDiagnostico> lazyGrupoDiagnosticos) {
        this.lazyGrupoDiagnosticos = lazyGrupoDiagnosticos;
    }

    public List<AuGrupoTecnologia> getListaGrupoTecnologia() {
        return listaGrupoTecnologia;
    }

    public void setListaGrupoTecnologia(List<AuGrupoTecnologia> listaGrupoTecnologia) {
        this.listaGrupoTecnologia = listaGrupoTecnologia;
    }

    public LazyDataModel<AuGrupoTecnologia> getLazyGrupoTecnologia() {
        return lazyGrupoTecnologia;
    }

    public void setLazyGrupoTecnologia(LazyDataModel<AuGrupoTecnologia> lazyGrupoTecnologia) {
        this.lazyGrupoTecnologia = lazyGrupoTecnologia;
    }

    public List<AuGrupoUsuario> getListaGrupoAuditores() {
        return listaGrupoAuditores;
    }

    public void setListaGrupoAuditores(List<AuGrupoUsuario> listaGrupoAuditores) {
        this.listaGrupoAuditores = listaGrupoAuditores;
    }

    public LazyDataModel<AuGrupoUsuario> getLazyGrupoAuditores() {
        return lazyGrupoAuditores;
    }

    public void setLazyGrupoAuditores(LazyDataModel<AuGrupoUsuario> lazyGrupoAuditores) {
        this.lazyGrupoAuditores = lazyGrupoAuditores;
    }

    public List<AuGrupoPrograma> getListaGrupoProgramas() {
        return listaGrupoProgramas;
    }

    public void setListaGrupoProgramas(List<AuGrupoPrograma> listaGrupoProgramas) {
        this.listaGrupoProgramas = listaGrupoProgramas;
    }

    public LazyDataModel<AuGrupoPrograma> getLazyGrupoProgramas() {
        return lazyGrupoProgramas;
    }

    public void setLazyGrupoProgramas(LazyDataModel<AuGrupoPrograma> lazyGrupoProgramas) {
        this.lazyGrupoProgramas = lazyGrupoProgramas;
    }

    public List<PePrograma> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<PePrograma> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public LazyDataModel<PePrograma> getLazyProgramas() {
        return lazyProgramas;
    }

    public void setLazyProgramas(LazyDataModel<PePrograma> lazyProgramas) {
        this.lazyProgramas = lazyProgramas;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public SelPaquetesBean getPaquetesBean() {
        paquetesBean = (SelPaquetesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPaquetesBean");
        return paquetesBean;
    }

    public void setPaquetesBean(SelPaquetesBean paquetesBean) {
        this.paquetesBean = paquetesBean;
    }

    public List<Maestro> getListaAmbitos() {
        return listaAmbitos;
    }

    public void setListaAmbitos(List<Maestro> listaAmbitos) {
        this.listaAmbitos = listaAmbitos;
    }

    public HashMap<Integer, Maestro> getHashAmbitos() {
        return hashAmbitos;
    }

    public void setHashAmbitos(HashMap<Integer, Maestro> hashAmbitos) {
        this.hashAmbitos = hashAmbitos;
    }

    public List<Maestro> getListaRegiones() {
        return listaRegiones;
    }

    public void setListaRegiones(List<Maestro> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }

    public HashMap<Integer, Maestro> getHashRegiones() {
        return hashRegiones;
    }

    public void setHashRegiones(HashMap<Integer, Maestro> hashRegiones) {
        this.hashRegiones = hashRegiones;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaTipoAuditor() {
        return listaTipoAuditor;
    }

    public void setListaTipoAuditor(List<Maestro> listaTipoAuditor) {
        this.listaTipoAuditor = listaTipoAuditor;
    }

    public HashMap<Integer, Maestro> getHashTipoAuditor() {
        return hashTipoAuditor;
    }

    public void setHashTipoAuditor(HashMap<Integer, Maestro> hashTipoAuditor) {
        this.hashTipoAuditor = hashTipoAuditor;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<AuAnexo3Item> getListaSeleccionCasos() {
        return listaSeleccionCasos;
    }

    public void setListaSeleccionCasos(List<AuAnexo3Item> listaSeleccionCasos) {
        this.listaSeleccionCasos = listaSeleccionCasos;
    }

    public List<AuGrupoUsuario> getListaUsuarioModificar() {
        return listaUsuarioModificar;
    }

    public void setListaUsuarioModificar(List<AuGrupoUsuario> listaUsuarioModificar) {
        this.listaUsuarioModificar = listaUsuarioModificar;
    }

    public List<AuGrupoSede> getListaSedeModificar() {
        return listaSedeModificar;
    }

    public void setListaSedeModificar(List<AuGrupoSede> listaSedeModificar) {
        this.listaSedeModificar = listaSedeModificar;
    }

    public List<AuGrupoDiagnostico> getListaDiagnosticoModificar() {
        return listaDiagnosticoModificar;
    }

    public void setListaDiagnosticoModificar(List<AuGrupoDiagnostico> listaDiagnosticoModificar) {
        this.listaDiagnosticoModificar = listaDiagnosticoModificar;
    }

    public List<AuGrupoTecnologia> getListaTecnologiaModificar() {
        return listaTecnologiaModificar;
    }

    public void setListaTecnologiaModificar(List<AuGrupoTecnologia> listaTecnologiaModificar) {
        this.listaTecnologiaModificar = listaTecnologiaModificar;
    }

    public List<AuGrupoPrograma> getListaProgramaModificar() {
        return listaProgramaModificar;
    }

    public void setListaProgramaModificar(List<AuGrupoPrograma> listaProgramaModificar) {
        this.listaProgramaModificar = listaProgramaModificar;
    }

    public List<AuGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<AuGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<Maestro> getListaSeguimientoServicio() {
        return listaSeguimientoServicio;
    }

    public void setListaSeguimientoServicio(List<Maestro> listaSeguimientoServicio) {
        this.listaSeguimientoServicio = listaSeguimientoServicio;
    }

    public HashMap<Integer, Maestro> getHashSeguimientoServicio() {
        return hashSeguimientoServicio;
    }

    public void setHashSeguimientoServicio(HashMap<Integer, Maestro> hashSeguimientoServicio) {
        this.hashSeguimientoServicio = hashSeguimientoServicio;
    }

    public AuGrupoTecnologia getObjetoGrupoTecnologia() {
        return objetoGrupoTecnologia;
    }

    public void setObjetoGrupoTecnologia(AuGrupoTecnologia objetoGrupoTecnologia) {
        this.objetoGrupoTecnologia = objetoGrupoTecnologia;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuGrupoServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        setObjeto(new AuGrupo(_id));
        inicializarListasAuxiliares();
        inicializarLazyGrupos();
        super.setAccion(ACCION_VER);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        inicializarListasAuxiliares();
        inicializarLazyGrupos();
        super.setAccion(ACCION_CREAR);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        unirListasModificar();
        super.setAccion(ACCION_GUARDAR);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void editar(int _id) {
        setObjeto(new AuGrupo(_id));
        inicializarListasAuxiliares();
        inicializarLazyGrupos();
        super.setAccion(ACCION_EDITAR);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        unirListasModificar();
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_MODIFICAR);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrearGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoCrearGrupo').show()");
                    crearLog("Crear", "Se inicializo una creción de grupo");
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmEditarGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoEditarGrupo').show()");
                    crearLog("Editar", getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrearGrupo').hide()");
                    PrimeFaces.current().ajax().update("frmGrupos");
                    crearLog("Crear", getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().executeScript("PF('dialogoEditarGrupo').hide()");
                            PrimeFaces.current().ajax().update("frmGrupos");
                            crearLog("Modificar", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmGrupos");
                            crearLog("Modificar", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    crearLog("Borrar", getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmReasignarGrupo");
                            PrimeFaces.current().executeScript("PF('dialogoReasignarGrupo').show()");
                            crearLog("Ver Reasignar", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            getObjeto().setAuditor(null);
                            setListaSeleccionCasos(new ArrayList());
                            PrimeFaces.current().ajax().update("frmReasignarGrupo");
                            crearLog("Reasignar", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVerGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoVerGrupo').show()");
                    break;
            }
        }
        generarMensajes();
    }

    public void llenarAmbitoGrupo() {
        Maestro ambito = obtenerMaestroAmbito(getObjeto().getMaeAmbitoId());
        if (ambito != null) {
            getObjeto().setMaeAmbitoCodigo(ambito.getValor());
            getObjeto().setMaeAmbitoValor(ambito.getNombre());
        } else {
            addError("Hubo un error cargando el ambito");
            generarMensajes();
        }
    }

    public Maestro obtenerMaestroAmbito(int id) {
        try {
            return getHashAmbitos().get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void consultarAuditores() {
        PrimeFaces.current().executeScript("PF('dialogoAuditoresLista').show()");
        PrimeFaces.current().ajax().update("frmAuditorLista");
    }

    public void refrescarAuditores() {
        getAuGrupoServicio().listarAuditores(this);
        generarMensajes();
    }

    public void onRowSelectAuditor(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        boolean agregar = true;
//        if (getObjeto().getId() != null) {
//            agregar = getAuGrupoServicio().validarAuditor(usuario, this);
//        }
//        if (agregar) {
//            for (AuGrupoUsuario user : getListaUsuarioModificar()) {
//                if (user.getUsuario().getId().equals(usuario.getId())) {
//                    agregar = false;
//                }
//            }
//        }
        if (agregar) {
            AuGrupoUsuario auditor = new AuGrupoUsuario();
            auditor.setActivo(true);
            auditor.setUsuario(usuario);
            getListaUsuarioModificar().add(auditor);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pAuditorCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pAuditorEditar");
            PrimeFaces.current().executeScript("PF('dialogoAuditoresLista').hide()");
        } else {
            addError("El auditor ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void borrarAuditor(AuGrupoUsuario auditorBorrar) {
        List<AuGrupoUsuario> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (AuGrupoUsuario auditor : getListaUsuarioModificar()) {
            if (auditor.equals(auditorBorrar)) {
                if (auditor.getId() != null) {
                    getAuGrupoServicio().borrarAuditor(auditor.getId());
                }
                eliminado = true;
                addMensaje("Se eliminó el auditor con éxito");
            } else {
                nuevaLista.add(auditor);
            }
        }
        setListaUsuarioModificar(nuevaLista);
        if (!eliminado) {
            getListaGrupoUsuario().stream()
                    .filter(auditor -> (auditor.equals(auditorBorrar)))
                    .filter(auditor -> (auditor.getId() != null))
                    .map(auditor -> {
                        getAuGrupoServicio().borrarAuditor(auditor.getId());
                        return auditor;
                    }).forEachOrdered(_item -> {
                addMensaje("Se eliminó el auditor con éxito");
            });
        }
        PrimeFaces.current().ajax().update("frmCrearGrupo:pAuditorCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:tablaAuditoresEditar");
        generarMensajes();
    }

    public void agregarRegion() {
        boolean agregar = true;
        for (AuGrupoRegion region : getObjeto().getAuGrupoRegionList()) {
            if (region.getMaeRegionId() == getObjeto().getIdRegion()) {
                agregar = false;
            }
        }
        if (agregar) {
            AuGrupoRegion region = new AuGrupoRegion();
            Maestro maestroRegion = getHashRegiones().get(getObjeto().getIdRegion());
            region.setMaeRegionId(maestroRegion.getId());
            region.setMaeRegionCodigo(maestroRegion.getValor());
            region.setMaeRegionValor(maestroRegion.getNombre());
            getObjeto().getAuGrupoRegionList().add(region);
            getObjeto().setIdRegion(0);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pRegionCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pRegionEditar");
        } else {
            addError("La región ya fue agregada");
            generarMensajes();
        }
    }

    public void consultarSedes() {
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        PrimeFaces.current().ajax().update("frmIpsLista");
    }

    public void consultarProgramas() {
        PrimeFaces.current().executeScript("PF('dialogoProgramasLista').show()");
        PrimeFaces.current().ajax().update("frmProgramasLista");
    }

    public void refrescarSedes() {
        getAuGrupoServicio().listarSedes(this);
        generarMensajes();
    }

    public void refrescarProgramas() {
        getAuGrupoServicio().listarProgramas(this);
        generarMensajes();
    }

    public void refrescarCasos() {
        getAuGrupoServicio().listarCasos(this);
        generarMensajes();
    }

    public void refrescarGrupoUsuario() {
        getAuGrupoServicio().listarGrupoUsuario(this);
        generarMensajes();
    }

    public void refrescarGrupoSede() {
        getAuGrupoServicio().listarGrupoSede(this);
        generarMensajes();
    }

    public void refrescarGrupoDiagnostico() {
        getAuGrupoServicio().listarGrupoDiagnostico(this);
        generarMensajes();
    }

    public void refrescarGrupoTecnologia() {
        getAuGrupoServicio().listarGrupoTecnologia(this);
        generarMensajes();
    }

    public void onRowSelectSede(SelectEvent event) {
        CntPrestadorSede sede = (CntPrestadorSede) event.getObject();
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarSede(sede, this);
        }
        if (agregar) {
            for (AuGrupoSede grupoSede : getListaSedeModificar()) {
                if (grupoSede.getCntPrestadorSede().getId().equals(sede.getId())) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            AuGrupoSede grupoSede = new AuGrupoSede();
            grupoSede.setCntPrestadorSede(sede);
            grupoSede.setCntPrestadoresId(sede.getCntPrestador());
            getListaSedeModificar().add(grupoSede);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pSedeCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pSedeEditar");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        } else {
            addMensaje("La sede ya se encuentra agregada");
            generarMensajes();
        }
    }

    public void onRowSelectPrograma(SelectEvent event) {
        PePrograma programa = (PePrograma) event.getObject();
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarPrograma(programa.getId(), this);
        }
        if (agregar) {
            for (AuGrupoPrograma grupoPrograma : getListaProgramaModificar()) {
                if (grupoPrograma.getPeProgramaId().getId().equals(programa.getId())) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            AuGrupoPrograma grupoPrograma = new AuGrupoPrograma();
            grupoPrograma.setPeProgramaId(programa);
            getListaProgramaModificar().add(grupoPrograma);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pProgramasCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pProgramasEditar");
            PrimeFaces.current().executeScript("PF('dialogoProgramasLista').hide()");
        } else {
            addMensaje("El programa ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void borrarSede(int idSede) {
        List<AuGrupoSede> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (AuGrupoSede sede : getListaSedeModificar()) {
            if (sede.getCntPrestadorSede().getId().equals(idSede)) {
                if (sede.getId() != null) {
                    getAuGrupoServicio().borrarSede(sede.getId());
                }
                eliminado = true;
                addMensaje("Se eliminó la sede con éxito");
            } else {
                nuevaLista.add(sede);
            }
        }
        setListaSedeModificar(nuevaLista);
        if (!eliminado) {
            for (AuGrupoSede sede : getListaGrupoSedes()) {
                if (sede.getCntPrestadorSede().getId().equals(idSede)) {
                    if (sede.getId() != null) {
                        getAuGrupoServicio().borrarSede(sede.getId());
                        addMensaje("Se eliminó la sede con éxito");
                    }
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearGrupo:pSedeCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:pSedeEditar");
        generarMensajes();
    }

    public void borrarPrograma(int idPrograma) {
        List<AuGrupoPrograma> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (AuGrupoPrograma programa : getListaProgramaModificar()) {
            if (programa.getPeProgramaId().getId().equals(idPrograma)) {
                if (programa.getId() != null) {
                    getAuGrupoServicio().borrarPrograma(programa.getId());
                }
                eliminado = true;
                addMensaje("Se eliminó el programa con éxito");
            } else {
                nuevaLista.add(programa);
            }
        }
        setListaProgramaModificar(nuevaLista);
        if (!eliminado) {
            for (AuGrupoPrograma programa : getListaGrupoProgramas()) {
                if (programa.getPeProgramaId().getId().equals(idPrograma)) {
                    if (programa.getId() != null) {
                        getAuGrupoServicio().borrarPrograma(programa.getId());
                        addMensaje("Se eliminó el programa con éxito");
                    }
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearGrupo:pProgramasCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:pProgramasEditar");
        generarMensajes();
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerTipoDocumentoIps(int id) {
        try {
            return hashTipoDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarDiagnostico(getDiagnosticosBean().getObjeto(), this);
        }
        if (agregar) {
            for (AuGrupoDiagnostico diagnostico : getListaDiagnosticoModificar()) {
                if (diagnostico.getMaDiagnosticoId() == getDiagnosticosBean().getObjeto().getId()) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            AuGrupoDiagnostico diagnostico = new AuGrupoDiagnostico();
            diagnostico.setMaDiagnosticoId(getDiagnosticosBean().getObjeto().getId());
            diagnostico.setMaDiagnosticoCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticoValor(getDiagnosticosBean().getObjeto().getNombre());
            getListaDiagnosticoModificar().add(diagnostico);
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrearGrupo:tablaDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pDiagnosticoEditar");
        } else {
            addError("El diagnóstico ya se encuentra agregada");
            generarMensajes();
        }
    }

    public void cerrarDialogoMedicamento() {
        boolean agregar = true;
        //intercambia medicamento por agrupador
        AuGrupoTecnologia grupo = new AuGrupoTecnologia();
        grupo.setTipoTecnologia(AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO);
        MaMedicamento medicamento = getMedicamentosBean().getObjeto();
        grupo.setMaTecnologiaId(medicamento.getMaAgrupadorMedicamento().getId());
        grupo.setMaTecnologiaCodigo(medicamento.getMaAgrupadorMedicamento().getCodigo());
        grupo.setMaTecnologiasValor(medicamento.getMaAgrupadorMedicamento().getNombre());

        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarTecnologia(grupo.getMaTecnologiaId(), AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO, this);
        }
        if (agregar) {
//            for (AuGrupoTecnologia medicamento : getListaTecnologiaModificar()) {
//                if (medicamento.getMaTecnologiaId().equals(getMedicamentosBean().getObjeto().getId())
//                        && medicamento.getTipoTecnologia() == AuGrupoTecnologia.ID_MEDICAMENTO) {
//                    agregar = false;
//                }
//            }
            agregar = getListaTecnologiaModificar().stream()
                    .noneMatch(tecno -> tecno.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO
                    && Objects.equals(tecno.getMaTecnologiaId(), grupo.getMaTecnologiaId()));
        }
        if (agregar) {
            //informacion de medicamenteo para uditoria
            grupo.setMaeMedicamentoId(medicamento.getId());
            grupo.setMaeMedicamentoCodigo(medicamento.getCum());
            grupo.setMaeMedicamentoValor(medicamento.getDescripcionEstandarizada());

            getListaTecnologiaModificar().add(grupo);
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrearGrupo:pTecnologiaCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pTecnologiaEditar");
        } else {
            addError("El medicamento ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void cerrarDialogoTecnologia() {
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarTecnologia(getTecnologiasBean().getObjeto().getId(), AuGrupoTecnologia.ID_TECNOLOGIA, this);
        }
        if (agregar) {
            for (AuGrupoTecnologia tecnologia : getListaTecnologiaModificar()) {
                if (tecnologia.getMaTecnologiaId().equals(getTecnologiasBean().getObjeto().getId())
                        && tecnologia.getTipoTecnologia() == AuGrupoTecnologia.ID_TECNOLOGIA) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            AuGrupoTecnologia tecnologia = new AuGrupoTecnologia();
            tecnologia.setMaTecnologiaId(getTecnologiasBean().getObjeto().getId());
            tecnologia.setMaTecnologiaCodigo(getTecnologiasBean().getObjeto().getCodigoPropio());
            tecnologia.setMaTecnologiasValor(getTecnologiasBean().getObjeto().getCupsDescipcion());
            tecnologia.setTipoTecnologia(AuGrupoTecnologia.ID_TECNOLOGIA);
            getListaTecnologiaModificar().add(tecnologia);
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrearGrupo:pTecnologiaCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pTecnologiaEditar");
        } else {
            addError("La tecnología ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void cerrarDialogoPaquete() {
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarTecnologia(getPaquetesBean().getObjeto().getId(), AuGrupoTecnologia.ID_PAQUETE, this);
        }
        if (agregar) {
            for (AuGrupoTecnologia paquete : getListaTecnologiaModificar()) {
                if (paquete.getMaTecnologiaId().equals(getPaquetesBean().getObjeto().getId())
                        && paquete.getTipoTecnologia() == AuGrupoTecnologia.ID_PAQUETE) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            AuGrupoTecnologia paquete = new AuGrupoTecnologia();
            paquete.setMaTecnologiaId(getPaquetesBean().getObjeto().getId());
            paquete.setMaTecnologiaCodigo(getPaquetesBean().getObjeto().getCodigo());
            paquete.setMaTecnologiasValor(getPaquetesBean().getObjeto().getNombre());
            paquete.setTipoTecnologia(AuGrupoTecnologia.ID_PAQUETE);
            getListaTecnologiaModificar().add(paquete);
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrearGrupo:pTecnologiaCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pTecnologiaEditar");
        } else {
            addError("El paquete ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void cerrarDialogoInsumo() {
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            agregar = getAuGrupoServicio().validarTecnologia(getInsumosBean().getObjeto().getId(), AuGrupoTecnologia.ID_INSUMO, this);
        }
        if (agregar) {
            for (AuGrupoTecnologia insumo : getListaTecnologiaModificar()) {
                if (insumo.getMaTecnologiaId().equals(getInsumosBean().getObjeto().getId())
                        && insumo.getTipoTecnologia() == AuGrupoTecnologia.ID_INSUMO) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            AuGrupoTecnologia insumo = new AuGrupoTecnologia();
            insumo.setMaTecnologiaId(getInsumosBean().getObjeto().getId());
            insumo.setMaTecnologiaCodigo(getInsumosBean().getObjeto().getCodigo());
            insumo.setMaTecnologiasValor(getInsumosBean().getObjeto().getDescripcion());
            insumo.setTipoTecnologia(AuGrupoTecnologia.ID_INSUMO);
            getListaTecnologiaModificar().add(insumo);
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrearGrupo:pTecnologiaCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pTecnologiaEditar");
        } else {
            addError("El insumo ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void consultarDiagnostico() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void consultarMedicamento() {
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
    }

    public void consultarProcedimiento() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void consultarInsumo() {
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
    }

    public void consultarPaquete() {
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
        PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
    }

    public void borrarDiagnostico(int idDiagnostico) {
        List<AuGrupoDiagnostico> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (AuGrupoDiagnostico diagnostico : getListaDiagnosticoModificar()) {
            if (diagnostico.getMaDiagnosticoId() == idDiagnostico) {
                if (diagnostico.getId() != null) {
                    getAuGrupoServicio().borrarDiagnostico(diagnostico.getId());
                }
                eliminado = true;
                addMensaje("Se eliminó el diagnostico con éxito");
            } else {
                nuevaLista.add(diagnostico);
            }
        }
        setListaDiagnosticoModificar(nuevaLista);
        if (!eliminado) {
            for (AuGrupoDiagnostico diagnostico : getListaGrupoDiagnosticos()) {
                if (diagnostico.getMaDiagnosticoId() == idDiagnostico) {
                    if (diagnostico.getId() != null) {
                        getAuGrupoServicio().borrarDiagnostico(diagnostico.getId());
                        addMensaje("Se eliminó el diagnostico con éxito");
                    }
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearGrupo:pDiagnosticoCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:pDiagnosticoEditar");
        generarMensajes();
    }

    public void borrarTecnologia(int idTecnologia) {
        List<AuGrupoTecnologia> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (AuGrupoTecnologia tecnologia : getListaTecnologiaModificar()) {
            if (tecnologia.getMaTecnologiaId().equals(idTecnologia)) {
                if (tecnologia.getId() != null) {
                    getAuGrupoServicio().borrarSede(tecnologia.getId());
                }
                eliminado = true;
                addMensaje("Se eliminó la tecnología con éxito");
            } else {
                nuevaLista.add(tecnologia);
            }
        }
        setListaTecnologiaModificar(nuevaLista);
        if (!eliminado) {
            for (AuGrupoTecnologia tecnologia : getListaGrupoTecnologia()) {
                if (tecnologia.getMaTecnologiaId().equals(idTecnologia)) {
                    if (tecnologia.getId() != null) {
                        getAuGrupoServicio().borrarTecnologia(tecnologia.getId());
                        addMensaje("Se eliminó la tecnología con éxito");
                    }
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearGrupo:pTecnologiaCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:pTecnologiaEditar");
        generarMensajes();
    }

    public void borrarRegion(int idRegion) {
        List<AuGrupoRegion> nuevaLista = new ArrayList();
        for (AuGrupoRegion region : getObjeto().getAuGrupoRegionList()) {
            if (region.getMaeRegionId() == idRegion) {
                if (region.getId() != null) {
                    getAuGrupoServicio().borrarRegion(region.getId());
                }
            } else {
                nuevaLista.add(region);
            }
        }
        getObjeto().setAuGrupoRegionList(nuevaLista);
        PrimeFaces.current().ajax().update("frmCrearGrupo:pRegionCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:pRegionEditar");
    }

    public void verReasginar(int id) {
        getObjeto().setId(id);
        inicializarListasAuxiliares();
        inicializarLazyGestionar();
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_VER);
        getAuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void reasignar() {
        if (getObjeto().getAuditor() == null) {
            addError("No se ha seleccionado el auditor");
        }
        if (getListaSeleccionCasos().isEmpty()) {
            addError("No se ha seleccionado ningún ítem");
        }
        if (getErrores().isEmpty()) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_MODIFICAR);
            getAuGrupoServicio().Accion(this);
            procesoFinal();
        } else {
            generarMensajes();
        }

    }

    public void inicializarLazyGestionar() {
        inicializarLazyUsuario();
        //Lazy casos
        lazyCasos = new LazyDataModel<AuAnexo3Item>() {

            private List<AuAnexo3Item> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<AuAnexo3Item> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros){
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCasos();
                lista = getListaCasos();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuAnexo3Item objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AuAnexo3Item getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuAnexo3Item objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };

    }

    public void inicializarLazyUsuario() {
        //Lazy Grupos Usuario
        lazyGrupoUsuario = new LazyDataModel<AuGrupoUsuario>() {

            private List<AuGrupoUsuario> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			
            @Override
            public List<AuGrupoUsuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(4).setPrimerRegistro(primerRegistro);
                getParamConsulta(4).setRegistrosPagina(registrosPagina);
                getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(4).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoUsuario();
                lista = getListaGrupoUsuario();
                setRowCount(getParamConsulta(4).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuGrupoUsuario objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AuGrupoUsuario getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuGrupoUsuario objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarLazyGrupos() {
        inicializarLazyUsuario();
        //Lazy Grupo Sede
        lazyGrupoSede = new LazyDataModel<AuGrupoSede>() {

            private List<AuGrupoSede> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			

            @Override
            public List<AuGrupoSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(5).setPrimerRegistro(primerRegistro);
                getParamConsulta(5).setRegistrosPagina(registrosPagina);
                getParamConsulta(5).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(5).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoSede();
                lista = getListaGrupoSedes();
                setRowCount(getParamConsulta(5).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuGrupoSede objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AuGrupoSede getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuGrupoSede objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
        //Lazy Grupo Diagnostico
        lazyGrupoDiagnosticos = new LazyDataModel<AuGrupoDiagnostico>() {

            private List<AuGrupoDiagnostico> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			

            @Override
            public List<AuGrupoDiagnostico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(6).setPrimerRegistro(primerRegistro);
                getParamConsulta(6).setRegistrosPagina(registrosPagina);
                getParamConsulta(6).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(6).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoDiagnostico();
                lista = getListaGrupoDiagnosticos();
                setRowCount(getParamConsulta(6).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuGrupoDiagnostico objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AuGrupoDiagnostico getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuGrupoDiagnostico objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
        //Lazy Grupo Tecnologia
        lazyGrupoTecnologia = new LazyDataModel<AuGrupoTecnologia>() {

            private List<AuGrupoTecnologia> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<AuGrupoTecnologia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(7).setPrimerRegistro(primerRegistro);
                getParamConsulta(7).setRegistrosPagina(registrosPagina);
                getParamConsulta(7).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(7).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoTecnologia();
                lista = getListaGrupoTecnologia();
                setRowCount(getParamConsulta(7).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuGrupoTecnologia objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AuGrupoTecnologia getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuGrupoTecnologia objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
        //Lazy Grupo Tecnologia
        lazyGrupoProgramas = new LazyDataModel<AuGrupoPrograma>() {

            private List<AuGrupoPrograma> lista;
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<AuGrupoPrograma> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(9).setPrimerRegistro(primerRegistro);
                getParamConsulta(9).setRegistrosPagina(registrosPagina);
                getParamConsulta(9).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(9).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoPrograma();
                lista = getListaGrupoProgramas();
                setRowCount(getParamConsulta(9).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuGrupoPrograma objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AuGrupoPrograma getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AuGrupoPrograma objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };

    }

    public boolean validarAuditorGrupoTipo(AuGrupoUsuario auditor) {
        boolean repetido = getAuGrupoServicio().validarAuditorTipo(auditor);
        if (false) {
            for (AuGrupoUsuario usuario : getListaUsuarioModificar()) {
                if (Objects.equals(usuario.getUsuario().getId(), auditor.getUsuario().getId())
                        && usuario.getMaeTipoAuditorId().equals(auditor.getMaeTipoAuditorId())) {
                    repetido = true;
                    break;
                }
            }
        }
        return repetido;
    }

    public void actualizarAuditor(AuGrupoUsuario auditor) {
        List<AuGrupoUsuario> nueva = new ArrayList();
        if (validarAuditorGrupoTipo(auditor)) {
            auditor.setMaeTipoAuditorId(null);
            addError("El usuario modificado ya cuenta con el tipo seleccionado.");
        } else {
            getListaUsuarioModificar().forEach(usuario -> {
                if (usuario.equals(auditor)) {
                    nueva.add(auditor);
                    addMensaje("Pendiente de presionar el botón de modificar");
                } else {
                    nueva.add(usuario);
                }
            });

            if (!nueva.isEmpty()) {
                setListaUsuarioModificar(nueva);
            }
            if (auditor.getId() != null) {
                for (AuGrupoUsuario usuario : getListaGrupoUsuario()) {
                    if (usuario.getId() != null && usuario.getId().equals(auditor.getId())) {
                        getListaUsuarioModificar().add(auditor);
                        addMensaje("Pendiente de presionar el botón de modificar");
                        break;
                    }
                }
                for (AuGrupoUsuario usuario : getObjeto().getAuGrupoUsuarioList()) {
                    if (usuario.getUsuario().getId().equals(auditor.getUsuario().getId())) {
                        getListaUsuarioModificar().add(auditor);
                        addMensaje("Pendiente de presionar el botón de modificar");
                        break;
                    }
                }
            }
        }
        generarMensajes();
    }

    public void actualizarTecnologia(AuGrupoTecnologia tecnologia) {
        List<AuGrupoTecnologia> nueva = new ArrayList();
        setObjetoGrupoTecnologia(tecnologia);
        //validacion de aplica seguimientos
        boolean estaMarcado = false;
        boolean agregarAplicaSeguimiento = false;
        if ((tecnologia.getAplicaSeguimiento() != null && tecnologia.getAplicaSeguimiento())
                && tecnologia.getMaeSeguimientoServicioId() != null) {
            estaMarcado = getAuGrupoServicio().validarTecnologiaTieneSeguimiento(this);
        }
        if ((tecnologia.getAplicaSeguimiento() != null && tecnologia.getAplicaSeguimiento())
                && tecnologia.getMaeSeguimientoServicioId() == null) {
            agregarAplicaSeguimiento = true;
            addMensaje("Seleccione Tipo Seguimiento");
        }
        if (tecnologia.getAplicaSeguimiento() == null
                && tecnologia.getMaeSeguimientoServicioId() != null) {
            agregarAplicaSeguimiento = true;
            addMensaje("Marque Seguimiento");
        }
        if (estaMarcado) {
            addError("Ya esta marcado como aplica seguimiento");
            tecnologia.setAplicaSeguimiento(false);
            tecnologia.setMaeSeguimientoServicioId(null);
            generarMensajes();
        } else if (agregarAplicaSeguimiento) {
            generarMensajes();
        } else {
            for (AuGrupoTecnologia tecno : getListaTecnologiaModificar()) {
                if (tecno.getMaTecnologiaId().equals(tecnologia.getMaTecnologiaId())) {
                    nueva.add(tecnologia);
                    addMensaje("Pendiente de presionar el botón de modificar");
                } else {
                    nueva.add(tecno);
                }
            }
            if (!nueva.isEmpty()) {
                setListaTecnologiaModificar(nueva);
                generarMensajes();
            }
            if (tecnologia.getId() != null) {
                for (AuGrupoTecnologia tecno : getListaGrupoTecnologia()) {
                    if (tecno.getId().equals(tecnologia.getId())) {
                        getListaTecnologiaModificar().add(tecnologia);
                        addMensaje("Pendiente de presionar el botón de modificar");
                        generarMensajes();
                        break;
                    }
                }
                for (AuGrupoTecnologia tecno : getObjeto().getAuGrupoTecnologiaList()) {
                    if (tecno.getMaTecnologiaId().equals(tecnologia.getMaTecnologiaId())) {
                        getListaTecnologiaModificar().add(tecnologia);
                        addMensaje("Pendiente de presionar el botón de modificar");
                        generarMensajes();
                        break;
                    }
                }
            }
        }
    }

    private void unirListasModificar() {
        getObjeto().setAuGrupoUsuarioList(getListaUsuarioModificar());
        getObjeto().setAuGrupoDiagnosticoList(getListaDiagnosticoModificar());
        getObjeto().setAuGrupoSedeList(getListaSedeModificar());
        getObjeto().setAuGrupoTecnologiaList(getListaTecnologiaModificar());
        getObjeto().setAuGrupoProgramasList(getListaProgramaModificar());
    }

    private void inicializarListasAuxiliares() {
        setListaUsuarioModificar(new ArrayList());
        setListaSedeModificar(new ArrayList());
        setListaDiagnosticoModificar(new ArrayList());
        setListaTecnologiaModificar(new ArrayList());
        setListaProgramaModificar(new ArrayList());
    }

    public void onRowReorder(ReorderEvent event) {
        int posicionMover = event.getFromIndex();
        int posicionMovida = event.getToIndex();
        AuGrupo grupoMover = registros.get(event.getFromIndex());
        AuGrupo grupoMovido = registros.get(event.getToIndex());
        getAuGrupoServicio().reordenar(posicionMover, posicionMovida, grupoMovido, grupoMover, this);
        setAccion(ACCION_MODIFICAR);
        setDoAccion(ACCION_ADICIONAL_1);
        procesoFinal();
    }

    public boolean validarGrupoGenerico() {
        return getAuGrupoServicio().validarGenerico(this);
    }

    public boolean validarGrupoTutela() {
        return getAuGrupoServicio().validarTutela(this);
    }

    public boolean validarGrupoPbs() {
        return getAuGrupoServicio().validarPbs(this);
    }

    public void refrescarGrupoPrograma() {
        getAuGrupoServicio().listarGrupoProgramas(this);
        generarMensajes();
    }

}
