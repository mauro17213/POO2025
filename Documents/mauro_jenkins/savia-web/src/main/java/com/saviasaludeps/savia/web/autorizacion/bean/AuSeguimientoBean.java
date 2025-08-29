/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoServicio;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuSeguimientoIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author iavenegas
 */
@ManagedBean
@ViewScoped
public class AuSeguimientoBean extends Url {

    AuSeguimientoIface auSeguimientoServicio;
    
    private LazyDataModel<AuSeguimiento> lazySeguimientos;
    private List<AuSeguimiento> registros;
    private LazyDataModel<AuSeguimientoGestion> lazySeguimientoGestiones;
    private List<AuSeguimientoGestion> registrosGestion;
    private List<CntPrestadorSede> listaIps;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private List<CntContratoDetalle> listaContratosDetalles;
    private LazyDataModel<CntContratoDetalle> lazyContratoDetalles;
    private List<AuGrupo> listaGruposActivos;

    private AuSeguimiento objetoSeguimiento;
    private AuSeguimientoGestion objetoSeguimientoGestion;
    private AuSeguimientoServicio objetoSeguimientoServicio;
    private AuSeguimientoAfiliadoContacto objetoContacto;
    private AuAnexo3 objetoAnexo3;

    //Listas auxiliares
    private List<Ubicacion> listaUbicacion;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaTipoTecnologia;
    private List<Maestro> listaEstadosItem;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaEstadoGestion;
    private List<Maestro> listaEstadoGestionCrear;
    private HashMap<Integer, Maestro> hashEstadoGestion;
    private HashMap<String, Maestro> hashEstadoGestionValor;
    private List<Maestro> listaEstadoSeguimiento;
    private HashMap<String, Maestro> hashEstadoSeguimientoValor;
    private HashMap<Integer, Maestro> hashEstadoSeguimiento;
    private List<Maestro> listaMotivoGestion;
    private List<Maestro> listaMotivoGestionCrear;
    private HashMap<Integer, Maestro> hashMotivoGestion;
    private HashMap<String, Maestro> hashMotivoGestionValor;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private List<Maestro> listaTiposContacto;
    private HashMap<Integer, Maestro> hashTiposContacto;
    private List<Maestro> listaServicioDetalle;
    private List<Maestro> listaServicioDetalle2;
    private HashMap<Integer, Maestro> hashServicioDetalle;
    private HashMap<Integer, Maestro> hashServicioDetalle2;
    private List<Maestro> listaTipoDocumentoArchivo;
    private HashMap<Integer, Maestro> hashTipoDocumentoArchivo;
    private List<Maestro> listaTipoAmbito;
    private HashMap<Integer, Maestro> hashTipoAmbito;

    private List<String> valoresNoPermitidosTelefonoFijo = new ArrayList();
    private int maeTipoDocumentoArchivo;
    private String descripcionGenerica;
    
    //2023-12-01 jyperez nuevos campos Adjunto Seguimiento Gestión
    private int maeTipoDocumentoArchivoGestion;
    private LazyDataModel<AuSolicitudAdjunto> lazySeguimientoGestionAdjuntos;
    private List<AuSolicitudAdjunto> registrosSeguimientoGestionAdjuntos;
    private ParamConsulta paramConsultaSeguimientoGestion;
    private boolean estadoCancelarValido;

    public AuSeguimientoBean() {
        this.objetoSeguimiento = new AuSeguimiento();
        this.objetoContacto = new AuSeguimientoAfiliadoContacto();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_SEGUIMIENTOS);

