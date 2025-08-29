package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItemGestion;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.tutela.TuAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuEstadoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaItemGestionRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuItemRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuPersonaContactoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.bean.TuTutelaBean;
import com.saviasaludeps.savia.web.tutela.bean.TuTutelaItemBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TuTutelaItemServicioImpl extends AccionesBO implements TuTutelaItemServicioIface {

    private TuItemRemoto getTuTutelaItemRemoto() throws Exception {
        return (TuItemRemoto) RemotoEJB.getEJBRemoto("TuTutelaItemServicio", TuItemRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }
    
    private TuTutelaItemGestionRemoto getTuTutelaItemGestionRemoto() throws Exception {
        return (TuTutelaItemGestionRemoto) RemotoEJB.getEJBRemoto("TuTutelaItemGestionServicio", TuTutelaItemGestionRemoto.class.getName());
    }
    
    private TuPersonaContactoRemoto getTuPersonaContactoRemoto() throws Exception {
        return (TuPersonaContactoRemoto) RemotoEJB.getEJBRemoto(("TuPersonaContactoServicio"), TuPersonaContactoRemoto.class.getName());
    }
    
    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto(("AuAnexo4Servicio"), AuAnexo4Remoto.class.getName());
    }
    
    private TuAdjuntoRemoto getTuAdjuntoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("TuAdjuntoServicio", TuAdjuntoRemoto.class.getName());
        return (TuAdjuntoRemoto) object;
    }
    
    private TuTutelaRemoto getTutelaRemoto() throws Exception {
        return (TuTutelaRemoto) RemotoEJB.getEJBRemoto("TuTutelaServicio", TuTutelaRemoto.class.getName());
    }

    private TuEstadoRemoto getTuEstadoRemoto() throws Exception {
        return (TuEstadoRemoto) RemotoEJB.getEJBRemoto("TuEstadoTutelaServicio", TuEstadoRemoto.class.getName());
    }
    
    @Override
    public void Accion(TuTutelaItemBean bean) {
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
                default:
                    break;
            }

