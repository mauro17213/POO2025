package com.saviasaludeps.savia.web.tutela.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgadoContacto;
import com.saviasaludeps.savia.web.tutela.servicio.TuJuzgadoServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class TuJuzgadoBean extends Url {

    //Constantes Auxiliares
    private static final String VALOR_TIPO_CONTACTO_CORREO = "3";

    private TuJuzgadoServicioIface tuJuzgadoServicio;
    private TuJuzgado objeto;
    private List<TuJuzgado> registros;
    private LazyDataModel<TuJuzgado> lazyTuJuzgado;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private List<Ubicacion> ubicacionesAntioquia;
    private HashMap<Integer, Ubicacion> hashUbicacionesAntioquia;

    private List<TuJuzgadoContacto> listaTuJuzgadoContacto;
    private TuJuzgadoContacto aucAfiliadoContacto;

    private List<Maestro> listaTipoContacto;
    private HashMap<Integer, Maestro> hashTipoContacto;

    public static final char BORRAR_TELEFONO_CONTACTO = 'a';

    public TuJuzgadoBean() {
        this.objeto = new TuJuzgado();
        Modulo mod = super.validarModulo(Modulo.ID_TU_JUZGADOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyTuJuzgado = new LazyDataModel<TuJuzgado>() {
                private List<TuJuzgado> tuJuzgado;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<TuJuzgado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    tuJuzgado = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return tuJuzgado;
                }

                @Override
                public String getRowKey(TuJuzgado tuJuzgado) {
                    return tuJuzgado.getId().toString();
                }

                @Override
                public TuJuzgado getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (TuJuzgado tuJuzgado : tuJuzgado) {
                        if (id.equals(tuJuzgado.getId())) {
                            return tuJuzgado;
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
        getTuJuzgadoServicio().cargaInial(this);
        listar();
    }

    public TuJuzgado getObjeto() {
        return objeto;
    }

    public void setObjeto(TuJuzgado objeto) {
        this.objeto = objeto;
    }

    public List<TuJuzgado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TuJuzgado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<TuJuzgado> getLazyTuJuzgado() {
        return lazyTuJuzgado;
    }

    public void setLazyTuJuzgado(LazyDataModel<TuJuzgado> lazyTuJuzgado) {
        this.lazyTuJuzgado = lazyTuJuzgado;
    }

    public TuJuzgadoServicioIface getTuJuzgadoServicio() {
        return tuJuzgadoServicio;
    }

    public void setTuJuzgadoServicio(TuJuzgadoServicioIface tuJuzgadoServicio) {
        this.tuJuzgadoServicio = tuJuzgadoServicio;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Maestro> getListaTipoContacto() {
        return listaTipoContacto;
    }

    public void setListaTipoContacto(List<Maestro> listaTipoContacto) {
        this.listaTipoContacto = listaTipoContacto;
    }

    public HashMap<Integer, Maestro> getHashTipoContacto() {
        return hashTipoContacto;
    }

    public void setHashTipoContacto(HashMap<Integer, Maestro> hashTipoContacto) {
        this.hashTipoContacto = hashTipoContacto;
    }

    public List<Ubicacion> getUbicacionesAntioquia() {
        return ubicacionesAntioquia;
    }

    public void setUbicacionesAntioquia(List<Ubicacion> ubicacionesAntioquia) {
        this.ubicacionesAntioquia = ubicacionesAntioquia;
    }

    public HashMap<Integer, Ubicacion> getHashUbicacionesAntioquia() {
        return hashUbicacionesAntioquia;
    }

    public void setHashUbicacionesAntioquia(HashMap<Integer, Ubicacion> hashUbicacionesAntioquia) {
        this.hashUbicacionesAntioquia = hashUbicacionesAntioquia;
    }

    public List<TuJuzgadoContacto> getListaTuJuzgadoContacto() {
        if (listaTuJuzgadoContacto == null) {
            listaTuJuzgadoContacto = new ArrayList<>();
        }
        return listaTuJuzgadoContacto;
    }

    public void setListaTuJuzgadoContacto(List<TuJuzgadoContacto> listaTuJuzgadoContacto) {
        this.listaTuJuzgadoContacto = listaTuJuzgadoContacto;
    }

    public TuJuzgadoContacto getAucAfiliadoContacto() {
        if (aucAfiliadoContacto == null) {
            aucAfiliadoContacto = new TuJuzgadoContacto();
        }
        return aucAfiliadoContacto;
    }

    public void setAucAfiliadoContacto(TuJuzgadoContacto aucAfiliadoContacto) {
        this.aucAfiliadoContacto = aucAfiliadoContacto;
    }

    public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getTuJuzgadoServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        setListaTuJuzgadoContacto(new ArrayList<>());
        super.setAccion(ACCION_VER);
        getTuJuzgadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        setListaTuJuzgadoContacto(new ArrayList<>());
        getTuJuzgadoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();

    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getTuJuzgadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        setListaTuJuzgadoContacto(new ArrayList<>());
        super.setAccion(ACCION_EDITAR);
        getTuJuzgadoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getTuJuzgadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_BORRAR);
        super.setDoAccion(Url.ACCION_BORRAR);
        getTuJuzgadoServicio().Accion(this);
        procesoFinal();
        PrimeFaces.current().ajax().update("frmPersonas");
    }

    public void crearContacto() {
        setAucAfiliadoContacto(new TuJuzgadoContacto());
        PrimeFaces.current().resetInputs("frmCrearContactoJuzgado:panelCrearContactoJuzgado");
        PrimeFaces.current().ajax().update("frmCrearContactoJuzgado:panelCrearContactoJuzgado");
        PrimeFaces.current().executeScript("PF('dialogoCrearContactoJuzgado').show()");
    }

    public void adicionarContacto() {
        int longitudMaximaNumero = 10;
        boolean esContactoValido = true;
        TuJuzgadoContacto tuPersonaContacto = getAucAfiliadoContacto();
        Maestro tipoContacto = getHashTipoContacto().get(tuPersonaContacto.getMaeTipoContactoId());
        if (tipoContacto != null) {
            tuPersonaContacto.setMaeTipoContactoCodigo(tipoContacto.getValor());
            tuPersonaContacto.setMaeTipoContactoValor(tipoContacto.getNombre());
        }

        for (TuJuzgadoContacto contacto : getListaTuJuzgadoContacto()) {
            if (contacto.getContacto().equals(tuPersonaContacto.getContacto())) {
                esContactoValido = false;
                addError("El contacto ya existe");
                break;
            }
        }

        if (tuPersonaContacto.getMaeTipoContactoCodigo().equals(VALOR_TIPO_CONTACTO_CORREO)) {
            if (!tuPersonaContacto.getContacto().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                addError("El correo no es valido");
                esContactoValido = false;
            }
        } else {

            if (esContactoValido && tuPersonaContacto.getContacto().length() > longitudMaximaNumero) {
                addError("El número de contacto excede la longitud de caracteres : " + longitudMaximaNumero);
                esContactoValido = false;
            }

            if (!tuPersonaContacto.getContacto().matches("\\d*")) {
                addError("El número de contacto tiene letras");
                esContactoValido = false;
            }
        }

        if (esContactoValido) {
            tuPersonaContacto.setPosicion(getListaTuJuzgadoContacto().size());
            getListaTuJuzgadoContacto().add(tuPersonaContacto);
        }

        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearContactoJuzgado').hide();");
            PrimeFaces.current().ajax().update("frmCrear:tablaContactoJuzgadoCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaContactoJuzgadoEditar");
        }
        generarMensajes();
    }

    public void borrarContacto(TuJuzgadoContacto personaContacto) {
        List<TuJuzgadoContacto> listaContactos = new ArrayList<>();
        int posicionEliminar = personaContacto.getPosicion();
        int posicionNueva = 0;
        for (TuJuzgadoContacto contacto : getListaTuJuzgadoContacto()) {
            if (contacto.getPosicion() != posicionEliminar) {
                contacto.setPosicion(posicionNueva++);
                listaContactos.add(contacto);
            }
        }

        if (personaContacto.getId() != null) {
            this.getAucAfiliadoContacto().setId(personaContacto.getId());
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_TELEFONO_CONTACTO);
            getTuJuzgadoServicio().Accion(this);
        }

        setListaTuJuzgadoContacto(listaContactos);
        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaContactoJuzgadoCrear");
        PrimeFaces.current().ajax().update("frmEditar:tablaContactoJuzgadoEditar");
        //PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmPersonas");
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = ubicacionesRecursiva.get(id);
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        return ubicacionesFiltradas;
    }
}
