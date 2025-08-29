package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusPersonaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AusPersonaBean extends Url {

    private AusPersonaServicioIface ausPersonaServicio;
    private AusPersona objeto;
    private AsegAfiliado afiliadoCompleto;
    private List<AusPersona> registros;
    private LazyDataModel<AusPersona> lazyAusPersona;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaSexo;
    private HashMap<Integer, Maestro> hashSexo;
    private List<Maestro> listaEstadosAusPersona;
    private HashMap<Integer, Maestro> hashEstadosPersona;
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private final static Map<Integer, Integer> listaTiposEstratos;
    private boolean regimen;

    private AusPersonaTelefono ausPersonaTelefono;
    private List<AusPersonaTelefono> listaausPersonaTelefono;

    static {
        listaTiposEstratos = AusPersona.getTiposEstratos();
    }

    public AusPersonaBean() {
        this.objeto = new AusPersona();
        Modulo mod = super.validarModulo(Modulo.ID_AUS_PERSONAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyAusPersona = new LazyDataModel<AusPersona>() {
                private List<AusPersona> ausPersona;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AusPersona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    ausPersona = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return ausPersona;
                }

                @Override
                public String getRowKey(AusPersona ausPersona) {
                    return ausPersona.getId().toString();
                }

                @Override
                public AusPersona getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (AusPersona ausPersona : ausPersona) {
                        if (id.equals(ausPersona.getId())) {
                            return ausPersona;
                        }
                    }
                    return null;
                }
            };
        }
    }

    public AusPersonaBean(AusPersona persona) {
        this.objeto = persona;
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAusPersonaServicio().cargaInial(this);
        listar();
    }

    public AusPersonaTelefono getAusPersonaTelefono() {
        if (ausPersonaTelefono == null) {
            ausPersonaTelefono = new AusPersonaTelefono();
        }
        return ausPersonaTelefono;
    }

    public void setAusPersonaTelefono(AusPersonaTelefono ausPersonaTelefono) {
        this.ausPersonaTelefono = ausPersonaTelefono;
    }

    public List<AusPersonaTelefono> getListaausPersonaTelefono() {
        if (listaausPersonaTelefono == null) {
            listaausPersonaTelefono = new ArrayList<>();
        }
        return listaausPersonaTelefono;
    }

    public void setListaausPersonaTelefono(List<AusPersonaTelefono> listaausPersonaTelefono) {
        this.listaausPersonaTelefono = listaausPersonaTelefono;
    }

    public Map<Integer, Integer> getListaTiposEstratos() {
        return listaTiposEstratos;
    }

    public AusPersona getObjeto() {
        return objeto;
    }

    public void setObjeto(AusPersona objeto) {
        this.objeto = objeto;
    }

    public List<AusPersona> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AusPersona> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AusPersona> getLazyAusPersona() {
        return lazyAusPersona;
    }

    public void setLazyAusPersona(LazyDataModel<AusPersona> lazyAusPersona) {
        this.lazyAusPersona = lazyAusPersona;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public String getTipoDocumento(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public int getTipoEstrato(int id) {
        try {
            return listaTiposEstratos.get(id);
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Maestro> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Maestro> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public HashMap<Integer, Maestro> getHashSexo() {
        return hashSexo;
    }

    public boolean isRegimen() {
        return regimen;
    }

    public boolean getRegimen() {
        return regimen;
    }

    public void setRegimen(boolean regimen) {
        this.regimen = regimen;
    }

    public String getSexo(int id) {
        try {
            return hashSexo.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    public List<Maestro> getListaEstadosAusPersona() {
        return listaEstadosAusPersona;
    }

    public void setListaEstadosAusPersona(List<Maestro> listaEstadosAusPersona) {
        this.listaEstadosAusPersona = listaEstadosAusPersona;
    }

    public HashMap<Integer, Maestro> getHashEstadosPersona() {
        return hashEstadosPersona;
    }

    public void setHashEstadosPersona(HashMap<Integer, Maestro> hashEstadosPersona) {
        this.hashEstadosPersona = hashEstadosPersona;
    }

    public AusPersonaServicioIface getAusPersonaServicio() {
        return ausPersonaServicio;
    }

    public void setAusPersonaServicio(AusPersonaServicioIface ausPersonaServicio) {
        this.ausPersonaServicio = ausPersonaServicio;
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

    public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }
//    public void setPersonaTelefono(PersonaTelefono personaTelefono) {
//        this.personaTelefono = personaTelefono;
//    }
//
//    public void setListaPersonaTelefono(List<PersonaTelefono> listaPersonaTelefono) {
//        this.listaPersonaTelefono = listaPersonaTelefono;
//    }

    public AsegAfiliado getAfiliadoCompleto() {
        return afiliadoCompleto;
    }

    public void setAfiliadoCompleto(AsegAfiliado afiliadoCompleto) {
        this.afiliadoCompleto = afiliadoCompleto;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAusPersonaServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getAusPersonaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAusPersonaServicio().Accion(this);
//        listaPersonaTelefono = new ArrayList<>();
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();

    }

    public void guardar() {

        AusPersona personaModificar = getObjeto();
        asignarTelefonoParaPersona(listaausPersonaTelefono);
        getAusPersonaServicio().consultarPersona(this);
        if (existePersonaEnDB()) {
            personaModificar.setId(this.getObjeto().getId());
            this.setObjeto(personaModificar);
            modificar();
        } else {
            super.setAccion(ACCION_GUARDAR);
            getAusPersonaServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
            }
            PrimeFaces.current().ajax().update("frmPersonas");
            procesoFinal();
        }

    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getAusPersonaServicio().Accion(this);
//        listaPersonaTelefono = new ArrayList(this.getObjeto().getListaTelefonos());
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
//        asignarTelefonosEditar();
        super.setAccion(ACCION_MODIFICAR);
        getAusPersonaServicio().Accion(this);

        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

//    private void asignarTelefonosEditar() {
//        List<PersonaTelefono> listaInicial = new ArrayList();
//        listaInicial.addAll(getObjeto().getListaTelefonos());
//        List<PersonaTelefono> listaFinal = new ArrayList();
//        listaFinal.addAll(listaPersonaTelefono);
//        List<PersonaTelefono> listaResultado = new ArrayList();
//
//        for (PersonaTelefono telIni : listaInicial) {
//            boolean encontro = false;
//            for (PersonaTelefono telFin : listaFinal) {
//                if (telIni.getId() != null && Objects.equals(telIni.getId(), telFin.getId())) {
//                    telIni.setAccion(PersonaTelefono.ACCION_NINGUNO);
//                    listaResultado.add(telIni);
//                    listaFinal.remove(telFin);
//                    encontro = true;
//                    break;
//                }
//            }
//            if (!encontro) {
//                telIni.setAccion(PersonaTelefono.ACCION_BORRAR);
//                listaResultado.add(telIni);
//            }
//        }
//        for (PersonaTelefono detFin : listaFinal) {
//            detFin.setAccion(PersonaTelefono.ACCION_INSERTAR);
//            listaResultado.add(detFin);
//        }
//        getObjeto().setListaTelefonos(listaResultado);
//    }
    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getAusPersonaServicio().Accion(this);
        procesoFinal();
        PrimeFaces.current().ajax().update("frmPersonas");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmPersonas");
                    break;
                case Url.ACCION_CREAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
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
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setPersonaUbicacion(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public void buscarPersonaEnSistema() {
        if (validarPeticionBusquedaAfiliado()) {
            try {
                refreshBusquedaPersona();
                if (!getHashTiposDocumento().isEmpty() && getHashTiposDocumento() != null) {
                    Maestro tipoDocumento = getHashTiposDocumento().get(this.getObjeto().getMae_tipo_documento_id());
                    if (tipoDocumento != null) {
                        this.getObjeto().setMae_tipo_documento_codigo(tipoDocumento.getValor());
                        this.getObjeto().setMae_tipo_documento_valor(tipoDocumento.getNombre());
                    }
                }
                getAusPersonaServicio().consultarPersona(this);
                if (!existePersonaEnDB()) {
                    getAusPersonaServicio().buscarAfiliado(this);
                }

                if (!existePersonaEnDB() && !existePersonaAfiliada()) {
                    String documento = this.getObjeto().getDocumento();
                    int tipoDoc = this.getObjeto().getMae_tipo_documento_id();
                    this.setObjeto(new AusPersona());
                    this.getObjeto().setMae_tipo_documento_id(tipoDoc);
                    this.getObjeto().setDocumento(documento);
//                     this.listaPersonaTelefono = new ArrayList<>();
                }
                PrimeFaces.current().ajax().update("frmCrear");
//                PrimeFaces.current().ajax().update("frmEditar");
                PrimeFaces.current().resetInputs("frmCrear");
                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AusPersonaBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    private boolean existePersonaEnDB() {
        return this.getObjeto().exitePersona();
    }

    private boolean existePersonaAfiliada() {
        return this.getObjeto().getEsAfiliado();
    }

    private void refreshBusquedaPersona() {
        this.getObjeto().setId(0);
        this.getObjeto().setEsAfiliado(false);
    }

    private boolean validarPeticionBusquedaAfiliado() {
        boolean esActividadValida = false;
        if (this.getObjeto().getMae_tipo_documento_id() > 0
                && this.getObjeto().getDocumento() != null) {
            esActividadValida = true;
        }
        return esActividadValida;
    }

    public void asignarTelefonoParaPersona(List<AusPersonaTelefono> telefonosAsignar) {
        getObjeto().setListaTelefonos(telefonosAsignar);
    }

    public void crearTelefono() {
        ausPersonaTelefono = new AusPersonaTelefono();
        PrimeFaces.current().resetInputs("frmCrearContacto:pCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto:pCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    public void adicionarTelefono() {
        try {
            boolean validar = true;
            AusPersonaTelefono obj = getAusPersonaTelefono();
            //Adicionar registro a la lista
            if (listaausPersonaTelefono == null) {
                listaausPersonaTelefono = new ArrayList();
            }
            for (AusPersonaTelefono telefono : listaausPersonaTelefono) {
                if (telefono.getNumero().equals(obj.getNumero())) {
                    validar = false;
                    addError("El numero de telefono ya existe");
                    break;
                }
            }
            if (obj.getNumero().length() > 10) {
                validar = false;
                addError("El numero de telefono es muy grande");
            }
            if (validar) {

                try {
                    obj.setPos(listaausPersonaTelefono.size());
                    this.listaausPersonaTelefono.add(obj);
                } catch (Exception e) {
                    addError("El numero de telefono es muy grande");
                }
            }
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide();");
                PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
                PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersonaEditar");
            }
        } catch (Exception e) {
            this.addError("No es posible adicionar teléfono");
        }
        this.generarMensajes();

    }

    public void borrarContacto(AusPersonaTelefono personaContacto) {
        List<AusPersonaTelefono> listaContactos = new ArrayList<>();
        int posicionEliminar = personaContacto.getPos();
        for (AusPersonaTelefono contacto : getListaausPersonaTelefono()) {
            if (contacto.getPos() != posicionEliminar) {
                listaContactos.add(contacto);
            }
        }

        if (personaContacto.getId() != null) {
            this.getAusPersonaTelefono().setId(personaContacto.getId());
            super.setAccion(Url.ACCION_BORRAR);
            //super.setDoAccion(ACCION_BORRAR_CONTACTOS_PERSONAS);
            getAusPersonaServicio().Accion(this);
        }

        setListaausPersonaTelefono(listaContactos);

        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
        PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersonaEditar");
    }
//    
//    public void borrarTelefono(int pos){
//      try {
//            delTelefono(pos);
//        } catch (Exception e) {
//            super.addError("No es posible borrar telefono");
//        }
//        if (!super.isError()) {
//            RequestContext.getCurrentInstance().execute("PF('dialogoCrearTelefono').hide();");
//            RequestContext.getCurrentInstance().update("frmCrear:telefonos");
//            RequestContext.getCurrentInstance().update("frmEditar:telefonos");
//        }
//    }
//    
//    public void adicionarTelefono() {
//        try {
//            PersonaTelefono obj = getPersonaTelefono();
//            addTelefono(obj);
//        } catch (Exception e) {
//            super.addError("No es posible adicionar teléfono");
//        }
//        if (!super.isError()) {
//            RequestContext.getCurrentInstance().execute("PF('dialogoCrearTelefono').hide();");
//            RequestContext.getCurrentInstance().update("frmCrear:telefonos");
//            RequestContext.getCurrentInstance().update("frmEditar:telefonos");
//        }
//    }
//    
//    public void addTelefono(PersonaTelefono obj) {
//        if (listaPersonaTelefono == null) {
//            listaPersonaTelefono = new ArrayList();
//        }
//        obj.setPos(listaPersonaTelefono.size());
//        this.listaPersonaTelefono.add(obj);
//    }
//    
//     public void delTelefono(int pos) {
//        List<PersonaTelefono> lista = new ArrayList();
//        int i = 0, j = 0;
//        for (PersonaTelefono det : listaPersonaTelefono) {
//            if (j != pos) {
//                det.setPos(i);
//                lista.add(det);
//                i++;
//            }
//            j++;
//        }
//        listaPersonaTelefono = lista;
//    }
//     

    private void refrescarFormularios() {
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().ajax().update("frmEditar");
    }

    public List<String> completarCorreo(String query) {
        List<String> listaCorreos = new ArrayList<>();

        listaCorreos.add(query + "@gmail.com");
        listaCorreos.add(query + "@hotmail.com");
        listaCorreos.add(query + "@outlook.com");

        return listaCorreos;
    }
}