        valoresNoPermitidosTelefonoFijo = new ArrayList<>();
        valoresNoPermitidosTelefonoFijo.add("0000000");
        valoresNoPermitidosTelefonoFijo.add("1111111");
        valoresNoPermitidosTelefonoFijo.add("2222222");
        valoresNoPermitidosTelefonoFijo.add("3333333");
        valoresNoPermitidosTelefonoFijo.add("4444444");
        valoresNoPermitidosTelefonoFijo.add("5555555");
        valoresNoPermitidosTelefonoFijo.add("6666666");
        valoresNoPermitidosTelefonoFijo.add("7777777");
        valoresNoPermitidosTelefonoFijo.add("8888888");
        valoresNoPermitidosTelefonoFijo.add("9999999");

        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            //
            lazySeguimientos = new LazyDataModel<AuSeguimiento>() {

                private List<AuSeguimiento> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<AuSeguimiento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AuSeguimiento objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuSeguimiento getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuSeguimiento objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //gestiones
            lazySeguimientoGestiones = new LazyDataModel<AuSeguimientoGestion>() {

                private List<AuSeguimientoGestion> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<AuSeguimientoGestion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarGestion();
                    lista = getRegistrosGestion();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuSeguimientoGestion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuSeguimientoGestion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuSeguimientoGestion objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //IPSs
            lazyIps = new LazyDataModel<CntPrestadorSede>() {
                private List<CntPrestadorSede> listaIps;
                
@Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(2).setPrimerRegistro(primerRegistro);
                    getParamConsulta(2).setRegistrosPagina(registrosPagina);
                    getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarIps();
                    listaIps = getListaIps();
                    setRowCount(getParamConsulta(2).getCantidadRegistros());
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
            //Contrato Detalles
            lazyContratoDetalles = new LazyDataModel<CntContratoDetalle>() {
                private List<CntContratoDetalle> listaContrato;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

                @Override
                public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(3).setPrimerRegistro(primerRegistro);
                    getParamConsulta(3).setRegistrosPagina(registrosPagina);
                    getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarContratoDetalle();
                    listaContrato = getListaContratosDetalles();
                    setRowCount(getParamConsulta(3).getCantidadRegistros());
                    return listaContrato;
                }

                @Override
                public String getRowKey(CntContratoDetalle ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntContratoDetalle getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntContratoDetalle contrato : listaContrato) {
                        if (id.equals(contrato.getId())) {
                            return contrato;
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
        getAuSeguimientoServicio().cargaInicial(this);
        listar();
    }

    public AuSeguimientoIface getAuSeguimientoServicio() {
        return auSeguimientoServicio;
    }

    public void setAuSeguimientoServicio(AuSeguimientoIface auSeguimientoServicio) {
        this.auSeguimientoServicio = auSeguimientoServicio;
    }

    public List<Ubicacion> getListaUbicacion() {
        return listaUbicacion;
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public AuAnexo3 getObjetoAnexo3() {
        return objetoAnexo3;
    }

    public void setObjetoAnexo3(AuAnexo3 objetoAnexo3) {
        this.objetoAnexo3 = objetoAnexo3;
    }

    public List<CntContratoDetalle> getListaContratosDetalles() {
        return listaContratosDetalles;
    }

    public void setListaContratosDetalles(List<CntContratoDetalle> listaContratosDetalles) {
        this.listaContratosDetalles = listaContratosDetalles;
    }

    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalles() {
        return lazyContratoDetalles;
    }

    public void setLazyContratoDetalles(LazyDataModel<CntContratoDetalle> lazyContratoDetalles) {
        this.lazyContratoDetalles = lazyContratoDetalles;
    }

    public List<Maestro> getListaServicioDetalle2() {
        return listaServicioDetalle2;
    }

    public void setListaServicioDetalle2(List<Maestro> listaServicioDetalle2) {
        this.listaServicioDetalle2 = listaServicioDetalle2;
    }

    public HashMap<Integer, Maestro> getHashServicioDetalle2() {
        return hashServicioDetalle2;
    }

    public void setHashServicioDetalle2(HashMap<Integer, Maestro> hashServicioDetalle2) {
        this.hashServicioDetalle2 = hashServicioDetalle2;
    }

    public List<Maestro> getListaEstadosItem() {
        return listaEstadosItem;
    }

    public void setListaEstadosItem(List<Maestro> listaEstadosItem) {
        this.listaEstadosItem = listaEstadosItem;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public List<Maestro> getListaEstadoSeguimiento() {
        return listaEstadoSeguimiento;
    }

    public void setListaEstadoSeguimiento(List<Maestro> listaEstadoSeguimiento) {
        this.listaEstadoSeguimiento = listaEstadoSeguimiento;
    }

    public HashMap<String, Maestro> getHashEstadoSeguimientoValor() {
        return hashEstadoSeguimientoValor;
    }

    public void setHashEstadoSeguimientoValor(HashMap<String, Maestro> hashEstadoSeguimientoValor) {
        this.hashEstadoSeguimientoValor = hashEstadoSeguimientoValor;
    }

    public HashMap<Integer, Maestro> getHashEstadoSeguimiento() {
        return hashEstadoSeguimiento;
    }

    public void setHashEstadoSeguimiento(HashMap<Integer, Maestro> hashEstadoSeguimiento) {
        this.hashEstadoSeguimiento = hashEstadoSeguimiento;
    }

    public List<Maestro> getListaEstadoGestion() {
        return listaEstadoGestion;
    }

    public void setListaEstadoGestion(List<Maestro> listaEstadoGestion) {
        this.listaEstadoGestion = listaEstadoGestion;
    }

    public HashMap<Integer, Maestro> getHashEstadoGestion() {
        return hashEstadoGestion;
    }

    public void setHashEstadoGestion(HashMap<Integer, Maestro> hashEstadoGestion) {
        this.hashEstadoGestion = hashEstadoGestion;
    }

    public HashMap<String, Maestro> getHashEstadoGestionValor() {
        return hashEstadoGestionValor;
    }

    public void setHashEstadoGestionValor(HashMap<String, Maestro> hashEstadoGestionValor) {
        this.hashEstadoGestionValor = hashEstadoGestionValor;
    }

    public List<Maestro> getListaMotivoGestion() {
        return listaMotivoGestion;
    }

    public void setListaMotivoGestion(List<Maestro> listaMotivoGestion) {
        this.listaMotivoGestion = listaMotivoGestion;
    }

    public HashMap<Integer, Maestro> getHashMotivoGestion() {
        return hashMotivoGestion;
    }

    public void setHashMotivoGestion(HashMap<Integer, Maestro> hashMotivoGestion) {
        this.hashMotivoGestion = hashMotivoGestion;
    }

    public List<Maestro> getListaTipoAmbito() {
        return listaTipoAmbito;
    }

    public void setListaTipoAmbito(List<Maestro> listaTipoAmbito) {
        this.listaTipoAmbito = listaTipoAmbito;
    }

    public HashMap<Integer, Maestro> getHashTipoAmbito() {
        return hashTipoAmbito;
    }

    public void setHashTipoAmbito(HashMap<Integer, Maestro> hashTipoAmbito) {
        this.hashTipoAmbito = hashTipoAmbito;
    }

    public List<Maestro> getListaEstadoGestionCrear() {
        return listaEstadoGestionCrear;
    }

    public void setListaEstadoGestionCrear(List<Maestro> listaEstadoGestionCrear) {
        this.listaEstadoGestionCrear = listaEstadoGestionCrear;
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

    public LazyDataModel<AuSeguimiento> getLazySeguimientos() {
        return lazySeguimientos;
    }

    public void setLazySeguimientos(LazyDataModel<AuSeguimiento> lazySeguimientos) {
        this.lazySeguimientos = lazySeguimientos;
    }

    public List<AuSeguimiento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuSeguimiento> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuSeguimientoGestion> getLazySeguimientoGestiones() {
        return lazySeguimientoGestiones;
    }

    public void setLazySeguimientoGestiones(LazyDataModel<AuSeguimientoGestion> lazySeguimientoGestiones) {
        this.lazySeguimientoGestiones = lazySeguimientoGestiones;
    }

    public List<AuSeguimientoGestion> getRegistrosGestion() {
        return registrosGestion;
    }

    public void setRegistrosGestion(List<AuSeguimientoGestion> registrosGestion) {
        this.registrosGestion = registrosGestion;
    }

    public List<Maestro> getListaTipoTecnologia() {
        return listaTipoTecnologia;
    }

    public void setListaTipoTecnologia(List<Maestro> listaTipoTecnologia) {
        this.listaTipoTecnologia = listaTipoTecnologia;
    }

    public AuSeguimiento getObjetoSeguimiento() {
        return objetoSeguimiento;
    }

    public void setObjetoSeguimiento(AuSeguimiento objetoSeguimiento) {
        this.objetoSeguimiento = objetoSeguimiento;
    }

    public List<Maestro> getListaMotivoGestionCrear() {
        return listaMotivoGestionCrear;
    }

    public void setListaMotivoGestionCrear(List<Maestro> listaMotivoGestionCrear) {
        this.listaMotivoGestionCrear = listaMotivoGestionCrear;
    }

    public List<CntPrestadorSede> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestadorSede> listaIps) {
        this.listaIps = listaIps;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestadorSede> lazyIps) {
        this.lazyIps = lazyIps;
    }

    public AuSeguimientoGestion getObjetoSeguimientoGestion() {
        return objetoSeguimientoGestion;
    }

    public void setObjetoSeguimientoGestion(AuSeguimientoGestion objetoSeguimientoGestion) {
        this.objetoSeguimientoGestion = objetoSeguimientoGestion;
    }

    public List<Maestro> getListaTipoDocumentoArchivo() {
        return listaTipoDocumentoArchivo;
    }

    public void setListaTipoDocumentoArchivo(List<Maestro> listaTipoDocumentoArchivo) {
        this.listaTipoDocumentoArchivo = listaTipoDocumentoArchivo;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoArchivo() {
        return hashTipoDocumentoArchivo;
    }

    public void setHashTipoDocumentoArchivo(HashMap<Integer, Maestro> hashTipoDocumentoArchivo) {
        this.hashTipoDocumentoArchivo = hashTipoDocumentoArchivo;
    }

    public int getMaeTipoDocumentoArchivo() {
        return maeTipoDocumentoArchivo;
    }

    public void setMaeTipoDocumentoArchivo(int maeTipoDocumentoArchivo) {
        this.maeTipoDocumentoArchivo = maeTipoDocumentoArchivo;
    }

    public List<Maestro> getListaServicioDetalle() {
        return listaServicioDetalle;
    }

    public void setListaServicioDetalle(List<Maestro> listaServicioDetalle) {
        this.listaServicioDetalle = listaServicioDetalle;
    }

    public HashMap<Integer, Maestro> getHashServicioDetalle() {
        return hashServicioDetalle;
    }

    public void setHashServicioDetalle(HashMap<Integer, Maestro> hashServicioDetalle) {
        this.hashServicioDetalle = hashServicioDetalle;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public AuSeguimientoServicio getObjetoSeguimientoServicio() {
        return objetoSeguimientoServicio;
    }

    public void setObjetoSeguimientoServicio(AuSeguimientoServicio objetoSeguimientoServicio) {
        this.objetoSeguimientoServicio = objetoSeguimientoServicio;
    }

    public AuSeguimientoAfiliadoContacto getObjetoContacto() {
        return objetoContacto;
    }

    public void setObjetoContacto(AuSeguimientoAfiliadoContacto objetoContacto) {
        this.objetoContacto = objetoContacto;
    }

    public List<Maestro> getListaTiposContacto() {
        return listaTiposContacto;
    }

    public void setListaTiposContacto(List<Maestro> listaTiposContacto) {
        this.listaTiposContacto = listaTiposContacto;
    }

    public HashMap<Integer, Maestro> getHashTiposContacto() {
        return hashTiposContacto;
    }

    public void setHashTiposContacto(HashMap<Integer, Maestro> hashTiposContacto) {
        this.hashTiposContacto = hashTiposContacto;
    }

    public List<AuGrupo> getListaGruposActivos() {
        return listaGruposActivos;
    }

    public void setListaGruposActivos(List<AuGrupo> listaGruposActivos) {
        this.listaGruposActivos = listaGruposActivos;
    }

    public HashMap<String, Maestro> getHashMotivoGestionValor() {
        return hashMotivoGestionValor;
    }

    public void setHashMotivoGestionValor(HashMap<String, Maestro> hashMotivoGestionValor) {
        this.hashMotivoGestionValor = hashMotivoGestionValor;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuSeguimientoServicio().Accion(this);
    }

    public void refrescarGestion() {
        getAuSeguimientoServicio().listarGestiones(this);
    }

    public void refrescarIps() {
        getAuSeguimientoServicio().listarIPS(this);
    }

    public void refrescarContratoDetalle() {
        getAuSeguimientoServicio().listarContratoDetalle(this);
        generarMensajes();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        setObjetoSeguimiento(new AuSeguimiento(id));
        super.setAccion(ACCION_VER);
        getAuSeguimientoServicio().Accion(this);
        procesoFinal();
    }

    public void gestionar(int id) {
        setObjetoSeguimiento(new AuSeguimiento(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        getAuSeguimientoServicio().Accion(this);
        procesoFinal();
    }

    public void guardarGestionar() {
        BigDecimal valor = new BigDecimal("0.0");
        if (this.objetoSeguimientoServicio.getMaeSeguimientoServicioCodigo().equals(AuSeguimientoServicio.SEGUIMIENTO_OXIGENO) &&
                this.objetoSeguimientoServicio.getLitros().compareTo(valor) > 0) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_MODIFICAR);
            getAuSeguimientoServicio().Accion(this);
        } else if (!this.objetoSeguimientoServicio.getMaeSeguimientoServicioCodigo().equals(AuSeguimientoServicio.SEGUIMIENTO_OXIGENO)) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_MODIFICAR);
            getAuSeguimientoServicio().Accion(this);
        }else {
            addError("Se debe ingresar un valor en Litros mayor a 0,00");
        }
        procesoFinal();
    }

    public void cancelar(int id) {
        setObjetoSeguimiento(new AuSeguimiento(id));
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getAuSeguimientoServicio().Accion(this);
        procesoFinal();
    }

    public void guardarCancelar() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getAuSeguimientoServicio().Accion(this);
        procesoFinal();
    }

    public void crearGestion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getAuSeguimientoServicio().Accion(this);
        procesoFinal();
    }

    public void guardarGestion() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getAuSeguimientoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmSeguimientos");
                    crearLog(getObjetoSeguimiento().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().ajax().update("frmAuditoriaVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    crearLog(getObjetoSeguimiento().toString());
                    break;
                case Url.ACCION_ADICIONAL_1://Gestionar
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().resetInputs("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmAuditoriaGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            crearLog("Seguimiento editar",getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
//                            PrimeFaces.current().ajax().update("frmGestionar");
//                            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                            crearLog("Seguimiento modificado", getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmAgregarGestion");
                            PrimeFaces.current().ajax().update("frmAgregarGestion");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarGestion').show()");
                            crearLog("Gestión crear",getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            //no eliminar informacion en inputs
                            if (getObjetoSeguimientoGestion().getMaeEstadoSeguimientoGestionCodigo().equals(AuSeguimientoGestion.ESTADO_GESTION_NOTA)
                                    || getObjetoSeguimientoGestion().getMaeEstadoSeguimientoGestionCodigo().equals(AuSeguimientoGestion.ESTADO_EN_GESTION)) {
                                PrimeFaces.current().ajax().update("frmGestionar:pnlSeguimientoGestion");
                                PrimeFaces.current().ajax().update("frmGestionar:pnlGestionesGestionar");
                            } else {
                                PrimeFaces.current().ajax().update("frmGestionar");
                            }
                            PrimeFaces.current().executeScript("PF('dialogoAgregarGestion').hide()");
                            //si rechaza el prestador no dejar modal abierta
                            if (!getConexion().getEmpresa().isAdministradora()
                                    && getObjetoSeguimiento().getMaeEstadoSeguimientoCodigo().equals(AuSeguimiento.ESTADO_RECHAZADO_PRESTADOR)) {
                                PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                                PrimeFaces.current().ajax().update("frmSeguimientos");
                            }
                            crearLog("Guardar Gestión", getObjetoSeguimientoGestion().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2://Cancelar
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmCancelar");
                            PrimeFaces.current().executeScript("PF('dialogoCancelar').show()");
                            crearLog("Ver cancelar", getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmSeguimientos");
                            PrimeFaces.current().executeScript("PF('dialogoCancelar').hide()");
                            crearLog("Seguimiento cancelado", getObjetoSeguimiento().toString());
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_3://Adjuntos Gestion Seguimiento
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Listar Adjuntos Gestión Seguimiento", getObjetoSeguimientoGestion().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Adjunto Gestión Seguimiento", getObjetoSeguimientoGestion().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public void validarGestion() {

        getAuSeguimientoServicio().motivosGestionEstado(this);

    }

    public boolean validarEstadoGestionIPS() {
        if (objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId() != null && objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId() != 0) {
            Maestro estadoGestion = getHashEstadoGestion().get(objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId());
            return estadoGestion.getValor()
                    .equalsIgnoreCase(AuSeguimientoGestion.ESTADO_ASIGNADO_PRESTADOR);
        }
        return false;
    }

    public boolean validarFechaPropuesta() {
        if (objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId() != null && objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId() != 0) {
            Maestro estadoGestion = getHashEstadoGestion().get(objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId());
            return estadoGestion.getValor()
                    .equalsIgnoreCase(AuSeguimientoGestion.ESTADO_ACEPTADO_PRESTADOR);
        }
        return false;
    }

    public void cerrarModalAuditar() {
        PrimeFaces.current().ajax().update("frmSeguimientos");
    }

    public boolean validarFechaEntrega() {
        if (objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId() != null && objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId() != 0) {
            Maestro estadoGestion = getHashEstadoGestion().get(objetoSeguimientoGestion.getMaeEstadoSeguimientoGestionId());
            return estadoGestion.getValor().equalsIgnoreCase(AuSeguimientoGestion.ESTADO_ENTREGADO);
        }
        return false;
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        getListaUbicacion().stream()
                .filter(ubicacion -> (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())))
                .forEachOrdered(ubicacion -> {
                    ubicacionesFiltradas.add(ubicacion);
                });

        return ubicacionesFiltradas;
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion municipio = null;
        if (getHashUbicaciones() != null) {
            municipio = getHashUbicaciones().get(id);
        }
        if (municipio != null && municipio.getUbicacionPadre() != null) {
            Ubicacion departamento = municipio.getUbicacionPadre();
            ubicacionStr = departamento.getNombre();
            ubicacionStr = municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public String obtenerBoolean(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void consultarIPS() {
        try {
//            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
            getParamConsulta(3).setParametroConsulta1(getObjetoSeguimiento().getTipoTecnologia());
            getParamConsulta(3).setParametroConsulta2(getObjetoSeguimiento().getMaTecnologiaId() + "");
            PrimeFaces.current().executeScript("PF('dialogoContratoLista').show()");
//            PrimeFaces.current().ajax().update("frmContratoLista");
//            PrimeFaces.current().ajax().update("frmIpsLista");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjetoSeguimiento().setCntPrestadorSedeAsignadoId(ips);
        getParamConsulta(2).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmAgregarGestion:olPrestadorIPS");
    }

    public void onRowSelectContrato(SelectEvent event) {
        CntContratoDetalle contrato = (CntContratoDetalle) event.getObject();
        CntPrestadorSede ips = contrato.getCntContratoSede().getCntPrestadorSede();
        getObjetoSeguimiento().setCntPrestadorSedeAsignadoId(ips);
        getParamConsulta(3).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoContratoLista').hide()");
        PrimeFaces.current().ajax().update("frmAgregarGestion:olPrestadorIPS");
    }

    public void ventanaAdjuntoAfiliado() {
        PrimeFaces.current().resetInputs("frmAdjuntoGestionar");
        PrimeFaces.current().ajax().update("frmAdjuntoGestionar");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').show()");
    }

    public void cargarAdjuntoAfiliado(FileUploadEvent event) throws IOException {
        try {

            AuSolicitudAdjunto adjuntoDocumento = new AuSolicitudAdjunto();
            UploadedFile archivo = event.getFile();
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
            adjuntoDocumento.setByteStream(arreglo);
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
            adjuntoDocumento.setNombre(nombreAdjunto);
            adjuntoDocumento.setArchivo(nombreAdjunto);
            adjuntoDocumento.setExtension(extension);
            adjuntoDocumento.setAuSeguimientoAfiliado(getObjetoSeguimiento().getAuSeguimientoAfiliadosId());
            //2023-12-01 jyperez se asigna origen del archivo adjunto
            adjuntoDocumento.setOrigen(AuSolicitudAdjunto.ORIGEN_SEGUIMIENTO_AFILIADOS);
            getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSolicitudAdjuntosList().add(adjuntoDocumento);
            PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').hide()");
            PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosPrepaGestionar");

        } catch (IOException ex) {
            Logger.getLogger(AuSolicitudBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosPrepaGestionar");
        }
    }

    public void ventanaAdjunto() {
        PrimeFaces.current().resetInputs("frmAdjuntoGestionar1");
        PrimeFaces.current().ajax().update("frmAdjuntoGestionar1");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar1').show()");
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {

            AuSolicitudAdjunto adjuntoDocumento = new AuSolicitudAdjunto();
            UploadedFile archivo = event.getFile();
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
            adjuntoDocumento.setByteStream(arreglo);
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
            adjuntoDocumento.setNombre(nombreAdjunto);
            adjuntoDocumento.setArchivo(nombreAdjunto);
            adjuntoDocumento.setExtension(extension);
            adjuntoDocumento.setAuSeguimiento(getObjetoSeguimiento());
            Maestro tipoArchivo = getHashTipoDocumentoArchivo().get(getMaeTipoDocumentoArchivo());
            adjuntoDocumento.setMaeTipoArchivoId(tipoArchivo.getId());
            adjuntoDocumento.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
            adjuntoDocumento.setMaeTipoArchivoValor(tipoArchivo.getNombre());
            //2023-12-01 jyperez se asigna origen del archivo adjunto
            adjuntoDocumento.setOrigen(AuSolicitudAdjunto.ORIGEN_SEGUIMIENTO);

            getObjetoSeguimiento().getAuSolicitudAdjuntosList().add(adjuntoDocumento);
            PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar1').hide()");
            PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosSeguimientoGestionar");

        } catch (IOException ex) {
            Logger.getLogger(AuSolicitudBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosSeguimientoGestionar");
        }
    }

    public void descargarAdjunto(AuSolicitudAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void borrarAdjuntoAfiliado(AuSolicitudAdjunto adjunto) {
        if (adjunto.getId() != null && adjunto.getId() != 0) {
            getAuSeguimientoServicio().borrarAdjunto(adjunto.getId(), this);
        }
        this.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSolicitudAdjuntosList().remove(adjunto);
        generarMensajes();
        PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosPrepaGestionar");
    }

    public void borrarAdjuntoSeguimiento(AuSolicitudAdjunto adjunto) {
        if (adjunto.getId() != null && adjunto.getId() != 0) {
            getAuSeguimientoServicio().borrarAdjunto(adjunto.getId(), this);
        }
        if (!isError()) {
            this.getObjetoSeguimiento().getAuSolicitudAdjuntosList().remove(adjunto);
            addMensaje("Adjunto borrado correctamente");
        }

        generarMensajes();
        PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosSeguimientoGestionar");
    }
    
    public void ventanaAdjuntoSeguimientoGestion(AuSeguimientoGestion obj) {
        objetoSeguimientoGestion = obj;
        objetoSeguimientoGestion.setAuSolicitudAdjuntosList(new ArrayList());
        PrimeFaces.current().resetInputs("frmAdjuntoSeguimientoGestion");
        PrimeFaces.current().ajax().update("frmAdjuntoSeguimientoGestion");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntoSeguimientoGestion').show()");
    }

    public void cargarAdjuntoSeguimientoGestion(FileUploadEvent event) throws IOException {
        try {

            AuSolicitudAdjunto adjuntoDocumento = new AuSolicitudAdjunto();
            UploadedFile archivo = event.getFile();
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
            adjuntoDocumento.setByteStream(arreglo);
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
            adjuntoDocumento.setNombre(nombreAdjunto);
            adjuntoDocumento.setArchivo(nombreAdjunto);
            adjuntoDocumento.setExtension(extension);
            //adjuntoDocumento.setAuSeguimiento(getObjetoSeguimiento());
            //2023-12-01 jyperez le adjuntamos el id de la gestión del seguimiento, con el cual lo identificaremos
            adjuntoDocumento.setAuSeguimientoGestion(objetoSeguimientoGestion);
            Maestro tipoArchivo = getHashTipoDocumentoArchivo().get(getMaeTipoDocumentoArchivoGestion());
            adjuntoDocumento.setMaeTipoArchivoId(tipoArchivo.getId());
            adjuntoDocumento.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
            adjuntoDocumento.setMaeTipoArchivoValor(tipoArchivo.getNombre());
            //2023-12-01 jyperez se asigna origen del archivo adjunto
            adjuntoDocumento.setOrigen(AuSolicitudAdjunto.ORIGEN_SEGUIMIENTO_GESTIONES);
            getObjetoSeguimientoGestion().getAuSolicitudAdjuntosList().add(adjuntoDocumento);
            super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_GUARDAR);
            getAuSeguimientoServicio().Accion(this);
            if(!isError()) {
                PrimeFaces.current().executeScript("PF('dialogoAdjuntoSeguimientoGestion').hide()");
            //PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosSeguimientoGestionar");
            }
            procesoFinal();

        } catch (IOException ex) {
            Logger.getLogger(AuSolicitudBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            //PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntosSeguimientoGestionar");
        }
    }
    
    public void ventanaListarAdjuntoSeguimientoGestion(AuSeguimientoGestion obj) {
        objetoSeguimientoGestion = obj;
        //2023-12-01 jyperez PENDIENTE CONFIGURAR ACCION ADICIONAL PARA EJECUTAR LA CARGA DE LA LISTA
        inicializarTablaAdjuntosSeguimientoGestion(objetoSeguimientoGestion.getId());
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getAuSeguimientoServicio().Accion(this);
        if (!getRegistrosSeguimientoGestionAdjuntos().isEmpty()) {
            PrimeFaces.current().resetInputs("frmlistarSeguimientoGestion");
            PrimeFaces.current().ajax().update("frmlistarSeguimientoGestion");
            PrimeFaces.current().executeScript("PF('dialogoListarAdjuntoSeguimientoGestion').show()");
        } else {
            addMensaje("No se encontraron adjuntos para la gestión seleccionada");
        }
        procesoFinal();
    }
    
    public void inicializarTablaAdjuntosSeguimientoGestion(int id) {
        this.setParamConsultaSeguimientoGestion(new ParamConsulta());
        this.getParamConsultaSeguimientoGestion().setParametroConsulta3(id);// este valor será nuestro diferenciador a nivel de contrato Sede
        lazySeguimientoGestionAdjuntos = new LazyDataModel<AuSolicitudAdjunto>() {
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            private List<AuSolicitudAdjunto> adjuntos;

            @Override
            public List<AuSolicitudAdjunto> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaSeguimientoGestion().setPrimerRegistro(primerRegistro);
                getParamConsultaSeguimientoGestion().setRegistrosPagina(registrosPagina);
                getParamConsultaSeguimientoGestion().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaAdjuntosSeguimientoGestion();
                adjuntos = getRegistrosSeguimientoGestionAdjuntos();
                setRowCount(getParamConsultaSeguimientoGestion().getCantidadRegistros());
                return adjuntos;
            }

            @Override
            public String getRowKey(AuSolicitudAdjunto adjunto) {
                return adjunto.getId().toString();
            }

            @Override
            public AuSolicitudAdjunto getRowData(String adjuntoId) {
                Integer id = Integer.valueOf(adjuntoId);
                for (AuSolicitudAdjunto adj : adjuntos) {
                    if (id.equals(adj.getId())) {
                        return adj;
                    }
                }
                return null;
            }

        };
    }
    
    public void refrescarListaAdjuntosSeguimientoGestion() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getAuSeguimientoServicio().Accion(this);
    }

    public void ventanaContactoAfiliado() {
        objetoContacto = new AuSeguimientoAfiliadoContacto();
        PrimeFaces.current().resetInputs("frmContactoGestionar");
        PrimeFaces.current().ajax().update("frmContactoGestionar");
        PrimeFaces.current().executeScript("PF('dialogoContactoGestionar').show()");
    }

    public void adicionarContacto() {
        boolean guardar = true;
        String mensaje;
        try {
            //2023-11-27 jyperez adicionamos validación numérica
            if (!objetoContacto.getNumeroContacto().matches("\\d*")) {
                guardar = false;
                addError("El Número de Contacto ingresado no es válido.");
            }
            for (AuSeguimientoAfiliadoContacto contacto : objetoSeguimiento.getAuSeguimientoAfiliadosId().getAuSeguimientoAfiliadoContactosList()) {
                if (objetoContacto.getNumeroContacto().equals(contacto.getNumeroContacto())) {
                    guardar = false;
                    addError("Ya existe el contacto ingresado.");
                    break;
                }
            }
            if (guardar) {
                Maestro tipo = hashTiposContacto.get(objetoContacto.getMaeTipoContactoId());
                if (tipo != null) {
                    switch (tipo.getValor()) {
                        case AuSeguimientoAfiliadoContacto.TIPO_CONTACTO_CELULAR:
                            if (objetoContacto.getNumeroContacto().length() != 10) {
                                addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el Número de Contacto debe ser de 10 dígitos.");
                                guardar = false;
                            }
                            mensaje = validarRangoTelefonoMovil(objetoContacto.getNumeroContacto());
                            if (!mensaje.isEmpty()) {
                                addError(mensaje);
                                guardar = false;
                            }
                            break;
                        case AuSeguimientoAfiliadoContacto.TIPO_CONTACTO_TELEFONO:
                            if (objetoContacto.getNumeroContacto().length() != 10) {
                                addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el Número de Contacto debe ser de 10 dígitos.");
                                guardar = false;
                            }
                            mensaje = validarTelefonosFijosNoPermitidos(objetoContacto.getNumeroContacto());
                            if (!mensaje.isEmpty()) {
                                addError(mensaje);
                                guardar = false;
                            }
                            break;
                    }
                    objetoContacto.setMaeTipoContactoCodigo(tipo.getValor());
                    objetoContacto.setMaeTipoContactoValor(tipo.getNombre());
                } else {
                    guardar = false;
                    addError("No se encontró el valor maestro del Tipo seleccionado.");
                }
            }
        } catch (Exception e) {
            addError("No se encontró el valor maestro del Tipo seleccionado.");
            objetoContacto.setMaeTipoContactoCodigo("");
            objetoContacto.setMaeTipoContactoValor("");
            guardar = false;
        }
        if (guardar) {
            objetoContacto.setActivo(true);
            objetoContacto.setAuSeguimientoAfiliado(objetoSeguimiento.getAuSeguimientoAfiliadosId());
            objetoSeguimiento.getAuSeguimientoAfiliadosId().getAuSeguimientoAfiliadoContactosList().add(objetoContacto);
            PrimeFaces.current().ajax().update("frmGestionar:tablaContactosGestionar");
            PrimeFaces.current().executeScript("PF('dialogoContactoGestionar').hide()");
        } else {
            generarMensajes();
        }
    }

    /**
     * Función para validar que el numero del telefono movil se encuentre entre
     * un rango de 300 y 350 inicialmente.
     *
     * @param objeto
     * @return
     */
    private String validarRangoTelefonoMovil(String movil) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try {
            if (movil != null && !movil.equals("") && !movil.isBlank()) {
                if (movil.length() >= 3) {
                    prefijo = Integer.valueOf(movil.substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        mensaje = "";
                    } else {
                        mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                    }

                } else {
                    mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                }

            }
        } catch (Exception e) {
            mensaje = "El teléfono móvil no contiene un valor numérico.";
        }
        return mensaje;
    }

    /**
     * Se valida que no se ingresen los valores no permitidos ( numeros
     * repetitivos) para el campo teléfono fijo.
     *
     * @param objeto
     * @return
     */
    private String validarTelefonosFijosNoPermitidos(String fijo) {
        String mensaje = "";
        if (fijo != null && !fijo.equals("") && !fijo.isBlank()) {
            if (valoresNoPermitidosTelefonoFijo.contains(fijo)) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
    }

    public void borrarContacto(AuSeguimientoAfiliadoContacto contacto) {
        objetoContacto = contacto;
        if (contacto.getId() != null) {
            getAuSeguimientoServicio().borrarContacto(this);
        }
        if (!isError()) {
            objetoSeguimiento.getAuSeguimientoAfiliadosId().getAuSeguimientoAfiliadoContactosList()
                    .remove(contacto);
            addMensaje("Contacto borrado correctamente.");
        }
        PrimeFaces.current().ajax().update("frmGestionar:tablaContactosGestionar");
        generarMensajes();
    }

    public boolean validarTipoServicio(String servicioValor) {
        if (objetoSeguimientoServicio != null) {
            return objetoSeguimientoServicio.getMaeSeguimientoServicioCodigo()
                    .equalsIgnoreCase(servicioValor);
        }
        return false;

    }

    public void verSolicitud(int idAnexo) {
        setObjetoAnexo3(new AuAnexo3(idAnexo));
        getAuSeguimientoServicio().verAnexo3(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoVerSolicitud').show()");
            PrimeFaces.current().ajax().update("frmAuditoriaVerSolicitud");
            PrimeFaces.current().ajax().update("frmVerSolicitud");
        }
        generarMensajes();
    }

    public void verGestion(AuSeguimientoGestion gestion) {
        setObjetoSeguimientoGestion(gestion);
        PrimeFaces.current().executeScript("PF('dialogoVerGestion').show()");
        PrimeFaces.current().ajax().update("frmVerGestion");
    }

    public String obtenerValorBoolean(boolean bool) {

        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;

    }

    public void verBitacoras(AuAnexo3Item item) {
        getObjetoAnexo3().setObjetoTecnologia(item);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorass').show()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }

    public void mostrarMensaje(String desc) {
        setDescripcionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
    public boolean validarEstadoCancelar( AuSeguimiento obj) {
        this.objetoSeguimiento = obj;
        getAuSeguimientoServicio().validarEstadoCancelar(this);
        return this.estadoCancelarValido;
    }

    /**
     * @return the maeTipoDocumentoArchivoGestion
     */
    public int getMaeTipoDocumentoArchivoGestion() {
        return maeTipoDocumentoArchivoGestion;
    }

    /**
     * @param maeTipoDocumentoArchivoGestion the maeTipoDocumentoArchivoGestion to set
     */
    public void setMaeTipoDocumentoArchivoGestion(int maeTipoDocumentoArchivoGestion) {
        this.maeTipoDocumentoArchivoGestion = maeTipoDocumentoArchivoGestion;
    }

    /**
     * @return the lazySeguimientoGestionAdjuntos
     */
    public LazyDataModel<AuSolicitudAdjunto> getLazySeguimientoGestionAdjuntos() {
        return lazySeguimientoGestionAdjuntos;
    }

    /**
     * @param lazySeguimientoGestionAdjuntos the lazySeguimientoGestionAdjuntos to set
     */
    public void setLazySeguimientoGestionAdjuntos(LazyDataModel<AuSolicitudAdjunto> lazySeguimientoGestionAdjuntos) {
        this.lazySeguimientoGestionAdjuntos = lazySeguimientoGestionAdjuntos;
    }

    /**
     * @return the registrosSeguimientoGestionAdjuntos
     */
    public List<AuSolicitudAdjunto> getRegistrosSeguimientoGestionAdjuntos() {
        return registrosSeguimientoGestionAdjuntos;
    }

    /**
     * @param registrosSeguimientoGestionAdjuntos the registrosSeguimientoGestionAdjuntos to set
     */
    public void setRegistrosSeguimientoGestionAdjuntos(List<AuSolicitudAdjunto> registrosSeguimientoGestionAdjuntos) {
        this.registrosSeguimientoGestionAdjuntos = registrosSeguimientoGestionAdjuntos;
    }

    /**
     * @return the paramConsultaSeguimientoGestion
     */
    public ParamConsulta getParamConsultaSeguimientoGestion() {
        return paramConsultaSeguimientoGestion;
    }

    /**
     * @param paramConsultaSeguimientoGestion the paramConsultaSeguimientoGestion to set
     */
    public void setParamConsultaSeguimientoGestion(ParamConsulta paramConsultaSeguimientoGestion) {
        this.paramConsultaSeguimientoGestion = paramConsultaSeguimientoGestion;
    }

    /**
     * @return the estadoCancelarValido
     */
    public boolean isEstadoCancelarValido() {
        return estadoCancelarValido;
    }

    /**
     * @param estadoCancelarValido the estadoCancelarValido to set
     */
    public void setEstadoCancelarValido(boolean estadoCancelarValido) {
        this.estadoCancelarValido = estadoCancelarValido;
    }
}
