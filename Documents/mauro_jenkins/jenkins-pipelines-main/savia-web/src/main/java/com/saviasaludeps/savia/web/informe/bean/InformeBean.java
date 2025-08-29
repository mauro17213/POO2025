package com.saviasaludeps.savia.web.informe.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.dominio.informe.InfRuta;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.informe.servicio.InformeServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class InformeBean extends Url {

    private InformeServicioIface informeServicio;
    private InfInforme objeto;
    private InfInformeVariable objetoVariable;
    private List<InfInforme> registros;
    private List<Usuario> listaGnUsuarios;
    private LazyDataModel<InfInforme> lazyInforme;
    private LazyDataModel<InfGrupoUsuario> lazyUsuario;
    private LazyDataModel<InfInformeVariable> lazyVariable;
    private List<InfGrupo> listaGrupos;
    private List<InfInformeVariable> listaVariablesBorrar;
    private List<InfRuta> listaRutas;
    private List<Maestro> listaTipoFormato;

    public InformeBean() {
        this.objeto = new InfInforme();
        Modulo _mod = super.validarModulo(Modulo.ID_INFORMES);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyInforme = new LazyDataModel<InfInforme>() {

                private List<InfInforme> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<InfInforme> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(InfInforme objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public InfInforme getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (InfInforme objeto : lista) {
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
        getInformeServicio().cargaInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getInformeServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getInformeServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().ajax().update("frmVer:pVariables");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getInformeServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getInformeServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getInformeServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().resetInputs("frmEditar:tablaVariables");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getInformeServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getInformeServicio().Accion(this);
        procesoFinal();
    }

    public void agregarVariable() {
        List<String> errores = new ArrayList<>();
        if (getObjetoVariable().getId() != null && getObjetoVariable().getId() > 0) {
            errores.add("Ocurió un error ingresando la variable");
        }
        if (getObjetoVariable().getNombre() == null && getObjetoVariable().getNombre().equals("")) {
            errores.add("Ingresa un nombre al parametro");
        }
        if (getObjetoVariable().getValor() == null && getObjetoVariable().getValor().equals("")) {
            errores.add("Ingresa un parametro");
        } else {
            if (!getObjetoVariable().isDinamico()) {
                switch (getObjetoVariable().getTipo()) {
                    case InfInformeVariable.TIPO_NUMERO:
                        try {
                        Integer.parseInt(getObjetoVariable().getValor());
                    } catch (NumberFormatException e) {
                        errores.add("El valor no corresponde al tipo número");
                    }
                    break;
                    case InfInformeVariable.TIPO_BOOLEAN:
                        if (!getObjetoVariable().getValor().equals("1") || !getObjetoVariable().getValor().equals("0")) {
                            errores.add("El valor no corresponde al tipo boolean");
                        }
                        break;
                    case InfInformeVariable.TIPO_FECHA:
                        if (!DateUtil.esFechaValida(getObjetoVariable().getValor())) {
                            errores.add("El valor no corresponde al tipo fecha");
                        }
                        break;
                    case InfInformeVariable.TIPO_FECHA_HORA:
                        if (!DateUtil.esFechaHoraValida(getObjetoVariable().getValor())) {
                            errores.add("El valor no corresponde al tipo fecha hora");
                        }
                        break;
                }
            }
        }
        if (getObjetoVariable().getOrden() <= 0) {
            errores.add("Ingresa el orden comenzando desde 1");
        }
        if (getObjetoVariable().getTipo() == InfInformeVariable.TIPO_CARPETA && getObjetoVariable().isDinamico()) {
            errores.add("La carpeta no puede ser ingresada por el usuario");
        }
        for (InfInformeVariable variable : getObjeto().getListaVariables()) {
            if (variable.getNombre().equals(getObjetoVariable().getNombre())) {
                errores.add("El nombre debe ser único");
            }
        }
        if (!getObjetoVariable().isDinamico() && !getObjeto().isProgramado() && !isError()) {
            if (getObjetoVariable().getValor() == null || getObjetoVariable().getValor().equals("")
                    && (getObjetoVariable().getTipo() != InfInformeVariable.TIPO_FECHA_INICIO_AUTOMATICO
                    && getObjetoVariable().getTipo() != InfInformeVariable.TIPO_FECHA_FIN_AUTOMATICO)) {
                errores.add("Si el campo no lo ingresa el usuario debe indicar el valor");
            }
        }
        if (getObjetoVariable().isDinamico() && getObjeto().isProgramado() && !isError()) {
            errores.add("No se pueden utilizar valores ingresados por el usuario en un informe programado");
        }
        if (!getObjeto().isProgramado()
                && (getObjetoVariable().getTipo() == InfInformeVariable.TIPO_FECHA_INICIO_AUTOMATICO
                || getObjetoVariable().getTipo() == InfInformeVariable.TIPO_FECHA_FIN_AUTOMATICO)
                && !isError()) {
            errores.add("No se pueden utilizar valores automaticos en un informe no programado");
        }
        if (!isError() && !getObjeto().getListaVariables().isEmpty()) {
            if ((getObjetoVariable().getOrden()) - (getObjeto().getListaVariables().get((getObjeto().getListaVariables().size()) - 1).getOrden()) != 1) {
                errores.add("El orden debe seguir la secuencia");
            }
        }
        if (!isError() && getObjeto().getListaVariables().isEmpty()) {
            if (getObjetoVariable().getOrden() != 1) {
                errores.add("El orden debe inciar desde uno");
            }
        }
        //Requiere valor en una variable programada de tipo texto,numero y fecha
        if (getObjeto().isProgramado()) {
            switch (getObjetoVariable().getTipo()) {
                case InfInformeVariable.TIPO_TEXTO:
                    if (getObjetoVariable().getValor() == null || getObjetoVariable().getValor().equals("") && !getObjetoVariable().isDinamico()) {
                        errores.add("Si el informe es programado y el valor de la variable no la ingresa el usuario debe ingresar un valor a las variables no automaticas");
                    }
                    break;
                case InfInformeVariable.TIPO_NUMERO:
                    if (getObjetoVariable().getValor() == null || getObjetoVariable().getValor().equals("") && !getObjetoVariable().isDinamico()) {
                        errores.add("Si el informe es programado debe ingresar un valor a las variables no automaticas");
                    }
                    break;
                case InfInformeVariable.TIPO_FECHA:
                    if (getObjetoVariable().getValor() == null || getObjetoVariable().getValor().equals("") && !getObjetoVariable().isDinamico()) {
                        errores.add("Si el informe es programado y el valor de la variable no la ingresa el usuario debe ingresar un valor a las variables no automaticas");
                    }
                    break;
                case InfInformeVariable.TIPO_FECHA_HORA:
                    if (getObjetoVariable().getValor() == null || getObjetoVariable().getValor().equals("") && !getObjetoVariable().isDinamico()) {
                        errores.add("Si el informe es programado y el valor de la variable no la ingresa el usuario debe ingresar un valor a las variables no automaticas");
                    }
                    break;
            }
        }
        if (errores.isEmpty()) {
            getObjetoVariable().setEstadoRegistro(InfInformeVariable.ESTADO_REGISTRO_NUEVO);
            getObjeto().getListaVariables().add(getObjetoVariable());
            setObjetoVariable(new InfInformeVariable());
            PrimeFaces.current().executeScript("PF('dialogoAgregarVariable').hide()");
            PrimeFaces.current().ajax().update("frmCrear:pVariables");
            PrimeFaces.current().ajax().update("frmEditar:pVariables");
        } else {
            addErrores(errores);
            generarMensajes();
        }

    }

    public void borrarVariable(Integer id) {
//        super.setAccion(ACCION_BORRAR);
//        super.setDoAccion(ACCION_ADICIONAL_1);
        List<InfInformeVariable> nuevaLista = new ArrayList<>();
        getObjeto().getListaVariables().forEach(variable -> {
            if (variable.getId().equals(id)) {
                getListaVariablesBorrar().add(variable);
            } else {
                nuevaLista.add(variable);
            }
        });
        getObjeto().setListaVariables(nuevaLista);
        setObjetoVariable(null);
        PrimeFaces.current().ajax().update("frmCrear:pVariables");
        PrimeFaces.current().ajax().update("frmEditar:pVariables");
//        procesoFinal();
    }

    public void ventanaAgregarVariable() {
        setObjetoVariable(new InfInformeVariable());
        PrimeFaces.current().ajax().update("frmAgregarVariable:pDatosVariable");
        PrimeFaces.current().executeScript("PF('dialogoAgregarVariable').show()");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmInformes");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    //GETTERS SETTER
    public InformeServicioIface getInformeServicio() {
        return informeServicio;
    }

    public void setInformeServicio(InformeServicioIface informeServicio) {
        this.informeServicio = informeServicio;
    }

    public InfInforme getObjeto() {
        return objeto;
    }

    public void setObjeto(InfInforme objeto) {
        this.objeto = objeto;
    }

    public List<InfInforme> getRegistros() {
        return registros;
    }

    public void setRegistros(List<InfInforme> registros) {
        this.registros = registros;
    }

    public LazyDataModel<InfInforme> getLazyInforme() {
        return lazyInforme;
    }

    public void setLazyInforme(LazyDataModel<InfInforme> lazyInforme) {
        this.lazyInforme = lazyInforme;
    }

    public InfInformeVariable getObjetoVariable() {
        return objetoVariable;
    }

    public void setObjetoVariable(InfInformeVariable objetoVariable) {
        this.objetoVariable = objetoVariable;
    }

    public LazyDataModel<InfGrupoUsuario> getLazyUsuario() {
        return lazyUsuario;
    }

    public void setLazyUsuario(LazyDataModel<InfGrupoUsuario> lazyUsuario) {
        this.lazyUsuario = lazyUsuario;
    }

    public LazyDataModel<InfInformeVariable> getLazyVariable() {
        return lazyVariable;
    }

    public void setLazyVariable(LazyDataModel<InfInformeVariable> lazyVariable) {
        this.lazyVariable = lazyVariable;
    }

    public List<Usuario> getListaGnUsuarios() {
        return listaGnUsuarios;
    }

    public void setListaGnUsuarios(List<Usuario> listaGnUsuarios) {
        this.listaGnUsuarios = listaGnUsuarios;
    }

    public List<InfGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<InfGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<InfInformeVariable> getListaVariablesBorrar() {
        return listaVariablesBorrar;
    }

    public void setListaVariablesBorrar(List<InfInformeVariable> listaVariablesBorrar) {
        this.listaVariablesBorrar = listaVariablesBorrar;
    }

    public List<InfRuta> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<InfRuta> listaRutas) {
        this.listaRutas = listaRutas;
    }

    public Date getFechaActual() {
        return new Date();
    }

    public List<Maestro> getListaTipoFormato() {
        return listaTipoFormato;
    }

    public void setListaTipoFormato(List<Maestro> listaTipoFormato) {
        this.listaTipoFormato = listaTipoFormato;
    }

    public boolean validarTipoTexto() {
        if (getObjetoVariable() == null) {
            return false;
        }
        int tipo = getObjetoVariable().getTipo();
        return (tipo == InfInformeVariable.TIPO_TEXTO || tipo == InfInformeVariable.TIPO_LITERAL
                || tipo == InfInformeVariable.TIPO_CARPETA);
    }

    public boolean validarTipoNumero() {
        if (getObjetoVariable() == null) {
            return false;
        }
        return getObjetoVariable().getTipo() == InfInformeVariable.TIPO_NUMERO;
    }

    public boolean validarTipoFechaCorta() {
        if (getObjetoVariable() == null) {
            return false;
        }
        return (getObjetoVariable().getTipo() == InfInformeVariable.TIPO_FECHA);
    }

    public boolean validarTipoFechaLarga() {
        if (getObjetoVariable() == null) {
            return false;
        }
        return (getObjetoVariable().getTipo() == InfInformeVariable.TIPO_FECHA_HORA);
    }

    public boolean validarTipoBoolean() {
        if (getObjetoVariable() == null) {
            return false;
        }
        return getObjetoVariable().getTipo() == InfInformeVariable.TIPO_BOOLEAN;
    }

    public boolean validarEmpresa() {
        if (getObjetoVariable() == null) {
            return false;
        }
        return getObjetoVariable().getTipo() == InfInformeVariable.TIPO_EMPRESA;
    }

    public void cambiarTipo() {
        if (getObjetoVariable().getTipo() == InfInformeVariable.TIPO_EMPRESA) {
            getObjetoVariable().setValor("Automatico");
        } else {
            getObjetoVariable().setValor("");
        }
        PrimeFaces.current().ajax().update("frmAgregarVariable:pDatosVariable");
    }

    public Maestro completarMaestroFormato() {
        if (getObjeto().getMaeTipoFormatoId() != null) {
            for (Maestro mae : listaTipoFormato) {
                if (mae.getId().equals(getObjeto().getMaeTipoFormatoId())) {
                    return mae;
                }
            }
        }
        return null;
    }

}
