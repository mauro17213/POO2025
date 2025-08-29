/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.google.gson.reflect.TypeToken;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeCorrelacion;
import com.saviasaludeps.savia.dominio.especial.PeEtapaCalculo;
import com.saviasaludeps.savia.dominio.especial.PeGestion;
import com.saviasaludeps.savia.dominio.especial.PeGestionHistorico;
import com.saviasaludeps.savia.dominio.especial.PeInsumoCalculo;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PeOperando;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaTraslado;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.dominio.especial.PeTipoCorrelacion;
import com.saviasaludeps.savia.dominio.especial.PeTipoValidacion;
import com.saviasaludeps.savia.dominio.especial.PeTipoVariable;
import com.saviasaludeps.savia.dominio.especial.PeUsuariosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeValidacion;
import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.dominio.especial.PeVariableValor;
import com.saviasaludeps.savia.dominio.especial.PeVariableValorAlmacenado;
import com.saviasaludeps.savia.dominio.especial.PeVariableValorHistorico;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucEgresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionAdversoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstanciaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionInoportunidadRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionPatologiaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionServicioRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucIngresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucJustificacionEstanciasProlongadaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAdjuntoProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeGestionProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeIpsProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeUsuariosProgramaRemoto;
import com.saviasaludeps.savia.web.especial.bean.GestionAfiliadosPEBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeGestionHistoricoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTrasladoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.negocio.especial.PeVariableRemoto;
import com.saviasaludeps.savia.negocio.especial.PeVariableValorHistoricoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeVariableValorRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.web.especial.utilidades.GsonUtil;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Jaime Andres Olarte
 */
public class GestionAfiliadosPEServicioImpl extends AccionesBO implements GestionAfiliadosPEServicioIface {

    private boolean comodinValidado;
    private boolean comodinCorrelacionado;

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private PeGestionProgramaRemoto getGestionProgramaRemoto() throws Exception {
        return (PeGestionProgramaRemoto) RemotoEJB.getEJBRemoto("PeGestionProgramaServicio", PeGestionProgramaRemoto.class.getName());
    }

    private PeAdjuntoProgramaRemoto getPeAdjuntoProgramaRemoto() throws Exception {
        return (PeAdjuntoProgramaRemoto) RemotoEJB.getEJBRemoto("PeAdjuntoProgramaServicio", PeAdjuntoProgramaRemoto.class.getName());
    }

    private PeUsuariosProgramaRemoto getPeUsuariosProgramaRemoto() throws Exception {
        return (PeUsuariosProgramaRemoto) RemotoEJB.getEJBRemoto("PeUsuariosProgramaServicio", PeUsuariosProgramaRemoto.class.getName());
    }

    private PeIpsProgramaRemoto getPeIpsProgramaRemoto() throws Exception {
        return (PeIpsProgramaRemoto) RemotoEJB.getEJBRemoto("PeIpsProgramaServicio", PeIpsProgramaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private PeAfiliadoDiagnosticoRemoto getPeAfiliadoDiagnosticoRemoto() throws Exception {
        return (PeAfiliadoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoDiagnosticoServicio", PeAfiliadoDiagnosticoRemoto.class.getName());
    }

    private AucHospitalizacionRemoto getAucHospitalizacionRemoto() throws Exception {
        return (AucHospitalizacionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicio", AucHospitalizacionRemoto.class.getName());
    }

    private AucAfiliadoRemoto getAucAfiliadoRemoto() throws Exception {
        return (AucAfiliadoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoServicio", AucAfiliadoRemoto.class.getName());
    }

    private AucIngresoRemoto getAucIngresoRemoto() throws Exception {
        return (AucIngresoRemoto) RemotoEJB.getEJBRemoto("AucIngresoServicio", AucIngresoRemoto.class.getName());
    }

    private AucEgresoRemoto getAucEgresoRemoto() throws Exception {
        return (AucEgresoRemoto) RemotoEJB.getEJBRemoto("AucEgresoServicio", AucEgresoRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private AucHospitalizacionAdversoRemoto getAucHospitalizacionAdversoRemoto() throws Exception {
        return (AucHospitalizacionAdversoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionAdversoServicio", AucHospitalizacionAdversoRemoto.class.getName());
    }

    private AucHospitalizacionSeguimientoRemoto getAucHospitalizacionSeguimientoRemoto() throws Exception {
        return (AucHospitalizacionSeguimientoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionSeguimientoServicio", AucHospitalizacionSeguimientoRemoto.class.getName());
    }

    private AucHospitalizacionInoportunidadRemoto getAucHospitalizacionInoportunidadRemoto() throws Exception {
        return (AucHospitalizacionInoportunidadRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionInoportunidadServicio", AucHospitalizacionInoportunidadRemoto.class.getName());
    }

    private AucHospitalizacionObjecionRemoto getAucHospitalizacionObjecionRemoto() throws Exception {
        return (AucHospitalizacionObjecionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionObjecionServicio", AucHospitalizacionObjecionRemoto.class.getName());
    }

    private AucHospitalizacionPatologiaRemoto getAucHospitalizacionPatologiaRemoto() throws Exception {
        return (AucHospitalizacionPatologiaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionPatologiaServicio", AucHospitalizacionPatologiaRemoto.class.getName());
    }

    private AucHospitalizacionServicioRemoto getAucHospitalizacionServicioRemoto() throws Exception {
        return (AucHospitalizacionServicioRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicioServicio", AucHospitalizacionServicioRemoto.class.getName());
    }

    private AucHospitalizacionEstanciaRemoto getAucHospitalizacionEstanciaRemoto() throws Exception {
        return (AucHospitalizacionEstanciaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionEstanciaServicio", AucHospitalizacionEstanciaRemoto.class.getName());
    }

    private AucJustificacionEstanciasProlongadaRemoto getAucJustificacionEstanciasProlongadaRemoto() throws Exception {
        return (AucJustificacionEstanciasProlongadaRemoto) RemotoEJB.getEJBRemoto("AucJustificacionEstanciasProlongadaServicio", AucJustificacionEstanciasProlongadaRemoto.class.getName());
    }

    private PeGestionHistoricoRemoto getPeGestionHistoricoRemoto() throws Exception {
        return (PeGestionHistoricoRemoto) RemotoEJB.getEJBRemoto("PeGestionHistoricoServicio", PeGestionHistoricoRemoto.class.getName());
    }

    private PeVariableRemoto getPeVariableRemoto() throws Exception {
        return (PeVariableRemoto) RemotoEJB.getEJBRemoto("PeVariableServicio", PeVariableRemoto.class.getName());
    }

    private PeVariableValorRemoto getPeVariableValorRemoto() throws Exception {
        return (PeVariableValorRemoto) RemotoEJB.getEJBRemoto("PeVariableValorServicio", PeVariableValorRemoto.class.getName());
    }

    private PeVariableValorHistoricoRemoto getPeVariableValorHistoricoRemoto() throws Exception {
        return (PeVariableValorHistoricoRemoto) RemotoEJB.getEJBRemoto("PeVariableValorHistoricoServicio", PeVariableValorHistoricoRemoto.class.getName());
    }

    private PeProgramaTrasladoRemoto getPeProgramaTrasladoRemoto() throws Exception {
        return (PeProgramaTrasladoRemoto) RemotoEJB.getEJBRemoto("PeProgramaTrasladoServicio", PeProgramaTrasladoRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getMaDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }

    public boolean isComodinValidado() {
        return comodinValidado;
    }

    public void setComodinValidado(boolean comodinValidado) {
        this.comodinValidado = comodinValidado;
    }

    public boolean isComodinCorrelacionado() {
        return comodinCorrelacionado;
    }

    public void setComodinCorrelacionado(boolean comodinCorrelacionado) {
        this.comodinCorrelacionado = comodinCorrelacionado;
    }

    @Override
    public void Accion(GestionAfiliadosPEBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearAdjunto(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarGestion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestion(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarGestion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarGestion(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarGestion(bean);
                            break;
                        case Url.ACCION_VER:
                            verHistoricoGestion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarHospitalizacionAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verHospitalizacion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            this.consultarTrasladoProgramas(bean);
                            break;
                        case Url.ACCION_CREAR:
                            this.crearCambioPrograma(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            this.guardarCambioPrograma(bean);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(GestionAfiliadosPEBean bean) {
        try {
            bean.setListaFuenteOrigen(PeConstantes.obtenerTiposOrigen());
            //Maestro regimen
            bean.setListaRegimen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            //Maestro Tipo Documento
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(PeConstantes.obtenerHashMaestro(bean.getListaTiposDocumento()));
            //Maestro Sexo(Genero)
            bean.setListaTiposGenero(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashTiposGenero(PeConstantes.obtenerHashMaestro(bean.getListaTiposGenero()));
            //Maestro Estado afiliación
            bean.setListaEstadosAfiliacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosAfiliacion(PeConstantes.obtenerHashMaestro(bean.getListaEstadosAfiliacion()));
            //Maestro Estado Diagnostico
            bean.setListaEstadoDiagnostico(PeConstantes.listaEstadoDiagnostico());
            //Maestro Region corporal
            bean.setListaRegionCorporal(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_REGION_CORPORAL));
            bean.setHashRegionCorporal(PeConstantes.obtenerHashMaestro(bean.getListaRegionCorporal()));
            //Maestro Medio Diagnostico
            bean.setListaMedioDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_MEDIO_DIAGNOSTICO));
            bean.setHashMedioDiagnostico(PeConstantes.obtenerHashMaestro(bean.getListaMedioDiagnostico()));
            //Programa Especiales
            bean.setListaPePrograma(getPeProgramaRemoto().consultarTodosEstado(PeConstantes.PE_PROGRAMA_ACTIVO));
            //Ubicacion
            bean.setHashUbicacion(UbicacionSingle.getInstance().getHashMunicipios());
            bean.getObjeto().setPePrograma(new PePrograma());
            //Causas Activo
            bean.setListCausasActivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_CAUSA_ESTADO_ACTIVO));
            bean.setHashCausasActivo(PeConstantes.obtenerHashMaestro(bean.getListCausasActivo()));
            //Causas InActivo
            bean.setListCausasInactivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_CAUSA_ESTADO_INACTIVO));
            bean.setHashCausasInActivo(PeConstantes.obtenerHashMaestro(bean.getListCausasInactivo()));
            //Usuarios por programa
            bean.setListResponsablesProg(new ArrayList<>());
            //2021-04-24 jyperez INC 778 Se solicita no cargar ningun responsable al iniciar la pantalla.
            //List<PeUsuariosPrograma> listPrograma = getPeUsuariosProgramaRemoto().consultarTodos();
            //Maestro Tipo Documento
            bean.setListaTipoAdjunto(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_ADJUNTO_TIPO));
            bean.setHashMaeTipoAdjunto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.PE_ADJUNTO_TIPO));
            bean.setListaGestionTipo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_GESTION_TIPO));
            bean.setListaTiposContacto(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setHashTiposContacto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setListaContactosBorrar(new ArrayList<>());
            bean.setListaEstadoSivigila(PeConstantes.listaSivigilaAll());
            bean.setHashEstadosSivigila(PeConstantes.obtenerHashMaestro(PeConstantes.listaSivigilaAll()));
            bean.setListaCausaDescarte(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_CAUSA_DESCARTE));
            bean.setHashCausaDescarte(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.PE_CAUSA_DESCARTE));
            //lista notificacion sivigila
            bean.setListaNotificacionSivigila(PeConstantes.listaNotficacionSivigila());
            bean.setHashNotificacionSivigila(PeConstantes.obtenerHashMaestro(PeConstantes.listaNotficacionSivigila()));
            // variables especificas
            bean.setListadoValoresVariables(new ArrayList<>());
            // Maestro y listado sentencia PE NUEVE SENTENCIAS
            bean.setHashSentencias(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.PE_NUEVE_SENTENCIAS));
            bean.setListaSentencias(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_NUEVE_SENTENCIAS));
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError(e.getMessage());
        }
    }

    /**
     * Metodo encargado de consultar el listado de uaurios para elegir el
     * reponsable del programa y consultar el listado de ips para seleccionar la
     * ips programa
     *
     * @param bean
     */
    @Override
    public void consultarResponsablesPrestadorPrograma(GestionAfiliadosPEBean bean) {
        try {
            //Consultar listado de usuario para seleccionar el responsable del programa
            List<PeUsuariosPrograma> listResponsablesPrograma = getPeUsuariosProgramaRemoto().consultarPorProgramaActivo(bean.getObjeto().getPePrograma().getId());
            bean.getObjeto().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId()));
            if (bean.getObjeto().getAsegAfiliado().getId() != null) {
                bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            }
            bean.setListResponsablesProg(listResponsablesPrograma);

            //Consultar listado de ips para seleccionar ips del programa
            List<PeIpsPrograma> listIpsProgramas = getPeIpsProgramaRemoto().consultarPorProgramaActivo(bean.getObjeto().getPePrograma().getId());
            for (PeIpsPrograma ipsPrograma : listIpsProgramas) {
                ipsPrograma.setCntPrestadorSedesId(getPrestadoresRemoto().consultarSede(ipsPrograma.getCntPrestadorSedesId().getId()));
            }
            bean.setListPrestadorSede(listIpsProgramas);
        } catch (Exception e) {
            bean.addError("Se presento inconvenientes al consultar lista de responsable programa e ips programa, intentelo nuevamente.");
        }

    }

    /**
     * Metodo encargado de consultar el listado de afilaidos que se pueden
     * matricular en un programa
     *
     * @param bean
     */
    @Override
    public void consultarAfiliado(GestionAfiliadosPEBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(1)));
            bean.setListaAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(1)));
            for (AsegAfiliado registro : bean.getListaAfiliados()) {
                if (registro.getResidenciaUbicacion() != null) {
                    registro.setResidenciaUbicacion(bean.getHashUbicacion().get(registro.getResidenciaUbicacion().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            return maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void consultarAfiliadoContactos(GestionAfiliadosPEBean bean) {
        try {
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            List<AsegAfiliadoContacto> contacto_afiliado = bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto();
            bean.setListaContactos(contacto_afiliado);
            List<PeTelefono> telefonos = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), "");
            bean.setListaContactosPrograma(telefonos);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarVariablesEspecificas(int idPrograma, GestionAfiliadosPEBean bean) {
        try {
            bean.setListadoVariablesEspecificas(getPeVariableRemoto().consultarPorIdPrograma(String.valueOf(idPrograma)));
        } catch (Exception e) {
            bean.addError("Se presento inconveninetes al obtener el listado de variables.");
        }
    }

    @Override
    public void consultarValoresVariablesHistorico(PeVariableValor variableValor, GestionAfiliadosPEBean bean) {
        try {
            List<PeVariableValorHistorico> historico = getPeVariableValorHistoricoRemoto().consultarListadoIdAfiliadoIdProgramaIdValor(bean.getObjeto().getPePrograma().getId(),
                    variableValor.getIdAfiliado(), variableValor.getIdVariable(), variableValor.getId());
            bean.setListadoValoresVariablesHistorico(historico);
        } catch (Exception e) {
            bean.addError("Se presento inconveninetes al obtener el historico de valores de variables.");
        }
    }

    private void ver(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto() != null) {
                bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
                bean.getObjeto().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId()));
                bean.getObjeto().getPePrograma().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getPePrograma().getUsuariosId().getId()));
                bean.getObjeto().getAsegAfiliado().setAfiliacionUbicacion(bean.getHashUbicacion().get(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getId()));
                bean.getObjeto().setCntPrestadorSede(getPrestadoresRemoto().consultarSede(bean.getObjeto().getCntPrestadorSede().getId()));
                List<AsegAfiliadoContacto> contacto_afiliado = bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto();
                bean.setListaContactos(contacto_afiliado);
                List<PeTelefono> telefonos = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), "");
                bean.setListaContactosPrograma(telefonos);
                bean.setListaContactosBorrar(new ArrayList<>());
                bean.getObjeto().setPeAfiliadoDiagnosticoList(getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(bean.getObjeto().getId()));

