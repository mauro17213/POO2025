package com.saviasaludeps.savia.web.cuentamedica.rips.regla.servicio;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsRegla;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaEntrada;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaSalida;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsReglaEntradaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsReglaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsReglaSalidaRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.regla.bean.CmRipsReglaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CmRipsReglaServicioImpl extends AccionesBO implements CmRipsReglaServicioIface {

    private CmRipsReglaRemoto getCmRipsReglaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsReglaServicio", CmRipsReglaRemoto.class.getName());
        return (CmRipsReglaRemoto) object;
    }

    private CmRipsReglaEntradaRemoto getCmRipsReglaEntradaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsReglaEntradaServicio", CmRipsReglaEntradaRemoto.class.getName());
        return (CmRipsReglaEntradaRemoto) object;
    }

    private CmRipsReglaSalidaRemoto getCmRipsReglaSalidaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsReglaSalidaServicio", CmRipsReglaSalidaRemoto.class.getName());
        return (CmRipsReglaSalidaRemoto) object;
    }

    @Override
    public void Accion(CmRipsReglaBean bean) {
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
                case Url.ACCION_ADICIONAL_1:
                    modificarOrden(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void listar(CmRipsReglaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCmRipsReglaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmRipsReglaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardar(CmRipsReglaBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getCmRipsReglaRemoto().insertar(bean.getObjeto()));
            if (bean.getObjeto().getId() != null) {
                for (CmRipsReglaEntrada cmRipsReglaEntrada : bean.getListaCmRipsReglasEntradas()) {
                    cmRipsReglaEntrada.setCmRipsRegla(new CmRipsRegla(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(cmRipsReglaEntrada);
                    getCmRipsReglaEntradaRemoto().insertar(cmRipsReglaEntrada);
                }
                for (CmRipsReglaSalida cmRipsReglaSalida : bean.getListaCmRipsReglasSalidas()) {
                    bean.auditoriaGuardar(cmRipsReglaSalida);
                    cmRipsReglaSalida.setCmRipsRegla(new CmRipsRegla(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(cmRipsReglaSalida);
                    getCmRipsReglaSalidaRemoto().insertar(cmRipsReglaSalida);
                }
            }
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    @Override
    public void ver(CmRipsReglaBean bean) {
        try {
            bean.setObjeto(getCmRipsReglaRemoto().consultar(bean.getObjeto().getId()));
            bean.setReglaSalida(new CmRipsReglaSalida());
            bean.setReglaEntrada(new CmRipsReglaEntrada());
            bean.setListaCmRipsReglasEntradas(bean.getObjeto().getListaCmRipsReglaEntrada());
            bean.setListaCmRipsReglasSalidas(bean.getObjeto().getListaCmRipsReglaSalida());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void editar(CmRipsReglaBean bean) {
        try {
            bean.setListaCmRipsReglasEntradas(new ArrayList());
            bean.setListaCmRipsReglasSalidas(new ArrayList());
            bean.setObjeto(getCmRipsReglaRemoto().consultar(bean.getObjeto().getId()));
            bean.setReglaSalida(new CmRipsReglaSalida());
            bean.setReglaEntrada(new CmRipsReglaEntrada());
            bean.setListaCmRipsReglasEntradas(bean.getObjeto().getListaCmRipsReglaEntrada());
            bean.setListaCmRipsReglasSalidas(bean.getObjeto().getListaCmRipsReglaSalida());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(CmRipsReglaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getCmRipsReglaRemoto().actualizar(bean.getObjeto());
            List<CmRipsReglaEntrada> reglasEntradaOriginales = getCmRipsReglaEntradaRemoto().consultarLista(bean.getObjeto().getId());
            List<CmRipsReglaEntrada> reglasEntradaNuevas = bean.getListaCmRipsReglasEntradas();
            List<CmRipsReglaSalida> reglasSalidaOriginales = getCmRipsReglaSalidaRemoto().consultarLista(bean.getObjeto().getId());
            List<CmRipsReglaSalida> reglasSalidaNuevas = bean.getListaCmRipsReglasSalidas();
            //Reglas entrada
            //Insertar nuevos
            for (CmRipsReglaEntrada reglaEntradaNueva : reglasEntradaNuevas) {
                if (reglaEntradaNueva.getIdInsertar() != null && reglaEntradaNueva.getIdInsertar() != 0) {
                    //Insertar
                    reglaEntradaNueva.setCmRipsRegla(new CmRipsRegla(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(reglaEntradaNueva);
                    reglaEntradaNueva.setId(getCmRipsReglaEntradaRemoto().insertar(reglaEntradaNueva));
                }
            }
            //Borrar retirados
            for (CmRipsReglaEntrada reglaEntradaOriginal : reglasEntradaOriginales) {
                boolean borrar = true;
                for (CmRipsReglaEntrada reglaEntradaNueva : reglasEntradaNuevas) {
                    if (Objects.equals(reglaEntradaOriginal.getId(), reglaEntradaNueva.getId())) {
                        borrar = false;
                        break;
                    }
                }
                if (borrar) {
                    getCmRipsReglaEntradaRemoto().eliminar(reglaEntradaOriginal.getId());
                }
            }
            //Reglas salida
            //Insertar nuevos
            for (CmRipsReglaSalida reglaSalidaNueva : reglasSalidaNuevas) {
                if (reglaSalidaNueva.getIdInsertar() != null && reglaSalidaNueva.getIdInsertar() != 0) {
                    //Insertar
                    reglaSalidaNueva.setCmRipsRegla(new CmRipsRegla(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(reglaSalidaNueva);
                    getCmRipsReglaSalidaRemoto().insertar(reglaSalidaNueva);
                }
            }
            //Borrar retirados
            for (CmRipsReglaSalida reglaSalidaOriginal : reglasSalidaOriginales) {
                boolean borrar = true;
                for (CmRipsReglaSalida reglaSalidaNueva : reglasSalidaNuevas) {
                    if (Objects.equals(reglaSalidaOriginal.getId(), reglaSalidaNueva.getId())) {
                        borrar = false;
                        break;
                    }
                }
                if (borrar) {
                    getCmRipsReglaSalidaRemoto().eliminar(reglaSalidaOriginal.getId());
                }
            }
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarOrden(CmRipsReglaBean bean) {
        try {
            CmRipsRegla regla = getCmRipsReglaRemoto().consultar(bean.getObjeto().getId());
            regla.setOrden(bean.getObjeto().getOrden());
            bean.auditoriaModificar(regla);
            getCmRipsReglaRemoto().actualizar(regla);
            bean.addMensaje("Se reordeno la regla " + regla.getNombre() + " de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(CmRipsReglaBean bean) {
        try {
            bean.setObjeto(getCmRipsReglaRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargasInicial(CmRipsReglaBean bean) {
        try {

        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void crear(CmRipsReglaBean bean) {
        bean.setObjeto(new CmRipsRegla());
        bean.setReglaEntrada(new CmRipsReglaEntrada());
        bean.setReglaSalida(new CmRipsReglaSalida());
        bean.setListaCmRipsReglasEntradas(new ArrayList());
        bean.setListaCmRipsReglasSalidas(new ArrayList());
    }

}
