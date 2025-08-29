/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoMipresRemoto;
import com.saviasaludeps.savia.web.maestro.bean.InsumosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaInsumoRemoto;
import com.saviasaludeps.savia.web.maestro.utilidades.MaestroParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class InsumosServicioImpl extends AccionesBO implements InsumosServicioIface {
    
    private MaInsumoRemoto getInsumoRemoto() throws Exception {
        return (MaInsumoRemoto) RemotoEJB.getEJBRemoto(("MaInsumoServicio"), MaInsumoRemoto.class.getName());
    }
    
    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }
    
    private MaInsumoMipresRemoto getInsumoMipresRemoto() throws Exception {
        return (MaInsumoMipresRemoto) RemotoEJB.getEJBRemoto(("MaInsumoMipresServicio"), MaInsumoMipresRemoto.class.getName());
    }

    @Override
    public void Accion(InsumosBean bean) {
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
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(InsumosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getInsumoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getInsumoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(InsumosBean bean) {
        try {
            bean.setObjeto(getInsumoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(InsumosBean bean) {
        try {
            bean.setObjeto(new MaInsumo());
            bean.setListaCodigoInsumo(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(InsumosBean bean) {
        Maestro maestro;
        try {
            //obtenemos el valor restante de maestros
            if (bean.getObjeto().getMaeTipoId()!= 0) {
                maestro = bean.getHashTipos().get(bean.getObjeto().getMaeTipoId());
                bean.getObjeto().setMaeTipoCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoValor(maestro.getNombre());
            }
            // validaciones
            //2022-03-14 jyperez validamos codigo duplicado.
            MaInsumo obj = getInsumoRemoto().consultarPorCodigo(bean.getObjeto().getCodigo());
            if (obj != null) {
                bean.addError("Existe un registro con el código ingresado.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getInsumoRemoto().insertar(bean.getObjeto()));
                //2024-08-06 jyperez agregamos cada objeto de la lista a la tabla MaInsumoMipres
                if (bean.getListaCodigoInsumo() != null && !bean.getListaCodigoInsumo().isEmpty()) {
                    for (MpCodigoInsumo codigoMipres:  bean.getListaCodigoInsumo() ) {
                        MaInsumoMipres insumoMipres = new MaInsumoMipres();
                        insumoMipres.setCodigoMipres(codigoMipres.getCodigoMipres());
                        insumoMipres.setDescripcionMipres(codigoMipres.getDescripcion());
                        insumoMipres.setMaInsumo(bean.getObjeto());
                        insumoMipres.setMpCodigoInsumo(codigoMipres);
                        bean.auditoriaGuardar(insumoMipres);
                        getInsumoMipresRemoto().insertar(insumoMipres);
                    }
                }
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(InsumosBean bean) {
        try {
            bean.setObjeto(getInsumoRemoto().consultar(bean.getObjeto().getId()));
            //2024-08-05 jyperez inicializamos la lista de codigos mipres
            if (bean.getObjeto().getListaInsumoMipres()!= null && !bean.getObjeto().getListaInsumoMipres().isEmpty()) {
                bean.setListaCodigoInsumo(new ArrayList<>());
                for ( MaInsumoMipres cod: bean.getObjeto().getListaInsumoMipres()) {
                    bean.getListaCodigoInsumo().add(cod.getMpCodigoInsumo());
                }
            }else {
                bean.setListaCodigoInsumo(new ArrayList<>());
            }
            //inicializamos la lista de codigos mipres a borrar en cero
            bean.setListaCodigoInsumoBorrar(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(InsumosBean bean) {
        Maestro maestro;
        List<CntContrato> listaContratos = new ArrayList();
        try {
            //obtenemos el valor restante de maestros
            if (bean.getObjeto().getMaeTipoId()!= 0) {
                maestro = bean.getHashTipos().get(bean.getObjeto().getMaeTipoId());
                bean.getObjeto().setMaeTipoCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoValor(maestro.getNombre());
            }
            // validaciones
            //2021-04-22 jyperez validamos si hubo cambio de estado en el objeto, para lanzar la validación
            if (bean.isEstadoInicialTecnologia() != bean.getObjeto().isActivo() && !bean.getObjeto().isActivo()) {
                listaContratos = getContratoDetalleRemoto().consultarPorTecnologia(MaestroParametro.TIPO_TECNOLOGIA_INSUMO,bean.getObjeto().getId());
                if (!listaContratos.isEmpty()) {
                    String mensajeContratos = "";
                    for (CntContrato contrato : listaContratos) {
                        if (mensajeContratos.equals("")) {
                            mensajeContratos = contrato.getContrato();
                        }else {
                            mensajeContratos = mensajeContratos  + ", " + contrato.getContrato();
                        }
                    }
                    bean.addError("La tecnología se encuentra relacionada con los contratos : " + mensajeContratos);
                }
            }
            //2022-03-14 jyperez validamos codigo duplicado.
            MaInsumo obj = getInsumoRemoto().consultarPorCodigo(bean.getObjeto().getCodigo());
            if (obj != null && !obj.getId().equals(bean.getObjeto().getId())) {
                bean.addError("Existe un registro con el código ingresado.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getInsumoRemoto().actualizar(bean.getObjeto());
                //2024-08-05 jyperez eliminamos los registros en la lista de codigos mipres
                if (!bean.getListaCodigoInsumoBorrar().isEmpty()) {
                    for (MpCodigoInsumo cod: bean.getListaCodigoInsumoBorrar()) {
                        for (MaInsumoMipres ins: bean.getObjeto().getListaInsumoMipres()) {
                            if (ins.getCodigoMipres().equals(cod.getCodigoMipres())) {
                                getInsumoMipresRemoto().eliminar(ins.getId());
                            }
                        }
                    }
                }
                //agregamos los registros en la lista
                if (bean.getListaCodigoInsumo() != null && !bean.getListaCodigoInsumo().isEmpty()) {
                    for (MpCodigoInsumo codigoMipres:  bean.getListaCodigoInsumo() ) {
                        if (codigoMipres.isNuevoRegistro()) {
                            MaInsumoMipres insumoMipres = new MaInsumoMipres();
                            insumoMipres.setCodigoMipres(codigoMipres.getCodigoMipres());
                            insumoMipres.setDescripcionMipres(codigoMipres.getDescripcion());
                            insumoMipres.setMaInsumo(bean.getObjeto());
                            insumoMipres.setMpCodigoInsumo(codigoMipres);
                            bean.auditoriaGuardar(insumoMipres);
                            getInsumoMipresRemoto().insertar(insumoMipres);
                        }
                    }
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(InsumosBean bean) {
        try {
            bean.setObjeto(getInsumoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(InsumosBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(InsumosBean bean) {
        try {
            //carga de maestros
            bean.setListaTipos(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_INSUMO_TIPO));
            bean.setHashTipos(Util.convertToHash(bean.getListaTipos()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