                for (PeAfiliadoDiagnostico afiliadoDiagnostico : bean.getObjeto().getPeAfiliadoDiagnosticoList()) {
                    MaDiagnostico diagnostico = getMaDiagnosticoRemoto().consultar(
                            Integer.parseInt(afiliadoDiagnostico.getMaDiagnosticosId())
                    );

                    if (diagnostico != null) {
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacCodigo(
                                diagnostico.getMaCacCodigo() != null ? diagnostico.getMaCacCodigo() : ""
                        );
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacId(
                                diagnostico.getMaCacId() != null ? diagnostico.getMaCacId() : null
                        );
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacValor(
                                diagnostico.getMaCacValor() != null ? diagnostico.getMaCacValor() : ""
                        );
                    } else {
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacCodigo("");
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacId(null);
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacValor("");
                    }
                }

                if (Objects.nonNull(bean.getObjeto().getGnUsuario())) {
                    bean.getObjeto().setGnUsuario(getUsuarioRemoto().consultar(bean.getObjeto().getGnUsuario().getId()));
                }
                bean.setListadoValoresVariables(getPeVariableValorRemoto().consultarListadoIdAfiliado(bean.getObjeto().getId()));
                this.verificacionVariablesCalculo(bean);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(new PeAfiliadosPrograma());
            bean.getObjeto().setAsegAfiliado(new AsegAfiliado());
            bean.getObjeto().setPePrograma(new PePrograma());
            bean.getObjeto().setGnUsuario(new Usuario());
            bean.getObjeto().setCntPrestadorSede(new CntPrestadorSede());
            bean.getObjeto().setActivo(Boolean.TRUE);
            bean.setListaContactos(new ArrayList<>());
            bean.setListaContactosPrograma(new ArrayList<>());
            bean.setListaContactosBorrar(new ArrayList<>());
            bean.getObjeto().setPeAfiliadoDiagnosticoList(new ArrayList<>());
            //variables especificas
            bean.setListadoVariablesEspecificas(new ArrayList<>());
            bean.setListadoValoresVariables(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(GestionAfiliadosPEBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getPeAfiliadosProgramaRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getPeAfiliadosProgramaRemoto().consultarLista(bean.getParamConsulta(0)));
            bean.getObjeto().setPeAfiliadoDiagnosticoAuxList(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GestionAfiliadosPEBean bean) {
        try {
            //Se inicializa valor por defecto para mensaje de validación cuando
            //ya el usuario esta matriculado en el programa
            bean.setMensajeConfirma("");

            if (bean.getObjeto().getAsegAfiliado() == null || bean.getObjeto().getAsegAfiliado().getPrimerNombre() == null || bean.getObjeto().getAsegAfiliado().getPrimerNombre().trim().isEmpty()) {
                bean.addError("Debe seleccionar un afiliado");
            }

            List<PeAfiliadoDiagnostico> principal = new ArrayList();
            if (bean.getObjeto().getPeAfiliadoDiagnosticoList().isEmpty()) {
                bean.addError("Debe existir por lo menos un diagnostico");
            } else {
                bean.getObjeto().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (diagnostico.getPrincipal() == true)).forEachOrdered(diagnostico -> {
                    principal.add(diagnostico);
                });
            }

            if (principal.isEmpty()) {
                bean.addError("Debe existir diagnostico principal.");
            }

            if (bean.getObjeto().getNotificadoSivigila() == null) {
                bean.addError("Debe inidicar un valor para el campo Notificado Sivigila.");
            }

            PePrograma pe_programa = getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId());
            List<PeAfiliadosPrograma> listAfiliadoProgramas = getPeAfiliadosProgramaRemoto().consultarAfiliados(bean.getObjeto().getAsegAfiliado().getId());

            if (pe_programa.getCantidadRegistro() == 0) {
                for (PeAfiliadosPrograma afiliadoPrograma : listAfiliadoProgramas) {
                    if (afiliadoPrograma.getPePrograma().getId().intValue() == bean.getObjeto().getPePrograma().getId().intValue()) {
                        if (afiliadoPrograma.getFechaFinPrograma() != null && afiliadoPrograma.getFechaFinPrograma().before(bean.getFechaHoy())) {
                            bean.setDialogoAbrir("dialogoProgAfilActivo");
                            bean.setMensajeConfirma("El afiliado ya tiene una matrícula finalizada para este programa de " + pe_programa.getDescripcionPrograma() + ", para activarlo nuevamente debe editar el registro.");
                        } else {
                            if (afiliadoPrograma.getActivo()) {
                                bean.setDialogoAbrir("dialogoProgAfilActivo");
                                bean.setMensajeConfirma("El afiliado ya tiene una matrícula para este programa de "
                                        + pe_programa.getDescripcionPrograma() + " en estado ACTIVO; no se podrá generar un nuevo registro si el usuario se encuentra vigente en el programa.");
                            } else {
                                bean.setDialogoAbrir("dialogoProgAfil");
                                bean.setMensajeConfirma("El afiliado ya tiene una matrícula INACTIVA para este programa de "
                                        + pe_programa.getDescripcionPrograma() + ".");
                            }
                        }
                    }
                }
            } else {
                Integer activo = getPeAfiliadosProgramaRemoto().consultarCantidadProgramaEstado(bean.getObjeto().getAsegAfiliado().getId(), bean.getObjeto().getPePrograma().getId(), PeConstantes.PE_PROGRAMA_ACTIVO);
                if (activo > 0) {
                    bean.setDialogoAbrir("dialogoProgAfilActivo");
                    bean.setMensajeConfirma("El afiliado ya tiene una matrícula para este programa de "
                            + pe_programa.getDescripcionPrograma() + " en estado ACTIVO; no se podrá generar un nuevo registro si el usuario se encuentra vigente en el programa.");
                }
            }

            if (!validarSexoAplica(bean)) {
                bean.setDialogoAbrir("dialogoProgAfilActivo");
                bean.setMensajeConfirma("El género del afiliado no corresponde al permitido por el programa, el programa " + bean.getObjeto().getPePrograma().getDescripcionPrograma() + " solo permite género " + PeConstantes.getListaSexoAplicaDescripcion(bean.getObjeto().getPePrograma().getSexoAplica()));
            }

            if (bean.getObjeto().getNotificadoSivigila() != null && bean.getObjeto().getEstadoSivigila() == null) {
                if (bean.getObjeto().getNotificadoSivigila() == PeConstantes.NOTIFICACION_SIVIGILA_SI) {
                    bean.addError("Debe seleccionar un valor para el campo Estado sivigila.");
                }
            }

            if (bean.getObjeto().getNotificadoSivigila() != null && bean.getObjeto().getEstadoSivigila() != null) {
                if (bean.getObjeto().getEstadoSivigila() == PeConstantes.ESTADO_CASO_DESCARTADO && bean.getObjeto().getMaeCausaDescarteId() == null) {
                    bean.addError("Debe seleccionar un valor para el campo Causa Descarte");
                }
            }
            //se obtienen y se validan los valores para la creacion
            List<PeVariableValor> valoresCreacion = this.obtenerValoresVariablesCreacion(bean);

            if (!bean.isError() && bean.getMensajeConfirma().isEmpty()) {
                bean.auditoriaGuardar(bean.getObjeto());
                if (bean.getObjeto().getMaeRegionCorporalId() != null) {
                    Maestro regCorporal = bean.getHashRegionCorporal().get(bean.getObjeto().getMaeRegionCorporalId());
                    bean.getObjeto().setMaeRegionCorporalId(regCorporal.getId());
                    bean.getObjeto().setMaeRegionCorporalCodigo(regCorporal.getValor());
                    bean.getObjeto().setMaeRegionCorporalValor(regCorporal.getNombre());
                }

                if (bean.getObjeto().getMaeMedioDxId() != null) {
                    Maestro regCorporal = bean.getHashMedioDiagnostico().get(bean.getObjeto().getMaeMedioDxId());
                    bean.getObjeto().setMaeMedioDxId(regCorporal.getId());
                    bean.getObjeto().setMaeMedioDxCodigo(regCorporal.getValor());
                    bean.getObjeto().setMaeMedioDxValor(regCorporal.getNombre());
                }

                if (bean.getListaContactosBorrar().size() > 0) {
                    try {
                        borrarContactoAfiliados(bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (bean.getListaContactosPrograma().size() > 0) {
                    try {
                        guardarContactoAfiliados(bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (bean.getObjeto().getTipoPaciente() == null) {
                    bean.getObjeto().setTipoPaciente(PeConstantes.PE_TIPO_PACIENTE_NUEVO);
                }

                if (bean.getObjeto().getMaeCausaDescarteId() != null) {
                    Maestro maeCausaDescarte = bean.getHashCausaDescarte().get(bean.getObjeto().getMaeCausaDescarteId());
                    bean.getObjeto().setMaeCausaDescarteId(maeCausaDescarte.getId());
                    bean.getObjeto().setMaeCausaDescarteCodigo(maeCausaDescarte.getValor());
                    bean.getObjeto().setMaeCausaDescarteValor(maeCausaDescarte.getNombre());
                }

                //sentencia PE nueve sentencias
                if (Objects.nonNull(bean.getObjeto().getIdSentencia())) {
                    Maestro maestroSentencia = bean.getHashSentencias().get(bean.getObjeto().getIdSentencia());
                    bean.getObjeto().setCodigoSentencia(maestroSentencia.getValor());
                    bean.getObjeto().setValorSentencia(maestroSentencia.getDescripcion());
                }

                bean.getObjeto().setFuenteOrigen(PeConstantes.ORIGEN_MANUAL);
                bean.getObjeto().setId(getPeAfiliadosProgramaRemoto().insertar(bean.getObjeto()));
                for (PeAfiliadoDiagnostico diagnostico : bean.getObjeto().getPeAfiliadoDiagnosticoList()) {
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(bean.getObjeto().getId()));
                    getPeAfiliadoDiagnosticoRemoto().insertar(diagnostico);
                }
                bean.addMensaje("Se creo un registro de manera exitosa");
                if (bean.getObjeto().getPePrograma().getId() == 32 || bean.getObjeto().getPePrograma().getId() == 79) {
                    try {
                        AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId());
                        bean.auditoriaModificar(afiliado);
                        afiliado.setUsuarioGestante(true);
                        afiliado.setFechaFinPeriodoGestacion(bean.getObjeto().getFechaFinPrograma());
                        getAfiliadoRemoto().actualizar(afiliado);
                    } catch (Exception e) {
                        bean.addError("No fue posible actualizar la información de usuario gestante.");
                    }
                }
                //creacion de valor en variables segun el caso
                this.gestionGuardarValoresVariables(bean, valoresCreacion);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto() != null) {
                bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
                bean.getObjeto().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId()));
                bean.getObjeto().getPePrograma().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getPePrograma().getUsuariosId().getId()));
                bean.getObjeto().getAsegAfiliado().setAfiliacionUbicacion(bean.getHashUbicacion().get(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getId()));
                if (bean.getObjeto().getGnUsuario() != null) {
                    bean.getObjeto().setGnUsuario(getUsuarioRemoto().consultar(bean.getObjeto().getGnUsuario().getId()));
                } else {
                    bean.getObjeto().setGnUsuario(new Usuario());
                }

                if (bean.getObjeto().getMaeRegionCorporalId() != null) {
                    bean.getObjeto().setMaeRegionCorporal(new Maestro(bean.getObjeto().getMaeRegionCorporalId()));
                }

                if (bean.getObjeto().getMaeMedioDxId() != null) {
                    bean.getObjeto().setMaeMedioDx(new Maestro(bean.getObjeto().getMaeMedioDxId()));
                }
                consultarResponsablesPrestadorPrograma(bean);
                List<AsegAfiliadoContacto> contacto_afiliado = bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto();
                bean.setListaContactos(contacto_afiliado);
                List<PeTelefono> telefonos = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), "");
                bean.setListaContactosPrograma(telefonos);
                bean.setListaContactosBorrar(new ArrayList<>());
                //Se consultan los diagnosticos que ya tenga el afiliado                
                List<PeAfiliadoDiagnostico> listDiagnosticos = getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(bean.getObjeto().getId());
                for (PeAfiliadoDiagnostico afiliadoDiagnostico : listDiagnosticos) {
                    MaDiagnostico diagnostico = getMaDiagnosticoRemoto().consultar(
                            Integer.parseInt(afiliadoDiagnostico.getMaDiagnosticosId())
                    );

                    if (diagnostico != null) {
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacCodigo(
                                diagnostico.getMaCacCodigo() != null ? diagnostico.getMaCacCodigo() : ""
                        );
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacId(
                                diagnostico.getMaCacId() != null ? diagnostico.getMaCacId() : null
                        );
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacValor(
                                diagnostico.getMaCacValor() != null ? diagnostico.getMaCacValor() : ""
                        );
                    } else {
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacCodigo("");
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacId(null);
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacValor("");
                    }
                }
                
