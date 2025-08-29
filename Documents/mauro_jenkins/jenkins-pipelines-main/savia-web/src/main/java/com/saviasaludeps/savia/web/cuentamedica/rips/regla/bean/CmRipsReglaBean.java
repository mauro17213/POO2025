package com.saviasaludeps.savia.web.cuentamedica.rips.regla.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsRegla;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaEntrada;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaSalida;
import com.saviasaludeps.savia.web.utilidades.Url;
import javax.faces.bean.ViewScoped;
import java.util.List;
import com.saviasaludeps.savia.web.cuentamedica.rips.regla.servicio.CmRipsReglaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class CmRipsReglaBean extends Url {

    public static final int TIPO_OPERACION_CREACION = 1;
    public static final int TIPO_OPERACION_MODIFICACION = 2;

    @Autowired
    private CmRipsReglaServicioIface cmRipsReglaServicio;
    private List<CmRipsRegla> registros;
    private CmRipsRegla objeto;
    private LazyDataModel<CmRipsRegla> lazyCmRipsRegla;
    private int tamanoPagina = 30;
    private CmRipsRegla cmRipsRegla;
    private List<CmRipsReglaEntrada> listaCmRipsReglasEntradas;
    private List<CmRipsReglaSalida> listaCmRipsReglasSalidas;
    private CmRipsReglaSalida reglaSalida;
    private CmRipsReglaEntrada reglaEntrada;

    public CmRipsReglaBean() {
        this.objeto = new CmRipsRegla();
        Modulo _mod = super.validarModulo(Modulo.ID_CM_RIPS_REGLAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCmRipsRegla = new LazyDataModel<CmRipsRegla>() {

                private List<CmRipsRegla> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmRipsRegla> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CmRipsRegla objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmRipsRegla getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmRipsRegla objeto : lista) {
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
        //getCmRipsReglaServicio().cargasInicial(this);
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmRipsReglaServicio().Accion(this);
    }

    public void crear() {
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        setReglaEntrada(new CmRipsReglaEntrada());
        getReglaEntrada().setTipo(0);
        super.setAccion(ACCION_CREAR);
        getCmRipsReglaServicio().Accion(this);
        getObjeto().setOrden(1);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        if (validarExistenciaReglas() && validarFechaFinMayor()) {
            super.setAccion(ACCION_GUARDAR);
            getCmRipsReglaServicio().Accion(this);
            if (!super.isError()) {
                addMensaje("Se ha creado una nueva regla de manera exitosa.");
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
            }
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        setReglaEntrada(new CmRipsReglaEntrada());
        getReglaEntrada().setTipo(0);
        super.setAccion(ACCION_EDITAR);
        getCmRipsReglaServicio().Accion(this);
//        actulizarFechasInferiores2000();
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:tablaEntradaSalida");
        PrimeFaces.current().ajax().update("frmEditar:reglasSalida");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getCmRipsReglaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().ajax().update("frmVer:tablaEntradaSalida");
        PrimeFaces.current().ajax().update("frmVer:reglasSalida");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void modificar() {
        if (validarExistenciaReglas() && validarFechaFinMayor()) {
            super.setAccion(ACCION_MODIFICAR);
            getCmRipsReglaServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
            }
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getCmRipsReglaServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsReglas");
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmRipsReglas");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmRipsReglas");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsReglas");
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
            }
        }
        generarMensajes();
    }

    /*-------------------------------------
    ------REGLAS DE ENTRADA Y RESPUESTAS---
    ---------------------------------------*/
    public void adicionarReglaEntrada(int tipoOperacion) {
        int idInsertar = 1;
        for (CmRipsReglaEntrada regla : listaCmRipsReglasEntradas) {
            if (regla.getIdInsertar() != null) {
                idInsertar = regla.getIdInsertar() + 1;
            }
        }

        if (validarReglasEntrada()) {
            reglaEntrada.setIdInsertar(idInsertar);
            listaCmRipsReglasEntradas.add(reglaEntrada);
            setReglaEntrada(new CmRipsReglaEntrada());
            addMensaje("Regla Entrada Agregada");
        }

        if (TIPO_OPERACION_CREACION == tipoOperacion) {
            PrimeFaces.current().ajax().update("frmCrear:tablaEntradaSalida");
            PrimeFaces.current().ajax().update("frmCrear:panelCrearReglaEntrada");
        }

        if (TIPO_OPERACION_MODIFICACION == tipoOperacion) {
            PrimeFaces.current().ajax().update("frmEditar:tablaEntradaSalida");
            PrimeFaces.current().ajax().update("frmEditar:panelCrearReglaEntrada");
        }

        procesoFinal();
    }

    private boolean validarReglasEntrada() {
        boolean esValido = true;
        String mensaje = "Regla Entrada: campos obligatorios (";

        if (reglaEntrada.getOrden() == null) {
            mensaje += "Orden - ";
            esValido = false;
        }

        if (reglaEntrada.getTipo() == null) {
            mensaje += "Tipo - ";
            esValido = false;
        }

        if (reglaEntrada.getArchivo() == null) {
            mensaje += "Archivo - ";
            esValido = false;
        }

        if (reglaEntrada.getPosicion() == 0) {
            mensaje += "Posición (mayor que cero) - ";
            esValido = false;
        }

        if ("".equals(reglaEntrada.getCampo())) {
            mensaje += "Campo ";
            esValido = false;
        }

        if (!esValido) {
            addError(mensaje + ")");
        }

        return esValido;
    }

    private boolean validarReglasSalida() {
        boolean esValido = true;
        String mensaje = "Regla Salida: campos obligatorios ( ";

        if (reglaSalida.getCodigo() < 0) {
            mensaje += "Código - ";
            esValido = false;
        }
        if ("".equals(reglaSalida.getDescripcion())) {
            mensaje += " Descripción ";
            esValido = false;
        }
        if (!esValido) {
            addError(mensaje + " )");
        }

        return esValido;
    }

    private boolean validarExistenciaReglas() {
        boolean esValido = true;
        String mensaje = "Se requiere la creación de (";
        if (this.getListaCmRipsReglasEntradas().isEmpty()) {
            mensaje += " una regla de entrada, ";
            esValido = false;
        }

        if (this.getListaCmRipsReglasSalidas().isEmpty()) {
            mensaje += " una regla de salida";
            esValido = false;
        }

        if (!esValido) {
            mensaje += " )";
            addError(mensaje);
        }
        return esValido;
    }

    private boolean validarFechaFinMayor() {
        if (this.getObjeto().getFechaFinal() != null) {
            if (this.getObjeto().getFechaFinal().before(this.getObjeto().getFechaInicial())) {
                this.addError("La fecha final no debe ser menor a la fecha inicial");
                return false;
            }
        }
        return true;
    }

    public void adicionarReglaSalida() {
        int idInsertar = 1;
        for (CmRipsReglaSalida regla : listaCmRipsReglasSalidas) {
            if (regla.getIdInsertar() != null) {
                idInsertar = regla.getIdInsertar() + 1;
            }
        }

        CmRipsReglaSalida cmRipsReglaSalida = new CmRipsReglaSalida();

        if (validarReglasSalida()) {
            cmRipsReglaSalida.setCodigo(reglaSalida.getCodigo());
            cmRipsReglaSalida.setDescripcion(reglaSalida.getDescripcion());
            cmRipsReglaSalida.setIdInsertar(idInsertar);
            listaCmRipsReglasSalidas.add(cmRipsReglaSalida);
        }
        PrimeFaces.current().ajax().update("frmEditar:pReglasSalida");
        PrimeFaces.current().ajax().update("frmCrear:pReglasSalida");
        addMensaje("Regla Salida Agregada");
        reglaSalida.setCodigo(0);
        reglaSalida.setDescripcion("");
        procesoFinal();
    }

    public void retirarReglaEntrada(int id) {
        for (int i = 0; i < listaCmRipsReglasEntradas.size(); i++) {
            CmRipsReglaEntrada reglaMensaje = listaCmRipsReglasEntradas.get(i);
            if (reglaMensaje.getIdInsertar() != null && reglaMensaje.getIdInsertar() == id) {
                listaCmRipsReglasEntradas.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaEntradaSalida");
        PrimeFaces.current().ajax().update("frmEditar:tablaEntradaSalida");
    }

    public void retirarReglaSalida(int id) {
        for (int i = 0; i < listaCmRipsReglasSalidas.size(); i++) {
            CmRipsReglaSalida reglaMensaje = listaCmRipsReglasSalidas.get(i);
            if (reglaMensaje.getIdInsertar() != null && reglaMensaje.getIdInsertar() == id) {
                listaCmRipsReglasSalidas.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:reglasSalida");
        PrimeFaces.current().ajax().update("frmEditar:reglasSalida");
    }

    public void borrarReglaSalida(int id) {
        for (int i = 0; i < listaCmRipsReglasSalidas.size(); i++) {
            CmRipsReglaSalida reglaMensaje = listaCmRipsReglasSalidas.get(i);
            if (reglaMensaje.getId() == id) {
                listaCmRipsReglasSalidas.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:reglasSalida");
        PrimeFaces.current().ajax().update("frmEditar:reglasSalida");
    }

    public void borrarReglaEntrada(int id) {
        for (int i = 0; i < listaCmRipsReglasEntradas.size(); i++) {
            CmRipsReglaEntrada reglaMensaje = listaCmRipsReglasEntradas.get(i);
            if (reglaMensaje.getId() == id) {
                listaCmRipsReglasEntradas.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaEntradaSalida");
        PrimeFaces.current().ajax().update("frmEditar:tablaEntradaSalida");
    }

    public void onRowReorder(ReorderEvent event) {
        CmRipsRegla reglaMovida = registros.get(event.getFromIndex());
        CmRipsRegla reglaCambiada = registros.get(event.getToIndex());
        if (reglaMovida != null && reglaCambiada != null) {
            int ordenReglaMovida = reglaMovida.getOrden();
            int ordenReglaCambiada = reglaCambiada.getOrden();
            reglaMovida.setOrden(ordenReglaCambiada);
            reglaCambiada.setOrden(ordenReglaMovida);
            this.setObjeto(reglaMovida);
            super.setAccion(ACCION_ADICIONAL_1);
            getCmRipsReglaServicio().Accion(this);
            this.setObjeto(reglaCambiada);
            super.setAccion(ACCION_ADICIONAL_1);
            getCmRipsReglaServicio().Accion(this);
        }
        PrimeFaces.current().ajax().update("frmRipsReglas");
    }

    public CmRipsReglaServicioIface getCmRipsReglaServicio() {
        return cmRipsReglaServicio;
    }

    public void setCmRipsReglaServicio(CmRipsReglaServicioIface cmRipsReglaServicio) {
        this.cmRipsReglaServicio = cmRipsReglaServicio;
    }

    public List<CmRipsRegla> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmRipsRegla> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CmRipsRegla> getLazyCmRipsRegla() {
        return lazyCmRipsRegla;
    }

    public void setLazyCmRipsRegla(LazyDataModel<CmRipsRegla> lazyCmRipsRegla) {
        this.lazyCmRipsRegla = lazyCmRipsRegla;
    }

    public CmRipsRegla getObjeto() {
        return objeto;
    }

    public void setObjeto(CmRipsRegla objeto) {
        this.objeto = objeto;
    }

    public int getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(int tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public CmRipsReglaSalida getReglaSalida() {
        return reglaSalida;
    }

    public void setReglaSalida(CmRipsReglaSalida reglaSalida) {
        this.reglaSalida = reglaSalida;
    }

    public CmRipsReglaEntrada getReglaEntrada() {
        return reglaEntrada;
    }

    public void setReglaEntrada(CmRipsReglaEntrada reglaEntrada) {
        this.reglaEntrada = reglaEntrada;
    }

    public CmRipsRegla getCmRipsRegla() {
        return cmRipsRegla;
    }

    public void setCmRipsRegla(CmRipsRegla cmRipsRegla) {
        this.cmRipsRegla = cmRipsRegla;
    }

    public List<CmRipsReglaEntrada> getListaCmRipsReglasEntradas() {
        return listaCmRipsReglasEntradas;
    }

    public void setListaCmRipsReglasEntradas(List<CmRipsReglaEntrada> listaCmRipsReglasEntradas) {
        this.listaCmRipsReglasEntradas = listaCmRipsReglasEntradas;
    }

    public List<CmRipsReglaSalida> getListaCmRipsReglasSalidas() {
        return listaCmRipsReglasSalidas;
    }

    public void setListaCmRipsReglasSalidas(List<CmRipsReglaSalida> listaCmRipsReglasSalidas) {
        this.listaCmRipsReglasSalidas = listaCmRipsReglasSalidas;
    }

//    private void actulizarFechasInferiores2000() {
//        if (this.getObjeto().getFechaInicial().before(new Date(100, 0, 1))) {
//            this.getObjeto().setFechaInicial(new Date(100, 0, 1));
//        }
//        if (this.getObjeto().getFechaFinal() != null) {
//            if (this.getObjeto().getFechaFinal().before(new Date(100, 0, 1))) {
//                this.getObjeto().setFechaFinal(new Date(100, 0, 1));
//            }
//        }
//    }
}
