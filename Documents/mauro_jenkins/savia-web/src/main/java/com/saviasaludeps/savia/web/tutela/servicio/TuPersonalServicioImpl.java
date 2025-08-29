package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialFirma;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialPersona;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuMemorialFirmaRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuMemorialPersonaRemoto;
import com.saviasaludeps.savia.web.tutela.bean.TuPersonalBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;

public class TuPersonalServicioImpl extends AccionesBO implements TuPersonalServicioIface {

    private TuMemorialPersonaRemoto getTuMemorialPersonaRemoto() throws Exception {
        return (TuMemorialPersonaRemoto) RemotoEJB.getEJBRemoto("TuMemorialPersonaServicio", TuMemorialPersonaRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }
    
    private TuMemorialFirmaRemoto getTuMemorialFirmaRemoto() throws Exception {
        return (TuMemorialFirmaRemoto) RemotoEJB.getEJBRemoto("TuMemorialFirmaServicio", TuMemorialFirmaRemoto.class.getName());
    }
    
    @Override
    public void Accion(TuPersonalBean bean) {
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
                    switch(bean.getDoAccion()){
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case TuPersonalBean.ACCION_BORRAR_ADJUNTOS_FIRMA:
                            borrarAdjuntosFirma(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    ver(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_ADICIONAL_5:
                    break;
                case Url.ACCION_ADICIONAL_6:
                    break;
                default:
                    break;
            }

//            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(TuPersonalBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap());
            }
         
            //bean.getParamConsulta().getFiltros().put("borradoTutela", "1");
            bean.getParamConsulta().setCantidadRegistros(getTuMemorialPersonaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTuMemorialPersonaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void ver(TuPersonalBean bean) {
        try {
            bean.setObjeto(getTuMemorialPersonaRemoto().consultar(bean.getObjeto().getId()));
   
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(TuPersonalBean bean) {
        try {
            bean.setObjeto(new TuMemorialPersona());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TuPersonalBean bean) {
        try {
            TuMemorialPersona tuMemorialPersona = getTuMemorialPersonaRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(tuMemorialPersona);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

   

    private void guardar(TuPersonalBean bean) {
        try {
            int idtuMemorialPersona = 0;
            TuMemorialPersona tuMemorialPersona = bean.getObjeto();
            bean.auditoriaGuardar(bean.getObjeto());
            
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(tuMemorialPersona.getMaeTipoDocumentoId());
            if (tipoDocumento != null) {
                tuMemorialPersona.setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                tuMemorialPersona.setMaeTipoDocumentoValor(tipoDocumento.getNombre());
            }
            Maestro cargo = bean.getHashCargoPersonal().get(tuMemorialPersona.getMaeGnCargoId());
            if (cargo != null) {
                tuMemorialPersona.setMaeGnCargoCodigo(cargo.getValor());
                tuMemorialPersona.setMaeGnCargoValor(cargo.getNombre());
            }
            idtuMemorialPersona = getTuMemorialPersonaRemoto().insertar(tuMemorialPersona);
            tuMemorialPersona.setId(idtuMemorialPersona);
            procesarAdjuntosFirmas(tuMemorialPersona.getTuMemorialFirmasList(), bean, idtuMemorialPersona);
            if (!bean.isError()) {
                bean.addMensaje("Se creo la personal con id (" + idtuMemorialPersona + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(TuPersonalBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            TuMemorialPersona tuMemorialPersona = bean.getObjeto();
            bean.auditoriaModificar(tuMemorialPersona);
            Maestro tipoDocumento = bean.getHashTiposDocumento().get(tuMemorialPersona.getMaeTipoDocumentoId());
            if (tipoDocumento != null) {
                tuMemorialPersona.setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                tuMemorialPersona.setMaeTipoDocumentoValor(tipoDocumento.getNombre());
            }
            Maestro cargo = bean.getHashCargoPersonal().get(tuMemorialPersona.getMaeGnCargoId());
            if (cargo != null) {
                tuMemorialPersona.setMaeGnCargoCodigo(cargo.getValor());
                tuMemorialPersona.setMaeGnCargoValor(cargo.getNombre());
            }
            getTuMemorialPersonaRemoto().actualizar(tuMemorialPersona);
            procesarAdjuntosFirmas(tuMemorialPersona.getTuMemorialFirmasList(), bean, tuMemorialPersona.getId());
            if(!bean.isError()){
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void modificarGestion(TuPersonalBean bean) {
        try {
            TuMemorialPersona tuMemorialPersona = bean.getObjeto();
            bean.auditoriaModificar(tuMemorialPersona);
            getTuMemorialPersonaRemoto().actualizar(tuMemorialPersona);
            if (!bean.isError()) {
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

  

    private void borrar(TuPersonalBean bean) {
        try {
            TuMemorialPersona tuMemorialPersona = bean.getObjeto();
            tuMemorialPersona = getTuMemorialPersonaRemoto().consultar(tuMemorialPersona.getId());
            if(tuMemorialPersona.getId() != null){
                for(TuMemorialFirma tuMemorialFirma: tuMemorialPersona.getTuMemorialFirmasList()){
                    getTuMemorialFirmaRemoto().eliminar(tuMemorialFirma.getId());
                }
                getTuMemorialPersonaRemoto().eliminar(tuMemorialPersona.getId());
            }
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarAdjuntosFirma(TuPersonalBean bean) {
        try {
            TuMemorialFirma tuMemorialFirma = bean.getTuMemorialFirma();
            tuMemorialFirma = getTuMemorialFirmaRemoto().consultar(tuMemorialFirma.getId());
            if(tuMemorialFirma.getId() != null){
                getTuMemorialFirmaRemoto().eliminar(tuMemorialFirma.getId());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void procesarAdjuntosFirmas(List<TuMemorialFirma> adjuntosIn, TuPersonalBean bean,
            int idtuMemorialPersona) {

        try {
            for (TuMemorialFirma adjunto : adjuntosIn) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    if (idtuMemorialPersona > 0) {
                        adjunto.setTuMemorialPersonaId(new TuMemorialPersona(idtuMemorialPersona));
                    }
                    int idAdjunto = getTuMemorialFirmaRemoto().insertar(adjunto);
                    adjunto.setId(idAdjunto);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

//    private void cargas(TuTutelaBean bean) {
//        try {
//            switch (bean.getAccion()) {
//                case Url.ACCION_LISTAR:
////                     bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
////                     bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
//                    break;
//                case Url.ACCION_VER:
//
//                    break;
//                case Url.ACCION_CREAR:
//                case Url.ACCION_EDITAR:
////                    bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
////                    bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception ex) {
//            bean.addError(ex.getMessage());
//        }
//    }
   
    
    private HashMap<Integer, Maestro> convertToHashMaestros(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }
    
    @Override
    public void cargaInial(TuPersonalBean bean) {
        try {
            
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(convertToHashMaestros(bean.getListaTiposDocumento()));
            
            bean.setListaCargoPersonal(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_CARGO));
            bean.setHashCargoPersonal(convertToHashMaestros(bean.getListaCargoPersonal()));
            
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
}
