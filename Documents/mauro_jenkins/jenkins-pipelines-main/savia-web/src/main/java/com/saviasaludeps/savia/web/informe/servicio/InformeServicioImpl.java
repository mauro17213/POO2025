package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.dominio.informe.InfInformeValor;
import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeRutaRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeValorRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeVariableRemoto;
import com.saviasaludeps.savia.web.informe.bean.InformeBean;
import com.saviasaludeps.savia.web.informe.servicio.validaciones.ValidacionInformeBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;

public class InformeServicioImpl extends AccionesBO implements InformeServicioIface {

    private InformeRemoto getInformeRemoto() throws Exception {
        return (InformeRemoto) RemotoEJB.getEJBRemoto("InformeServicio", InformeRemoto.class.getName());
    }

    private InformeGrupoRemoto getGrupoRemoto() throws Exception {
        return (InformeGrupoRemoto) RemotoEJB.getEJBRemoto("InformeGrupoServicio", InformeGrupoRemoto.class.getName());
    }

    private InformeVariableRemoto getInformeVariableRemoto() throws Exception {
        return (InformeVariableRemoto) RemotoEJB.getEJBRemoto("InformeVariableServicio", InformeVariableRemoto.class.getName());
    }
    
    private InformeRutaRemoto getInformeRutaRemoto() throws Exception {
        return (InformeRutaRemoto) RemotoEJB.getEJBRemoto("InformeRutaServicio", InformeRutaRemoto.class.getName());
    }
    
    private InformeValorRemoto getInformeValorRemoto() throws Exception {
        return (InformeValorRemoto) RemotoEJB.getEJBRemoto("InformeValorServicio", InformeValorRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    @Override
    public void Accion(InformeBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(InformeBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getInformeRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getInformeRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void ver(InformeBean bean) {
        try {
            bean.setObjeto(getInformeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(InformeBean bean) {
        try {
            bean.setObjeto(new InfInforme());
            bean.setObjetoVariable(new InfInformeVariable());
            bean.getObjeto().setListaVariables(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(InformeBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setEstado(InfGenerado.ESTADO_EN_COLA);
            ValidacionInformeBean.validarGuardarInforme(bean);
            Maestro maeFormato = bean.completarMaestroFormato();
            if (maeFormato != null) {
                bean.getObjeto().setMaeTipoFormatoCodigo(maeFormato.getValor());
                bean.getObjeto().setMaeTipoFormatoValor(maeFormato.getNombre());
            }
            if (!bean.isError()) {
                bean.getObjeto().setId(getInformeRemoto().insertar(bean.getObjeto()));
                for (InfInformeVariable variable : bean.getObjeto().getListaVariables()) {
                    variable.setInfInforme(new InfInforme(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(variable);
                    if (variable.getValor() == null || variable.getValor().equals("")) {
                        variable.setValor("automatico");
                    }
                    if (variable.getTipo() == InfInformeVariable.TIPO_EMPRESA) {
                        if (bean.getConexion().getEmpresa().getId().equals(1)) {
                            variable.setValor("%");
                        } else {
                            variable.setValor(bean.getConexion().getEmpresa().getNit());
                        }                        
                    }
                    getInformeVariableRemoto().insertar(variable);
                }
                bean.addMensaje("Se ha programado el informe");
                bean.getObjeto().setListaVariables(new ArrayList<>());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(InformeBean bean) {
        try {
            bean.setObjetoVariable(new InfInformeVariable());
            bean.setObjeto(getInformeRemoto().consultar(bean.getObjeto().getId()));
            for (InfGrupo grupo : bean.getListaGrupos()) {
                if (grupo.getId().equals(bean.getObjeto().getGrupo().getId())) {
                    bean.getObjeto().setGrupo(grupo);
                    break;
                }
            }
            bean.setListaVariablesBorrar(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(InformeBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            ValidacionInformeBean.validarGuardarInforme(bean);
            Maestro maeFormato = bean.completarMaestroFormato();
            if (maeFormato != null) {
                bean.getObjeto().setMaeTipoFormatoCodigo(maeFormato.getValor());
                bean.getObjeto().setMaeTipoFormatoValor(maeFormato.getNombre());
            }
            if (!bean.isError()) {
                getInformeRemoto().actualizar(bean.getObjeto());
                //Actualizar variables de entrada
                for (InfInformeVariable variable : bean.getObjeto().getListaVariables()) {
                    if (variable.getId() == null) {
                        variable.setInfInforme(new InfInforme(bean.getObjeto().getId()));
                        bean.auditoriaGuardar(variable);
                        if (variable.getValor() == null || variable.getValor().equals("")) {
                            variable.setValor("automatico");
                        }
                        if (variable.getTipo() == InfInformeVariable.TIPO_EMPRESA) {
                            variable.setValor(bean.getConexion().getEmpresa().getNit());
                        }
                        getInformeVariableRemoto().insertar(variable);
                    }
                }
                //Eliminar variables
                for(InfInformeVariable variable : bean.getListaVariablesBorrar()) {
                    List<InfInformeValor> valores = getInformeValorRemoto().consultarPorIdVariable(variable.getId());
                    if (valores != null) {
                        for (InfInformeValor valor : valores) {
                            getInformeValorRemoto().borrar(valor.getId());
                        }
                    }                    
                    getInformeVariableRemoto().eliminar(variable.getId());
                }
                bean.addMensaje("Se actualizó el registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(InformeBean bean) {
        try {
            getInformeRemoto().eliminar(bean.getObjeto().getId());
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInicial(InformeBean bean) {
        try {
            bean.setListaGrupos(getGrupoRemoto().consultarTodos());
            bean.setListaRutas(getInformeRutaRemoto().consultarTodas());
            bean.setListaTipoFormato(getMaestroRemoto().consultarPorTipo(MaestroTipo.INF_TIPO_FORMATO));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void cargas(InformeBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }
    
}
