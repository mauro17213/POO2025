/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeProgramaTecnologia;
import com.saviasaludeps.savia.dominio.especial.PeUsuariosPrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.especial.servicio.GestionProgramaServicioIface;
import com.saviasaludeps.savia.web.especial.servicio.GestionProgramaServicioImpl;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class GestionProgramaBean extends Url {

    private GestionProgramaServicioIface gestionProgramaServicio;
    private PePrograma objeto;
    private PeCarga objetoCarga;
    private List<PePrograma> registros;
    private LazyDataModel<PePrograma> lazyProgramas;
    private LazyDataModel<PeCarga> lazyCargaMasiva;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private LazyDataModel<Usuario> lazyUsuarios;
    private LazyDataModel<PeProgramaTecnologia> lazyProgramaTecnologias;
    private LazyDataModel<PeProgramaDiagnostico> lazyProgramaDiagnostico;
    private PeUsuariosPrograma objetoUsuario;
    private PeIpsPrograma objetoIps;
    private List<PeCarga> registrosCarga;

    private SelDiagnosticosBean diagnosticosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;
    private SelServiciosHabilitacionBean servicioHabilitacionBean;
    private SelEspecialidadesBean selEspecialidadesBean;

    //listas auxiliares
    private List<Maestro> listaProgramaTipo;
    private HashMap<Integer, Maestro> hashProgramaTipo;
    private List<Maestro> listaUsuarioResponsableTipo;
    private HashMap<Integer, Maestro> hashUsuarioResponsableTipo;
    private List<Maestro> listaProgramaCategoria;
    private HashMap<Integer, Maestro> hashProgramaCategoria;
    private List<Maestro> listaProgramaActivo;
    private List<Usuario> listaUsuario;
    private HashMap<Integer, Usuario> usuarioRecursiva;
    private List<CntPrestadorSede> listaIps;
    private HashMap<Integer, CntPrestadorSede> sedeRecursiva;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Ubicacion> listaCiudades;
    private List<Ubicacion> listaDepartamentos;
    private List<Maestro> listaMaeAgrupador;
    private HashMap<Integer, Maestro> hashListaMaeAgrupador;
    //2021-03-18 jyperez nuevas variables
    private boolean deshabilitarExoneracion;
    private List<Maestro> listaTipoRegistroAfiliado;
    private List<Maestro> listaSexoAplica;
    private List<Maestro> listaCantidadRegistro;
    private List<Maestro> listaTipoTecnologia;
    private List<Maestro> listaTipoCarga;
    private List<Maestro> listaEstadoCarga;

    public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
    public final static int TIPO_ALTO_COSTO = 1;

    private int contadorIdDiagnosticos;
    private Integer tipoSelectUsuario;

    public GestionProgramaBean() {
        contadorIdDiagnosticos = -1;
        this.objeto = new PePrograma();
        this.objetoCarga = new PeCarga();
        deshabilitarExoneracion = false;
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_GESTION_PROGRAMAS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyProgramas = new LazyDataModel<PePrograma>() {
                private List<PePrograma> programas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PePrograma> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    programas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return programas;
                }

                @Override
                public String getRowKey(PePrograma programa) {
                    return programa.getId().toString();
                }

                @Override
                public PePrograma getRowData(String programaId) {
                    Integer id = Integer.valueOf(programaId);
                    for (PePrograma programa : programas) {
                        if (id.equals(programa.getId())) {
                            return programa;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getGestionProgramaServicio().cargaInicial(this);
        listar();
    }

    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">
    public GestionProgramaServicioIface getGestionProgramaServicio() {
        if (gestionProgramaServicio == null) {
            gestionProgramaServicio = new GestionProgramaServicioImpl();
        }
        return gestionProgramaServicio;
    }

    public void setGestionProgramaServicio(GestionProgramaServicioIface gestionProgramaServicio) {
        this.gestionProgramaServicio = gestionProgramaServicio;
    }

    public PePrograma getObjeto() {
        return objeto;
    }

    public void setObjeto(PePrograma objeto) {
        this.objeto = objeto;
    }

    public List<PePrograma> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PePrograma> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PePrograma> getLazyProgramas() {
        return lazyProgramas;
    }

    public void setLazyProgramas(LazyDataModel<PePrograma> lazyProgramas) {
        this.lazyProgramas = lazyProgramas;
    }

    public PeUsuariosPrograma getObjetoUsuario() {
        return objetoUsuario;
    }

    public void setObjetoUsuario(PeUsuariosPrograma objetoUsuario) {
        this.objetoUsuario = objetoUsuario;
    }

    public PeIpsPrograma getObjetoIps() {
        return objetoIps;
    }

    public void setObjetoIps(PeIpsPrograma objetoIps) {
        this.objetoIps = objetoIps;
    }

    public List<Maestro> getListaTipoRegistroAfiliado() {
        return listaTipoRegistroAfiliado;
    }

    public void setListaTipoRegistroAfiliado(List<Maestro> listaTipoRegistroAfiliado) {
        this.listaTipoRegistroAfiliado = listaTipoRegistroAfiliado;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public HashMap<Integer, Usuario> getUsuarioRecursiva() {
        return usuarioRecursiva;
    }

    public void setUsuarioRecursiva(HashMap<Integer, Usuario> usuarioRecursiva) {
        this.usuarioRecursiva = usuarioRecursiva;
    }

    public List<CntPrestadorSede> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestadorSede> listaIps) {
        this.listaIps = listaIps;
    }

    public HashMap<Integer, CntPrestadorSede> getSedeRecursiva() {
        return sedeRecursiva;
    }

    public void setSedeRecursiva(HashMap<Integer, CntPrestadorSede> sedeRecursiva) {
        this.sedeRecursiva = sedeRecursiva;
    }

    /**
     * @return the deshabilitarExoneracion
     */
    public boolean isDeshabilitarExoneracion() {
        return deshabilitarExoneracion;
    }

    /**
     * @param deshabilitarExoneracion the deshabilitarExoneracion to set
     */
    public void setDeshabilitarExoneracion(boolean deshabilitarExoneracion) {
        this.deshabilitarExoneracion = deshabilitarExoneracion;
    }

    public List<Maestro> getListaProgramaTipo() {
        return listaProgramaTipo;
    }

    public void setListaProgramaTipo(List<Maestro> listaProgramaTipo) {
        this.listaProgramaTipo = listaProgramaTipo;
    }

    public List<Maestro> getListaUsuarioResponsableTipo() {
        return listaUsuarioResponsableTipo;
    }

    public void setListaUsuarioResponsableTipo(List<Maestro> listaUsuarioResponsableTipo) {
        this.listaUsuarioResponsableTipo = listaUsuarioResponsableTipo;
    }

    public HashMap<Integer, Maestro> getHashUsuarioResponsableTipo() {
        return hashUsuarioResponsableTipo;
    }

    public void setHashUsuarioResponsableTipo(HashMap<Integer, Maestro> hashUsuarioResponsableTipo) {
        this.hashUsuarioResponsableTipo = hashUsuarioResponsableTipo;
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

    public SelServiciosHabilitacionBean getServicioHabilitacionBean() {
        servicioHabilitacionBean = (SelServiciosHabilitacionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selServiciosHabilitacionBean");
        return servicioHabilitacionBean;
    }

    public void setServicioHabilitacionBean(SelServiciosHabilitacionBean servicioHabilitacionBean) {
        this.servicioHabilitacionBean = servicioHabilitacionBean;
    }

    public SelEspecialidadesBean getSelEspecialidadesBean() {
        selEspecialidadesBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return selEspecialidadesBean;
    }

    public void setSelEspecialidadesBean(SelEspecialidadesBean selEspecialidadesBean) {
        this.selEspecialidadesBean = selEspecialidadesBean;
    }

    public PeCarga getObjetoCarga() {
        return objetoCarga;
    }

    public void setObjetoCarga(PeCarga objetoCarga) {
        this.objetoCarga = objetoCarga;
    }

    public LazyDataModel<PeCarga> getLazyCargaMasiva() {
        return lazyCargaMasiva;
    }

    public void setLazyCargaMasiva(LazyDataModel<PeCarga> lazyCargaMasiva) {
        this.lazyCargaMasiva = lazyCargaMasiva;
    }

    public List<PeCarga> getRegistrosCarga() {
        return registrosCarga;
    }

    public List<Maestro> getListaProgramaCategoria() {
        return listaProgramaCategoria;
    }

    public void setListaProgramaCategoria(List<Maestro> listaProgramaCategoria) {
        this.listaProgramaCategoria = listaProgramaCategoria;
    }

    public void setRegistrosCarga(List<PeCarga> registrosCarga) {
        this.registrosCarga = registrosCarga;
    }

    public List<Maestro> getListaProgramaActivo() {
        return listaProgramaActivo;
    }

    public void setListaProgramaActivo(List<Maestro> listaProgramaActivo) {
        this.listaProgramaActivo = listaProgramaActivo;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestadorSede> lazyIps) {
        this.lazyIps = lazyIps;
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

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Ubicacion> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Ubicacion> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public HashMap<Integer, Maestro> getHashProgramaTipo() {
        return hashProgramaTipo;
    }

    public void setHashProgramaTipo(HashMap<Integer, Maestro> hashProgramaTipo) {
        this.hashProgramaTipo = hashProgramaTipo;
    }

    public HashMap<Integer, Maestro> getHashProgramaCategoria() {
        return hashProgramaCategoria;
    }

    public void setHashProgramaCategoria(HashMap<Integer, Maestro> hashProgramaCategoria) {
        this.hashProgramaCategoria = hashProgramaCategoria;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public LazyDataModel<PeProgramaTecnologia> getLazyProgramaTecnologias() {
        return lazyProgramaTecnologias;
    }

    public void setLazyProgramaTecnologias(LazyDataModel<PeProgramaTecnologia> lazyProgramaTecnologias) {
        this.lazyProgramaTecnologias = lazyProgramaTecnologias;
    }

    public LazyDataModel<PeProgramaDiagnostico> getLazyProgramaDiagnostico() {
        return lazyProgramaDiagnostico;
    }

    public void setLazyProgramaDiagnostico(LazyDataModel<PeProgramaDiagnostico> lazyProgramaDiagnostico) {
        this.lazyProgramaDiagnostico = lazyProgramaDiagnostico;
    }

    public List<Maestro> getListaMaeAgrupador() {
        return listaMaeAgrupador;
    }

    public void setListaMaeAgrupador(List<Maestro> listaMaeAgrupador) {
        this.listaMaeAgrupador = listaMaeAgrupador;
    }

    public HashMap<Integer, Maestro> getHashListaMaeAgrupador() {
        return hashListaMaeAgrupador;
    }

    public void setHashListaMaeAgrupador(HashMap<Integer, Maestro> hashListaMaeAgrupador) {
        this.hashListaMaeAgrupador = hashListaMaeAgrupador;
    }

    public List<Maestro> getListaSexoAplica() {
        return listaSexoAplica;
    }

    public void setListaSexoAplica(List<Maestro> listaSexoAplica) {
        this.listaSexoAplica = listaSexoAplica;
    }

    public List<Maestro> getListaCantidadRegistro() {
        return listaCantidadRegistro;
    }

    public void setListaCantidadRegistro(List<Maestro> listaCantidadRegistro) {
        this.listaCantidadRegistro = listaCantidadRegistro;
    }

    public List<Maestro> getListaTipoTecnologia() {
        return listaTipoTecnologia;
    }

    public void setListaTipoTecnologia(List<Maestro> listaTipoTecnologia) {
        this.listaTipoTecnologia = listaTipoTecnologia;
    }
    
    public String getTipoCarga(Integer idtipo){
        return PeConstantes.obtenerTipoCarga(idtipo);
    }

    public List<Maestro> getListaTipoCarga() {
        return listaTipoCarga;
    }

    public void setListaTipoCarga(List<Maestro> listaTipoCarga) {
        this.listaTipoCarga = listaTipoCarga;
    }

    public List<Maestro> getListaEstadoCarga() {
        return listaEstadoCarga;
    }

    public void setListaEstadoCarga(List<Maestro> listaEstadoCarga) {
        this.listaEstadoCarga = listaEstadoCarga;
    }
    

    // </editor-fold>
    //Metodos
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGestionProgramaServicio().Accion(this);
    }

    public void refrescarIps() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getGestionProgramaServicio().Accion(this);
    }
    
    public void listarCargasMasivas(){
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescarCargaMasiva() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGestionProgramaServicio().Accion(this);
    }

    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGestionProgramaServicio().Accion(this);
    }

    public void refrescarTecnologiasPrograma() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGestionProgramaServicio().Accion(this);
    }

    public void refrescarDiagnosticosPrograma() {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGestionProgramaServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmVer:tablaRegistrosI");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        deshabilitarExoneracion = false;
        super.setAccion(ACCION_CREAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getGestionProgramaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int id) {
        deshabilitarExoneracion = false;
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getGestionProgramaServicio().Accion(this);
        if (Integer.parseInt(getObjeto().getMaeTipoProgramaCodigo()) == TIPO_ALTO_COSTO) {
            deshabilitarExoneracion = true;
        }
        //PrimeFaces.current().resetInputs("frmEditarPrograma");
        PrimeFaces.current().ajax().update("frmEditarPrograma");
        PrimeFaces.current().ajax().update("frmEditarPrograma:tablaRegistrosIE");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getGestionProgramaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getGestionProgramaServicio().Accion(this);
        procesoFinal();
    }

    public void verTecnologias(PePrograma obj) {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_LISTAR);
        setObjeto(obj);
        contadorIdDiagnosticos = -1;
        cargarLazyTecnologiaPrograma();
        PrimeFaces.current().ajax().update("frmListarTecnologias:tablaTecnologiasCrear");
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmListarTecnologias:tablaTecnologiasCrear");
        dataTable.reset();
        PrimeFaces.current().resetInputs("frmListarTecnologias:tablaTecnologiasCrear");
        PrimeFaces.current().executeScript("PF('dialogoVerTecnologias').show()");
        procesoFinal();
    }

    private void cargarLazyTecnologiaPrograma() {
        lazyProgramaTecnologias = new LazyDataModel<PeProgramaTecnologia>() {
            private List<PeProgramaTecnologia> listaTecnologias;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<PeProgramaTecnologia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarTecnologiasPrograma();
                listaTecnologias = getObjeto().getPeProgramaTecnologiasList();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaTecnologias;
            }

            @Override
            public String getRowKey(PeProgramaTecnologia tecnologia) {
                return tecnologia.getId().toString();
            }

            @Override
            public PeProgramaTecnologia getRowData(String tecnologiaId) {
                Integer id = Integer.valueOf(tecnologiaId);
                for (PeProgramaTecnologia tecno : listaTecnologias) {
                    if (id.equals(tecno.getId())) {
                        return tecno;
                    }
                }
                return null;
            }
        };
    }

    public void verDiagnosticos(PePrograma obj) {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_LISTAR);
        setObjeto(obj);
        contadorIdDiagnosticos = -1;
        cargarLazyDiacnosticoPrograma();
        PrimeFaces.current().ajax().update("frmListarDiagnosticos:tablaDiagnosticosCrear");
        PrimeFaces.current().executeScript("PF('dialogoVerDiagnostico').show()");
        procesoFinal();
    }

    private void cargarLazyDiacnosticoPrograma() {
        lazyProgramaDiagnostico = new LazyDataModel<PeProgramaDiagnostico>() {
            private List<PeProgramaDiagnostico> listaDiagnosticos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<PeProgramaDiagnostico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDiagnosticosPrograma();
                listaDiagnosticos = getObjeto().getPeProgramaDiagnosticosList();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaDiagnosticos;
            }

            @Override
            public String getRowKey(PeProgramaDiagnostico diagnostico) {
                return diagnostico.getId().toString();
            }

            @Override
            public PeProgramaDiagnostico getRowData(String diagnosticoId) {
                Integer id = Integer.valueOf(diagnosticoId);
                for (PeProgramaDiagnostico diagnostico : listaDiagnosticos) {
                    if (id.equals(diagnostico.getId())) {
                        return diagnostico;
                    }
                }
                return null;
            }
        };
    }

    public void verCargasMasivasTecnologias(int id) {
        getObjeto().setId(id);
        cargarLazyCargaMasivaTecnologia();
        PrimeFaces.current().executeScript("PF('dialogoVerCargaMasiva').show()");
        listarCargasMasivas();
        //PrimeFaces.current().ajax().update("frmListarCargaMasiva:tablaCargasTecnologias");
    }

    private void cargarLazyCargaMasivaTecnologia() {
        lazyCargaMasiva = new LazyDataModel<PeCarga>() {
            private List<PeCarga> listaCargas;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(4).getCantidadRegistros();
            }

            @Override
            public List<PeCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(4).setPrimerRegistro(primerRegistro);
                getParamConsulta(4).setRegistrosPagina(registrosPagina);
                getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));                
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(PeConstantes.STR_PROGRAMA, objeto.getId());
                getParamConsulta(4).setFiltros(filtrosHash);                
                refrescarCargaMasiva();
                listaCargas = getRegistrosCarga();
                setRowCount(getParamConsulta(4).getCantidadRegistros());
                return listaCargas;
            }

            @Override
            public String getRowKey(PeCarga portabilidad) {
                return portabilidad.getId().toString();
            }

            @Override
            public PeCarga getRowData(String portabilidadId) {
                Integer id = Integer.valueOf(portabilidadId);
                for (PeCarga portabilidad : listaCargas) {
                    if (id.equals(portabilidad.getId())) {
                        return portabilidad;
                    }
                }
                return null;
            }

        };
    }

    public String getEstado(Integer idEstado){
        return PeConstantes.obtenerEstadoCargaMasivaStr(idEstado);
    }

    public List<Usuario> completarUsuarios(String query) {
        List<Usuario> usuariosFiltrados = new ArrayList<>();
        for (Usuario usuario : getListaUsuario()) {
            if (usuario.getNombre().toLowerCase().contains(query.toLowerCase())) {
                usuariosFiltrados.add(usuario);
            }
        }

        if (usuariosFiltrados.size() == 1) {
            getObjetoUsuario().setUsuariosId(usuariosFiltrados.get(0));
        }
        return usuariosFiltrados;
    }

    public String getUsuarioRecursiva(int id) {
        String usuarioStr = "";
        if (usuarioRecursiva != null) {
            Usuario usuario = usuarioRecursiva.get(id);
            if (usuario != null && usuario.getId() != null) {
                usuarioStr = usuario.getNombre();
            }
        }
        return usuarioStr;
    }

    public void crearUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearUsuario");
        PrimeFaces.current().ajax().update("frmCrearUsuario");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').show()");
        procesoFinal();
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

    public void consultarDiagnostico() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().executeScript("PF('frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos').clearFilters()");
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
        dataTable.reset();
        PrimeFaces.current().resetInputs("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
    }

    public void cerrarDialogoDiagnostico() {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_GUARDAR);
        PeProgramaDiagnostico diagnostico = new PeProgramaDiagnostico();
        diagnostico.setId(contadorIdDiagnosticos);
        diagnostico.setPeProgramasId(getObjeto());
        diagnostico.setMaDiagnosticoId(getDiagnosticosBean().getObjeto().getId());
        boolean existeDiagnostico = getGestionProgramaServicio().existeDiagnosticoPrograma(diagnostico);
        if (!existeDiagnostico) {
            diagnostico.setMaDiagnosticoCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticoValor(getDiagnosticosBean().getObjeto().getNombre());
            getObjeto().setObjetoDiagnostico(diagnostico);
            getGestionProgramaServicio().Accion(this);
            //getObjeto().getPeProgramaDiagnosticosList().add(getObjeto().getObjetoDiagnostico());
            contadorIdDiagnosticos--;
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            getObjeto().setObjetoDiagnostico(null);
        } else {
            addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " Ya se encuentra agregado en el programa.");
        }
        getDiagnosticosBean().setObjeto(new MaDiagnostico());
        generarMensajes();
        procesoFinal();
    }

    public void cerrarDialogoTecnologia() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR);
        boolean agregar = true;
        if (agregar && !getObjeto().getPeProgramaTecnologiasList().isEmpty()) {
            for (PeProgramaTecnologia item : getObjeto().getPeProgramaTecnologiasList()) {
                if (item.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA && item.getMaTecnologiaId() == getTecnologiasBean().getObjeto().getId()) {
                    agregar = false;
                    addMensaje("El procedimiento " + getTecnologiasBean().getObjeto().getCupsDescipcion() + " Ya se encuentra agregado en el programa.");
                    break;
                }
            }
        }
        if (agregar) {
            PeProgramaTecnologia itemNuevo = new PeProgramaTecnologia();
            itemNuevo.setPeProgramasId(getObjeto());
            itemNuevo.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_TECNOLOGIA)));
            MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
            itemNuevo.setMaTecnologiaId(tecnologia.getId());
            itemNuevo.setMaTecnologiaCodigo(tecnologia.getCodigoPropio());
            itemNuevo.setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            //getObjeto().setObjetoTecnologia(itemNuevo);
            //getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);            
            getObjeto().setObjetoTecnologia(itemNuevo);
            getGestionProgramaServicio().Accion(this);
            getObjeto().getPeProgramaTecnologiasList().add(getObjeto().getObjetoTecnologia());
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
            getObjeto().setObjetoTecnologia(null);
        }
        getTecnologiasBean().setObjeto(new MaTecnologia());
        generarMensajes();
        procesoFinal();
    }

    public void cerrarDialogoMedicamento() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR);
        boolean agregar = true;
        if (!getObjeto().getPeProgramaTecnologiasList().isEmpty()) {
            for (PeProgramaTecnologia item : getObjeto().getPeProgramaTecnologiasList()) {
                if (item.getTipoTecnologia() == AuConstantes.ID_MEDICAMENTO && item.getMaTecnologiaId() == getMedicamentosBean().getObjeto().getId()) {
                    agregar = false;
                    addMensaje("El medicamento " + getMedicamentosBean().getObjeto().getDescripcionEstandarizada() + " Ya se encuentra agregado en el programa.");
                    break;
                }
            }
        }
        if (agregar) {
            PeProgramaTecnologia itemNuevo = new PeProgramaTecnologia();
            itemNuevo.setPeProgramasId(getObjeto());
            itemNuevo.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)));
            MaMedicamento medicamento = getMedicamentosBean().getObjeto();
            itemNuevo.setMaTecnologiaId(medicamento.getId());
            itemNuevo.setMaTecnologiaCodigo(medicamento.getCum());
            itemNuevo.setMaTecnologiaValor(medicamento.getDescripcionEstandarizada());
            //getObjeto().setObjetoTecnologia(itemNuevo);
            //getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);            
            getObjeto().setObjetoTecnologia(itemNuevo);
            getGestionProgramaServicio().Accion(this);
            getObjeto().getPeProgramaTecnologiasList().add(getObjeto().getObjetoTecnologia());
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
            getObjeto().setObjetoTecnologia(null);
        }
        getMedicamentosBean().setObjeto(new MaMedicamento());
        generarMensajes();
        procesoFinal();
    }

    public void cerrarDialogoInsumo() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR);
        boolean agregar = true;
        if (!getObjeto().getPeProgramaTecnologiasList().isEmpty()) {
            for (PeProgramaTecnologia item : getObjeto().getPeProgramaTecnologiasList()) {
                if (item.getTipoTecnologia() == AuConstantes.ID_INSUMO && item.getMaTecnologiaId() == getInsumosBean().getObjeto().getId()) {
                    agregar = false;
                    addMensaje("El insumo " + getInsumosBean().getObjeto().getDescripcion() + " Ya se encuentra agregado en el programa.");
                    break;
                }
            }
        }

        if (agregar) {
            MaInsumo insumo = getInsumosBean().getObjeto();
            if (insumo.getCodigo() != null && !insumo.getCodigo().equals("")) {
                PeProgramaTecnologia itemNuevo = new PeProgramaTecnologia();
                itemNuevo.setPeProgramasId(getObjeto());
                itemNuevo.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_INSUMO)));
                itemNuevo.setMaTecnologiaId(insumo.getId());
                itemNuevo.setMaTecnologiaCodigo(insumo.getCodigo());
                itemNuevo.setMaTecnologiaValor(insumo.getDescripcion());
                //getObjeto().setObjetoTecnologia(itemNuevo);
                //getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);            
                getObjeto().setObjetoTecnologia(itemNuevo);
                getGestionProgramaServicio().Accion(this);
                getObjeto().getPeProgramaTecnologiasList().add(getObjeto().getObjetoTecnologia());
                PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
                getObjeto().setObjetoTecnologia(null);
            } else {
                addError("El insumo no tiene codigo");
            }
        }
        getInsumosBean().setObjeto(new MaInsumo());
        generarMensajes();
        procesoFinal();
    }

    public void cerrarDialogoPaquete() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR);
        boolean agregar = true;
        if (!getObjeto().getPeProgramaTecnologiasList().isEmpty()) {
            for (PeProgramaTecnologia item : getObjeto().getPeProgramaTecnologiasList()) {
                if (item.getTipoTecnologia() == AuConstantes.ID_PAQUETE && item.getMaTecnologiaId() == getPaquetesBean().getObjeto().getId()) {
                    agregar = false;
                    addMensaje("El paquete " + getPaquetesBean().getObjeto().getNombre() + " Ya se encuentra agregado en el programa.");
                    break;
                }
            }
        }

        if (agregar) {
            MaPaquete paquete = getPaquetesBean().getObjeto();
            PeProgramaTecnologia itemNuevo = new PeProgramaTecnologia();
            itemNuevo.setPeProgramasId(getObjeto());
            itemNuevo.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_PAQUETE)));
            itemNuevo.setMaTecnologiaId(paquete.getId());
            itemNuevo.setMaTecnologiaCodigo(paquete.getCodigo());
            itemNuevo.setMaTecnologiaValor(paquete.getNombre());
            //getObjeto().setObjetoTecnologia(itemNuevo);
            //getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);            
            getObjeto().setObjetoTecnologia(itemNuevo);
            getGestionProgramaServicio().Accion(this);
            getObjeto().getPeProgramaTecnologiasList().add(getObjeto().getObjetoTecnologia());
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
            getObjeto().setObjetoTecnologia(null);
        }
        getPaquetesBean().setObjeto(new MaPaquete());
        generarMensajes();
        procesoFinal();
    }

    public void borrarDiagnostico(int id) {
        getObjeto().setObjetoDiagnostico(new PeProgramaDiagnostico(id));
        super.setAccion(Url.ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_BORRAR);
        getGestionProgramaServicio().Accion(this);
        procesoFinal();
    }

    public void borrarTecnologia(int id) {
        getObjeto().setObjetoTecnologia(new PeProgramaTecnologia(id));
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_BORRAR);
        getGestionProgramaServicio().Accion(this);        
        procesoFinal();
    }

    public void agregarUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().ajax().update("frmEditarPrograma");
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').hide();");
        }
        procesoFinal();
    }

    public void quitarUsuario(int usuarioId) {
        PeUsuariosPrograma borrarObj = new PeUsuariosPrograma();
        for (PeUsuariosPrograma peUsuariosPrograma : getObjeto().getPeUsuariosProgramaList()) {
            if (peUsuariosPrograma.getUsuariosId().getId().intValue() == usuarioId) {
                borrarObj = peUsuariosPrograma;
            }
        }
        getObjeto().getPeUsuariosProgramaList().remove(borrarObj);
        PrimeFaces.current().ajax().update("frmCrear");

    }

    public void editarUsuario(PeUsuariosPrograma usuario) {
        this.objetoUsuario = usuario;
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.objetoUsuario.setFechaInicio(std.parse(usuario.getFechaInicio().toString()));
            this.objetoUsuario.setFechaFin(std.parse(usuario.getFechaFin().toString()));
        } catch (ParseException ex) {
            this.objetoUsuario.setFechaInicio(new Date());
            this.objetoUsuario.setFechaFin(new Date());
        }
        PrimeFaces.current().ajax().update("frmEditarUsuario");
        PrimeFaces.current().executeScript("PF('dialogoEditarUsuario').show()");
        procesoFinal();
    }

    public void modificarUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarPrograma");
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarUsuario').hide();");
        }
        procesoFinal();
    }

    public void borrarUsuario(int id) {
        setObjetoUsuario(new PeUsuariosPrograma());
        getObjetoUsuario().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_BORRAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarPrograma");
        procesoFinal();
    }

    public List<CntPrestadorSede> completarIps(String query) {
        List<CntPrestadorSede> ipsFiltrados = new ArrayList<>();
        for (CntPrestadorSede cntPrestadorSede : getListaIps()) {
            // 2021-01-04 jyperez Se adiciona validación debido a que por cargue de datos, el nombre de las sedes puede llegar NULO
            if (cntPrestadorSede.getNombreSede() != null && cntPrestadorSede.getNombreSede().toLowerCase().contains(query.toLowerCase())) {
                ipsFiltrados.add(cntPrestadorSede);
            }
        }

        if (ipsFiltrados.size() == 1) {
            getObjetoIps().setCntPrestadorSedesId(ipsFiltrados.get(0));
        }
        return ipsFiltrados;
    }

    public String getIpsRecursiva(int id) {
        String sedeStr = "";
        if (sedeRecursiva != null) {
            CntPrestadorSede sede = sedeRecursiva.get(id);
            if (sede != null && sede.getId() != null) {
                sedeStr = sede.getNombreSede();
            }
        }
        return sedeStr;
    }

    public void seleccionarIps() {
        cargarLazyIps();
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        PrimeFaces.current().ajax().update("frmIpsLista:tablaRegistrosIps");
    }

    private void cargarLazyIps() {
        lazyIps = new LazyDataModel<CntPrestadorSede>() {
            private List<CntPrestadorSede> listaIps;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarIps();
                listaIps = getListaIps();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaIps;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaIps) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectIps(SelectEvent event) {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getGestionProgramaServicio().Accion(this);
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjetoIps().setCntPrestadorSedesId(ips);
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmCrearIps");
        crearIps();
    }

    public void crearIps() {
        //super.setAccion(ACCION_ADICIONAL_2);
        //super.setDoAccion(ACCION_CREAR);
        //getGestionProgramaServicio().Accion(this);
        //PrimeFaces.current().resetInputs("frmCrearIps");
        PrimeFaces.current().ajax().update("frmCrearIps");
        PrimeFaces.current().executeScript("PF('dialogoCrearIps').show()");
        procesoFinal();
    }

    public void agregarIps() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getGestionProgramaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearIps').hide();");
        }
        procesoFinal();
    }

    public void quitarIps(int sedeId) {
        PeIpsPrograma borrarObj = new PeIpsPrograma();
        for (PeIpsPrograma ipsPrograma : getObjeto().getPeIpsProgramaList()) {
            if (ipsPrograma.getCntPrestadorSedesId().getId().intValue() == sedeId) {
                borrarObj = ipsPrograma;
            }
        }
        getObjeto().getPeIpsProgramaList().remove(borrarObj);
        PrimeFaces.current().ajax().update("frmCrear");
    }

    public void editarIps(PeIpsPrograma ips) {
        SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
        this.objetoIps = ips;
        try {
            this.objetoIps.setFechaInicio(std.parse(ips.getFechaInicio().toString()));
            this.objetoIps.setFechaFin(std.parse(ips.getFechaFin().toString()));
        } catch (ParseException ex) {
            this.objetoIps.setFechaInicio(new Date());
            this.objetoIps.setFechaFin(new Date());
        }
        PrimeFaces.current().resetInputs("frmEditarIps");
        PrimeFaces.current().ajax().update("frmEditarIps");
        PrimeFaces.current().executeScript("PF('dialogoEditarIps').show()");
        procesoFinal();
    }

    public void modificarIps() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarPrograma");
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarIps').hide();");
        }
        procesoFinal();
    }

    public void borrarIps(int id) {
        setObjetoIps(new PeIpsPrograma());
        getObjetoIps().setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarPrograma");
        procesoFinal();
    }

    public void actualizarValorExoneracion() {
        if (Integer.parseInt(objeto.getMaeTipoProgramaCodigo()) == TIPO_ALTO_COSTO) {
            objeto.setExoneradoCopago(true);
            deshabilitarExoneracion = true;
        } else {
            deshabilitarExoneracion = false;
        }
    }

    public void crearAdjuntoCargaMasiva() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_CREAR);
        getGestionProgramaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (getObjetoCarga().getTipo() != null && getObjetoCarga().getTipo() > 0) {
                UploadedFile archivo = event.getFile();
                this.objetoCarga.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                this.objetoCarga.setNombre(nombreAdjunto);
                // llamamos al guardar
                if (this.objetoCarga.getAdjuntoStream() != null) {
                    super.setAccion(ACCION_ADICIONAL_6);
                    super.setDoAccion(ACCION_GUARDAR);
                    getGestionProgramaServicio().Accion(this);
                    PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
                } else {
                    this.addError("No se ha cargado el archivo.");
                }
            } else {
                addMensaje("Debe seleccionar el tipo de carga");
                PrimeFaces.current().ajax().update("frmCrearAdjunto");
            }
        } catch (IOException ex) {
            addError(ex.toString());
        }
        procesoFinal();
    }

    public void guardarAdjuntoCargaMasiva() {
        if (this.objetoCarga.getAdjuntoStream() != null) {
            super.setAccion(ACCION_ADICIONAL_6);
            super.setDoAccion(ACCION_GUARDAR);
            getGestionProgramaServicio().Accion(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide()");
            PrimeFaces.current().ajax().update("frmListarCargaMasiva:tablaCargasTecnologias");
        } else {
            this.addError("No se ha cargado el archivo.");
        }
        procesoFinal();
    }

    public String obtenerTipoTencnologia(int id) {
        return PeConstantes.obtenerTipoTecnologia(id);
    }
    
    public void generarArchivoCarga(int id) {
        this.setObjetoCarga(new PeCarga(id));
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getGestionProgramaServicio().Accion(this);
        if(this.getObjetoCarga().getExiste()){
            String rutaCompleta = this.objetoCarga.getRuta() + this.objetoCarga.getArchivo();
            deacargarAcvhivo(rutaCompleta, this.objetoCarga.getArchivo());
        } else {
            this.addError("El archivo de carga original no existe en la ruta de descarga.");
        }         
        procesoFinal();
    }

    public void generarArchivoResultados(int id) {
        this.setObjetoCarga(new PeCarga(id));
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getGestionProgramaServicio().Accion(this);
        if(this.getObjetoCarga().getRespExiste()){
            String rutaCompleta = this.objetoCarga.getRespRuta()+ this.objetoCarga.getRespArchivo();
            deacargarAcvhivo(rutaCompleta, this.objetoCarga.getRespArchivo());
        } else {
            this.addError("El archivo de resultados no existe en la ruta de descarga.");
        }         
        procesoFinal();
    }
    
    private void deacargarAcvhivo(String rutaCompleta, String nombreArchivo){
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + nombreArchivo + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("text/plain");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo.");
        }
    }

    public void cambiarPeProgramaTecnologia(PeProgramaTecnologia peProgramaTecnologia) {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_MODIFICAR);
        if (peProgramaTecnologia.getId() == null) {
            addError("Debe indicar la tecnología a modificar.");
            return;
        }
        getObjeto().setObjetoTecnologia(peProgramaTecnologia);
        getGestionProgramaServicio().Accion(this);
        procesoFinal();
    }

    public void modificarPeProgramaDiagnostico(PeProgramaDiagnostico peProgramaDiagnostico) {
        super.setAccion(Url.ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_MODIFICAR);
        if (peProgramaDiagnostico.getId() == null) {
            addError("Debe indicar el diagnostico a modificar.");
            return;
        }
        getObjeto().setObjetoDiagnostico(peProgramaDiagnostico);
        getGestionProgramaServicio().Accion(this);
        procesoFinal();
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public void onRowSelectUsuario(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        if (tipoSelectUsuario == 0) {
            getObjeto().setUsuariosId(usuario);
            getObjetoUsuario().setUsuariosId(usuario);
            PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
            PrimeFaces.current().ajax().update("frmCrear:autoUsuario");
            PrimeFaces.current().ajax().update("frmEditarPrograma:autoUsuario");
        } else {
            getObjetoUsuario().setUsuariosId(usuario);
            PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
            PrimeFaces.current().ajax().update("frmCrearUsuario:autoUsuario");
        }

    }

    public void consultarUsuarios(Integer tipoSelectUsuario) {
        this.tipoSelectUsuario = tipoSelectUsuario;
        cargarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        PrimeFaces.current().ajax().update("frmUsuariosLista:tablaRegistrosUsuarios");
    }

    private void cargarLazyUsuario() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                listaUsuario = getListaUsuario();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaUsuario;
            }

            @Override
            public String getRowKey(Usuario usuario) {
                return usuario.getId().toString();
            }

            @Override
            public Usuario getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (Usuario user : listaUsuario) {
                    if (id.equals(user.getId())) {
                        return user;
                    }
                }
                return null;
            }
        };
    }

    public String getRegistrosAfiliados(int id) {
        return PeConstantes.getRegistrosAfiliados(id);
    }

    public String getSexoAplicaDescripcion(Integer id) {
        return PeConstantes.getListaSexoAplicaDescripcion(id);
    }

    public String getCantidadRegistrosStr(Integer id) {
        return PeConstantes.getCantidadRegistrosStr(id);
    }
    
    public void auditoriaTecnologiaPrograma(PeProgramaTecnologia obj){
        super.getAuditoria(obj.getAuditoriaStrHTML());
    }
    
    public void auditoriaDiagnosticoPrograma(PeProgramaDiagnostico obj){
        super.getAuditoria(obj.getAuditoriaStrHTML());
    }
    
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1://Crear y Modificar de usuarios
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        crearLog("Creación de Usuario", getObjetoUsuario().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                        crearLog("Guardar Usuario", getObjetoUsuario().toString());
                        break;
                    case Url.ACCION_MODIFICAR:
                        crearLog("Modificar Usuarios", getObjetoUsuario().toString());
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2://Crear y Modificar de IPS's
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        crearLog("Creación de IPS", getObjetoIps().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().ajax().update("frmCrear");
                        PrimeFaces.current().ajax().update("frmEditarPrograma");
                        crearLog("Guardar IPS", getObjetoIps().toString());
                        break;
                    case Url.ACCION_MODIFICAR:
                        crearLog("Modificar IPS", getObjetoIps().toString());
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_3://Borrar usuarios
                crearLog("Borrar Usuario", getObjetoUsuario().toString());
                break;
            case Url.ACCION_ADICIONAL_4://Borrar IPS's
                crearLog("Borrar IPS", getObjetoIps().toString());
                break;
            case Url.ACCION_ADICIONAL_5: //Gestionar tecnologias
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog("Gestionar tecnologia programa", getObjeto().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_BORRAR:
                        PrimeFaces.current().ajax().update("frmListarTecnologias:tablaTecnologiasCrear");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_6: //Carga masiva tecnologias 
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmListarCargaMasiva:tablaCargasTecnologias");
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().ajax().update("frmListarCargaMasiva:tablaCargasTecnologias");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_7: //Gestionar diagnosticos
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog("Gestionar diagnosticos programa", getObjeto().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                    case Url.ACCION_BORRAR:
                        PrimeFaces.current().ajax().update("frmListarDiagnosticos:tablaDiagnosticosCrear");
                        break;
                }
                break;
        }
        generarMensajes();
    }

}
