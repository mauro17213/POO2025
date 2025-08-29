/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuTopeAfiliado;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsCopagoModeradoraHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuTopeAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsCopagoModeradoraHistoricoRemoto;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsTopeCopagoRemoto;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsTopeModeradoraRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaRemoto;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaCopagoDTO;
import com.saviasaludeps.savia.solicitud.negocio.ValidadorRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.CopagoModeradoraBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.RemotoEJBSolicitud;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author iavenegas
 */
public class CopagoModeradoraImpl extends AccionesBO implements CopagoModeradoraIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private TuTutelaRemoto getTutelaRemoto() throws Exception {
        return (TuTutelaRemoto) RemotoEJB.getEJBRemoto("TuTutelaServicio", TuTutelaRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private ValidadorRemoto getValidadorRemoto() throws Exception {
        return (ValidadorRemoto) RemotoEJBSolicitud.getEJBRemoto("ValidadorServicio", ValidadorRemoto.class.getName());
    }

    private CntContratoSedeRemoto getCntContratoSedeRemoto() throws Exception {
        return (CntContratoSedeRemoto) RemotoEJB.getEJBRemoto("CntContratoSedeServicio", CntContratoSedeRemoto.class.getName());
    }

    private CsTopeCopagoRemoto getCsTopeCopagoRemoto() throws Exception {
        return (CsTopeCopagoRemoto) RemotoEJB.getEJBRemoto("CsTopeCopagoServicio", CsTopeCopagoRemoto.class.getName());
    }
    private CsTopeModeradoraRemoto getCsTopeModeradoraRemoto() throws Exception {
        return (CsTopeModeradoraRemoto) RemotoEJB.getEJBRemoto("CsTopeModeradoraServicio", CsTopeModeradoraRemoto.class.getName());
    }

    private CsCopagoModeradoraHistoricoRemoto getCsCopagoModeradoraHistoricoRemoto() throws Exception {
        return (CsCopagoModeradoraHistoricoRemoto) RemotoEJB.getEJBRemoto("CsCopagoModeradoraHistoricoServicio", CsCopagoModeradoraHistoricoRemoto.class.getName());
    }
    
    private AuTopeAfiliadoRemoto getTopeAfiliadoRemoto() throws Exception {
        return (AuTopeAfiliadoRemoto) RemotoEJB.getEJBRemoto("AuTopeAfiliadoServicio", AuTopeAfiliadoRemoto.class.getName());
    }
    
    @Override
    public void Accion(CopagoModeradoraBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            validaValores(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
            }
        } else {
            System.err.println("Sesión inactiva");
        }
    }

    @Override
    public void cargaInicial(CopagoModeradoraBean bean) {
        try {

            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));

