package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudDiagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaDetalle;
import static com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaDetalle.CLASIFICACION_FALTANTE_ENTREGADO;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudHistorico;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.NovedadAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaDetalleRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudHistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudItemRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuNoSolicitudBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaAgrupadoresMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import com.saviasaludeps.savia.solicitud.negocio.ValidadorRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJBSolicitud;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public class AuNoSolicitudServicioImpl extends AccionesBO implements AuNoSolicitudIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private AuNoSolicitudRemoto getAuSinSolicitudRemoto() throws Exception {
        return (AuNoSolicitudRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudServicio", AuNoSolicitudRemoto.class.getName());
    }

    private AuNoSolicitudDiagnosticoRemoto getAuNoSolicitudDiagnosticoRemoto() throws Exception {
        return (AuNoSolicitudDiagnosticoRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudDiagnosticoServicio", AuNoSolicitudDiagnosticoRemoto.class.getName());
    }

    private AuNoSolicitudItemRemoto getAuNoSolicitudItemRemoto() throws Exception {
        return (AuNoSolicitudItemRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudItemServicio", AuNoSolicitudItemRemoto.class.getName());
    }

    private AuNoSolicitudEntregaRemoto getAuNoSolicitudEntregaRemoto() throws Exception {
        return (AuNoSolicitudEntregaRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudEntregaServicio", AuNoSolicitudEntregaRemoto.class.getName());
    }

    private AuNoSolicitudHistoricoRemoto getAuNoSolicitudHistoricoRemoto() throws Exception {
        return (AuNoSolicitudHistoricoRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudHistoricoServicio", AuNoSolicitudHistoricoRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private CntProfesionalRemoto getCntProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }

    private CntContratoDetalleRemoto getCntContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto("CntContratoDetalleServicio", CntContratoDetalleRemoto.class.getName());
    }

    private MaAgrupadoresMedicamentoRemoto getMaAgrupadoresMedicamentoRemoto() throws Exception {
        return (MaAgrupadoresMedicamentoRemoto) RemotoEJB.getEJBRemoto("MaAgrupadoresServicio", MaAgrupadoresMedicamentoRemoto.class.getName());
    }

    private MaMedicamentoRemoto getMaMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto("MaMedicamentoServicio", MaMedicamentoRemoto.class.getName());
    }
    
    private AuNoSolicitudEntregaDetalleRemoto getAuNoSolicitudEntregaDetalleRemoto() throws Exception {
        return (AuNoSolicitudEntregaDetalleRemoto) RemotoEJB.getEJBRemoto("AuNoSolicitudEntregaDetalleServicio", AuNoSolicitudEntregaDetalleRemoto.class.getName());
    }
    
    private ValidadorRemoto getValidadorRemoto() throws Exception {
        return (ValidadorRemoto) RemotoEJB.getEJBRemoto("ValidadorServicio", ValidadorRemoto.class.getName());
    }
    
    private NovedadAfiliadoRemoto getNovedadAfiliadoRemoto() throws Exception {
        return (NovedadAfiliadoRemoto) RemotoEJB.getEJBRemoto("NovedadAfiliadoServicio", NovedadAfiliadoRemoto.class.getName());
    }
    
   
    @Override
    public void Accion(AuNoSolicitudBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verItem(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verEntrega(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crear(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_CREAR:
                                    crearItem(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_CREAR:
                                    crearAdjunto(bean);
                                    break;
                            }
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_VER:
                                    verItem(bean);
                                    break;
                                case Url.ACCION_EDITAR:
                                    editarItem(bean);
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    modificarItem(bean);
                                    break;
                                case Url.ACCION_BORRAR:
                                    borrarItem(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            borrarItem(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            gestionar(bean);
                            break;
                        case Url.ACCION_CREAR:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_CREAR:
                                    crearGestiones(bean);
                                    break;
                                case Url.ACCION_GUARDAR:

                                    break;
                                case Url.ACCION_VER:
                                    verGestion(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_LISTAR:
                                    listarEntregas(bean);
                                    break;
                                case Url.ACCION_VER:
                                    verEntrega(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            switch (bean.getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    editarItem(bean);
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    modificarItem(bean);
                                    break;
                                case Url.ACCION_VER:
                                    verItem(bean);
                                    break;
                                case Url.ACCION_BORRAR:
                                    borrarItem(bean);
                                    break;
                                case Url.ACCION_GUARDAR:
                                    guardarItem(bean);
                                    break;
                            }
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            noPrestado(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            ventanaEntregas(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarEntregas(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            verEntrega(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            anularEntrega(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            break;
                        case Url.ACCION_GUARDAR:
                            anularSinEntregaEntrega(bean);
                            break;
                    }
                    break;

                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_6:

                            break;
                        case Url.ACCION_GUARDAR:

                            break;
                    }
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void cargas(AuNoSolicitudBean bean) {
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

    private void listar(AuNoSolicitudBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getAuSinSolicitudRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAuSinSolicitudRemoto().consultarLista(bean.getParamConsulta()));
            for (AuNoSolicitud noSolicitud : bean.getRegistros()) {
                if (noSolicitud.getCntPrestadorEntregaId() != null) {
                    if (noSolicitud.getCntPrestadorEntregaId().getId() != null) {
                        noSolicitud.setCntPrestadorEntregaId(getPrestadorRemoto().consultar(noSolicitud.getCntPrestadorEntregaId().getId()));
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AuNoSolicitudBean bean) {
        try {
            //cargarMaestros(bean);
            bean.setObjeto(getAuSinSolicitudRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsegAfiliadosId() != null) {
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId());
                bean.getObjeto().getAsegAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            if (bean.getObjeto().getCntPrestadorId() != null) {
                bean.getObjeto().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorSedesId() != null) {
                bean.getObjeto().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorEntregaId() != null) {
                bean.getObjeto().setCntPrestadorEntregaId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorEntregaId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorSedeEntregaId() != null) {
                bean.getObjeto().setCntPrestadorSedeEntregaId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedeEntregaId().getId()));
            }
            if (bean.getObjeto().getCntProfesionalesId() != null) {
                bean.getObjeto().setCntProfesionalesId(getCntProfesionalRemoto().consultar(bean.getObjeto().getCntProfesionalesId().getId()));
                if (bean.getObjeto().getCntProfesionalesId() != null) {
                    bean.setObjetoProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(
                            bean.getObjeto().getCntProfesionalesId().getId(),
                            bean.getObjeto().getCntPrestadorSedesId().getCntPrestador().getId()
                    ));
                }
            }

            bean.getObjeto().setListAuNoSolicitudDiagnostico(getAuNoSolicitudDiagnosticoRemoto().consultarDiagnosticoPorAuNosolicitudesId(bean.getObjeto().getId()));
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId()));
            bean.getObjeto().setListAuSolicitudAdjunto(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdNoSolicitudes(bean.getObjeto().getId()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verGestion(AuNoSolicitudBean bean) {
        try {
            //bean.setAntAnticipoGestion(getAnticipoGestionRemoto().consultar(bean.getAntAnticipoGestion().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verItem(AuNoSolicitudBean bean) {
        try {
            bean.setObjetoItem(getAuNoSolicitudItemRemoto().consultar(bean.getObjetoItem().getId()));
            if (bean.getObjetoItem().getCntContratoDetallesId() != null) {
                bean.getObjetoItem().setCntContratoDetallesId(getCntContratoDetalleRemoto().consultar(bean.getObjetoItem().getCntContratoDetallesId().getId()));
            }
            bean.getObjetoItem().setListAuNoSolicitudEntregaDetalle(getAuNoSolicitudEntregaDetalleRemoto().consultarEntregasPorAuNosolicitudesItemId(bean.getObjetoItem().getId()));
            bean.getObjetoItem().setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(bean.getObjetoItem().getId()));
            for (AuNoSolicitudEntrega entrega : bean.getObjetoItem().getListAuSolicitudEntrega()) {
                entrega.setAuNoSolicitudItemsId(getAuNoSolicitudItemRemoto().consultar(entrega.getAuNoSolicitudItemsId().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AuNoSolicitudBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(new AuNoSolicitud());
            bean.getObjeto().setCntProfesionalesId(new CntProfesional());
            bean.getObjeto().getCntProfesionalesId().setGuardar(true);
            bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
            bean.getObjeto().setListAuNoSolicitudDiagnostico(new ArrayList<>());
            bean.getObjeto().setListAuNoSolicitudItem(new ArrayList<>());
            bean.getObjeto().setListAuSolicitudAdjunto(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearItem(AuNoSolicitudBean bean) {
        try {
            //bean.setObjetoItem(new AuNoSolicitudItem());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearAdjunto(AuNoSolicitudBean bean) {
        try {
            bean.setObjetoAdjunto(new AuSolicitudAdjunto());
            bean.setListaAuSolicitudAdjunto(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearGestiones(AuNoSolicitudBean bean) {
        try {
            /*bean.setAntAnticipoGestion(new AntAnticipoGestion());
            bean.getAntAnticipoGestion().setTipo(AntAnticipoGestion.TIPO_NOTA);*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(AuNoSolicitudBean bean) {
        try {
            cargarMaestros(bean);
            bean.setListaItemsBorrar(new ArrayList<>());
            bean.setObjeto(getAuSinSolicitudRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsegAfiliadosId() != null) {
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId());
                bean.getObjeto().getAsegAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            if (bean.getObjeto().getCntPrestadorId() != null) {
                bean.getObjeto().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorSedesId() != null) {
                bean.getObjeto().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorEntregaId() != null) {
                bean.getObjeto().setCntPrestadorEntregaId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorEntregaId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorSedeEntregaId() != null) {
                bean.getObjeto().setCntPrestadorSedeEntregaId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedeEntregaId().getId()));
            }
            if (bean.getObjeto().getCntProfesionalesId() != null) {
                bean.getObjeto().setCntProfesionalesId(getCntProfesionalRemoto().consultar(bean.getObjeto().getCntProfesionalesId().getId()));
                if (bean.getObjeto().getCntProfesionalesId() != null) {
                    bean.setObjetoProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(
                            bean.getObjeto().getCntProfesionalesId().getId(),
                            bean.getObjeto().getCntPrestadorSedesId().getCntPrestador().getId()
                    ));
                }
            } else {
                bean.getObjeto().setCntProfesionalesId(new CntProfesional());
            }

            bean.getObjeto().setListAuNoSolicitudDiagnostico(getAuNoSolicitudDiagnosticoRemoto().consultarDiagnosticoPorAuNosolicitudesId(bean.getObjeto().getId()));
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId()));
            bean.getObjeto().setListAuSolicitudAdjunto(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdNoSolicitudes(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editarItem(AuNoSolicitudBean bean) {
        try {
            bean.setObjetoItem(getAuNoSolicitudItemRemoto().consultar(bean.getObjetoItem().getId()));
            if(bean.getObjetoItem().getTipoTecnologia() == AuConstantes.ID_INSUMO && bean.getObjetoItem().getCntContratoDetallesId() != null){
                bean.getObjetoItem().setHabilitarValorUnitario(true);
            }else{
                bean.getObjetoItem().setHabilitarValorUnitario(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void gestionar(AuNoSolicitudBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(getAuSinSolicitudRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsegAfiliadosId() != null) {
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorId() != null) {
                bean.getObjeto().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorSedesId() != null) {
                bean.getObjeto().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorEntregaId() != null) {
                bean.getObjeto().setCntPrestadorEntregaId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadorEntregaId().getId()));
            }
            if (bean.getObjeto().getCntPrestadorSedeEntregaId() != null) {
                bean.getObjeto().setCntPrestadorSedeEntregaId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedeEntregaId().getId()));
            }
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId()));
            int cantidadEntregada = 0;
            for (AuNoSolicitudItem item : bean.getObjeto().getListAuNoSolicitudItem()) {
                if (item.getCntContratoDetallesId() != null) {
                    item.setCntContratoDetallesId(getCntContratoDetalleRemoto().consultar(item.getCntContratoDetallesId().getId()));
                }
                item.setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(item.getId()));
                for (AuNoSolicitudEntrega entrega : item.getListAuSolicitudEntrega()) {
                    cantidadEntregada = cantidadEntregada + entrega.getCantidadEntregada();
                    if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO
                            || item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO) {
                        item.setCantidadPendiente(item.getCantidadSolicitada());
                    } else {
                        item.setCantidadPendiente(item.getCantidadSolicitada() - cantidadEntregada);
                    }
                }
                if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO && item.getListAuSolicitudEntrega().isEmpty()) {
                    item.setCantidadPendiente(item.getCantidadSolicitada());
                }
                item.setCantidadEntrega(cantidadEntregada);
                cantidadEntregada = 0;
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(AuNoSolicitudBean bean) {
        try {
            AuNoSolicitud obj = bean.getObjeto();
            bean.auditoriaGuardar(obj);
            obj.setEstado(AuNoSolicitud.ESTADO_PENDIENTE);
            obj.setFuenteOrigen(AuNoSolicitud.FUENTE_ORIGEN_MANUAL);
            //obj.setGnUsuariosId(bean.getConexion().getUsuario());
            if (obj.getCntPrestadorSedesId() != null) {
                Empresa empresa = getEmpresaRemoto().consultarPorPrestador(obj.getCntPrestadorSedeEntregaId().getCntPrestador().getId());
                if (empresa != null) {
                    obj.setGnEmpresasId(empresa);
                } else {
                    obj.setGnEmpresasId(bean.getConexion().getEmpresa());
                }
            } else {
                obj.setGnEmpresasId(bean.getConexion().getEmpresa());
            }
            Maestro ambitoAtencion = bean.getHashAmbitosAtencion().get(obj.getMaeAmbitoAtencionId());
            if (ambitoAtencion != null) {
                obj.setMaeAmbitoAtencionCodigo(ambitoAtencion.getValor());
                obj.setMaeAmbitoAtencionValor(ambitoAtencion.getNombre());
                obj.setMaeAmbitoAtencionTipo(ambitoAtencion.getTipo());
            }
            Maestro motivoSinAutorizacion = bean.getHashMotivoSinAutorizacion().get(obj.getMaeMotivoSinAutorizacionId());
            if (motivoSinAutorizacion != null) {
                obj.setMaeMotivoSinAutorizacionCodigo(motivoSinAutorizacion.getValor());
                obj.setMaeMotivoSinAutorizacionValor(motivoSinAutorizacion.getNombre());
                obj.setMaeMotivoSinAutorizacionTipo(motivoSinAutorizacion.getTipo());
            }
            //Asignación del profesional
            CntProfesional profesional = obj.getCntProfesionalesId();
            CntProfesional profesionalRegistrado = null;
            if (profesional.getId() == null) {
                //Validar existencia del profesional
                if(profesional.getMaeTipoCodumentoId() > 0 && !profesional.getDocumento().isEmpty()){
                    profesionalRegistrado = getCntProfesionalRemoto().consultarNumDocumento(profesional.getMaeTipoCodumentoId(),profesional.getDocumento());
                    //Crear profesional si no se encuentra
                    if (profesionalRegistrado == null) {
                           Maestro tipoDocProfesional = bean.getHashTiposDocumentoProfesional().get(profesional.getMaeTipoCodumentoId());
                           if(tipoDocProfesional != null){
                               profesional.setMaeTipoDocumentoCodigo(tipoDocProfesional.getValor());
                               profesional.setMaeTipoDocumentoValor(tipoDocProfesional.getNombre());
                           }
                           if (profesional.getRegistroMedico() == null) {
                               profesional.setRegistroMedico("Sin registro");
                           }
                           profesional.setUsuarioCrea(obj.getUsuarioCrea());
                           profesional.setTerminalCrea(obj.getTerminalCrea());
                           profesional.setFechaHoraCrea(obj.getFechaHoraCrea());
                           int idProfesional = getCntProfesionalRemoto().insertar(profesional);
                           profesional.setId(idProfesional);
                           //Crear el profesional en el prestador
                           if (bean.getObjetoProfesionalPrestador() != null) {
                               CntProfesionalPrestador especialidad = new CntProfesionalPrestador();
                               especialidad.setCntProfesionalesId(profesional);
                               especialidad.setCntPrestador(obj.getCntPrestadorSedesId().getCntPrestador());
                               especialidad.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                               especialidad.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                               especialidad.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                               especialidad.setActivo(true);
                               especialidad.setUsuarioCrea(obj.getUsuarioCrea());
                               especialidad.setTerminalCrea(obj.getTerminalCrea());
                               especialidad.setFechaHoraCrea(obj.getFechaHoraCrea());
                               especialidad.setId(getCntPrestadorProfesionalRemoto().insertar(especialidad));
                               obj.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                               obj.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                               obj.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                           }
                    }else{
                        //Usar el profesional encontrado
                        profesionalRegistrado.setRegistroMedico(profesional.getRegistroMedico());
                        profesionalRegistrado.setUsuarioModifica(obj.getUsuarioCrea());
                        profesionalRegistrado.setTerminalModifica(obj.getTerminalCrea());
                        profesionalRegistrado.setFechaHoraModifica(obj.getFechaHoraCrea());
                        getCntProfesionalRemoto().actualizarRegistroMedico(profesionalRegistrado);
                        profesional = profesionalRegistrado;
                        CntProfesionalPrestador especialidad = getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(profesional.getId(), obj.getCntPrestadorSedesId().getCntPrestador().getId());
                        if (especialidad != null) {
                            especialidad = new CntProfesionalPrestador();
                            especialidad.setCntProfesionalesId(profesional);
                            especialidad.setCntPrestador(obj.getCntPrestadorSedesId().getCntPrestador());
                            especialidad.setMaEspecialidadId(obj.getObjetoEspecialidad().getId());
                            especialidad.setMaEspecialidadCodigo(obj.getObjetoEspecialidad().getCodigo());
                            especialidad.setMaEspecialidadValor(obj.getObjetoEspecialidad().getNombre());
                            especialidad.setActivo(true);
                            especialidad.setUsuarioCrea(obj.getUsuarioCrea());
                            especialidad.setTerminalCrea(obj.getTerminalCrea());
                            especialidad.setFechaHoraCrea(obj.getFechaHoraCrea());
                            especialidad.setId(getCntPrestadorProfesionalRemoto().insertar(especialidad));
                            obj.setMaEspecialidadId(obj.getObjetoEspecialidad().getId());
                            obj.setMaEspecialidadCodigo(obj.getObjetoEspecialidad().getCodigo());
                            obj.setMaEspecialidadValor(obj.getObjetoEspecialidad().getNombre());
                        }
                    }
                }
            }else if(profesional.getId() != null){
                profesionalRegistrado = getCntProfesionalRemoto().consultarNumDocumento(profesional.getMaeTipoCodumentoId(),profesional.getDocumento());
                if (profesionalRegistrado != null) {
                    //Usar el profesional encontrado
                    profesionalRegistrado.setRegistroMedico(profesional.getRegistroMedico());
                    profesionalRegistrado.setUsuarioModifica(obj.getUsuarioCrea());
                    profesionalRegistrado.setTerminalModifica(obj.getTerminalCrea());
                    profesionalRegistrado.setFechaHoraModifica(obj.getFechaHoraCrea());
                    getCntProfesionalRemoto().actualizarRegistroMedico(profesionalRegistrado);
                    profesional = profesionalRegistrado;
                    if (bean.getObjetoProfesionalPrestador() != null && bean.getObjetoProfesionalPrestador().getId() == null) {
                        CntProfesionalPrestador especialidad = new CntProfesionalPrestador();
                        especialidad.setCntProfesionalesId(profesional);
                        especialidad.setCntPrestador(obj.getCntPrestadorSedesId().getCntPrestador());
                        especialidad.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                        especialidad.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                        especialidad.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                        especialidad.setActivo(true);
                        especialidad.setUsuarioCrea(obj.getUsuarioCrea());
                        especialidad.setTerminalCrea(obj.getTerminalCrea());
                        especialidad.setFechaHoraCrea(obj.getFechaHoraCrea());
                        especialidad.setId(getCntPrestadorProfesionalRemoto().insertar(especialidad));
                        obj.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                        obj.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                        obj.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                    }
                }
            }
            obj.setId(getAuSinSolicitudRemoto().insertar(obj));
            procesarDiagnosticos(bean);
            procesarItems(bean);
            procesarAdjuntos(bean);
            procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_PENDIENTE, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, "");
            bean.addMensaje("El no autorizado se creo exitosamente " + obj.getId());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void guardarItem(AuNoSolicitudBean bean) {
        try {
            /* AntAnticipoItem obj = bean.getObjetoItem();
            bean.auditoriaGuardar(obj);
            obj.setId(getAnticipoItemRemoto().insertar(obj));
            bean.getObjeto().getAntAnticipoItemsList().add(obj);
            bean.addMensaje("El Item se creo exitosamente");*/
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void noPrestado(AuNoSolicitudBean bean) {
        try {
            bean.getObjetoEntrega().setReclamaAfiliado(false);
            bean.getObjetoEntrega().setNombreReclama(" ");
            Date fechaActual = new Date();
            bean.getObjetoEntrega().setFechaHoraEntrega(fechaActual);
            bean.getObjetoEntrega().setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO);
            bean.getObjetoEntrega().setFuenteOrigen(AuNoSolicitudEntrega.FUENTE_ORIGEN_MANUAL);
            bean.getObjetoEntrega().setAnulada(Boolean.FALSE);
            bean.auditoriaGuardar(bean.getObjetoEntrega());
            getAuNoSolicitudEntregaRemoto().insertar(bean.getObjetoEntrega());
            bean.getObjetoEntrega().getAuNoSolicitudItemsId().setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO);
            bean.getObjetoEntrega().getAuNoSolicitudItemsId().setEstado(AuNoSolicitudItem.ESTADO_GESTIONADO);
            getAuNoSolicitudItemRemoto().actualizarTipoEntrega(bean.getObjetoEntrega().getAuNoSolicitudItemsId());
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId()));
            int contadorEstadoGestionado = 0;
            int cantidadEntregada = 0;
            for (AuNoSolicitudItem item : bean.getObjeto().getListAuNoSolicitudItem()) {
                if (item.getCntContratoDetallesId() != null) {
                    item.setCntContratoDetallesId(getCntContratoDetalleRemoto().consultar(item.getCntContratoDetallesId().getId()));
                }
                item.setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(item.getId()));
                if (item.getEstado() == AuNoSolicitudItem.ESTADO_GESTIONADO) {
                    contadorEstadoGestionado++;
                }
                for (AuNoSolicitudEntrega entrega : item.getListAuSolicitudEntrega()) {
                    cantidadEntregada = cantidadEntregada + entrega.getCantidadEntregada();
                    if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO
                            || item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO) {
                        item.setCantidadPendiente(item.getCantidadSolicitada());
                    } else {
                        item.setCantidadPendiente(item.getCantidadSolicitada() - cantidadEntregada);
                    }
                }
                if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO && item.getListAuSolicitudEntrega().isEmpty()) {
                    item.setCantidadPendiente(item.getCantidadSolicitada());
                }
                item.setCantidadEntrega(cantidadEntregada);
                cantidadEntregada = 0;
            }

            if (contadorEstadoGestionado == bean.getObjeto().getListAuNoSolicitudItem().size()) {
                bean.auditoriaModificar(bean.getObjeto());
                bean.getObjeto().setEstado(AuNoSolicitud.ESTADO_GESTIONADO);
                getAuSinSolicitudRemoto().actualizarEstado(bean.getObjeto());
                procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_GESTIONADO, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, "");
            }else{
                bean.auditoriaModificar(bean.getObjeto());
                bean.getObjeto().setEstado(AuNoSolicitud.ESTADO_EN_GESTION);
                getAuSinSolicitudRemoto().actualizarEstado(bean.getObjeto());
                procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_EN_GESTION, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, "");
            }
            bean.addMensaje("Se realizó la modificacion a No Prestado con éxito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al marcar como No Entregado, favor contactar al administrador");
        }
    }

    private void ventanaEntregas(AuNoSolicitudBean bean) {
        try {
            AuNoSolicitudEntrega obj = bean.getObjetoEntrega();
            if(obj.getAuNoSolicitudItemsId() != null){
                obj.setListAuNoSolicitudEntregaDetalle(getAuNoSolicitudEntregaDetalleRemoto().consultarEntregasPorAuNosolicitudesItemId(obj.getAuNoSolicitudItemsId().getId()));
                int sumatoriaCantidadEntregada = 0;
                int sumatoriaCantidadPendiente = 0;
                for(AuNoSolicitudEntregaDetalle detalle : obj.getListAuNoSolicitudEntregaDetalle()){
                    sumatoriaCantidadEntregada = sumatoriaCantidadEntregada + detalle.getCantidadEntregada();
                    sumatoriaCantidadPendiente = obj.getAuNoSolicitudItemsId().getCantidadSolicitada() - sumatoriaCantidadEntregada;
                }
                obj.setCantidadPendiente(sumatoriaCantidadPendiente);
                obj.setCantidadEntregada(sumatoriaCantidadEntregada);
            }
            obj.setListAuSolicitudAdjunto(new ArrayList<>());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void guardarEntregas(AuNoSolicitudBean bean) {
        try {
            Date fechaActual = new Date();
            AuNoSolicitudEntrega obj = bean.getObjetoEntrega();
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaActualStr = formato.format(fechaActual);
            fechaActual = formato.parse(fechaActualStr);
            bean.auditoriaGuardar(obj);
            obj.setAnulada(Boolean.FALSE);
            obj.setFuenteOrigen(AuNoSolicitudEntrega.FUENTE_ORIGEN_MANUAL);
            Maestro causaNoEntrega = bean.getHashCausaNoEntrega().get(obj.getMaeCausaMoEntregaId());
            if (causaNoEntrega != null) {
                obj.setMaeCausaMoEntregaId(causaNoEntrega.getId());
                obj.setMaeCausaMoEntregaCodigo(causaNoEntrega.getValor());
                obj.setMaeCausaMoEntregaValor(causaNoEntrega.getNombre());
                obj.setMaeCausaMoEntregaTipo(causaNoEntrega.getTipo());
            }
            if (obj.isReclamaAfiliado()) {
                obj.setNombreReclama(obj.getAuNoSolicitudesId().getAsegAfiliadosId().getNombreCompleto());
                obj.setTelefonoReclama(obj.getAuNoSolicitudesId().getAsegAfiliadosId().getTelefonoFijo());
                obj.setCelularReclama(obj.getAuNoSolicitudesId().getAsegAfiliadosId().getTelefonoMovil());
            } else {
                if (obj.getAuNoSolicitudItemsId().getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL
                    || obj.getAuNoSolicitudItemsId().getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO) {
                    AsegAfiliado afiliado = bean.getObjeto().getAsegAfiliadosId();
                    obj.setNombreReclama(afiliado.getNombreCompleto());
                }
            }  
            int paraNoInsertarVariosDocumentos = 0;
            for(AuNoSolicitudEntregaDetalle entregaDetalle: obj.getListAuNoSolicitudEntregaDetalle()){
                if(entregaDetalle.getCantidadAEntregar() != null 
                        && !entregaDetalle.getCantidadAEntregar().isBlank()){
                    //Guardar Entrega
                    List<AuNoSolicitudEntrega> listaEntregas = getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemIdWithNoAnuladas(obj.getAuNoSolicitudItemsId().getId());
                    if(!listaEntregas.isEmpty()){
                        int cantidadEntregadas = 0;
                        // se reaiza esto para sacar la cantidad pendientes
                        for (AuNoSolicitudEntrega entrega : listaEntregas) {
                            cantidadEntregadas = cantidadEntregadas + entrega.getCantidadEntregada();
                        }
                        AuNoSolicitudItem item = getAuNoSolicitudItemRemoto().consultar(obj.getAuNoSolicitudItemsId().getId());
                        obj.setCantidadPorEntregar(item.getCantidadSolicitada() - cantidadEntregadas);
                        obj.setCantidadPendiente(item.getCantidadSolicitada() - cantidadEntregadas - Integer.parseInt(entregaDetalle.getCantidadAEntregar()));
                    }else{
                        obj.setCantidadPorEntregar(obj.getAuNoSolicitudItemsId().getCantidadSolicitada());
                        obj.setCantidadPendiente(obj.getAuNoSolicitudItemsId().getCantidadSolicitada() - Integer.parseInt(entregaDetalle.getCantidadAEntregar()));
                    }
                    obj.setAuNoSolicitudEntregaDetallesId(entregaDetalle);
                    obj.setCantidadEntregada(Integer.parseInt(entregaDetalle.getCantidadAEntregar()));
                    obj.setNumeroEntrega(entregaDetalle.getNumeroEntrega());
                    obj.setFaltantes(entregaDetalle.getCatidadTotal() - entregaDetalle.getCantidadEntregada() - Integer.parseInt(entregaDetalle.getCantidadAEntregar()));
                    if (obj.getCantidadPendiente() == 0) {
                        obj.setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL); //Total
                    } else {
                        if (obj.getCantidadEntregada() == 0) {
                            obj.setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO); //Sin entrega
                        } else {
                            obj.setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL); //Parcial
                        }
                    }
                    switch (obj.getTipoEntrega()) {
                        case AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL:
                            bean.auditoriaModificar(obj.getAuNoSolicitudItemsId());
                            obj.getAuNoSolicitudItemsId().setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL);
                            obj.getAuNoSolicitudItemsId().setEstado(AuNoSolicitudItem.ESTADO_GESTIONADO);
                            getAuNoSolicitudItemRemoto().actualizarTipoEntrega(obj.getAuNoSolicitudItemsId());
                            //actualizar registro principal aunosolicitudes
                            List<AuNoSolicitudItem> itms = getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId());
                            int totalEstadoGestionado = 0;
                            totalEstadoGestionado = itms.stream().filter(entrega -> (entrega.getEstado() == AuNoSolicitudItem.ESTADO_GESTIONADO))
                                    .map(_item -> 1).reduce(totalEstadoGestionado, Integer::sum);
                            if (totalEstadoGestionado == itms.size()) {
                                AuNoSolicitud solicitudDb = getAuSinSolicitudRemoto().consultar(bean.getObjeto().getId());
                                bean.getObjeto().setEstado(AuNoSolicitud.ESTADO_GESTIONADO);
                                if(bean.getObjeto().getEstado() != solicitudDb.getEstado()){
                                    procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_GESTIONADO, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, "");
                                }
                                //actualizar registro principal aunosolicitudes
                                bean.auditoriaModificar(bean.getObjeto());
                                getAuSinSolicitudRemoto().actualizarEstado(bean.getObjeto());
                                
                            }
                            break;
                        case AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL:
                            bean.auditoriaModificar(obj.getAuNoSolicitudItemsId());
                            obj.getAuNoSolicitudItemsId().setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL);
                            obj.getAuNoSolicitudItemsId().setEstado(AuNoSolicitudItem.ESTADO_EN_GESTION);
                            getAuNoSolicitudItemRemoto().actualizarTipoEntrega(obj.getAuNoSolicitudItemsId());
                            // 
                            bean.getObjeto().setEstado(AuNoSolicitud.ESTADO_EN_GESTION);
                            AuNoSolicitud solicitudDb = getAuSinSolicitudRemoto().consultar(bean.getObjeto().getId());
                            if(bean.getObjeto().getEstado() != solicitudDb.getEstado()){
                                procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_EN_GESTION, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, "");
                            }
                            //actualizar registro principal aunosolicitudes
                            bean.auditoriaModificar(bean.getObjeto());
                            getAuSinSolicitudRemoto().actualizarEstado(bean.getObjeto());
                            break;
                    }    
                    int idEntrega = getAuNoSolicitudEntregaRemoto().insertar(obj);
                    //procesar adjuntos
                    for (AuSolicitudAdjunto adjunto : bean.getObjetoEntrega().getListAuSolicitudAdjunto()) {
                        bean.auditoriaGuardar(adjunto);
                        adjunto.setAuNoSolicitudEntregasId(new AuNoSolicitudEntrega(idEntrega));
                        getAuSolicitudAdjuntoRemoto().insertar(adjunto);
                        if (paraNoInsertarVariosDocumentos == 0) {
                            generarArchivo(adjunto);
                        }
                    }
                    // se utiliza esta variable para que no guarde varias veces el archivo al servidor
                    paraNoInsertarVariosDocumentos++;
                    // acutalizar enrega detalle
                    bean.auditoriaModificar(entregaDetalle);
                    entregaDetalle.setCantidadEntregada(entregaDetalle.getCantidadEntregada() + Integer.parseInt(entregaDetalle.getCantidadAEntregar()));
                    entregaDetalle.setFaltantes(entregaDetalle.getCatidadTotal() - entregaDetalle.getCantidadEntregada());
                    AuNoSolicitudEntregaDetalle entregaDetalleBD = getAuNoSolicitudEntregaDetalleRemoto().consultar(entregaDetalle.getId());
                    if(entregaDetalleBD.getClasificacionEntrega() == null && entregaDetalle.getFaltantes() == 0){
                        entregaDetalle.setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_NORMAL);
                    }else if(obj.getFaltantes() > 0 && fechaActual.before(formato.parse(entregaDetalle.getFechaFin().toString()))){ 
                        entregaDetalle.setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_ENTREGA_CON_FALTANTES);
                    }else if(obj.getFaltantes() > 0 && formato.parse(entregaDetalle.getFechaFin().toString()).after(fechaActual)){
                        entregaDetalle.setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_FALTANTE_SIN_ENTREGA);
                    }else if(entregaDetalleBD.getClasificacionEntrega() != null && entregaDetalle.getFaltantes() == 0){
                        entregaDetalle.setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_FALTANTE_ENTREGADO);
                    }
                    getAuNoSolicitudEntregaDetalleRemoto().actualizar(entregaDetalle);
                }
            }
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId()));
            int cantidadEntregada = 0;
            for (AuNoSolicitudItem item : bean.getObjeto().getListAuNoSolicitudItem()) {
                if (item.getCntContratoDetallesId() != null) {
                    item.setCntContratoDetallesId(getCntContratoDetalleRemoto().consultar(item.getCntContratoDetallesId().getId()));
                }
                item.setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(item.getId()));
                for (AuNoSolicitudEntrega entrega : item.getListAuSolicitudEntrega()) {
                    cantidadEntregada = cantidadEntregada + entrega.getCantidadEntregada();
                    if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO
                            || item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO) {
                        item.setCantidadPendiente(item.getCantidadSolicitada());
                    } else {
                        item.setCantidadPendiente(item.getCantidadSolicitada() - cantidadEntregada);
                    }
                }
                if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO && item.getListAuSolicitudEntrega().isEmpty()) {
                    item.setCantidadPendiente(item.getCantidadSolicitada());
                }
                item.setCantidadEntrega(cantidadEntregada);
                cantidadEntregada = 0;
            }
            bean.addMensaje("Se realizó la entrega con éxito");
            bean.addMensaje("La Sin autorización no se puede anular");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void listarEntregas(AuNoSolicitudBean bean) {
        try {
            AuNoSolicitudItem objItem = bean.getObjetoItem();
            objItem.setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(objItem.getId()));
            objItem.setListAuNoSolicitudEntregaDetalle(getAuNoSolicitudEntregaDetalleRemoto().consultarEntregasPorAuNosolicitudesItemId(objItem.getId()));
            for (AuNoSolicitudEntrega entrega : objItem.getListAuSolicitudEntrega()) {
                entrega.setAuNoSolicitudItemsId(getAuNoSolicitudItemRemoto().consultar(entrega.getAuNoSolicitudItemsId().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo buscando las entregas, por favor contactar con el administrador");
        }
    }

    private void verEntrega(AuNoSolicitudBean bean) {
        try {
            bean.setObjetoEntrega(getAuNoSolicitudEntregaRemoto().consultar(bean.getObjetoEntrega().getId()));
            bean.getObjetoEntrega().setListAuSolicitudAdjunto(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdNoSollicitudesEntregas(bean.getObjetoEntrega().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo buscando las entregas, por favor contactar con el administrador");
        }
    }

    private void anularEntrega(AuNoSolicitudBean bean) {
        try {
            // actualizar entrega detalle
            if(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId() != null){
                Date fechaActual = new Date();
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaActualStr = formato.format(fechaActual);
                fechaActual = formato.parse(fechaActualStr);
                bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().setCantidadEntregada(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getCantidadEntregada() - bean.getObjetoEntrega().getCantidadEntregada());
                bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().setFaltantes(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getFaltantes() + bean.getObjetoEntrega().getCantidadEntregada());
                if(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getFaltantes() == 0){
                    bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_NORMAL);
                }else if(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getFaltantes() > 0 && fechaActual.before(formato.parse(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getFechaFin().toString()))){ 
                    bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_ENTREGA_CON_FALTANTES);
                }else if(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getFaltantes() > 0 && formato.parse(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().getFechaFin().toString()).after(fechaActual)){
                    bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId().setClasificacionEntrega(AuNoSolicitudEntregaDetalle.CLASIFICACION_FALTANTE_SIN_ENTREGA);
                }
                bean.auditoriaModificar(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId());
                getAuNoSolicitudEntregaDetalleRemoto().actualizar(bean.getObjetoEntrega().getAuNoSolicitudEntregaDetallesId());
            }            
            //actualizar entrega        
            bean.getObjetoEntrega().setAnulada(true);
            bean.getObjetoEntrega().setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO);
            bean.getObjetoEntrega().setCantidadEntregada(0);
            bean.getObjetoEntrega().setCantidadPendiente(bean.getObjetoEntrega().getCantidadPorEntregar());
            bean.auditoriaModificar(bean.getObjetoEntrega());
            getAuNoSolicitudEntregaRemoto().actualizarAnular(bean.getObjetoEntrega());
            bean.getObjetoItem().setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(bean.getObjetoEntrega().getAuNoSolicitudItemsId().getId()));
            int totalAnuladas = 0;
            for (AuNoSolicitudEntrega entrega : bean.getObjetoItem().getListAuSolicitudEntrega()) {
                entrega.setAuNoSolicitudItemsId(getAuNoSolicitudItemRemoto().consultar(entrega.getAuNoSolicitudItemsId().getId()));
                if (entrega.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO) {
                    totalAnuladas++;
                }
            }
            //actualizar item
            if (totalAnuladas == bean.getObjetoItem().getListAuSolicitudEntrega().size()) {
                bean.getObjetoEntrega().getAuNoSolicitudItemsId().setEstado(AuNoSolicitudItem.ESTADO_PENDIENTE);
                bean.getObjetoEntrega().getAuNoSolicitudItemsId().setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO);
                bean.auditoriaModificar(bean.getObjetoEntrega().getAuNoSolicitudItemsId());
                getAuNoSolicitudItemRemoto().actualizarTipoEntrega(bean.getObjetoEntrega().getAuNoSolicitudItemsId());
            } else {
                bean.getObjetoEntrega().getAuNoSolicitudItemsId().setEstado(AuNoSolicitudItem.ESTADO_EN_GESTION);
                bean.getObjetoEntrega().getAuNoSolicitudItemsId().setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL);
                bean.auditoriaModificar(bean.getObjetoEntrega().getAuNoSolicitudItemsId());
                getAuNoSolicitudItemRemoto().actualizarTipoEntrega(bean.getObjetoEntrega().getAuNoSolicitudItemsId());
            }
            // actualizar item a en gestion
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstado(AuNoSolicitud.ESTADO_EN_GESTION);
            getAuSinSolicitudRemoto().actualizarEstado(bean.getObjeto());
            procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_EN_GESTION, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, "");
           
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId()));
            int cantidadEntregada = 0;
            for (AuNoSolicitudItem item : bean.getObjeto().getListAuNoSolicitudItem()) {
                item.setListAuSolicitudEntrega(getAuNoSolicitudEntregaRemoto().consultarEntregasPorAuNosolicitudesItemId(item.getId()));
                for (AuNoSolicitudEntrega entrega : item.getListAuSolicitudEntrega()) {
                    cantidadEntregada = cantidadEntregada + entrega.getCantidadEntregada();
                    if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO
                            || item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO) {
                        item.setCantidadPendiente(item.getCantidadSolicitada());
                    } else {
                        item.setCantidadPendiente(item.getCantidadSolicitada() - cantidadEntregada);
                    }
                }
                if (item.getTipoEntrega() == AuNoSolicitudEntrega.TIPO_NO_ENTREGADO && item.getListAuSolicitudEntrega().isEmpty()) {
                    item.setCantidadPendiente(item.getCantidadSolicitada());
                }
                item.setCantidadEntrega(cantidadEntregada);
                cantidadEntregada = 0;
            }
            bean.getObjetoItem().setListAuNoSolicitudEntregaDetalle(getAuNoSolicitudEntregaDetalleRemoto().consultarEntregasPorAuNosolicitudesItemId( bean.getObjetoEntrega().getAuNoSolicitudItemsId().getId()));
            bean.addMensaje("Se anuló la entrega con éxito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo anulando la entrega, favor contactar al administrador");
        }
    }

    private void anularSinEntregaEntrega(AuNoSolicitudBean bean) {
        try {
            List<AuNoSolicitudItem> items = getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(bean.getObjeto().getId());
            for (AuNoSolicitudItem item : items) {
                bean.auditoriaModificar(item);
                item.setEstado(AuNoSolicitudItem.ESTADO_ANULADO);
                item.setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                item.setTipoEntrega(AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO);
                getAuNoSolicitudItemRemoto().actualizarAnularSinEntrega(item);
            }
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstado(AuNoSolicitud.ESTADO_ANULADO);
            // actualizar item a en gestion
            getAuSinSolicitudRemoto().actualizarEstado(bean.getObjeto());
            procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_ANULADO, AuNoSolicitudHistorico.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoJustificacion());
            bean.addMensaje("Se anuló sin autorizacion con éxito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo anulando la entrega, favor contactar al administrador");
        }
    }

    private void modificar(AuNoSolicitudBean bean) {
        try {
            AuNoSolicitud obj = bean.getObjeto();
            if (bean.getObjeto().getListAuNoSolicitudItem().isEmpty()) {
                bean.addError("Debe agregar al menos un ítem para modificar la solicitud");
            }
            //ivanegaa validar item modificado en otra pestaña
            AuNoSolicitud solicitudDB = getAuSinSolicitudRemoto().consultar(bean.getObjeto().getId());
            solicitudDB.setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(solicitudDB.getId()));
            if (bean.validarModificar(solicitudDB)) {
                bean.addError("La solicitud fue modificada, por favor refrescar la pagina.");
            }
            
            bean.auditoriaModificar(obj);
            Maestro clase = bean.getHashAmbitosAtencion().get(obj.getMaeAmbitoAtencionId());
            if (clase != null) {
                obj.setMaeAmbitoAtencionCodigo(clase.getValor());
                obj.setMaeAmbitoAtencionValor(clase.getNombre());
                obj.setMaeAmbitoAtencionTipo(clase.getTipo());
            }
            Maestro motivoSinAutorizacion = bean.getHashMotivoSinAutorizacion().get(obj.getMaeMotivoSinAutorizacionId());
            if (motivoSinAutorizacion != null) {
                obj.setMaeMotivoSinAutorizacionCodigo(motivoSinAutorizacion.getValor());
                obj.setMaeMotivoSinAutorizacionValor(motivoSinAutorizacion.getNombre());
                obj.setMaeMotivoSinAutorizacionTipo(motivoSinAutorizacion.getTipo());
            }
            CntProfesional profesional = bean.getObjeto().getCntProfesionalesId();
            CntProfesional profesionalRegistrado = null;
            if (profesional.getId() == null) {
                //Validar existencia del profesional
                if(profesional.getMaeTipoCodumentoId() > 0 && !profesional.getDocumento().isEmpty()){
                    profesionalRegistrado = getCntProfesionalRemoto().consultarNumDocumento(profesional.getMaeTipoCodumentoId(), profesional.getDocumento());
                    if (profesionalRegistrado == null) {
                        Maestro tipoDocProfesional = bean.getHashTiposDocumentoProfesional().get(profesional.getMaeTipoCodumentoId());
                        if(tipoDocProfesional != null){
                            profesional.setMaeTipoDocumentoCodigo(tipoDocProfesional.getValor());
                            profesional.setMaeTipoDocumentoValor(tipoDocProfesional.getNombre());
                        }

                        if (profesional.getRegistroMedico() == null) {
                            profesional.setRegistroMedico("Sin registro");
                        }
                        profesional.setUsuarioCrea(bean.getObjeto().getUsuarioCrea());
                        profesional.setTerminalCrea(bean.getObjeto().getTerminalCrea());
                        profesional.setFechaHoraCrea(bean.getObjeto().getFechaHoraCrea());
                        int idProfesional = getCntProfesionalRemoto().insertar(profesional);
                        profesional.setId(idProfesional);
                        //Crear el profesional en el prestador
                        if (bean.getObjeto().getObjetoEspecialidad() != null) {
                            CntProfesionalPrestador especialidad = new CntProfesionalPrestador();
                            especialidad.setCntProfesionalesId(profesional);
                            especialidad.setCntPrestador(bean.getObjeto().getCntPrestadorSedesId().getCntPrestador());
                            especialidad.setMaEspecialidadId(bean.getObjeto().getObjetoEspecialidad().getId());
                            especialidad.setMaEspecialidadCodigo(bean.getObjeto().getObjetoEspecialidad().getCodigo());
                            especialidad.setMaEspecialidadValor(bean.getObjeto().getObjetoEspecialidad().getNombre());
                            especialidad.setActivo(true);
                            especialidad.setUsuarioCrea(bean.getObjeto().getUsuarioCrea());
                            especialidad.setTerminalCrea(bean.getObjeto().getTerminalCrea());
                            especialidad.setFechaHoraCrea(bean.getObjeto().getFechaHoraCrea());
                            especialidad.setId(getCntPrestadorProfesionalRemoto().insertar(especialidad));
                        }
                    }
                }
            }else if(profesional.getId() != null){
                profesionalRegistrado = getCntProfesionalRemoto().consultarNumDocumento(profesional.getMaeTipoCodumentoId(),profesional.getDocumento());
                if (profesionalRegistrado != null) {
                    //Usar el profesional encontrado
                    profesionalRegistrado.setRegistroMedico(profesional.getRegistroMedico());
                    profesionalRegistrado.setUsuarioModifica(obj.getUsuarioCrea());
                    profesionalRegistrado.setTerminalModifica(obj.getTerminalCrea());
                    profesionalRegistrado.setFechaHoraModifica(obj.getFechaHoraCrea());
                    getCntProfesionalRemoto().actualizarRegistroMedico(profesionalRegistrado);
                    profesional = profesionalRegistrado;
                    if (bean.getObjetoProfesionalPrestador() != null && bean.getObjetoProfesionalPrestador().getId() == null) {
                        CntProfesionalPrestador especialidad = new CntProfesionalPrestador();
                        especialidad.setCntProfesionalesId(profesional);
                        especialidad.setCntPrestador(obj.getCntPrestadorSedesId().getCntPrestador());
                        especialidad.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                        especialidad.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                        especialidad.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                        especialidad.setActivo(true);
                        especialidad.setUsuarioCrea(obj.getUsuarioCrea());
                        especialidad.setTerminalCrea(obj.getTerminalCrea());
                        especialidad.setFechaHoraCrea(obj.getFechaHoraCrea());
                        especialidad.setId(getCntPrestadorProfesionalRemoto().insertar(especialidad));
                        obj.setMaEspecialidadId(bean.getObjetoProfesionalPrestador().getMaEspecialidadId());
                        obj.setMaEspecialidadCodigo(bean.getObjetoProfesionalPrestador().getMaEspecialidadCodigo());
                        obj.setMaEspecialidadValor(bean.getObjetoProfesionalPrestador().getMaEspecialidadValor());
                    }
                }
            }
            //String str = "";
            //str += (profesional.getPrimerNombre() == null) ? "" : profesional.getPrimerNombre();
            //str += (profesional.getSegundoNombre() == null) ? "" : " " + profesional.getSegundoNombre();
            //bean.getObjeto().setNombreProfesional(str);
            getAuSinSolicitudRemoto().actualizar(obj);
            procesarDiagnosticos(bean);
            procesarItems(bean);
            procesarBorrarItems(bean);
            procesarAdjuntos(bean);
            procesarHistorico(bean, AuNoSolicitudHistorico.ESTADO_PENDIENTE, AuNoSolicitudHistorico.TIPO_EDICION, obj.getEstadoStr());
            bean.addMensaje("El sin autorización se actualizo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void modificarItem(AuNoSolicitudBean bean) {
        try {
            AuNoSolicitudItem obj = bean.getObjetoItem();
            bean.auditoriaModificar(obj);
            getAuNoSolicitudItemRemoto().actualizar(obj);
            bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(obj.getAuNoSolicitudesId().getId()));
            bean.addMensaje("El Item se actualizo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void borrar(AuNoSolicitudBean bean) {
        try {
            //bean.setObjeto(getAnticipoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarItem(AuNoSolicitudBean bean) {
        try {
            AuNoSolicitudItem obj = bean.getObjetoItem();
            obj.setAuNoSolicitudesId(bean.getObjeto());
            List<AuNoSolicitudEntregaDetalle> entregasDetalles = getAuNoSolicitudEntregaDetalleRemoto().consultarEntregasPorAuNosolicitudesItemId(obj.getId());
            for(AuNoSolicitudEntregaDetalle entregasDetalle:entregasDetalles){
                entregasDetalle.setBorrado(Boolean.TRUE);
                bean.auditoriaModificar(entregasDetalle);
                entregasDetalle.setUsuarioBorra(entregasDetalle.getUsuarioModifica());
                entregasDetalle.setTerminalBorra(entregasDetalle.getTerminalModifica());
                entregasDetalle.setFechaHoraBorra(entregasDetalle.getFechaHoraModifica());
                getAuNoSolicitudEntregaDetalleRemoto().actualizarBorradoLogico(entregasDetalle);
            }
            obj.setBorrado(Boolean.TRUE);
            bean.auditoriaModificar(obj);
            obj.setUsuarioBorra(obj.getUsuarioModifica());
            obj.setTerminalBorra(obj.getTerminalModifica());
            obj.setFechaHoraBorra(obj.getFechaHoraModifica());
            getAuNoSolicitudItemRemoto().actualizarBorradoLogico(obj);
            //bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(obj.getAuNoSolicitudesId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, int idAsegAfiliado, AuNoSolicitudBean bean) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            if(maeEstado.getValor().equals("777")){
                bean.addError("El afiliado esta en estado pendiente soporte" );
                return false;
            }
            if (maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                return true;
            } else {
                AsegRegistroNovedad registrosNovedades = getNovedadAfiliadoRemoto().consultarUltimoRegistroUsuarioInactivoPorAfiliado(idAsegAfiliado);
                if(registrosNovedades != null){
                    if(registrosNovedades.getFechaNovedad().after(new Date())){
                        bean.addError("Fecha Novedad no puede ser mayor a hoy" );
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return true;
                }
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    @Override
    public void consultarProfesional(AuNoSolicitudBean bean) {
        try {
            int tipoDocumento = bean.getObjeto().getCntProfesionalesId().getMaeTipoCodumentoId();
            String documento = bean.getObjeto().getCntProfesionalesId().getDocumento();
            CntProfesional cntProfesional = getCntProfesionalRemoto().consultarNumDocumento(tipoDocumento, documento);

            if (cntProfesional != null) {
                bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
                CntProfesionalPrestador profesionalesP = getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(cntProfesional.getId(), bean.getObjeto().getCntPrestadorSedesId().getCntPrestador().getId());
                if (profesionalesP != null) {
                    bean.setObjetoProfesionalPrestador(profesionalesP);
                    bean.getObjeto().setNewProfesional(true);
                } else {
                    bean.getObjeto().setNewProfesional(false);
                }
                bean.getObjeto().setCntProfesionalesId(cntProfesional);
                bean.getObjeto().getCntProfesionalesId().setGuardar(false);
            } else {
                bean.getObjeto().setCntProfesionalesId(new CntProfesional());
                bean.getObjeto().getCntProfesionalesId().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjeto().getCntProfesionalesId().setDocumento(documento);
                bean.setObjetoProfesionalPrestador(new CntProfesionalPrestador());
                bean.getObjeto().getCntProfesionalesId().setGuardar(true);
                bean.getObjeto().setNewProfesional(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @SuppressWarnings("null")
    private boolean generarArchivo(AuSolicitudAdjunto adjunto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(adjunto.getRuta(), adjunto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(adjunto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {

            }
        }
        return esArchivoGuardado;
    }

    public void procesarDiagnosticos(AuNoSolicitudBean bean) {
        try {
            for (AuNoSolicitudDiagnostico diagnostico : bean.getObjeto().getListAuNoSolicitudDiagnostico()) {
                if (diagnostico.getId() == null) {
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setAuNoSolicitudesId(bean.getObjeto());
                    int idDiagnostico = getAuNoSolicitudDiagnosticoRemoto().insertar(diagnostico);
                    diagnostico.setId(idDiagnostico);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo insetar diagnostico, intentelo nuevamente");
        }
    }

    public void procesarItems(AuNoSolicitudBean bean) {
        try {
            int sumatorioCantidadTotal = 0;
            for (AuNoSolicitudItem item : bean.getObjeto().getListAuNoSolicitudItem()) {
                if (item.getId() == null) {
                    int cantidadEntregas = item.getCantidadSolicitada() / item.getNumEntregas(); 
                    bean.auditoriaGuardar(item);
                    item.setId(getAuNoSolicitudItemRemoto().insertar(item));
                    int numEntregas = 0;
                    Date fechaTemporal = null;
                    sumatorioCantidadTotal = 0;
                    for(int i = 0; i < item.getNumEntregas(); i++){
                        sumatorioCantidadTotal = sumatorioCantidadTotal + cantidadEntregas;
                        numEntregas = numEntregas + 1;
                        AuNoSolicitudEntregaDetalle entregaDetalle = new AuNoSolicitudEntregaDetalle();
                        bean.auditoriaGuardar(entregaDetalle);
                        entregaDetalle.setAuNoSolicitudItemsId(item);
                        entregaDetalle.setNumeroEntrega(numEntregas);
                        if(numEntregas == item.getNumEntregas()){
                            if(sumatorioCantidadTotal != item.getCantidadSolicitada()){
                                int restante = item.getCantidadSolicitada() - sumatorioCantidadTotal;
                                entregaDetalle.setCatidadTotal(cantidadEntregas + restante);
                            }else{
                                entregaDetalle.setCatidadTotal(cantidadEntregas);
                            }
                        }else {
                            entregaDetalle.setCatidadTotal(cantidadEntregas);
                        }
                        int duracion = item.getDuracionTratamiento() / item.getNumEntregas();
                        if(i == 0){
                            entregaDetalle.setFechaInicio(new Date());
                            fechaTemporal = sumarDiasAFecha(entregaDetalle.getFechaInicio(), duracion);
                            entregaDetalle.setFechaFin(fechaTemporal);
                        }else{
                            entregaDetalle.setFechaInicio(fechaTemporal);
                            fechaTemporal = sumarDiasAFecha(entregaDetalle.getFechaInicio(), duracion);
                            entregaDetalle.setFechaFin(fechaTemporal);
                        }
                        entregaDetalle.setId(getAuNoSolicitudEntregaDetalleRemoto().insertar(entregaDetalle));
                    }
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo insetar Item, intentelo nuevamente");
        }
    }
    
    public void procesarBorrarItems(AuNoSolicitudBean bean) {
        try {
            
            for (AuNoSolicitudItem item : bean.getListaItemsBorrar()) {
                if (item.getId() != null) {
                    List<AuNoSolicitudEntregaDetalle> entregasDetalles = getAuNoSolicitudEntregaDetalleRemoto().consultarEntregasPorAuNosolicitudesItemId(item.getId());
                    for(AuNoSolicitudEntregaDetalle entregasDetalle:entregasDetalles){
                        entregasDetalle.setBorrado(Boolean.TRUE);
                        bean.auditoriaModificar(entregasDetalle);
                        entregasDetalle.setUsuarioBorra(entregasDetalle.getUsuarioModifica());
                        entregasDetalle.setTerminalBorra(entregasDetalle.getTerminalModifica());
                        entregasDetalle.setFechaHoraBorra(entregasDetalle.getFechaHoraModifica());
                        getAuNoSolicitudEntregaDetalleRemoto().actualizarBorradoLogico(entregasDetalle);
                    }
                    bean.auditoriaModificar(item);
                    item.setBorrado(Boolean.TRUE);
                    item.setUsuarioBorra(item.getUsuarioModifica());
                    item.setTerminalBorra(item.getTerminalModifica());
                    item.setFechaHoraBorra(item.getFechaHoraModifica());
                    getAuNoSolicitudItemRemoto().actualizarBorradoLogico(item);
                    bean.getObjeto().setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(item.getAuNoSolicitudesId().getId()));
                }
            }
            bean.setListaItemsBorrar(new ArrayList<>());
        } catch (Exception e) {
            bean.addError("Hubo un fallo insetar Item, intentelo nuevamente");
        }
    }
    
    public static Date sumarDiasAFecha(Date fecha, int dias){
        if (dias == 0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        return calendar.getTime(); 
    }
    
    public void procesarAdjuntos(AuNoSolicitudBean bean) {
        try {
            for (AuSolicitudAdjunto adjunto : bean.getObjeto().getListAuSolicitudAdjunto()) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAuNoSolicitud(bean.getObjeto());
                    int idItem = getAuSolicitudAdjuntoRemoto().insertar(adjunto);
                    adjunto.setId(idItem);
                    generarArchivo(adjunto);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo insetar Item, intentelo nuevamente");
        }
    }

    public void procesarAdjuntosEntregas(AuNoSolicitudBean bean, int idEntregas) {
        try {
            for (AuSolicitudAdjunto adjunto : bean.getObjetoEntrega().getListAuSolicitudAdjunto()) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAuNoSolicitudEntregasId(new AuNoSolicitudEntrega(idEntregas));
                    int idItem = getAuSolicitudAdjuntoRemoto().insertar(adjunto);
                    generarArchivo(adjunto);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo insetar Item, intentelo nuevamente");
        }
    }
    
    public void procesarHistorico(AuNoSolicitudBean bean, int estado, int tipo, String observacion){
        try {
            AuNoSolicitudHistorico historico = new AuNoSolicitudHistorico();
            bean.auditoriaGuardar(historico);
            historico.setAuNoSolicitudesId(bean.getObjeto());
            historico.setEstado(estado);
            historico.setTipo(tipo);
            historico.setObservacion(observacion);
            historico.setId(getAuNoSolicitudHistoricoRemoto().insertar(historico));
        } catch (Exception e) {
            bean.addError("Hubo un fallo insetar Item, intentelo nuevamente");
        }
    }
    
    @Override
    public void listarAfiliado(AuNoSolicitudBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(0)));
            bean.setListaAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarPrestadores(AuNoSolicitudBean bean) {
        try {
            if (bean.getParamConsulta(1).getFiltros() == null) {
                bean.getParamConsulta(1).setFiltros(new HashMap());
            }
            bean.getParamConsulta(1).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(1).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(1).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(1)));
            bean.setListaPrestadores(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarPrestadoresEntrega(AuNoSolicitudBean bean) {
        try {
            if (bean.getParamConsulta(2).getFiltros() == null) {
                bean.getParamConsulta(2).setFiltros(new HashMap());
            }
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(2).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            bean.getParamConsulta(2).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(2).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(2).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(2)));
            bean.setListaPrestadoresEntrega(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarProcedimientos(AuNoSolicitudBean bean) {
        try {
            if (bean.getParamConsulta(3).getFiltros() == null) {
                bean.getParamConsulta(3).setFiltros(new HashMap());
            }

            bean.getParamConsulta(3).getFiltros().put("cntPrestadorSedesId", bean.getObjeto().getCntPrestadorSedeEntregaId().getId());
            bean.getParamConsulta(3).getFiltros().put("tipoTecnologia", AuConstantes.ID_TECNOLOGIA);
            bean.getParamConsulta(3).getFiltros().put("activo", true);
            bean.getParamConsulta(3).getFiltros().put("cntContratoSedesId.maeModalidadContratoCodigo", "02");
            bean.getParamConsulta(3).getFiltros().put("cntContrato.maeEstadoContratoCodigo", "01");
            bean.getParamConsulta(3).setCantidadRegistros(getCntContratoDetalleRemoto().consultarCantidadListaSinAutorizacion(bean.getParamConsulta(3)));
            bean.setListaProcedimientos(getCntContratoDetalleRemoto().consultarListaSinAutorizacion(bean.getParamConsulta(3)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMedicamentos(AuNoSolicitudBean bean) {
        try {
            if (bean.getParamConsulta(4).getFiltros() == null) {
                bean.getParamConsulta(4).setFiltros(new HashMap());
            }
            bean.getParamConsulta(4).getFiltros().put("cntPrestadorSedesId", bean.getObjeto().getCntPrestadorSedeEntregaId().getId());
            bean.getParamConsulta(4).getFiltros().put("tipoTecnologia", AuConstantes.ID_MEDICAMENTO);
            bean.getParamConsulta(4).getFiltros().put("activo", true);
            bean.getParamConsulta(4).getFiltros().put("cntContratoSedesId.maeModalidadContratoCodigo", "02");
            bean.getParamConsulta(4).getFiltros().put("cntContrato.maeEstadoContratoCodigo", "01");
            bean.getParamConsulta(4).setCantidadRegistros(getCntContratoDetalleRemoto().consultarCantidadListaSinAutorizacion(bean.getParamConsulta(4)));
            bean.setListaMedicamentos(getCntContratoDetalleRemoto().consultarListaSinAutorizacion(bean.getParamConsulta(4)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarInsumos(AuNoSolicitudBean bean) {
        try {
            if (bean.getParamConsulta(5).getFiltros() == null) {
                bean.getParamConsulta(5).setFiltros(new HashMap());
            }
            bean.getParamConsulta(5).getFiltros().put("cntPrestadorSedesId", bean.getObjeto().getCntPrestadorSedeEntregaId().getId());
            bean.getParamConsulta(5).getFiltros().put("tipoTecnologia", AuConstantes.ID_INSUMO);
            bean.getParamConsulta(5).getFiltros().put("activo", true);
            bean.getParamConsulta(5).getFiltros().put("cntContratoSedesId.maeModalidadContratoCodigo", "02");
            bean.getParamConsulta(5).getFiltros().put("cntContrato.maeEstadoContratoCodigo", "01");
            bean.getParamConsulta(5).setCantidadRegistros(getCntContratoDetalleRemoto().consultarCantidadListaSinAutorizacion(bean.getParamConsulta(5)));
            bean.setListaInsumos(getCntContratoDetalleRemoto().consultarListaSinAutorizacion(bean.getParamConsulta(5)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarPaquetes(AuNoSolicitudBean bean) {
        try {
            if (bean.getParamConsulta(6).getFiltros() == null) {
                bean.getParamConsulta(6).setFiltros(new HashMap());
            }
            bean.getParamConsulta(6).getFiltros().put("cntPrestadorSedesId", bean.getObjeto().getCntPrestadorSedeEntregaId().getId());
            bean.getParamConsulta(6).getFiltros().put("tipoTecnologia", AuConstantes.ID_PAQUETE);
            bean.getParamConsulta(6).getFiltros().put("activo", true);
            bean.getParamConsulta(6).getFiltros().put("cntContratoSedesId.maeModalidadContratoCodigo", "02");
            bean.getParamConsulta(6).getFiltros().put("cntContrato.maeEstadoContratoCodigo", "01");
            bean.getParamConsulta(6).setCantidadRegistros(getCntContratoDetalleRemoto().consultarCantidadListaSinAutorizacion(bean.getParamConsulta(6)));
            bean.setListaPaquetes(getCntContratoDetalleRemoto().consultarListaSinAutorizacion(bean.getParamConsulta(6)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public boolean consultarCantidadAnuladas(AuNoSolicitudBean bean, int idAnexo4Item) {
        boolean factura = true;
        try {
            int respuesta = getAuNoSolicitudEntregaRemoto().consultarCantidadEstadoAnuladaloporAuNoSolicitudItem(idAnexo4Item);
            if (respuesta >= 4) {
                factura = false;
            }
        } catch (Exception e) {
            factura = true;
        }
        return factura;
    }

    @Override
    public MaMedicamento consultarMedicamento(AuNoSolicitudBean bean, int idMedicamento) {
        MaMedicamento medicamento = null;
        try {
            medicamento = getMaMedicamentoRemoto().consultar(idMedicamento);

        } catch (Exception e) {
            medicamento = null;
        }
        return medicamento;
    }
    
    @Override
    public AuNoSolicitud consultarAuNoSolicitud(AuNoSolicitudBean bean, int idAuNoSolicitud) {
        AuNoSolicitud auNoSolicitud = null;
        try {
            auNoSolicitud = getAuSinSolicitudRemoto().consultar(idAuNoSolicitud);
            auNoSolicitud.setListAuNoSolicitudItem(getAuNoSolicitudItemRemoto().consultarItemPorAuNosolicitudesId(idAuNoSolicitud));
        } catch (Exception e) {
            auNoSolicitud = null;
        }
        return auNoSolicitud;
    }
    
    @Override
    public AsegRegistroNovedad consultarUltinaNovedadInactivoAfiliado(AuNoSolicitudBean bean) {
        AsegRegistroNovedad asegRegistroNovedad = null;
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getMaeEstadoAfiliacion());
            if (!maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                asegRegistroNovedad = getNovedadAfiliadoRemoto().consultarUltimoRegistroUsuarioInactivoPorAfiliado(bean.getObjeto().getAsegAfiliadosId().getId());
            } 
        } catch (Exception e) {
            asegRegistroNovedad = null;
        }
        return asegRegistroNovedad;
    }
    
    @Override
    public void verBitacorasSinAutorizaciones(AuNoSolicitudBean bean) {
        try {
            bean.getObjeto().setListaAuNoSolicitudHistorico(getAuNoSolicitudHistoricoRemoto().consultarHistoricoPorAuNosolicitudesId(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las historicos");
        }
    }
    
    @Override
    public String validarTecnologia(String tipoTecnologia, String codigoTecnologia, String tipoDocumento, String numeroDocumento) {
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarCodigoTecnologia(tipoTecnologia, codigoTecnologia, tipoDocumento, numeroDocumento);
            if (respuesta.getCodigo() == 1) {
                return respuesta.getMensaje();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return "Se presento un fallo en la validación de la tecnologia, por favor ponerse en contacto con el adminitrador";
        }
    }

    @Override
    public String validarDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento) {
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarDiagnostico(codigoDiagnostico, tipoDocumento, numeroDocumento);
            if (respuesta.getCodigo() == 1) {
                return respuesta.getMensaje();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    @Override
    public String validarCantidadTecnologia(String tipoTecnologia, int cantidad, String codigoTecnologia) {
        try {
            ValidaRespuestaDTO respuesta = getValidadorRemoto().validarAuCantidadTecnologia(tipoTecnologia, "" + cantidad, codigoTecnologia);
            if (respuesta.getCodigo() == 1) {
                return respuesta.getMensaje();
            } else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public void cargarMaestros(AuNoSolicitudBean bean){
        try {
            //Singleton Ubicaciones
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());

            bean.setListaTipoDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentoPersona(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoPersona()));

            bean.setListaTiposDocumentoProfesional(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL));
            bean.setHashTiposDocumentoProfesional(AuConstantes.obtenerHashMaestro(bean.getListaTiposDocumentoProfesional()));

            bean.setListaAmbitosAtencion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));
            if (!bean.getListaAmbitosAtencion().isEmpty()) {
                List<Maestro> nuevaLista = new ArrayList();
                bean.getListaAmbitosAtencion().stream().filter(maestro -> (!maestro.getValor().equalsIgnoreCase(AuConstantes.CODIGO_AMBITO_MIXTO)))
                        .forEachOrdered(maestro -> {
                            nuevaLista.add(maestro);
                        });
                bean.setListaAmbitosAtencion(nuevaLista);
            }
            bean.setHashAmbitosAtencion(AuConstantes.obtenerHashMaestro(bean.getListaAmbitosAtencion()));

            bean.setListaViaAdmistracion(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_MEDICAMENTO_VIA_ADMINISTRACION));
            bean.setHashViaAdmistracion(AuConstantes.obtenerHashMaestro(bean.getListaViaAdmistracion()));

            bean.setListaTiposAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ADJUNTO_TIPO));
            bean.setHashTiposAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposAdjuntos()));

            bean.setListaCausaNoEntrega(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_ENTREGA_CAUSAS_NO_ENTREGA));
            bean.setHashCausaNoEntrega(AuConstantes.obtenerHashMaestro(bean.getListaCausaNoEntrega()));
            
            bean.setListaMotivoSinAutorizacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_MOTIVO_SIN_AUTORIZACION));
            bean.setHashMotivoSinAutorizacion(AuConstantes.obtenerHashMaestro(bean.getListaMotivoSinAutorizacion()));

            /// listas lazy afiliado, prestado
            bean.setListaTipoDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaGeneroAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setListaRegimenAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());

        } catch (Exception ex) {
            Logger.getLogger(AuNoSolicitudServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void cargaInicial(AuNoSolicitudBean bean) {
        try {
            /*bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
            bean.setHashClasificacion(AuConstantes.obtenerHashMaestro(bean.getListaClasificacion()));*/
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