                bean.getObjeto().setPeAfiliadoDiagnosticoList(listDiagnosticos == null ? new ArrayList<>() : listDiagnosticos);
                
                if (Objects.nonNull(bean.getObjeto().getNotificadoSivigila())) {
                    if (bean.getObjeto().getNotificadoSivigila() == PeConstantes.NOTIFICACION_SIVIGILA_SI) {
                        bean.setActivoEstadoSivigila(false);
                    }
                }
                //variables especificas
                this.consultarVariablesEspecificas(bean.getObjeto().getPePrograma().getId(), bean);
                bean.setListadoValoresVariables(getPeVariableValorRemoto().consultarListadoIdAfiliado(bean.getObjeto().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(GestionAfiliadosPEBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            if (bean.getObjeto().getActivo() && bean.getObjeto().getMaeCausaActivoId() == 0) {
                bean.addError("Debe ingresar la causa activo");
            } else if (!bean.getObjeto().getActivo() && bean.getObjeto().getMaeCausaInactivoId() == 0) {
                bean.addError("Debe ingresar la causa inactivo");
            }

            if (bean.getObjeto().getPeAfiliadoDiagnosticoList() == null || bean.getObjeto().getPeAfiliadoDiagnosticoList().isEmpty()) {
                bean.addError("Debe existir por lo menos un diagnostico");
            }
            List<PeAfiliadoDiagnostico> principal = new ArrayList();
            bean.getObjeto().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (diagnostico.getPrincipal() == true)).forEachOrdered(diagnostico -> {
                principal.add(diagnostico);
            });
            if (principal.isEmpty()) {
                bean.addError("Debe existir diagnostico principal.");
            }

            int cant_afiliado_activo = getPeAfiliadosProgramaRemoto().consultarDuplicadoProgramaAfiliadoActivo(bean.getObjeto().getAsegAfiliado().getId(), bean.getObjeto().getPePrograma().getId(), bean.getObjeto().getId());
            if (bean.getObjeto().getActivo() && cant_afiliado_activo > 0) {
                bean.addError("El afiliado ya tiene una matrícula para este programa de "
                        + bean.getObjeto().getPePrograma().getDescripcionPrograma() + " en estado ACTIVO; no se podrá activar el registro si el usuario se encuentra vigente en el programa.");
            }

            Maestro mae_estado_afiliado = getMaestroRemoto().consultar(bean.getObjeto().getAsegAfiliado().getMaeEstadoAfiliacion());
            if (bean.getObjeto().getActivo() && mae_estado_afiliado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO)) {
                bean.addError("No es posible activar el usuario ya que el estado de afiliación es " + mae_estado_afiliado.getNombre());
            }

            if (bean.getObjeto().getNotificadoSivigila() == null) {
                bean.addError("Debe inidicar un valor para el campo Notificado Sivigila.");
            }

            if (bean.getObjeto().getNotificadoSivigila() != null && bean.getObjeto().getEstadoSivigila() == null) {
                if (bean.getObjeto().getNotificadoSivigila() == PeConstantes.NOTIFICACION_SIVIGILA_SI) {
                    bean.addError("Debe seleccionar un valor para el campo Estado sivigila.");
                }
            }

            if (bean.getObjeto().getNotificadoSivigila() != null && bean.getObjeto().getEstadoSivigila() != null) {
                if (bean.getObjeto().getEstadoSivigila() == PeConstantes.ESTADO_CASO_DESCARTADO && bean.getObjeto().getMaeCausaDescarteId() == null) {
                    bean.addError("Debe seleccionar un valor para el campo Causa Descarte");
                }
            }

            List<PeVariableValor> valoresVariablesCreacion = this.obtenerValoresVariablesCreacion(bean);
            List<PeVariableValor> valoresVariablesActualizacion = this.obtenerValoresVariablesActualizacion(bean);

            if (bean.isError()) {
                return;
            }

            if (bean.getObjeto().getMaeRegionCorporalId() != null) {
                Maestro regCorporal = bean.getHashRegionCorporal().get(bean.getObjeto().getMaeRegionCorporalId());
                bean.getObjeto().setMaeRegionCorporalId(regCorporal.getId());
                bean.getObjeto().setMaeRegionCorporalCodigo(regCorporal.getValor());
                bean.getObjeto().setMaeRegionCorporalValor(regCorporal.getNombre());
            }

            if (bean.getObjeto().getMaeMedioDxId() != null) {
                Maestro regCorporal = bean.getHashMedioDiagnostico().get(bean.getObjeto().getMaeMedioDxId());
                bean.getObjeto().setMaeMedioDxId(regCorporal.getId());
                bean.getObjeto().setMaeMedioDxCodigo(regCorporal.getValor());
                bean.getObjeto().setMaeMedioDxValor(regCorporal.getNombre());
            }
            if (bean.getObjeto().getMaeCausaActivoId() != 0) {
                Maestro maeCausaActivo = getMaestroRemoto().consultar(bean.getObjeto().getMaeCausaActivoId());
                if (maeCausaActivo != null) {
                    bean.getObjeto().setMaeCausaActivoCodigo(maeCausaActivo.getValor());
                    bean.getObjeto().setMaeCausaActivoValor(maeCausaActivo.getDescripcion());
                }
            }
            if (bean.getObjeto().getMaeCausaInactivoId() != 0) {
                Maestro maeCausaInactivo = getMaestroRemoto().consultar(bean.getObjeto().getMaeCausaInactivoId());
                if (maeCausaInactivo != null) {
                    bean.getObjeto().setMaeCausaInactivoCodigo(maeCausaInactivo.getValor());
                    bean.getObjeto().setMaeCausaInactivoValor(maeCausaInactivo.getDescripcion());
                }
            }

            if (bean.getObjeto().getNotificadoSivigila() != null && bean.getObjeto().getEstadoSivigila() != null && bean.getObjeto().getMaeCausaDescarteId() != null && bean.getObjeto().getEstadoSivigila() == PeConstantes.ESTADO_CASO_DESCARTADO) {
                Maestro maeCausaDescarte = bean.getHashCausaDescarte().get(bean.getObjeto().getMaeCausaDescarteId());
                bean.getObjeto().setMaeCausaDescarteCodigo(maeCausaDescarte.getValor());
                bean.getObjeto().setMaeCausaDescarteValor(maeCausaDescarte.getDescripcion());
            } else {
                //maestro de causa descarte
                bean.getObjeto().setMaeCausaDescarteId(null);
                bean.getObjeto().setMaeCausaDescarteCodigo(null);
                bean.getObjeto().setMaeCausaDescarteValor(null);
            }

            if (bean.getListaContactosBorrar().size() > 0) {
                try {
                    borrarContactoAfiliados(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (bean.getListaContactosPrograma().size() > 0) {
                try {
                    guardarContactoAfiliados(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //borrar diagnosticos
            for (PeAfiliadoDiagnostico diagnostico : bean.getObjeto().getPeAfiliadoDiagnosticoAuxList()) {
                getPeAfiliadoDiagnosticoRemoto().eliminar(diagnostico);
            }
            //actualizando antiguos diagnosticos
            bean.getObjeto().getPeAfiliadoDiagnosticoList().stream()
                    .filter(diagnostico -> (diagnostico.getId() > 0))
                    .filter(diagnostico -> {
                        try {
                            return this.validarActualizacionDiagnostico(bean.getObjeto().getId(), diagnostico);
                        } catch (Exception ex) {
                            Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    })
                    .forEachOrdered(diagnostico -> {
                        try {
                            bean.auditoriaModificar(diagnostico);
                            getPeAfiliadoDiagnosticoRemoto().actualizar(diagnostico);
                        } catch (Exception ex) {
                            Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
            //guardar nuevos diagnosticos
            bean.getObjeto().getPeAfiliadoDiagnosticoList().stream()
                    .filter(diagnostico -> (diagnostico.getId() < 0)).forEachOrdered(diagnostico -> {
                try {
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setPeAfiliadosProgramasId(bean.getObjeto());
                    getPeAfiliadoDiagnosticoRemoto().insertar(diagnostico);
                } catch (Exception ex) {
                    Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //sentencia PE nueve sentencias
            if (Objects.nonNull(bean.getObjeto().getIdSentencia())) {
                Maestro maestroSentencia = bean.getHashSentencias().get(bean.getObjeto().getIdSentencia());
                bean.getObjeto().setCodigoSentencia(maestroSentencia.getValor());
                bean.getObjeto().setValorSentencia(maestroSentencia.getDescripcion());
            }

            getPeAfiliadosProgramaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se modifico el registro de manera exitosa");
            if (bean.getObjeto().getPePrograma().getId() == 32 || bean.getObjeto().getPePrograma().getId() == 79) {
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId());
                bean.auditoriaModificar(afiliado);
                if (bean.getObjeto().getActivo()) {
                    afiliado.setUsuarioGestante(true);
                    afiliado.setFechaFinPeriodoGestacion(bean.getObjeto().getFechaFinPrograma());
                    getAfiliadoRemoto().actualizar(afiliado);
                } else {
                    afiliado.setUsuarioGestante(false);
                    afiliado.setFechaFinPeriodoGestacion(new Date());
                    getAfiliadoRemoto().actualizar(afiliado);
                }
            }
            //edicion o creacion de valor en variables segun el caso
            this.gestionGuardarValoresVariables(bean, valoresVariablesCreacion);
            this.gestionActualizarValoresVariables(bean, valoresVariablesActualizacion);

        } catch (Exception e) {
            e.printStackTrace();
            bean.addError(e.getMessage());
        }
    }

    private void borrar(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearAdjunto(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaPeAdjunto(getPeAdjuntoProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.setListaContactosPrograma(getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), ""));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarAdjunto(GestionAfiliadosPEBean bean) {
        try {
            String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_ADJUNTOS_GESTIONES);
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los manuales del sistema");
            }
            if (bean.getPeAdjuntos().isEmpty()) {
                bean.addError("No hay adjuntos para guardar");
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            bean.auditoriaGuardar(bean.getObjectoAdjunto());
            bean.getObjectoAdjunto().setPeAfiliadosId(new PeAfiliadosPrograma(bean.getObjeto().getId()));
            //PeGestion peGestion = new PeGestion();
            //peGestion.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(bean.getObjeto().getId()));
            //peGestion.setUsuariosId(new Usuario(bean.getConexion().getUsuario().getId()));
            //peGestion.setTipo(1); // OJO quemado mientras tanto
            String tipoAdjunto = "";
            StringBuilder prefijoNombreArchivo = new StringBuilder();
            prefijoNombreArchivo = nombreArchivo.append(bean.getHashTiposDocumento().get(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumento()).getValor())
                    .append(bean.getObjeto().getAsegAfiliado().getNumeroDocumento())
                    .append("_");
            for (PeAdjunto peAdjunto : bean.getPeAdjuntos()) {
                nombreArchivo = new StringBuilder(prefijoNombreArchivo.toString());
                Maestro maestroTipoAdjunto = bean.getHashMaeTipoAdjunto().get(peAdjunto.getMaeTipoArchivoId());
                nombreArchivo.append(maestroTipoAdjunto.getNombre());
                nombreArchivo
                        .append("_")
                        .append(sdf.format(new Date()));
                nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                bean.auditoriaGuardar(peAdjunto);
                //bean.auditoriaGuardar(peGestion);

                peAdjunto.setMaeTipoArchivoCodigo(maestroTipoAdjunto.getValor());
                peAdjunto.setMaeTipoArchivoValor(maestroTipoAdjunto.getNombre());
                peAdjunto.setPeAfiliadosId(new PeAfiliadosPrograma(bean.getObjeto().getId()));
                peAdjunto.setNombre(nombreArchivo.toString());
                peAdjunto.setRuta(ruta);
                peAdjunto.setArchivo(nombreArchivo.append(peAdjunto.getExtensión()).toString());
                File archivo = new File(ruta, peAdjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(peAdjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(peAdjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                //peGestion.setDescripcion("Se adjunta el archivo de tipo " + tipoAdjunto + " con el nombre " + peAdjunto.getNombre() + "." + peAdjunto.getExtensión());
                //peAdjunto.setPeGestionesId(new PeGestion(getGestionProgramaRemoto().insertar(peGestion)));
                peAdjunto.setId(getPeAdjuntoProgramaRemoto().insertar(peAdjunto));
            }
            bean.getObjeto().setListaPeAdjunto(getPeAdjuntoProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjeto().getId()));
            bean.setPeAdjuntos(new ArrayList<>());
            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarGestion(GestionAfiliadosPEBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoGestion());
            bean.getObjetoGestion().setBorrado(false);
            bean.getObjetoGestion().setFuenteOriegen(PeConstantes.FUENTE_ORIGEN_MANUAL);
            bean.getObjetoGestion().setPeAfiliadosProgramasId(new PeAfiliadosPrograma(bean.getObjeto().getId()));
            bean.getObjetoGestion().setUsuariosId(new Usuario(bean.getConexion().getUsuario().getId()));
            Maestro mae_tipo = getMaestroRemoto().consultar(bean.getObjetoGestion().getTipo());
            bean.getObjetoGestion().setMaeTipoId(mae_tipo.getId());
            bean.getObjetoGestion().setMaeTipoCodigo(mae_tipo.getValor());
            bean.getObjetoGestion().setMaeTipoValor(mae_tipo.getNombre());
            bean.getObjetoGestion().setId(getGestionProgramaRemoto().insertar(bean.getObjetoGestion()));

            PeGestionHistorico historico = new PeGestionHistorico();
            historico.setDescripcion(bean.getObjetoGestion().getDescripcion());
            historico.setPeGestionId(bean.getObjetoGestion());
            bean.auditoriaGuardar(historico);
            getPeGestionHistoricoRemoto().insertar(historico);

            bean.getObjeto().setListaPeGestion(getGestionProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjeto().getId()));
            for (PeGestion peGestion : bean.getObjeto().getListaPeGestion()) {
                peGestion.setUsuariosId(getUsuarioRemoto().consultar(peGestion.getUsuariosId().getId()));
            }
            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void listarGestion(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto() != null) {
                bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
                bean.getObjeto().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId()));
                bean.getObjeto().getPePrograma().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getPePrograma().getUsuariosId().getId()));
                bean.getObjeto().getAsegAfiliado().setAfiliacionUbicacion(bean.getHashUbicacion().get(bean.getObjeto().getAsegAfiliado().getAfiliacionUbicacion().getId()));
                bean.getObjeto().setListaPeGestion(getGestionProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjeto().getId()));
                for (PeGestion peGestion : bean.getObjeto().getListaPeGestion()) {
                    peGestion.setUsuariosId(getUsuarioRemoto().consultar(peGestion.getUsuariosId().getId()));
                }
                List<AsegAfiliadoContacto> contactoAfiliado = bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto();
                bean.setListaContactos(contactoAfiliado);
                List<PeTelefono> contactosPrograma = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), "");
                bean.setListaContactosPrograma(contactosPrograma);
                bean.getObjeto().setPeAfiliadoDiagnosticoList(getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(bean.getObjeto().getId()));
                bean.getObjeto().setPuedeGestionar(getPeUsuariosProgramaRemoto().isUsuarioResponsable(bean.getConexion().getUsuario().getId(), bean.getObjeto().getPePrograma().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarContactoAfiliados(GestionAfiliadosPEBean bean) throws Exception {
        if (bean.getListaContactosPrograma().size() > 0) {
            List<PeTelefono> tel_agregar = new ArrayList();
            bean.getListaContactosPrograma().stream().filter(contacto -> (contacto.isNuevo() == true)).forEachOrdered(contacto -> {
                tel_agregar.add(contacto);
            });
            for (PeTelefono item : tel_agregar) {
                bean.auditoriaGuardar(item);
                getPeTelefonosRemoto().insertarTelefonosAfiliadosProgramas(item);
            }
        }
    }

    private void borrarContactoAfiliados(GestionAfiliadosPEBean bean) throws Exception {
        if (bean.getListaContactosBorrar().size() > 0) {
            for (PeTelefono item : bean.getListaContactosBorrar()) {
                getPeTelefonosRemoto().eliminar(item.getId());
            }
        }
    }

    private void consultarHospitalizacionAfiliado(GestionAfiliadosPEBean bean) {
        try {
            bean.getParamConsulta(2).setCantidadRegistros(getAucHospitalizacionRemoto().consultarCantidadListaConsultaTrescientosSesenta(bean.getParamConsulta(2)));
            bean.setListaHospitalizacion(getAucHospitalizacionRemoto().consultarListaConsultaTrescientosSesenta(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verHospitalizacion(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoHospitalizacion(getAucHospitalizacionRemoto().consultar(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjetoHospitalizacion().getAucAfiliadoId().getId()));
            bean.getObjetoHospitalizacion().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucIngresoId().getId()));
            bean.getObjetoHospitalizacion().getAucIngresoId().setUltimaFechaIngreso(bean.getObjetoHospitalizacion().getAucIngresoId().getFechaIngreso());
            if (bean.getObjetoHospitalizacion().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(0);
            }
            if (bean.getObjetoHospitalizacion().getAucEgresoId() != null && bean.getObjetoHospitalizacion().getAucEgresoId().getId() != null) {
                bean.getObjetoHospitalizacion().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucEgresoId().getId()));
            }
            bean.getObjetoHospitalizacion().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorId().getId()));
            bean.getObjetoHospitalizacion().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorSedeId().getId()));
            bean.getObjetoHospitalizacion().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.getObjeto().setPeAfiliadoDiagnosticoList(getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean validarSexoAplica(GestionAfiliadosPEBean bean) {
        if (bean.getObjeto().getPePrograma().getSexoAplica() == PeConstantes.APLICA_SEXO_AMBOS) {
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplica(bean.getObjeto().getPePrograma().getSexoAplica()).equalsIgnoreCase(bean.getObjeto().getAsegAfiliado().getMaeGeneroCodigo())) {
            return false;
        }
        return true;
    }

    private void borrarGestion(GestionAfiliadosPEBean bean) {
        try {
            String observacion = bean.getObjetoGestion().getBorradoObservacion();
            bean.setObjetoGestion(getGestionProgramaRemoto().consultar(bean.getObjetoGestion().getId()));
            bean.auditoriaModificar(bean.getObjetoGestion());
            bean.getObjetoGestion().setBorradoObservacion(observacion);
            getGestionProgramaRemoto().eliminar(bean.getObjetoGestion());
            bean.getObjeto().setListaPeGestion(getGestionProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjetoGestion().getPeAfiliadosProgramasId().getId()));
            bean.addMensaje("Gestión eliminada correctamente.");
            bean.setObjetoGestion(new PeGestion());
        } catch (Exception e) {
            bean.addError("Se presentó inconveniente al eliminar la gestión.");
        }
    }

    private void editarGestion(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjetoGestion(getGestionProgramaRemoto().consultar(bean.getObjetoGestion().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar gestión para actualizar.");
        }
    }

    private void modificarGestion(GestionAfiliadosPEBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoGestion());
            getGestionProgramaRemoto().modificar(bean.getObjetoGestion());
            //Se guarda el historico de gestion
            PeGestionHistorico historico = new PeGestionHistorico();
            historico.setDescripcion(bean.getObjetoGestion().getDescripcion());
            historico.setPeGestionId(bean.getObjetoGestion());
            bean.auditoriaGuardar(historico);
            getPeGestionHistoricoRemoto().insertar(historico);

            bean.addMensaje("Se modifico correctamente la gestión.");
            bean.getObjeto().setListaPeGestion(getGestionProgramaRemoto().consultarPorIdAfiliadoPrograma(bean.getObjetoGestion().getPeAfiliadosProgramasId().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveninetes al modificar la gestión.");
        }
    }

    private void verHistoricoGestion(GestionAfiliadosPEBean bean) {
        try {
            bean.setListaHistoricoGestion(getPeGestionHistoricoRemoto().consultarListaHistorico(bean.getObjetoGestion().getId()));
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Se presento inconveninetes al ver el historico de gestión.");
        }
    }

    /**
     * Permite validar la obligatoriedad del valor de variable, a partir de las
     * validaciones previas.
     */
    private void validarObligatoriedadValorVariable(GestionAfiliadosPEBean bean, PeVariableValor vv, Object valor) {

        boolean existenciaValor;
        if (vv.getTipo().equals(3) || vv.getTipo().equals(4)) {//validaciones previas para garantizar la existencia del valor
            existenciaValor = !((String) valor).isBlank();
        } else {
            existenciaValor = Objects.nonNull(valor);
        }

        if (!existenciaValor && vv.isObligatoria()) {//se verifica si existe el valor (para insercion o actualizacion) y si es obligatoria
            vv.setError(true); //se establece con error para cambiar la clase css en el input
            bean.addError("Debe diligenciar el valor de la variable" + " (" + vv.getDescripcion() + ")");//se agrega mensaje de error de obligatoriedad
            return;
        }
        vv.setError(false);

    }

    /**
     * Permite validar la obligatoriedad del valor de variable, a partir de las
     * de su valor, y si es obligatoria. (usada para variables valor con
     * correlaciones)
     */
    private boolean validarObligatoriedadValorVariableCorrelacion(GestionAfiliadosPEBean bean,
            PeVariableValor vv, Object valor) {

        boolean existenciaValor;
        if (vv.getTipo().equals(3) || vv.getTipo().equals(4)) {
            existenciaValor = !((String) valor).isBlank();
        } else {
            existenciaValor = Objects.nonNull(valor);
        }

        if (!existenciaValor && vv.isObligatoria()) {
            vv.setError(true);
            bean.addError("Debe diligenciar el valor de la variable" + " (" + vv.getDescripcion() + ")");
            return false;
        }
        vv.setError(false);
        return true;
    }

    /*
    * Permite validar el valor de variable para que sea guardado.
    * valida que el valor exista y tambien su obligatoriedad.
    * valor tipo fecha -> valida su rango maximo
     */
    private boolean validacionesPrimariasGuardar(GestionAfiliadosPEBean bean, PeVariableValor vv) {
        boolean valido, existeValor;

        switch (vv.getTipo()) {
            case 0: //Fecha
                this.validarObligatoriedadValorVariable(bean, vv, vv.getFecha());
                existeValor = Objects.nonNull(vv.getFecha());
                valido = existeValor;
                if (valido) {
                    valido = this.validarFechaMaxima(bean, vv);
                }
                return valido;//valida si el valor existe o no

            case 1://Entero
                this.validarObligatoriedadValorVariable(bean, vv, vv.getEntero());
                return Objects.nonNull(vv.getEntero());

            case 2://Decimal
                this.validarObligatoriedadValorVariable(bean, vv, vv.getDecimal());
                return Objects.nonNull(vv.getDecimal());

            case 3://Texto
                this.validarObligatoriedadValorVariable(bean, vv, vv.getTexto());
                return !vv.getTexto().isBlank();

            case 4://Texto largo
                this.validarObligatoriedadValorVariable(bean, vv, vv.getTextoLargo());
                return !vv.getTextoLargo().isBlank();

            default:
                return false;
        }
    }

    private boolean validacionMayorQue(GestionAfiliadosPEBean bean, PeVariableValor variableValor, PeValidacion validacion, List<Object> valoresIgualdad) {
        boolean valido = false;
        String mensajeError = "Validaciones norma: La variable %s, debe ser mayor o igual a %s.";
        switch (variableValor.getTipo()) {
            case 0://FECHA
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date valorValidacionFecha;
                try {
                    valorValidacionFecha = formatter.parse(validacion.getValor().toString());
                    valido = variableValor.getFecha().equals(valorValidacionFecha) || variableValor.getFecha().after(valorValidacionFecha);

                    if (valido && !valoresIgualdad.isEmpty()) {//si es valido y tiene valores de igualdad, establecemos el comodin validado para que no valide de nuevo la igualdad.
                        this.setComodinValidado(true);
                    }

                    if (!valido) {//si no es valido miramos si tiene validaciones de igualdad en este caso hacen el rol de COMODIN

                        if (!valoresIgualdad.isEmpty()) {
                            this.setComodinValidado(true);
                            valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                        }

                        if (!valido) {
                            variableValor.setError(true);
                            bean.addError(String.format(mensajeError, variableValor.getDescripcion(), validacion.getValor().toString()));

                        }
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                return valido;
            case 1://ENTERO
                Double valorValidacionEntero = (double) validacion.getValor();
                valido = variableValor.getEntero() >= valorValidacionEntero.intValue();

                if (valido && !valoresIgualdad.isEmpty()) {
                    this.setComodinValidado(true);
                }

                if (!valido) {

                    if (!valoresIgualdad.isEmpty()) {
                        this.setComodinValidado(true);
                        valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                    }

                    if (!valido) {
                        variableValor.setError(true);
                        bean.addError(String.format(mensajeError, variableValor.getDescripcion(), validacion.getValor().toString()));

                    }
                }
                return valido;
            case 2://DECIMAL
                Double valorValidacionDecimal = (double) validacion.getValor();
                valido = variableValor.getDecimal().doubleValue() >= valorValidacionDecimal;

                if (valido && !valoresIgualdad.isEmpty()) {
                    this.setComodinValidado(true);
                }

                if (!valido) {

                    if (!valoresIgualdad.isEmpty()) {
                        this.setComodinValidado(true);
                        valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                    }

                    if (!valido) {
                        variableValor.setError(true);
                        bean.addError(String.format(mensajeError, variableValor.getDescripcion(), validacion.getValor().toString()));

                    }
                }
                return valido;
            default:
                return valido;
        }
    }

    private boolean validacionMenorQue(GestionAfiliadosPEBean bean, PeVariableValor variableValor, PeValidacion validacion, List<Object> valoresIgualdad) {
        boolean valido = false;
        String mensajeError = "Validaciones norma: La variable %s, debe ser menor o igual a %s.";
        switch (variableValor.getTipo()) {
            case 0://FECHA
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date valorValidacionFecha;
                try {
                    valorValidacionFecha = formatter.parse(validacion.getValor().toString());
                    valido = variableValor.getFecha().equals(valorValidacionFecha) || variableValor.getFecha().before(valorValidacionFecha);

                    if (valido && !valoresIgualdad.isEmpty()) { //si es valido y tiene valores de igualdad, establecemos el comodin validado para que no valide de nuevo la igualdad.
                        this.setComodinValidado(true);
                    }

                    if (!valido) {//si no es valido miramos si tiene validaciones de igualdad en este caso hacen el rol de COMODIN.

                        if (!valoresIgualdad.isEmpty()) {
                            this.setComodinValidado(true);
                            valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                        }

                        if (!valido) {
                            variableValor.setError(true);
                            bean.addError(String.format(mensajeError, variableValor.getDescripcion(), validacion.getValor().toString()));

                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                return valido;
            case 1://ENTERO
                Double valorValidacionEntero = (double) validacion.getValor();
                valido = variableValor.getEntero() <= valorValidacionEntero.intValue();

                if (valido && !valoresIgualdad.isEmpty()) {
                    this.setComodinValidado(true);
                }

                if (!valido) {

                    if (!valoresIgualdad.isEmpty()) {
                        this.setComodinValidado(true);
                        valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                    }

                    if (!valido) {
                        variableValor.setError(true);
                        bean.addError(String.format(mensajeError, variableValor.getDescripcion(), validacion.getValor().toString()));

                    }
                }
                return valido;
            case 2://DECIMAL
                Double valorValidacionDecimal = (double) validacion.getValor();
                valido = variableValor.getDecimal().doubleValue() <= valorValidacionDecimal;

                if (valido && !valoresIgualdad.isEmpty()) {
                    this.setComodinValidado(true);
                }

                if (!valido) {

                    if (!valoresIgualdad.isEmpty()) {
                        this.setComodinValidado(true);
                        valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                    }

                    if (!valido) {
                        variableValor.setError(true);
                        bean.addError(String.format(mensajeError, variableValor.getDescripcion(), validacion.getValor().toString()));

                    }
                }
                return valido;
            default:
                return valido;
        }
    }

    private boolean validacionIgualdad(GestionAfiliadosPEBean bean, PeVariableValor variableValor, List<Object> valoresIgualdad) {
        boolean valido = false;
        List<Boolean> verificaciones = new ArrayList<>();
        String mensajeError = "Validaciones norma: La variable %s, solo permite los valores %s.";
        switch (variableValor.getTipo()) {

            case 0://FECHA
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date valorValidacionFecha;
                try {

                    for (Object valor : valoresIgualdad) {
                        valorValidacionFecha = formatter.parse(valor.toString());
                        verificaciones.add(variableValor.getFecha().equals(valorValidacionFecha));
                    }
                    valido = verificaciones.stream()
                            .anyMatch(b -> b);

                    if (!valido) {
                        variableValor.setError(true);
                        String valoresVerificar = valoresIgualdad.stream()
                                .map(v -> v.toString())
                                .collect(Collectors.joining(", "));
                        bean.addError(String.format(mensajeError, variableValor.getDescripcion(), valoresVerificar));
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                return valido;
            case 1://ENTERO
                for (Object valor : valoresIgualdad) {
                    Double valorValidacionEntero = (double) valor;
                    verificaciones.add(variableValor.getEntero() == valorValidacionEntero.intValue());
                }
                valido = verificaciones.stream()
                        .anyMatch(b -> b);

                if (!valido) {
                    variableValor.setError(true);
                    String valoresVerificar = valoresIgualdad.stream()
                            .map(v -> String.valueOf(((Number) v).intValue()))
                            .collect(Collectors.joining(", "));
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), valoresVerificar));
                }

                return valido;
            case 2://DECIMAL
                for (Object valor : valoresIgualdad) {
                    Double valorValidacionDecimal = (double) valor;
                    verificaciones.add(variableValor.getDecimal().doubleValue() == valorValidacionDecimal);
                }
                valido = verificaciones.stream()
                        .anyMatch(b -> b);

                if (!valido) {
                    variableValor.setError(true);
                    String valoresVerificar = valoresIgualdad.stream()
                            .map(v -> v.toString())
                            .collect(Collectors.joining(", "));
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), valoresVerificar));
                }

                return valido;
            default:
                return valido;
        }
    }

    /*
    * verifica validaciones tipo 3 solo valido para fechas
     */
    private boolean validacionAnterioridad(GestionAfiliadosPEBean bean, PeVariableValor variableValor, PeValidacion validacion, List<Object> valoresIgualdad) {
        boolean valido = false;
        String mensajeErrorFechaNoSuperior = "Validaciones norma: La variable %s, no puede tener mas de %s meses de anterioridad.";
        String mensajeErrorFechaSuperior = "Validaciones norma: La variable %s, no puede ser superior a la fecha de reporte.";
        switch (variableValor.getTipo()) {

            case 0://FECHA
                int mesesAnterioridad = ((Double) validacion.getValor()).intValue();
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(new Date());
                calendario.add(Calendar.MONTH, -1);//nos devolvemos un mes atras
                calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));//tomamos el ultimo dia del mes en el que estemos
                calendario.set(Calendar.HOUR_OF_DAY, 0);
                calendario.set(Calendar.MINUTE, 0);
                calendario.set(Calendar.SECOND, 0);
                calendario.set(Calendar.MILLISECOND, 0);
                Date fechaMaxima = calendario.getTime(); //varia de acuerdo al momento de ejecucion, obteniendo el ultimo dia del mes anterior

                calendario.add(Calendar.MONTH, (-mesesAnterioridad + 1));//restamos x meses hacia atras para conocer la fecha minima con anterioridad, sumamos un mes ya que se cuenta el mes de la fecha maxima
                calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMinimum(Calendar.DAY_OF_MONTH));//tomatmos el primer dia del mes en el que estemos
                Date fechaMinima = calendario.getTime();

                valido = !variableValor.getFecha().before(fechaMinima) && !variableValor.getFecha().after(fechaMaxima);

                if (valido && !valoresIgualdad.isEmpty()) {//si es valido y tiene valores de igualdad, establecemos el comodin validado para que no valide de nuevo la igualdad.
                    this.setComodinValidado(true);
                }
                if (!valido) {//si no es valido miramos si tiene validaciones de igualdad en este caso hacen el rol de COMODIN
                    if (!valoresIgualdad.isEmpty()) {
                        this.setComodinValidado(true);
                        valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                    }
                    if (!valido) {
                        variableValor.setError(true);
                        if (variableValor.getFecha().after(fechaMaxima)) {
                            bean.addError(String.format(mensajeErrorFechaSuperior, variableValor.getDescripcion()));
                        } else {
                            bean.addError(String.format(mensajeErrorFechaNoSuperior, variableValor.getDescripcion(), mesesAnterioridad));
                        }
                    }
                }

                return valido;

            default:
                return valido;
        }
    }

    /**
     * Verifica validaciones tipo 4 solo valido para fechas
     */
    private boolean validacionActualidad(GestionAfiliadosPEBean bean, PeVariableValor variableValor, List<Object> valoresIgualdad) {
        boolean valido = false;
        String mensajeError = "Validaciones norma: La variable %s, debe tener una fecha anterior o igual a la del ultimo dia del periodo de cargue.";
        switch (variableValor.getTipo()) {

            case 0://FECHA
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(new Date());
                calendario.add(Calendar.MONTH, -1);//nos devolvemos un mes atras
                calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));//tomamos el ultimo dia del mes en el que estemos
                calendario.set(Calendar.HOUR_OF_DAY, 0);
                calendario.set(Calendar.MINUTE, 0);
                calendario.set(Calendar.SECOND, 0);
                calendario.set(Calendar.MILLISECOND, 0);
                Date fechaMaxima = calendario.getTime(); //varia de acuerdo al momento de ejecucion, obteniendo el ultimo dia de fecha de cargue (ultimo dia del mes anterior)
                valido = variableValor.getFecha().equals(fechaMaxima) || variableValor.getFecha().before(fechaMaxima);

                if (valido && !valoresIgualdad.isEmpty()) {//si es valido y tiene valores de igualdad, establecemos el comodin validado para que no valide de nuevo la igualdad.
                    this.setComodinValidado(true);
                }
                if (!valido) {//si no es valido miramos si tiene validaciones de igualdad en este caso hacen el rol de COMODIN
                    if (!valoresIgualdad.isEmpty()) {
                        this.setComodinValidado(true);
                        valido = this.validacionIgualdad(bean, variableValor, valoresIgualdad);
                    }
                    if (!valido) {
                        variableValor.setError(true);
                        bean.addError(String.format(mensajeError, variableValor.getDescripcion()));
                    }
                }

                return valido;

            default:
                return valido;
        }
    }

    /**
     * Permite obtener los valores de igualdad si una variableValor tiene
     * validaciones tipo 2
     */
    private List<Object> obtenerValoresVariableIgualdad(PeVariableValor variableValor) {
        List<PeValidacion> validacionesIgualdad = variableValor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(2))//IGUALDAD
                .collect(Collectors.toList());

        return validacionesIgualdad.stream()
                .map(v -> v.getValor())
                .collect(Collectors.toList());

    }

    /*
    * Permite validar el valor de variable para que sea guardado o actualizado.
    * valida a partir de las validaciones adicionales asignadas en la variable especifica al momento de crearse o actualizarse.
     */
    private boolean validacionesAdicionales(GestionAfiliadosPEBean bean, PeVariableValor vv) {
        if (Objects.isNull(vv.getValidaciones())) {
            return true;
        }

        List<Boolean> validaciones = new ArrayList<>();
        List<Object> valoresIgualdad = this.obtenerValoresVariableIgualdad(vv);

        for (PeValidacion validacion : vv.getValidaciones()) {
            switch (validacion.getTipo()) {//PeTipoValidacion
                case 0://MAYOR_QUE
                    validaciones.add(this.validacionMayorQue(bean, vv, validacion, valoresIgualdad));
                    break;
                case 1://MENOR_QUE
                    validaciones.add(this.validacionMenorQue(bean, vv, validacion, valoresIgualdad));
                    break;
                case 3://ANTERIORIDAD
                    validaciones.add(this.validacionAnterioridad(bean, vv, validacion, valoresIgualdad));
                    break;
                case 4://ACTUALIDAD
                    validaciones.add(this.validacionActualidad(bean, vv, valoresIgualdad));
                    break;
            }
        }

        if (!this.isComodinValidado() && !valoresIgualdad.isEmpty()) {
            validaciones.add(this.validacionIgualdad(bean, vv, valoresIgualdad));
        }

        this.setComodinValidado(false);//Una vez verificado las validaciones se establece el comodin validado en falso
        return validaciones.stream().allMatch(b -> b);
    }

    /*
    * Permite validar el valor de variable para que sea actualizado.
    * valida que el valor exista y que sea distinto al guardado previamente ademas de su obligatoriedad.
    * si tiene correlacion toma priorirdad la validacion primaria de correlacion
     */
    private boolean validacionesPrimariasActualizar(GestionAfiliadosPEBean bean, PeVariableValor vv) throws Exception {

        if (this.validacionPrimariaCorrelacionActualizar(bean, vv)) {
            return this.validarObligatoriedadValorVariableCorrelacion(bean, vv, vv.getValorObject());
        } else {

            PeVariableValor valorConsultado = getPeVariableValorRemoto().consultar(vv.getId());
            boolean valido, existeValor;

            switch (vv.getTipo()) {
                case 0://Fecha
                    existeValor = Objects.nonNull(vv.getFecha());
                    valido = existeValor && !valorConsultado.getFecha().equals(vv.getFecha()); //se valida si no es nula y si el valor es diferente al guardado previamente
                    this.validarObligatoriedadValorVariable(bean, vv, vv.getFecha());

                    if (valido) {
                        valido = this.validarFechaMaxima(bean, vv);
                    }

                    return valido;
                case 1://Entero
                    valido = Objects.nonNull(vv.getEntero()) && !valorConsultado.getEntero().equals(vv.getEntero());
                    this.validarObligatoriedadValorVariable(bean, vv, vv.getEntero());
                    return valido;
                case 2://Decimal
                    valido = Objects.nonNull(vv.getDecimal()) && !valorConsultado.getDecimal().equals(vv.getDecimal());
                    this.validarObligatoriedadValorVariable(bean, vv, vv.getDecimal());
                    return valido;
                case 3://Texto
                    valido = !vv.getTexto().isBlank() && !valorConsultado.getTexto().equals(vv.getTexto());
                    this.validarObligatoriedadValorVariable(bean, vv, vv.getTexto());
                    return valido;
                case 4://Texto largo
                    valido = !vv.getTextoLargo().isBlank() && !valorConsultado.getTextoLargo().equals(vv.getTextoLargo());
                    this.validarObligatoriedadValorVariable(bean, vv, vv.getTextoLargo());
                    return valido;
                default:
                    return false;
            }
        }
    }

    /**
     * Permite obtener el listado de valores validos para crear de acuerdo
     * algunas validaciones Realiza validaciones primarias y adicionales
     */
    private List<PeVariableValor> obtenerValoresVariablesCreacion(GestionAfiliadosPEBean bean) throws Exception {
        //listado de valores auxiliar aplicando logica de validaciones correspondientes
        List<PeVariableValor> listadoAux = bean.getListadoValoresVariables().stream()
                .filter(vv -> Objects.isNull(vv.getId()) && vv.isActiva())
                .filter(vv -> this.validacionesPrimariasGuardar(bean, vv))
                .filter(vv -> this.validacionesAdicionales(bean, vv))
                .collect(Collectors.toList());
        if (bean.isError()) {//si hay errores no seguimos verificando las variables en caso de tener correlacion
            return Collections.emptyList();
        }

        return listadoAux.stream()
                .filter(vv -> {
                    try {
                        return this.validacionCorrelacion(bean.getListadoValoresVariables(), bean, vv);
                    } catch (Exception ex) {
                        Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }

                })
                .collect(Collectors.toList());
    }

    /**
     * Permite obtener el listado de valores validos para actualizar de acuerdo
     * algunas validaciones. Realiza validaciones primarias y adicionales
     */
    private List<PeVariableValor> obtenerValoresVariablesActualizacion(GestionAfiliadosPEBean bean) throws Exception {
        List<PeVariableValor> valoresActualizacion = new ArrayList<>();

        for (PeVariableValor valor : bean.getListadoValoresVariables()) {
            if (Objects.nonNull(valor.getId()) && valor.isActiva()
                    && this.validacionesPrimariasActualizar(bean, valor)) {
                valoresActualizacion.add(valor);
            }
        }
        List<PeVariableValor> listadoAux = valoresActualizacion.stream()
                .filter(vv -> this.validacionesAdicionales(bean, vv))
                .collect(Collectors.toList());
        if (bean.isError()) {//si hay errores no seguimos verificando las variables en caso de tener correlacion
            return Collections.emptyList();
        }
        return listadoAux.stream()
                .filter(vv -> {
                    try {
                        return this.validacionCorrelacion(listadoAux, bean, vv);
                    } catch (Exception ex) {
                        Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }

                })
                .filter(vv -> {
                    try {
                        if (!this.tieneValidacionesCorrelacion(vv)) {
                            return true;
                        }
                        //solo verificaremos variables valor con correlacion
                        return !this.verificacionValorAlmacenado(bean, vv);//si es false indica que es apta para actualizar
                    } catch (Exception ex) {
                        Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    /*
    * Realiza auditoria y guarda valores de variables.
     */
    private List<PeVariableValor> guardarValoresVariables(GestionAfiliadosPEBean bean, List<PeVariableValor> listadoValoresVariables) throws Exception {
        //guardar auditoria 
        listadoValoresVariables.forEach(vv -> bean.auditoriaGuardar(vv));
        //insercion de valores en bd
        return getPeVariableValorRemoto().insertarListado(listadoValoresVariables, bean.getObjeto().getId());
    }

    /*
    * Actualiza auditoria y actualiza valores de variables.
     */
    private void modificarValoresVariables(GestionAfiliadosPEBean bean, List<PeVariableValor> valoresActualizacion) throws Exception {
        //modificar auditoria 
        valoresActualizacion.forEach(vv -> bean.auditoriaModificar(vv));
        //modificacion de valores en bd
        getPeVariableValorRemoto().actualizarListado(valoresActualizacion);
    }

    /*
    * Guarda auditoria y guarda historicos de valores de variables.
     */
    private void guardarValoresVariablesHistoricos(GestionAfiliadosPEBean bean, PeVariableValorHistorico historico) throws Exception {
        bean.auditoriaGuardar(historico);
        historico.setPeProgramasId(bean.getObjeto().getPePrograma().getId());
        getPeVariableValorHistoricoRemoto().insertar(historico);
    }

    /*
    * Permite gestionar la insercion de valores de variables a partir de un listado valido para crear
     */
    private void gestionGuardarValoresVariables(GestionAfiliadosPEBean bean, List<PeVariableValor> valoresCreacion) throws Exception {
        if (!valoresCreacion.isEmpty()) {
            //guardar nuevos registros
            List<PeVariableValor> valoresVariablesGuardados = this.guardarValoresVariables(bean, valoresCreacion);
            //guardar valor historico (primer valor)
            for (PeVariableValor v : valoresVariablesGuardados) {
                this.guardarValoresVariablesHistoricos(bean, new PeVariableValorHistorico(v));
            }

        }
    }

    /*
    * Permite gestionar la actualizacion de valores de variables a partir de un listado valido para actualizar
     */
    private void gestionActualizarValoresVariables(GestionAfiliadosPEBean bean, List<PeVariableValor> valoresActualizacion) throws Exception {
        if (!valoresActualizacion.isEmpty()) {
            //modificacion de registros
            this.modificarValoresVariables(bean, valoresActualizacion);
            //insercion de historicos valores variables
            for (PeVariableValor v : valoresActualizacion) {
                this.guardarValoresVariablesHistoricos(bean, new PeVariableValorHistorico(v));
            }

        }
    }

    private boolean validarActualizacionDiagnostico(Integer idAfiliado, PeAfiliadoDiagnostico diagnostico) throws Exception {
        PeAfiliadoDiagnostico diagnosticoConsultado = getPeAfiliadoDiagnosticoRemoto().consultarAfiliadoPrograma(idAfiliado, diagnostico.getMaDiagnosticosCodigo());
        return diagnostico.getPrincipal() != diagnosticoConsultado.getPrincipal();
    }

    /*
    *Valida si una fecha de entrada no supera el rango maximo (20xx-01-01) del siguiente año actual
     */
    private boolean validarFechaMaxima(GestionAfiliadosPEBean bean, PeVariableValor variableValor) {
        LocalDate fechaEntrada = variableValor.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate fechaMaxima = LocalDate.now().withDayOfYear(1).withMonth(1).plusYears(1);

        boolean validacion = fechaEntrada.isBefore(fechaMaxima);

        if (!validacion) {
            variableValor.setError(true);
            bean.addError("El valor maximo de la variable" + " (" + variableValor.getDescripcion() + ") No puede ser mayor o igual a " + fechaMaxima);//se agrega mensaje de error rango maximo de fecha
            return validacion;
        }

        variableValor.setError(false);
        return validacion;
    }

    /**
     * Permite verificar si una variable valor tiene validaciones de
     * correlacion.
     *
     * 03/04/2025
     *
     * @param variableValor
     * @return boolean
     */
    private boolean tieneValidacionesCorrelacion(PeVariableValor variableValor) {
        if (variableValor.getValidaciones() == null || variableValor.getValidaciones().isEmpty()) {
            return false;
        }
        return variableValor.getValidaciones().stream()
                .anyMatch(v -> v.getTipo().equals(PeTipoValidacion.CORRELACION.getId()));

    }

    /**
     * Logica para validacion correlacion (tipo 5)
     */
    private boolean validacionCorrelacion(List<PeVariableValor> variablesValor, GestionAfiliadosPEBean bean, PeVariableValor variableValor) throws Exception {
        if (!this.tieneValidacionesCorrelacion(variableValor)) {
            return true;//en este caso la variable sera apta para guardar o actualizar
        }

        //obtenemos variablesValor con validaciones tipo 5 (correlaciones)
        List<PeValidacion> validacionesCorrelacion = variableValor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(PeTipoValidacion.CORRELACION.getId()))
                .collect(Collectors.toList());

        //se identifica si tiene validacion de comodin requerido
        boolean validacionComodinRequerido = this.existeValidacionComodinRequerido(variableValor);
        List<Boolean> validaciones = new ArrayList<>();

        this.setComodinValidado(false);//permite conocer si se valido un flujo como comodin
        this.setComodinCorrelacionado(true);//permite conocer si al momento de hacer el flujo como comodin se valida a partir de la variable correlacionada (flujo normal)

        for (PeValidacion validacion : validacionesCorrelacion) {
            PeCorrelacion peCorrelacion = GsonUtil.deserializar(validacion.getValor().toString(), new TypeToken<PeCorrelacion>() {
            });
            PeVariableValor variableCorrelacion = null;

            if (!peCorrelacion.getTipo().equals(PeTipoCorrelacion.COMBINACION.getId())) {
                variableCorrelacion = this.obtenerValorVariableCorrelacion(bean, variablesValor, peCorrelacion.getCorrelacion());

                if (variableCorrelacion == null) {
                    return true;
                }
            }

            switch (peCorrelacion.getTipo()) {//PeTipoCorrelacion
                case 0://MAYOR
                    validaciones.add(this.validacionCorrelacionMayor(bean, variableValor, variableCorrelacion, validacionComodinRequerido));
                    break;
                case 1://MENOR
                    validaciones.add(this.validacionCorrelacionMenor(bean, variableValor, variableCorrelacion, validacionComodinRequerido));
                    break;
                case 2://COMODIN REQUERIDO
                    if (this.isComodinValidado()) {
                        validaciones.add(true);
                        break;
                    }
                    validaciones.add(this.validacionComodinRequerido(bean, variableValor, variableCorrelacion));
                    break;
                case 3://IGUALDAD
                    validaciones.add(this.validacionIgualdadCorrelacionada(bean, peCorrelacion, variableValor, variableCorrelacion));
                    break;
                case 4://COMBINACIONES
                    validaciones.add(this.validacionCombinacionCorrelacionada(bean, variablesValor, peCorrelacion, variableValor));
                    break;
            }
        }
        this.setComodinValidado(false);
        return validaciones.stream().allMatch(b -> b);
    }

    private PeVariableValor obtenerValorVariableCorrelacion(GestionAfiliadosPEBean bean, List<PeVariableValor> valoresVariable, String nombreVariableCorrelacion) throws Exception {
//        PeVariable variableCorrelacion = getPeVariableRemoto().consultarIdPorIdProgramaNombre(bean.getObjeto().getPePrograma().getId(), nombreVariableCorrelacion);
        Integer idVariableCorrelacion = getPeVariableRemoto().consultarIdPorIdProgramaNombre(bean.getObjeto().getPePrograma().getId(), nombreVariableCorrelacion);
        PeVariableValor variableValorCorrelacion = valoresVariable.stream()//buscamos el valor correlacionado de acuerdo a los valores en el flujo
                .filter(vv -> vv.getIdVariable().equals(idVariableCorrelacion))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(variableValorCorrelacion)) {

            if (Objects.isNull(bean.getObjeto().getId())) {
                return null;
            }
            //buscamos el valor correlacionado si no esta en el flujo y el afiliado existe
            variableValorCorrelacion = getPeVariableValorRemoto().consultarIdVariableIdAfiliado(idVariableCorrelacion, bean.getObjeto().getId());
        }
        return variableValorCorrelacion;
    }

    /**
     * Permite conocer si la variable valor tiene una validacion de correlacion
     * tipo 2 (previamente la lista debe venir filtrada por validacion tipo 5)
     */
    private boolean existeValidacionComodinRequerido(PeVariableValor variableValor) {
        return variableValor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(PeTipoValidacion.CORRELACION.getId()))
                .anyMatch(v -> {
                    PeCorrelacion peCorrelacion = GsonUtil.deserializar(v.getValor().toString(), new TypeToken<PeCorrelacion>() {
                    });
                    return peCorrelacion.getTipo().equals(PeTipoCorrelacion.COMODIN_REQUERIDO.getId());
                });
    }

    /**
     * Permite verificar la validacion de correlacion mayor
     */
    private boolean validacionCorrelacionMayor(GestionAfiliadosPEBean bean, PeVariableValor variableValor, PeVariableValor variableCorrelacion, boolean validacionComodinRequerido) {
        boolean valido = false;
        if (validacionComodinRequerido) {
            this.setComodinValidado(true);
            valido = this.validacionComodinRequerido(bean, variableValor, variableCorrelacion);

            if (this.isComodinCorrelacionado()) {
                return valido;
            }
        }

        String mensajeError = "Validaciones norma: La variable %s, debe ser mayor al valor de la variable correlacionada. (%s)";

        switch (variableValor.getTipo()) {
            case 0: //FECHA
                valido = variableValor.getFecha().after(variableCorrelacion.getFecha());
                if (!valido) {
                    variableValor.setError(true);
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), variableCorrelacion.getDescripcion()));
                }
                return valido;

            case 1://ENTERO
                valido = variableValor.getEntero() > variableCorrelacion.getEntero();

                if (!valido) {
                    variableValor.setError(true);
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), variableCorrelacion.getDescripcion()));

                }
                return valido;
            case 2://DECIMAL
                valido = variableValor.getDecimal().doubleValue() > variableCorrelacion.getDecimal().doubleValue();

