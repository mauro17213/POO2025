/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.bean;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionComentario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionHistorico;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatServicioUmbral;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiempo;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.web.gestion.atencion.servicio.GatAtencionServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class GatAtencionBean extends Url {

    @Autowired
    private GatAtencionServicioIface gatAtencionServicio;
    private GatAtencion objeto;
    private GatSedeTaquilla objetoTaquilla;
    private List<GatAtencion> listaAtenciones;
    private List<GnSede> listaSedes;
    private GnSede objetoSede;
    private GatTiquete objetoTiquete;
    private GatAtencionComentario objetoComentario;
    private GatAtencionHistorico objetoServicio;
    private GatSedeFuncionario objetoFuncionario;
    private List<Maestro> listaTipoServicio;
    private HashMap<Integer, Maestro> hashTipoServicio;
    private List<Maestro> listaTiposDocumentos;
    private HashMap<Integer, Maestro> hashTipoDocumentos;

    //Variables auxiliares
    private List<GatServicioUmbral> listaServicios;
    private List<GatServicioUmbral> listaResposos;
    private List<GatSedeTaquilla> listaTaquillasOcupadas;
    private boolean salir;
    private Integer idTaquillaTransferir;
    private int llamadosMaximos;
    private GatTiempo objReposo;
    private int totalAtendidos;
    private int totalSobreAtendidos;
    private int totalEspera;
    private int totalAbandonados;
    private int totalReposos;
    private Date fechaMaxima;
    private boolean mostrarDatosUsuario;
    private int minutosReposo;
    private int segundosReposo;
    private boolean editarDatosUsuario;

    public GatAtencionBean() {
        try {
            this.objeto = new GatAtencion();
            Modulo _mod = super.validarModulo(Modulo.ID_GAT_ATENCION);
            if (_mod == null) {
                super.redireccionar("/savia/home.faces");
            } else {
                super.setModulo(_mod);
            }
        } catch (Exception e) {
            super.redireccionar("/savia/home.faces");
        }

    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
        getGatAtencionServicio().cargasInicial(this);
        if (isSalir()) {
            addError("No esta disponible en este horario");
            generarMensajes();
            salir();
        } else if (getObjReposo() != null) {
            Date fechaActual = new Date();
            Long tiempoEnSegundos = TimeUnit.MILLISECONDS.toSeconds(fechaActual.getTime() - getObjReposo().getFechaInicio().getTime());
            setMinutosReposo(tiempoEnSegundos.intValue() / 60);
            setSegundosReposo(tiempoEnSegundos.intValue() % 60);
            super.setAccion(Url.ACCION_ADICIONAL_6);
            super.setDoAccion(Url.ACCION_GUARDAR);
            procesoFinal();
        } else {
            listar();
        }
    }

    public GatAtencionServicioIface getGatAtencionServicio() {
        return gatAtencionServicio;
    }

    public void setGatAtencionServicio(GatAtencionServicioIface gatAtencionServicio) {
        this.gatAtencionServicio = gatAtencionServicio;
    }

    public GatAtencion getObjeto() {
        return objeto;
    }

    public void setObjeto(GatAtencion objeto) {
        this.objeto = objeto;
    }

    public GatSedeTaquilla getObjetoTaquilla() {
        return objetoTaquilla;
    }

    public void setObjetoTaquilla(GatSedeTaquilla objetoTaquilla) {
        this.objetoTaquilla = objetoTaquilla;
    }

    public List<GatAtencion> getListaAtenciones() {
        return listaAtenciones;
    }

    public void setListaAtenciones(List<GatAtencion> listaAtenciones) {
        this.listaAtenciones = listaAtenciones;
    }

    public List<GatServicioUmbral> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<GatServicioUmbral> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<GnSede> getListaSedes() {
        return listaSedes;
    }

    public void setListaSedes(List<GnSede> listaSedes) {
        this.listaSedes = listaSedes;
    }

    public GnSede getObjetoSede() {
        return objetoSede;
    }

    public void setObjetoSede(GnSede objetoSede) {
        this.objetoSede = objetoSede;
    }

    public GatTiquete getObjetoTiquete() {
        return objetoTiquete;
    }

    public void setObjetoTiquete(GatTiquete objetoTiquete) {
        this.objetoTiquete = objetoTiquete;
    }

    public GatAtencionComentario getObjetoComentario() {
        return objetoComentario;
    }

    public void setObjetoComentario(GatAtencionComentario objetoComentario) {
        this.objetoComentario = objetoComentario;
    }

    public GatAtencionHistorico getObjetoServicio() {
        return objetoServicio;
    }

    public void setObjetoServicio(GatAtencionHistorico objetoServicio) {
        this.objetoServicio = objetoServicio;
    }

    public GatSedeFuncionario getObjetoFuncionario() {
        return objetoFuncionario;
    }

    public void setObjetoFuncionario(GatSedeFuncionario objetoFuncionario) {
        this.objetoFuncionario = objetoFuncionario;
    }

    public List<Maestro> getListaTipoServicio() {
        return listaTipoServicio;
    }

    public void setListaTipoServicio(List<Maestro> listaTipoServicio) {
        this.listaTipoServicio = listaTipoServicio;
    }

    public HashMap<Integer, Maestro> getHashTipoServicio() {
        return hashTipoServicio;
    }

    public void setHashTipoServicio(HashMap<Integer, Maestro> hashTipoServicio) {
        this.hashTipoServicio = hashTipoServicio;
    }

    public List<Maestro> getListaTiposDocumentos() {
        return listaTiposDocumentos;
    }

    public void setListaTiposDocumentos(List<Maestro> listaTiposDocumentos) {
        this.listaTiposDocumentos = listaTiposDocumentos;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentos() {
        return hashTipoDocumentos;
    }

    public void setHashTipoDocumentos(HashMap<Integer, Maestro> hashTipoDocumentos) {
        this.hashTipoDocumentos = hashTipoDocumentos;
    }

    public List<GatSedeTaquilla> getListaTaquillasOcupadas() {
        return listaTaquillasOcupadas;
    }

    public void setListaTaquillasOcupadas(List<GatSedeTaquilla> listaTaquillasOcupadas) {
        this.listaTaquillasOcupadas = listaTaquillasOcupadas;
    }

    public boolean isSalir() {
        return salir;
    }

    public void setSalir(boolean salir) {
        this.salir = salir;
    }

    public Integer getIdTaquillaTransferir() {
        return idTaquillaTransferir;
    }

    public void setIdTaquillaTransferir(Integer idTaquillaTransferir) {
        this.idTaquillaTransferir = idTaquillaTransferir;
    }

    public int getLlamadosMaximos() {
        return llamadosMaximos;
    }

    public void setLlamadosMaximos(int llamadosMaximos) {
        this.llamadosMaximos = llamadosMaximos;
    }

    public List<GatServicioUmbral> getListaResposos() {
        return listaResposos;
    }

    public void setListaResposos(List<GatServicioUmbral> listaResposos) {
        this.listaResposos = listaResposos;
    }

    public GatTiempo getObjReposo() {
        return objReposo;
    }

    public void setObjReposo(GatTiempo objReposo) {
        this.objReposo = objReposo;
    }

    public int getTotalAtendidos() {
        return totalAtendidos;
    }

    public void setTotalAtendidos(int totalAtendidos) {
        this.totalAtendidos = totalAtendidos;
    }

    public int getTotalSobreAtendidos() {
        return totalSobreAtendidos;
    }

    public void setTotalSobreAtendidos(int totalSobreAtendidos) {
        this.totalSobreAtendidos = totalSobreAtendidos;
    }

    public int getTotalEspera() {
        return totalEspera;
    }

    public void setTotalEspera(int totalEspera) {
        this.totalEspera = totalEspera;
    }

    public int getTotalAbandonados() {
        return totalAbandonados;
    }

    public void setTotalAbandonados(int totalAbandonados) {
        this.totalAbandonados = totalAbandonados;
    }

    public int getTotalReposos() {
        return totalReposos;
    }

    public void setTotalReposos(int totalReposos) {
        this.totalReposos = totalReposos;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public boolean isMostrarDatosUsuario() {
        return mostrarDatosUsuario;
    }

    public void setMostrarDatosUsuario(boolean mostrarDatosUsuario) {
        this.mostrarDatosUsuario = mostrarDatosUsuario;
    }

    public int getMinutosReposo() {
        return minutosReposo;
    }

    public void setMinutosReposo(int minutosReposo) {
        this.minutosReposo = minutosReposo;
    }

    public int getSegundosReposo() {
        return segundosReposo;
    }

    public void setSegundosReposo(int segundosReposo) {
        this.segundosReposo = segundosReposo;
    }

    public boolean isEditarDatosUsuario() {
        return editarDatosUsuario;
    }

    public void setEditarDatosUsuario(boolean editarDatosUsuario) {
        this.editarDatosUsuario = editarDatosUsuario;
    }

    public void listar() {
        refrescar();
        procesoFinal();
    }

    public void actualizarTabla() {
        PrimeFaces.current().ajax().update("frmAtenciones:tablaAtenciones");
    }

    public void refrescar() {
        if (getObjetoTaquilla().getId() == null) {
            taquillaCrear();
        } else {
            super.setAccion(Url.ACCION_LISTAR);
            super.setDoAccion(Url.ACCION_LISTAR);
            getGatAtencionServicio().Accion(this);
        }
    }

    public void taquillaCrear() {
        setObjetoSede(new GnSede());
        getObjetoSede().setGatSedeTaquillaList(new ArrayList<>());
        setObjetoTaquilla(new GatSedeTaquilla());
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_CREAR);
        procesoFinal();
    }

    public void taquillaGuardar() {
        for (GatSedeTaquilla taquilla : getObjetoSede().getGatSedeTaquillaList()) {
            if (taquilla.getId().equals(getObjetoTaquilla().getId())) {
                setObjetoTaquilla(taquilla);
                getObjetoTaquilla().setUsuarioId(getConexion().getUsuario());
                getObjetoTaquilla().setSeleccionada(true);
                break;
            }
        }
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void sedeSeleccionar() {
        for (GnSede sede : getListaSedes()) {
            if (sede.getId().equals(getObjetoSede().getId())) {
                setObjetoSede(sede);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmSedes:pSedes");
    }

    public void taquillaSalir() {
        super.redireccionar("/savia/home.faces");
    }

    public void taquillaAbandonar() {
        if (listaAtenciones.isEmpty()) {
            super.setAccion(Url.ACCION_LISTAR);
            super.setDoAccion(Url.ACCION_BORRAR);
            getGatAtencionServicio().Accion(this);
            procesoFinal();
//            refrescar();
//            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
        } else {
            addError("Para abandonar taquilla no debe tener atenciones en proceso.");
            generarMensajes();
        }
    }

    public void llamar(int idTiquete) {
        setObjetoTiquete(new GatTiquete(idTiquete));
        super.setAccion(ACCION_ADICIONAL_5);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void rellamar(int idAtencion) {
        setObjeto(new GatAtencion(idAtencion));
        super.setAccion(Url.ACCION_ADICIONAL_2);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void abandonar(int idAtencion) {
        setObjeto(new GatAtencion(idAtencion));
        super.setAccion(Url.ACCION_ADICIONAL_4);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void gestionEditar(int idAtencion) {
        setMostrarDatosUsuario(false);
        setObjeto(new GatAtencion(idAtencion));
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_EDITAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void gestionModificar() {
        if (validarDatosUsuario()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_MODIFICAR);
            getGatAtencionServicio().Accion(this);
//            PrimeFaces.current().executeScript("PF('pollGestionar').stop()");
        }
        procesoFinal();
    }

    public void gestionFinalizar() {
        if (validarDatosUsuario()) {
            getObjeto().setFinalizo(true);
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_MODIFICAR);
            getGatAtencionServicio().Accion(this);
            if (this.getErrores().isEmpty()) {
                this.getMensajes().clear();
                this.addMensaje("Se finalizó la atención de manera exitosa");
//                PrimeFaces.current().executeScript("PF('pollGestionar').start()");
            }
        }
        procesoFinal();
    }

    public void gestionSalir() {
//        if (validarDatosUsuario()) {
//            super.setAccion(Url.ACCION_ADICIONAL_1);
//            super.setDoAccion(Url.ACCION_MODIFICAR);
//            getGatAtencionServicio().Accion(this);
//            if (this.getErrores().isEmpty()) {
        this.getMensajes().clear();
        PrimeFaces.current().executeScript("PF('pollGestionar').start()");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
//            }
        refrescar();
//        }
        procesoFinal();
    }

    public void comentarioCrear() {
        setObjetoComentario(new GatAtencionComentario());
        PrimeFaces.current().ajax().update("frmComentario");
        PrimeFaces.current().executeScript("PF('dialogoComentario').show()");
    }

    public void comentarioGuardar() {
        getObjeto().getGatListaComentarios().add(getObjetoComentario());
        this.auditoriaGuardar(getObjetoComentario());
        PrimeFaces.current().ajax().update("frmGestionar:pComentarios");
        PrimeFaces.current().executeScript("PF('dialogoComentario').hide()");
    }

    public void servicioCrear() {
        setObjetoServicio(new GatAtencionHistorico());
        getObjetoServicio().setFechaHoraInicio(new Date());
        PrimeFaces.current().ajax().update("frmServicio");
        PrimeFaces.current().executeScript("PF('dialogoServicio').show()");
    }

    public void servicioGuardar() {
        getObjetoServicio().setMaeTipoServicioId(getObjetoTiquete().getMaeTipoServicio());
        getObjetoServicio().setMaeTipoServicioCodigo(getObjetoTiquete().getMaeTipoServicioCodigo());
        getObjetoServicio().setMaeTipoServicioValor(getObjetoTiquete().getMaeTipoServicioValor());
        getObjetoServicio().setFechaHoraFin(new Date());
        long diff = getObjetoServicio().getFechaHoraFin().getTime() - getObjetoServicio().getFechaHoraInicio().getTime();
        Long segundos = TimeUnit.MILLISECONDS.toSeconds(diff);
        getObjetoServicio().setTiempo(segundos.intValue());
        getObjeto().getGatListaHistoricos().add(getObjetoServicio());
        this.auditoriaGuardar(getObjetoServicio());
        PrimeFaces.current().ajax().update("frmGestionar:pServicios");
        PrimeFaces.current().executeScript("PF('dialogoServicio').hide()");
    }

    public void transferenciaCrear(int idAtencion) {
        setObjeto(new GatAtencion(idAtencion));
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_CREAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void transferenciaGuardar() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void salir() {
        super.redireccionar("/savia/home.faces");
    }

    public Maestro obtenerTipoServicio(int id) {
        for (Maestro tipo : getListaTipoServicio()) {
            if (tipo.getId().equals(id)) {
                return tipo;
            }
        }
        return null;
    }

    public String calcularTiempo(Date fecha) {
        if (fecha == null) {
            return "0 min 0 seg";
        } else {
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime() - fecha.getTime();
            long segundosEnMilisegundos = 1000;
            long minutosEnMilisegundos = segundosEnMilisegundos * 60;
            long tiempoEnMinutos = diferencia / minutosEnMilisegundos;
            diferencia %= minutosEnMilisegundos;
            long tiempoEnSegundos = diferencia / segundosEnMilisegundos;
            return tiempoEnMinutos + " min " + tiempoEnSegundos + " seg";
        }
    }

    public String calcularTiempoAt(Date fecha, String numero) {
        if (fecha == null) {
            return "0 min 0 seg";
        } else {
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime() - fecha.getTime();
            long segundosEnMilisegundos = 1000;
            long minutosEnMilisegundos = segundosEnMilisegundos * 60;
            long tiempoEnMinutos = diferencia / minutosEnMilisegundos;
            diferencia %= minutosEnMilisegundos;
            long tiempoEnSegundos = diferencia / segundosEnMilisegundos;
            if (tiempoEnMinutos >= 5) {
                if (!getObjeto().isFueraTiempo()) {
                    this.getObjeto().setFueraTiempo(true);
//                    this.addMensaje("El turno " + numero + " ha superado el tiempo de atención.");
                }
            } else {
                this.getObjeto().setFueraTiempo(false);
            }
            return tiempoEnMinutos + " min " + tiempoEnSegundos + " seg";
        }
    }

    public Integer calcularTimepoAtencion(Date fecha) {
        if (fecha == null) {
            return 0;
        } else {
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime() - fecha.getTime();
            long segundosEnMilisegundos = 1000;
            long minutosEnMilisegundos = segundosEnMilisegundos * 60;
            Long tiempoEnMinutos = diferencia / minutosEnMilisegundos;
            return tiempoEnMinutos.intValue();
        }
    }

    public void turnoCrear() {
        super.setAccion(Url.ACCION_CREAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void turnoGuardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void turnoSolicitar() {
        super.setAccion(Url.ACCION_MODIFICAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void buscarAfiliado() {
        if (getObjetoTiquete().getGatUsuario().getMaeTipoDocumentoId() > 0 && getObjetoTiquete().getGatUsuario().getNumeroDocumento() != null) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_LISTAR);
            getGatAtencionServicio().Accion(this);
            PrimeFaces.current().ajax().update("frmCrearTurno");
            this.generarMensajes();
        }
    }

    public void buscarAfiliadoGestionar() {
        if (getObjeto().getGatUsuario().getMaeTipoDocumentoId() > 0 && getObjeto().getGatUsuario().getNumeroDocumento() != null) {
            getGatAtencionServicio().buscarAfiliado(this);
            PrimeFaces.current().ajax().update("frmGestionar:pDatosCrear");
            this.generarMensajes();
        }
    }

    public String obtenerUmbral() {
        String tiempo = "10 min";
        if (getListaServicios() != null) {
            for (GatServicioUmbral umbral : getListaServicios()) {
                if (umbral.getMaeTipoServicioId() == getObjetoTiquete().getMaeTipoServicio()) {
                    tiempo = umbral.getTiempo() + " min";
                    break;
                }
            }
        }
        return tiempo;
    }

    public void cerrarTransferir() {
        PrimeFaces.current().executeScript("PF('dialogoTransferir').hide()");
        PrimeFaces.current().ajax().update("frmAtenciones");
        PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
    }

    public GatServicioUmbral getServicio(int tipo) {
        GatServicioUmbral gat = getListaServicios().stream().filter(s -> s.getMaeTipoServicioId() == tipo)
                .findFirst()
                .orElse(null);
        return gat;
    }

    public boolean validarTiempo(GatAtencion gestion) {
        int tiempo = calcularTimepoAtencion(gestion.getFechaHoraInicio());
        int tipoServicio = gestion.getGatTiquete().getMaeTipoServicio();
        GatServicioUmbral ga = getServicio(tipoServicio);
        if (ga != null) {
            if (tiempo > ga.getTiempo()) {
                return true;
            }
        }
        return false;
    }

    public void reposoCrear() {
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_CREAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void reposoGuardar() {
        setMinutosReposo(0);
        setSegundosReposo(0);
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public void reposoFinalizar() {
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        PrimeFaces.current().executeScript("stop(); reset();");
        getGatAtencionServicio().Accion(this);
        procesoFinal();
    }

    public boolean validarDatosUsuario() {
        if (getObjeto().getGatUsuario().getId() == null) {
            if (getObjeto().getGatUsuario().getMaeTipoDocumentoId() == 0) {
                addError("Tipo de documento: Este campo es obligatorio");
            }
            if (getObjeto().getGatUsuario().getNumeroDocumento().isEmpty()) {
                addError("Numero de documento: Este campo es obligatorio");
            }
            if (getObjeto().getGatUsuario().getNombres().isEmpty()) {
                addError("Nombres: Este campo es obligatorio");
            }
            if (getObjeto().getGatUsuario().getApellidos().isEmpty()) {
                addError("Apellidos: Este campo es obligatorio");
            }
        }
        return this.getErrores().isEmpty();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR://Listar
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmAtenciones");
                            PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                            crearLog(getObjeto().toString());
                            break;
                        // Procesos de seleccionad de la sede al iniciar
                        case Url.ACCION_CREAR://Apertura y carga de formulacio de seleccion de sede
                            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
                            PrimeFaces.current().ajax().update("frmSedes");
                            PrimeFaces.current().executeScript("PF('dialogoSedes').show()");
                            crearLog(getObjetoSede().toString());
                            break;
                        case Url.ACCION_GUARDAR://Guardar sede seleccionada
                            PrimeFaces.current().executeScript("PF('dialogoSedes').hide()");
                            refrescar();
                            PrimeFaces.current().ajax().update("frmAtenciones");
                            PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_BORRAR://Abandonar taquilla
                            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
                            refrescar();
                            PrimeFaces.current().ajax().update("frmAtenciones");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR://Apertura formularion para crear usuario
                    PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
                    PrimeFaces.current().ajax().update("frmCrearTurno");
                    PrimeFaces.current().executeScript("PF('dialogoCrearTurno').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR://Guardar usuario para atender
                    PrimeFaces.current().executeScript("PF('dialogoCrearTurno').hide()");
                    refrescar();
                    PrimeFaces.current().ajax().update("frmAtenciones");
                    PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR://Solicitud de turno emitido en taquilla de turnos
                    refrescar();
                    PrimeFaces.current().ajax().update("frmAtenciones");
                    PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1://Gestionar
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
//                            refrescar();
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            PrimeFaces.current().executeScript("PF('pollGestionar').stop()");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().executeScript("PF('pollGestionar').stop()");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                            refrescar();
                            PrimeFaces.current().ajax().update("frmAtenciones");
                            PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2://Rellamar
                    refrescar();
                    PrimeFaces.current().ajax().update("frmAtenciones");
                    PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                    break;
                case Url.ACCION_ADICIONAL_3://Transferir
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
                            PrimeFaces.current().ajax().update("frmTransferir");
                            PrimeFaces.current().executeScript("PF('dialogoTransferir').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            refrescar();
                            PrimeFaces.current().ajax().update("frmAtenciones");
                            PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                            PrimeFaces.current().executeScript("PF('dialogoTransferir').hide()");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4://Abandonar
                    refrescar();
                    PrimeFaces.current().ajax().update("frmAtenciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_5://Llamar
                    refrescar();
                    PrimeFaces.current().ajax().update("frmAtenciones");
                    break;
                case Url.ACCION_ADICIONAL_6://Reposo
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR://Guardar reposo
                            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
                            PrimeFaces.current().ajax().update("frmReposos");
                            PrimeFaces.current().executeScript("PF('dialogoReposo').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR://Crear reposo
                            PrimeFaces.current().executeScript("PF('pollPrincipal').stop()");
                            PrimeFaces.current().executeScript("PF('dialogoReposo').hide()");
                            PrimeFaces.current().ajax().update("frmRepoReposos");
                            PrimeFaces.current().executeScript("PF('dialogoReporReposo').show()");
                            PrimeFaces.current().executeScript("init();");
                            break;
                        case Url.ACCION_MODIFICAR://Finalizar reposo
                            refrescar();
                            PrimeFaces.current().ajax().update("frmAtenciones");
                            PrimeFaces.current().executeScript("PF('pollPrincipal').start()");
                            PrimeFaces.current().executeScript("PF('dialogoReporReposo').hide()");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
            }
        } else {
            if (isSalir()) {
                salir();
            }
        }
        generarMensajes();
    }

}
