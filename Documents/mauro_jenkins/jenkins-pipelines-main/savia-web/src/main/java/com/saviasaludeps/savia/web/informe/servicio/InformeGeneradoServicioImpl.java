package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.dominio.informe.InfInformeValor;
import com.saviasaludeps.savia.negocio.informe.InformeDescargadoRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeGeneradoRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeRemoto;
import com.saviasaludeps.savia.negocio.informe.InformeValorRemoto;
import com.saviasaludeps.savia.web.informe.bean.InformeGeneradoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformeGeneradoServicioImpl extends AccionesBO implements InformeGeneradoServicioIface {

    private InformeRemoto getInformeRemoto() throws Exception {
        return (InformeRemoto) RemotoEJB.getEJBRemoto("InformeServicio", InformeRemoto.class.getName());
    }

    private InformeGrupoUsuarioRemoto getGrupoUsuarioRemoto() throws Exception {
        return (InformeGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("InformeGrupoUsuarioServicio", InformeGrupoUsuarioRemoto.class.getName());
    }

    private InformeDescargadoRemoto getDescargadoRemoto() throws Exception {
        return (InformeDescargadoRemoto) RemotoEJB.getEJBRemoto("InformeDescargadoServicio", InformeDescargadoRemoto.class.getName());
    }

    private InformeGeneradoRemoto getGeneradoRemoto() throws Exception {
        return (InformeGeneradoRemoto) RemotoEJB.getEJBRemoto("InformeGeneradoServicio", InformeGeneradoRemoto.class.getName());
    }

    private InformeValorRemoto getValorRemoto() throws Exception {
        return (InformeValorRemoto) RemotoEJB.getEJBRemoto("InformeValorServicio", InformeValorRemoto.class.getName());
    }

    private InformeGrupoRemoto getGrupoRemoto() throws Exception {
        return (InformeGrupoRemoto) RemotoEJB.getEJBRemoto("InformeGrupoServicio", InformeGrupoRemoto.class.getName());
    }

    @Override
    public void Accion(InformeGeneradoBean bean) {
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
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    verDescargados(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(InformeGeneradoBean bean) {
        try {
            if (bean.isAccionAdicional1()) {
                bean.setListaGrupos(getGrupoRemoto().consultarTodos());
                bean.setListaInforme(getInformeRemoto().consultarTodos());
            } else {
                bean.setListaGrupos(getGrupoUsuarioRemoto().consultarListaDeGruposPorUsuario(bean.getConexion().getUsuario().getId()));
                String into = "";
                int contador = 0;
                for (InfGrupo grupo : bean.getListaGrupos()) {
                    if (contador > 0 && contador < bean.getListaGrupos().size()) {
                        into += ",";
                    }
                    into += grupo.getId();
                    contador++;
                }
                bean.setListaInforme(getInformeRemoto().consultarPorListaGrupo(into));
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void listar(InformeGeneradoBean bean) {
        try {
            if (!bean.isAccionAdicional1()) {
                //Obtener grupo
                List<InfGrupo> listaGrupos = getGrupoUsuarioRemoto().consultarListaDeGruposPorUsuario(bean.getConexion().getUsuario().getId());
                if (listaGrupos.isEmpty()) {
                    bean.addError("No puedes visualizar informes si no perteneces a ningún grupo");
                }
                int[] gruposId = new int[listaGrupos.size()];
                int i = 0;
                for (InfGrupo grupo : listaGrupos) {
                    gruposId[i] = grupo.getId();
                    i++;
                }
                bean.getParamConsulta().setParametroConsulta1(gruposId);
            }
            if (!bean.getConexion().getEmpresa().getId().equals(1)) {
                bean.getParamConsulta().setParametroConsulta2(bean.getConexion().getUsuario().getUsuario());
                bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getEmpresa().getId());
            }
            if (!bean.isError()) {
                bean.getParamConsulta().setCantidadRegistros(getGeneradoRemoto().consultarCantidadLista(bean.getParamConsulta()));
                bean.setRegistros(getGeneradoRemoto().consultarLista(bean.getParamConsulta()));
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void guardar(InformeGeneradoBean bean) {
        try {
            if (bean.getObjetoInforme() == null) {
                bean.addMensaje("Debe seleccionar un informe");
            }
            String texto = validarExistencia(bean);
            if (texto != null) {
                bean.addError(texto);
            }
            if (!bean.isError()) {
                if (validarCantidadActual(bean.getConexion().getUsuario().getUsuario())) {
                    bean.getObjeto().setInfInforme(new InfInforme(bean.getObjetoInforme().getId()));
                    bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.INF_RUTA_REPORTES) + bean.getObjetoInforme().getCarpeta());
                    bean.getObjeto().setNombreArchivo(bean.getObjetoInforme().getNombreArchivo());
                    bean.getObjeto().setArchivo(bean.getObjetoInforme().getNombreArchivo());
                    bean.getObjeto().setExiste(true);
                    bean.getObjeto().setGnEmpresa(bean.getConexion().getEmpresa().getId());
                    bean.getObjeto().setEstado(InfGenerado.ESTADO_EN_COLA);
                    bean.auditoriaGuardar(bean.getObjeto());
                    bean.getObjeto().setId(getGeneradoRemoto().insertar(bean.getObjeto()));
                    if (!bean.getListaValores().isEmpty()) {
                        for (InfInformeValor valor : bean.getListaValores()) {
                            if (valor.getTipo() == InfInformeValor.TIPO_EMPRESA) {
                                if (bean.getConexion().getEmpresa().getId().equals(1)) {
                                    valor.setVariable("%");
                                } else {
                                    valor.setVariable(bean.getConexion().getEmpresa().getNit());
                                }
                            } else {
                                valor.setVariable(valor.getValor());
                            }
                            bean.auditoriaGuardar(valor);
                            valor.setInfGenerado(new InfGenerado(bean.getObjeto().getId()));
                            getValorRemoto().insertar(valor);
                        }
                    }
                    bean.addMensaje("Se ha creado el registro de manera exitosa");
                } else {
                    bean.addError("Cantidad Maxima alcanzada de informes al tiempo");
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al generar el informe, favor contactar al administrador");
        }
    }

    private void ver(InformeGeneradoBean bean) {
        try {
            bean.setObjeto(getGeneradoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(InformeGeneradoBean bean) {
        try {
            bean.setListaValores(new ArrayList());
            bean.setObjeto(new InfGenerado());
            if (!bean.isAccionAdicional1()) {
                //Obtener grupo
                List<InfGrupo> listaGrupos = getGrupoUsuarioRemoto().consultarListaDeGruposPorUsuario(bean.getConexion().getUsuario().getId());
                if (listaGrupos.isEmpty()) {
                    bean.addError("No puedes visualizar o generar informes si no perteneces a un grupo");
                }
                int[] gruposId = new int[listaGrupos.size()];
                int i = 0;
                for (InfGrupo grupo : listaGrupos) {
                    gruposId[i] = grupo.getId();
                    i++;
                }
                bean.getParamConsulta().setParametroConsulta1(gruposId);
            }
            bean.setListaInformes(getInformeRemoto().consultarListaPlantillasNoProgramados(bean.getParamConsulta()));
            bean.setObjeto(new InfGenerado());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verDescargados(InformeGeneradoBean bean) {
        try {
            bean.setListaDescargados(new ArrayList<>());
            bean.setListaDescargados(getDescargadoRemoto().listarPorGenerado(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardarDescargado(InformeGeneradoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoDescargado());
            bean.getObjetoDescargado().setFechaDescarga(new Date());
            bean.getObjetoDescargado().setEmpresaNombre(bean.getConexion().getEmpresa().getRazonSocial());
            getDescargadoRemoto().insertar(bean.getObjetoDescargado());
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void borrar(InformeGeneradoBean bean) {
        try {
            getGeneradoRemoto().eliminar(bean.getObjeto().getId());
            bean.addMensaje("Se elimino con exito");
        } catch (Exception e) {
            bean.addError("Hubo un error eliminando, favor comunicarse con el administrador");
        }
    }

    private String validarExistencia(InformeGeneradoBean bean) {
        try {
            if (bean.getObjetoInforme() != null && bean.getObjetoInforme().getId() != null) {
                if (bean.getObjetoInforme().getMultipleGeneracionStr().equals("Si")) {
                    return null;
                }
                List<InfGenerado> informes = getGeneradoRemoto().consultarPorInforme(bean.getObjetoInforme().getId());
                if (informes.size() > 0) {
                    for (InfGenerado generado : informes) {
                        if (generado.getListaValores().size() > 0) {
                            if (bean.getListaValores() != null) {
                                int contadorIguales = 0;
                                for (InfInformeValor valorActual : bean.getListaValores()) {
                                    for (InfInformeValor valorBD : generado.getListaValores()) {
                                        if (valorActual.getInfInformeVariable().getId().equals(valorBD.getInfInformeVariable().getId())
                                                && valorActual.getValor().equals(valorBD.getVariable())) {
                                            contadorIguales++;
                                        }
                                    }
                                }
                                if (contadorIguales == bean.getListaValores().size()) {
                                    return "Existe uno ya generado con fecha " + generado.getFechaHoraCrea();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo validando la existencia, favor contactar al administrador");
        }
        return null;
    }

    private boolean validarCantidadActual(String usuario) {
        try {
            return getGeneradoRemoto().consultarLimiteGeneracion(usuario, PropApl.getInstance().get(PropApl.INF_INFORMES_LIMITE_GENERADO));
        } catch (Exception e) {
            return false;
        }
    }

}