            bean.setListaCategoria(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_CATEGORIA_IBC));
            bean.setHashCategoria(Util.convertToHashValor(bean.getListaCategoria()));

            bean.setListaTipoAmbito(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setListaTipoAmbito(
                    bean.getListaTipoAmbito().stream()
                            .filter(tipo -> !tipo.getValor().equalsIgnoreCase("M"))
                            .collect(Collectors.toList())
            );

            bean.setListaTopeCopagos(
                    getCsTopeCopagoRemoto().consultarTopesByAnio(bean.getAnioActual())
            );
            bean.setListaTopeModeradoras(
                    getCsTopeModeradoraRemoto().consultarTopesByAnio(bean.getAnioActual())
            );

            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());

        } catch (Exception e) {
            bean.addError("No fue posible realizar carga inicial");
        }
    }

    private void ver(CopagoModeradoraBean bean) {
        try {
            if (bean.getObjetoTecnologia() != null && bean.getCntContratoDetalle() == null) {
                bean.addError("Falta seleccionar el contrato.");
            } else {
                //reset
                resetInformacion(bean);
                //consulta afiliado
                bean.setObjeto(getAfiliadoRemoto().consultar(bean.getObjeto().getMaeTipoDocumento(), bean.getObjeto().getNumeroDocumento()));
                if (bean.getObjeto() == null) {
                    bean.addError("El afiliado no existe.");
                    bean.setObjeto(new AsegAfiliado());
                } else {
                    Maestro maeEstado = getMaestroRemoto().consultar(bean.getObjeto().getMaeEstadoAfiliacion());
                    if (!maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                        bean.addError("El afiliado no esta activo.");
                    } else {
                        buscarTutelasAfiliado(bean);
                        buscarProgramasEspecialesAfiliado(bean);
                        //completa afiliado
                        bean.setObjeto(getAfiliadoRemoto().consultar(bean.getObjeto().getId()));
                        if (bean.getCntContratoDetalle() != null && bean.getObjetoTecnologia() != null) {
                            extraerTipoPago(bean);
                            validaValores(bean);
                        }

                    }
                }
            }

        } catch (Exception e) {
            bean.addError("Error en consulta afiliado ".concat(e.getMessage()));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="metodos auxiliares ver()">
    private void buscarTutelasAfiliado(CopagoModeradoraBean bean) throws Exception {
        //tutelas que exoneran copagoo
        bean.setListaTutelas(
                getTutelaRemoto().consultarConExoneracionAfiliadoPorId(
                        bean.getObjeto().getId(), Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.REST_TIPO_ESTADO_FALLO))
                ).stream()
                        .filter(tutela -> tutela.isExoneracionTutela())
                        .collect(Collectors.toList())
        );
    }

    private void buscarProgramasEspecialesAfiliado(CopagoModeradoraBean bean) throws Exception {
        //programas que exoneran copago
        bean.setListaProgramasEspeciales(
                getAuAnexo3Remoto().consultarProgramasAfiliado(bean.getObjeto().getId())
                        .stream()
                        .filter(programa -> programa.isExoneradoCopago())
                        .collect(Collectors.toList())
        );
    }

    private void validaValores(CopagoModeradoraBean bean) {
        //funcion fn_gn_cal_copagomoderadora
        try {
            String numTutela = "0";
            String idPrograma = "0";
            if (bean.isAplicaProgramasEspecial()) {
                idPrograma = (bean.getListaProgramasEspeciales().isEmpty() ? "0" : bean.getListaProgramasEspeciales().get(0).getId().toString());
            }
            if (bean.isAplicaTutelas()) {
                numTutela = (bean.getListaTutelas().isEmpty() ? "0" : bean.getListaTutelas().get(0).getNumeroTutela());
            }

            ValidaRespuestaCopagoDTO copago = getValidadorRemoto().validarCalCopagoModeradora(
                    bean.getObjeto().getId().toString(),
                    bean.getObjetoTecnologia().getTipoTecnologia() + "",
                    bean.getObjetoTecnologia().getMaTecnologiaId() + "",
                    bean.getCntContratoDetalle().getValorContratado().toString(),
                    bean.getCodigoAmbito(),
                    numTutela, idPrograma);

            bean.setAplicaCopago(bean.obtenerBoolean(copago.getCodigo4() == 1));
            bean.setAplicaCuotaModeradora(bean.obtenerBoolean(copago.getCodigo5() == 1));
            bean.setValorCopago(new BigDecimal(copago.getCodigo()));
            bean.setValorCuotaModeradora(new BigDecimal(copago.getValor()));
            bean.setPorcentajeRecuperacion(new BigDecimal(copago.getCodigo3()));
            
            AuTopeAfiliado auTopeAfiliado = getTopeAfiliadoRemoto().consultarAfiliadoActivo(bean.getObjeto().getId());
            if(auTopeAfiliado != null){
                bean.setApliacaTopeAfiliado("Si");
            }else{
                bean.setApliacaTopeAfiliado("No");
            }
        } catch (Exception e) {
            bean.addError("Error en validación de valores ".concat(e.getMessage()));
        }
    }

    private void extraerTipoPago(CopagoModeradoraBean bean) {
        try {
            if (bean.getObjetoTecnologia().getTipoPago() != null) {
                bean.setAplicaSM(
                        bean.obtenerBoolean(
                                (bean.getObjetoTecnologia().getTipoPago().charAt(0) + "").equals("1"))
                );
                bean.setAplicaSC(
                        bean.obtenerBoolean(
                                (bean.getObjetoTecnologia().getTipoPago().charAt(1) + "").equals("1"))
                );
                bean.setAplicaCM(
                        bean.obtenerBoolean(
                                (bean.getObjetoTecnologia().getTipoPago().charAt(2) + "").equals("1"))
                );
                bean.setAplicaCC(
                        bean.obtenerBoolean(
                                (bean.getObjetoTecnologia().getTipoPago().charAt(3) + "").equals("1"))
                );

            } else {
                bean.setAplicaSM("N/A");
                bean.setAplicaSC("N/A");
                bean.setAplicaCM("N/A");
                bean.setAplicaCC("N/A");
            }
        } catch (Exception e) {
            bean.addError("Error al extraer tipo pago ".concat(e.getMessage()));
        }
    }

    private void resetInformacion(CopagoModeradoraBean bean) {
        bean.setAplicaCopago(null);
        bean.setAplicaCuotaModeradora(null);
        bean.setApliacaTopeAfiliado(null);
        bean.setValorCopago(null);
        bean.setValorCuotaModeradora(null);
        bean.setPorcentajeRecuperacion(null);
        bean.setListaTutelas(new ArrayList());
        bean.setListaProgramasEspeciales(new ArrayList());
        bean.setAplicaProgramasEspecial(true);
        bean.setAplicaTutelas(true);
        bean.setAplicaSM(null);
        bean.setAplicaSC(null);
        bean.setAplicaCM(null);
        bean.setAplicaCC(null);
        bean.setCodigoAmbito("A");//predeterminado ambulatorio
    }
    // </editor-fold>

    @Override
    public void listarCopagoModeradoraHistorico(CopagoModeradoraBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                bean.getParamConsulta(1).setParametroConsulta1(bean.getAnioActual());
                bean.getParamConsulta(1).setParametroConsulta2(bean.getObjeto().getId());
                bean.getParamConsulta(1).setCantidadRegistros(getCsCopagoModeradoraHistoricoRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
                bean.setListaCopagoModeradoraHistorico(getCsCopagoModeradoraHistoricoRemoto().consultarLista(bean.getParamConsulta(1)));
                //calculo total de moderadora y copago
                bean.getParamConsulta(1).setParametroConsulta3(CsCopagoModeradoraHistorico.MODERADORA);
                bean.setTotalModeradoraHistorico(
                        getCsCopagoModeradoraHistoricoRemoto().consultarTotalCopagoModeradora(bean.getParamConsulta(1))
                );
                bean.getParamConsulta(1).setParametroConsulta3(CsCopagoModeradoraHistorico.COPAGO);
                bean.setTotalCopagoHistorico(
                        getCsCopagoModeradoraHistoricoRemoto().consultarTotalCopagoModeradora(bean.getParamConsulta(1))
                );
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarContratoDetalle(CopagoModeradoraBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta(0)));
            bean.setListaContratosDetalles(getAuAnexo3Remoto().consultarListaContratos(bean.getParamConsulta(0)));
            for (CntContratoDetalle contratoDetalle : bean.getListaContratosDetalles()) {
                CntContratoSede contratoSede = getCntContratoSedeRemoto().consultar(contratoDetalle.getCntContratoSede().getId());
                contratoDetalle.setCntContratoSede(contratoSede);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
