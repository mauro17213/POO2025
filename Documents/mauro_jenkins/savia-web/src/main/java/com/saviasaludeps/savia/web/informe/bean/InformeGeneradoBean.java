package com.saviasaludeps.savia.web.informe.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfDescargado;
import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.dominio.informe.InfInformeValor;
import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.negocio.informe.InformeRemoto;
import com.saviasaludeps.savia.web.informe.servicio.InformeGeneradoServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.DateUtil;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class InformeGeneradoBean extends Url {

    private InformeGeneradoServicioIface informeGeneradoServicio;
    private InfGenerado objeto;
    private InfInformeValor objetoValor;
    private InfDescargado objetoDescargado;
    private List<InfGenerado> registros;
    private InfInforme objetoInforme;
    private List<InfInforme> listaInformes;
    private InfInformeVariable objetoVariable;
    private LazyDataModel<InfGenerado> lazyGenerado;
    private ParamConsulta paramConsultaInforme;
    private List<InfInformeValor> listaValores;
    private List<InfDescargado> listaDescargados;
    private List<InfGrupo> listaGrupos;
    private List<InfInforme> listaInforme;

    private InformeRemoto getInformeRemoto() throws Exception {
        return (InformeRemoto) RemotoEJB.getEJBRemoto("InformeServicio", InformeRemoto.class.getName());
    }

    public InformeGeneradoBean() {
        this.objeto = new InfGenerado();
        Modulo _mod = super.validarModulo(Modulo.ID_INFORMES_GENERADOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);

            lazyGenerado = new LazyDataModel<InfGenerado>() {

                private List<InfGenerado> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<InfGenerado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(InfGenerado objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public InfGenerado getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (InfGenerado objeto : lista) {
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
        getInformeGeneradoServicio().cargaInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getInformeGeneradoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().ajax().update("frmVer:pVariables");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verDescargados(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_2);
        getInformeGeneradoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmDescargados:pDescargados");
        PrimeFaces.current().executeScript("PF('dialogoDescargados').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getInformeGeneradoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void crearPlantilla() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getInformeGeneradoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        for (InfInformeValor valor : getListaValores()) {
            if (valor.getValor() == null || valor.getValor().equals("")) {
                addError("El campo " + valor.getNombre() + " esta vacio favor revisar");
            } else {
                switch (valor.getTipo()) {
                    case InfInformeVariable.TIPO_NUMERO:
                    try {
                        Integer.parseInt(valor.getValor());
                    } catch (NumberFormatException e) {
                        addError("El valor de + " + valor.getNombre() + " no corresponde al tipo número");
                    }
                    break;
                    case InfInformeVariable.TIPO_BOOLEAN:
                        if (!valor.getValor().equals("1") || !valor.getValor().equals("0")) {
                            addError("El valor de + " + valor.getNombre() + " no corresponde al tipo boolean");
                        }
                        break;
                    case InfInformeVariable.TIPO_FECHA:
                        if (!DateUtil.esFechaValida(valor.getValor())) {
                            addError("El valor de + " + valor.getNombre() + " no corresponde al tipo fecha");
                        }
                        break;
                    case InfInformeVariable.TIPO_FECHA_HORA:
                        if (!DateUtil.esFechaHoraValida(valor.getValor())) {
                            addError("El valor de + " + valor.getNombre() + " no corresponde al tipo fecha hora");
                        }
                        break;
                }
            }
        }
        if (super.isError()) {
            generarMensajes();
            return;
        }
        super.setAccion(ACCION_GUARDAR);
        getInformeGeneradoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void guardarDesdePlantilla() {
        super.setAccion(ACCION_GUARDAR);
        getInformeGeneradoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearDesdePlantilla').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getInformeGeneradoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:pEditar");
        PrimeFaces.current().ajax().update("frmEditar:pVariablesEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getInformeGeneradoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getInformeGeneradoServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getInformeGeneradoServicio().Accion(this);
    }

    public void refrescarInforme() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getInformeGeneradoServicio().Accion(this);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmGenerados:pGenerados");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Informes - Ver Descargados " + getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    crearLog("Informes - guardar: " + getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmInformes");
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog("Informes - modificar: " + getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmInformes");
                    break;
                case Url.ACCION_BORRAR:
                    crearLog("Informes - borrar: " + getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmInformes");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Informes - listar: " + getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmInformes");
                    break;
                case Url.ACCION_CREAR:
                    crearLog("Informes - crear: " + getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmInformes");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmVerGrupos:pVerGrupos");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Informes - guardar grupo: " + getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerGrupos:pVerGrupos");
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog("Informes - modificar grupo;: " + getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmEditarGrupo:pEditarGrupo");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmVerUsuarios:pVerUsuarios");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Informes - crear usuario de grupo: " + getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerUsuarios:pVerUsuarios");
                            break;
                        case Url.ACCION_BORRAR:
                            crearLog("Informes - eliminar usuario de grupo: " + getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerUsuarios:pVerUsuarios");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    //GETTERS SETTER
    public InfGenerado getObjeto() {
        return objeto;
    }

    public void setObjeto(InfGenerado objeto) {
        this.objeto = objeto;
    }

    public InfInforme getObjetoInforme() {
        return objetoInforme;
    }

    public void setObjetoInforme(InfInforme objetoInforme) {
        this.objetoInforme = objetoInforme;
    }

    public InfInformeVariable getObjetoVariable() {
        return objetoVariable;
    }

    public void setObjetoVariable(InfInformeVariable objetoVariable) {
        this.objetoVariable = objetoVariable;
    }

    public LazyDataModel<InfGenerado> getLazyGenerado() {
        return lazyGenerado;
    }

    public void setLazyGenerado(LazyDataModel<InfGenerado> lazyGenerado) {
        this.lazyGenerado = lazyGenerado;
    }

    public InformeGeneradoServicioIface getInformeGeneradoServicio() {
        return informeGeneradoServicio;
    }

    public void setInformeGeneradoServicio(InformeGeneradoServicioIface informeGeneradoServicio) {
        this.informeGeneradoServicio = informeGeneradoServicio;
    }

    public List<InfGenerado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<InfGenerado> registros) {
        this.registros = registros;
    }

    public List<InfInforme> getListaInformes() {
        return listaInformes;
    }

    public void setListaInformes(List<InfInforme> listaInformes) {
        this.listaInformes = listaInformes;
    }

    public ParamConsulta getParamConsultaInforme() {
        return paramConsultaInforme;
    }

    public void setParamConsultaInforme(ParamConsulta paramConsultaInforme) {
        this.paramConsultaInforme = paramConsultaInforme;
    }

    public InfInformeValor getObjetoValor() {
        return objetoValor;
    }

    public void setObjetoValor(InfInformeValor objetoValor) {
        this.objetoValor = objetoValor;
    }

    public List<InfInformeValor> getListaValores() {
        return this.listaValores;
    }

    public void setListaValores(List<InfInformeValor> listaValores) {
        this.listaValores = listaValores;
    }

    public void llenarDesdeInforme(SelectEvent event) throws Exception {
        InfInforme informe = (InfInforme) event.getObject();
        if (informe != null) {
            setObjetoInforme(getInformeRemoto().consultar(informe.getId()));
        }
        crearVariables();
    }

    public InfDescargado getObjetoDescargado() {
        return objetoDescargado;
    }

    public void setObjetoDescargado(InfDescargado objetoDescargado) {
        this.objetoDescargado = objetoDescargado;
    }

    public void descargarAdjunto(InfGenerado adj) throws IOException {
        String rutaCompleta = adj.getRuta() + adj.getArchivo();
        FileInputStream fis = null;
        OutputStream output = null;
        try {
            File file = new File(rutaCompleta);

            // Validación de la existencia del archivo
            if (file.exists()) {
                byte[] exportContent = new byte[(int) file.length()];
                fis = new FileInputStream(file);
                fis.read(exportContent);
                int i = rutaCompleta.lastIndexOf(".");
                String ext = rutaCompleta.substring(i, rutaCompleta.length());
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                ec.responseReset();
                ec.setResponseContentLength(exportContent.length);
                String attachmentName = "attachment; filename=\"" + adj.getNombreArchivo();
                ec.setResponseHeader("Content-Disposition", attachmentName);
                ec.setResponseContentType("application/zip");
                output = ec.getResponseOutputStream();
                output.write(exportContent);
                fc.responseComplete();

                setObjetoDescargado(new InfDescargado());
                getObjetoDescargado().setInfInformeGenerado(new InfGenerado(adj.getId()));
                getObjetoDescargado().setInfGrupoUsuario(new InfGrupoUsuario(getConexion().getUsuario().getId()));
                getInformeGeneradoServicio().guardarDescargado(this);

            } else {
                this.addError("El informe no existe dado que se superó el tiempo de retención de la información");
            }

        } catch (IOException e) {
            this.addError("Ocurrio un error al intentar descargar el archivo: " + e.getMessage());
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Informes - Descargar Informe", getObjeto().toString());
        procesoFinal();
    }

    public List<InfDescargado> getListaDescargados() {
        return listaDescargados;
    }

    public void setListaDescargados(List<InfDescargado> listaDescargados) {
        this.listaDescargados = listaDescargados;
    }

    public List<InfGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<InfGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<InfInforme> getListaInforme() {
        return listaInforme;
    }

    public void setListaInforme(List<InfInforme> listaInforme) {
        this.listaInforme = listaInforme;
    }

    public Date getFechaActual() {
        return new Date();
    }

    public List<InfInformeValor> getListaValoress() {
        return listaValores;
    }

    public void setListaValoress(List<InfInformeValor> listaValoress) {
        this.listaValores = listaValoress;
    }

    private void crearVariables() {
        setListaValores(new ArrayList());
        for (InfInformeVariable variable : getObjetoInforme().getListaVariables()) {
            InfInformeValor valor = new InfInformeValor();
            valor.setTipo(variable.getTipo());
            valor.setNombre(variable.getNombre());
            valor.setValor(variable.getValor());
            valor.setInfInformeVariable(variable);
            getListaValores().add(valor);
        }
        PrimeFaces.current().ajax().update("frmCrear:pVariables");
    }

    public boolean validarTipoTexto(InfInformeValor valor) {
        return (valor.getTipo() == InfInformeVariable.TIPO_TEXTO || valor.getTipo() == InfInformeVariable.TIPO_LITERAL
                || valor.getTipo() == InfInformeVariable.TIPO_CARPETA);
    }

    public boolean validarTipoNumero(InfInformeValor valor) {
        return valor.getTipo() == InfInformeVariable.TIPO_NUMERO;
    }

    public boolean validarTipoFechaCorta(InfInformeValor valor) {
        return (valor.getTipo() == InfInformeVariable.TIPO_FECHA);
    }

    public boolean validarTipoFechaLarga(InfInformeValor valor) {
        return (valor.getTipo() == InfInformeVariable.TIPO_FECHA_HORA);
    }

    public boolean validarTipoBoolean(InfInformeValor valor) {
        return valor.getTipo() == InfInformeVariable.TIPO_BOOLEAN;
    }
}
