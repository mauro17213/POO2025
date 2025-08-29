/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaGrupo;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaGrupoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaMipresRemoto;
import com.saviasaludeps.savia.web.maestro.bean.TecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.web.maestro.utilidades.MaestroParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class TecnologiasServicioImpl extends AccionesBO implements TecnologiasServicioIface {
    
    private MaTecnologiaRemoto getTecnologiaRemoto() throws Exception {
        return (MaTecnologiaRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicio"), MaTecnologiaRemoto.class.getName());
    }
    
    private MaTecnologiaServicioHabilitacionRemoto getTecnologiaServicioHabilitacionRemoto() throws Exception {
        return (MaTecnologiaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicioHabilitacionServicio"), MaTecnologiaServicioHabilitacionRemoto.class.getName());
    }
    
    private MaTecnologiaGrupoRemoto getTecnologiaGrupoRemoto() throws Exception {
        return (MaTecnologiaGrupoRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaGrupoServicio"), MaTecnologiaGrupoRemoto.class.getName());
    }
    
    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }
    
    private MaTecnologiaMipresRemoto getTecnologiaMipresRemoto() throws Exception {
        return (MaTecnologiaMipresRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaMipresServicio"), MaTecnologiaMipresRemoto.class.getName());
    }

    @Override
    public void Accion(TecnologiasBean bean) {
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

    private void listar(TecnologiasBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getTecnologiaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTecnologiaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(TecnologiasBean bean) {
        try {
            bean.setObjeto(getTecnologiaRemoto().consultar(bean.getObjeto().getId()));
            //cargar la lista de Servicios
            bean.setListaTecnologiaServicios(getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologia(bean.getObjeto().getId()));
            //una vez obtenidos los valores, cargamos la lista de Servicio
            if (bean.getListaTecnologiaServicios() != null && !bean.getListaTecnologiaServicios().isEmpty()) {
                bean.setListaServicios(new ArrayList<>());
                for ( MaTecnologiaServicioHabilitacion tech:bean.getListaTecnologiaServicios()) {
                    bean.getListaServicios().add(tech.getMaServicioHabilitacion());
                }
            }else {
                bean.setListaServicios(new ArrayList<>());
            }
            //cargamos la lista de Grupos del objeto
            if (bean.getObjeto().getListaTecnologiaGrupos() != null) {
                bean.setListaTecnologiaGrupo(bean.getObjeto().getListaTecnologiaGrupos());
            }else {
                bean.setListaTecnologiaGrupo(new ArrayList<>());
            }
            //
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(TecnologiasBean bean) {
        try {
            bean.setObjeto(new MaTecnologia());
            // se inicializan los objetos
            bean.setListaServicios(new ArrayList<>());
            bean.setListaTecnologiaGrupo(new ArrayList<>());
            bean.setListaCodigoInsumo(new ArrayList<>());
            //bean.getObjeto().setMaIss2000Tarifario(new MaIss2000Tarifario());
            //bean.getObjeto().setMaIss2001Tarifario(new MaIss2001Tarifario());
            //bean.getObjeto().setMaSoatTarifario(new MaSoatTarifario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(TecnologiasBean bean) {
        MaTecnologiaServicioHabilitacion tecnologiaServicio = null;
        try {
            // validaciones
            //2023-07-19 jyperez validar valor mayor a cero
            if (bean.getObjeto().getVigenciaAutorizacion() != null && 
                    bean.getObjeto().getVigenciaAutorizacion() <= 0) {
                bean.addError("El valor de la vigencia debe ser mayor a cero");
            }
            //2022-01-11 jyperez validación maestro cobertura
            Maestro cobertura = bean.getHashCoberturas().get(bean.getObjeto().getMaeCoberturaId());
            if (cobertura != null) {
                bean.getObjeto().setMaeCoberturaCodigo(cobertura.getValor());
                bean.getObjeto().setMaeCoberturaValor(cobertura.getNombre());
            } else {
                bean.addError("No se pudo obtener los valores para el maestro Cobertura");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                try {
                //actualizamos el valor de cobertura, con el código que tiene el valor Id
                bean.getObjeto().setCobertura(Integer.valueOf(bean.getObjeto().getMaeCoberturaCodigo()));
                }catch (NumberFormatException ex) {
                    bean.getObjeto().setCobertura(0);
                }
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getTecnologiaRemoto().insertar(bean.getObjeto()));
                // 2020-12-25 jyperez agregamos cada objeto de la lista, a la tabla de MaTecnologiasServiciosHabilitacion
                if (bean.getListaServicios().size() > 0) {
                    for (MaServicioHabilitacion serv: bean.getListaServicios()) {
                        tecnologiaServicio = new MaTecnologiaServicioHabilitacion();
                        tecnologiaServicio.setActivo(true);
                        tecnologiaServicio.setMaTecnologia(new MaTecnologia(bean.getObjeto().getId()));
                        tecnologiaServicio.setMaServicioHabilitacion(new MaServicioHabilitacion(serv.getId()));
                        //ponemos información de auditoria
                        bean.auditoriaGuardar(tecnologiaServicio);
                        getTecnologiaServicioHabilitacionRemoto().insertar(tecnologiaServicio);
                    }
                }
                // 2021-03-13 jyperez agregamos cada objeto de la lista a la tabla MaTecnologiaGrupos.
                if (bean.getListaTecnologiaGrupo() != null && bean.getListaTecnologiaGrupo().size() > 0) {
                    for (MaTecnologiaGrupo grupo: bean.getListaTecnologiaGrupo()) {
                        bean.auditoriaGuardar(grupo);
                        getTecnologiaGrupoRemoto().insertar(grupo);
                    }
                }
                //2024-08-05 jyperez agregamos cada objeto de la lista a la tabla MaTecnologiaMipres
                if (bean.getListaCodigoInsumo() != null && !bean.getListaCodigoInsumo().isEmpty()) {
                    for (MpCodigoInsumo codigoMipres:  bean.getListaCodigoInsumo() ) {
                        MaTecnologiaMipres tecnologiaMipres = new MaTecnologiaMipres();
                        tecnologiaMipres.setCodigoMipres(codigoMipres.getCodigoMipres());
                        tecnologiaMipres.setDescripcion(codigoMipres.getDescripcion());
                        tecnologiaMipres.setMaTecnologia(bean.getObjeto());
                        tecnologiaMipres.setMpCodigoInsumo(codigoMipres);
                        bean.auditoriaGuardar(tecnologiaMipres);
                        getTecnologiaMipresRemoto().insertar(tecnologiaMipres);
                    }
                }
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TecnologiasBean bean) {
        try {
            bean.setObjeto(getTecnologiaRemoto().consultar(bean.getObjeto().getId()));
            //cargar la lista de Servicios
            bean.setListaTecnologiaServicios(getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologia(bean.getObjeto().getId()));
            //una vez obtenidos los valores, cargamos la lista de Servicio
            if (bean.getListaTecnologiaServicios() != null && !bean.getListaTecnologiaServicios().isEmpty()) {
                bean.setListaServicios(new ArrayList<>());
                for ( MaTecnologiaServicioHabilitacion tech:bean.getListaTecnologiaServicios()) {
                    bean.getListaServicios().add(tech.getMaServicioHabilitacion());
                }
            }else {
                bean.setListaServicios(new ArrayList<>());
            }
            //inicialilzamos la lista de servicios a borrar en cero
            bean.setListaServiciosBorrar(new ArrayList<>());
            //cargamos la lista de Grupos del objeto
            if (bean.getObjeto().getListaTecnologiaGrupos() != null) {
                bean.setListaTecnologiaGrupo(bean.getObjeto().getListaTecnologiaGrupos());
            }else {
                bean.setListaTecnologiaGrupo(new ArrayList<>());
            }
            //inicialilzamos la lista de grupos a borrar en cero
            bean.setListaTecnologiaGrupoBorrar(new ArrayList<>());
            //2024-08-05 jyperez inicializamos la lista de codigos mipres
            if (bean.getObjeto().getListaTecnologiaMipres()!= null && !bean.getObjeto().getListaTecnologiaMipres().isEmpty()) {
                bean.setListaCodigoInsumo(new ArrayList<>());
                for ( MaTecnologiaMipres cod: bean.getObjeto().getListaTecnologiaMipres()) {
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

    private void modificar(TecnologiasBean bean) {
        MaTecnologiaServicioHabilitacion tecnologiaServicio = null;
        MaTecnologiaGrupo tecnologiaGrupo = null;
        List<CntContrato> listaContratos = new ArrayList();
        try {
            // validaciones
            //2023-07-19 jyperez validar valor mayor a cero
            if (bean.getObjeto().getVigenciaAutorizacion() != null && 
                    bean.getObjeto().getVigenciaAutorizacion() <= 0) {
                bean.addError("El valor de la vigencia debe ser mayor a cero");
            }
            //2021-04-22 jyperez validamos si hubo cambio de estado en el objeto, para lanzar la validación
            if (bean.isEstadoInicialTecnologia() != bean.getObjeto().isActivo() && !bean.getObjeto().isActivo()) {
                listaContratos = getContratoDetalleRemoto().consultarPorTecnologia(MaestroParametro.TIPO_TECNOLOGIA_TECNOLOGIA,bean.getObjeto().getId());
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
            //2022-01-11 jyperez validación maestro cobertura
            Maestro cobertura = bean.getHashCoberturas().get(bean.getObjeto().getMaeCoberturaId());
            if (cobertura != null) {
                bean.getObjeto().setMaeCoberturaCodigo(cobertura.getValor());
                bean.getObjeto().setMaeCoberturaValor(cobertura.getNombre());
            } else {
                bean.addError("No se pudo obtener los valores para el maestro Cobertura");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                try {
                //actualizamos el valor de cobertura, con el código que tiene el valor Id
                bean.getObjeto().setCobertura(Integer.valueOf(bean.getObjeto().getMaeCoberturaCodigo()));
                }catch (NumberFormatException ex) {
                    bean.getObjeto().setCobertura(0);
                }
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getTecnologiaRemoto().actualizar(bean.getObjeto());
                //2020-12-28 jyperez eliminamos los registros de la tabla si es el caso.
                if (!bean.getListaServiciosBorrar().isEmpty()) {
                    //recorremos la lista de los servicios a borrar
                    for (MaServicioHabilitacion serv: bean.getListaServiciosBorrar()) {
                        tecnologiaServicio = getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologiaYServicio(bean.getObjeto().getId(), serv.getId());
                        if (tecnologiaServicio != null && tecnologiaServicio.getId() != null) {
                            //eliminamos el registro de la BD
                            getTecnologiaServicioHabilitacionRemoto().eliminar(tecnologiaServicio.getId());
                        }
                    }
                }
                tecnologiaServicio = null;
                // 2020-12-28 jyperez agregamos cada objeto de la lista, a la tabla de MaTecnologiasServiciosHabilitacion
                if (bean.getListaServicios().size() > 0) {
                    for (MaServicioHabilitacion serv: bean.getListaServicios()) {
                        //se agregan sólo los nuevos registros a la tabla relacional.
                        if (serv.isNuevoRegistro()) {
                            tecnologiaServicio = new MaTecnologiaServicioHabilitacion();
                            tecnologiaServicio.setActivo(true);
                            tecnologiaServicio.setMaTecnologia(new MaTecnologia(bean.getObjeto().getId()));
                            tecnologiaServicio.setMaServicioHabilitacion(new MaServicioHabilitacion(serv.getId()));
                            //ponemos información de auditoria
                            bean.auditoriaGuardar(tecnologiaServicio);
                            getTecnologiaServicioHabilitacionRemoto().insertar(tecnologiaServicio);
                        }
                    }
                }
                //2021-03-12 jyperez eliminamos los registros de grupo de la tabla si es el caso
                if (!bean.getObjeto().getListaTecnologiaGrupos().isEmpty()) {
                    for (MaTecnologiaGrupo grupo: bean.getListaTecnologiaGrupoBorrar()) {
                        getTecnologiaGrupoRemoto().eliminar(grupo.getId());
                    }
                }
                //agregamos cada objeto de la lista a la tabla MaTecnologiaGrupo
                if (bean.getListaTecnologiaGrupo() != null && bean.getListaTecnologiaGrupo().size() > 0) {
                    for (MaTecnologiaGrupo grupo: bean.getListaTecnologiaGrupo()) {
                        //se agregan únicamente los nuevos registros
                        if (grupo.isNuevoRegistro()) {
                            grupo.setId(null);
                            bean.auditoriaGuardar(grupo);
                            getTecnologiaGrupoRemoto().insertar(grupo);
                        }
                    }
                }
                //2024-08-05 jyperez eliminamos los registros en la lista de codigos mipres
                if (!bean.getListaCodigoInsumoBorrar().isEmpty()) {
                    for (MpCodigoInsumo cod: bean.getListaCodigoInsumoBorrar()) {
                        for (MaTecnologiaMipres obj: bean.getObjeto().getListaTecnologiaMipres()) {
                            if (obj.getCodigoMipres().equals(cod.getCodigoMipres())) {
                                getTecnologiaMipresRemoto().eliminar(obj.getId());
                            }
                        }
                    }
                }
                //agregamos los registros en la lista
                if (bean.getListaCodigoInsumo() != null && !bean.getListaCodigoInsumo().isEmpty()) {
                    for (MpCodigoInsumo codigoMipres:  bean.getListaCodigoInsumo() ) {
                        if (codigoMipres.isNuevoRegistro()) {
                            MaTecnologiaMipres tecnologiaMipres = new MaTecnologiaMipres();
                            tecnologiaMipres.setCodigoMipres(codigoMipres.getCodigoMipres());
                            tecnologiaMipres.setDescripcion(codigoMipres.getDescripcion());
                            tecnologiaMipres.setMaTecnologia(bean.getObjeto());
                            tecnologiaMipres.setMpCodigoInsumo(codigoMipres);
                            bean.auditoriaGuardar(tecnologiaMipres);
                            getTecnologiaMipresRemoto().insertar(tecnologiaMipres);
                        }
                    }
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(TecnologiasBean bean) {
        try {
            bean.setObjeto(getTecnologiaRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(TecnologiasBean bean) {
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
    public void cargaInicial(TecnologiasBean bean) {
        try {
            //carga de maestros
            //2021-04-07 jyperez INC 758 Se carga el maestro de cobertura
            bean.setListaCoberturas(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_COBERTURA));
            bean.setHashCoberturas(Util.convertToHash(bean.getListaCoberturas()));
            bean.setListaGrupoTecnologia(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_TECNOLOGIA_GRUPO));
            bean.setHashGrupoTecnologia(Util.convertToHash(bean.getListaGrupoTecnologia()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
