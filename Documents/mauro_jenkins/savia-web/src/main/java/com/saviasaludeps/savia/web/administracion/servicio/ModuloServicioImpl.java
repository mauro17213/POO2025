package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.ModuloBean;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.negocio.administracion.ModuloManualRemoto;
import com.saviasaludeps.savia.negocio.administracion.ModuloRemoto;
import com.saviasaludeps.savia.negocio.administracion.ModuloVersionRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author raul-palacios
 */
public class ModuloServicioImpl extends AccionesBO implements ModuloServicioIface {

    private ModuloRemoto getModuloRemoto() throws Exception {
        return (ModuloRemoto) RemotoEJB.getEJBRemoto("ModuloServicio", ModuloRemoto.class.getName());
    }

    private ModuloVersionRemoto getModuloVersionRemoto() throws Exception {
        return (ModuloVersionRemoto) RemotoEJB.getEJBRemoto("ModuloVersionServicio", ModuloVersionRemoto.class.getName());
    }

    private ModuloManualRemoto getModuloManualRemoto() throws Exception {
        return (ModuloManualRemoto) RemotoEJB.getEJBRemoto("ModuloManualServicio", ModuloManualRemoto.class.getName());
    }

    @Override
    public void Accion(ModuloBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarModuloVersion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarVersion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarModuloManual(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            guardarManual(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarManualActual(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            guardarManualIPS(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarManualActualIPS(bean);
                            break;
                    }
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(ModuloBean bean) {
        try {
            Modulo modulo = getModuloRemoto().consultarArbolModulo();
            List<Modulo> listModulo = new ArrayList<>();
            listModulo.add(modulo);
            Modulo aplicativoModulos = new Modulo();
            aplicativoModulos.setModulosHijos(listModulo);
            bean.setRaizArbol(construirTodosArbol(aplicativoModulos, null));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ModuloBean bean) {
        try {
            bean.setObjeto(getModuloRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ModuloBean bean) {
        try {
            bean.setObjeto(getModuloRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ModuloBean bean) {
        try {
            getModuloRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(ModuloBean bean) {
        switch (bean.getAccion()) {
            case Url.ACCION_LISTAR:
                break;
            case Url.ACCION_VER:
                break;
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                bean.setModulosPadres(padres());
                break;
            default:
                break;
        }
    }

    public List<Modulo> padres() {
        List<Modulo> padres = new ArrayList();
        try {
            for (Modulo _obj : getModuloRemoto().consultarPorTipo(Modulo.TIPO_APLICACION)) {
                padres.add(_obj);
            }
            for (Modulo _obj : getModuloRemoto().consultarPorTipo(Modulo.TIPO_MODULO)) {
                padres.add(_obj);
            }
        } catch (Exception e) {
        }
        return padres;
    }

    /**
     * Método para realizar la construcción de un argol de menú tipo TreeNode
     *
     * @param raiz
     * @param padre
     * @return
     * @throws Exception
     */
    private TreeNode construirTodosArbol(Modulo raiz, TreeNode padre) throws Exception {
        TreeNode nuevoNodo = new DefaultTreeNode(raiz, padre);
        for (Modulo moduloNodo : raiz.getModulosHijos()) {
            construirTodosArbol(moduloNodo, nuevoNodo);
        }
        return nuevoNodo;
    }

    private void listarModuloVersion(ModuloBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getModuloVersionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setListModuloVersion(getModuloVersionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarVersion(ModuloBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoModVer());
            bean.getObjetoModVer().setId(getModuloVersionRemoto().insertar(bean.getObjetoModVer()));
            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarModuloManual(ModuloBean bean) {
        try {
            bean.getParamConsultaManual().setCantidadRegistros(getModuloManualRemoto().consultarCantidadLista(bean.getParamConsultaManual()));
            bean.setListModuloManual(getModuloManualRemoto().consultarLista(bean.getParamConsultaManual()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarManual(ModuloBean bean) {

        try {
            String ruta = PropApl.getInstance().get(PropApl.GN_RUTA_MANUALES_AYUDA);
            Modulo modulo = getModuloRemoto().consultar(bean.getObjetoModMan().getModulo().getId());

            if (ruta == null || ruta.isEmpty()) {
                throw new Exception("No esta configurada la ruta para los manuales del sistema");
            }

            if (modulo == null) {
                throw new Exception("No se encontro el modulo.");
            }

            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo
                    .append(modulo.getModuloPadre() == null ? modulo.getNombre() : modulo.getModuloPadre().getNombre())
                    .append("_")
                    .append(modulo.getNombre())
                    .append("_")
                    .append(bean.getObjetoModMan().getVersion().replace(".", "-"))
                    .append("_")
                    .append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            bean.getObjetoModMan().setNombre(nombreArchivo.toString());
            bean.getObjetoModMan().setRuta(ruta);
            bean.getObjetoModMan().setArchivo(nombreArchivo.append(bean.getObjetoModMan().getExtensión()).toString());
            //Guardar manual Interno
            File archivo = new File(ruta, bean.getObjetoModMan().getArchivo());
            OutputStream destino = new FileOutputStream(archivo);
            IOUtils.copy(bean.getObjetoModMan().getAdjuntoStream(), destino);
            IOUtils.closeQuietly(bean.getObjetoModMan().getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            bean.auditoriaGuardar(bean.getObjetoModMan());
            getModuloManualRemoto().actualizarXModulo(modulo.getId(), ModuloManual.TIPO_MANUAL_INTERNO);
            getModuloManualRemoto().insertar(bean.getObjetoModMan());
            bean.addMensaje("El registro se creo correctamente");

        } catch (FileNotFoundException ex) {
            bean.addError("Error al subir un adjunto. " + ex.getMessage());
        } catch (Exception ex) {
            bean.addError("Se presento un error al momento de guardar. " + ex.getMessage());
        }
    }

    private void guardarManualIPS(ModuloBean bean) {

        try {
            String ruta = PropApl.getInstance().get(PropApl.GN_RUTA_MANUALES_AYUDA);
            Modulo modulo = getModuloRemoto().consultar(bean.getObjetoModMan().getModulo().getId());

            if (ruta == null || ruta.isEmpty()) {
                throw new Exception("No esta configurada la ruta para los manuales del sistema");
            }

            if (modulo == null) {
                throw new Exception("No se encontro el modulo.");
            }

            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo
                    .append(modulo.getModuloPadre() == null ? modulo.getNombre() : modulo.getModuloPadre().getNombre())
                    .append("_")
                    .append(modulo.getNombre())
                    .append("_")
                    .append(bean.getObjetoModMan().getVersion().replace(".", "-"))
                    .append("_")
                    .append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            bean.getObjetoModMan().setNombre(nombreArchivo.toString());
            bean.getObjetoModMan().setRuta(ruta);
            bean.getObjetoModMan().setArchivo(nombreArchivo.append(bean.getObjetoModMan().getExtensión()).toString());
            //Guardar manual Interno
            File archivo = new File(ruta, bean.getObjetoModMan().getArchivo());
            OutputStream destino = new FileOutputStream(archivo);
            IOUtils.copy(bean.getObjetoModMan().getAdjuntoStream(), destino);
            IOUtils.closeQuietly(bean.getObjetoModMan().getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            bean.auditoriaGuardar(bean.getObjetoModMan());
            getModuloManualRemoto().actualizarXModulo(modulo.getId(), ModuloManual.TIPO_MANUAL_EXTERNO);
            getModuloManualRemoto().insertar(bean.getObjetoModMan());
            bean.addMensaje("El registro se creo correctamente");

        } catch (FileNotFoundException ex) {
            bean.addError("Error al subir un adjunto. " + ex.getMessage());
        } catch (Exception ex) {
            bean.addError("Se presento un error al momento de guardar. " + ex.getMessage());
        }
    }

    private void modificarManualActual(ModuloBean bean) {
        try {
            ModuloManual moduloManual = getModuloManualRemoto().consultar(bean.getObjetoModMan().getId());
            getModuloManualRemoto().actualizarXModulo(moduloManual.getModulo().getId(), ModuloManual.TIPO_MANUAL_INTERNO);
            getModuloManualRemoto().actualizarActual(bean.getObjetoModMan().getId(), bean.getObjetoModMan().isActual());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarManualActualIPS(ModuloBean bean) {
        try {
            ModuloManual moduloManual = getModuloManualRemoto().consultar(bean.getObjetoModMan().getId());
            getModuloManualRemoto().actualizarXModulo(moduloManual.getModulo().getId(), ModuloManual.TIPO_MANUAL_EXTERNO);
            getModuloManualRemoto().actualizarActual(bean.getObjetoModMan().getId(), bean.getObjetoModMan().isActual());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
