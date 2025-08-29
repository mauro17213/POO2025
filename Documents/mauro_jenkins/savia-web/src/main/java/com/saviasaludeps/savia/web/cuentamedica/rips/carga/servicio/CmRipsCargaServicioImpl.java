package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsInhabilitado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDevolucionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsInhabilitadoRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmRipsCargaBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CmRipsCargaServicioImpl extends AccionesBO implements CmRipsCargaServicioIface {

    private CmRipsCargaRemoto getCmRipsCargaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsCargaServicio", CmRipsCargaRemoto.class.getName());
        return (CmRipsCargaRemoto) object;
    }

    private CntContratoSedeRemoto getCntContratoSedeRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CntContratoSedeServicio", CntContratoSedeRemoto.class.getName());
        return (CntContratoSedeRemoto) object;
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private CmAuditoriaDevolucionRemoto getAuditoriaDevolucionRemoto() throws Exception {
        return (CmAuditoriaDevolucionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDevolucionServicio", CmAuditoriaDevolucionRemoto.class.getName());
    }

    private CmGrupoUsuarioRemoto getCmGrupoUsuarioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoUsuarioServicio", CmGrupoUsuarioRemoto.class.getName());
        return (CmGrupoUsuarioRemoto) object;
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CmRipsInhabilitadoRemoto getCmRipsInhabilitadoRemoto() throws Exception {
        return (CmRipsInhabilitadoRemoto) RemotoEJB.getEJBRemoto("CmRipsInhabilitadoServicio", CmRipsInhabilitadoRemoto.class.getName());
    }
    
     private CntContratoRemoto getContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto(("CntContratoServicio"), CntContratoRemoto.class.getName());
    }

