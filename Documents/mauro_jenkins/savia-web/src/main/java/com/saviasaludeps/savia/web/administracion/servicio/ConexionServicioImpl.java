/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.GnMensaje;
import com.saviasaludeps.savia.dominio.administracion.GnUsuarioSesion;
import com.saviasaludeps.savia.dominio.administracion.Login;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.dominio.administracion.ModuloVersion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.negocio.administracion.ConexionRemoto;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnMensajeRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnUsuarioSesionRemoto;
import com.saviasaludeps.savia.negocio.administracion.ModuloManualRemoto;
import com.saviasaludeps.savia.negocio.administracion.ModuloRemoto;
import com.saviasaludeps.savia.negocio.administracion.ModuloVersionRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.web.conexion.bean.ContrasenaBean;
import com.saviasaludeps.savia.web.utilidades.Correo;
import com.saviasaludeps.savia.web.utilidades.GeneradorContrasena;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author raul-palacios
 */
public class ConexionServicioImpl implements ConexionServicioIface {

    private ConexionRemoto getConexionRemoto() throws Exception {
        return (ConexionRemoto) RemotoEJB.getEJBRemoto("ConexionServicio", ConexionRemoto.class.getName());
    }

    private ModuloRemoto getModuloRemoto() throws Exception {
        return (ModuloRemoto) RemotoEJB.getEJBRemoto("ModuloServicio", ModuloRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private GnUsuarioSesionRemoto getGnUsuarioSesionRemoto() throws Exception {
        return (GnUsuarioSesionRemoto) RemotoEJB.getEJBRemoto("GnUsuarioSesionServicio", GnUsuarioSesionRemoto.class.getName());
    }

    private ModuloVersionRemoto getModuloVersionRemoto() throws Exception {
        return (ModuloVersionRemoto) RemotoEJB.getEJBRemoto("ModuloVersionServicio", ModuloVersionRemoto.class.getName());
    }

    private ModuloManualRemoto getModuloManualRemoto() throws Exception {
        return (ModuloManualRemoto) RemotoEJB.getEJBRemoto("ModuloManualServicio", ModuloManualRemoto.class.getName());
    }

    private GnMensajeRemoto getGnMensajeRemoto() throws Exception {
        return (GnMensajeRemoto) RemotoEJB.getEJBRemoto("GnMensajeServicio", GnMensajeRemoto.class.getName());
    }

    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }

    @Override
    public Login validaConexion(Login obj) throws Exception {
        Usuario usu = getConexionRemoto().validarConexion(obj);
        String usuario = obj.getUsuario().getUsuario();
        obj = new Login();
        try {
            obj.setUsuario(new Usuario());
            obj.getUsuario().setEmpresa(new Empresa());
            obj.setEmpresa(new Empresa());
            if (usu != null) {//Conectado
                obj.setUsuario(usu);
                usu.setIntentos(0);
                usu.setBloqueado(false);
                obj.setEmpresa(usu.getEmpresa());
                obj.setConectado(true);
                getUsuarioRemoto().registroConexion(usu);
            } else {//No conectado
                usu = getUsuarioRemoto().consultarPorUsuario(usuario);
                if (usu != null) {
                    //Usuario NO bloqueado
                    if (!usu.isBloqueado()) {
                        usu.setIntentos(usu.getIntentos() + 1);
                        int maximos = Integer.parseInt(PropApl.getInstance().get(PropApl.GN_LOGIN_INTENTOS));
                        if (usu.getIntentos() >= maximos) {
                            usu.setBloqueado(true);
                            usu.setIntentos(0);
                        }
                        getUsuarioRemoto().actualizar(usu);
                    }
                    obj.setUsuario(usu);
                }
                obj.setConectado(false);
            }
        } catch (Exception e) {
            throw new Exception("Error en la validación: " + e.getMessage());
        }
        return obj;
    }

    @Override
    public List<Empresa> consultarEmpresasActivas() throws Exception {
        List<Empresa> list = getEmpresaRemoto().consultarActivas();
        return list;
    }

    @Override
    public Empresa cambiarEmpresa(Empresa obj) {
        Empresa objResp = null;
        try {
            objResp = getEmpresaRemoto().consultar(obj.getId());
        } catch (Exception ex) {
        }
        if (!obj.isActiva()) {
            objResp = null;
        }
        return objResp;
    }

    @Override
    public List<Modulo> consultarModulosPermiso(Usuario obj) {
        List<Modulo> lista = new ArrayList();
        try {
            for (Modulo mod : getModuloRemoto().consultarModulosPorUsuario(obj)) {
                Modulo _obj = new Modulo();
                if (mod.getModuloPadre() == null) {
                    _obj.setModuloPadre(null);
                } else {
                    _obj.setModuloPadre(new Modulo(mod.getModuloPadre().getId(), mod.getModuloPadre().getNombre()));
                }
                _obj.setId(mod.getId());
                _obj.setTipo(mod.getTipo());
                _obj.setNombre(mod.getNombre());
                _obj.setDescripcion(mod.getDescripcion());
                _obj.setActivo(mod.isActivo());
                _obj.setOrden(mod.getOrden());
                _obj.setPrivilegios(mod.getPrivilegios());
                _obj.setUrl(mod.getUrl());
                _obj.setVersion(mod.getVersion());
                _obj.setFechaVersion(mod.getFechaVersion());
                lista.add(_obj);
            }
        } catch (Exception ex) {
//            Logger.getLogger(ConexionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Modulo consultarArbolModuloPorUsuario(Usuario obj) {
        Modulo mod;
        try {
            mod = getModuloRemoto().consultarArbolModuloPorUsuario(obj);
        } catch (Exception ex) {
            mod = null;
        }
        return mod;
    }

    @Override
    public void cambiarContrasena(ContrasenaBean bean) {
        try {
            if (bean.getContrasenaActual().trim().equals("")
                    || bean.getContrasenaNueva1().trim().equals("")
                    || bean.getContrasenaNueva2().trim().equals("")) {
                throw new Exception("Todos los campos son obligatorios");
            }
            String nuevaContrasena1 = bean.getContrasenaNueva1();
            String nuevaContrasena2 = bean.getContrasenaNueva2();
            if (!nuevaContrasena1.equals(nuevaContrasena2)) {
                throw new Exception("La nueva contraseña y su verificación no coinciden");
            }
            Usuario obj = new Usuario();
            obj.setId(bean.getIdUsuario());
            obj.setContrasena(bean.getContrasenaActual());
            obj = getUsuarioRemoto().validarUsuario(obj);
            if (obj != null) {
                obj.setContrasena(nuevaContrasena1);
                getUsuarioRemoto().cambioContrasena(obj);
            } else {
                throw new Exception("Contraseña inválida");
            }
            bean.addMensaje("Se cambio la contraseña de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public boolean restaurarContrasena(Login obj) throws Exception {
        Usuario usu = getUsuarioRemoto().consultarPorUsuario(obj.getUsuario().getUsuario());
        //Usuario NO bloqueado
        if (!usu.isBloqueado()) {
            usu.setContrasena(GeneradorContrasena.getPassword());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String RequestURI = request.getRequestURI();
            String requiestURL = String.valueOf(request.getRequestURL());
            String ContextPath = request.getContextPath();
            String url = requiestURL.replace(RequestURI, "") + ContextPath + "/";
            String correo = usu.getCorreoElectronico();
            String encabezado = "Sistema Savia: Restauración de contraseña";
            String mensaje = "Señor " + usu.getNombre() + ". Su contraseña, la cual corresponde al usuario " + usu.getUsuario() + ", ha sido restablecida. Su nueva contraseña es :" + usu.getContrasena();
            getUsuarioRemoto().cambioContrasena(usu);
            new Thread(new Correo(correo, encabezado, mensaje)).start();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ModuloVersion consultarVersion() throws Exception {
        return getModuloVersionRemoto().consultarActualByModulo(1);
    }

    @Override
    public ModuloManual consultarManual(int id, int tipo) throws Exception {
        return getModuloManualRemoto().consultarXModulo(id, true, tipo);
    }

    @Override
    public List<GnMensaje> consultarMensajes(int idEmpresa) throws Exception {
        return getGnMensajeRemoto().consultarPorEmpresaUsuario(idEmpresa);
    }

    @Override
    public List<GnAlerta> consultarAlertas(int idUsuario) throws Exception {
        return getGnAlertaRemoto().consultaPorIdUsuarioNoDescartadas(idUsuario);
    }

    @Override
    public void actualizarAlerta(GnAlerta alerta) throws Exception {
        getGnAlertaRemoto().actualizar(alerta);
    }

    @Override
    public GnMensaje consultarMensaje(int idMensaje) throws Exception {
        try {
            GnMensaje mensaje = getGnMensajeRemoto().consultar(idMensaje);
            return mensaje;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean actualizarSesion(String idSesion, String ip, int idUsuario, int maximo) {
        boolean validar = false;
        try {
            List<GnUsuarioSesion> sesiones = getGnUsuarioSesionRemoto().listarSesionesUsuario(idUsuario);
            if (maximo > 0) {
                if (sesiones.isEmpty()) {
                    validar = true;
                } else {
                    if (sesiones.size() >= maximo) {
                        int lastPosition = sesiones.size() - 1;
                        GnUsuarioSesion sesion = sesiones.get(lastPosition);
                        sesion.setActiva(false);
                        sesion.setFechaHoraFin(new Date());
                        getGnUsuarioSesionRemoto().actualizar(sesion);
                        validar = false;
                    } else {
                        validar = true;
                    }
                }
            } else {
                validar = true;
            }
            GnUsuarioSesion sesion = new GnUsuarioSesion();
            sesion.setFechaHoraInicio(new Date());
            sesion.setFechaHoraUltimaGestion(new Date());
            sesion.setIdSesion(idSesion);
            sesion.setIpServidor(ip);
            sesion.setGnUsuarioId(new Usuario(idUsuario));
            sesion.setActiva(true);
            getGnUsuarioSesionRemoto().insertar(sesion);
        } catch (Exception e) {
        }
        return validar;
    }

    @Override
    public void cerrarSesion(String idSesion, String ip) {
        try {
            GnUsuarioSesion sesion = getGnUsuarioSesionRemoto().consultarPorIdYIp(idSesion, ip);
            if (sesion != null) {
                sesion.setActiva(false);
                sesion.setFechaHoraFin(new Date());
                getGnUsuarioSesionRemoto().actualizar(sesion);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean existeSesion(String idSesion, int idUser) {
        boolean existe = false;
        try {
            List<GnUsuarioSesion> sesiones = getGnUsuarioSesionRemoto().consultarPorIdSesion(idSesion);
            for (GnUsuarioSesion sesion : sesiones) {
                existe = true;
                sesion.setActiva(false);
                sesion.setFechaHoraFin(new Date());
                getGnUsuarioSesionRemoto().actualizar(sesion);
            }
            return existe;
        } catch (Exception e) {
            return existe;
        }
    }

}
