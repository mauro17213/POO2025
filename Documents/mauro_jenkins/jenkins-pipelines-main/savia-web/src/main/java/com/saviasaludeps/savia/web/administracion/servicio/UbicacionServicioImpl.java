/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.web.administracion.bean.UbicacionBean;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public class UbicacionServicioImpl extends AccionesBO implements UbicacionServicioIface {

    private Ubicacion obj = new Ubicacion();

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    @Override
    public void Accion(UbicacionBean bean) {
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
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(UbicacionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getUbicacionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getUbicacionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(UbicacionBean bean) {
        try {
            bean.setObjeto(getUbicacionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(UbicacionBean bean) {
        try {
            bean.setObjeto(new Ubicacion());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(UbicacionBean bean) {
        try {
            obj.setId(bean.getObjeto().getId());
            bean.setObjeto(getUbicacionRemoto().consultar(obj.getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(UbicacionBean bean) {
        try {
            if (bean.getObjeto().getTipo() == Ubicacion.TIPO_DEPARTAMENTO || bean.getObjeto().getTipo() == Ubicacion.TIPO_MUNICIPIO) {
                if (bean.getObjeto().getUbicacionPadre() == null) {
                    throw new Exception("Los tipos Departamente y Municipio requieren un padre");
                }
            }
            bean.auditoriaGuardar(bean.getObjeto());
            if (bean.getObjeto().getMaeRegionId() != null) {
                Maestro maestro = bean.getRegionesRecursiva().get(bean.getObjeto().getMaeRegionId());
                if(maestro != null){
                    bean.getObjeto().setMaeRegionCodigo(maestro.getValor());
                    bean.getObjeto().setMaeRegionValor(maestro.getNombre());
                }
            }
            bean.getObjeto().setId(getUbicacionRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(UbicacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            if (bean.getObjeto().getMaeRegionId() != null) {
                Maestro maestro = bean.getRegionesRecursiva().get(bean.getObjeto().getMaeRegionId());
                if(maestro != null){
                    bean.getObjeto().setMaeRegionCodigo(maestro.getValor());
                    bean.getObjeto().setMaeRegionValor(maestro.getNombre());
                }
            }
            getUbicacionRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(UbicacionBean bean) {
        try {
            obj = new Ubicacion();
            obj.setId(bean.getObjeto().getId());
            getUbicacionRemoto().eliminar(obj.getId());
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public List<Ubicacion> consultarPorTipo(int tipo, int profundidad, UbicacionBean bean) {
        List<Ubicacion> lista = new ArrayList();
        try {
            for (Ubicacion _obj : UbicacionSingle.getInstance().listarPorTipo(tipo)) {
                if (profundidad > 0) {
                    _obj.setUbicacionPadre(consultarPadre(_obj, profundidad, 1, bean));
                }
                lista.add(_obj);
            }
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }

    private Ubicacion consultarPadre(Ubicacion hijo, int profFinal, int profActual, UbicacionBean bean) {
        Ubicacion padre = new Ubicacion();
        try {
            padre = UbicacionSingle.getInstance().consultarPadre(hijo.getId());
            if (profFinal > profActual) {
                padre.setUbicacionPadre(consultarPadre(padre, profFinal, profActual + 1,bean));
            }
        } catch (Exception e) {

        }
        return padre;
    }

    public Ubicacion consultarPorID(int _id) {
        try {
            obj = getUbicacionRemoto().consultar(_id);
        } catch (Exception e) {
            obj = new Ubicacion();
        }
        return obj;
    }

    @Override
    public void cargarPadreTipo(UbicacionBean bean) {
        bean.getObjeto().setTipo(bean.getObjeto().getTipo() == 7 ? 4 : bean.getObjeto().getTipo() - 1); //Corregimiento es 7 y el padre es ZONA: 4, en cualquier otro caso el padre es el tipo menos uno
        try {
            List<Ubicacion> _lista = new ArrayList();
            for (Ubicacion _obj : UbicacionSingle.getInstance().listarPorTipo(bean.getObjeto().getTipo())) {
                _obj.setUbicacionPadre(null);
                _lista.add(_obj);
            }
            bean.setPadres(_lista);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public Ubicacion objetoConverter(Ubicacion obj) {
        return obj;
    }

    private ArrayList<Ubicacion> consultarPadres(Ubicacion hijo, ArrayList<Ubicacion> listaPadres, UbicacionBean bean) {
        Ubicacion padre;
        try {
            padre = UbicacionSingle.getInstance().consultarPadre(hijo.getId());
            if (padre != null) {
                listaPadres.add(padre);
                consultarPadres(padre, listaPadres, bean);
            }
        } catch (Exception e) {
        }
        return listaPadres;
    }

    public String armarJerarquiaSuperior(Ubicacion ubicacionActual, UbicacionBean bean) {
        ArrayList<Ubicacion> listaPadres = new ArrayList<>();
        listaPadres = consultarPadres(ubicacionActual, listaPadres, bean);
        String jerarquia = ubicacionActual.getNombre() + " / ";
        for (Ubicacion padre : listaPadres) {
            jerarquia = jerarquia + padre.getNombre() + " / ";
        }
        return jerarquia.substring(0, jerarquia.length() - 2);
    }

    public ArrayList<Ubicacion> consultarHijos(Ubicacion padre, UbicacionBean bean) {
        ArrayList<Ubicacion> listaHijos = new ArrayList<>();
        try {
            for (Ubicacion hijo : UbicacionSingle.getInstance().consultarHijos(padre.getId())) {
                listaHijos.add(hijo);
            }
        } catch (Exception e) {
        }
        return listaHijos;
    }

    @Override
    public void cargaInicial(UbicacionBean bean) {
        try {
            bean.setListaRegiones(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGION));
            bean.setRegionesRecursiva(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGION));
        } catch (Exception ex) {

        }
    }

}
