package com.saviasaludeps.savia.web.recobro.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import com.saviasaludeps.savia.negocio.recobro.RcoFacturaDetalleRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoFacturaRemoto;
import com.saviasaludeps.savia.negocio.recobro.RcoGrupoRemoto;
import com.saviasaludeps.savia.web.recobro.bean.RecobroFacturaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sgiraldov
 */
public class RecobroFacturaServicioImpl extends AccionesBO implements RecobroFacturaServicioIface {

    private RcoFacturaRemoto getRcoFacturaRemoto() throws Exception {
        return (RcoFacturaRemoto) RemotoEJB.getEJBRemoto("RcoFacturaServicio", RcoFacturaRemoto.class.getName());
    }

    private RcoFacturaDetalleRemoto getRcoFacturaDetalleRemoto() throws Exception {
        return (RcoFacturaDetalleRemoto) RemotoEJB.getEJBRemoto("RcoFacturaDetalleServicio", RcoFacturaDetalleRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private RcoGrupoRemoto getRcoGrupoRemoto() throws Exception {
        return (RcoGrupoRemoto) RemotoEJB.getEJBRemoto("RcoGrupoServicio", RcoGrupoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    @Override
    public void Accion(RecobroFacturaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    verRecobro(bean);
                    listarVerFacturaDetalle(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarFacturaDetalle(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            gestionarDetalleFactura(bean);
                            break;
                        case RecobroFacturaBean.ACCION_GUARDAR_GESTIONAR_MASIVO:
                            guardarGestionMasivaPorFactura(bean);
                            break;
                        case RecobroFacturaBean.ACCION_GUARDAR_GESTIONAR_TOTAL:
                            guardarGestionMasivaRecobroTodas(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            gestionar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            modificar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            verDetalleFactura(bean);
                            break;
                    }
            }
        }
    }

    @Override
    public void cargaInicial(RecobroFacturaBean bean) {
        try {
            bean.setListaProgramas(getPeProgramaRemoto().consultarTodos());
            bean.setListaEstadoFactura(getMaestroRemoto().consultarPorTipo(MaestroTipo.RCO_ESTADO_FACTURA));
        } catch (Exception e) {
            bean.addError("Hubo un error en la carga inicial, favor contactar con el administrador");
        }
    }

    public void cargarMaestros(RecobroFacturaBean bean) {
        try {
            bean.setListaEstadoAuditoria(getMaestroRemoto().consultarPorTipo(MaestroTipo.RCO_ESTADO_AUDITORIA));
            //bean.setListaEstadoAuditoria(getMaestroRemoto().consultarPorTipo(MaestroTipo.RCO_ESTADO_CONCILIACION));
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));

        } catch (Exception ex) {
            Logger.getLogger(RecobroConciliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listar(RecobroFacturaBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getRcoFacturaRemoto()
                    .consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getRcoFacturaRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar al administrador");
        }
    }

    private void listarFacturaDetalle(RecobroFacturaBean bean) {
        try {
            cargarMaestros(bean);
            bean.getParamConsulta(1).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getRcoFacturaDetalleRemoto()
                    .consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosFacturaDetalles(getRcoFacturaDetalleRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar el Detalle de la Factura, favor contactar al administrador1");
        }
    }

    @Override
    public void listarIPS(RecobroFacturaBean bean) {
        try {
            bean.getParamConsulta(2).setCantidadRegistros(getCntPrestadorRemoto()
                    .consultarCantidadListaSede(bean.getParamConsulta(2)));//Se agrega la pos del PARAM para no tener problemas.
            bean.setRegistroIPS(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar IPS, favor contactar con el adminitrador");
        }
    }

    @Override
    public void listarGrupos(RecobroFacturaBean bean) {
        try {
            bean.getParamConsulta(3).setCantidadRegistros(getRcoGrupoRemoto()
                    .consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setRegistrosGrupos(getRcoGrupoRemoto().consultarLista(bean.getParamConsulta(3)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar Grupos, favor contactar con el adminitrador");
        }
    }

    @Override
    public void listarIPSMasivo(RecobroFacturaBean bean) {
        try {
            bean.getParamConsulta(4).setCantidadRegistros(getCntPrestadorRemoto()
                    .consultarCantidadListaSede(bean.getParamConsulta(4)));//Se agrega la pos del PARAM para no tener problemas.
            bean.setRegistroIPS(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta(4)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar IPS , favor contactar con el adminitrador");
        }
    }

    @Override
    public void listarGrupoMasivo(RecobroFacturaBean bean) {
        try {
            bean.getParamConsulta(5).setCantidadRegistros(getRcoGrupoRemoto()
                    .consultarCantidadLista(bean.getParamConsulta(5)));
            bean.setRegistrosGrupos(getRcoGrupoRemoto().consultarLista(bean.getParamConsulta(5)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar Grupos, favor contactar con el adminitrador");
        }
    }

    private void listarVerFacturaDetalle(RecobroFacturaBean bean) {
        try {
            cargarMaestros(bean);
            bean.getParamConsulta(6).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(6).setCantidadRegistros(getRcoFacturaDetalleRemoto()
                    .consultarCantidadLista(bean.getParamConsulta(6)));
            bean.setRegistrosFacturaDetalles(getRcoFacturaDetalleRemoto().consultarLista(bean.getParamConsulta(6)));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar el Detalle de la Factura, favor contactar al administrador1");
        }
    }

    private void gestionar(RecobroFacturaBean bean) {
        try {
            bean.setObjeto(getRcoFacturaRemoto().consultar(bean.getObjeto().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }

    private void verRecobro(RecobroFacturaBean bean) {
        try {
            bean.setObjeto(getRcoFacturaRemoto().consultar(bean.getObjeto().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar, favor contactar al administrador");
        }
    }

    private void verDetalleFactura(RecobroFacturaBean bean) {
        try {
            bean.setObjFacturaDetalle(getRcoFacturaDetalleRemoto().consultar(bean.getObjFacturaDetalle().getId()));
            bean.setAsegAfiliadoId(getAfiliadoRemoto().consultar(bean.getObjFacturaDetalle().getMaeTipoDocumentoId(), bean.getObjFacturaDetalle().getDocumento()));
            if (bean.getObjFacturaDetalle().getPeProgramaId() != null) {
                bean.setPeProgramaId(getPeProgramaRemoto().consultar(bean.getObjFacturaDetalle().getPeProgramaId().getId()));
            }
            if (bean.getObjFacturaDetalle().getCntPrestadoresSedesId() != null) {
                bean.setCntPresdadorSedeId(getCntPrestadorRemoto().consultarSede(bean.getObjFacturaDetalle().getCntPrestadoresSedesId().getId()));
                bean.setObjGrupo(getRcoGrupoRemoto().consultar(bean.getObjFacturaDetalle().getRcoGruposId().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al ver el Detalle, favor contactar al administrador");
        }
    }

    private void gestionarDetalleFactura(RecobroFacturaBean bean) {
        try {
            bean.getObjeto().setPeProgramaId(new PePrograma());
            bean.setObjFacturaDetalle(getRcoFacturaDetalleRemoto().consultar(bean.getObjFacturaDetalle().getId()));
            bean.setAsegAfiliadoId(getAfiliadoRemoto().consultar(bean.getObjFacturaDetalle().getMaeTipoDocumentoId(), bean.getObjFacturaDetalle().getDocumento()));
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar el detalle, favor contactar al administrador");
        }
    }

    @Override
    public void elegirGrupo(RecobroFacturaBean bean) {
        try {

            bean.setObjGrupo(getRcoGrupoRemoto().consultar(bean.getObjGrupo().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un error al elegir grupo, favor contactar al administrador");
        }
    }

    @Override
    public void elegirIPS(RecobroFacturaBean bean) {
        try {

            bean.setCntPresdadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getCntPresdadorSedeId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al elegir IPS, favor contactar al administrador");
        }
    }

    @Override
    public void seleccionarPePrograma(RecobroFacturaBean bean) {
        try {
            bean.setPeProgramaId(getPeProgramaRemoto().consultar(bean.getObjFacturaDetalle().getPeProgramaId().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un error al seleccionar un Programa Especial, favor contactar al administrador");
        }
    }

    @Override
    public void seleccionarPeProgramaMasivo(RecobroFacturaBean bean) {
        try {
            bean.setPeProgramaId(getPeProgramaRemoto().consultar(bean.getPeProgramaId().getId()));

        } catch (Exception e) {
            bean.addError("Hubo un error al seleccionar un Programa Especial, favor contactar al administrador");
        }
    }

    private void modificar(RecobroFacturaBean bean) {
        try {
            bean.setMaestroId(getMaestroRemoto().consultar(bean.getMaestroId().getId()));
            bean.auditoriaModificar(bean.getObjFacturaDetalle());
            bean.getObjFacturaDetalle().setMaeEstadoId(bean.getMaestroId().getId());
            bean.getObjFacturaDetalle().setMaeEstadoCodigo(bean.getMaestroId().getValor());
            bean.getObjFacturaDetalle().setMaeEstadoValor(bean.getMaestroId().getDescripcion());
            if (bean.getObjFacturaDetalle().isAplicaRecobro() == false) {
                getRcoFacturaDetalleRemoto().actualizarRecobroNoAplica(bean.getObjFacturaDetalle());
            } else {
                getRcoFacturaDetalleRemoto().actualizar(bean.getObjFacturaDetalle());
            }

            bean.addMensaje("La factura " + bean.getObjFacturaDetalle().getCmDetalleId().getId() + " a sido gestionada");
        } catch (Exception e) {
            bean.addError("Hubo un error al guardar la gestión, favor contactar al administrador");
        }

    }

    private void guardarGestionMasivaPorFactura(RecobroFacturaBean bean) {
        try {

            for (RcoFacturaDetalle detalle : bean.getRegistroDetalleFacturaSeleccionadoMasivos()) {
                if ("2".equals(detalle.getMaeEstadoCodigo())) {
                } else {
                    if (bean.isAplicaRecobroMasivo() == false) {
                        bean.setMaestroId(getMaestroRemoto().consultar(bean.getMaestroId().getId()));
                        bean.auditoriaModificar(detalle);
                        detalle.setAplicaRecobro(bean.isAplicaRecobroMasivo());
                        detalle.setMaeEstadoId(bean.getMaestroId().getId());
                        detalle.setMaeEstadoCodigo(bean.getMaestroId().getValor());
                        detalle.setMaeEstadoValor(bean.getMaestroId().getDescripcion());
                        detalle.setObservacion(bean.getObservacionRecobroMasivo());
                        getRcoFacturaDetalleRemoto().actualizarRecobroNoAplica(detalle);
                    } else {
                        bean.setMaestroId(getMaestroRemoto().consultar(bean.getMaestroId().getId()));
                        bean.auditoriaModificar(detalle);
                        detalle.setAplicaRecobro(bean.isAplicaRecobroMasivo());
                        detalle.setMaeEstadoId(bean.getMaestroId().getId());
                        detalle.setMaeEstadoCodigo(bean.getMaestroId().getValor());
                        detalle.setMaeEstadoValor(bean.getMaestroId().getDescripcion());
                        detalle.setValorTotalRecobro(BigDecimal.valueOf(detalle.getValorFacturado()));
                        detalle.setValorRestanteRecobro(BigDecimal.ZERO);
                        detalle.setObservacion(bean.getObservacionRecobroMasivo());
                        detalle.setCntPrestadoresSedesId(bean.getCntPresdadorSedeId());
                        detalle.setPeProgramaId(bean.getPeProgramaId());
                        detalle.setRcoGruposId(bean.getObjGrupo());
                        getRcoFacturaDetalleRemoto().actualizarRecobroSiAplica(detalle);

                    }

                    //getRcoFacturaDetalleRemoto().actualizarRecobro(detalle);  
                }

            }
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar las facturas, favor contactar al administrador");
        }

    }

    private void guardarGestionMasivaRecobroTodas(RecobroFacturaBean bean) {
        try {

            for (RcoFacturaDetalle detalle : bean.getRegistrosFacturaDetalles()) {
                if ("2".equals(detalle.getMaeEstadoCodigo())) {
                } else {
                    if (bean.isAplicaRecobroMasivo() == false) {
                        bean.setMaestroId(getMaestroRemoto().consultar(bean.getMaestroId().getId()));
                        bean.auditoriaModificar(detalle);
                        detalle.setAplicaRecobro(bean.isAplicaRecobroMasivo());
                        detalle.setMaeEstadoId(bean.getMaestroId().getId());
                        detalle.setMaeEstadoCodigo(bean.getMaestroId().getValor());
                        detalle.setMaeEstadoValor(bean.getMaestroId().getDescripcion());
                        detalle.setObservacion(bean.getObservacionRecobroMasivo());
                        getRcoFacturaDetalleRemoto().actualizarRecobroNoAplica(detalle);

                    } else {
                        bean.setMaestroId(getMaestroRemoto().consultar(bean.getMaestroId().getId()));
                        bean.auditoriaModificar(detalle);
                        detalle.setAplicaRecobro(bean.isAplicaRecobroMasivo());
                        detalle.setMaeEstadoId(bean.getMaestroId().getId());
                        detalle.setMaeEstadoCodigo(bean.getMaestroId().getValor());
                        detalle.setMaeEstadoValor(bean.getMaestroId().getDescripcion());
                        detalle.setValorTotalRecobro(BigDecimal.valueOf(detalle.getValorFacturado()));
                        detalle.setValorRestanteRecobro(BigDecimal.ZERO);
                        detalle.setObservacion(bean.getObservacionRecobroMasivo());
                        detalle.setCntPrestadoresSedesId(bean.getCntPresdadorSedeId());
                        detalle.setRcoGruposId(bean.getObjGrupo());
                        getRcoFacturaDetalleRemoto().actualizarRecobroSiAplica(detalle);
                    }
                }

            }
        } catch (Exception e) {
            bean.addError("Hubo un error al gestionar las facturas, favor contactar al administrador");
        }

    }

    @Override
    public void actualizarEstadoFactura(RecobroFacturaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getRcoFacturaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("La Factura" + bean.getObjeto().getCmFacturaId().getId() + "a cambiado de estado a");
        } catch (Exception e) {
            bean.addError("Hubo un error al cambiar estado de la factura, favor contactar al administrador");
        }

    }

}
