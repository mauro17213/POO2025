/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSedeServicioHabilitacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadoresUnionTemporal;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorBean;
import com.saviasaludeps.savia.web.contratacion.servicio.IpsSedesServicioIface;
import com.saviasaludeps.savia.web.contratacion.servicio.IpsSedesServicioImpl;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
//import com.saviasaludeps.savia.web.singleton.ContratacionSingle;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

/**
 *
 * @author jyperez
 */
@ManagedBean
@ViewScoped
public class IpsSedesBean extends Url {

    private IpsSedesServicioIface ipsSedesServicio;
    private CntPrestador objeto;
    private List<CntPrestador> registros;
    private LazyDataModel<CntPrestador> lazyPrestador;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaClasePrestador;
    private HashMap<Integer, Maestro> hashClasePrestador;
    private List<CntPrestadorSede> listaSedes;
    private List<CntPrestadorSede> listaSedesBorrar;
    private CntPrestadorSede sede;
    private List<Ubicacion> listaUbicaciones;
    private int contadorIdSede;

    //2021-07-22 jyperez adición variables manejo de Sedes y Servicios REPS
    private CntPrestadorSede objetoSede;
    private CntPrestadorSedeServicioHabilitacion objetoSedeServicioHabilitacion;
    private List<CntPrestadorSede> listaGestionSedes;
    private LazyDataModel<CntPrestadorSede> lazySedes;
    private ParamConsulta paramConsultaSedes;
    private List<CntPrestadorSedeServicioHabilitacion> listaServiciosREPS;
    private LazyDataModel<CntPrestadorSedeServicioHabilitacion> lazyServiciosREPS;
    private ParamConsulta paramConsultaServiciosREPS;
    //objeto Buscador Servicios Habilitacion
    private SelServiciosHabilitacionBean selserviciosREPSBean;

    //2022-05-26 jyperez desarrollo contactos
    private int contadorIdContacto;
    private CntPrestadorContacto contacto;
    private List<Maestro> listaTiposContacto;
    private HashMap<Integer, Maestro> hashTiposContacto;
    private List<Maestro> listaAreasContacto;
    private HashMap<Integer, Maestro> hashAreasContacto;

    //2023-07-04 jyperez Desarrollo Unión Temporal
    private CntPrestadoresUnionTemporal objetoUnionTemporal;
    private List<CntPrestadoresUnionTemporal> listaUnionTemporal;
    private LazyDataModel<CntPrestadoresUnionTemporal> lazyUnionTemporal;
    private ParamConsulta paramConsultaUnionTemporal;
    //objeto buscador Prestadores
    private SelPrestadorBean selPrestadorBean;

//    @Autowired
//    private ContratacionSingle contratacionSingle;

