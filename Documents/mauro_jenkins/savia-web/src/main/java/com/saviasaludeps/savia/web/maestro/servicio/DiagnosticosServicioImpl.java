/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.maestro.bean.DiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;

/**
 *
 * @author jyperez
 */
public class DiagnosticosServicioImpl extends AccionesBO implements DiagnosticosServicioIface {
    
    private MaDiagnosticoRemoto getDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto(("MaDiagnosticoServicio"), MaDiagnosticoRemoto.class.getName());
    }
    
    @Override
    public void Accion(DiagnosticosBean bean) {
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

    private void listar(DiagnosticosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getDiagnosticoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getDiagnosticoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(DiagnosticosBean bean) {
        try {
            bean.setObjeto(getDiagnosticoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(DiagnosticosBean bean) {
        try {
            bean.setObjeto(new MaDiagnostico());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(DiagnosticosBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeDiagnosticoCapituloId() != 0) {
                maestro = bean.getHashCapitulo().get(bean.getObjeto().getMaeDiagnosticoCapituloId());
                bean.getObjeto().setMaeDiagnosticoCapituloCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCapituloValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeDiagnosticoCategoriaId() != 0) {
                maestro = bean.getHashCategoria().get(bean.getObjeto().getMaeDiagnosticoCategoriaId());
                bean.getObjeto().setMaeDiagnosticoCategoriaCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCategoriaValor(maestro.getNombre());
            }
            //2025-05-06 jyperez nuevo maestro Denominación Cac
            if (bean.getObjeto().getMaCacId()!= 0) {
                maestro = bean.getHashDenominacionCac().get(bean.getObjeto().getMaCacId());
                bean.getObjeto().setMaCacCodigo(maestro.getValor());
                bean.getObjeto().setMaCacValor(maestro.getNombre());
            }
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                getDiagnosticoRemoto().insertar(bean.getObjeto());
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(DiagnosticosBean bean) {
        try {
            bean.setObjeto(getDiagnosticoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(DiagnosticosBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeDiagnosticoCapituloId() != 0) {
                maestro = bean.getHashCapitulo().get(bean.getObjeto().getMaeDiagnosticoCapituloId());
                bean.getObjeto().setMaeDiagnosticoCapituloCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCapituloValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeDiagnosticoCategoriaId()!= 0) {
                maestro = bean.getHashCategoria().get(bean.getObjeto().getMaeDiagnosticoCategoriaId());
                bean.getObjeto().setMaeDiagnosticoCategoriaCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCategoriaValor(maestro.getNombre());
            }
            //2025-05-06 jyperez nuevo maestro Denominación Cac
            if (bean.getObjeto().getMaCacId() != null && bean.getObjeto().getMaCacId()!= 0) {
                maestro = bean.getHashDenominacionCac().get(bean.getObjeto().getMaCacId());
                bean.getObjeto().setMaCacCodigo(maestro.getValor());
                bean.getObjeto().setMaCacValor(maestro.getNombre());
            } else {
                bean.getObjeto().setMaCacCodigo("");
                bean.getObjeto().setMaCacValor("");
            }
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getDiagnosticoRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(DiagnosticosBean bean) {
        try {
            bean.setObjeto(getDiagnosticoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(DiagnosticosBean bean) {
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
    public void cargaInicial(DiagnosticosBean bean) {
        try {
            bean.setListaCapitulo(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_DIAGNOSTICO_CAPITULO));
            bean.setHashCapitulo(Util.convertToHash(bean.getListaCapitulo()));
            bean.setListaCategoria(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_DIAGNOSTICO_CATEGORIA));
            bean.setHashCategoria(Util.convertToHash(bean.getListaCategoria()));
            //2025-05-06 jyperez nuevo maestro Denominación Cac
            bean.setListaDenominacionCac(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.PE_AGRUPADOR_CAC));
            bean.setHashDenominacionCac(Util.convertToHash(bean.getListaDenominacionCac()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
