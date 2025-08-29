/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Destino;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Zona;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4DestinoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ZonaRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuZonaBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author Stiven Giraldo
 */
public class AuZonaServicioImpl extends AccionesBO implements AuZonaServicioIface {
    
    private AuAnexo4ZonaRemoto getAuAnexo4ZonaRemoto() throws Exception {
        return (AuAnexo4ZonaRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ZonaServicio", AuAnexo4ZonaRemoto.class.getName());
    }
    
    private AuAnexo4DestinoRemoto getAuAnexo4DestinoRemoto() throws Exception {
        return (AuAnexo4DestinoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4DestinoServicio", AuAnexo4DestinoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuZonaBean bean) {
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
                    eliminar(bean);
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(AuZonaBean bean) {
        try {
            bean.setListaMunicipiosCobertura(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setListaMunicipios(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashMunicipios(UbicacionSingle.getInstance().getHashMunicipios());
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.cargaInicial()"+e.toString());
        }
    }

    private void listar(AuZonaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAuAnexo4ZonaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAuAnexo4ZonaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.listar() "+e.toString());
        }
    }

    private void crear(AuZonaBean bean) {
        try {
            bean.setObjeto(new AuAnexo4Zona());
            bean.getObjeto().setActivo(true);
        } catch (Exception e) {
            bean.addError("Hubo un fallo al crear, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.crear() "+e.toString());
        }
    }

    private void guardar(AuZonaBean bean) {
        try {
            if (getAuAnexo4ZonaRemoto().validarZona(bean.getObjeto().getUbicacionId())) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setId(getAuAnexo4ZonaRemoto().insertar(bean.getObjeto()));
                if (bean.getObjeto().getAuAnexo4DestinoList() != null && !bean.getObjeto().getAuAnexo4DestinoList().isEmpty()){ 
                    for (AuAnexo4Destino destino : bean.getObjeto().getAuAnexo4DestinoList()) {
                        destino.setAuAnexo3ZonaId(bean.getObjeto());
                        bean.auditoriaGuardar(destino);
                        getAuAnexo4DestinoRemoto().insertar(destino);
                    }
                }
                bean.addMensaje("Se guardo la zona "+bean.getObjeto().getNombre()+" de manera satisfactoria");
            } else {
                bean.addError("Ya existe una zona para este origen");
            }
                
        } catch (Exception e) {
            bean.addError("Hubo un fallo guardando, favor comunicarse con el admintrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.guardar() "+e.toString());
        }
    }

    private void editar(AuZonaBean bean) {
        try {
            bean.setObjeto(getAuAnexo4ZonaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAuAnexo4DestinoList(getAuAnexo4DestinoRemoto().consultarListaPorIdZona(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al buscar la zona a editar, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.editar() "+e.toString());
        }
    }

    private void modificar(AuZonaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getAuAnexo4ZonaRemoto().actualizar(bean.getObjeto());
            if (bean.getObjeto().getAuAnexo4DestinoList() != null && !bean.getObjeto().getAuAnexo4DestinoList().isEmpty()){ 
                for (AuAnexo4Destino destino : bean.getObjeto().getAuAnexo4DestinoList()) {
                    if (destino.getId() == null) {
                        bean.auditoriaGuardar(destino);
                        getAuAnexo4DestinoRemoto().insertar(destino);
                    } else {
                        bean.auditoriaModificar(destino);
                        getAuAnexo4DestinoRemoto().actualizar(destino);
                    }
                        
                }
            }
            if (!bean.getListaDestinosEliminar().isEmpty()) {
                for (AuAnexo4Destino destino : bean.getListaDestinosEliminar()) {
                    getAuAnexo4DestinoRemoto().eliminar(destino.getId());
                }
                
            }
            bean.addMensaje("Se modifico con exito la zona "+bean.getObjeto().getNombre());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al editar una zona, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.modificar() "+e.toString());
        }
    }

    private void eliminar(AuZonaBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError("Hubo un error eliminando la zona, favor comuncarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.eliminar() "+e.toString());
        }
    }

    private void ver(AuZonaBean bean) {
        try {
            bean.setObjeto(getAuAnexo4ZonaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAuAnexo4DestinoList(getAuAnexo4DestinoRemoto().consultarListaPorIdZona(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al buscar la zona, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.ver() "+e.toString());
        }
    }

    private void gestionar(AuZonaBean bean) {
        try {
            bean.setObjetoDestino(new AuAnexo4Destino());
            bean.setObjeto(getAuAnexo4ZonaRemoto().consultar(bean.getObjeto().getId()));            
        } catch (Exception e) {
            bean.addError("Hubo un error al abrir la gestion de zona, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.gestionar() "+e.toString());
        }
    }

    @Override
    public void eliminarDestino(AuZonaBean bean) {
        try {
            bean.setObjetoDestino(getAuAnexo4DestinoRemoto().eliminar(bean.getObjetoDestino().getId()));
            bean.addMensaje("Se elimino con exito el destino "+bean.getObjetoDestino().getUbicacionValor());
            for (AuAnexo4Destino des : bean.getObjeto().getAuAnexo4DestinoList()){
                if (des.getId() != null) {
                    getAuAnexo4DestinoRemoto().actualizar(des);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al eliminar el destino, favor comunicarse con el administrador");
            System.out.println("com.saviasaludeps.savia.web.autorizacion.servicio.AuZonaServicioImpl.eliminarDestino() "+e.toString());
        }
    }
    
}