//    private SingletonHiloRemoto getSingletonRemoto() throws Exception {
//        return RemotoEJB.getGenericoSingletonRemoto();
//    }

    @Override
    public void Accion(CmRipsCargaBean bean) {
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
                            verListaErrores(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verSucesos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verContratos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            verAdjuntos(bean);
                            break;
                         case CmRipsCargaBean.ACCION_VALIDAR_CONTRATOS_VIGENTES:
                            validarExistenciaContratosVigentes(bean);
                            break;
                    }
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
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            gestionar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            enviarAuditoria(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    rechazar(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    verDevoluciones(bean);
                    break;
                 case Url.ACCION_ADICIONAL_5:
                    guardarEstadoEnColaRips(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(CmRipsCargaBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaUbicacion(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicacion(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setHashUbicacionDepartamento( UbicacionSingle.getInstance().getHashDepartamentos());
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    @Override
    public void listar(CmRipsCargaBean bean) {
        try {
            //SI NO TIENE HABILITADO VER TODO O NO ES CUENTAS MEDICAS REQUIERE 
            if (bean.isAccionAdicional1() == false && bean.isAccionAdicional3() == false) {
                int idPrestador = bean.getConexion().getEmpresa().getCntPrestador().getId();
                if (idPrestador > 0) {
                    bean.getParamConsulta().setParametroConsulta3(idPrestador);
                } else {
                    bean.addError("Error su usuario no cuenta con los permisos adecuados");
                }
            } else {
            }
            if (bean.isError() == false) {
                bean.getParamConsulta().setCantidadRegistros(getCmRipsCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
                bean.setRegistros(getCmRipsCargaRemoto().consultarLista(bean.getParamConsulta()));
            }
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    @Override
    public void cargar(CmRipsCargaBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ver(CmRipsCargaBean bean) {
        try {
            bean.setObjeto(getCmRipsCargaRemoto().consultar(bean.getObjeto().getId()));
            asignarCntContratoCompleto(bean);
        } catch (Exception ex) {
            bean.addError("Error :" + ex.toString());
        }
    }

    @Override
    public void verListaErrores(CmRipsCargaBean bean) {
        try {
            bean.setObjeto(getCmRipsCargaRemoto().consultarErroresEstructura(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError("Error :" + ex.toString());
        }
    }

    private void verContratos(CmRipsCargaBean bean) {
        try {
            if (bean.getObjeto().getGnPrestadorSede() == null) {
                bean.addError("Seleccione primero una IPS");
            } else {
                organizarFiltrosBusqueda(bean);
               
                bean.getParamConsultaContratos().setCantidadRegistros(getCntContratoSedeRemoto().consultarCantidadListaBuscador(bean.getParamConsultaContratos()));
                bean.setListaContratos(getCntContratoSedeRemoto().consultarListaBuscador(bean.getParamConsultaContratos()));
            }
        } catch (Exception ex) {
            bean.addError("Error :" + ex.toString());
        }
    }

    private void organizarFiltrosBusqueda(CmRipsCargaBean bean) {
        if (bean.getCntContratoSede() != null) {
            
             Map<String, Object> filtros = bean.getParamConsultaContratos().getFiltros();
            
            if (bean.getParamConsultaContratos().getFiltros() == null) {
                filtros = new HashMap<>();
                bean.getParamConsultaContratos().setFiltros(filtros);
            }
            
            if ( bean.getCntContratoSede().getMaeModalidadContratoCodigo() != null &&
                    !"".equals(bean.getCntContratoSede().getMaeModalidadContratoCodigo())) {
                filtros.put("cntContrato.contrato", bean.getCntContratoSede().getMaeModalidadContratoCodigo());
            }

            filtros.put("activo", true);          
            bean.getParamConsultaContratos().setParametroConsulta3(bean.getObjeto().getGnPrestadorSede().getId());
            String nivel = (String) filtros.get("cntContratoSede.nivelAtencion");
            String nivelAtencion =  Optional.ofNullable(nivel).orElse("0");  
            if(!nivelAtencion.equals("0")){
               int atencion =  Integer.parseInt(nivelAtencion);
                switch(atencion){
                    case 1:
                        filtros.put("aplicaSubsidiado", 1);
                        break;
                    case 2:
                        filtros.put("aplicaContribuitivo", 1);
                        break;
                    case 3:
                        filtros.put("aplicaSubsidiado", 1);
                        filtros.put("aplicaContribuitivo", 1);
                        break;
                }
            }
            
            if(bean.getCntContratoSede().getMaeModalidadContratoId() > 0){
                filtros.put("maeModalidadContratoId", bean.getCntContratoSede().getMaeModalidadContratoId());
            } 
            
            if (bean.getCntContratoSede().getFechaInicio() != null) {
                LocalDate date = bean.getCntContratoSede().getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                filtros.put("cntContrato.fechaInicio", date);
            }
            if (bean.getCntContratoSede().getFechaFin() != null) {
                LocalDate date = bean.getCntContratoSede().getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                filtros.put("cntContrato.fechaFin",date);
            }           
        }
    }

    private void verDevoluciones(CmRipsCargaBean bean) {
        try {
            if (bean.isAccionAdicional1() == false && bean.isAccionAdicional3() == false) {
                /*
                String codigoHabilitacion = bean.getConexion().getEmpresa().getCodigoHabilitacion();
                if (codigoHabilitacion != null) {
                    bean.getParamConsultaDevoluciones().setParametroConsulta3(codigoHabilitacion);
                } else {
                    bean.addError("Su usuario no cuenta con un código de habilitación");
                }*/
            }
            if (bean.isError() == false) {
                bean.getParamConsultaDevoluciones().setCantidadRegistros(getAuditoriaDevolucionRemoto().consultarCantidadLista(bean.getParamConsultaDevoluciones()));
                if (bean.getParamConsulta().getParametroConsulta1() != null) {
                    bean.getParamConsultaDevoluciones().setParametroConsulta1(bean.getParamConsulta().getParametroConsulta1());
                }
                if (bean.getParamConsulta().getParametroConsulta2() != null) {
                    bean.getParamConsultaDevoluciones().setParametroConsulta2(bean.getParamConsulta().getParametroConsulta2());
                }
                bean.setListaAuditoriaDevoluciones(getAuditoriaDevolucionRemoto().consultarLista(bean.getParamConsultaDevoluciones()));
            }
        } catch (Exception ex) {
            bean.addError("Error :" + ex.toString());
        }
    }

    @Override
    public void gestionar(CmRipsCargaBean bean) {
        try {
            bean.setObjeto(getCmRipsCargaRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getErrores().isEmpty()) {
                bean.setHashRechazos(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.RIPS_ESTADO_RECHAZO));
                asignarCntContratoCompleto(bean);             
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    @Override
    public void enviarAuditoria(CmRipsCargaBean bean) {
        try {
            //VALIDAR ESTADO ANTES DE ENVIAR A AUDITORIA
            CmRipsCarga carga = getCmRipsCargaRemoto().consultar(bean.getObjeto().getId());
            if (carga.getEstado() != CmRipsCarga.ESTADO_VALIDADO) {
                bean.addError("Ha ocurrido un error la carga ya no se encuentra en estado VALIDADO");
            }
            if (bean.isError() == false) {
                //VALIDAR GRUPO DE LA IPS
                CmGrupoUsuario cmGrupoUsuario = getCmGrupoUsuarioRemoto()
                        .consultarUsuarioLider(CmGrupoUsuario.TIPO_LIDER,
                                bean.getObjeto().getGnPrestadorSede().getCntPrestador().getNumeroDocumento(),
                                bean.getObjeto().getCamaFija(),
                                bean.getObjeto().getPbs()
                        );
                if (cmGrupoUsuario != null) {
                    carga.setUsuarioAudita(bean.getConexion().getUsuario().getUsuarioNombre());
                    carga.setEstado(CmRipsCarga.EN_COLA_AUDITORIA);
                    getCmRipsCargaRemoto().actualizar(carga);
                    bean.addMensaje("Se ha programado la creación y notificación de Factura, por favor consulte más tarde para ver el estado");
                } else {
                    bean.addMensaje("No se encuentra el grupo o el lider técnico para asignar las Facturas");
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void crear(CmRipsCargaBean bean) {
        try {
            bean.setObjetoPrestador(new CntPrestador());
            //SI NO TIENE PERMISO PARA VER TODAS LAS IPS SETEAR EL OBJETO PRESTADOR 
            if (bean.isAccionAdicional1() == false && bean.isAccionAdicional3() == false) {
                //CARGAR IPS
                if (bean.getConexion().getEmpresa().getCntPrestador().getId() != null) {
                    bean.setObjetoPrestador(getPrestadorRemoto()
                            .consultar(
                                    bean.getConexion().getEmpresa().getCntPrestador().getId())
                    );
                } else if (bean.isAccionAdicional3() == false) {
                    bean.addError("Error su usuario no tiene un codigo de habilitación");
                }
                if (bean.getObjetoPrestador() != null) {
                    //bean.setListaPrestadores(bean.getObjetoPrestador().getListaPrestadorSedes());
                } else {
                    bean.addError("Error no se encontraron sedes para el prestador");
                }
            }
            if (bean.getErrores().isEmpty()) {
                bean.setHashRegimenes(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGIMEN));
                bean.setHashModalidadContratos(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CNT_MODALIDAD));
                bean.setHashRegionales(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGION));
                bean.setListaAdjuntos(new ArrayList<>());
                bean.setObjetoPrestadorSede(new CntPrestadorSede());
                bean.setObjeto(new CmRipsCarga());
                bean.setCntContratoSede(new CntContratoSede());
                bean.setListaAdjuntos(new ArrayList());
            }
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    private void guardar(CmRipsCargaBean bean) {
        try {
            int[] estadosProceso = {CmRipsCarga.EN_COLA,
                CmRipsCarga.ESTADO_EN_VALIDACION_ESTRUCTURA,
                CmRipsCarga.ESTADO_ESTRUCTURA_VALIDA,
                CmRipsCarga.ESTADO_VALIDACION_NORMATIVA
            };
            bean.auditoriaGuardar(bean.getObjeto());
            
            //REGIMEN
            if (bean.getObjeto().getMaeRegimenId() == null) {
                bean.addError(("Ocurrio un error el regimen se encuentra vacio"));
            }
            for (Map.Entry<Integer, Maestro> mae : bean.getHashRegimenes().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMaeRegimenId())) {
                    bean.getObjeto().setMaeRegimenCodigo(mae.getValue().getValor());
                    bean.getObjeto().setMaeRegimenValor(mae.getValue().getNombre());
                }
            }
            //MAE REGIONALES
            bean.getObjeto().setMaeRegionalCodigo("1");
            bean.getObjeto().setMaeRegionalId(1);
            bean.getObjeto().setMaeRegionalValor("Nacional");
            //MAE MODALIDAD DE CONTRATOS
            for (Map.Entry<Integer, Maestro> mae : bean.getHashModalidadContratos().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMaeContratoModalidadId())) {
                    bean.getObjeto().setMaeContratoModalidadCodigo(mae.getValue().getValor());
                    bean.getObjeto().setMaeContratoModalidadValor(mae.getValue().getNombre());
                }
            }
            //VALIDAR IPS Y LIMITAR A 1 CARGA POR IPS
            if (bean.getObjeto().getGnPrestadorSede() == null) {
                bean.addError("Por favor seleccione la sede del Prestador");
            } else {
                if (!getCmRipsCargaRemoto().consultarSedeEnProceso(estadosProceso, bean.getObjeto().getGnPrestadorSede().getId()).isEmpty()) {
                    bean.addError("Tiene una carga en proceso por favor espere a que finalice e intente de nuevo.");
                }
            }
            //MODALIDAD DE CONTRATO OBLIGATORIA
            if (bean.getObjeto().getMaeContratoModalidadCodigo() == null) {
                bean.addError("Por favor ingrese la modalidad del contrato");
            } else {
                //VALIDAR CIERRE DE MODALIDAD
                ParamConsulta paramConsulta = new ParamConsulta();
                paramConsulta.setParametroConsulta1(new Date());
                paramConsulta.setParametroConsulta2(bean.getObjeto().getMaeContratoModalidadId());
                paramConsulta.setParametroConsulta3(bean.getObjeto().getCoberturaCierre());
                
                List<CmRipsInhabilitado> listaInhabilitados
                        = getCmRipsInhabilitadoRemoto().consultarModalidadInhabilitada(paramConsulta);
                for (CmRipsInhabilitado inhabilitado : listaInhabilitados) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    bean.addError(
                            "La modalidad "
                            + bean.getObjeto().getMaeContratoModalidadValor() + " "
                            + " por motivo de " + inhabilitado.getMotivo() + " "
                            + " y cobertura " + inhabilitado.getCoberturaCierreStr() + " "        
                            + " desde el " + formatter.format(inhabilitado.getFechaHoraDesde()) + " "
                            + " hasta el " + formatter.format(inhabilitado.getFechaHoraHasta()) + " "
                            + " esta cerrada. "
                    );
                }
            }

            if (bean.isError() == false) {
                validarNumeroCuentaPermitido(bean);
            }
            
             if (bean.isError() == false) {
                 validarDeclaracionRegistrosArchivoCt(bean);
             }
             
             if (bean.isError() == false) {
                 validarNumeroFacturadoUnicoPorNit(bean);
             }

            if (bean.isError() == false) {
                //TIPO CONTRATO
                if (bean.getObjeto().getCntContratoSede() != null) {
                    bean.getObjeto().setCntContrato(bean.getObjeto().getCntContratoSede().getCntContrato());
                    if (bean.getObjeto().getCntContratoSede().getAplicaContribuitivo() != null) {
                        if (bean.getObjeto().getCntContratoSede().getAplicaContribuitivo()) {
                            bean.getObjeto().setCntTipoContratoId(CntContratoSede.TIPO_CONTRATO_CONTRIBUTIVO);
                            bean.getObjeto().setContrato(bean.getObjeto().getCntContratoSede().getCntContrato().getContrato());
                        }
                    }
                    if (bean.getObjeto().getCntContratoSede().getAplicaSubsidiado() != null) {
                        if (bean.getObjeto().getCntContratoSede().getAplicaSubsidiado()) {
                            bean.getObjeto().setCntTipoContratoId(CntContratoSede.TIPO_CONTRATO_SUBSIDIADO);
                            bean.getObjeto().setContrato(bean.getObjeto().getCntContratoSede().getCntContrato().getContrato());
                        }
                    }
                }
                bean.getObjeto().setEmpresa(new Empresa(bean.getConexion().getEmpresa().getId()));
                bean.getObjeto().setEstado(CmRipsCarga.EN_COLA);
                bean.getObjeto().setFechaHoraInicio(new Date());
                bean.getObjeto().setTiempo(0);
                bean.getObjeto().setId(getCmRipsCargaRemoto().insertar(bean.getObjeto()));
                //Guardar control de estados
                CmRipsCargaEstado cmRipsCargaEstado = new CmRipsCargaEstado();
                cmRipsCargaEstado.setCmRipsCarga(new CmRipsCarga(bean.getObjeto().getId()));
                cmRipsCargaEstado.setEstado(bean.getObjeto().getEstado());
                cmRipsCargaEstado.setFechaHoraCrea(new Date());
                cmRipsCargaEstado.setTerminalCrea(bean.getObjeto().getTerminalCrea());
                cmRipsCargaEstado.setUsuarioCrea(bean.getObjeto().getUsuarioCrea());
                getCmRipsCargaRemoto().insertarCargaEstados(cmRipsCargaEstado, CmRipsCarga.TABLA_CARGA);
                //INSERTAR ADJUNTOS
                for (CmRipsCargaAnexo adjuntoNuevo : bean.getListaAdjuntos()) {
                    if (adjuntoNuevo.getIdInsertar() != null && adjuntoNuevo.getIdInsertar() != 0) {
                        com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo adjunto = new CmRipsCargaAnexo();
                        adjunto.setCmRipsCarga(new CmRipsCarga(bean.getObjeto().getId()));
                        adjunto.setArchivoNombre(adjuntoNuevo.getArchivoNombre());
                        adjunto.setMaeTipoArchivoId(adjuntoNuevo.getMaeTipoArchivoId());
                        adjunto.setArchivoNombreOriginal(adjuntoNuevo.getArchivoNombreOriginal());
                        adjunto.setArchivoRuta(adjuntoNuevo.getArchivoRuta());
                        adjunto.setInputStream(adjuntoNuevo.getInputStream());
                        adjunto.setIdInsertar(adjuntoNuevo.getIdInsertar());
                        adjunto.setFechaHoraCrea(adjuntoNuevo.getFechaHoraCrea());
                        adjunto.getGenerarArchivo();
                        //GUARDAR ARCHIVO
                        String crearArchivo = crearArchivo(adjunto);
                        if (crearArchivo.startsWith("Error") == false) {
                            adjunto.setExiste(true);
                            adjunto.setUsuarioCrea(adjuntoNuevo.getUsuarioCrea());
                            adjunto.setTerminalCrea(adjuntoNuevo.getTerminalCrea());
                            adjunto.setFechaHoraCrea(adjuntoNuevo.getFechaHoraCrea());
                            getCmRipsCargaRemoto().insertarAdjunto(adjunto);
                        }
                    }
                }
                //se devuelve la lista a null para GC
                bean.setListaAdjuntos(null);
                bean.getObjeto().setEstado(CmRipsCarga.EN_COLA);
                getCmRipsCargaRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Cargue iniciado por favor consulte de nuevo en un momento para validar el estado");
            }

        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
    
     private void guardarEstadoEnColaRips(CmRipsCargaBean bean) {
        try {
            CmRipsCarga carga  =  getCmRipsCargaRemoto().consultar(bean.getObjeto().getId());         
            boolean estadoValido =  validarEstadoDiferenteColaAuditoria(carga, bean);
            if(estadoValido){
                eliminarRegistrosTemporalesCarga(carga.getId());
                eliminarRegistrosFijosCarga(carga.getId());
                carga.setEstado(CmRipsCarga.EN_COLA);
                getCmRipsCargaRemoto().actualizar(carga);
            }           
        } catch (Exception ex) {
            bean.addError("Error :" + ex.toString());
        }
    }

    private boolean validarEstadoDiferenteColaAuditoria(CmRipsCarga carga, CmRipsCargaBean bean) {
        boolean esEstadoValido = true;
        if(carga.getEstado() == CmRipsCarga.EN_COLA_AUDITORIA ||
           carga.getEstado() == CmRipsCarga.EN_AUDITORIA ||  
           carga.getEstado() == CmRipsCarga.ESTADO_RECHAZADO ){
            bean.addError("La carga tiene el estado ( "+ carga.getEstadoStr()+" ) la cual no se puede cambiar de estado.");
            esEstadoValido = false;
        }
        return esEstadoValido;
    }

    private void verSucesos(CmRipsCargaBean bean) {
        try {
            bean.getParamConsultaSucesos().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaSucesos().setCantidadRegistros(getCmRipsCargaRemoto()
                    .consultarCantidadListaSucesos(bean.getParamConsultaSucesos()));
            bean.setListaCmRipsSucesos(getCmRipsCargaRemoto().consultarListaSucesos(bean.getParamConsultaSucesos()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private boolean validarNumeroCuentaPermitido(CmRipsCargaBean bean) {
        boolean numeroValido = true;
        try {
            Integer numeroCuenta = bean.getObjeto().getNumeroCuenta();
            CntPrestadorSede prestadorSede = bean.getObjeto().getGnPrestadorSede();
            CntPrestador prestador = prestadorSede != null && prestadorSede.getCntPrestador() != null
                    ? prestadorSede.getCntPrestador() : new CntPrestador();
            String nit = prestador.getNumeroDocumento();

            if (numeroCuenta != null) {
                ParamConsulta param = new ParamConsulta();
                param.setParametroConsulta1(numeroCuenta);
                param.setParametroConsulta2(nit);
                CmRipsCarga carga = getCmRipsCargaRemoto().consultarPorParametros(param);

                if (carga != null && carga.getId() != null) {
                    int estado = carga.getEstado();
                    numeroValido = CmRipsCarga.ESTADO_VALIDADO != estado && CmRipsCarga.EN_AUDITORIA != estado;
                }
                if (!numeroValido) {
                    bean.addError("El número de cuenta se encuentra actualmente en uso con el consecutivo " + carga.getId() + " y estado " + carga.getEstadoStr());
                }
            }
        } catch (Exception e) {
            numeroValido = true;
        }
        return numeroValido;
    }

    @Override
    public void rechazar(CmRipsCargaBean bean) {
        try {
            bean.getObjeto().setEstado(CmRipsCarga.ESTADO_RECHAZADO);

            for (Map.Entry<Integer, Maestro> mae : bean.getHashRechazos().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMaeRechazoId())) {
                    bean.getObjeto().setMaeRechazoCodigo(mae.getValue().getValor());
                    bean.getObjeto().setMaeRechazoValor(mae.getValue().getNombre());
                }
            }

            getCmRipsCargaRemoto().actualizarEstadoRechazo(bean.getObjeto());
            bean.getObjeto().setObservacionRechazo("");
            bean.addMensaje("Se ha rechazado la carga");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    @Override
    public void listarPrestadoresYSedes(CmRipsCargaBean bean) {
        try {
            if (bean.isAccionAdicional1() == false || bean.isAccionAdicional3() == false) {
                if (bean.getConexion().getEmpresa().getCntPrestador() == null) {
                    bean.addError("Su usuario no se encuentra asociado a un prestador");
                } else {
                    bean.getParamConsultaPrestador().setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
                }
            }
            if (bean.isError() == false) {
                bean.getParamConsultaPrestador().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsultaPrestador()));
                bean.setListaPrestadorSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsultaPrestador()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private String crearArchivo(com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo adjunto) {
        String respuesta;
        try {
            copyInputStreamToFile(adjunto.getInputStream(), new File(adjunto.getArchivoRuta(), adjunto.getArchivoNombre()));
            respuesta = "Rips guardados";
        } catch (IOException e) {
            respuesta = "Error: " + e.toString();
        } finally {
            adjunto.setInputStream(null);
        }
        return respuesta;
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

    private void editar(CmRipsCargaBean bean) {
        try {
            CmRipsCarga carga = getCmRipsCargaRemoto().consultar(bean.getObjeto().getId());
            if (carga.getEstado() == CmRipsCarga.ESTADO_VALIDADO) {
                bean.setObjeto(carga);
                bean.setObjetoPrestador(carga.getGnPrestadorSede().getCntPrestador());
                bean.setObjetoPrestadorSede(carga.getGnPrestadorSede());
            } else {
                bean.addError("Esta carga no se encuentra en estado VALIDADO, por favor refresque el listado");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void eliminarRegistrosTemporalesCarga(int idRipCarga) throws Exception {
         getCmRipsCargaRemoto().eliminarCmRipsCargaAcConsultas(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAdServiciosAgrupados(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAfFacturas(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAhHospitalizaciones(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAmMedicamentos(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAnRecienNacidos(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaApProcedimientos(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAtOtrosServicios(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaAuUrgencias(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaCtControl(idRipCarga);
         getCmRipsCargaRemoto().eliminarCmRipsCargaUsUsuarios(idRipCarga);
    }
    
    private void eliminarRegistrosFijosCarga(int idRipCarga) throws Exception {
        getCmRipsCargaRemoto().eliminarCmRipsAcConsultas(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAdServiciosAgrupados(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAfFacturas(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAhHospitalizaciones(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAmMedicamentos(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAnRecienNacidos(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsApProcedimientos(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAtOtrosServicios(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsAuUrgencias(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsCtControl(idRipCarga);
        getCmRipsCargaRemoto().eliminarCmRipsUsUsuarios(idRipCarga);
    }

    private void modificar(CmRipsCargaBean bean) {
        try {
            getCmRipsCargaRemoto().actualizarPbsCamaFija(bean.getObjeto());
            bean.addMensaje("Datos de carga actualizados");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verAdjuntos(CmRipsCargaBean bean) {
        try {
            List<com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo> listaAdjuntos = getCmRipsCargaRemoto().consultarAnexos(bean.getObjeto().getId());
            if (listaAdjuntos.size() > 0) {
                bean.setListaAnexos(listaAdjuntos);
            }
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
    
    public boolean validarDeclaracionRegistrosArchivoCt(CmRipsCargaBean bean) {
        Map<String, Integer> hashCantidadLineasCt = obtenerCantidadLineasDeclaradasCt(bean.getListaAdjuntos());
        Map<String, Integer> hashCantidadLineasPorArchivo = obtenerCantidadLineasPorArchivo(bean.getListaAdjuntos());
        for (Map.Entry<String, Integer> hashLineaArchivoCt : hashCantidadLineasCt.entrySet()) {
            String archivo = hashLineaArchivoCt.getKey();
            Integer cantidad = hashLineaArchivoCt.getValue();
            if (!Objects.equals(hashCantidadLineasPorArchivo.get(archivo), cantidad)) {
                bean.addError("El archivo " + archivo + " tiene una cantidad de registros (" + hashCantidadLineasPorArchivo.get(archivo) + ") diferente a la declarada en el archivo CT (" + cantidad + ") ");
            }
        }
        return !hashCantidadLineasCt.isEmpty();
    }
    
    public boolean validarNumeroFacturadoUnicoPorNit(CmRipsCargaBean bean) {
        boolean hayNumerosFacturasdosUnicos = true;
        int CAMPO_NIT = 0;
        int CAMPO_NUMERO_FACTURADO = 1 ;
        
         Map<String,Integer> facturasRepetidas = buscarNumeroFacturadoRepetidoPorNit(bean.getListaAdjuntos());
         
         if(facturasRepetidas.isEmpty()){
            return hayNumerosFacturasdosUnicos ;   
         }else{
             for (Map.Entry<String, Integer> facturaRepetida : facturasRepetidas.entrySet()) {
                 String key = facturaRepetida.getKey();
                 String[] datosFactura =  key.split("-");
                 bean.addError("El archivo Af contiene facturas repetidas, nit ( "+datosFactura[CAMPO_NIT]+" )"
                            + ", número facturado ( "+datosFactura[CAMPO_NUMERO_FACTURADO]+" )");
                 hayNumerosFacturasdosUnicos = false;         
             }
         }
        return hayNumerosFacturasdosUnicos;
    }

    private Map<String, Integer>  obtenerCantidadLineasPorArchivo(List<CmRipsCargaAnexo> listaAdjuntos) {
        BufferedReader br = null;
        Map<String, Integer> hashArchivoLineas = new HashMap<>();           
        try {         
            for (CmRipsCargaAnexo adjuntoAnexo : listaAdjuntos) {

                InputStream inputEntrada = adjuntoAnexo.getInputStream();
                byte[] buffer = inputEntrada.readAllBytes(); // leer todo el contenido del InputStream en un arreglo de bytes
                InputStream newInput = new ByteArrayInputStream(buffer);
                InputStream newInputNuevo = new ByteArrayInputStream(buffer);
                br = new BufferedReader(new InputStreamReader(newInput));
                String linea;
                int numeroLinea = 0;
                while ((linea = br.readLine()) != null) {
                    if (!"".equals(linea)) {
                        numeroLinea++;
                    }
                } 
                adjuntoAnexo.setInputStream(newInputNuevo);
                hashArchivoLineas.put(adjuntoAnexo.getTipoStr(), numeroLinea);
            }         
        } catch (IOException ioe) {
          hashArchivoLineas = new HashMap<>();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
            }
        }   
        return hashArchivoLineas;
    }
    
    private void validarExistenciaContratosVigentes(CmRipsCargaBean bean) {
        try {
            bean.getObjeto().setTieneContratosActivos(false);
            ParamConsulta parametro = new ParamConsulta();   
            parametro.setParametroConsulta1( bean.getObjeto().getGnPrestadorSede().getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateFechaFinString = sdf.format(bean.getObjeto().getFechaPrestacion());
            Date fechaPrestacion = sdf.parse(dateFechaFinString);
            parametro.setParametroConsulta2("VIGENTE");
            parametro.setParametroConsulta3(fechaPrestacion);
            int cantidadContratos = getCmRipsCargaRemoto().consultarCantidadContratos(parametro);
            if(cantidadContratos>0){
                 bean.getObjeto().setTieneContratosActivos(true);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private Map<String, Integer> obtenerCantidadLineasDeclaradasCt(List<CmRipsCargaAnexo> listaAdjuntos) {
        BufferedReader br = null;
        Map<String, Integer> hashArchivoLineas = new HashMap<>();
        int CANTIDAD_COLUMNAS_ESPERADAS = 4;
        int COLUMNAS_NOMBRE_ARCHIVO = 2;
        int COLUMNAS_CANTIDAD_REGISTROS = 3;
        try {
            for (CmRipsCargaAnexo adjuntoAnexo : listaAdjuntos) {

                if (adjuntoAnexo.getTipoStr() == null ? CmRipsCargaAnexo.CT == null : adjuntoAnexo.getTipoStr().equals(CmRipsCargaAnexo.CT)) {
 
                    InputStream inputEntrada = adjuntoAnexo.getInputStream();
                    byte[] buffer = inputEntrada.readAllBytes(); // leer todo el contenido del InputStream en un arreglo de bytes
                    InputStream newInput = new ByteArrayInputStream(buffer);
                    InputStream newInputNuevo = new ByteArrayInputStream(buffer);

                    br = new BufferedReader(new InputStreamReader(newInput));
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (!"".equals(linea)) {
                            String[] informacionFila = linea.split(",");
                            if (CANTIDAD_COLUMNAS_ESPERADAS ==  informacionFila.length ) {
                                String prefijo = informacionFila[COLUMNAS_NOMBRE_ARCHIVO].substring(0, 2);
                                hashArchivoLineas.put(prefijo, Integer.valueOf(informacionFila[COLUMNAS_CANTIDAD_REGISTROS]));
                            }

                        }
                    }
                    adjuntoAnexo.setInputStream(newInputNuevo);
                }
            }
        } catch (IOException ioe) {
            hashArchivoLineas = new HashMap<>();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
            }
        }
        return hashArchivoLineas;
    }
    
     private Map<String, Integer> buscarNumeroFacturadoRepetidoPorNit(List<CmRipsCargaAnexo> listaAdjuntos) {
         BufferedReader br = null;
         Map<String, Integer> hashArchivoLineas = new HashMap<>();
         Map<String, Integer> hashFacturaRepetidas = new HashMap<>();
         int COLUMNAS_NUMERO_NIT = 3;
         int COLUMNAS_NUMERO_FACTURADO = 4;
         try {
             for (CmRipsCargaAnexo adjuntoAnexo : listaAdjuntos) {

                 if (adjuntoAnexo.getTipoStr() == null ? CmRipsCargaAnexo.AF == null : adjuntoAnexo.getTipoStr().equals(CmRipsCargaAnexo.AF)) {

                     InputStream inputEntrada = adjuntoAnexo.getInputStream();
                     byte[] buffer = inputEntrada.readAllBytes();
                     InputStream newInput = new ByteArrayInputStream(buffer);
                     InputStream newInputNuevo = new ByteArrayInputStream(buffer);

                     br = new BufferedReader(new InputStreamReader(newInput));
                     String linea;
                     while ((linea = br.readLine()) != null) {
                         if (!"".equals(linea)) {
                             String[] informacionFila = linea.split(",");
                             String llave = informacionFila[COLUMNAS_NUMERO_NIT].trim() + "-" +informacionFila[COLUMNAS_NUMERO_FACTURADO].trim();
                             if (hashArchivoLineas.get(llave) == null) {
                                 hashArchivoLineas.put(llave, 1);
                             } else {
                                 hashFacturaRepetidas.put(llave, hashArchivoLineas.get(llave) + 1);
                             }
                         }
                     }
                     adjuntoAnexo.setInputStream(newInputNuevo);
                 }
             }
         } catch (IOException ioe) {
             hashFacturaRepetidas = new HashMap<>();
         } finally {
             try {
                 if (br != null) {
                     br.close();
                 }
             } catch (IOException ioe) {
             }
         }
         return hashFacturaRepetidas;
    }
     
    private void asignarCntContratoCompleto(CmRipsCargaBean bean) throws Exception {
        CntContrato contrato = Optional.ofNullable(bean.getObjeto().getCntContrato()).orElse(new CntContrato());
        if (contrato.getId() != null && contrato.getId() > 0) {
            contrato = getContratoRemoto().consultar(bean.getObjeto().getCntContrato().getId());
            bean.getObjeto().setCntContrato(contrato);
        }
    }
}