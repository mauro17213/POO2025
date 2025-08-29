/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Log;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.LogRemoto;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author raul-palacios
 */
public class Url implements Serializable {

    public static final char ACCION_LISTAR = 'L';
    public static final char ACCION_VER = 'V';
    public static final char ACCION_CREAR = 'C';
    public static final char ACCION_GUARDAR = 'G';
    public static final char ACCION_EDITAR = 'E';
    public static final char ACCION_MODIFICAR = 'M';
    public static final char ACCION_BORRAR = 'B';

    public static final char ACCION_ADICIONAL_1 = 'O';
    public static final char ACCION_ADICIONAL_2 = 'P';
    public static final char ACCION_ADICIONAL_3 = 'Q';
    public static final char ACCION_ADICIONAL_4 = 'R';
    public static final char ACCION_ADICIONAL_5 = 'S';
    public static final char ACCION_ADICIONAL_6 = 'T';
    public static final char ACCION_ADICIONAL_7 = 'U';
    public static final char ACCION_ADICIONAL_8 = 'W';
    public static final char ACCION_ADICIONAL_9 = 'X';
    public static final char ACCION_ADICIONAL_10 = 'Y';
    public static final char ACCION_ADICIONAL_11 = 'Z';
    public static final char ACCION_ADICIONAL_12 = 'A';
    public static final char ACCION_ADICIONAL_13 = 'D';
    public static final char ACCION_ADICIONAL_14 = 'F';
    public static final char ACCION_ADICIONAL_15 = 'H';
    public static final char ACCION_ADICIONAL_16 = 'I';
    public static final char ACCION_ADICIONAL_17 = 'J';
    public static final char ACCION_ADICIONAL_18 = 'K';
    public static final char ACCION_ADICIONAL_19 = 'N';

    private ParamConsulta paramConsulta;

    private List<ParamConsulta> listaParamConsultas;

    public Url() {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        this.setConexion((Conexion) session.getAttribute("conexion"));
    }

    private LogRemoto getLogRemoto() throws Exception {
        return (LogRemoto) RemotoEJB.getEJBRemoto("LogServicio", LogRemoto.class.getName());
    }

    private char accion = ACCION_LISTAR;
    private char doAccion;
    private char takeAccion;
    private String url = "";

    private int tamanoPagina = 30;

    private List<String> mensajes = new ArrayList();
    private List<String> errores = new ArrayList();

    private String orden = "";

    private Modulo modulo = null;
    private Conexion conexion = null;

    private String pagina = "";

    public char getAccion() {
        return accion;
    }

    public String getAccionStr() {
        String str = "Listar";
        switch (getAccion()) {
            case ACCION_VER:
                str = "Ver";
                break;
            case ACCION_CREAR:
                str = "Crear";
                break;
            case ACCION_GUARDAR:
                str = "Guardar";
                break;
            case ACCION_EDITAR:
                str = "Editar";
                break;
            case ACCION_MODIFICAR:
                str = "Modificar";
                break;
            case ACCION_BORRAR:
                str = "Borrar";
                break;
        }
        return str;
    }

    public static String getAccionStr(char accion) {
        String str = "Listar";
        switch (accion) {
            case ACCION_VER:
                str = "Ver";
                break;
            case ACCION_CREAR:
                str = "Crear";
                break;
            case ACCION_GUARDAR:
                str = "Guardar";
                break;
            case ACCION_EDITAR:
                str = "Editar";
                break;
            case ACCION_MODIFICAR:
                str = "Modificar";
                break;
            case ACCION_BORRAR:
                str = "Borrar";
                break;
        }
        return str;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }

    public char getDoAccion() {
        return doAccion;
    }

    public void setDoAccion(char doAccion) {
        this.doAccion = doAccion;
    }

    public char getTakeAccion() {
        return takeAccion;
    }

    public void setTakeAccion(char takeAccion) {
        this.takeAccion = takeAccion;
    }