    public IpsSedesBean() {
        // el contador actuara en forma descendente con números negativos. Esto con el fin de poder obtener el id correcto en el manejo de listas
        contadorIdSede = -1;
        this.objeto = new CntPrestador();
        this.sede = new CntPrestadorSede();
        Modulo mod = super.validarModulo(Modulo.ID_CONTRATACION_PRESTADORES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyPrestador = new LazyDataModel<CntPrestador>() {

                private List<CntPrestador> prestadores;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    prestadores = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return prestadores;
                }

                @Override
                public String getRowKey(CntPrestador prestador) {
                    return prestador.getId().toString();
                }

                @Override
                public CntPrestador getRowData(String prestadorId) {
                    Integer id = Integer.valueOf(prestadorId);
                    for (CntPrestador vendedor : prestadores) {
                        if (id.equals(vendedor.getId())) {
                            return vendedor;
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
        getIpsSedesServicio().cargaInicial(this);
        listar();
    }

    public CntPrestador getObjeto() {
        return objeto;
    }

    public void setObjeto(CntPrestador objeto) {
        this.objeto = objeto;
    }

    public List<CntPrestador> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntPrestador> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntPrestador> getLazyPrestador() {
        return lazyPrestador;
    }

    public void setLazyPrestador(LazyDataModel<CntPrestador> lazyPrestador) {
        this.lazyPrestador = lazyPrestador;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getIpsSedesServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        // se debe reiniciar la variable de ids
        contadorIdSede = -1;
        super.setAccion(ACCION_CREAR);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        // almacenamos los valores de los maestros seleccionados
        // tipo documento
        objeto.setMaeTipoDocumentoCodigo(hashTiposDocumento.get(objeto.getMaeTipoDocumentoId()).getValor());
        objeto.setMaeTipoDocumentoValor(hashTiposDocumento.get(objeto.getMaeTipoDocumentoId()).getNombre());
        //tipo documento rep
        objeto.setMaeTipoDocumentoRepCodigo(hashTiposDocumento.get(objeto.getMaeTipoDocumentoRepId()).getValor());
        objeto.setMaeTipoDocumentoRepValor(hashTiposDocumento.get(objeto.getMaeTipoDocumentoRepId()).getNombre());
        //clase prestador
        objeto.setMaeClasePrestadorCodigo(hashClasePrestador.get(objeto.getMaeClasePrestador()).getValor());
        objeto.setMaeClasePrestadorValor(hashClasePrestador.get(objeto.getMaeClasePrestador()).getNombre());
        //2023-07-05 jyperez se valida si se ha seleccionado unión temporal para actualizar el prestador
        if (ContratacionParametro.TIPO_PRESTADOR_UNION_TEMPORAL.equals(objeto.getMaeClasePrestadorCodigo())) {
            objeto.setUnionTemporal(true);
        } else {
            objeto.setUnionTemporal(false);
        }
        getObjeto().setListaPrestadorSedes(listaSedes);
        super.setAccion(ACCION_GUARDAR);
        getIpsSedesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        contadorIdSede = -1;
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    /**
     * Función para Ver información adicional de la Sede
     *
     * @param sede
     */
    public void verSede(CntPrestadorSede sede) {
        this.sede = sede;
        PrimeFaces.current().resetInputs("frmVerSede");
        PrimeFaces.current().ajax().update("frmVerSede");
        PrimeFaces.current().executeScript("PF('dialogoVerSede').show()");
    }

    /**
     * Función llamada desde Crear Prestador y desde Editar Prestador, para
     * adicionar una nueva Sede
     */
    public void adicionarSede() {
        sede = new CntPrestadorSede();
        PrimeFaces.current().resetInputs("frmCrearSede");
        PrimeFaces.current().ajax().update("frmCrearSede");
        PrimeFaces.current().executeScript("PF('dialogoCrearSede').show()");
    }

    /**
     * Función llamada desde Crear Prestador, para guardar una sede -- Esto
     * todavía no se refleja en la BD debido a que debe hacerse el proceso de
     * creación de Prestador
     */
    public void guardarSede() {

        sede.setId(contadorIdSede);
        sede.setNuevoRegistro(true);
        //recordar que el contador va a la inversa con valores negativos
        contadorIdSede--;
        listaSedes.add(sede);
        PrimeFaces.current().ajax().update("frmCrear:tablaSedes");
        PrimeFaces.current().ajax().update("frmEditar:tablaSedes");
        PrimeFaces.current().executeScript("PF('dialogoCrearSede').hide()");
    }

    /**
     * Función usada desde Crear Prestadores con el objetivo de eliminar una
     * sede de la lista
     *
     * @param id
     */
    public void retirarSede(int id) {
        for (int i = 0; i < listaSedes.size(); i++) {
            CntPrestadorSede agrup = listaSedes.get(i);
            if (agrup.getId() != null && agrup.getId() == id) {
                //if (agrup.getIdInsertar() != null && agrup.getIdInsertar() == id) {
                listaSedes.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaSedes");
        PrimeFaces.current().ajax().update("frmEditar:tablaSedes");
    }

    public void editarSede(int id) {
        sede = new CntPrestadorSede();
        for (CntPrestadorSede sed : listaSedes) {
            if (sed.getId() != null && sed.getId() == id) {
                sede = sed;
                break;
            }
        }
        PrimeFaces.current().resetInputs("frmEditarSede");
        PrimeFaces.current().ajax().update("frmEditarSede");
        PrimeFaces.current().executeScript("PF('dialogoEditarSede').show()");
    }

    public void modificarSede() {
        for (int i = 0; i < listaSedes.size(); i++) {
            CntPrestadorSede agrup = listaSedes.get(i);
            if (agrup.getId() != null && sede.getId().equals(agrup.getId())) {
                // validamos que sede no sea un registro nuevo
                if (!sede.isNuevoRegistro()) {
                    sede.setEditado(true);
                } else {
                    sede.setEditado(false);
                }
                listaSedes.set(i, sede);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmEditar:tablaSedes");
        PrimeFaces.current().executeScript("PF('dialogoEditarSede').hide()");
    }

    /**
     * Función que se llama desde Editar Sede - para borrar una Sede Existente
     *
     * @param id
     */
    public void borrarSede(int id) {
        for (int i = 0; i < listaSedes.size(); i++) {
            CntPrestadorSede agrup = listaSedes.get(i);
            if (agrup.getId() == id) {
                listaSedesBorrar.add(agrup);
                listaSedes.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmEditar:tablaSedes");
    }

    public void modificar() {
        // almacenamos los valores de los maestros seleccionados
        // tipo documento
        objeto.setMaeTipoDocumentoCodigo(hashTiposDocumento.get(objeto.getMaeTipoDocumentoId()).getValor());
        objeto.setMaeTipoDocumentoValor(hashTiposDocumento.get(objeto.getMaeTipoDocumentoId()).getNombre());
        //tipo documento rep
        objeto.setMaeTipoDocumentoRepCodigo(hashTiposDocumento.get(objeto.getMaeTipoDocumentoRepId()).getValor());
        objeto.setMaeTipoDocumentoRepValor(hashTiposDocumento.get(objeto.getMaeTipoDocumentoRepId()).getNombre());
        //clase prestador
        objeto.setMaeClasePrestadorCodigo(hashClasePrestador.get(objeto.getMaeClasePrestador()).getValor());
        objeto.setMaeClasePrestadorValor(hashClasePrestador.get(objeto.getMaeClasePrestador()).getNombre());
        super.setAccion(ACCION_MODIFICAR);
        getIpsSedesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getIpsSedesServicio().Accion(this);
        procesoFinal();
    }

    public void gestionarSedes(int idPrestador) {
        this.objeto = new CntPrestador(idPrestador);
        inicializarTablaSedes(idPrestador);
        //le pasamos como parámetro el id del Prestador
        getParamConsultaSedes().setParametroConsulta1(idPrestador);
        //permisos que hay que enviar
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGestionarSedes");
        PrimeFaces.current().ajax().update("frmGestionarSedes:tablaRegistrosGestionSede");
        PrimeFaces.current().executeScript("PF('dialogoGestionarSedes').show()");
        procesoFinal();
    }

    public void inicializarTablaSedes(int id) {
        this.setParamConsultaSedes(new ParamConsulta());
        this.getParamConsultaSedes().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de contrato Sede
        lazySedes = new LazyDataModel<CntPrestadorSede>() {

            private List<CntPrestadorSede> sedes;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSedes().getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaSedes().setPrimerRegistro(primerRegistro);
                getParamConsultaSedes().setRegistrosPagina(registrosPagina);
                getParamConsultaSedes().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaSedes().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaSedes();
                sedes = getListaGestionSedes();
                setRowCount(getParamConsultaSedes().getCantidadRegistros());
                return sedes;
            }

            @Override
            public String getRowKey(CntPrestadorSede sedes) {
                return sedes.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String sedeId) {
                Integer id = Integer.valueOf(sedeId);
                for (CntPrestadorSede novedad : sedes) {
                    if (id.equals(novedad.getId())) {
                        return novedad;
                    }
                }
                return null;
            }

        };

    }

    public void refrescarListaSedes() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getIpsSedesServicio().Accion(this);
    }

    public void gestionarServiciosREPS(CntPrestadorSede sede) {
        this.setObjetoSede(sede);
        inicializarTablaServicioREPS(sede);
        //le pasamos como parámetro el id de la Sede
        getParamConsultaServiciosREPS().setParametroConsulta1(sede.getId());
        //permisos que hay que enviar
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGestionarServiciosREPS");
        PrimeFaces.current().ajax().update("frmGestionarServiciosREPS:tablaRegistrosServiciosREPS");
        PrimeFaces.current().executeScript("PF('dialogoGestionarServiciosREPS').show()");
        procesoFinal();
    }

    public void inicializarTablaServicioREPS(CntPrestadorSede sede) {
        this.setParamConsultaServiciosREPS(new ParamConsulta());
        this.getParamConsultaServiciosREPS().setParametroConsulta1(sede.getId());// este valor será nuestro diferenciador a nivel de Sede
        lazyServiciosREPS = new LazyDataModel<CntPrestadorSedeServicioHabilitacion>() {

            private List<CntPrestadorSedeServicioHabilitacion> serviciosREPS;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaServiciosREPS().getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSedeServicioHabilitacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaServiciosREPS().setPrimerRegistro(primerRegistro);
                getParamConsultaServiciosREPS().setRegistrosPagina(registrosPagina);
                getParamConsultaServiciosREPS().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaServiciosREPS().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaServiciosREPS();
                serviciosREPS = getListaServiciosREPS();
                setRowCount(getParamConsultaServiciosREPS().getCantidadRegistros());
                return serviciosREPS;
            }

            @Override
            public String getRowKey(CntPrestadorSedeServicioHabilitacion serviciosREPS) {
                return serviciosREPS.getId().toString();
            }

            @Override
            public CntPrestadorSedeServicioHabilitacion getRowData(String servicioId) {
                Integer id = Integer.valueOf(servicioId);
                for (CntPrestadorSedeServicioHabilitacion novedad : serviciosREPS) {
                    if (id.equals(novedad.getId())) {
                        return novedad;
                    }
                }
                return null;
            }

        };

    }

    public void refrescarListaServiciosREPS() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getIpsSedesServicio().Accion(this);
    }

    public void verServicioREPS(CntPrestadorSedeServicioHabilitacion servicioREPS) {
        this.objetoSedeServicioHabilitacion = servicioREPS;
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_VER);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVerServicioREPS");
        PrimeFaces.current().ajax().update("frmVerServicioREPS");
        PrimeFaces.current().executeScript("PF('dialogoVerServicioREPS').show()");
        procesoFinal();
    }

    public void crearServicioREPS() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_CREAR);
        //getIpsSedesServicio().Accion(this);
        this.objetoSedeServicioHabilitacion = new CntPrestadorSedeServicioHabilitacion();
        this.objetoSedeServicioHabilitacion.setCntPrestadorSede(objetoSede);
        PrimeFaces.current().resetInputs("frmCrearServicioREPS");
        PrimeFaces.current().ajax().update("frmCrearServicioREPS");
        PrimeFaces.current().executeScript("PF('dialogoCrearServicioREPS').show()");
        procesoFinal();
    }

    public void guardarServicioREPS() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getIpsSedesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearServicioREPS').hide()");
            PrimeFaces.current().ajax().update("frmGestionarServiciosREPS");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void editarServicioREPS(CntPrestadorSedeServicioHabilitacion servicioREPS) {
        this.objetoSedeServicioHabilitacion = servicioREPS;
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_EDITAR);
        getIpsSedesServicio().Accion(this);
        //le asignamos los datos del objeto de sede
        this.objetoSedeServicioHabilitacion.setCntPrestadorSede(objetoSede);
        PrimeFaces.current().resetInputs("frmEditarServicioREPS");
        PrimeFaces.current().ajax().update("frmEditarServicioREPS");
        PrimeFaces.current().executeScript("PF('dialogoEditarServicioREPS').show()");
        procesoFinal();
    }

    public void modificarServicioREPS() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getIpsSedesServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarServicioREPS').hide()");
            PrimeFaces.current().ajax().update("frmGestionarServiciosREPS");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void borrarServicioREPS(Integer id) {
        this.objetoSedeServicioHabilitacion = new CntPrestadorSedeServicioHabilitacion(id);
        super.setAccion(ACCION_ADICIONAL_5);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionarServiciosREPS");
        procesoFinal();
    }

    public void cerrarDialogoMaServiciosHabilitacion() {
        getObjetoSedeServicioHabilitacion().setMaServicioHabilitacion(getSelserviciosREPSBean().getObjeto());
        getObjetoSedeServicioHabilitacion().setMaServiciosHabilitacionCodigo(getSelserviciosREPSBean().getObjeto().getCodigo() + "");//VALIDAR
        getObjetoSedeServicioHabilitacion().setMaServiciosHabilitacionValor(getSelserviciosREPSBean().getObjeto().getNombre());//validar
        //limpiamos el objeto del buscador
        getSelserviciosREPSBean().setObjeto(new MaServicioHabilitacion());
        PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').hide()");
        // actualizar las pantalla de Creación y Edicion del Servicio REPS
        PrimeFaces.current().ajax().update("frmCrearServicioREPS");
        PrimeFaces.current().ajax().update("frmEditarServicioREPS");
    }

    public void consultarServicioHabilitacion() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').show()");
            PrimeFaces.current().ajax().update("frmServiciosHabilitacionBusqueda");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void gestionarUnionTemporal(Integer idPrestador) {
        this.objeto = new CntPrestador(idPrestador);
        inicializarTablaUnionTemporal(idPrestador);
        //le pasamos como parámetro el id del Prestador
        getParamConsultaUnionTemporal().setParametroConsulta1(idPrestador);
        //permisos que hay que enviar
        super.setAccion(ACCION_ADICIONAL_6);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGestionarUnionTemporal");
        PrimeFaces.current().ajax().update("frmGestionarUnionTemporal:tablaRegistrosUnionTemporal");
        PrimeFaces.current().executeScript("PF('dialogoGestionarUnionTemporal').show()");
        procesoFinal();
    }

    public void inicializarTablaUnionTemporal(int id) {
        this.setParamConsultaUnionTemporal(new ParamConsulta());
        this.getParamConsultaUnionTemporal().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de contrato Sede
        lazyUnionTemporal = new LazyDataModel<CntPrestadoresUnionTemporal>() {

            private List<CntPrestadoresUnionTemporal> listaUnionTemporal;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<CntPrestadoresUnionTemporal> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaUnionTemporal().setPrimerRegistro(primerRegistro);
                getParamConsultaUnionTemporal().setRegistrosPagina(registrosPagina);
                getParamConsultaUnionTemporal().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaUnionTemporal().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaUnionTemporal();
                listaUnionTemporal = getListaUnionTemporal();
                setRowCount(getParamConsultaUnionTemporal().getCantidadRegistros());
                return listaUnionTemporal;
            }

            @Override
            public String getRowKey(CntPrestadoresUnionTemporal unionTemporal) {
                return unionTemporal.getId().toString();
            }

            @Override
            public CntPrestadoresUnionTemporal getRowData(String unionTemporalId) {
                Integer id = Integer.valueOf(unionTemporalId);
                for (CntPrestadoresUnionTemporal unionTemp : listaUnionTemporal) {
                    if (id.equals(unionTemp.getId())) {
                        return unionTemp;
                    }
                }
                return null;
            }

        };

    }

    public void refrescarListaUnionTemporal() {
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        getIpsSedesServicio().Accion(this);
    }

    public void borrarUnionTemporal(Integer id) {
        this.objetoUnionTemporal = new CntPrestadoresUnionTemporal(id);
        super.setAccion(ACCION_ADICIONAL_8);
        getIpsSedesServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionarUnionTemporal");
        procesoFinal();
    }

    public void crearUnionTemporal() {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_CREAR);
        //getIpsSedesServicio().Accion(this);
        this.objetoUnionTemporal = new CntPrestadoresUnionTemporal();
        this.objetoUnionTemporal.setCntPrestadorUnionTemporal(objeto);
        //MODIFICAR acá hacemos que se lance el buscador de Prestadores y cuando este seleccione un valor, entonces cargamos la creación
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
        PrimeFaces.current().ajax().update("frmPrestadorLista");
        procesoFinal();
    }

    public void cerrarDialogoSelPrestador() {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_GUARDAR);
        this.objetoUnionTemporal.setCntPrestador(getSelPrestadorBean().getObjeto());
        getIpsSedesServicio().Accion(this);
        if (!super.isError()) {
            //limpiamos el objeto del buscador
            getSelPrestadorBean().setObjeto(new CntPrestador());
            PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");
            // actualizar las pantalla de Listar Union Temporal
            PrimeFaces.current().ajax().update("frmGestionarUnionTemporal");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmPrestadores");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Gestionar Sedes", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Gestionar Servicios REPS", getObjetoSede().toString());
                            break;
                        case Url.ACCION_VER:
                            crearLog("Ver Servicio REPS", getObjetoSedeServicioHabilitacion().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear Servicio REPS", getObjetoSedeServicioHabilitacion().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Servicio REPS", getObjetoSedeServicioHabilitacion().toString());
                            break;
                        case Url.ACCION_EDITAR:
                            crearLog("Editar Servicio REPS", getObjetoSedeServicioHabilitacion().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog("Modificar Servicio REPS", getObjetoSedeServicioHabilitacion().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    crearLog("Eliminar Servicio REPS", getObjetoSedeServicioHabilitacion().toString());
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Listar Unión Temporal");
                            break;
                        case Url.ACCION_VER:
                            crearLog("Ver Unión Temporal", getObjetoUnionTemporal().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear Unión Temporal");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Unión Temporal", getObjetoUnionTemporal().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_8:
                    crearLog("Eliminar Unión Temporal", getObjetoUnionTemporal().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void buttonAction() {
        addMensaje("Un proyecto genérico permite que las personas pertenecientes "
                + "al área a la que se asignó dicho proyecto, puedan crear sus tareas "
                + "y gestionarlas de manera independiente");
        generarMensajes();
    }

    /**
     * @return the listaTiposDocumento
     */
    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    /**
     * @param listaTiposDocumento the listaTiposDocumento to set
     */
    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    /**
     * @return the hashTiposDocumento
     */
    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    /**
     * @param hashTiposDocumento the hashTiposDocumento to set
     */
    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getClasePrestador(Integer id) {
        try {
            return hashClasePrestador.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @return the listaClasePrestador
     */
    public List<Maestro> getListaClasePrestador() {
        return listaClasePrestador;
    }

    /**
     * @param listaClasePrestador the listaClasePrestador to set
     */
    public void setListaClasePrestador(List<Maestro> listaClasePrestador) {
        this.listaClasePrestador = listaClasePrestador;
    }

    /**
     * @return the hashClasePrestador
     */
    public HashMap<Integer, Maestro> getHashClasePrestador() {
        return hashClasePrestador;
    }

    /**
     * @param hashClasePrestador the hashClasePrestador to set
     */
    public void setHashClasePrestador(HashMap<Integer, Maestro> hashClasePrestador) {
        this.hashClasePrestador = hashClasePrestador;
    }

    /**
     * @return the sede
     */
    public CntPrestadorSede getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(CntPrestadorSede sede) {
        this.sede = sede;
    }

    /**
     * @return the listaSedes
     */
    public List<CntPrestadorSede> getListaSedes() {
        return listaSedes;
    }

    /**
     * @param listaSedes the listaSedes to set
     */
    public void setListaSedes(List<CntPrestadorSede> listaSedes) {
        this.listaSedes = listaSedes;
    }

    /**
     * @return the listaUbicaciones
     */
    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    /**
     * @param listaUbicaciones the listaUbicaciones to set
     */
    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    /**
     * @return the ipsSedesServicio
     */
    public IpsSedesServicioIface getIpsSedesServicio() {
        if (ipsSedesServicio == null) {
            ipsSedesServicio = new IpsSedesServicioImpl();
        }
        return ipsSedesServicio;
    }

    /**
     * @param ipsSedesServicio the ipsSedesServicio to set
     */
    public void setIpsSedesServicio(IpsSedesServicioIface ipsSedesServicio) {
        this.ipsSedesServicio = ipsSedesServicio;
    }

    /**
     * @return the contadorIdSede
     */
    public int getContadorIdSede() {
        return contadorIdSede;
    }

    /**
     * @param contadorIdSede the contadorIdSede to set
     */
    public void setContadorIdSede(int contadorIdSede) {
        this.contadorIdSede = contadorIdSede;
    }

    /**
     * @return the listaSedesBorrar
     */
    public List<CntPrestadorSede> getListaSedesBorrar() {
        return listaSedesBorrar;
    }

    /**
     * @param listaSedesBorrar the listaSedesBorrar to set
     */
    public void setListaSedesBorrar(List<CntPrestadorSede> listaSedesBorrar) {
        this.listaSedesBorrar = listaSedesBorrar;
    }

    public String getNombreMunicipioDepartamento(Ubicacion ubi) {
        String nombre = "";
        String nombrePadre = "";
        nombre = ubi.getNombre();
        if (ubi.getUbicacionPadre() != null) {
            nombrePadre = ubi.getUbicacionPadre().getNombre();
        }
        if (!nombrePadre.equals("")) {
            nombre = nombre + " - " + nombrePadre;
        }
        return nombre;
    }

    public String getNombreMunicipioDepartamento(int ubicacionId) {
        String nombre = "";
        String nombrePadre = "";
        Ubicacion ubi = null;
        for (Ubicacion u : listaUbicaciones) {
            if (u.getId().equals(ubicacionId)) {
                ubi = u;
            }
        }
        if (ubi != null) {
            nombre = ubi.getNombre();
            if (ubi.getUbicacionPadre() != null) {
                nombrePadre = ubi.getUbicacionPadre().getNombre();
            }
            if (!nombrePadre.equals("")) {
                nombre = nombre + " - " + nombrePadre;
            }
        }
        return nombre;
    }

    public String getZona(String zona) {
        String mensaje = "";
        switch (zona) {
            case "U":
                mensaje = "Urbana";
                break;
            case "R":
                mensaje = "Rural";
                break;
        }
        return mensaje;
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

    /**
     * Función llamada desde Crear Afiliado y desde Editar Afiliado, para
     * adicionar un nuevo contacto
     */
    public void adicionarContacto() {
        contacto = new CntPrestadorContacto();
        // el contacto siempre se creará activo
        contacto.setActivo(true);
        PrimeFaces.current().resetInputs("frmCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    public void guardarContacto() {
        boolean guardar = true;
        //obtenemos los valores del maestro
        try {
            Maestro tipo = hashTiposContacto.get(contacto.getMaeTipoContactoId());
            if (tipo != null) {
                switch (tipo.getValor()) {
                    case ContratacionParametro.TIPO_CONTACTO_CELULAR:
                    case ContratacionParametro.TIPO_CONTACTO_TELEFONO:
                    case ContratacionParametro.TIPO_CONTACTO_PBX:
                        if (!validarCampoNumerico(contacto.getContacto(), 10).equals("")) {
                            addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el valor ingresado debe ser numérico de 10 dígitos.");
                            guardar = false;
                        }
                        break;
                    case ContratacionParametro.TIPO_CONTACTO_CORREO_ELECTRONICO:
                        if (!(validarEmailCorrecto(contacto.getContacto()).equals(""))) {
                            addError("Si el Tipo seleccionado es " + tipo.getNombre() + " se debe ingresar el formato válido.");
                            guardar = false;
                        }
                        break;
                }
                contacto.setMaeTipoContactoCodigo(tipo.getValor());
                contacto.setMaeTipoContactoValor(tipo.getNombre());
            } else {
                guardar = false;
                addError("No se encontró el valor maestro del Tipo seleccionado.");
            }
        } catch (Exception e) {
            addError("No se encontró el valor maestro del Tipo seleccionado.");
            contacto.setMaeTipoContactoCodigo("");
            contacto.setMaeTipoContactoValor("");
            guardar = false;
        }
        //cargamos el maestro Area
        //obtenemos datos de los maestros
        Maestro valor = hashAreasContacto.get(contacto.getMaeAreaContactoId());
        if (valor != null) {
            contacto.setMaeAreaContactoCodigo(valor.getValor());
            contacto.setMaeAreaContactoValor(valor.getNombre());
        } else {
            addError("Ocurrió un eror consultando el maestro de Áreas Contacto (no hay valores). Consulte al administrador.");
            guardar = false;
        }
        if (guardar) {
            contacto.setId(contadorIdContacto);
            contacto.setNuevoRegistro(true);
            //recordar que el contador va a la inversa con valores negativos
            contadorIdContacto--;
            sede.getListaCntPrestadorContactos().add(contacto);
            PrimeFaces.current().ajax().update("frmCrearSede:tablaContactos");
            PrimeFaces.current().ajax().update("frmEditarSede:tablaContactos");
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide()");
        } else {
            generarMensajes();
        }
    }

    /**
     * Función para validar que la escritura de un email sea correcta.
     *
     * @param email
     * @return
     */
    public String validarEmailCorrecto(String email) {
        String mensaje = "";
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (email != null
                && !email.trim().equals("")
                && mather.find()) {
            mensaje = "";

        } else if (email != null
                && !email.trim().equals("")
                && !email.isBlank()) {
            mensaje = "Email: Debe ingresarse un email válido";
        }

        return mensaje;
    }

    /**
     * función para validar que un campo sea numérico y que tenga un tamaño
     * especifico de dígitos
     *
     * @param numero
     * @param tamanio
     * @return
     */
    public String validarCampoNumerico(String numero, int tamanio) {
        String mensaje;
        if (numero.matches("\\d*") && numero.length() == tamanio) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato igual a " + tamanio + " dígitos.";
        }

        return mensaje;
    }

    /**
     * Función usada desde Crear Sedes con el objetivo de eliminar un contacto
     * de la lista
     *
     * @param id
     */
    public void retirarContacto(int id) {
        for (int i = 0; i < sede.getListaCntPrestadorContactos().size(); i++) {
            CntPrestadorContacto agrup = sede.getListaCntPrestadorContactos().get(i);
            if (agrup.getId() != null && agrup.getId() == id) {
                //if (agrup.getIdInsertar() != null && agrup.getIdInsertar() == id) {
                sede.getListaCntPrestadorContactos().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrearSede:tablaContactos");
        PrimeFaces.current().ajax().update("frmEditarSede:tablaContactos");
    }

    /**
     * Función que se llama desde Editar Sede - para borrar un contacto
     * Existente
     *
     * @param id
     */
    public void borrarContacto(int id) {
        for (int i = 0; i < sede.getListaCntPrestadorContactos().size(); i++) {
            CntPrestadorContacto agrup = sede.getListaCntPrestadorContactos().get(i);
            if (agrup.getId() == id) {
                sede.getListaCntPrestadorContactosBorrar().add(agrup);
                sede.getListaCntPrestadorContactos().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmEditarSede:tablaContactos");
    }

    /**
     * @return the lazySedes
     */
    public LazyDataModel<CntPrestadorSede> getLazySedes() {
        return lazySedes;
    }

    /**
     * @param lazySedes the lazySedes to set
     */
    public void setLazySedes(LazyDataModel<CntPrestadorSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    /**
     * @return the paramConsultaSedes
     */
    public ParamConsulta getParamConsultaSedes() {
        return paramConsultaSedes;
    }

    /**
     * @param paramConsultaSedes the paramConsultaSedes to set
     */
    public void setParamConsultaSedes(ParamConsulta paramConsultaSedes) {
        this.paramConsultaSedes = paramConsultaSedes;
    }

    /**
     * @return the lazyServiciosREPS
     */
    public LazyDataModel<CntPrestadorSedeServicioHabilitacion> getLazyServiciosREPS() {
        return lazyServiciosREPS;
    }

    /**
     * @param lazyServiciosREPS the lazyServiciosREPS to set
     */
    public void setLazyServiciosREPS(LazyDataModel<CntPrestadorSedeServicioHabilitacion> lazyServiciosREPS) {
        this.lazyServiciosREPS = lazyServiciosREPS;
    }

    /**
     * @return the paramConsultaServiciosREPS
     */
    public ParamConsulta getParamConsultaServiciosREPS() {
        return paramConsultaServiciosREPS;
    }

    /**
     * @param paramConsultaServiciosREPS the paramConsultaServiciosREPS to set
     */
    public void setParamConsultaServiciosREPS(ParamConsulta paramConsultaServiciosREPS) {
        this.paramConsultaServiciosREPS = paramConsultaServiciosREPS;
    }

    /**
     * @return the selserviciosREPSBean
     */
    public SelServiciosHabilitacionBean getSelserviciosREPSBean() {
        selserviciosREPSBean = (SelServiciosHabilitacionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selServiciosHabilitacionBean");
        return selserviciosREPSBean;
    }

    /**
     * @param selserviciosREPSBean the selserviciosREPSBean to set
     */
    public void setSelserviciosREPSBean(SelServiciosHabilitacionBean selserviciosREPSBean) {
        this.selserviciosREPSBean = selserviciosREPSBean;
    }

    /**
     * @return the listaGestionSedes
     */
    public List<CntPrestadorSede> getListaGestionSedes() {
        return listaGestionSedes;
    }

    /**
     * @param listaGestionSedes the listaGestionSedes to set
     */
    public void setListaGestionSedes(List<CntPrestadorSede> listaGestionSedes) {
        this.listaGestionSedes = listaGestionSedes;
    }

    /**
     * @return the objetoSede
     */
    public CntPrestadorSede getObjetoSede() {
        return objetoSede;
    }

    /**
     * @param objetoSede the objetoSede to set
     */
    public void setObjetoSede(CntPrestadorSede objetoSede) {
        this.objetoSede = objetoSede;
    }

    /**
     * @return the listaServiciosREPS
     */
    public List<CntPrestadorSedeServicioHabilitacion> getListaServiciosREPS() {
        return listaServiciosREPS;
    }

    /**
     * @param listaServiciosREPS the listaServiciosREPS to set
     */
    public void setListaServiciosREPS(List<CntPrestadorSedeServicioHabilitacion> listaServiciosREPS) {
        this.listaServiciosREPS = listaServiciosREPS;
    }

    /**
     * @return the objetoSedeServicioHabilitacion
     */
    public CntPrestadorSedeServicioHabilitacion getObjetoSedeServicioHabilitacion() {
        return objetoSedeServicioHabilitacion;
    }

    /**
     * @param objetoSedeServicioHabilitacion the objetoSedeServicioHabilitacion
     * to set
     */
    public void setObjetoSedeServicioHabilitacion(CntPrestadorSedeServicioHabilitacion objetoSedeServicioHabilitacion) {
        this.objetoSedeServicioHabilitacion = objetoSedeServicioHabilitacion;
    }

    /**
     * @return the contadorIdContacto
     */
    public int getContadorIdContacto() {
        return contadorIdContacto;
    }

    /**
     * @param contadorIdContacto the contadorIdContacto to set
     */
    public void setContadorIdContacto(int contadorIdContacto) {
        this.contadorIdContacto = contadorIdContacto;
    }

    /**
     * @return the contacto
     */
    public CntPrestadorContacto getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(CntPrestadorContacto contacto) {
        this.contacto = contacto;
    }

    /**
     * @return the listaTiposContacto
     */
    public List<Maestro> getListaTiposContacto() {
        return listaTiposContacto;
    }

    /**
     * @param listaTiposContacto the listaTiposContacto to set
     */
    public void setListaTiposContacto(List<Maestro> listaTiposContacto) {
        this.listaTiposContacto = listaTiposContacto;
    }

    /**
     * @return the hashTiposContacto
     */
    public HashMap<Integer, Maestro> getHashTiposContacto() {
        return hashTiposContacto;
    }

    /**
     * @param hashTiposContacto the hashTiposContacto to set
     */
    public void setHashTiposContacto(HashMap<Integer, Maestro> hashTiposContacto) {
        this.hashTiposContacto = hashTiposContacto;
    }

    /**
     * @return the listaAreasContacto
     */
    public List<Maestro> getListaAreasContacto() {
        return listaAreasContacto;
    }

    /**
     * @param listaAreasContacto the listaAreasContacto to set
     */
    public void setListaAreasContacto(List<Maestro> listaAreasContacto) {
        this.listaAreasContacto = listaAreasContacto;
    }

    /**
     * @return the hashAreasContacto
     */
    public HashMap<Integer, Maestro> getHashAreasContacto() {
        return hashAreasContacto;
    }

    /**
     * @param hashAreasContacto the hashAreasContacto to set
     */
    public void setHashAreasContacto(HashMap<Integer, Maestro> hashAreasContacto) {
        this.hashAreasContacto = hashAreasContacto;
    }

    /**
     * @return the objetoUnionTemporal
     */
    public CntPrestadoresUnionTemporal getObjetoUnionTemporal() {
        return objetoUnionTemporal;
    }

    /**
     * @param objetoUnionTemporal the objetoUnionTemporal to set
     */
    public void setObjetoUnionTemporal(CntPrestadoresUnionTemporal objetoUnionTemporal) {
        this.objetoUnionTemporal = objetoUnionTemporal;
    }

    /**
     * @return the listaUnionTemporal
     */
    public List<CntPrestadoresUnionTemporal> getListaUnionTemporal() {
        return listaUnionTemporal;
    }

    /**
     * @param listaUnionTemporal the listaUnionTemporal to set
     */
    public void setListaUnionTemporal(List<CntPrestadoresUnionTemporal> listaUnionTemporal) {
        this.listaUnionTemporal = listaUnionTemporal;
    }

    /**
     * @return the lazyUnionTemporal
     */
    public LazyDataModel<CntPrestadoresUnionTemporal> getLazyUnionTemporal() {
        return lazyUnionTemporal;
    }

    /**
     * @param lazyUnionTemporal the lazyUnionTemporal to set
     */
    public void setLazyUnionTemporal(LazyDataModel<CntPrestadoresUnionTemporal> lazyUnionTemporal) {
        this.lazyUnionTemporal = lazyUnionTemporal;
    }

    /**
     * @return the paramConsultaUnionTemporal
     */
    public ParamConsulta getParamConsultaUnionTemporal() {
        return paramConsultaUnionTemporal;
    }

    /**
     * @param paramConsultaUnionTemporal the paramConsultaUnionTemporal to set
     */
    public void setParamConsultaUnionTemporal(ParamConsulta paramConsultaUnionTemporal) {
        this.paramConsultaUnionTemporal = paramConsultaUnionTemporal;
    }

    /**
     * @return the selPrestadorBean
     */
    public SelPrestadorBean getSelPrestadorBean() {
        selPrestadorBean = (SelPrestadorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorBean");
        return selPrestadorBean;
    }

    /**
     * @param selPrestadorBean the selPrestadorBean to set
     */
    public void setSelPrestadorBean(SelPrestadorBean selPrestadorBean) {
        this.selPrestadorBean = selPrestadorBean;
    }

    /**
     * @return the contratacionSingle
     */
//    public ContratacionSingle getContratacionSingle() {
//        return contratacionSingle;
//    }
//
//    /**
//     * @param contratacionSingle the contratacionSingle to set
//     */
//    public void setContratacionSingle(ContratacionSingle contratacionSingle) {
//        this.contratacionSingle = contratacionSingle;
//    }
}
