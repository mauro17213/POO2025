/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.web.administracion.bean.UsuarioBean;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.administracion.UsuarioRol;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.RolRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.especial.PeUsuariosProgramaRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Correo;
import com.saviasaludeps.savia.web.utilidades.GeneradorContrasena;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author raul-palacios
 */
public class UsuarioServicioImpl extends AccionesBO implements UsuarioServicioIface {

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private RolRemoto getRolRemoto() throws Exception {
        return (RolRemoto) RemotoEJB.getEJBRemoto("RolServicio", RolRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private PeUsuariosProgramaRemoto getPeUsuariosProgramaRemoto() throws Exception {
        return (PeUsuariosProgramaRemoto) RemotoEJB.getEJBRemoto("PeUsuariosProgramaServicio", PeUsuariosProgramaRemoto.class.getName());
    }

    @Override
    public void Accion(UsuarioBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
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
                    restaurarContrasena(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    desbloquearUsuario(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(UsuarioBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getUsuarioRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(UsuarioBean bean) {
        try {
            bean.setObjeto(getUsuarioRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(UsuarioBean bean) {
        try {
            bean.setObjeto(new Usuario());
            bean.getObjeto().setSesiones(bean.getConexion().getEmpresa().getSesionesUsuario() == null ? 0 : bean.getConexion().getEmpresa().getSesionesUsuario().intValue());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(UsuarioBean bean) {
        try {
            bean.setObjeto(getUsuarioRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(UsuarioBean bean) {
        try {
            bean.getObjeto().setContrasena(GeneradorContrasena.getPassword());
            bean.getObjeto().setFechaInicio(new Date());

            //Completar Historicos
            Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
            bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getNombre());
            Maestro area = bean.getHashAreas().get(bean.getObjeto().getMaeAreaId());
            bean.getObjeto().setMaeAreaCodigo(area.getValor());
            bean.getObjeto().setMaeAreaValor(area.getNombre());
            if (bean.getObjeto().getMaeCargoId() != null) {
                Maestro cargo = bean.getHashCargos().get(bean.getObjeto().getMaeCargoId());
                bean.getObjeto().setMaeCargoCodigo(cargo.getValor());
                bean.getObjeto().setMaeCargoValor(cargo.getNombre());
            }

            bean.auditoriaGuardar(bean.getObjeto());
            //carga de lista de roles
            List<UsuarioRol> usuarioRoles = new ArrayList<>();
            for (Integer rolSel : bean.getSeleccionRoles()) {
                UsuarioRol perfil = new UsuarioRol(new Rol(rolSel));
                usuarioRoles.add(perfil);
            }
            bean.getObjeto().setListaRoles(usuarioRoles);
            //Insertar dato
            bean.getObjeto().setId(getUsuarioRemoto().insertar(bean.getObjeto()));

            bean.addMensaje("Se creo un registro de manera exitosa. "
                    + "En los póximos minutos el usuario recibirá un correo "
                    + "con los datos de acceso al sistema");
            //Enviar correo electrónico
            enviarCorreoInsert(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(UsuarioBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());

            //Completar Historicos
            Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
            bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getNombre());
            Maestro area = bean.getHashAreas().get(bean.getObjeto().getMaeAreaId());
            bean.getObjeto().setMaeAreaCodigo(area.getValor());
            bean.getObjeto().setMaeAreaValor(area.getNombre());
            if (bean.getObjeto().getMaeCargoId() != null) {
                Maestro cargo = bean.getHashCargos().get(bean.getObjeto().getMaeCargoId());
                bean.getObjeto().setMaeCargoCodigo(cargo.getValor());
                bean.getObjeto().setMaeCargoValor(cargo.getNombre());
            }

            //carga de lista de roles
            List<UsuarioRol> usuarioRoles = new ArrayList<>();
            for (Integer rolSel : bean.getSeleccionRoles()) {
                UsuarioRol perfil = new UsuarioRol(new Rol(rolSel));
                usuarioRoles.add(perfil);
            }
            bean.getObjeto().setListaRoles(usuarioRoles);
            //Actualizar dato
            getUsuarioRemoto().actualizar(bean.getObjeto());
            cambioDeEstado(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(UsuarioBean bean) {
        try {
            bean.setObjeto(getUsuarioRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void restaurarContrasena(UsuarioBean bean) {
        try {
            Usuario obj = getUsuarioRemoto().consultar(bean.getObjeto().getId());
            if (obj.isBloqueado()) {
                bean.addMensaje("Usuario bloqueado, no se puede restaurar contraseña");
            } else {
                obj.setContrasena(GeneradorContrasena.getPassword());
                bean.setObjeto(obj);
                getUsuarioRemoto().cambioContrasena(bean.getObjeto());
                enviarCorreoReset(bean);
                bean.addMensaje("Se restauro la contraseña de manera exitosa. "
                        + "En los próximos minutos, el usuario recibirá un correo "
                        + "con su nueva contraseña");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void desbloquearUsuario(UsuarioBean bean) {
        try {
            Usuario obj = getUsuarioRemoto().consultar(bean.getObjeto().getId());
            obj.setBloqueado(false);
            obj.setIntentos(0);
            bean.auditoriaModificar(obj);
            bean.setObjeto(obj);
            getUsuarioRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Usuario desbloqueado exitosamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al desbloquear usuario, favor contactar con mesa de servicio");
        }
    }

    private void cargas(UsuarioBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargasInicial(UsuarioBean bean) {
        try {
            bean.setListaRoles(getRolRemoto().consultarTodos());

            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTipoDocumento(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));

            bean.setListaAreas(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AREA));
            bean.setHashAreas(AuConstantes.obtenerHashMaestro(bean.getListaAreas()));

            bean.setListaCargos(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_CARGO));
            bean.setHashCargos(AuConstantes.obtenerHashMaestro(bean.getListaCargos()));

        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    private void enviarCorreoInsert(UsuarioBean bean) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String RequestURI = request.getRequestURI();
        String requiestURL = String.valueOf(request.getRequestURL());
        String ContextPath = request.getContextPath();
        String url = requiestURL.replace(RequestURI, "") + ContextPath + "/";
        String correo = bean.getObjeto().getCorreoElectronico();
        String encabezado = "CONEXIONES SAVIA: Creación de nuevo Usuario";
        String mensaje = "Sr(a) " + bean.getObjeto().getNombre() + "."
                + " El Sistema CONEXIONES SAVIA ha detectado la creación de un nuevo"
                + " Usuario, el cual tiene asignado este correo electrónico."
                + " Sus datos son. Usuario: " + "'" + bean.getObjeto().getUsuario() + "'"
                + " y Contraseña: " + "'" + bean.getObjeto().getContrasena() + "'."
                + " Para acceder al sistema lo puede hacer por la siguiente dirección '" + url + "'";
        //Se arranca el hilo, para que comiencen su ejecución
        new Thread(new Correo(correo, encabezado, mensaje)).start();

    }

    private void enviarCorreoReset(UsuarioBean bean) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String RequestURI = request.getRequestURI();
        String requiestURL = String.valueOf(request.getRequestURL());
        String ContextPath = request.getContextPath();
        String url = requiestURL.replace(RequestURI, "") + ContextPath + "/";
        String correo = bean.getObjeto().getCorreoElectronico();
        String encabezado = "CONEXIONES SAVIA: Restauración contraseña";
        String mensaje = "Sr(a) " + bean.getObjeto().getNombre() + ".'\n\n"
                + " El administrador del Sistema CONEXIONES SAVIA ha restaurado la contraseña"
                + " para el usuario " + "'" + bean.getObjeto().getUsuario() + "'.\n\n "
                + " La nueva contraseña para dicho usuario es " + "'" + bean.getObjeto().getContrasena() + "'" + ".\n\n"
                + " Le recomendamos cambiar la contraseña en su próximo ingreso al sistema.\n\n"
                + " Para acceder al sistema lo puede hacer por la dirección '" + url + "'";
        //Se arranca el hilo, para que comiencen su ejecución
        new Thread(new Correo(correo, encabezado, mensaje)).start();
    }

    private void cambioDeEstado(Usuario usuarioModificado) {
        try {
            if (!usuarioModificado.isActivo()) {
                getPeUsuariosProgramaRemoto().cambiarEstadoUsuario(usuarioModificado);
            }
        } catch (Exception e) {
        }
    }

}