    public int getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(int tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    /**
     * Método para adicionar un nuevo mensaje
     *
     * @param mensaje (String) Texto a adicionar
     */
    public void addMensaje(String mensaje) {
        if (mensaje != null && !mensaje.trim().equals("")) {
            this.mensajes.add(mensaje);
        }
    }

    public List<String> getErrores() {
        return errores;
    }

    /**
     * Método para adicionar un nuevo error
     *
     * @param error (String) Texto a adicionar
     */
    public void addError(String error) {
        if (error != null && !error.trim().equals("")) {
            this.errores.add(error);
        }
    }

    public void addErrores(List<String> lista) {
        for (String msg : lista) {
            addError(msg);
        }
    }

    public void limpiarMensajes() {
        this.mensajes = new ArrayList();
        this.errores = new ArrayList();
    }

    /**
     * Método para validad la existencia de eroores
     *
     * @return (boolean)
     */
    public boolean isError() {
        return (!this.errores.isEmpty());
    }

    public Conexion getConexion() {
        return conexion;
    }

    private void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public boolean isAccionListar() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_LISTAR));
    }

    public boolean isAccionVer() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_VER));
    }

    public boolean isAccionVerReporte() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_VER));
    }

    public boolean isAccionCrear() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_CREAR));
    }

    public boolean isAccionGuardar() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_CREAR));
    }

    public boolean isAccionEditar() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_EDITAR));
    }

    public boolean isAccionModificar() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_EDITAR));
    }

    public boolean isAccionBorrar() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_BORRAR));
    }

    public boolean isAccionAdicional1() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_1));
    }

    public boolean isAccionAdicional2() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_2));
    }

    public boolean isAccionAdicional3() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_3));
    }

    public boolean isAccionAdicional4() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_4));
    }

    public boolean isAccionAdicional5() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_5));
    }

    public boolean isAccionAdicional6() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_6));
    }

    public boolean isAccionAdicional7() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_7));
    }

    public boolean isAccionAdicional8() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_8));
    }

    public boolean isAccionAdicional9() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_9));
    }

    public boolean isAccionAdicional10() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_10));
    }

    public boolean isAccionAdicional11() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_11));
    }
    
    public boolean isAccionAdicional12() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_12));
    }
    
    public boolean isAccionAdicional13() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_13));
    }
    
    public boolean isAccionAdicional14() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_14));
    }
    
    public boolean isAccionAdicional15() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_15));
    }
    
    public boolean isAccionAdicional16() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_16));
    }
    
    public boolean isAccionAdicional17() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_17));
    }
    
    public boolean isAccionAdicional18() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_18));
    }
    
    public boolean isAccionAdicional19() {
        return getModulo().getPrivilegios().contains(String.valueOf(ACCION_ADICIONAL_19));
    }

    public boolean isEstadoListar() {
        return getAccion() == ACCION_LISTAR && isAccionListar();
    }

    public boolean isEstadoVer() {
        return getAccion() == ACCION_VER && isAccionVer();
    }

    public boolean isEstadoCrear() {
        return getAccion() == ACCION_CREAR && isAccionCrear();
    }

    public boolean isEstadoEditar() {
        return getAccion() == ACCION_EDITAR && isAccionEditar();
    }

    public boolean isEstadoBorrar() {
        return getAccion() == ACCION_BORRAR && isAccionBorrar();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Método para realizar la impresión de mensajes en la pantalla.
     */
    protected void generarMensajes() {
        for (String mensaje : this.mensajes) {
            genMensaje(mensaje);
        }
        for (String error : this.errores) {
            genError(error);
        }
        this.mensajes = new ArrayList();
        this.errores = new ArrayList();
    }

    protected void genError(String encabezado) {
        if (encabezado != null && !encabezado.trim().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", encabezado));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "no identificado"));
        }
    }

    protected void genMensaje(String encabezado) {
        if (encabezado != null && !encabezado.trim().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MENSAJE", encabezado));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "MENSAJE", "No identificado"));
        }
    }

    protected Modulo validarModulo(int idModulo) {
        Modulo modRet = null;
        if (getConexion() != null) {
            for (Modulo mod : getConexion().getModulos()) {
                if (mod.getId() == idModulo) {
                    modRet = mod;
                    break;
                }
            }
        }
        return modRet;
    }

    protected void redireccionarHome() {
        redireccionar("/savia/home.faces");
    }

    protected void redireccionar(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
        }
    }

    public void crearLogInicioSesion(Conexion conexion, String descripcion) {
        try {
            Usuario usu = conexion.getUsuario();
            Empresa emp = conexion.getEmpresa();
//            String ip = conexion.getIp();
            String ip = "0.0.0.0";
            try {
                ip = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
            }
            getLogRemoto().insertar(
                    new Log(
                            "Login",
                            "Conexiones",
                            "Sistema",
                            descripcion,
                            new Date(),
                            ip,
                            ip,
                            emp.getNit() + " - " + emp.getNombreComercial(),
                            usu.getUsuarioNombre(),
                            usu.getEmpresa().getNit() + " - " + usu.getEmpresa().getNombreComercial()
                    )
            );
        } catch (Exception ex) {
        }
    }

    /**
     * Método para registrar log de suceso
     *
     * @param descripcion (String) descripción de la acción
     */
    public void crearLog(String descripcion) {
        try {
            Usuario usu = getConexion().getUsuario();
            Empresa emp = getConexion().getEmpresa();
//            String ip = getConexion().getIp();
//        String ip = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            String ip = "0.0.0.0";
            try {
                ip = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
            }
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    descripcion = "Lista de Registros";
                    break;
                case Url.ACCION_CREAR:
                    descripcion = "Creación de Registro";
                    break;
            }
            getLogRemoto().insertar(
                    new Log(
                            getAccionStr(),
                            ((getModulo().getModuloPadre() == null) ? "Conexiones" : getModulo().getModuloPadre().getNombre()),
                            getModulo().getNombre(),
                            descripcion,
                            new Date(),
                            ip,
                            ip,
                            emp.getNit() + " - " + emp.getNombreComercial(),
                            usu.getUsuarioNombre(),
                            usu.getEmpresa().getNit() + " - " + usu.getEmpresa().getNombreComercial()
                    )
            );
        } catch (Exception ex) {
        }
    }

    /**
     * Método para registrar log de suceso
     *
     * @param accion (String) acción descriptiva
     * @param descripcion (String) descripción de la acción
     */
    public void crearLog(String accion, String descripcion) {
        try {
            Usuario usu = getConexion().getUsuario();
            Empresa emp = getConexion().getEmpresa();
//            String ip = getConexion().getIp();
//        String ip = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
            String ip = "0.0.0.0";
            try {
                ip = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
            }
            getLogRemoto().insertar(
                    new Log(
                            accion,
                            ((getModulo().getModuloPadre() == null) ? "Conexiones" : getModulo().getModuloPadre().getNombre()),
                            getModulo().getNombre(),
                            descripcion,
                            new Date(),
                            ip,
                            ip,
                            emp.getNit() + " - " + emp.getNombreComercial(),
                            usu.getUsuarioNombre(),
                            usu.getEmpresa().getNit() + " - " + usu.getEmpresa().getNombreComercial()
                    )
            );
        } catch (Exception ex) {
        }
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public void auditoriaGuardar(Auditoria au) {
        au.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
        au.setTerminalCrea(getConexion().getIp());
        au.setFechaHoraCrea(new Date());
    }

    public void auditoriaModificar(Auditoria au) {
        au.setUsuarioModifica(getConexion().getUsuario().getUsuarioNombre());
        au.setTerminalModifica(getConexion().getIp());
        au.setFechaHoraModifica(new Date());
    }

    public void getAuditoria(String str) {
        if (str != null && !str.equals("")) {
            String[] strs = str.split("&&");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CREACIÓN", strs[0]));
            if (strs.length > 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ÚLTIMA EDICIÓN", strs[1]));
            }
        }
    }

    public ParamConsulta getParamConsulta() {
        if (paramConsulta == null) {
            paramConsulta = new ParamConsulta();
        }
        return paramConsulta;
    }

    public void setParamConsulta(ParamConsulta paramConsulta) {
        this.paramConsulta = paramConsulta;
    }

    public List<ParamConsulta> getListaParamConsultas() {
        if (listaParamConsultas == null) {
            listaParamConsultas = new ArrayList();
        }
        return listaParamConsultas;
    }

    public ParamConsulta getParamConsulta(int pos) {
        if (listaParamConsultas != null) {
            if (pos > listaParamConsultas.size()) {
                return null;
            } else {
                return listaParamConsultas.get(pos);
            }
        } else {
            return null;
        }
    }

    public void setListaParamConsultas(List<ParamConsulta> listaParamConsultas) {
        if (listaParamConsultas == null) {
            this.listaParamConsultas = new ArrayList();
        } else {
            this.listaParamConsultas = listaParamConsultas;
        }
    }

    public void addListaParamConsultas(ParamConsulta paramConsulta) {
        if (listaParamConsultas == null) {
            this.listaParamConsultas = new ArrayList();
        }
        if (paramConsulta != null) {
            this.listaParamConsultas.add(paramConsulta);
        }
    }

}

