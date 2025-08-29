package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgadoContacto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoContactoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.bean.TuJuzgadoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;

public class TuJuzgadoServicioImpl extends AccionesBO implements TuJuzgadoServicioIface {

    private TuJuzgadoRemoto getJuzgadoRemoto() throws Exception {
        return (TuJuzgadoRemoto) RemotoEJB.getEJBRemoto("TuJuzgadoServicio", TuJuzgadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private TuJuzgadoContactoRemoto getTuJuzgadoContactoRemoto() throws Exception {
        return (TuJuzgadoContactoRemoto) RemotoEJB.getEJBRemoto("TuJuzgadoContactoServicio", TuJuzgadoContactoRemoto.class.getName());
    }
    
    @Override
    public void Accion(TuJuzgadoBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case TuJuzgadoBean.BORRAR_TELEFONO_CONTACTO:
                            borrarTelefonoJuzgadoContacto(bean);
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

    private void listar(TuJuzgadoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getJuzgadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getJuzgadoRemoto().consultarLista(bean.getParamConsulta()));
//            bean.setUbicaciones(getUbicacionRemoto().consultar(bean.getObjeto().getUbicacionesId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void ver(TuJuzgadoBean bean) {
        try {
            bean.setObjeto(getJuzgadoRemoto().consultar(bean.getObjeto().getId()));
            listarTuJuzgadoContactos(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(TuJuzgadoBean bean) {
        try {
            bean.setObjeto(new TuJuzgado());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TuJuzgadoBean bean) {
        try {
            TuJuzgado tuJuzgado = getJuzgadoRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(tuJuzgado);
            listarTuJuzgadoContactos(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(TuJuzgadoBean bean) {
        try {
            TuJuzgado tuJuzgado = bean.getObjeto();
            bean.auditoriaGuardar(bean.getObjeto());
            bean.auditoriaGuardar(tuJuzgado);
            int idJuzgado = getJuzgadoRemoto().insertar(bean.getObjeto());
            bean.getObjeto().setId(idJuzgado);
            procesarJuzgadoTelefonos(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(TuJuzgadoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getJuzgadoRemoto().actualizar(bean.getObjeto());
            procesarJuzgadoTelefonos(bean);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
//

    private void borrar(TuJuzgadoBean bean) {
        try {
            bean.setObjeto(getJuzgadoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarTelefonoJuzgadoContacto(TuJuzgadoBean bean) {
        try {
            TuJuzgadoContacto contacto = getTuJuzgadoContactoRemoto().consultar(bean.getAucAfiliadoContacto().getId());
            if (contacto != null) {
                getTuJuzgadoContactoRemoto().eliminar(contacto.getId());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void procesarJuzgadoTelefonos(TuJuzgadoBean bean) {
        try {
            for (TuJuzgadoContacto tuJuzgadoContacto : bean.getListaTuJuzgadoContacto()) {
                if (tuJuzgadoContacto.getId() == null) {
                    tuJuzgadoContacto.setTuJuzgadosId(new TuJuzgado(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(tuJuzgadoContacto);
                    getTuJuzgadoContactoRemoto().insertar(tuJuzgadoContacto);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void listarTuJuzgadoContactos(TuJuzgadoBean bean) {
        try {
            if (bean.getObjeto().getId() != null ) {
                bean.setListaTuJuzgadoContacto(getTuJuzgadoContactoRemoto().consultarListaContactos(bean.getObjeto().getId()));
            }
        } catch (Exception ex) {
            bean.setListaTuJuzgadoContacto(new ArrayList<>());
        }
    }
    
    private void cargas(TuJuzgadoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
//                     bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
//                     bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
                    break;
                case Url.ACCION_VER:

                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    @Override
    public void cargaInial(TuJuzgadoBean bean) {
        try {
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setListaTipoContacto(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setHashTipoContacto(AuConstantes.obtenerHashMaestro(bean.getListaTipoContacto()));
            bean.setHashUbicacionesAntioquia(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
            
        } catch (Exception ex) {
            bean.addError("Error en carga inicial, favor contactar al adminitrador");
        }
    }
}
