/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteMipresRemoto;
import com.saviasaludeps.savia.web.maestro.bean.PaquetesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import com.saviasaludeps.savia.web.maestro.utilidades.MaestroParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class PaquetesServicioImpl extends AccionesBO implements PaquetesServicioIface {
    
    private MaPaqueteRemoto getPaqueteRemoto() throws Exception {
        return (MaPaqueteRemoto) RemotoEJB.getEJBRemoto(("MaPaqueteServicio"), MaPaqueteRemoto.class.getName());
    }
    
    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }
    
    private MaPaqueteMipresRemoto getPaqueteMipresRemoto() throws Exception {
        return (MaPaqueteMipresRemoto) RemotoEJB.getEJBRemoto(("MaPaqueteMipresServicio"), MaPaqueteMipresRemoto.class.getName());
    }

    @Override
    public void Accion(PaquetesBean bean) {
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

    private void listar(PaquetesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPaqueteRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPaqueteRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(PaquetesBean bean) {
        try {
            bean.setObjeto(getPaqueteRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(PaquetesBean bean) {
        try {
            bean.setObjeto(new MaPaquete());
            bean.getObjeto().setTipoTecnologia(MaestroParametro.TIPO_TECNOLOGIA_TECNOLOGIA);
            bean.setListaCodigoInsumo(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(PaquetesBean bean) {
        Maestro maestro;
        MaPaquete paquete;
        try {
            //obtenemos el valor restante de maestros
            if (bean.getObjeto().getMaeAmbitoId()!= 0) {
                maestro = bean.getHashAmbito().get(bean.getObjeto().getMaeAmbitoId());
                bean.getObjeto().setMaeAmbitoCodigo(maestro.getValor());
                bean.getObjeto().setMaeAmbitoValor(maestro.getNombre());
            }
            // validaciones
            //2022-03-31 jyperez adicionar validación de codigo duplicado
            paquete = getPaqueteRemoto().consultarPorCodigo(bean.getObjeto().getCodigo());
            if (paquete != null) {
                bean.addError("El código ingresado se encuentra asignado a otro Paquete.");
            }
            //2022-03-31 jyperez se valida que se haya seleccionado el paquete de tecnología
            if (bean.getObjeto().getMaTecnologia() == null) {
                bean.addError("Se debe seleccionar la Tecnología.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getPaqueteRemoto().insertar(bean.getObjeto()));
                //2024-11-18 jyperez agregamos cada objeto de la lista a la tabla MaInsumoMipres
                if (bean.getListaCodigoInsumo() != null && !bean.getListaCodigoInsumo().isEmpty()) {
                    for (MpCodigoInsumo codigoMipres:  bean.getListaCodigoInsumo() ) {
                        MaPaqueteMipres paqueteMipres = new MaPaqueteMipres();
                        paqueteMipres.setCodigoMipres(codigoMipres.getCodigoMipres());
                        paqueteMipres.setDescripcionMipres(codigoMipres.getDescripcion());
                        paqueteMipres.setMaPaquete(bean.getObjeto());
                        paqueteMipres.setMpCodigoInsumo(codigoMipres);
                        bean.auditoriaGuardar(paqueteMipres);
                        getPaqueteMipresRemoto().insertar(paqueteMipres);
                    }
                }
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(PaquetesBean bean) {
        try {
            bean.setObjeto(getPaqueteRemoto().consultar(bean.getObjeto().getId()));
            //2024-11-18 jyperez inicializamos la lista de codigos mipres
            if (bean.getObjeto().getListaPaqueteMipres()!= null && !bean.getObjeto().getListaPaqueteMipres().isEmpty()) {
                bean.setListaCodigoInsumo(new ArrayList<>());
                for ( MaPaqueteMipres cod: bean.getObjeto().getListaPaqueteMipres()) {
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

    private void modificar(PaquetesBean bean) {
        Maestro maestro;
        MaPaquete paquete;
        List<CntContrato> listaContratos = new ArrayList();
        try {
            //obtenemos el valor restante de maestros
            if (bean.getObjeto().getMaeAmbitoId()!= 0) {
                maestro = bean.getHashAmbito().get(bean.getObjeto().getMaeAmbitoId());
                bean.getObjeto().setMaeAmbitoCodigo(maestro.getValor());
                bean.getObjeto().setMaeAmbitoValor(maestro.getNombre());
            }
            // validaciones
            //2022-03-31 jyperez adicionar validación de codigo duplicado
            paquete = getPaqueteRemoto().consultarPorCodigo(bean.getObjeto().getCodigo());
            if (paquete != null && !paquete.getId().equals(bean.getObjeto().getId())) {
                bean.addError("El código ingresado se encuentra asignado a otro Paquete.");
            }
            //2022-03-31 jyperez se valida que se haya seleccionado el paquete de tecnología
            if (bean.getObjeto().getMaTecnologia() == null) {
                bean.addError("Se debe seleccionar la Tecnología.");
            }
            //2021-04-22 jyperez validamos si hubo cambio de estado en el objeto, para lanzar la validación
            if (bean.isEstadoInicialTecnologia() != bean.getObjeto().isActivo() && !bean.getObjeto().isActivo()) {
                listaContratos = getContratoDetalleRemoto().consultarPorTecnologia(MaestroParametro.TIPO_TECNOLOGIA_PAQUETE,bean.getObjeto().getId());
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
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getPaqueteRemoto().actualizar(bean.getObjeto());
                //2024-11-18 jyperez eliminamos los registros en la lista de codigos mipres
                if (!bean.getListaCodigoInsumoBorrar().isEmpty()) {
                    for (MpCodigoInsumo cod: bean.getListaCodigoInsumoBorrar()) {
                        for (MaPaqueteMipres ins: bean.getObjeto().getListaPaqueteMipres()) {
                            if (ins.getCodigoMipres().equals(cod.getCodigoMipres())) {
                                getPaqueteMipresRemoto().eliminar(ins.getId());
                            }
                        }
                    }
                }
                //agregamos los registros en la lista
                if (bean.getListaCodigoInsumo() != null && !bean.getListaCodigoInsumo().isEmpty()) {
                    for (MpCodigoInsumo codigoMipres:  bean.getListaCodigoInsumo() ) {
                        if (codigoMipres.isNuevoRegistro()) {
                            MaPaqueteMipres paqueteMipres = new MaPaqueteMipres();
                            paqueteMipres.setCodigoMipres(codigoMipres.getCodigoMipres());
                            paqueteMipres.setDescripcionMipres(codigoMipres.getDescripcion());
                            paqueteMipres.setMaPaquete(bean.getObjeto());
                            paqueteMipres.setMpCodigoInsumo(codigoMipres);
                            bean.auditoriaGuardar(paqueteMipres);
                            getPaqueteMipresRemoto().insertar(paqueteMipres);
                        }
                    }
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(PaquetesBean bean) {
        try {
            bean.setObjeto(getPaqueteRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(PaquetesBean bean) {
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
    public void cargaInicial(PaquetesBean bean) {
        try {
            //carga de maestros
            bean.setListaAmbito(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setHashAmbito(Util.convertToHash(bean.getListaAmbito()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