                if (!valido) {
                    variableValor.setError(true);
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), variableCorrelacion.getDescripcion()));

                }
                return valido;
            default:
                return valido;
        }
    }

    /**
     * Permite verificar la validacion de correlacion menor
     */
    private boolean validacionCorrelacionMenor(GestionAfiliadosPEBean bean, PeVariableValor variableValor, PeVariableValor variableCorrelacion, boolean validacionComodinRequerido) {
        boolean valido = false;
        if (validacionComodinRequerido) {
            this.setComodinValidado(true);
            valido = this.validacionComodinRequerido(bean, variableValor, variableCorrelacion);

            if (this.isComodinCorrelacionado()) {
                return valido;
            }
        }

        String mensajeError = "Validaciones norma: La variable %s, debe ser menor al valor de la variable correlacionada. (%s)";

        switch (variableValor.getTipo()) {
            case 0: //FECHA
                valido = variableValor.getFecha().before(variableCorrelacion.getFecha());
                if (!valido) {
                    variableValor.setError(true);
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), variableCorrelacion.getDescripcion()));
                }
                return valido;

            case 1://ENTERO
                valido = variableValor.getEntero() < variableCorrelacion.getEntero();

                if (!valido) {
                    variableValor.setError(true);
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), variableCorrelacion.getDescripcion()));

                }
                return valido;
            case 2://DECIMAL
                valido = variableValor.getDecimal().doubleValue() < variableCorrelacion.getDecimal().doubleValue();

                if (!valido) {
                    variableValor.setError(true);
                    bean.addError(String.format(mensajeError, variableValor.getDescripcion(), variableCorrelacion.getDescripcion()));

                }
                return valido;
            default:
                return valido;
        }
    }

    /**
     * Permite verificar la validacion de comodin requerido
     */
    private boolean validacionComodinRequerido(GestionAfiliadosPEBean bean, PeVariableValor variableValor, PeVariableValor variableValorCorrelacion) {
        List<Object> valoresIgualdadVariable = this.obtenerValoresVariableIgualdad(variableValor);//valores comodines de variable en flujo de validacion
        List<Object> valoresIgualdadVariableCorrelacion = this.obtenerValoresVariableIgualdad(variableValorCorrelacion);//valores comodines de variable correlacionada

        boolean variableCorrelacionComodin = valoresIgualdadVariableCorrelacion.stream() //indica que la variable correlacionada tiene un valor comodin asignado
                .anyMatch(vi -> {
                    try {
                        return this.validarIgualdadValorVariableTipo(variableValorCorrelacion, vi);
                    } catch (ParseException ex) {
                        Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                });

        boolean variableComodin = valoresIgualdadVariable.stream() //indica que la variable tiene un valor comodin asignado
                .anyMatch(vi -> {
                    try {
                        return this.validarIgualdadValorVariableTipo(variableValor, vi);
                    } catch (ParseException ex) {
                        Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                });

        if (!variableCorrelacionComodin && variableComodin) {// la variable correlacionada no tiene un valor comodin pero la variable de entrada si

            variableValor.setError(true);
            bean.addError("Validaciones norma: La variable " + variableValor.getDescripcion() + ", debe tener un valor real asignado.");
            return false;
        }

        if (variableCorrelacionComodin && !variableComodin) {

            variableValor.setError(true);
            bean.addError("Validaciones norma: La variable " + variableValor.getDescripcion() + ", debe tener un comodin asignado.");
            return false;
        }

        if (variableCorrelacionComodin && variableComodin) {
            return true;
        }

        this.setComodinCorrelacionado(false);//indica que no ha sido validado ya que la variable correlacionada no tenia comodin asignado
        return true;
    }

    /**
     * Permite verificar la validacion de igualdad de correlacion
     */
    private boolean validacionIgualdadCorrelacionada(GestionAfiliadosPEBean bean, PeCorrelacion peCorrelacion, PeVariableValor variableValor, PeVariableValor variableCorrelacion) throws ParseException {

        if (peCorrelacion.getValorVariable() == null && peCorrelacion.getValorCorrelacion() == null) { //flujo donde el valor de la variable debe ser igual al de la correlacion (mismo tipo de variables) (igualdad) (flujo 3)
            //consultar valor de correlacion
            Object valorCorrelacion = variableCorrelacion.getValorObject();
            if (!this.validarIgualdadValorVariableTipo(variableValor, valorCorrelacion)) {//indica que la variableValor no es igual a variableValor (correlacionada)
                variableValor.setError(true);
                bean.addError("Validaciones norma: La variable " + variableValor.getDescripcion()
                        + ", debe tener el mismo valor de la variable correlacionada (" + variableCorrelacion.getDescripcion() + "). ");
                return false;
            }
            return true;
        }

        if (peCorrelacion.getValorCorrelacion() == null) { //flujo con dos o mas valores en la correlacion (comodines o igualdad) (flujo 2)
            //consultar valores comodines
            List<Object> valoresIgualdadVariableCorrelacion = this.obtenerValoresVariableIgualdad(variableCorrelacion);
            boolean variableCorrelacionComodin = valoresIgualdadVariableCorrelacion.stream() //indica que la variable correlacionada tiene un valor comodin asignado
                    .anyMatch(vi -> {
                        try {
                            return this.validarIgualdadValorVariableTipo(variableCorrelacion, vi);
                        } catch (ParseException ex) {
                            Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    });

            if (!variableCorrelacionComodin && this.validarIgualdadValorVariableTipo(variableValor, peCorrelacion.getValorVariable())) {
                variableValor.setError(true);
                bean.addError("Validaciones norma: La variable " + variableValor.getDescripcion()
                        + ", solo puede tener el valor " + this.obtenerValorVariableStr(variableValor, peCorrelacion.getValorVariable())
                        + " si la variable correlacionada (" + variableCorrelacion.getDescripcion()
                        + ") tiene algun valor comodin asignado. ");
                return false;
            }
            return true;
        }
        //flujo con un valor en especifico de correlacion (flujo 1)
        if (!this.validarIgualdadValorVariableTipo(variableCorrelacion, peCorrelacion.getValorCorrelacion())
                && this.validarIgualdadValorVariableTipo(variableValor, peCorrelacion.getValorVariable())) {
            variableValor.setError(true);
            bean.addError("Validaciones norma: La variable " + variableValor.getDescripcion()
                    + ", solo puede tener el valor " + this.obtenerValorVariableStr(variableValor, peCorrelacion.getValorVariable())
                    + " si la variable correlacionada (" + variableCorrelacion.getDescripcion()
                    + ") tiene valor " + this.obtenerValorVariableStr(variableCorrelacion, peCorrelacion.getValorCorrelacion()) + ". ");
            return false;
        }
        return true;
    }

    /**
     * Permite verificar la igualdad de una variable valor con un OBJECT
     */
    private boolean validarIgualdadValorVariableTipo(PeVariableValor variableValor, Object valor) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        switch (variableValor.getTipo()) {

            case 0://FECHA
                try {
                return variableValor.getFecha().equals(sdf.parse(valor.toString()));
            } catch (ParseException e) {
                return false;
            }
            case 1: //ENTERO
                double enteroDouble = Double.parseDouble(valor.toString());
                return Objects.equals(variableValor.getEntero(), (int) enteroDouble);

            case 2://DECIMAL
                double numeroDouble = Double.parseDouble(valor.toString());
                return variableValor.getDecimal().compareTo(new BigDecimal(String.valueOf(numeroDouble))) == 0;//true si es 0
            case 3://TEXTO
                return Objects.equals(variableValor.getTexto(), valor.toString());
            case 4://TEXTO_LARGO
                return Objects.equals(variableValor.getTextoLargo(), valor.toString());
            default:
                return false;
        }
    }

    private String obtenerValorVariableStr(PeVariableValor variableValor, Object valor) {
        switch (variableValor.getTipo()) {

            case 1: //ENTERO
                double enteroDouble = Double.parseDouble(valor.toString());
                int valorEntero = (int) enteroDouble;
                return String.valueOf(valorEntero);
            default://FECHA, DECIMAL, TEXTO Y TEXTO LARGO
                return valor.toString();
        }
    }

    private boolean tieneCorrelacionIgualdadFlujo3(PeVariableValor variableValor) {
        if (variableValor.getValidaciones() != null && !variableValor.getValidaciones().isEmpty()) {
            return variableValor.getValidaciones().stream()
                    .filter(v -> v.getTipo().equals(PeTipoValidacion.CORRELACION.getId()))
                    .map(v -> GsonUtil.deserializar(v.getValor().toString(), new TypeToken<PeCorrelacion>() {
            }))
                    .filter(c -> c.getTipo().equals(PeTipoCorrelacion.IGUALDAD.getId()))
                    .anyMatch(c -> c.getValorVariable() == null && c.getValorCorrelacion() == null);
        }
        return false;
    }

    /**
     * asigna resultado temporal deacuerdo a la ultima eta y asignacion de un
     * insumo calculo
     */
    private boolean asignarResultadoTemporal(Double resultadoOperacion, boolean ultimaEtapa, String asignacionTemporal, List<PeOperando> operandos) {

        if (!ultimaEtapa && asignacionTemporal != null) {
            PeOperando operandoAsignacion = operandos.stream()
                    .filter(operando -> operando.getNombre().equals(asignacionTemporal))
                    .findFirst()
                    .orElse(null);
            operandoAsignacion.setValor(resultadoOperacion);
            return true;
        }
        return false;
    }

    /**
     * obtiene el valor de un operando a partir de su nombre
     */
    private double obtenerValorOperando(String nombreOperando, List<PeOperando> operandos) {
        return operandos.stream()
                .filter(o -> o.getNombre().equals(nombreOperando))
                .findFirst()
                .map(o -> new BigDecimal(o.getValor().toString()).doubleValue())
                .orElseThrow(() -> new IllegalArgumentException("Operando no encontrado: " + nombreOperando));
    }

    /**
     * obtiene el valor tipo fecha de un operando a partir de su nombre
     */
    private LocalDate obtenerValorOperandoTipoFecha(String nombreOperando, List<PeOperando> operandos) {
        return operandos.stream()
                .filter(o -> o.getNombre().equals(nombreOperando))
                .findFirst()
                .map(o -> LocalDate.parse(o.getValor().toString()))
                .orElseThrow(() -> new IllegalArgumentException("Operando no encontrado: " + nombreOperando));
    }

    /**
     * opera resta de una etapa de insumo calculo
     */
    private Double operarSuma(PeEtapaCalculo etapa, List<PeOperando> operandos) {
        double resultado = (double) this.obtenerValorOperando(etapa.getOperandos().get(0), operandos);
        for (int i = 1; i < etapa.getOperandos().size(); i++) {
            resultado += (double) obtenerValorOperando(etapa.getOperandos().get(i), operandos);
        }
        return resultado;
    }

    /**
     * opera resta de una etapa de insumo calculo
     */
    private Double operarResta(PeEtapaCalculo etapa, List<PeOperando> operandos, boolean operacionFecha) {
        if (operacionFecha) {
            LocalDate fechaInicial = this.obtenerValorOperandoTipoFecha(etapa.getOperandos().get(0), operandos);
            long diferenciaDias = 0;

            for (int i = 1; i < etapa.getOperandos().size(); i++) {
                LocalDate fechaSiguiente = this.obtenerValorOperandoTipoFecha(etapa.getOperandos().get(i), operandos);
                diferenciaDias += Math.abs(ChronoUnit.DAYS.between(fechaSiguiente, fechaInicial));
                fechaInicial = fechaSiguiente;
            }

            return (double) diferenciaDias;
        } else {
            double resultado = (double) this.obtenerValorOperando(etapa.getOperandos().get(0), operandos);
            for (int i = 1; i < etapa.getOperandos().size(); i++) {
                resultado -= (double) obtenerValorOperando(etapa.getOperandos().get(i), operandos);
            }
            return resultado;
        }
    }

    /**
     * opera multiplicacion de una etapa de insumo calculo
     */
    private Double operarMultiplicacion(PeEtapaCalculo etapa, List<PeOperando> operandos) {
        double resultado = (double) this.obtenerValorOperando(etapa.getOperandos().get(0), operandos);
        for (int i = 1; i < etapa.getOperandos().size(); i++) {
            resultado *= (double) obtenerValorOperando(etapa.getOperandos().get(i), operandos);
        }
        return resultado;
    }

    /**
     * opera division de una etapa de insumo calculo
     */
    private Double operarDivision(PeEtapaCalculo etapa, List<PeOperando> operandos) {
        double dividendo = obtenerValorOperando(etapa.getOperandos().get(0), operandos);
        for (int i = 1; i < etapa.getOperandos().size(); i++) {
            double divisor = obtenerValorOperando(etapa.getOperandos().get(i), operandos);
            if (divisor == 0) {
                throw new ArithmeticException("División por cero no permitida");
            }
            dividendo /= divisor;
        }
        return dividendo;
    }

    /**
     * opera potencia de una etapa de insumo calculo
     */
    private Double operarPotencia(PeEtapaCalculo etapa, List<PeOperando> operandos) {
        PeOperando operando = operandos.stream()
                .filter(o -> o.getNombre().equals(etapa.getOperandos().get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Operando no encontrado"));
        int exponente = 2;
        if (etapa.getOperacion().length() > 1) {
            exponente = Integer.parseInt(etapa.getOperacion().substring(1));
        }
        return Math.pow(obtenerValorOperando(operando.getNombre(), operandos), exponente);
    }

    /**
     * obtiene los operandos (campo variables) de insumo calculo
     */
    private List<PeOperando> obtenerOperandosInsumoCalculo(PeInsumoCalculo insumoCalculo, List<PeVariableValor> variablesValor) {
        return variablesValor.stream()
                .filter(variableValor -> insumoCalculo.getVariables().contains(variableValor.getNombre()))
                .map(PeOperando::new)
                .collect(Collectors.toList());
    }

    /**
     * permite la ejecucion de un insumo calculo y sus etapas asignando la
     * variable valor con su resultado respectivo para ser visualizado en el
     * listado de variables de valor
     */
    private void ejecutarInsumoCalculo(GestionAfiliadosPEBean bean, PeVariable variable, List<PeVariableValor> variablesValor) {
        PeInsumoCalculo insumoCalculo = variable.getInsumoCalculo();
        Double valorCalculo = null;
        List<PeOperando> operandos = this.obtenerOperandosInsumoCalculo(insumoCalculo, variablesValor);
        int contadorEtapas = 1;
        int tamEtapas = insumoCalculo.getEtapas().size();

        for (PeEtapaCalculo etapa : insumoCalculo.getEtapas()) {
            boolean ultimaEtapa = contadorEtapas == tamEtapas;
            boolean asignarResultadoTemporal = false;
            Double resultado = null;

            switch (etapa.getOperacion()) {
                case "+":
                    resultado = this.operarSuma(etapa, operandos);
                    break;
                case "-":
                    boolean operacionFecha = operandos.stream().allMatch(o -> o.getTipo().equals(PeTipoVariable.FECHA.getId()));
                    resultado = this.operarResta(etapa, operandos, operacionFecha);
                    break;

                case "*":
                    resultado = this.operarMultiplicacion(etapa, operandos);
                    break;
                case "/":
                    resultado = this.operarDivision(etapa, operandos);
                    break;
                default:
                    if (etapa.getOperacion().matches(PeConstantes.PATRON_OPERACION_POTENCIA)) {
                        resultado = this.operarPotencia(etapa, operandos);
                    }
                    break;
            }

            if (resultado != null) {
                asignarResultadoTemporal = this.asignarResultadoTemporal(resultado, ultimaEtapa, etapa.getAsignacionTemporal(), operandos);
                if (!asignarResultadoTemporal) {
                    valorCalculo = resultado;
                }
            }

            contadorEtapas++;
        }
        PeVariableValor peVariableValor = new PeVariableValor(variable);
        peVariableValor.setValorCalculo(valorCalculo);
        bean.getListadoValoresVariables().add(peVariableValor);
    }

    /**
     * permite la ejecucion de un insumo calculo y sus etapas asignando la
     * variable valor con su resultado respectivo para ser visualizado en el
     * listado de variables de valor
     */
    private void verificacionVariablesCalculo(GestionAfiliadosPEBean bean) throws Exception {
        List<PeVariable> variablesCalculo = getPeVariableRemoto()
                .consultarPorIdProgramaTipo(bean.getObjeto().getPePrograma().getId(), (short) PeTipoVariable.CALCULO.getId());

        for (PeVariable variable : variablesCalculo) {
            if (variable.getInsumoCalculo() != null) {
                //validacion variables asociadas
                List<PeVariableValor> variablesValor = bean.getListadoValoresVariables()
                        .stream()
                        .filter(vv -> variable.getInsumoCalculo().getVariables().contains(vv.getNombre()) && this.verificarValorCalculo(vv))
                        .collect(Collectors.toList()); //cuenta si la variable esta contenida en las variables del insumo y si tiene un valor
                if (variablesValor.size() == variable.getInsumoCalculo().getVariables().size()) {
                    this.ejecutarInsumoCalculo(bean, variable, variablesValor);
                }
            }
        }
    }

    /**
     * verifica si existe el valor de una variable la cual sea distinta de nulo
     * y 0.0
     */
    private boolean verificarValorCalculo(PeVariableValor vv) {
        // Verificar si el valor es nulo
        if (vv.getValorObject() == null) {
            return false;
        }

        // Si el tipo es fecha, devolver true directamente
        if (vv.getTipo().equals(PeTipoVariable.FECHA.getId())) {
            return true;
        }

        double valorDouble = new BigDecimal(vv.getValorObject().toString()).doubleValue();
        return valorDouble > 0.0;
    }

    private void crearCambioPrograma(GestionAfiliadosPEBean bean) {
        try {
            bean.setObjeto(getPeAfiliadosProgramaRemoto().consultar(bean.getObjeto().getId()));
            PeProgramaTraslado peProgramaTraslado = new PeProgramaTraslado();
            peProgramaTraslado.setAfiliadoAseg(new AsegAfiliado(bean.getObjeto().getAsegAfiliado().getId()));
            peProgramaTraslado.setAfiliadoPrograma(new PeAfiliadosPrograma(bean.getObjeto().getId()));
            peProgramaTraslado.setProgramaOrigen(new PePrograma(bean.getObjeto().getPePrograma().getId()));
            peProgramaTraslado.setProgramaDestino(new PePrograma());
            bean.setProgramaTraslado(peProgramaTraslado);

            short generoAfiliado = bean.getObjeto().getAsegAfiliado().getGenero().equals(PeConstantes.APLICA_SEXO_FEMENINO_CODIGO)
                    ? (short) PeConstantes.APLICA_SEXO_FEMENINO : (short) PeConstantes.APLICA_SEXO_MASCULINO;
            List<PePrograma> resultado = getPeProgramaRemoto()
                    .consultarPorCodigoAgrupadorGeneroAfiliado(bean.getObjeto().getPePrograma().getMaeAgrupadorCodigo(),
                            generoAfiliado);
            resultado.removeIf(p -> p.getId().equals(bean.getObjeto().getPePrograma().getId())); //remueve el programa donde el afiliado se encuentra actualmente
            bean.setListaProgramasAgrupador(resultado);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    /**
     * Guarda un cambio de programa de un afiliado, inactivando el afiliado del
     * programa origen, trasladando su informacion y sus diagnosticos, asi como
     * el registro del traslado.
     *
     * si el afiliado cuenta con un registro inactivo en el programa destino se
     * activa.
     */
    private void guardarCambioPrograma(GestionAfiliadosPEBean bean) {
        try {
            //validar si no hay un registro de afiliado con el programa de destino 
            Integer activo = getPeAfiliadosProgramaRemoto()
                    .consultarCantidadProgramaEstado(bean.getProgramaTraslado().getAfiliadoAseg().getId(), bean.getProgramaTraslado().getProgramaDestino().getId(), PeConstantes.PE_PROGRAMA_ACTIVO);
            if (activo > 0) {
                bean.addError("El afiliado ya tiene una matrícula en el programa " + bean.getProgramaTraslado().getProgramaDestino().getDescripcionPrograma() + " con estado ACTIVO. ");
                return;
            }

            List<PeAfiliadosPrograma> afiliadosInactivosProgramaDestino = getPeAfiliadosProgramaRemoto()
                    .consultarAfiliadoPorProgramaEstado(bean.getProgramaTraslado().getAfiliadoAseg().getId(), bean.getProgramaTraslado().getProgramaDestino().getId(), Boolean.FALSE);
            if (afiliadosInactivosProgramaDestino.isEmpty()) {
                //afiliado apto para traslado                
                // creando afiliado con programa destino
                PeAfiliadosPrograma afiliadoTraslado = new PeAfiliadosPrograma(bean.getObjeto());
                afiliadoTraslado.setId(null);
                afiliadoTraslado.setActivo(true);
                afiliadoTraslado.setPePrograma(bean.getProgramaTraslado().getProgramaDestino());
                afiliadoTraslado.setGnUsuario(new Usuario(bean.getConexion().getUsuario().getId()));
                bean.auditoriaGuardar(afiliadoTraslado);
                //insertar afiliado
                afiliadoTraslado.setId(this.getPeAfiliadosProgramaRemoto().insertar(afiliadoTraslado));

                //insertar diagnosticos
                for (PeAfiliadoDiagnostico diagnostico : bean.getObjeto().getPeAfiliadoDiagnosticoList()) {
                    diagnostico.setId(null);
                    diagnostico.setTerminalModifica(null);
                    diagnostico.setUsuarioModifica(null);
                    diagnostico.setFechaHoraModifica(null);
                    bean.auditoriaGuardar(diagnostico);
                    diagnostico.setPeAfiliadosProgramasId(afiliadoTraslado);
                    getPeAfiliadoDiagnosticoRemoto().insertar(diagnostico);
                }

            } else {
                //el afiliado ya cuenta con un registro en estado INACTIVO
                PeAfiliadosPrograma afiliadoExistenteProgramaDestino = afiliadosInactivosProgramaDestino.get(0);
                //activando afiliado en programa destino
                afiliadoExistenteProgramaDestino.setActivo(true);//activando 
                bean.auditoriaModificar(afiliadoExistenteProgramaDestino);
                this.getPeAfiliadosProgramaRemoto().actualizarEstadoActivo(afiliadoExistenteProgramaDestino);
            }
            //inactivar afiliado con programa origen           
            bean.getObjeto().setActivo(false);//inactivado 
            bean.auditoriaModificar(bean.getObjeto());
            this.getPeAfiliadosProgramaRemoto().actualizarEstadoActivo(bean.getObjeto());

            //guardar traslado
            bean.auditoriaGuardar(bean.getProgramaTraslado());
            this.getPeProgramaTrasladoRemoto().insertar(bean.getProgramaTraslado());

            bean.addMensaje("El cambio de programa se realizó correctamente. ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarTrasladoProgramas(GestionAfiliadosPEBean bean) {
        try {
            bean.getParamConsulta(3).setCantidadRegistros(getPeProgramaTrasladoRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setListaTrasladoProgramas(getPeProgramaTrasladoRemoto().consultarLista(bean.getParamConsulta(3)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<PeCorrelacion> obtenerValidacionesCorrelacion(PeVariableValor variableValor) {
        return variableValor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(PeTipoValidacion.CORRELACION.getId()))
                .map(v -> GsonUtil.deserializar(v.getValor().toString(), new TypeToken<PeCorrelacion>() {
        }))
                .collect(Collectors.toList());
    }

//    private PeVariableValor obtenerValorCorrelacionFlujo(GestionAfiliadosPEBean bean, int idVariableCorrelacion) {
//        return bean.getListadoValoresVariables().stream()//buscamos el valor correlacionado de acuerdo a los valores en el flujo
//                .filter(vv -> vv.getIdVariable().equals(idVariableCorrelacion))
//                .findFirst()
//                .orElse(null);
//
//    }
    /**
     * Permite validar cuando una variable es apta para actualizar teniendo en
     * cuenta sus correlaciones (de esta manera valida de nuevo la variable si
     * la correlacion tiene algun cambio de valor dentro del flujo y detectar
     * errores)
     */
    private boolean validacionPrimariaCorrelacionActualizar(GestionAfiliadosPEBean bean, PeVariableValor variableValor) {
        if (variableValor.getValidaciones() == null || variableValor.getValidaciones().isEmpty()) {
            return false;
        }

        List<PeCorrelacion> validacionesCorrelacion = this.obtenerValidacionesCorrelacion(variableValor);

        if (validacionesCorrelacion == null || validacionesCorrelacion.isEmpty()) {
            return false;
        }
        return validacionesCorrelacion.stream().anyMatch(c -> {
            try {
                if (c.getTipo().equals(PeTipoCorrelacion.COMBINACION.getId())) {
                    return true;
                }
                //si la correlacion tiene un valor distinto al almacenado sera apta para validar la actualizacion
                Integer idVariableCorrelacion = getPeVariableRemoto().consultarIdPorIdProgramaNombre(bean.getObjeto().getPePrograma().getId(), c.getCorrelacion());
                if (idVariableCorrelacion == null) {//no existe la variable de correlacion
                    return false;
                }
                PeVariableValor variableValorCorrelacion = this.obtenerValorVariableCorrelacion(bean, bean.getListadoValoresVariables(), c.getCorrelacion());
                if (variableValorCorrelacion == null) {
                    return false;
                }
                PeVariableValorAlmacenado valorAlmacenadoCorrelacion = getPeVariableValorRemoto().consultarValorPorIdVariableIdAfiliado(variableValorCorrelacion.getTipo(), idVariableCorrelacion, bean.getObjeto().getId());
                if (valorAlmacenadoCorrelacion == null || valorAlmacenadoCorrelacion.getValor() == null) {//no hay valorAlmacenado
                    return false;
                }
                return !this.validarIgualdadValorVariableTipo(variableValorCorrelacion, valorAlmacenadoCorrelacion.getValor());

            } catch (Exception ex) {
                Logger.getLogger(GestionAfiliadosPEServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;

            }
        });
    }

    /**
     * Permite verificar si una variable valor tiene un valor almacenado (db)
     * igual al del flujo.
     *
     * 03/04/2025
     *
     * @param variableValor
     * @return boolean
     */
    private boolean verificacionValorAlmacenado(GestionAfiliadosPEBean bean, PeVariableValor variableValor) throws Exception {
        PeVariableValorAlmacenado variableValorAlmacenado
                = getPeVariableValorRemoto().consultarValorPorIdVariableIdAfiliado(variableValor.getTipo(), variableValor.getIdVariable(),
                        bean.getObjeto().getId());

        if (variableValorAlmacenado == null) {
            return false;
        }
        return this.validarIgualdadValorVariableTipo(variableValor,
                variableValorAlmacenado.getValor());
    }

    /**
     * Validacion de combinacion correlacionada tipo 4
     *
     * @param bean
     * @param variablesValor
     * @param peCorrelacion
     * @param variableValor
     * @return
     * @throws Exception
     */
    public boolean validacionCombinacionCorrelacionada(GestionAfiliadosPEBean bean,
            List<PeVariableValor> variablesValor, PeCorrelacion peCorrelacion,
            PeVariableValor variableValor) throws Exception {

        Map<String, List<Object>> combinaciones = peCorrelacion.getCombinaciones();
        List<PeVariableValor> variablesValorCorrelacionadas = new ArrayList<>();
        List<Boolean> coincidencias = new ArrayList<>();

        for (Map.Entry<String, List<Object>> llave : combinaciones.entrySet()) {
            this.agregarVariableParaValidarCombinacionCorrelacionada(bean, variablesValor, variablesValorCorrelacionadas,
                    llave.getKey(), variableValor);
        }
        int indice = 0;
        for (Map.Entry<String, List<Object>> llave : combinaciones.entrySet()) {
            coincidencias.add(this.validacionCoincidenciaCombinacionCorrelacionada(
                    variablesValorCorrelacionadas.get(indice),//se envia la ultima variable valor agregada en el flujo
                    llave.getValue(),
                    peCorrelacion));
            indice++;
        }
        boolean resultado = false;
        if (peCorrelacion.getMatch().equals(Boolean.FALSE)) {
            resultado = coincidencias.stream().anyMatch(coincidencia -> coincidencia);
        } else {
            resultado = coincidencias.stream().allMatch(coincidencia -> coincidencia);
        }
        if (!resultado) {
            String variablesError = variablesValorCorrelacionadas.stream()
                    .map(PeVariableValor::getDescripcion)
                    .collect(Collectors.joining(", "));

            bean.addError("Validaciones norma: Para las variables " + variablesError
                    + ", debe tener almenos una con diagnostico confirmado. ");

            Set<Integer> idsVariablesError = variablesValorCorrelacionadas.stream()
                    .map(PeVariableValor::getIdVariable)
                    .collect(Collectors.toSet());

            bean.getListadoValoresVariables().stream()
                    .filter(v -> idsVariablesError.contains(v.getIdVariable()))
                    .forEach(v -> v.setError(true));
            return false;
        }
        return resultado;
    }

    /**
     * metodo auxiliar para obtener las variables valor a validar en la
     * combinacion corelacionada
     *
     * @param bean
     * @param variablesValor
     * @param variablesValorCorrelacionadas
     * @param llave
     * @param variableValor
     * @throws Exception
     */
    private void agregarVariableParaValidarCombinacionCorrelacionada(GestionAfiliadosPEBean bean,
            List<PeVariableValor> variablesValor, List<PeVariableValor> variablesValorCorrelacionadas,
            String llave, PeVariableValor variableValor) throws Exception {

        if (llave.equals(variableValor.getNombre())) {
            variablesValorCorrelacionadas.add(variableValor);
        } else {
            variablesValorCorrelacionadas.add(this.obtenerValorVariableCorrelacion(bean, variablesValor, llave));
        }
    }

    /**
     * metodo auxiliar para validar la coincidencia de la combinacion
     * correlacionada el metodo evalua de acuerdo al atributo *MATCH*
     *
     * @param variableValor
     * @param valores
     * @param peCorrelacion
     * @return
     * @throws ParseException
     */
    private Boolean validacionCoincidenciaCombinacionCorrelacionada(PeVariableValor variableValor,
            List<Object> valores, PeCorrelacion peCorrelacion) throws ParseException {
        boolean debeCoincidir = peCorrelacion.getMatch();
        for (Object valor : valores) {
            boolean coincide = this.validarIgualdadValorVariableTipo(variableValor, valor);

            if (!debeCoincidir && coincide) {
                return false; // No debe coincidir pero encontró coincidencia
            }
            if (debeCoincidir && coincide) {
                return true; // Debe coincidir y encontró coincidencia
            }
        }
        return !debeCoincidir; // Si debeCoincidir=true y no encontró: false, si false y no encontró: true
    }
}
