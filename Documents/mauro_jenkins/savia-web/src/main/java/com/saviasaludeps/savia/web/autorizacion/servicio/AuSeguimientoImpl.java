/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoPrestadorAsignado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoServicio;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuItemBitacoraRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoAfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoGestionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoPrestadorAsignadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoServicioRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuSeguimientoBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author iavenegas
 */
public class AuSeguimientoImpl extends AccionesBO implements AuSeguimientoIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private AuSeguimientoRemoto getAuSeguimientoRemoto() throws Exception {
        return (AuSeguimientoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoServicio", AuSeguimientoRemoto.class.getName());
    }

    private AuSeguimientoAfiliadoRemoto getAuSeguimientoAfiliadoRemoto() throws Exception {
        return (AuSeguimientoAfiliadoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoAfiliadoServicio", AuSeguimientoAfiliadoRemoto.class.getName());
    }

    private AuSeguimientoServicioRemoto getAuSeguimientoServicioRemoto() throws Exception {
        return (AuSeguimientoServicioRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoServicioServicio", AuSeguimientoServicioRemoto.class.getName());
    }

    private AuSeguimientoAfiliadoContactoRemoto getAuSeguimientoAfiliadoContactoRemoto() throws Exception {
        return (AuSeguimientoAfiliadoContactoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoAfiliadoContactoServicio", AuSeguimientoAfiliadoContactoRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }

    private AuSeguimientoGestionRemoto getAuSeguimientoGestionRemoto() throws Exception {
        return (AuSeguimientoGestionRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoGestionServicio", AuSeguimientoGestionRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private CntContratoSedeRemoto getCntContratoSedeRemoto() throws Exception {
        return (CntContratoSedeRemoto) RemotoEJB.getEJBRemoto("CntContratoSedeServicio", CntContratoSedeRemoto.class.getName());
    }

    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }

    private AuItemBitacoraRemoto getAuItemBitacoraRemoto() throws Exception {
        return (AuItemBitacoraRemoto) RemotoEJB.getEJBRemoto("AuItemBitacoraServicio", AuItemBitacoraRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private AuSeguimientoPrestadorAsignadoRemoto getAuSeguimientoPrestadorAsignadoRemoto() throws Exception {
        return (AuSeguimientoPrestadorAsignadoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoPrestadorAsignadoServicio", AuSeguimientoPrestadorAsignadoRemoto.class.getName());
    }

    private AuGrupoRemoto getAuGrupoRemoto() throws Exception {
        return (AuGrupoRemoto) RemotoEJB.getEJBRemoto("AuGrupoServicio", AuGrupoRemoto.class.getName());
    }

    @Override
    public void Accion(AuSeguimientoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            gestionar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            guardarGestionar(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearGestion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            cancelar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarCancelar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarAdjuntosGestion(bean);
                            break;
                        case Url.ACCION_CREAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarAdjuntosGestion(bean);
                            break;
                    }
                    break;
            }
        } else {
            System.err.println("Sesión inactiva");
        }
    }

    @Override
    public void cargaInicial(AuSeguimientoBean bean) {
        try {

            bean.setListaTipoTecnologia(AuConstantes.obtenerMaestroTipoTecnologias());
            bean.setListaEstadosItem(AuConstantes.obtenerEstadosItemSolicitud());
            //documento
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumento(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));
            //estado seguimiento 
            bean.setListaEstadoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_SEGUIMIENTO_ESTADO));
            bean.setHashEstadoSeguimiento(AuConstantes.obtenerHashMaestro(bean.getListaEstadoSeguimiento()));
            bean.setHashEstadoSeguimientoValor(AuConstantes.obtenerHashMaestroValor(bean.getListaEstadoSeguimiento()));
            //estado seguimiento gestion
            bean.setListaEstadoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_SEGUIMIENTO_ESTADO));
            bean.setHashEstadoGestion(AuConstantes.obtenerHashMaestro(bean.getListaEstadoGestion()));
            bean.setHashEstadoGestionValor(AuConstantes.obtenerHashMaestroValor(bean.getListaEstadoGestion()));
            //motivo seguimiento gestion
            bean.setListaMotivoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_SEGUIMIENTO_MOTIVO));
            bean.setHashMotivoGestion(AuConstantes.obtenerHashMaestro(bean.getListaMotivoGestion()));
            bean.setHashMotivoGestionValor(AuConstantes.obtenerHashMaestroValor(bean.getListaMotivoGestion()));
            //documentos empresa
            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            //ubicaciones
            bean.setListaUbicacion(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            //tipo contactos
            bean.setListaTiposContacto(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setHashTiposContacto(AuConstantes.obtenerHashMaestro(bean.getListaTiposContacto()));
            //documento archivo
            bean.setListaTipoDocumentoArchivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ADJUNTO_TIPO));
            bean.setHashTipoDocumentoArchivo(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoArchivo()));
            bean.setListaTipoAmbito(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));
            if (!bean.getListaTipoAmbito().isEmpty()) {
                List<Maestro> nuevaLista = new ArrayList();
                for (Maestro maestro : bean.getListaTipoAmbito()) {
                    if (!maestro.getValor().equalsIgnoreCase(AuConstantes.CODIGO_AMBITO_MIXTO)) {
                        nuevaLista.add(maestro);
                    }
                }
                bean.setListaTipoAmbito(nuevaLista);
            }
            bean.setHashTipoAmbito(AuConstantes.obtenerHashMaestro(bean.getListaTipoAmbito()));
            //grupos
            bean.setListaGruposActivos(getAuGrupoRemoto().consultarActivos());

        } catch (Exception e) {
            bean.addError("No fue posible realizar carga inicial");
        }
    }

    private void listar(AuSeguimientoBean bean) {
        try {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }

            bean.getParamConsulta(0).setCantidadRegistros(getAuSeguimientoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuSeguimientoRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception ex) {
            bean.addError("Hubo un fallo listando, favor consultar con el administrador");
        }
    }

    private void ver(AuSeguimientoBean bean) {
        try {
            bean.setObjetoSeguimiento(
                    getAuSeguimientoRemoto().consultar(bean.getObjetoSeguimiento().getId())
            );
            if (!bean.getObjetoSeguimiento().getAuSeguimientoServicioList().isEmpty()) {
                bean.setObjetoSeguimientoServicio(
                        bean.getObjetoSeguimiento().getAuSeguimientoServicioList().get(0)
                );
            } else {
                bean.setObjetoSeguimientoServicio(null);
            }

        } catch (Exception e) {
            bean.addError("Error en ver ".concat(e.getMessage()));
        }
    }

    private void gestionar(AuSeguimientoBean bean) {
        try {
            ver(bean);
            if (bean.getObjetoSeguimientoServicio() != null) {
                bean.setListaServicioDetalle(new ArrayList());
                bean.setListaServicioDetalle2(new ArrayList());
                if (bean.validarTipoServicio(AuSeguimientoServicio.SEGUIMIENTO_CPAP)
                        || bean.validarTipoServicio(AuSeguimientoServicio.SEGUIMIENTO_BPAP)) {

                    List<Maestro> listaDetalle = getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AU_SEGUIMIENTO_SERVICIO_DETALLE,
                            bean.getObjetoSeguimientoServicio().getMaeSeguimientoServicioId());
                    for (Maestro maestro : listaDetalle) {
                        if (maestro.contieneAccion(MaestroAccion.AU_SEGUIMIENTO_SERVICIO_DETALLE_TIPO_MASCARA)) {
                            bean.getListaServicioDetalle().add(maestro);
                        }
                        if (maestro.contieneAccion(MaestroAccion.AU_SEGUIMIENTO_SERVICIO_DETALLE_TALLA_MASCARA)) {
                            bean.getListaServicioDetalle2().add(maestro);
                        }
                    }
                } else {
                    bean.setListaServicioDetalle(
                            getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AU_SEGUIMIENTO_SERVICIO_DETALLE,
                                    bean.getObjetoSeguimientoServicio().getMaeSeguimientoServicioId())
                    );
                }
                bean.setHashServicioDetalle(AuConstantes.obtenerHashMaestro(bean.getListaServicioDetalle()));
                bean.setHashServicioDetalle2(AuConstantes.obtenerHashMaestro(bean.getListaServicioDetalle2()));
            }

            //2023-12-11 jyperez realizamos el registro de la fecha hora atiende
            //2024-01-31 jyperez se incluye la validación de que el campo esté vacio para actualizarlo solo una vez
            AuSeguimiento seguimiento = getAuSeguimientoRemoto().consultarCorto(bean.getObjetoSeguimiento().getId());
            if (seguimiento != null && seguimiento.getFechaHoraAtiende() == null) {
                seguimiento.setFechaHoraAtiende(new Date());
                bean.auditoriaModificar(seguimiento);
                getAuSeguimientoRemoto().actualizarFechasSeguimiento(seguimiento);
            } else if (seguimiento == null) {
                throw new Exception("Error almacenando registro de fecha hora atiende");
            }
        } catch (Exception e) {
            bean.addError("Error al abrir gestion: " + e.getMessage());
        }
    }

    private void guardarGestionar(AuSeguimientoBean bean) {

        try {
            if (bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSeguimientoAfiliadoContactosList().isEmpty()) {
                bean.addError("Debe completar Contactos");
            } else if (bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getEnergiaPrepagada() != null
                    && bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getEnergiaPrepagada()
                    && bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSolicitudAdjuntosList().isEmpty()) {
                bean.addError("Debe completar Adjuntos energía prepagada");
            } else {
                //Observaciones especificas afiliado
                getAuSeguimientoAfiliadoRemoto().actualizar(bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId());
                for (AuSeguimientoAfiliadoContacto contacto : bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSeguimientoAfiliadoContactosList()) {
                    if (contacto.getId() == null) {
                        Maestro maestroTipoContacto = bean.getHashTiposContacto().get(contacto.getMaeTipoContactoId());
                        contacto.setMaeTipoContactoCodigo(maestroTipoContacto.getValor());
                        contacto.setMaeTipoContactoValor(maestroTipoContacto.getNombre());
                        bean.auditoriaGuardar(contacto);
                        getAuSeguimientoAfiliadoContactoRemoto().insertar(contacto);
                    }
                }
                //adjuntos afiliado
                for (AuSolicitudAdjunto adjunto : bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSolicitudAdjuntosList()) {
                    if (adjunto.getId() == null) {
                        bean.auditoriaGuardar(adjunto);
                        adjunto.setRuta(PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_SEGUIMIENTO_AFILIADOS));
                        guardarAdjunto(adjunto);
                    }
                }
                //servicio
                if (bean.getObjetoSeguimientoServicio().getMaeTipoMascaraId() != null) {
                    Maestro maestroTipoMascara = bean.getHashServicioDetalle().get(bean.getObjetoSeguimientoServicio().getMaeTipoMascaraId());
                    bean.getObjetoSeguimientoServicio().setMaeTipoMascaraCodigo(maestroTipoMascara.getValor());
                    bean.getObjetoSeguimientoServicio().setMaeTipoMascaraValor(maestroTipoMascara.getNombre());
                }
                if (bean.getObjetoSeguimientoServicio().getMaeTallaMascaraId() != null) {
                    Maestro maestroTallaMascara = bean.getHashServicioDetalle2().get(bean.getObjetoSeguimientoServicio().getMaeTallaMascaraId());
                    bean.getObjetoSeguimientoServicio().setMaeTallaMascaraCodigo(maestroTallaMascara.getValor());
                    bean.getObjetoSeguimientoServicio().setMaeTallaMascaraValor(maestroTallaMascara.getNombre());
                }
                if (bean.getObjetoSeguimientoServicio().getMaeTipoSondaId() != null) {
                    Maestro maestroTipoSonda = bean.getHashServicioDetalle().get(bean.getObjetoSeguimientoServicio().getMaeTipoSondaId());
                    bean.getObjetoSeguimientoServicio().setMaeTipoSondaCodigo(maestroTipoSonda.getValor());
                    bean.getObjetoSeguimientoServicio().setMaeTipoSondaValor(maestroTipoSonda.getNombre());
                }
                if (bean.getObjetoSeguimientoServicio().getMaeTipoInsumoId() != null) {
                    Maestro maestroTipoInsumo = bean.getHashServicioDetalle().get(bean.getObjetoSeguimientoServicio().getMaeTipoInsumoId());
                    bean.getObjetoSeguimientoServicio().setMaeTipoInsumoCodigo(maestroTipoInsumo.getValor());
                    bean.getObjetoSeguimientoServicio().setMaeTipoInsumoValor(maestroTipoInsumo.getNombre());
                }
                getAuSeguimientoServicioRemoto().actualizar(bean.getObjetoSeguimientoServicio());

                //seguimiento
                if (bean.getObjetoSeguimiento().getMaeAcompananteTipoDocumentoId() != null) {
                    Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjetoSeguimiento().getMaeAcompananteTipoDocumentoId());
                    bean.getObjetoSeguimiento().setMaeAcompananteTipoDocumentoCodigo(tipoDoc.getValor());
                    bean.getObjetoSeguimiento().setMaeAcompananteTipoDocumentoValor(tipoDoc.getNombre());
                }
                //adjuntos del seguimiento
                for (AuSolicitudAdjunto adjunto : bean.getObjetoSeguimiento().getAuSolicitudAdjuntosList()) {
                    if (adjunto.getId() == null) {
                        bean.auditoriaGuardar(adjunto);
                        adjunto.setRuta(PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_SEGUIMIENTOS));
                        guardarAdjunto(adjunto);
                    }
                }
                bean.auditoriaModificar(bean.getObjetoSeguimiento());
                getAuSeguimientoRemoto().actualizar(bean.getObjetoSeguimiento());

                bean.addMensaje("Seguimiento modificado correctamente");
            }
        } catch (Exception exception) {
            bean.addError("Error en guardar gestionar ");
        }
    }

    private void crearGestion(AuSeguimientoBean bean) {
        try {
            bean.setObjetoSeguimientoGestion(new AuSeguimientoGestion());

            bean.setListaEstadoGestionCrear(
                    getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AU_SEGUIMIENTO_ESTADO, bean.getObjetoSeguimiento().getMaeEstadoSeguimientoId())
            );
        } catch (Exception e) {
            bean.addError("Error en crear gestion ".concat(e.getMessage()));
        }
    }

    private void guardarGestion(AuSeguimientoBean bean) {
        try {
            bean.limpiarMensajes();
            Maestro maestroEstadoGestion = bean.getHashEstadoGestion().get(bean.getObjetoSeguimientoGestion().getMaeEstadoSeguimientoGestionId());

            //verifica que la informacion este completa antes de cambiar de estado
            if (maestroEstadoGestion.getValor().equalsIgnoreCase(AuSeguimientoGestion.ESTADO_ASIGNADO_PRESTADOR)
                    || maestroEstadoGestion.getValor().equalsIgnoreCase(AuSeguimientoGestion.ESTADO_GESTIONADO)) {
                validarInformacionObligatoria(bean, maestroEstadoGestion);
            }

            if (!bean.isError()) {
                bean.getObjetoSeguimientoGestion().setMaeEstadoSeguimientoGestionCodigo(maestroEstadoGestion.getValor());
                bean.getObjetoSeguimientoGestion().setMaeEstadoSeguimientoGestionValor(maestroEstadoGestion.getNombre());

                Maestro maestroMotivoGestion = bean.getHashMotivoGestion().get(bean.getObjetoSeguimientoGestion().getMaeMotivoSeguimientoId());
                bean.getObjetoSeguimientoGestion().setMaeMotivoSeguimientoCodigo(maestroMotivoGestion.getValor());
                bean.getObjetoSeguimientoGestion().setMaeMotivoSeguimientoValor(maestroMotivoGestion.getNombre());

                bean.getObjetoSeguimientoGestion().setAuSeguimiento(bean.getObjetoSeguimiento());

                Maestro maestroEstado = null;
                //valida estados que cambian(los valores son los mismos entre los dos tipo de maestro)
                //2023-11-27 jyperez se actualizar error lógico debido a que no se puede validar la misma variable con dos valores diferentes
                if (maestroEstadoGestion.getValor().equalsIgnoreCase(AuSeguimientoGestion.ESTADO_GESTION_NOTA)
                       || maestroEstadoGestion.getValor().equalsIgnoreCase(AuSeguimientoGestion.ESTADO_REPROGRAMAR_SERVICIO)) {
                    bean.getObjetoSeguimientoGestion().setTipo(AuSeguimientoGestion.TIPO_GESTION);
                } else {
                    maestroEstado = bean.getHashEstadoSeguimientoValor().get(maestroEstadoGestion.getValor());
                    bean.getObjetoSeguimientoGestion().setTipo(AuSeguimientoGestion.TIPO_CAMBIO_ESTADO);
                }
                bean.auditoriaGuardar(bean.getObjetoSeguimientoGestion());
                getAuSeguimientoGestionRemoto().insertar(bean.getObjetoSeguimientoGestion());
                bean.addMensaje("La gestión se creo correctamente");
                //cambia estado seguimiento
                if (maestroEstado != null) {
                    //Prestador asignado
                    if (maestroEstado.getValor().equalsIgnoreCase(AuSeguimiento.ESTADO_ASIGNADO_PRESTADOR)) {
                        Empresa empresa = getEmpresaRemoto().consultarPorPrestador(
                                bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId().getCntPrestador().getId()
                        );
                        bean.getObjetoSeguimiento().setGnEmpresasId(empresa);
                        AuSeguimientoPrestadorAsignado prestador = new AuSeguimientoPrestadorAsignado();
                        prestador.setCntPrestadorSede(bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId());
                        prestador.setEstado(AuSeguimientoPrestadorAsignado.ESTADO_ASIGNADO);
                        prestador.setAuSeguimiento(bean.getObjetoSeguimiento());
                        bean.auditoriaGuardar(prestador);
                        getAuSeguimientoPrestadorAsignadoRemoto().insertar(prestador);
                        AuSeguimientoGestion seguimientoGestionNota = castGestionSeguimiento(bean, "IPS " + bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId().getNombreSede());
                        getAuSeguimientoGestionRemoto().insertar(seguimientoGestionNota);
                        //2023-12-07 jyperez se actualiza la fecha de asignado prestador
                        bean.getObjetoSeguimiento().setFechaHoraAsignaPrestador(new Date());
                    } else if (maestroEstado.getValor().equalsIgnoreCase(AuSeguimiento.ESTADO_RECHAZADO_PRESTADOR)) {
                        if (bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId() != null) {

                            AuSeguimientoPrestadorAsignado prestador = getAuSeguimientoPrestadorAsignadoRemoto().prestadorPorSeguimientoPorPrestador(
                                    bean.getObjetoSeguimiento().getId(),
                                    bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId().getId()
                            );
                            prestador.setEstado(AuSeguimientoPrestadorAsignado.ESTADO_RECHAZADO);
                            bean.auditoriaModificar(prestador);
                            getAuSeguimientoPrestadorAsignadoRemoto().actualizar(prestador);
                            bean.getObjetoSeguimiento().setGnEmpresasId(null);
                            bean.getObjetoSeguimiento().setCntPrestadorSedeAsignadoId(null);
                        }
                    } else if (maestroEstado.getValor().equalsIgnoreCase(AuSeguimiento.ESTADO_ACEPTADO_PRESTADOR)) {
                        if (bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId() != null) {
                            AuSeguimientoPrestadorAsignado prestador = getAuSeguimientoPrestadorAsignadoRemoto().prestadorPorSeguimientoPorPrestador(
                                    bean.getObjetoSeguimiento().getId(),
                                    bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId().getId()
                            );
                            prestador.setEstado(AuSeguimientoPrestadorAsignado.ESTADO_ACEPTADO);
                            bean.auditoriaModificar(prestador);
                            getAuSeguimientoPrestadorAsignadoRemoto().actualizar(prestador);
                        }
                    }
                    //para que no le vuelva aparecer al proveedor
                    if (maestroEstado.getValor().equalsIgnoreCase(AuSeguimiento.ESTADO_ENTREGADO)) {
                        bean.getObjetoSeguimiento().setGnEmpresasId(null);
                    }
                    bean.getObjetoSeguimiento().setMaeEstadoSeguimientoId(maestroEstado.getId());
                    bean.getObjetoSeguimiento().setMaeEstadoSeguimientoCodigo(maestroEstado.getValor());
                    bean.getObjetoSeguimiento().setMaeEstadoSeguimientoValor(maestroEstado.getNombre());
                    bean.auditoriaModificar(bean.getObjetoSeguimiento());

                    getAuSeguimientoRemoto().actualizarEstado(bean.getObjetoSeguimiento());
                    //2023-11-30 jyperez modificación a uso de maestro acción AU_SEGUIMIENTO_ESTADO_DEVOLUCION_ITEM 
                    if (maestroEstado.contieneAccion(MaestroAccion.AU_SEGUIMIENTO_ESTADO_DEVOLUCION_ITEM )) {
                        //actualizar estado Item
                        AuAnexo3Item itemAnexo3 = bean.getObjetoSeguimiento().getAuAnexo3ItemsId();
                        AuItemBitacora penultima = getAuItemBitacoraRemoto().penultimaBitacora(itemAnexo3.getId());
                        //2023-12-13 jyperez realizamos validación del estado actual del item
                        int estadoActualAnexo3Item = itemAnexo3.getEstado();
                        switch(maestroEstado.getValor()) {
                            case AuSeguimiento.ESTADO_CANCELADO:
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_CANCELADO_SEGUIMIENTO);
                                break;
                            default:
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_CON_SEGUIMIENTO);
                                break;
                        }
                        bean.auditoriaModificar(itemAnexo3);
                        getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                        //regresa a estado anterior de seguimiento para ser aprobado
                        //cambiar por campo estado de bitacora
                        if (penultima.getEstado() != null) {
                            itemAnexo3.setEstado(penultima.getEstado());
                        } else {
                            itemAnexo3.setEstado(itemAnexo3.getEstadoInt(penultima.getDescripcion()));
                        }
                        //2023-12-13 jyperez validamos si el estado actual es un estado donde no se modifica, entonces actualizamos a ese 
                        // y no al estado de la penúltima bitácora
                        if (validarEstadoFinal(estadoActualAnexo3Item)) {
                            itemAnexo3.setEstado(estadoActualAnexo3Item);
                        } else if(penultima.getEstado() == AuAnexo3Item.ESTADO_CON_SEGUIMIENTO) {
                            itemAnexo3.setEstado(estadoActualAnexo3Item);
                        }
                        bean.auditoriaModificar(itemAnexo3);
                        getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                        guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                    }

                    bean.addMensaje("El estado se actualizo correctamente");
                }
                //2023-12-07 jyperez se valida y se actualiza las fechas de seguimiento
                AuSeguimiento seguimiento= getAuSeguimientoRemoto().consultarCorto(bean.getObjetoSeguimientoGestion().getAuSeguimiento().getId());
                //validamos las fechas de seguimiento para poderlas actualizar.
                if (seguimiento != null) {
                    if (seguimiento.getFechaHoraPrimeraGestion() == null) {
                        Date fechaGestion = new Date();
                        seguimiento.setFechaHoraPrimeraGestion(fechaGestion);
                        seguimiento.setFechaHoraUltimaGestion(fechaGestion);
                    } else {
                        seguimiento.setFechaHoraUltimaGestion(new Date());
                    }
                    //actualizamos únicamente las fechas de gestión
                    bean.auditoriaModificar(seguimiento);
                    getAuSeguimientoRemoto().actualizarFechasSeguimiento(seguimiento);
                }
            }

        } catch (Exception e) {
            bean.addError("Hubo un error al guardar gestión, intentelo nuevamente");
        }
    }
    
    /**
     * Función para validar los estados donde no se debe modificar el estado del anexo3Item
     * @return 
     */
    
    private boolean validarEstadoFinal(int estadoActual){
        boolean estadoFinal = false;
       if (estadoActual == AuAnexo3Item.ESTADO_RECHAZO ||
            estadoActual == AuAnexo3Item.ESTADO_APROBADO_AUDITORIA ||
            estadoActual == AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO ||
            estadoActual == AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA ||
            estadoActual == AuAnexo3Item.ESTADO_ANULADO ||
            estadoActual == AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA ||
            estadoActual == AuAnexo3Item.ESTADO_CON_COTIZACION ||
            estadoActual == AuAnexo3Item.ESTADO_ANULADO_AUTORIZACION ||
            estadoActual == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION ||
            estadoActual == AuAnexo3Item.ESTADO_DIRECCIONADO ||
            estadoActual == AuAnexo3Item.ESTADO_DIRECCIONADO_AUTOMATICO ||
            estadoActual == AuAnexo3Item.ESTADO_PREAUTORIZADO ||
            estadoActual == AuAnexo3Item.ESTADO_AUTORIZADA_PREAUTORIZACION ||
            estadoActual == AuAnexo3Item.ESTADO_ANULADO_PREAUTORIZACION
               ) {
           estadoFinal = true;
       }
        return estadoFinal;
    }

    private void cancelar(AuSeguimientoBean bean) {
        try {
            bean.setObjetoSeguimiento(
                    getAuSeguimientoRemoto().consultarCorto(bean.getObjetoSeguimiento().getId())
            );
            bean.setObjetoSeguimientoGestion(new AuSeguimientoGestion());
            bean.setListaEstadoGestionCrear(new ArrayList());
            bean.getListaEstadoGestionCrear().add(
                    bean.getHashEstadoGestionValor().get(AuSeguimientoGestion.ESTADO_CANCELADO)
            );
        } catch (Exception exception) {
            bean.addError("Error al abrir cancelar, intentelo nuevamente");
        }
    }

    private void guardarCancelar(AuSeguimientoBean bean) {
        try {
            Maestro maestroEstadoGestion = bean.getHashEstadoGestion().get(bean.getObjetoSeguimientoGestion().getMaeEstadoSeguimientoGestionId());
            Maestro maestroEstado = bean.getHashEstadoSeguimientoValor().get(maestroEstadoGestion.getValor());

            bean.getObjetoSeguimientoGestion().setMaeEstadoSeguimientoGestionCodigo(maestroEstadoGestion.getValor());
            bean.getObjetoSeguimientoGestion().setMaeEstadoSeguimientoGestionValor(maestroEstadoGestion.getNombre());

            bean.getObjetoSeguimientoGestion().setAuSeguimiento(bean.getObjetoSeguimiento());
            bean.getObjetoSeguimientoGestion().setTipo(AuSeguimientoGestion.TIPO_CAMBIO_ESTADO);
            
            //2023-12-01 jyperez completamos los valores de motivo seguimiento
            
            Maestro maestroMotivo = bean.getHashMotivoGestion().get(bean.getObjetoSeguimientoGestion().getMaeMotivoSeguimientoId());
            if (maestroMotivo != null) {
                bean.getObjetoSeguimientoGestion().setMaeMotivoSeguimientoCodigo(maestroMotivo.getValor());
                bean.getObjetoSeguimientoGestion().setMaeMotivoSeguimientoValor(maestroMotivo.getNombre());
            }
            
            bean.auditoriaGuardar(bean.getObjetoSeguimientoGestion());
            getAuSeguimientoGestionRemoto().insertar(bean.getObjetoSeguimientoGestion());
            //cambia estado seguimiento
            if (maestroEstado != null) {

                bean.getObjetoSeguimiento().setMaeEstadoSeguimientoId(maestroEstado.getId());
                bean.getObjetoSeguimiento().setMaeEstadoSeguimientoCodigo(maestroEstado.getValor());
                bean.getObjetoSeguimiento().setMaeEstadoSeguimientoValor(maestroEstado.getNombre());
                bean.auditoriaModificar(bean.getObjetoSeguimiento());

                getAuSeguimientoRemoto().actualizarEstado(bean.getObjetoSeguimiento());
                //actualizar estado Item
                AuAnexo3Item itemAnexo3 = bean.getObjetoSeguimiento().getAuAnexo3ItemsId();
                AuItemBitacora penultima = getAuItemBitacoraRemoto().penultimaBitacora(itemAnexo3.getId());
                //2023-12-13 jyperez realizamos validación del estado actual del item
                int estadoActualAnexo3Item = itemAnexo3.getEstado();
                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_CANCELADO_SEGUIMIENTO);
                bean.auditoriaModificar(itemAnexo3);
                getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                //regresa a estado anterior de seguimiento para ser aprobado
                if (penultima.getEstado() != null) {
                    itemAnexo3.setEstado(penultima.getEstado());
                } else {
                    itemAnexo3.setEstado(itemAnexo3.getEstadoInt(penultima.getDescripcion()));
                }
                //2023-12-13 jyperez validamos si el estado actual es un estado donde no se modifica, entonces actualizamos a ese 
                // y no al estado de la penúltima bitácora
                if (validarEstadoFinal(estadoActualAnexo3Item)) {
                    itemAnexo3.setEstado(estadoActualAnexo3Item);
                } else if(penultima.getEstado() == AuAnexo3Item.ESTADO_CON_SEGUIMIENTO) {
                    itemAnexo3.setEstado(estadoActualAnexo3Item);
                }
                bean.auditoriaModificar(itemAnexo3);
                getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                bean.addMensaje("Se cancelo correctamente");
            }

        } catch (Exception e) {
            bean.addError("Hubo un error al cancelar, intentelo nuevamente");
        }
    }

    private void guardarItemBitacora(AuAnexo3Item item, AuSeguimientoBean bean, int tipo, String descripcion) throws Exception {
        AuItemBitacora bitacora = new AuItemBitacora();
        bitacora.setDescripcion(descripcion);
        bitacora.setTipo(tipo);
        bitacora.setAuAnexo3ItemId(item);
        bitacora.setEstado(item.getEstado());
        bean.auditoriaGuardar(bitacora);
        getAuItemBitacoraRemoto().insertar(bitacora);
    }

    @Override
    public void borrarAdjunto(int id, AuSeguimientoBean bean) {
        try {
            getAuSolicitudAdjuntoRemoto().eliminar(id);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void borrarContacto(AuSeguimientoBean bean) {
        try {
            bean.getObjetoContacto().setBorrado(Boolean.TRUE);
            bean.getObjetoContacto().setUsuarioBorra(bean.getConexion().getUsuario().getUsuarioNombre());
            bean.getObjetoContacto().setTerminalBorra(bean.getConexion().getIp());
            bean.getObjetoContacto().setFechaHoraBorra(new Date());
            getAuSeguimientoAfiliadoContactoRemoto().actualizar(bean.getObjetoContacto());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo borrando el contacto, intentelo nuevamente");
        }
    }

    @Override
    public void motivosGestionEstado(AuSeguimientoBean bean) {
        try {
            bean.setListaMotivoGestionCrear(
                    getMaestroRemoto().consultarListaPorPadre(MaestroTipo.AU_SEGUIMIENTO_MOTIVO,
                            bean.getObjetoSeguimientoGestion().getMaeEstadoSeguimientoGestionId())
            );
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando los motivos del estado, intentelo nuevamente");
        }
    }

    @Override
    public void listarGestiones(AuSeguimientoBean bean) {
        try {
            bean.getParamConsulta(1).setParametroConsulta1(bean.getObjetoSeguimiento().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getAuSeguimientoGestionRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosGestion(getAuSeguimientoGestionRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception ex) {
            bean.addError("Hubo un fallo listando, favor consultar con el administrador");
        }
    }

    @Override
    public void listarIPS(AuSeguimientoBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(2).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }

            bean.getParamConsulta(2).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(2).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(2).setCantidadRegistros(getPrestadorRemoto().consultarCantidadBuscadorSede(bean.getParamConsulta(2)));
            bean.setListaIps(getPrestadorRemoto().consultarListaBuscadorSede(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarContratoDetalle(AuSeguimientoBean bean) {
        try {
            bean.getParamConsulta(3).setCantidadRegistros(getAuAnexo3Remoto().consultarCantidadListaContratos(bean.getParamConsulta(3)));
            bean.setListaContratosDetalles(getAuAnexo3Remoto().consultarListaContratos(bean.getParamConsulta(3)));
            for (CntContratoDetalle contratoDetalle : bean.getListaContratosDetalles()) {
                CntContratoSede contratoSede = getCntContratoSedeRemoto().consultar(contratoDetalle.getCntContratoSede().getId());
                contratoSede.setCntPrestadorSede(
                        getCntPrestadorSedeRemoto().consultar(contratoSede.getCntPrestadorSede().getId())
                );
                contratoDetalle.setCntContratoSede(contratoSede);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void verAnexo3(AuSeguimientoBean bean) {
        try {
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjetoAnexo3().getId()));
            bean.getObjetoAnexo3().getAsegAfiliadoId().setEdad(Util.calcularEdad(bean.getObjetoAnexo3().getAsegAfiliadoId().getFechaNacimiento()));
            bean.setObjetoSeguimiento(new AuSeguimiento());
            bean.getObjetoSeguimiento().setAuSeguimientoAfiliadosId(new AuSeguimientoAfiliado());
            bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId()
                    .setAsegAfiliado(bean.getObjetoAnexo3().getAsegAfiliadoId());
        } catch (Exception e) {
            bean.addError("Error al consultar anexo3");
        }
    }

    public AuSeguimientoGestion castGestionSeguimiento(AuSeguimientoBean bean, String observacion) throws Exception {
        AuSeguimientoGestion objetoSeguimientoGestion = new AuSeguimientoGestion();
        objetoSeguimientoGestion.setAuSeguimiento(bean.getObjetoSeguimiento());
        Maestro maestroEstadoGestion = bean.getHashEstadoGestionValor().get(AuSeguimientoGestion.ESTADO_GESTION_NOTA);
        objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionId(maestroEstadoGestion.getId());
        objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionCodigo(maestroEstadoGestion.getValor());
        objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionValor(maestroEstadoGestion.getNombre());
        Maestro maestroMotivoGestion = bean.getHashMotivoGestionValor().get(AuSeguimientoGestion.MOTIVO_OTRO);
        objetoSeguimientoGestion.setMaeMotivoSeguimientoId(maestroMotivoGestion.getId());
        objetoSeguimientoGestion.setMaeMotivoSeguimientoCodigo(maestroMotivoGestion.getValor());
        objetoSeguimientoGestion.setMaeMotivoSeguimientoValor(maestroMotivoGestion.getNombre());
        objetoSeguimientoGestion.setDescripcion(observacion);
        objetoSeguimientoGestion.setTipo(AuSeguimientoGestion.TIPO_GESTION);
        bean.auditoriaGuardar(objetoSeguimientoGestion);
        return objetoSeguimientoGestion;
    }

    private void validarInformacionObligatoria(AuSeguimientoBean bean, Maestro maestroEstadoGestion) {
        if (maestroEstadoGestion.getValor().equalsIgnoreCase(AuSeguimientoGestion.ESTADO_ASIGNADO_PRESTADOR)
                && bean.getObjetoSeguimiento().getCntPrestadorSedeAsignadoId() == null) {
            bean.addError("IPS: Este campo es obligatorio.");
        } else if (bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getDireccionResidencia() == null) {
            bean.addError("Debe completar información del seguimiento.");
        } else if (bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSeguimientoAfiliadoContactosList().isEmpty()) {
            bean.addError("Debe completar Contactos.");
        } else if (bean.getObjetoSeguimientoServicio() == null) {
            bean.addError("Debe completar información del Servicio.");
        } else if (bean.getObjetoSeguimientoServicio() != null) {
            switch (bean.getObjetoSeguimientoServicio().getMaeSeguimientoServicioCodigo()) {
                case AuSeguimientoServicio.SEGUIMIENTO_OXIGENO:
                    if (bean.getObjetoSeguimientoServicio().getLitros() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_CPAP:
                    if (bean.getObjetoSeguimientoServicio().getPresion() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_BPAP:
                    if (bean.getObjetoSeguimientoServicio().getPresion() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_ASPIRADOR:
                    if (bean.getObjetoSeguimientoServicio().getMaeTipoSondaId() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_NEBULIZADOR:
                    if (bean.getObjetoSeguimientoServicio().getMaeTipoSondaId() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_ASISTENTE_TOS:
                    if (bean.getObjetoSeguimientoServicio().getPresiones() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_INSUMO:
                    if (bean.getObjetoSeguimientoServicio().getMaeTipoInsumoId() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
                case AuSeguimientoServicio.SEGUIMIENTO_OTRO:
                    if (bean.getObjetoSeguimientoServicio().getObservaciones() == null) {
                        bean.addError("Debe completar información del Servicio.");
                    }
                    break;
            }
        }

        if ((bean.getObjetoSeguimiento().getMaeAcompananteTipoDocumentoId() != null && bean.getObjetoSeguimiento().getMaeAcompananteTipoDocumentoId() != 0)
                || (bean.getObjetoSeguimiento().getAcompananteDocumento() != null && !bean.getObjetoSeguimiento().getAcompananteDocumento().isBlank())
                || (bean.getObjetoSeguimiento().getAcompanantePrimerNombre() != null && !bean.getObjetoSeguimiento().getAcompanantePrimerNombre().isBlank())
                || (bean.getObjetoSeguimiento().getAcompanantePrimerApellido() != null && !bean.getObjetoSeguimiento().getAcompanantePrimerApellido().isBlank())) {
            if (bean.getObjetoSeguimiento().getMaeAcompananteTipoDocumentoId() == null || bean.getObjetoSeguimiento().getMaeAcompananteTipoDocumentoId() == 0) {
                bean.addError("Tipo documento Acompañante: Este campo es obligatorio.");
            }
            if (bean.getObjetoSeguimiento().getAcompananteDocumento() == null
                    || bean.getObjetoSeguimiento().getAcompananteDocumento().isBlank()) {
                bean.addError("Número documento Acompañante: Este campo es obligatorio.");
            }
            if (bean.getObjetoSeguimiento().getAcompanantePrimerNombre() == null
                    || bean.getObjetoSeguimiento().getAcompanantePrimerNombre().isBlank()) {
                bean.addError("Primer nombre Acompañante: Este campo es obligatorio.");
            }
            if (bean.getObjetoSeguimiento().getAcompanantePrimerApellido() == null
                    || bean.getObjetoSeguimiento().getAcompanantePrimerApellido().isBlank()) {
                bean.addError("Primer apellido Acompañante: Este campo es obligatorio.");
            }
        }

        if (bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getEnergiaPrepagada() != null
                && bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getEnergiaPrepagada()) {
            if (bean.getObjetoSeguimiento().getAuSeguimientoAfiliadosId().getAuSolicitudAdjuntosList().isEmpty()) {
                bean.addError("Debe completar Adjuntos energia prepagada");
            }
        }
    }

    private void guardarAdjunto(AuSolicitudAdjunto adjunto) {
        try {
            boolean error = false;
            String ruta = adjunto.getRuta();
            if (ruta == null || ruta.isEmpty()) {
                error = true;
            }
            if (error) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            String tipoDocumento = adjunto.getMaeTipoArchivoCodigo() == null ? "0" : adjunto.getMaeTipoArchivoCodigo();
            nombreArchivo.append(tipoDocumento)
                    .append("_")
                    .append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            adjunto.setArchivo(nombreArchivo.append(adjunto.getExtension()).toString());
            if (adjunto.getMaeTipoArchivoId() != null) {
                Maestro maeTipoAdjunto = getMaestroRemoto().consultar(adjunto.getMaeTipoArchivoId());
                adjunto.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
                adjunto.setMaeTipoArchivoId(maeTipoAdjunto.getId());
                adjunto.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
            }

//            adjunto.setRuta(ruta);
            File archivo = new File(ruta, adjunto.getArchivo());
            OutputStream destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(adjunto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            adjunto.setAdjuntoStream(null);
            adjunto.setId(getAuSolicitudAdjuntoRemoto().insertar(adjunto));
        } catch (Exception e) {
        }
    }
    
    private void listarAdjuntosGestion(AuSeguimientoBean bean) {
        try {
            /*
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }*/

            bean.getParamConsultaSeguimientoGestion().setCantidadRegistros(getAuSolicitudAdjuntoRemoto().consultarCantidadLista(bean.getParamConsultaSeguimientoGestion()));
            bean.getParamConsultaSeguimientoGestion().setRegistrosPagina(30);
            bean.setRegistrosSeguimientoGestionAdjuntos(getAuSolicitudAdjuntoRemoto().consultarLista(bean.getParamConsultaSeguimientoGestion()));
        } catch (Exception ex) {
            bean.addError("Hubo un fallo listando las gestiones del seguimiento, favor consultar con el administrador");
        }
        
    }
    
    private void guardarAdjuntosGestion(AuSeguimientoBean bean) {
        try{
            //obtenemos los adjuntos de la lista del objeto de auSeguimientoGestion
            //adjuntos del seguimiento de la gestión
            if (bean.getObjetoSeguimientoGestion().getAuSolicitudAdjuntosList() != null && !bean.getObjetoSeguimientoGestion().getAuSolicitudAdjuntosList().isEmpty()) {
                for (AuSolicitudAdjunto adjunto : bean.getObjetoSeguimientoGestion().getAuSolicitudAdjuntosList()) {
                    if (adjunto.getId() == null) {
                        bean.auditoriaGuardar(adjunto);
                        adjunto.setRuta(PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_SEGUIMIENTOS));
                        guardarAdjunto(adjunto);
                    }
                }
                bean.addMensaje("archivo adjuntado a la gestión correctamente");
            } else {
                bean.addMensaje("No se encontraron archivos adjuntos");
            }
        } catch (Exception ex) {
            bean.addError("Ocurrió un error adjuntando el archivo a la gestión. Mensaje: " + ex.getMessage());
        }
        
    }
    
    @Override
    public void validarEstadoCancelar(AuSeguimientoBean bean) {
        try {
            Maestro mae = bean.getHashEstadoSeguimiento().get(bean.getObjetoSeguimiento().getMaeEstadoSeguimientoId());
            if (mae.contieneAccion(MaestroAccion.AU_SEGUIMIENTO_ESTADO_VALIDA_CANCELA)) {
                bean.setEstadoCancelarValido(true);
            } else {
                bean.setEstadoCancelarValido(false);
            }
            
        } catch (Exception ex) {
            bean.setEstadoCancelarValido(false);
        }
    }

}