//            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(TuTutelaItemBean bean) {
        try {
            //2025-07-23 jyperez adición de validación de permiso adicional para permitirle Ver Todo lo de la organización a la que pertenece el usuario
            if (!bean.isAccionAdicional1()){
                bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getUsuario().getId());
            }
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap());
            }
            bean.getParamConsulta().setCantidadRegistros(getTuTutelaItemRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTuTutelaItemRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void ver(TuTutelaItemBean bean) {
        try {
            bean.setObjeto(getTuTutelaItemRemoto().consultar(bean.getObjeto().getId()));
   
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(TuTutelaItemBean bean) {
        try {
            bean.setObjeto(new TuTutelaItem());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TuTutelaItemBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            TuTutelaItem tuTutelaItem = getTuTutelaItemRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(tuTutelaItem);
            //consultamos los contactos de la persona
            if (bean.getObjeto().getTuPersonasId().getId() != null) {
                paramConsulta.setParametroConsulta2(bean.getObjeto().getTuPersonasId().getId());
                bean.setListaTuPersonaContacto(getTuPersonaContactoRemoto().consultarLista(paramConsulta));
            } else {
                bean.setListaTuPersonaContacto(new ArrayList<>());
            }
            //consultamos la autorización asociada al item
            if (bean.getObjeto().getConsultarAutorizacion() != null) {
                AuAnexo4 auAnexo4 = getAuAnexo4Remoto().consultar(bean.getObjeto().getConsultarAutorizacion());
                if (auAnexo4 != null) {
                    List<AuAnexo4> listaAutoriacion = new ArrayList<>();
                    listaAutoriacion.add(auAnexo4);
                    bean.getObjeto().setRegistrosAuditoriaAutorizacion(listaAutoriacion);
                }
            }
            //2025-07-10 jyperez actualizamos la lista maestro de estados
            bean.setListaEstadoItemCierre(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.TU_ESTADO_ASIGNACION_ITEM, bean.getObjeto().getMaeEstadoItemId()));
            bean.setHashEstadoItem(convertToHashMaestros(bean.getListaEstadoItemCierre()));
            //2025-07-24 jyperez consultamos la gestión correspondiente al ítem
            bean.getObjeto().setTuTutelaItemGestionesList(getTuTutelaItemGestionRemoto().consultarLista(bean.getObjeto().getId()));
            //2025-07-24 jyperez blanqueamos campo de descripción
            bean.getObjeto().setObservacion("");
        } catch (Exception e) {
            bean.setListaTuPersonaContacto(new ArrayList<>());
            bean.addError(e.getMessage());
        }
    }

   

    private void guardar(TuTutelaItemBean bean) {
        try {
        
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(TuTutelaItemBean bean) {
        boolean existeAdjunto = false;
        TuTutela tutela = null;
        TuTutelaEstado tutelaEstado = null;
        try {
            //actualizamos los datos de item y adicionamos la gestión del item
            bean.auditoriaModificar(bean.getObjeto());
            //guardamos la fecha de la modificación en la fecha de respuesta
            bean.getObjeto().setFechaRespuesta(bean.getObjeto().getFechaHoraModifica());
            //datos para la gestion item tabla
            TuTutelaItemGestion gestionItem = new TuTutelaItemGestion();
            gestionItem.setTuTutelaItemId(bean.getObjeto());
            gestionItem.setObservacion(bean.getObjeto().getObservacion());
            //el campo observacionIps no puede ser nulo. entonces adjuntamos el dato de observacion
            gestionItem.setObservacionIps(bean.getObjeto().getObservacion());
            gestionItem.setMaeEstadoItemId(bean.getObjeto().getMaeEstadoItemId());
            gestionItem.setMaeEstadoItemCodigo(bean.getObjeto().getMaeEstadoItemCodigo());
            gestionItem.setMaeEstadoItemValor(bean.getObjeto().getMaeEstadoItemValor());
            bean.auditoriaGuardar(gestionItem);
            //validaciones
            //2025-07-15 jyperez validamos si se está cambiando a estado de Cerrado, que se tenga al menos un adjunto.
            if (bean.getObjeto().getMaeEstadoItemCodigo().equals(TuTutelaItem.ESTADO_ITEM_CERRADO)) {
                for (TuAdjunto adjunto : bean.getObjeto().getTuAdjuntosList()) {
                    if (adjunto.getId() == null) {
                        existeAdjunto = true;
                    }
                }
                if (!existeAdjunto) {
                    bean.addError("Se debe adjuntar un archivo si el estado en la gestión es Cerrado");
                }
            }
            ////2025-07-18 jyperez actualizamos los valores de conteo de items en TuTutela y TuTutelaEstado
            tutela = getTutelaRemoto().consultar(bean.getObjeto().getTuTutelasId().getId());
            if (tutela == null) {
                bean.addError("Error obteniendo los datos de la tutela");
            }
            tutelaEstado = getTuEstadoRemoto().consultar(bean.getObjeto().getTuTutelaEstadosId().getId());
            if (tutelaEstado == null) {
                bean.addError("Error obteniendo los datos del estado de la tutela");
            }
            if(!bean.isError()){
                getTuTutelaItemRemoto().actualizarGestionItem(bean.getObjeto());
                getTuTutelaItemGestionRemoto().insertar(gestionItem);
                insertarAdjuntos(bean.getObjeto().getTuAdjuntosList(), bean, 0, bean.getObjeto().getTuTutelaEstadosId().getId(), 0, bean.getObjeto().getId());
                //2025-07-18 jyperez actualizamos los valores de conteo de items en TuTutela y TuTutelaEstado
                tutela.setCantidadItems(getTuEstadoRemoto().contarItemsTutela(tutela.getId()));
                tutela.setCantidadItemsCerrados(getTuEstadoRemoto().contarItemsCerradosTutela(tutela.getId()));
                tutelaEstado.setCantidadItems(getTuEstadoRemoto().contarItemsEstado(tutelaEstado.getId()));
                tutelaEstado.setCantidadItemsCerrados(getTuEstadoRemoto().contarItemsCerradosEstado(tutelaEstado.getId()));
                getTutelaRemoto().actualizar(tutela);
                getTuEstadoRemoto().actualizar(tutelaEstado);
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void insertarAdjuntos(List<TuAdjunto> adjuntosIn, TuTutelaItemBean bean,
            int idTutela, int idTutelaEstado, int idSeguimiento, int idTutelaItem) {

        try {
            for (TuAdjunto adjunto : adjuntosIn) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    /*if (idTutela > 0) {
                        adjunto.setTuTutelaId(new TuTutela(idTutela));
                    }*/

                    if (idTutelaEstado > 0) {
                        adjunto.setTuTutelaEstadosId(new TuTutelaEstado(idTutelaEstado));
                    }

                    if (idSeguimiento > 0) {
                        adjunto.setTuSeguimientosId(new TuSeguimiento(idSeguimiento));
                    }

                    if (idTutelaItem > 0) {
                        adjunto.setTuTutelaItemsId(new TuTutelaItem(idTutelaItem));
                    }

                    int idAdjunto = getTuAdjuntoRemoto().insertar(adjunto);
                    adjunto.setId(idAdjunto);
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private HashMap<Integer, Maestro> convertToHashMaestros(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }
    
    @Override
    public void cargaInicial(TuTutelaItemBean bean) {
        try {
            
            bean.setListaTipoPrestacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SERVICIO_TIPO_PRESTACION));
            bean.setHashTipoPrestacion(convertToHashMaestros(bean.getListaTipoPrestacion()));
            
            bean.setListaCausaTutela(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_PRESTACION_CAUSA));
            bean.setHashCausaTutela(convertToHashMaestros(bean.getListaCausaTutela()));
            
            bean.setListaPresentacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SERVICIO_PRESENTACION));
            bean.setHashPresentacion(convertToHashMaestros(bean.getListaPresentacion()));
            
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTipoDocumento(convertToHashMaestros(bean.getListaTipoDocumento()));
            
            //PENDIENTE obtenerlos mediante la nueva Acción de maestros para solo sacar los estados Asignado y En Gestión
            bean.setListaEstadoItem(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.TU_TUTELA_ESTADO_ITEM_LISTAR));
            bean.setHashEstadoItem(convertToHashMaestros(bean.getListaEstadoItem()));
            //PENDIENTE obtenerlos mediante la nueva Acción de maestros para solo sacar los estados Rechazado y Cerrado
            bean.setListaEstadoItemCierre(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.TU_TUTELA_ESTADO_ITEM_NO_LISTAR));
            
            bean.setListaEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_ESTADO_ASIGNACION_ITEM));
            bean.setHashEstadoSolicitud(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.TU_ESTADO_ASIGNACION_ITEM));
            
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
}
